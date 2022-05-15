package com.young.sarangbang.model.entity.address.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Date : 2022-03-27
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Getter
@AllArgsConstructor
public enum ZipcodeHan {
    Busan("부산"),
    Chungcheongbukdo("충청북도"),
    Chungcheongnamdo("충청남도"),
    Daegu("대구"),
    Daejeon("대전"),
    Gangwondo("강원도"),
    Gwangju("광주"),
    Gyeonggido("경기도"),
    Gyeongsangbukdo("경상북도"),
    Gyeongsangnamdo("경상남도"),
    Incheon("인천"),
    Jeollabukdo("전라북도"),
    Jeollanamdo("전라남도"),
    Sejongsi("세종시"),
    Seoul("서울"),
    Ulsan("울산");

    private String han;
}
