package com.gpcsoft.pubservice.application.template.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.template.domain.DotTemplateFavorite;
import com.gpcsoft.pubservice.application.template.manager.DotTemplateFavoriteManager;
import com.gpcsoft.pubservice.application.template.service.DotTemplateFavoriteService;

@Service 
public class DotTemplateFavoriteServiceImpl extends BaseGenericServiceImpl<DotTemplateFavorite> implements DotTemplateFavoriteService {

	@Autowired(required=true) @Qualifier("dotTemplateFavoriteManagerImpl")
	private DotTemplateFavoriteManager dotTemplateFavoriteManager;

	/** 
	 * Description :  根据参数获取范本收藏列表集合
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id  orgInfoId:收藏机构的Id  userId:收藏人的Id
	 * @return  
	 * @Exception   
	 */
	public List<DotTemplateFavorite> getTemplateFavoriteList(Map<String, Object> param) throws Exception {
		return dotTemplateFavoriteManager.getTemplateFavoriteList(param);
	}

	/** 
	 * Description :  保存范本收藏信息
	 * Create Date: 2011-8-1上午11:44:37 by likg  Modified Date: 2011-8-1上午11:44:37 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveDotTemplateFavorite(DotTemplateFavorite dotTemplateFavorite) throws Exception {
		if(AuthenticationHelper.getCurrentUser()!=null && AuthenticationHelper.getCurrentUser().getOrgInfo()!=null) {
			dotTemplateFavorite.setOrg((OrgInfo)AuthenticationHelper.getCurrentUser().getOrgInfo());
		}
		
		dotTemplateFavoriteManager.save(dotTemplateFavorite);
	}

}
