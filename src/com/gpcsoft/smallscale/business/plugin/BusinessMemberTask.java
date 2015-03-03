package com.gpcsoft.smallscale.business.plugin;

import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.smallscale.business.manager.BusinessMemberManager;


/**
 * 
  *  Comments: <strong>商圈会员角色定时任务</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2011-2-15 上午15:55:47 by sunl    					                            
  *  <br/>Modified Date:  2011-2-15 上午15:55:47 by sunl                                 
 */
public class BusinessMemberTask extends TimerTask {
	
	@Autowired(required=true) @Qualifier("businessMemberManagerImpl")
	private BusinessMemberManager businessMemberManager;
	
	public void run() {
		System.out.println("==============如果商圈会员到期，自动删除会员角色==============");
		try {
			this.update_businessmember_orgrole();
		} catch (Exception e) {
			System.out.println("==============执行商圈会员角色定时任务时出错：==============");
			e.printStackTrace();
		}
	}

	/** 
	 * Description :  商圈会员角色定时任务(如果商圈会员到期，自动删除角色)
	 * Create Date: 2010-7-21下午05:38:03 by sunl  Modified Date: 2010-7-21下午05:38:03 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void update_businessmember_orgrole() throws Exception {
		businessMemberManager.update_businessmember_orgrole();
	}
} 
