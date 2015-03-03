/**
 * 
 */
package com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dao.hibernate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dao.ElectronicReviewDao;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

/**
 * @author shenjz
 *
 */
@Repository
public class ElectronicReviewDaoHibernate extends BaseGenericDaoHibernate<Project> implements ElectronicReviewDao{
	
	/**
	 * FuncName: getList
	 * Description :  获取已到评审时间的项目集合
	 * @param 
	 * @return List<Project>
	 * @author: shenjz
	 * @Create Date:2011-3-23  上午11:23:40
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-23  上午11:23:40
	 */
	public List getList(String signUpSTime, String signUpSTime_op, String signUpETime, String signUpETime_op, String submitStTime, String submitStTime_op, String submitETime, String submitETime_op, String openBidSDate, String openBidSDate_op, String evalSTime, String evalSTime_op, String evalETime, String evalETime_op,String tenderType,String tenderType_op){
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		sb.append("select t1.objId,t1.projCode,t1.projName,t1.content,t1.projSummary,t1.agencies.objId,t1.tenderType,t1.budgetTotalMoney,t1.purCategoryIds,t1.purCategoryNames,t1.goodsClassIds,t1.goodsClassNames,t3.objId,t1.buyersId,t1.buyersName,t2.openBidSDate,t2.openBidAddr,t2.evalSTime,t2.evalETime,t1.ebuyMethod ,t1.agencies.orgName ,t4.name  from Project t1,ProjectRule t2,PurchaseDoc t3 , Employee t4    ");
		sb.append(" where t1.objId = t2.project.objId");
		sb.append(" and t1.objId = t3.project.objId "); 
		sb.append(" and t1.manager.objId = t4.objId "); 
		//拼装查询条件
		if (!StringUtils.empty(tenderType) && !StringUtils.empty(tenderType_op)) {
			String[]tenderTypes = tenderType.split(",");
			for (int i = 0; i < tenderTypes.length; i++) {
				if(i==0){
					sb2.append("'"+tenderTypes[i]+"'");
				}else{
					sb2.append(",'"+tenderTypes[i]+"'");
				}
			}
			sb.append(" and t1.tenderType "+tenderType_op+"("+sb2+")");
		}
		ArrayList<Object> paramArry = new ArrayList();
		if (!StringUtils.empty(signUpSTime) && !StringUtils.empty(signUpSTime_op)) {
			sb.append(" and t2.openBidSDate " + signUpSTime_op + "?"); //评审系统决定用报名开始时间传入参数来查询开标时间  by liuke 2011-0615
			try {
				paramArry.add(DateUtil.parse(signUpSTime, "yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.empty(signUpETime) && !StringUtils.empty(signUpETime_op)) {
			sb.append(" and t2.openBidSDate " + signUpETime_op  + "?"); //评审系统决定用报名开始时间传入参数来查询开标时间  by liuke 2011-0615
			try {
				paramArry.add(DateUtil.parse(signUpETime, "yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.empty(submitStTime) && !StringUtils.empty(submitStTime_op)) {
			sb.append(" and t2.submitStTime " + submitStTime_op  + "?"); 
			try {
				paramArry.add(DateUtil.parse(submitStTime, "yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.empty(submitETime) && !StringUtils.empty(submitETime_op)) {
			sb.append(" and t2.submitETime " + submitETime_op  + "?"); 
			try {
				paramArry.add(DateUtil.parse(submitETime, "yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.empty(openBidSDate) && !StringUtils.empty(openBidSDate_op)) {
			sb.append(" and t2.openBidSDate " + openBidSDate_op  + "?"); 
			try {
				paramArry.add(DateUtil.parse(openBidSDate, "yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.empty(evalSTime) && !StringUtils.empty(evalSTime_op)) {
			sb.append(" and t2.evalSTime " + evalSTime_op  + "?"); 
			try {
				paramArry.add(DateUtil.parse(evalSTime, "yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.empty(evalETime) && !StringUtils.empty(evalETime_op)) {
			sb.append(" and t2.evalETime " + evalETime_op  + "?"); 
			try {
				paramArry.add(DateUtil.parse(evalETime, "yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		//转换查询条件为Object
		Object[] hqlPparams = new Object[paramArry.size()];
		for(int i = 0 ; i < paramArry.size() ; i ++ ){
			hqlPparams[i] = paramArry.get(i);
		}
		
		return this.findbyHql(sb.toString(), hqlPparams);
	}

	
	
	/**
	 * FuncName: getListByProjCode
	 * Description :  根据编号查询项目信息
	 * @param 
	 * @param prjCode
	 * @return List
	 * @author: liuke
	 * @Create Date:2011-9-7  下午01:26:35
	 * @Modifier: liuke
	 * @Modified Date:2011-9-7  下午01:26:35
	 */
	public List getListByProjCode(String prjCode) {
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		sb.append("select t1.objId,t1.projCode,t1.projName,t1.content,t1.projSummary,t1.agencies.objId,t1.tenderType,t1.budgetTotalMoney,t1.purCategoryIds,t1.purCategoryNames,t1.goodsClassIds,t1.goodsClassNames,t3.objId,t1.buyersId,t1.buyersName,t2.openBidSDate,t2.openBidAddr,t2.evalSTime,t2.evalETime,t1.ebuyMethod ,t1.agencies.orgName ,t4.name  from Project t1,ProjectRule t2,PurchaseDoc t3 , Employee t4    ");
		sb.append(" where t1.objId = t2.project.objId");
		sb.append(" and t1.objId = t3.project.objId "); 
		sb.append(" and t1.manager.objId = t4.objId "); 
		sb.append(" and t1.projCode = '"+prjCode+"'");
		sb.append(" and t1.parentId is null ");
		return this.findbyHql(sb.toString());
	}


	/**
	 * FuncName: getOpenBidProjectList
	 * Description :  根据查询条件获取待开标的项目清单xml
	 * @param startDate:开始时间(例：2011-10-03 23:00:33)
	 * @param endDate:结束时间(例：2011-10-04 23:00:33)
	 * @param tenderType:项目类型(枚举：01:政府采购, 02:土地交易, 03:产权交易,04:建筑工程,00:所有)
     * @param agencyCode:代理机构编号
     * @param username:经办人帐号
	 * @return String
	 * @author: liuy	 
	 * @Modified Date:2011-10-20  上午11:23:40
	 */
	public List getOpenBidProjectList(String startDate,
			String endDate, String tenderType, String agencyCode,
			String username) {
		StringBuffer sb = new StringBuffer();
		sb.append("select t1.objId,t1.projCode,t1.projName,t1.content,t1.projSummary,t1.agencies.objId,t1.tenderType,t1.budgetTotalMoney,t1.purCategoryIds,t1.purCategoryNames,t1.goodsClassIds,t1.goodsClassNames,t3.objId,t1.buyersId,t1.buyersName,t2.openBidSDate,t2.openBidAddr,t2.evalSTime,t2.evalETime,t1.ebuyMethod ,t1.agencies.orgName ,t4.name,t1.monitor.objId  from Project t1,ProjectRule t2,PurchaseDoc t3 , Employee t4    ");
		sb.append(" where t1.objId = t2.project.objId");
		sb.append(" and t1.objId = t3.project.objId "); 
		sb.append(" and t1.manager.objId = t4.objId "); 
		//拼装查询条件
		if (!StringUtils.empty(tenderType)) {
			sb.append(" and t1.tenderType='"+tenderType+"'");
		}
		
		if (!StringUtils.empty(startDate)) {
			sb.append(" and "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().dateTime2String("t2.openBidSDate")+" >= '"+startDate+"' "); 
		}
		
		if (!StringUtils.empty(endDate)) {
			sb.append(" and "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().dateTime2String("t2.openBidSDate")+" <= '"+endDate+"' "); 
		}
		
		if(!StringUtils.empty(agencyCode)){//若有代理机构，则需要根据代理机构过滤查询出的项目数据
			if(!StringUtils.empty(username)){//若有经办人帐号，则需要根据代理机构经办人过滤查询出的项目数据
				//sb.append(" and t1.agencies.orgCode='" + agencyCode  + "'"); 
			}
			sb.append(" and t1.agencies.orgCode='" + agencyCode  + "'"); 
		}
		return this.findbyHql(sb.toString());
	}
	
	
}
