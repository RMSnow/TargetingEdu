package org.sklse.targetedcourse.repository;

import org.sklse.targetedcourse.bean.CollectedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by MIC on 2017/8/12.
 */
public interface CollectedQuestionRepository extends JpaRepository<CollectedQuestion, String> {
    CollectedQuestion findById(String id);
}