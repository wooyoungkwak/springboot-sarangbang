package com.young.sarangbang.model.entity.stock.service;

import com.young.sarangbang.model.entity.stock.domain.StockCompany;
import com.young.sarangbang.model.entity.stock.domain.StockInfo;
import com.young.sarangbang.model.entity.stock.enums.StockType;

import java.util.List;

/**
 * Date : 2022-03-17
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
public interface StockInfoService {

    public StockInfo get(Integer stockInfoSeq);

    public List<StockInfo> gets(StockCompany stockCompany, StockType stockType);

    public List<StockInfo> gets(String alias, StockType stockType);

    public List<StockInfo> getsByFirstStockCompanyAndDayType();

}
