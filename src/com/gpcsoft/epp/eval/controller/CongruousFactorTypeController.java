package com.gpcsoft.epp.eval.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.tree.DHtmlTree;
import com.gpcsoft.core.tree.TreeProperty;
import com.gpcsoft.core.utils.DHtmlTreeUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.eval.domain.CongruousFactorEnum;
import com.gpcsoft.epp.eval.domain.CongruousFactorType;
import com.gpcsoft.epp.eval.service.CongruousFactorTypeService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="congruousFactorTypeFormView"
  *  url="view/es/planform/eval/congruousFactorTypeForm.jsp"
  * @gpcsoft.view value="updateCongruousFactorTypeFormView"
  *  url="view/es/planform/eval/updateCongruousFactorTypeForm.jsp"
  * @gpcsoft.view value="updateCongruousFactorTypeDetailView"
  *  url="view/es/planform/eval/updateCongruousFactorTypeForm2.jsp"
  * @gpcsoft.view value="congruousFactorTypeTreeFormView"
  *  url="view/es/planform/eval/congruousFactorTypeTreeForm.jsp"
  * @gpcsoft.view value="congruousFactorTypeDetailView"
  *  url="view/es/planform/eval/congruousFactorTypeDetail.jsp"
  * @gpcsoft.view value="createCongruousFactorTypeListView"
  *  url="view/es/planform/eval/congruousFactorTypeList.jsp"
  * @gpcsoft.view value="updateCongruousFactorTypeListView"
  *  url="view/es/planform/eval/updateCongruousFactorTypeList.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={CongruousFactorType.class})
@RequestMapping("/CongruousFactorTypeController.do")//页面请求路径,可修改
public class CongruousFactorTypeController extends AnnotationMultiController<CongruousFactorType> {
	
	@Autowired(required=true) @Qualifier("congruousFactorTypeServiceImpl")
	private CongruousFactorTypeService congruousFactorTypeService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	/**
	 * @Description: 保存指标分类
	 * @param congruousFactorType
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-8-2 下午02:42:22 By wanghz
	 */
	@RequestMapping(params = "method=createCongruousFactorType")
	public ModelAndView createCongruousFactorType(String congruousFactorTypes,SessionStatus status)throws Exception {
		logger.debug("\nCongruousFactorTypeController||createCongruousFactorType\n");
		Map<String, String> model=new HashMap<String, String>();
		JSONArray jsonArray = JSONArray.fromObject(congruousFactorTypes);
		if(null != jsonArray && (!jsonArray.isEmpty()) ){
			List<CongruousFactorType> congruousFactorTypeList = new ArrayList<CongruousFactorType>(1);
			for(int i=0;i<jsonArray.size();i++){
				JSONObject jSONObject = (JSONObject)jsonArray.get(i);
				CongruousFactorType congruousFactorType = (CongruousFactorType)JSONObject.toBean(jSONObject,CongruousFactorType.class);
				congruousFactorTypeList.add(congruousFactorType);
			}
			congruousFactorTypeService.saveCreateCongruousFactorType(congruousFactorTypeList);
		}
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * @Description: 更新指标分类
	 * @param congruousFactorType
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-8-2 下午02:42:22 By wanghz
	 */
	@RequestMapping(params = "method=updateCongruousFactorType")
	public ModelAndView updateCongruousFactorType(String congruousFactorTypes,SessionStatus status,String projectId)throws Exception {
		logger.debug("\nCongruousFactorTypeController||updateCongruousFactorType\n");
		Map<String, String> model=new HashMap<String, String>();
		JSONArray jsonArray = JSONArray.fromObject(congruousFactorTypes);
		if(null != jsonArray && (!jsonArray.isEmpty()) ){
			List<CongruousFactorType> congruousFactorTypeList = new ArrayList<CongruousFactorType>(1);
			for(int i=0;i<jsonArray.size();i++){
				JSONObject jSONObject = (JSONObject)jsonArray.get(i);
				CongruousFactorType congruousFactorType = (CongruousFactorType)JSONObject.toBean(jSONObject,CongruousFactorType.class);
				congruousFactorTypeList.add(congruousFactorType);
			}
			congruousFactorTypeService.updateCongruousFactorType(congruousFactorTypeList,projectId);
		}
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,model);
	}

	
	@Override
	public ModelAndView toList(HttpServletRequest request) throws Exception {// TODO By wanghz 待修改方法名
		logger.debug("\nes CongruousFactorTypeController||toList\n");
		Map<String, Object> model=new HashMap<String, Object>();
		String viewName = "";
		String projectId = request.getParameter("projectId");
		Project project = (Project)projectService.getProjectByObjId(projectId);
		List<CongruousFactorType> congruousFactorTypeList = congruousFactorTypeService.getCongruousFactorTypeByProjectId(projectId);
		if (null == congruousFactorTypeList || congruousFactorTypeList.isEmpty()) {		// 跳转到新增页面
			viewName = "createCongruousFactorTypeListView";
			congruousFactorTypeList = this.initAccordCongruousFactorType(projectId);			// 组装初始化指标分类数据
			congruousFactorTypeList.addAll(this.initCongruousFactorType(projectId,2L));
		} else {// 跳转到修改页面
			viewName = "updateCongruousFactorTypeListView";
			congruousFactorTypeList.addAll(this.initCongruousFactorType(projectId,congruousFactorTypeList.size()+1L));
		}
		
		model.put("project", project);
		model.put("congruousFactorTypeList", congruousFactorTypeList);
		return new ModelAndView(viewName,model);
	}
	
