package com.gpcsoft.epp.bulletin.manager.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.axis.client.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.bulletin.dao.BulletinDao;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.manager.BulletinManager;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.srplatform.baseData.dao.SequenceNumberDao;

@Repository
public class BulletinManagerImpl extends BaseManagerImpl<Bulletin> implements BulletinManager {

	@Autowired(required=true)  @Qualifier("bulletinDaoHibernate")
	private BulletinDao bulletinDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("sequenceNumberDaoHibernate")
	private SequenceNumberDao sequenceNumberdao;

	/** 
	 * FuncName:getBulletinByQueryObject
	 * Description:根据查询条件获取对应公告
	 * @param queryObject
	 * @return Bulletin
	 * @author Administrator
	 * @Create Date: 2010-5-19下午01:55:50 
	 * @Modifier   Administrator
	 * @Modified Date: 2010-5-19下午01:55:50 
	 */
	@SuppressWarnings("unchecked")
	public Bulletin getBulletinByQueryObject(QueryObject queryObject) {
		List list = bulletinDaoHibernate.findByQuery(queryObject);
		return (null != list && !list.isEmpty())?(Bulletin)list.get(0):null;
	}

	/** 
	 * FuncName:getBulletinListByQueryObject
	 * Description:根据查询条件获取对应公告列表
	 * @param queryObject
	 * @return  List<Bulletin>
	 * @author Administrator
	 * @Create Date: 2010-5-19下午01:55:50 
	 * @Modifier  Administrator
	 * @Modified Date: 2010-5-19下午01:55:50 
	 */
	public List<Bulletin> getBulletinListByQueryObject(QueryObject queryObject) {
		List<Bulletin> list = bulletinDaoHibernate.getBulletinListByObject(queryObject);
		return list;
	}
	
	/** 
	 * FuncName:getBulletinListByObject
	 * Description :  根据对象获取公告列表
	 * @param   page:公告分页对象,queryObject[bulletinNo:公告编号;bullTitle:公告标题;bullType:公告类型;auditStatus:审核状态;monitorId:监管人;buyerId:采购人]
	 * @return  Page<Bulletin>
	 * @author yangx
	 * @Create Date: 2010-7-8下午01:56:21 
	 * @Modifier yangx
	 * @Modified Date: 2010-7-8下午01:56:21     
	 */
	@SuppressWarnings("unchecked")
	public Page<Bulletin> getBulletinListByObject(Page<Bulletin> page,QueryObject queryObject){
		return bulletinDaoHibernate.getBulletinListByObject(page, queryObject);
	}
	
	/**
	 * FuncName:getBulletinTotalByQueryObject
	 * Description:根据查询条件统计对应的公告数据
	 * @param queryObject[buyerId:采购人ID;bullType:公告类型;auditStatus:审核状态;monitorId:监管人]
	 * @return BigDecimal
	 * @author Administrator
	 * @Create Date: 2010-7-7下午01:56:46   
	 * @Modifier Administrator
	 * @Modified Date: 2010-7-7下午01:56:46 
	 */
	public BigDecimal getBulletinTotalByQueryObject(QueryObject queryObject) {
		return bulletinDaoHibernate.getBulletinTotalByQueryObject(queryObject);
	}
	
