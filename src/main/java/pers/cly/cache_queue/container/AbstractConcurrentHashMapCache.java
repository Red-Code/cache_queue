package pers.cly.cache_queue.container;

import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/8/5.
 */

/**
 * 缓存容器ConcurrentHashMap的代理类，
 * 增加相应的缓存淘汰算法
 * @param <K> 用来查询的键值名
 * @param <V> 需要缓存的对象
 */
public abstract class AbstractConcurrentHashMapCache<K,V> implements Cache<K,V> {
    //目标对象
    //此时出使用软引用缓存对象，内存不够时首先回收缓存对象。
    protected ConcurrentHashMap<K,SoftReference<V>> concurrentHashMap = new ConcurrentHashMap<K, SoftReference<V>>();

    @Override
    public int size() {
        return concurrentHashMap.size();
    }

    @Override
    public boolean isEmpty() {
        return concurrentHashMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return concurrentHashMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return concurrentHashMap.containsValue(value);
    }

    @Override
    public void clear() {
        concurrentHashMap.clear();
    }
}
