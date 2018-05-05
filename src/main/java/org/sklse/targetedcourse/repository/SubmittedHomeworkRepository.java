package org.sklse.targetedcourse.repository;

import org.sklse.targetedcourse.bean.Answer;
import org.sklse.targetedcourse.bean.SubmittedHomework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubmittedHomeworkRepository extends JpaRepository<SubmittedHomework, String> {


    SubmittedHomework findByUid(String uid);

    List<SubmittedHomework> findAllByStudentUid(String StudentUid);

    SubmittedHomework findByAssignmentUid(String assignmentUid);

    List<SubmittedHomework> findAllByAssignmentUid(String assignmentUid);

    @Modifying
    @Query(value = "update SubmittedHomework sh set score=?2, comment=?3 where assignmentUid=?1")
    void checkHomeworkByAssignmentUid(String assignmentUid,int score,String comment);

    List<SubmittedHomework> findAll();

    @Query(value = "select s.* from submit_homework s where s.assignment_uid in " +
            "(select a.assignment_uid from assignment a where a.teacher_uid=?1 and a.class_list is not null)",
    nativeQuery = true)
    List<SubmittedHomework> findAllClassAssignmentByTeacherUid(String teacherUid);


    @Query(value = "select s.* from submit_homework s where s.assignment_uid in " +
            "(select a.assignment_uid from assignment a where a.teacher_uid=?1 and a.student_list is not null)",
            nativeQuery = true)
    List<SubmittedHomework> findAllTargetingAssignmentByTeacherUid(String teacherUid);
}
