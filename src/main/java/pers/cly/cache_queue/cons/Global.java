package pers.cly.cache_queue.cons;

import java.util.concurrent.TimeUnit;

/**
 * Created by CLY on 2017/7/18.
 */
public interface Global {
    //设定线程池大小
    int pool_size = 2;
    //UserCacheTask首次延时执行时间
    int userCacheTask_first_delay = 60;
    TimeUnit userCacheTask_first_unit = TimeUnit.MINUTES;//单位
    //UserEntityCache如果为null，则以下时间后再运行UserCacheTask
    int userCacheTask_null_delay = 60;
    TimeUnit userCacheTask_null_unit= TimeUnit.MINUTES;//单位
    //UserEntity的缓存时间（单位是分钟）
    int userCacheTime = 30;

    /**
     * 首先，查看缓存的时间一定在缓存插入时间的后面。
     * 关键就是在后面多久。
     *
     * 如果缓存时间为x分,k时，y天
     * 则缓存插入时间的分钟加上x，要小于等于，当前时间加上（x/60）
     */
}
