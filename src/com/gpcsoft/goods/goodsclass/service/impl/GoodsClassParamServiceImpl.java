package com.gpcsoft.goods.goodsclass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.BeanUtils;
import com.gpcsoft.goods.exception.GoodsClassParamException;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassParamDao;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassParamTypeDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParam;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParamType;
import com.gpcsoft.goods.goodsclass.enumeration.GoodsClassParamEnum;
import com.gpcsoft.goods.goodsclass.manager.GoodsClassParamManager;
import com.gpcsoft.goods.goodsclass.manager.GoodsClassParamTypeManager;
import com.gpcsoft.goods.goodsclass.service.GoodsClassParamService;

@Service 
public class GoodsClassParamServiceImpl extends BaseGenericServiceImpl<GoodsClassParam> implements GoodsClassParamService {

	@Autowired(required=true) @Qualifier("goodsClassParamTypeManagerImpl")
	private GoodsClassParamTypeManager goodsClassParamTypeManager;
	@Autowired(required=true) @Qualifier("goodsClassParamManagerImpl")
	private GoodsClassParamManager goodsClassParamManager;
	@Autowired(required=true)  @Qualifier("goodsClassParamTypeDaoHibernate")
	private GoodsClassParamTypeDao goodsClassParamTypeDaoHibernate;
	@Autowired(required=true)  @Qualifier("goodsClassParamDaoHibernate")
	private GoodsClassParamDao goodsClassParamDaoHibernate;

	/* (non-Javadoc)
	 * @see com.gpcsoft.goods.goodsclass.service.GoodsClassParamService#saveGoodsClassParam(com.gpcsoft.goods.goodsclass.domain.GoodsClassParam)
	 */
	public GoodsClassParam saveGoodsClassParam(GoodsClassParam goodsClassParam)
			throws Exception {
		// 如果是新增，则初始该商品参数分类为叶子结点
		if(null == goodsClassParam.getObjId() || "".equals(goodsClassParam.getObjId())) {
			goodsClassParam.setIsLeaf(true);
			goodsClassParam.setObjId(null);// 避免objId为空时保存失败
			// 取得当前父结点下的所有子节点的最大的排序号加1做为新的结点和编号
			int sort = goodsClassParamTypeDaoHibernate.getgoodsClassParamTypeMaxSort(goodsClassParam.getParent().getObjId()) + 1;
			
			// 排序号
			goodsClassParam.setSort(sort);
			
			// 查询父结点为当前新增的商品参数设置编号
			GoodsClassParamType parent = goodsClassParamTypeManager.get(goodsClassParam.getParent().getObjId());
			
			// 将父结点编号T为P
			goodsClassParam.setParamCode(parent.getParamCode().replaceFirst("T", "P") + ((sort + "").length() > 1 ? sort + "" : "0" + sort));
			
			// 设置path
			if(parent.getPath() == null) {
				goodsClassParam.setPath(parent.getObjId() + "#");
			} else {
				goodsClassParam.setPath(parent.getPath() + parent.getObjId() + "#");
			}
			
			goodsClassParam = goodsClassParamManager.save(goodsClassParam);
		}else {
			
			GoodsClassParam obj = goodsClassParamManager.get(goodsClassParam.getObjId());
			BeanUtils.copyPropertiesFilterEmpty(obj, goodsClassParam);
		}
		
		// 保存时将父节点改为非叶子结点
		if(null != goodsClassParam.getParent()) {
			goodsClassParamTypeDaoHibernate.updateGoodsClassParamTypeIsLeaf(goodsClassParam.getParent().getObjId(), false);
		}
		return goodsClassParam;
	}
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.goods.goodsclass.service.GoodsClassParamService#removeGoodsClassParam(java.lang.String)
	 */
	public void removeGoodsClassParam(String objId) {
		
		// 如果该参数被GoodsParam引用，则不充许删除，并抛出异常
		if(goodsClassParamDaoHibernate.getGoodsClassParamCountFromGoodsParam(objId) > 0) {
			throw new GoodsClassParamException(messageSource.getMessage(GoodsClassParamEnum.GOODS_CLASS_PARAM_CAN_NOT_REMOVE));
		} else {
			
			// 删除时如果该结果的父结点下已经只有1个子结点，则将父节点改为叶子结点
			if(goodsClassParamTypeDaoHibernate.getSubGoodsClassParamTypeCount(objId) <= 1) {
				goodsClassParamTypeDaoHibernate.updateGoodsClassParamTypeIsLeaf(goodsClassParamTypeDaoHibernate.get(objId).getParent().getObjId(), true);
			}
			
			goodsClassParamDaoHibernate.remove(objId, GoodsClassParam.class);
		}
	}
}
