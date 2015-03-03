/**
 * 
 */
package com.gpcsoft.epp.bid.domain;

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
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @author liuke
 * @since 0.5
 * @version: 0.6
 * @gpcsoft.package packageDir="com.gpcsoft.epp.bid"
 * @gpcsoft.page domain="bid" project="epp"
 * @gpcsoft.domain
 * @gpcsoft.title value="供应商投标"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPP_BID_RECEIPT")
public class BidReceipt implements GpcBaseObject, IPropertyCUserTime, IPropertyUUserTime{
	@Id
	@Column(name = "BID_RE_ID", nullable=false, length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; // 主键
	
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="BID_P_ID")
	private BidPackage bidPackage;   //投标包件ID
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="BID_ID")
	private Bid bid;   //投标包件ID
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="SUB_TENDER_ID")
	private Project pack;   //投标对应项目（包组）ID
	
	@Column(name="BID_RE_FILE")    
	private String bidReFileId; //回执文件[附件的关联ID]
	
	@Column(name="BID_RE_TYPE")    
	private String bidReType; //回执文件类型:00:投标回执 01:撤标回执
	
	@Column(name="BID_RE_PROCESS_STATUS")    
	private String bidReProcessStatus; //流程状态 00:生成 01:确认下载
	
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
	@JoinColumn(
	name="MODIFY_USER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
   	private User updateUser;
    
    //修改时间
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
     private Date updateTime;
	
  

	@Column(name = "USE_STATUS" ,length = 2)
	private String useStatus;//使用状态
	
    public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public BidPackage getBidPackage() {
		return bidPackage;
	}

	public void setBidPackage(BidPackage bidPackage) {
		this.bidPackage = bidPackage;
	}

	public Bid getBid() {
		return bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}

	public Project getPack() {
		return pack;
	}

	public void setPack(Project pack) {
		this.pack = pack;
	}

	public String getBidReFileId() {
		return bidReFileId;
	}

	public void setBidReFileId(String bidReFileId) {
		this.bidReFileId = bidReFileId;
	}

	public String getBidReType() {
		return bidReType;
	}

	public void setBidReType(String bidReType) {
		this.bidReType = bidReType;
	}

	public String getBidReProcessStatus() {
		return bidReProcessStatus;
	}

	public void setBidReProcessStatus(String bidReProcessStatus) {
		this.bidReProcessStatus = bidReProcessStatus;
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

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
