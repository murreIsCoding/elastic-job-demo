import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;

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
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("192.168.11.210:2181", "my-job"));
        regCenter.init();
        return regCenter;
    }

    private static JobConfiguration createJobConfiguration(String jobName) {
        // 创建作业配置
        JobConfiguration jobConfig = JobConfiguration.newBuilder(jobName, 10).jobListenerTypes("simpleJobListener").cron("0/5 * * * * ?").build();
        return jobConfig;
    }

}
