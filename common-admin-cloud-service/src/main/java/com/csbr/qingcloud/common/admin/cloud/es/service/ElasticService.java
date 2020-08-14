package com.csbr.qingcloud.common.admin.cloud.es.service;

import com.csbr.qingcloud.common.admin.cloud.es.entity.DocBean;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @author zhangheng
 * @date 2020/7/21 17:29
 */
public interface ElasticService {
    void createIndex();

    void deleteIndex(String index);

    void save(DocBean docBean);

    void saveAll(List<DocBean> list);

    Iterator<DocBean> findAll();

    Page<DocBean> findByContent(String content);

    Page<DocBean> findByFirstCode(String firstCode);

    Page<DocBean> findBySecondCode(String SecondCode);
}
