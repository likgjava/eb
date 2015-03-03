package com.gpcsoft.goods.goodsclass.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.gpcsoft.core.tree.TreeProperty;

/** 
  *  Comments: <strong>商品分类参数(参数分类)</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2010-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-7-27 上午11:26:29 by yucy    					                            
  *  <br/>Modified Date:  2010-7-27 上午11:26:29 by yucy                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
@Entity
@DiscriminatorValue("02")
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@TreeProperty(topIcon="book_titel.gif", nodeIcon="tombs_mag.gif", title="分类参数", text="name", tip="orgnization",nodeIconExtend="icon")
public class GoodsClassParam extends GoodsClassParamType{
    
    /** serialVersionUID */
	private static final long serialVersionUID = -7650463421930356458L;
    
    /**是否可选配*/
    @Column(name = "CAN_SELECT", length = 5)
    private Boolean canSelect;
    
    /**是否必须填写*/
    @Column(name = "NEED_INPUT", length = 5)
    private Boolean needInput;
    
    /**参数值计量单位*/
    @Column(name = "PARAM_UNIT", length = 100)
    private String paramUnit;
    
    /**是否常用检索参数*/
    @Column(name = "USUALLY_SEARCH", length = 5)
    private Boolean usuallySearch;
    
    /**是否主要参数*/
    @Column(name = "MAIN_PARAM", length = 5)
    private Boolean mainParam;

	public Boolean getCanSelect() {
		return canSelect;
	}

	public void setCanSelect(Boolean canSelect) {
		this.canSelect = canSelect;
	}

	public Boolean getNeedInput() {
		return needInput;
	}

	public void setNeedInput(Boolean needInput) {
		this.needInput = needInput;
	}

	public String getParamUnit() {
		return paramUnit;
	}

	public void setParamUnit(String paramUnit) {
		this.paramUnit = paramUnit;
	}

	public Boolean getUsuallySearch() {
		return usuallySearch;
	}

	public void setUsuallySearch(Boolean usuallySearch) {
		this.usuallySearch = usuallySearch;
	}

	public Boolean getMainParam() {
		return mainParam;
	}

	public void setMainParam(Boolean mainParam) {
		this.mainParam = mainParam;
	}

}