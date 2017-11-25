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

/**
 * @author fujin
 * @version $Id: CustomerSaveDelegate.java, v 0.1 2017-10-11 10:11 boyuan Exp $$
 */
@Service("resultDealDelegate")
public class ResultDealDelegate implements JavaDelegate{

    private static Logger logger = LoggerFactory.getLogger(ResultDealDelegate.class);
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //返回异常信息，需要返回的是
        try{
            logger.info("第四步：结果处理");

            delegateExecution.setVariable("结果处理","处理完成");
        }catch (Exception e){
            logger.info("第四步：结果处理");
            MailUtil.sendAndCc(ConfConstant.smtp, ConfConstant.from, ConfConstant.to, ConfConstant.copyto, "邮件警报",
                    "客户信息有误:" + e.getMessage() + "</br>"
                            + "实例ID：" + delegateExecution.getProcessInstanceId() + "</br>"
                            + "活动名称:" +delegateExecution.getCurrentActivityName(), ConfConstant.username, ConfConstant.password, ConfConstant.filename);
            throw new BizException(e);
        }
    }
}
