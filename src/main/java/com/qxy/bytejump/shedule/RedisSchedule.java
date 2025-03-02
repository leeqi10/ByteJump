package com.qxy.bytejump.shedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class RedisSchedule {
    @CacheEvict(value = "user", allEntries = true)
    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(cron = "5 * * * * ?")
    public void redisCacheFlush(){
        log.info("redis缓存已清空");
    }
}
