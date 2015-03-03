package com.gpcsoft.epp.bid.manager;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.bid.domain.BailRecord;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.project.domain.Project;

public interface BidManager extends BaseManager<Bid> {

	/**
	 * Funcname:saveOrUpdateBid
	 * Description : 修改或更新投标对象
	 * @Create Date: 2010-5-17下午02:34:27 
	 * @author liuke  
	 * @Modified Date: 2010-5-17下午02:34:27  
	 * @author liuke
	 * @param   Bid bid:投标对象
	 * @return  void
	 * @Exception
	 */
	public void saveOrUpdateBid(Bid bid);
	
	/**
	 * FuncName:getBidByQueryObject
	 * Description:根据查询条件得到投标对象 
	 * @Create Date: 2010-5-19下午06:59:31 
	 * @author liuke  
	 * @Modified Date: 2010-5-19下午06:59:31  
	 * @author liuke
	 * @param   queryObject:平台封装对象
	 * @return  Bid:投标对象
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public Bid getBidByQueryObject(QueryObject queryObject);
	
	/**
	 * Funcname:getAllBidByProjectId
	 * Description :通过项目主键得到投标列表  
	 * @Create Date: 2010-5-26上午09:58:30 
	 * @author liuke  
	 * @Modified Date: 2010-5-26上午09:58:30 
	 * @author liuke
	 * @param   projectId:项目主键
	 * @return  List<Bid>
	 * @Exception
	 */
	public List<Bid> getAllBidByProjectId(String projectId);
	
	/**
	 * FuncName:getAllPageBidByProjectId
	 * Description : 通过项目主键得到投标列表   
	 * @Create Date: 2010-5-26下午02:56:43 
	 * @author liuke  
	 * @Modified Date: 2010-5-26下午02:56:43 
	 * @author liuke
	 * @param   projectId:项目主键,start;起始条,pagesize:每页显示条数
	 * @return  Page<Bid>
	 * @Exception
	 */
	public Page<Bid> getAllPageBidByProjectId(String projectId,int start,int pagesize);
	
	/**
	 * FuncName:getBidListByProjectIdAndUserId
	 * Description :根据项目主键和供应商得到所有投标对象列表  
	 * @Create Date: 2010-6-24上午11:06:12 
	 * @author liuke 
	 * @Modified Date: 2010-6-24上午11:06:12 
	 * @author liuke
	 * @param   projectId:项目主键,supplierId：供应商主键
	 * @return  List<Bid>:投标对象集合
	 * @Exception
	 */
	public List<Bid> getBidListByProjectIdAndUserId(String projectId ,String supplierId);
	
	/**
	 * FuncName: getBidAnonymousListByProjectIdAndUserId
	 * Description :  根据项目主键和匿名投标号得到所有投标对象列表  
	 * @param 
	 * @param projectId
	 * @param bidNo
	 * @return List<Bid>
	 * @author: shenjz
	 * @Create Date:2011-12-13  上午11:34:46
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-13  上午11:34:46
	 */
	public List<Bid> getBidAnonymousListByProjectIdAndBidNo(String projectId,String bidNo);
	
	/**
	 * Funcname:getBidPackByBidId
	 * Description: 根据投标ID获取投标投标包件,只返回包件ID
	 * @param bidId:投标对象主键
	 * @return List<Project>
	 * @throws Exception
	 * @Create Date 2010-8-5 上午10:42:22 
	 * @author  wanghz
	 */
	public List<Project> getBidPackByBidId(String bidId);
	
	/**
	 * Funcname:isCanBid
	 * Description :是否可以投标  
	 * @Create Date: 2010-8-30上午10:04:27 
	 * @author liuke  
	 * @Modified Date: 2010-8-30上午10:04:27 
	 * @author liuke
	 * @param   projectId:项目主键,supplierId:供应商主键,bailRecord:投标保证金对象
	 * @return  boolean
	 * @Exception
	 */
	public boolean isCanBid(String projectId, String supplierId,BailRecord bailRecord)throws EsException;
	
	/** 
	 * Funcname:removeBidByProject
	 * Description :删除投标信息
	 * @Create Date: 2010-12-15下午07:26:10  
	 * @author liuke
	 * @Modified Date: 2010-12-15下午07:26:10 
	 * @author liuke
	 * @param projectId
	 * @return  void
	 * @Exception
	 */
	public void removeBidByProject(String projectId);
		
}
