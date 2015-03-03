package com.gpcsoft.epp.buyresult.dao.hibernate;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.buyresult.dao.BuyWinnerDao;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;
import com.gpcsoft.epp.buyresult.domain.ResultTypeEnum;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

import org.springframework.stereotype.Repository;

@Repository
public class BuyWinnerDaoHibernate extends BaseGenericDaoHibernate<BuyWinner> implements BuyWinnerDao {

	/**
	 * Description :根据项目得到中标供应商列表  
	 * Create Date: 2010-8-20下午01:25:46 by liuke  Modified Date: 2010-8-20下午01:25:46 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<BuyWinner> getBuyWinnerListByProjectId(String subProjectId) {
		List<BuyWinner> list = this.findbyHql("select bw from BuyWinner bw ,BuyResult br where bw.buyResult.objId = br.objId and br.subProjId = ?", subProjectId);
		return list;
	}
	
	/**
	 * 
	 * Description : 根据项目获得中标供应商列表 
	 * Create Date: 2010-8-25下午05:44:15 by liuke  Modified Date: 2010-8-25下午05:44:15 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<BuyWinner> getBuyWinnerByProjectId(String projectId) {
		List<BuyWinner> list = this.findbyHql("select bw from BuyWinner bw ,BuyResult br where bw.buyResult.objId = br.objId and br.project.objId = ?", projectId);
		return list;
	}
	
	/**
	 * FuncName: getBuyWinnerList
	 * Description :  根据项目Id得到中标供应商
	 * @param 
	 * @param projectId
	 * @return List<BuyWinner>
	 * @author: shenjz
	 * @Create Date:2011-3-30  下午05:15:16
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-30  下午05:15:16
	 */
	public List<BuyWinner> getBuyWinnerList(String projectId) {
		List<BuyWinner> list = this.findbyHql("select bw from BuyWinner bw ,BuyResult br where bw.buyResult.objId = br.objId and br.project.objId = ? and bw.resultType = ? and bw .selllerId = ?", projectId,ResultTypeEnum.DEAL_YES,AuthenticationHelper.getCurrentUser().getOrgInfo().getObjId());
		return list;
	}
	
	/**
	 * 
	 * Description : 根据项目Id获得中标供应商
	 * Create Date: 2011-8-31下午05:44:15 by caojz  Modified Date: 2011-8-31下午05:44:15 by caojz
	 * @param   
	 * @return  
	 * @Exception
	 */
	 public List<BuyWinner> getBuyWinnerByProjId(String projectId) {
		List<BuyWinner> list = this.findbyHql("select bw from BuyWinner bw ,BuyResult br where bw.buyResult.objId = br.objId and bw.resultType = '00' and br.project.objId = ?", projectId);
		return list;
	 }
	/**
	 * FuncName: getWinnerQuotesum
	 * Description :  根据项目Id得到中标供应商对包组的报价
	 * @param 
	 * @param projectId
	 * @return List<BuyWinner>
	 * @author: zhouzhanghe
	 * @Create Date:2011-8-11 10:09
	 */
	public List getWinnerQuotesum(String projectId) {
		String sql = "select eb.sub_proj_id, ebw.seller_id, ebw.seller_name, nvl(eor.quotesum,0) from ECP_BUYRESULT eb left join ECP_BUY_WINNER ebw on eb.buyr_id = ebw.buyr_id left join ECP_OPENBID_RECORD eor on eor.SUPPLIER_ID = ebw.seller_id and eor.SUB_PROJ_ID = eb.sub_proj_id where eb.proj_id = '"+projectId+"' and ebw.RESULT_TYPE = '00'";
		return getSession().createSQLQuery(sql).list();
	}
	
}
