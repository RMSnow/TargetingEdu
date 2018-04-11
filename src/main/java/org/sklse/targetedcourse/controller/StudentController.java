package org.sklse.targetedcourse.controller;

import io.swagger.annotations.ApiOperation;
import org.sklse.targetedcourse.bean.*;
import org.sklse.targetedcourse.repository.*;
import org.sklse.targetedcourse.service.GuardianService;
import org.sklse.targetedcourse.service.StudentService;
import org.sklse.targetedcourse.service.SubmittedHomeworkService;
import org.sklse.targetedcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by guolanqing on 2017/4/6.
 */
@RequestMapping("student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StuClassStudentRepository stuClassStudentRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private SubmittedHomeworkRepository submittedHomeworkRepository;
    @Autowired
    private GuardianService guardianService;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "提交作业")
    @PostMapping(value = "/submit")
    public ResponseEntity<ResultModel> submit(SubmittedHomework submit, HttpServletRequest request) throws Exception {

        Guardian guardian = userService.currentGuardian(request);
        Student student = studentRepository.findByStudentUid(guardian.getCurrentChildUid());



        SubmittedHomework dbSubmittedHomework = submittedHomeworkRepository.findByUid(submit.getUid());


        dbSubmittedHomework.setSubmitTime(new Date());
        dbSubmittedHomework.addSubmitCount();

        Assignment assignment = assignmentRepository.findByAssignmentUid(dbSubmittedHomework.getAssignmentUid());
//        首次提交，提交总人数+1
        if (!dbSubmittedHomework.isSubmitted()) {
            assignment.addSubmitCount();
        }


        //更新信息
        dbSubmittedHomework.setSubmitted(true);
        dbSubmittedHomework.setAudioUrl(submit.getAudioUrl());
        dbSubmittedHomework.setPictureUrl(submit.getPictureUrl());
        dbSubmittedHomework.setDuration(submit.getDuration());
        dbSubmittedHomework.setConcentration(submit.getConcentration());
        dbSubmittedHomework.setNonindependent(submit.getNonindependent());

        submittedHomeworkRepository.save(dbSubmittedHomework);
        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK, dbSubmittedHomework), HttpStatus.OK);

    }

    @ApiOperation(value = "修改作业")
    @PostMapping(value = "/update")
    public ResponseEntity<ResultModel> update(SubmittedHomework submittedHomework, HttpServletRequest request) throws ServletException {
        Guardian guardian = userService.currentGuardian(request);
        Student student = studentRepository.findByStudentUid(guardian.getCurrentChildUid());
        if (!new SubmittedHomeworkService().isMyAssignment(student, submittedHomework)) {
            throw new ServletException("No permission.");
        }
        submittedHomeworkRepository.save(submittedHomework);

        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK, "操作成功"), HttpStatus.OK);

    }

    @ApiOperation(value = "根据submittedHomeworkUid查询单次作业")
    @GetMapping(value = "/queryBySubmitHomework")
    public  ResponseEntity<ResultModel> queryBySubmitHomework(String submittedHomeworkUid, HttpServletRequest request) throws ServletException {
        Guardian guardian = userService.currentGuardian(request);
        Student student = studentRepository.findByStudentUid(guardian.getCurrentChildUid());
        SubmittedHomework submittedHomework = submittedHomeworkRepository.findByUid(submittedHomeworkUid);

        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK, submittedHomework), HttpStatus.OK);


    }

    @ApiOperation(value = "根据AssignmentUid查询单次作业")
    @GetMapping(value = "/queryByAssignment")
    public ResponseEntity<ResultModel> queryByAssignment(String AssignmentUid, HttpServletRequest request) throws ServletException {

        Guardian guardian = userService.currentGuardian(request);
        Student student = studentRepository.findByStudentUid(guardian.getCurrentChildUid());
        SubmittedHomework submittedHomework = submittedHomeworkRepository.findByAssignmentUid(AssignmentUid);

        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK, submittedHomework), HttpStatus.OK);


    }


    @ApiOperation(value = "作业列表")
    @GetMapping(value = "/submitAssignmentList")
    public List<SubmittedHomework> submitAssignmentList(HttpServletRequest request) throws ServletException {


        Guardian guardian = userService.currentGuardian(request);
        Student student = studentRepository.findByStudentUid(guardian.getCurrentChildUid());
        return student.getSubmittedHomeworks();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> save(@RequestBody Student entity) throws Exception {
        Map<String, Object> result = new HashMap<>();
        studentService.save(entity);
        result.put("id", entity.getStudentUid());
        return result;
    }



    @ApiIgnore
    @RequestMapping("findHomeworkByStudent")
    public List<Assignment> findHomeworkByStudent(String studentId) {
        ArrayList<Long> stuClasses = new ArrayList<Long>();
        for (int i = 0; i < stuClassStudentRepository.findStuClassByStudent(studentId).size(); i++) {
            stuClasses.add(stuClassStudentRepository.findStuClassByStudent(studentId).get(i));
        }

        List<Assignment> temp = null;
        return temp;
    }

    @RequestMapping(value = "/findByStudentUid", method = RequestMethod.GET)
    public Student findByStudentUid(@RequestParam String studentUid) {

        return studentRepository.findByStudentUid(studentUid);
    }


}

