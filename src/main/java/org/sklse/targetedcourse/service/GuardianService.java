package org.sklse.targetedcourse.service;

import org.sklse.targetedcourse.bean.Guardian;
import org.sklse.targetedcourse.bean.Student;
import org.springframework.stereotype.Service;


@Service
public class GuardianService {

    public  boolean isMyChild(Guardian guardian, Student student){
        if (guardian.getStudents().contains(student)){
            return true;
        }
        return false;
    }

}
