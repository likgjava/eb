package com.gpcsoft.epp.bulletin.manager.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.axis.client.Call;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.epp.bulletin.dao.BulletinDao;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.domain.PubLishBulModel;
import com.gpcsoft.epp.bulletin.manager.PublishBulletin;
import com.gpcsoft.epp.common.domain.ApiLogStatusEnum;
import com.gpcsoft.epp.common.domain.ApiLogTargetEnum;
import com.gpcsoft.epp.common.domain.ApiLogTypeEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.manager.ApiLogManager;
import com.gpcsoft.epp.common.utils.FileUtils;
import com.gpcsoft.epp.project.domain.Project;

public class PublishBulletinPortal implements PublishBulletin{

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	private BulletinDao bulletinDaoHibernate;
	private BulletinDao getBulletinDao(){
		if(this.bulletinDaoHibernate == null){
				this.bulletinDaoHibernate = (BulletinDao)FrameBeanFactory.getBean("bulletinDaoHibernate");
		}
		return this.bulletinDaoHibernate;
	}
	
	private ApiLogManager apiLogManager;
	private ApiLogManager getApiLogManager(){
		if (this.apiLogManager == null) {
			this.apiLogManager = (ApiLogManager)FrameBeanFactory.getBean("apiLogManagerImpl");
		}
		return this.apiLogManager;
	}
	
