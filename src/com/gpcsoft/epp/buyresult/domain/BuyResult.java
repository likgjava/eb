package com.gpcsoft.epp.buyresult.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 *  Comments: <strong>Consignprotocol</strong>成交结果表<br/>           		
 *	<br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 权限平台     		
 *  <br/>Create Date：2010-4-14 上午02:59:07 by yemx    					                            
 *  <br/>Modified Date:  2010-4-14 上午02:59:07 by yemx                                 
 *  @gpcsoft.package packageDir="com.gpcsoft.epp.buyresult"
 *  @gpcsoft.page domain="planform/buyresult" project="es"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="成交结果"    
 *  @since 0.4
 *  @version: 0.5
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_BUYRESULT")
@SuppressWarnings("serial")
public class BuyResult extends WorkFlowModel implements  GpcBaseObject ,IPropertyCUserTime, IPropertyUUserTime{
	
	@Id
	@Column(name = "BUYR_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;  //成交结果ID
	
	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="PROJ_ID")
	private Project project; //所属项目
	
	@Column(name = "PROJ_CODE", length = 500)
	private String projCode; // 相关项目编号
	
	@Column(name = "PROJ_NAME", length = 500)
	private String projName; // 相关项目名称
	
	@Column(name="SUB_PROJ_ID",length=36)
	private String subProjId;//相关包组ID
	
	@Column(name="SUB_PROJ_CODE",length=500)
	private String subProjCode;//相关包组编号
	
	@Column(name="SUB_PROJ_NAME",length=500)
	private String subProjName;//相关包组名称
	
	@Column(name="EBUY_METHOD",length=50)
	private String ebuyMethod;//采购方式
	
	@Transient
	private String ebuyMethodCN;
	
	@Column(name="BUYR_RESULT",length=50)
	private String buyrResult;//招标结果（选定、放弃、无人参与、没有足够的参与者）
	
	@Transient
	private String buyrResultCN;
	
	@Column(name="BUYR_OPINION",length=500)
	private String buyrOpinion;//意见
	
	@Column(name = "USE_STATUS")
	private Character useStatus;//使用状态
	
	//创建人
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATE_USER", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User createUser;

    //创建时间
    @Column(name = "CREATE_TIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    //修改人
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="MODIFY_USER", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User updateUser;
    
    //修改时间
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    
    //modify by liuke
	/**当前有效id*/
	@Column(name = "CURRENT_VALID_ID")
	private String currentId;


/********************************GET和SET方法**********************************************/   
	public String getCurrentId() {
		return currentId;
	}

	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	} 
    
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getProjCode() {
		return projCode;
	}

	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getSubProjId() {
		return subProjId;
	}

	public void setSubProjId(String subProjId) {
		this.subProjId = subProjId;
	}

	/**
	 * @gpcsoft.property title="包组编号"
	 * @gpcsoft.validate class="required"
	 */
	public String getSubProjCode() {
		return subProjCode;
	}

	public void setSubProjCode(String subProjCode) {
		this.subProjCode = subProjCode;
	}
	/**
	 * @gpcsoft.property title="包组名称"
	 * @gpcsoft.validate class="required"
	 */
	public String getSubProjName() {
		return subProjName;
	}

	public void setSubProjName(String subProjName) {
		this.subProjName = subProjName;
	}

	/**
	 * @gpcsoft.property title="采购方式"
	 * 
	 */
	public String getEbuyMethod() {
		return ebuyMethod;
	}

	public void setEbuyMethod(String ebuyMethod) {
		this.ebuyMethod = ebuyMethod;
	}
	
	/**
	 * @gpcsoft.property title="采购方式"
	 */
	public String getEbuyMethodCN() {
		this.ebuyMethodCN = EbuyMethodEnum.getEBuyMethodCN(this.getEbuyMethod());
		return this.ebuyMethodCN;
	}

	public void setEbuyMethodCN(String ebuyMethodCN) {
		this.ebuyMethodCN = ebuyMethodCN;
	}

	/**
	 * @gpcsoft.property title="招标结果"
	 * @gpcsoft.validate class="required"
	 */
	public String getBuyrResult() {
		return buyrResult;
	}

	public void setBuyrResult(String buyrResult) {
		this.buyrResult = buyrResult;
	}
	
	/**
	 * @gpcsoft.property title="招标结果"
	 * @gpcsoft.validate class="required"
	 */
	public String getBuyrResultCN() {
		this.buyrResultCN = ConfirmResultEnum.getConfirmResultCN(this.getBuyrResult());
		return this.buyrResultCN;
	}

	public void setBuyrResultCN(String buyrResultCN) {
		this.buyrResultCN = buyrResultCN;
	}

	public String getBuyrOpinion() {
		return buyrOpinion;
	}

	public void setBuyrOpinion(String buyrOpinion) {
		this.buyrOpinion = buyrOpinion;
	}

	
	public Character getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Character useStatus) {
		this.useStatus = useStatus;
	}

	
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}


	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
