package com.gpcsoft.bizplatform.base.purcatalog.controller;

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

import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalog;
import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogDetail;
import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogProcType;
import com.gpcsoft.bizplatform.base.purcatalog.service.PurCatalogService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.BeanUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
 * @gpcsoft.view value="purCatalogFormView"
 *               url="view/bizplatform/base/purcatalog/catalog_form.jsp"
 * 
 * @gpcsoft.view value="purCatalogTreeFormView"
 *               url="view/base/purcatalog/base/purcatalog/pu_catalog_tree_form.jsp"
 * 
 * @gpcsoft.view value="purCatalogListView"
 *               url="view/bizplatform/base/purcatalog/catalog_list.jsp"
 *               
 * @gpcsoft.view value="purCatalogAuditListView"
 *               url="view/bizplatform/base/purcatalog/catalog_audit_list.jsp"
 * 
 * @gpcsoft.view value="purCatalogDetailView"
 *               url="view/base/purcatalog/base/purcatalog/pu_catalog_detail.jsp"
 * 
 * @gpcsoft.view value="purCatalogAuditView"
 *               url="view/bizplatform/base/purcatalog/catalog_audit_form.jsp"
 * 
 * @gpcsoft.view value="purCatalogView"
 *               url="view/bizplatform/base/purcatalog/catalog_view.jsp"
 *               
 * @gpcsoft.view value="addCatalogFromCategoryView"
 *               url="view/bizplatform/base/purcatalog/catalog_add_catagory.jsp"
 *               
 */
@Controller
// 标识为控制器
@Scope("request")
@SessionAttributes(types = { PurCatalog.class })
@RequestMapping("/PurCatalogController.do")
// 页面请求路径,可修改
public class PurCatalogController extends AnnotationMultiController<PurCatalog> {

	@Autowired(required = true)
	@Qualifier("purCatalogServiceImpl")
	private PurCatalogService purCatalogService;

