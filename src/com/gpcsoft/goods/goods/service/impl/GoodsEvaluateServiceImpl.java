package com.gpcsoft.goods.goods.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.goods.goods.dao.GoodsEvaluateDao;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.GoodsEvaluate;
import com.gpcsoft.goods.goods.service.GoodsEvaluateService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Service 
public class GoodsEvaluateServiceImpl extends BaseGenericServiceImpl<GoodsEvaluate> implements GoodsEvaluateService {

	@Autowired(required=true) @Qualifier("goodsEvaluateDaoHibernate")
	private GoodsEvaluateDao goodsEvaluateDaoHibernate;

	/** 
	 * Description :  保存商品评价
	 * Create Date: 2010-8-17下午02:17:46 by yucy  Modified Date: 2010-8-17下午02:17:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> saveGoodsEvaluate(Map<String, Object> param)throws Exception {
		
		String goodsId = (String)param.get("goodsId");
		String level = (String)param.get("level");
		String remark = (String)param.get("remark");
		String title = (String)param.get("title");
		boolean isManager=false;
		Map<String, Object> model =  new HashMap<String, Object>();
		
		//是否已评价
		Long result = goodsEvaluateDaoHibernate.hasGoodsEvaluate(param);
		if("manager".equals(AuthenticationHelper.getCurrentUser(true).getUsName())){
			isManager=true;
		}
		if(result>0 && !isManager){
			model.put(Constants.FAILURE,true);
			model.put("flag",false);
			model.put(Constants.JSON_RESULT,"您已评价，不能重复评价评价！");
		}else{
			GoodsEvaluate goodsEvaluate = new GoodsEvaluate();
			Goods goods = new Goods();
			goods.setObjId(goodsId);
			goodsEvaluate.setGoods(goods);
			goodsEvaluate.setCreateUser(AuthenticationHelper.getCurrentUser(true));
			goodsEvaluate.setRateName(AuthenticationHelper.getCurrentUser(true).getEmp().getName());//评价人中文名
			goodsEvaluate.setCreateTime(new Date());
			goodsEvaluate.setTitle(title);
			goodsEvaluate.setLevel(Integer.parseInt(level));
			if(remark!=null&&!"".equals(remark)){
				goodsEvaluate.setRemark(remark);
			}
			goodsEvaluateDaoHibernate.save(goodsEvaluate);
			model.put(Constants.SUCCESS,true);
			model.put("flag",true);
			model.put(Constants.JSON_RESULT,"评价成功！");
		}
		return model;
	}
}
