package com.gpcsoft.goods.goodsclass.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.BeanUtils;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassParamTypeDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParamType;
import com.gpcsoft.goods.goodsclass.manager.GoodsClassManager;
import com.gpcsoft.goods.goodsclass.manager.GoodsClassParamTypeManager;
import com.gpcsoft.goods.goodsclass.service.GoodsClassParamTypeService;

@Service 
public class GoodsClassParamTypeServiceImpl extends BaseGenericServiceImpl<GoodsClassParamType> implements GoodsClassParamTypeService {

	@Autowired(required=true) @Qualifier("goodsClassManagerImpl")
	private GoodsClassManager goodsClassManager;
	@Autowired(required=true) @Qualifier("goodsClassParamTypeManagerImpl")
	private GoodsClassParamTypeManager goodsClassParamTypeManager;
	@Autowired(required=true)  @Qualifier("goodsClassParamTypeDaoHibernate")
	private GoodsClassParamTypeDao goodsClassParamTypeDaoHibernate;
	

	
	/* (non-Javadoc)
	 * @see com.gpcsoft.goods.goodsclass.service.GoodsClassParamTypeService#saveGoodsClassParamType(com.gpcsoft.goods.goodsclass.domain.GoodsClassParamType)
	 */
	public GoodsClassParamType saveGoodsClassParamType(
			GoodsClassParamType goodsClassParamType) throws Exception {

		// 如果是新增，则初始该商品参数分类为叶子结点
		if(null == goodsClassParamType.getObjId() || "".equals(goodsClassParamType.getObjId())) {
			goodsClassParamType.setIsLeaf(true);
			goodsClassParamType.setObjId(null);
			
			// 取得所有父结点为null的最大的排序号加1做为新的编号
			int sort = goodsClassParamTypeDaoHibernate.getgoodsClassParamTypeMaxSort(null) + 1;
			
			// 排序号
			goodsClassParamType.setSort(sort);
			GoodsClass goodsClass = goodsClassManager.get(goodsClassParamType.getGoodsClass().getObjId());
			// 编号
			goodsClassParamType.setParamCode("T" + goodsClass.getGoodsClassCode() +((sort +"").length() > 1 ? sort + "" : "0" + sort));
			goodsClassParamType = goodsClassParamTypeManager.save(goodsClassParamType);
		}else {
			
			GoodsClassParamType obj = goodsClassParamTypeManager.get(goodsClassParamType.getObjId());
			BeanUtils.copyPropertiesFilterEmpty(obj, goodsClassParamType);
		}
		
		// 保存是将父资质改为非叶子结点
		if(null != goodsClassParamType.getParent()) {
			goodsClassParamTypeDaoHibernate.updateGoodsClassParamTypeIsLeaf(goodsClassParamType.getParent().getObjId(), false);
		}
		return goodsClassParamType;
	}
	/* (non-Javadoc)
	 * @see com.gpcsoft.goods.goodsclass.service.GoodsClassParamTypeService#updateSort(java.lang.String, java.lang.String)
	 */
	public void updateSort(String sourceObjId, String targetObjId) {
		GoodsClassParamType sourcegoodsClassParamType = goodsClassParamTypeManager.get(sourceObjId);
		GoodsClassParamType targetgoodsClassParamType = goodsClassParamTypeManager.get(targetObjId);
		Integer tempSortInteger = sourcegoodsClassParamType.getSort();
		sourcegoodsClassParamType.setSort(targetgoodsClassParamType.getSort());
		targetgoodsClassParamType.setSort(tempSortInteger);
	}
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.goods.goodsclass.service.GoodsClassParamTypeService#removeGoodsClassParamType(java.lang.String)
	 */
	public void removeGoodsClassParamType(String objId) {
		
		goodsClassParamTypeDaoHibernate.remove(objId, GoodsClassParamType.class);
	}
	
	/** 
     * Description : 根据商品分类获取分类参数信息  
     * Create Date: 2010-8-4下午05:07:29 by sunl  Modified Date: 2010-8-4下午05:07:29 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    public List<GoodsClassParamType> getGoodsClassParamByClass(String classId) throws Exception{
    	return goodsClassParamTypeDaoHibernate.getGoodsClassParamByClass(classId);
    }
    
    /** 
	 * Description : 判断在同一商品分类下的商品参数在父节点下是不是唯一  
	 * Create Date: 2010-11-25上午10:23:13 by guoyr  Modified Date: 2010-11-25上午10:23:13 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isUnique(String goodsClassId, String paramName, String objId, String parentId ){
		return goodsClassParamTypeDaoHibernate.isUnique(goodsClassId, paramName, objId, parentId);
	}
	
	/** 
	 * Description :  批量导入商品分类参数
	 * Create Date: 2011-5-27下午04:55:07 by likg  Modified Date: 2011-5-27下午04:55:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveGoodsClassParamTypeBatch(Map<String, Object> param) throws Exception {
		goodsClassParamTypeDaoHibernate.saveGoodsClassParamTypeBatch(param);
	}
}
