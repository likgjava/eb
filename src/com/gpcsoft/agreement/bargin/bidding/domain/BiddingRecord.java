package com.gpcsoft.agreement.bargin.bidding.domain;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.agreement.bargin.project.domain.BargainTurn;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>议价记录</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 协议供货--议价   		
  *  <br/>Create Date：2010-4-7 下午03:50:06 by yucy    					                            
  *  <br/>Modified Date:  2010-4-7 下午03:50:06 by yucy     
  *                                      
  *  @since 0.5
  *  @version: 0.5 
  *  
  *  @gpcsoft.package packageDir="com.gpcsoft.eps.agreement.ebargain"
  *  @gpcsoft.page domain="ebargain" project="agreement"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="议价记录"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREE_BARGAIN_RECORD")
public class BiddingRecord implements GpcBaseObject, IPropertyCUserTime{

	/** serialVersionUID */
	private static final long serialVersionUID = -266212275358807013L;

	/**记录号*/
    @Id
    @Column(name = "BARGAIN_RECORD_ID", length = 50)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;

    /**项目*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="PROJECT_ID") 
    @BatchSize(size = 15)
    private Project project;
	
    /**供应商*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="SUPLIER_ID") 
    @BatchSize(size = 15)
    private OrgInfo supplier;
    
	/**报价轮次*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="BARGAIN_TURN_ID") 
    @BatchSize(size = 15)
    private BargainTurn bargainTurn;
    
	/**议价时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BARGIN_TIME")
	private Date barginTime;
	
	/** 报价人 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CRE_USER_ID")
	private User createUser;
	
	/**总折扣率*/
	@Column(name = "TOTAL_DISCOUNT", precision = 5)
	private BigDecimal totalDiscount;
	
	/**金额*/
	@Column(name = "GOODS_TOTAL", precision = 16, scale = 6)
	private BigDecimal goodsTotal;
	
	/**报价状态*/
	@Column(name = "BARGIN_STATUS", length = 2)
	private String barginStatus;
	
	/**备注*/
	@Column(name = "MEMO", length = 3000)
	private String remark;
	
	/**服务内容*/
	@Column(name = "SERVICE_CONTENT", length = 3000)
	private String serviceContent;

	/**报价文件*/
	@Column(name = "BARGIN_FILE" ,length = 50)
	private String bargainFile;
	
	/**报价条目*/
    @LazyCollection(value=LazyCollectionOption.EXTRA)
    @JoinColumn(name = "BARGAIN_RECORD_ID") 
    @OneToMany
    @Cascade({CascadeType.ALL})
    @OrderBy("objId asc")
    private Set<BiddingRecordItem> biddingRecordItemSet = new HashSet<BiddingRecordItem>();
    
	public void setObjId(String objId){
		this.objId = objId;
	}
	
	public String getObjId() {
		return this.objId;
	}

	public Set<BiddingRecordItem> getBiddingRecordItemSet() {
		return biddingRecordItemSet;
	}

	public void setBiddingRecordItemSet(Set<BiddingRecordItem> biddingRecordItemSet) {
		this.biddingRecordItemSet = biddingRecordItemSet;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public OrgInfo getSupplier() {
		return supplier;
	}

	public void setSupplier(OrgInfo supplier) {
		this.supplier = supplier;
	}

	public BargainTurn getBargainTurn() {
		return bargainTurn;
	}

	public void setBargainTurn(BargainTurn bargainTurn) {
		this.bargainTurn = bargainTurn;
	}

	public Date getBarginTime() {
		return barginTime;
	}

	public void setBarginTime(Date barginTime) {
		this.barginTime = barginTime;
	}

	public BigDecimal getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(BigDecimal totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public BigDecimal getGoodsTotal() {
		return goodsTotal;
	}

	public void setGoodsTotal(BigDecimal goodsTotal) {
		this.goodsTotal = goodsTotal;
	}

	public String getBarginStatus() {
		return barginStatus;
	}

	public void setBarginStatus(String barginStatus) {
		this.barginStatus = barginStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getServiceContent() {
		return serviceContent;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}

	public String getBargainFile() {
		return bargainFile;
	}

	public void setBargainFile(String bargainFile) {
		this.bargainFile = bargainFile;
	}

	public Date getCreateTime() {
		return barginTime;
	}

	public void setCreateTime(Date barginTime) {
		this.barginTime = barginTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	
}