package com.young.sarangbang.model.vo.home.service;

import com.young.sarangbang.exception.SarangbangException;
import com.young.sarangbang.model.entity.stock.domain.StockCompany;
import com.young.sarangbang.model.entity.stock.enums.StockType;
import com.young.sarangbang.model.vo.home.domain.VoStockCompany;
import com.young.sarangbang.model.vo.home.domain.VoStockInfo;

import java.util.List;

/**
 * Date : 2022-03-17
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
public interface VoStockService {

    public List<VoStockInfo> getsByFirstStockCompanyAndDayType() throws SarangbangException;

    public List<VoStockInfo> gets(String alias, StockType stockType) throws SarangbangException;

    public List<VoStockCompany> gets() throws SarangbangException;
}
