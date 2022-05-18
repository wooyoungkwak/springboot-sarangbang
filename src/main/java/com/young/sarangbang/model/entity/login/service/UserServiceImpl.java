package com.young.sarangbang.model.entity.login.service;

import com.google.common.collect.Lists;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.young.sarangbang.encrypt.YoungEncoder;
import com.young.sarangbang.exception.EncryptedException;
import com.young.sarangbang.exception.EncryptedExceptionCode;
import com.young.sarangbang.exception.SarangbangException;
import com.young.sarangbang.exception.SarangbangExceptionCode;
import com.young.sarangbang.model.entity.login.domain.QUser;
import com.young.sarangbang.model.entity.login.domain.User;
import com.young.sarangbang.model.entity.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Date : 2022-03-07
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = SarangbangException.class)
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final EntityManager entityManager;

    @Override
    public User get(Integer userSeq) throws SarangbangException {
        User user = userRepository.findById(userSeq).get();
        if (user != null) {
            try {
                user.setPassword(YoungEncoder.decrypt(user.getPassword()));
            } catch (EncryptedException e) {
                log.error("사용자 정보 가져오기 오류 : ", e);
                throw new SarangbangException(SarangbangExceptionCode.NULL);
            }
        }
        return user;
    }

    @Override
    public User get(String userName) throws SarangbangException {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        return query.selectFrom(QUser.user)
                .where(QUser.user.username.eq(userName))
                .fetchOne();
    }

    @Override
    public User getByDB(String userName) throws SarangbangException {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        return query.selectFrom(QUser.user)
                .where(QUser.user.username.eq(userName))
                .fetchOne();
    }

    @Override
    public List<User> gets() throws SarangbangException {
        List<User> users = userRepository.findAll();
        if (users == null) {
            users = Lists.newArrayList();
        }
        return users;
    }

    @Override
    public boolean isUser(String userName, String password) throws SarangbangException {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);

        try {
            String passwordKey = YoungEncoder.encrypt(password);
            if (query.selectFrom(QUser.user)
                    .where(QUser.user.username.eq(userName))
                    .where(QUser.user.password.eq(passwordKey))
                    .fetchOne() != null) {
                return true;
            }
        } catch (EncryptedException e) {
            log.error(EncryptedExceptionCode.ENCRYPT_FAILURE.getMessage(), e);
            throw new SarangbangException(SarangbangExceptionCode.NULL);
        }
        return false;
    }

    @Override
    public boolean isUser(String userName) throws SarangbangException {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);

        if (query.selectFrom(QUser.user)
                .where(QUser.user.username.eq(userName))
                .fetchOne() != null) {
            return true;
        }
        return false;
    }

    @Override
    public void add(User user) throws SarangbangException {
        try {
            user.setPassword(YoungEncoder.encrypt(user.getPassword()));
            userRepository.save(user);
        } catch (Exception e) {
            log.error("사용자 추가 오류 : ", e);
            throw new SarangbangException(SarangbangExceptionCode.NULL);
        }
    }

    @Override
    public void adds(List<User> users) throws SarangbangException {
        try {
            userRepository.saveAll(users);
        } catch (Exception e) {
            log.error("사용자 추가 오류 : ", e);
            throw new SarangbangException(SarangbangExceptionCode.NULL);
        }
    }

}
