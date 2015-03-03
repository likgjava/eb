package com.gpcsoft.pubservice.application.favorites.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.pubservice.application.favorites.dao.FavoritesDao;
import com.gpcsoft.pubservice.application.favorites.domain.Favorites;
import com.gpcsoft.pubservice.application.favorites.service.FavoritesService;

@Service 
public class FavoritesServiceImpl extends BaseGenericServiceImpl<Favorites> implements FavoritesService {
	@Autowired(required=true) @Qualifier("favoritesDaoHibernate")
	private FavoritesDao favoritesDaoHibernate;

	/** 
	 * Description : 保存收藏 
	 * Create Date: 2010-8-19下午01:51:55 by yucy  Modified Date: 2010-8-19下午01:51:55 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean saveFavorites(Favorites favorites) throws Exception {
		favoritesDaoHibernate.save(favorites);
		return true;
	}

	/** 
	 * Description :  取得类似的收藏
	 * Create Date: 2010-8-19下午02:31:47 by yucy  Modified Date: 2010-8-19下午02:31:47 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getSimilarFavorites(Map<String, Object> param)throws Exception {
		
		Map<String, Object> model = new HashMap<String, Object>(); 

		//是否收藏过
		if(!favoritesDaoHibernate.hasThisFavorite(param)){
			model = favoritesDaoHibernate.getSimilarFavorites(param);
		}
		return model;
	}
}
