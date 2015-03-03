package com.gpcsoft.epp.eval.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.tree.DHtmlTree;
import com.gpcsoft.epp.eval.domain.CongruousFactorType;

public interface CongruousFactorTypeService extends BaseGenericService<CongruousFactorType> {

	/**
	 * @Description: 新增指标分类
	 * @param congruousFactorType
	 * @throws Exception
	 * @Create Date 2010-9-7 下午04:22:55 By wanghz
	 */
	public void saveCreateCongruousFactorType(List<CongruousFactorType> congruousFactorTypeList);
	
	/**
	 * @Description: 更新指标分类
	 * @param projectId 项目ID
	 * @param congruousFactorType
	 * @return CongruousFactorType
	 * @throws Exception
	 * @Create Date 2010-9-7 下午04:23:06 By wanghz
	 */
	public void updateCongruousFactorType(List<CongruousFactorType> congruousFactorTypeList, String projectId);
	
	/**
	 * @Description: 删除指标分类
	 * @param objId
	 * @return void
	 * @throws Exception
	 * @Create Date 2010-9-7 下午11:38:29 By wanghz
	 */
	
	/** 
	 * Description :  根据objId查看指标分类
	 * Create Date: 2010-9-15下午04:11:39 by yangx  Modified Date: 2010-9-15下午04:11:39 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	
	/**
	 * @Description: 初始化指标分类
	 * @throws Exception
	 * @return List<CongruousFactorType>
	 * @Create Date 2010-9-17 上午10:54:09 By wanghz
	 */
	public  List<CongruousFactorType> createInitCongruousFactorType(List<CongruousFactorType> congruousFactorTypeList);
	
	/**
	 * @Description: 获取指标分类
	 * @param projectId
	 * @return List<CongruousFactorType>
	 * @throws Exception
	 * @Create Date 2010-9-17 上午11:04:01 By wanghz
	 */
	public List<CongruousFactorType> getCongruousFactorTypeByProjectId(String projectId);
	
	/** 
	 * Description :  根据选中的结点得到对应的树型结构的XML字符串
	 * Create Date: 2010-2-3下午08:49:44 by liuy  Modified Date: 2010-2-3下午08:49:44 by liuy
	 * @param dhtmlTree
	 * @param objId				选中的对象objId[若不为UUID,则表示是选中的根结点]
	 * @return  XML格式的字符串
	 * @throws Exception
	 */
	public String getXMLStringForCongruousFactorTypeTree(DHtmlTree dhtmlTree, String parentObjId,String projectId) throws Exception;
	
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
	public void deleteCongruousFactorType( String objId) throws Exception ;
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
	public List<CongruousFactorType> getListForCheck(String typeCode,String projectId);
	
	
	
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
	public List<CongruousFactorType> getChildFactorTypeList(String parentId,String projectId);
	
	
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
	public List<CongruousFactorType> getAllFactorTypeByProjectId(String projectId);
}
