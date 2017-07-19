package pers.cly.cache_queue.cons;

import java.util.concurrent.TimeUnit;

/**
 * Created by CLY on 2017/7/19.
 */
public class Global {
    //设定线程池大小
    public static final int pool_size = 2;
    //整个缓存系统中使用的时间单位
    public static final TimeUnit cache_time_unit = TimeUnit.MINUTES;
    //UserCacheTask首次延时执行时间
    public static final int userCacheTask_first_delay = 60;
    //UserEntityCache如果为null，则以下时间后再运行UserCacheTask
    public static final int userCacheTask_null_delay = 60;
    //UserEntity的缓存时间
    public static final int userEntityCacheTime = 30;
    public static long userEntityCacheTimeUnit;//UserEntity的缓存时间（时间戳形式），在容器启动时计算并赋值
}
