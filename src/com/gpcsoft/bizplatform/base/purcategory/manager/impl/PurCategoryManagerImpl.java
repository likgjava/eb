package com.gpcsoft.bizplatform.base.purcategory.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.purcategory.dao.PurCategoryDao;
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.base.purcategory.manager.PurCategoryManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.StringUtils;

@Repository
public class PurCategoryManagerImpl extends BaseManagerImpl<PurCategory> implements PurCategoryManager {

	@Autowired(required=true)  @Qualifier("purCategoryDaoHibernate")
	private PurCategoryDao purCategoryDaoHibernate;
	
	/**
     * 
     * Description :  获取采购品目
     * Create Date: 2010-1-18上午09:43:38 by xiaogh  Modified Date: 2010-1-18上午09:43:38 by xiaogh
     * @param   orderName:排序字段	isAsc:排序方式
     * @return  List<PurCategory>
     * @Exception
     */
	@SuppressWarnings("unchecked")
	public List<PurCategory> getPurCategory(String orderName, boolean isAsc){
		String query = "from PurCategory where objId > :c";		
		if(StringUtils.hasLength(orderName)) {
			query += " order by " + orderName + (isAsc ? " asc" : " desc");
		}
		return purCategoryDaoHibernate.getHibernateCacheTemplate().findByNamedParam(query,"c","0");
	}

	/** 
	 * Description :  首页需要的采购品目3级
	 * Create Date: 2010-7-15下午02:59:17 by liangxj  Modified Date: 2010-7-15下午02:59:17 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> findPurCategoryForIndex(String nameFirstSpell) {
		return purCategoryDaoHibernate.findPurCategoryForIndex(nameFirstSpell);
	}
	
    /**
     * Description : 获得该父类下面的子类的下一个最大的编号。
     * Create Date: May 6, 2010 10:11:03 AM by liujf  Modified Date: May 6, 2010 10:11:03 AM by yucy
     * @param   
     * @return  
     * @Exception
     */
	public String getNextChildKeyCode(String firstCode, String parentId, String domian, String parentPropertyName, String targetPropertyName) throws Exception {
		String maxKeyCode = purCategoryDaoHibernate.getMaxChildKeyCodeByHQl(parentId, domian, parentPropertyName, targetPropertyName);
		if (parentId == null||"".equals(parentId)) parentId = firstCode;
		if (maxKeyCode == null||"".equals(maxKeyCode)) maxKeyCode = parentId + "00";
		
		String nextKeyCode = null;
		if(maxKeyCode.length()==1){
			int offset=(int)'B'-(int)'A';
			int v=maxKeyCode.charAt(0); 
			char lowercase=(char)(v+offset);
			nextKeyCode = ""+lowercase;
		}else{
			nextKeyCode = maxKeyCode.substring(maxKeyCode.length() - 2, maxKeyCode.length());
			Integer code = new Integer(nextKeyCode);
			code = code + 1;  
			nextKeyCode = parentId + StringUtils.constructCode(code, 2);
		}
		return nextKeyCode;
	}

	/** 
	 * Description :  获取采购品目的最新更新时间
	 * Create Date: 2011-5-13上午11:45:39 by likg  Modified Date: 2011-5-13上午11:45:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Date getLastUpdateTime() throws Exception {
		return purCategoryDaoHibernate.getLastUpdateTime();
	}

	/** 
	 * Description :  根据品目code获取品目
	 * Create Date: 2011-12-9下午03:07:20 by yucy  Modified Date: 2011-12-9下午03:07:20 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public PurCategory getPurCategoryByCategoryCode(String categoryCode) throws Exception {
		return purCategoryDaoHibernate.getPurCategoryByCategoryCode(categoryCode);
	}
}
