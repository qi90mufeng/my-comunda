/**
 * sinafenqi.com
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.snfq.module.flow.service;

import com.snfq.common.exception.BizException;
import com.snfq.module.flow.util.ConfConstant;
import com.snfq.module.flow.util.MailUtil;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author fujin
 * @version $Id: CustomerSaveDelegate.java, v 0.1 2017-10-11 10:11 boyuan Exp $$
 */
@Service("customerSaveDelegate")
public class CustomerSaveDelegate implements JavaDelegate{

    private static Logger logger = LoggerFactory.getLogger(CustomerSaveDelegate.class);
    /**
     * 事务边界
     *
     *  1、execute方法的参数DelegateExecution execution可以在流程中各个结点之间传递流程变量
     *
     * @param delegateExecution
     * @throws Exception
     */
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //返回异常信息，需要返回的是
        try{
            logger.info("第二步：客户信息保存成功");
            //int i = 1/0;
            delegateExecution.setVariable("客户信息","保存成功"); //
        }catch (Exception e){
            logger.info("第二步：客户信息保存失败");
            MailUtil.sendAndCc(ConfConstant.smtp, ConfConstant.from, ConfConstant.to, ConfConstant.copyto, "邮件警报",
                    "客户信息有误:" + e.getMessage() + "</br>"
                            + "实例ID：" + delegateExecution.getProcessInstanceId() + "</br>"
                            + "活动名称:" +delegateExecution.getCurrentActivityName(), ConfConstant.username, ConfConstant.password, ConfConstant.filename);
            throw new BizException(e);
        }
    }
}
