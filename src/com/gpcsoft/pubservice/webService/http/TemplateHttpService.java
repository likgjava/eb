package com.gpcsoft.pubservice.webService.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.MathUtil;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.pubservice.application.template.domain.DotTemplate;
import com.gpcsoft.pubservice.application.template.service.DotTemplateService;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.UserService;
import com.gpcsoft.srplatform.common.utils.SysInfo;

@Controller
@Scope("request")
@SuppressWarnings("unchecked")
@RequestMapping("/TemplateHttpService.do")
public class TemplateHttpService extends AnnotationMultiController {

	@Autowired(required=true) @Qualifier("dotTemplateServiceImpl")
	private DotTemplateService dotTemplateService;
	
	@Autowired(required = true) @Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	
	/** 
	 * Description :  选择远程范本
	 * Create Date: 2011-10-10下午02:33:11 by likg  Modified Date: 2011-10-10下午02:33:11 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=selectPurchaseFileTemplate")
	public void selectPurchaseFileTemplate(HttpServletRequest request, HttpServletResponse response)throws Exception {
		boolean isSuccess = true; //是否执行成功
		String operDesc = "操作成功！"; //操作描述
		String operException = ""; //操作异常描述
		List<DotTemplate> templateList = new ArrayList<DotTemplate>(); //存放范本信息
		
		try {
			//验证用户的用户名和密码
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if(!StringUtils.hasLength(username)) {
				operDesc = "用户名为空！";
				isSuccess = false;
			} else if(!StringUtils.hasLength(password)) {
				operDesc = "密码为空！";
				isSuccess = false;
			}
			
			if(isSuccess) {
				User user = userService.getUserByUsName(username);
				if(user!=null && MathUtil.encryptPassWordSHA(password).equals(user.getPassword())) {
					//获取范本信息
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("categoryCode", request.getParameter("itemCode")); //品目编号
					param.put("districtCode", request.getParameter("districtCode")); //区域编号
					param.put("orgInfoId", (request.getParameter("orgCode")==null ? null : this.getOrgIdByOrgCode(request.getParameter("orgCode")))); //所属机构编号
					param.put("keyWord", request.getParameter("shareName")); //共享文件名称
					param.put("isShare", true); //取共享范本
					templateList = dotTemplateService.getTemplateListByParam(param);
				} else {
					operDesc = "用户名或密码不正确！";
					isSuccess = false;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			operException = e.getMessage();
			isSuccess = false;
		}
		
		//添加返回XML的主体内容
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
		responseXml.append("<selectPurchaseFileTemplate xmlns='http://www.gpcsoft.com'>");
		responseXml.append("<header>");
		responseXml.append("<operTag>" + (isSuccess == true ? "Y" : "N") + "</operTag>");
		responseXml.append("<operDesc>" + operDesc + "</operDesc>");
		responseXml.append("<operException>" + operException + "</operException>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<templateList>");
		for(DotTemplate template : templateList) {
			responseXml.append("<template>");
			responseXml.append("<itemCode>" + template.getCategoryCode() + "</itemCode>");
			responseXml.append("<templateId>" + template.getObjId() + "</templateId>");
			responseXml.append("<shareName>" + template.getTemplateName() + "</shareName>");
			responseXml.append("<uploadOrg>" + (template.getOrg()==null ? "" : template.getOrg().getOrgName()) + "</uploadOrg>");
			responseXml.append("<uploadUser>" + (template.getCreateUser().getEmp()==null ? "" : template.getCreateUser().getEmp().getName()) + "</uploadUser>");
			responseXml.append("<uploadTime>" + DateUtil.format(template.getCreateTime(), "yyyy-MM-dd HH:mm:ss") + "</uploadTime>");
			responseXml.append("<remark></remark>");
			responseXml.append("</template>");
		}
		responseXml.append("</templateList>");
		responseXml.append("</body>");
		responseXml.append("</selectPurchaseFileTemplate>");
		write(responseXml.toString(), response);
	}
	
	/** 
	 * Description :  获取远程范本
	 * Create Date: 2011-10-10下午02:59:25 by likg  Modified Date: 2011-10-10下午02:59:25 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=downPurchaseFileTemplate")
	public void downPurchaseFileTemplate(HttpServletRequest request, HttpServletResponse response)throws Exception {
		boolean isSuccess = true; //是否执行成功
		String operDesc = "操作成功！"; //操作描述
		String operException = ""; //操作异常描述
		List<DotTemplate> templateList = new ArrayList<DotTemplate>(); //存放范本信息
		User user = null;
		
		try {
			//验证用户的用户名和密码
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if(!StringUtils.hasLength(username)) {
				operDesc = "用户名为空！";
				isSuccess = false;
			} else if(!StringUtils.hasLength(password)) {
				operDesc = "密码为空！";
				isSuccess = false;
			}
			
			if(isSuccess) {
				user = userService.getUserByUsName(username);
				if(user!=null && MathUtil.encryptPassWordSHA(password).equals(user.getPassword())) {
					//获取范本信息
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("categoryCode", request.getParameter("itemCode")); //品目编号
					param.put("districtCode", request.getParameter("districtCode")); //区域编号
					param.put("orgInfoId", (request.getParameter("orgCode")==null ? null : this.getOrgIdByOrgCode(request.getParameter("orgCode")))); //所属机构编号
					param.put("objIds", request.getParameter("templateId")); //范本文件号
					param.put("isShare", true); //取共享范本
					templateList = dotTemplateService.getTemplateListByParam(param);
				} else {
					operDesc = "用户名或密码不正确！";
					isSuccess = false;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			operException = e.getMessage();
			isSuccess = false;
		}
		
		//添加返回XML的主体内容
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
		responseXml.append("<downPurchaseFileTemplate xmlns='http://www.gpcsoft.com'>");
		responseXml.append("<header>");
		responseXml.append("<operTag>" + (isSuccess == true ? "Y" : "N") + "</operTag>");
		responseXml.append("<operDesc>" + operDesc + "</operDesc>");
		responseXml.append("<operException>" + operException + "</operException>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<templateList>");
		for(DotTemplate template : templateList) {
			responseXml.append("<template>");
			responseXml.append("<templateId>" + template.getObjId() + "</templateId>");
			responseXml.append("<shareName>" + template.getTemplateName() + "</shareName>");
			responseXml.append("<downType>http</downType>");
			responseXml.append("<downUrl>http://" +SysInfo.getInstance().getServername()+"/DotTemplateShowController.do?method=downloadTemplateFile&amp;templateFileId="+template.getTemplateFile()+"&amp;userId="+user.getObjId()+"&amp;templateId="+template.getObjId()+ "</downUrl>");
			responseXml.append("</template>");
		}
		responseXml.append("</templateList>");
		responseXml.append("</body>");
		responseXml.append("</downPurchaseFileTemplate>");
		write(responseXml.toString(), response);
	}
	
	/** 
	 * Description :  根据机构编号获取机构Id
	 * Create Date: 2011-10-11上午09:16:15 by likg  Modified Date: 2011-10-11上午09:16:15 by likg
	 * @param   orgCode:机构编号
	 * @return  
	 * @Exception   
	 */
	private String getOrgIdByOrgCode(String orgCode) throws Exception {
		String orgId = null;
		QueryObject<OrgInfo> query = new QueryObjectBase<OrgInfo>();
		query.setEntityClass(OrgInfo.class);
		query.setQueryParam(new QueryParam("orgCode",QueryParam.OPERATOR_EQ,orgCode));
		List<OrgInfo> orgInfoList = orgInfoService.findByQuery(query);
		if(orgInfoList!=null && orgInfoList.size()>0) {
			orgId = orgInfoList.get(0).getObjId();
		}
		return orgId;
	}
	
}
