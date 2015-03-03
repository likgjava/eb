package com.gpcsoft.epp.projreview.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.projreview.dao.EvalBidRecordDao;
import com.gpcsoft.epp.projreview.domain.EvalBidRecord;
import com.gpcsoft.epp.projreview.manager.EvalBidRecordManager;

@Repository
public class EvalBidRecordManagerImpl extends BaseManagerImpl<EvalBidRecord> implements EvalBidRecordManager {

	@Autowired(required=true)  @Qualifier("evalBidRecordDaoHibernate")
	private EvalBidRecordDao evalBidRecordDaoHibernate;
	
	/**
	 * FuncName:getEvalBidRecordByProjectId
	 * Description : 根据项目主键得到项目评标对象 
	 * @param   projectId:项目主键
	 * @return  EvalBidRecord
	 * @author liuke
	 * @Create Date: 2010-6-4下午01:38:54  
	 * @Modifier liuke
	 * @Modified Date: 2010-6-4下午01:38:54 
	 */
	public EvalBidRecord getEvalBidRecordByProjectId(String projectId) {
		QueryObject<EvalBidRecord> queryObject = new QueryObjectBase<EvalBidRecord>();
		queryObject.setEntityClass(EvalBidRecord.class);
		queryObject.setQueryParam(new QueryParam("projId",QueryParam.OPERATOR_EQ,projectId));
		List list = evalBidRecordDaoHibernate.findByQuery(queryObject);
        if(list.size()>0)
        {
        	return (EvalBidRecord) list.get(0);
        }
        else
        {
        	return null;
        }
	}

	/**
	 * FuncName:getEvalBidRecordByProjectIdAndUserId
	 * Description :  通过项目主键和用户主键得到项目评标对象
	 * @param  projectId:项目主键,userId:用户主键
	 * @return  EvalBidRecord
	 * @author liuke
	 * @Create Date: 2010-6-22下午05:54:11 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-22下午05:54:11 
	 */
	public EvalBidRecord getEvalBidRecordByProjectIdAndUserId(String projectId,String userId) {
		QueryObject<EvalBidRecord> queryObject = new QueryObjectBase<EvalBidRecord>();
		queryObject.setEntityClass(EvalBidRecord.class);
		queryObject.getQueryParam().and(new QueryParam("evalLinker",QueryParam.OPERATOR_EQ,userId));
		queryObject.getQueryParam().and(new QueryParam("projId",QueryParam.OPERATOR_EQ,projectId));
		List list = evalBidRecordDaoHibernate.findByQuery(queryObject);
        if(list.size()>0)
        {
        	return (EvalBidRecord) list.get(0);
        }
        else
        {
        	return null;
        }
	}

	/**
	 * FuncName:getEvalBidRecordBySubProjectIdAndUserId
	 * Description :  通过包组主键和用户主键得到项目评标对象
	 * @param   subProjectId:包组主键,userId:用户主键
	 * @return  EvalBidRecord
	 * @author liuke
	 * @Create Date: 2010-6-22下午05:54:11 
	 * @Modifier liuke
	 * @Modified Date: 2010-6-22下午05:54:11  
	 */
	public EvalBidRecord getEvalBidRecordBySubProjectIdAndUserId(String subProjectId, String userId) {
		QueryObject<EvalBidRecord> queryObject = new QueryObjectBase<EvalBidRecord>();
		queryObject.setEntityClass(EvalBidRecord.class);
		queryObject.getQueryParam().and(new QueryParam("evalLinker",QueryParam.OPERATOR_EQ,userId));
		queryObject.getQueryParam().and(new QueryParam("subProjId",QueryParam.OPERATOR_EQ,subProjectId));
		
		List list = evalBidRecordDaoHibernate.findByQuery(queryObject);
        if(list.size()>0) {
        	return (EvalBidRecord) list.get(0);
        } else {
        	return null;
        }
	}
	
	/**
	 * FuncName:getEvalBidRecordBySubProjectIdAndExpertName
	 * Description :  根据包组主键和专家姓名得到项目评标对象
	 * @param   subProjectId:包组主键,expertName专家姓名
	 * @return  EvalBidRecord
	 * @author liuke
	 * @Create Date: 2010-8-12上午09:41:34
	 * @Modifier    liuke
	 * @Modified Date: 2010-8-12上午09:41:34
	 */
	public EvalBidRecord getEvalBidRecordBySubProjectIdAndExpertName(String subProjectId, String expertName) {
		QueryObject<EvalBidRecord> queryObject = new QueryObjectBase<EvalBidRecord>();
		queryObject.setEntityClass(EvalBidRecord.class);
		queryObject.getQueryParam().and(new QueryParam("evalLinkerName",QueryParam.OPERATOR_EQ,expertName));
		queryObject.getQueryParam().and(new QueryParam("subProjId",QueryParam.OPERATOR_EQ,subProjectId));
		List list = evalBidRecordDaoHibernate.findByQuery(queryObject);
        if(list.size()>0)
        {
        	return (EvalBidRecord) list.get(0);
        }
        else
        {
        	return null;
        }
	}
}
