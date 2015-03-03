package com.gpcsoft.epp.common.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.PubLishBulModel;
import com.gpcsoft.epp.bulletin.manager.BulletinManager;
import com.gpcsoft.epp.bulletin.manager.PublishBulletin;
import com.gpcsoft.epp.common.domain.ApiLog;
import com.gpcsoft.epp.common.domain.ApiLogStatusEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.service.ApiLogService;
import com.gpcsoft.epp.common.utils.FileUtils;

@Service 
public class ApiLogServiceImpl extends BaseGenericServiceImpl<ApiLog> implements ApiLogService {

	@Autowired(required=true) @Qualifier("bulletinManagerImpl")
	private BulletinManager bulletinManager;

	/** 
	 * Description :  根据日志记录Id发送公告
	 * Create Date: 2010-9-15上午10:50:09 by yangx  Modified Date: 2010-9-15上午10:50:09 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String saveSendBulletin(String bizId){
		String flag = ApiLogStatusEnum.APILOG_FALSE;
		String[] bizIds = bizId.split(SeparationEnum.COMMA);
		String publishBulletinKey = messageSource.getMessage("publishBulletinKey");//获取发布清单
		String basePath = messageSource.getMessage("uploadUrl");
		List<Bulletin> bulletinList = bulletinManager.getBulletinByObjIds(bizIds);
		if (bulletinList!=null&&bulletinList.size()>0) {//判断是否有要发布的公告
		if (publishBulletinKey!=null&&!"".equals(publishBulletinKey)) {
			String[] publishBulletinKeys = publishBulletinKey.split(SeparationEnum.NUM);
			for (String className:publishBulletinKeys) {//循环要发布的清单
				PublishBulletin publishBulletin;
				try {
					publishBulletin = (PublishBulletin)Class.forName(className).newInstance();
					for (Bulletin bulletin:bulletinList) {
						PubLishBulModel pubLishBulModel = new PubLishBulModel();
						String attachPath = bulletin.getContent().getPath()==null?"":bulletin.getContent().getPath();
						String bullContent = FileUtils.readFileByLines(basePath+attachPath+bulletin.getContent().getFileName(), "UTF-8");//读取公告内容
						bulletin.setBullContents(bullContent);
						pubLishBulModel.setBulletin(bulletin);
						publishBulletin.publishBulletin(pubLishBulModel);//发布公告
					}
				} catch (Exception e) {
					this.publishBulletinImplError("获取发布公告实现类异常");
					e.printStackTrace();
				}
			}
		}
		}
		return flag;
	}
	
	/** 
	 * Description :  获取发布服务错误
	 * Create Date: 2010-11-26下午01:38:19 by yangx  Modified Date: 2010-11-26下午01:38:19 by yangx
	 * @param   errorContent:错误内容
	 */
	private void publishBulletinImplError(String errorContent){
		
		OutputStreamWriter osw=null;
		try {
			File file = this.createFile();
			osw = new OutputStreamWriter(new FileOutputStream(file,true));
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		if(osw!=null){
			BufferedWriter bw=new BufferedWriter(osw); 
			try {
				bw.write("\n"+DateUtil.getCurDateTime()+"_"+errorContent);
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					bw.close();
					osw.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 			
			}
		}
	}
	
	private File createFile() throws Exception{
		Properties props = new Properties();
    	//拿到配置的日志存储路径
		try {			
			InputStream in = this.getClass().getResourceAsStream("/sys/publishLog.properties");
			props.load(in);
	        in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String logFileAddr = props.getProperty("logFlie.addr");
    	//这里要注意文件的格式“CZW_2010-11-24.log".CZW是为了识别是发送到CZW的日志。
    	File fileMK = new File(logFileAddr);
		if (!fileMK.exists()) {
			fileMK.mkdir();
		}
		File file = new File(logFileAddr+"Manual_ERROR"+DateUtil.getCurDate()+".log");
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
    }
}
