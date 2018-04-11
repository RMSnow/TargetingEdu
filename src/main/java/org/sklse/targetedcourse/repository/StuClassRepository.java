package org.sklse.targetedcourse.repository;

import org.sklse.targetedcourse.bean.StuClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Alison on 2017/4/13.
 */


public interface StuClassRepository extends JpaRepository<StuClass, String> {


    StuClass findByClassUid(String classUid);

    StuClass findByTitle(String title);

//    //通过序号数组查询班级
//    List<StuClass> findAllByNumberIn(String numbers);


    List<StuClass> findAll();


    List<StuClass> findAllByStatus(int status);


    Page<StuClass> findAllByStatus(int status, Pageable pageable);
    @Query(value = "select c.* from class c where c.class_uid in (select stu_classes_class_uid from teacher_stu_classes where teacher_id=?1 )"+"order by c.class_uid \n#pageable\n",
            countQuery="select count(*) from teacher_stu_classes where teacher_id=?1",
            nativeQuery=true)
    public Page<StuClass> find(long teacher_id, Pageable pageable);



}
