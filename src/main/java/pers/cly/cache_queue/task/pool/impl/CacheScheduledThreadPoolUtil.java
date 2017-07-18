package pers.cly.cache_queue.task.pool.impl;

import org.springframework.stereotype.Component;
import pers.cly.cache_queue.task.pool.ScheduledThreadPoolUtil;
import pers.cly.cache_queue.task.run.UserCacheTask;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by CLY on 2017/7/18.
 */
@Component("CacheScheduledThreadPoolUtil")
public class CacheScheduledThreadPoolUtil implements ScheduledThreadPoolUtil {
    private static ScheduledThreadPoolExecutor cache_pool;

    @Override
    public void init() {
        //设定线程池大小
        int pool_size = 2;
        //UserCacheTask延时执行时间
        int userCacheTask_delay_value = 1;
        //UserCacheTask延时执行时间单位
        TimeUnit userCacheTask_time_unit= TimeUnit.SECONDS;

        //因为是初始化，所以先关闭一下现有线程，重新创建。
        shutdown();
        //创建线程池
        cache_pool = new ScheduledThreadPoolExecutor(pool_size);

        //创建定时任务
        UserCacheTask userCacheTask = new UserCacheTask();

        //创建定时线程任务，userCacheTask_time_value值，单位userCacheTask_time_unit，后执行一次
        cache_pool.schedule(userCacheTask,userCacheTask_delay_value,userCacheTask_time_unit);

    }

    @Override
    public ScheduledThreadPoolExecutor getThreadPool() {
        return cache_pool;
    }

    @Override
    public void shutdown() {
        if (cache_pool!=null){
            cache_pool.shutdown();
        }
    }
}
