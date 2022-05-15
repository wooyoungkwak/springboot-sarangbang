package com.young.sarangbang.model.entity.stock.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.young.sarangbang.model.entity.stock.domain.QStockCompany;
import com.young.sarangbang.model.entity.stock.domain.QStockInfo;
import com.young.sarangbang.model.entity.stock.domain.StockCompany;
import com.young.sarangbang.model.entity.stock.domain.StockInfo;
import com.young.sarangbang.model.entity.stock.enums.StockType;
import com.young.sarangbang.model.entity.stock.repository.StockInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Date : 2022-03-17
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Service
public class StockInfoServiceImpl implements StockInfoService{

    @Autowired
    StockInfoRepository stockInfoRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public StockInfo get(Integer stockInfoSeq) {
        return stockInfoRepository.findById(stockInfoSeq).get();
    }

    @Override
    public List<StockInfo> gets(StockCompany stockCompany, StockType stockType) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        return query.selectFrom(QStockInfo.stockInfo)
                // 명시적 join 선언 ( 암묵적 join 을 하게 되면 cross join 이 추가로 생기기 때문에 아래와 같이 명시적으로 join 을함)
                .leftJoin(QStockInfo.stockInfo.stockCompany, QStockCompany.stockCompany)
                .where(QStockCompany.stockCompany.name.eq(stockCompany.getName()))
                .where(QStockInfo.stockInfo.stockType.eq(stockType))
                .orderBy(QStockInfo.stockInfo.regDate.asc())
                .fetch();
    }

    @Override
    public List<StockInfo> gets(String alias, StockType stockType) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        return query.selectFrom(QStockInfo.stockInfo)
                // 명시적 join 선언 ( 암묵적 join 을 하게 되면 cross join 이 추가로 생기기 때문에 아래와 같이 명시적으로 join 을함)
                .leftJoin(QStockInfo.stockInfo.stockCompany, QStockCompany.stockCompany)
                .where(QStockInfo.stockInfo.stockCompany.alias.eq(alias))
                .where(QStockInfo.stockInfo.stockType.eq(stockType))
                .orderBy(QStockInfo.stockInfo.regDate.asc())
                .fetch();
    }

    @Override
    public List<StockInfo> getsByFirstStockCompanyAndDayType(){
        JPAQueryFactory query = new JPAQueryFactory(entityManager);

        return query.selectFrom(QStockInfo.stockInfo)
                .leftJoin(QStockInfo.stockInfo.stockCompany, QStockCompany.stockCompany)
                .where(QStockCompany.stockCompany.stockCompanySeq.eq(1))
                .where(QStockInfo.stockInfo.stockType.eq(StockType.DAY))
                .orderBy(QStockInfo.stockInfo.regDate.asc())
                .fetch();
    }
}
