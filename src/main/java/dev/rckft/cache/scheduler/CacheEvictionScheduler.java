package dev.rckft.cache.scheduler;


import dev.rckft.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;

public class CacheEvictionScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheEvictionScheduler.class);
    private final Cache cache;

    public CacheEvictionScheduler(Cache cache) {
        this.cache = cache;
    }

    @Scheduled(cron = "${cache.scheduler.cron}")
    public void deleteExpiredCacheEntry() {
        LOGGER.info("Deleting expired entries");
        cache.deleteExpiredEntries(Instant.now());
    }


}
