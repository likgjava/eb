package com.gpcsoft.smallscale.pointmall.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.smallscale.pointmall.domain.GiftSeries;

public interface GiftSeriesDao extends BaseGenericDao<GiftSeries> {

	/** 
	 * Description :  取得礼品系列
	 * Create Date: 2011-1-10上午10:05:48 by yucy  Modified Date: 2011-1-10上午10:05:48 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<GiftSeries> getSeriesList(Map<String, Object> paramsMap) throws Exception;

	
	/**
	 * Description :  判断礼品系列名称在同一父节点下是否唯一
	 * Create Date: 2011-1-10上午10:40:44 by zhaojf  Modified Date: 2011-1-10上午10:40:44 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean isUnique(String giftSeriesName,String objId,String parentId);
	

	/**
	 * Description :  修改礼品系列的名称
	 * Create Date: 2011-1-10下午05:28:18 by zhaojf  Modified Date: 2011-1-10下午05:28:18 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void updateGigtSeriesName(String objId, String giftSeriesName);
	
	
	/**
	 * Description :  判断此系列是否还包含有礼品
	 * Create Date: 2011-1-11下午01:41:15 by zhaojf  Modified Date: 2011-1-11下午01:41:15 by zhaojf
	 * @param   
	 * @return  是：返回true 否:返回false
	 * @Exception
	 */
	public boolean isHasGiftByGiftSeries(String objId);
	
	
	/**
	 * Description :  获取当前父节点下所拥有的最大子节点编码
	 * Create Date: 2011-1-11下午02:48:56 by zhaojf  Modified Date: 2011-1-11下午02:48:56 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public String getGiftSeriesCount(String parentId);
}
