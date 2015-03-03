package com.gpcsoft.agreement.bargin.bidding.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.bidding.dao.BiddingRecordDetailDao;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordDetail;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.StringUtils;

@Repository
public class BiddingRecordDetailDaoHibernate extends BaseGenericDaoHibernate<BiddingRecordDetail> implements BiddingRecordDetailDao {

	/** 
	 * Description : 获取最低报价  
	 * Create Date: 2011-5-17下午12:05:37 by yucy  Modified Date: 2011-5-17下午12:05:37 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getMinRecord(final String objId) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String sql = "select x.supplyerid,x.require_dtl_id,y.goods_total,y.is_choose,x.supplyername from "+
				" (select r.require_dtl_id, a.supplyerid ,a.supplyername from eps_agreement_require_item r , ecp_tender_apply_rec a "+
				" where r.project_id = '"+objId+"' and a.tenderid = '"+objId+"')x"+
				" left join "+
				" (select ard.suplier_id, ard.require_dtl_id,min(ard.goods_total) goods_total,ard.is_choose"+
				" from eps_agree_record_detail ard"+
				" where ard.project_id = '"+objId+"'"+
				" group by ard.suplier_id, ard.require_dtl_id,ard.is_choose) y"+
				" on x.require_dtl_id = y.require_dtl_id and  x.supplyerid = y.suplier_id"+
				" order by x.supplyerid,x.require_dtl_id ,y.is_choose desc ";
				
				Query query = session.createSQLQuery(sql.toString());
				//query.setString("projectId", objId);
				List<Object[]> result = new ArrayList<Object[]>();
				List<Object[]> list= query.list();
				
				//标志位
				String supplierId = "";
				String requireId = "";
				
				for (Object[] objects : list) {
					if(! supplierId .equals( objects[0] ) || !requireId.equals(objects[1]) || '1'==(Character)objects[3] ){
						supplierId = ( String )objects[0] ;
						requireId = ( String )objects[1] ;
						result.add(objects);
					}
				}
				return result;
			}
		});
	}
	
	/** 
	 * Description : 获取中标供应商的最低报价或选中的报价
	 * Create Date: 2011-6-13下午01:30:48 by yucy  Modified Date: 2011-6-13下午01:30:48 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getMinOrSelectRecordDetail(final String projectId,final String dealSupplier) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String sql = "select ard.suplier_id,"+
                    " ard.require_dtl_id,"+
                    " ard.goods_total,"+
                    " ard.is_choose,"+
                    " supplier.org_name,"+
                    " g.product_name || ard.service_content," +
                    " ard.sort," +
                    " g.spec," +
                    " g.goods_id," +
                    " ard.record_detail_id,"+
                    " ari.goods_total as gt"+
                    
					" from eps_agree_record_detail ard"+
					" left join goods g on ard.goods_id = g.goods_id"+
					
					" ,org_info supplier"+
					" ,eps_agreement_require_item ari "+
					
					" where " +
					" supplier.org_info_id = ard.suplier_id and "+
					" ari.require_dtl_id = ard.require_dtl_id and"+
					" ard.project_id = '"+projectId+"'"+
					" and ard.suplier_id in ( :supplier )"+
					" order by ard.suplier_id ,ard.require_dtl_id , ard.is_choose desc ,ard.goods_total ";
				
				Query query = session.createSQLQuery(sql.toString());
				//query.setString("projectId", objId);
				query.setParameterList("supplier", dealSupplier.split(","));
				List<Object[]> result = new ArrayList<Object[]>();
				List<Object[]> list= query.list();
				
				//标志位
				String supplierId = "";
				String requireId = "";
				
				for (Object[] objects : list) {
					if(! supplierId .equals( objects[0] ) || !requireId.equals(objects[1]) || '1'==(Character)objects[3] ){
						supplierId = ( String )objects[0] ;
						requireId = ( String )objects[1] ;
						result.add(objects);
					}
				}
				return result;
			}
		});
	}

	/** 
	 * Description :  获取各供应商的报价,按需求
	 * Create Date: 2011-5-16下午06:39:08 by yucy  Modified Date: 2011-5-16下午06:39:08 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> loadRecordByRequire(final String requireId ,final Map<String, Object> param) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String sql = "select ard.record_detail_id,"+
					" ard.suplier_id,"+
					" o.org_name,"+
					" ard.goods_total,"+
					" ard.is_choose,"+
					" ard.bargin_file,"+
					" g.product_name || ard.service_content, ard.sort, g.spec, g.goods_id,g.product_name, g.PRODUCT_CODE, g.PARAM_INPUT_TYPE,ard.MEMO"+
					" from eps_agree_record_detail ard"+
					" left join goods g on ard.goods_id = g.goods_id, "+
					" org_info o"+
					" where ard.suplier_id = o.org_info_id"+
					" and ard.require_dtl_id = '"+ requireId +"' "+
					" order by ";
				
				//排序条件ard.goods_total,ard.suplier_id
				StringBuilder orderHql = new StringBuilder();
				if(param.get("order")!=null) {
					String[] order = ((String)param.get("order")).split(",");
					for (String string : order) {
						orderHql.append(" ard.").append(string).append(",");
					}
				}
				
				orderHql.append( " ard.sort , ard.goods_total " );//默认排序sort
				
				Query query = session.createSQLQuery(sql.toString()+ orderHql.toString());
				List<Object[]> list= query.list();
				return list;
			}
		});
	}
	
	/** 
	 * Description :  获取供应商对需求的所有报价
	 * Create Date: 2011-5-20下午02:36:03 by yucy  Modified Date: 2011-5-20下午02:36:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getDetailBySupplierAndRequire(final String supplierId, final String requireId) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String sql2 = " select ard.record_detail_id," +
								" to_char(arh.bargin_time ,'yyyy-MM-dd HH24:mi:ss') ," +
								" arh.goods_total ," +
								" ard.is_choose, " +
								" g.product_name || ard.service_content," +
								" ard.bargin_file" +
								" ,arh.RECORD_HISTORY_ID,g.spec,g.goods_id, ard.memo"+
								" from eps_agree_record_history arh"+
								" left join eps_agree_record_detail ard on ard.record_detail_id = arh.record_detail_id"+
								" left join goods g on g.goods_id = ard.goods_id"+
								" where ard.require_dtl_id = :requireId"+
								" and ard.suplier_id = :supplierId order by ard.record_detail_id, arh.goods_total ";
				Query query1 = session.createSQLQuery(sql2);
				query1.setString("requireId", requireId);
				query1.setString("supplierId", supplierId);
				
				//历史
				List<Object[]> listHistory= query1.list();
				
				return listHistory;
			}
		});
	}

	/** 
	 * Description : 更新选中状态 并返回数据与状态
	 * Create Date: 2011-5-17下午06:43:12 by yucy  Modified Date: 2011-5-17下午06:43:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> updateRecordChooseStatus(final String changedIdandValue ,final String requireId)throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				
				//定义一个结果
				List<Object[]> resultList = new ArrayList<Object[]>();
				
				//供应商标志位
				String supplierId = "";
				
				int updateCont = 0;
				if( StringUtils.hasLength(changedIdandValue) ){
					for(String idandValue : changedIdandValue.split(",")){
						
						String detailId =  idandValue.split(":")[0];
						String chooseValue =  idandValue.split(":")[1];
						String supplier =  idandValue.split(":")[2];
						String price =  idandValue.split(":")[3];
							
						String sql = " update eps_agree_record_detail ard set ard.is_choose = "+chooseValue+" where ard.record_detail_id = '" + detailId + "' ";
						Query query = session.createSQLQuery( sql.toString() );
						updateCont += query.executeUpdate();
						
						//往结果集填充
						if( !supplierId.equals(supplier)  ){
							resultList.add( new String[]{ detailId, supplier, requireId , chooseValue, price } );
							
						}else if ( chooseValue.equals("1") ){//设置最后一个
							resultList.set(resultList.size()-1, new String[]{ detailId, supplier, requireId , chooseValue, price } );
						}
						supplierId = supplier;//标志位后移 
					}
				}
				return resultList;
			}
		});
	}

	/** 
	 * Description :  保存排序
	 * Create Date: 2011-5-24下午02:31:18 by yucy  Modified Date: 2011-5-24下午02:31:18 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean saveSort(final String[] orderArray) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				int updateCont = 0;
				for(String idandValue : orderArray){
						Query query = session.createSQLQuery("update eps_agree_record_detail ard set ard.sort = "+ idandValue.split("__")[1]+" where ard.record_detail_id = '"+ idandValue.split("__")[0]+"'");
						updateCont += query.executeUpdate();
				}
				return updateCont>0;
			}
		});
	}

	/**
	 * Description :  获取我报过的最低价
	 * Create Date: 2011-5-26上午10:39:06 by yucy  Modified Date: 2011-5-26上午10:39:06 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getMyMinRecord(final String projectId ,final String orgId) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String sql2 = " select "+
					" ard.record_detail_id ," +
					" ari.require_dtl_id ," +
					" ari.goods_descr||g.product_name," +
					" ard.goods_total," +
					" ari.goods_total," +
					" ''," +
					" g2.product_name || ard.service_content," +
					" g2.goods_id ," +
					" g2.spec," +
					" ard.bargin_file,"+
					" ari.goods_id"+
				" from eps_agreement_require_item ari"+
				" left join eps_agree_record_detail ard on ard.require_dtl_id = ari.require_dtl_id"+
				" and ard.suplier_id = '"+orgId+"'"+
				" left join goods g2 on g2.goods_id = ard.goods_id"+
				" left join goods g on g.goods_id = ari.goods_id"+
				" where ari.project_id = '"+projectId+"'"+
				" order by ari.require_dtl_id,ard.goods_total ";
				
				Query query = session.createSQLQuery(sql2);
				
				List<Object[]> list = query.list();
				
				//标记与数目
				int index = 0;
				int count = 0;
				
				for (int i = 0; i < list.size(); i++) {
					if(i == list.size()-1 ){
						list.get(index)[5] = count+1;
					}else if( ! list.get(i)[1].equals( list.get(i+1)[1] ) ){
						list.get(index)[5] =  count+1;
						count = 0;
						index = i+1;
					}else{
						count++;
					}
				}
				return list;
			}
		});
	}
	
	/** 
	 * Description :  根据项目id、需求条目id、供应商id查询报价
	 * Create Date: 2011-5-26下午03:08:08 by sunl  Modified Date: 2011-5-26下午03:08:08 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getBiddingDetailList(final Map<String,Object> params) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append(" select d.record_detail_id,nvl(d.service_content, g.PRODUCT_NAME),");
				sql.append(" to_char(d.bargin_time,'yyyy-MM-dd hh24:mi:ss'),d.goods_price,d.goods_total,");
				sql.append(" nvl(d.bargin_file,' '),");
				sql.append(" nvl(d.memo,' '),");
				sql.append(" nvl(d.goods_id,' '),");
				sql.append(" o.org_name,d.require_dtl_id, ");
				sql.append(" nvl(g.PRODUCT_NAME,' '), ");
				sql.append(" nvl(g.PRODUCT_CODE,' '), ");
				sql.append(" nvl(g.PARAM_INPUT_TYPE,' '), ");
				sql.append(" d.suplier_id");
				sql.append(" from eps_agree_record_detail d left join goods g on g.goods_id=d.goods_id ");
				sql.append(" left join org_info o on o.org_info_id=d.suplier_id");
				sql.append(" where 1=1 ");
				
//				//过滤掉已剔除的供应商的报价
//				if(!StringUtils.hasLength((String)params.get("supplierId")) && StringUtils.hasLength((String)params.get("projId"))) {
//					sql.append(" and d.suplier_id not in (");
//					sql.append(" select es.SUPPLIER_ID from EPS_AGREE_ELIMINATE_SUPPLYER es");
//					sql.append(" where es.PROJECT_ID=:projId");
//					sql.append(" )");
//				}
				
				if(StringUtils.hasLength((String)params.get("projId"))) {
					sql.append(" and d.project_id=:projId");
				}
				if(StringUtils.hasLength((String)params.get("requireId"))) {
					sql.append(" and d.require_dtl_id=:requireId");
				}
				if(StringUtils.hasLength((String)params.get("supplierId"))) {
					sql.append(" and d.suplier_id=:supplierId");
				}
				if(StringUtils.hasLength((String)params.get("orderType"))) {
					sql.append(" order by d.").append(params.get("orderType").toString());
				}
				
				Query query = session.createSQLQuery(sql.toString());
				
				if(StringUtils.hasLength((String)params.get("projId"))) {
					query.setString("projId", (String)params.get("projId"));
				}
				if(StringUtils.hasLength((String)params.get("requireId"))) {
					query.setString("requireId", (String)params.get("requireId"));
				}
				if(StringUtils.hasLength((String)params.get("supplierId"))) {
					query.setString("supplierId", (String)params.get("supplierId"));
				}
				
				return query.list();
			}
		});
	}

	/** 
	 * Description :  获取供应商需求条目的排名
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getMyRanking(final String projId, final String requireId,final String supplierId) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("select t.Suplier_Id,T.Require_Dtl_Id, MIN(T.GOODS_PRICE) price from EPS_AGREE_RECORD_DETAIL t where 1=1 ");
				if(StringUtils.hasLength(projId)) {
					sql.append("and t.project_id=:projId ");
				}
				if(StringUtils.hasLength(requireId)) {
					sql.append(" and t.require_dtl_id in (:requireId) ");
				}
				sql.append("group by t.Suplier_Id , T.Require_Dtl_Id order by MIN(T.GOODS_PRICE) asc ");
				
				Query query = session.createSQLQuery(sql.toString());
				if(StringUtils.hasLength(projId)) {
					query.setString("projId", projId);
				}
				if(StringUtils.hasLength(requireId)) {
					query.setParameterList("requireId", requireId.split(","));
				}
				return query.list();
			}
		});
	}

	/** 
	 * Description :  需求条目最低报价[根据项目、供应商、需求条目]
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getRequireMinPrice(final String projId,final String requireId, final String supplierId) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append(" select t.require_dtl_id,min(t.goods_total) from eps_agree_record_detail t where 1=1 ");
				sql.append(" and t.project_id=:projId ");
				if(StringUtils.hasLength(requireId)) {
					sql.append(" and t.require_dtl_id in (:requireId) ");
				}
				if(StringUtils.hasLength(supplierId)) {
					sql.append(" and t.suplier_id=:supplierId ");
				}
				sql.append(" group by t.require_dtl_id ");
				
				Query query = session.createSQLQuery(sql.toString());
				query.setString("projId", projId);
				if(StringUtils.hasLength(requireId)) {
					query.setParameterList("requireId", requireId.split(","));
				}
				if(StringUtils.hasLength(supplierId)) {
					query.setString("supplierId", supplierId);
				}
				return query.list();
			}
		});
	}

	/** 
	 * Description :  获取最低报价(确认成交供应商)
	 * Create Date: 2011-6-1上午09:55:47 by yucy  Modified Date: 2011-6-1上午09:55:47 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getMinRecordAndDetail(final String projectId) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("select ard.suplier_id,")
				.append(" ard.require_dtl_id,")
				.append(" ard.goods_total,")
				.append(" ard.is_choose,")
				.append(" o.org_name,")
				.append(" g.goods_id,")
				.append(" g.spec,")
				.append(" g.product_name,")
				.append(" ard.service_content")
				.append(" from eps_agree_record_detail ard ")
				.append(" left join goods g on g.goods_id = ard.goods_id")
				.append(" , org_info o")
				.append(" where ard.suplier_id = o.org_info_id")
				.append(" and ard.project_id = '").append( projectId ).append("'")
				.append(" order by ard.suplier_id ,ard.require_dtl_id , ard.is_choose desc ,ard.goods_total");
				
				Query query = session.createSQLQuery(sql.toString());

				List<Object[]> result = new ArrayList<Object[]>();
				List<Object[]> list= query.list();
				
				//标志位
				String supplierId = "";
				String requireId = "";
				
				for (Object[] objects : list) {
					if(! supplierId .equals( objects[0] ) || !requireId.equals(objects[1]) || '1'==(Character)objects[3] ){
						supplierId = ( String )objects[0] ;
						requireId = ( String )objects[1] ;
						result.add(objects);
					}
				}
				return result;
			}
		});
	}

	/**
	 * Description :  更新为成交报价
	 * Create Date: 2011-6-13下午04:17:00 by yucy  Modified Date: 2011-6-13下午04:17:00 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BiddingRecordDetail> updateRecordDetailForResult(final String detailIds) throws Exception {
		return (List<BiddingRecordDetail>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createSQLQuery(" update eps_agree_record_detail ard set ard.is_deal = 1 where ard.record_detail_id in (:detailIds) ");
				
				query.setParameterList("detailIds", detailIds.split(","));
				
				query.executeUpdate();
				
				Query query1 = session.createQuery(" select ard from BiddingRecordDetail ard where ard.objId in (:detailIds) ");
				query1.setParameterList("detailIds", detailIds.split(","));
				
				return  query1.list();
			}
		});
	}

	/** 
	 * Description :  获取成交报价条目(按供应商)
	 * Create Date: 2011-6-14上午10:49:12 by yucy  Modified Date: 2011-6-14上午10:49:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<BiddingRecordDetail> getDealRecordDetail(final Map<String, Object> param) throws Exception {
		return (List<BiddingRecordDetail>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String projectId = (String)param.get("projectId");
				String supplierId = (String)param.get("supplierId");
				
				String objIds = (String)param.get("objIds");
				
				String hqlString = " select ard from BiddingRecordDetail ard where 1=1 " ;
				if(StringUtils.hasLength(projectId)){
					hqlString += " and ard.project.objId= '"+projectId+"' ";
				}
				if(StringUtils.hasLength(supplierId)){
					hqlString += " and ard.supplier.objId = '"+supplierId+"'";
				}
				
				if(StringUtils.hasLength(objIds)){
					hqlString += " and ard.objId in (";
					for(String objId : objIds.split(",")){
						hqlString+="'"+objId+"',";
					}
					
					hqlString += "'-1')";
				}
				
				hqlString += " and ard.isDeal = :isDeal ";
		
				Query query = session.createQuery(hqlString);
		
				query.setBoolean("isDeal", true);		
				
				return  query.list();
			}
		});
	}

	/** 
	 * Description :  获取供应商报价排名数据
	 * Create Date: 2011-7-1上午09:59:34 by likg  Modified Date: 2011-7-1上午09:59:34 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getBiddingRankChartDate(final Map<String, Object> param) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String requireItemId = (String) param.get("requireItemId"); //需求条目Id
				String bargainTurnId = (String) param.get("bargainTurnId"); //报价轮次Id
				
				String sql = "" +
					"select rd.SUPLIER_ID, min(rd.GOODS_PRICE) || '' mp" +
					" from EPS_AGREE_RECORD_DETAIL rd " +
					" join EPS_AGREE_RECORD_HISTORY rh on rh.RECORD_DETAIL_ID = rd.RECORD_DETAIL_ID" +
					" and rd.REQUIRE_DTL_ID = :requireItemId" +
					" and rh.BARGAIN_TURN_ID = :turnId" +
					" group by rd.SUPLIER_ID" +
					" order by min(rd.GOODS_PRICE) asc";
				
				Query query = session.createSQLQuery(sql);
				query.setParameter("requireItemId", requireItemId);
				query.setParameter("turnId", bargainTurnId);
				
				return query.list();
			}
		});
	}
}
