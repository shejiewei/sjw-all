package flink_spring;

/**
 * Created by shejiewei on 2020/5/11.
 */
import java.util.List;

public interface UrlInfoManager {

    int insert(UrlInfo urlInfo);

    List<UrlInfo> queryAll();
}
