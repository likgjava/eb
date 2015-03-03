package com.gpcsoft.agreement.bargin.bulletin.service;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.project.domain.Project;

public interface BulletinShowService {

	/** 
	 * Description :  获取不同采购价区段的项目数量
	 * Create Date: 2010-10-20下午02:47:47 by likg  Modified Date: 2010-10-20下午02:47:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<String[]> getBulletinListShowByBudgetMoney(Map<String, Object> paramsMap) throws Exception;

	/** 
	 * Description :  获取采购项目公告
	 * Create Date: 2010-10-20下午04:48:34 by likg  Modified Date: 2010-10-20下午04:48:34 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Bulletin> getBulletinListForShow(Page<Bulletin> page, Map<String, Object> paramsMap) throws Exception;

	/** 
	 * Description :  根据不同的采购价区间获得包含采购项目数量的区域信息
	 * Create Date: 2010-10-20下午06:14:29 by likg  Modified Date: 2010-10-20下午06:14:29 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<String[]> getDistrictListForShow(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  根据条件获得不同状态的项目公告
	 * Create Date: 2010-10-23上午10:32:16 by likg  Modified Date: 2010-10-23上午10:32:16 by likg
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
	 * Description :  获取采购预告
	 * Create Date: 2010-12-30下午04:48:34 by dongcl  Modified Date: 2010-12-30下午04:48:34 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Object> getBulletinBuyPreList(Page<Object> page, Map<String, Object> paramsMap) throws Exception;

	
	/** 
	 * Description :  获取机构发布的竞价项目公告数量
	 * Create Date: 2011-2-16下午04:31:02 by likg  Modified Date: 2011-2-16下午04:31:02 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Long getBulletinNum(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  将项目采购公告通过邮件分享给好友
	 * Create Date: 2011-2-28上午11:30:05 by likg  Modified Date: 2011-2-28上午11:30:05 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void shareBulletin(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  获取项目的信息（包括项目进度，报名情况）
	 * Create Date: 2011-5-13下午02:43:14 by likg  Modified Date: 2011-5-13下午02:43:14 by likg
	 * @param   project:项目		bulletinType:公告类型
	 * @return  
	 * @Exception   
	 */
	void getProjectInfo(Project project, String bulletinType, Map<String,Object> model) throws Exception;
	
	/** 
	 * Description :  获取公告列表（根据项目Id和公告类型）
	 * Create Date: 2011-8-16下午07:23:41 by likg  Modified Date: 2011-8-16下午07:23:41 by likg
	 * @param   projectId:项目Id bulletinType:公告类型
	 * @return  
	 * @Exception   
	 */
	List<Bulletin> getBulletinList(String projectId, String bulletinType) throws Exception;
	
}
