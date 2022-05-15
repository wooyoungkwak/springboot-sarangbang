package com.young.sarangbang.model.entity.stock.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.young.sarangbang.model.entity.stock.domain.QStockCompany;
import com.young.sarangbang.model.entity.stock.domain.StockCompany;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Date : 2022-04-25
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Service
public class StockCompanyServiceImpl implements StockCompanyService{

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<StockCompany> gets() {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        return query.selectFrom(QStockCompany.stockCompany).fetch();
    }
}
