package com.gpcsoft.epp.bulletin.manager;

import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.common.exception.EsException;

public interface BulletinManager extends BaseManager<Bulletin> {

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
	public Bulletin getBulletinByQueryObject(QueryObject queryObject);
	
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
	public List<Bulletin> getBulletinListByQueryObject(QueryObject queryObject);

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
	public Page<Bulletin> getBulletinListByObject(Page<Bulletin> page,QueryObject queryObject);
	
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
	public BigDecimal getBulletinTotalByQueryObject(QueryObject queryObject);
	
	/**
	 * FuncName: generatorBulletinCodeByQO
	 * Description:生成规则编号
	 * @param  String
	 * @author: shenjz
	 * @Create Date:2010-12-30  下午02:19:26
	 * @Modifier: shenjz
	 * @Modified Date:2010-12-30  下午02:19:26
	 */
	public String generatorBulletinCodeByQO(QueryObject queryObject,String projBuyMethod)throws EsException;
	
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
	public void releaseBulletin(String bulletinContent,String bulletinType,String bulletinTitle,String relUserName,String projectName,String projectCode,String ebuyMethod) throws Exception;
	
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
	public List<Bulletin> getBulletinByObjIds(String[] objIds);
	
	/**
	 * 根据公告编号获取公告对象
	 * @param bulletinNo
	 * @return 公告对象
	 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
	 */
	public Bulletin getBulletinByBulletinNo(String bulletinNo);

}
