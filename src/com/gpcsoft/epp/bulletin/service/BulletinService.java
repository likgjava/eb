package com.gpcsoft.epp.bulletin.service;
import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.srplatform.auth.domain.ListBind;
import com.gpcsoft.srplatform.auth.domain.User;

public interface BulletinService extends BaseGenericService<Bulletin> {

	/** 
	 * FuncName:getBulletinByObjId
	 * Description :  根据公告ID获取公告
	 * @param   objId:公告主键
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-6-21上午09:56:48  
	 * @Modifier yangx
	 * @Modified Date: 2010-6-21上午09:56:48 
	 */	
	public Bulletin getBulletinByObjId(String objId);
	
	/** 
	 * FuncName: getBulletinByProjectId
	 * Description :根据项目主键、公告类型获取公告
	 * @param   projectId:项目主键,bullType:公告类型
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-6-21上午09:56:48 
	 * @Modifier  yangx
	 * @Modified Date: 2010-6-21上午09:56:48    
	 */	
	public Bulletin getBulletinByProjectId(String projectId,String bullType);
	
	/** 
	 * FuncName:getPurBulletinByProjectId
	 * Description :根据项目主键拿到采购公告
	 * @param projectId:项目主键
	 * @return Bulletin
	 * @author Administrator
	 * @Create Date: 2010-5-19下午01:53:18 
	 * @Modifier Administrator
	 * @Modified Date: 2010-5-19下午01:53:18  
	 */
	public Bulletin getPurBulletinByProjectId(String projectId);
	
	/** 
	 * FuncName:getResultBulletinByProjectId
	 * Description :  根据项目主键获取结果公告
	 * @param   projectId:项目主键
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午02:15:48 
	 * @Modifier   yangx
	 * @Modified Date: 2010-9-7下午02:15:48 
	 */
	public Bulletin getResultBulletinByProjectId(String projectId);

	/** 
	 * FuncName:getResultPublicityByProjectId
	 * Description :根据项目主键获取结果公示
	 * @param  projectId：项目主键
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午10:06:09 
	 * @Modifier   yangx
	 * @Modified Date: 2010-9-7下午10:06:09     
	 */
	public Bulletin getResultPublicityByProjectId(String projectId);
	
	/**
	 * FuncName:getInqpBulletinByProjectId
	 * Description : 根据项目ID获取询价公告 
	 * @param   projectId:项目主键
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-8-3下午05:17:35
	 * @Modifier liuke
	 * @Modified Date: 2010-8-3下午05:17:35 
	 */
	public Bulletin getInqpBulletinByProjectId(String projectId);

	/** 
	 * FuncName:savePurBulletinForAudit
	 * Description :  审核招标公告
	 * @param ids:公告Id,passStatus:审核状态,opinion:意见
	 * @return Bulletin
	 * @author Administrator
	 * @Create Date: 2010-5-29下午02:11:37   
	 * @Modifier Administrator
	 * @Modified Date: 2010-5-29下午02:11:37 
	 */
	public Bulletin savePurBulletinForAudit(String ids, String passStatus,String opinion);
	
	/** 
	 * FuncName:saveVariationBulletinForAudit
	 * Description :审核更正公告
	 * @param   ids:公告主键,passStatus:审核状态
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午12:41:48 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-7下午12:41:48     
	 */
	public Bulletin saveVariationBulletinForAudit(String ids, String passStatus);
	
	
	/**
	 * 保存公告方法重载  
	 * Description :  
	 * Create Date: 2011-11-17下午03:08:17 by chenhongjun  
	 * Modified Date: 2011-11-17下午03:08:17 by chenhongjun
	 *@param bulletin  公告对象
	 *@param projProcessStatus  项目实施状态
	 *@param fileVirtualPath  公告文件类型路径 FileVirtualPathEnum枚举对象中获取
	 *@param completedWorkTaskType   完成工作任务类型 CompletedWorkTaskTypeEnum枚举对象中获取
	 *@return 
	 *下午03:08:17 
	 *Bulletin
	 */
	public Bulletin saveBulletin(Bulletin bulletin,String projProcessStatus,String fileVirtualPath,String completedWorkTaskType);
	
	/** 
	 * FuncName:saveResultBulletinForAudit
	 * Description :审核结果公告
	 * @param   ids:公告Id,passStatus:审核状态,opinion:意见
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午12:41:48 
	 * @Modifier  yangx  
	 * @Modified Date: 2010-9-7下午12:41:48     
	 */
	public Bulletin saveResultBulletinForAudit(String ids, String passStatus,String opinion);
	
