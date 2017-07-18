package pers.cly.cache_queue.container.impl;

import org.springframework.stereotype.Component;
import pers.cly.cache_queue.container.CacheQueue;
import pers.cly.cache_queue.container.HashContainer;
import pers.cly.cache_queue.po.UserEntity;

/**
 * Created by CLY on 2017/7/18.
 */
@Component
public class UserEntityCache implements CacheQueue<UserEntity>,HashContainer<Long,UserEntity> {

    @Override
    public void init() {

    }

    @Override
    public void set(Long aLong, UserEntity userEntity) {

    }

    @Override
    public UserEntity put(Long aLong) {
        return null;
    }

    @Override
    public void del(Long aLong) {

    }

    @Override
    public void in(UserEntity userEntity) {

    }

    @Override
    public UserEntity out() {
        return null;
    }

    @Override
    public UserEntity front() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
