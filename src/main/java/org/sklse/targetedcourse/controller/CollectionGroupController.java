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

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("group")
public class CollectionGroupController {
    @Autowired
    private CollectionGroupRepository collectionGroupRepository;

    @Autowired
    private CollectedQuestionRepository collectedQuestionRepository;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取全部收藏夹")
    @GetMapping(value = "getAll")
    public List<CollectionGroup> findByStudentId(){

        return collectionGroupRepository.findAll();
    }

    @ApiOperation(value = "新增收藏夹")
    @PostMapping(value = "addGroup")
    public ResponseEntity<ResultModel> addgroup(CollectionGroup group,HttpServletRequest request) throws Exception{
        Guardian guardian = userService.currentGuardian(request);
        String studentId=guardian.getCurrentChildUid();
        group.setStudentId(studentId);
        collectionGroupRepository.save(group);
        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK,"操作成功"), HttpStatus.OK);
    }

    @ApiOperation(value = "删除收藏夹")
    @RequestMapping(value = "deleteGroup",method = RequestMethod.DELETE)
    public void deleteGruop(@RequestParam  String id){
        CollectionGroup group=collectionGroupRepository.findById(id);
        List<CollectedQuestion> collection= group.getCollections();
        for(CollectedQuestion collectedQuestion:collection){
            collectedQuestionRepository.delete(collectedQuestion);
        }
        collectionGroupRepository.delete(group);
    }
}

