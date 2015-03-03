package com.gpcsoft.bizplatform.organization.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.domain.OrgQuality;
import com.gpcsoft.core.service.BaseGenericService;

public interface OrgQualityService extends BaseGenericService<OrgQuality> {

	 /**
     * Description :获取资质分类下资质信息 （资质分类下联动菜单数据拼装）
     * Create Date: 2010-5-28 by liqy  Modified Date: 2010-5-28 by liqy
     * @param defindId 
     * @param   
     * @return  
     * @Exception
     */
	public List<Object> getQualityGradesSelect(String defindId, List<Object> modelReturnList)throws Exception;

	/**
     * Description :资质信息对象唯一性判断
     * Create Date: 2010-6-1 by liqy  Modified Date: 2010-6-1 by liqy
     * @param   objectId所属对象ID, quaGradesId资质定义信息ID,objectName所属对象名称
     * @return  存在返回true 不存在返回false
     * @Exception
     */
	public Boolean isUniqueQualification(String objectId, String quaGradesId,String objectName)throws Exception;
	
	/**
     * Description :保存资质信息 
     * Create Date: 2010-5-31 by liqy  Modified Date: 2010-5-31 by yucy
     * @param   
     * @return  
     * @Exception
     */
	public Boolean saveQualificationInfo(OrgQuality orgQuality,Map<String, Object> param)throws Exception;

	/** 
	 * Description :  删除资质信息 (草稿和不通过可删)
	 * Create Date: 2010-8-3上午01:24:49 by yucy  Modified Date: 2010-8-3上午01:24:49 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean removeQualificationInfo(String objId)throws Exception;


	/** 
	 * Description : 取得机构信息 
	 * Create Date: 2010-8-2下午09:54:36 by yucy  Modified Date: 2010-8-2下午09:54:36 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getQualityOrgInfo(String objId)throws Exception;

	/** 
	 * Description :  审核资质信息
	 * Create Date: 2010-8-3上午01:23:35 by yucy  Modified Date: 2010-8-3上午01:23:35 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean saveAuditStatus(Map<String, Object> param);

	/** 
	 * Description :  取得以保存的资质（组织内部对象,包括新添加的参数）
	 * Create Date: 2010-8-30上午11:37:11 by yucy  Modified Date: 2010-8-30上午11:37:11 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getCreateOrUpdateOrgQuality(String parameter) throws Exception;

	/** 
	 * Description :  取得机构的资质(根据机构id)
	 * Create Date: 2009-4-8下午07:50:19 by yucy  Modified Date: 2009-4-8下午07:50:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<OrgQuality> getOrgQuality(String objId) throws Exception;
}
