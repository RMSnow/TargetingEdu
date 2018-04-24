package org.sklse.targetedcourse.repository;

import org.sklse.targetedcourse.bean.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by WYJ on 2018/4/24.
 */
public interface AnswerRepository extends JpaRepository<Answer,String> {

    @Query(value = "select a.* from Answer a where AnswerUid=?1",
            nativeQuery = true)
    Answer findByAnswerUid(String AnswerUid);

    @Modifying
    @Query(value = "update Answer set score=?2,remark=?3 where AnswerUid=?1")
    void updateByAnswerUid(String AnswerUid,String score,String remark);
}