	/**
	 * FuncName: generatorBulletinCodeByQO
	 * Description:生成规则编号
	 * @param  String
	 * @author: shenjz
	 * @Create Date:2010-12-30  下午02:19:26
	 * @Modifier: shenjz
	 * @Modified Date:2010-12-30  下午02:19:26
	 */
	public String generatorBulletinCodeByQO(QueryObject queryObject,String projBuyMethod) throws EsException {
		// 1.根据项目采购方式获取公告前缀编号
		String prefixCode = "";
		try {
			if(projBuyMethod.equals(EbuyMethodEnum.OPEN_BIDDING)){				// 公开招标
				prefixCode = messageSource.getMessage("openBulletinPrefixCode");
			}else if(projBuyMethod.equals(EbuyMethodEnum.INVITE_BIDDING)){		// 邀请招标
				prefixCode = messageSource.getMessage("inviteBulletinPrefixCode");
			}else if(projBuyMethod.equals(EbuyMethodEnum.NEGOTIATE)){			// 竞争性谈判
				prefixCode = messageSource.getMessage("negotiateBulletinPrefixCode");
			}else if(projBuyMethod.equals(EbuyMethodEnum.INQUIRY)){				// 询价
				prefixCode = messageSource.getMessage("inquiryBulletinPrefixCode");
			}else if(projBuyMethod.equals(EbuyMethodEnum.SINGLE_SOURCE)){		// 单一来源
				prefixCode = messageSource.getMessage("singleSourceBulletinPrefixCode");
			}else{
				prefixCode = "";
			}
		} catch (Exception e) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GENERATOR_CODE_GET_PREFIX_CODE_ISNULL));
		}
		// 2.按照规则生成公告编号
		StringBuffer returnCode = new StringBuffer();
		returnCode.append(prefixCode);
		if(null != prefixCode && !"".equals(prefixCode)){
			returnCode.append("-");
		}
		returnCode.append(DateUtil.format(new Date(), "yyyyMMdd"));
		try {
			returnCode.append("-"+sequenceNumberdao.updateAndGetCurSn(returnCode.toString(), 3));
		} catch (Exception e) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GENERATOR_CODE_IS_DB_READONLY));
		}
		return returnCode.toString();
	}
	
	/** 
	 * FuncName:releaseBulletin
	 * Description:将公告发布到外网 
	 * @param   bulletinContent:公告内容,bulletinType:公告类型,bulletinTitle:公告标题,relUserName:发布人名称,projectName:项目名称,projectCode:项目编号,ebuyMethod:采购方式
	 * @return  void
	 * @author yangx
	 * @Create Date: 2010-8-24下午01:57:12 
	 * @Modified yangx
	 * @Modified Date: 2010-8-24下午01:57:12  
	 */
	public void releaseBulletin(String bulletinContent,String bulletinType,String bulletinTitle,String relUserName,String projectName,String projectCode,String ebuyMethod) throws Exception{
		/* 为服务器的服务地址 */
		String endPoints = messageSource.getMessage("bulletinEndPoint");
		if (endPoints!=null&&!"".equals(endPoints)&&endPoints.split(SeparationEnum.AT)!=null&&endPoints.split(SeparationEnum.AT).length>0) {//判断是否配置URL信息
			String[] endPoint = endPoints.split(SeparationEnum.AT);//一组数据格式为 URL,操作名,用户名,密码
			for (int i=0;i<endPoint.length;i++) {
				if (endPoint[i]!=null||!"".equals(endPoint[i])&&endPoint[i].split(SeparationEnum.COMMA)!=null&&endPoint[i].split(SeparationEnum.COMMA).length>0) {//判断每个配置信息
					String[] endPointq = endPoint[i].split(SeparationEnum.COMMA);
						org.apache.axis.client.Service service = new org.apache.axis.client.Service();
						Call call = (Call) service.createCall();
						if (endPointq.length==2) {
							if (endPointq[0]!=null&&!"".equals(endPointq[0])) {//URL路径
								call.setTargetEndpointAddress(new java.net.URL(endPoint[0]));
							}
							if (endPointq[1]!=null&&!"".equals(endPointq[1])) {//操作名称
								call.setOperationName(endPointq[1]);
							}
						}else if (endPointq.length==4) {
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
						String  result = (String)call.invoke(new Object[]{getXmlForCms(bulletinType,bulletinTitle,relUserName,projectName,projectCode,ebuyMethod),bulletinContent, "1"});
						if("success".equals(result)){
							logger.info("===============发布到CMS成功=======================");
						}else{
							logger.info("===============发布到CMS失败=======================");
							throw new Exception("error");
						}
				}else {
					throw new Exception("error");
				}
			}
		}else{
			throw new Exception("error");
		}
	}
	
	private String getXmlForCms(String bulletinType,String bulletinTitle,String relUserName,String projectName,String projectCode,String ebuyMethod){
		String bulletinTypes = getBulletinStringByType(messageSource.getMessage("relBulletinType"),bulletinType);
		StringBuffer stringb = new StringBuffer();
		stringb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		stringb.append("<bulletins>");
		stringb.append("<bulletin>");
		stringb.append("<xmlType>CGGG</xmlType>");
		stringb.append("<bulletin-title>"+bulletinTitle+"</bulletin-title>");
		stringb.append("<bulletin-type>"+bulletinTypes+"</bulletin-type>");
		stringb.append("<defStr3>市本级</defStr3>");
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		stringb.append("<bulletin-pubdate>"+sDateFormat.format(new Date())+"</bulletin-pubdate>");
		stringb.append("<bulletin-valid-date>"+sDateFormat.format(new Date())+"</bulletin-valid-date>");
		stringb.append("<bulletin-pubperson>"+(relUserName)+"</bulletin-pubperson>");
		stringb.append("<projName>"+projectName+"</projName>");
		stringb.append("<projCode>"+projectCode+"</projCode>");
		stringb.append("<countryOrBlew></countryOrBlew>");
		if((EbuyMethodEnum.OPEN_BIDDING).equals(ebuyMethod)){
			stringb.append("<procurementMethod>1</procurementMethod>");
		}else if((EbuyMethodEnum.INVITE_BIDDING).equals(ebuyMethod)){
			stringb.append("<procurementMethod>2</procurementMethod>");
		}else if((EbuyMethodEnum.NEGOTIATE).equals(ebuyMethod)){
			stringb.append("<procurementMethod>3</procurementMethod>");
		}else if((EbuyMethodEnum.INQUIRY).equals(ebuyMethod)){
			stringb.append("<procurementMethod>4</procurementMethod>");
		}else if((EbuyMethodEnum.SINGLE_SOURCE).equals(ebuyMethod)){
			stringb.append("<procurementMethod>5</procurementMethod>");
		}
		stringb.append("</bulletin>");
		stringb.append("</bulletins>");
		return stringb.toString();
	}
	

	/** 
	 * FuncName:getBulletinStringByType
	 * Description :  获取公告类型对应的发布公告类型字符串
	 * @param   BulletinString:公告类型字符串,type:类型
	 * @return  String
	 * @author yangx
	 * @Create Date: 2010-8-24上午11:48:06 
	 * @Modifier   yangx
	 * @Modified Date: 2010-8-24上午11:48:06  
	 */
	public String getBulletinStringByType(String BulletinString,String type){
		if (BulletinString==null||"".equals(BulletinString)||BulletinString.split(SeparationEnum.COMMA)==null||BulletinString.split(SeparationEnum.COMMA).length<1) {//判断是否配置了相应信息
			throw new EsException(messageSource.getMessage(EsExceptionEnum.IS_NOT_BULLETINSTRING));
		}
		String[] bulletin = BulletinString.split(SeparationEnum.COMMA);
		for(int i=0;i<bulletin.length;i++){
			String outBulletin = bulletin[i];
			if (outBulletin==null||"".equals(outBulletin)||outBulletin.split(SeparationEnum.COLON)==null||outBulletin.split(SeparationEnum.COLON).length<1) { //判断每个对应的是否有值
				throw new EsException(messageSource.getMessage(EsExceptionEnum.IS_NOT_EBUYMETHOD));
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
	
	/** 
	 * FuncName:getBulletinByObjId
	 * Description :  根据日志Id查询公告
	 * @param   objIds:日志主键
	 * @return  List<Bulletin>
	 * @author yangx
	 * @Create Date: 2010-9-15上午11:00:18 
	 * @Modifier    yangx
	 * @Modified Date: 2010-9-15上午11:00:18  
	 */
	public List<Bulletin> getBulletinByObjIds(String[] objIds){
		String objIdq = "";
		for (String objId:objIds) {
			objIdq += "'"+objId+"',";
		}
		objIdq = objIdq.substring(0,objIdq.lastIndexOf(SeparationEnum.COMMA));
		return bulletinDaoHibernate.getBulletinByObjIds(objIdq);
	}

	public Bulletin getBulletinByBulletinNo(String bulletinNo) {
		return bulletinDaoHibernate.getBulletinByBulletinNo(bulletinNo);
	}
}
