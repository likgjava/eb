package com.gpcsoft.goods.goodsprice.manager.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.goods.goodsprice.dao.GoodsPriceProcessDao;
import com.gpcsoft.goods.goodsprice.domain.GoodsPrice;
import com.gpcsoft.goods.goodsprice.domain.GoodsPriceProcess;
import com.gpcsoft.goods.goodsprice.manager.GoodsPriceProcessManager;

@Repository
public class GoodsPriceProcessManagerImpl extends BaseManagerImpl<GoodsPriceProcess> implements GoodsPriceProcessManager {

	@Autowired(required=true)  @Qualifier("goodsPriceProcessDaoHibernate")
	private GoodsPriceProcessDao goodsPriceProcessDaoHibernate;
	
	/** 
	 * Description :  根据行情生成行情统计单元
	 * Create Date: 2010-10-12上午09:43:49 by yucy  Modified Date: 2010-10-12上午09:43:49 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveProcessByPrice(GoodsPrice goodsPrice) throws Exception {
		List<GoodsPriceProcess> list = new ArrayList<GoodsPriceProcess>();
		
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMM");
		
		SimpleDateFormat ftMonth = new SimpleDateFormat("MM");

		Calendar StartTime = Calendar.getInstance();
		
		StartTime.setTime(goodsPrice.getEfctDate());
		
		while(Integer.parseInt(ft.format(StartTime.getTime())) <= Integer.parseInt(ft.format(goodsPrice.getEndDate()))  ){
			
			GoodsPriceProcess goodsPriceProcess= new GoodsPriceProcess();
			
			goodsPriceProcess.setAmount(goodsPrice.getPrtcPrice());
			goodsPriceProcess.setDistrict(goodsPrice.getDistrict());
			goodsPriceProcess.setGoodsPriceSupplier(goodsPrice.getGoodsPriceSupplier());
			goodsPriceProcess.setMonth(Integer.parseInt(ftMonth.format(StartTime.getTime())));
			goodsPriceProcess.setYear(StartTime.get(Calendar.YEAR));
			goodsPriceProcess.setCreateTime(new Date());
			
			list.add(goodsPriceProcess);
			
			StartTime.add(Calendar.MONTH, 1);//递增月份
		}
		
		goodsPriceProcessDaoHibernate.save(list);//保存
	}

}
