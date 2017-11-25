package com.snfq.module.flow.controller;

import com.snfq.common.exception.BizException;
import com.snfq.common.json.JsonResult;
import com.snfq.common.util.CommonUtil;
import com.snfq.module.flow.service.ProcessService;
import com.snfq.module.flow.util.FlowUtil;
import com.snfq.common.constant.ErrorCode;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fujin
 * @version $Id: CustomerSaveDelegate.java, v 0.1 2017-10-11 15:19 Exp $$
 */
@RestController
@RequestMapping("/flow")
public class FlowController {

    private static final Logger logger = LoggerFactory.getLogger(FlowController.class);

    @Autowired
    private ProcessService processService;

    /**
     * 管理者操作流程.
     * 1、根据条件查询流程定义         050201
     * 2、上传流程并部署             050202
     * 3、 启动流程                        050203
     * 4、删除流程                         050204
     * 5、查看流程定义详情                     050205
     * 6、查看流程图                     050206
     * 7、挂起流程定义                 050207
     * 
     * @param userId 管理者ID
     * @param zipJson 流程zip压缩二进制流
     * @param processDefinitionId 流程定义ID
     * @param deploymentId 部署ID
     * @param assignUserId 被指派人ID
     * @param paramJson 启动流程变量   需要设置assignCompany的key
     * @param deployName 部署名称
     * @param processDefName 流程定义名称
     * @param suspendsionState 流程是否挂起  1:激活  0：挂起
     * @param methodCode 接口代码
     * @param targetPage 目标页，默认为1
     * @param pageSize 每页显示数量，默认为10
     * @param isNeedPage 是否开启分页
     * @return
     */
    @ApiOperation(value = "流程操作", notes = "销售排行报表")
    //@AccessLogAnnotation(reportType = DepartmentConstant.BOSS,reportName = "销售排行报表")
    @RequestMapping(value = "/operFlow", method = {RequestMethod.POST,RequestMethod.GET})
    public JsonResult operFlow(Long userId, String zipJson, String processDefinitionId, String deploymentId, 
                               Long assignUserId, String paramJson, String deployName, String processDefName, 
                               String suspendsionState, String methodCode,
                               Integer targetPage, Integer pageSize, Boolean isNeedPage) {
    	if (CommonUtil.isEmpty(methodCode)) {
			throw new BizException("methodCode 不能为空",ErrorCode.ERROR_PARAM_LOSE.getErrorCode());
		}
    	if (CommonUtil.isNull(isNeedPage)) {
            isNeedPage = true;
        }
    	if (FlowUtil.METHOD_050202.equals(methodCode)) {
			return new JsonResult(processService.deployementProcessDefinitionByzip(deployName, zipJson,""));
		}
    	throw new BizException(ErrorCode.ERROR_FUNCTION_NOTSUPPORT.getErrorName(),ErrorCode.ERROR_FUNCTION_NOTSUPPORT.getErrorCode());
    }
    

}
