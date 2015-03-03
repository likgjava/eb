package com.gpcsoft.agreement.bargin.invitation.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.invitation.service.InvitationService;
import com.gpcsoft.agreement.bargin.project.dao.ProjectContactInfoDao;
import com.gpcsoft.agreement.bargin.project.dao.ProjectPayInfoDao;
import com.gpcsoft.agreement.bargin.project.dao.ProjectSignInfoDao;
import com.gpcsoft.agreement.bargin.project.domain.ProjectContactInfo;
import com.gpcsoft.agreement.bargin.project.domain.ProjectPayInfo;
import com.gpcsoft.agreement.bargin.project.domain.ProjectSignInfo;
import com.gpcsoft.agreement.bargin.require.dao.RequireItemDao;
import com.gpcsoft.agreement.bargin.require.domain.RequireItem;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.organization.dao.OrgInfoDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.manager.OrgInfoManager;
import com.gpcsoft.cms.util.FileUtils;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.inviterollrequ.domain.InrqDetail;
import com.gpcsoft.epp.inviterollrequ.domain.Inviterollrequ;
import com.gpcsoft.epp.inviterollrequ.manager.InrqDetailManager;
import com.gpcsoft.epp.inviterollrequ.manager.InviterollrequManager;
import com.gpcsoft.epp.inviterollrequ.service.impl.InviterollrequServiceImpl;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.manager.ProjProcessRuleManager;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.utils.MailUtil;
import com.gpcsoft.pubservice.utils.MessageUtil;
import com.gpcsoft.pubservice.utils.MobileMessageUtil;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.AttachmentManager;
import com.gpcsoft.srplatform.auth.manager.UserManager;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;
import com.gpcsoft.srplatform.common.utils.SysInfo;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigItem;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigItemPreValue;
import com.gpcsoft.srplatform.sysconfig.manager.SysConfigManager;

@Service 
public class InvitationServiceImpl extends InviterollrequServiceImpl implements InvitationService {

	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	@Autowired(required=true) @Qualifier("attachmentManagerImpl")
	private AttachmentManager attachmentManager;
	@Autowired(required=true) @Qualifier("inviterollrequManagerImpl")
	private InviterollrequManager inviterollrequManager;
	@Autowired(required=true) @Qualifier("inrqDetailManagerImpl")
	private InrqDetailManager inrqDetailManager;
	@Autowired(required=true) @Qualifier("orgInfoManagerImpl")
	private OrgInfoManager orgInfoManager;
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	@Autowired(required=true)  @Qualifier("orgInfoDaoHibernate")
	private OrgInfoDao orgInfoDaoHibernate;
	@Autowired(required=true) @Qualifier("sysConfigManagerImpl")
	private SysConfigManager sysConfigManager;
	@Autowired(required=true) @Qualifier("projProcessRuleManagerImpl")
	private ProjProcessRuleManager projProcessRuleManager;
	@Autowired(required=true) @Qualifier("requireItemDaoHibernate")
	private RequireItemDao requireItemDao;
	@Autowired(required=true) @Qualifier("userManagerImpl")
	private UserManager userManagerImpl;
	@Autowired(required=true)  @Qualifier("goodsClassDaoHibernate")
	private GoodsClassDao goodsClassDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("projectPayInfoDaoHibernate")
	private ProjectPayInfoDao projectPayInfoDao;
	
	@Autowired(required=true)  @Qualifier("projectSignInfoDaoHibernate")
	private ProjectSignInfoDao projectSignInfoDao;
	
	@Autowired(required=true)  @Qualifier("projectContactInfoDaoHibernate")
	private ProjectContactInfoDao projectContactInfoDao;
	
