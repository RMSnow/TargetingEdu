package org.sklse.targetedcourse.service;

import org.sklse.targetedcourse.bean.StuClass;
import org.sklse.targetedcourse.bean.Student;
import org.sklse.targetedcourse.bean.Teacher;
import org.sklse.targetedcourse.repository.StuClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StuClassService {

    @Autowired
    private StuClassRepository stuClassRepository;




    public List<StuClass> findAll() {
        return stuClassRepository.findAll();
    }

    public List<StuClass> findAllByStatus(int status) {
        return stuClassRepository.findAllByStatus(status);
    }






    public StuClass findByNumber(String no) {
        return stuClassRepository.findByClassUid(no);
    }

    public StuClass findByTitle(String title) {
        return stuClassRepository.findByTitle(title);
    }

    public Page<StuClass> findAll(Pageable pageable) {
        return stuClassRepository.findAll(pageable);
    }


    public Page<StuClass> findByStatus(int status, Pageable pageable) {
        return stuClassRepository.findAllByStatus(status, pageable);
    }

    public void save(StuClass entity) throws Exception {
        // 持久化
        stuClassRepository.save(entity);
        // 如果持久化成功，就抛出异常。如果开启事务，那么刚才持久化的数据应回滚
    }


    public  boolean isInClass(StuClass stuClass, Student student){
        if (stuClass.getStudents().contains(student)){
            return true;
        }
        return false;
    }


}
