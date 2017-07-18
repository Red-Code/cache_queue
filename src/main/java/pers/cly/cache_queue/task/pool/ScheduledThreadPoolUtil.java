package pers.cly.cache_queue.task.pool;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by CLY on 2017/7/18.
 */

/**
 * 定时线程池工具
 */
public interface ScheduledThreadPoolUtil {
    /**
     * 初始化
     */
    void init();

    /**
     * 获取定时线程池
     * @return 线程池
     */
    ScheduledThreadPoolExecutor getThreadPool();

    /**
     * 销毁
     */
    void shutdown();
}
