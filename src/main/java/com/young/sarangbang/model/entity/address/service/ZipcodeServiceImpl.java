package com.young.sarangbang.model.entity.address.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Lists;
import com.young.sarangbang.model.entity.address.domain.Zipcode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Date : 2022-03-27
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Slf4j
@Service
public class ZipcodeServiceImpl implements ZipcodeService{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Zipcode> getByZipcode(String siDoEng, String zipcode) {
        Query query = entityManager.createNativeQuery("EXEC selectZipcode :siDoEng, :zipcode", Zipcode.class);
        query.setParameter("siDoEng", siDoEng);
        query.setParameter("zipcode", zipcode);

        List<Zipcode> zipcodes = query.getResultList();
        return zipcodes;
    }

    @Override
    public List<Zipcode> getByRawDongName(String siDoEng, String rawDongName) {
        Query query = entityManager.createStoredProcedureQuery("EXEC selectZipcode :siDoEng, :rawDongName", Zipcode.class);
        query.setParameter("siDoEng", siDoEng);
        query.setParameter("rawDongName", rawDongName);

        List<Zipcode> zipcodes = query.getResultList();
        return zipcodes;
    }

    @Override
    public List<Zipcode> getByRoadNameHan(String siDoEng, String roadNameHan) {
        Query query = entityManager.createStoredProcedureQuery("EXEC selectZipcode :siDoEng, :roadNameHan", Zipcode.class);
        query.setParameter("siDoEng", siDoEng);
        query.setParameter("roadNameHan", roadNameHan);

        List<Zipcode> zipcodes = query.getResultList();
        return zipcodes;
    }
}
