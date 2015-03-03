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
import org.hibernate.annotations.GenericGenerator;
import com.gpcsoft.core.model.GpcBaseObject;



/**
 * @Description: 开标子密钥
 * @gpcsoft.package packageDir="com.gpcsoft.epp.bid"
 * @gpcsoft.page domain="planform/bid" project="es"
 * @gpcsoft.domain
 * @gpcsoft.title value="开标子密钥"
 * @version V1.0
 * @Create Date 2011-8-3 上午09:55:01 By caojz
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "ECP_OPEN_SUBKEY")
public class BidSubKey implements GpcBaseObject{

	@Id
	@Column(name = "SUBKEY_ID", nullable=false, length = 50)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;                                   //主键
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="BID_P_ID")
	private BidPackage bidPackage;                           //关联的投标记录
	
	@Column(name="TENDER_ID", length=50)
	private String packId;                                         //关联的项目(包组)
	
	public String getPackId() {
		return packId;
	}

	public void setPackId(String packId) {
		this.packId = packId;
	}

	@Column(name = "OPENER_ID", length = 50)
	private String opener;							         // 开标人编号
	
	@Column(name = "OPEN_SUBKEY", length = 2000)
	private String subKey;								     // 开标子密钥信息
	
	@Column(name = "DEC_OPEN_SUBKEY", length = 2000)
	private String decSubkey;								 //解密后子钥信息
	
	@Column(name = "OPENER_TYPE" ,length = 2)
	private String openerType;                               //开标人类型 00:机构[投标人] 01:个人[经办人、监标人等] 

	
    public void setObjId(String objId) {
		this.objId = objId;
	}
    
	public String getObjId() {

		return objId;
	}
	public BidPackage getBidPackage() {
		return bidPackage;
	}

	public void setBidPackage(BidPackage bidPackage) {
		this.bidPackage = bidPackage;
	}

	public String getOpener() {
		return opener;
	}

	public void setOpener(String opener) {
		this.opener = opener;
	}

	public String getSubKey() {
		return subKey;
	}

	public void setSubKey(String subKey) {
		this.subKey = subKey;
	}

	public String getDecSubkey() {
		return decSubkey;
	}

	public void setDecSubkey(String decSubkey) {
		this.decSubkey = decSubkey;
	}

	public String getOpenerType() {
		return openerType;
	}

	public void setOpenerType(String openerType) {
		this.openerType = openerType;
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date arg0) {
		
	}


}
