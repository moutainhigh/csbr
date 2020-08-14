package com.csbr.cloud.camunda.server.controller;

import com.csbr.cloud.camunda.server.request.CommonProcessRequest;
import io.swagger.annotations.ApiOperation;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhangheng
 * @date 2019/11/15 15:41
 */

@RestController
@RequestMapping("/simpleRestProcessHandler")
public interface SimpleRestProcessHandler {

    @ApiOperation(value = "流程提交", notes = "流程提交")
    @RequestMapping(value = "/simpleStartProcess", method = RequestMethod.POST)
    public List<TaskDto> simpleStartProcess(@RequestBody CommonProcessRequest commonProcessRequest, HttpServletRequest request) throws Exception;

}