	/** 
	 * Description : 保存邀请函
	 * Create Date: 2010-10-8下午05:19:42 by guoyr  Modified Date: 2010-10-8下午05:19:42 by guoyr
	 * @param content 邀请函内容  supplierIds邀请供应商
	 * @return  
	 * @throws Exception 
	 * @Exception   
	 */
	public Inviterollrequ saveInvitation(Inviterollrequ inviterollrequ,
			String content, String supplierIds) throws Exception {
		/** 邀请函的储路径 */
		Properties properties = new Properties();
        InputStream in =InvitationServiceImpl.class.getClassLoader().getResourceAsStream("sys/attachment.properties");
        properties.load(in);
        String path = properties.getProperty("defaultUrl") ;
        
		Attachment attachment =null;
		if(null != inviterollrequ.getAttachment() && null != inviterollrequ.getAttachment().getObjId()){
			attachment = attachmentManager.get(inviterollrequ.getAttachment().getObjId());
		}else {
			attachment = new Attachment();
			attachment.setFileName(java.util.UUID.randomUUID().toString().replace('-', '_'));
		}
		if(null == inviterollrequ.getCreateTime()){
			inviterollrequ.setCreateTime(new Date());
		}
//		attachment.setPath(path);
		attachment.setViewName(inviterollrequ.getProjName()+"邀请函");
		attachment.setCreateTime(inviterollrequ.getCreateTime());
		attachmentManager.save(attachment);
		inviterollrequ.setAttachment(attachment);
		inviterollrequManager.save(inviterollrequ);
		
		// 保存邀请函
		//FileUtil.writerFile(path+attachment.getFileName(), content,"UTF-8");
		FileUtils.writerFile(path+attachment.getFileName(), content);
		
		// 为选 中的所有供应商发送邀请函
		for(String supplierId:supplierIds.split(",")){
			if(StringUtils.hasLength(supplierId)){
				InrqDetail inrqDetail = new InrqDetail();
				OrgInfo supplier = orgInfoManager.get(supplierId);
				inrqDetail.setSupplier(supplier);
				inrqDetail.setInviterollrequ(inviterollrequ);
				inrqDetailManager.save(inrqDetail);
				String emaile = "gslzgyr@163.com";
				if(null != supplier.getCompany()){
					//获取机构管理员用户
					User user = userManagerImpl.getManagerUser(supplier.getCompany().getObjId(), User.USER_TYPE_MANAGER.toString());
					emaile = user.getEmail();
					
					//获取‘邀请供应商参与项目短信通知的内容模板’的内容
					Map<String, Object> templateMap = new HashMap<String, Object>();
					templateMap.put("projectName", inviterollrequ.getProjName());
					templateMap.put("siteName", SysInfo.getInstance().getSitename());
					
					//发送短信
					MobileMessageUtil.sendMobileMessage(
							user.getEmp().getMobile(), 
							AuthenticationHelper.getCurrentUser(true).getObjId(), 
							AuthenticationHelper.getCurrentUser(true).getUsername(),
							user.getObjId(), 
							user.getUsername(), 
							null, 
							messageSource.getMessage(CustomerMessage.INVITATION_SUPPLIER_MOBILE_MESSAGE_CONTENT), 
							templateMap);
				}
				
				 // 替换单位名称
		        content = content.replaceFirst("尊敬的负责人", "尊敬的"+supplier.getOrgName()+"负责人");
		        Map<String,Object> model = new HashMap<String,Object>();
		        model.put("content", content);//邀请函内容
		        //发送邮件
		       MailUtil.sendEmailNotAlways(
		        		new String[]{emaile}, 
		        		messageSource.getMessage(CustomerMessage.INVITATION_EMAIL_SUBJECT), 
		        		null, 
		        		messageSource.getMessage(CustomerMessage.INVITATION_EMAIL_TEMPLATE), 
		        		model);
		        
			}
		}
		
		Map<String, Object> templateMap = new HashMap<String, Object>();
		templateMap.put("projectName", inviterollrequ.getProjName());
		
		// 给选中机构的管理员发站内信
		MessageUtil.sendMessageSystem(
				orgInfoDaoHibernate.getAllManagersByOrgInfoIds(User.USER_TYPE_MANAGER.toString(), supplierIds),
				inviterollrequ.getProjName(),
				null,
				messageSource.getMessage(CustomerMessage.INVITATION_MESSAGE_TEMPLATE),
				templateMap,
				true);
		return inviterollrequ;
	}
	
