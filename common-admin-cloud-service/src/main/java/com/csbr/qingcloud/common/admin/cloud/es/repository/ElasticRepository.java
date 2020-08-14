package com.csbr.qingcloud.common.admin.cloud.es.repository;

import com.csbr.qingcloud.common.admin.cloud.es.entity.DocBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * @author zhangheng
 * @date 2020/7/21 17:24
 */
public interface ElasticRepository extends ElasticsearchRepository<DocBean,Long> {
    //默认的注释
//    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"content\" : \"?\"}}}}")
    Page<DocBean> findByContent(String content, Pageable pageable);

    Page<DocBean> findByFirstCode(String firstCode, Pageable pageable);

    Page<DocBean> findBySecondCode(String SecondCode, Pageable pageable);
}
