package com.gpcsoft.epp.signuprecord.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.signuprecord.domain.SignUpCondFactor;
import com.gpcsoft.epp.signuprecord.service.SignUpCondFactorService;

/**
  * @gpcsoft.view value="signUpCondFactorFormView"
  *  url="view/es/planform/signuprecord/signUpCondFactorForm.jsp"
  * @gpcsoft.view value="signUpCondFactorTreeFormView"
  *  url="view/es/planform/signuprecord/signUpCondFactorTreeForm.jsp"
  * @gpcsoft.view value="signUpCondFactorListView"
  *  url="view/es/planform/signuprecord/signUpCondFactorList.jsp"
  *  @gpcsoft.view value="inqpBulletinFormView"
  *  url="view/es/inquiryprice/inqpbulletin/inqpBulletinForm.jsp"
  *  @gpcsoft.view value="signUpCondFactorView"
  *  url="view/es/inquiryprice/inqpbulletin/signUpCondFactorView.jsp"
  *  @gpcsoft.view value="signUpCondFactorDetailView"
  *  url="view/es/inquiryprice/inqpbulletin/signUpCondFactorDetailView.jsp"
  *  @gpcsoft.view value="signUpCondFactorForInqpView"
  *  url="view/es/inquiryprice/inqpbulletin/signUpCondFactorView1.jsp"
  *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={SignUpCondFactor.class})
@RequestMapping("/SignUpCondFactorController.do")//页面请求路径,可修改
public class SignUpCondFactorController extends AnnotationMultiController<SignUpCondFactor> {

	@Autowired(required=true) @Qualifier("signUpCondFactorServiceImpl")
	private SignUpCondFactorService signUpCondFactorService;

	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	/**
	 * Description : 保存报名指标 
	 * Create Date: 2010-8-10下午01:04:21 by liuke  Modified Date: 2010-8-10下午01:04:21 by liuke
	 * @Exception
	 */
	@RequestMapping(params="method=saveSignUpCondFactor")
	public ModelAndView saveSignUpCondFactor(HttpServletRequest request,SignUpCondFactor factor, SessionStatus status)throws Exception {
		logger.debug("\nes SignUpCondFactorController||saveSignUpCondFactor\n");
		String projectId = request.getParameter("projectId");	//项目ID
		Project project = projectService.getProjectByObjId(projectId);
		factor.setProject(project);
		signUpCondFactorService.saveSignUpCondFactor(factor);
		status.isComplete();
		Map<String ,Object> model = new HashMap<String ,Object>();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * Description :  根据项目得到报名指标对象
	 * Create Date: 2010-8-10下午01:04:21 by liuke  Modified Date: 2010-8-10下午01:04:21 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=getSignUpCondFactorListByProjectId")
	public ModelAndView getSignUpCondFactorListByProjectId(HttpServletRequest request,SessionStatus status)throws Exception {
		logger.debug("\nes SignUpCondFactorController||getSignUpCondFactorListByProjectId\n");
		String type = request.getParameter("type");//返回类型
		String projectId = request.getParameter("projectId");//项目ID
		Project project = projectService.getProjectByObjId(projectId);
		List<SignUpCondFactor> list = signUpCondFactorService.getSignUpCondFactorListByProjectId(projectId);
		Map<String ,Object> model = new HashMap<String ,Object>();
		model.put("signUpCondFactorList", list);
		String returnValue = "";
		if ("detail".equals(type)) {//往明细页面跳转
			returnValue ="signUpCondFactorDetailView";
		} else {//往修改页面跳转
			if(EbuyMethodEnum.INQUIRY.equals(project.getEbuyMethod())){//如果是询价采购
				returnValue = "signUpCondFactorForInqpView";
			}else{
				returnValue ="signUpCondFactorView";
			}
		}
		return new ModelAndView(returnValue, model);
	}
	
	/**
	 * Description : 删除报名指标 
	 * Create Date: 2010-8-10下午01:04:21 by liuke  Modified Date: 2010-8-10下午01:04:21 by liuke
	 * @Exception
	 */
	@RequestMapping(params="method=removeSignUpCondFactor")
	public ModelAndView removeSignUpCondFactor(HttpServletRequest request,SessionStatus status)throws Exception {
		logger.debug("\nes SignUpCondFactorController||removeSignUpCondFactor\n");
		String objId = request.getParameter("signupfacId"); 	//项目ID
		signUpCondFactorService.removeSignUpCondFactorByObjId(objId);
		Map<String,Object> model = new HashMap<String,Object>();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