	/**
	 * @Description: 初始化符合性指标分类
	 * @param projectId
	 * @return void
	 * @throws Exception
	 * @Create Date 2010-9-19 上午09:17:36 By wanghz
	 */
	private List<CongruousFactorType> initAccordCongruousFactorType(String projectId) {
		logger.debug("\nCongruousFactorTypeController||initAccordCongruousFactorType\n");
		List<CongruousFactorType> congruousFactorTypeList = new ArrayList<CongruousFactorType>(1);
		congruousFactorTypeList = new ArrayList<CongruousFactorType>(1);
		CongruousFactorType congruousFactorType = new CongruousFactorType();
		CongruousFactorType congruousFactorType2 = new CongruousFactorType();
		CongruousFactorType congruousFactorType3 = new CongruousFactorType();
		User user = AuthenticationHelper.getCurrentUser();
		congruousFactorType.setSort(1L);
		congruousFactorType.setProjId(projectId);
		congruousFactorType.setTypeCode(CongruousFactorEnum.FIRSTCHECK);
		congruousFactorType.setTypeName("初审指标");
		congruousFactorType.setCreateTime(new java.util.Date());
		congruousFactorType.setCreateOrgId(user.getOrgInfo().getObjId());
		congruousFactorType.setCreator(user.getObjId());
		congruousFactorType.setIsLeaf(true);
		congruousFactorTypeList.add(congruousFactorType);
		congruousFactorType2.setSort(2L);
		congruousFactorType2.setProjId(projectId);
		congruousFactorType2.setTypeCode(CongruousFactorEnum.RECHECK);
		congruousFactorType2.setTypeName("复审指标");
		congruousFactorType2.setCreateTime(new java.util.Date());
		congruousFactorType2.setCreateOrgId(user.getOrgInfo().getObjId());
		congruousFactorType2.setCreator(user.getObjId());
		congruousFactorType2.setIsLeaf(true);
		congruousFactorTypeList.add(congruousFactorType2);
		congruousFactorType3.setSort(3L);
		congruousFactorType3.setProjId(projectId);
		congruousFactorType3.setTypeCode(CongruousFactorEnum.PRICECHECK);
		congruousFactorType3.setTypeName("价格指标");
		congruousFactorType3.setCreateTime(new java.util.Date());
		congruousFactorType3.setCreateOrgId(user.getOrgInfo().getObjId());
		congruousFactorType3.setCreator(user.getObjId());
		congruousFactorType3.setIsLeaf(true);
		congruousFactorTypeList.add(congruousFactorType3);
		congruousFactorTypeList = congruousFactorTypeService.createInitCongruousFactorType(congruousFactorTypeList);
		return congruousFactorTypeList;
	}
	
	/**
	 * @Description: 初始化指标分类
	 * @param projectId
	 * @return List<CongruousFactorType>
	 * @throws Exception
	 * @Create Date 2010-9-17 上午11:48:38 By wanghz
	 */
	private List<CongruousFactorType> initCongruousFactorType(String projectId,Long sort) {
		logger.debug("\nCongruousFactorTypeController||initCongruousFactorType\n");
		List<CongruousFactorType> congruousFactorTypeList = new ArrayList<CongruousFactorType>(1);
		CongruousFactorType congruousFactorType1 = new CongruousFactorType();
		congruousFactorType1.setSort(sort);
		congruousFactorType1.setProjId(projectId);
		congruousFactorTypeList.add(congruousFactorType1);
		CongruousFactorType congruousFactorType2 = new CongruousFactorType();
		congruousFactorType2.setSort((sort+1));
		congruousFactorType2.setProjId(projectId);
		congruousFactorTypeList.add(congruousFactorType2);
		CongruousFactorType congruousFactorType3 = new CongruousFactorType();
		congruousFactorType3.setSort((sort+2));
		congruousFactorType3.setProjId(projectId);
		congruousFactorTypeList.add(congruousFactorType3);
		return congruousFactorTypeList;
	}
	
