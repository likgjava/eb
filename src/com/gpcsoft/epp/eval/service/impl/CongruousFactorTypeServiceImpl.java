package com.gpcsoft.epp.eval.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.tree.DHtmlTree;
import com.gpcsoft.core.utils.DHtmlTreeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.eval.dao.CongruousFactorTypeDao;
import com.gpcsoft.epp.eval.domain.CongruousFactor;
import com.gpcsoft.epp.eval.domain.CongruousFactorType;
import com.gpcsoft.epp.eval.manager.CongruousFactorTypeManager;
import com.gpcsoft.epp.eval.service.CongruousFactorTypeService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Service 
public class CongruousFactorTypeServiceImpl extends BaseGenericServiceImpl<CongruousFactorType> implements CongruousFactorTypeService {

	@Autowired(required=true) @Qualifier("congruousFactorTypeManagerImpl")
	private CongruousFactorTypeManager congruousFactorTypeManager;
	
	@Autowired(required=true)  @Qualifier("congruousFactorTypeDaoHibernate")
	private CongruousFactorTypeDao congruousFactorTypeDaoHibernate;
	/**
	 * @Description: 新增指标分类
	 * @param congruousFactorType
	 * @throws Exception
	 * @Create Date 2010-9-7 下午04:22:55 By wanghz
	 */
	public void saveCreateCongruousFactorType(List<CongruousFactorType> congruousFactorTypeList){
		logger.debug("\nCongruousFactorTypeServiceImpl||saveCreateCongruousFactorType\n");
		for(CongruousFactorType congruousFactorType:congruousFactorTypeList){
			congruousFactorType.setCreateOrgId(AuthenticationHelper.getCurrentUser().getOrgInfo().getObjId());
			congruousFactorType.setCreator(AuthenticationHelper.getCurrentUser().getObjId());
			congruousFactorType.setIsLeaf(true);
			if (null != congruousFactorType.getObjId() && "".equals(congruousFactorType.getObjId())) {
				congruousFactorType.setObjId(null);
			}
			congruousFactorTypeManager.save(congruousFactorType);
		}
	}
	
	/**
	 * @Description: 更新指标分类
	 * @param congruousFactorType
	 * @param projectId 项目ID
	 * @return CongruousFactorType
	 * @throws Exception
	 * @Create Date 2010-9-7 下午04:23:06 By wanghz
	 */
	public void updateCongruousFactorType(List<CongruousFactorType> congruousFactorTypeList, String projectId){
		logger.debug("\nCongruousFactorTypeServiceImpl||updateCongruousFactorType\n");
		// 删除指标分类
		congruousFactorTypeManager.removeCongruousFactorType(congruousFactorTypeList,projectId);
		for(CongruousFactorType congruousFactorType:congruousFactorTypeList){
			congruousFactorType.setIsLeaf(true);
			if (null != congruousFactorType.getObjId() && "".equals(congruousFactorType.getObjId())) {
				congruousFactorType.setObjId(null);
				congruousFactorType.setCreateOrgId(AuthenticationHelper.getCurrentUser().getOrgInfo().getObjId());
				congruousFactorType.setCreator(AuthenticationHelper.getCurrentUser().getObjId());
			} else {
				congruousFactorType.setChangeOrgId(AuthenticationHelper.getCurrentUser().getOrgInfo().getObjId());
				congruousFactorType.setChanger(AuthenticationHelper.getCurrentUser().getObjId());
				// 判断当前节点是否有子节点
				QueryObject query = new QueryObjectBase();
				query.setEntityClass(CongruousFactorType.class);
				query.getQueryParam().and(new QueryParam("parent.objId",QueryParam.OPERATOR_EQ,congruousFactorType.getObjId()));
				query.getQueryProjections().setProperty(new String[]{"objId"});
				List list = congruousFactorTypeManager.findByQuery(query);
				if(list.size()>0){
					congruousFactorType.setIsLeaf(false);
				}
			}
			congruousFactorTypeManager.save(congruousFactorType);
		}
	}
	
	
	/**
	 * @Description: 初始化指标分类
	 * @throws Exception
	 * @Create Date 2010-9-17 上午10:54:09 By wanghz
	 */
	public  List<CongruousFactorType> createInitCongruousFactorType(List<CongruousFactorType> congruousFactorTypeList){
		logger.debug("\nCongruousFactorTypeServiceImpl||createInitCongruousFactorType\n");
		congruousFactorTypeManager.save(congruousFactorTypeList);
		return congruousFactorTypeList;
	}
	
	
	/**
	 * @Description: 获取指标分类
	 * @param projectId
	 * @return List<CongruousFactorType>
	 * @throws Exception
	 * @Create Date 2010-9-17 上午11:04:01 By wanghz
	 */
	public List<CongruousFactorType> getCongruousFactorTypeByProjectId(String projectId){
		logger.debug("\n CongruousFactorTypeServiceImpl||getCongruousFactorTypeByProjectId\n");
		return congruousFactorTypeManager.getCongruousFactorTypeByProjectId(projectId);
	}
	
