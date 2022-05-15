package com.young.sarangbang.model.entity.banginfo.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.young.sarangbang.exception.SarangbangException;
import com.young.sarangbang.exception.SarangbangExceptionCode;
import com.young.sarangbang.model.entity.banginfo.domain.BangInfo;
import com.young.sarangbang.model.entity.banginfo.domain.QBangInfo;
import com.young.sarangbang.model.entity.banginfo.repository.BangInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Date : 2022-03-07
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Slf4j
@Transactional
@Service
public class BangInfoServiceImpl implements BangInfoService {

    @Autowired
    BangInfoRepository biRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public BangInfo get(Integer bangInfoSeq) {
        return biRepository.findById(bangInfoSeq).get();
    }

    @Override
    public List<BangInfo> getsAll() {
        return biRepository.findAll();
    }

    @Override
    public List<BangInfo> getsAllByIsFavorites() {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        return query.selectFrom(QBangInfo.bangInfo)
                .where(QBangInfo.bangInfo.isFavorites.eq(true))
                .fetch();
    }

    @Override
    public void add(BangInfo bangInfo) throws SarangbangException {
        try {
            biRepository.save(bangInfo);
        } catch (Exception e) {
            log.error("부동산 정보 추가 오류 : ", e);
            throw new SarangbangException(SarangbangExceptionCode.WRONG_BANGINFO_AGAIN_REGISTER);
        }
    }

    @Override
    public void adds(List<BangInfo> bangInfos) throws SarangbangException {
        try {
            biRepository.saveAll(bangInfos);
        } catch (Exception e) {
            log.error("부동산 정보 추가 오류 : ", e);
            throw new SarangbangException(SarangbangExceptionCode.WRONG_BANGINFO_AGAIN_REGISTER);
        }
    }

    @Override
    public void updateIsFavorites(Integer bangInfoSeq, Boolean isFavorites) throws SarangbangException {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        long amount = queryFactory.update(QBangInfo.bangInfo)
                .set(QBangInfo.bangInfo.isFavorites, isFavorites)
                .where(QBangInfo.bangInfo.bangInfoSeq.eq(bangInfoSeq))
                .execute();

        if (amount == 0) {
            throw new SarangbangException(SarangbangExceptionCode.NOT_CHANGE_IS_FAVORITES);
        }
    }

}
