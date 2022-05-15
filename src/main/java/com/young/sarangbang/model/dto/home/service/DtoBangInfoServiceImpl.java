package com.young.sarangbang.model.dto.home.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.young.sarangbang.exception.SarangbangException;
import com.young.sarangbang.exception.SarangbangExceptionCode;
import com.young.sarangbang.model.entity.banginfo.domain.BangInfo;
import com.young.sarangbang.model.entity.banginfo.domain.QBangInfo;
import com.young.sarangbang.model.entity.banginfo.service.BangInfoService;
import com.young.sarangbang.model.dto.home.domain.DtoBangInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Date : 2022-03-10
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Slf4j
@Service
public class DtoBangInfoServiceImpl implements DtoBangInfoService {

    @Autowired
    BangInfoService bangInfoService;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public DtoBangInfo get(Object obj) throws SarangbangException {
        BangInfo bangInfo = null;

        if (obj instanceof Integer) {
            bangInfo = bangInfoService.get(((Integer) obj).intValue());
        } else if (obj instanceof String) {

        } else {
            throw new SarangbangException(SarangbangExceptionCode.NULL);
        }

        DtoBangInfo dtoBangInfo = objectMapper.convertValue(bangInfo, DtoBangInfo.class);
        return dtoBangInfo;
    }

    @Override
    public List<DtoBangInfo> gets(Object obj) throws SarangbangException {
        return null;
    }

    @Override
    public List<DtoBangInfo> getsAll() throws SarangbangException {
        List<BangInfo> bangInfoList = bangInfoService.getsAll();
        return convertFromListBangInfoToListDtoBangInfo(bangInfoList);
    }

    @Override
    public List<DtoBangInfo> getsAllByIsFavorites() throws SarangbangException {
        List<BangInfo> bangInfoList = bangInfoService.getsAllByIsFavorites();
        return convertFromListBangInfoToListDtoBangInfo(bangInfoList);
    }

    @Override
    public void setIsFavorites(Integer bangInfoSeq, Boolean isFavorites) throws SarangbangException {
        bangInfoService.updateIsFavorites(bangInfoSeq, isFavorites);
    }

    public List<DtoBangInfo> convertFromListBangInfoToListDtoBangInfo(List<BangInfo> bangInfoList) throws SarangbangException {
        List<DtoBangInfo> dtoBangInfoList;
        dtoBangInfoList = objectMapper.convertValue(bangInfoList, new TypeReference<List<DtoBangInfo>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });

        return dtoBangInfoList;
    }

}
