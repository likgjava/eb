package com.gpcsoft.pubservice.application.favorites.domain;

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
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>收藏</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   pubservice                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-8-9 下午01:55:58 by yucy   					                            
  *  <br/>Modified Date:  2010-8-9 下午01:55:58 by yucy   
  *  @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.favorites.domain.Favorites"
  *  @gpcsoft.page domain="Favorites" project="pubservice/application"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="收藏"                              
  *  @since 0.5
  *  @version: 0.5 
  */ 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PUB_FAVORITES")
public class Favorites implements GpcBaseObject ,IPropertyCUserTime{
    
	/** serialVersionUID */
	private static final long serialVersionUID = 2711365021142479938L;

	/**记录号*/
    @Id
    @Column(name = "FAVORITES_ID")
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
    private String objId;
    
    /**收藏对象Id */
    @Column(name = "FAVORITES_OBJECT_ID")
    private String favoriteId;
    
    /**收藏对象类型01:商品 02:供应商 03:代理机构 04:采购人 07:项目采购公告*/
    @Column(name = "FAVORITES_OBJECT_TYPE")
    private String favoriteType;
    
    /**收藏对象名称 */
    @Column(name = "FAVORITES_OBJECT_NAME")
    private String favoriteName;
    
    /**收藏对象关键字*/
    @Column(name = "FAVORITES_OBJECT_KEY")
    private String favoriteKey;

    /** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATOR_ID") 
    @BatchSize(size = 15)
    private User createUser;
    
    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(String favoriteId) {
		this.favoriteId = favoriteId;
	}

	public String getFavoriteType() {
		return favoriteType;
	}

	public void setFavoriteType(String favoriteType) {
		this.favoriteType = favoriteType;
	}

	public String getFavoriteKey() {
		return favoriteKey;
	}

	public void setFavoriteKey(String favoriteKey) {
		this.favoriteKey = favoriteKey;
	}

	public String getFavoriteName() {
		return favoriteName;
	}

	public void setFavoriteName(String favoriteName) {
		this.favoriteName = favoriteName;
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
	
}