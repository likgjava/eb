package com.gpcsoft.agreement.bargin.bulletin.service.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.bulletin.dao.BulletinShowDao;
import com.gpcsoft.agreement.bargin.bulletin.service.BulletinShowService;
import com.gpcsoft.agreement.bargin.signup.dao.SignUprecordDaoXygh;
import com.gpcsoft.agreement.order.enumeration.OrderEnum;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.manager.OrgInfoManager;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.bizplatform.suppliers.manager.SupplierManager;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.bid.domain.BailRecord;
import com.gpcsoft.epp.bid.service.BailRecordService;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.purchasedoc.domain.DosBuyRecord;
import com.gpcsoft.epp.purchasedoc.domain.DosBuyTypeEnum;
import com.gpcsoft.epp.purchasedoc.manager.DosBuyRecordManager;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.utils.MailUtil;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;

@Service
public class BulletinShowServiceImpl extends BaseGenericServiceImpl<Bulletin> implements BulletinShowService {

	@Autowired(required=true)  @Qualifier("bulletinShowDaoHibernate")
	private BulletinShowDao bulletinShowDaoHibernate;
	
	@Autowired(required=true) @Qualifier("signUprecordDaoXyghHibernate")
	private SignUprecordDaoXygh signUprecordDaoXyghHibernate;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true) @Qualifier("bailRecordServiceImpl")
	private BailRecordService bailRecordService;
	
	@Autowired(required=true) @Qualifier("dosBuyRecordManagerImpl")
	private DosBuyRecordManager dosBuyRecordManager;
	
	@Autowired(required=true) @Qualifier("supplierManagerImpl")
	private SupplierManager supplierManager;
	
	@Autowired(required=true) @Qualifier("orgInfoManagerImpl")
	private OrgInfoManager orgInfoManager;
	
	/** 
	 * Description :  获取不同采购价区段的项目数量
	 * Create Date: 2010-10-20下午02:47:47 by likg  Modified Date: 2010-10-20下午02:47:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getBulletinListShowByBudgetMoney(Map<String, Object> paramsMap) throws Exception 
	{
		return bulletinShowDaoHibernate.getBulletinListShowByBudgetMoney(paramsMap);
	}

	/** 
	 * Description :  获取采购项目公告
	 * Create Date: 2010-10-20下午04:48:34 by likg  Modified Date: 2010-10-20下午04:48:34 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Bulletin> getBulletinListForShow(Page<Bulletin> page, Map<String, Object> paramsMap) throws Exception 
	{
		return bulletinShowDaoHibernate.getBulletinListForShow(page, paramsMap);
	}
	
	
	/** 
	 * Description :  获取采购预告
	 * Create Date: 2010-12-30下午04:48:34 by dongcl  Modified Date: 2010-12-30下午04:48:34 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Object> getBulletinBuyPreList(Page<Object> page, Map<String, Object> paramsMap) throws Exception 
	{
		return bulletinShowDaoHibernate.getBulletinBuyPreList(page, paramsMap);
	}
	

	/** 
	 * Description :  根据不同的采购价区间获得包含采购项目数量的区域信息
	 * Create Date: 2010-10-20下午06:14:29 by likg  Modified Date: 2010-10-20下午06:14:29 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getDistrictListForShow(Map<String, Object> param) throws Exception {
		return bulletinShowDaoHibernate.getDistrictListForShow(param);
	}

	/** 
	 * Description :  根据条件获得不同状态的项目公告
	 * Create Date: 2010-10-23上午10:32:16 by likg  Modified Date: 2010-10-23上午10:32:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Bulletin> getBulletinForList(Page<Bulletin> page, Map<String, Object> paramsMap) throws Exception 
	{
		return bulletinShowDaoHibernate.getBulletinForList(page, paramsMap);
	}

	/** 
	 * Description :  获取商品分类信息
	 * Create Date: 2010-11-23上午10:08:27 by likg  Modified Date: 2010-11-23上午10:08:27 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getGoodsClassList(Map<String, Object> params) throws Exception 
	{
		return bulletinShowDaoHibernate.getGoodsClassList(params);
	}

	/** 
	 * Description :  获取机构发布的竞价项目公告数量
	 * Create Date: 2011-2-16下午04:31:02 by likg  Modified Date: 2011-2-16下午04:31:02 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Long getBulletinNum(Map<String, Object> param) throws Exception {
		return bulletinShowDaoHibernate.getBulletinNum(param);
	}

	/** 
	 * Description :  将项目采购公告通过邮件分享给好友
	 * Create Date: 2011-2-28上午11:30:05 by likg  Modified Date: 2011-2-28上午11:30:05 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void shareBulletin(Map<String, Object> param) throws Exception {
		String bulletinId = (String) param.get("bulletinId"); //项目公告id
		String toEmail = (String) param.get("toEmail"); //收件人的Email
		String bulletinContents = null;
		
		Bulletin bulletin = this.get(bulletinId);
		
		String uploadUrl=messageSource.getMessage("uploadUrl");
		
		try {
			Attachment content = bulletin.getContent();
			String attachPath = content.getPath()==null?"":content.getPath();
			String contentPath = uploadUrl + attachPath + content.getFileName();
			InputStream in = new FileInputStream(contentPath);
			bulletinContents = FileUtil.read(in);
			in.close();
		} catch (Exception e) {
			bulletinContents = "对不起！未找到公告文件！";
		}
		
		//获取邮件推荐公告的标题（从资源文件中获取）
		String subject = messageSource.getMessage(CustomerMessage.MAIL_RECOMMEND_BULLETIN_SUBJECT).replace("#bulletinTitle", bulletin.getBullTitle());
		
		//发送邮件
        MailUtil.sendEmailNotAlways(new String[]{toEmail}, subject, bulletinContents, null, null);
	}

	/** 
	 * Description :  获取项目的信息（包括项目进度，报名情况）
	 * Create Date: 2011-5-13下午02:43:14 by likg  Modified Date: 2011-5-13下午02:43:14 by likg
	 * @param   project:项目		bulletinType:公告类型
	 * @return  
	 * @Exception   
	 */
	public void getProjectInfo(Project project, String bulletinType, Map<String, Object> model) throws Exception {
		Long remainSignUpTime = 0L; //剩余报名时间(单位：毫秒)
		Long remainEvalTime = 0L; //剩余报价时间(单位：毫秒)
		Date nowDate = new Date(); //当前时间
		ProjectRule projectRule = null; //招标公告的项目规则信息
		
		//计算项目进度
		String processStatus = "1";
		//招标公告
		if(BulletinTypeEnum.PURCHASE_BULLETIN.equals(bulletinType)) {
			projectRule = projectManager.getProjectRuleByProjectId(project.getObjId());
			
			//项目结束
			if("01".equals(project.getTenderRecord())) {
				processStatus = "5";
			}
			//定标
			else if(projectRule.getOpenBidSDate()!=null && nowDate.after(projectRule.getOpenBidSDate())) {
				processStatus = "4";
			}
			//投标、评标
			else if(projectRule.getSubmitStTime()!=null && nowDate.after(projectRule.getSubmitStTime())) {
				processStatus = "3";
				if(nowDate.after(projectRule.getSignUpSTime()) && nowDate.before(projectRule.getSignUpETime())) {
					remainSignUpTime = projectRule.getSignUpETime().getTime() - nowDate.getTime();
				}
			}
			//供应商报名
			else if(projectRule.getSignUpSTime()!=null && projectRule.getSignUpETime()!=null && nowDate.after(projectRule.getSignUpSTime()) && nowDate.before(projectRule.getSignUpETime())) {
				processStatus = "2";
				remainSignUpTime = projectRule.getSignUpETime().getTime() - nowDate.getTime();
			}
			//发布公告
			else {
				processStatus = "1";
			}
		}
		//竞价公告
		else {
			//项目结束
			if("01".equals(project.getTenderRecord())) {
				processStatus = "5";
			}
			//确定成交供应商
			else if(ProjProcessStatusEnum.CALIBRATION_BID.equals(project.getProjProcessStatus()) || (project.getEvalEndTime()!=null && nowDate.after(project.getEvalEndTime()))) {
				processStatus = "4";
			}
			//供应商报价
			else if(project.getEvalStartTime()!=null && project.getEvalEndTime()!=null && nowDate.after(project.getEvalStartTime())) {
				processStatus = "3";
				if(nowDate.after(project.getSignUpSTime()) && nowDate.before(project.getSignUpETime())) {
					remainSignUpTime = project.getSignUpETime().getTime() - nowDate.getTime();
				}
			}
			//供应商报名
			else if(project.getSignUpSTime()!=null && project.getSignUpETime()!=null && nowDate.after(project.getSignUpSTime())) {
				processStatus = "2";
				if(nowDate.after(project.getSignUpSTime()) && nowDate.before(project.getSignUpETime())) {
					remainSignUpTime = project.getSignUpETime().getTime() - nowDate.getTime();
				}
			}
			//发布公告
			else {
				processStatus = "1";
			}
		}
		model.put("processStatus", processStatus);
		model.put("remainSignUpTime", remainSignUpTime);
				
		//判断当前用户类型
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = null;
		String userType = null; //用户类型
		if(user != null) {
			//orgInfo = (OrgInfo) user.getOrgInfo();
			//重新到数据库中查询OrgInfo(防止在登录期间管理员审核机构信息，而获取不到机构的审核状态)
			if(StringUtils.hasLength(user.getOrgInfo().getObjId())) {
				orgInfo = orgInfoManager.get(user.getOrgInfo().getObjId());
			}
			//非企业用户
			if(orgInfo == null) {
				userType = "personal";
			}
			//项目公告发布者
			else if(user.getObjId().equals(project.getCreateUser().getObjId())) {
				userType = "publisher";
			}
			//项目委托代理机构
			else if(project.getAgencies()!=null && orgInfo.getObjId().equals(project.getAgencies().getObjId())) {
				userType = "agency";
			}
			//供应商
			else if(StringUtils.hasLength(orgInfo.getSupplierId())) {
//				String roleStr = (String) model.get("roleStr");
//				if(StringUtils.hasLength(roleStr) && roleStr.indexOf("s")!=-1) {//&& to ||子机构的问题by yucy
//					userType = "supplier";
//					model.put("currentOrgId", orgInfo.getObjId());
//				}
				Supplier supplier = supplierManager.get(orgInfo.getSupplierId());
				if(OrganizationEnum.USE_VALID.equals(orgInfo.getUseStatus()) && OrganizationEnum.PASS_EXAM.equals(orgInfo.getAuditStatus()) && OrganizationEnum.ENABLE.equals(orgInfo.getIsOff()) && OrganizationEnum.PASS_EXAM.equals(supplier.getAuditStatus())) {
					userType = "supplier";
					model.put("currentOrgId", orgInfo.getObjId());
				}
			}
		} else {
			userType = "visitor"; //游客
		}
		model.put("userType", userType);
		
		//判断供应商是否报过名
		if("supplier".equals(userType)) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("orgId", orgInfo.getObjId());
			param.put("projectId", project.getObjId());
			//获取报名记录
			SignUprecord signUprecord = signUprecordDaoXyghHibernate.getSignUprecordByprojectAndSupplierId(project.getObjId(), orgInfo.getObjId());
			//是否报名
			Boolean hasSignUp = signUprecord==null?false:true;
			model.put("hasSignUp", hasSignUp);
			
			//已报名的供应商，计算项目的剩余报价时间
			if(hasSignUp) {
				//招标公告
				if(BulletinTypeEnum.PURCHASE_BULLETIN.equals(bulletinType)) {
					if(nowDate.before(projectRule.getSubmitStTime())) {
						remainEvalTime = -2L;
					} else if(nowDate.after(projectRule.getSubmitStTime()) && nowDate.before(projectRule.getSubmitETime())) {
						remainEvalTime = projectRule.getSubmitETime().getTime() - nowDate.getTime();
					}
					
					//根据项目规则，判断该项目是否分包
					model.put("isDividePack", projectRule.getIsDividePack());
					if(!projectRule.getIsDividePack()) { //未分包
						Boolean hasBailPay = false; //是否支付保证金
						Boolean hasDocPay = false; //是否购买采购文件
						
						//获取保证金支付记录
						BailRecord bailRecord = bailRecordService.getBailRecordBySignUprecord(signUprecord.getObjId());
						if(bailRecord != null && OrderEnum.HAS_PAY.equals(bailRecord.getBailStatus())) {
							hasBailPay = true;
						}
						
						//获取采购文件支付记录
						List<DosBuyRecord> dosBuyRecordList = dosBuyRecordManager.getDosBuyRecordBySupplierId(orgInfo.getObjId(), project.getObjId());
						if(dosBuyRecordList!=null && !dosBuyRecordList.isEmpty()) {
							DosBuyRecord dosBuyRecord = dosBuyRecordList.get(0);
							if(dosBuyRecord!=null && DosBuyTypeEnum.ALREADY_PAY.equals(dosBuyRecord.getDocBuyStatus())) {
								hasDocPay = true;
							}
						}
						
						model.put("hasDocPay", hasDocPay);
						model.put("hasBailPay", hasBailPay);
						
						//获取采购文件id，为查看支付记录调用（业务id为采购文件id）
						model.put("doc_pay_business_id", project.getObjId());
						
						//获取保证金支付业务ID就是项目ID
						model.put("bail_pay_business_id", project.getObjId());
					}
				} else {
					if(nowDate.before(project.getEvalStartTime())) {
						remainEvalTime = -2L;
					} else if(nowDate.after(project.getEvalStartTime()) && nowDate.before(project.getEvalEndTime())) {
						remainEvalTime = project.getEvalEndTime().getTime() - nowDate.getTime();
					}
				}
			}
			model.put("remainEvalTime", remainEvalTime);
		}
	}

	/** 
	 * Description :  获取公告列表（根据项目Id和公告类型）
	 * Create Date: 2011-8-16下午07:23:41 by likg  Modified Date: 2011-8-16下午07:23:41 by likg
	 * @param   projectId:项目Id bulletinType:公告类型
	 * @return  
	 * @Exception   
	 */
	public List<Bulletin> getBulletinList(String projectId, String bulletinType) throws Exception {
		//获取公告信息
    	QueryObject<Bulletin> query = new QueryObjectBase<Bulletin>();
    	query.setEntityClass(Bulletin.class);
    	if(StringUtils.hasLength(projectId)) {
    		query.getQueryParam().and(new QueryParam("project.objId",QueryParam.OPERATOR_EQ,projectId));
    	}
    	if(StringUtils.hasLength(bulletinType)) {
    		query.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,bulletinType));
    	}
    	
    	return this.findByQuery(query);
	}

}
