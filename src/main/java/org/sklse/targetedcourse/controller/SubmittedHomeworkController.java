package org.sklse.targetedcourse.controller;

import io.swagger.annotations.ApiOperation;
import org.sklse.targetedcourse.bean.Question;
import org.sklse.targetedcourse.bean.ResultModel;
import org.sklse.targetedcourse.bean.SubmittedHomework;
import org.sklse.targetedcourse.bean.Teacher;
import org.sklse.targetedcourse.repository.*;
import org.sklse.targetedcourse.service.SubmittedHomeworkService;
import org.sklse.targetedcourse.service.UserService;
import org.sklse.targetedcourse.util.ListParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * The type Submitted homework controller.
 */
@RestController
@RequestMapping("submitHomework")
public class SubmittedHomeworkController {

    @Autowired
    private UserService userService;

    @Autowired
    private SubmittedHomeworkRepository submittedHomeworkRepository;


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private SubmittedHomeworkService submittedHomeworkService;

    @ApiOperation("根据teacherId选出所有作业")
    @RequestMapping(value="getAllAssignment",method = RequestMethod.GET)
    public ResponseEntity<ResultModel> getAllAssignment(@RequestParam String id){
        List<SubmittedHomework> list=submittedHomeworkRepository.findAll();

        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK,list),HttpStatus.OK);
    }

    @ApiOperation("根据teacherId选出所有班级作业")
    @RequestMapping(value="getAllClassAssignment",method = RequestMethod.GET)
    public List<SubmittedHomework> getAllClassAssignment (HttpServletRequest request)throws ServletException {
        Teacher teacher = userService.currentTeacher(request);
        return submittedHomeworkRepository.findAllClassAssignmentByTeacherUid(String.valueOf(teacher.getId()));
    }

    @ApiOperation("根据teacherId选出所有精准作业")
    @RequestMapping(value="getAllTargetingAssignment",method = RequestMethod.GET)
    public List<SubmittedHomework> getAllTargetingAssignment(HttpServletRequest request)throws ServletException{
        Teacher teacher = userService.currentTeacher(request);
        List<SubmittedHomework> list=submittedHomeworkRepository.findAllTargetingAssignmentByTeacherUid(String.valueOf(teacher.getId()) );

        return list;
    }

    @ApiOperation("根据submittedHomeworkUid查询对应的试题题型列表")
    @RequestMapping(value = "/getTypeList", method = RequestMethod.GET)
    public ResponseEntity<ResultModel> getTypeList(@RequestParam String uid) {
        List<String> types = new ArrayList<>();
        for (int i = 0; i < getQuestions(uid).size(); i++) {
            types.add(getQuestions(uid).get(i).getType());
        }
        List<String> typeList = new ArrayList<String>(new HashSet<String>(types));
        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK, typeList), HttpStatus.OK);


    }

    @ApiOperation("根据submittedHomeworkUid查询对应的提交图片")
    @RequestMapping(value = "/getPictures", method = RequestMethod.GET)
    public ResponseEntity<ResultModel> getPictures(String uid) {
        SubmittedHomework submittedHomework = submittedHomeworkRepository.findByUid(uid);
        String pictures = submittedHomework.getPictureUrl();
        if (pictures == null) {
            return new ResponseEntity<>(ResultModel.error(HttpStatus.OK, "无图片"), HttpStatus.NOT_FOUND);
        }

        String[] pictureArray = ListParse.stringToArray(pictures);
        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK, pictureArray), HttpStatus.OK);


    }

    @ApiOperation("根据submittedHomeworkUid查询对应的题目")
    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public ResponseEntity<ResultModel> questions(@RequestParam(value = "uid") String uid) {
        SubmittedHomework submittedHomework = submittedHomeworkRepository.findByUid(uid);
        String homeworkId = submittedHomework.getAssignmentUid();
        String[] questions = ListParse.stringToArray(assignmentRepository.findByAssignmentUid(homeworkId).getQuestionList());


        List<Question> questionList = questionRepository.findAllByNumberIn(questions);

        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK, questionList), HttpStatus.OK);


    }


    @ApiOperation("根据submittedHomeworkUid查询submittedHomework")
    @RequestMapping(value = "/findByUid", method = RequestMethod.GET)
    public SubmittedHomework findByUid(@RequestParam String uid) {
        return submittedHomeworkRepository.findByUid(uid);
    }


    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String check(@RequestBody SubmittedHomework submittedHomework) throws Exception {

        submittedHomework.setChecked(true);
//        List<Answer> answers=new ArrayList<>();
//        List<Question> testQuestions=getQuestions(submittedHomework.getAnswerUid());
//        for(int i=0;i<testQuestions.size();i++){
//            Answer answer=new Answer(testQuestions.get(i).getClassUid());
//            answers.add(answer);
//        }
//        submittedHomework.setAnswers(answers);
        submittedHomeworkService.save(submittedHomework);
        return "success";
    }

    @ApiIgnore
    @RequestMapping(value = "/getQuestions", method = RequestMethod.GET)
    public List<Question> getQuestions(@RequestParam(value = "uid") String uid) {
        SubmittedHomework submittedHomework = submittedHomeworkRepository.findByUid(uid);
        String homeworkId = submittedHomework.getAssignmentUid();
        String[] questions = ListParse.stringToArray(assignmentRepository.findByAssignmentUid(homeworkId).getQuestionList());
        return questionRepository.findAllByNumberIn(questions);


    }


    @ApiIgnore
    @RequestMapping(value = "/findByStuId", method = RequestMethod.GET)
    public List<SubmittedHomework> findByStuId(String student_id) {
        List<SubmittedHomework> homeworkList = submittedHomeworkRepository.findAllByStudentUid(student_id);
        return homeworkList;

    }


}
