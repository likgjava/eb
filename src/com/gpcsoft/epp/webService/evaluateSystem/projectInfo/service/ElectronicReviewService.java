
package com.gpcsoft.epp.webService.evaluateSystem.projectInfo.service;

import java.util.List;

import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dto.ApplyDTO;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dto.CongruousFactorDTO;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dto.ProjectDTO;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dto.WorkMembersDTO;
import com.gpcsoft.srplatform.auth.domain.Attachment;


/**
 * @author shenjz
 *
 */

public interface ElectronicReviewService {
	
	/**
	 * FuncName: getList
	 * Description :  获取已到评审时间的项目集合(电子评审系统接口)
	 * @param tenderType_op TODO
	 * @param 
	 * @return List<Project>
	 * @author: shenjz
	 * @Create Date:2011-3-23  上午11:23:40
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-23  上午11:23:40
	 */
	public List<ProjectDTO> getList(String signUpSTime, String signUpSTime_op, String signUpETime, String signUpETime_op, String submitStTime, String submitStTime_op, String submitETime, String submitETime_op, String openBidSDate, String openBidSDate_op, String evalSTime, String evalSTime_op, String evalETime, String evalETime_op, String tenderType,String tenderType_op);
	
	/**
	 * FuncName: getCongruousFactorDTOList
	 * Description : 获取指标集合(电子评审系统接口)
	 * @param 
	 * @return List<CongruousFactorDTO>
	 * @author: shenjz
	 * @Create Date:2011-3-24  下午01:55:28
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-24  下午01:55:28
	 */
	public String getCongruousFactor(String projectCode,String packCode);
	
	/**
	 * FuncName: getWorkMemberList
	 * Description :  获取评审专家集合(电子评审系统接口)
	 * @param 
	 * @return List<WorkgMember>
	 * @author: shenjz
	 * @Create Date:2011-3-24  上午09:25:02
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-24  上午09:25:02
	 */
	public List<WorkMembersDTO>  getWorkMemberList();
	
	/**
	 * FuncName: getSignUprecordList
	 * Description :  获取报名集合(电子评审系统接口)
	 * @param tenderIdList 项目Id List
	 * @return List<SignUprecord>
	 * @author: shenjz
	 * @Create Date:2011-3-24  上午11:33:21
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-24  上午11:33:21
	 */
	public List<ApplyDTO> getSignUprecordList(List<String> tenderIdList)throws Exception;
	
	
	
	
	/**
	 * FuncName: saveOpenBidByXml
	 * Description :  根据xml文档录入开标结果对象
	 * @param 
	 * @param xml
	 * @return boolean
	 * @author: liuke
	 * @Create Date:2011-5-26  上午09:32:03
	 * @Modifier: liuke
	 * @Modified Date:2011-5-26  上午09:32:03
	 */
	public Project saveOpenBidByXml(String xml) throws Exception;
	
	
	
	/**
	 * FuncName: saveEvalBidRecord
	 * Description :  根据xml文档录入评标结果对象
	 * @param 
	 * @param xml
	 * @return boolean
	 * @author: liuke
	 * @Create Date:2011-5-27  上午11:32:56
	 * @Modifier: liuke
	 * @Modified Date:2011-5-27  上午11:32:56
	 */
	public Project saveEvalBidRecordByXml(String xml)throws Exception;
	
	
	
	/**
	 * FuncName: getBidAttachmentByPackCodeAndOrgCode
	 * Description :  根据包件Code与机构Code获得投标文件附件对象
	 * @param tenderCode
	 * @param packCode
	 * @param orgCode
	 * @return Attachment
	 * @author: liuke
	 * @Create Date:2011-7-1  上午11:54:45
	 * @Modifier: liuke
	 * @Modified Date:2011-7-1  上午11:54:45
	 */
	public Attachment getBidAttachmentByTenderCodeAndOrgCode(String tenderCode,String packCode, String orgCode)throws Exception; 
	
	
	
	
	/**
	 * FuncName: ProjectListForSimpleInfo
	 * Description :  获取项目清单
	 * @param 
	 * @param signUpSTime
	 * @param signUpSTime_op
	 * @param signUpETime
	 * @param signUpETime_op
	 * @param submitStTime
	 * @param submitStTime_op
	 * @param submitETime
	 * @param submitETime_op
	 * @param openBidSDate
	 * @param openBidSDate_op
	 * @param evalSTime
	 * @param evalSTime_op
	 * @param evalETime
	 * @param evalETime_op
	 * @param tenderType
	 * @param tenderType_op
	 * @return Project
	 * @author: liuke
	 * @Create Date:2011-9-7  上午09:52:43
	 * @Modifier: liuke
	 * @Modified Date:2011-9-7  上午09:52:43
	 */
	public List<Project> getProjectListForSimpleInfo(String signUpSTime, String signUpSTime_op, String signUpETime, String signUpETime_op, String submitStTime, String submitStTime_op, String submitETime, String submitETime_op, String openBidSDate, String openBidSDate_op, String evalSTime, String evalSTime_op, String evalETime, String evalETime_op,String tenderType,String tenderType_op);
	
	
	
	
	/**
	 * FuncName: getProjectInfoByCode
	 * Description :  创建或修改对象
	 * @param 
	 * @param prjCode
	 * @param packCodes
	 * @return List<ProjectDTO>
	 * @author: liuke
	 * @Create Date:2011-9-7  上午11:54:39
	 * @Modifier: liuke
	 * @Modified Date:2011-9-7  上午11:54:39
	 */
	public List<ProjectDTO> getProjectInfoByCode(String prjCode,String packCodes);
	
	
	
	/**
	 * FuncName: getApplyInfoByCode
	 * Description :  根据包组编号获取报名信息
	 * @param 
	 * @param tenderIdList
	 * @param projCode
	 * @return
	 * @throws Exception List<ApplyDTO>
	 * @author: liuke
	 * @Create Date:2011-9-8  上午10:46:12
	 * @Modifier: liuke
	 * @Modified Date:2011-9-8  上午10:46:12
	 */
	public List<ApplyDTO> getApplyInfoByCode(List<String> tenderIdList,String projCodes)throws Exception;
	
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
	public List<ProjectDTO> getOpenBidProjectList(String startDate, String endDate, String tenderType, String agencyCode, String username);
	
}
