package com.young.sarangbang.model.dto.home.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.young.sarangbang.model.dto.home.domain.DtoBangInfo;
import com.young.sarangbang.model.dto.home.domain.DtoFileInfo;
import com.young.sarangbang.model.entity.files.domain.FileInfo;
import com.young.sarangbang.model.entity.files.enums.FileType;
import com.young.sarangbang.model.entity.files.service.FileInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Date : 2022-05-09
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DtoFileInfoServiceImpl implements DtoFileInfoService{

    private final ObjectMapper objectMapper;

    private final FileInfoService fileInfoService;

    @Override
    public DtoFileInfo get() {
        DtoFileInfo dtoFileInfo = new DtoFileInfo();

        dtoFileInfo.setFileInfoSeq(1);
        dtoFileInfo.setDescription("샘플파일 1");
        dtoFileInfo.setFileName("sample1.xlsx");
        dtoFileInfo.setRegDt(LocalDateTime.now());
        dtoFileInfo.setDelDt(null);
        dtoFileInfo.setDelYN(Boolean.FALSE);

        return dtoFileInfo;
    }

    @Override
    public List<DtoFileInfo> gets() {
        List<FileInfo> fileInfos = fileInfoService.selectAll();
        List<DtoFileInfo> dtoFileInfos = objectMapper.convertValue(fileInfos, new TypeReference<List<DtoFileInfo>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        return dtoFileInfos;
    }

    @Override
    public DtoFileInfo add(DtoFileInfo dtoFileInfo) throws Exception {
        FileInfo fileInfo = objectMapper.convertValue(dtoFileInfo, FileInfo.class);
        FileInfo result = fileInfoService.insert(fileInfo);
        return objectMapper.convertValue(result, DtoFileInfo.class);
    }

    @Override
    public List<DtoFileInfo> adds(List<DtoFileInfo> dtoFileInfos) throws Exception {
        List<FileInfo> fileInfos = objectMapper.convertValue(dtoFileInfos, new TypeReference<List<FileInfo>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        for(FileInfo fileInfo: fileInfos) fileInfo.setDelYN(false);

        List<FileInfo> results = fileInfoService.inserts(fileInfos);
        return objectMapper.convertValue(results, List.class);
    }

    @Override
    public boolean modify(Integer fileInfoSeq, boolean delYN) {
        return fileInfoService.updateByDelYN(fileInfoSeq, delYN);
    }

}
