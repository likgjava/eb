package com.gpcsoft.goods.goods.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.BeanUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.dao.GoodsDao;
import com.gpcsoft.goods.goods.dao.GoodsParamDao;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.GoodsParam;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.goods.goods.manager.GoodsManager;
import com.gpcsoft.goods.goods.service.GoodsService;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassParamDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParam;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.utils.SequenceNumberUtil;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class GoodsServiceImpl extends BaseGenericServiceImpl<Goods> implements GoodsService {

	@Autowired(required=true) @Qualifier("goodsManagerImpl")
	private GoodsManager goodsManager;
	@Autowired(required=true) @Qualifier("goodsDaoHibernate")
	private GoodsDao goodsDaoHibernate;
	@Autowired(required=true) @Qualifier("goodsParamDaoHibernate")
	private GoodsParamDao goodsParamDaoHibernate;
	@Autowired(required=true) @Qualifier("goodsClassParamDaoHibernate")
	private GoodsClassParamDao goodsClassParamDaoHibernate;
	
	/** 
     * Description : 商品删除，接收逗号分隔的objId字串可进行批量删除
     * 				 如果是变更商品(currentId不为空)，则删除之后更新currentId
     * Create Date: 2010-8-10上午11:13:50 by sunl  Modified Date: 2010-8-10上午11:13:50 by sunl
     * @param   
     * @return  
     * @Exception   
     */
	public void removeGoods(String objIds) throws Exception{
		String objId[] = objIds.split(",");
		this.beforeRemove(objId);
		goodsDaoHibernate.remove(objId, Goods.class);
	}
	
	/** 
	 * Description :  商品唯一性校验
	 * 				  1 同分类、同品牌、有效的、商品名称不能重复
	 * 				  2 同分类、同品牌、有效的、商品型号不能重复
	 * Create Date: 2011-5-6下午06:50:51 by sunl  Modified Date: 2011-5-6下午06:50:51 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String,Object> checkGoodsUnique(Map<String,Object> param) throws Exception {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		Integer res = goodsDaoHibernate.checkGoodsUnique(param);
		
		if(res > 0){
			result.put(Constants.SUCCESS,true);
			if(StringUtils.hasLength((String)param.get("productName")) && StringUtils.hasLength((String)param.get("productCode"))) {
				result.put(Constants.JSON_RESULT, "同类别和品牌下已存在相同名称或型号的商品");
			} else if(StringUtils.hasLength((String)param.get("productName"))) {
				result.put(Constants.JSON_RESULT, "同类别和品牌下已存在相同名称的商品");
			} else if(StringUtils.hasLength((String)param.get("productCode"))){
				result.put(Constants.JSON_RESULT, "同类别和品牌下已存在同样的型号");
			}
		} else {
			result.put(Constants.FAILURE,true);
		}
		
		return result;
	}
	
	/** 
     * Description : 如果是变更商品(currentId不为空)，则删除之后更新currentId
     * Create Date: 2010-8-20上午11:13:50 by sunl  Modified Date: 2010-8-20上午11:13:50 by sunl
     */
	protected void beforeRemove(String objId[]) throws Exception {
		String currentIds = "";
		for(int i=0; i<objId.length; i++){
			Goods goods = (Goods)goodsManager.get(objId[i]);
			//判断是否是变更商品
			if(StringUtils.hasLength(goods.getCurrentId()) && GoodsEnum.USE_TEMP.equals(goods.getUseStatus())){
				currentIds += goods.getCurrentId() + ",";
			}
		}
		if(!currentIds.equals("")){
			currentIds = currentIds.substring(0,currentIds.length() - 1);
			goodsDaoHibernate.updateGoodsCurrentId("",currentIds,"remove");
		}
	}
	
	/** 
	 * Description :  禁卖商品
	 * Create Date: 2010-8-10下午03:55:09 by sunl  Modified Date: 2010-8-10下午03:55:09 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void disableGoods(String objIds) throws Exception {
		goodsDaoHibernate.disableGoods(objIds);
	}
	
	/** 
	 * Description :  启卖商品
	 * Create Date: 2010-8-10下午03:55:09 by sunl  Modified Date: 2010-8-10下午03:55:09 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void enableGoods(String objIds) throws Exception {
		goodsDaoHibernate.enableGoods(objIds);
	}
	
	/** 
	 * Description :  报废商品
	 * Create Date: 2010-8-10下午03:55:09 by sunl  Modified Date: 2010-8-10下午03:55:09 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void scrappedGoods(String objIds) throws Exception {
		goodsDaoHibernate.scrappedGoods(objIds);
	}
	
	/** 
	 * Description :  根据参数获得商品的展示信息列表，及时加载商品的品牌信息、分类信息、参数集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含商品分类信息、商品品牌信息、排序信息
	 * @return  
	 * @Exception   
	 */
	public Page<Goods> getGoodsListForShow(Page<Goods> page,Map<String, Object> paramsMap) throws Exception{
		return goodsDaoHibernate.getGoodsListForShow(page,paramsMap);
	}
	
	/** 
	 * Description :  根据主键，获得商品的详细信息，包括基本信息、扩展信息、图片、参数等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Goods getGoodsAllInfo(String objId) throws Exception {
		return goodsDaoHibernate.getGoodsAllInfo(objId);
	}
	
	/** 
	 * Description :  根据主键，获得商品的详细信息，包括基本信息、扩展信息、图片、参数等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param objIds以逗号分隔
	 * @return  
	 * @Exception   
	 */
	public List<Goods> getGoodsAllInfoList(String objIds) throws Exception {
		return goodsDaoHibernate.getGoodsAllInfoList(objIds);
	}
	
	/** 
	 * Description :  根据商品分类获得下级商品分类的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   goodsClassId 商品分类id，goodsClassCode 商品分类编号, isLeaf 是否只取叶子节点
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getClassListShowByClass(String goodsClassId,String goodsClassCode,Boolean isLeaf,String keyWord) throws Exception {
		return goodsDaoHibernate.getClassListShowByClass(goodsClassId, goodsClassCode, isLeaf,keyWord);
	}

	/** 
	 * Description :  根据品目获得商品品牌的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   categoryId 品目id，categoryCode 品目编号
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getBrandListShowByCategory(String categoryId,String categoryCode) throws Exception {
		return goodsDaoHibernate.getBrandListShowByCategory(categoryId, categoryCode);
	}

	/** 
	 * Description :  根据商品分类获得商品品牌的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   classId 分类id，classCode 分类编号
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getBrandsListShowByClass(String classId,String classCode,String keyWord) throws Exception {
		return goodsDaoHibernate.getBrandsListShowByClass(classId, classCode,keyWord);
	}
	
	/** 
	 * Description :  查询商品历史
	 * Create Date: 2010-8-6下午06:57:32 by sunl  Modified Date: 2010-8-6下午06:57:32 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Goods> getGoodsHistory(Map<String,Object> param) throws Exception {
		return goodsDaoHibernate.getGoodsHistory(param);
	}
	
	/** 
     * Description : 获得商品参数分类和商品参数值信息
     * Create Date: 2010-8-4下午05:07:29 by sunl  Modified Date: 2010-8-4下午05:07:29 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    public List<Object> getGoodsParamAndClassParam(String goodsId,String classId) throws Exception {
    	return goodsDaoHibernate.getGoodsParamAndClassParam(goodsId,classId);
    }
    
	/** 
	 * Description :  审核商品（新增商品的审核）
	 * 			      如果审核通过,将currentId置为空,更新发布时间为当前时间
	 * Create Date: 2010-8-4下午04:31:42 by sunl  Modified Date: 2010-8-4下午04:31:42 by sunl
	 * @param 
	 * @return  
	 * @Exception   
	 */
	public void auditGoods(Goods goods) throws Exception {
		Goods goodsObj = goodsManager.get(goods.getObjId());
		goodsObj.setAuditStatus(goods.getAuditStatus());
		//审核意见,时间,人
		goodsObj.setOpinion(goods.getOpinion());
		preVerify(goodsObj);
		//审核通过
		if(goods.getAuditStatus().equals(GoodsEnum.PASS_EXAM)){
			goodsObj.setUseStatus(GoodsEnum.USE_VALID);//有效
			goodsObj.setValidTime(new Date());//生效时间
			goodsObj.setProductDateIssued(new Date());//发布时间
			goodsObj.setCurrentId(null);
		}
	}
	
	/** 
	 * Description :  商品列表
	 * 				  查询条件为：指定维护商的维护商品
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Goods> listGoods(Page<Goods> page,Map<String,Object> param) throws Exception {
		return goodsDaoHibernate.listGoods(page, param);
	}
	
	/** 
     * Description : 根据当前用户组织机构ID获取所能维护的商品分类
     * Create Date: 2010-8-10上午11:13:50 by sunl  Modified Date: 2010-8-10上午11:13:50 by sunl
     * @param   
     * @return  
     * @Exception   
     */
	public List<GoodsClass> getGoodsClassByOrg(String orgInfoId) throws Exception {
		return goodsDaoHibernate.getGoodsClassByOrg(orgInfoId);
	}

	/** 
     * Description : 数据迁移
     * Create Date: 2010-8-10上午11:13:50 by sunl  Modified Date: 2010-8-10上午11:13:50 by sunl
     * @param   
     * @return  
     * @Exception   
     */
	public void update_goods_transfer(String goodsId) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("from Goods t left join fetch t.goodsParamSet as gp ");
		hql.append(" left join fetch gp.goodsClassParam as cp ");
		hql.append(" where t.paramInputType = '01' ");
		if(StringUtils.hasLength(goodsId)) {
			hql.append(" and t.objId = '").append(goodsId).append("'");
		}
		List<Goods> res = goodsDaoHibernate.findbyHql(hql.toString());//查询分项录入的商品参数
		Goods goods = res.get(0);
		//for (Goods goods : res) {
			String temp = "";
			Set<GoodsParam> gpSet = goods.getGoodsParamSet();
			if(gpSet.size()>0){
				for (GoodsParam goodsParam : gpSet) {
					if(null!=goodsParam){
						GoodsClassParam t = goodsClassParamDaoHibernate.get(goodsParam.getGoodsClassParam().getObjId());
						if(!t.getIsLeaf()) {
							temp += goodsParam.getTypeName()+"：";
							List<GoodsParam> paramList = goodsParamDaoHibernate.findbyHql("from GoodsParam t left join fetch t.goodsClassParam where t.goods.objId=? and t.goodsClassParam.parent.objId=?",goods.getObjId(),goodsParam.getGoodsClassParam().getObjId());
							for (GoodsParam goodsParam2 : paramList) {
								if(StringUtils.hasLength(goodsParam2.getParamValue())) {
									temp += goodsParam2.getParamName()+"-"+goodsParam2.getParamValue()+"，";
								}
							}
							if(temp.endsWith("，")) {
								temp = temp.substring(0,temp.length()-1);
							}
							temp += "；";
						}
					}
				}
			}
			if(!temp.equals("")) {
				goodsDaoHibernate.update_goods_transfer(temp, goods.getObjId());
			}
		//}
		
	}
	
	/** 
	 * Description :   获取商品展示的列表数据(过滤供应商的 投标范围及类别)
	 * Create Date: 2009-4-13上午11:04:42 by yucy  Modified Date: 2009-4-13上午11:04:42 by yucy
	 * @param   
	 * @return                       
	 * @Exception   
	 */
	public Page<Goods> getGoodsListBySupplierBidRange(Page<Goods> page,Map<String, Object> param) throws Exception {
		return goodsDaoHibernate.getGoodsListBySupplierBidRange(page,param);
	}
	
	/** 
	 * Description :  按关键字获得商品类目
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getProductsBySearchName(Map<String,Object> params) throws Exception {
		return goodsDaoHibernate.getProductsBySearchName(params);
	}
	
	/** 
	 * Description :  创建商品
	 * Create Date: 2011-5-6下午02:22:46 by sunl  Modified Date: 2011-5-6下午02:22:46 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Goods createGoods(Goods goods) throws Exception {
		User user = AuthenticationHelper.getCurrentUser(true);
		
		goods.setCreateUser(user);
		goods.setCreateTime(new Date());
		goods.setProductDateIssued(new Date());//发布日期
		goods.setSellStatus(GoodsEnum.SELL_START);//默认起卖状态
		goods.setUseStatus(GoodsEnum.USE_TEMP);//默认临时状态
		
		Set<GoodsParam> goodsParamSet = goods.getGoodsParamSet();
		for (GoodsParam goodsParam : goodsParamSet) {
			//处理jsonBean转换时objId为空值的情况
			if(goodsParam != null) {
				if(goodsParam.getObjId() != null && "".equals(goodsParam.getObjId())){
					goodsParam.setObjId(null);
					goodsParam.setCreateUser(user);
					goodsParam.setCreateTime(new Date());
				}else if(StringUtils.hasLength(goodsParam.getObjId())){
					goodsParam.setModifier(user);
					goodsParam.setModifyTime(new Date());
				}
			}
		}
		
		//保存为正式
		if(GoodsEnum.PASS_EXAM.equals(goods.getAuditStatus())) {
			goods.setUseStatus(GoodsEnum.USE_VALID);//有效
			goods.setAuditStatus(GoodsEnum.PASS_EXAM);//通过
			goods.setValidTime(new Date());//生效时间
			goods.setProductDateIssued(new Date());//发布时间
		}

		//商品编号
		goods.setGoodsCode(SequenceNumberUtil.getGoodsSN(goods.getGoodsClass().getObjId()));
		
		goodsManager.save(goods);
		
		//同步商品参数分类和参数值到spec字段中
		if(null != goodsParamSet && goodsParamSet.size() > 0) {
			this.update_goods_transfer(goods.getObjId());
		}
		
		return goods;
	}
	
	/** 
	 * Description :  修改商品
	 * Create Date: 2011-5-6下午02:22:46 by sunl  Modified Date: 2011-5-6下午02:22:46 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Goods updateGoods(Goods goods) throws Exception {
		User user = AuthenticationHelper.getCurrentUser(true);
		
		//获取修改前商品对象
		Goods oldGoods = goodsManager.get(goods.getObjId());
		
		//获得商品参数，并处理jsonBean转换时objId为空值“”的情况
		Set<GoodsParam> goodsParamSet = goods.getGoodsParamSet();
		for (GoodsParam goodsParam : goodsParamSet) {
			if(goodsParam != null) {
				if(goodsParam.getObjId() != null && "".equals(goodsParam.getObjId())){
					goodsParam.setObjId(null);
					goodsParam.setCreateUser(user);
					goodsParam.setCreateTime(new Date());
				}else if(StringUtils.hasLength(goodsParam.getObjId())){
					goodsParam.setModifier(user);
					goodsParam.setModifyTime(new Date());
				}
			}
		}
		
		//保存为正式
		if(GoodsEnum.PASS_EXAM.equals(goods.getAuditStatus()) && !GoodsEnum.PASS_EXAM.equals(oldGoods.getAuditStatus())) {
			oldGoods.setUseStatus(GoodsEnum.USE_VALID);//有效
			oldGoods.setAuditStatus(GoodsEnum.PASS_EXAM);//通过
			oldGoods.setValidTime(new Date());//生效时间
			oldGoods.setProductDateIssued(new Date());//发布时间
		}
		
		//更新商品对象
		BeanUtils.copyPropertiesFilterEmpty(oldGoods, goods);
		oldGoods.setGoodsParamSet(goodsParamSet);

		//保存商品
		goodsManager.save(oldGoods);
		
		//同步商品参数分类和参数值到spec字段中
		if(null != goodsParamSet && goodsParamSet.size() > 0) {
			this.update_goods_transfer(oldGoods.getObjId());
		}
		
		return oldGoods;
	}
}
