package com.gpcsoft.epp.projreview.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.projreview.domain.EvaSellerRecord;

public interface EvaSellerRecordDao extends BaseGenericDao<EvaSellerRecord> {

	/**
	 * FuncName:getEvaSellerRecordListOrderByScore
	 * Description : 根据项目主键得到按推荐次数排列并且过滤的评标结果 
	 * @param   subProjId:包组主键
	 * @return  List<EvaSellerRecord>
	 * @author liuke
	 * @Create Date: 2010-6-26下午01:30:15    
	 * @Modifier liuke
	 * @Modified Date: 2010-6-26下午01:30:15 
	 */
	public List<EvaSellerRecord> getEvaSellerRecordListOrderByScore(String subProjId);	
	
	/**
	 * FuncName:getEvaSellerRecordBySupplierId
	 * Description : 根据供应商主键得到评标结果信息 
	 * @param   supplierId:供应商主键
	 * @return  EvaSellerRecord
	 * @author liuke
	 * @Create Date: 2010-6-26下午02:56:52 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-26下午02:56:52  
	 */
	public EvaSellerRecord getEvaSellerRecordBySupplierId(String SupplierId);
	
	/**
	 * FuncName:getEvaSellerRecordList
	 * Description : 根据项目主键评标结果 
	 * @param   subProjId:包组主键
	 * @return  List<EvaSellerRecord>
	 * @author liuke
	 * @Create Date: 2010-6-26下午01:30:15 
	 * @Modifier liuke
	 * @Modified Date: 2010-6-26下午01:30:15  
	 */
	public List<EvaSellerRecord> getEvaSellerRecordList(String subProjId);
	
	/**
	 * FuncName:getEvaSellerRecordListByproject
	 * Description:根据项目主键评标结果 
	 * @param   projectId:项目主键
	 * @return  List<EvaSellerRecord>
	 * @author liuke
	 * @Create Date: 2010-6-26下午01:30:15 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-26下午01:30:15  
	 */
    public  List<EvaSellerRecord> getEvaSellerRecordListByproject(String projectId);
    
	/**
     * FuncName:getEvaSellerRecordListBySubProjectAndSupplier
     * Description : 根据项目主键与供应商获得评标结果 
     * @param   subProjId:包组主键,supplierId:供应商主键
     * @return   List<EvaSellerRecord> 
     * @author	 liuke
     * @Create Date: 2010-6-26下午01:30:15 
     * @Modifier   liuke
     * @Modified Date: 2010-6-26下午01:30:15 
     */
    public List<EvaSellerRecord> getEvaSellerRecordListBySubProjectAndSupplier(String subProjId,String supplierId);
    
    /**
	 * FuncName:getEvaSellerRecord
	 * Description: 获取评审记录
	 * @param supplierId 供应商主键,packId 项目/包组主键,userId:打分人
	 * @return EvaSellerRecord
	 * @author wanghz
	 * @Create Date 2010-10-9 下午04:34:20  
	 */
	public EvaSellerRecord getEvaSellerRecord(String supplierId,String packId,String userId);
	
	
	/**
	 * FuncName: removeEvaSellerRecordByPack
	 * Description :  创建或修改对象
	 * @param 
	 * @param packId void
	 * @author: liuke
	 * @Create Date:2011-5-28  下午04:27:07
	 * @Modifier: liuke
	 * @Modified Date:2011-5-28  下午04:27:07
	 */
	public void removeEvaSellerRecordByPack(String packId);
}
