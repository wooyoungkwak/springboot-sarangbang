package com.young.sarangbang.model.vo.home.domain;

import com.young.sarangbang.model.entity.stock.domain.StockCompany;
import com.young.sarangbang.model.entity.stock.enums.StockType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Date : 2022-03-17
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoStockInfo {

    private Integer stockInfoSeq;

    private StockType stockType;                 // 주식 정보 타입 (시간/일/월/년)

    private Integer price;                       // 주식 가격

    private Integer highPrice;                   // 주식 가격 (상한가)

    private Integer lowPrice;                    // 주식 가격 (하한가)

    private LocalDateTime regDate;               // 주식 등록일

    private StockCompany stockCompany;           // 주식 회사

}
