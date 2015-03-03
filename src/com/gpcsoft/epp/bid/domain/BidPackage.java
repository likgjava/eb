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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.model.IPropertyCUserTime;
import com.gpcsoft.core.model.IPropertyUUserTime;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 * @Description: 投标与包件中间表 
 * @version V1.0
 * @Create Date 2010-8-4 下午03:04:55 By wanghz   
 * @gpcsoft.package packageDir="com.gpcsoft.epp.bid"
 * @gpcsoft.page domain="bid" project="es/planform"
 * @gpcsoft.domain
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_BID_PACKAGE")
public class BidPackage implements GpcBaseObject, IPropertyCUserTime, IPropertyUUserTime{
	
	@Id
	@Column(name="BID_P_ID")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="BID_ID")
	private Bid bid;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="SUB_PROJ_ID")
	private Project pack;
	
	@Column(name="BID_P_QUOTESUM")
	private Float quotesum;
	
	@Column(name="BID_P_PROCESS_STATUS")     //投标状态
	private String processStatus;
	
	@Column(name="BID_FILE")     //投标文件Id(压缩后的投标文件)
	private String bidFile;

	/** 创建人 */
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
	@JoinColumn(name="MODIFY_USER")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
    private User updateUser;
    
    //修改时间
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
	
    @Column(name = "USE_STATUS", length = 2)
	private String useStatus;//使用状态
    
	@Transient
	private String isOpenBid;//包组是否开标
	
	@Transient
	private Integer bidFileTotalSize;//投标文件大小
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	public String getIsOpenBid() {
		return isOpenBid;
	}

	public void setIsOpenBid(String isOpenBid) {
		this.isOpenBid = isOpenBid;
	}
	

	/**
	 * @GPCSOFT.property title="投标"
	 */
	public Bid getBid() {
		return bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}

	/**
	 * @GPCSOFT.property title="所属包件"
	 */
	public Project getPack() {
		return pack;
	}

	public void setPack(Project pack) {
		this.pack = pack;
	}

	/**
	 * @GPCSOFT.property title="投标金额"
	 */
	public Float getQuotesum() {
		return quotesum;
	}

	public void setQuotesum(Float quotesum) {
		this.quotesum = quotesum;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
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

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	
	public String getBidFile() {
		return bidFile;
	}

	public void setBidFile(String bidFile) {
		this.bidFile = bidFile;
	}

	public Integer getBidFileTotalSize() {
		return bidFileTotalSize;
	}

	public void setBidFileTotalSize(Integer bidFileTotalSize) {
		this.bidFileTotalSize = bidFileTotalSize;
	}
}
