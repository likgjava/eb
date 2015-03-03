package com.gpcsoft.epp.consign.service;
import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.consign.domain.Consign;
import com.gpcsoft.srplatform.auth.domain.User;

public interface ConsignService extends BaseGenericService<Consign> {
	
	/**
	 * FuncName:saveBatchSubmitConsign
	 * Description :  提交委托书
	 * @param   以逗号分割的id
	 * @return  void
	 * @author liangxj
	 * @Create Date: 2010-6-6下午05:52:43 
	 * @Modifier liangxj
	 * @Modified Date: 2010-6-6下午05:52:43 
	 */
	public void saveBatchSubmitConsign(String objIds,String useStatus,String confirmStatus);

	/** 
	 * FuncName:getConsignListByObject
	 * Description :  根据对象获取委托协议列表
	 * @param   page:分页对象,orgInfo:机构,confirmStatus:确认状态,consCode:委托协议标号,consName:委托协议名称,taskType:
	 * @return  Page<Consign>
	 * @author yangx
	 * @Create Date: 2010-7-13下午04:13:25 
	 * @Modifier yangx
	 * @Modified Date: 2010-7-13下午04:13:25 
	 */
	public Page<Consign> getConsignListByObject(Page<Consign> page,OrgInfo orgInfo,String confirmStatus,String consCode,String consName,String taskType);
	
	/**
	 * FuncName:removeTaskPlanBytaskPlanIdAndProjectId
	 * Description :根据项目主键和委托书主键删除申报书对象 
	 * @param   taskPlanId:申报书主键,consignId:委托协议主键
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-7-7下午03:29:53 
	 * @Modifier  liuke
	 * @Modified Date: 2010-7-7下午03:29:53 
	 */
	public void removeTaskPlanBytaskPlanIdAndProjectId(String taskPlanId,String consignId);
	
	/**
	 * FuncName:removeConsign
	 * Description :删除大宗项目申报书
	 * @param   ConsignId:委托协议主键
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-7-7下午03:29:53 
	 * @Modifier   liuke
	 * @Modified Date: 2010-7-7下午03:29:53  
	 */
	public void removeConsign(String ConsignId);
	
	/**
	 * FuncName:createConsignCodeByQO
	 * Description: 生成委托协议编号,Service层必须开启DB事物
	 * @param queryObject 为扩展参数，暂时不用
	 * @return String
	 * @author wanghz
	 * @Create Date 2010-7-15 上午09:59:44 
	 */
	@SuppressWarnings("unchecked")
	public String createConsignCodeByQO(QueryObject queryObject)throws EsException;
	
	/**
	 * FuncName:getConsignListOrCountByQuery
	 * Description: 获取委托协议List 或 记录条数
	 * @param count:记录条数,type:查询类型{data:数据,count记录条数},createUser:创建人ID,confirmStatus:确认状态,consBuyer:采购人
	 * @return Object
	 * @author wanghz
	 * @Create Date 2010-9-2 下午02:36:16 
	 */
	public Object getConsignListOrCountByQuery(String type,int count,String confirmStatus,String useStatus,User user)throws EsException;
	
	/**
	 * FuncName:getConsignListOrCountByQuery
	 * Description: 获取委托协议List 或 记录条数
	 * @param count:记录条数,type:查询类型{data:数据,count记录条数},createUser:创建人ID,confirmStatus:确认状态,consBuyer:采购人
	 * @return Object
	 * @author wanghz
	 * @Create Date 2010-9-2 下午02:36:16 
	 */
	@SuppressWarnings("unchecked")
	public Object getConsignListOrCountByQuery(QueryObject queryObject);
	
	/**
	 * FuncName:createConsign
	 * Description: 创建委托协议
	 * @param taskPlanId:申报书主键,consignId:委托协议主键
	 * @return Consign
	 * @author wanghz
	 * @Create Date 2010-9-6 下午03:53:35 
	 */
	public Consign createConsign(Consign consign,String taskPlanId,String consignId)throws Exception;
	
