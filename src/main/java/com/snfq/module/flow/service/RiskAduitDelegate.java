/**
 * sinafenqi.com
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.snfq.module.flow.service;

import com.snfq.common.exception.BizException;
import com.snfq.module.flow.util.ConfConstant;
import com.snfq.module.flow.util.MailUtil;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.rest.sub.runtime.JobResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fujin
 * @version $Id: CustomerSaveDelegate.java, v 0.1 2017-10-11 10:11 boyuan Exp $$
 */
@Service("riskAduitDelegate")
public class RiskAduitDelegate implements JavaDelegate{

    private static Logger logger = LoggerFactory.getLogger(RiskAduitDelegate.class);

    @Autowired
    TaskService taskService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //需要将风控审批结果传递给下一环节
        //返回异常信息，需要返回的是
        try{
            logger.info("第三步：风控审批");
            int i = 1 / 0;
            delegateExecution.setVariable("风控审批","审批结束");
        }catch (Exception e){
            taskService.createTaskQuery().taskId(delegateExecution.getId()).singleResult();
            logger.info("第三步：风控审批");
            MailUtil.sendAndCc(ConfConstant.smtp, ConfConstant.from, ConfConstant.to, ConfConstant.copyto, "邮件警报",
                    "客户信息有误:" + e.getMessage() + "</br>"
                            + "实例ID：" + delegateExecution.getProcessInstanceId() + "</br>"
                            + "活动名称:" +delegateExecution.getCurrentActivityName(), ConfConstant.username, ConfConstant.password, ConfConstant.filename);
            throw new BizException(e);
        }
    }
}
