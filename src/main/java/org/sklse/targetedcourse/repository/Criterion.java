package org.sklse.targetedcourse.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


/**
 * 条件接口
 * 用户提供条件表达式接口
 * @author sen
 *
 */
public interface Criterion {
	/**
	 * 比较符类型
	 * @author sen
	 *
	 */
	public enum Operator{
		EQ,NE,LIKE,GT,LT,GTE,LTE,AND,OR
	}
	/**
	 * 数据库运算符表达式
	 * @param root
	 * @param query
	 * @param builder
	 * @return
	 */
	public Predicate  toPredicate(Root<?> root, CriteriaQuery<?> query,
                                  CriteriaBuilder builder);
    
}
