package org.sklse.targetedcourse.repository;

import org.sklse.targetedcourse.bean.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by guolanqing on 2017/4/6.
 */
public interface StudentRepository extends JpaRepository<Student, String> {
    public Student findByStudentUid(String studentUid);

    public Student findByStudentNumber(String studentNumber);

    public List<Student> findByStudentUidContaining(String number);

    @Query(value = "select s.* from student s where s.student_uid in " +
            "(select students_student_uid from class_students where stu_class_class_uid=?1)" +
            "ORDER BY s.student_uid \n#pageable\n",
            countQuery = "select count(*) from class_students where stu_class_class_uid=?1 ",
            nativeQuery = true)
    public Page<Student> findByStuclassId(String classId, Pageable pageable);


}
