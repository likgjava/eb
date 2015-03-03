package com.gpcsoft.pubservice.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.dao.OrgInfoDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.member.domain.Member;
import com.gpcsoft.pubservice.application.member.manager.MemberManager;
import com.gpcsoft.pubservice.common.dao.IndexViewDao;
import com.gpcsoft.pubservice.common.service.IndexViewService;
import com.gpcsoft.smallscale.expert.dao.ExpertInfoDao;
import com.gpcsoft.smallscale.expert.domain.ExpertInfo;
import com.gpcsoft.srplatform.auth.dao.UserDao;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class IndexViewServiceImpl extends BaseGenericServiceImpl<GpcBaseObject> implements IndexViewService {
	
	@Autowired(required=true)  @Qualifier("indexViewDaoJdbc")
	private IndexViewDao indexViewDaoJdbc;
	
	@Autowired(required=true)  @Qualifier("userDaoHibernate")
	private UserDao userDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("orgInfoDaoHibernate")
	private OrgInfoDao orgInfoDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("memberManagerImpl")
	private MemberManager memberManager;
	
	@Autowired(required=true)  @Qualifier("expertInfoDaoHibernate")
	private ExpertInfoDao expertInfoDaoHibernate;
	
	/** 
	 * Description :  获取当前登录用户，并获得机构、角色、级别等信息
	 * Create Date: 2011-4-11上午10:15:05 by liangxj  Modified Date: 2011-4-11上午10:15:05 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public User getAndSetCurrentUserInfo(Map<String, Object> model,HttpSession session) throws Exception{
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		//获得用户角色
		User user=AuthenticationHelper.getCurrentUser();
		if(user != null) {
			//设置用户的orgInfo
			if(user.getOrgInfo() == null) {
				if(user.getEmp() != null && user.getEmp().getCompany() != null) {
					OrgInfo orgInfo = orgInfoDaoHibernate.getLastedOrgInfo(user.getEmp().getCompany().getObjId(),true);
					if(orgInfo!=null&&orgInfo.getCompany()!=null&&orgInfo.getCompany().getTown() != null) {
						//orgInfo.getCompany().getTown().setParent(null); //防止session失效报错
					}
					user.setOrgInfo(orgInfo);
					paramsMap.put("orgInfoId", orgInfo.getObjId());
					
					//获取用户会员级别
					if(OrganizationEnum.USE_VALID.equals(orgInfo.getUseStatus())) {
						Member member= memberManager.getMemberByOrgInfoId(paramsMap);
						session.setAttribute("memberClassId",member.getMemberClass().getObjId());
					} else if(OrganizationEnum.USE_TEMP.equals(orgInfo.getUseStatus())) {
						session.setAttribute("memberClassId","");
					}
				}
			}
			
			//读取角色信息
			String roleStr = "";
			List<Role> roles = userDaoHibernate.getSelfRoleByUserId(user.getObjId());
			for (Role role : roles) {
				roleStr += "".equals(roleStr)?role.getType():","+role.getType();
			}
			model.put("roleStr",roleStr);
			session.setAttribute("roleStr",roleStr);
			
			// 设置用户的扩展专家信息
			if(user.getExtInfo() == null || user.getExtInfo().getObjId() == null){
				ExpertInfo expert = expertInfoDaoHibernate.getUserExpertInfo(user.getObjId());
				expert.setDistrict(null);expert.setUser(null); //防止session失效报错
				user.setExtInfo(expert);
			}
		}
		return user;
	}
	
	/** 
	 * Description : 获得与项目有关的统计信息 
	 * Create Date: 2010-8-18下午04:47:26 by guoyr  Modified Date: 2010-8-18下午04:47:26 by guoyr
	 * @param 项目的总数,订单数,合同数,议价数,采购总额  
	 * @return  
	 * @Exception   
	 */
	public Map<String,String> getProjectStatistics() throws Exception{
		return indexViewDaoJdbc.getProjectStatistics();
	}
	
	/** 
	 * Description : 获得供应商的统计信息
	 * Create Date: 2010-8-18下午04:47:26 by guoyr  Modified Date: 2010-8-18下午04:47:26 by guoyr
	 * @param 供应商的总数,已审核通过的总数,待审核的总数,最新注册的供应商总数(7个自然日内)  
	 * @return  
	 * @Exception   
	 */
	public Map<String,String> getSupplierStatistics() throws Exception {
		return indexViewDaoJdbc.getSupplierStatistics();
	}
	
	/** 
	 * Description : 获得代理机构的统计信息
	 * Create Date: 2010-8-18下午04:47:26 by guoyr  Modified Date: 2010-8-18下午04:47:26 by guoyr
	 * @param 代理机构的总数,已审核通过的总数,待审核的总数,最新注册的代理机构总数(7个自然日内)  
	 * @return  
	 * @Exception   
	 */
	public Map<String,String> getAgencyStatistics() throws Exception {
		return indexViewDaoJdbc.getAgencyStatistics();
	}

	/** 
	 * Description :  获取系统统计信息
	 * Create Date: 2010-10-28下午05:04:34 by likg  Modified Date: 2010-10-28下午05:04:34 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, String> getStatisticsInfo() throws Exception
	{
		return indexViewDaoJdbc.getStatisticsInfo();
	}
}
