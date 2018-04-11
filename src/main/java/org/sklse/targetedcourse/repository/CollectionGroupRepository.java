package org.sklse.targetedcourse.repository;

import org.sklse.targetedcourse.bean.CollectionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by MIC on 2017/8/12.
 */
public interface CollectionGroupRepository extends JpaRepository<CollectionGroup,String> {
    CollectionGroup findById(String id);
}