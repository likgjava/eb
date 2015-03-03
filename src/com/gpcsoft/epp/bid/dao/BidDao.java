package com.gpcsoft.epp.bid.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.project.domain.Project;

public interface BidDao extends BaseGenericDao<Bid> {

	/**
	 * Funcname:saveOrUpdateBid
	 * Description :  修改或更新投标对象
	 * @Create Date: 2010-5-17下午02:34:27    
	 * @author liuke
	 * @Modified Date: 2010-5-17下午02:34:27  
	 * @author liuke
	 * @param   Bid bid
	 * @return  void
	 * @Exception
	 */
	public void saveOrUpdateBid(Bid bid);
	
	/**
	 * Funcname:getAllBidByProjectId
	 * Description:通过项目主键得到投标列表  
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
	 * Funcname:getBidListByProjectIdAndUserId
	 * Description:根据项目主键和供应商得到所有投标对象列表  
	 * @Create Date: 2010-6-24上午11:06:12   
	 * @author liuke
	 * @Modified Date: 2010-6-24上午11:06:12 
	 * @author liuke
	 * @param   projectId:项目主键,supplierId:供应商主键
	 * @return  List<Bid>
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
	public List<Bid> getBidAnonymousListByProjectIdAndBidNO(String projectId,String bidNo);
	
	/**
	 * Funcname:getBidPackByBidId
	 * Description: 根据投标ID获取投标投标包件,只返回包件ID,projSummary 项目摘要临时存放 投标包件价格
	 * @param bidId:投标主键
	 * @return List<Project>
	 * @throws Exception
	 * @Create Date 2010-8-5 上午10:42:22 
	 * @wanghz
	 */
	public List<Project> getBidPackByBidId(String bidId);
	
	/**
	 * FuncName:getBidSupplierInfoByProjectId
	 * Description: 根据项目ID获取投标供应商信息以及是否中标
	 * @param projectId
	 * @return List<Map<String,String>>
	 * @throws Exception
	 * @Create Date 2010-8-14 下午05:27:12 
	 * @author wanghz
	 */
	public List<Map<String, String>> getBidSupplierInfoByProjectId(String projectId);
	
	/**
	 * Funcname:getBidListByProjectId
	 * Description :根据项目得到投标信息  
	 * @Create Date: 2010-8-30上午11:42:36 
	 * @author liuke  
	 * @Modified Date: 2010-8-30上午11:42:36 
	 * @author liuke
	 * @param   projectId:项目主键
	 * @return  List<Bid>
	 * @Exception
	 */
	public List<Bid> getBidListByProjectId(String projectId);
	
	/**
	 * Funcname:getBidBySupplierid
	 * Description :  根据供应商ID和项目ID获取供应商投标对象
	 * @Create Date: 2010-10-27上午11:42:10 
	 * @author shenjianzhuang  
	 * @Modified Date: 2010-10-27上午11:42:10 
	 * @author shenjianzhuang
	 * @param supplierId:供应商主键
	 * @param projectId:项目主键
	 * @return  Bid:投标对象
	 * @Exception	
	 */	
	public Bid getBidBySupplierid(String supplierId,String projectId);
	
	/**
	 * FuncName:getBidListByProject
	 * Description : 根据项目获得投标对象
	 * @Create Date: 2010-12-15上午11:00:40    
	 * @author liuke
	 * @Modified Date: 2010-12-15上午11:00:40 
	 * @author liuke
	 * @param projectId:项目主键
	 * @return  List<Bid>
	 * @Exception
	 */
	public List<Bid> getBidListByProject(String projectId);
	
	/**
	 * FuncName:removeAllBidByProject
	 * Description : 删除投标信息 
	 * @Create Date: 2010-12-15下午07:28:35 
	 * @author liuke  
	 * @Modified Date: 2010-12-15下午07:28:35  
	 * @author liuke
	 * @param projectId
	 * @return  void
	 * @Exception
	 */
	public void removeAllBidByProject(String projectId);
	
	/**
	 * FuncName: getBidByProjCode
	 * Description :  根据项目编号和供应商Id获取到供应商投标对象
	 * @param supplierId:供应商主键
	 * @param projectId:项目主键
	 * @return Bid:投标对象
	 * @author: shenjz
	 * @Create Date:2011-6-22  下午04:24:31
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-22  下午04:24:31
	 */
	public Bid getBidByProjCode(String projectCode,String supplierId);
	
}
