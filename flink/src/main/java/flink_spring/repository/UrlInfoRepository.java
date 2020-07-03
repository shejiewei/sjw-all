package flink_spring.repository;

/**
 * Created by shejiewei on 2020/5/11.
 */
import flink_spring.UrlInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlInfoRepository {

    UrlInfo selectByPrimaryKey(Integer id);

    UrlInfo selectByUrl(String url);

    int insert(UrlInfo urlInfo);

    List<UrlInfo> queryAll();
}