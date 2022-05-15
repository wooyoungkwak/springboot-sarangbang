package com.young.sarangbang.model.entity.stock.repository;

import com.young.sarangbang.model.entity.stock.domain.StockCompany;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Date : 2022-03-17
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
public interface StockCompanyRepository extends JpaRepository<StockCompany, Integer> {
}
