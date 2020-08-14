package com.csbr.qingcloud.common.admin.cloud.controller;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.es.domain.vo.ElasticDataVo;
import com.csbr.cloud.es.domain.vo.QueryVo;
import com.csbr.cloud.es.entity.ElasticEntity;
import com.csbr.cloud.es.util.ElasticUtil;
import com.csbr.cloud.es.util.ElasticsearchHelper;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangheng
 * @date 2020/7/16 17:57
 */
@Slf4j
@RequestMapping("/elastic")
@RestController
@RefreshScope//刷新配置
@Api(tags = {"es数据管理"})
public class ElasticMgrController {

    @Autowired
    private ElasticsearchHelper baseElasticService;

//    @Autowired
//    LocationService locationService;


    /**
     * @Description 新增数据
     * @param elasticDataVo
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @date 2019/11/20 17:10
     */
    @PostMapping(value = "/add")
    public CommonRes add(@RequestBody ElasticDataVo elasticDataVo){
        CommonRes res = null;
        try {
            if(!StringUtils.isNotEmpty(elasticDataVo.getIdxName())){
                res = CommonRes.ok("索引为空",null);
                log.warn("索引为空");
                return res;
            }
            ElasticEntity elasticEntity = new ElasticEntity();
            elasticEntity.setId(elasticDataVo.getElasticEntity().getId());
            elasticEntity.setData(elasticDataVo.getElasticEntity().getData());

            baseElasticService.insertOrUpdateOne(elasticDataVo.getIdxName(), elasticEntity);

        } catch (Exception e) {
            res = CommonRes.ok("S0001",e.getMessage(),false);
            log.error("插入数据异常，metadataVo={},异常信息={}", elasticDataVo.toString(),e.getMessage());
        }
        return res;
    }


    /**
     * @Description 删除
     * @param elasticDataVo
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @date 2019/11/21 9:56
     */
    @PostMapping(value = "/delete")
    public CommonRes delete(@RequestBody ElasticDataVo elasticDataVo){
        CommonRes res = null;
        try {
            if(!StringUtils.isNotEmpty(elasticDataVo.getIdxName())){
                res = CommonRes.ok("索引为空",null);
                log.warn("索引为空");
                return res;
            }
            baseElasticService.deleteOne(elasticDataVo.getIdxName(),elasticDataVo.getElasticEntity());
        } catch (Exception e) {
            res = CommonRes.ok("S0001",e.getMessage(),false);
            log.error("删除数据失败");
        }
        return res;

    }

    /**
     * @Description
     * @param index 初始化Location区域，写入数据。
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @date 2019/11/20 17:10
     */
//    @GetMapping(value = "/addLocation/{index}")
//    public ResponseResult addLocation(@PathVariable(value = "index") String index){
//        ResponseResult response = getResponseResult();
//        try {
//            for(int lv=0;lv<4;lv++){
//                addLocationPage(1,100,index,lv);
//            }
//
//        } catch (Exception e) {
//            response.setCode(ResponseCode.ERROR.getCode());
//            response.setMsg("服务忙，请稍后再试");
//            response.setStatus(false);
//        }
//        return response;
//    }
//
//    public void addLocationPage(int pageNum,int pageSize,String index,int lv){
//        Location location = new Location();
//        location.setLv(lv);
//        PageHelper.startPage(pageNum, pageSize);
//        List<Location> locations = locationService.getList2(location);
//        PageInfo pageInfo = new PageInfo(locations);
//        if(!pageInfo.getList().isEmpty()){
//            log.error("第{}层级，第{}页，开始入ES库",lv,pageNum);
//            insertDatas(index,locations);
//            if(pageInfo.isHasNextPage()){
//                addLocationPage(pageInfo.getNextPage(),pageSize,index,lv);
//            }
//        }
//    }
//
//
//    public void insertDatas(String idxName,List<Location> locations){
//        List<ElasticEntity> elasticEntitys = new ArrayList<ElasticEntity>(locations.size());
//        for(Location _loca:locations){
//            ElasticEntity elasticEntity = new ElasticEntity();
//            elasticEntity.setId(_loca.getId().toString());
////            elasticEntity.setData(gson.toJson(_loca));
////            elasticEntitys.add(elasticEntity);
////            log.error(_loca.toString());
//        }
//        baseElasticService.insertBatch(idxName,elasticEntitys);
//    }

    /**
     * @Description
     * @param queryVo 查询实体对象
     * @throws
     * @date 2019/11/21 9:31
     */
    @GetMapping(value = "/get")
    public CommonRes get(@RequestBody QueryVo queryVo){

        CommonRes res = null;
        if(!StringUtils.isNotEmpty(queryVo.getIdxName())){
            res = CommonRes.ok("索引为空",null);
            log.warn("索引为空");
            return res;
        }

        try {
            Class<?> clazz = ElasticUtil.getClazz(queryVo.getClassName());
            Map<String,Object> params = queryVo.getQuery().get("match");
            Set<String> keys = params.keySet();
            MatchQueryBuilder queryBuilders=null;
            for(String ke:keys){
                queryBuilders = QueryBuilders.matchQuery(ke, params.get(ke));
            }
            if(null!=queryBuilders){
                SearchSourceBuilder searchSourceBuilder = ElasticUtil.initSearchSourceBuilder(queryBuilders);
                List<?> data = baseElasticService.search(queryVo.getIdxName(),searchSourceBuilder,clazz);
                res = CommonRes.ok("查询索引成功!",data);
            }
        } catch (Exception e) {
            res = CommonRes.ok("S0001",e.getMessage(),false);
            log.error("查询数据异常，metadataVo={},异常信息={}", queryVo.toString(),e.getMessage());
        }
        return res;
    }

//    public ResponseResult getResponseResult(){
//        return new ResponseResult();
//    }
}
