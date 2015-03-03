package com.gpcsoft.agreement.bargin.bidding.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.bidding.dao.BiddingEliminateSupplierDao;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingEliminateSupplier;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingEliminateSupplierService;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.utils.MessageUtil;
import com.gpcsoft.pubservice.utils.MobileMessageUtil;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.UserManager;

@Service 
public class BiddingEliminateSupplierServiceImpl extends BaseGenericServiceImpl<BiddingEliminateSupplier> implements BiddingEliminateSupplierService {
	
	@Autowired(required=true) @Qualifier("biddingEliminateSupplierDaoHibernate")
	private BiddingEliminateSupplierDao biddingEliminateSupplierDaoHibernate;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoServiceImpl;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectServiceImpl;
	
	@Autowired(required=true) @Qualifier("userManagerImpl")
	private UserManager userManagerImpl;
	
	/** 
	 * Description :  根据项目ID和轮次ID获取剔除供应商信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isSupplierEliminated(String projId,String turnId,String supplierId) throws Exception {
		return biddingEliminateSupplierDaoHibernate.isSupplierEliminated(projId, turnId, supplierId);
	}
	
	/** 
	 * Description :  获取报名供应商和供应商剔除信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object> getSignupSupplierAndEliminate(String projId,String turnId) throws Exception {
		return biddingEliminateSupplierDaoHibernate.getSignupSupplierAndEliminate(projId, turnId);
	}
	
	/** 
	 * Description :  获取剔除的供应商
	 * Create Date: 2011-7-4下午04:40:46 by likg  Modified Date: 2011-7-4下午04:40:46 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BiddingEliminateSupplier> getEliminateSupplier(Map<String, Object> param) throws Exception {
		return biddingEliminateSupplierDaoHibernate.getEliminateSupplier(param);
	}

	/** 
	 * Description :  剔除供应商
	 * Create Date: 2011-7-22下午03:03:13 by yucy  Modified Date: 2011-7-22下午03:03:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void deleteSupplier(String endSupplierListStr) throws Exception {
		
		JSONArray jsonArray = JSONArray.fromObject(endSupplierListStr);
		JSONObject[] jsonbject=(JSONObject[]) jsonArray.toArray(new JSONObject[]{});
		List<BiddingEliminateSupplier> elimateSupplierList = new ArrayList<BiddingEliminateSupplier>();
		if(jsonbject!=null) {
			for(JSONObject obj:jsonbject){
				BiddingEliminateSupplier endSupplier=(BiddingEliminateSupplier) JSONObject.toBean(obj, BiddingEliminateSupplier.class);
				elimateSupplierList.add(endSupplier);
				
				OrgInfo supplier = orgInfoServiceImpl.get(endSupplier.getSupplier().getObjId());
				
				Project project = projectServiceImpl.get(endSupplier.getProject().getObjId());
				
				//获取机构管理员用户
				User user = userManagerImpl.getManagerUser(supplier.getCompany().getObjId(), User.USER_TYPE_MANAGER.toString());
				
				//发送短信
				MobileMessageUtil.sendMobileMessage(
						user.getEmp().getMobile(), 
						AuthenticationHelper.getCurrentUser(true).getObjId(), 
						AuthenticationHelper.getCurrentUser(true).getUsername(),
						user.getObjId(), 
						user.getUsername(), 
						messageSource.getMessage(CustomerMessage.PROJECT_ELIMINATE_MESSAGE).replace("#projectName", project.getProjName()), 
						null, 
						null);
				
				//发站内信
				MessageUtil.sendMessagePrivete(
						new String[]{user.getEmp().getEmail()},
						"对不起！您已被剔除！",
						messageSource.getMessage(CustomerMessage.PROJECT_ELIMINATE_MESSAGE).replace("#projectName", project.getProjName()),
						null,
						null,
						true);
			}
		}
		biddingEliminateSupplierDaoHibernate.save(elimateSupplierList);
	}
}
