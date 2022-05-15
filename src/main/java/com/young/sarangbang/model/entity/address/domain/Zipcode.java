package com.young.sarangbang.model.entity.address.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Date : 2022-03-27
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Getter
@Entity
public class Zipcode {
    @Id
    int zipcodeSeq;                     // 26
    @Column
    String zipcode;                     // 0 우편 번호
    @Column
    String siDoHan;                     // 1시도 한글 이름
    @Column
    String siDoEng;                     // 2시도 영문 이름
    @Column
    String sigunguHan;                  // 3시군구 한글 이름
    @Column
    String sigunguEng;                  // 4시군군 영문 이름
    @Column
    String eupMyunHan;                  // 5읍면 한글 이름
    @Column
    String eupMyunEng;                  // 6읍면 영문 이름
    @Column
    String roadNameCode;                // 7도로명 코드 번호
    @Column
    String roadNameHan;                 // 8도로명 한글 이름
    @Column
    String roadNameEng;                 // 9도로면 영문 이름
    @Column
    String isUnderground;               // 10 지하도 여부 ( 0: 지상,  1:지하도)
    @Column
    int buildingMajorNumber;            // 11 건물 주 번호
    @Column
    int buildingMinorNumber;            // 12 건물 부 번호
    @Column
    String buildingNumber;              // 13 건물 번호
    @Column
    String deliveryLocationName;        // 14 배달 위치 이름
    @Column
    String sigunguBuildingName;         // 15 시군구 건물 이름
    @Column
    String rawDongCode;                 // 16 법적 동 코드
    @Column
    String rawDongName;                 // 17 법적 동 이름
    @Column
    String leeName;                     // 18 리 이름
    @Column
    String administrationDongName;      // 19
    @Column
    String isMountain;                  // 20 산 여부 ( 0: 일반, 1: 산)
    @Column
    int giMajorNumber;                  // 21 지 주 번호
    @Column
    String eupMyunCode;                 // 22 읍면 코드
    @Column
    int giMinorNumber;                  // 23 지 부 번호
    @Column
    String guZipcode;                   // 24 구 우편번호 (NULL)
    @Column
    String zipcodeIndex;                // 25 우번번호 인덱트 (NULL)

}
