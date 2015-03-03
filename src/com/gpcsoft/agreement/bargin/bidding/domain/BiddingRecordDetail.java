package com.gpcsoft.agreement.bargin.bidding.domain;
import java.math.BigDecimal;
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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.agreement.bargin.require.domain.RequireItem;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.goods.goods.domain.Goods;
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
  *  @gpcsoft.title value="报价记录"
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPS_AGREE_RECORD_DETAIL")
public class BiddingRecordDetail implements GpcBaseObject{

	/** serialVersionUID */
	private static final long serialVersionUID = -665827740982801732L;

	/**记录号*/
    @Id
    @Column(name = "RECORD_DETAIL_ID", length = 50)
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
    
	/**议价时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BARGIN_TIME")
	private Date barginTime;
	
	/** 报价人 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CRE_USER_ID")
	private User createUser;
	
	/**金额*/
	@Column(name = "GOODS_TOTAL", precision = 16, scale = 6)
	private BigDecimal goodsTotal;
	
	/** 单价 */
	@Column(name = "GOODS_PRICE", precision = 16, scale = 6)
	private BigDecimal goodsPrice;
	
	/**备注*/
	@Column(name = "MEMO", length = 3000)
	private String remark;
	
	/**服务内容*/
	@Column(name = "SERVICE_CONTENT", length = 2000)
	private String serviceContent;

	/**报价文件*/
	@Column(name = "BARGIN_FILE" ,length = 50)
	private String bargainFile;
	
    /**商品*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="GOODS_ID") 
    @BatchSize(size = 15)
    private Goods goods;
    
	/**需求条目*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="REQUIRE_DTL_ID") 
    @BatchSize(size = 15)
    private RequireItem requireItem;
    
	/**排序*/
	@Column(name = "SORT")
	private Integer sort;
	
	/**是否成交*/
	@Column(name = "is_deal")
	private Boolean isDeal;
	
	public void setObjId(String objId){
		this.objId = objId;
	}
	
	public String getObjId() {
		return this.objId;
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

	public Date getBarginTime() {
		return barginTime;
	}

	public void setBarginTime(Date barginTime) {
		this.barginTime = barginTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public BigDecimal getGoodsTotal() {
		return goodsTotal;
	}

	public void setGoodsTotal(BigDecimal goodsTotal) {
		this.goodsTotal = goodsTotal;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
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

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public RequireItem getRequireItem() {
		return requireItem;
	}

	public void setRequireItem(RequireItem requireItem) {
		this.requireItem = requireItem;
	}

	public Date getCreateTime() {
		return barginTime;
	}

	public void setCreateTime(Date barginTime) {
		this.barginTime = barginTime;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getIsDeal() {
		return isDeal;
	}

	public void setIsDeal(Boolean isDeal) {
		this.isDeal = isDeal;
	}
}