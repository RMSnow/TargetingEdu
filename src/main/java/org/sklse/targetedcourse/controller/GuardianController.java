package org.sklse.targetedcourse.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.sklse.targetedcourse.bean.Guardian;
import org.sklse.targetedcourse.bean.ResultModel;
import org.sklse.targetedcourse.bean.Student;
import org.sklse.targetedcourse.repository.GuardianRepository;
import org.sklse.targetedcourse.repository.StudentRepository;
import org.sklse.targetedcourse.service.GuardianService;
import org.sklse.targetedcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by MIC on 2017/7/19.
 */
@RestController
@RequestMapping("guardian")
public class GuardianController {
    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private GuardianService guardianService;


    @ApiOperation(value = "添加孩子")
    @ApiImplicitParam(name = "student", value = "学生实体student", required = true, dataType = "Student")
    @PostMapping(value = "/addChild")
    public ResponseEntity<ResultModel> addChild(Student student, HttpServletRequest request) throws ServletException {

        Guardian guardian = userService.currentGuardian(request);
        if (student.getStudentUid() != null) {
            return new ResponseEntity<>(ResultModel.error(HttpStatus.FORBIDDEN,"禁止提交uid参数"), HttpStatus.FORBIDDEN);
        }


        studentRepository.save(student);
        guardian.getStudents().add(student);
        guardianRepository.save(guardian);
        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK,"操作成功"), HttpStatus.OK);
    }

    @ApiOperation(value = "修改孩子信息")
    @RequestMapping(value = "/updateChild", method = RequestMethod.POST)
    public ResponseEntity<ResultModel>  updateChild( Student student, HttpServletRequest request) throws ServletException {
        Guardian guardian = userService.currentGuardian(request);
        if (student == null || guardianService.isMyChild(guardian, student)) {
            return new ResponseEntity<>(ResultModel.error(HttpStatus.FORBIDDEN,"Failure"), HttpStatus.FORBIDDEN);
        }
        studentRepository.save(student);
        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK,"操作成功"), HttpStatus.OK);


    }

    @ApiOperation(value = "删除孩子")
    @RequestMapping(value = "deleteChild/{studentUid}",method = RequestMethod.DELETE)
    public ResponseEntity<ResultModel> deleteChild(@PathVariable("studentUid")  String studentUid, HttpServletRequest request) throws Exception {

        Guardian guardian = userService.currentGuardian(request);

        Student student = studentRepository.findByStudentUid(studentUid);

        if (!guardianService.isMyChild(guardian, student)) {
            return new ResponseEntity<>(ResultModel.error(HttpStatus.FORBIDDEN,"操作失败"), HttpStatus.FORBIDDEN);
        }

        guardian.getStudents().remove(student);
        studentRepository.delete(student);
        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK,"操作成功"), HttpStatus.OK);


    }


    @ApiOperation(value = "孩子列表")
    @GetMapping(value = "/children")
    public  List<Student> children(HttpServletRequest request) throws ServletException {


        Guardian guardian = userService.currentGuardian(request);


        return guardian.getStudents();
    }

    @ApiOperation(value = "设定当前孩子")
    @PostMapping(value = "/setCurrentChild")
    public ResponseEntity<ResultModel> setCurrentChild(String studentUid, HttpServletRequest request) throws ServletException {
        Guardian guardian = userService.currentGuardian(request);


        List<Student> students = guardian.getStudents();

        Student student = studentRepository.findByStudentUid(studentUid);
        if (!students.contains(student)) {
            return new ResponseEntity<>(ResultModel.error(HttpStatus.FORBIDDEN,"Failure"), HttpStatus.FORBIDDEN);
        }
        guardian.setCurrentChildUid(studentUid);
        guardianRepository.save(guardian);
        return new ResponseEntity<>(ResultModel.ok("Success"), HttpStatus.OK);

    }


    @ApiOperation(value = "获取当前孩子")
    @GetMapping(value = "/getCurrentChild")
    public ResponseEntity<ResultModel> getCurrentChild(HttpServletRequest request) throws ServletException {

        Guardian guardian = userService.currentGuardian(request);

        String studentUid = guardian.getCurrentChildUid();
        Student student = studentRepository.findByStudentUid(studentUid);

        if (student == null) {
            return new ResponseEntity<>(ResultModel.error(HttpStatus.NOT_FOUND,"Failure"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(ResultModel.ok(student), HttpStatus.OK);


    }


}
