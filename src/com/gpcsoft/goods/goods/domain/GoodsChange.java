package com.gpcsoft.goods.goods.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.core.model.VerifyObject;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * 商品变更信息
 * @gpcsoft.package packageDir="com.gpcsoft.goods.goods"
 * @gpcsoft.page domain="goods"
 * @hibernate.class table="GOODS_CHANGE"
 * @author Administrator
 * @version 1.0
 * @created 08-三月-2010 10:41:18
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "GOODS_CHANGE")
@OrderProperty(property="createTime", flag="false")
public class GoodsChange implements GpcBaseObject,IPropertyCUserTime,VerifyObject {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "GOODS_CHANGE_ID",length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	/** 商品 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="GOODS_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private Goods goods;
	
	/**
	 * 变更类型
	 */
	@Column(name = "MODIFY_TYPE", length = 100)
	private String modifyType;
	
	/**
	 * 旧值
	 */
	@Column(name = "OLD_VALUE", length = 100)
	private String oldValue;
	
	@Transient
	private String oldValueId;
	
	@Transient
	private String oldValueName;
	
	/**
	 * 新值
	 */
	@Column(name = "NEW_VALUE", length = 100)
	private String newValue;
	
	@Transient
	private String newValueId;
	
	@Transient
	private String newValueName;
	
	
	/** 创建人 */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CREATOR")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private User createUser;
	
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", length = 7)
	private Date createTime;
	
	/** 审核状态[00:草稿;01:待审;02:通过,03:不通过] */
	@Column(name = "AUDIT_STATUS", length = 2)
	private String auditStatus;
	
	@Transient
	private String auditStatusCN;
	
	 /**审核人*/
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="VERIFIER_ID") 
	@BatchSize(size = 15)
	private User verifyUser;
	
    /**审核时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VERIFY_TIME")
    private Date verifyTime;
    
    /**意见*/
    @Column(name = "OPINION")
    private String opinion;

    public String getNewValueName() {
		if(this.newValue != null){
			String temp = this.newValue.replace("##||##", "&");
			String args[] = temp.split("&");
			if(args != null && args.length > 1){
				this.newValueName = args[1]==null?"":args[1];
			}else{
				this.newValueName = "";
			}
		}
		return newValueName;
	}
	public String getNewValueId() {
		if(this.newValue != null){
			String temp = this.newValue.replace("##||##", "&");
			String args[] = temp.split("&");
			if(args != null && args.length > 1){
				this.newValueId = args[0]==null?"":args[0];
			}else{
				this.newValueId = "";
			}
		}
		return newValueId;
	}
	public String getOldValueName() {
		if(this.oldValue != null){
			String temp = this.oldValue.replace("##||##", "&");
			String args[] = temp.split("&");
			if(args != null && args.length > 1){
				this.oldValueName = args[1]==null?"":args[1];
			}else{
				this.oldValueName = "";
			}
		}
		return oldValueName;
	}
	public String getOldValueId() {
		if(this.oldValue != null){
			String temp = this.oldValue.replace("##||##", "&");
			String args[] = temp.split("&");
			if(args != null && args.length > 1){
				this.oldValueId = args[0]==null?"":args[0];
			}else{
				this.oldValueId = "";
			}
		}
		return oldValueId;
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
	
	public User getVerifyUser() {
		return verifyUser;
	}
	public void setVerifyUser(User verifyUser) {
		this.verifyUser = verifyUser;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}
	
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public String getModifyType() {
		return modifyType;
	}
	public void setModifyType(String modifyType) {
		this.modifyType = modifyType;
	}
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditStatusCN() {
		this.auditStatusCN = OrganizationEnum.getAuditStatusCN(this.auditStatus);
		return auditStatusCN;
	}
	public void setAuditStatusCN(String auditStatusCN) {
		this.auditStatusCN = auditStatusCN;
	}
}