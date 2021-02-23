import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyJob implements SimpleJob {

    @Override
    public void execute(ShardingContext context) {
        String jobName = context.getJobName();

        Integer id = Integer.parseInt(jobName.split("-")[1]);
        Integer shardingTotalCount = context.getShardingTotalCount();
        Integer shardingItem = context.getShardingItem();
        //这里是负载均衡实现重点，id 和 shardingTotalCount 取模，如果等于shardingItem，那么就由当前机器处理，如果不是就跳过
        if (id % shardingTotalCount == shardingItem) {
            print(jobName, shardingTotalCount, shardingItem);
        }
    }

    private void print(String jobName, Integer shardingTotalCount, Integer shardingItem) {
        String dateStr = new SimpleDateFormat("HH:mm:ss").format(new Date());

        String log = String.format("%s 执行任务：%s,总序号：%s，分片序号：%s", dateStr, jobName, shardingTotalCount, shardingItem);
        System.out.println(log);
    }
}
