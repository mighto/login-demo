package com.login.cache;

public interface ValueLoader<K, V> {

    V load(K key);

}
