package com.gpcsoft.epp.worktask.domain;

/** 
 * @Description: 任务状态枚举类
 * @version V1.0
 * @Create Date 2010-8-23 上午11:45:52 By wanghz   
 */
public class WorktaskStatusEnum {
	public final static String IN_GEAR = "1";		// 正常
    public final static String PREPARATORY = "2"; 	// 预警
    public final static String PRESS = "3";			// 催办
    public final static String OVERDUE = "4";		// 过期
    public final static String SUSPEND = "5";		// 暂停
    public final static String FINISH = "6";		// 完成
    public final static String HASTEN = "7";		// 提示
    
    public final static String FLOW_COMMISSION_TASK = "00";// 工作流委托代办
    public final static String CODING_COMMISSION_TASK = "01";// 硬编码待办任务
    
}
