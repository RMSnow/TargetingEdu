package org.sklse.targetedcourse.controller;

import io.swagger.annotations.ApiOperation;
import org.sklse.targetedcourse.bean.*;
import org.sklse.targetedcourse.repository.AssignmentRepository;
import org.sklse.targetedcourse.repository.StuClassRepository;
import org.sklse.targetedcourse.repository.StudentRepository;
import org.sklse.targetedcourse.service.StuClassService;
import org.sklse.targetedcourse.service.TeacherService;
import org.sklse.targetedcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("class")
public class StuClassController {

    @Autowired
    private StuClassService stuClassService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StuClassRepository stuClassRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserService userService;


    @ApiOperation(value = "新增班级")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> save(@RequestBody StuClass entity) throws Exception {
        Map<String, Object> result = new HashMap<>();
        stuClassService.save(entity);
        result.put("id", entity.getClassUid());
        return result;
    }

//    @ApiOperation(value = "删除班级")
//    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    public void delete(@RequestParam("id") String id) {
//        stuClassService.delete(id);
//    }



    @ApiOperation(value = "按照状态查找班级")
    @RequestMapping(value = "/findAllByStatus", method = RequestMethod.GET)
    public List<StuClass> findAllByNoLike(int status) {
        return stuClassService.findAllByStatus(status);
    }

    @ApiOperation(value = "班级学生列表")
    @RequestMapping(value = "/findStudentsByClassNumber", method = RequestMethod.GET)
    public List<Student> findStudentsByClassNumber(String number) {
        StuClass stuClass = stuClassRepository.findByClassUid(number);
        return stuClass.getStudents();
    }


    @ApiOperation(value = "班级学生列表")
    @RequestMapping(value = "/findPageStudentsByClassNumber", method = RequestMethod.GET)
    public Page<Student> findPageStudentsByClassNumber(@RequestParam(value = "currentPage", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "numPerPage", defaultValue = "10") Integer pageSize,
                                                       @RequestParam(value = "stuclass") String stuclass) {
        Pageable pageable = new PageRequest(page, pageSize);
        return studentRepository.findByStuclassId(stuclass, pageable);
    }

/*
    @ApiOperation(value = "添加学生")
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public void addClass(@RequestBody Student student, @RequestParam(value = "class_id") String class_id) throws Exception {

        StuClass stuClass = stuClassRepository.findByClassUid(class_id);
        studentRepository.save(student);
        stuClass.getStudents().add(student);
        stuClassService.save(stuClass);

    }
*/
   @ApiOperation(value = "删除班级学生")
   @RequestMapping(value="/deletestudent",method = RequestMethod.DELETE)
   public Page<Student> deletestudent(@RequestParam(value = "currentPage",defaultValue = "0") Integer page,
                                   @RequestParam(value = "numPerPage",defaultValue = "10") Integer pageSize,
                                   @RequestParam(value = "studentUid") String studentUid,
                                   @RequestParam (value="classid") String classid){
       Pageable pageable = new PageRequest(page, pageSize);
       Student student=studentRepository.findByStudentUid(studentUid);
       StuClass stuClass=stuClassRepository.findByClassUid(classid);
       stuClass.getStudents().remove(student);
       stuClassRepository.save(stuClass);
       return studentRepository.findByStuclassId(classid,pageable);

   }
    @ApiOperation(value = "获取班级作业")
    @GetMapping(value = "/getHomeworkList")
    public  ResponseEntity<ResultModel> getHomeworkList(String classUid, HttpServletRequest request) throws ServletException {
        Teacher teacher = userService.currentTeacher(request);
        StuClass stuClass = stuClassRepository.findByClassUid(classUid);

        if (!teacherService.isMyClass(teacher, stuClass)) {
            return new ResponseEntity<>(ResultModel.error(HttpStatus.FORBIDDEN, "无权限操作"), HttpStatus.FORBIDDEN);
        }


        List<Assignment> assignmentList = stuClass.getAssignmentList();
        return new ResponseEntity<>(ResultModel.error(HttpStatus.OK, assignmentList), HttpStatus.OK);

    }

    @ApiOperation(value = "获取班级详情")
    @GetMapping(value = "/findByNumber")
    public StuClass findByNumber(@RequestParam String class_id) {

        StuClass stuClass = stuClassRepository.findByClassUid(class_id);
        return stuClass;

    }

}

