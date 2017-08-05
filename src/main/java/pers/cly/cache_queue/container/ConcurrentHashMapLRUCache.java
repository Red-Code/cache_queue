package pers.cly.cache_queue.container;

import java.lang.ref.SoftReference;

/**
 * Created by CLY on 2017/7/19.
 */


public class ConcurrentHashMapLRUCache<K,V> extends AbstractConcurrentHashMapCache<K,V> implements LRUCache<K,V> {

    @Override
    public V get(Object key) {
        SoftReference<V> v = concurrentHashMap.get(key);

        if (v!=null)
            return v.get();
        else
            return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }
}
