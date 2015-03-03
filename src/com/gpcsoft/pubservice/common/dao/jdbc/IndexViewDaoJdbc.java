package com.gpcsoft.pubservice.common.dao.jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.dao.jdbc.BaseDaoJDBC;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.pubservice.common.dao.IndexViewDao;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class IndexViewDaoJdbc extends BaseDaoJDBC<GpcBaseObject> implements IndexViewDao {

	/** 
	 * Description : 获得与项目有关的统计信息 
	 * Create Date: 2010-8-18下午04:47:26 by guoyr  Modified Date: 2010-8-18下午04:47:26 by guoyr
	 * @param 项目的总数,订单数,合同数,议价数,采购总额  
	 * @return  
	 * @Exception   
	 */
	public Map<String,String> getProjectStatistics() throws Exception{
		final Map<String, String> map = new HashMap<String, String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ");
		
		// 项目数
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count (*)", "0") + " p_c from ecp_tender_project), ");
		
		// 订单数
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count (*)", "0") + " o_c from eps_agreement_order), ");
		
		// 合同数
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count (*)", "0") + " c_c from ecp_pub_contract), ");
		
		// 项目的采购金额总和+合同的采购金额和
		sql.append("(select m1 + m2 t_m from (select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("sum(t.goods_total)", "0") + " m1 from eps_agreement_order t  ),(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("sum(t.budget_total_money)", "0") + " m2 from ecp_tender_project t )  ),");
		
		//今日发布的采购项目数量
		sql.append("( select nvl(count(ebb.BULLETIN_ID), 0) t_n from ecp_base_bulletin ebb where to_char(ebb.credate, 'yyyy-MM-dd') = to_char(sysdate, 'yyyy-MM-dd') ),");
		
		//已成交的项目
		sql.append("( select nvl(count(br.buyr_id),0) t_c from ECP_BUYRESULT br where br.buyr_result = '03' )");
		
		this.getJdbcTemplate().query(
				sql.toString(),
                new Object[]{},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                    	map.put("projectCount", rs.getString("p_c"));// 项目总数
            			map.put("orderCount", rs.getString("o_c"));// 订单总数
            			map.put("contractCount", rs.getString("c_c"));// 合同总数
            			map.put("totalMoney", Double.parseDouble(rs.getString("t_m"))/1000+"");// 采购总金额
            			
            			map.put("todayCount", rs.getString("t_n"));// 今日发布的项目数量
            			map.put("dealCount", rs.getString("t_c"));// 成交的项目
                    }
                });
		return map;
	}
	
	/** 
	 * Description : 获得供应商的统计信息
	 * Create Date: 2010-8-18下午04:47:26 by guoyr  Modified Date: 2010-8-18下午04:47:26 by guoyr
	 * @param 供应商的总数,已审核通过的总数,待审核的总数,最新注册的供应商(7个自然日内)  
	 * @return  
	 * @Exception   
	 */
	public Map<String,String> getSupplierStatistics() throws Exception {
		final Map<String, String> map = new HashMap<String, String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ");
		String newData = UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date(DateUtil.format(DateUtil.addDay(new Date(), -7)));

		// 供应商总数
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count (*)", "0") + " total from org_info o where o.supplier_id is not null and o.use_status != " + OrganizationEnum.USE_INVALID + " ), ");
		
		// 待审核供应商总数
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count (*)", "0") + " await from ( select * from spl_supplier s ,org_info o where s.supplier_id = o.supplier_id and s.audit_status = ? and o.use_status != " + OrganizationEnum.USE_INVALID + "  ) ), ");
		
		// 审核通过的供应商总数
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count (*)", "0") + " pass from ( select * from spl_supplier s ,org_info o where s.supplier_id = o.supplier_id and s.audit_status = ? and o.use_status != " + OrganizationEnum.USE_INVALID + "  ) ), ");
		
		// 最新注册的供应商总数
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count (*)", "0") + " newCount from ( select * from spl_supplier s ,org_info o where s.supplier_id = o.supplier_id and  o.use_status != " + OrganizationEnum.USE_INVALID + " and  s.create_time <= " + newData + " ))");
		
			 
		this.getJdbcTemplate().query(
				sql.toString(),
                new Object[]{OrganizationEnum.AWAIT_EXAM, OrganizationEnum.PASS_EXAM},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                    	map.put("allCount", rs.getString("total"));// 供应商总数
            			map.put("awaitCount", rs.getString("await"));// 待审核供应商总数
            			map.put("passCount", rs.getString("pass"));// 审核通过的供应商总数
            			map.put("newCount", rs.getString("newCount"));// 最新注册的供应商总数
                    }
                });
		return map;
	}
	
	/** 
	 * Description : 获得代理机构的统计信息
	 * Create Date: 2010-8-18下午04:47:26 by guoyr  Modified Date: 2010-8-18下午04:47:26 by guoyr
	 * @param 代理机构的总数,已审核通过的总数,待审核的总数,最新注册的代理机构总数(7个自然日内)  
	 * @return  
	 * @Exception   
	 */
	public Map<String,String> getAgencyStatistics() throws Exception {
		final Map<String, String> map = new HashMap<String, String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ");
		String newData = UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date(DateUtil.format(DateUtil.addDay(new Date(), -7)));

		// 代理机构总数
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count (*)", "0") + " total from org_info o where o.agency_id is not null and o.use_status != " + OrganizationEnum.USE_INVALID + " ), ");
		
		// 待审核代理机构总数
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count (*)", "0") + " await from ( select * from agency_agent s ,org_info o where s.agent_id = o.agency_id and s.audit_status = ? and o.use_status != " + OrganizationEnum.USE_INVALID + "  )), ");
		
		// 审核通过的代理机构总数
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count (*)", "0") + " pass from ( select * from agency_agent s ,org_info o where s.agent_id = o.agency_id and s.audit_status = ? and o.use_status != " + OrganizationEnum.USE_INVALID + "  )), ");
		
		// 最新注册的代理机构总数
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count (*)", "0") + " newCount from ( select * from agency_agent s ,org_info o where s.agent_id = o.agency_id  and o.use_status != " + OrganizationEnum.USE_INVALID + " and s.create_time <= " + newData + " ))");
		
			 
		this.getJdbcTemplate().query(
				sql.toString(),
                new Object[]{OrganizationEnum.AWAIT_EXAM, OrganizationEnum.PASS_EXAM},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                    	map.put("allCount", rs.getString("total"));// 代理机构总数
            			map.put("awaitCount", rs.getString("await"));// 待审核代理机构总数
            			map.put("passCount", rs.getString("pass"));// 审核通过的代理机构总数
            			map.put("newCount", rs.getString("newCount"));// 最新注册的代理机构总数
                    }
                });
		return map;
	}

	/** 
	 * Description :  获取系统统计信息
	 * Create Date: 2010-10-28下午05:06:56 by likg  Modified Date: 2010-10-28下午05:06:56 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, String> getStatisticsInfo() throws Exception 
	{
		final Map<String, String> map = new HashMap<String, String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ");
		
		//交易总额
		sql.append(" (select sum(nvl(o.GOODS_TOTAL, 0)) o_m from EPS_AGREEMENT_ORDER o join EPS_PUB_CONTRACT c on o.CONTRACT_ID = c.CONTRACT_ID ");
		sql.append(" and o.USE_STATUS = '").append(CommonEnum.USER_STATUS_FORMAL).append("' ");
		sql.append(" and c.USE_STATUS = '").append(CommonEnum.USER_STATUS_FORMAL).append("'), ");
		
		//加盟供应商总数
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count (*)", "0") + " s_c from SPL_SUPPLIER s ");
		sql.append(" where s.AUDIT_STATUS = '").append(OrganizationEnum.PASS_EXAM).append("'), ");
		
		//加盟采购人总数
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count (*)", "0") + " b_c from BUY_BUYER b ");
		sql.append(" join ORG_INFO o on b.ORG_INFO_ID = o.ORG_INFO_ID ");
		sql.append(" where o.USE_STATUS = '" ).append(OrganizationEnum.USE_VALID).append("'");
		sql.append(" and o.IS_OFF = '" ).append(OrganizationEnum.ENABLE).append("'");
		sql.append(" and b.AUDIT_STATUS = '").append(OrganizationEnum.PASS_EXAM).append("')");
		
		this.getJdbcTemplate().query(
				sql.toString(),
                new Object[]{},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException 
                    {
                    	map.put("bargainTotalMoney", rs.getString("o_m")); //交易总额
            			map.put("supplierCount", rs.getString("s_c")); //加盟供应商总数
            			map.put("buyerCount", rs.getString("b_c")); //加盟采购人总数
                    }
                });
		return map;
	}
}
