package com.young.sarangbang.model.entity.stock.domain;

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
public class StockCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer stockCompanySeq;

    @Column (nullable = false)
    private String name;                // 회사 이름

    @Column (nullable = false)
    private String alias;               // 회사 이름 별칭

    @Column (nullable = false)
    private String address;             // 주소

    @Column
    private LocalDateTime birthDay;     // 설림일

}
