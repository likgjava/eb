package com.gpcsoft.epp.projreview.domain;

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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @author liuke
 * @since 0.5
 * @version: 0.6
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.projreview"
 * @gpcsoft.page domain="planform/projreview" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="评标"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_EVALBIDRECORD")
public class EvalBidRecord implements GpcBaseObject ,IPropertyCUserTime, IPropertyUUserTime
{
	@Id
	@Column(name = "EVAL_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	@Column(name = "PROJ_ID",length = 36)
	private String projId;//项目主键
	
	@Column(name = "PROJ_NAME",length = 500)
	private String projName;//项目名称

	@Column(name = "PROJ_CODE",length = 500)
	private String projCode;//项目编号

	@Column(name = "SUB_PROJ_ID",length = 36)
	private String subProjId;//包组ID
	
	@Column(name = "EVAL_OPINION",length = 500)
	private String evalOpinion;//评审意见

	@Column(name = "EVAL_LINKER",length = 50)
	private String evalLinker;//评审人
	
	@Column(name = "EVAL_LINKER_NAME",length = 50)
	private String evalLinkerName;//评审人姓名
	
	@Column(name = "USE_STATUS")
	private Character useStatus;//使用状态
	
	/** 创建人 */
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
	@JoinColumn(
	name="MODIFY_USER", updatable = false)//关联的外键	 
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
	
	
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}
   
	 /**
	 * @gpcsoft.property title="项目名称"
	 * @gpcsoft.validate class="required"
	 */
	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}
	 /**
	 * @gpcsoft.property title="项目编码"
	 * @gpcsoft.validate class="required"
	 */
	public String getProjCode() {
		return projCode;
	}

	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}
	 /**
	 * @gpcsoft.property title="包组ID"
	 * @gpcsoft.validate class="required"
	 */
	public String getSubProjId() {
		return subProjId;
	}

	public void setSubProjId(String subProjId) {
		this.subProjId = subProjId;
	}
	 /**
	 * @gpcsoft.property title="评审意见"
	 * @gpcsoft.validate class="required"
	 */
	public String getEvalOpinion() {
		return evalOpinion;
	}

	public void setEvalOpinion(String evalOpinion) {
		this.evalOpinion = evalOpinion;
	}
	 /**
	 * @gpcsoft.property title="评审人"
	 * @gpcsoft.validate class="required"
	 */
	public String getEvalLinker() {
		return evalLinker;
	}

	public void setEvalLinker(String evalLinker) {
		this.evalLinker = evalLinker;
	}
    
	 /**
	 * @gpcsoft.property title="评审人姓名"
	 */
	public String getEvalLinkerName() {
		return evalLinkerName;
	}

	public void setEvalLinkerName(String evalLinkerName) {
		this.evalLinkerName = evalLinkerName;
	}
	
	
    public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
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
