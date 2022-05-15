package com.young.sarangbang.model.entity.estateagency.service;

import com.young.sarangbang.exception.SarangbangException;
import com.young.sarangbang.model.entity.estateagency.domain.EstateAgency;

import java.util.List;

/**
 * Date : 2022-03-07
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
public interface EstateAgencyService {

    public EstateAgency get(Integer estateAgencySeq) throws SarangbangException;

    public List<EstateAgency> getsAll() throws SarangbangException;

    public List<EstateAgency> getsAllByIsFavorites() throws SarangbangException;

    public void add(EstateAgency estateAgency) throws SarangbangException;

    public void adds(List<EstateAgency> estateAgencies) throws SarangbangException;

    public void updateIsFavorites(Integer estateAgencySeq, Boolean isFavorites) throws SarangbangException;

}
