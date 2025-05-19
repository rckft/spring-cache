package dev.rckft.cache;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class Cache {

    private record CacheEntry(Double sum, Instant ttl) {}
    private static final long TTL = Duration.ofMinutes(1).toMillis();
    private final Map<Addends, CacheEntry> data = new ConcurrentHashMap<>();

    public Optional<Double> get(Addends addends) {
        var cacheEntry = data.get(addends);
        if (cacheEntry != null) {
            return Optional.ofNullable(cacheEntry.sum);
        }
        return Optional.empty();
    }

    public void save(Addends addends, Double sum) {
        data.put(addends, new CacheEntry(sum, Instant.now().plusMillis(TTL)));
    }

    public void deleteExpiredEntries(Instant now) {
        data.values().removeIf((cacheEntry -> cacheEntry.ttl.isAfter(now)));
    }

}

