package com.gpcsoft.epp.negotationrecord.dao;

import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.negotationrecord.domain.NegotationRecord;

public interface NegotationRecordDao extends BaseGenericDao<NegotationRecord> {

	/**
	 * 
	 * Description :根据QueryObject对象查询谈判记录对象  
	 * Create Date: 2010-11-3下午12:57:57 by liuke  Modified Date: 2010-11-3下午12:57:57 by liuke
	 * @param   QueryObject queryObject 查询对象，Page<NegotationRecord> page 页面信息对象 ，User user 当前用户
	 * @return  
	 * @Exception
	 */
	public Page<NegotationRecord> searchNegotationRecordForAgent(QueryObject queryObject, Page<NegotationRecord> page);	
	
	/**
	 * FuncName: getNegotationRecordList
	 * Description : List<NegotationRecord>
	 * @param subProjecId;项目或包组主键
	 * @param supplierId:供应商主键
	 * @return List<NegotationRecord>
	 * @author: shenjz
	 * @Create Date:2011-7-29  上午11:29:53
	 * @Modifier: shenjz
	 * @Modified Date:2011-7-29  上午11:29:53
	 */
	public List<NegotationRecord> getNegotationRecordList(String subProjecId,String supplierId);
	
	/** 
	 * Description :根据包ID和项目ID查询谈判记录对象  
	 * Create Date: 2011-09-22 14:00 by shengn
	 * @param   subProjectId 包组ID
	 * @param   projectId 包组ID
	 * @return  
	 * @Exception
	 */
	public List<NegotationRecord> searchNegotationRecordListBySubProjId(String subProjectId,String projectId);
	
	/**
	 * 根据报价时间(NegotationRecord.recordTime)获取最后一次报价金额
	 * @param subProjectId　报价的项目或包组Id(NegotationRecord.subProject.objId);
	 * @param supplierId 供应商Id(NegotationRecord.supplier.objId);
	 * @return null:表示未找到记录
	 * @author zhouzhanghe
	 * @Created date 2011-10-12 17:24
	 */
	public BigDecimal getTheLastRecordTotal(String subProjectId, String supplierId);
}
