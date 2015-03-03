package com.gpcsoft.pubservice.application.template.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.pubservice.application.template.domain.DotTemplateFavorite;

public interface DotTemplateFavoriteDao extends BaseGenericDao<DotTemplateFavorite> {

	/** 
	 * Description :  根据参数获取范本收藏列表集合
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id  orgInfoId:收藏机构的Id  userId:收藏人的Id
	 * @return  
	 * @Exception   
	 */
	List<DotTemplateFavorite> getTemplateFavoriteList(Map<String, Object> param) throws Exception;
}
