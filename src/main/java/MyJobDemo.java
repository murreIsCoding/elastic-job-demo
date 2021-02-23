import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;

import java.util.ResourceBundle;

public class MyJobDemo {

    public static void main(String[] args) {
        CoordinatorRegistryCenter coordinatorRegistryCenter = createRegistryCenter();

        new ScheduleJobBootstrap(coordinatorRegistryCenter, new MyJob(), createJobConfiguration("MyJob-0")).schedule();
        new ScheduleJobBootstrap(coordinatorRegistryCenter, new MyJob(), createJobConfiguration("MyJob-1")).schedule();
        new ScheduleJobBootstrap(coordinatorRegistryCenter, new MyJob(), createJobConfiguration("MyJob-2")).schedule();
        new ScheduleJobBootstrap(coordinatorRegistryCenter, new MyJob(), createJobConfiguration("MyJob-3")).schedule();
        new ScheduleJobBootstrap(coordinatorRegistryCenter, new MyJob(), createJobConfiguration("MyJob-4")).schedule();
        new ScheduleJobBootstrap(coordinatorRegistryCenter, new MyJob(), createJobConfiguration("MyJob-5")).schedule();
        new ScheduleJobBootstrap(coordinatorRegistryCenter, new MyJob(), createJobConfiguration("MyJob-6")).schedule();
        new ScheduleJobBootstrap(coordinatorRegistryCenter, new MyJob(), createJobConfiguration("MyJob-7")).schedule();
        new ScheduleJobBootstrap(coordinatorRegistryCenter, new MyJob(), createJobConfiguration("MyJob-8")).schedule();
        new ScheduleJobBootstrap(coordinatorRegistryCenter, new MyJob(), createJobConfiguration("MyJob-9")).schedule();

    }

    private static CoordinatorRegistryCenter createRegistryCenter() {
        String zkServer = readProp("config", "zkServer");
        String zkPort = readProp("config", "zkPort");
        String zkCon = zkServer + ":" + zkPort;
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration(zkCon, "my-job"));
        regCenter.init();
        return regCenter;
    }

    private static JobConfiguration createJobConfiguration(String jobName) {
        // 创建作业配置
        JobConfiguration jobConfig = JobConfiguration.newBuilder(jobName, 10).jobListenerTypes("simpleJobListener").cron("0/5 * * * * ?").build();
        return jobConfig;
    }

    /**
     * 默认读取resource文件夹下的配置文件，文件只输入文件名就行，如jdbc.properties 输入jdbc就行，不需要输入后缀
     *
     * @param confName  配置文件文件名，不要后缀
     * @param filedName 读取的属性名
     * @return 读取的属性值
     */
    public static String readProp(String confName, String filedName) {
        ResourceBundle bundle = ResourceBundle.getBundle(confName);
        String result = bundle.getString(filedName).trim();
        return result;
    }

}
