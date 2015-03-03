package com.gpcsoft.smallscale.pointmall.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.pointmall.manager.GiftCommentManager;
import com.gpcsoft.smallscale.pointmall.service.GiftCommentService;
import com.gpcsoft.smallscale.pointmall.dao.GiftCommentDao;
import com.gpcsoft.smallscale.pointmall.domain.Gift;
import com.gpcsoft.smallscale.pointmall.domain.GiftComment;

@Service 
public class GiftCommentServiceImpl extends BaseGenericServiceImpl<GiftComment> implements GiftCommentService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("giftCommentManagerImpl")
	private GiftCommentManager giftCommentManager;
	
	@Autowired(required=true)  @Qualifier("giftCommentDaoHibernate")
	private GiftCommentDao giftCommentDaoHibernate;

	
	/**
	 * Description :  保存礼品评价
	 * Create Date: 2011-1-14下午04:39:20 by zhaojf  Modified Date: 2011-1-14下午04:39:20 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Map<String, Object> saveGiftComment(Map<String, Object> param) throws Exception {
		
		String giftId = (String)param.get("giftId");
		String rateName = (String)param.get("rateName");
		String level = (String)param.get("level");
		String comment = (String)param.get("comment");
		String title = (String)param.get("title");
		boolean isManager=false;		
		Map<String, Object> model =  new HashMap<String, Object>();
		
		//是否已评价
		Long result = giftCommentDaoHibernate.hasGiftComment(param);
		
		//是否manager
		if("manager".equals(AuthenticationHelper.getCurrentUser(true).getUsName())){
			isManager=true;
		}
		if(result>0 && !isManager){
			model.put(Constants.FAILURE,true);
			model.put("flag",false);
			model.put(Constants.JSON_RESULT,"您已评价，不能重复评价评价！");
		}else{
			GiftComment giftComment = new GiftComment();
			Gift gift = new Gift();
			gift.setObjId(giftId);
			giftComment.setGift(gift);			
			giftComment.setCreateUser(AuthenticationHelper.getCurrentUser(true));
			giftComment.setRateName(AuthenticationHelper.getCurrentUser(true).getUsName());//设置评价人name
			giftComment.setCreateTime(new Date());
			giftComment.setTitle(title);
			giftComment.setLevel(Integer.parseInt(level));
			if(isManager && rateName != "" && rateName != null){
				giftComment.setRateName(rateName);
			}
			if(comment!=null&&!"".equals(comment)){
				giftComment.setComment(comment);
			}
			giftCommentDaoHibernate.save(giftComment);
			model.put(Constants.SUCCESS,true);
			model.put("flag",true);
			model.put(Constants.JSON_RESULT,"评价成功！");
		}
		return model;
	}


}
