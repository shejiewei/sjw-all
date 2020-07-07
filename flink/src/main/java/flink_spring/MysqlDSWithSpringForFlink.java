package flink_spring;

/**
 * Created by shejiewei on 2020/5/11.
 */

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class MysqlDSWithSpringForFlink extends RichSourceFunction<UrlInfo> implements ApplicationContextAware {

    private UrlInfoManager urlInfoManager;

    private ApplicationContext applicationContext;

    @Override
    public void open(Configuration parameters) throws Exception {
        log.info("------open connection,applicationContext=" + applicationContext);
        super.open(parameters);
        if(applicationContext == null){
            init();
        }

    }

    private void init(){
        // 在这里进行spring的初始化
        applicationContext = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
        urlInfoManager = (UrlInfoManager) applicationContext.getBean("urlInfoManager");
    }

    @Override
    public void run(SourceContext<UrlInfo> sourceContext) throws Exception {
        log.info("------query ");

        if(urlInfoManager == null){
            init();
        }

        List<UrlInfo> urlInfoList = urlInfoManager.queryAll();
        urlInfoList.parallelStream().forEach(urlInfo -> sourceContext.collect(urlInfo));
    }

    @Override
    public void cancel() {
        log.info("------cancel ");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}