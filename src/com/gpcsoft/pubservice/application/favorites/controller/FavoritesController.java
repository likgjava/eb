package com.gpcsoft.pubservice.application.favorites.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.favorites.domain.Favorites;
import com.gpcsoft.pubservice.application.favorites.service.FavoritesService;

/**
  * @gpcsoft.view value="favoritesFormView"
  *  url="view/pubservice/application/favorites/favorites_form.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Favorites.class})
@RequestMapping("/FavoritesController.do")//页面请求路径,可修改
public class FavoritesController extends AnnotationMultiController<Favorites> {

	@Autowired(required=true) @Qualifier("favoritesServiceImpl")
	private FavoritesService favoritesService;
	
	/** 
	 * Description :  跳转到收藏页面
	 * Create Date: 2010-8-19下午01:57:24 by yucy  Modified Date: 2010-8-19下午01:57:24 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toFavoritesForm")
	public ModelAndView toFavoritesForm(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>(); 
		Map<String, Object> param = new HashMap<String, Object>(); 
		if(null!=request.getParameter("favoriteName")){
			param.put("favoriteName",new String((request.getParameter("favoriteName")).getBytes("ISO-8859-1"),"UTF-8") );
			param.put("favoriteId",request.getParameter("favoriteId") );
			param.put("favoriteType",request.getParameter("favoriteType") );
			model = favoritesService.getSimilarFavorites(param);
		}
		return new ModelAndView("favoritesFormView",model);
	}
	
	/** 
	 * Description : 保存收藏 
	 * Create Date: 2010-8-19下午01:51:55 by yucy  Modified Date: 2010-8-19下午01:51:55 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveFavorites")
	public ModelAndView saveFavorites(HttpServletRequest request ,Favorites favorites) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>(); 
		favorites.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		favorites.setCreateTime(new Date());
		Boolean result = favoritesService.saveFavorites(favorites);
		if(result){
			model.put(Constants.JSON_RESULT, "加入收藏成功！");
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	@SuppressWarnings("unchecked")
	@Override
    protected QueryObject onFind(QueryObject query, HttpServletRequest request)throws Exception{
    	query.getQueryParam().and(new QueryParam("createUser.objId",AuthenticationHelper.getCurrentUser(true).getObjId()));
    	return query;
    }
}
