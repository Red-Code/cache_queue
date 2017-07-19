package pers.cly.cache_queue.container.impl;

import org.springframework.stereotype.Component;
import pers.cly.cache_queue.container.CacheQueue;
import pers.cly.cache_queue.container.HashContainer;
import pers.cly.cache_queue.po.UserEntity;

import java.util.*;

/**
 * Created by CLY on 2017/7/18.
 */
@Component
public class UserEntityCache implements CacheQueue<UserEntity>,HashContainer<Long,UserEntity> {

    LinkedHashMap<Long,Node> linkedHashMap;

    /**
     * 构造方法，初始化LinkedHashMap
     */
    public UserEntityCache(){
        linkedHashMap = new LinkedHashMap<Long, Node>();
    }

    @Override
    public void init() {
        linkedHashMap.clear();
    }

    @Override
    public void set(Long aLong, UserEntity userEntity) {
        long now_time = System.currentTimeMillis();
        Node node = new Node(userEntity,now_time);//连同时间戳一起装入节点，然后装入容器。

        linkedHashMap.put(aLong,node);
    }

    @Override
    public UserEntity get(Long aLong) {
        Node node = linkedHashMap.get(aLong);
        return node.getUserEntity();
    }

    @Override
    public void del(Long aLong) {
        linkedHashMap.remove(aLong);
    }

    @Override
    public UserEntity out() {
        Iterator<Long> it = linkedHashMap.keySet().iterator();
        if (it.hasNext()){
            Long key = it.next();
            Node re = linkedHashMap.remove(key);
            return re.getUserEntity();
        }else {
            return null;
        }
    }

    @Override
    public UserEntity front() {
        Iterator<Map.Entry<Long, Node>> it = linkedHashMap.entrySet().iterator();
        if (it.hasNext()){
            return it.next().getValue().getUserEntity();
        }else {
            return null;
        }
    }

    @Override
    public Long frontTime() {
        Iterator<Map.Entry<Long, Node>> it = linkedHashMap.entrySet().iterator();
        if (it.hasNext()){
            return it.next().getValue().getTime();
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

    class Node{
        UserEntity userEntity;//存储实体
        long time;//存储入队时间戳

        public Node(UserEntity userEntity,long time){
            this.userEntity = userEntity;
            this.time = time;
        }

        public UserEntity getUserEntity() {
            return userEntity;
        }

        public void setUserEntity(UserEntity userEntity) {
            this.userEntity = userEntity;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }
    }
}
