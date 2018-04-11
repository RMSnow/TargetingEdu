package org.sklse.targetedcourse.controller;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.sklse.targetedcourse.bean.*;
import org.sklse.targetedcourse.repository.*;
import org.sklse.targetedcourse.service.UserService;
import org.sklse.targetedcourse.util.ListParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("assignment")
public class AssignmentController {



    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StuClassRepository stuClassRepository;

    @Autowired
    private SubmittedHomeworkRepository submittedHomeworkRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionRepository questionRepository;


    @ApiOperation(value = "发布作业")
    @PostMapping(value = "/teacher/release")
    public ResponseEntity<ResultModel> release(Assignment assignment, HttpServletRequest request) throws ServletException {
        Teacher teacher = userService.currentTeacher(request);



//        Assignment相关
        assignment.setTeacherUid(teacher.getId());
        assignment.setCreateTime(new Date());

        assignmentRepository.save(assignment);
        String assignmentUid = assignment.getAssignmentUid();

        teacher.getAssignments().add(assignment);


        String classUidList = assignment.getClassList();
        if (classUidList == null) {
            return new ResponseEntity<>(ResultModel.error(HttpStatus.FORBIDDEN, "班级列表为空"), HttpStatus.FORBIDDEN);
        }


        String[] classList = ListParse.stringToArray(classUidList);

//        发布到每个学生
        for (String stuClassUid : classList) {
            StuClass stuClass = stuClassRepository.findByClassUid(stuClassUid);
            stuClass.getAssignmentList().add(assignment);
            List<Student> students = stuClass.getStudents();
            for (Student student : students) {


                SubmittedHomework submittedHomework = new SubmittedHomework();
                submittedHomework.setAssignmentUid(assignmentUid);
                submittedHomework.setStudentName(student.getName());
                submittedHomeworkRepository.save(submittedHomework);

                student.getSubmittedHomeworks().add(submittedHomework);
                studentRepository.save(student);

            }
            stuClassRepository.save(stuClass);
        }

        return new ResponseEntity<>(ResultModel.ok("作业发布成功"), HttpStatus.OK);
    }

    @ApiOperation(value = "获取单个作业")
    @GetMapping(value = "/teacher/getAssignment")
    public ResponseEntity<ResultModel> getAssignment(String assignmentUid, HttpServletRequest request) throws ServletException {

        Teacher teacher = userService.currentTeacher(request);

        Assignment assignment = assignmentRepository.findByAssignmentUid(assignmentUid);
        if (assignment == null) {
            throw new ServletException("Assignment not found.");
        }
        if (!teacher.getAssignments().contains(assignment)) {
            throw new ServletException("No permission.");
        }
        return new ResponseEntity<>(ResultModel.ok(assignment), HttpStatus.OK);
    }


    @ApiOperation(value = "作业试题列表")
    @RequestMapping(value = "/questions",method = RequestMethod.GET)
    public ResponseEntity<ResultModel> findQuestionList(@RequestParam(value = "assignmentUid") String assignmentUid){
        Assignment assignment=assignmentRepository.findByAssignmentUid(assignmentUid);
        String[] qestionList=ListParse.stringToArray(assignment.getQuestionList());
        List<Question> questions=questionRepository.findAllByNumberIn(qestionList);
         return new ResponseEntity<>(ResultModel.ok(questions), HttpStatus.OK);

    }

    @ApiOperation(value = "获取assignmentUid单个作业信息")
    @GetMapping(value = "/student/assignment")
    public ResponseEntity<ResultModel> assignment(String assignmentUid, HttpServletRequest request) throws ServletException {


        Assignment assignment = assignmentRepository.findByAssignmentUid(assignmentUid);
        if (assignment == null) {
            throw new ServletException("Assignment not found.");
        }

        return new ResponseEntity<>(ResultModel.ok(assignment), HttpStatus.OK);
    }

    @ApiOperation(value = "获取老师发布的全部作业")
     @GetMapping(value = "/teacher/assignmentList")
    public ResponseEntity<ResultModel> getAssignmentList(HttpServletRequest request) throws ServletException {
        Claims claims = (Claims) request.getAttribute("claims");
        String phoneNumber = (String) claims.get("sub");
        Teacher teacher = teacherRepository.findByPhoneNumber(phoneNumber);

        return new ResponseEntity<>(ResultModel.ok(teacher.getAssignments()), HttpStatus.OK);

    }

    @ApiOperation(value = "学生作业列表")
    @RequestMapping(value = "findSubmitHomeworkByHomework", method = RequestMethod.GET)
    public List<SubmittedHomework> findSubmitHomeworkByHomework(@RequestParam String uid) {

        List<SubmittedHomework> submitHomeworkList = submittedHomeworkRepository.findAllByAssignmentUid(uid);

        return submitHomeworkList;
    }

//    @ApiOperation(value = "修改作业")
//    @RequestMapping(value = "updateHomework", method = RequestMethod.POST)
//    public String updateHomework(String assignmentUid, HttpServletRequest request) {
//
//        Assignment preAssignment = assignmentRepository.findByAssignmentUid(assignmentUid);
//        preAssignment = assignment;
//        assignmentRepository.save(preAssignment);
//        return "success";
//    }
//
//    @ApiOperation(value = "删除作业")
//    @RequestMapping(value = "deleteHomework/{assignmentUid}", method = RequestMethod.DELETE)
//    public String deleteHomework(@PathVariable String assignmentUid, String classUid, HttpSession session) {
//        Teacher teacher = teacherRepository.findByPhoneNumber((String) session.getAttribute("phoneNumber"));
//
//        Assignment assignment = assignmentRepository.findByAssignmentUid(assignmentUid);
//        teacher.getAssignments().remove(assignment);
//
//        List<StuClass> stuClassList = stuClassRepository.findAllByNumberIn(classUid);
//        for (StuClass stuClass : stuClassList) {
//            List<Student> students = stuClass.getStudents();
//            for (Student student : students) {
//                student.getAssignmentList().remove(assignment);
//            }
//        }
//        assignmentRepository.delete(assignment);
//
//        return "success";
//    }


}
