package org.example.bookmyshow_lld.services;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.Set;

@Service
public class RedisService implements CacheService {

    private final StringRedisTemplate stringRedisTemplate;

    public RedisService(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void set(String key, Object value) {
        stringRedisTemplate
                .opsForValue()
                .set(key, value.toString(), 2, TimeUnit.MINUTES);
    }

    @Override
    public Object get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public void getAllKeysAndValues() {
        Set<String> keys = stringRedisTemplate.keys("*");
        if (keys == null || keys.isEmpty()) {
            System.out.println("No keys in Redis");
            return;
        }
        keys.forEach(key ->
                System.out.println("Key: " + key +
                        " | Value: " + stringRedisTemplate.opsForValue().get(key))
        );
    }

    @Override
    public void deleteAll() {
        Set<String> keys = stringRedisTemplate.keys("*");
        if (keys != null && !keys.isEmpty()) {
            stringRedisTemplate.delete(keys);
        }
    }

    @Override
    public boolean setIfAbsent(String key, String value, long ttlSeconds) {
        Boolean success = stringRedisTemplate
                .opsForValue()
                .setIfAbsent(key, value, Duration.ofSeconds(ttlSeconds));
        return Boolean.TRUE.equals(success);
    }
}
