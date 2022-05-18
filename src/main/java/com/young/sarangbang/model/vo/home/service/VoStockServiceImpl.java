package com.young.sarangbang.model.vo.home.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.young.sarangbang.exception.SarangbangException;
import com.young.sarangbang.model.entity.stock.domain.StockCompany;
import com.young.sarangbang.model.entity.stock.domain.StockInfo;
import com.young.sarangbang.model.entity.stock.enums.StockType;
import com.young.sarangbang.model.entity.stock.service.StockCompanyService;
import com.young.sarangbang.model.entity.stock.service.StockInfoService;
import com.young.sarangbang.model.mapper.StockMapper;
import com.young.sarangbang.model.vo.home.domain.VoStockCompany;
import com.young.sarangbang.model.vo.home.domain.VoStockInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Date : 2022-03-17
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@RequiredArgsConstructor
@Service
public class VoStockServiceImpl implements VoStockService {

    private final StockInfoService stockInfoService;
    private final ObjectMapper objectMapper;
    private final StockMapper stockMapper;
    private final StockCompanyService stockCompanyService;

    @Override
    public List<VoStockInfo> getsByFirstStockCompanyAndDayType() throws SarangbangException {
        List<StockInfo> stockInfos = stockInfoService.getsByFirstStockCompanyAndDayType();

        List<VoStockInfo> voStockInfos = objectMapper.convertValue(stockInfos, new TypeReference<List<VoStockInfo>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        return voStockInfos;
    }

    @Override
    public List<VoStockInfo> gets(String alias, StockType stockType) throws SarangbangException {
        List<StockInfo> stockInfos = stockInfoService.gets(alias, stockType);

        List<VoStockInfo> voStockInfos = objectMapper.convertValue(stockInfos, new TypeReference<List<VoStockInfo>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        return voStockInfos;
    }

    @Override
    public List<VoStockCompany> gets() throws SarangbangException {
//        List<StockCompany> stockCompanyList = stockCompanyService.gets(); // JPA-Hibernate
        List<StockCompany> stockCompanyList = stockMapper.getsStockCompany(); // mybatis
        List<VoStockCompany> voStockCompanyList = objectMapper.convertValue(stockCompanyList, new TypeReference<List<VoStockCompany>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });

        return voStockCompanyList;
    }

}
