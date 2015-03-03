package com.gpcsoft.goods.goods.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.GoodsOptionalFitting;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

public interface GoodsDao extends BaseGenericDao<Goods> {
	
	/** 
	 * Description :  商品列表
	 * 				  查询条件为：指定维护商的维护商品
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Goods> listGoods(Page<Goods> page,Map<String,Object> param) throws Exception;
	
	/** 
	 * Description :  商品唯一性校验
	 * 				  1 同分类、同品牌、有效的商品名称不能重复
	 * 				  2 同分类、同品牌、有效的商品型号不能重复
	 * Create Date: 2011-5-6下午06:50:51 by sunl  Modified Date: 2011-5-6下午06:50:51 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer checkGoodsUnique(Map<String,Object> param) throws Exception;
	
	/** 
	 * Description :  根据参数获得商品的展示信息列表，及时加载商品的品牌信息、分类信息、参数集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含商品分类信息、商品品牌信息、排序信息
	 * @return  
	 * @Exception   
	 */
	public Page<Goods> getGoodsListForShow(Page<Goods> page,Map<String, Object> paramsMap)throws Exception;
	
	/** 
	 * Description :  根据主键，获得商品的详细信息，包括基本信息、扩展信息、图片、参数等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param
	 * @return  
	 * @Exception   
	 */
	public Goods getGoodsAllInfo(String objId) throws Exception;
	
	/** 
	 * Description :  根据主键，获得商品的详细信息，包括基本信息、扩展信息、图片、参数等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param objIds以逗号分隔
	 * @return  
	 * @Exception   
	 */
	public List<Goods> getGoodsAllInfoList(String objIds) throws Exception;
	
	
	/** 
	 * Description :  根据商品分类获得下级商品分类的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   goodsClassId 商品分类id，goodsClassCode 商品分类编号, isLeaf 是否只取叶子节点
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getClassListShowByClass(String goodsClassId,String goodsClassCode,Boolean isLeaf,String keyWord) throws Exception;
	
	/** 
	 * Description :  根据商品分类获得商品品牌的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   classId 分类id，classCode 分类编号
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getBrandsListShowByClass(final String classId,final String classCode,String keyWord) throws Exception;
	
	/** 
	 * Description :  根据品目获得商品品牌的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   categoryId 品目id，categoryCode 品目编号
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getBrandListShowByCategory(String categoryId,String categoryCode) throws Exception;
	
	/** 
	 * Description :  查询商品历史
	 * Create Date: 2010-8-6下午06:57:32 by sunl  Modified Date: 2010-8-6下午06:57:32 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Goods> getGoodsHistory(Map<String,Object> param) throws Exception;
	
	/** 
     * Description : 获得商品参数分类和商品参数值信息
     * Create Date: 2010-8-4下午05:07:29 by sunl  Modified Date: 2010-8-4下午05:07:29 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    public List<Object> getGoodsParamAndClassParam(String goodsId,String classId) throws Exception;
    
    /** 
     * Description : 此方法在
     * 				 1 商品删除之后触发（删除变更商品之后，需要将objId为）
     *  			 2 保存变更商品之后触发（将所变更的商品currentId更新为新商品的objId）
     * Create Date: 2010-8-4下午05:07:29 by sunl  Modified Date: 2010-8-4下午05:07:29 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    public Integer updateGoodsCurrentId(String objId, String currentId, String type) throws Exception;
    
    
    /**
	 * Description :  根据商品Id获取商品选配信息
	 * Create Date: 2010-8-11下午02:41:57 by sunl  Modified Date: 2010-8-11下午02:41:57 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<GoodsOptionalFitting> getOptionFittingByGoods(String goodsId) throws Exception;
    
    /** 
	 * Description :  禁卖商品
	 * Create Date: 2010-8-10下午03:55:09 by sunl  Modified Date: 2010-8-10下午03:55:09 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer disableGoods(String objIds) throws Exception;
	
	/** 
	 * Description :  启卖商品
	 * Create Date: 2010-8-10下午03:55:09 by sunl  Modified Date: 2010-8-10下午03:55:09 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer enableGoods(String objIds) throws Exception;
	
	/** 
	 * Description :  报废商品
	 * Create Date: 2010-8-10下午03:55:09 by sunl  Modified Date: 2010-8-10下午03:55:09 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer scrappedGoods(String objIds) throws Exception;
	
	/** 
     * Description : 根据当前用户组织机构ID获取所能维护的商品分类
     * Create Date: 2010-8-10上午11:13:50 by sunl  Modified Date: 2010-8-10上午11:13:50 by sunl
     * @param   
     * @return  
     * @Exception   
     */
	public List<GoodsClass> getGoodsClassByOrg(String orgInfoId) throws Exception;
	
	/** 
	 * Description :  数据迁移
	 * Create Date: 2010-9-2下午04:10:45 by sunl  Modified Date: 2010-9-2下午04:10:45 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer update_goods_transfer(final String spec,final String goodsId) throws Exception;
	
	/** 
	 * Description :   获取商品展示的列表数据(过滤供应商的 投标范围及类别)
	 * Create Date: 2009-4-13上午11:04:42 by yucy  Modified Date: 2009-4-13上午11:04:42 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Goods> getGoodsListBySupplierBidRange(Page<Goods> page,Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  按关键字获得商品类目
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getProductsBySearchName(Map<String,Object> params) throws Exception;
}
