package com.gpcsoft.agreement.bargin.bulletin.manager.impl;

import java.io.File;
import java.io.IOException;
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

import com.gpcsoft.agreement.bargin.bulletin.manager.BulletinAgreementManager;
import com.gpcsoft.agreement.bargin.bulletin.service.impl.BulletinAgreementServiceImpl;
import com.gpcsoft.agreement.bargin.project.dao.ProjectContactInfoDao;
import com.gpcsoft.agreement.bargin.project.dao.ProjectPayInfoDao;
import com.gpcsoft.agreement.bargin.project.dao.ProjectSignInfoDao;
import com.gpcsoft.agreement.bargin.project.domain.ProjectContactInfo;
import com.gpcsoft.agreement.bargin.project.domain.ProjectPayInfo;
import com.gpcsoft.agreement.bargin.project.domain.ProjectSignInfo;
import com.gpcsoft.agreement.bargin.require.dao.RequireItemDao;
import com.gpcsoft.agreement.bargin.require.domain.RequireItem;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.base.purcategory.dao.PurCategoryDao;
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.bulletin.dao.BulletinDao;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinConfirmStatusEnum;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.manager.BulletinManager;
import com.gpcsoft.epp.bulletin.service.impl.BulletinServiceImpl;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.manager.ProjProcessRuleManager;
import com.gpcsoft.goods.goods.dao.GoodsDao;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.utils.SequenceNumberUtil;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.AttachmentManager;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;

import freemarker.template.TemplateException;

@Service 
public class BulletinAgreementManagerImpl extends BulletinServiceImpl implements BulletinAgreementManager {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("bulletinManagerImpl")
	private BulletinManager bulletinManager;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;

	@Autowired(required=true)  @Qualifier("bulletinDaoHibernate")
	private BulletinDao bulletinDaoHibernate;
	
	@Autowired(required=true) @Qualifier("attachmentManagerImpl")
	private AttachmentManager attachmentManager;
	
	@Autowired(required=true) @Qualifier("projProcessRuleManagerImpl")
	private ProjProcessRuleManager projProcessRuleManager;
	
