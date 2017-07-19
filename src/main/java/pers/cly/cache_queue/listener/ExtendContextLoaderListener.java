package pers.cly.cache_queue.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pers.cly.cache_queue.task.pool.ScheduledThreadPoolUtil;

import javax.servlet.ServletContextEvent;
/**
 * Created by CLY on 2017/7/18.
 */
public class ExtendContextLoaderListener extends ContextLoaderListener{

    @Override
    public void contextInitialized(ServletContextEvent event){
        super.contextInitialized(event);
        //获取上下文
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        ScheduledThreadPoolUtil scheduledThreadPoolUtil = (ScheduledThreadPoolUtil) applicationContext.getBean("CacheScheduledThreadPoolUtil");
        scheduledThreadPoolUtil.setApplicationContext(applicationContext);
        scheduledThreadPoolUtil.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event){
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        ScheduledThreadPoolUtil scheduledThreadPoolUtil = (ScheduledThreadPoolUtil) applicationContext.getBean("CacheScheduledThreadPoolUtil");
        scheduledThreadPoolUtil.shutdown();
        super.contextDestroyed(event);
    }
}