package org.sklse.targetedcourse.service;

import org.sklse.targetedcourse.bean.StuClass;
import org.sklse.targetedcourse.bean.Teacher;
import org.sklse.targetedcourse.repository.StuClassRepository;
import org.sklse.targetedcourse.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StuClassRepository stuClassRepository;


    @Transactional
    public Teacher findByUsername(String phoneNum) {
        return teacherRepository.findByPhoneNumber(phoneNum);
    }

    @Transactional
    public List<StuClass> findAllClassByStatus(int status) {
        return stuClassRepository.findAllByStatus(status);
    }


    @Transactional
    public Page<StuClass> findAll(Pageable pageable) {
        return stuClassRepository.findAll(pageable);
    }

    @Transactional
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public Teacher findByPhoneNum(String phoneNumber) {
        return teacherRepository.findByPhoneNumber(phoneNumber);
    }


    public boolean isMyClass(Teacher teacher, StuClass stuClass) {
        if (teacher.getStuClasses().contains(stuClass)) {
            return true;
        }
        return false;
    }

}
