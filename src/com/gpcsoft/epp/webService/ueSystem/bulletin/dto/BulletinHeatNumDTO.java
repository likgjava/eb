package com.gpcsoft.epp.webService.ueSystem.bulletin.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/**
 * 公告热度信息
 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
 * @since 2011-3-14 15:08
 */
@NodeMapping(tag="bulletin")
public class BulletinHeatNumDTO{

	private String heatNum;//公告收藏热度
	
	private String viewNum;//浏览热度
	
	private String applyNum;//报名热度

	@NodeMapping(tag="heatNum")
	public String getHeatNum() {
		return heatNum;
	}

	public void setHeatNum(String heatNum) {
		this.heatNum = heatNum;
	}

	@NodeMapping(tag="viewNum")
	public String getViewNum() {
		return viewNum;
	}

	public void setViewNum(String viewNum) {
		this.viewNum = viewNum;
	}

	@NodeMapping(tag="applyNum")
	public String getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(String applyNum) {
		this.applyNum = applyNum;
	}
}
