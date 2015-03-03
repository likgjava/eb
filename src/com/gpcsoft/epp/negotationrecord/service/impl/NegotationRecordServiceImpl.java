package com.gpcsoft.epp.negotationrecord.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.negotationrecord.dao.NegotationRecordDao;
import com.gpcsoft.epp.negotationrecord.dao.NegotationRecordItemDao;
import com.gpcsoft.epp.negotationrecord.domain.NegotationRecord;
import com.gpcsoft.epp.negotationrecord.domain.NegotationRecordItem;
import com.gpcsoft.epp.negotationrecord.manager.NegotationRecordItemManager;
import com.gpcsoft.epp.negotationrecord.manager.NegotationRecordManager;
import com.gpcsoft.epp.negotationrecord.service.NegotationRecordService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class NegotationRecordServiceImpl extends BaseGenericServiceImpl<NegotationRecord> implements NegotationRecordService {

	@Autowired(required=true) @Qualifier("negotationRecordManagerImpl")
	private NegotationRecordManager negotationRecordManager;

	@Autowired(required=true)  @Qualifier("negotationRecordDaoHibernate")
	private NegotationRecordDao negotationRecordDaoHibernate;
	
	@Autowired(required=true) @Qualifier("negotationRecordItemManagerImpl")
	private NegotationRecordItemManager negotationRecordItemManager;
	
	@Autowired(required=true)  @Qualifier("negotationRecordItemDaoHibernate")
	private NegotationRecordItemDao negotationRecordItemDaoHibernate;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	/**
	 * 
	 * Description :根据QueryObject对象查询谈判记录对象  
	 * Create Date: 2010-11-3下午12:57:57 by liuke  Modified Date: 2010-11-3下午12:57:57 by liuke
	 * @param   QueryObject queryObject 查询对象，Page<NegotationRecord> page 页面信息对象 ，User user 当前用户
	 * @return  
	 * @Exception
	 */
	public Page<NegotationRecord> searchNegotationRecordForAgent(
			QueryObject queryObject, Page<NegotationRecord> page) {
		 logger.debug("\nes NegotationRecordServiceImpl||searchNegotationRecordForAgent\n");
		return negotationRecordDaoHibernate.searchNegotationRecordForAgent(queryObject, page);
	}
	
	/**
	 * 
	 * Description :保存谈判记录与谈判记录明细对象  
	 * Create Date: 2010-11-3下午06:10:02 by liuke  Modified Date: 2010-11-3下午06:10:02 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public NegotationRecord saveNegotationRecord(NegotationRecord negotationRecord,List<NegotationRecordItem> negotationRecordItemList) {
		logger.debug("\nes NegotationRecordServiceImpl||saveNegotationRecord\n");
		Date date = new Date();
		negotationRecord.setRecordTime(date);
		negotationRecordManager.save(negotationRecord); //保存谈判记录
		for(NegotationRecordItem NegotationRecordItem :negotationRecordItemList){
			NegotationRecordItem.setNegotationRecord(negotationRecord);
			negotationRecordItemManager.save(NegotationRecordItem);
		}
		User user=AuthenticationHelper.getCurrentUser(true);
		negotationRecord.setUser(user);
		String parentBizId = negotationRecord.getTenderId();
		negotationRecord.setParentBizId(parentBizId);
		return negotationRecord;
	}

	
	/**
	 * 
	 * Description :删除谈判记录与谈判记录明细 
	 * Create Date: 2010-11-3下午08:22:13 by liuke  Modified Date: 2010-11-3下午08:22:13 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void removeNegotationRecord(String negotationRecordId) {
		logger.debug("\nes NegotationRecordServiceImpl||removeNegotationRecord\n");
		List<NegotationRecordItem> negotationRecordItemList = negotationRecordItemDaoHibernate.getNegotationRecordItembyNegotationRecordId(negotationRecordId);
		for(NegotationRecordItem negotationRecordItem :negotationRecordItemList){ //删除谈判记录明细 
			negotationRecordItemDaoHibernate.remove(negotationRecordItem);
		}
		negotationRecordManager.remove(negotationRecordId, NegotationRecord.class);
	}

	
	/**
	 * 
	 * Description :根据主键得到谈判记录  
	 * Create Date: 2010-11-4上午09:41:27 by liuke  Modified Date: 2010-11-4上午09:41:27 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public NegotationRecord getNegotationRecordByObjId(String objId) {
		logger.debug("\nes NegotationRecordServiceImpl||getNegotationRecordByObjId\n");
		return negotationRecordManager.get(objId);
	}

	
	/**
	 * 
	 * Description :根据谈判记录得到谈判记录条目列表  
	 * Create Date: 2010-11-4上午09:44:19 by liuke  Modified Date: 2010-11-4上午09:44:19 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<NegotationRecordItem> getNegotationRecordItemListByNegotationRecord(
			String negotationRecordId) {
		logger.debug("\nes NegotationRecordServiceImpl||getNegotationRecordItemListByNegotationRecord\n");
		List<NegotationRecordItem> negotationRecordItemList = negotationRecordItemDaoHibernate.getNegotationRecordItembyNegotationRecordId(negotationRecordId);
		return negotationRecordItemList;
	}

	
	/**
	 * 
	 * Description :修改谈判记录与谈判记录明细对象  
	 * Create Date: 2010-11-3下午06:10:02 by liuke  Modified Date: 2010-11-3下午06:10:02 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public NegotationRecord updateNegotationRecord(NegotationRecord negotationRecord,List<NegotationRecordItem> negotationRecordItemList) {
		logger.debug("\nes NegotationRecordServiceImpl||updateNegotationRecord\n");
		negotationRecordManager.save(negotationRecord); //保存谈判记录
		for(NegotationRecordItem NegotationRecordItem :negotationRecordItemList){
			NegotationRecordItem.setNegotationRecord(negotationRecord);
			negotationRecordItemManager.save(NegotationRecordItem);
		}
		return negotationRecord;
	}

	/**
	 * 
	 * Description :根据包ID和项目ID查询谈判记录对象  
	 * Create Date: 2011-09-22 14:00 by shengn
	 * @param   subProjectId 包组ID
	 * @param   projectId 包组ID
	 * @return  
	 * @Exception
	 */
	public List<NegotationRecord> searchNegotationRecordListBySubProjId(String subProjectId,String projectId) {
		logger.debug("\nes NegotationRecordServiceImpl||searchNegotationRecordListBySubProjId\n");
		return negotationRecordDaoHibernate.searchNegotationRecordListBySubProjId(subProjectId,projectId);
	}

	/**
	 * 根据报价时间(NegotationRecord.recordTime)获取最后一次报价金额
	 * @param subProjectId　报价的项目或包组Id(NegotationRecord.subProject.objId);
	 * @param supplierId 供应商Id(NegotationRecord.supplier.objId);
	 * @return null:表示未找到记录
	 * @author zhouzhanghe
	 * @Created date 2011-10-12 17:24
	 */
	public BigDecimal getTheLastRecordTotal(String subProjectId,
			String supplierId) {
		return negotationRecordDaoHibernate.getTheLastRecordTotal(subProjectId, supplierId);
	}

	/**
	 * FuncName: getFillLastNegotiatePriceInOpenBidRecordQuoteSum
	 * Description :  将最后一次谈判价格填入开标报价总金额
	 * @param projectId
	 * @return List<BuyWinner>
	 * @author: zhouzhanghe
	 * @Create Date:2011-10-12 17:54
	 */
	public void getFillLastNegotiatePriceInOpenBidRecordQuoteSum(
			List<OpenBidRecord> openBidRecordList) {
		for(OpenBidRecord openBidRecord : openBidRecordList){
			BigDecimal lastbid = negotationRecordDaoHibernate.getTheLastRecordTotal(openBidRecord.getSubProjId(), openBidRecord.getSupplier().getObjId());//获取最后一次报价
			if(lastbid != null){
				openBidRecord.setQuoteSum(lastbid);
			}
		}
	}

	
	/**
	 * FuncName : finishNegotationRecord
	 * Description :  完成谈判记录节点方法
	 * Create Date: 2011-12-5下午06:05:59 by liuke
	 * Modified Date: 2011-12-5下午06:05:59 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Project finishNegotationRecord(String projectId) {
		Project project = projectManager.get(projectId);
		project.setParentBizId(projectId);
		project.setUser(AuthenticationHelper.getCurrentUser(true));
		return project;
	}
}