	/**
	 * FuncName: getCongruousFactorTypeTree
	 * Description :  根据指标分类获取指标(树形结构)
	 * @param request
	 * @param id
	 * @param dHtmlTree
	 * @return ModelAndView
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-9  上午11:09:12
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-9  上午11:09:12
	 */
	@RequestMapping(params = "method=getCongruousFactorTypeTree")
	public ModelAndView getCongruousFactorTypeTree(HttpServletRequest request, String id, DHtmlTree dHtmlTree)throws Exception {
		logger.debug("\nCongruousFactorTypeController||getCongruousFactorTypeTree\n");
		TreeProperty treeProperty=DHtmlTreeUtils.getTreeProperty(CongruousFactorType.class);
		Map<String, String> model=new HashMap<String, String>();
		dHtmlTree.setTreeName(treeProperty.title());
		String projectId = request.getParameter("projectId");
		dHtmlTree.setId((id==null || "".equals(id) || "undefined".equals(id))?"0":id);
		//显示树结果数据
		if("0".equals(dHtmlTree.getId())){
			dHtmlTree.setAction("listTop");
		}
		model.put(Constants.STRING_RESULT,congruousFactorTypeService.getXMLStringForCongruousFactorTypeTree(dHtmlTree, id,projectId));
		return new ModelAndView(Constants.STRING_VIEW,model);
	}
	/**
	 * FuncName: getCongruousFactorTypeTree
	 * Description :  创建或修改对象
	 * @param 
	 * @param request
	 * @param id
	 * @param dHtmlTree
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-9  下午05:42:45
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-9  下午05:42:45
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getCongruousFactorTypeById")
	public ModelAndView getCongruousFactorTypeById(HttpServletRequest request)throws Exception {
		Map  model = new HashMap();
		model.put("CongruousFactorType",congruousFactorTypeService.get(request.getParameter("objId")));
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	/**
	 * FuncName: getCongruousFactorTypeTree
	 * Description :  创建或修改对象
	 * @param 
	 * @param request
	 * @param id
	 * @param dHtmlTree
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-9  下午05:42:45
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-9  下午05:42:45
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getParentId")
	public ModelAndView getParentId(HttpServletRequest request)throws Exception {
		Map  model = new HashMap();
		CongruousFactorType congruousFactorType  = congruousFactorTypeService.get(request.getParameter("objId"));
		model.put("parentId", "");
		if(congruousFactorType.getParent()!=null){
			model.put("parentId",congruousFactorType.getParent().getObjId());
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	/**
	 * FuncName: getCongruousFactorTypeTree
	 * Description :  创建或修改对象
	 * @param 
	 * @param request
	 * @param id
	 * @param dHtmlTree
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-9  下午05:42:45
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-9  下午05:42:45
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=saveCongruousFactorType")
	public ModelAndView saveCongruousFactorType(CongruousFactorType congruousFactorType)throws Exception {
		Map  model = new HashMap();
		if(null!=congruousFactorType.getParent()){
			CongruousFactorType supCongruousFactorType = congruousFactorTypeService.get(congruousFactorType.getParent().getObjId());
			supCongruousFactorType.setIsLeaf(false);
			congruousFactorTypeService.save(supCongruousFactorType);
			short t=supCongruousFactorType.getLevel();
			congruousFactorType.setLevel((short)(t+1));
		}else{
			congruousFactorType.setLevel((short) 1);
		}
		congruousFactorTypeService.save(congruousFactorType);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	/**
	 * FuncName: removeCongruousFactorType
	 * Description :  删除指标类别对象
	 * @param 
	 * @param congruousFactorType
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-10  下午12:00:47
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-10  下午12:00:47
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=removeCongruousFactorType")
	public ModelAndView removeCongruousFactorType(String objId)throws Exception {
		Map  model = new HashMap();
		CongruousFactorType congruousFactorType = congruousFactorTypeService.get(objId);
		List<CongruousFactorType> childCongruousFactorTypeList = congruousFactorTypeService.getChildFactorTypeList(congruousFactorType.getObjId(), congruousFactorType.getProjId());
		if(null!=childCongruousFactorTypeList&&!childCongruousFactorTypeList.isEmpty()){ //有子指标分类
			model.put("result", "请先删除下级指标分类！");
		}else{
			congruousFactorTypeService.deleteCongruousFactorType(objId);
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	/**
	 * FuncName: removeCongruousFactorType
	 * Description : 到达新增指标类别对象页面
	 * @param 
	 * @param congruousFactorType
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-10  下午12:00:47
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-10  下午12:00:47
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toAddCongruousFactorType")
	public ModelAndView toAddCongruousFactorType(String objId,HttpServletRequest request)throws Exception {
		Map  model = new HashMap();
		String parentId = request.getParameter("parentId");
		CongruousFactorType congruousFactorType = congruousFactorTypeService.get(parentId);
		String projectId=request.getParameter("projectId");
		model.put("supCongruousFactorType", congruousFactorType);
		model.put("projectId", projectId);
		return new ModelAndView("congruousFactorTypeFormView",model);
	}
	/**
	 * FuncName: removeCongruousFactorType
	 * Description : 到达修改指标类别对象页面
	 * @param 
	 * @param congruousFactorType
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-10  下午12:00:47
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-10  下午12:00:47
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toUpdateCongruousFactorType")
	public ModelAndView toUpdateCongruousFactorType(String objId,HttpServletRequest request)throws Exception {
		Map  model = new HashMap();
		CongruousFactorType congruousFactorType = congruousFactorTypeService.get(objId);
		model.put("congruousFactorType", congruousFactorType);
		return new ModelAndView("updateCongruousFactorTypeFormView",model);
	}
	/**
	 * FuncName: removeCongruousFactorType
	 * Description : 到达指标类别详情页面
	 * @param 
	 * @param congruousFactorType
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-10  下午12:00:47
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-10  下午12:00:47
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toCongruousFactorTypeDetail")
	public ModelAndView toCongruousFactorTypeDetail(String objId,HttpServletRequest request)throws Exception {
		Map  model = new HashMap();
		CongruousFactorType congruousFactorType = congruousFactorTypeService.get(objId);
		model.put("congruousFactorType", congruousFactorType);
		return new ModelAndView("updateCongruousFactorTypeDetailView",model);
	}
	/**
	 * FuncName: saveCongruousFactorTypeOther
	 * Description :  保存指标类型额外方法
	 * @param 
	 * @param congruousFactorType
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-28  下午04:30:16
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-28  下午04:30:16
	 */
	@RequestMapping(params = "method=saveCongruousFactorTypeOther")
	public ModelAndView saveCongruousFactorTypeOther(CongruousFactorType congruousFactorType)throws Exception {
		Map  model = new HashMap();
		congruousFactorType.setObjId(null);
		String typeCode = UUID.randomUUID().toString();
		congruousFactorType.setTypeCode(typeCode);//编号
		if(null!=congruousFactorType.getParent()){
			CongruousFactorType supCongruousFactorType = congruousFactorTypeService.get(congruousFactorType.getParent().getObjId());
			supCongruousFactorType.setIsLeaf(false);
			congruousFactorTypeService.save(supCongruousFactorType);
		}
		congruousFactorTypeService.save(congruousFactorType);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	/**
	 * FuncName: checkTypecodeUnique
	 * Description :  创建或修改对象
	 * @param 
	 * @param congruousFactorType
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-6-2  下午04:04:25
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-2  下午04:04:25
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=checkTypecodeUnique")
	public ModelAndView checkTypecodeUnique(HttpServletRequest request)throws Exception {
		Map  model = new HashMap();
		String typeCode = request.getParameter("typeCode");
		String projectId = request.getParameter("projectId");
		List<CongruousFactorType> typeList = congruousFactorTypeService.getListForCheck(typeCode, projectId);
		String objId = request.getParameter("objId");
		if(typeList.size()==0){
			model.put("notUnique", "false");
		}else{
			if(objId!=null&&typeList.size()==1){
				CongruousFactorType congruousFactorType = typeList.get(0);
				CongruousFactorType congruousFactorType2 = congruousFactorTypeService.get(objId);
				if(congruousFactorType.getObjId().equals(objId)&&congruousFactorType.getTypeCode().equals(congruousFactorType2.getTypeCode())){
					model.put("notUnique", "false");
				}else{
					model.put("notUnique", "true");
				}
			}else{
				model.put("notUnique", "true");
			}
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
}
