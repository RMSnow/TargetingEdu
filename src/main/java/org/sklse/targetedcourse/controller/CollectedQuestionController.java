package org.sklse.targetedcourse.controller;

import io.swagger.annotations.ApiOperation;
import org.sklse.targetedcourse.bean.CollectedQuestion;
import org.sklse.targetedcourse.bean.CollectionGroup;
import org.sklse.targetedcourse.bean.Guardian;
import org.sklse.targetedcourse.bean.ResultModel;
import org.sklse.targetedcourse.repository.CollectedQuestionRepository;
import org.sklse.targetedcourse.repository.CollectionGroupRepository;
import org.sklse.targetedcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by MIC on 2017/8/12.
 */
@RestController
@RequestMapping("collectedquestion")
public class CollectedQuestionController {
    @Autowired
    private CollectedQuestionRepository collectedQuestionRepository;

    @Autowired
    private CollectionGroupRepository collectionGroupRepository;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取收藏夹的全部错题")
    @GetMapping(value = "getAll")
    public List<CollectedQuestion> findAll(String groupId){
        CollectionGroup group=collectionGroupRepository.findById(groupId);
        return group.getCollections();
    }

    @ApiOperation(value = "获取单个收藏的错题")
    @GetMapping(value = "getcollectedquestion")
    public CollectedQuestion find(String id){
        return collectedQuestionRepository.findById(id);

    }

    @ApiOperation(value = "新增收藏")
    @PostMapping(value ="addCollected" )
    public ResponseEntity<ResultModel> addCollected(CollectedQuestion collectedQuestion,HttpServletRequest request) throws ServletException {

        Guardian guardian = userService.currentGuardian(request);
        String studentId=guardian.getCurrentChildUid();
        collectedQuestion.setStudentId(studentId);
        if(collectedQuestion.getGroupId()==null){
            return new ResponseEntity<>(ResultModel.error(HttpStatus.FORBIDDEN,"请选择收藏夹/创建收藏夹"), HttpStatus.FORBIDDEN);
        }else{
            CollectionGroup group=collectionGroupRepository.findById(collectedQuestion.getGroupId());
            group.getCollections().add(collectedQuestion);
            collectedQuestionRepository.save(collectedQuestion);
            return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK,"操作成功"), HttpStatus.OK);
        }
    }
    @ApiOperation(value = "删除单个收藏")
    @RequestMapping(value = "deleteCollected",method = RequestMethod.DELETE)
    public void deleteCollected(@RequestParam String id){
        CollectedQuestion collectedQuestion=collectedQuestionRepository.findById(id);
        CollectionGroup group=collectionGroupRepository.findById(collectedQuestion.getGroupId());
        group.getCollections().remove(collectedQuestion);
        collectedQuestionRepository.delete(collectedQuestion);
    }

}
