package com.gpcsoft.epp.bid.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.bid.dao.BidDao;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.buyresult.domain.ResultTypeEnum;
import com.gpcsoft.epp.project.domain.Project;

@Repository
public class BidDaoHibernate extends BaseGenericDaoHibernate<Bid> implements BidDao {
    
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
	public void saveOrUpdateBid(Bid bid) {
	    this.save(bid);
	}
	
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
	public List<Bid> getAllBidByProjectId(String projectId) {
		return this.findbyHql("select bidPackage.bid from BidPackage as bidPackage where bidPackage.pack.objId =? ", projectId);
	}
	
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
	public List<Bid> getBidListByProjectIdAndUserId(String projectId,String supplierId) {
		List<Bid> list = this.findbyHql("from Bid b where b.project.objId = ? and b.supplier.objId =?", projectId,supplierId); 
		return list;
	}
	
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
	public List<Bid> getBidAnonymousListByProjectIdAndBidNO(String projectId,String bidNo) {
		List<Bid> list = this.findbyHql("from Bid b where b.project.objId = ? and b.bidNo =?", projectId,bidNo); 
		return list;
	}
	
	/**
	 * Funcname:getBidPackByBidId
	 * Description: 根据投标ID获取投标投标包件,只返回包件ID,projSummary 项目摘要临时存放 投标包件价格
	 * @param bidId:投标主键
	 * @return List<Project>
	 * @throws Exception
	 * @Create Date 2010-8-5 上午10:42:22 
	 * @wanghz
	 */
	public List<Project> getBidPackByBidId(String bidId) {
		List<Project> bidPackList = new ArrayList<Project>(0);
		String hql = "select bp.pack.objId,bp.quotesum from BidPackage as bp where bid.objId=:bidId and bp.pack.objId is not null ";
		List<Object[]> objects = this.getSession().createQuery(hql).setString("bidId", bidId).list();
		if(null != objects && objects.size()>0){
			for(Object[] object:objects){
				Project bidPack = new Project();
				bidPack.setObjId(object[0]+"");
				bidPack.setProjSummary(object[1]+"");
				bidPackList.add(bidPack);
			}
		}
		return bidPackList;
	}
	
	/**
	 * FuncName:getBidSupplierInfoByProjectId
	 * Description: 根据项目ID获取投标供应商信息以及是否中标
	 * @param projectId
	 * @return List<Map<String,String>>
	 * @throws Exception
	 * @Create Date 2010-8-14 下午05:27:12 
	 * @author wanghz
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getBidSupplierInfoByProjectId(final String projectId){
		return (List<Map<String, String>>)getHibernateTemplate().execute(new HibernateCallback(){
			public List<Map<String, String>> doInHibernate(Session session)throws HibernateException, SQLException {
			StringBuffer hql = new StringBuffer();
			hql.append("SELECT bidPack.bid.supplierName ");
			hql.append(",( SELECT COUNT(buyWinner.objId) FROM BuyWinner as buyWinner ");
			hql.append("where buyWinner.selllerId = bidPack.bid.supplier.objId ");
			hql.append("and buyWinner.buyResult.subProjId = bidPack.pack.objId ");
			hql.append("and buyWinner.resultType=:resultType ");
			hql.append(" ) ");
			hql.append(" FROM BidPackage as bidPack ");
			hql.append("where bidPack.bid.project.objId=:projectId ");
			List<Object[]> objects = session.createQuery(hql.toString()).setString("resultType", ResultTypeEnum.DEAL_YES).setString("projectId", projectId).list();
			List<Map<String, String>> returnDataList = new ArrayList<Map<String,String>>(0);
			if (null != objects && objects.size()>0) {
				for(Object[] object:objects){
					Map<String, String> bidMap = new HashMap<String, String>();
					bidMap.put("supplierName", object[0]+"");
					bidMap.put("isBid", "是");
					if ((Long)object[1] > 0) {
						bidMap.put("result", "中标");
					} else {
						bidMap.put("result", "非中标");
					}
					returnDataList.add(bidMap);
				}
			}
			return returnDataList;
		}});
	}
	
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
	public List<Bid> getBidListByProjectId(String projectId) {
	    List<Bid> list = this.findbyHql(" from Bid t where t.project.objId = ?",projectId );
		return list;
	}
	
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
	public Bid getBidBySupplierid(String supplierId,String projectId){
		Bid bid = null;
		List<Bid> bids = this.findbyHql("from Bid t where t.project.objId ='"+projectId+"' and t.supplier.objId='"+supplierId+"' ");
		if(bids.size()!=0){
			bid = bids.get(0);
		}
		return bid;
	}

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
	public List<Bid> getBidListByProject(String projectId) {
		return this.findbyHql("from Bid t where t.project.objId = ?", projectId);
	}

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
	@SuppressWarnings("unchecked")
	public void removeAllBidByProject(final String projectId) {
		this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append("delete from Bid t where t.project.objId = ? ");
				Query query  = session.createQuery(hql.toString());
				query.setString(0, projectId).executeUpdate();
				return null;
			}});
	}
	
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
	public Bid getBidByProjCode(String projectCode,String supplierId){
		Bid bid = null;
		List<Bid> bids = this.findbyHql("from Bid t where t.project.projCode ='"+projectCode+"' and t.supplier.objId='"+supplierId+"' ");
		if(bids.size()!=0){
			bid = bids.get(0);
		}
		return bid;
	}
}
