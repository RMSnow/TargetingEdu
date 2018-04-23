package org.sklse.targetedcourse.service;

import org.sklse.targetedcourse.bean.Student;
import org.sklse.targetedcourse.bean.SubmittedHomework;
import org.sklse.targetedcourse.repository.SubmittedHomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SubmittedHomeworkService {


    @Autowired
    private SubmittedHomeworkRepository submittedHomeworkRepository;



    public boolean isMyAssignment( Student student,SubmittedHomework submittedHomework){
        if (student.getSubmittedHomeworks().contains(submittedHomework)){
            return true;
        }



        return false;
    }


    public void save(SubmittedHomework entity) throws Exception {
        // 持久化
        submittedHomeworkRepository.save(entity);
        // 如果持久化成功，就抛出异常。如果开启事务，那么刚才持久化的数据应回滚
    }

    public void updateByAssignmentUid(String assignmentUid){
        System.out.println("!!!!!!!!!!!!!!!!");
        submittedHomeworkRepository.updateByAssignmentUid(assignmentUid);

    }
}
