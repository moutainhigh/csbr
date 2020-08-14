package com.csbr.cloud.flinkserver.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangheng
 * @date 2019/12/16 10:31
 */
public class MongoUtils {

    public static MongoClient getConnect(){
        ServerAddress serverAddress1 = new ServerAddress("localhost", 27017);
        //集群
        ServerAddress serverAddress2 = new ServerAddress("localhost", 27017);
        ServerAddress serverAddress3 = new ServerAddress("localhost", 27017);
        List<ServerAddress> seeds = new ArrayList<>();
        seeds.add(serverAddress1);
        seeds.add(serverAddress2);
        seeds.add(serverAddress3);
        MongoClientOptions options = MongoClientOptions.builder().sslEnabled(false).build();     //连接操作对象
//        List<MongoCredential> credential = new ArrayList<>();
        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("root", "soul_db", "root".toCharArray());
//        credential.add(mongoCredential1);
        //通过连接认证获取MongoDB连接
        MongoClient mongoClient = new MongoClient(serverAddress1,mongoCredential,options);
        //集群链接
//        MongoClient mongoClient = new MongoClient(seeds,mongoCredential,options);
        return mongoClient;
    }
}
