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

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author fujin
 * @version $Id: ServiceDelegate.java, v 0.1 2017-10-11 10:11 Exp $$
 */
@Service("serviceDelegate")
public class ServiceDelegate implements JavaDelegate{

    private static Logger logger = LoggerFactory.getLogger(ServiceDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try{
            logger.info("服务调用");
            Map<String, Object> param = delegateExecution.getVariables();

//            if (param.containsKey("service_name")){
//                Class clazz = Class.forName(String.valueOf(param.get("service_name")));
//
//                try{
//                    Method method = clazz.getMethod(String.valueOf(param.get("method_name")));
//                    // method.invoke(clazz.newInstance());
//                }catch(NoSuchMethodException e){
//
//                }
//            }

            System.out.println(param);
        }catch (Exception e){
            MailUtil.sendAndCc(ConfConstant.smtp, ConfConstant.from, ConfConstant.to, ConfConstant.copyto, "邮件警报",
                    "客户信息有误:" + e.getMessage() + "</br>"
                            + "实例ID：" + delegateExecution.getProcessInstanceId() + "</br>"
                            + "活动名称:" +delegateExecution.getCurrentActivityName(), ConfConstant.username, ConfConstant.password, ConfConstant.filename);
            throw new BizException(e);
        }
    }
}
