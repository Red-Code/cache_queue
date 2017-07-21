package pers.cly.cache_queue.container.impl;

import pers.cly.cache_queue.container.CacheQueue;
import pers.cly.cache_queue.container.HashContainer;

import java.lang.ref.SoftReference;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by CLY on 2017/7/19.
 */

/**
 * 所有缓存队列的抽象父类，利用泛型提供了各个接口方法的实现
 * @param <K> 用来查询的键值名（如果存放的为实体对象，一般设为id）
 * @param <T>需要缓存的对象
 */
public abstract class CacheQueueAbstract<K,T> implements CacheQueue<T>,HashContainer<K,T> {

    LinkedHashMap<K,SoftReference<Node<T>>> linkedHashMap;

    /**
     * 构造方法，初始化LinkedHashMap
     */
    public CacheQueueAbstract(){
        linkedHashMap = new LinkedHashMap<K,SoftReference<Node<T>>>();
    }

    @Override
    public void init() {
        linkedHashMap.clear();
    }

    @Override
    public void set(K k, T t) {
        long now_time = System.currentTimeMillis();
        Node<T> node = new Node(t,now_time);//连同时间戳一起装入节点，然后装入容器。
        SoftReference<Node<T>> nodeRefer = new SoftReference (node);//此处使用软引用，当内存不够用时，会将其回收
        linkedHashMap.put(k,nodeRefer);
    }

    @Override
    public T get(K k) {
        Node<T> node = linkedHashMap.get(k).get();
        if (node==null){
            return null;
        }else {
            return node.getT();
        }
    }

    @Override
    public void del(K k) {
        linkedHashMap.remove(k);
    }

    @Override
    public T out() {
        Iterator<K> it = linkedHashMap.keySet().iterator();
        if (it.hasNext()){
            K key = it.next();
            Node<T> re = linkedHashMap.remove(key).get();
            return re.getT();
        }else {
            return null;
        }
    }

    @Override
    public T front() {
        Iterator<Map.Entry<K, SoftReference<Node<T>>>> it = linkedHashMap.entrySet().iterator();
        if (it.hasNext()){
            return it.next().getValue().get().getT();
        }else {
            return null;
        }
    }

    @Override
    public Long frontTime() {
        Iterator<Map.Entry<K, SoftReference<Node<T>>>> it = linkedHashMap.entrySet().iterator();
        if (it.hasNext()){
            return it.next().getValue().get().getTime();
        }else {
            return null;
        }
    }

    @Override
    public boolean isEmpty() {
        return linkedHashMap.isEmpty();
    }

    @Override
    public int getSize() {
        return linkedHashMap.size();
    }

    /**
     * linkedhashmap键值节点，负责保存数据与插入时间
     * @param <T>
     */
    class Node<T>{
        T t;//存储实体
        long time;//存储入队时间戳

        public Node(T t,long time){
            this.t = t;
            this.time = time;
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }
    }
}
