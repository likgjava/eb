package com.gpcsoft.epp.projreview.dao.hibernate;

import java.math.BigDecimal;
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
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;
import com.gpcsoft.epp.projreview.dao.EvalBidRecordDao;
import com.gpcsoft.epp.projreview.domain.EvalBidRecord;
import com.gpcsoft.epp.projreview.domain.EvalFactorResult;

@Repository
public class EvalBidRecordDaoHibernate extends BaseGenericDaoHibernate<EvalBidRecord> implements EvalBidRecordDao {
	
	/**
	 * FuncName:getEvalBidRecordBySubProjectId
	 * Description: 根据包组获得评标列表 
	 * @return  List<EvalBidRecord>
	 * @author liuke
	 * @Create Date: 2010-9-19下午03:37:08 
	 * @Modifier liuke   
	 * @Modified Date: 2010-9-19下午03:37:08  
	 */
	public List<EvalBidRecord> getEvalBidRecordBySubProjectId(String subProjectId) {
			List<EvalBidRecord> list = this.findbyHql("from EvalBidRecord t where t.subProjId = ?", subProjectId);
			return list;
	}
	
	/**
	 * FuncName:getAllBidSupplierByPackId
	 * Description: 获取所有投标供应商
	 * @param packId:包件ID/项目ID
	 * @return List<Map<String,String>>:为了避免代码耦合返回 MAP 集合,map{supplierId:供应商主键,supplierName:供应商名称}
	 * @author wanghz
	 * @Create Date 2010-9-20 下午03:39:10 
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> getAllBidSupplierByPackId(final String packId){
		return (List<Map<String,String>>)getHibernateTemplate().execute(new HibernateCallback(){
			public List<Map<String,String>> doInHibernate(Session session)throws HibernateException, SQLException {
				List<Map<String,String>> supplierList = null;
				StringBuffer hql = new StringBuffer();
				hql.append(" select bid.supplier.objId,bid.supplierName ");
				hql.append(" from Bid as bid where bid.objId in ( ");
				hql.append(" select distinct bidPackage.bid.objId ");
				hql.append(" from BidPackage as bidPackage ");
				hql.append(" where bidPackage.pack.objId=:packId ");
				hql.append(" ) ");
				List<Object[]> objects = session.createQuery(hql.toString()).setString("packId", packId).list();
				if (null != objects && !objects.isEmpty()) {
					supplierList = new ArrayList<Map<String,String>>(objects.size());
					for (Object[] object:objects) {// 封装数据
						Map<String,String> supplier = new HashMap<String, String>();
						supplier.put("supplierId", object[0].toString());
						supplier.put("supplierName", object[1].toString());
						supplierList.add(supplier);
					}
				}else{
					supplierList = new ArrayList<Map<String,String>>(0);
				}
				return supplierList;
			}});
	}
	
	/**
	 * FuncName:getAllEvalFactorResult
	 * Description: 获取所有评审打分
	 * @param supplierId:供应商ID,packId:项目/包组ID,factorTypeId:指标分类主键,userId:打分人
	 * @return List<EvalFactorResult>
	 * @author wanghz
	 * @Create Date 2010-10-9 下午03:20:16 
	 */
	@SuppressWarnings("unchecked")
	public List<EvalFactorResult> getAllEvalFactorResult(final String supplierId,final String packId,final String factorTypeId,final String userId){
		return (List<EvalFactorResult>)getHibernateTemplate().execute(new HibernateCallback(){
			public List<EvalFactorResult> doInHibernate(Session session)throws HibernateException, SQLException {
				List<EvalFactorResult> evalFactorResultList = new ArrayList<EvalFactorResult>(0);
				StringBuffer hql = new StringBuffer();
				hql.append(" select factorResult,packFactorResponse.congFactorResponse ");
				hql.append(" from ");
				hql.append("  EvalFactorResult as factorResult ");
				hql.append(" ,EvaSellerRecord as sellerRecord ");
				hql.append(" ,EvalBidRecord as evalBidRecord ");
				hql.append(" ,PackCongFactorResponse as packFactorResponse ");
				hql.append(" ,BidPackage as bidPackage ");
				hql.append(" where  ");
				hql.append(" sellerRecord.objId = factorResult.sellerRecId ");
				hql.append(" and evalBidRecord.objId = sellerRecord.evalId ");
				hql.append(" and packFactorResponse.congFactorResponse.factorId = factorResult.factorId ");
				hql.append(" and bidPackage.objId = packFactorResponse.bidPackageId ");
				hql.append(" and evalBidRecord.subProjId=:packId ");
				hql.append(" and evalBidRecord.evalLinker=:userId ");
				hql.append(" and bidPackage.bid.supplier.objId=:supplierId ");
				hql.append(" and bidPackage.pack.objId=:packId ");
				hql.append(" and factorResult.factorTypeId=:factorTypeId ");
				hql.append(" and sellerRecord.supplier.objId=:supplierId ");
				List<Object[]> objects = session.createQuery(hql.toString())
				.setString("packId", packId)
				.setString("userId", userId)
				.setString("supplierId", supplierId)
				.setString("packId", packId)
				.setString("factorTypeId", factorTypeId)
				.list();
				if (null != objects && !objects.isEmpty()) {// 将投标响应临时注入到评审记录
					for (Object[] object:objects) {
						EvalFactorResult evalFactorResult = (EvalFactorResult)object[0];
						CongFactorResponse congFactorResponse = (CongFactorResponse)object[1];
						evalFactorResult.setRespValue(congFactorResponse.getRespValue());
						String respAttrId = "";
						String respAttrName = "";
						if (null != congFactorResponse.getAttr() && null != congFactorResponse.getAttr().getObjId()) {
							respAttrId = congFactorResponse.getAttr().getObjId();
							respAttrName = congFactorResponse.getAttr().getViewName();
						}
						evalFactorResult.setRespAttrId(respAttrId);
						evalFactorResult.setRespAttrName(respAttrName);
						evalFactorResultList.add(evalFactorResult);
					}
				}
				return evalFactorResultList;
			}});
	}
	