	/**
	 * FuncName:saveChangeBulletinForAudit
	 * Description :审核变更公告  
	 * @param   ids:变更公告主键,passStatus:审核状态
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-10-15下午01:12:39 
	 * @Modifier   liuke
	 * @Modified Date: 2010-10-15下午01:12:39  
	 */
	public Bulletin saveChangeBulletinForAudit(String ids, String passStatus);
	
	/** 
	 * FuncName:getVariationBulletinListByProjectId
	 * Description: 根据项目Id获取审核通过的更正公告
	 * @param   projectId:项目主键
	 * @return  List<Bulletin>
	 * @author yangx
	 * @Create Date: 2010-9-7下午07:08:39 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午07:08:39     
	 */
	public List<Bulletin> getVariationBulletinListByProjectId(String projectId);
	
	/**
	 * FuncName:saveInqpBulletinForAudit
	 * Description :审核询价公告  
	 * @param  bulletin:询价公告对象, user:用户对象
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-8-3下午05:52:20 
	 * @Modifier   liuke
	 * @Modified Date: 2010-8-3下午05:52:20 
	 */
	public Bulletin saveInqpBulletinForAudit(Bulletin bulletin, User user);
	
	/** 
	 * FuncName:saveResultPublicityForAudit
	 * Description :审核结果公示 
	 * @param   bulletin:结果公示,user:用户对象
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午10:55:21  
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午10:55:21    
	 */
	public Bulletin saveResultPublicityForAudit(Bulletin bulletin, User user);
	
	/** 
	 * FuncName:saveBulletinAndProjSchedule
	 * Description :  保存招标公告[同步保存时间规则]
	 * @param bulletin:公告对象,projProcessStatus:项目实施状态
	 * @return Bulletin
	 * @author Administrator
	 * @Create Date: 2010-6-5上午11:30:42 
	 * @Modifier Administrator
	 * @Modified Date: 2010-6-5上午11:30:42  
	 */
	public Bulletin saveBulletinAndProjSchedule(Bulletin bulletin,String projProcessStatus);
	
	/**
	 * FuncName:saveInqpBulletinAndProjSchedule
	 * Description:保存询价公告[同步保存时间规则]
	 * @param   bulletin:询价公告实体对象,projProcessStatus:项目进度,list:传入的报名响应集合
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-9-8下午06:54:56 
	 * @Modifier liuke
     * @Modified Date: 2010-9-8下午06:54:56 
	 */
	public Bulletin saveInqpBulletinAndProjSchedule(Bulletin bulletin,String projProcessStatus,ListBind list);
	
	/** 
	 * FuncName:saveUpdateBulletinAndProjSchedule
	 * Description :保存修改招标公告[同步保存时间规则]
	 * @param   bulletin：公告;projProcessStatus：项目状态
	 * @return  Bulletin
	 * @author     yangx
	 * @Create Date: 2010-9-7下午01:32:09 
	 * @Modifier    yangx
	 * @Modified Date: 2010-9-7下午01:32:09   
	 */
	public Bulletin saveUpdateBulletinAndProjSchedule(Bulletin bulletin,String projProcessStatus );

	/**
	 * FuncName:saveUpdateInqpBulletinAndProjSchedule
	 * Description :保存修改询价公告[同步保存时间规则]  
	 * @param   bulletin:询价公告,projProcessStatus:项目进度,list:传入的报名响应
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-9-9上午09:14:05 
	 * @Modifier liuke
	 * @Modified Date: 2010-9-9上午09:14:05 
	 */
	public Bulletin saveUpdateInqpBulletinAndProjSchedule(Bulletin bulletin,String projProcessStatus,ListBind list);
	
	/** 
	 * FuncName:saveSubmitBulletinAndProjSchedule
	 * Description :  保存提交招标公告[同步保存时间规则]
	 * @param   bulletin:公告,projProcessStatus:项目状态,checkValue:用以判断招标公告是否是被退回再提交
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7上午11:57:51 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7上午11:57:51  
	 */
	public Bulletin saveSubmitBulletinAndProjSchedule(Bulletin bulletin,String projProcessStatus,String checkValue);
	
