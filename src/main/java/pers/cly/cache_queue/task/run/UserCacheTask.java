package pers.cly.cache_queue.task.run;

import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import pers.cly.cache_queue.cons.Global;
import pers.cly.cache_queue.container.CacheQueue;
import pers.cly.cache_queue.po.UserEntity;
import pers.cly.cache_queue.task.pool.ScheduledThreadPoolUtil;

import javax.annotation.Resource;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by CLY on 2017/7/14.
 */
public class UserCacheTask implements Runnable{
    @Resource(name = "userEntityCache")
    private CacheQueue<UserEntity> cacheQueue;
    @Resource(name = "cacheScheduledThreadPoolUtil")
    private ScheduledThreadPoolUtil scheduledThreadPoolUtil;

    /**
     * 查找队列是否为空，如果为null，则创建一个延迟线程，到时再执行该方法。
     如果不为null，则出队列底部的一个元素后，查找当前底部元素的“过期时间”，
     然后判断是否在当前时间之前，如果在当前时间之前，就出队列，然后再查看下一个元素········
     最后如果队列又为空了，则还是设定延迟线程。
     如果不为null，且存在还没到过期时间的元素，就创建延迟线程，延迟到过期时间再执行当前方法类。
     */
    @Override
    public void run() {
        checkDelay();
    }

    private void checkDelay(){
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = scheduledThreadPoolUtil.getThreadPool();

        if (cacheQueue.isEmpty()){
            scheduledThreadPoolExecutor.schedule(this, Global.userCacheTask_null_delay,Global.cache_time_unit);
        }else {
            long delay_time = calculationDelayTime(cacheQueue.frontTime());
            long now_time = System.currentTimeMillis();

            //距离过期的时间：若果结果为0或负数，则表示该值过期了，否则表示还没过期
            long distance_delay_time = delay_time - now_time;
            //"距离过期时间"小于1分钟的元素，都出队，否则设定下次的定时出队线程任务
            if (distance_delay_time<=60000){
                cacheQueue.out();
                checkDelay();
            }else {
                //距离当前队首元素过期时间distance_delay_time后，再次执行该任务，出掉它
                scheduledThreadPoolExecutor.schedule(this,distance_delay_time, TimeUnit.NANOSECONDS);
            }
        }
    }

    /**
     * 计算过期时间并返回
     * @param insert_time 原时间
     * @return 应该到期的时间
     */
    private long calculationDelayTime(long insert_time){
        long delay_time = 0;
        //将延迟时间计算成时间戳
        switch (Global.cache_time_unit){
            case NANOSECONDS:
                delay_time = Global.userCacheTime;
                break;
            case MICROSECONDS:
                delay_time = Global.userCacheTime*10;
                break;
            case MILLISECONDS:
                delay_time = Global.userCacheTime*100;
                break;
            case SECONDS:
                delay_time = Global.userCacheTime*1000;
                break;
            case MINUTES:
                delay_time = Global.userCacheTime*60000;
                break;
            case HOURS:
                delay_time = Global.userCacheTime*3600000;
                break;
            case DAYS:
                delay_time = Global.userCacheTime*86400000;
                break;
        }

        return insert_time+delay_time;
    }
}
