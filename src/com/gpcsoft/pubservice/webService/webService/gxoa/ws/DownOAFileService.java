/**
 * 
 */
package com.gpcsoft.pubservice.webService.webService.gxoa.ws;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServlet;

/** 
  *  Comments: <strong>SyncProjectService</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2011-8-2 下午05:58:04 by liangxj    					                            
  *  <br/>Modified Date:  2011-8-2 下午05:58:04 by liangxj                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
public class DownOAFileService extends HttpServlet {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
	    try {
	        URL url = new URL("http://count.crsky.com/view_down.asp?down_url=http://8.jsdx3.crsky.com/201104/Editplus-v3.30.397.zip&downd_id=24&ID=6441&SOFTID=1578&down=yes");
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        DataInputStream in = new DataInputStream(connection.getInputStream());
	        DataOutputStream out = new DataOutputStream(new FileOutputStream("D:/gpcsoftfile/gxoa/ss.zip"));
	        byte[] buffer = new byte[4096];
	        int count = 0;
	        while ((count = in.read(buffer)) > 0) {
	          out.write(buffer, 0, count);
	        }
	        out.close();
	        in.close();
	      }
	      catch (Exception e) {
	        e.printStackTrace();
	      }
	}
}