	/**
	 * FuncName:getAllCongFactorResponse
	 * Description: 获取所有指标响应
	 * @param supplierId:供应商ID,packId:包件ID/项目Id,factorTypeId:指标分类ID
	 * @return List<CongFactorResponse>
	 * @author  wanghz
	 * @Create Date 2010-9-20 下午10:16:06
	 */
	@SuppressWarnings("unchecked")
	public List<CongFactorResponse> getAllCongFactorResponse(final String supplierId,final String packId,final String factorTypeId){
		return (List<CongFactorResponse>) getHibernateTemplate().execute(new HibernateCallback(){
			public List<CongFactorResponse> doInHibernate(Session session)throws HibernateException, SQLException {	
				StringBuffer hql = new StringBuffer();
				hql.append(" select packFactorResponse.congFactorResponse from PackCongFactorResponse as packFactorResponse ");
				hql.append(" ,BidPackage as bidPackage ");
				hql.append(" where bidPackage.objId = packFactorResponse.bidPackageId ");
				hql.append(" and bidPackage.pack.objId=:packId ");
				hql.append(" and bidPackage.bid.supplier.objId=:supplierId ");
				hql.append(" and packFactorResponse.congFactorResponse.factorId in ( ");
				hql.append(" select factor.objId from CongruousFactor as factor ");
				hql.append(" where factor.factorType.objId=:factorTypeId ");
				hql.append(" ) ");
				List<CongFactorResponse> congFactorResponseList = session.createQuery(hql.toString())
				.setString("supplierId", supplierId)
				.setString("packId", packId)
				.setString("factorTypeId", factorTypeId)
				.list();
				if (null == congFactorResponseList || congFactorResponseList.isEmpty()) {
					congFactorResponseList = new ArrayList<CongFactorResponse>(0);
				}
				return congFactorResponseList;
			}
		});
	}
	
	/**
	 * FuncName:getBidQuoteSum
	 * Description:获取投标总金额
	 * @param supplierId 供应商ID,packId:包件ID
	 * @return BigDecimal
	 * @author wanghz
	 * @Create Date 2010-10-9 下午05:30:38  
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal getBidQuoteSum(final String supplierId,final String packId){
		return (BigDecimal) getHibernateTemplate().execute(new HibernateCallback(){
			public BigDecimal doInHibernate(Session session)throws HibernateException, SQLException {	
				StringBuffer hql = new StringBuffer();
				hql.append(" from BidPackage as bidPackage ");
				hql.append(" where bidPackage.pack.objId=:packId ");
				hql.append(" and bidPackage.bid.supplier.objId=:supplierId ");
				List<BidPackage> bidPackageList = session.createQuery(hql.toString())
				.setString("supplierId", supplierId)
				.setString("packId", packId)
				.list();
				if (null != bidPackageList && bidPackageList.size()>0) {
					return  new BigDecimal(bidPackageList.get(0).getQuotesum().toString());
				}
				return new BigDecimal(0);
			}
		});
	}

	/**
	 * FuncName: removeEvalBidRecordByPack
	 * Description :  创建或修改对象
	 * @param 
	 * @param packId void
	 * @author: liuke
	 * @Create Date:2011-5-28  上午10:42:02
	 * @Modifier: liuke
	 * @Modified Date:2011-5-28  上午10:42:02
	 */
	@SuppressWarnings("unchecked")
	public void removeEvalBidRecordByPack(final String packId) {
	this.getHibernateTemplate().execute(new HibernateCallback(){
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			StringBuffer hql = new StringBuffer();
			hql.append("delete from EvalBidRecord t where t.subProjId=:packId");
			Query query =  session.createQuery(hql.toString());
			query.setString("packId", packId).executeUpdate();
			return null;
		}});
		
	}
}
