package pers.cly.cache_queue.task.run;

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

/**
 * 查找队列是否为空，如果为null，则创建一个延迟线程，到时再执行该方法。
 * 如果不为null,则查看队首元素是否到达过期时间，如果过期就出队，然后递归，继续查看下一个队首元素是否过期。
 * 如果队首元素没到过期时间，就设置定时线程，定时到该元素的过期时间再执行此任务。
 */
public class UserCacheTask implements Runnable{
    @Resource(name = "userEntityCache")
    private CacheQueue<UserEntity> cacheQueue;
    @Resource(name = "cacheScheduledThreadPoolUtil")
    private ScheduledThreadPoolUtil scheduledThreadPoolUtil;

    @Override
    public void run() {
        checkDelay();
    }

    private void checkDelay(){
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = scheduledThreadPoolUtil.getThreadPool();

        if (cacheQueue.isEmpty()){
            scheduledThreadPoolExecutor.schedule(this, Global.userCacheTask_null_delay,Global.cache_time_unit);
        }else {
            long delay_time = cacheQueue.frontTime()+Global.userEntityCacheTimeUnit;//计算过期时间并返回
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
}
