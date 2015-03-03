package com.gpcsoft.epp.worktask.domain;

/**
 * @Description:展现待办任务MODEL 
 * @version V1.0
 * @Create Date 2010-8-27 上午10:14:55 By wanghz
 */
public class WaitprocWorkTaskModel {

	private String worktaskName;		// 任务名称
	
	private String worktaskType;		// 任务类别
	
	private Long count;					// 记录条数

	public String getWorktaskName() {
		return worktaskName;
	}

	public void setWorktaskName(String worktaskName) {
		this.worktaskName = worktaskName;
	}

	public String getWorktaskType() {
		return worktaskType;
	}

	public void setWorktaskType(String worktaskType) {
		this.worktaskType = worktaskType;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}
