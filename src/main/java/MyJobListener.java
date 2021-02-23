import org.apache.shardingsphere.elasticjob.infra.listener.ElasticJobListener;
import org.apache.shardingsphere.elasticjob.infra.listener.ShardingContexts;

public class MyJobListener implements ElasticJobListener {

    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        System.out.println("执行前");
        // do something ...
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        System.out.println("执行后");
        // do something ...
    }

    @Override
    public String getType() {
        return "simpleJobListener";
    }
}
