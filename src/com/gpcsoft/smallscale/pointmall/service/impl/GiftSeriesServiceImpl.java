package com.gpcsoft.smallscale.pointmall.service.impl;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.smallscale.pointmall.manager.GiftSeriesManager;
import com.gpcsoft.smallscale.pointmall.service.GiftSeriesService;
import com.gpcsoft.smallscale.pointmall.dao.GiftSeriesDao;
import com.gpcsoft.smallscale.pointmall.domain.GiftSeries;

@Service 
public class GiftSeriesServiceImpl extends BaseGenericServiceImpl<GiftSeries> implements GiftSeriesService {

	@Autowired(required=true) @Qualifier("giftSeriesManagerImpl")
	private GiftSeriesManager giftSeriesManager;
	
	@Autowired(required=true) @Qualifier("giftSeriesDaoHibernate")
	private GiftSeriesDao giftSeriesDao;

	
	/**
	 * Description :  判断礼品系列名称在同一父节点下是否唯一
	 * Create Date: 2011-1-10上午10:31:22 by zhaojf  Modified Date: 2011-1-10上午10:31:22 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean isUnique(String giftSeriesName, String objId, String parentId) {
		return giftSeriesDao.isUnique(giftSeriesName, objId, parentId);
	}


	/**
	 * Description :  保存礼品系列
	 * Create Date: 2011-1-10上午11:11:52 by zhaojf  Modified Date: 2011-1-10上午11:11:52 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public GiftSeries saveGiftSeries(GiftSeries giftSeries) {
		
		// 如果是新增，则初始该商品参数分类为叶子结点
		if(null == giftSeries.getObjId() || "".equals(giftSeries.getObjId())) {
			giftSeries.setIsUsed(true);
			giftSeries.setObjId(null);
			giftSeries.setIsLeaf(true);//子结点	
			
			if(giftSeries.getParent() == null || "".equals(giftSeries.getParent())){
				giftSeries.setIsLeaf(false);
			}
			
			// 系列编号
			giftSeries.setSeriesCode(this.generateGiftSeriesCode(giftSeries));
			
			//排序号
			giftSeries.setSort(Integer.parseInt(giftSeries.getSeriesCode().substring("GS".length())));
			
			giftSeries = giftSeriesDao.save(giftSeries);
		}else {
			//修改
			giftSeriesDao.updateGigtSeriesName(giftSeries.getObjId(), giftSeries.getName());
		}		
		return giftSeries;
	}

	/**
	 * Description :  删除
	 * Create Date: 2011-1-10下午05:10:58 by zhaojf  Modified Date: 2011-1-10下午05:10:58 by zhaojf
	 * @param   
	 * @return  此系列还包含礼品时,返回'unsuccess'；此系列没有包含礼品时,返回'success'
	 * @Exception
	 */
	public String removeGiftSeries(String objId) {
		String isGiftSeriesExits = "success";
		
		//判断此系列是否还包含有礼品
		if(giftSeriesDao.isHasGiftByGiftSeries(objId)){
			isGiftSeriesExits = "unsuccess";
		}else{
			giftSeriesDao.remove(objId, GiftSeries.class);
		}
		return isGiftSeriesExits;
	}
	
	
	public List<GiftSeries> getSeriesList(Map<String, Object> paramsMap) throws Exception {
		return giftSeriesDao.getSeriesList(paramsMap);
	}
	
	/**
	 * Description :  产生礼品系列的编号(编号规则："GS"+ 四位父节点数字+四位子节点数字)
	 * Create Date: 2011-1-21下午03:27:12 by zhaojf  Modified Date: 2011-1-21下午03:27:12 by zhaojf
	 * @param  
	 * @return  
	 * @Exception
	 */
	protected String generateGiftSeriesCode(GiftSeries giftSeries){
		String seriesCode = "GS";
		
		//父节点
		if(giftSeries.getParent() == null || "".equals(giftSeries.getParent())){		
			
			String scode = giftSeriesDao.getGiftSeriesCount(null);//获取父节点最大的编号
			if(scode == null){
				seriesCode = seriesCode + "0001";
			}else{
				int ss = Integer.parseInt(scode.substring(seriesCode.length())) + 1;				
				seriesCode = seriesCode + this.supplyZeroByBit(ss);
			}
		}else{
			seriesCode = giftSeriesDao.get(giftSeries.getParent().getObjId()).getSeriesCode();//获取父节点编号
			String scode = giftSeriesDao.getGiftSeriesCount(giftSeries.getParent().getObjId());//获取当前父节点下子节点最大的编号
			if(scode == null){
				seriesCode = seriesCode + "0001";
			}else{
				int ss = Integer.parseInt(scode.substring(seriesCode.length())) + 1;
				seriesCode = seriesCode + this.supplyZeroByBit(ss);
			}
		}
		return seriesCode;
	}
	
	/**
	 * Description :  编号补0
	 * Create Date: 2011-1-21下午05:05:46 by zhaojf  Modified Date: 2011-1-21下午05:05:46 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	protected String supplyZeroByBit(Integer len){
		String ss = len.toString();
		int leng = 4 - ss.length();
		for(int i = 0;i < leng;i++){
			ss = "0" + ss;
		}
		return ss;
	}


	/**
	 * Description :  上下移动商品参数时修改原行和目标行的排序
	 * Create Date: 2011-1-24下午04:25:18 by zhaojf  Modified Date: 2011-1-24下午04:25:18 by zhaojf
	 * @param    sourceObjId 原行的排序   targetObjId 目标行的排序
	 * @return  
	 * @Exception
	 */
	public void updateSort(String sourceObjId, String targetObjId) {
		GiftSeries sourceGiftSeries = giftSeriesManager.get(sourceObjId);
		GiftSeries targetGiftSeries = giftSeriesManager.get(targetObjId);
		Integer tempSortInteger = sourceGiftSeries.getSort();
		sourceGiftSeries.setSort(targetGiftSeries.getSort());
		targetGiftSeries.setSort(tempSortInteger);
	}
	
}
