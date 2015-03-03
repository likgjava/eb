package com.gpcsoft.goods.goods.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.dao.GoodsChangeDao;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.GoodsChange;
import com.gpcsoft.goods.goods.service.GoodsChangeService;

@Service 
public class GoodsChangeServiceImpl extends BaseGenericServiceImpl<GoodsChange> implements GoodsChangeService {

	@Autowired(required=true) @Qualifier("goodsChangeDaoHibernate")
	private GoodsChangeDao goodsChangeDao;

	/** 
	 * Description :  获取变更待审核的商品
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsChange> getGoodsAuditList(String goodsId) throws Exception {
		List<GoodsChange> changeList = goodsChangeDao.getGoodsAuditList(goodsId);
		
		List<GoodsChange> resList = new ArrayList<GoodsChange>();
		
		if(StringUtils.hasLength(goodsId)) {
			resList = changeList;
		} else {
			String groupOrgId = "";
			for (GoodsChange goodsChange : changeList) {
				if(!groupOrgId.equals(goodsChange.getGoods().getObjId())) {
					groupOrgId = goodsChange.getGoods().getObjId();
					resList.add(goodsChange);
				}
			}
		}
		
		return resList;
	}
	
	/** 
	 * Description :  获取变更待审核的商品(分页)
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Goods> getGoodsAuditListPage(Page page,Map<String,Object> params) throws Exception {
		return goodsChangeDao.getGoodsAuditListPage(page,params);
	}
}