	/**
	 * FuncName:saveSubmitConsign
	 * Description: 提交委托协议
	 * @param taskPlanId：申报书Id;consign：委托协议对象;
	 * @return Consign
	 * @author wanghz
	 * @Create Date 2010-9-6 下午03:53:35 
	 * Modified Date: 2011-9-30上午11:43:24 by yangx
	 */
	public Consign saveSubmitConsign(List<Consign> consignList,String taskPlanId,String consignId)throws Exception;
	
	/**
	 * FuncName:saveAffirmConsign
	 * Description: 确认委托协议 支持批量  支持双向
	 * @param ids  委托协议id集，多个以逗号隔开,opinion 审核意见,flag:true pass;false no pass;
	 * @return Consign
	 * @author wanghz
	 * @Create Date 2010-9-6 下午04:55:31 
	 */
	public Consign saveAffirmConsign(String ids, String opinion,boolean flag)throws EsException;
	
	/**
	 * FuncName:saveCreateBlockTradeDraftConsign
	 * Description: 创建大宗委托协议
	 * @param consign:委托协议,taskPlanIds:申报书主键
	 * @return Consign
	 * @author wanghz
	 * @Create Date 2010-9-7 上午11:56:17 
	 */
	public Consign saveCreateBlockTradeDraftConsign(Consign consign ,String taskPlanIds)throws EsException;
	
	/**
	 * FuncName:saveSubmitBlockTradeDraftConsign
	 * Description: 提交大宗委托协议
	 * @param consign:委托协议对象,taskPlanIds:申报书主键
	 * @return Consign
	 * @author wanghz
	 * @Create Date 2010-9-7 上午11:56:17  
	 */
	public Consign saveSubmitBlockTradeDraftConsign(Consign consign ,String taskPlanIds)throws EsException;
	
	/**
	 * FuncName:updateBlockTradeDraftConsign
	 * Description: 修改大宗委托协议
	 * @param consign:委托协议对象
	 * @return Consign
	 * @author wanghz
	 * @Create Date 2010-9-7 上午11:56:17 
	 */
	public Consign updateBlockTradeDraftConsign(Consign consign)throws EsException;
	
	/**
	 * FuncName:removeNotBlockConsign
	 * Description : 删除非大宗委托协议 
	 * @param   consignId:委托协议主键
	 * @return void
	 * @author liuke  
	 * @Create Date: 2010-9-17上午10:43:31 
	 * @Modifier liuke
	 * @Modified Date: 2010-9-17上午10:43:31 
	 */
	public void removeNotBlockConsign(String consignId)throws EsException;
	
	/**
	 * FuncName: getQuery
	 * Description : 用于查询与当前登录人相匹配的委托协议列表的前置条件
	 * @param query:组装的用于数据查询的对象, user:当前登录用户,findMethod:用以判断当前登录角色
	 * @return QueryObject
	 * @author: shenjz
	 * @Create Date:2010-12-20  下午03:31:25
	 * @Modifier: shenjz
	 * @Modified Date:2010-12-20  下午03:31:25
	 */
	public QueryObject getQueryObject(QueryObject query,User user,String findMethod);
	
	/**
	 * 
	 * FuncName: getConsignByObjId
	 * Description :  根据主键得到委托协议
	 * @param 
	 * @param objId
	 * @return Consign
	 * @author: liuke
	 * @Create Date:2010-12-30  下午02:14:00
	 * @Modifier: liuke
	 * @Modified Date:2010-12-30  下午02:14:00
	 */
	public Consign getConsignByObjId(String objId);
	
	
	/**
	 * 
	 * FuncName: saveConsign
	 * Description : 保存委托协议 
	 * @param 
	 * @param consign void
	 * @author: liuke
	 * @Create Date:2010-12-30  下午03:15:38
	 * @Modifier: liuke
	 * @Modified Date:2010-12-30  下午03:15:38
	 */
	public void saveConsign(Consign consign);
	
	/**
	 * FuncName:saveSubmitConsign
	 * Description: 提交委托协议
	 * @param taskPlanId：申报书Id;consign：委托协议对象;
	 * @return Consign
	 * @author wanghz
	 * @Create Date 2010-9-6 下午03:53:35 
	 */
	public Consign saveSubmitConsign(Consign consign,String taskPlanId,String consignId)throws Exception;
	
}
