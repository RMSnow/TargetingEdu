package org.sklse.targetedcourse.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sklse.targetedcourse.bean.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYJ on 2018/4/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StuClassServiceTest {
    @Autowired
    StuClassService scs;
    @Autowired
    SubmittedHomeworkService shs;
    @Test
    public void findAll() throws Exception {
        Answer ans=new Answer();
        ans.setAnswerUid("1");
        ans.setScore("100");
        ans.setRemark("gg");
        List<Answer> list=new ArrayList<>();
        list.add(ans);

        shs.checkHomeworkByAssignmentUid("1",list,"gg my friend");
    }
}