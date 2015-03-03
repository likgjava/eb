package com.gpcsoft.agreement.management.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.management.domain.Area;
import com.gpcsoft.agreement.management.enumeration.AgreementEnum;
import com.gpcsoft.agreement.management.service.AreaService;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.tree.DHtmlTree;
import com.gpcsoft.core.utils.BeanUtils;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="areaFormView"
  *  url="view/agreement/management/areaForm.jsp"
  * @gpcsoft.view value="areaTreeFormView"
  *  url="view/agreement/management/areaTreeForm.jsp"
  * @gpcsoft.view value="areaListView"
  *  url="view/agreement/mamagement/agreement_area_list.jsp"
  * @gpcsoft.view value="areaDetailView"
  *  url="view/agreement/management/areaDetail.jsp"
  * @gpcsoft.view value="toDistrictTreeView"
  *  url="view/agreement/mamagement/agreement_area_form.jsp"
  * @gpcsoft.view value="toAreaTreeChooseView"
  *  url="view/agreement/mamagement/agreement_area_choose.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Area.class})
@RequestMapping("/AreaController.do")//页面请求路径,可修改
public class AreaController extends AnnotationMultiController<Area> {

	@Autowired(required=true) @Qualifier("areaServiceImpl")
	private AreaService areaService;
	
	/** 
	 * Description :  跳转到区间列表
	 * Create Date: 2011-12-5上午12:27:23 by yucy  Modified Date: 2011-12-5上午12:27:23 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAreaList")   
	public ModelAndView toAreaList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String,  Object>();   
		//取得无效的数据
		List<Object[]> inValidList = areaService.getAreaList(AgreementEnum.AREA_INVALID);
		String inValidListStr = "";
		for(Object[] inValidIds :inValidList){
			inValidListStr += StringUtils.hasLength(inValidListStr)?","+(String)inValidIds[0]:(String)inValidIds[0];
		}
		model.put("inValidListStr", inValidListStr);
		//取得删除的数据
		List<Object[]> delteList = areaService.getAreaList(AgreementEnum.AREA_DELETED);
		String delteListStr = "";
		for(Object[] delteIds :delteList){
			delteListStr += StringUtils.hasLength(delteListStr)?","+(String)delteIds[0]:(String)delteIds[0];
		}
		model.put("delteListStr", delteListStr);
		return new ModelAndView("areaListView",model);
	}

	
	/** 
	 * Description :  保存区间
	 * Create Date: 2010-4-18下午10:24:28 by yucy  Modified Date: 2010-4-18下午10:24:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveArea")   
	public ModelAndView saveArea(HttpServletRequest request, Area area) throws Exception {
		Map<String, String> model = new HashMap<String, String>();   
		if(null==area.getObjId())
		{
			area.setIsLeaf(true);
			area.setSort(new Long(0));
		}
		String parentId = request.getParameter("parent.objId");
		if(!"".equals(parentId)&&null!=parentId){
			Area parent = areaService.get(parentId);
			parent.setIsLeaf(false);
			area.setParent(parent);
		}
		areaService.save(area);
		model.put("objId", area.getObjId());
		model.put(Constants.JSON_RESULT, "保存成功!");
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description :  保存区间(beanCopy)
	 * Create Date: 2010-4-18下午10:24:28 by yucy  Modified Date: 2010-4-18下午10:24:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveAreaByBeanCopy")   
	public ModelAndView saveAreaByBeanCopy(HttpServletRequest request, String objStr) throws Exception {
		Map<String, String> model = new HashMap<String, String>();   
		Area area = JsonUtils.json2Bean(objStr, Area.class);
		
		if(null!=area){
			if(null==area.getObjId()||"".equals(area.getObjId())){
				area.setObjId(null);
				area.setIsLeaf(true);
				area.setSort(new Long(0));
				areaService.save(area);
			}else {
				Area areaBefore = areaService.get(area.getObjId());
				BeanUtils.copyPropertiesFilterEmptyNew(areaBefore, area);
				areaService.save(areaBefore);
			}
			Area parent = areaService.get(area.getParent().getObjId());
			parent.setIsLeaf(false);
			areaService.save(parent);
			model.put("objId", area.getObjId());
			model.put(Constants.JSON_RESULT, "保存成功!");
		}else{
			model.put(Constants.FAILURE, "修改失败!");
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description :  保存区间(批量)
	 * Create Date: 2010-4-18下午10:24:28 by yucy  Modified Date: 2010-4-18下午10:24:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveAreaBatch")   
	public ModelAndView saveAreaBatch(HttpServletRequest request, String validArea, String invalidArea) throws Exception {
		Map<String, String> model = new HashMap<String, String>();   
		Map<String, Object> param = new HashMap<String, Object>();   
		param.put("validArea", validArea);
		param.put("invalidArea", invalidArea);
		areaService.saveAreaBatch(param);
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description :  删除区间(安全删除)
	 * Create Date: 2010-4-17下午02:30:03 by yucy  Modified Date: 2010-4-17下午02:30:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=delArea")
	public ModelAndView delArea(HttpServletRequest request) throws Exception {
		Map<String, String> model = new HashMap<String, String>();   
		String areaId = request.getParameter("areaId");
		int result = areaService.removeArea(areaId);
		if(result>=0){
			model.put(Constants.JSON_RESULT, "删除成功!");
		}else{
			model.put(Constants.JSON_RESULT, "删除失败,所选区期间使用中!");
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  区分新增和修改
	 * Create Date: 2010-5-18下午06:08:21 by yucy  Modified Date: 2010-5-18下午06:08:21 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreate")
	public ModelAndView toCreate(HttpServletRequest request,String objId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Area area = new Area();
		if(null!=objId){
			area = areaService.get(objId);
//			Area parent = (Area) HibernateUtils.getObjectFromHibernateProxy(area.getParent());
//			area.setParent(parent);
		}
		model.put("area", area);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  取到当前的Area综合信息(它的父id,可否修改,可否删除等)
	 * Create Date: 2010-5-20上午10:48:19 by yucy  Modified Date: 2010-5-20上午10:48:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getAreaInfo")
	public ModelAndView getAreaInfo(HttpServletRequest request,String objId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Map<String, Object> params = new HashMap<String, Object>();   
		params.put("objId", objId);
		Map<String,Object> areaInfo = areaService.getAreaInfo(params);
		Area area = (Area) areaInfo.get("area");
		Boolean isModify = (Boolean)areaInfo.get("isModify");
		Boolean isDelete = (Boolean)areaInfo.get("isDelete");
		
		model.put("area", area);
		model.put("isModify", isModify);
		model.put("isDelete", isDelete);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	
    /** 
     * Description :  获得区间树
     * Create Date: 2010-5-24上午01:11:12 by yucy  Modified Date: 2010-5-24上午01:11:12 by yucy
     * @param   
     * @return  
     * @Exception   
     */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getOwnerAreaTree")
    public ModelAndView getOwnerAreaTree(HttpServletRequest request, DHtmlTree dHtmlTree) throws Exception{
		Map<String ,Object> param = new HashMap<String ,Object>();
		param.put("isValid", request.getParameter("isValid"));
		String id = request.getParameter("id");
    	dHtmlTree.setTreeName("协议区间");
    	Map model = new HashMap();
    	model.put("string", areaService.getOwnerAreaTree(dHtmlTree,id ,param));
    	return new ModelAndView(Constants.STRING_VIEW, model);
    }
	
