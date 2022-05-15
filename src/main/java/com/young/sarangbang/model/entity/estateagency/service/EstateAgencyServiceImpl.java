package com.young.sarangbang.model.entity.estateagency.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.young.sarangbang.exception.SarangbangException;
import com.young.sarangbang.exception.SarangbangExceptionCode;
import com.young.sarangbang.model.entity.estateagency.domain.EstateAgency;
import com.young.sarangbang.model.entity.estateagency.domain.QEstateAgency;
import com.young.sarangbang.model.entity.estateagency.repository.EstateAgencyRepository;
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
public class EstateAgencyServiceImpl implements EstateAgencyService {

    @Autowired
    EstateAgencyRepository estateAgencyRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public EstateAgency get(Integer estateAgencySeq) throws SarangbangException {
        try {
            return estateAgencyRepository.findById(estateAgencySeq).get();
        } catch (Exception e){
            log.error("부동산 소계소 정보 가져오기 오류 : ", e);
            throw new SarangbangException(SarangbangExceptionCode.NULL);
        }
    }

    @Override
    public List<EstateAgency> getsAll() throws SarangbangException {
        try {
            return estateAgencyRepository.findAll();
        } catch (Exception e){
            log.error("부동산 소계소 정보 가져오기 오류 : ", e);
            throw new SarangbangException(SarangbangExceptionCode.NULL);
        }
    }

    @Override
    public List<EstateAgency> getsAllByIsFavorites() throws SarangbangException {
        try {
            JPAQueryFactory query = new JPAQueryFactory(entityManager);
            return query.selectFrom(QEstateAgency.estateAgency)
                    .where(QEstateAgency.estateAgency.isFavorites.eq(true))
                    .fetch();
        } catch (Exception e) {
            log.error("부동산 소계소 즐겨 찾기 정보 가져오기 오류 : ", e);
            throw new SarangbangException(SarangbangExceptionCode.NULL);
        }

    }

    @Override
    public void add(EstateAgency estateAgency) throws SarangbangException {
        try {
            estateAgencyRepository.save(estateAgency);
        } catch (Exception e) {
            log.error("부동산 중계소 정보 추가 오류 : ", e);
            throw new SarangbangException(SarangbangExceptionCode.WRONG_ESTATE_AGENCY_AGAIN_REGISTER);
        }
    }

    @Override
    public void adds(List<EstateAgency> estateAgencies) throws SarangbangException {
        try {
            estateAgencyRepository.saveAll(estateAgencies);
        } catch (Exception e) {
            log.error("부동산 중계소 정보 추가 오류 : ", e);
            throw new SarangbangException(SarangbangExceptionCode.WRONG_ESTATE_AGENCY_AGAIN_REGISTER);
        }
    }

    @Override
    public void updateIsFavorites(Integer estateAgencySeq, Boolean isFavorites) throws SarangbangException {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);

        long amount = query.update(QEstateAgency.estateAgency)
                .set(QEstateAgency.estateAgency.isFavorites, isFavorites)
                .where(QEstateAgency.estateAgency.estateAgencySeq.eq(estateAgencySeq))
                .execute();

        if (amount == 0) {
            throw new SarangbangException(SarangbangExceptionCode.NOT_CHANGE_IS_FAVORITES);
        }
    }

}