	/** 
	 * Description : 跳转到邀请函页面时获得邀请函内容
	 * Create Date: 2010-10-29下午03:31:44 by guoyr  Modified Date: 2010-10-29下午03:31:44 by guoyr
	 * @param   
	 * @return  
	 * @throws Exception 
	 * @Exception   
	 */
	public Map<String, Object> getInvitationInfo(String projectId) throws Exception{
		
		Map<String, Object> model = new HashMap<String, Object>();
		Inviterollrequ inviterollrequ = null;
		String contentsString = "";
		List<InrqDetail> inrqDetailList = new ArrayList<InrqDetail>();
		
		// 如果是根据项目发布公告，读取公告的模板
		if(StringUtils.hasLength(projectId)){
			Project project = projectManager.get(projectId);
			model.put("project", project);
			
			QueryObject<Inviterollrequ> queryObject = new QueryObjectBase<Inviterollrequ>();
			queryObject.setEntityClass(Inviterollrequ.class);
			queryObject.getQueryParam().and(new QueryParam("project.objId",QueryParam.OPERATOR_EQ,projectId));
			List<Inviterollrequ> list = inviterollrequManager.findByQuery(queryObject);
			// 如果该项目已经有邀请函
			if(null != list && list.size() > 0) {
				inviterollrequ = list.get(0);
				
				//根据邀请函获取邀请名单
				QueryObject<InrqDetail> inrqDetailQueryObject = new QueryObjectBase<InrqDetail>();
				inrqDetailQueryObject.setEntityClass(InrqDetail.class);
				inrqDetailQueryObject.getQueryParam().and(new QueryParam("inviterollrequ.objId",QueryParam.OPERATOR_EQ,inviterollrequ.getObjId()));
				inrqDetailList = inrqDetailManager.findByQuery(inrqDetailQueryObject);
				Attachment attachment = inviterollrequ.getAttachment();
				contentsString = FileUtils.readFileByLines(messageSource.getMessage("defaultUrl")+attachment.getFileName());
			}else {// 生成新的邀请函
				inviterollrequ = new Inviterollrequ();
				// 获得邀请函的内容
				contentsString = getInviterollrequContent(project);
			}
		}
		
		model.put("inrqDetailList", inrqDetailList);
		model.put("inviterollrequ", inviterollrequ);
		model.put("inviterollrequContents", contentsString);
		return model;
	}
	
