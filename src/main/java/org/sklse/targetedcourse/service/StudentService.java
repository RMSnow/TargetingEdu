package org.sklse.targetedcourse.service;

import org.sklse.targetedcourse.bean.Student;
import org.sklse.targetedcourse.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

;

/**
 * Created by guolanqing on 2017/4/27.
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll () {
        return studentRepository.findAll();
    }
    public void save(Student entity) throws Exception {
        // 持久化
        studentRepository.save(entity);
        // 如果持久化成功，就抛出异常。如果开启事务，那么刚才持久化的数据应回滚
    }
}
