package com.test;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.snfq.module.flow.service.FlowService;
import com.snfq.module.flow.util.ConfConstant;
import com.snfq.module.flow.util.MailUtil;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.snfq.module.Application;
import com.snfq.module.flow.service.ProcessService;
import com.snfq.common.util.Base64;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = Application.class)
public class TestFlow {
	
	@Autowired
	private ProcessService test;

	@Autowired
	private FlowService test1;
	@Autowired
	private CaseService caseService;
	@Autowired
	private DecisionService decisionService;

	/** 空部署*/
	@Test
	public void nullStartProject(){
		//
	}

	@Test
	public void sendMail(){
		MailUtil.sendAndCc(ConfConstant.smtp, ConfConstant.from, "fujin@sinafenqi.com", ConfConstant.copyto, "邮件警报",
				"客户信息有误", ConfConstant.username, ConfConstant.password, ConfConstant.filename);
	}
	//----------------------------------------------------------------流程----------------------------------------------------------------
	/** 部署流程 */
	@Test
	public void deployProcess(){
		/**
		 *
		 */
		//String pdfId = test.deployementProcessDefinitionByzip("INTERFACE-TEST", unzip("D:/sina/apps/example/boot-comunda/src/main/resources/interface-test.zip"),"sina-test");
		String pdfId = test.deployementProcessDefinitionByzip("SERVICE-TIMER-TEST", unzip("D:/sina/apps/example/boot-comunda/src/main/resources/service_timer.zip"),"sina-test");
		//String pdfId = test.deployementProcessDefinitionByzip("CALL-SUB-TEST", unzip("D:/sina/apps/example/boot-comunda/src/main/resources/sub-test.zip"),"sina-test");
		//String pdfId2 = test.deployementProcessDefinitionByzip("SERVICE-TEST", unzip("D:/sina/apps/example/boot-comunda/src/main/resources/service.zip"),"sina-test");
		//String pdfId3 = test.deployementProcessDefinitionByzip("TIMER-TEST", unzip("D:/sina/apps/example/boot-comunda/src/main/resources/timer-test.zip"),"sina-test");
		//String pdfId4 = test.deployementProcessDefinitionByzip("TIMER-TEST", unzip("D:/sina/apps/example/boot-comunda/src/main/resources/timer-test.zip"),"sina-test");
	}

	/** 启动服务 */
	@Test
	public void startProcess(){
		//for(int i = 0; i < 10000000;i++){
			Map<String, Object> param = new HashMap<>();
			//param.put("time",new Date());//注释之后：Unknown property used in expression: ${subprocess}. Cause: Cannot resolve identifier 'subprocess'
			//param.put("tenantId","sina-test2");//org.camunda.bpm.engine.exception.NullValueException: no processes deployed with key 'Process_1' and tenant-id 'sina-test2': processDefinition is null
			//param.put("tenantId","sina-test");
			//param.put("count",i);
			test.startProcessDefinition("xjd-test:1:875d4313-c859-11e7-86f4-1c1b0d4aab1f", param);
		//}

	}

	/**
	 * 完成任务
	 */
	 @Test
	 public void completeTask(){
	 	test1.complete("3f9f4332-ae2e-11e7-b388-1c1b0d4aab1f",null,null);
	 }


	 //----------------------------------------------------------------call Activity----------------------------------------------------------------
	 /** 部署外部活动 */
	@Test
	public void deployCallActivity(){
		//String pdfId = test.deployementProcessDefinitionByzip("CALL-SUB-TEST", unzip("D:/sina/apps/example/boot-comunda/src/main/resources/sub-test.zip"),"sina-test");
		String pdfId2 = test.deployementProcessDefinitionByzip("CALL-TEST", unzip("D:/sina/apps/example/boot-comunda/src/main/resources/call-activity-2.zip"),"sina-test");
	}

