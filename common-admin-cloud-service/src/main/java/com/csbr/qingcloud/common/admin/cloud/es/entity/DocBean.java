package com.csbr.qingcloud.common.admin.cloud.es.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author zhangheng
 * @date 2020/7/21 17:24
 */
@Data
@NoArgsConstructor
//需要使用Document注解，indexName:库的名称，type:存储类型，一般使用bean的名称，
//分片（Shard）以及副本（Replica），分布式存储系统为了解决单机容量以及容灾的问题，都需要有分片以及副本机制。
@Document(indexName = "ems", shards = 1, replicas = 0)
public class DocBean {
    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String firstCode;

    @Field(type = FieldType.Keyword)
    private String secondCode;

    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String content;

    @Field(type = FieldType.Integer)
    private Integer type;

    public DocBean(Long id, String firstCode, String secondCode, String content, Integer type) {
        this.id = id;
        this.firstCode = firstCode;
        this.secondCode = secondCode;
        this.content = content;
        this.type = type;
    }
}
