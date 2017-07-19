package pers.cly.cache_queue.cons;

import java.util.concurrent.TimeUnit;

/**
 * Created by CLY on 2017/7/18.
 */
public interface Global {
    //设定线程池大小
    int pool_size = 2;
    //整个缓存系统中使用的时间单位
    TimeUnit cache_time_unit = TimeUnit.MINUTES;
    //UserCacheTask首次延时执行时间
    int userCacheTask_first_delay = 60;
    //UserEntityCache如果为null，则以下时间后再运行UserCacheTask
    int userCacheTask_null_delay = 60;
    //UserEntity的缓存时间
    int userCacheTime = 30;
}
