package com.gpcsoft.bizplatform.organization.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.dao.OrgQualityDao;
import com.gpcsoft.bizplatform.organization.domain.OrgQuality;
import com.gpcsoft.bizplatform.organization.manager.OrgQualityManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class OrgQualityManagerImpl extends BaseManagerImpl<OrgQuality> implements OrgQualityManager {

	@Autowired(required=true)  @Qualifier("orgQualityDaoHibernate")
	private OrgQualityDao orgQualityDaoHibernate;
	
	/**
     * Description :获取资质分类下资质信息 （资质分类下联动菜单数据拼装）
     * Create Date: 2010-5-28 by liqy  Modified Date: 2010-5-28 by liqy
     * @param   
     * @return  
     * @Exception
     */
	public List<Object> getQualityGradesSelect(String defindId,List<Object> modelReturnList)throws Exception{
		
/*		List<QualityGrades> qualityGradesList=qualityGradesDaoHibernate.findbyHql(" FROM QualityGrades t where t.qualityDefine.objId=? ", defindId);
		if(!qualityGradesList.isEmpty()){
			for(QualityGrades list : qualityGradesList){
				Map<String, Object> modelTemp = new HashMap<String, Object>();
				modelTemp.put("objId", list.getObjId());
				modelTemp.put("name", list.getGradesName());
				modelTemp.put("qualityType", list.getQualityDefine().getQualityType_value());
				modelReturnList.add(modelTemp);
			}
		}		
		return modelReturnList;		*/
		return null;
	}
	
	/**
     * Description :根据条件获取资质信息对象
     * Create Date: 2010-6-1 by liqy  Modified Date: 2010-6-1 by liqy
     * @param   objectId所属对象ID, quaGradesId资质定义信息ID,objectName所属对象名称,verNo版本号
     * @return  存在返回true 不存在返回false
     * @Exception
     */
	public List<OrgQuality> getQualificationList(String objectId,String quaGradesId,String objectName,String verNo) throws Exception{
		List<OrgQuality> qualificationList = orgQualityDaoHibernate.findbyHql(" from Qualification t where t.belongObjectId=? and t.qualityGrades.objId=? and t.belongObjectName=? and t.verNo=? ", objectId,quaGradesId,objectName,verNo);
		if(!qualificationList.isEmpty())return qualificationList;
		else return null;				
	}
}