	/** 
	 * Description :  根据选中的结点得到对应的树型结构的XML字符串
	 * Create Date: 2010-2-3下午08:49:44 by liuy  Modified Date: 2010-2-3下午08:49:44 by liuy
	 * @param dhtmlTree
	 * @param objId				选中的对象objId[若不为UUID,则表示是选中的根结点]
	 * @return  XML格式的字符串
	 * @throws Exception
	 */
	public String getXMLStringForCongruousFactorTypeTree(DHtmlTree dhtmlTree, String parentObjId,String projectId) throws Exception {
		List list = new ArrayList();
		list.add(congruousFactorTypeDaoHibernate.getCongruousFactorTypeList(parentObjId,projectId));
		return DHtmlTreeUtils.getTree(dhtmlTree, list);
	}
	/**
	 * FuncName: deleteCongruousFactorTypeTree
	 * Description :  删除指标类别及旗下条目和中间表 
	 * @param 
	 * @param objId
	 * @throws Exception void
	 * @author: shenjz
	 * @Create Date:2011-5-10  下午02:59:20
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-10  下午02:59:20
	 */
	public void deleteCongruousFactorType( String objId) throws Exception {
		congruousFactorTypeDaoHibernate.removeCongruousFactorTypeCascade(objId);
	}
	/**
	 * FuncName: getListForCheck
	 * Description :  根据编号和项目主键校验编号是否唯一
	 * @param 
	 * @param typeCode
	 * @param projectId
	 * @return List<CongruousFactorType>
	 * @author: shenjz
	 * @Create Date:2011-6-2  下午04:09:13
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-2  下午04:09:13
	 */
	public List<CongruousFactorType> getListForCheck(String typeCode,String projectId){
		return congruousFactorTypeDaoHibernate.getListForCheck(typeCode, projectId);
	}

	
	/**
	 * 
	 * FuncName: getChildFactorTypeList
	 * Description : 获取子指标分类列表
	 * @param 
	 * @param parentId
	 * @return List<CongruousFactorType>
	 * @author: liuke
	 * @Create Date:2011-9-6  下午06:16:44
	 * @Modifier: liuke
	 * @Modified Date:2011-9-6  下午06:16:44
	 */
	public List<CongruousFactorType> getChildFactorTypeList(String parentId,String projectId) {
		return congruousFactorTypeDaoHibernate.getCongruousFactorTypeList(parentId,projectId);
	}
	
	/**
	 * FuncName: getListForCheck
	 * Description :  根据项目Id取得所有指标分类
	 * @param 
	 * @param projectId
	 * @return List<CongruousFactorType>
	 * @author: yangyc
	 * @Create Date:2011-11-29  16:50:13
	 * @Modifier: yangyc
	 * @Modified Date:2011-11-29  16:50:13
	 */
	public List<CongruousFactorType> getAllFactorTypeByProjectId(String projectId) {
		return congruousFactorTypeDaoHibernate.getAllFactorTypeByProjectId(projectId);
	}

}

