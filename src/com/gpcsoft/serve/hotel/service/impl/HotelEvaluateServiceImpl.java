package com.gpcsoft.serve.hotel.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.serve.hotel.dao.HotelEvaluateDao;
import com.gpcsoft.serve.hotel.domain.Hotel;
import com.gpcsoft.serve.hotel.domain.HotelEvaluate;
import com.gpcsoft.serve.hotel.manager.HotelEvaluateManager;
import com.gpcsoft.serve.hotel.service.HotelEvaluateService;

@Service 
public class HotelEvaluateServiceImpl extends BaseGenericServiceImpl<HotelEvaluate> implements HotelEvaluateService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("hotelEvaluateManagerImpl")
	private HotelEvaluateManager hotelEvaluateManager;
	
	@Autowired(required=true) @Qualifier("hotelEvaluateDaoHibernate")
	private HotelEvaluateDao hotelEvaluateDao;

	
	/** 
	 * Description :  保存酒店评价
	 * Create Date: 2010-8-17下午02:17:46 by yucy  Modified Date: 2010-8-17下午02:17:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> saveHotelEvaluate(Map<String, Object> param)throws Exception {
		
		String hotelId = (String)param.get("hotelId");
		String rateName = (String)param.get("rateName");
		String level = (String)param.get("level");
		String remark = (String)param.get("remark");
		String title = (String)param.get("title");
		boolean isManager=false;
		Map<String, Object> model =  new HashMap<String, Object>();
		
		//是否已评价
		Long result = hotelEvaluateDao.hasHotelEvaluate(param);
		if("manager".equals(AuthenticationHelper.getCurrentUser(true).getUsName())){
			isManager=true;
		}
		if(result>0 && !isManager){
			model.put(Constants.FAILURE,true);
			model.put("flag",false);
			model.put(Constants.JSON_RESULT,"您已评价，不能重复评价评价！");
		}else{
			HotelEvaluate hotelEvaluate = new HotelEvaluate();
			Hotel hotel = new Hotel();
			hotel.setObjId(hotelId);
			hotelEvaluate.setHotel(hotel);
			hotelEvaluate.setRateName(rateName);
			hotelEvaluate.setCreateUser(AuthenticationHelper.getCurrentUser(true));
			hotelEvaluate.setRateName(AuthenticationHelper.getCurrentUser(true).getUsName());//设置评价人name
			hotelEvaluate.setCreateTime(new Date());
			hotelEvaluate.setTitle(title);
			hotelEvaluate.setLevel(Integer.parseInt(level));
			if(remark!=null&&!"".equals(remark)){
				hotelEvaluate.setRemark(remark);
			}
			hotelEvaluateDao.save(hotelEvaluate);
			model.put(Constants.SUCCESS,true);
			model.put("flag",true);
			model.put(Constants.JSON_RESULT,"评价成功！");
		}
		return model;
	}
}
