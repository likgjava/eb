package com.gpcsoft.epp.purchasedoc.dao.hibernate;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.purchasedoc.dao.DosBuyRecordDao;
import com.gpcsoft.epp.purchasedoc.domain.DosBuyRecord;

import org.springframework.stereotype.Repository;

@Repository
public class DosBuyRecordDaoHibernate extends BaseGenericDaoHibernate<DosBuyRecord> implements DosBuyRecordDao {
	/**
	 * 
	 * Description :通过供应商主键判断是否购买采购文件  
	 * Create Date: 2010-5-27下午04:52:06 by liuke  Modified Date: 2010-5-27下午04:52:06 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean IsBuyPurchaseDoc(String supplierId, String purchaseDocId) {
		List list = this.findbyHql("from DosBuyRecord dosBuyRecord where dosBuyRecord.supplierId=? and dosBuyRecord.purDocId=?", supplierId,purchaseDocId);
		if(list.size()>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 
	 * Description :根据文件得到文件购买记录  
	 * Create Date: 2010-8-9下午02:17:16 by liuke  Modified Date: 2010-8-9下午02:17:16 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public DosBuyRecord getDosBuyRecordByDosId(String PurchaseDocId,String userId) {
		  List<DosBuyRecord> list = this.findbyHql("from DosBuyRecord dr where dr.fileId = ? and dr.supplierId =?", PurchaseDocId,userId);
		  if(list.size()>0){
			  return list.get(0);
		  }else {
			  return null;
		  }
	}

	/** 
	 * Description :  根据项目id获取招标文件购买记录
	 * Create Date: 2010-10-19下午05:00:25 by wangcl  
	 * Modified Date: 2011-9-22上午10:26:06 by yangx
	 * @param   projectId：项目Id
	 * @return  List<DosBuyRecord>
	 * @Exception   
	 */
	public List<DosBuyRecord> getByProjectId(String projectId) {
//		String hql = "select d from DosBuyRecord d , PurchaseDoc p where d.fileId = p.objId and p.project.objId = '"+projectId+"'";
		String hql = "select d from DosBuyRecord d where d.tenderId = '"+projectId+"'";
		return this.findbyHql(hql);
	}
	
	/** 
	 * FuncName : getDosBuyRecordBySupplierId
	 * Description :  根据供应商Id获取标书费购买记录
	 * Create Date: 2011-9-22下午11:38:18 by yangx  
	 * Modified Date: 2011-9-22下午11:38:18 by yangx
	 * @param   supplierId：供应商Id
	 * @return  DosBuyRecord
	 * @Exception   
	 */
	public List<DosBuyRecord> getDosBuyRecordBySupplierId(String supplierId,String projectId){
//		String hql = "Select dr from DosBuyRecord dr, PurchaseDoc p where dr.fileId=p.objId and dr.supplierId = '"+supplierId+"' and p.project.objId ='"+projectId+"'";
		String hql = "from DosBuyRecord dr where dr.supplierId = '"+supplierId+"'and dr.tenderId='"+projectId+"'";
		List<DosBuyRecord> list = this.findbyHql(hql);
		return list;
	}

}
