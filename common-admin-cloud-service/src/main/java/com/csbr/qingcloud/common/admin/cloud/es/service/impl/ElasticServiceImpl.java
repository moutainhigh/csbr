package com.csbr.qingcloud.common.admin.cloud.es.service.impl;

import com.csbr.qingcloud.common.admin.cloud.es.entity.DocBean;
import com.csbr.qingcloud.common.admin.cloud.es.repository.ElasticRepository;
import com.csbr.qingcloud.common.admin.cloud.es.service.ElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @author zhangheng
 * @date 2020/7/21 17:31
 */
@Service
public class ElasticServiceImpl implements ElasticService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;
    @Autowired
    private ElasticRepository elasticRepository;

    private Pageable pageable = PageRequest.of(0, 10);

    @Override
    public void createIndex() {
        //设置索引信息(绑定实体类)  返回IndexOperations
        IndexOperations indexOperations = elasticsearchTemplate.indexOps(DocBean.class);
        //创建索引库
        indexOperations.create();
        //Creates the index mapping for the entity this IndexOperations is bound to.
        //为该IndexOperations绑定到的实体创建索引映射。  有一个为给定类创建索引的重载,需要类的字节码文件
        Document mapping = indexOperations.createMapping();
        //writes a mapping to the index  将刚刚通过类创建的映射写入索引
        indexOperations.putMapping(mapping);
//        elasticsearchTemplate.createIndex(DocBean.class);
        //实体类注解后，此处需要添加mapping，不然分词不生效
//        elasticsearchTemplate.putMapping(DocBean.class);
    }

    @Override
    public void deleteIndex(String index) {
        elasticsearchTemplate.deleteIndex(index);
    }

    @Override
    public void save(DocBean docBean) {
        elasticRepository.save(docBean);
    }

    @Override
    public void saveAll(List<DocBean> list) {
        elasticRepository.saveAll(list);
    }

    @Override
    public Iterator<DocBean> findAll() {
        return elasticRepository.findAll().iterator();
    }

    @Override
    public Page<DocBean> findByContent(String content) {
        return elasticRepository.findByContent(content, pageable);
    }

    @Override
    public Page<DocBean> findByFirstCode(String firstCode) {
        return elasticRepository.findByFirstCode(firstCode, pageable);
    }

    @Override
    public Page<DocBean> findBySecondCode(String SecondCode) {
        return elasticRepository.findBySecondCode(SecondCode, pageable);
    }

}
