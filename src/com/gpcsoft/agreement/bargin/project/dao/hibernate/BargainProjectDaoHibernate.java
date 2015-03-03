package com.gpcsoft.agreement.bargin.project.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.project.dao.BargainProjectDao;
import com.gpcsoft.agreement.bargin.project.domain.ProjectQueryDto;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.project.dao.hibernate.ProjectDaoHibernate;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class BargainProjectDaoHibernate extends ProjectDaoHibernate implements BargainProjectDao {

	/** 
	 * Description :  查找被评价对象
	 * Create Date: 2010-10-28下午04:12:52 by yucy  Modified Date: 2010-10-28下午04:12:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getEvaluateObjectSupplier(final String buyersId ,final String projectId) throws Exception {
		
		return (Map<String, Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Map<String,Object> model = new HashMap<String, Object>();
				
				String hql=" select o.objId, o.orgName, 'b'  from OrgInfo o where o.objId in ( :ids ) and o.objId not in ( select e.org.objId from Evaluate e where e.rater.objId = :currentUserId and e.project = :projectId ) ";
				Query query = session.createQuery(hql);
				String[] ids = buyersId.split(",");
				query.setParameterList("ids", ids);
				query.setParameter("currentUserId", AuthenticationHelper.getCurrentUser(true).getObjId());
				query.setParameter("projectId", projectId);
				model.put("evaluateOrg", query.list());
				return model;
		}});
	}

	/** 
	 * Description :  项目查询数据(采购人)
	 * Create Date: 2011-6-29下午02:14:33 by yucy  Modified Date: 2011-6-29下午02:14:33 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Object> getProjectList(final Page<Object> page,final Map<String, Object> param)throws Exception {
		return (Page<Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				
				String today = UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.formatDateTime(new Date()));
				
				String preSql  = " from ecp_tender_project p"+
								" left join ecp_base_bulletin b on p.tenderid = b.tenderid "+
								" left join ecp_tender_apply_rec  recc on p.tenderid = recc.tenderid"+
									" where 1=1 ";
				if(StringUtils.hasLength((String)param.get("orgId"))){
					preSql += " and p.buyers_id = '"+(String)param.get("orgId")+"'";
				}else if(StringUtils.hasLength((String)param.get("buyerId"))){
					preSql += " and p.creuser = '"+AuthenticationHelper.getCurrentUser(true).getObjId()+"'";
				}
				
				/*************************************查询条件开始*************************************/
				if(StringUtils.hasLength((String)param.get("projName"))){//名称
					preSql += " and p.tendername like '%" +(String)param.get("projName")+ "%'";
				}
				
				if(StringUtils.hasLength((String)param.get("projCode"))){//编号
					preSql += " and p.tenderno like '%" +(String)param.get("projCode")+ "%'";
				}
				
				if(StringUtils.hasLength((String)param.get("supplierName"))){//供应商名称
					preSql += " and recc.supplyername like '%" +(String)param.get("supplierName")+ "%'";
				}
				
				if(StringUtils.hasLength((String)param.get("startTime"))){//开始时间
					preSql += " and "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime((String)param.get("startTime"))+" < p.credate";
				}
				
				if(StringUtils.hasLength((String)param.get("endTime"))){//结束时间
					preSql += " and "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime((String)param.get("endTime"))+" > p.credate";
				}
				
				if("bargaining".equals(param.get("status"))){//竞价状态
					preSql += " and ( "+today+" between p.eval_start_date and p.eval_end_date and p.processstatus = '" + ProjProcessStatusEnum.SUPPLIERS_BID + "' ) ";
				}else if("bargained".equals(param.get("status"))){
					preSql += " and ("+today+" > p.eval_end_date or p.processstatus = '" + ProjProcessStatusEnum.OPEN_BID + "' )";
				}else if("nostart".equals(param.get("status"))){
					preSql += " and (( p.eval_start_date > "+today+" or p.processstatus ='20' ) and "+today+" < p.eval_end_date  )";
				}else{
                    preSql += " and ( ("+today+" > p.eval_start_date and "+today+" < p.eval_end_date and p.processstatus = '"+ProjProcessStatusEnum.SUPPLIERS_BID+"') or ( "+today+" >p.eval_end_date or p.processstatus = '"+ProjProcessStatusEnum.OPEN_BID+"')  or ("+ today +" < p.eval_start_date or p.processstatus = '"+ProjProcessStatusEnum.DESIGNATED_PROJECT_RULE+"') )";
				}
				/*************************************查询条件结束*************************************/
				
				/*************************************排序条件*****************************************/
				preSql += " order by p.credate desc ";//创建时间倒序
				
				//查询结果
				Query query = session.createSQLQuery("select distinct p.tenderid," +
						"p.tendername," +
						"p.tenderno," +
						"substr(p.tendermethod,1,2) ," +
						"p.budget_total_money,"+
						"p.credate,"+
						"(select sum( ed.goods_total ) from eps_agree_record_detail ed where ed.project_id = p.tenderid and ed.is_deal = '1'),"+
						"(select count(rc.applyid) from ecp_tender_apply_rec rc where rc.tenderid = p.tenderid )"+
						",(case when p.eval_start_date < "+today+"  and p.eval_end_date > "+today+"  and p.processstatus = '160' then 'A' when p.eval_end_date  < "+today+" then 'C' when p.eval_start_date > "+today+" or p.processstatus ='20' then 'B' end ) bargainStatus" +
						",substr(p.usestatus,1,2)" +
						",p.buyers_name "+
						preSql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				List<ProjectQueryDto> data = new ArrayList<ProjectQueryDto>();
				for(Object[] object : (List<Object[]>)query.list()){
					ProjectQueryDto rojectQueryDto = new ProjectQueryDto();
					rojectQueryDto.setObjId((String)object[0]);//objId
					rojectQueryDto.setProjName((String)object[1]);//项目名称
					rojectQueryDto.setProjCode((String)object[2]);//项目编码
					rojectQueryDto.setEbuyMethod((String)object[3]);//采购方式
					rojectQueryDto.setBudgetTotalMoney(object[4].toString());//采购预算
					rojectQueryDto.setCreateTime(object[5].toString());//创建时间
					if(object[6]!=null){
						rojectQueryDto.setDealTotal(object[6].toString());//中标金额
					}
					if(object[6]!=null){
						rojectQueryDto.setSaveTotal(((BigDecimal)object[6]).subtract(( BigDecimal)object[4]).toString());//节省金额
					}
					rojectQueryDto.setSupplierNumber(((BigDecimal)object[7]).intValue());//报名供应商数
					rojectQueryDto.setBargainStatus(object[8].toString());//竞价状态
					rojectQueryDto.setUseStatus((String)object[9]);//使用状态
					rojectQueryDto.setSignUprecordList(session.createSQLQuery("select rec.applyid, rec.supplyerid ,rec.supplyername" +
							" from ecp_tender_apply_rec  rec where rec.tenderid = '"+ (String)object[0] +"' ").list());//报名集合
					
					rojectQueryDto.setBuyersName((String)object[10]);//采购人名称
					data.add(rojectQueryDto);
				}
				page.setData(data);
				
				//查询记录数
				query = session.createSQLQuery("select count(distinct p.tenderid )"+preSql);
				page.setTotalRowNum(new Long(query.list().get(0).toString()));
				return page;
			}
		});
	}

	/** 
	 * Description :  项目查询数据(供应商)
	 * Create Date: 2011-6-29下午02:14:33 by yucy  Modified Date: 2011-6-29下午02:14:33 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Object> getMyProjectList(final Page<Object> page, final Map<String, Object> param) throws Exception {
		return (Page<Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				
				String today = UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.formatDateTime(new Date()));
				
				String supplierId = AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId();
				
				String preSql  = " from  ecp_tender_apply_rec recc left join"+
                          " ecp_tender_project p on p.tenderid = recc.tenderid"+
                          " left join ecp_base_bulletin b on p.tenderid = b.tenderid"+
                          " where recc.tenderid = p.tenderid"+
                          " and recc.supplyerid = '"+ supplierId +"'";
				
				/*************************************查询条件开始*************************************/
				if(StringUtils.hasLength((String)param.get("projName"))){//名称
					preSql += " and p.tendername like '%" +(String)param.get("projName")+ "%'";
				}
				
				if(StringUtils.hasLength((String)param.get("projCode"))){//编号
					preSql += " and p.tenderno like '%" +(String)param.get("projCode")+ "%'";
				}
				
				if(StringUtils.hasLength((String)param.get("buyerName"))){//采购人名称
					preSql += " and  p.buyers_name like '%"+ (String)param.get("buyerName") +"%'";
				}
				
				if(StringUtils.hasLength((String)param.get("startTime"))){//开始时间
					preSql += " and "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime((String)param.get("startTime"))+" < p.credate";
				}
				
				if(StringUtils.hasLength((String)param.get("endTime"))){//结束时间
					preSql += " and "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime((String)param.get("endTime"))+" > p.credate";
				}
				
				if("bargaining".equals(param.get("status"))){//竞价状态
					preSql += " and ( "+today+" between p.eval_start_date and p.eval_end_date and p.processstatus = '" + ProjProcessStatusEnum.SUPPLIERS_BID + "' ) ";
				}else if("bargained".equals(param.get("status"))){
					preSql += " and ("+today+" > p.eval_end_date or p.processstatus = '" + ProjProcessStatusEnum.OPEN_BID + "' )";
				}else if("nostart".equals(param.get("status"))){
					preSql += " and (( p.eval_start_date > "+today+" or p.processstatus ='20' ) and "+today+" < p.eval_end_date  )";
				}else{
                    preSql += " and ( ("+today+" > p.eval_start_date and "+today+" < p.eval_end_date and p.processstatus = '"+ProjProcessStatusEnum.SUPPLIERS_BID+"') or ( "+today+" >p.eval_end_date or p.processstatus = '"+ProjProcessStatusEnum.OPEN_BID+"')  or ("+ today +" < p.eval_start_date or p.processstatus = '"+ProjProcessStatusEnum.DESIGNATED_PROJECT_RULE+"') )";
				}
				/*************************************查询条件结束*************************************/
				
				/*************************************排序条件*****************************************/
				preSql += " order by p.credate desc ";//创建时间倒序

				//查询结果
				Query query = session.createSQLQuery("select distinct p.tenderid," +
						"nvl(p.tendername,'暂无')," +
						"nvl(p.tenderno,'暂无')," +
						"substr(p.tendermethod,1,2) ," +
						"nvl(p.budget_total_money,0),"+
						"p.credate,"+
						"(select sum( ed.goods_total ) from eps_agree_record_detail ed where ed.project_id = p.tenderid and ed.is_deal = '1'),"+
						"(select count(distinct bw.buy_w_id) from  ECP_BUY_WINNER bw , ECP_BUYRESULT eb where bw.buyr_id = eb.buyr_id and bw.seller_id = '"+supplierId+"' and eb.proj_id = p.tenderid and bw.result_type = '00')," +
						" p.buyers_name"+
						",(case when p.eval_start_date < "+today+"  and p.eval_end_date > "+today+"  and p.processstatus = '160' then 'A' when p.eval_end_date  < "+today+" then 'C' when p.eval_start_date > "+today+" or p.processstatus ='20' then 'B' end ) bargainStatus" +
						preSql);
				
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				List<ProjectQueryDto> data = new ArrayList<ProjectQueryDto>();
				for(Object[] object : (List<Object[]>)query.list()){
					ProjectQueryDto rojectQueryDto = new ProjectQueryDto();
					rojectQueryDto.setObjId((String)object[0]);//objId
					rojectQueryDto.setProjName((String)object[1]);//项目名称
					rojectQueryDto.setProjCode((String)object[2]);//项目编码
					rojectQueryDto.setEbuyMethod((String)object[3]);//采购方式
					rojectQueryDto.setBudgetTotalMoney(object[4].toString());//采购预算
					rojectQueryDto.setCreateTime(object[5].toString());//创建时间
					if(object[6]!=null){
						rojectQueryDto.setDealTotal(object[6].toString());//中标金额
					}
					rojectQueryDto.setIsDeal(!"0".equals(object[7].toString()));//是否中标
					rojectQueryDto.setBuyersName((String)object[8]);//采购人名称
					rojectQueryDto.setBargainStatus(object[9].toString());//竞价状态
					
					rojectQueryDto.setRequirementMinRec(session.createSQLQuery("select rd.require_dtl_id , ri.goods_descr, min(rd.goods_total) from EPS_AGREE_RECORD_DETAIL rd"+
							" left join eps_agreement_require_item ri on ri.require_dtl_id = rd.require_dtl_id"+
							" where rd.project_id = '"+object[0]+"'"+
							" group by rd.require_dtl_id  ,ri.goods_descr").list());
					data.add(rojectQueryDto);
				}
				page.setData(data);
				
				//查询记录数
				query = session.createSQLQuery("select count(distinct p.tenderid )"+preSql);
				page.setTotalRowNum(new Long(query.list().get(0).toString()));
				return page;
			}
		});
	}

	/**
	 * 我与客户的交易项目记录
	 */
	@SuppressWarnings("unchecked")
	public Page<ProjectQueryDto> getExchangeProjectRecord(final Page<ProjectQueryDto> page,final Map<String, Object> param) throws Exception {
		return (Page<ProjectQueryDto>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String supplierOrgId = (String) param.get("supplierOrgId");////供货商机构id
				String projectCreateUserId = (String) param.get("projectCreateUserId");//项目创建人用户Id
				StringBuffer sql = new StringBuffer();
				StringBuffer sql1 = new StringBuffer();
				StringBuffer sql2 = new StringBuffer();
				
				sql1.append("select app.tenderid,p.tendername,p.tenderno,''||substr(p.tendermethod,1,2),p.credate,p.creuser,app.supplyerid from ecp_tender_apply_rec app, ecp_tender_project p ");
				sql1.append("where app.tenderid = p.tenderid and app.supplyerid = '"+supplierOrgId+"' and p.creuser = '"+projectCreateUserId+"' and p.usestatus = '01'");				
				sql2.append("select projectBuy.*, br.buyr_id, br.buyr_result from ("+sql1+") projectBuy left join ecp_buyresult br on projectBuy.tenderid = br.proj_id");				
				sql.append("select buyResult.*, ''||substr(w.result_type,1,2) from ("+sql2+") buyResult left join ecp_buy_winner w on buyResult.buyr_id = w.buyr_id and buyResult.supplyerid = w.seller_id");
				
				Query query = session.createSQLQuery(sql.toString());
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List<Object[]> objectList = query.list();
				
				List<ProjectQueryDto> data = new ArrayList<ProjectQueryDto>();
				for(Object[] object : objectList){
					ProjectQueryDto rojectQueryDto = new ProjectQueryDto();
					rojectQueryDto.setObjId((String)object[0]);//objId
					rojectQueryDto.setProjName((String)object[1]);//项目名称
					rojectQueryDto.setProjCode((String)object[2]);//项目编码
					rojectQueryDto.setEbuyMethod(object[3].toString());//采购方式
					rojectQueryDto.setCreateTime(object[4].toString());//创建时间
					rojectQueryDto.setBuyrResult((String) object[8]);
					rojectQueryDto.setIsDeal("00".equals(object[9]));
					data.add(rojectQueryDto);
				}
				page.setData(data);
				
				//查询记录数
				query = session.createSQLQuery("select count(*) from ("+sql.toString()+")");
				page.setTotalRowNum(new Long(query.list().get(0).toString()));
				return page;
			}
		});
	}
}
