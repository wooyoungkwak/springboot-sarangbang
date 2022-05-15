package com.young.sarangbang.model.dto.home.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.young.sarangbang.exception.SarangbangException;
import com.young.sarangbang.model.entity.estateagency.domain.EstateAgency;
import com.young.sarangbang.model.entity.estateagency.service.EstateAgencyService;
import com.young.sarangbang.model.dto.home.domain.DtoEstateAgency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Date : 2022-03-10
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Slf4j
@Service
public class DtoEstateAgencyServiceImpl implements DtoEstateAgencyService {

    @Autowired
    EstateAgencyService estateAgencyService;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public DtoEstateAgency get(Integer estateAgencySeq) throws SarangbangException {
        DtoEstateAgency estateAgency = (DtoEstateAgency) estateAgencyService.get(estateAgencySeq);
        return estateAgency;
    }

    @Override
    public List<DtoEstateAgency> getsAll() throws SarangbangException {
        List<EstateAgency> estateAgencyList = estateAgencyService.getsAll();

        List<DtoEstateAgency> dtoEstateAgencyList = objectMapper.convertValue(estateAgencyList, new TypeReference<List<DtoEstateAgency>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });

        return dtoEstateAgencyList;
    }

    @Override
    public List<DtoEstateAgency> getsAllByIsFavorites() throws SarangbangException {
        List<EstateAgency> estateAgencyList = estateAgencyService.getsAllByIsFavorites();
        List<DtoEstateAgency> dtoEstateAgencyList = objectMapper.convertValue(estateAgencyList, new TypeReference<List<DtoEstateAgency>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        return dtoEstateAgencyList;
    }

    @Override
    public void setIsFavorites(Integer estateAgencySeq, Boolean isFavorites) throws SarangbangException {
        estateAgencyService.updateIsFavorites(estateAgencySeq, isFavorites);
    }

}
