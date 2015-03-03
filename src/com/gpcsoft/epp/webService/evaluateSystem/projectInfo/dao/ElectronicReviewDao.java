/**
 * 
 */
package com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dao;

import java.util.List;

import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dto.ProjectDTO;

/**
 * @author shenjz
 *
 */
public interface ElectronicReviewDao {
	
	/**
	 * FuncName: getList
	 * Description :  获取已到评审时间的项目集合
	 * @param tenderType_op TODO
	 * @param 
	 * @return List<Project>
	 * @author: shenjz
	 * @Create Date:2011-3-23  上午11:23:40
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-23  上午11:23:40
	 */
	public List getList(String signUpSTime, String signUpSTime_op, String signUpETime, String signUpETime_op, String submitStTime, String submitStTime_op, String submitETime, String submitETime_op, String openBidSDate, String openBidSDate_op, String evalSTime, String evalSTime_op, String evalETime, String evalETime_op,String tenderType,String tenderType_op);
	
	
	/**
	 * 
	 * FuncName: getListByProjCode
	 * Description :  创建或修改对象
	 * @param 
	 * @param prjCode
	 * @return List
	 * @author: liuke
	 * @Create Date:2011-9-7  下午01:26:35
	 * @Modifier: liuke
	 * @Modified Date:2011-9-7  下午01:26:35
	 */
	public List getListByProjCode(String prjCode);
	
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
	public List getOpenBidProjectList(String startDate, String endDate, String tenderType, String agencyCode, String username);
}
