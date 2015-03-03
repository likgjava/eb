package com.gpcsoft.agreement.bargin.bulletin.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.bulletin.domain.Bulletin;

public interface BulletinShowDao 
{

	/** 
	 * Description :  获取不同采购价区段的项目数量
	 * Create Date: 2010-10-20下午02:51:41 by likg  Modified Date: 2010-10-20下午02:51:41 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<String[]> getBulletinListShowByBudgetMoney(Map<String, Object> paramsMap) throws Exception;

	/** 
	 * Description :   获取采购项目公告
	 * Create Date: 2010-10-20下午04:52:51 by likg  Modified Date: 2010-10-20下午04:52:51 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Bulletin> getBulletinListForShow(Page<Bulletin> page, Map<String, Object> paramsMap) throws Exception;

	/** 
	 * Description :  根据不同的采购价区间获得包含采购项目数量的区域信息
	 * Create Date: 2010-10-20下午06:17:43 by likg  Modified Date: 2010-10-20下午06:17:43 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<String[]> getDistrictListForShow(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  根据条件获得不同状态的项目公告
	 * Create Date: 2010-10-23上午10:34:16 by likg  Modified Date: 2010-10-23上午10:34:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Bulletin> getBulletinForList(Page<Bulletin> page, Map<String, Object> paramsMap) throws Exception;

	/** 
	 * Description :  获取商品分类信息
	 * Create Date: 2010-11-23上午10:08:27 by likg  Modified Date: 2010-11-23上午10:08:27 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<String[]> getGoodsClassList(Map<String, Object> params) throws Exception;
	
	/** 
	 * Description :   获取采购预告
	 * Create Date: 2010-12-30下午04:52:51 by dongcl  Modified Date: 2010-12-30下午04:52:51 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Object> getBulletinBuyPreList(final Page<Object> page, final Map<String, Object> paramsMap) throws Exception;

	/** 
	 * Description :  获取机构发布的竞价项目公告数量
	 * Create Date: 2011-2-16下午04:33:13 by likg  Modified Date: 2011-2-16下午04:33:13 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Long getBulletinNum(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  批量更新状态
	 * Create Date: 2011-3-11下午10:51:30 by yucy  Modified Date: 2011-3-11下午10:51:30 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean updateStatus(String objIds, Map<String, Object> param) throws Exception;

}
