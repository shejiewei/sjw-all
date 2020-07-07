package flink_spring;

/**
 * Created by shejiewei on 2020/5/11.
 */
import flink_spring.repository.UrlInfoRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component("urlInfoManager")
public class UrlInfoManagerImpl implements UrlInfoManager {

    @Autowired
    private UrlInfoRepository urlInfoRepository;

    @Override
    public int insert(UrlInfo urlInfo) {

        urlInfo.setHash(DigestUtils.md5Hex(urlInfo.getUrl()));

        UrlInfo info = urlInfoRepository.selectByUrl(urlInfo.getUrl());
        if(null != info)
        {
            return 0;
        }

        return urlInfoRepository.insert(urlInfo);
    }

    @Override
    public List<UrlInfo> queryAll() {
        return urlInfoRepository.queryAll();
    }
}
