package com.gpcsoft.pubservice.application.security.domain;

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
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.baseData.domain.Dictionary;

/** 
  *  Comments: <strong>UserSecurity</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   pubservice                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-9-1 下午04:24:53 by zhangyd    					                            
  *  <br/>Modified Date:  2010-9-1 下午04:24:53 by zhangyd    
  *  @gpcsoft.package packageDir="com.gpcsoft.pubservice.application.security"
  *  @gpcsoft.page domain="security" project="pubservice/application"
  *  @gpcsoft.domain
  *  @gpcsoft.title value="密保安全"                               
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_PUB_USER_SECURITY")
public class UserSecurity implements GpcBaseObject,IPropertyCUserTime{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "US_ID", unique = true, nullable = false, length = 50)
	private String objId;
	
	/** 用户 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="U_ID") 
    @BatchSize(size = 15)
    private User user;
	
    /** 密保问题(取自数据字典) */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="DIC_ID") 
    @BatchSize(size = 15)
    private Dictionary question;
    
    /** 密保问题答案 */
    @Column(name="ANSWER")
    private String answer;
    
	/** 创建人 */
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="CREATE_USER") 
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

	/**
	 * @gpcsoft.property title="用户名"
	 */
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * @gpcsoft.property title="问题"
	 */
	public Dictionary getQuestion() {
		return question;
	}

	public void setQuestion(Dictionary question) {
		this.question = question;
	}

	/**
	 * @gpcsoft.property title="答案"
	 */
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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
