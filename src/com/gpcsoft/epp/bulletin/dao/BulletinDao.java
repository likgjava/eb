package com.gpcsoft.epp.bulletin.dao;

import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.project.domain.Project;

public interface BulletinDao extends BaseGenericDao<Bulletin> {
	
	/** 
	 * FuncName:getBulletinListByObject
	 * Description:根据对象获取公告列表
	 * @param   page:分页对象,queryObject:组装的查询对象数据
	 * @return  Page<Bulletin>
	 * @author yangx
	 * @Create Date: 2010-7-8下午01:56:21 
	 * @Modifier  yangx
	 * @Modified Date: 2010-7-8下午01:56:21 
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
	 * FuncName:getBulletinTotalByQueryObject
	 * Description:根据查询条件统计对应的公告数据
	 * @param queryObject[buyerId:采购人ID;bullType:公告类型;auditStatus:审核状态;monitorId:监管人]
	 * @return BigDecimal
	 * @author Administrator
	 * @Create Date: 2010-7-7下午01:56:46 
	 * @Modifier Administrator
	 * @Modified Date: 2010-7-7下午01:56:46 
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal getBulletinTotalByQueryObject(QueryObject queryObject);
	
	/** 
	 * FuncName:getBulletinByTypeAndAuditStatus
	 * Description:根据公告类型查询审核通过且没有发布过的公告
	 * @param   bullType:公告类型
	 * @return List<Bulletin>
	 * @author yangx
	 * @Create Date: 2010-8-24下午03:12:50 
	 * @Modifier   yangx
	 * @Modified Date: 2010-8-24下午03:12:50 by   
	 */
	public List<Bulletin> getBulletinByTypeAndAuditStatus(String bullType);

	/** 
	 * FuncName:getBulletinListByObjIds
	 * Description :  根据公告Ids获取公告对象
	 * Create Date: 2011-1-15下午12:39:57 by yangx  Modified Date: 2011-1-15下午12:39:57 by yangx
	 * @param   bulletinIds:公告Ids
	 * @return  List<Bulletin>
	 * @Exception   
	 */
	public List<Bulletin> getBulletinListByObjIds(String bulletinIds);
	
	/**
	 * FuncName: getProjectByBulletinId
	 * Description:根据公告Id查询项目
	 * @param   bulletinId:公告主键
	 * @return  Project
	 * @author yangx
	 * @Create Date: 2010-8-24下午05:30:16    
	 * @Modifier yangx
	 * @Modified Date: 2010-8-24下午05:30:16     
	 */
	public Project getProjectByBulletinId(String bulletinId);
	
	/** 
	 * FuncName:getBulletinByObjIds
	 * Description:根据日志Id查询公告
	 * @param   objIds:日志主键
	 * @return  List<Bulletin>
	 * @author yangx  
	 * @Create Date: 2010-9-15上午11:00:18 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-15上午11:00:18 
	 */
	public List<Bulletin> getBulletinByObjIds(String objIds);
	
	/**
	 * Funcname:getBulletinByBulletinNo
	 * Description:根据公告编号获取公告对象
	 * @param bulletinNo
	 * @return 公告对象
	 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
	 */
	public Bulletin getBulletinByBulletinNo(String bulletinNo);
	
	
	
	
	/**
	 * FuncName: getBulletinListByObject
	 * Description :  根据queryObject对象查询公告列表
	 * @param 
	 * @param queryObject
	 * @return List<Bulletin>
	 * @author: liuke
	 * @Create Date:2011-10-11  上午11:27:35
	 * @Modifier: liuke
	 * @Modified Date:2011-10-11  上午11:27:35
	 */
	public List<Bulletin> getBulletinListByObject(QueryObject queryObject);
}
