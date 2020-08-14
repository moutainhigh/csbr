package com.csbr.qingcloud.eureka.server.listen;

//import com.csbr.qingcloud.eureka.server.crontask.ScheduledTasks;
import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangheng
 * @date 2019/11/19 12:05
 */
@Slf4j
@Component
public class EurekaStateChangeListener {

    /**
     * 服务下线监听事件
     * @param eurekaInstanceCanceledEvent
     */
    @EventListener
    public void listen(EurekaInstanceCanceledEvent eurekaInstanceCanceledEvent) {
        //服务断线事件
        String appName = eurekaInstanceCanceledEvent.getAppName();
        String serverId = eurekaInstanceCanceledEvent.getServerId();
        System.out.println(appName);
        System.out.println(serverId);
    }

    /**
     * 服务注册事件
     * @param event
     */
    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
//        log.info("微服务信息"+instanceInfo);
        if(instanceInfo.getStatus()==InstanceInfo.InstanceStatus.UP){
            log.info(instanceInfo.getAppName()+"微服务状态:"+instanceInfo.getStatus());
            //执行调度任务
            //获取脚本所在的目录
//            String configFilePath = Thread.currentThread().getContextClassLoader().getResource("zipkinbuild.sh").getPath();
//            log.info(configFilePath);
//            ScheduledTasks.execShell(configFilePath);
//            ScheduledTasks.RemoteInvokeShell("139.9.190.186",2022,"xx","xx","sh /mnt/zipkin/zipkinbuild.sh");
        }
    }

    /**
     * 服务续约事件
     * @param event
     */
    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        event.getAppName();
        event.getServerId();
    }

    /**
     * Eureka注册中心启动事件
     * @param event
     */
    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {

    }

    /**
     * Eureka Server启动事件
     * @param event
     */
    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        //Server启动
    }

}
