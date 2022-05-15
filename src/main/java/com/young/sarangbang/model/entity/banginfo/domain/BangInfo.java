package com.young.sarangbang.model.entity.banginfo.domain;

import com.young.sarangbang.model.entity.estateagency.domain.EstateAgency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Date : 2022-03-07
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Getter
@Setter
@Entity
public class BangInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bangInfoSeq;

    @Column(nullable = false)
    private Integer room;       // 방수

    @Column(nullable = false)
    private Integer bathRoom;   // 욕실수

    @Column
    private Integer floor;      // 층수

    @Column(nullable = false)
    private String deal;        // 거래 방식 (매매/분양/전세)

    @Column
    private String location;    // 위치

    @Column
    private String heating;     // 난방

    @Column
    private Integer addressSeq; // 주소 (주소 키)

    @Column(nullable = false)
    private String address;     // 주소

    @Column(nullable = false)
    private String direction;   // 방향 (남향/동향/북향/서향)

    @Column
    private String price;       // 가격

    @Column
    private String place;       // 대지

    @Column
    private String ground;      // 연면적

    @Column
    private LocalDateTime registerDate;     // 사용일 (등록일)

    @Column(nullable = false)
    private Boolean isFavorites;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "estateAgencySeq")
    private EstateAgency estateAgency;      // 부동산 키

}

