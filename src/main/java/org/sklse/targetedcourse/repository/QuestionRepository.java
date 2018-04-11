package org.sklse.targetedcourse.repository;


import org.sklse.targetedcourse.bean.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, String> {

    List<Question> findAll();
    List<Question> findAllByNumberIn(String[] array);
}