	/**
	 * FuncName:saveSubmitInqpBulletinAndProjSchedule
	 * Description : 保存提交询价公告[同步保存时间规则] 
	 * @param   bulletin:询价公告,projProcessStatus:项目进度,list:传入的报名响应集合
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-9-8下午07:00:21 
	 * @Modifier   liuke
	 * @Modified Date: 2010-9-8下午07:00:21 
	 */
	public Bulletin saveSubmitInqpBulletinAndProjSchedule(Bulletin bulletin,String projProcessStatus,ListBind list);

	/** 
	 * FuncName:saveResultBulletin
	 * Description :  保存结果公告
	 * @param   bulletin:公告对象,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author  yangx
	 * @Create Date: 2010-9-7下午02:25:35 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午02:25:35  
	 */
	public Bulletin saveResultBulletin(Bulletin bulletin,String projProcessStatus);

	/** 
	 * FuncName:saveUpdateResultBulletin
	 * Description :  保存修改结果公告
	 * @param   bulletin:公告对象,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午02:25:35 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午02:25:35  
	 */
	public Bulletin saveUpdateResultBulletin(Bulletin bulletin,String projProcessStatus);
	
	/** 
	 * FuncName:saveSubmitResultBulletin
	 * Description :  提交结果公告
	 * @param   bulletin:公告对象,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author  yangx
	 * @Create Date: 2010-9-7下午02:25:35 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-7下午02:25:35 
	 */
	public Bulletin saveSubmitResultBulletin(Bulletin bulletin,String projProcessStatus);

	/** 
	 * FuncName:saveVariationBulletin
	 * Description :  保存更正公告
	 * @param   bulletin:公告对象,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午02:25:35 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午02:25:35   
	 */
	public Bulletin saveVariationBulletin(Bulletin bulletin,String projProcessStatus);

	/**
	 * FuncName:saveChangeBulletin
	 * Description :保存变更公告  
	 * @param   bulletin: 变更公告,projProcessStatus:项目进度
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-10-14下午03:05:15 
	 * @Modifier liuke
	 * @Modified Date: 2010-10-14下午03:05:15  
	 */
	public Bulletin saveChangeBulletin(Bulletin bulletin,String projProcessStatus);
	
	/** 
	 * FuncName:saveUpdateVariationBulletin
	 * Description :  保存修改更正公告
	 * @param   bulletin:公告对象,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午06:31:08    
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午06:31:08  
	 */
	public Bulletin saveUpdateVariationBulletin(Bulletin bulletin,String projProcessStatus);
	
	/**
	 * FuncName:saveUpdateChangeBulletin
	 * Description:保存修改变更公告 
	 * @param   bulletin:变更公告,projProcessStatus:项目实施状态
	 * @return Bulletin
	 * @author liuke 
	 * @Create Date: 2010-10-15上午10:46:09 
	 * @Modifier  liuke
	 * @Modified Date: 2010-10-15上午10:46:09 
	 */
	public Bulletin saveUpdateChangeBulletin(Bulletin bulletin,String projProcessStatus);
	
	/** 
	 * FuncName:saveSubmitVariationBulletin
	 * Description:提交更正公告
	 * @param   bulletin:公告对象,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午02:25:35 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-7下午02:25:35
	 */
	public Bulletin saveSubmitVariationBulletin(Bulletin bulletin,String projProcessStatus);
	
	/** 
	 * FuncName:saveSubmitChangeBulletin
	 * Description:提交变更公告
	 * @param   bulletin:变更公告对象,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-9-7下午02:25:35 
	 * @Modifier   liuke
	 * @Modified Date: 2010-9-7下午02:25:35   
	 */
	public Bulletin saveSubmitChangeBulletin(Bulletin bulletin,String projProcessStatus);
	
	/** 
	 * FuncName:saveUpdateSubmitChangeBulletin
	 * Description:提交修改变更公告
	 * @param   bulletin:变更公告,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-9-7下午06:32:35 
	 * @Modifier liuke
	 * @Modified Date: 2010-9-7下午06:32:35     
	 */
	public Bulletin saveUpdateSubmitChangeBulletin(Bulletin bulletin,String projProcessStatus);
	
	/** 
	 * FuncName:saveResultPublicity
	 * Description :保存结果公示
	 * @param   bulletin:结果公示,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午10:24:34 
	 * @Modifier yangx   
	 * @Modified Date: 2010-9-7下午10:24:34 by   
	 */
	public Bulletin saveResultPublicity(Bulletin bulletin,String projProcessStatus);
	
	/** 
	 * FuncName:saveUpdateResultPublicity
	 * Description :保存修改结果公示
	 * @param   bulletin:公告对象,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午10:24:34 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-7下午10:24:34 
	 */
	public Bulletin saveUpdateResultPublicity(Bulletin bulletin,String projProcessStatus);
	
