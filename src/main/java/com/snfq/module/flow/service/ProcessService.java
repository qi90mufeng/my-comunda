package com.snfq.module.flow.service;

import java.io.*;
import java.nio.charset.Charset;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snfq.common.util.Base64;


@Service
public class ProcessService {
	private static Logger logger = LoggerFactory.getLogger(ProcessService.class);

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;
    /**
     * zip方式部署流程.
     <pre>
      	会在三张表中产生数据：
   		act_ge_bytearray 产生两条数据
   		act_re_deployment 产生一条数据
		act_re_procdef 产生一条数据
     </pre>
     * @param deployName 部署名
     * @param zipFile base64字符串
     * @return 返回流程定义ID
     * @throws Exception 
     */
    public String deployementProcessDefinitionByzip(String deployName,String zipFile,String tenantId) {
        InputStream in = new ByteArrayInputStream(Base64.decode(zipFile));

        ZipInputStream zipInputStream = new ZipInputStream(in, Charset.forName("UTF-8"));
        Deployment deployment = repositoryService//获取流程定义和部署对象相关的Service
                .createDeployment() //创建部署对象
                .name(deployName) // 添加部署的名称
                .addZipInputStream(zipInputStream) //使用zip方式部署，将helloworld.bpmn和helloworld.png压缩成zip格式的文件
                .tenantId(tenantId)
                .deploy();

        return "";
    }

    public String startProcessDefinition(String processDefinitionId, Map<String, Object> param){
        logger.info("第一步：启动流程");
        //启动流程实例
//        Map<String, Object> param = new HashMap<>();
//        param.put("subprocess","Process_1");//注释之后：Unknown property used in expression: ${subprocess}. Cause: Cannot resolve identifier 'subprocess'
//        //param.put("tenantId","sina-test2");//org.camunda.bpm.engine.exception.NullValueException: no processes deployed with key 'Process_1' and tenant-id 'sina-test2': processDefinition is null
//        param.put("tenantId","sina-test");
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, param);
        return "";
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
