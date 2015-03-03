package com.gpcsoft.goods.goods.domain;
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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>可选配件</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-7-27 下午02:19:12 by yucy    					                            
  *  <br/>Modified Date:  2010-7-27 下午02:19:12 by yucy                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "GOODS_OPTIONAL_FITTING")
public class GoodsOptionalFitting implements GpcBaseObject ,IPropertyCUserTime {

    /** serialVersionUID */
	private static final long serialVersionUID = -5052466765478222750L;

	@Id
    @Column(name = "GOODS_OPTIONAL_FITTING_ID", length = 36)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**顺序号*/
    @Column(name = "SORT_NO", length = 9)
    private Integer sortNo;
    
    /**选配内容 例如1G内存换为2G内存*/
    @Column(name = "OPTION_CONTENT", length = 100)
    private String optionContent;
    
    /**选配规格型号描述*/
    @Column(name = "SPECIFICATION", length = 100)
    private String specification;

    /**是否可用[01:可用 02:不可用]*/
    @Column(name = "ISUSE", length = 100)
    private String isUse;
    
    @Transient
	private String isUseCN;
    
    /**创建人*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="CREATOR_ID")
    @BatchSize(size = 15)
    private User createUser;
    
    /**创建时间*/
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**修改人*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @Cascade({CascadeType.REFRESH})
    @JoinColumn(name="MODIFIER_ID")
    @BatchSize(size = 15)
    private User modifier;

    /**修改时间*/
    @Temporal(TemporalType.DATE)
    @Column(name = "MODIFY_TIME")
    private Date modifyTime;

    /**商品参数*/
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="GOODS_PARAM_ID")
    @BatchSize(size = 15)
    private GoodsParam goodsParam;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getOptionContent() {
		return optionContent;
	}

	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}

	public String getIsUseCN() {
		this.isUseCN = GoodsEnum.getUseStatusCN(this.isUse);
		return isUseCN;
	}

	public void setIsUseCN(String isUseCN) {
		this.isUseCN = isUseCN;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getModifier() {
		return modifier;
	}

	public void setModifier(User modifier) {
		this.modifier = modifier;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public GoodsParam getGoodsParam() {
		return goodsParam;
	}

	public void setGoodsParam(GoodsParam goodsParam) {
		this.goodsParam = goodsParam;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
    
    
}