package com.csbr.cloud.camunda.server.client;

import io.swagger.annotations.ApiOperation;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangheng
 * @date 2019/11/15 15:53
 */
@FeignClient(name = "camunda-server", url = "http://127.0.0.1:8090/engine-rest/engine/default")
public interface CamundaRestfulApiClient {

    @ApiOperation(value = "启动流程", notes = "根据流程定义ID启动流程 ,StartProcessInstanceDto")
    @RequestMapping(value = "/process-definition/{id}/start", method = RequestMethod.POST, consumes = "application/json")
    public ProcessInstanceDto startProcessById(@PathVariable("id") String id,
                                               @RequestBody(required = false) String entity) throws Exception;

    @ApiOperation(value = "启动流程", notes = "根据流程定义key启动最新版本并且无租户的流程")
    @RequestMapping(value = "/process-definition/key/{key}/start", method = RequestMethod.POST, consumes = "application/json")
    public ProcessInstanceDto startProcessByKey(@PathVariable("key") String key,
                                                @RequestBody(required = false) String entity) throws Exception;

    @ApiOperation(value = "启动流程", notes = "根据流程定义key启动最新版本并且是所属租户的流程")
    @RequestMapping(value = "/process-definition/key/{key}/tenant-id/{tenantId}/start", method = RequestMethod.POST, consumes = "application/json")
    public ProcessInstanceDto startProcessByKeyWithTenant(@RequestHeader("Authentication") String auth,
                                                          @PathVariable("key") String key, @PathVariable("tenantId") String tenantId,
                                                          @RequestBody(required = false) String entity) throws Exception;

    @ApiOperation(value = "查询运行任务", notes = "根据查询条件过滤运行任务,TaskQueryDto")
    @RequestMapping(value = "/task", method = RequestMethod.POST, consumes = "application/json")
    public ProcessInstanceDto queryTask(@RequestBody(required = false) String entity) throws Exception;
}
