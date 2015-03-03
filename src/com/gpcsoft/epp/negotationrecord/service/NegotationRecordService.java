package com.gpcsoft.epp.negotationrecord.service;
import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.negotationrecord.domain.NegotationRecord;
import com.gpcsoft.epp.negotationrecord.domain.NegotationRecordItem;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;

public interface NegotationRecordService extends BaseGenericService<NegotationRecord> {

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
	 * 
	 * Description :保存谈判记录与谈判记录明细对象  
	 * Create Date: 2010-11-3下午06:10:02 by liuke  Modified Date: 2010-11-3下午06:10:02 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public NegotationRecord saveNegotationRecord(NegotationRecord negotationRecord,List<NegotationRecordItem> negotationRecordItemList); 
	
	
	/**
	 * 
	 * Description :删除谈判记录与谈判记录明细 
	 * Create Date: 2010-11-3下午08:22:13 by liuke  Modified Date: 2010-11-3下午08:22:13 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void removeNegotationRecord(String negotationRecordId);
	
	/**
	 * 
	 * Description :根据主键得到谈判记录  
	 * Create Date: 2010-11-4上午09:41:27 by liuke  Modified Date: 2010-11-4上午09:41:27 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public NegotationRecord getNegotationRecordByObjId(String objId);
	
	
	/**
	 * 
	 * Description :根据谈判记录得到谈判记录条目列表  
	 * Create Date: 2010-11-4上午09:44:19 by liuke  Modified Date: 2010-11-4上午09:44:19 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<NegotationRecordItem> getNegotationRecordItemListByNegotationRecord(String negotationRecordId);
	
	
	/**
	 * 
	 * Description :修改谈判记录与谈判记录明细对象  
	 * Create Date: 2010-11-3下午06:10:02 by liuke  Modified Date: 2010-11-3下午06:10:02 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public NegotationRecord updateNegotationRecord(NegotationRecord negotationRecord,List<NegotationRecordItem> negotationRecordItemList); 
	
	/**
	 * 
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
	
	/**
	 * FuncName: getFillLastNegotiatePriceInOpenBidRecordQuoteSum
	 * Description :  将最后一次谈判价格填入开标报价总金额
	 * @param projectId
	 * @return List<BuyWinner>
	 * @author: zhouzhanghe
	 * @Create Date:2011-10-12 17:54
	 */
	public void getFillLastNegotiatePriceInOpenBidRecordQuoteSum(List<OpenBidRecord> openBidRecordList);
	
	
	/**
	 * FuncName : finishNegotationRecord
	 * Description :  完成谈判记录节点方法
	 * Create Date: 2011-12-5下午06:05:59 by liuke
	 * Modified Date: 2011-12-5下午06:05:59 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Project finishNegotationRecord(String projectId);
	
}
