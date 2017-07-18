package pers.cly.cache_queue.container;

/**
 * Created by CLY on 2017/7/18.
 */

/**
 * 提供作为hash容器的操作
 */
public interface HashContainer<K,T> {
    /**
     * 初始化hash容器
     */
    void init();

    /**
     * 将键值对存入容器
     * @param k
     * @param t
     */
    void set(K k,T t);

    /**
     * 根据键值名提取元素
     * @param k
     * @return
     */
    T put(K k);

    /**
     * 根据键名删除元素
     * @param k
     */
    void del(K k);

    /**
     * 判断容器大小
     * @return
     */
    int getSize();
}