	/**
	 * Description : 跳转到列表 Create Date: 2010-8-9下午05:19:43 by yucy Modified
	 * Date: 2010-8-9下午05:19:43 by yucy
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=toCatalogListView")
	public ModelAndView toCatalogListView(HttpServletRequest request)throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		List<Integer> anual = purCatalogService.getAnual();
		model.put("anual", anual);
		return new ModelAndView("purCatalogListView", model);
	}
	
	/**
	 * Description : 跳转到审核列表 Create Date: 2010-8-9下午05:19:43 by yucy Modified
	 * Date: 2010-8-9下午05:19:43 by yucy
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=toCatalogAuditListView")
	public ModelAndView toCatalogAuditListView(HttpServletRequest request)throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		List<Integer> anual = purCatalogService.getAnual();
		model.put("anual", anual);
		return new ModelAndView("purCatalogAuditListView", model);
	}
	
	/**
	 * Description : 跳转到新增或修改页面 Create Date: 2010-8-9下午05:19:43 by yucy Modified
	 * Date: 2010-8-9下午05:19:43 by yucy
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=toCreateOrModifyView")
	public ModelAndView toCreateOrModifyView(HttpServletRequest request)throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String objId = request.getParameter("objId");
		PurCatalog purcatalog;
		if (null != objId && !"".equals(objId)) {
			purcatalog = purCatalogService.get(objId);
		} else {
			purcatalog = new PurCatalog();
		}
		model.put("purCatalog", purcatalog);
		return new ModelAndView("purCatalogFormView", model);
	}

	/**
	 * Description : 跳转到审核页面 Create Date: 2010-8-12上午11:16:31 by yucy Modified
	 * Date: 2010-8-12上午11:16:31 by yucy
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=toAuditCatalogView")
	public ModelAndView toAuditCatalogView(HttpServletRequest request)throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String objId = request.getParameter("objId");
		PurCatalog purcatalog = purCatalogService.get(objId);
		model.put("purCatalog", purcatalog);
		return new ModelAndView("purCatalogAuditView", model);
	}

	/**
	 * Description : 跳转到查看页面 Create Date: 2010-8-12上午11:16:31 by yucy Modified
	 * Date: 2010-8-12上午11:16:31 by yucy
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=toCatalogView")
	public ModelAndView toCatalogView(HttpServletRequest request)throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String objId = request.getParameter("objId");
		PurCatalog purcatalog = purCatalogService.get(objId);
		model.put("purCatalog", purcatalog);
		return new ModelAndView("purCatalogView", model);
	}

	/**
	 * Description : 保存目录 Create Date: 2010-8-9下午02:17:31 by yucy Modified Date:
	 * 2010-8-9下午02:17:31 by yucy
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=savePurCatalog")
	public ModelAndView savePurCatalog(HttpServletRequest request,PurCatalog purCatalog, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String saveType = request.getParameter("saveType");
		String type = request.getParameter("type");
		
		if("new".equals(type)){
			PurCatalog old = purCatalog;
			purCatalog  = new PurCatalog();
			BeanUtils.copyPropertiesFilterEmpty(purCatalog, old);
			purCatalog.setObjId(null);
			purCatalog.setPurCatalogDetailSet(null);
			purCatalog.setPurCatalogProcTypeSet(null);
			purCatalog.setPurCatalogDistrictSet(null);
		}
		//区域id串
		String districtString = request.getParameter("districtString");
		
		PurCatalog purCatalogPrisist = purCatalogService.savePurCatalog(purCatalog, saveType,districtString);
		if (purCatalogPrisist != null) {
			model.put(Constants.JSON_RESULT, "操作成功！");
			// status.setComplete();
			purCatalog = purCatalogPrisist;
			model.put("purCatalog", purCatalog);
		} else {
			model.put(Constants.JSON_RESULT, "操作失败！");
		}

		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * Description : 审核目录 Create Date: 2010-8-9下午02:17:31 by yucy Modified Date:
	 * 2010-8-9下午02:17:31 by yucy
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=auditPurCatalog")
	public ModelAndView auditPurCatalog(HttpServletRequest request,PurCatalog purCatalog,SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("auditType", request.getParameter("auditType"));
		param.put("objId", request.getParameter("objId"));
		param.put("purCatalog", purCatalog);

		Boolean result = purCatalogService.auditPurCatalog(param);

		if (result) {
			model.put(Constants.JSON_RESULT, "操作成功！");
		} else {
			model.put(Constants.JSON_RESULT, "操作失败！");
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到新增目录
	 * Create Date: 2010-9-3下午04:17:41 by yucy  Modified Date: 2010-9-3下午04:17:41 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=addCatalogFromCategoryView")
	public ModelAndView addCatalogFromCategoryView(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("categoryId", request.getParameter("categoryId"));
		//List<String[]> result =  purCatalogService.getCatalogByCategory(param);
		//model.put("catalogObject", result);
		model.put("categoryId", request.getParameter("categoryId"));
		return new ModelAndView("addCatalogFromCategoryView", model);
	}

	/**
	 * Description : 保存目录明细对象 Create Date: 2010-8-10下午04:09:34 by yucy Modified
	 * Date: 2010-8-10下午04:09:34 by yucy
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=saveCatalogDetailOrProcType")
	public ModelAndView saveCatalogDetailOrProcType(HttpServletRequest request,SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("detailId", request.getParameter("detailId"));
		param.put("catalogId", request.getParameter("catalogId"));
		param.put("categoryIds", request.getParameter("categoryIds"));
		param.put("goodsPrice", request.getParameter("goodsPrice"));
		param.put("yearTotal", request.getParameter("yearTotal"));

		param.put("catalogTypeJson", request.getParameter("catalogTypeJson"));
		Boolean result = purCatalogService.saveCatalogDetailOrProcType(param);
		if (result) {
			PurCatalog purCatalog = purCatalogService.get(request.getParameter("catalogId"));
			model.put("purCatalog", purCatalog);//避免sessionbug 
			model.put(Constants.JSON_RESULT, "操作成功！");
		} else {
			model.put(Constants.JSON_RESULT, "操作失败！");
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * Description : 获得展开节点的信息 Create Date: 2010-8-11上午12:27:28 by yucy Modified
	 * Date: 2010-8-11上午12:27:28 by yucy
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getOpenItemInfo")
	public ModelAndView getOpenItemInfo(HttpServletRequest request)throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("catalogId", request.getParameter("catalogId"));
		param.put("categoryId", request.getParameter("categoryId"));

		List openItemInfo = purCatalogService.getOpenItemInfo(param);

		model.put("openItemInfo", openItemInfo);

		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * Description : 根据id抓取数据 Create Date: 2010-8-11上午10:43:50 by yucy Modified
	 * Date: 2010-8-11上午10:43:50 by yucy
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=getDetailAndTypeDetailInfo")
	public ModelAndView getDetailAndTypeDetailInfo(HttpServletRequest request)throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("catalogId", request.getParameter("catalogId"));
		param.put("categoryId", request.getParameter("categoryId"));

		List<PurCatalogDetail> detailInfo = purCatalogService
				.getDetailInfo(param);
		List<PurCatalogProcType> typeInfo = purCatalogService
				.getTypeInfo(param);
		model.put("detailInfo", detailInfo);
		model.put("typeInfo", typeInfo);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * Description : 复制目录 Create Date: 2010-8-11下午07:35:03 by yucy Modified
	 * Date: 2010-8-11下午07:35:03 by yucy
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=createCopyCatalog")
	public ModelAndView createCopyCatalog(HttpServletRequest request,SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("objId", request.getParameter("objId"));
		param.put("copyTitle", request.getParameter("copyTitle"));

		purCatalogService.createCopyCatalog(param);

		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * Description : 删除目录(备以约束删除) Create Date: 2010-8-12上午01:02:08 by yucy
	 * Modified Date: 2010-8-12上午01:02:08 by yucy
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=deleteCatalog")
	public ModelAndView deleteCatalog(HttpServletRequest request,SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("objId", request.getParameter("objId"));

		Boolean result = purCatalogService.deleteCatalog(param);

		if (result) {
			model.put(Constants.JSON_RESULT, "操作成功！");
		} else {
			model.put(Constants.JSON_RESULT, "操作失败！");
		}

		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  发布操作
	 * Create Date: 2010-8-25上午11:19:32 by yucy  Modified Date: 2010-8-25上午11:19:32 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updatePubStatus")
	public ModelAndView updatePubStatus(String catalogId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Boolean result = purCatalogService.updatePubStatus(catalogId);
		if(result){
			model.put(Constants.JSON_RESULT, "发布成功！");
		}else{
			model.put(Constants.JSON_RESULT, "发布失败！");
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * Description : 是否有详细内容 Create Date: 2010-8-12上午10:23:54 by yucy Modified
	 * Date: 2010-8-12上午10:23:54 by yucy
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=getSubmitConfrim")
	public ModelAndView getIshasAnyDetail(HttpServletRequest request,SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("objId", request.getParameter("objId"));
		Boolean IshasAnyDetail = purCatalogService.getIshasAnyDetail(param);
		model.put("flag", IshasAnyDetail);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
