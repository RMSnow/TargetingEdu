package org.sklse.targetedcourse.repository;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 定义一个查询条件容器
 * @author sen
 *
 */
public class Criteria<T> implements Specification<T> {
	private List<Criterion> criterions=new ArrayList<Criterion>();

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		if(!criterions.isEmpty()){
			List<Predicate> predicates=new ArrayList<>();
			for(Criterion c:criterions){
				predicates.add( c.toPredicate(root, query, builder));
			}
			//将所有的条件用and联合起来
			if(predicates.size()>0){
				return builder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}
		return builder.conjunction();
	}
	/**
	 * 增加简单条件表达式
	 */
    public void add(Criterion criterion){
    	if(criterion!=null){
    		criterions.add(criterion);
    	}
    }

}
