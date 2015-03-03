package com.gpcsoft.smallscale.point.controller;

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

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.point.domain.Consume;
import com.gpcsoft.smallscale.point.service.ConsumeService;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="consumeFormView"
  *  url="view/smallscale/point/consume_form.jsp"
  *  
  * @gpcsoft.view value="consumeTreeFormView"
  *  url="view/smallscale/point/consume_treeForm.jsp"
  *  
  * @gpcsoft.view value="consumeListView"
  *  url="view/smallscale/point/consume_list.jsp"
  *  
  * @gpcsoft.view value="consumeDetailView"
  *  url="view/smallscale/point/consume_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Consume.class})
@RequestMapping("/ConsumeController.do")//页面请求路径,可修改
public class ConsumeController extends AnnotationMultiController<Consume> {

	@Autowired(required=true) @Qualifier("consumeServiceImpl")
	private ConsumeService consumeService;

	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected QueryObject onFind(QueryObject query, HttpServletRequest request)
			throws Exception {
		User user=AuthenticationHelper.getCurrentUser(true);
		String userIdes = request.getParameter("userIdes");
		if("userIdes".equals(userIdes)){//当前用户下的消费积分
			query.getQueryParam().and(new QueryParam("userId",QueryParam.OPERATOR_EQ,user));
		}
		return super.onFind(query, request);
	}
	
	/**
	 * 根据id查询一条消费积分的详细记录
	 * @param objId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=queryConsumeById")
	public ModelAndView queryConsumeById(String objId) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		Consume consume=consumeService.get(objId);
		map.put("consume", consume);
		return new ModelAndView("consumeDetailView",map);
	}
}
