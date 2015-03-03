package com.gpcsoft.epp.consign.manager.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.consign.dao.ConsignDao;
import com.gpcsoft.epp.consign.domain.Consign;
import com.gpcsoft.epp.consign.manager.ConsignManager;
import com.gpcsoft.srplatform.baseData.dao.SequenceNumberDao;

@Repository
public class ConsignManagerImpl extends BaseManagerImpl<Consign> implements ConsignManager {

	@Autowired(required=true)  @Qualifier("consignDaoHibernate")
	private ConsignDao consignDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("sequenceNumberDaoHibernate")
	private SequenceNumberDao sequenceNumberdao;
    
	/**
	 * FuncName:auditConsign
	 * Description :  确认委托协议
	 * @param consign:委托协议对象
	 * @return   void
	 * @author liangxj
	 * @Create Date: 2010-6-6下午05:52:43 
	 * @Modifier liangxj
	 * @Modified Date: 2010-6-6下午05:52:43 
	 */
	public void auditConsign(Consign consign){
		consignDaoHibernate.submitConsign(consign.getObjId(), consign.getUseStatus(), consign.getConfirmStatus());
	}

	/** 
	 * FuncName:submitConsign
	 * Description :  提交委托书
	 * @param   objIds:以逗号分割的申报书主键,useStatus:使用状态,confirmStatus:确认状态
	 * @return  void
	 * @author liangxj
	 * @Create Date: 2010-6-6下午05:52:43 
	 * @Modifier liangxj
	 * @Modified Date: 2010-6-6下午05:52:43 
	 */
	public void submitConsign(String objIds,String useStatus,String confirmStatus){
		consignDaoHibernate.submitConsign(objIds, useStatus, confirmStatus);
	}
	
	/** 
	 * FuncName:getConsignListByObject
	 * Description :  根据对象获取委托协议列表
	 * @param  [consCode:编号,consName:名称,consAgent:代理机构,confirmStatus:状态,taskType,申报书类型] 
	 * @return  getConsignListByObject
	 * @author yangx
	 * @Create Date: 2010-7-13下午04:13:25 
	 * @Modifier   yangx
	 * @Modified Date: 2010-7-13下午04:13:25 
	 */
	@SuppressWarnings("unchecked")
	public Page<Consign> getConsignListByObject(Page<Consign> page,QueryObject queryObject){
		return consignDaoHibernate.getConsignListByObject(page,queryObject);
	}
	
	/**
	 * FuncName:generatorConsignCodeByQO
	 * Description: 生成委托协议编号,Service层必须开启DB事物
	 * @param queryObject 为扩展参数，暂时不用
	 * @return String
	 * @author wanghz
	 * @Create Date 2010-7-15 上午09:59:44 
	 */
	public String generatorConsignCodeByQO(QueryObject queryObject)throws EsException {
		// 1.获取委托协议前缀编号
		String prefixCode = "";
		try {
			prefixCode = messageSource.getMessage("consignPrefixCode");
		} catch (Exception e) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GENERATOR_CODE_GET_PREFIX_CODE_ISNULL));
		}
		// 2.按照规则生成委托协议编号
		StringBuffer returnCode = new StringBuffer();
		returnCode.append(prefixCode);
		if(null != prefixCode && !"".equals(prefixCode)){
			returnCode.append("-");
		}
		returnCode.append(DateUtil.format(new Date(), "yyyyMMdd"));
		try {
			returnCode.append("-"+sequenceNumberdao.updateAndGetCurSn(returnCode.toString(), 3));
		} catch (Exception e) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GENERATOR_CODE_IS_DB_READONLY));
		}
		return returnCode.toString();
	}
	
	/**
	 * FuncName:getConsignListOrCountByQuery
	 * Description: 获取委托协议List 或 记录条数
	 * @param queryObject{count:记录条数,type:查询类型{data:数据,count记录条数},createUser:创建人ID,confirmStatus:确认状态,consBuyer:采购人}
	 * @return Object
	 * @author wanghz
	 * @Create Date 2010-9-2 下午02:36:16 
	 */
	public Object getConsignListOrCountByQuery(QueryObject queryObject)throws EsException{
		return consignDaoHibernate.getConsignListOrCountByQuery(queryObject);
	}
}
