package com.young.sarangbang.model.entity.files.repository;

import com.young.sarangbang.model.entity.files.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Date : 2022-05-10
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
public interface FileInfoRepository extends JpaRepository<FileInfo, Integer> {
    
}
