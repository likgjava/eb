package com.gpcsoft.pubservice.mydesktop.dao.jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.agreement.order.enumeration.OrderEnum;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.dao.jdbc.BaseDaoJDBC;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.common.enumeration.CommonEnum;
import com.gpcsoft.pubservice.mydesktop.dao.ModelIndexDao;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class ModelIndexDaoJdbc extends BaseDaoJDBC<GpcBaseObject> implements ModelIndexDao {

	/**
	 * 获得采购人的任务信息
	 */
	public Map<String, String> getBuyerTask(String userId, String orgId) throws Exception{
		final Map<String, String> map = new HashMap<String, String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ");
		
		//待提交订单 00
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count(ORDER_ID)", "0") + " b_o from EPS_AGREEMENT_ORDER o where o.CRE_USER_ID=? and o.B_CONFIRM_STATUS = '")
					.append(CommonEnum.CONFIRM_STATUS_WAIT).append("'),");
		
		//待确认合同 00
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count(CONTRACT_ID)", "0") + " b_c from EPS_PUB_CONTRACT c where c.BUYER_ID=? and c.B_CONFIRM_STATUS = '")
					.append(CommonEnum.CONFIRM_STATUS_WAIT).append("' and c.S_CONFIRM_STATUS != '")
					.append(CommonEnum.CONFIRM_STATUS_WAIT).append("'),");
		//进行中议价 01
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count(tenderid)", "0") + " b_b from ecp_tender_project p where p.processstatus = '")
		   .append(ProjProcessStatusEnum.SUPPLIERS_BID).append("' and p.creuser =? ")
		   .append(" and p.eval_start_date < ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()))
		   .append(" and p.eval_end_date > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()))
		   .append(" and p.tendermethod = '").append(EbuyMethodEnum.TALK).append("' and p.USESTATUS !='").append(CommonEnum.CONFIRM_STATUS_CANCEL).append("'), ");
		
		//进行中的竞价 非280
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count(tenderid)", "0") + " b_b_i from ecp_tender_project p where p.processstatus = '")
		   .append(ProjProcessStatusEnum.SUPPLIERS_BID).append("' and p.creuser =? ")
		   .append(" and p.eval_start_date < ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()))
		   .append(" and p.eval_end_date > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()))
		   .append(" and p.tendermethod = '").append(EbuyMethodEnum.COMPETITION).append("' and p.USESTATUS !='").append(CommonEnum.CONFIRM_STATUS_CANCEL).append("'), ");
		
		//进行中的反拍 非280
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count(tenderid)", "0") + " b_r from ecp_tender_project p where p.processstatus != '")
			.append(ProjProcessStatusEnum.PUT_ON_RECORDS).append("' and p.creuser =? ")
			.append("and p.useStatus = '").append(CommonEnum.USER_STATUS_CONFIRM).append("' ")
			.append("and p.PROCESSSTATUS < '").append(ProjProcessStatusEnum.CALIBRATION_BID).append("' ")
			.append("and p.TenderMethod = '").append(EbuyMethodEnum.REVERSE).append("'), ");
		
		//我的购物车
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count(CART_DTL_ID)", "0") + " b_s from eps_agreement_cart_item i join EPS_AGREEMENT_CART c ")
			.append("on c.CART_ID = i.CART_ID where c.CRE_USER_ID =?), ");
		
		//进行中的采购需求
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count(PURCHASE_REQUIRE_ID)", "0") + " r_s from EPS_PURCHASE_REQUIREMENT r ")
			.append(" where r.ENDTIME > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()))
			.append(" and r.CREATE_USER =?)");
			 
		this.getJdbcTemplate().query(
				sql.toString(),
                new Object[]{userId,orgId,userId,userId,userId,userId,userId},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
            			map.put("BO", rs.getString("b_o"));//待提交订单
            			map.put("BC", rs.getString("b_c"));//待确认合同
            			map.put("BB", rs.getString("b_b"));//进行中议价
            			map.put("BBI", rs.getString("b_b_i"));//进行中的竞价
            			map.put("BR", rs.getString("b_r"));//进行中的反拍
            			map.put("BS", rs.getString("b_s"));//购物车数据
            			map.put("RS", rs.getString("r_s"));//采购需求
                    }
                });
		return map;
	}
	
	/**
	 * 获得供应商的任务信息
	 */
	public Map<String, String> getSupplierTask(String userId, String orgId) throws Exception{
		final Map<String, String> map = new HashMap<String, String>();
		
		StringBuilder sql = new StringBuilder();
		//待确认订单 00 00
		sql.append("select * from ")
		   .append("(select nvl(count(ORDER_ID),0) s_o from EPS_AGREEMENT_ORDER o where o.SUPPLIER_ID=? and o.USE_STATUS = '")
		   .append(CommonEnum.USER_STATUS_DRAEF)
		   .append("' and o.S_CONFIRM_STATUS = '").append(CommonEnum.CONFIRM_STATUS_WAIT)
		   .append("' and o.B_CONFIRM_STATUS = '").append(CommonEnum.CONFIRM_STATUS_NEGOTIATE).append("'),");
		
		//待确认合同 00 00
		sql.append("(select nvl(count(CONTRACT_ID),0) s_c from EPS_PUB_CONTRACT c where c.SUPPLIER_ID=? and c.S_CONFIRM_STATUS = '")
		   .append(CommonEnum.CONFIRM_STATUS_WAIT).append("' and c.B_CONFIRM_STATUS != '")
		   .append(CommonEnum.CONFIRM_STATUS_WAIT)
		   .append("' and c.B_CONFIRM_STATUS != '").append(CommonEnum.CONFIRM_STATUS_CANCEL)//by yucy退回的不代表待确认 有争议
		   .append("'),");
		
		//我的有效商品(指定给我机构维护的商品)
		sql.append("(select nvl(count(GOODS_ID),0) s_g from goods t ")
		   .append(", goods_modifier m where m.goods_brand_id = t.goods_brand_id  and m.goods_class_id =  t.goods_class_id ")
		   .append("and m.supplier_id=? and t.manger_status = '01' and t.sell_status = '01'")
		   .append("),");
		
		//进行中的竞价项目
		sql.append("(select nvl(count(b.APPLYID),0) s_p from ECP_TENDER_APPLY_REC b , ECP_Tender_Project p where b.TENDERID = p.tenderid ")
			.append(" and p.eval_start_date < ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()))
			.append(" and p.eval_end_date > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()))
			.append(" and p.tendermethod = '").append(EbuyMethodEnum.COMPETITION).append("' ")
			.append(" and p.processstatus = '").append(ProjProcessStatusEnum.SUPPLIERS_BID).append("' ")
			.append(" and b.SUPPLYERID =?),");
		
		//进行中议价 01
		sql.append("(select nvl(count(b.APPLYID),0) s_b from ECP_TENDER_APPLY_REC b , ECP_Tender_Project p where b.TENDERID = p.tenderid ")
			.append(" and p.eval_start_date < ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()))
			.append(" and p.eval_end_date > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()))
			.append(" and p.tendermethod = '").append(EbuyMethodEnum.TALK).append("' ")
			.append(" and b.SUPPLYERID =?),");
		
		//进行中采购需求
		sql.append("(select nvl(count(rg.EPS_PURCHASE_REQ_REG_ID),0) s_r from EPS_PURCHASE_REQUIREMENT_REG rg join EPS_PURCHASE_REQUIREMENT r on r.PURCHASE_REQUIRE_ID=rg.PURCHASE_REQUIRE_ID ")
		.append(" where r.ENDTIME > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()))
		.append(" and rg.ORG_INFO_ID=?)");
		
		this.getJdbcTemplate().query(
				sql.toString(),
                new Object[]{orgId,orgId,orgId,orgId,orgId,orgId},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
            			map.put("SO", rs.getString("s_o"));//待确认订单
            			map.put("SC", rs.getString("s_c"));//待确认合同
            			map.put("SG", rs.getString("s_g"));//我的商品
            			map.put("SP", rs.getString("s_p"));//进行中的竞价
            			map.put("SB", rs.getString("s_b"));//进行中议价
            			map.put("SR", rs.getString("s_r"));//进行中采购需求
                    }
                });
		return map;
	}
	
	/** 
	 * Description :  获得供应商的推荐公告信息（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   根据供应商的投标范围及类别
	 * @return  推荐公告信息
	 * @Exception   
	 */
	public List<Map<String,Object>> getRecommendBulletin(String bidForRangeName) throws Exception{
		//拆分投标范围及类别拼到sql语句中
		
		StringBuilder sql = new StringBuilder();
		
		//推荐公告 01(采购公告) 01(审核通过已发布)
		String cellSql = "";
		if(StringUtils.hasLength(bidForRangeName)){
			String[] bidFor =  bidForRangeName.split(",");
			cellSql += " and ( ";
			for(int i= 0; i<bidFor.length; i++){
				if(i==0){
					cellSql += " r.pur_category_id like '%"+bidFor[i]+"%'";
				}else{
					cellSql += " or r.pur_category_id like '%"+bidFor[i]+"%'";
				}
			}
			cellSql += " ) ";
		}
		
		sql.append("select * from ( ")
		 .append("select distinct b.bulletin_id,b.title,b.reldate,sysdate - 1 olddate from ")
		 .append(" ecp_tender_project p,")
		 .append(" ecp_base_bulletin b ,")
		 .append(" eps_agreement_require_item r,")
		 .append(" org_info o,")
		 .append(" spl_supplier s")
		 .append(" where r.project_id = b.tenderid")
		 .append(" and b.tenderid = p.tenderid")
		 .append(" and o.supplier_id = s.supplier_id")
		 .append(cellSql)
		 .append(" and o.org_info_id = '"+AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()+"'")
		 .append(" and b.bulletin_type = '"+CommonEnum.CONFIRM_STATUS_NEGOTIATE+"'")
		 .append(" and p.usestatus = '"+CommonEnum.USER_STATUS_CONFIRM+"'")
		 .append(" and p.tendermethod in ( '"+ EbuyMethodEnum.COMPETITION +"','"+ EbuyMethodEnum.TALK +"')")//小额过滤竞价和议价
		 .append(" and b.auditstatus = '"+CommonEnum.CONFIRM_STATUS_NEGOTIATE+"' order by b.reldate desc ")
		 .append(" ) where rownum <=5");
		
		//如果供应商的投标范围字段为空，则查询最新的5条公告
		if(!StringUtils.hasLength(bidForRangeName)){
			sql.delete(0, sql.length());
			sql.append("select b.bulletin_id,b.title,b.reldate,sysdate - 1 olddate from ecp_base_bulletin b where rownum <=5 order by b.reldate desc ");
		}
		return this.getJdbcTemplate().queryForList(sql.toString());
	}
	
	/** 
	 * Description :  获得采购人桌面的我创建的竞价项目列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   采购人的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getBuyerBids(String orgId,String userId) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		//采购人桌面的我创建的竞价项目列表 非280
//		sql.append("select * from ( ")
//		   .append("select p.tenderid,p.tenderno,p.tendername,p.credate,p.EVAL_START_DATE,p.EVAL_END_DATE,p.TenderMethod,p.SIGN_UP_START_DATE,p.SIGN_UP_END_DATE,p.ProcessStatus from ecp_tender_project p ")
//		   .append("where p.creuser =? ")
//		   .append("and (p.TenderMethod = '").append(EbuyMethodEnum.COMPETITION).append("' ").append("or p.TenderMethod = '").append(EbuyMethodEnum.TALK).append("') ")
//		   .append("and p.useStatus = '").append(CommonEnum.USER_STATUS_CONFIRM).append("' ")
//		   .append("and p.PROCESSSTATUS < '").append(ProjProcessStatusEnum.CALIBRATION_BID).append("' ")
//		   .append("order by p.credate desc ")
//		   .append(") where rownum <= 3");
		sql.append("select * from (")
		.append("select distinct p.tenderid,")
		.append("p.tenderno,")
		.append("p.tendername,")
		.append("p.credate,")
		.append("p.EVAL_START_DATE,")
		.append("p.EVAL_END_DATE,")
		.append("p.TenderMethod,")
		.append("p.SIGN_UP_START_DATE,")
		.append("p.SIGN_UP_END_DATE,")
		.append("p.ProcessStatus")
		.append(" from ecp_tender_project p,")
		.append(" org_info           o,")
		.append(" auth_orgnization   org,")
		.append(" auth_user          u")
		.append(" left join auth_user_role ur on ur.usr_id = u.usr_id")
		.append(" left join auth_role ar on ar.rol_id = ur.rol_id")//by  yucy 采购人桌面优化   直接连接查询改为左外连接 因为查询条件并非写定
		.append(" where (p.TenderMethod = '").append( EbuyMethodEnum.COMPETITION ).append("' or p.TenderMethod = '").append( EbuyMethodEnum.COMPETITION ).append("')")
		.append(" and p.useStatus = '").append(CommonEnum.USER_STATUS_CONFIRM).append("'")
		.append(" and p.PROCESSSTATUS < '").append(ProjProcessStatusEnum.CALIBRATION_BID).append("'")
		.append(" and p.buyers_id = o.org_info_id")
		.append(" and o.company_id = org.org_id  ")
		.append(" and (p.creuser = '").append(userId).append("' or (instr(org.org_path,(select oo.company_id from org_info oo where oo.org_info_id = '").append(orgId).append("')) > 0 and ar.rol_en_name = 'buy_monitor') )")
		.append(" order by p.credate desc ")
		.append(") where rownum <= 3");
		
		return this.getJdbcTemplate().queryForList(sql.toString());
	}
	
	/** 
	 * Description :  获得供应商桌面的我参与竞价项目列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   供应商的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getSupplierBids(String orgId,String userId) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		//供应商桌面的进行中竞价 状态小于200
		sql.append("select * from ( ")
		.append("select p.tenderid,p.tenderno,p.tendername,p.credate,p.EVAL_START_DATE,p.EVAL_END_DATE,p.TenderMethod,p.SIGN_UP_START_DATE,p.SIGN_UP_END_DATE,p.ProcessStatus from ECP_TENDER_APPLY_REC b ")
		.append("join ECP_Tender_Project p on b.TENDERID = p.tenderid ")
		.append("where b.SUPPLYERID =? ")
		.append("and (p.TenderMethod = '").append(EbuyMethodEnum.COMPETITION).append("' ").append("or p.TenderMethod = '").append(EbuyMethodEnum.TALK).append("') ")
		.append("and p.useStatus = '").append(CommonEnum.USER_STATUS_CONFIRM).append("' ")
		.append("and p.PROCESSSTATUS < '").append(ProjProcessStatusEnum.CALIBRATION_BID).append("' ")
		.append("order by b.APPLYDATE desc ")
		.append(") where rownum <= 5");
		
		return this.getJdbcTemplate().queryForList(sql.toString(), orgId);
	}
	
	/** 
	 * Description :  获得采购人桌面的我的议价列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   采购人的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getBuyerBargains(String userId) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		//采购人桌面的我创建的议价项目列表  状态小于200
		sql.append("select * from ( ")
		   .append("select p.tenderid,p.tenderno,p.tendername,p.credate,p.EVAL_START_DATE,p.EVAL_END_DATE from ecp_tender_project p ")
		   .append("where p.creuser =? ")
		   .append("and p.TenderMethod = '").append(EbuyMethodEnum.TALK).append("' ")
		   .append("and p.useStatus = '").append(CommonEnum.USER_STATUS_CONFIRM).append("' ")
		   .append("and p.PROCESSSTATUS < '").append(ProjProcessStatusEnum.CALIBRATION_BID).append("' ")
		   .append("order by p.credate desc ")
		   .append(") where rownum <= 5");
		
		return this.getJdbcTemplate().queryForList(sql.toString(), userId);
	}
	
	/** 
	 * Description :  获得供应商桌面的我的议价列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   供应商的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getSupplierBargains(String orgId) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		//供应商桌面的进行中项目列表 280
		sql.append("select * from ( ")
		   .append("select p.tenderid,p.tenderno,p.tendername,p.credate,p.EVAL_START_DATE,p.EVAL_END_DATE from ECP_TENDER_APPLY_REC b ")
		   .append("join ECP_Tender_Project p on b.TENDERID = p.tenderid ")
		   .append("where b.SUPPLYERID =? ")
		   .append("and p.TenderMethod = '").append(EbuyMethodEnum.TALK).append("' ")
		   .append("and p.useStatus = '").append(CommonEnum.USER_STATUS_CONFIRM).append("' ")
		   .append("and p.PROCESSSTATUS < '").append(ProjProcessStatusEnum.CALIBRATION_BID).append("' ")
		   .append("and "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date(DateUtil.format(new Date()))+" between p.EVAL_START_DATE and p.EVAL_END_DATE ")//过滤已经过期的议价
		   .append("order by p.credate desc ")
		   .append(") where rownum <= 5");
		
		return this.getJdbcTemplate().queryForList(sql.toString(), orgId);
	}
	
	/** 
	 * Description :  获得供应商桌面的我参与反拍项目列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   供应商的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getSupplierReverses(String orgId,String userId) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		//供应商桌面的进行中项目列表 280
		sql.append("select * from ( ")
		   .append("select p.tenderid,p.tenderno,p.tendername,p.credate,p.EVAL_START_DATE,p.EVAL_END_DATE from ECP_TENDER_APPLY_REC b ")
		   .append("join ECP_Tender_Project p on b.TENDERID = p.tenderid where p.processstatus != '")
		   .append(ProjProcessStatusEnum.PUT_ON_RECORDS).append("' and b.SUPPLYERID =? ")
		   .append("and p.TenderMethod = '").append(EbuyMethodEnum.REVERSE).append("' ")
		   .append("and p.useStatus = '").append(CommonEnum.USER_STATUS_CONFIRM).append("' ")
		   .append("and p.PROCESSSTATUS < '").append(ProjProcessStatusEnum.CALIBRATION_BID).append("' ")
		   .append("order by p.credate desc ")
		   .append(") where rownum <= 5");
		
		return this.getJdbcTemplate().queryForList(sql.toString(), orgId);
	}
	
	/** 
	 * Description :  获得采购人桌面的我创建的反拍项目列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   采购人的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getBuyerReverses(String orgId,String userId) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		//采购人桌面的我创建的反拍项目列表 非280
		sql.append("select * from ( ")
		   .append("select p.tenderid,p.tenderno,p.tendername,p.credate,p.EVAL_START_DATE,p.EVAL_END_DATE from ecp_tender_project p where ")
		   .append("p.processstatus != '").append(ProjProcessStatusEnum.PUT_ON_RECORDS).append("' ")
		   .append("and p.creuser =? ")
		   .append("and p.TenderMethod = '").append(EbuyMethodEnum.REVERSE).append("' ")
		   .append("and p.useStatus = '").append(CommonEnum.USER_STATUS_CONFIRM).append("' ")
		   .append("and p.PROCESSSTATUS < '").append(ProjProcessStatusEnum.CALIBRATION_BID).append("' ")
		   .append("order by p.credate desc ")
		   .append(") where rownum <= 5");
		
		return this.getJdbcTemplate().queryForList(sql.toString(), userId);
	}
	
	
	/** 
	 * Description :  获得供应商桌面的投标项目列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   供应商的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getSupplierProjects(String orgId) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		//供应商桌面的进行中项目列表 280
		sql.append("select * from ( ")
		   .append("select p.tenderid,p.tenderno,p.tendername,p.credate,p.EVAL_START_DATE,p.EVAL_END_DATE from ECP_TENDER_APPLY_REC b ")
		   .append("join ECP_Tender_Project p on b.TENDERID = p.tenderid where p.processstatus != '")
		   .append(ProjProcessStatusEnum.PUT_ON_RECORDS).append("' and b.SUPPLYERID =? ")
		   .append("and p.useStatus = '").append(CommonEnum.USER_STATUS_CONFIRM).append("' ")
		   .append("order by p.credate desc ")
		   .append(") where rownum <= 5");
		
		return this.getJdbcTemplate().queryForList(sql.toString(), orgId);
	}
	
	/** 
	 * Description :  获得采购人桌面的我参与的项目列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   采购人的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getBuyerProjects(String orgId) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		//采购人桌面的我参与的项目列表 280
		sql.append("select * from ( ")
		   .append("select p.tenderid,p.tenderno,p.tendername,p.credate,p.EVAL_START_DATE,p.EVAL_END_DATE from ecp_tend_m_task t ")
		   .append("join ecp_tender_project p on t.tenderid = p.tenderid where p.processstatus != '")
		   .append(ProjProcessStatusEnum.PUT_ON_RECORDS).append("' and t.BUY_MAIN_BODY =? ")
		   .append("and p.useStatus = '").append(CommonEnum.USER_STATUS_CONFIRM).append("' ")
		   .append("order by p.credate desc ")
		   .append(") where rownum <= 5");
		
		return this.getJdbcTemplate().queryForList(sql.toString(), orgId);
	}
	
	/** 
	 * Description :  获得代理机构桌面的我参与的项目列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   供应商的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getAgencyProjects(String orgId) throws Exception {
		StringBuilder sql = new StringBuilder();
		
		//代理机构桌面的我参与的项目列表 280
		sql.append("select * from ( ")
		   .append("select p.tenderid,p.tenderno,p.tendername,p.credate,p.EVAL_START_DATE,p.EVAL_END_DATE from ECP_Tender_Project P ")
		   .append("where p.agenciesid = ? and p.processstatus != '")
		   .append(ProjProcessStatusEnum.PUT_ON_RECORDS).append("' ")
		   .append("order by p.credate desc ")
		   .append(") where rownum <= 5");
		
		return this.getJdbcTemplate().queryForList(sql.toString(), orgId);
	}
	
	/** 
	 * Description :  获得供应商桌面的待确认订单列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   供应商的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getSupplierOrders(String orgId) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		//供应商桌面的待确认订单列表 00 00
		sql.append("select * from ( ")
		   .append("select o.order_id, o.order_no, o.cre_time from eps_agreement_order o ")
		   .append("where o.SUPPLIER_ID=? and o.S_CONFIRM_STATUS = '")
		   .append(CommonEnum.CONFIRM_STATUS_WAIT).append("' and o.B_CONFIRM_STATUS != '")
		   .append(CommonEnum.CONFIRM_STATUS_WAIT).append("' order by o.cre_time desc ")
		   .append(") where rownum <= 5");
		
		return this.getJdbcTemplate().queryForList(sql.toString(), orgId);
	}
	
	/** 
	 * Description :  获得采购人桌面的待提交订单列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   供应商的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getBuyerOrders(String userId) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		//采购人桌面的待提交订单列表 00
		sql.append("select * from ( ")
		   .append("select o.order_id, o.order_no, o.cre_time from eps_agreement_order o ")
		   .append("where o.CRE_USER_ID=? and o.B_CONFIRM_STATUS = '")
		   .append(CommonEnum.CONFIRM_STATUS_WAIT).append("' order by o.cre_time desc ")
		   .append(") where rownum <= 5");
		
		return this.getJdbcTemplate().queryForList(sql.toString(), userId);
	}

	/** 
	 * Description :  获得供应商桌面的待确认合同列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   供应商的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getSupplierContracts(String orgId) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		//供应商桌面的待确认合同列表 00 00
		sql.append("select * from ( ")
		   .append("select t.contract_id,t.contract_name,t.contract_no,t.cre_time from EPS_PUB_CONTRACT t ")
		   .append("where t.SUPPLIER_ID = ? and t.S_CONFIRM_STATUS = '").append(CommonEnum.CONFIRM_STATUS_WAIT)
		   .append("' and t.B_CONFIRM_STATUS != '").append(CommonEnum.CONFIRM_STATUS_WAIT)
		   .append("' and t.B_CONFIRM_STATUS != '").append(CommonEnum.CONFIRM_STATUS_CANCEL)// 退回的不代表待确认 争议
		   .append("' order by t.cre_time desc ")
		   .append(") where rownum <= 5");
		
		return this.getJdbcTemplate().queryForList(sql.toString(), orgId);
	}
	
	/** 
	 * Description :  获得采购人桌面的待确认合同列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   供应商的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getBuyerContracts(String orgId) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		//采购人桌面的待确认合同列表 00 00
		sql.append("select * from ( ")
		   .append("select t.contract_id,t.contract_name,t.contract_no,t.cre_time from EPS_PUB_CONTRACT t ")
		   .append("where t.BUYER_ID = ? and t.B_CONFIRM_STATUS = '")
		   .append(CommonEnum.CONFIRM_STATUS_WAIT).append("' and t.S_CONFIRM_STATUS != '")
		   .append(CommonEnum.CONFIRM_STATUS_WAIT).append("' order by t.cre_time desc ")
		   .append(") where rownum <= 5");
		
		return this.getJdbcTemplate().queryForList(sql.toString(), orgId);
	}
	
	/**
	 * 获得代理机构的任务信息
	 */
	public Map<String, String> getAgencyTask(String userId, String orgId) throws Exception{
		final Map<String, String> map = new HashMap<String, String>();
		
		StringBuilder sql = new StringBuilder();
		
		//进行中的项目 280
		sql.append("select * from ");
		sql.append("(select nvl(count(TENDERID),0) a_p from ECP_Tender_Project p where p.AgenciesID=? and p.ProcessStatus != '");
		sql.append(ProjProcessStatusEnum.PUT_ON_RECORDS).append("')");
		
		this.getJdbcTemplate().query(
				sql.toString(),
                new Object[]{orgId},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
            			map.put("AP", rs.getString("a_p"));//进行中的项目
                    }
                });
		return map;
	}
	
	/** 
	 * Description :  获得商品库管理的任务信息
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   
	 * @Exception   
	 */
	public Map<String,String> getGoodsTask() throws Exception {
		final Map<String, String> map = new HashMap<String, String>();
		
		StringBuilder sql = new StringBuilder();
		
		//待审核品牌数 01
		sql.append("select * from ")
		   .append("(select nvl(count(goods_brand_id),0) g_b from goods_brand b where b.audit_status = '")
		   .append(GoodsEnum.AWAIT_EXAM).append("'),");
		
		//待审核商品数 01
		sql.append("(select nvl(count(goods_id),0) g_g from goods g where g.audit_status = '")
		   .append(GoodsEnum.AWAIT_EXAM).append("')");
		
		this.getJdbcTemplate().query(
				sql.toString(),
                new Object[]{},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
            			map.put("GB", rs.getString("g_b"));//待审核品牌数 
            			map.put("GG", rs.getString("g_g"));//待审核商品数 
                    }
                });
		return map;
	}
	
	/** 
	 * Description :  获得机构管理的任务信息
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   
	 * @Exception   
	 */
	public Map<String,String> getOrgInfoTask() throws Exception {
		final Map<String, String> map = new HashMap<String, String>();
		
		StringBuilder sql = new StringBuilder();
		
		//待审核品牌数 01
		sql.append("select * from ")
		   .append("(select nvl(count(goods_brand_id),0) g_b from goods_brand b where b.audit_status = '")
		   .append(GoodsEnum.AWAIT_EXAM).append("'),");
		
		//待审核商品数 01
		sql.append("(select nvl(count(goods_id),0) g_g from goods g where g.audit_status = '")
		   .append(GoodsEnum.AWAIT_EXAM).append("')");
		
		this.getJdbcTemplate().query(
				sql.toString(),
                new Object[]{},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
            			map.put("GB", rs.getString("g_b"));//待审核品牌数 
            			map.put("GG", rs.getString("g_g"));//待审核商品数 
                    }
                });
		return map;
	}
	
	/** 
	 * Description :  获得机构管理的首页
	 * Create Date: 2010-8-16上午10:47:31 by sunl  Modified Date: 2010-8-16上午10:47:31 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, String> getOrgInfoIndex(String orgInfoId) throws Exception {
		final Map<String, String> map = new HashMap<String, String>();
		
		StringBuilder sql = new StringBuilder();
		
		//机构管理员
		if(null != orgInfoId && !"".equals(orgInfoId)){
			//有效的资质数目 02 01
			sql.append("select * from ")
			   .append("(select nvl(count(QUALIFICATION_ID),0) z_v from ORG_QUALIFICATION q ")
			   .append(" join base_qualification t on q.quality_class_id = t.quality_id")
			   .append(" where t.is_display='1' and q.status = '").append(OrganizationEnum.PASS_EXAM)
			   .append("' and q.use_status = '").append(OrganizationEnum.USE_VALID)
			   .append("' and q.BELONG_OBJECT_ID =:orgInfoId),");
			
			//有效成功案例数目
			sql.append("(select nvl(count(case_id),0) a_v from ECP_PUB_SUCCESS_CASE a where a.use_status = '")
			   .append(OrganizationEnum.USE_VALID).append("' and a.orginfo_id =:orgInfoId)");
			
			this.getJdbcTemplate().query(
					sql.toString(),
	                new Object[]{orgInfoId,orgInfoId},
	                new RowCallbackHandler() {
	                    public void processRow(ResultSet rs) throws SQLException {
	            			map.put("ZV", rs.getString("z_v"));
	            			map.put("AV", rs.getString("a_v"));
	                    }
	                });
		}
		//manager管理员
		else{
			//待审核机构数目 01 00
			sql.append("select * from ") 
			   .append("(select nvl(count(org_info_id),0) m_c from org_info o where ")
			   .append("(o.audit_status = '").append(OrganizationEnum.AWAIT_EXAM).append("')),");
			
			//待审核资质数目 01 00
			sql.append("(select nvl(count(QUALIFICATION_ID),0) z_c from ORG_QUALIFICATION q where q.status = '")
			   .append(OrganizationEnum.AWAIT_EXAM).append("' and q.use_status = '").append(OrganizationEnum.USE_TEMP)
			   .append("'),");
			
			//待审核案例数目 01
			sql.append("(select nvl(count(case_id),0) a_c from ECP_PUB_SUCCESS_CASE a where a.audit_status = '")
			   .append(OrganizationEnum.AWAIT_EXAM).append("')");
			
			this.getJdbcTemplate().query(
					sql.toString(),
	                new Object[]{},
	                new RowCallbackHandler() {
	                    public void processRow(ResultSet rs) throws SQLException {
	            			map.put("MC", rs.getString("m_c"));
	            			map.put("ZC", rs.getString("z_c"));
	            			map.put("AC", rs.getString("a_c"));
	                    }
	                });
		}
		return map;
	}
	
	/** 
	 * Description :  获得基础数据的首页
	 * Create Date: 2010-8-16上午10:47:31 by sunl  Modified Date: 2010-8-16上午10:47:31 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, String> getBaseDataIndex() throws Exception {
		final Map<String, String> map = new HashMap<String, String>();
		
		StringBuilder sql = new StringBuilder();
		
		//待审核集中采购目录数目 00 01
		sql.append("select * from ") 
		   .append("(select nvl(count(catalog_id),0) b_m from ECP_BASE_CATALOG c where c.usestatus = '")
		   .append(OrganizationEnum.USE_TEMP).append("' and c.auditstatus = '")
		   .append(OrganizationEnum.AWAIT_EXAM).append("')");
		
		this.getJdbcTemplate().query(
				sql.toString(),
                new Object[]{},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
            			map.put("BM", rs.getString("b_m"));
                    }
                });
		
		return map;
	}
	
	/** 
	 * Description :  获取统计信息(违规记录数，被关注记录数，未读站内信数，未读留言数，发布项目数，发布采购需求数)
	 * Create Date: 2011-8-18下午04:21:40 by likg  Modified Date: 2011-8-18下午04:21:40 by likg
	 * @param   orgInfoId:机构Id userId:用户Id
	 * @return  
	 * @Exception   
	 */
	public Map<String, String> getStatisticsInfo(String orgInfoId, String userId) throws Exception {
		final Map<String, String> map = new HashMap<String, String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ");
		//违规记录数
		sql.append("(select count(ILLEGAL_RECORD_ID) i_n from ECP_PUB_ILLEGAL_RECORD i where i.ORGINFO_ID=?), ");
		//被关注记录数
		sql.append("(select count(CONCERN_ID) c_n from ECP_PUB_CONCERN c where c.ORGINFO=?), ");
		//未读站内信数
		sql.append("(select count(MSG_ID) m_n from EPS_PUB_MESSAGE m where m.RECEIVER=? and m.IS_READ=0 and m.IS_SAVE=0 and (m.SENDER!=? or m.SENDER is null)), ");
		//未读留言数
		sql.append("(select count(NOTE_ID) n_n from EPS_PUB_NOTE n where n.RECEIVER=? and n.IS_READ=0 ), ");
		//发布项目数
		sql.append("(select count(TenderID) p_n from ECP_Tender_Project p where p.CreUser=? and p.USESTATUS != '"+CommonEnum.USER_STATUS_INVALID+"'), ");
		//发布采购需求数
		sql.append("(select count(PURCHASE_REQUIRE_ID) r_n from EPS_PURCHASE_REQUIREMENT r where r.ORG_INFO_ID=?), ");
		//供应商参与项目数
		sql.append("(select count(ApplyID) rp_n from ECP_Tender_Apply_Rec rp where rp.CreUser=?),");
		//供应商参与采购需求数
		sql.append("(select count(EPS_PURCHASE_REQ_REG_ID) rg_n from EPS_PURCHASE_REQUIREMENT_REG rg where rg.ORG_INFO_ID=?),");
		//供应商销售总金额
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("sum(GOODS_TOTAL)", "0") + " s_a from EPS_AGREEMENT_ORDER dd where dd.PAY_STATUS='"+OrderEnum.HAS_PAY+"' and dd.SUPPLIER_ID=?)");
		
		this.getJdbcTemplate().query(sql.toString(), new Object[]{orgInfoId,orgInfoId,userId,userId,userId,userId,orgInfoId,userId,orgInfoId,orgInfoId}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				map.put("I_N", rs.getString("i_n")); //违规记录数
				map.put("C_N", rs.getString("c_n")); //被关注记录数
				map.put("M_N", rs.getString("m_n")); //未读站内信数
				map.put("N_N", rs.getString("n_n")); //未读留言数
				map.put("P_N", rs.getString("p_n")); //发布项目数
				map.put("R_N", rs.getString("r_n")); //发布采购需求数
				map.put("RP_N", rs.getString("rp_n")); //供应商参与项目数
				map.put("RG_N", rs.getString("rg_n")); //供应商参与采购需求数
				map.put("S_A", rs.getString("s_a")); //供应商销售总金额
			}
        });
		return map;
	}

	/** 
	 * Description :  获取子公司采购信息(采购计划数量和金额，订单数量和金额，项目数量)
	 * Create Date: 2011-8-19上午11:18:40 by likg  Modified Date: 2011-8-19上午11:18:40 by likg
	 * @param   parentOrgInfoId:总公司的机构Id
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getSubCompanyBuyInfo(final String parentOrgInfoId) throws Exception {
		//获取子公司的orgInfoId
		String sql1 = "select o.org_info_id orgId from org_info o join auth_orgnization n on o.company_id = n.org_id where n.org_parent_id = (select oo.company_id from org_info oo where oo.org_info_id = ?)";
		final List<String> orgIdList = new ArrayList<String>();
		this.getJdbcTemplate().query(sql1, new Object[]{parentOrgInfoId}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				orgIdList.add(rs.getString("orgId"));
			}
		});
		
		//获取(采购计划数量和金额，订单数量和金额，项目数量)
		final List<Object[]> infoList = new ArrayList<Object[]>();
		if(orgIdList!=null && !orgIdList.isEmpty()) {
			String orgIds = "";
			for(String str : orgIdList) { orgIds += "'" + str + "',"; }
			orgIds = orgIds.substring(0, orgIds.length()-1);
			String sql2 = "" +
			"select t1.orgid,t1.orgName,t1.tn, t1.ta, t2.dn,t2.da, t3.talkn, t4.bn from " +
				"(select o.ORG_NAME orgName, o.org_info_id orgid, count(t.task_id) tn, " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("sum(t.sum_total)", "0") + " ta " +
				" from org_info o  left join EPS_AGREEMENT_PROCUREMENTTASK t on t.buyer_id = o.org_info_id " +
				" where o.org_info_id in("+orgIds+") group by o.org_info_id, o.ORG_NAME) t1, " +
				//根据订单获取采购额和采购量
				"(select o.org_info_id orgid, count(d.order_id) dn, " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("sum(d.Goods_Total)", "0") + " da " +
				" from org_info o left join EPS_AGREEMENT_ORDER d on d.buyer_id = o.org_info_id and d.PAY_STATUS='"+OrderEnum.HAS_PAY+"'" +
				" where o.org_info_id in("+orgIds+") group by o.org_info_id ) t2, " +
				
				"(select o.org_info_id orgid, count(pr.tenderid) talkn " +
				" from org_info o left join ecp_tender_project pr on pr.BUYERS_ID = o.org_info_id and pr.tendermethod = '"+EbuyMethodEnum.TALK+"'" +
				" where o.org_info_id in("+orgIds+") group by o.org_info_id) t3, " +
				
				"(select o.org_info_id orgid, count(p.tenderid) bn " +
				" from org_info o left join ecp_tender_project p on p.buyer_id = o.org_info_id and p.tendermethod = '"+EbuyMethodEnum.COMPETITION+"'" +
				" where o.org_info_id in("+orgIds+") group by o.org_info_id) t4 " +
			" where t1.orgid=t2.orgid and t1.orgid=t3.orgid and t1.orgid=t4.orgid ";
			
			this.getJdbcTemplate().query(sql2, new Object[]{}, new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					Object[] objs = new Object[]{rs.getString("orgid"),rs.getString("orgName"),rs.getString("tn"),rs.getString("ta"),rs.getString("dn"),rs.getString("da"),rs.getString("talkn"),rs.getString("bn")};
					infoList.add(objs);
				}
			});
		}
		
		return infoList;
	}

	/** 
	 * Description :  获取子公司销售信息(参与项目数，中标项目数，成交总金额)
	 * Create Date: 2011-8-19上午11:18:40 by likg  Modified Date: 2011-8-19上午11:18:40 by likg
	 * @param   parentOrgInfoId:总公司的机构Id
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getSubCompanySaleInfo(String parentOrgInfoId) throws Exception {
		//获取子公司的orgInfoId
		String sql1 = "select o.org_info_id orgId from org_info o join auth_orgnization n on o.company_id = n.org_id " +
			"where n.org_parent_id = (select oo.company_id from org_info oo where oo.org_info_id = ?)";
		final List<String> orgIdList = new ArrayList<String>();
		this.getJdbcTemplate().query(sql1, new Object[]{parentOrgInfoId}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				orgIdList.add(rs.getString("orgId"));
			}
		});
		
		//获取子公司的（参与项目数，中标项目数，成交总金额）
		final List<Object[]> infoList = new ArrayList<Object[]>();
		if(orgIdList!=null && !orgIdList.isEmpty()) {
			String orgIds = "";
			for(String str : orgIdList) { orgIds += "'" + str + "',"; }
			orgIds = orgIds.substring(0, orgIds.length()-1);
			String sql2 = "" +
			"select t1.orgid,t1.orgName,t1.pn1,t2.pn2,t3.da from " +
				"(select o.ORG_NAME orgName, o.org_info_id orgid, count(t.ApplyID) pn1 " +
				" from org_info o left join ECP_Tender_Apply_Rec t on t.SupplyerID = o.org_info_id " +
				" where o.org_info_id in("+orgIds+") group by o.org_info_id, o.ORG_NAME) t1, " +
				"(select o.org_info_id orgid, count(w.BUY_W_ID) pn2 " +
				" from org_info o  left join ECP_BUY_WINNER w on w.SELLER_ID = o.org_info_id " +
				" where o.org_info_id in("+orgIds+") group by o.org_info_id ) t2, " +
				"(select o.org_info_id orgid, " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("sum(dt.Goods_Total)", "0") + " da " +
				" from org_info o left join EPS_AGREEMENT_ORDER dt on dt.SUPPLIER_ID=o.org_info_id and dt.PAY_STATUS='"+OrderEnum.HAS_PAY+"'" +
				" where o.org_info_id in("+orgIds+") group by o.org_info_id) t3" +
			" where t1.orgid = t2.orgid and t1.orgid = t3.orgid ";
			
			this.getJdbcTemplate().query(sql2, new Object[]{}, new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					Object[] objs = new Object[]{rs.getString("orgid"),rs.getString("orgName"),rs.getString("pn1"),rs.getString("pn2"),rs.getString("da")};
					infoList.add(objs);
				}
			});
		}
		
		return infoList;
	}
	
	/** 
	 * Description :  获取子公司的统计信息(认证资质数，成功案例数，客户评价次数，客户评价总分，被投诉次数，被举报次数，违规记录数)
	 * Create Date: 2011-8-18下午04:21:40 by likg  Modified Date: 2011-8-18下午04:21:40 by likg
	 * @param   parentOrgInfoId:总公司的机构Id
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getSubCompanyStatisticsInfo(String parentOrgInfoId) throws Exception {
		//获取子公司的orgInfoId
		String sql1 = "select o.org_info_id orgId from org_info o join auth_orgnization n on o.company_id = n.org_id " +
			"where n.org_parent_id = (select oo.company_id from org_info oo where oo.org_info_id = ?)";
		final List<String> orgIdList = new ArrayList<String>();
		this.getJdbcTemplate().query(sql1, new Object[]{parentOrgInfoId}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				orgIdList.add(rs.getString("orgId"));
			}
		});
		
		//获取(认证资质数，成功案例数，客户评价次数，客户评价总分，被投诉次数，被举报次数，违规记录数)
		final List<Object[]> infoList = new ArrayList<Object[]>();
		if(orgIdList!=null && !orgIdList.isEmpty()) {
			String orgIds = "";
			for(String str : orgIdList) { orgIds += "'" + str + "',"; }
			orgIds = orgIds.substring(0, orgIds.length()-1);
			String sql2 = "" +
			"select t1.orgid,t1.orgName,t1.tn1,t2.tn2,t3.ev_n,t3.evs_n,t4.tn4,t5.tn5,t6.tn6 from " +
				"(select o.ORG_NAME orgName, o.org_info_id orgid, count(q.QUALIFICATION_ID) tn1 " +
				" from org_info o left join ORG_QUALIFICATION q on q.BELONG_OBJECT_ID = o.org_info_id " +
				" where o.org_info_id in("+orgIds+") group by o.org_info_id, o.ORG_NAME) t1, " +
				
				"(select o.org_info_id orgid, count(s.CASE_ID) tn2 " +
				" from org_info o  left join ECP_PUB_SUCCESS_CASE s on s.ORGINFO_ID = o.org_info_id and s.AUDIT_STATUS='"+OrganizationEnum.PASS_EXAM+"'" +
				" where o.org_info_id in("+orgIds+") group by o.org_info_id ) t2, " +
				
				"(select o.org_info_id orgid, count(ev.EVALUATE_ID) ev_n," + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("sum(ev.SUMMARY_SCORE)", "0") + " evs_n " +
				" from org_info o left join ECP_PUB_EVALUATE ev on ev.ORGINFO_ID = o.org_info_id " +
				" where o.org_info_id in("+orgIds+") group by o.org_info_id ) t3, " +
				
				"(select o.org_info_id orgid, count(ts.CPL_ID) tn4 " +
				" from org_info o left join EPS_PUB_COMPLAIN ts on ts.be_company_id=o.org_info_id and ts.type='00'" +
				" where o.org_info_id in("+orgIds+") group by o.org_info_id ) t4, " +
				
				"(select o.org_info_id orgid, count(jb.CPL_ID) tn5 " +
				" from org_info o  left join EPS_PUB_COMPLAIN jb on jb.be_company_id=o.org_info_id and jb.type='01'" +
				" where o.org_info_id in("+orgIds+") group by o.org_info_id ) t5, " +
				
				"(select o.org_info_id orgid, count(i.ILLEGAL_RECORD_ID) tn6 " +
				" from org_info o  left join ECP_PUB_ILLEGAL_RECORD i on i.ORGINFO_ID = o.org_info_id " +
				" where o.org_info_id in("+orgIds+") group by o.org_info_id ) t6 " +
			" where t1.orgid = t2.orgid and t1.orgid = t3.orgid and t1.orgid = t4.orgid and t1.orgid = t5.orgid and t1.orgid = t6.orgid ";
			
			this.getJdbcTemplate().query(sql2, new Object[]{}, new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					Object[] objs = new Object[]{rs.getString("orgid"),rs.getString("orgName"),rs.getString("tn1"),rs.getString("tn2"),rs.getString("ev_n"),rs.getString("evs_n"),rs.getString("tn4"),rs.getString("tn5"),rs.getString("tn6")};
					infoList.add(objs);
				}
			});
		}
		
		return infoList;
	}

	/** 
	 * Description :  获取机构的统计信息(认证资质数，成功案例数，已有联系人数，客户评价次数，客户评价总分，好评次数，被投诉次数，被举报次数，违规记录数)
	 * Create Date: 2011-8-18下午04:21:40 by likg  Modified Date: 2011-8-18下午04:21:40 by likg
	 * @param   orgInfoId:机构Id
	 * @return  
	 * @Exception   
	 */
	public Map<String, String> getOrgStatisticsInfo(String orgInfoId) throws Exception {
		final Map<String, String> map = new HashMap<String, String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ");
		//认证资质数
		sql.append("(select count(QUALIFICATION_ID) q_n from ORG_QUALIFICATION q join BASE_QUALIFICATION qc on q.QUALITY_CLASS_ID=qc.QUALITY_ID where qc.IS_DISPLAY=1 and q.BELONG_OBJECT_ID=?), ");
		//成功案例数
		sql.append("(select count(CASE_ID) s_n from ECP_PUB_SUCCESS_CASE s where s.ORGINFO_ID=? and s.AUDIT_STATUS='"+OrganizationEnum.PASS_EXAM+"'), ");
		//已有联系人数
		sql.append("(select count(EMP_ID) e_n from AUTH_ORG_EMPLOYEE e join AUTH_ORGNIZATION n on e.EMP_COMPANY_ID=n.ORG_ID join ORG_INFO o on o.COMPANY_ID=n.ORG_ID where o.ORG_INFO_ID=?), ");
		//客户评价次数,客户评价总分
		sql.append("(select count(EVALUATE_ID) ev_n," + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("sum(SUMMARY_SCORE)", "0") + " evs_n from ECP_PUB_EVALUATE ev where ev.ORGINFO_ID=? ), ");
		//好评次数
		sql.append("(select count(EVALUATE_ID) gev_n from ECP_PUB_EVALUATE gev where gev.ORGINFO_ID=? and gev.EVAL_LEVAL='0' ), ");
		//被投诉次数
		sql.append("(select count(CPL_ID) ts_n from EPS_PUB_COMPLAIN ts where ts.be_company_id=? and ts.type='00'), ");
		//被举报次数
		sql.append("(select count(CPL_ID) jb_n from EPS_PUB_COMPLAIN jb where jb.be_company_id=? and jb.type='01'), ");
		//违规记录数
		sql.append("(select count(ILLEGAL_RECORD_ID) i_n from ECP_PUB_ILLEGAL_RECORD i where i.ORGINFO_ID=?) ");
		
		this.getJdbcTemplate().query(sql.toString(), new Object[]{orgInfoId,orgInfoId,orgInfoId,orgInfoId,orgInfoId,orgInfoId,orgInfoId,orgInfoId}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				map.put("Q_N", rs.getString("q_n")); //认证资质数
				map.put("S_N", rs.getString("s_n")); //成功案例数
				map.put("E_N", rs.getString("e_n")); //已有联系人数
				map.put("EV_N", rs.getString("ev_n")); //客户评价次数
				map.put("EVS_N", rs.getString("evs_n")); //客户评价总分
				map.put("GEV_N", rs.getString("gev_n")); //好评次数
				map.put("TS_N", rs.getString("ts_n")); //被投诉次数
				map.put("JB_N", rs.getString("jb_n")); //被举报次数
				map.put("I_N", rs.getString("i_n")); //违规记录数
			}
        });
		return map;
	}

	/** 
	 * Description :  获取指定时间段的机构统计信息(被收藏次数，关注数量，销售金额，采购金额)
	 * Create Date: 2011-8-18下午04:21:40 by likg  Modified Date: 2011-8-18下午04:21:40 by likg
	 * @param   orgInfoId:机构Id days:时间范围
	 * @return  
	 * @Exception   
	 */
	public Map<String, String> getOrgStatisticsInfoByTime(String orgInfoId, int days) throws Exception {
		final Map<String, String> map = new HashMap<String, String>();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, +1);
		String today = UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date(DateUtil.format(calendar.getTime()));
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 0-days);
		String dayRange = UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date(DateUtil.format(calendar.getTime()));
		String timeSql = " between " + dayRange + " and " + today;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ");
		//采购人被收藏次数
		sql.append("(select count(FAVORITES_ID) bf_n from ECP_PUB_FAVORITES bf join BUY_BUYER b on bf.FAVORITES_OBJECT_ID=b.BUYER_ID join ORG_INFO o on b.ORG_INFO_ID=o.ORG_INFO_ID where o.ORG_INFO_ID=? and bf.FAVORITES_OBJECT_TYPE='04' and bf.CREATE_TIME "+timeSql+"), ");
		//供应商被收藏次数
		sql.append("(select count(FAVORITES_ID) sf_n from ECP_PUB_FAVORITES sf where sf.FAVORITES_OBJECT_ID=? and sf.FAVORITES_OBJECT_TYPE='02' and sf.CREATE_TIME "+timeSql+"), ");
		//关注数量
		sql.append("(select count(CONCERN_ID) c_n from ECP_PUB_CONCERN c where c.ORGINFO=? and c.CREATE_TIME "+timeSql+"), ");
		//销售金额
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("sum(GOODS_TOTAL)", "0") + " s_n from EPS_AGREEMENT_ORDER bd where bd.SUPPLIER_ID=? and bd.PAY_STATUS='"+OrderEnum.HAS_PAY+"' and bd.CRE_TIME "+timeSql+"), ");
		//采购金额
		sql.append("(select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("sum(GOODS_TOTAL)", "0") + " b_n from EPS_AGREEMENT_ORDER sd where sd.BUYER_ID=? and sd.PAY_STATUS='"+OrderEnum.HAS_PAY+"' and sd.CRE_TIME "+timeSql+")");
		
		this.getJdbcTemplate().query(sql.toString(), new Object[]{orgInfoId,orgInfoId,orgInfoId,orgInfoId,orgInfoId}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				map.put("BF_N", rs.getString("bf_n")); //采购人被收藏次数
				map.put("SF_N", rs.getString("sf_n")); //供应商被收藏次数
				map.put("C_N", rs.getString("c_n")); //关注数量
				map.put("S_N", rs.getString("s_n")); //销售金额
				map.put("B_N", rs.getString("b_n")); //采购金额
			}
        });
		return map;
	}
}
