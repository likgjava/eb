package com.gpcsoft.epp.projrule.controller;

import java.util.ArrayList;
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
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectEnum;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.service.ProjProcessRuleService;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocService;
import com.gpcsoft.srplatform.auth.domain.ListBind;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigItem;
import com.gpcsoft.srplatform.sysconfig.service.SysConfigService;

/**
  * @gpcsoft.view value="projProcessRuleFormView"
  *  url="view/es/planform/projrule/projProcessRuleForm.jsp"
  * @gpcsoft.view value="projViewProcessRule"
  *  url="view/es/planform/projrule/projViewProcessRule.jsp"
  * @gpcsoft.view value="projProcessRuleTreeFormView"
  *  url="view/es/planform/projrule/projProcessRuleTreeForm.jsp"
  * @gpcsoft.view value="projProcessRuleListView"
  *  url="view/es/planform/projrule/projProcessRuleList.jsp"
  * @gpcsoft.view value="projProcessRuleDetailView"
  *  url="view/es/planform/projrule/projProcessRuleDetail.jsp"
  *  @gpcsoft.view value="blankView"
  *  url="view/es/common/blank.jsp"
  *  @gpcsoft.view value="projProcessRuleFormAudit"
  *  url="view/es/planform/projrule/projProcessRuleFormAudit.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ProjProcessRule.class})
@RequestMapping("/ProjProcessRuleController.do")//页面请求路径,可修改
@SuppressWarnings("unchecked")
public class ProjProcessRuleController extends AnnotationMultiController<ProjProcessRule> {

	@Autowired(required=true) @Qualifier("projProcessRuleServiceImpl")
	private ProjProcessRuleService projProcessRuleService;

	@Autowired(required = true) @Qualifier("sysConfigServiceImpl")
	private SysConfigService sysConfigService;
	
	@Autowired(required=true) @Qualifier("purchaseDocServiceImpl")
	private PurchaseDocService purchaseDocService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	

	
	/** 
	 * Description :  代理机构跳转项目规则页面
	 * Create Date: 2010-11-15下午02:00:24 by yangx  Modified Date: 2010-11-15下午02:00:24 by yangx
	 * Modified Date: 2010-10-27下午14:09:09 by zhouzhanghe 
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toProjProcessRule")
	public ModelAndView toProjProcessRule(HttpServletRequest request, SessionStatus stauts)throws Exception {
		logger.debug("\nes=ProjProcessRuleController||toProjProcessRule\n");
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		String fromType = request.getParameter("fromType");
		String projectId = request.getParameter("projectId");
		Project project = projectService.getProjectByObjId(projectId);
		Map model = new HashMap();
		//查询Boolean类型的系统配置条目
		List<SysConfigItem> sysConfigItemListBoolean = sysConfigService.getSysConfigItemBySysConfigItemPathAndDataType(SysConfigEnum.PUBLEOPENBID+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B+SysConfigEnum.PROJRULE+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A,"Boolean");
		//查询String类型的系统配置条目
		List<SysConfigItem> sysConfigItemListString = sysConfigService.getSysConfigItemBySysConfigItemPathAndDataType(SysConfigEnum.PUBLEOPENBID+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B+SysConfigEnum.PROJRULE+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A,"String");
		//查询Date类型的系统配置条目
		List<SysConfigItem> sysConfigItemListDate = sysConfigService.getSysConfigItemBySysConfigItemPathAndDataType(SysConfigEnum.PUBLEOPENBID+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B+SysConfigEnum.PROJRULE+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A,"Date");
		
		//如果是建设工程项目，则只保留待采购人确认规则
		List<SysConfigItem> sysConfigItemList = new ArrayList<SysConfigItem>();
		if(ProjectEnum.JZGC.equals(project.getTenderType())){
			//NEEDBUYERACKPURCHASEDOC
			for (int i = 0; i < sysConfigItemListBoolean.size(); i++) {
				SysConfigItem configItem = sysConfigItemListBoolean.get(i);
				if("NEEDBUYERACKPURCHASEDOC".equals(configItem.getCode())){
					sysConfigItemList.add(configItem);
				}
			}
			sysConfigItemListBoolean = sysConfigItemList;
		}
		PurchaseDoc  purchasedoc  =   purchaseDocService.getPurchaseDocByProjectId(projectId); 
		PurchaseDoc  inqpdocdoc  =  purchaseDocService.getInqpDocByProjectId(projectId);
		if(inqpdocdoc!=null||purchasedoc!=null){   //判断一下该项目是否已经提交了招标文件或询价文件
			model.put("fileDOC", "isCreate");	   //存在文件的项目不能再修改规则
		   }
		model.put("sysConfigItemListBoolean", sysConfigItemListBoolean);
		model.put("sysConfigItemListString", sysConfigItemListString);
		model.put("sysConfigItemListDate", sysConfigItemListDate);
		model.put("sysConfigItemListBooleanLength", sysConfigItemListBoolean.size());
		model.put("sysConfigItemListStringLength", sysConfigItemListString.size());
		model.put("sysConfigItemListDateLength", sysConfigItemListDate.size());
		model.put("fromType", fromType);
		return new ModelAndView("projProcessRuleFormView",model);//代理机构不管如何都显示修改页面
	}
	
	/** 
	 * Description :  获取规则数据
	 * Create Date: 2010-10-18下午04:25:18 by yangx  Modified Date: 2010-10-18下午04:25:18 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getProjProcessRuleList")
	public ModelAndView getProjProcessRuleList(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjProcessRuleController||getProjProcessRuleList\n");
		String projectId = request.getParameter("projectId");
		Map model = new HashMap();
		List<ProjProcessRule> projProcessRuleList = projProcessRuleService.getProjProcessRuleByProjectId(projectId);
		List<SysConfigItem> sysConfigItemListBoolean = sysConfigService.getSysConfigItemBySysConfigItemPathAndDataType(SysConfigEnum.PUBLEOPENBID+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B+SysConfigEnum.PROJRULE+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A,"Boolean");
		List<SysConfigItem> sysConfigItemListString = sysConfigService.getSysConfigItemBySysConfigItemPathAndDataType(SysConfigEnum.PUBLEOPENBID+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B+SysConfigEnum.PROJRULE+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A,"String");
		List<SysConfigItem> sysConfigItemListDate = sysConfigService.getSysConfigItemBySysConfigItemPathAndDataType(SysConfigEnum.PUBLEOPENBID+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B+SysConfigEnum.PROJRULE+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A,"Date");
		model.put("sysConfigItemListBoolean", sysConfigItemListBoolean);
		model.put("sysConfigItemListString", sysConfigItemListString);
		model.put("sysConfigItemListDate", sysConfigItemListDate);
		model.put("projProcessRuleList", projProcessRuleList);
		return new ModelAndView(Constants.JSON_VIEW,model);//代理机构不管如何都显示修改页面
	}
	
	/** 
	 * Description :  保存项目规则
	 * Create Date: 2010-11-4下午03:44:43 by yangx  Modified Date: 2010-11-4下午03:44:43 by yangx
	 * @param   list：绑定的规则集合
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=saveProjProcessRule")
	public ModelAndView saveProjProcessRule(ListBind list,HttpServletRequest request,SessionStatus status)throws Exception{
		logger.debug("\nes=ProjProcessRuleController||saveProjProcessRule\n");
		String projectId = request.getParameter("projectId");
		projProcessRuleService.saveProjProcessRule(projectId,list.getSets());
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName: toViewProjProcessRule
	 * Description :  查看项目规则页面
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: zhangsh
	 * @Create Date:2011-11-15  上午05:42:04
	 * @Modifier: zhangsh
	 * @Modified Date:2011-11-15  上午05:42:04
	 */
	@RequestMapping(params="method=toViewProjProcessRule")
	public ModelAndView toViewProjProcessRule(HttpServletRequest request,SessionStatus stauts)throws Exception {
		logger.debug("\nes=ProjProcessRuleController||toViewProjProcessRule\n");
		String projectId = request.getParameter("projectId");
		Map model = new HashMap();
		List<ProjProcessRule> projProcessRuleList = projProcessRuleService.getProjProcessRuleByProjectId(projectId);
		model.put("projProcessRuleList", projProcessRuleList);
		return new ModelAndView("projViewProcessRule",model);
	}
}
