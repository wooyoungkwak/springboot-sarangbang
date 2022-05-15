package com.young.sarangbang.model.entity.files.domain;

import com.young.sarangbang.model.entity.files.enums.FileType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Date : 2022-05-10
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Getter
@Setter
@Entity
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer fileInfoSeq;

    @Column (nullable = false)
    private FileType fileType = FileType.NONE;

    @Column (nullable = false)
    private String description;

    @Column (nullable = false)
    private String fileName;

    @Column (nullable = false)
    private LocalDateTime regDt;

    @Column
    private LocalDateTime delDt;

    @Column (nullable = false)
    private Boolean delYN = Boolean.FALSE;

}
