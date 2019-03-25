package com.login.cache.impl;

import com.login.cache.CacheService;
import com.login.cache.ValueLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * 缓存操作类
 *
 * @author mistaker
 */
@Component
public class CacheServiceImpl implements CacheService {

    Logger logger = LoggerFactory.getLogger(CacheService.class);


    @Autowired
    private CacheManager cacheManager;
    

    public Cache getCache(String cacheName) {
        return cacheManager.getCache(cacheName);
    }

    @Override
    public <K, V> V get(String cacheName, K key) {
        Cache cache = getCache(cacheName);
        Cache.ValueWrapper valueWrapper = cache.get(key);
        if (valueWrapper != null) {
            return (V) valueWrapper.get();
        } else {
            return null;
        }
    }

    @Override
    public <K, V> V get(String cacheName, K key, ValueLoader<K, V> valueLoader) {
        V value = get(cacheName, key);
        if (value != null) {
            return value;
        }
        synchronized (CacheService.class) {
            value = get(cacheName, key);
            if (value != null) {
                return value;
            }

            logger.info("load value for cache: {}, key: {}", cacheName, key);

            value = valueLoader.load(key);
            Cache cache = getCache(cacheName);
            cache.put(key, value);
            return value;
        }
    }

    @Override
    public <K, V> void put(String cacheName, K key, V value) {
        Cache cache = getCache(cacheName);
        cache.put(key, value);
    }

    @Override
    public <K> void evict(String cacheName, K key) {
        logger.info("evict cache: {}, key: {}", cacheName, key);
        Cache cache = getCache(cacheName);
        cache.evict(key);
    }

    @Override
    public void clear(String cacheName) {
        logger.info("clear cache: {}", cacheName);

        Cache cache = getCache(cacheName);
        cache.clear();
    }

    @Override
    public CacheManager getManager() {
        return cacheManager;
    }

}
