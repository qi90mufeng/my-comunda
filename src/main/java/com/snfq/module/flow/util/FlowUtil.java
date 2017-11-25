package com.snfq.module.flow.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.snfq.common.constant.ErrorCode;
import com.snfq.common.exception.BizException;


@Service
@Configuration
public class FlowUtil {

	private static Logger logger = LoggerFactory.getLogger(FlowUtil.class);

	private String productUrl;
	
	//-----------------------------------------------------------------------
	public static enum WorkStatus{
		PENDING,COMPLETED
	}
    //-----------------------------------------------------------------------
	/**
	 * 根据条件查询符合条件的组
	 */
	public static String METHOD_050101 = "050101";
	
	public static String METHOD_050101_NAME = "查询符合条件的组";
	
	/**
	 * 批量添加组
	 */
	public static String METHOD_050102 = "050102";
	
	public static String METHOD_050102_NAME = "批量添加组";
	

	/**
	 * 删除组
	 */
	public static String METHOD_050103 = "050103";
	
	public static String METHOD_050103_NAME = "删除组";
	
	/**
	 * 修改组
	 */
	public static String METHOD_050104 = "050104";
	
	public static String METHOD_050104_NAME = "修改组";
	
	
	/**
	 * 根据条件查询符合条件的用户
	 */
	public static String METHOD_050105 = "050105";
	
	public static String METHOD_050105_NAME = "查询用户";
	
	/**
	 * 添加用户
	 */
	public static String METHOD_050106 = "050106";
	
	public static String METHOD_050106_NAME = "添加用户";
	
	
	/**
	 * 删除用户
	 */
	public static String METHOD_050107 = "050107";
	
	public static String METHOD_050107_NAME = "删除用户";

	/**
	 * 修改用户
	 */
	public static String METHOD_050108 = "050108";
	
	public static String METHOD_050108_NAME = "修改用户";
	
	/**
	 * 查询组和用户的关系
	 */
	public static String METHOD_050109 = "050109";
	
	public static String METHOD_050109_NAME = "查询组和用户的关系";
	/**
	 * 建立组和用户的关系
	 */
	public static String METHOD_050110 = "050110";
	
	public static String METHOD_050110_NAME = "建立组和用户的关系";
	
	/**
	 * 删除组和用户的关系
	 */
	public static String METHOD_050111 = "050111";
	
	public static String METHOD_050111_NAME = "删除组和用户的关系";

	/**
	 * 修改组和用户的关系
	 */
	public static String METHOD_050112 = "050112";
	
	public static String METHOD_050112_NAME = "修改组和用户的关系";
	
	//------------------------------------------------------------------------
	/**
	 * 根据条件查询流程定义
	 */
	public static String METHOD_050201 = "050201";
	
	public static String METHOD_050201_NAME = "根据条件查询流程定义";
	
	/**
	 * 上传流程文件并部署  
	 */
	public static String METHOD_050202 = "050202";
	
	public static String METHOD_050202_NAME = "部署流程";
	/**
	 * 启动流程
	 */
	public static String METHOD_050203 = "050203";
	
	public static String METHOD_050203_NAME = "启动流程";
	/**
	 * 删除流程  
	 */
	public static String METHOD_050204 = "050204";
	
	public static String METHOD_050204_NAME = "删除流程  ";

	/**
     * 查询流程定义详细 
     */
    public static String METHOD_050205 = "050205";
    
    public static String METHOD_050205_NAME = "查询流程定义详细";
    
    /**
     * 查看流程图
     */
    public static String METHOD_050206 = "050206";
    
    public static String METHOD_050206_NAME = "查看流程图";
    
    /**
     * 挂起/激活流程定义
     */
    public static String METHOD_050207 = "050207";
    
    public static String METHOD_050207_NAME = "挂起/激活流程定义 ";
    
    /**
     * 修改流程定义
     */
    public static String METHOD_050208 = "050208";
    
    public static String METHOD_050208_NAME = "修改流程定义 ";
    
	//------------------------------------------------------------------------
	/**
	 * 查询指定组未签收任务(分配给角色)
	 */
	public static final String METHOD_050300 = "050300";
	
	public static String METHOD_050300_NAME = "查询指定组未签收任务";
	/**
	 * 查询个人未签收的任务(分配给角色)
	 */
	public static String METHOD_050301 = "050301";
	
	public static String METHOD_050301_NAME = "查询个人未签收任务";
	
	/**
	 * 查询个人已签收任务
	 */
	public static String METHOD_050302 = "050302";
	
	public static String METHOD_050302_NAME = "查询个人已签收任务";
	
	/**
	 * 查询个人已处理任务
	 */
	public static String METHOD_050303 = "050303";
	
	public static String METHOD_050303_NAME = "查询个人已处理任务";
	
	/**
	 * 查询个人被委托任务
	 */
	public static String METHOD_050304 = "050304";
	
	public static String METHOD_050304_NAME = "查询个人被委托任务";
	
	/**
	 * 查询流程日志
	 */
	public static String METHOD_050305 = "050305";
	
	public static String METHOD_050305_NAME = "查询流程日志";
	
	
	/**
	 * 签收任务
	 */
	public static String METHOD_050306 = "050306";
	
	public static String METHOD_050306_NAME = "签收任务";
	
	/**
	 * 完成任务
	 */
	public static String METHOD_050307 = "050307";
	
	public static String METHOD_050307_NAME = "完成任务";
	
	/**
	 * 委托任务
	 */
	public static String METHOD_050308 = "050308";
	
	public static String METHOD_050308_NAME = "委托任务";
	
	/**
	 * 完成委托任务
	 */
	public static String METHOD_050309 = "050309";
	
	public static String METHOD_050309_NAME = "完成委托任务";
	
	/**
	 * 指派任务
	 */
	public static String METHOD_050310 = "050310";
	
	public static String METHOD_050310_NAME = "指派任务";
	
	/**
	 * 查询任务的订单变量
	 */
	public static String METHOD_050311 = "050311";
	
	public static String METHOD_050311_NAME = "查询任务的订单变量";
	
	
	/**
	 * 查询任务详情
	 */
	public static String METHOD_050312 = "050312";
	
	public static String METHOD_050312_NAME = "查询任务详情";
	
	//--------------------------------------------------------------------
	
	/**
	 * 个人待处理任务统计
	 */
	public static String METHOD_050401 = "050401";
	
	public static String METHOD_050401_NAME = "个人待处理任务统计";
	
	/**
	 * 个人已完成任务统计
	 */
	public static String METHOD_050402 = "050402";
	
	public static String METHOD_050402_NAME = "个人已完成任务统计";

	/**
     * 查询当前日期N天以内的已处理任务数
     */
    public static String METHOD_050403 = "050403";
    
    public static String METHOD_050403_NAME = "查询当前日期N天以内的已处理任务数";
    
    //------------------------------------------------------------------------
    /**
     * 事件触发
     */
    public static String METHOD_050501 = "050501";
    
    public static String METHOD_050501_NAME = "订单结束事件";
    
    //------------------------------------------------------------------------
    

}
