package com.gpcsoft.pubservice.application.favorites.dao;

import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.pubservice.application.favorites.domain.Favorites;

public interface FavoritesDao extends BaseGenericDao<Favorites> {

	/** 
	 * Description :  取得类似的收藏
	 * Create Date: 2010-8-19下午02:31:47 by yucy  Modified Date: 2010-8-19下午02:31:47 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String,Object> getSimilarFavorites(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  是否收藏过
	 * Create Date: 2010-9-3上午10:48:56 by yucy  Modified Date: 2010-9-3上午10:48:56 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	boolean hasThisFavorite(Map<String, Object> param) throws Exception;

}
