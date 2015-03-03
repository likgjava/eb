package com.gpcsoft.epp.signuprecord.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.signuprecord.dao.SignUpCondFactorDao;
import com.gpcsoft.epp.signuprecord.dao.SignUpMResponeDao;
import com.gpcsoft.epp.signuprecord.dao.SignUpResponeDao;
import com.gpcsoft.epp.signuprecord.domain.SignUpCondFactor;
import com.gpcsoft.epp.signuprecord.domain.SignUpRespone;
import com.gpcsoft.epp.signuprecord.manager.SignUpResponeManager;
import com.gpcsoft.epp.signuprecord.service.SignUpResponeService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class SignUpResponeServiceImpl extends BaseGenericServiceImpl<SignUpRespone> implements SignUpResponeService {

	@Autowired(required=true) @Qualifier("signUpResponeManagerImpl")
	private SignUpResponeManager signUpResponeManager;

	@Autowired(required=true)  @Qualifier("signUpCondFactorDaoHibernate")
	private SignUpCondFactorDao signUpCondFactorDaoHibernate;
		
	@Autowired(required=true)  @Qualifier("signUpMResponeDaoHibernate")
	private SignUpMResponeDao signUpMResponeDaoHibernate;
	/**
	 * 
	 * Description :根据项目得到报名响应列表  
	 * Create Date: 2010-8-13上午09:16:46 by liuke  Modified Date: 2010-8-13上午09:16:46 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<SignUpRespone> getSignUpResponeListByProjectIdAndSupplierId(String projectId,String supplierId) {
		logger.debug("\n SignUpResponeServiceImpl||getSignUpResponeListByProjectIdAndSupplierId\n");
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo supplier = (OrgInfo)user.getOrgInfo();
		if(null!=supplierId&&!"".equals(supplierId)){
			supplier = (OrgInfo)signUpResponeManager.get(supplierId, OrgInfo.class);
		}
		//获得报名指标列表
		List<SignUpCondFactor> signUpCondFactorList = signUpCondFactorDaoHibernate.getSignUpCondFactorListByProjectId(projectId);
		List<SignUpRespone> signUpResponeTableList =  signUpMResponeDaoHibernate.getSignUpResponeFromTableByProjectId(projectId, supplier);
		List<SignUpRespone> signUpResponeList = new ArrayList<SignUpRespone>();
		if(signUpResponeTableList.size()>0){//从数据库表格读取
			return signUpResponeTableList;
		}else{//第一次进入
			for(Iterator<SignUpCondFactor>iterator =signUpCondFactorList.iterator();iterator.hasNext();){
				SignUpCondFactor condFactor = iterator.next();
				SignUpRespone signUpRespone = new SignUpRespone();
				signUpRespone.setSignUpCondFactor(condFactor);
				signUpResponeList.add(signUpRespone);
			}	
		}
		return signUpResponeList;
	}

}
