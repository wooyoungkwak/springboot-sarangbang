package com.young.sarangbang.model.entity.estateagency.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Date : 2022-03-07
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Getter
@Setter
@Entity
public class EstateAgency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer estateAgencySeq;

    @Column
    private Integer addressSeq;     // 주소 외래 키

    @Column
    private String address;         // 부동산 주소

    @Column
    private String phone;           // 부동산 전화

    @Column (nullable = false)
    private String name;            // 부동산 이름

    @Column
    private String agencyName;      // 부동산 거래자 이름

    @Column
    private String agencyPhone;     // 부동산 거래자 전화

    @Column(nullable = false)
    private Boolean isFavorites;


}
