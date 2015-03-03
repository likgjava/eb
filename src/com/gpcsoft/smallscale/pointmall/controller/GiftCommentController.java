package com.gpcsoft.smallscale.pointmall.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.pointmall.service.GiftCommentService;
import com.gpcsoft.smallscale.pointmall.domain.GiftComment;

/**
  * @gpcsoft.view value="giftCommentFormView"
  *  url="view/smallscale/pointmall/gift_comment_add.jsp"
  * @gpcsoft.view value="giftCommentListView"
  *  url="view/smallscale/pointmall/gift_comment_manager_list.jsp"
  * @gpcsoft.view value="viewGiftCommentView"
  *  url="view/smallscale/pointmall/gift_comment_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GiftComment.class})
@RequestMapping("/GiftCommentController.do")//页面请求路径,可修改
public class GiftCommentController extends AnnotationMultiController<GiftComment> {

	@Autowired(required=true) @Qualifier("giftCommentServiceImpl")
	private GiftCommentService giftCommentService;

	/**
	 * Description :  礼品点评查看
	 * Create Date: 2011-1-14上午09:12:33 by zhaojf  Modified Date: 2011-1-14上午09:12:33 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=viewGiftComment")
	public ModelAndView viewGiftComment(String objId,SessionStatus status) throws Exception{
		Map<String , Object> model = new HashMap<String, Object>();
		status.setComplete();
		GiftComment giftComment = giftCommentService.get(objId);
		model.put("giftComment", giftComment);		
		return new ModelAndView("viewGiftCommentView",model);
	}
	
	
	/**
	 * Description :  礼品点评删除
	 * Create Date: 2011-1-14上午09:28:04 by zhaojf  Modified Date: 2011-1-14上午09:28:04 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=removeGiftComment")
	public ModelAndView removeGiftComment(String objId,SessionStatus status) throws Exception{
		Map<String , Object> model = new HashMap<String, Object>();
		giftCommentService.remove(objId, GiftComment.class);
		model.put("giftComment", "success");
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	
	/**
	 * Description :  跳转到新增页面
	 * Create Date: 2011-1-14上午09:31:30 by zhaojf  Modified Date: 2011-1-14上午09:31:30 by zhaojf
	 * @param 
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toGiftCommentAddORModify")
	public ModelAndView toGiftCommentAddORModify(HttpServletRequest request) throws Exception{
		Map<String , Object> model = new HashMap<String, Object>();
		String returnStrView = "giftCommentFormView";	
		
		return new ModelAndView(returnStrView,model);
	}
	
	
	/**
	 * Description :  保存礼品评价
	 * Create Date: 2011-1-14下午04:25:55 by zhaojf  Modified Date: 2011-1-14下午04:25:55 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params= "method=saveGiftComment")
	public ModelAndView saveGiftComment(HttpServletRequest request) throws Exception{
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> model = new HashMap<String, Object>();
		
		param.put("giftId", request.getParameter("giftId"));
		param.put("level", request.getParameter("level"));
		param.put("rateName", request.getParameter("rateName"));
		param.put("comment", request.getParameter("comment"));
		param.put("title", request.getParameter("title"));
		param.put("userId",AuthenticationHelper.getCurrentUser(true).getObjId());
		model = giftCommentService.saveGiftComment(param);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	
}
