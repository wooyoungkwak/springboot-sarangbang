package com.young.sarangbang.model.entity.stock.repository;

import com.young.sarangbang.model.entity.stock.domain.StockInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Date : 2022-03-17
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
public interface StockInfoRepository extends JpaRepository<StockInfo, Integer> {

}
