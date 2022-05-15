package com.young.sarangbang.model.dto.home.service;

import com.young.sarangbang.exception.SarangbangException;
import com.young.sarangbang.model.dto.home.domain.DtoBangInfo;

import java.util.List;

/**
 * Date : 2022-03-10
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
public interface DtoBangInfoService {

    public DtoBangInfo get(Object obj) throws SarangbangException;

    public List<DtoBangInfo> gets(Object obj) throws SarangbangException;

    public List<DtoBangInfo> getsAll() throws SarangbangException;

    public List<DtoBangInfo> getsAllByIsFavorites() throws SarangbangException;

    public void setIsFavorites(Integer bangInfoSeq, Boolean isFavorites) throws SarangbangException;

}
