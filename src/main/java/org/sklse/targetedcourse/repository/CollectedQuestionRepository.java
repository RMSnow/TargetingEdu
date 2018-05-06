package org.sklse.targetedcourse.repository;

import org.sklse.targetedcourse.bean.CollectedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CollectedQuestionRepository extends JpaRepository<CollectedQuestion, String> {
    CollectedQuestion findById(String id);
}