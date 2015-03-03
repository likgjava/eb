package com.gpcsoft.bizplatform.organization.manager;

import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.OrgQuality;
import com.gpcsoft.core.manager.BaseManager;

public interface OrgQualityManager extends BaseManager<OrgQuality> {
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
     * Description :根据条件获取资质信息对象
     * Create Date: 2010-6-1 by liqy  Modified Date: 2010-6-1 by liqy
     * @param   objectId所属对象ID, quaGradesId资质定义信息ID,objectName所属对象名称,verNo版本号
     * @return  存在返回true 不存在返回false
     * @Exception
     */
	public List<OrgQuality> getQualificationList(String objectId,String quaGradesId,String objectName,String verNo) throws Exception;
}
