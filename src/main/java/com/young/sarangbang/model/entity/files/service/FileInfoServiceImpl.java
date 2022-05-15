package com.young.sarangbang.model.entity.files.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.young.sarangbang.model.entity.files.domain.FileInfo;
import com.young.sarangbang.model.entity.files.domain.QFileInfo;
import com.young.sarangbang.model.entity.files.repository.FileInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Date : 2022-05-10
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    FileInfoRepository fileInfoRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public FileInfo select(Integer fileInfoSeq) {
        return fileInfoRepository.findById(fileInfoSeq).get();
    }

    @Override
    public List<FileInfo> selectAll() {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QFileInfo fileInfo = QFileInfo.fileInfo;
        return query.selectFrom(fileInfo)
                .where(fileInfo.delYN.eq(Boolean.FALSE))
                .fetch();
    }

    @Override
    public FileInfo insert(FileInfo fileInfo) {
        return fileInfoRepository.save(fileInfo);
    }

    @Override
    public List<FileInfo> inserts(List<FileInfo> fileInfos) {
        return fileInfoRepository.saveAll(fileInfos);
    }

    @Transactional
    @Override
    public boolean updateByDelYN(Integer fileInfoSeq, Boolean delYN) {
        try {
            JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
            QFileInfo info = QFileInfo.fileInfo;
            queryFactory
                    .update(info)
                    .set(info.delYN, delYN)
                    .set(info.delDt, LocalDateTime.now())
                    .where(info.fileInfoSeq.eq(fileInfoSeq))
                    .execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