	/** 
	 * FuncName:saveSubmitResultPublicity
	 * Description :  提交结果公示
	 * @param   bulletin:公告对象,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午10:24:34
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-7下午10:24:34   
	 */
	public Bulletin saveSubmitResultPublicity(Bulletin bulletin,String projProcessStatus);
	
	/**
	 * FuncName:getBulletinTotalByQueryObject
	 * Description :  根据查询条件统计对应的公告数据
	 * @param queryObject[buyerId:采购人ID;bullType:公告类型;auditStatus:审核状态;monitorId:监管人]公告类型一定是第一个参数
	 * @return	BigDecimal
	 * @author Administrator
	 * @Create Date: 2010-7-7下午01:56:46 
	 * @Modifier  Administrator
	 * @Modified Date: 2010-7-7下午01:56:46  
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal getBulletinTotalByQueryObject(QueryObject queryObject);
	
	/** 
	 * FuncName:getBulletinListByObject
	 * Description :  根据监管人获取公告列表
	 * @param 	page:公告分页对象,queryObject:查询条件
	 * @return  Page<Bulletin>
	 * @author yangx
	 * @Create Date: 2010-7-8上午10:26:13 
	 * @Modifier   yangx
	 * @Modified Date: 2010-7-8上午10:26:13 
	 */
	@SuppressWarnings("unchecked")
	public Page<Bulletin> getBulletinListByObject(Page<Bulletin> page,QueryObject queryObject);

	/** 
	 * FuncName:getBulletinListForRel
	 * Description :  获取待发送的公告列表
	 * Create Date: 2011-1-15下午12:10:05 by yangx  Modified Date: 2011-1-15下午12:10:05 by yangx
	 * @param   page:公告分页对象,queryObject:查询条件
	 * @return  Page<Bulletin>
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Bulletin> getBulletinListForRel(Page<Bulletin> page,QueryObject queryObject);
	
	/** 
	 * FuncName: relBulletin
	 * Description :  发布公告
	 * @param   
	 * @return  void
	 * @author yangx
	 * @Create Date: 2010-8-24下午03:12:50 
	 * @Modifier  yangx
	 * @Modified Date: 2010-8-24下午03:12:50 
	 */
	public void relBulletin();

	/** 
	 * FuncName: saveRelBulletin
	 * Description :  手动发送公告
	 * Create Date: 2011-1-15下午12:28:44 by yangx  Modified Date: 2011-1-15下午12:28:44 by yangx
	 * @param   bulletinIds：公告Ids
	 * @return  
	 * @Exception   
	 */
	public void saveRelBulletin(String bulletinIds);
	
	/**
	 * FuncName:createBulletinCodeByQO
	 * Description: 生成采购公告编号,Service层必须开启DB事物
	 * @param queryObject:为扩展参数，暂时不用,projBuyMethod:项目采购方式
	 * @return String
	 * @author wanghz
	 * @Create Date 2010-7-15 上午09:59:44 
	 */
	@SuppressWarnings("unchecked")
	public String createBulletinCodeByQO(QueryObject queryObject,String projBuyMethod)throws EsException;
	
	/**
	 * FuncName:createChangeBulletinCodeByQO
	 * Description :生成变更公告编号,Service层必须开启DB事物  
	 * @param   queryObject:用于扩展的对象,projBuyMethod:采购方式
	 * @return  String
	 * @author liuke
	 * @Create Date: 2010-10-14下午03:54:59    
	 * @Modifier  liuke
	 * @Modified Date: 2010-10-14下午03:54:59  
	 */
	public String createChangeBulletinCodeByQO(QueryObject queryObject,String projBuyMethod)throws EsException;
	
	/**
	 * 
	 * Description :  保存提交公告 
	 * Create Date: 2011-11-17下午03:34:47 by chenhongjun  
	 * Modified Date: 2011-11-17下午03:34:47 by chenhongjun
	 *@param bulletin  公告对象
	 *@param projProcessStatus   计划进程状态
	 *@param checkValue   检查值 
	 *@param fileVirtualPath   公告文件存放路径
	 *@param completedWorkTaskType  完成的工作任务类型
	 *@return
	 *下午03:34:47 
	 *Bulletin
	 */
	public Bulletin saveSubmitBulletin(Bulletin bulletin,String projProcessStatus,String checkValue,String fileVirtualPath,String completedWorkTaskType);
	
}
