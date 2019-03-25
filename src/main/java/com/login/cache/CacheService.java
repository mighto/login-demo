package com.login.cache;

import org.springframework.cache.CacheManager;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface CacheService {

    <K, V> V get(@NotBlank String cacheName, @NotNull K key);

    <K, V> V get(@NotBlank String cacheName, K key, ValueLoader<K, V> valueLoader);

    <K, V> void put(@NotBlank String cacheName, @NotNull K key, V value);

    /**
     * 根据key，单点清除cache
     *
     * @param cacheName
     * @param key
     * @param <K>
     */
    <K> void evict(@NotBlank String cacheName, K key);

    /**
     * 整体清除整个cacheName
     *
     * @param cacheName
     */
    void clear(@NotBlank String cacheName);

    CacheManager getManager();

}
