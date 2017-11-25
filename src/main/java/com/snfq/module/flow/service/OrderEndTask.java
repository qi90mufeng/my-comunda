package com.snfq.module.flow.service;


import com.snfq.common.util.CommonUtil;
import com.snfq.module.flow.util.FlowUtil;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Service("orderEndTask")
public class OrderEndTask implements Serializable, JavaDelegate {
	private static Logger logger = LoggerFactory.getLogger(OrderEndTask.class);

	private static final long serialVersionUID = 7930893119310542885L;

	
	@Transactional
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		logger.info("订单结束，触发事件：orderNo=" + execution.getVariable("orderNo"));
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("orderNo", CommonUtil.nullToStr(execution.getVariable("orderNo")));
			
		params.put("state", CommonUtil.nullToStr(execution.getVariable("result")).equals("1") ? "1" : "2");//1成功2失败

	 try{
	     //....
      } catch (Exception e) {
          logger.error(e.getMessage());
      }finally{

      }
		
	}

	
}
