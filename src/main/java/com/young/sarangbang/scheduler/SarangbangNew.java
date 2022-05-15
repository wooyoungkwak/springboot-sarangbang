package com.young.sarangbang.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Date : 2022-04-28
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Slf4j
@Component
public class SarangbangNew {

    @Scheduled(fixedDelay = 1 * 1000)
    public void sampleRunning() {
        log.info("sampleRunning ... ");

    }
}
