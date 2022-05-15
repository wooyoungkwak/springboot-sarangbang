package com.young.sarangbang.model.dto.home.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Date : 2022-05-09
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Data
public class DtoFileInfo {

    private Integer fileInfoSeq;

    private String description;

    private String fileName;

    private LocalDateTime regDt;

    private LocalDateTime delDt;

    private Boolean delYN;

}
