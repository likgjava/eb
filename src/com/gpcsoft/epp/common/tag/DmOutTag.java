package com.gpcsoft.epp.common.tag;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.context.FrameMessageResource;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.project.domain.ProjectEnum;
import com.gpcsoft.srplatform.common.utils.SysInfo;
import com.gpcsoft.srplatform.sysconfig.service.SysConfigService;
import com.gpcsoft.srplatform.sysconfig.service.SysConfigServiceImpl;

/**
 * 直接在页面中打印领域对象属性
 * 
 */
public class DmOutTag extends TagSupport {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private String value;
	private String tenderType;
	private String ebuyMethod;
	private static SysConfigService sysConfigService = null;
	private EnumService enumService;

	private EnumService getEnumService(){
		if(null == this.enumService){
			this.enumService = (EnumService)FrameBeanFactory.getBean("enumServiceImpl");
		}
		return this.enumService;
	}
	

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	public String getTenderType() {
		return tenderType;
	}

	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}

	public String getEbuyMethod() {
		return ebuyMethod;
	}

	public void setEbuyMethod(String ebuyMethod) {
		this.ebuyMethod = ebuyMethod;
	}

	public static SysConfigService getSysConfigService() {
		return sysConfigService;
	}

	public static void setSysConfigService(SysConfigService sysConfigService) {
		DmOutTag.sysConfigService = sysConfigService;
	}

	public int doStartTag() throws JspException {
		try {
			if (value == null || "".equals(value))
				return EVAL_BODY_INCLUDE;
			else {
				if (sysConfigService == null) {
					sysConfigService = (SysConfigService) FrameBeanFactory
							.getBean(SysConfigServiceImpl.BEAN_NAME);
				}
				Object object = null;
				if (tenderType != null && !"".equals(tenderType)) {// 若没有项目类型，则取默认值，否则根据项目类型和采购方式来去取值
					String tenderTypeStr = this
							.getTenderTypeByEnumName(ProjectEnum.PROJECTENUM); // 从枚举文件得到项目类型
					Map<String, String> tenderTypeMap = this
							.getMapByEnumStr(tenderTypeStr);
					String tenderTemp = tenderTypeMap.get(tenderType);

					String ebuyMethodStr = this
							.getEbuyMethodByEnumName(EbuyMethodEnum.EBUY_METHOD);
					Map<String, String> ebuyMethodMap = this
							.getMapByEnumStr(ebuyMethodStr);
					String ebuyMethodTemp = ebuyMethodMap.get(ebuyMethod);
					if ("OPEN_BIDDING".equals(ebuyMethodTemp)) {
						ebuyMethodTemp = "OPENBIDDING";
					}
					if ("INVITE_BIDDING".equals(ebuyMethodTemp)) {
						ebuyMethodTemp = "INVITEBIDDING";
					}
					if ("SINGLE_SOURCE".equals(ebuyMethodTemp)) {
						ebuyMethodTemp = "SINGLESOURCE";
					}
					value = value
							+ SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B
							+ tenderTemp;
					if (ebuyMethod == null || "".equals(ebuyMethod)) {
						value = value
								+ SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A
								+ "cn";
					} else {
						value = value
								+ SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B
								+ ebuyMethodTemp
								+ SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A
								+ "cn";
					}
					object = sysConfigService.getCacheValue(value);
				}
				if (object == null) {
					return EVAL_BODY_INCLUDE;
				}
				pageContext.getOut().print(object);
				return SKIP_BODY;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return SKIP_BODY;

	}

	/**
	 * Description : 获得项目类型枚举值 Create Date: 2010-11-29下午02:55:27 by yangx
	 * Modified Date: 2010-11-29下午02:55:27 by yangx
	 * 
	 * @param contentkey
	 *            :要读取文件的值
	 * @return String:获取的值
	 * @Exception
	 */
	private String getTenderTypeByEnumName(String enumName) {
		return this.getEnumService().getValueByKey(enumName);
	}

	/**
	 * Description : 获得采购方式枚举值 Create Date: 2010-11-29下午02:55:27 by yangx
	 * Modified Date: 2010-11-29下午02:55:27 by yangx
	 * 
	 * @param contentkey
	 *            :要读取文件的值
	 * @return String:获取的值
	 * @Exception
	 */
	private String getEbuyMethodByEnumName(String enumName) {
		return this.getEnumService().getValueByKey(enumName);
	}

	/**
	 * FuncName: getMapByEnumStr Description : 根据枚举字符串获得枚举Map
	 * 
	 * @param
	 * @param enumStr
	 * @return Map<String,String>
	 * @author: liuke
	 * @Create Date:2011-5-25 下午01:21:24
	 * @Modifier: liuke
	 * @Modified Date:2011-5-25 下午01:21:24
	 */
	private Map<String, String> getMapByEnumStr(String enumStr) {
		Map<String, String> tempMap = new HashMap<String, String>();
		String[] tenderTypeArray = enumStr.split(",");
		for (String tenderStr : tenderTypeArray) {
			String[] tenderArray = tenderStr.split(":");
			tempMap.put(tenderArray[1], tenderArray[0]);
		}
		return tempMap;
	}

}
