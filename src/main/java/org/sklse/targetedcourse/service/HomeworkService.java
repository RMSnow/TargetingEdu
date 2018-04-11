package org.sklse.targetedcourse.service;

import org.sklse.targetedcourse.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class HomeworkService {


    @Autowired
    private AssignmentRepository assignmentRepository;


}
