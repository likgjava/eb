package com.gpcsoft.pubservice.application.template.controller;

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
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.template.domain.DotTemplate;
import com.gpcsoft.pubservice.application.template.domain.DotTemplateFavorite;
import com.gpcsoft.pubservice.application.template.service.DotTemplateFavoriteService;
import com.gpcsoft.pubservice.application.template.service.DotTemplateService;

/**
  * @gpcsoft.view value="dotTemplateFavoriteFormView"
  *  url="view/pubservice/application/template/template_favorite_form.jsp"
  */
@Controller
@Scope("request")
@SessionAttributes(types={DotTemplateFavorite.class})
@RequestMapping("/DotTemplateFavoriteController.do")
public class DotTemplateFavoriteController extends AnnotationMultiController<DotTemplateFavorite> {

	@Autowired(required=true) @Qualifier("dotTemplateFavoriteServiceImpl")
	private DotTemplateFavoriteService dotTemplateFavoriteService;
	
	@Autowired(required=true) @Qualifier("dotTemplateServiceImpl")
	private DotTemplateService dotTemplateService;

	/** 
	 * Description :  跳转到新增或修改页面
	 * Create Date: 2011-8-1上午11:23:45 by likg  Modified Date: 2011-8-1上午11:23:45 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateOrUpdateView")
	public ModelAndView toCreateOrUpdateView(String objId, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String templateId = request.getParameter("templateId"); //范本Id
		
		//获取范本信息
		DotTemplate dotTemplate = dotTemplateService.get(templateId);
		model.put("dotTemplate", dotTemplate);
		
		if(!StringUtils.hasLength(objId)) {
			//判断是否已经收藏
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("templateId", templateId);
			param.put("userId", AuthenticationHelper.getCurrentUser(true).getObjId());
			List<DotTemplateFavorite> favoriteList = dotTemplateFavoriteService.getTemplateFavoriteList(param);
			if(favoriteList!=null && favoriteList.size()>0) {
				model.put("hadFavorite", true);
				model.put("favoriteName", favoriteList.get(0).getFavoriteName());
			}
		}
		
		return new ModelAndView("dotTemplateFavoriteFormView", model);
	}
	
	/** 
	 * Description :  保存范本收藏信息
	 * Create Date: 2011-8-1上午11:46:47 by likg  Modified Date: 2011-8-1上午11:46:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveDotTemplateFavorite")
	public ModelAndView saveDotTemplateFavorite(DotTemplateFavorite dotTemplateFavorite, HttpServletRequest request, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//保存范本信息
		dotTemplateFavoriteService.saveDotTemplateFavorite(dotTemplateFavorite);
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
