package com.csbr.cloud.flinkserver.sink;

import com.csbr.cloud.flinkserver.config.MongoUtils;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.tuple.Tuple5;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangheng
 * @date 2019/12/16 10:28
 */
@Slf4j
public class MongoDBSink extends RichSinkFunction<Tuple5<String, String, String, String, String>> {

    private static final long serialVersionUID = 1L;
    MongoClient mongoClient = null;

    public void invoke(Tuple5<String, String, String, String, String> value, SinkFunction.Context context) {
        try {
            if (mongoClient != null) {
                mongoClient = (MongoClient) MongoUtils.getConnect();
                MongoDatabase db = mongoClient.getDatabase("soul_db");
                MongoCollection collection = db.getCollection("kafka");
                List<Document> list = new ArrayList<>();
                Document doc = new Document();
                doc.append("IP", value.f0);
                doc.append("TIME", value.f1);
                doc.append("CourseID", value.f2);
                doc.append("Status_Code", value.f3);
                doc.append("Referer", value.f4);
                list.add(doc);
                log.info("Insert Starting");
                collection.insertMany(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open(Configuration parms) throws Exception {
        super.open(parms);
        mongoClient = (MongoClient)MongoUtils.getConnect();
    }

    public void close() throws Exception {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
