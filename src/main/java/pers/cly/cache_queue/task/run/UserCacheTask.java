package pers.cly.cache_queue.task.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.Scope;
import pers.cly.cache_queue.cons.Global;
import pers.cly.cache_queue.container.CacheQueue;
import pers.cly.cache_queue.po.UserEntity;
import pers.cly.cache_queue.task.pool.ScheduledThreadPoolUtil;

import javax.annotation.Resource;
import javax.print.attribute.standard.MediaSize;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static java.text.DateFormat.Field.MINUTE;

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
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = scheduledThreadPoolUtil.getThreadPool();

        if (cacheQueue.isEmpty()){
            scheduledThreadPoolExecutor.schedule(this, Global.userCacheTask_null_delay,Global.userCacheTask_null_unit);
        }else {
        }
    }

    public static void main(String[] arg){
        long now = new Date().getTime();
        long now2 = new Date().getTime()-1;

        long k = now-now2;
        System.out.println(k);

//        UserCacheTask demo = new UserCacheTask();
//        demo.run();
//        long now = new Date().getTime();
//        System.out.println(now);
////        System.out.println((int) now);
//        long startTime11 = System.currentTimeMillis();//获取当前时间
//        for (int i=0;i<999999999;i++){
//
//        }
//        long endTime11 = System.currentTimeMillis();
//        System.out.println("运行时间："+(endTime11-startTime11)+"ms");
    }
}