	public PubLishBulModel publishBulletin(PubLishBulModel pubLishBulModel){
		Bulletin bulletin = pubLishBulModel.getBulletin();
		String filePath = getFilePropertyByCKey("uploadUrl");
		if(bulletin!=null&&bulletin.getContent()!=null){
			//			String bullContent = FileUtils.readFileByLines(filePath+bulletin.getContent().getPath()+bulletin.getContent().getFileName(), null);//读取公告内容
			String bullContent = FileUtils.readFileByLines(filePath+bulletin.getContent().getPath()+bulletin.getContent().getFileName(), "UTF-8");//读取公告内容
			bulletin.setBullContents(bullContent);
		}
		String relUserName = this.getUserName();
		Project project = this.getBulletinDao().getProjectByBulletinId(bulletin.getObjId());
		String endPoints = this.getFilePropertyByCKey("bulletinEndPoint");
		if (endPoints!=null&&!"".equals(endPoints)&&endPoints.split(SeparationEnum.AT)!=null&&endPoints.split(SeparationEnum.AT).length>0) {//判断是否配置URL信息
			String[] endPoint = endPoints.split(SeparationEnum.AT);//一组数据格式为 URL,操作名,用户名,密码
			for (int i=0;i<endPoint.length;i++) {
				if (endPoint[i]!=null||!"".equals(endPoint[i])&&endPoint[i].split(SeparationEnum.COMMA)!=null&&endPoint[i].split(SeparationEnum.COMMA).length>0) {//判断每个配置信息
					String[] endPointq = endPoint[i].split(SeparationEnum.COMMA);
						org.apache.axis.client.Service service = new org.apache.axis.client.Service();
						Call call;
						try {
							call = (Call) service.createCall();
							if (endPointq.length==2) {
								if (endPointq[0]!=null&&!"".equals(endPointq[0])) {//URL路径
									call.setTargetEndpointAddress(endPointq[0]);
								}
								if (endPointq[1]!=null&&!"".equals(endPointq[1])) {//操作名称
									call.setOperationName(endPointq[1]);
								}
							}
							else if (endPointq.length==4) {
								if (endPointq[0]!=null&&!"".equals(endPointq[0])) {//URL路径
									call.setTargetEndpointAddress(new java.net.URL(endPoint[0]));
								}
								if (endPointq[1]!=null&&!"".equals(endPointq[1])) {//操作名称
									call.setOperationName(endPointq[1]);
								}
								if (endPointq[2]!=null&&!"".equals(endPointq[2])) {//用户名
									call.setUsername(endPointq[2]);
								}
								if (endPointq[3]!=null&&!"".equals(endPointq[3])) {//密码
									call.setPassword(endPointq[3]);
								}
							}
							logger.debug(this.getXmlForCms(bulletin, project, relUserName));
							String  result = (String)call.invoke(new Object[]{getXmlForCms(bulletin,project,relUserName),bulletin.getBullContents()});
							if("success".equals(result)){
								if (project!=null) {
									this.getApiLogManager().saveOrUpdateLogRecord(bulletin.getObjId(),project.getObjId(),ApiLogTypeEnum.BULLETIN,ApiLogTargetEnum.QINGDAO_PORTAL,ApiLogTargetEnum.getCN(ApiLogTargetEnum.QINGDAO_PORTAL), ApiLogStatusEnum.APILOG_TRUE,DateUtil.getCurDateTime()+"_成功!");
								}else{
									this.getApiLogManager().saveOrUpdateLogRecord(bulletin.getObjId(),null,ApiLogTypeEnum.BULLETIN,ApiLogTargetEnum.QINGDAO_PORTAL,ApiLogTargetEnum.getCN(ApiLogTargetEnum.QINGDAO_PORTAL), ApiLogStatusEnum.APILOG_TRUE,DateUtil.getCurDateTime()+"_成功!");
								}
							}else{
								if (project!=null) {
									this.getApiLogManager().saveOrUpdateLogRecord(bulletin.getObjId(),project.getObjId(),ApiLogTypeEnum.BULLETIN,ApiLogTargetEnum.QINGDAO_PORTAL,ApiLogTargetEnum.getCN(ApiLogTargetEnum.QINGDAO_PORTAL), ApiLogStatusEnum.APILOG_FALSE,DateUtil.getCurDateTime()+"_失败!");
								}else{
									this.getApiLogManager().saveOrUpdateLogRecord(bulletin.getObjId(),null,ApiLogTypeEnum.BULLETIN,ApiLogTargetEnum.QINGDAO_PORTAL,ApiLogTargetEnum.getCN(ApiLogTargetEnum.QINGDAO_PORTAL), ApiLogStatusEnum.APILOG_FALSE,DateUtil.getCurDateTime()+"_失败!");
								}
							}
						} catch (Exception e) {
							if (project!=null) {
								this.getApiLogManager().saveOrUpdateLogRecord(bulletin.getObjId(),project.getObjId(),ApiLogTypeEnum.BULLETIN,ApiLogTargetEnum.QINGDAO_PORTAL,ApiLogTargetEnum.getCN(ApiLogTargetEnum.QINGDAO_PORTAL), ApiLogStatusEnum.APILOG_FALSE,DateUtil.getCurDateTime()+"_发布到CMS失败!发送服务异常!");
							}else{
								this.getApiLogManager().saveOrUpdateLogRecord(bulletin.getObjId(),null,ApiLogTypeEnum.BULLETIN,ApiLogTargetEnum.QINGDAO_PORTAL,ApiLogTargetEnum.getCN(ApiLogTargetEnum.QINGDAO_PORTAL), ApiLogStatusEnum.APILOG_FALSE,DateUtil.getCurDateTime()+"_发布到CMS失败!发送服务异常!");
							}
							e.printStackTrace();
						}
				}else {
					if (project!=null) {
						this.getApiLogManager().saveOrUpdateLogRecord(bulletin.getObjId(),project.getObjId(),ApiLogTypeEnum.BULLETIN,ApiLogTargetEnum.QINGDAO_PORTAL,ApiLogTargetEnum.getCN(ApiLogTargetEnum.QINGDAO_PORTAL), ApiLogStatusEnum.APILOG_FALSE,DateUtil.getCurDateTime()+"_发布到CMS失败!没有配置发送的URL、操作名称等信息!");
					}else{
						this.getApiLogManager().saveOrUpdateLogRecord(bulletin.getObjId(),null,ApiLogTypeEnum.BULLETIN,ApiLogTargetEnum.QINGDAO_PORTAL,ApiLogTargetEnum.getCN(ApiLogTargetEnum.QINGDAO_PORTAL), ApiLogStatusEnum.APILOG_FALSE,DateUtil.getCurDateTime()+"_发布到CMS失败!没有配置发送的URL、操作名称等信息!");
					}
				}
			}
		}else{
			if (project!=null) {
				this.getApiLogManager().saveOrUpdateLogRecord(bulletin.getObjId(),project.getObjId(),ApiLogTypeEnum.BULLETIN,ApiLogTargetEnum.QINGDAO_PORTAL,ApiLogTargetEnum.getCN(ApiLogTargetEnum.QINGDAO_PORTAL), ApiLogStatusEnum.APILOG_FALSE,DateUtil.getCurDateTime()+"_发布到CMS失败!没有配置发送信息!");
			}else{
				this.getApiLogManager().saveOrUpdateLogRecord(bulletin.getObjId(),null,ApiLogTypeEnum.BULLETIN,ApiLogTargetEnum.QINGDAO_PORTAL,ApiLogTargetEnum.getCN(ApiLogTargetEnum.QINGDAO_PORTAL), ApiLogStatusEnum.APILOG_FALSE,DateUtil.getCurDateTime()+"_发布到CMS失败!没有配置发送信息!");
				
			}
		}
	return null;
}
	private String getXmlForCms(Bulletin bulletin,Project project,String relUserName){
		StringBuffer stringb = new StringBuffer();
		stringb.append("<?xml version='1.0' encoding='UTF-8'?>\n");
		stringb.append("<tables>\n");
		stringb.append("<ecpBaseBulletin>\n");
		stringb.append("<bulletinNo>"+bulletin.getBulletinNo()+"</bulletinNo>\n");
		stringb.append("<bullTitle>"+bulletin.getBullTitle()+"</bullTitle>\n");
		if(bulletin.getBullType().equals(BulletinTypeEnum.PURCHASE_PREVIEW)){
			stringb.append("<bullType>00</bullType>\n");//预审公告
		}else if(bulletin.getBullType().equals(BulletinTypeEnum.PURCHASE_BULLETIN)){
			stringb.append("<bullType>01</bullType>\n");//采购公告
		}else if(bulletin.getBullType().equals(BulletinTypeEnum.RESULT_BULLETIN)){
			stringb.append("<bullType>02</bullType>\n");//结果公告
		}
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(bulletin.getSignUpSTime()!=null){
			stringb.append("<signUpSTime>"+sDateFormat.format(bulletin.getSignUpSTime())+"</signUpSTime>\n");
		}
		if(bulletin.getSignUpETime()!=null){
			stringb.append("<signUpETime>"+sDateFormat.format(bulletin.getSignUpETime())+"</signUpETime>\n");
		}
		if(bulletin.getTenderStartTime()!=null){
			stringb.append("<tenderStartTime>"+sDateFormat.format(bulletin.getTenderStartTime())+"</tenderStartTime>\n");
		}
		if(bulletin.getTenderEndTime()!=null){
			stringb.append("<tenderEndTime>"+sDateFormat.format(bulletin.getTenderEndTime())+"</tenderEndTime>\n");
		}
		if(project!=null&&null!=project.getAgencies()){
			stringb.append("<srcApp>"+project.getAgencies().getOrgName()+"</srcApp>\n");
		}
		stringb.append("<signupUrl></signupUrl>\n");
		stringb.append("<region>00</region>\n");
		stringb.append("</ecpBaseBulletin>\n");
		stringb.append("</tables>\n");
		return stringb.toString();
	}
	
