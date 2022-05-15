package com.young.sarangbang.model.entity.banginfo.service;

import com.young.sarangbang.exception.SarangbangException;
import com.young.sarangbang.model.entity.banginfo.domain.BangInfo;

import java.util.List;

/**
 * Date : 2022-03-07
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
public interface BangInfoService {

    public BangInfo get(Integer bangInfoSeq);

    public List<BangInfo> getsAll();

    public List<BangInfo> getsAllByIsFavorites();

    public void add(BangInfo bangInfo) throws SarangbangException;

    public void adds(List<BangInfo> bangInfos) throws SarangbangException;

    public void updateIsFavorites(Integer bangInfoSeq, Boolean isFavorites) throws SarangbangException;
}
