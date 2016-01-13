package com.hoon.cmd.domain.hello;

import com.mysema.query.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

/**
 * 서비스 설명
 *
 * @author : baby bong
 * @since : 2016-01-13
 */
public class HelloRepositoryImpl extends QueryDslRepositorySupport implements HelloRepositoryCustom {
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	@Override
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	public HelloRepositoryImpl() {
		super(Hello.class);
	}

	@Override
	public List<Hello> findByIdLimit() {
		//return from(QHello.hello).limit(3).list(QHello.hello);

		QHello hello = QHello.hello;
		JPQLQuery query = from(hello).where(hello.id.gt(2)
			.and((hello.name.contains("세세").or(hello.name.contains("lo")))));
		return query.list(hello);
	}
}
