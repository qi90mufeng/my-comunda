package com.snfq.module.flow.bean;

import java.io.Serializable;

public class TaskForm implements Serializable{

    /**  */
    private static final long serialVersionUID = 2358673621794908345L;
    
    //用户ID(任务执行者)
    private Long userId;
    //角色ID
    private String groupId;
    //角色ID列表（逗号分隔）
    private String groupIds;
    //任务ID
    private String taskId;
    //流程实例ID
    private String processInstanceId;
    //审批批注
    private String comment; 
    //流程变量
    private String paramJson;
    //订单号
    private String orderNo;
    //会员手机号
    private String memberPhone; 
    //会员姓名
    private String memberName; 
    //>=任务开始时间
    private String startTime; 
    //<=任务结束时间
    private String endTime; 
    //==任务结束时间
    private String completeTime;
    //时间点
    private String calDate; 
    //N天以内
    private String days;
//    //接口代码
//    private String methodCode; 
//    //目标页，默认为1
//    private Integer targetPage; 
//    //每页显示数量，默认为10
//    private Integer pageSize;
//    //是否开启分页
//    private Boolean isNeedPage;
    
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getGroupId() {
        return groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getGroupIds() {
        return groupIds;
    }
    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getProcessInstanceId() {
        return processInstanceId;
    }
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getParamJson() {
        return paramJson;
    }
    public void setParamJson(String paramJson) {
        this.paramJson = paramJson;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getMemberPhone() {
        return memberPhone;
    }
    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getCompleteTime() {
        return completeTime;
    }
    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }
    public String getCalDate() {
        return calDate;
    }
    public void setCalDate(String calDate) {
        this.calDate = calDate;
    }
    public String getDays() {
        return days;
    }
    public void setDays(String days) {
        this.days = days;
    }
//    public String getMethodCode() {
//        return methodCode;
//    }
//    public void setMethodCode(String methodCode) {
//        this.methodCode = methodCode;
//    }
//    public Integer getTargetPage() {
//        return targetPage;
//    }
//    public void setTargetPage(Integer targetPage) {
//        this.targetPage = targetPage;
//    }
//    public Integer getPageSize() {
//        return pageSize;
//    }
//    public void setPageSize(Integer pageSize) {
//        this.pageSize = pageSize;
//    }
//    public Boolean getIsNeedPage() {
//        return isNeedPage;
//    }
//    public void setIsNeedPage(Boolean isNeedPage) {
//        this.isNeedPage = isNeedPage;
//    }
    
}
