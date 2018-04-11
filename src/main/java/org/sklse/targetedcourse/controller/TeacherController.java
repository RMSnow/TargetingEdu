package org.sklse.targetedcourse.controller;

import io.swagger.annotations.ApiOperation;
import org.sklse.targetedcourse.bean.*;
import org.sklse.targetedcourse.repository.*;
import org.sklse.targetedcourse.service.StuClassService;
import org.sklse.targetedcourse.service.TeacherService;
import org.sklse.targetedcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Alison on 2017/4/13.
 */
@RestController
@RequestMapping("teacher")
public class TeacherController {


    @Autowired
    private UserService userService;
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StuClassService stuClassService;

    @Autowired
    private StuClassRepository stuClassRepository;


    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GuardianRepository guardianRepository;





    @ApiOperation(value = "老师布置的全部作业")
    @GetMapping("assignmentList")
    public List<Assignment> assignmentList(HttpServletRequest request) throws ServletException {
        Teacher teacher = userService.currentTeacher(request);
        return teacher.getAssignments();
    }


    @ApiOperation(value = "班级列表")
    @GetMapping(value = "classList")
    public  ResponseEntity<ResultModel> classList(HttpServletRequest request) throws ServletException {
        Teacher teacher = userService.currentTeacher(request);
        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK, teacher.getStuClasses()), HttpStatus.OK);

     }
    @ApiOperation(value = "班级列表")
    @RequestMapping(value = "findPageClass", method = RequestMethod.GET)
    public Page<StuClass> findAllClass(@RequestParam(value = "currentPage",defaultValue = "0") Integer page,
                                       @RequestParam(value = "numPerPage",defaultValue = "10") Integer pageSize,
                                       HttpServletRequest request) throws ServletException{
        Pageable pageable = new PageRequest(page, pageSize);
        Teacher teacher = userService.currentTeacher(request);
        long teacher_id = teacher.getId();
        return stuClassRepository.find(teacher_id, pageable);
    }


    @ApiOperation(value = "注销")
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public void logout(HttpSession session) {
        session.invalidate();
    }


    @ApiOperation(value = "添加班级")
    @PostMapping("addClass")
    public ResponseEntity<ResultModel> addClass(StuClass stuClass, HttpServletRequest request) throws Exception {

        Teacher teacher = userService.currentTeacher(request);
        stuClass.setStart_time(new Date());
        stuClassService.save(stuClass);
        teacher.getStuClasses().add(stuClass);
        teacherService.save(teacher);
        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK, "操作成功"), HttpStatus.OK);
    }

    @GetMapping("getTeacherDetail")
    public Teacher getTeacherDetail(HttpServletRequest request) throws Exception{
        System.out.println(userService.currentTeacher(request).getName());
        return userService.currentTeacher(request);
    }

    @ApiOperation(value = "删除班级")
    @RequestMapping(value = "/deleteClass",method = RequestMethod.DELETE)
    public void deleteclass(@RequestParam(value = "classid") String classid, HttpServletRequest request) throws Exception {

        Teacher teacher = userService.currentTeacher(request);
        Long teacherid = teacher.getId();
        StuClass stuClass = stuClassRepository.findByClassUid(classid);
        teacher.getStuClasses().remove(stuClass);
        teacherRepository.save(teacher);
    }

    @ApiOperation(value = "添加学生到班级")
    @PostMapping("addStudent")
    public ResponseEntity<ResultModel> addStudent(String studentNumber, String classUid, HttpServletRequest request) throws Exception {
        Teacher teacher = userService.currentTeacher(request);
        StuClass stuClass = stuClassRepository.findByClassUid(classUid);

        if (!teacherService.isMyClass(teacher, stuClass)) {
            return new ResponseEntity<>(ResultModel.error(HttpStatus.FORBIDDEN, "无权限操作"), HttpStatus.FORBIDDEN);
        }

        Student student = studentRepository.findByStudentNumber(studentNumber);
        if (student == null) {
            return new ResponseEntity<>(ResultModel.error(HttpStatus.NOT_FOUND, "学生不存在"), HttpStatus.NOT_FOUND);
        }
        if (stuClassService.isInClass(stuClass,student)){
            return new ResponseEntity<>(ResultModel.error(HttpStatus.FORBIDDEN, "不能重复添加学生"), HttpStatus.FORBIDDEN);

        }

        stuClass.getStudents().add(student);
        stuClassRepository.save(stuClass);
        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK, "操作成功"), HttpStatus.OK);

    }


}
