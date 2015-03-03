package com.gpcsoft.goods.goodsprice.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.CollectionUtils;
import com.gpcsoft.goods.goodsprice.dao.GoodsPriceProcessDao;
import com.gpcsoft.goods.goodsprice.domain.GoodsPriceProcess;
import com.gpcsoft.goods.goodsprice.manager.GoodsPriceProcessManager;
import com.gpcsoft.goods.goodsprice.service.GoodsPriceProcessService;

@Service 
public class GoodsPriceProcessServiceImpl extends BaseGenericServiceImpl<GoodsPriceProcess> implements GoodsPriceProcessService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("goodsPriceProcessManagerImpl")
	private GoodsPriceProcessManager goodsPriceProcessManager;

	@Autowired(required=true) @Qualifier("goodsPriceProcessDaoHibernate")
	private GoodsPriceProcessDao goodsPriceProcessDaoHibernate;
	
	/** 
	 * Description :  获取行情图形
	 * Create Date: 2010-10-12下午02:23:46 by yucy  Modified Date: 2010-10-12下午02:23:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<ListOrderedMap> getPriceChartDate(Map<String, Object> param) throws Exception {
		Calendar currentDate = Calendar.getInstance(); 
		//currentDate.add(Calendar.MONTH, 1);
		param.put("endTime", ""+currentDate.get(Calendar.YEAR)+""+(currentDate.get(Calendar.MONTH )+1) ); 
		currentDate.add(Calendar.MONTH, -5);
		param.put("startTime",""+currentDate.get(Calendar.YEAR)+""+(currentDate.get(Calendar.MONTH )+1) );
		
		List<Object[]> data = goodsPriceProcessDaoHibernate.getPriceChartDate(param);
		
		//取得热门区间 
		//List<HotTags> tagsList = (List<HotTags>)param.get("tagsList");
		String[] districtIds = (String[])param.get("districtIds");
		
		
		List<ListOrderedMap> temp = new ArrayList<ListOrderedMap>();
		for (Object[] objects : data) {
			ListOrderedMap col1 = new ListOrderedMap();
			col1.put("0", objects[0]);// month
	        col1.put("1", objects[1]);//districtId
	        col1.put("2", objects[2]);//price
	        temp.add(col1);
		}
		
		//横向表头
		List<ListOrderedMap> colData = new ArrayList<ListOrderedMap>();
		for(String districtId: districtIds){
			ListOrderedMap c = new ListOrderedMap();
			c.put("1",districtId);
			colData.add(c);
		}
		
		List<ListOrderedMap> resultDate  =  CollectionUtils.crossList(temp,colData,1);
		
		//当前日期往前推六个月的数据结果
		List<ListOrderedMap> result = new ArrayList<ListOrderedMap>();

		for(int i=0 ;i<6;i++){
			ListOrderedMap 	map = new ListOrderedMap();
			
			String currentMonth = ""+currentDate.get(Calendar.YEAR)+"-"+(currentDate.get(Calendar.MONTH)+1);
			
			map.put("month",""+currentDate.get(Calendar.YEAR)+"-"+((currentDate.get(Calendar.MONTH)+1)<10?"0"+(currentDate.get(Calendar.MONTH )+1):(currentDate.get(Calendar.MONTH )+1)));
			
			for(String districtId:districtIds){
				for(ListOrderedMap cellmap :resultDate){
					if(currentMonth.equals((String)cellmap.get("0"))) {
						if(cellmap.get(districtId+"2")!=null&&!"".equals(cellmap.get(districtId+"2"))){
							map.put(districtId, cellmap.get(districtId+"2"));
						}else{
							map.put(districtId, "0");
						}
					}
				}
				if(map.get(districtId)==null){
					map.put(districtId, "0");
				}
			}
			
			result.add(map);
			currentDate.add(Calendar.MONTH, 1);
		}
		
		return result;
	}

}
