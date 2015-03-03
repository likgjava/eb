package com.gpcsoft.pubservice.webService.http;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.base.purcategory.manager.PurCategoryManager;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.MathUtil;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.UserService;
import com.gpcsoft.srplatform.baseData.domain.District;
import com.gpcsoft.srplatform.baseData.manager.DistrictManager;
import com.gpcsoft.srplatform.baseData.service.DistrictService;
import com.gpcsoft.srplatform.common.utils.SysInfo;

@Controller
@Scope("request")
@SuppressWarnings("unchecked")
@RequestMapping("/ConfigInfo.do")
public class BaseInfoHttpService extends AnnotationMultiController {

	@Autowired(required=true) @Qualifier("districtServiceImpl")
	private DistrictService districtService;
	
	@Autowired(required=true) @Qualifier("districtManagerImpl")
	private DistrictManager districtManager;
	
	@Autowired(required = true) @Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired(required=true) @Qualifier("purCategoryManagerImpl")
	private PurCategoryManager purCategoryManager;
	
	/** 
	 * Description :  获取公告区域信息列表
	 * Create Date: 2011-5-11下午04:12:23 by likg  Modified Date: 2011-5-11下午04:12:23 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getBulletinRegionInfo")
	public void getBulletinRegionInfo(HttpServletRequest request, HttpServletResponse response)throws Exception {
		boolean isSuccess = true; //是否执行成功
		String operDesc = "操作成功！"; //操作描述
		List<District> districtList = new ArrayList<District>(); //存放区域信息
		
		try {
			//验证用户的用户名和密码
			String userCode = request.getParameter("userCode");
			String password = request.getParameter("password");
			if(!StringUtils.hasLength(userCode)) {
				operDesc = "用户名为空！";
				isSuccess = false;
			} else if(!StringUtils.hasLength(password)) {
				operDesc = "密码为空！";
				isSuccess = false;
			}
			
			if(isSuccess) {
				User user = userService.getUserByUsName(userCode);
				if(user!=null && MathUtil.encryptPassWordSHA(password).equals(user.getPassword())) {
					//获取第一级区域信息（省级）
					districtList = districtService.findTopDistricts();
				} else {
					operDesc = "用户名或密码不正确！";
					isSuccess = false;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			isSuccess = false;
		}
		
		//添加返回XML的主体内容
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
		responseXml.append("<getBulletinRegionInfo xmlns='http://www.gpcsoft.com'>");
		responseXml.append("<header>");
		responseXml.append("<operTag>" + (isSuccess == true ? "Y" : "N") + "</operTag>");
		responseXml.append("<operDesc>" + operDesc + "</operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<regionList>");
		for(District district : districtList) {
			responseXml.append("<region>");
			responseXml.append("<code>" + district.getCode() + "</code>");
			responseXml.append("<name>" + district.getName() + "</name>");
			responseXml.append("<parentCode></parentCode>");
			responseXml.append("<price></price>");
			responseXml.append("<offer></offer>");
			responseXml.append("<open>true</open>");
			responseXml.append("<httpWebRequestUrl>http://" + SysInfo.getInstance().getServername() +"/BulletinController.do</httpWebRequestUrl>");
			responseXml.append("<webServiceUrl></webServiceUrl>");
			responseXml.append("<subregions></subregions>");
			responseXml.append("</region>");
		}
		responseXml.append("</regionList>");
		responseXml.append("</body>");
		responseXml.append("</getBulletinRegionInfo>");
		write(responseXml.toString(), response);
	}
	
	/** 
	 * Description :  获取品目列表
	 * Create Date: 2011-5-11下午05:48:35 by likg  Modified Date: 2011-5-11下午05:48:35 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getPurchaseItemInfo")
	public void getPurchaseItemInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean isSuccess = true; //是否执行成功
		String operDesc = "操作成功！"; //操作描述
		List<PurCategory> purCategoryList = new ArrayList<PurCategory>(); //存放采购品目信息
		
		try {
			//验证用户的用户名和密码
			String userCode = request.getParameter("userCode");
			String password = request.getParameter("password");
			if(!StringUtils.hasLength(userCode)) {
				operDesc = "用户名为空！";
				isSuccess = false;
			} else if(!StringUtils.hasLength(password)) {
				operDesc = "密码为空！";
				isSuccess = false;
			}
			
			if(isSuccess) {
				User user = userService.getUserByUsName(userCode);
				if(user!=null && MathUtil.encryptPassWordSHA(password).equals(user.getPassword())) {
					//查询采购品目信息
					purCategoryList = purCategoryManager.getPurCategory("categoryCode", true);
				} else {
					operDesc = "用户名或密码不正确！";
					isSuccess = false;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			isSuccess = false;
		}

		//添加返回XML的主体内容
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
		responseXml.append("<getPurchaseItemInfo xmlns='http://www.gpcsoft.com'>");
		responseXml.append("<header>");
		responseXml.append("<operTag>" + (isSuccess == true ? "Y" : "N") + "</operTag>");
		responseXml.append("<operDesc>" + operDesc + "</operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<purchaseItemList>");
		for(PurCategory purCategory : purCategoryList) {
			responseXml.append("<item>");
			responseXml.append("<code>" + purCategory.getCategoryCode() + "</code>");
			responseXml.append("<name>" + purCategory.getCategoryName() + "</name>");
			responseXml.append("<parentCode>" + (purCategory.getParent()==null ? "" : purCategory.getParent().getCategoryCode()) + "</parentCode>");
			responseXml.append("</item>");
		}
		responseXml.append("</purchaseItemList>");
		responseXml.append("</body>");
		responseXml.append("</getPurchaseItemInfo>");
		write(responseXml.toString(), response);
	}
	
	/** 
	 * Description :  获取基础数据最新更新时间
	 * Create Date: 2011-5-13上午11:40:26 by likg  Modified Date: 2011-5-13上午11:40:26 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getLastUpdateTime")
	public void getLastUpdateTime(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean isSuccess = true; //是否执行成功
		Date lastUpdateTime = null; //最后更新时间
		
		try {
			//信息类型
			String infoType = request.getParameter("infoType");
			
			//获取区域的最新更新时间
			if("area".equals(infoType)) {
				lastUpdateTime = districtManager.getLastUpdateTime();
			}
			//获取采购品目的最新更新时间
			else if("item".equals(infoType)) {
				lastUpdateTime = purCategoryManager.getLastUpdateTime();
			}
			//获取服务地址的最新更新时间
			else if("server".equals(infoType)) {
			}
			//获取公告类型的最新更新时间
			else if("btype".equals(infoType)) {
			}
			//获取资质定义的最新更新时间
			else if("qualification".equals(infoType)) {
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			isSuccess = false;
		}

		//添加返回XML的主体内容
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
		responseXml.append("<getHeatNum xmlns='http://www.gpcsoft.com'>");
		responseXml.append("<header>");
		responseXml.append("<operTag>" + (isSuccess == true ? "Y" : "N") + "</operTag>");
		responseXml.append("<operDesc>" + (lastUpdateTime==null ? "记录为空！" : "操作成功！") + "</operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<updateInfo>");
		responseXml.append("<lastUpdateTime>"+ (lastUpdateTime==null ? "" : DateUtil.format(lastUpdateTime, "yyyy-MM-dd HH:mm:ss")) +"</lastUpdateTime>");
		responseXml.append("</updateInfo>");
		responseXml.append("</body>");
		responseXml.append("</getHeatNum>");
		write(responseXml.toString(), response);
	}
	
}
