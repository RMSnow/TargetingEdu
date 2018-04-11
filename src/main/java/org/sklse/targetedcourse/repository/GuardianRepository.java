package org.sklse.targetedcourse.repository;


import org.sklse.targetedcourse.bean.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by MIC on 2017/7/19.
 */
public interface GuardianRepository extends JpaRepository<Guardian, String> {
    Guardian findByPhoneNumber(String phoneNumber);

    List<Guardian> findAll();

    Guardian findByGuardianUid(String guardianUid);

}
