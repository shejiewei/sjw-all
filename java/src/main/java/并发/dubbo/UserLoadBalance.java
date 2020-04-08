/*
package 并发.dubbo;

*/
/**
 * Created by shejiewei on 2019/12/20.
 *//*

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.LoadBalance;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

*/
/**
 * @author daofeng.xjf
 *
 * 负载均衡扩展接口
 * 必选接口，核心接口
 * 此类可以修改实现，不可以移动类或者修改包名
 * 选手需要基于此类实现自己的负载均衡算法
 *//*


*/
/**
 * 要求
 修改题目提供的扩展接口(UserLoadBalance)，实现一套自适应负载均衡机制。要求能够具备以下能力：
 1、Gateway(Consumer) 端能够自动根据服务处理能力变化动态最优化分配请求保证较低响应时间，较高吞吐量；
 2、Provider 端能自动进行服务容量评估，当请求数量超过服务能力时，允许拒绝部分请求，以保证服务不过载；
 3、当请求速率高于所有的 Provider 服务能力之和时，允许 Gateway( Consumer ) 拒绝服务新到请求。
 *//*

public class UserLoadBalance implements LoadBalance {

    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {


        return invokers.get(ThreadLocalRandom.current().nextInt(invokers.size()));
    }
}*/