	/** 
	 * Description :  得到新的邀请函内容
	 * Create Date: 2010-11-17下午06:10:31 by guoyr  Modified Date: 2010-11-17下午06:10:31 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String getInviterollrequContent(Project project) throws Exception{
		
		String contentsString = "";
		Map<String, Object> templateMap = new HashMap<String, Object>();
		templateMap.put("project", project);
		templateMap.put("nowDate", DateUtil.format(new Date(), "yyyy年MM月dd日"));
		User user = AuthenticationHelper.getCurrentUser(true);
		templateMap.put("userName", user.getEmp().getName());
		templateMap.put("companyName", user.getEmp().getCompany().getName());
		List<ProjProcessRule> projruleList = projProcessRuleManager.getProjProcessRuleByProjectId(project.getObjId());
		
		for (ProjProcessRule projProcessRule :projruleList) {
			
			// 报价规则
			if(SysConfigEnum.BargainProjectRule_singlePrice.equals(projProcessRule.getCode())){
				SysConfigItem sysConfigItem = sysConfigManager.getSysConfigItemByCode(SysConfigEnum.BargainProjectRule + "_" + SysConfigEnum.BargainProjectRule_singlePrice);
				for(SysConfigItemPreValue sysConfigItemPreValue: sysConfigItem.getPreValues()){
					if(sysConfigItemPreValue.getCode().equals(projProcessRule.getResValue())){
						projProcessRule.setResValue(sysConfigItemPreValue.getName());
						templateMap.put("singlePrice",sysConfigItemPreValue.getName());
					}
				}
			}
			
			// 成交规则
			if(SysConfigEnum.BargainProjectRule_bargainRule.equals(projProcessRule.getCode())){
				SysConfigItem sysConfigItem = sysConfigManager.getSysConfigItemByCode(SysConfigEnum.BargainProjectRule + "_" + SysConfigEnum.BargainProjectRule_bargainRule);
				for(SysConfigItemPreValue sysConfigItemPreValue: sysConfigItem.getPreValues()){
					if(sysConfigItemPreValue.getCode().equals(projProcessRule.getResValue())){
						templateMap.put("bargainRule", sysConfigItemPreValue.getName());
					}
				}
			}
			
		}
		
		//是否显示采购人预算
		String buyerBudget = "0";
		
		//项目扩展信息
		ProjectPayInfo projectPayInfo =  projectPayInfoDao.getPayInfoByProjectId(project.getObjId());
		if(projectPayInfo==null){
			projectPayInfo = new ProjectPayInfo();
		}
		templateMap.put("projectPayInfo", projectPayInfo);
		
		//项目报名条件
		ProjectSignInfo projectSignInfo =  projectSignInfoDao.getSignInfoByProjectId(project.getObjId());
		if(projectSignInfo==null){
			projectSignInfo = new ProjectSignInfo();
		}
		templateMap.put("projectSignInfo", projectSignInfo);
		
		//获取项目联系方式
		ProjectContactInfo projectContactInfo = projectContactInfoDao.getContactInfoByProjectId(project.getObjId());
		if(projectContactInfo==null) {
			projectContactInfo = new ProjectContactInfo();
		}
		templateMap.put("projectContactInfo", projectContactInfo);
		
		List<RequireItem> requireItemList = new ArrayList<RequireItem>();
		try {
			requireItemList = requireItemDao.getRequireItemsByProjId(project.getObjId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Map<String, String>> goodsList  = new ArrayList<Map<String, String>>();
		int i=1;
		for(RequireItem requireItem:requireItemList){
			Map<String, String> cell = new HashMap<String, String>();
			// 序号
			cell.put("no",""+i++);
			
			// 商品分类
			String goodsClass = "--";
			if(null != requireItem.getGoodsClass() && null != requireItem.getGoodsClass().getObjId()){
				// 解决第二次获取列表时取不到代理对象
				if(null != requireItem.getGoodsClass().getGoodsClassName()){
					goodsClass = requireItem.getGoodsClass().getGoodsClassName();
				}else {
					GoodsClass obj = goodsClassDaoHibernate.get(requireItem.getGoodsClass().getObjId());
					goodsClass = obj.getGoodsClassName();
				}
			}
			cell.put("goodsClass",goodsClass);
			
			// 商品名称/描述(避免因数据库中出现null字附串而显示到公告中)
			String name = ((null != requireItem.getDescr()&& !"null".equals(requireItem.getDescr()) && !"".equals(requireItem.getDescr()))?(requireItem.getDescr()):"");
			if(null != requireItem.getGoods() && null != requireItem.getGoods().getProductName()){
				name = requireItem.getGoods().getProductName()+(!"".equals(name)?(":"+name):"");
			}
			cell.put("name", name);
			
			// 商品型号
			String code = "--";
			if(null != requireItem.getGoods() && null != requireItem.getGoods().getProductCode()){
				code = requireItem.getGoods().getProductCode();
			}
			cell.put("code", code);
			
			// 数量
			cell.put("qty", ""+requireItem.getGoodsQty());
			
			//是否显示采购人预算
			if(buyerBudget.equals("0")){
				cell.put("price", "--");
			}else{
				cell.put("price", "" + requireItem.getGoodsPrice());
			}
			goodsList.add(cell);
		}
		templateMap.put("goodsList",goodsList);
		
		// 默认为议价邀请函模板
		String templatePath = CustomerMessage.INVITATION_BARGAIN_TEMPLATE;
		
		// 如果是竞价
		if(project.getEbuyMethod().equals(EbuyMethodEnum.COMPETITION)){
			templatePath = CustomerMessage.INVITATION_TEMPLATE;
		}
		contentsString = freeMarkerService.getFreeMarkerContent(messageSource.getMessage(templatePath), templateMap);
	
		return contentsString;
	}
	
}
