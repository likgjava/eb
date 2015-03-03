package com.gpcsoft.epp.eval.dao.hibernate;

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
import com.gpcsoft.epp.eval.dao.PackCongFactorResponseDao;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;
import com.gpcsoft.epp.eval.domain.CongruousFactor;
import com.gpcsoft.epp.eval.domain.CongruousFactorType;
import com.gpcsoft.epp.eval.domain.FactypeMfactor;
import com.gpcsoft.epp.eval.domain.PackCongFactorResponse;

@Repository
@SuppressWarnings(value={"unchecked"})
public class PackCongFactorResponseDaoHibernate extends BaseGenericDaoHibernate<PackCongFactorResponse> implements PackCongFactorResponseDao {

	
	/**
	 * @Description: 获取所有指标响应
	 * @param bidId投标ID
	 * @param packIds 包件ID
	 * @return List<Map<String,String>>
	 * @throws Exception
	 * @Create Date 2010-8-4 下午05:13:45 By wanghz
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getFactorResponseByBidIdAndPackIds(final String bidId,final String[] packIds) throws Exception {
		return (List<Map<String, String>>) getHibernateTemplate().execute(new HibernateCallback(){
			public List<Map<String, String>> doInHibernate(Session session)throws HibernateException, SQLException {	
				List<Map<String, String>> list = new ArrayList<Map<String,String>>(0);
				if (null != bidId &&  !"".equals(bidId) && (null == packIds || packIds.length<1)) {// 根据投标ID获取指标响应
					StringBuffer hql = new StringBuffer();
					hql.append(" select packRes.congFactorResponse,bidPackage.pack.objId,congruousFactor from PackCongFactorResponse as packRes,BidPackage bidPackage,CongruousFactor congruousFactor ");
					hql.append(" where packRes.bidPackageId = bidPackage.objId ");
					hql.append(" and congruousFactor.objId = packRes.congFactorResponse.factorId ");
					hql.append(" and bidPackage.bid.objId=:bidId ");
					List<Object[]> objects = session.createQuery(hql.toString()).setString("bidId", bidId).list();
					if(null != objects && objects.size()>0){
						for(Object[] object:objects){
							CongFactorResponse factorResponse = (CongFactorResponse)object[0];
							CongruousFactor factor = (CongruousFactor)object[2];
							Map<String, String> dataMap = new HashMap<String, String>();
							dataMap.put("factorId", factor.getObjId());
							dataMap.put("factorName", factor.getFactorName());
							dataMap.put("factorTypeId", factor.getFactorType().getObjId());
							dataMap.put("factorTypeName", factor.getFactorType().getTypeName());
							dataMap.put("responseId", factorResponse.getObjId());
							dataMap.put("respValue", factorResponse.getRespValue());
							if(null == factorResponse.getAttr()){
								dataMap.put("attrId", "");
								dataMap.put("viewName", "");
							}else{
								dataMap.put("attrId", factorResponse.getAttr().getObjId());
								dataMap.put("viewName", factorResponse.getAttr().getViewName());
							}
							dataMap.put("packId", object[1]+"");
							dataMap.put("packName", factor.getProjName());
							dataMap.put("isNeed", factor.getIsNeed());// 01:必填,00:选填
							list.add(dataMap);
						}
					}	
				}
				if ((null == bidId || "".equals(bidId)) && null != packIds && packIds.length>0) {// 根据包件ID获取指标
					StringBuffer hql = new StringBuffer();
					hql.append("select factotM from FactypeMfactor as factotM ");
					hql.append(" where factotM.projId in (:projId) ");
					Query query = session.createQuery(hql.toString());
					query.setParameterList("projId", packIds);
					List<FactypeMfactor> factypeMfactorList = query.list();
					if(null != factypeMfactorList && factypeMfactorList.size()>0){
						for(FactypeMfactor factypeMfactor:factypeMfactorList){
							CongruousFactor congruousFactor = factypeMfactor.getFactor();
							/**
							 * 组装返回数据,数据格式如下
							 * "factorId","factorName","packId","packName","factorTypeId","factorTypeName",isNeed
							 */
							if(null != congruousFactor){
								Map<String, String> dataMap = new HashMap<String, String>();
								dataMap.put("factorId", congruousFactor.getObjId());
								dataMap.put("factorName", congruousFactor.getFactorName());
								dataMap.put("packId", factypeMfactor.getProjId());
								dataMap.put("packName", congruousFactor.getProjName());
								dataMap.put("factorTypeId", congruousFactor.getFactorType().getObjId());
								dataMap.put("factorTypeName", congruousFactor.getFactorType().getTypeName());
								dataMap.put("isNeed", factypeMfactor.getFactor().getIsNeed());// 01:必填,00:选填
								list.add(dataMap);
							}
						}
					}
				}
				// 根据投标ID获取投标响应，并且根据包件获取指标
				if(null != bidId && !"".equals(bidId) && null != packIds && packIds.length>0){
					StringBuffer hql = new StringBuffer();// 1.根据投标ID获取投标响应
					hql.append(" select packRes.congFactorResponse,bidPackage.pack.objId,congruousFactor from PackCongFactorResponse as packRes,BidPackage bidPackage,CongruousFactor congruousFactor ");
					hql.append(" where packRes.bidPackageId = bidPackage.objId ");
					hql.append(" and congruousFactor.objId = packRes.congFactorResponse.factorId ");
					hql.append(" and bidPackage.bid.objId=:bidId ");
					hql.append(" and bidPackage.pack.objId in (:packIds) ");
					Query query = session.createQuery(hql.toString());
					query.setString("bidId", bidId);
					query.setParameterList("packIds", packIds);
					List<Object[]> objects = query.list();
					if(null != objects && objects.size()>0){
						for(Object[] object:objects){
							CongFactorResponse factorResponse = (CongFactorResponse)object[0];
							CongruousFactor factor = (CongruousFactor)object[2];
							Map<String, String> dataMap = new HashMap<String, String>();
							dataMap.put("factorId", factor.getObjId());
							dataMap.put("factorName", factor.getFactorName());
							dataMap.put("factorTypeId", factor.getFactorType().getObjId());
							dataMap.put("factorTypeName", factor.getFactorType().getTypeName());
							dataMap.put("responseId", factorResponse.getObjId());
							dataMap.put("respValue", factorResponse.getRespValue());
							if(null == factorResponse.getAttr()){
								dataMap.put("attrId", "");
								dataMap.put("viewName", "");
							}else{
								dataMap.put("attrId", factorResponse.getAttr().getObjId());
								dataMap.put("viewName", factorResponse.getAttr().getViewName());
							}
							dataMap.put("packId", object[1]+"");
							dataMap.put("packName", factor.getProjName());
							dataMap.put("isNeed", factor.getIsNeed());// 01:必填,00:选填
							list.add(dataMap);
						}
					}
					
					// 2.根据包件ID获取指标，并过滤掉指标响应存在的指标
					hql = new StringBuffer();
					hql.append(" select factotM from FactypeMfactor as factotM ");
					hql.append(" where factotM.projId in (:projId) ");
					hql.append(" and factotM.objId not in ( ");
					hql.append(" select factotMCongru.objId from FactypeMfactor as factotMCongru ");
					hql.append(" ,PackCongFactorResponse as packRes ");
					hql.append(" ,BidPackage bidPackage ");
					hql.append(" where factotMCongru.factor.objId = packRes.congFactorResponse.factorId ");
					hql.append(" and bidPackage.objId = packRes.bidPackageId ");
					hql.append(" and bidPackage.bid.objId=:bidId ");
					hql.append(" and bidPackage.pack.objId in (:projId) ");
					hql.append(" and factotMCongru.projId = bidPackage.pack.objId ");
					hql.append(" ) ");
					query = session.createQuery(hql.toString());
					query.setParameterList("projId", packIds);
					query.setString("bidId", bidId);
					List<FactypeMfactor> factypeMfactorList = query.list();
					if(null != factypeMfactorList && factypeMfactorList.size()>0){
						for(FactypeMfactor factypeMfactor:factypeMfactorList){
							CongruousFactor congruousFactor = factypeMfactor.getFactor();
							/**
							 * 组装返回数据,数据格式如下
							 * "factorId","factorName","packId","packName","factorTypeId","factorTypeName",isNeed
							 */
							if(null != congruousFactor){
								Map<String, String> dataMap = new HashMap<String, String>();
								dataMap.put("factorId", congruousFactor.getObjId());
								dataMap.put("factorName", congruousFactor.getFactorName());
								dataMap.put("packId", factypeMfactor.getProjId());
								dataMap.put("packName", congruousFactor.getProjName());
								dataMap.put("factorTypeId", congruousFactor.getFactorType().getObjId());
								dataMap.put("factorTypeName", congruousFactor.getFactorType().getTypeName());
								dataMap.put("isNeed", factypeMfactor.getFactor().getIsNeed());// 01:必填,00:选填
								list.add(dataMap);
							}
						}
					}
				}
				return list;
			}
		});
	}
	
	/**
	 * @Description: 根据投标Id获取指标响应
	 * @param bidId
	 * @return List<PackCongFactorResponse>
	 * @throws Exception
	 * @Create Date 2010-8-5 下午01:18:03 By wanghz
	 */
	public List<PackCongFactorResponse> getPackCongFactorResponseIsMayRemove(final String bidId) {
		return (List<PackCongFactorResponse>) getHibernateTemplate().execute(new HibernateCallback(){
			public List<PackCongFactorResponse> doInHibernate(Session session)throws HibernateException, SQLException {	
				List<PackCongFactorResponse> packCongFactorResponseList = new ArrayList<PackCongFactorResponse>(0);
				StringBuffer hql = new StringBuffer();
				hql.append("select PCFR.objId,PCFR.objId from PackCongFactorResponse as PCFR,BidPackage as bidPackage where PCFR.bidPackageId = bidPackage.objId and bidPackage.bid.objId=:bidId ");
				List<Object[]> objects = session.createQuery(hql.toString()).setString("bidId", bidId).list();
				if(null != objects && objects.size()>0){
					for(Object[] object:objects){
						PackCongFactorResponse packCongFactorResponse = new PackCongFactorResponse();
						packCongFactorResponse.setObjId(object[0]+"");
						packCongFactorResponseList.add(packCongFactorResponse);
					}
				}
				return packCongFactorResponseList;
			}
		});
	}
	
	/**
	 * @Description: 获取 供应商投标的指标分类(一级分类)
	 * @param supplierId 供应商ID
	 * @param packId 包件ID/项目Id
	 * @return List<CongruousFactorType>
	 * @throws Exception
	 * @Create Date 2010-9-20 下午10:42:03 By wanghz
	 */
	public List<CongruousFactorType> getBidCongruousFactorType(final String supplierId,final String packId){
		return (List<CongruousFactorType>) getHibernateTemplate().execute(new HibernateCallback(){
			public List<CongruousFactorType> doInHibernate(Session session)throws HibernateException, SQLException {
				List<CongruousFactorType> congruousFactorTypeList = null;
				StringBuffer hql = new StringBuffer();
				hql.append("select distinct congruousFactorType from CongruousFactorType as congruousFactorType ");
				hql.append(" ,CongruousFactor as congruousFactor ");
				hql.append(" ,PackCongFactorResponse as packFactorResponse ");
				hql.append(" ,BidPackage as bidPackage ");
				hql.append(" where congruousFactorType.objId = congruousFactor.factorType.objId ");
				hql.append(" and congruousFactor.objId = packFactorResponse.congFactorResponse.factorId ");
				hql.append(" and bidPackage.objId = packFactorResponse.bidPackageId ");
				hql.append(" and bidPackage.pack.objId=:packId ");
				hql.append(" and bidPackage.bid.supplier.objId=:supplierId ");
				hql.append(" and congruousFactorType.parent.objId is null ");
				hql.append(" order by congruousFactorType.sort ");
				congruousFactorTypeList = session.createQuery(hql.toString())
				.setString("supplierId", supplierId).setString("packId", packId).list();
				if (null == congruousFactorTypeList) {
					congruousFactorTypeList = new ArrayList<CongruousFactorType>(0);
				}
				return congruousFactorTypeList;
			}
		});
	}
	
	/**
	 * @Description: 获取 供应商投标的指标分类(子分类)
	 * @param supplierId 供应商ID
	 * @param packId 包件ID/项目Id
	 * @return List<CongruousFactorType>
	 * @throws Exception
	 * @Create Date 2010-9-21 下午02:39:02 By wanghz
	 */
	public List<CongruousFactorType> getgetBidCongruousFactorTypeChild(final String supplierId,final String packId){
		return (List<CongruousFactorType>) getHibernateTemplate().execute(new HibernateCallback(){
			public List<CongruousFactorType> doInHibernate(Session session)throws HibernateException, SQLException {
				List<CongruousFactorType> congruousFactorTypeChildList = null;
				StringBuffer hql = new StringBuffer();
				hql.append("select distinct congruousFactorType from CongruousFactorType as congruousFactorType ");
				hql.append(" ,CongruousFactor as congruousFactor ");
				hql.append(" ,PackCongFactorResponse as packFactorResponse ");
				hql.append(" ,BidPackage as bidPackage ");
				hql.append(" where congruousFactorType.objId = congruousFactor.factorType.objId ");
				hql.append(" and congruousFactor.objId = packFactorResponse.congFactorResponse.factorId ");
				hql.append(" and bidPackage.objId = packFactorResponse.bidPackageId ");
				hql.append(" and bidPackage.pack.objId=:packId ");
				hql.append(" and bidPackage.bid.supplier.objId=:supplierId ");
				hql.append(" and congruousFactorType.parent.objId is not null ");
				hql.append(" order by congruousFactorType.sort ");
				congruousFactorTypeChildList = session.createQuery(hql.toString())
				.setString("supplierId", supplierId).setString("packId", packId).list();
				if (null == congruousFactorTypeChildList) {
					congruousFactorTypeChildList = new ArrayList<CongruousFactorType>(0);
				}
				return congruousFactorTypeChildList;
			}
		});
	}
	
	/**
	 * 
	 * Description :根据项目获得所有指标响应与包件中间表 
	 * Create Date: 2010-10-11上午09:27:01 by liuke  Modified Date: 2010-10-11上午09:27:01 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<PackCongFactorResponse> getAllPackCongFactorResponseListByProjectId(final String projectId) {
		return (List<PackCongFactorResponse>) getHibernateTemplate().execute(new HibernateCallback(){
			public List<PackCongFactorResponse> doInHibernate(Session session) throws HibernateException, SQLException {
				    List<PackCongFactorResponse> packCongFactorResponseList = null;
				    StringBuffer hql = new StringBuffer();
				    hql.append("from PackCongFactorResponse pcf where pcf.bidPackageId in (select bp.objId from BidPackage bp where (bp.pack.objId = '"+projectId+"' or bp.pack.parentId = '"+projectId+"'))");
				    packCongFactorResponseList = session.createQuery(hql.toString()).list();
				    if(null == packCongFactorResponseList){
				    	packCongFactorResponseList = new ArrayList<PackCongFactorResponse>(0);
				    }
				    return packCongFactorResponseList;
			}
		});

	}

	
	/** 
	 * Description :删除包件指标中间表信息
	 * Create Date: 2010-12-15下午07:08:05 by liuke  Modified Date: 2010-12-15下午07:08:05 by liuke
	 * @param projectId
	 * @return  void
	 * @Exception
	 */
	public void removeAllPackCongFactorResponseByProject(final String projectId) {
		this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer();
				sql.append("delete from PackCongFactorResponse t where t.bidPackageId in (select bp.objId from BidPackage bp where bp.bid.project.objId = ? )");
				Query query  = session.createQuery(sql.toString());
				query.setString(0, projectId).executeUpdate();
				return null;
			}});
		
	}
}
