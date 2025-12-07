package org.example.bookmyshow_lld.services;

import org.springframework.stereotype.Service;

@Service
public interface CacheService {

    void set(String key, Object value);

    Object get(String key);

    void delete(String key);

    void getAllKeysAndValues();

    void deleteAll();
    /**
     * Set the key only if it does not already exist (atomic lock).
     * @return true if key was set, false if it already existed
     */
    boolean setIfAbsent(String key, String value, long ttlSeconds);
}
