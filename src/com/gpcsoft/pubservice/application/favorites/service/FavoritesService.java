package com.gpcsoft.pubservice.application.favorites.service;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.pubservice.application.favorites.domain.Favorites;

public interface FavoritesService extends BaseGenericService<Favorites> {

	/** 
	 * Description :  保存收藏对象
	 * Create Date: 2010-8-19下午02:20:03 by yucy  Modified Date: 2010-8-19下午02:20:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean saveFavorites(Favorites favorites) throws Exception;

	/** 
	 * Description :  取得类似的收藏
	 * Create Date: 2010-8-19下午02:31:47 by yucy  Modified Date: 2010-8-19下午02:31:47 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getSimilarFavorites(Map<String, Object> param) throws Exception;

}
