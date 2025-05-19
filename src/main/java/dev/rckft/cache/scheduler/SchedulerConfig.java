package dev.rckft.cache.scheduler;

import dev.rckft.cache.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Bean
    public CacheEvictionScheduler cacheEvictionScheduler(Cache cache) {
        return new CacheEvictionScheduler(cache);
    }

}
