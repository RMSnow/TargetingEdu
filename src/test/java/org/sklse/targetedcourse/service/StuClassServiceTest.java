package org.sklse.targetedcourse.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sklse.targetedcourse.bean.StuClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

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
        shs.updateByAssignmentUid("1");
    }
}