package com.csbr.qingcloud.user.scm.feign;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.config.FastCallFeignConfiguration;
import com.csbr.qingcloud.user.scm.domain.dto.camunda.CsbrCommonProcessRequest;
import com.csbr.qingcloud.user.scm.domain.dto.camunda.CsbrCommonTaskRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangheng
 * @date 2020/8/4 15:49
 */
@FeignClient(value = "camunda-server",configuration = FastCallFeignConfiguration.class)
public interface CamundaFeign {

    /**
     * 初始化流程
     */
    @RequestMapping(value = "/csbr-process/initProcess", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject initProcess(@RequestBody CsbrCommonProcessRequest csbrCommonProcessRequest);


    /**
     * 流程审批
     */
    @RequestMapping(value = "/csbr-process/approveProcess", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject approveProcess(@RequestBody CsbrCommonTaskRequest csbrCommonTaskRequest);

    /**
     * 查找历史详情
     */
    @RequestMapping(value = "/csbr-process/getHisTasksDetail/{processInstanceId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject getHisTasksDetail(@PathVariable String processInstanceId);

    /**
     * 流程提交
     */
    @RequestMapping(value = "/csbr-process/startProcess", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject startProcess(@RequestParam String processInstanceId,@RequestParam String userId);

}
