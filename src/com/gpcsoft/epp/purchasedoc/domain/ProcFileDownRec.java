package com.gpcsoft.epp.purchasedoc.domain;
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

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.srplatform.auth.domain.User;
/**
 * @author liuke
 * @since 0.5
 * @version: 0.6
 * 
 * @gpcsoft.package packageDir="com.gpcsoft.epp.purchasedoc"
 * @gpcsoft.page domain="planform/purchasedoc" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="文件下载记录"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_Tender_ProcFile_DownRec")
public class ProcFileDownRec implements GpcBaseObject{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DownRec_ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId; //主键
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(
	name="DownOrg_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private OrgInfo downOrg;//下载单位
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(
	name="DownUser_ID")//关联的外键	 
	@BatchSize(size = 15)//批量抓取
	private User downUser;//下载人
	
	@Column(name = "DownReason", length = 50)
	private String downReason;//下载用途
	
	@Column(name = "DownDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date downDate;//下载时间
	
	@Column(name = "FILEID", length = 2)
	private String fileId;//关联文件ID
	
	@Column(name = "DownStatus", length = 2)
	private String downStatus;//下载状态 00：成功；01：失败
	
	@Column(name="DOWN_IP",length=20)
	private String downIP;//下载IP地址				
	
	public OrgInfo getDownOrg() {
		return downOrg;
	}

	public void setDownOrg(OrgInfo downOrg) {
		this.downOrg = downOrg;
	}

	public User getDownUser() {
		return downUser;
	}

	public void setDownUser(User downUser) {
		this.downUser = downUser;
	}

	public String getDownReason() {
		return downReason;
	}

	public void setDownReason(String downReason) {
		this.downReason = downReason;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Date getDownDate() {
		return downDate;
	}

	public void setDownDate(Date downDate) {
		this.downDate = downDate;
	}

	public String getDownIP() {
		return downIP;
	}

	public void setDownIP(String downIP) {
		this.downIP = downIP;
	}

	public String getDownStatus() {
		return downStatus;
	}

	public void setDownStatus(String downStatus) {
		this.downStatus = downStatus;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Date getCreateTime() {
		return null;
	}

	public String getObjId() {
		return this.objId;
	}

	public void setCreateTime(Date arg0) {
	}
}