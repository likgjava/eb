package com.gpcsoft.agreement.bargin.project.domain;

import java.util.ArrayList;
import java.util.List;

import com.gpcsoft.epp.buyresult.domain.ConfirmResultEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;

public class ProjectQueryDto {
	
	/**虚拟主键（等于项目主键）*/
	private String objId;
	
	/**项目名称*/
	private String projName;
	
	/**项目编号*/
	private String projCode;
	
	/**项目采购方式*/
	private String ebuyMethod;
	
	/**项目采购方式*/
	private String ebuyMethodCN;
	
	/**项目采购方式*/
	private String budgetTotalMoney;

	/**项目创建时间*/
	private String createTime;

	/**是否中标（供应商用）*/
	private Boolean isDeal = false;
	
	/**是否中标（供应商用）*/
	@SuppressWarnings("unused")
	private String isDealCN = "未中标";
	
	/**中标金额*/
	private String dealTotal = "--";
	
	/**节省金额*/
	private String saveTotal = "--";
	
	/**报名供应商数*/
	private Integer supplierNumber = 0;
	
	/**需求的最低价*/
	private List<Object[]> requirementMinRec = null;
	
	/**报名集合*/
	private List<Object[]> signUprecordList = new ArrayList<Object[]>();
	
	/**竞价状态*/
	private String bargainStatus = "--";
	
	/**使用状态*/
	private String useStatus = "--";
	
	/**采购人名称*/
	private String buyersName = "--";
	
	/**项目结果（选定、放弃、无人参与、没有足够的参与者）*/
	private String buyrResult;
	
	private String buyrResultCN;
	
	
	//默认构造方法
	public ProjectQueryDto(){
	}
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getProjCode() {
		return projCode;
	}

	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}

	public String getEbuyMethod() {
		return ebuyMethod;
	}

	public void setEbuyMethod(String ebuyMethod) {
		this.ebuyMethod = ebuyMethod;
	}
	
    public String getEbuyMethodCN(){
        ebuyMethodCN = EbuyMethodEnum.getEBuyMethodCN(getEbuyMethod());
        return ebuyMethodCN;
    }

	public void setEbuyMethodCN(String ebuyMethodCN) {
		this.ebuyMethodCN = ebuyMethodCN;
	}

	public String getBudgetTotalMoney() {
		return budgetTotalMoney;
	}

	public void setBudgetTotalMoney(String budgetTotalMoney) {
		this.budgetTotalMoney = budgetTotalMoney;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsDeal() {
		return isDeal;
	}

	public void setIsDeal(Boolean isDeal) {
		this.isDeal = isDeal;
	}

	public String getDealTotal() {
		return dealTotal;
	}

	public void setDealTotal(String dealTotal) {
		this.dealTotal = dealTotal;
	}

	public String getSaveTotal() {
		return saveTotal;
	}

	public void setSaveTotal(String saveTotal) {
		this.saveTotal = saveTotal;
	}

	public Integer getSupplierNumber() {
		return supplierNumber;
	}
	
	public void setSupplierNumber(Integer supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public List<Object[]> getSignUprecordList() {
		return signUprecordList;
	}

	public void setSignUprecordList(List<Object[]> signUprecordList) {
		this.signUprecordList = signUprecordList;
	}

	public List<Object[]> getRequirementMinRec() {
		return requirementMinRec;
	}

	public void setRequirementMinRec(List<Object[]> requirementMinRec) {
		this.requirementMinRec = requirementMinRec;
	}

	public String getIsDealCN() {
		return isDeal?(this.isDealCN = "中标"):(this.isDealCN="未中标");
	}

	public void setIsDealCN(String isDealCN) {
		this.isDealCN = isDealCN;
	}

	public String getBargainStatus() {
		return "A".equals(bargainStatus)?"进行中":("C".equals(bargainStatus)?"已结束":"未开始" );
	}

	public void setBargainStatus(String bargainStatus) {
		this.bargainStatus = bargainStatus;
	}
	
	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getBuyersName() {
		return buyersName;
	}

	public void setBuyersName(String buyersName) {
		this.buyersName = buyersName;
	}

	/** buyrResult */
	public void setBuyrResult(String buyrResult) {
		this.buyrResult = buyrResult;
	}

	/**
	 * @gpcsoft.property title="buyrResult"
	 */
	public String getBuyrResult() {
		return buyrResult;
	}

	/** buyrResultCN */
	public void setBuyrResultCN(String buyrResultCN) {
		this.buyrResultCN = buyrResultCN;
	}

	/**
	 * @gpcsoft.property title="buyrResultCN"
	 */
	public String getBuyrResultCN() {
		this.buyrResultCN = ConfirmResultEnum.getConfirmResultCN(this.getBuyrResult());
		return this.buyrResultCN;
	}
}
