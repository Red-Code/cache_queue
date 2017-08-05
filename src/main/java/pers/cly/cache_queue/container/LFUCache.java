package pers.cly.cache_queue.container;

/**
 * Created by Administrator on 2017/8/5.
 */

/**
 * 当缓存容器使用的是LFU缓存策略时，实现此接口。
 * 对外提供"频率链表"的获取方法，
 * 定义频率链表接口。
 * @param <K>
 * @param <V>
 */
public interface LFUCache<K,V> extends Cache<K,V> {

    LFULinkedListCache getLFULinkedListCache();

    interface LFULinkedListCache{

    }
}
