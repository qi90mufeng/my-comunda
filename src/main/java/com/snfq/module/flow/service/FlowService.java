/**
 * sinafenqi.com
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.snfq.module.flow.service;

import org.camunda.bpm.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author fujin
 * @version $Id: FlowService.java, v 0.1 2017-10-10 15:44 boyuan Exp $$
 */
@Service
public class FlowService {

    private static Logger logger = LoggerFactory.getLogger(FlowService.class);
    @Autowired
    private TaskService taskService;

    /**
     * 完成任务
     * @return
     */
    public String complete(String taskId, String assigneer, Map param){
        //1、一系列业务逻辑处理：当前是否是指定的办理人、变量数据是否OK、当前指定的办理人是否还具有相应操作权限
        //2、下一环节，如果是User Task,需要指定assigneer；自动分配（分配规则）、认领、人工分配
        //3、
        taskService.complete(taskId, param);
        return "success";
    }

    public String setAssignee(String assigneer, String taskId){

        return "";
    }

    public String setValue(String orderNo,String userMobile){
        logger.info( "setValue" + orderNo + "-" + userMobile);
        return orderNo + "-" + userMobile;
    }
}
