package com.gpcsoft.agreement.bargin.requirement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.requirement.dao.RequirementDao;
import com.gpcsoft.agreement.bargin.requirement.domain.Requirement;
import com.gpcsoft.agreement.bargin.requirement.manager.RequirementManager;
import com.gpcsoft.agreement.bargin.requirement.service.RequirementService;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.pubservice.utils.MailUtil;

@Service 
public class RequirementServiceImpl extends BaseGenericServiceImpl<Requirement> implements RequirementService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("requirementManagerImpl")
	private RequirementManager requirementManager;
	
	@Autowired(required=true) @Qualifier("requirementDaoHibernate")
	private RequirementDao requirementDao;

	public Requirement saveRequirement(Requirement requirement ,String saveType) throws Exception {
		return save(requirement);
	}

	/** 
	 * Description :  更新状态(可能批量)
	 * Create Date: 2011-3-25上午10:59:51 by yucy  Modified Date: 2011-3-25上午10:59:51 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateStatus(String objIds, Map<String, Object> param) throws Exception {
		return requirementDao.updateStatus(objIds,param);
	}

	/** 
	 * Description :  取得需求信息
	 * Create Date: 2011-3-30上午10:04:03 by yucy  Modified Date: 2011-3-30上午10:04:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Requirement> getRequirementListForShow(Page<Requirement> page,Map<String, Object> paramsMap) throws Exception {
		return requirementDao.getRequirementListForShow(page,paramsMap);
	}

	/** 
	 * Description :  根据品目获得品目的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by yucy  Modified Date: 2010-7-27下午06:12:58 by yucy
	 * @param   categoryId 品目id，categoryCode 品目编号, isLeaf 是否只取叶子节点
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getCategoryListShowByCategory(String categoryId,String categoryCode, Boolean isLeaf, String keyWord) throws Exception {
		return requirementDao.getCategoryListShowByCategory(categoryId, categoryCode, isLeaf,keyWord);
	}

	/** 
	 * Description :  根据品目获得区域信息展示信息集合
	 * Create Date: 2010-8-9下午03:25:57 by yucy  Modified Date: 2010-8-9下午03:25:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getDistrictListForShow(String districtId,String categoryCode, Short level, String keyWord) throws Exception {
		return requirementDao.getDistrictListForShow(districtId, categoryCode, level,keyWord);
	}

	/** 
	 * Description :  根据id 获得需求的集合
	 * Create Date: 2011-4-6下午02:27:52 by yucy  Modified Date: 2011-4-6下午02:27:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Requirement> getRequirementList(String objIds) throws Exception {
		return requirementDao.getRequirementList(objIds);
	}

	/** 
	 * Description :  推荐给朋友
	 * Create Date: 2011-4-8下午01:56:43 by yucy  Modified Date: 2011-4-8下午01:56:43 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean shareRequirement(Map<String, Object> param) throws Exception {
		String requirementId = (String) param.get("requirementId"); //项目公告id
		String toEmail = (String) param.get("toEmail"); //收件人的Email
		Requirement requirement = get(requirementId);
		
		//获取邮件推荐采购需求的邮件标题（从资源文件中获取）
		String subject = messageSource.getMessage(CustomerMessage.MAIL_RECOMMEND_REQUIREMENT_SUBJECT).replace("#requirementTitle", requirement.getTitle());
		
		//获取邮件推荐采购需求的邮件内容（从模板中获取）
		Map<String, Object> templateMap = new HashMap<String, Object>();
		templateMap.put("requirementTitle", requirement.getTitle());
		templateMap.put("requirementDiscription", requirement.getDiscription());
		
		//发送邮件
        MailUtil.sendEmailNotAlways(new String[]{toEmail}, subject, null, CustomerMessage.MAIL_RECOMMEND_REQUIREMENT_CONTENT, templateMap);
        return true;
	}
}