    /** 
     * Description :  获得区域树
     * Create Date: 2010-5-24上午01:11:12 by yucy  Modified Date: 2010-5-24上午01:11:12 by yucy
     * @param   
     * @return  
     * @Exception   
     */
	@RequestMapping(params = "method=toDistrictTree")
    public ModelAndView toDistrictTree(HttpServletRequest request, DHtmlTree dHtmlTree) throws Exception{
		Map<String ,Object> model = new HashMap<String ,Object>();
		//获得合法区间的区域
		List<Object[]> validList = areaService.getAreaList(AgreementEnum.AREA_VALID);
		String validListStr = "";
		for(Object[] validIds :validList){
			validListStr += StringUtils.hasLength(validListStr)?","+(String)validIds[1]:(String)validIds[1];
		}
		
		//获得不合法区间的区域
		List<Object[]> inValidList = areaService.getAreaList(AgreementEnum.AREA_INVALID);
		String inValidListStr = "";
		for(Object[] inValidIds :inValidList){
			inValidListStr += StringUtils.hasLength(inValidListStr)?","+(String)inValidIds[1]:(String)inValidIds[1];
		}
		
		model.put("validListStr", validListStr);
		model.put("inValidListStr", inValidListStr);
    	return new ModelAndView("toDistrictTreeView", model);
    }
	
    /** 
     * Description :  获得区间树（选择框）
     * Create Date: 2010-5-24上午01:11:12 by yucy  Modified Date: 2010-5-24上午01:11:12 by yucy
     * @param   
     * @return  
     * @Exception   
     */
	@RequestMapping(params = "method=toAreaTreeChoose")
    public ModelAndView toAreaTreeChoose(HttpServletRequest request, DHtmlTree dHtmlTree) throws Exception{
		Map<String ,Object> model = new HashMap<String ,Object>();
		//取得无效的数据
		List<Object[]> inValidList = areaService.getAreaList(AgreementEnum.AREA_INVALID);
		String inValidListStr = "";
		for(Object[] inValidIds :inValidList){
			inValidListStr += StringUtils.hasLength(inValidListStr)?","+(String)inValidIds[0]:(String)inValidIds[0];
		}
		model.put("inValidListStr", inValidListStr);
    	return new ModelAndView("toAreaTreeChooseView", model);
    }
}
