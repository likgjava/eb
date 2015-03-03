package com.gpcsoft.epp.resproject.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "EPM_01_ZJLY_CATEGORY")
public class MoneySourceCategory extends WorkFlowModel implements GpcBaseObject{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", length = 36)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid.hex")
	private String objId;                 //主键                        
	
	@Column(name="TYPE",length=2)
	private String type;                  //类型:00:中央投资 01 非中央资
	
	@Column(name="ZJLY_NAME",length=100)
	private String moneySourceName;      //资金来源名称       
	
	@Column(name="ISLEAF",length=2)
	private String isLeaf;               //是否 是叶子节点 0:false 1:true
	
	@Column(name="SORT",length=2)
	private String sort;                 //序号
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="Parent_ID", updatable = false)	 
	@BatchSize(size = 15)
	private MoneySourceCategory parent;  //父ID
	
	@LazyCollection(value=LazyCollectionOption.EXTRA)
    @OneToMany
    @BatchSize(size=10)
    @JoinColumn(name = "Parent_ID") 
    @Cascade({CascadeType.ALL})
    private Set<MoneySourceCategory> subCategorys;
	
	@Column(name="USE_STATUS",length=2)
	private String useStatus;             //状态： 0:无效,1:有效	状态：
	
	@Column(name="DESCRIPTION",length=100)
	private String description;           //备注
	
	

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getMoneySourceName() {
		return moneySourceName;
	}

	public void setMoneySourceName(String moneySourceName) {
		this.moneySourceName = moneySourceName;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public MoneySourceCategory getParent() {
		return parent;
	}

	public void setParent(MoneySourceCategory parent) {
		this.parent = parent;
	}

	public Set<MoneySourceCategory> getSubCategorys() {
		return subCategorys;
	}

	public void setSubCategorys(Set<MoneySourceCategory> subCategorys) {
		this.subCategorys = subCategorys;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
