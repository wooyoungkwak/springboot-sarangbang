package com.young.sarangbang.model.entity.files.service;

import com.young.sarangbang.model.entity.files.domain.FileInfo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Date : 2022-05-10
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
public interface FileInfoService {

    public FileInfo select(Integer fileInfoSeq);

    public List<FileInfo> selectAll();

    public FileInfo insert(FileInfo fileInfo);

    public List<FileInfo> inserts(List<FileInfo> fileInfos);

    public boolean updateByDelYN(Integer fileInfoSeq, Boolean delYN);
}
