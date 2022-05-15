package com.young.sarangbang.model.dto.home.service;

import com.young.sarangbang.exception.SarangbangException;
import com.young.sarangbang.model.dto.home.domain.DtoEstateAgency;
import com.young.sarangbang.model.entity.estateagency.domain.EstateAgency;

import java.util.List;

/**
 * Date : 2022-03-10
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
public interface DtoEstateAgencyService {

    public DtoEstateAgency get(Integer estateAgencySeq) throws SarangbangException;

    public List<DtoEstateAgency> getsAll() throws SarangbangException;

    public List<DtoEstateAgency> getsAllByIsFavorites() throws SarangbangException;

    public void setIsFavorites(Integer estateAgencySeq, Boolean isFavorites) throws SarangbangException;

}
