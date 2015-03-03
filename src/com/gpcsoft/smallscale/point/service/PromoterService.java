package com.gpcsoft.smallscale.point.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.smallscale.point.domain.Promoter;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;

public interface PromoterService extends BaseGenericService<Promoter> {
	
	
	/**
	 * 
	 * @param promoterid
	 * @param buyerId
	 * @param orgId
	 * @param demo
	 */
	void createByUserId(String promoterid,User buyer,Company company,String demo);
	
	/** 
	 * Description :  推广人发送邮件
	 * Create Date: 2010-10-27下午03:17:20 by dongcl  Modified Date: 2010-10-27下午03:17:20 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void sendMail(String mailadds,String objIds,String receiverName,String title,String content) throws Exception;
	
	/** 
	 * Description :  推广人推荐保存
	 * Create Date: 2010-11-23下午02:43:02 by dongcl  Modified Date: 2010-11-23下午02:43:02 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean savePromoter(Promoter promoter)throws Exception;
	
	/** 
	 * Description :  录入推广数据
	 * Create Date: 2010-11-16下午01:32:24 by dongcl  Modified Date: 2010-11-16下午01:32:24 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Promoter saveByType(Promoter promoter);
	
	/** 
	 * Description :  确认已推广用户
	 * Create Date: 2010-11-16下午01:32:39 by dongcl  Modified Date: 2010-11-16下午01:32:39 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	boolean saveDeal(Promoter promoter,String dealStatus);
	
	
	/** 
	 * Description :  根据被推广人公司名称取值
	 * Create Date: 2010-11-16下午03:37:42 by dongcl  Modified Date: 2010-11-16下午03:37:42 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Promoter  getByOrgName(String orgName);
	

	/** 
	 * Description :  保存用户(给用户增加了新的角色后保存)
	 * Create Date: 2010-11-16下午03:37:42 by dongcl  Modified Date: 2010-11-16下午03:37:42 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveUser(User userRole);
	
	
	/** 
	 * Description :  获得推广人角色
	 * Create Date: 2010-11-16下午03:37:42 by dongcl  Modified Date: 2010-11-16下午03:37:42 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Role getRoleByType(String roleType);
	
	
	/** 
	 * Description : 根据用户id获取用户信息
	 * Create Date: 2010-11-16下午03:37:42 by dongcl  Modified Date: 2010-11-16下午03:37:42 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public User getUser(String userId);
	
	
	/** 
	 * Description : 根据用户id,角色类型判断此用户是否拥有此角色
	 * Create Date: 2010-11-16下午03:37:42 by dongcl  Modified Date: 2010-11-16下午03:37:42 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isHasThisRole(String userId,String roleType);
}
