package com.gpcsoft.pubservice.application.staticpage;

import java.util.TimerTask;

/** 
  *  Comments: <strong>生成静态页面定时任务</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2011-10-28 下午04:40:22 by likg    					                            
  *  <br/>Modified Date:  2011-10-28 下午04:40:22 by likg                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
public class MakeStaticPageTimeTask extends TimerTask {
	
	@Override
	public void run() {
		try{
			MakeStaticPageUtil.makeStaticPage(MakeStaticPageUtil.STATIC_ALL);
		}catch(Exception ce){
			ce.printStackTrace();
		}
	}

} 
