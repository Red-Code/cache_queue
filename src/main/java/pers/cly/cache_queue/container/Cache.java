package pers.cly.cache_queue.container;

/**
 * Created by Administrator on 2017/8/5.
 */

import java.io.Serializable;

/**
 * 代理模式，代理接口
 * 代理类中增加相应的缓存淘汰算法
 */
public interface Cache<K,V>{
    int size();

    boolean isEmpty();

    boolean containsKey(Object key);

    boolean containsValue(Object value);

    V get(Object key);

    V put(K key, V value);

    V remove(Object key);

    void clear();
}
