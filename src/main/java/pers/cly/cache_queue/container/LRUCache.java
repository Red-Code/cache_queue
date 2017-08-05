package pers.cly.cache_queue.container;

/**
 * Created by Administrator on 2017/8/5.
 */

/**
 * 当缓存容器使用的是LRU缓存策略时，实现此接口。
 * 对外提供缓存队列的获取方法，
 * 定义缓存队列接口。
 * @param <K>
 * @param <V>
 */
public interface LRUCache<K,V> extends Cache<K,V> {

    /**
     * 获取本缓存容器的缓存队列
     * @return
     */
    LRUQueueCache getLRUQueueCache();

    interface LRUQueueCache<K>{
        /**
         * 当有新键值对存入缓存容器时，
         * 将此键值对的键值插入缓存队列尾部
         * @param k
         */
        void enQueue(K k);

        /**
         * 将头部节点出队
         * @return
         */
        Node<K> deQueue();

        update
        /**
         * 返回
         * @return
         */
        Node<K> frontQueue();

        /**
         * 读取队首元素的插入时间戳
         * @return 插入时间戳
         */
        Long frontQueueTime();

        /**
         * 判断队列是否为空
         * @return 为null则返回true
         */
        boolean isEmpty();

        /**
         * 判断队列大小
         * @return
         */
        int getSize();

        /**
         * 缓存队列中存储的节点，
         * 每个节点中包含了缓存键值对的键值与该键值对的更新时间
         * @param <K>
         */
        interface Node<K>{
            void setAccessTime(Long time);

            Long getAccessTime();

            void setKey(K k);

            K getKey();
        }
    }
}