	@Test
	public void startCallActivity(){
		//Map<String, Object> param = new HashMap<>();
		//param.put("subprocess","Process_1");//注释之后：Unknown property used in expression: ${subprocess}. Cause: Cannot resolve identifier 'subprocess'
		//param.put("tenantId","sina-test2");//org.camunda.bpm.engine.exception.NullValueException: no processes deployed with key 'Process_1' and tenant-id 'sina-test2': processDefinition is null
		//param.put("tenantId","sina-test");

		test.startProcessDefinition("call-activity-test:4:af3bb4b3-ad94-11e7-a4ae-1c1b0d4aab1f",null);
	}

	@Test
	public void completeCallActivity(){
		//act_ru_job
		test1.complete("b3f55c3b-c85b-11e7-83c4-1c1b0d4aab1f",null,null);
	}

	//----------------------------------------------------------------决策----------------------------------------------------------------
	/** 部署决策  启动服务*/
	@Test
	public void deployDmnAndStart(){
		//获取本地文件转换成字符换  
		String pdfId = test.deployementProcessDefinitionByzip("微农贷", unzip("D:/sina/apps/example/dinner-dmn/src/main/resources/dinnerDecisions.zip"),"sina-cmmn");

	}
	/** 测试DMN*/
	@Test
	public void evluateDmn(){
		//DecisionService decisionService = processEngine.getDecisionService();
		VariableMap variables = Variables.createVariables()
				.putValue("season", "Spring")
				.putValue("guestCount", 10)
				.putValue("guestsWithChildren", false);

		DmnDecisionTableResult dishDecisionResult = decisionService.evaluateDecisionTableById("dish:2:224286e0-a40b-11e7-916f-1c1b0d4aab1f", variables);
		String desiredDish = dishDecisionResult.getSingleEntry();
//14340be7-a40b-11e7-916f-1c1b0d4aab1f  221c613c-a40b-11e7-916f-1c1b0d4aab1f
		System.out.println("Desired dish: " + desiredDish);

		DmnDecisionTableResult beveragesDecisionResult = decisionService.evaluateDecisionTableById("beverages:2:2241003f-a40b-11e7-916f-1c1b0d4aab1f", variables);
		List<Object> beverages = beveragesDecisionResult.collectEntries("beverages");

		System.out.println("Desired beverages: " + beverages);
	}
	//----------------------------------------------------------------里程碑---------------------------------------------------------------
	/** 部署里程碑  启动服务*/
	@Test
	public void deployCmmnAndStart(){
		//获取本地文件转换成字符换
		/**
		 * act_re_case_def
		 */
		String pdfId = test.deployementProcessDefinitionByzip("CMMN-TEST", unzip("D:/sina/apps/example/boot-comunda/src/main/resources/loan-approval.zip"),"sina-test-2");

	}

	@Test
	public void startCmmn(){
		/**
		 * act_ru_case_execution
		 * act_ru_case_sentry_part
		 * act_hi_caseactinst
		 * act_hi_caseinst
		 */
		caseService.createCaseInstanceById("Case_1:1:5b0b4188-acbd-11e7-a250-1c1b0d4aab1f");
	}

	@Test
	public void completeCmmn(){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("task1", true);
		variables.put("task2", true);

		caseService.completeCaseExecution("a05e7299-acbd-11e7-8ed9-1c1b0d4aab1f",variables);
	}



	public static String unzip(String zipFile) {
		ByteArrayOutputStream out = null;
		FileInputStream in = null;
		String decompressed = null;
		try {
			out = new ByteArrayOutputStream();
			in = new FileInputStream(zipFile);

			byte[] buffer = new byte[1024];
			int n; 
			//每次从fis读1024个长度到buffer中，fis中读完就会返回-1  
			while ((n = in.read(buffer)) != -1){  
				out.write(buffer, 0, n);  
	        }  
				
			decompressed = Base64.encodeBytes(out.toByteArray());
		} catch (IOException e) {
			decompressed = null;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return decompressed;
	}
	
}
