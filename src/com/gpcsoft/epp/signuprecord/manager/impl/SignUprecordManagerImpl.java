package com.gpcsoft.epp.signuprecord.manager.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.project.dao.ProjectDao;
import com.gpcsoft.epp.signuprecord.dao.SignUprecordDao;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.manager.SignUprecordManager;

@Repository
@SuppressWarnings(value="unchecked")
public class SignUprecordManagerImpl extends BaseManagerImpl<SignUprecord> implements SignUprecordManager {

	@Autowired(required=true)  @Qualifier("signUprecordDaoHibernate")
	private SignUprecordDao signUprecordDaoHibernate;

	@Autowired(required=true)  @Qualifier("projectDaoHibernate")
	private ProjectDao projectDaoHibernate;
	/**
	 *  根据查询条件获取供应商报名列表
	 */
	public Page getSignUprecordByQueryObject(QueryObject queryObject,boolean cache,int start,int pageSize) {
		Page page=signUprecordDaoHibernate.findByQuery(queryObject, true, start, pageSize);
		return page;
	}

	/**
	 * 保存供应商报名
	 */
	public void saveSignUprecord(SignUprecord signUprecord) {
			signUprecordDaoHibernate.save(signUprecord);
	}
	
	/**
	 * 
	 * FuncName: getSignUprecord
	 * Description :  根据QueryObject对象查询报名信息
	 * @param 
	 * @param queryObject
	 * @return SignUprecord
	 * @author: liuke
	 * @Create Date:2010-12-31  上午11:36:16
	 * @Modifier: liuke
	 * @Modified Date:2010-12-31  上午11:36:16
	 */
	public SignUprecord getSignUprecord(QueryObject queryObject) {
		StringBuffer hql = new StringBuffer();
		hql.append("from SignUprecord t where 1=1  ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				/** 项目 */
				if("project.objId".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and (t.project.objId = '"+(String)queryParam.getValue()+"'");
					hql.append(" or t.project.parentId = '"+(String)queryParam.getValue()+"') ");
				}
				/** 供应商 */
				if("supplier.objId".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.supplier.objId = '"+(String)queryParam.getValue()+"'");
				}
			}
		}
		List list = signUprecordDaoHibernate.findbyHql(hql.toString());
		return (null!=list&&!list.isEmpty())?(SignUprecord)list.get(0):null;
	}

	/** 
	 * Description :  获取供应商报名信息列表 
	 * Create Date: 2010-6-23下午03:27:02 by yangx  Modified Date: 2010-6-23下午03:27:02 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<SignUprecord> searchSignUprecordByQueryObject(Page<SignUprecord> page,QueryObject queryObject){
		return signUprecordDaoHibernate.searchSignUprecordByQueryObject(page,queryObject);
	}
	/** 
	 * Description :  根据查询条件统计对应的报名信息数据
	 * Create Date: 2010-7-10上午10:57:34 by yangx  Modified Date: 2010-7-10上午10:57:34 by yangx
	 * @param   queryObject[auditStatus:审核状态;managerID:项目负责人]
	 * @return  
	 * @Exception   
	 */
	public BigDecimal getSignUprecordTotalByQueryObject(QueryObject queryObject){
		return signUprecordDaoHibernate.getSignUprecordTotalByQueryObject(queryObject);
	}
	/**
	 * Description :  用于判断代理机构是否重复录入供应商
	 * Create Date: 2010-10-21上午09:44:37 by shenjianzhuang  Modified Date: 2010-10-21上午09:44:37 by shenjianzhuang
	 * @param objId
	 * @param objId2
	 * @return  Boolean
	 */	
	public Boolean checkSupllierInSignupRecord(String objId,String objId2) {
		StringBuffer exception = new StringBuffer();
		Boolean checkValue = true;//判断是否有不满足条件的数据
		if(objId.equals(objId2) ){
			exception.append(messageSource.getMessage(EsExceptionEnum.SUPPLIER_IN_SIGNUP));//供应商已报名
		}
		if(exception.length()>0){checkValue=false;}//若有异常信息，表示有异常，需要把判断置为false;}
		return checkValue;
		
	}
	
	/** 
	 * FuncName : getSignUprecordListByProjectId
	 * Description :  根据项目Id获取报名记录
	 * Create Date: 2011-8-8下午02:02:37 by yangx  Modified Date: 2011-8-8下午02:02:37 by yangx
	 * @param   projectId：项目Id
	 * @return  List<SignUprecord>
	 * @Exception   
	 */
	public List<SignUprecord> getSignUprecordListByProjectId(String projectId){
		List<SignUprecord> signUprecordList = signUprecordDaoHibernate.getSignUprecordByprojectId(projectId);
		return signUprecordList;
	}
	
}
