package pers.cly.cache_queue.container;

/**
 * Created by Administrator on 2017/8/5.
 */
public class ConcurrentHashMapLFUCache<K,V> extends AbstractConcurrentHashMapCache<K,V> implements LFUCache<K,V> {

    @Override
    public V get(Object key) {
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
