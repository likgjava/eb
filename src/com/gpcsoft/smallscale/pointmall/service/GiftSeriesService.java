package com.gpcsoft.smallscale.pointmall.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.smallscale.pointmall.domain.GiftSeries;

public interface GiftSeriesService extends BaseGenericService<GiftSeries> {
	
	/**
	 * Description :  判断礼品系列名称在同一父节点下是否唯一
	 * Create Date: 2011-1-10上午10:31:22 by zhaojf  Modified Date: 2011-1-10上午10:31:22 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean isUnique(String giftSeriesName, String objId, String parentId );
	
	/**
	 * Description :  保存礼品系列
	 * Create Date: 2011-1-10上午11:11:52 by zhaojf  Modified Date: 2011-1-10上午11:11:52 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public GiftSeries saveGiftSeries(GiftSeries giftSeries);
	
	/**
	 * Description :  删除
	 * Create Date: 2011-1-10下午05:10:58 by zhaojf  Modified Date: 2011-1-10下午05:10:58 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public String removeGiftSeries(String objId);

	/** 
	 * Description :  取得系列集合
	 * Create Date: 2011-1-10上午10:52:50 by yucy  Modified Date: 2011-1-10上午10:52:50 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<GiftSeries> getSeriesList(Map<String, Object> paramsMap) throws Exception;
	
	
	/**
	 * Description :  上下移动商品参数时修改原行和目标行的排序
	 * Create Date: 2011-1-24下午04:25:18 by zhaojf  Modified Date: 2011-1-24下午04:25:18 by zhaojf
	 * @param    sourceObjId 原行的排序   targetObjId 目标行的排序
	 * @return  
	 * @Exception
	 */
	public void  updateSort (String sourceObjId, String targetObjId);

}
