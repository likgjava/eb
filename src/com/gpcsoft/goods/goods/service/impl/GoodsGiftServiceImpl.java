package com.gpcsoft.goods.goods.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.goods.goods.domain.GoodsGift;
import com.gpcsoft.goods.goods.manager.GoodsGiftManager;
import com.gpcsoft.goods.goods.service.GoodsGiftService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Service 
public class GoodsGiftServiceImpl extends BaseGenericServiceImpl<GoodsGift> implements GoodsGiftService {

	@Autowired(required=true) @Qualifier("goodsGiftManagerImpl")
	private GoodsGiftManager goodsGiftManager;

	/** 
	 * Description :  保存新增或修改的礼包信息
	 * Create Date: 2011-1-7下午02:30:08 by likg  Modified Date: 2011-1-7下午02:30:08 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveGoodsGift(GoodsGift goodsGift) throws Exception {
		if(StringUtils.hasLength(goodsGift.getObjId())){ //修改
			GoodsGift oldGoodsGift = goodsGiftManager.get(goodsGift.getObjId());
			
			oldGoodsGift.setGiftName(goodsGift.getGiftName());
			oldGoodsGift.setGiftDesc(goodsGift.getGiftDesc());
			oldGoodsGift.setGiftPicture(goodsGift.getGiftPicture());
			oldGoodsGift.setUpdateTime(new Date());
			oldGoodsGift.setUpdateUser(AuthenticationHelper.getCurrentUser(true));
		}else{ //新增
			goodsGift.setCreateTime(new Date());
			goodsGift.setCreateUser(AuthenticationHelper.getCurrentUser(true));
			goodsGiftManager.save(goodsGift);
		}
	}

}
