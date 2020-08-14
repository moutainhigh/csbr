package com.csbr.qingcloud.common.admin.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.es.domain.vo.IdxVo;
import com.csbr.cloud.es.util.ElasticsearchHelper;
import com.csbr.qingcloud.common.admin.cloud.es.entity.Event;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangheng
 * @date 2020/7/16 17:54
 */
@Slf4j
@RequestMapping("/elastic")
@RestController
@RefreshScope//刷新配置
@Api(tags = {"es相关操作"})
public class ElasticIndexController {

    @Autowired
    ElasticsearchHelper baseElasticService;

//    @GetMapping(value = "/")
//    public ResponseResult index(String index){
//        return new ResponseResult();
//    }

    /**
     * @Description 创建Elastic索引
     * @param idxVo
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @date 2019/11/19 11:07
     */
//    @PostMapping(value = "/createIndex")
//    public CommonRes createIndex(@RequestBody IdxVo idxVo){
//        CommonRes res = null;
//        try {
//            //索引不存在，再创建，否则不允许创建
//            if(!baseElasticService.isExistsIndex(idxVo.getIdxName())){
//                String idxSql = JSON.toJSONString(idxVo.getIdxSql());
//                log.warn(" idxName={}, idxSql={}",idxVo.getIdxName(),idxSql);
//                baseElasticService.createIndex(idxVo.getIdxName(),idxSql);
//                res = CommonRes.ok("索引创建成功!",null);
//            } else{
//                res = CommonRes.ok("索引已经存在，不允许创建",null);
//            }
//        } catch (Exception e) {
//            res = CommonRes.ok("S0001",e.getMessage(),false);
//        }
//        return res;
//    }

    @PostMapping(value = "/createIndex")
    public CommonRes createIndex(@RequestParam String idxName){
        CommonRes res = null;
        try {
            //索引不存在，再创建，否则不允许创建
            if(!baseElasticService.isExistsIndex(idxName)){
                String idxSql = JSON.toJSONString(new Event());
                log.warn(" idxName={}, idxSql={}",idxName,idxSql);
//                Event event = new Event();
                baseElasticService.createIndex(idxName, Event.class);
                res = CommonRes.ok("索引创建成功!",null);
            } else{
                res = CommonRes.ok("索引已经存在，不允许创建",null);
            }
        } catch (Exception e) {
            res = CommonRes.ok("S0001",e.getMessage(),false);
        }
        return res;
    }


    /**
     * @Description 判断索引是否存在；存在-TRUE，否则-FALSE
     * @param index
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @date 2019/11/19 18:48
     */
    @GetMapping(value = "/exist/{index}")
    public CommonRes indexExist(@PathVariable(value = "index") String index){

        CommonRes res = null;
        try {
            if(!baseElasticService.isExistsIndex(index)){
                log.error("index={},不存在",index);
                res = CommonRes.ok("索引不存在!",null);
            } else {
                res = CommonRes.ok("索引已经存在!",null);
            }
        } catch (Exception e) {
            res = CommonRes.ok("S0001",e.getMessage(),false);
        }
        return res;
    }

    @GetMapping(value = "/del/{index}")
    public CommonRes indexDel(@PathVariable(value = "index") String index){
        CommonRes res = null;
        try {
            baseElasticService.deleteIndex(index);
            res = CommonRes.ok("删除索引成功!",null);
        } catch (Exception e) {
            res = CommonRes.ok("S0001",e.getMessage(),false);
        }
        return res;
    }
}
