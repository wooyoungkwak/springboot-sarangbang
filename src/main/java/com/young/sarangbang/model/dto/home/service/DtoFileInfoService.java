package com.young.sarangbang.model.dto.home.service;

import com.young.sarangbang.model.dto.home.domain.DtoFileInfo;

import java.util.List;

/**
 * Date : 2022-05-09
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
public interface DtoFileInfoService {

    public DtoFileInfo get();

    public List<DtoFileInfo> gets();

    public DtoFileInfo add(DtoFileInfo dtoFileInfo) throws Exception;

    public List<DtoFileInfo> adds(List<DtoFileInfo> dtoFileInfos) throws Exception;

    public boolean modify(Integer fileInfoSeq, boolean delYN) ;

}
