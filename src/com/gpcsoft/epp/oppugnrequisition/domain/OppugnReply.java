package com.gpcsoft.epp.oppugnrequisition.domain;

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
import com.gpcsoft.core.model.OrderProperty;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 *  Comments: <strong>Consignprotocol</strong>质疑答复表<br/>            		
 *	<br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 权限平台     		
 *  <br/>Create Date：2010-4-14 上午02:59:07 by yemx    					                            
 *  <br/>Modified Date:  2010-4-14 上午02:59:07 by yemx                                 
 *  @gpcsoft.package packageDir="com.gpcsoft.epp.oppugnrequisition"
 *  @gpcsoft.page domain="oppugnrequisition" project="es/planform"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="质疑答复"    
 *  @since 0.4
 *  @version: 0.5
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_OPPUGNREPLY")
@SuppressWarnings("serial")
@OrderProperty(property="createTime", flag="true")
public class OppugnReply implements GpcBaseObject,IPropertyCUserTime, IPropertyUUserTime {

	@Id
	@Column(name = "OPPU_R_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;  //主键
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="OPPU_ID", updatable = false)//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private OppugnRequisition oppugnRequisition; //质疑申请
	
	@Column(name = "OPPU_R_REPLYCONTENT", length = 500)
	private String oppurReplyContent; // 答复内容
	
	@Column(name="ATTACH_RELA_ID",length=36)
	private String attachRelaId; //关联附件ID
	
	@Column(name = "OPPU_R_REPLYTYPE", length = 50)
	private String oppurReplyType; // 答复类型（注：原始答复、追加答复）
	
	@Transient
	private String oppurReplyTypeCN;

	@Column(name="OPPU_R_REMARK",length=500)
	private String oppurRemark; //备注

	@Column(name="USE_STATUS",length=2)
	private String useStatus; //使用状态
	
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

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/**
	 * @gpcsoft.property title="质疑申请"
	 */
	public OppugnRequisition getOppugnRequisition() {
		return oppugnRequisition;
	}

	public void setOppugnRequisition(OppugnRequisition oppugnRequisition) {
		this.oppugnRequisition = oppugnRequisition;
	}

	/**
	 * @gpcsoft.property title="答复内容"
	 */
	public String getOppurReplyContent() {
		return oppurReplyContent;
	}

	public void setOppurReplyContent(String oppurReplyContent) {
		this.oppurReplyContent = oppurReplyContent;
	}

	/**
	 * @gpcsoft.property title="关联附件"
	 */
	public String getAttachRelaId() {
		return attachRelaId;
	}

	public void setAttachRelaId(String attachRelaId) {
		this.attachRelaId = attachRelaId;
	}

	/**
	 * @gpcsoft.property title="答复类型"
	 */
	public String getOppurReplyType() {
		return oppurReplyType;
	}

	public void setOppurReplyType(String oppurReplyType) {
		this.oppurReplyType = oppurReplyType;
	}

	/**
	 * @gpcsoft.property title="备注"
	 */
	public String getOppurRemark() {
		return oppurRemark;
	}

	public void setOppurRemark(String oppurRemark) {
		this.oppurRemark = oppurRemark;
	}

	/**
	 * @gpcsoft.property title="使用状态"
	 */
	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	/**
	 * @gpcsoft.property title="创建人"
	 */
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	/**
	 * @gpcsoft.property title="创建时间"
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @gpcsoft.property title="修改人"
	 */
	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * @gpcsoft.property title="修改时间"
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	//答复类型
	public String getOppurReplyTypeCN() {
		oppurReplyTypeCN = OppugnTargetTypeEnum.getReplyCN(this.getOppurReplyType());
		return oppurReplyTypeCN;
	}
	public void setOppurReplyTypeCN(String oppurReplyTypeCN) {
		this.oppurReplyTypeCN = oppurReplyTypeCN;
	}
}
