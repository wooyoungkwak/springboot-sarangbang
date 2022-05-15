package com.young.sarangbang.model.entity.stock.domain;

import com.young.sarangbang.model.entity.stock.enums.StockType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Date : 2022-03-17
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Getter
@Setter
@Entity
public class StockInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer stockInfoSeq;

    @Column (nullable = false)
    private StockType stockType;                 // 주식 정보 타입 (시간/일/월/년)

    @Column (nullable = false)
    private Integer price;                       // 주식 가격

    @Column
    private Integer highPrice;                   // 주식 가격 (상한가)

    @Column
    private Integer lowPrice;                    // 주식 가격 (하한가)

    @Column
    private LocalDateTime regDate;               // 주식 등록일

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "stockCompanySeq")
    private StockCompany stockCompany;            // 주식 회사 (외래키)

}
