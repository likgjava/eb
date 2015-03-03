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
import com.gpcsoft.srplatform.auth.domain.User;

/**
 *  Comments: <strong>Consignprotocol</strong>成交供应商清单表<br/>           		
 *	<br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 权限平台     		
 *  <br/>Create Date：2010-4-14 上午02:59:07 by yemx    					                            
 *  <br/>Modified Date:  2010-4-14 上午02:59:07 by yemx                                 
 *  @gpcsoft.package packageDir="com.gpcsoft.epp.buyresult"
 *  @gpcsoft.page domain="planform/buyresult" project="es"
 *  @gpcsoft.domain
 *  @gpcsoft.title value="成交供应商清单"    
 *  @since 0.4
 *  @version: 0.5
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_BUY_WINNER")
@SuppressWarnings("serial")
public class BuyWinner implements GpcBaseObject ,IPropertyCUserTime, IPropertyUUserTime{

	@Id
	@Column(name = "BUY_W_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;  //成交供应商清单ID
	
	@Column(name = "SELLER_ID", length = 36)
	private String selllerId; //供应商ID
	
	@Column(name = "SELLER_NAME", length = 36)
	private String sellerName; //供应商名字

	@ManyToOne(fetch=FetchType.LAZY)
	@BatchSize(size = 20)
	@JoinColumn(name="BUYR_ID")
	private BuyResult buyResult ; //成交结果
	
	@Column(name = "USE_STATUS")
	private Character useStatus;//使用状态

	@Column(name = "RESULT_TYPE")
	private String resultType;//成交类型(01:未成交;00:成交)
	
	@Transient
	private String resultTypeCn;
	
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

    /********************************GET和SET方法**********************************************/     
    
    
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	/**
	 * @gpcsoft.property title="供应商ID"
	 * @gpcsoft.validate class="required"
	 */
	public String getSelllerId() {
		return selllerId;
	}

	public void setSelllerId(String selllerId) {
		this.selllerId = selllerId;
	}

	/**
	 * @gpcsoft.property title="供应商名字"
	 * @gpcsoft.validate class="required"
	 */
	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public BuyResult getBuyResult() {
		return buyResult;
	}

	public void setBuyResult(BuyResult buyResult) {
		this.buyResult = buyResult;
	}
	
	
	public Character getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Character useStatus) {
		this.useStatus = useStatus;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
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

	public String getResultTypeCn() {
		this.resultTypeCn = ResultTypeEnum.getResult(this.getResultType());
		return this.resultTypeCn;
	}

	public void setResultTypeCn(String resultTypeCn) {
		this.resultTypeCn = resultTypeCn;
	}
}
