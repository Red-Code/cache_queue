package pers.cly.cache_queue.DEL;

/**
 * Created by CLY on 2017/7/18.
 */

/**
 * 提供队列的相关操作（不提供入队操作）
 * @param <T> 队列中的缓存元素
 */
public interface CacheQueue<T> {
    /**
     * 初始化队列
     */
    void init();

    /**
     * 删除队首元素，并返回其值
     * @return 已被删除的队首元素
     */
    T out();

    /**
     * 读取队首元素
     * @return 队首元素
     */
    T front();

    /**
     * 读取队首元素的插入时间戳
     * @return 插入时间戳
     */
    Long frontTime();

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
}
