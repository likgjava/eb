package com.gpcsoft.epp.common.domain;

import java.io.Serializable;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;
/**
 * 
  *  Comments: <strong>此类从"优易平台与执行交易平台接口文档"中提取，用于各产品间数据交换。</strong>           		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   epp                    					          
  *  <br/>Module ID: 执行平台     		
  *  <br/>Create Date：2010-5-13 上午05:43:28 by zhouzhanghe    					                            
 */
@NodeMapping(tag = "dataItem")
public class DataItem implements Serializable{
	private String id;
	private String name;
	private String value;
	private String type;
	private String readOnly;
	private String remark;
	
	public DataItem(){
		this.id = "";
		this.name = "";
		this.value = "";
		this.type = "";
		this.readOnly = "";
		this.remark = "";
	}
	

	@NodeMapping(tag = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@NodeMapping(tag = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@NodeMapping(tag = "value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	@NodeMapping(tag = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@NodeMapping(tag = "readOnly")
	public String getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}
	@NodeMapping(tag = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
 