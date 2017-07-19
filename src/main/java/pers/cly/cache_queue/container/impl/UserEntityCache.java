package pers.cly.cache_queue.container.impl;

import org.springframework.stereotype.Component;
import pers.cly.cache_queue.po.UserEntity;

/**
 * Created by CLY on 2017/7/18.
 */
@Component
public class UserEntityCache extends CacheQueueAbstract<Long,UserEntity>{
}