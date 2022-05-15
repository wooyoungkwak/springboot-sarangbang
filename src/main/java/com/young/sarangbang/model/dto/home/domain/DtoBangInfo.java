package com.young.sarangbang.model.dto.home.domain;

import com.young.sarangbang.model.entity.estateagency.domain.EstateAgency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Date : 2022-03-10
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DtoBangInfo {

    private Integer bangInfoSeq;

    private Integer room;       // 방수

    private Integer bathRoom;   // 욕실수

    private Integer floor;      // 층수

    private String deal;        // 거래 방식 (매매/분양/전세)

    private String location;    // 위치

    private String heating;     // 난방

    private Integer addressSeq; // 주소 (주소 키)

    private String address;     // 주소

    private String direction;   // 방향 (남향/동향/북향/서향)

    private String price;       // 가격

    private String place;       // 대지

    private String ground;      // 연면적

    private LocalDateTime registerDate;     // 사용일 (등록일)

    private Boolean isFavorites;  // 즐겨찾기

    private EstateAgency estateAgency;      // 부동산 키

}
