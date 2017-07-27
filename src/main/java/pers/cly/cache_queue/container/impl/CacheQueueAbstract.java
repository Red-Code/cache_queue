package pers.cly.cache_queue.container.impl;

import pers.cly.cache_queue.container.CacheQueue;
import pers.cly.cache_queue.container.HashContainer;

import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by CLY on 2017/7/19.
 */

/**
 * 所有缓存队列的抽象父类，利用泛型提供了各个接口方法的实现
 * @param <K> 用来查询的键值名（如果存放的为实体对象，一般设为id）
 * @param <T>需要缓存的对象
 */
public abstract class CacheQueueAbstract<K,T> implements CacheQueue<T>,HashContainer<K,T> {

    ConcurrentHashMap<K,SoftReference<Node<K,T>>> concurrentHashMap = new ConcurrentHashMap<K, SoftReference<Node<K,T>>>();
    ConcurrentLinkedQueue<SoftReference<Node<K,T>>> concurrentLinkedQueue = new ConcurrentLinkedQueue<SoftReference<Node<K,T>>>();

    @Override
    public void init() {
        concurrentHashMap.clear();
        concurrentLinkedQueue.clear();
    }

    @Override
    public void put(K k, T t) {
        long now_time = System.currentTimeMillis();
        Node<K,T> node = new Node(k,t,now_time);//连同时间戳一起装入节点，然后装入容器。
        SoftReference<Node<K,T>> nodeRefer = new SoftReference (node);//此处使用软引用，当内存不够用时，会将其回收

        concurrentHashMap.put(k,nodeRefer);
        concurrentLinkedQueue.add(nodeRefer);
    }

    @Override
    public T get(K k) {
        Node<K,T> node = concurrentHashMap.get(k).get();
        if (node==null){
            return null;
        }else {
            return node.getT();
        }
    }

    @Override
    public T out() {
        //获取并移除头元素（如果队列为空则返回null）
        SoftReference<Node<K,T>> head = concurrentLinkedQueue.poll();
        if (head==null){
            return null;
        }else {
            concurrentHashMap.remove(head.get().getKey());
            return head.get().getT();
        }
    }

    @Override
    public T front() {
        SoftReference<Node<K,T>> head = concurrentLinkedQueue.peek();
        if (head==null){
            return null;
        }else {
            return head.get().getT();
        }
    }

    @Override
    public Long frontTime() {
        SoftReference<Node<K,T>> head = concurrentLinkedQueue.peek();
        if (head==null){
            return null;
        }else {
            return head.get().getTime();
        }
    }

    @Override
    public boolean isEmpty() {
        return concurrentHashMap.isEmpty();
    }

    @Override
    public int getSize() {
        //此处不要使用concurrentLinkedQueue.size()，因为这个方法会遍历整个集合
        return concurrentHashMap.size();
    }

    /**
     * linkedhashmap键值节点，负责保存数据与插入时间
     * @param <K,T>
     */
    class Node<K,T>{
        T t;//存储实体
        long time;//存储入队时间戳
        K key;//该节点在hashmap中所对应的键值名

        public Node(K key,T t,long time){
            this.key = key;
            this.t = t;
            this.time = time;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
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
