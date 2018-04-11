package org.sklse.targetedcourse.repository;

import org.sklse.targetedcourse.bean.SubmittedHomework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmittedHomeworkRepository extends JpaRepository<SubmittedHomework, String> {


    SubmittedHomework findByUid(String uid);

    List<SubmittedHomework> findAllByStudentUid(String StudentUid);

    SubmittedHomework findByAssignmentUid(String assignmentUid);

    List<SubmittedHomework> findAllByAssignmentUid(String assignmentUid);



}