	/** 
	 * Description :  根据相对路径读取文件
	 * Create Date: 2010-11-29下午02:55:27 by yangx  Modified Date: 2010-11-29下午02:55:27 by yangx
	 * @param   contentkey:要读取文件的值
	 * @return  String:获取的值
	 * @Exception   
	 */
	private String getFilePropertyByCKey(String contentkey){
		Properties props = new Properties();
    	//拿到配置的日志存储路径
		try {			
			InputStream in = this.getClass().getResourceAsStream("/sys/attachment.properties");
			props.load(in);
	        in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String contentVal = props.getProperty(contentkey);
		return contentVal;
	}
	
	/** 
	 * Description :  获取公告类型对应的发布公告类型字符串
	 * Create Date: 2010-8-24上午11:48:06 by yangx  Modified Date: 2010-8-24上午11:48:06 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private String getBulletinStringByType(String BulletinString,String type,Bulletin bulletinq,Project project){
		if (BulletinString==null||"".equals(BulletinString)||BulletinString.split(SeparationEnum.COMMA)==null||BulletinString.split(SeparationEnum.COMMA).length<1) {//判断是否配置了相应信息
			if (project!=null) {
				this.getApiLogManager().saveOrUpdateLogRecord(bulletinq.getObjId(),project.getObjId(),ApiLogTypeEnum.BULLETIN,ApiLogTargetEnum.QINGDAO_PORTAL,ApiLogTargetEnum.getCN(ApiLogTargetEnum.QINGDAO_PORTAL), ApiLogStatusEnum.APILOG_FALSE,DateUtil.getCurDateTime()+EsExceptionEnum.IS_NOT_BULLETINSTRING);
			}else{
				this.getApiLogManager().saveOrUpdateLogRecord(bulletinq.getObjId(),null,ApiLogTypeEnum.BULLETIN,ApiLogTargetEnum.QINGDAO_PORTAL,ApiLogTargetEnum.getCN(ApiLogTargetEnum.QINGDAO_PORTAL), ApiLogStatusEnum.APILOG_FALSE,DateUtil.getCurDateTime()+EsExceptionEnum.IS_NOT_BULLETINSTRING);
			}
		}
		String[] bulletin = BulletinString.split(SeparationEnum.COMMA);
		for(int i=0;i<bulletin.length;i++){
			String outBulletin = bulletin[i];
			if (outBulletin==null||"".equals(outBulletin)||outBulletin.split(SeparationEnum.COLON)==null||outBulletin.split(SeparationEnum.COLON).length<1) { //判断每个对应的是否有值
				if (project!=null) {
					this.getApiLogManager().saveOrUpdateLogRecord(bulletinq.getObjId(),project.getObjId(),ApiLogTypeEnum.BULLETIN,ApiLogTargetEnum.QINGDAO_PORTAL,ApiLogTargetEnum.getCN(ApiLogTargetEnum.QINGDAO_PORTAL), ApiLogStatusEnum.APILOG_FALSE,DateUtil.getCurDateTime()+EsExceptionEnum.IS_NOT_EBUYMETHOD);
				}else{
					this.getApiLogManager().saveOrUpdateLogRecord(bulletinq.getObjId(),null,ApiLogTypeEnum.BULLETIN,ApiLogTargetEnum.QINGDAO_PORTAL,ApiLogTargetEnum.getCN(ApiLogTargetEnum.QINGDAO_PORTAL), ApiLogStatusEnum.APILOG_FALSE,DateUtil.getCurDateTime()+EsExceptionEnum.IS_NOT_EBUYMETHOD);
				}
			}
			String[] outBulletins = outBulletin.split(SeparationEnum.COLON);
			String outBulletinType = outBulletins[0];
			String localBulletinType = outBulletins[1];
			if (localBulletinType.equals(type)) {
				return outBulletinType;
			}
		}
		return null;
	}
	
	private String getUserName(){
		Properties props = new Properties();
    	//拿到配置的日志存储路径
		try {			
			InputStream in = this.getClass().getResourceAsStream("/sys/publishLog.properties");
			props.load(in);
	        in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String userName = props.getProperty("userName");
		return userName;
	}
	
}
