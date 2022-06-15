package com.young.sarangbang.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Date : 2022-04-27
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Slf4j
@Configuration
public class UtilConfiguration {

    @Bean
    public ObjectMapper setObjectMapper() {
        log.info(" util configure register [ObjectMapper] ");
        ObjectMapper objectMapper = new ObjectMapper();

        // deserialize 하는 동안 매핑 되지 않은것이 있어도 무시하고 진행
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // ObjectMapper 에서 datetime 변환할때 jsr310 적용을 위해 JavaTimeModule 을 등록해야 함
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper;
    }

}
