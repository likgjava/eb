package com.gpcsoft.epp.negotationrecord.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.negotationrecord.dao.NegotationRecordDao;
import com.gpcsoft.epp.negotationrecord.domain.NegotationRecord;

@Repository
public class NegotationRecordDaoHibernate extends BaseGenericDaoHibernate<NegotationRecord> implements NegotationRecordDao {

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
		StringBuffer preHql = new StringBuffer("select t from NegotationRecord t where 1=1");
		StringBuffer whereHql = new StringBuffer();
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if("subProjectId".equals(queryParam.getName())&&(String)queryParam.getValue()!=""&&(String)queryParam.getValue()!=null){
					whereHql.append(" and t.subProject.objId = '"+(String)queryParam.getValue()+"' ");
				}
				if("tenderId".equals(queryParam.getName())&&(String)queryParam.getValue()!=""&&(String)queryParam.getValue()!=null){
					whereHql.append(" and t.tenderId = '"+(String)queryParam.getValue()+"' ");
				}
				if("supplierName".equals(queryParam.getName())&&(String)queryParam.getValue()!=""&&(String)queryParam.getValue()!=null){
					whereHql.append(" and t.supplierName like '%"+(String)queryParam.getValue()+"%' ");
				}
			}
		}
		Page<NegotationRecord> pageList = this.findbyHql(preHql.toString()+whereHql.toString(), page.getStart(), page.getPageSize(), NegotationRecord.class);
		return pageList;
	}
	
	/**
	 * FuncName: getNegotationRecordList
	 * Description : List<NegotationRecord>
	 * @param subProjecId;项目或包组主键
	 * @param supplierId:供应商主键
	 * @return List<NegotationRecord>
	 * @author: shenjz
	 * @Create Date:2011-7-29  上午11:29:53
	 * @Modifier: shenjz
	 * @Modified Date:2011-7-29  上午11:29:53
	 */
	public List<NegotationRecord> getNegotationRecordList(String subProjecId,String supplierId){
		List<NegotationRecord> negotationRecordList = this.findbyHql("from NegotationRecord t where 1=1 " +
				"and (t.tenderId='"+subProjecId+"' or t.subProject.objId = '"+subProjecId+"') " +
								"and t.supplier.objId='"+supplierId+"' " +
										"order by t.createTime desc");
		return  negotationRecordList;
	}
	/** 
	 * Description :根据包ID和项目ID查询谈判记录对象  
	 * Create Date: 2011-09-22 14:00 by shengn
	 * @param   subProjectId 包组ID
	 * @param   projectId 包组ID
	 * @return  
	 * @Exception
	 */
	public List<NegotationRecord> searchNegotationRecordListBySubProjId(String subProjectId,String projectId){	
		if(subProjectId!=null){
			return this.findbyHql(" from NegotationRecord r where r.subProject.objId=? ", new Object[]{subProjectId});
		}else{
			return this.findbyHql(" from NegotationRecord r where r.tenderId=? ", new Object[]{projectId});
		}
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
		StringBuffer sql = new StringBuffer();
		sql.append("select v.neg_record_total");
		sql.append("  from (select t.neg_record_total, t.NEG_RECORD_TIME");
		sql.append("          from ECP_NEGOTIATION_RECORD t");
		sql.append("         where t.sub_tenderid = '"+subProjectId+"'");
		sql.append("           and t.suplier_id = '"+supplierId+"'");
		sql.append("         order by t.NEG_RECORD_TIME desc) v");
		sql.append(" where rownum = 1");
		
		List<BigDecimal> result = getSession().createSQLQuery(sql.toString()).list();
		if(result == null || result.size() == 0)
			return null;
		else
			return result.get(0);
	}
}
