package com.gpcsoft.epp.worktask.domain;

/** 
 * @Description: 任务完成枚举类 
 * @version V1.0
 * @Create Date 2010-8-23 下午01:57:20 By wanghz   
 */
public class CompletedWorktaskEnum {

	public final static String SYSTEM_AUTO_COMPLETE = "1";// 系统自动完成
    public final static String MANUAL_COMPLETE = "0";// 手动完成
    
    // 任务完成 处理结果
	public final static String PROCESS_RESULT_A = "00";			// 通过
    public final static String PROCESS_RESULT_B = "01";			// 退回
	public final static String PROCESS_RESULT_C = "02";			// 完成
    
    
}
