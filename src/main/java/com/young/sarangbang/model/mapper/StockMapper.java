package com.young.sarangbang.model.mapper;

import com.young.sarangbang.model.entity.stock.domain.StockCompany;
import com.young.sarangbang.model.entity.stock.domain.StockInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Date : 2022-04-25
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Mapper
public interface StockMapper {

    public List<StockInfo> getsStockInfos();

    public List<StockCompany> getsStockCompany();

}