	@Autowired(required=true) @Qualifier("requireItemDaoHibernate")
	private RequireItemDao requireItemDao;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true)  @Qualifier("goodsClassDaoHibernate")
	private GoodsClassDao goodsClassDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("purCategoryDaoHibernate")
	private PurCategoryDao purCategoryDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("goodsDaoHibernate")
	private GoodsDao goodsDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("projectPayInfoDaoHibernate")
	private ProjectPayInfoDao projectPayInfoDao;
	
	@Autowired(required=true)  @Qualifier("projectSignInfoDaoHibernate")
	private ProjectSignInfoDao projectSignInfoDao;
	
	@Autowired(required=true)  @Qualifier("projectContactInfoDaoHibernate")
	private ProjectContactInfoDao projectContactInfoDao;
	
	/** 
	 * Description : 保存公告
	 * Create Date: 2010-10-8下午05:19:42 by guoyr  Modified Date: 2010-10-8下午05:19:42 by guoyr
	 * @param   
	 * @return  
	 * @throws IOException 
	 * @Exception   
	 */
	public Bulletin saveBulletinAgreement(Bulletin bulletin) throws IOException {
		/** 公告文件的存储路径 */
		Properties properties = new Properties();
        InputStream in =BulletinAgreementServiceImpl.class.getClassLoader().getResourceAsStream("sys/attachment.properties");
        properties.load(in);
        String path = properties.getProperty("bulletinUrl") + DateUtil.getCurDate()+File.separator;
		Attachment attachment =null;
		if(null != bulletin.getContent() && null != bulletin.getContent().getObjId()){
			attachment = attachmentManager.get(bulletin.getContent().getObjId());
		}else {
			attachment = new Attachment();
			attachment.setFileName(java.util.UUID.randomUUID().toString().replace('-', '_'));
		}
		if(null == bulletin.getCreateTime()){
			bulletin.setCreateTime(new Date());
		}
		attachment.setPath(DateUtil.getCurDate()+File.separator);
		attachment.setViewName(bulletin.getBullTitle());
		attachment.setCreateTime(bulletin.getCreateTime());
		attachmentManager.save(attachment);
		bulletin.setContent(attachment);
		bulletin.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
		bulletinDaoHibernate.save(bulletin);
		// 修改项目过程状态
		if(null != bulletin.getProject() && StringUtils.hasLength(bulletin.getProject().getObjId()) && StringUtils.hasLength(bulletin.getProject().getProjProcessStatus())){
			projectManager.saveProjProcessStatus(bulletin.getProject().getObjId(), bulletin.getProject().getProjProcessStatus());
		}
		File bulletinFile = new File((path+attachment.getFileName()).replace("/", File.separator)); 
		//写入文件到指定路径
		if (!bulletinFile.getParentFile().exists()) {
			bulletinFile.getParentFile().mkdirs(); 
		}
		/** 把公告内容写到指定文件中 */
		FileUtil.writerFile(path+attachment.getFileName(),bulletin.getBullContents(),"UTF-8");
		
		return bulletin;
	}
	
	/** 
	 * Description : 保存公告（接口）
	 * Create Date: 2010-10-8下午05:19:42 by guoyr  Modified Date: 2010-10-8下午05:19:42 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Bulletin saveBulletinAgreementInterface(Project project)  throws Exception{
		Bulletin bulletin = new Bulletin();
		bulletin.setBulletinNo(SequenceNumberUtil.getBulletinSN()); //设置公告编号
		bulletin.setProject(project);
		bulletin.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
		bulletin.setAuditStatus(CommonEnum.USER_STATUS_FORMAL);
		bulletin.setBullType(BulletinTypeEnum.BARGIN_BULLETIN);
		bulletin.setRelDate(new Date()); // 发布时间
		bulletin.setRelStatus(BulletinConfirmStatusEnum.SURE); // 发布状态
		
		User relUser = AuthenticationHelper.getCurrentUser(true);
		bulletin.setRelUser(relUser.getEmp()); // 发布人
		
		
		Map<String, Object> templateMap = new HashMap<String, Object>();
		templateMap = this.getBulletinTemplateMap(project);
		String  contentsString = "";
		try {
			contentsString = freeMarkerService.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.BULLETIN_TEMPLATE), templateMap);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		
		/** 公告文件的存储路径 */
		
		String fileName = java.util.UUID.randomUUID().toString().replace('-', '_');
		
		Properties properties = new Properties();
        InputStream in = BulletinAgreementManagerImpl.class.getClassLoader().getResourceAsStream("sys/attachment.properties");
        properties.load(in);
        String path = properties.getProperty("bulletinUrl") + DateUtil.getCurDate()+File.separator;
		
        File bulletinFile = new File((path+fileName).replace("/", File.separator)); 
		//写入文件到指定路径
		if (!bulletinFile.getParentFile().exists()) {
			bulletinFile.getParentFile().mkdirs(); 
		}
		//生成文件
		FileUtil.writerFile(path+fileName,contentsString,"UTF-8");
		
		Attachment attachment =null;
		attachment = new Attachment();
		attachment.setFileName(fileName);
		if(null == bulletin.getCreateTime()){
			bulletin.setCreateTime(new Date());
		}
		attachment.setPath(DateUtil.getCurDate()+File.separator);
		attachment.setViewName(bulletin.getBullTitle());
		attachment.setCreateTime(bulletin.getCreateTime());
		attachmentManager.save(attachment);
		
		bulletin.setContent(attachment);
		bulletin.setBullTitle(project.getProjName()+"采购公告");
		bulletinDaoHibernate.save(bulletin);
		
		//修改项目过程状态
		if(null != bulletin.getProject() && StringUtils.hasLength(bulletin.getProject().getObjId()) && StringUtils.hasLength(bulletin.getProject().getProjProcessStatus())){
			projectManager.saveProjProcessStatus(bulletin.getProject().getObjId(), ProjProcessStatusEnum.SUPPLIERS_BID);
		}
		return bulletin;
	}
	
	/** 
	 * Description :  根据项目获得模板所需的数据 
	 * Create Date: 2011-5-16下午04:41:18 by sunl  Modified Date: 2011-5-16下午04:41:18 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getBulletinTemplateMap(Project project) throws Exception {
		Map<String, Object> templateMap = new HashMap<String, Object>();
		
		//获取需求条目信息
		List<RequireItem> requireItemList = new ArrayList<RequireItem>();
		requireItemList = requireItemDao.getRequireItemsByProjId(project.getObjId());
		
		//获取项目规则信息
		List<ProjProcessRule> projruleList = projProcessRuleManager.getProjProcessRuleByProjectId(project.getObjId());
		String buyerBudget = "0"; //是否显示采购人预算
		for (ProjProcessRule projProcessRule : projruleList) {
			//采购人预算
			if(SysConfigEnum.BargainProjectRule_buyerBudget.equals(projProcessRule.getCode())) {
				buyerBudget = projProcessRule.getResValue();
			}
		}
		templateMap.put("projruleList", projruleList);
		
		//获取采购商品信息
		String hasGoods = "0"; //标记是否有具体商品
		List<Map<String, String>> goodsList  = new ArrayList<Map<String, String>>();
		int i=1;
		for(RequireItem requireItem:requireItemList){
			Map<String, String> cell = new HashMap<String, String>();
			//序号
			cell.put("no", ""+i++);
			
			//商品分类
			String goodsClass = "--";
			if(null != requireItem.getGoodsClass() && null != requireItem.getGoodsClass().getObjId()){
				//解决第二次获取列表时取不到代理对象
				if(null != requireItem.getGoodsClass().getGoodsClassName()){
					goodsClass = requireItem.getGoodsClass().getGoodsClassName();
				}else {
					GoodsClass obj = goodsClassDaoHibernate.get(requireItem.getGoodsClass().getObjId());
					goodsClass = obj.getGoodsClassName();
				}
			} else if(null!=requireItem.getPurCategory() && StringUtils.hasLength(requireItem.getPurCategory().getObjId())) {
				//解决第二次获取列表时取不到代理对象
				if(StringUtils.hasLength(requireItem.getPurCategory().getCategoryName())){
					goodsClass = requireItem.getPurCategory().getCategoryName();
				} else {
					PurCategory obj = purCategoryDaoHibernate.get(requireItem.getPurCategory().getObjId());
					goodsClass = obj.getCategoryName();
				}
			}
			cell.put("goodsClass", goodsClass);
			
			//商品名称/描述
			Goods goods = null;
			if(null != requireItem.getGoods() && null != requireItem.getGoods().getObjId()){
				//解决第二次获取列表时取不到代理对象
				if(null != requireItem.getGoods().getProductName()){
					goods = requireItem.getGoods();
				}else {
					goods = goodsDaoHibernate.get(requireItem.getGoods().getObjId());
				}
				hasGoods = "1";
			}
			//(避免因数据库中出现null字附串而显示到公告中)
			String name = ((null != requireItem.getDescr()&& !"null".equals(requireItem.getDescr()) && !"".equals(requireItem.getDescr()))?(requireItem.getDescr()):"");
			if(null != goods) {
				name = goods.getProductName()+(!"".equals(name)?(":"+name):"");
			}
			cell.put("name", name);
			
			//商品型号
			String code = "--";
			if(null != goods) {
				code = goods.getProductCode();
			}
			cell.put("productCode", code);
			
			//数量
			cell.put("qty", ""+requireItem.getGoodsQty());
			
			//采购人预算
			if(buyerBudget.equals("0")) {
				cell.put("price", "--");
			} else {
				cell.put("price", "" + requireItem.getGoodsPrice());
			}
			goodsList.add(cell);
		}
		templateMap.put("goodsList", goodsList);
		templateMap.put("hasGoods", hasGoods);
		
		templateMap.put("budgetTotalMoney", buyerBudget.equals("0")?"--":project.getBudgetTotalMoney());//采购预算总额
		templateMap.put("project", project);
		templateMap.put("currentDate", new Date());
		
		//获取项目支付信息
		ProjectPayInfo projectPayInfo = projectPayInfoDao.getPayInfoByProjectId(project.getObjId());
		if(projectPayInfo==null) {
			projectPayInfo = new ProjectPayInfo();
		}
		templateMap.put("projectPayInfo", projectPayInfo);
		
		//获取项目报名条件
		ProjectSignInfo projectSignInfo = projectSignInfoDao.getSignInfoByProjectId(project.getObjId());
		if(projectSignInfo==null) {
			projectSignInfo = new ProjectSignInfo();
		}
		templateMap.put("projectSignInfo", projectSignInfo);
		
		//获取项目联系方式
		ProjectContactInfo projectContactInfo = projectContactInfoDao.getContactInfoByProjectId(project.getObjId());
		if(projectContactInfo==null) {
			projectContactInfo = new ProjectContactInfo();
		}
		templateMap.put("projectContactInfo", projectContactInfo);
		
		return templateMap;
	}

}
