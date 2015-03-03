
package com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;

/**
 * @author shenjz
 *
 */
@NodeMapping(tag="ecpCongruousFactor")
public class CongruousFactorDTO{
	private String confactId;
	private String tenderId;
	private String factorName;
	private String factorCode;
	private String auditType;
	private String auditMethod;
	private String score;
	private String scoreMax;
	private String scoreMin;
	private String showNo;
	private String memo;
	private String isLeaf;
	private String parentId;
	
	@NodeMapping(tag="confactId")
	public String getConfactId() {
		return confactId;
	}
	public void setConfactId(String confactId) {
		this.confactId = confactId;
	}
	@NodeMapping(tag="tenderId")
	public String getTenderId() {
		return tenderId;
	}
	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}
	@NodeMapping(tag="factorName")
	public String getFactorName() {
		return factorName;
	}
	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}
	@NodeMapping(tag="factorCode")
	public String getFactorCode() {
		return factorCode;
	}
	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}
	@NodeMapping(tag="auditType")
	public String getAuditType() {
		return auditType;
	}
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	@NodeMapping(tag="auditMethod")
	public String getAuditMethod() {
		return auditMethod;
	}
	public void setAuditMethod(String auditMethod) {
		this.auditMethod = auditMethod;
	}
	@NodeMapping(tag="score")
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	@NodeMapping(tag="scoreMax")
	public String getScoreMax() {
		return scoreMax;
	}
	public void setScoreMax(String scoreMax) {
		this.scoreMax = scoreMax;
	}
	@NodeMapping(tag="scoreMin")
	public String getScoreMin() {
		return scoreMin;
	}
	public void setScoreMin(String scoreMin) {
		this.scoreMin = scoreMin;
	}
	@NodeMapping(tag="showNo")
	public String getShowNo() {
		return showNo;
	}
	public void setShowNo(String showNo) {
		this.showNo = showNo;
	}
	@NodeMapping(tag="memo")
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@NodeMapping(tag="isLeaf")
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	@NodeMapping(tag="parentId")
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
