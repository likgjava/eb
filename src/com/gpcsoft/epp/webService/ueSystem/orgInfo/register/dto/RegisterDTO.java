/**
 * 
 */
package com.gpcsoft.epp.webService.ueSystem.orgInfo.register.dto;

import com.gpcsoft.epp.webService.common.dto.annotation.NodeMapping;



/**
 * @author liuke
 *
 */
@NodeMapping(tag="body")
public class RegisterDTO {
			
		private String registerCode;  //营业执照注册号
		private String taxCode;	      //国税登记注册号
		private String rentCode;      //地税登记证号码
		private String registerScale; //工商注册经营范围
		private String registerDate;  //工商注册日期
		private String openDate;       //开业日期
		private String registerAuthority;       //工商注册发证机关
		
		@NodeMapping(tag="registerCode")
		public String getRegisterCode() {
			return registerCode;
		}
		public void setRegisterCode(String registerCode) {
			this.registerCode = registerCode;
		}
		@NodeMapping(tag="taxCode")
		public String getTaxCode() {
			return taxCode;
		}
		public void setTaxCode(String taxCode) {
			this.taxCode = taxCode;
		}
		@NodeMapping(tag="rentCode")
		public String getRentCode() {
			return rentCode;
		}
		public void setRentCode(String rentCode) {
			this.rentCode = rentCode;
		}
		@NodeMapping(tag="registerScale")
		public String getRegisterScale() {
			return registerScale;
		}
		public void setRegisterScale(String registerScale) {
			this.registerScale = registerScale;
		}
		@NodeMapping(tag="registerDate")
		public String getRegisterDate() {
			return registerDate;
		}
		public void setRegisterDate(String registerDate) {
			this.registerDate = registerDate;
		}
		@NodeMapping(tag="openDate")
		public String getOpenDate() {
			return openDate;
		}
		public void setOpenDate(String openDate) {
			this.openDate = openDate;
		}
		@NodeMapping(tag="registerAuthority")
		public String getRegisterAuthority() {
			return registerAuthority;
		}
		public void setRegisterAuthority(String registerAuthority) {
			this.registerAuthority = registerAuthority;
		}

}
