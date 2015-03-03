package com.gpcsoft.goods.goods.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.dao.GoodsDao;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.GoodsOptionalFitting;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class GoodsDaoHibernate extends BaseGenericDaoHibernate<Goods> implements GoodsDao {
	
	/** 
	 * Description :  根据参数获得商品的展示信息列表，及时加载商品的品牌信息、分类信息、参数集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含商品分类信息、商品品牌信息、排序信息
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Goods> getGoodsListForShow(final Page<Goods> page,final Map<String, Object> paramsMap) throws Exception  {
		return (Page<Goods>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String brandId = (String) paramsMap.get("brandId"); //品牌ID
				String goodsClassId  = (String) paramsMap.get("goodsClassId"); //商品分类ID,以逗号分割
				String goodsClassCode = (String) paramsMap.get("goodsClassCode"); //商品分类Code
				String keyWord = (String)paramsMap.get("keyWord");  //关键字
				String price = (String)paramsMap.get("price");  //价格
				String moreFilter = (String)paramsMap.get("moreFilter");  //更多过滤条件
				
//				
//				//查询商品信息
//				String preHql;
//				if(isSimple) {
//					preHql = "from Goods as gs where gs.useStatus = '"+GoodsEnum.USE_VALID+"' and gs.sellStatus = '"+GoodsEnum.SELL_START+"' ";
//				}else {
//					preHql = "from Goods as gs " +
//									"left join fetch gs.purCategory as pc " +
//									"left join fetch gs.goodsClass as cs " +
//									"left join fetch gs.goodsBrand as gb " +
//									//"left join fetch gs.goodsParamSet as gp left join fetch gp.goodsClassParam " + // WARNING: firstResult/maxResults specified with collection fetch; applying in memory! by yucy 性能问题
//									"where gs.useStatus = '"+GoodsEnum.USE_VALID+"' and gs.sellStatus = '"+GoodsEnum.SELL_START+"' ";
//				}
//				StringBuilder hql = new StringBuilder();
//				
//				//分类过滤
//				if(StringUtils.hasLength(goodsClassId)) {
//					hql.append(" and gs.goodsClass.objId in (").append(StringUtils.convertToQuoteString(goodsClassId)).append(") ");
//				}
//				if(StringUtils.hasLength(goodsClassCode)){
//					hql.append(" and gs.goodsClass.goodsClassCode like '%").append(goodsClassCode).append("%' ");
//				}
//				
//				//品牌过滤
//				if(StringUtils.hasLength(brandId)) {
//					hql.append(" and gs.goodsBrand.objId in (").append(StringUtils.convertToQuoteString(brandId)).append(") ");
//				}
//				
//				//关键字
//				if(StringUtils.hasLength(keyWord)) {
//					hql.append(" and ( gs.productName like '%").append(keyWord).append("%' ");
//					hql.append(" or gs.productCode like '%").append(keyWord).append("%' ");
//					hql.append(" or gs.purCategory.categoryName like '%").append(keyWord).append("%' ");
//					hql.append(" or gs.goodsClass.goodsClassName like '%").append(keyWord).append("%' ");
//					hql.append(" or gs.goodsBrand.brandName like '%").append(keyWord).append("%' ) ");
//				}
//				
//				//价格
//				if(StringUtils.hasLength(price)) {
//					String[] temp = price.split(",");
//					if(temp.length > 0 && StringUtils.hasLength(temp[0])) {
//						hql.append(" and gs.referPrice >= ").append(temp[0]);
//					}
//					if(temp.length > 1 && StringUtils.hasLength(temp[1])) {
//						hql.append(" and gs.referPrice <= ").append(temp[1]);
//					}
//				}
//				
//				//更多搜索条件
//				if(StringUtils.hasLength(moreFilter)) {
//					String[] temp = moreFilter.split(",");
//					for (String string : temp) {
//						hql.append(" and gs.").append(string).append(" is not null ");
//					}
//				}
//				
//				//排序条件
//				StringBuilder orderHql = new StringBuilder();
//				if(paramsMap.get("order")!=null) {
//					orderHql.append(" order by ");
//					String[] order = ((String)paramsMap.get("order")).split(",");
//					for (String string : order) {
//						orderHql.append(" gs.").append(string).append(",");
//					}
//					
//					if(orderHql.toString().endsWith(",")) {
//						orderHql.delete(orderHql.length()-1, orderHql.length());
//					}
//				}
//				
//				Query query = session.createQuery(preHql + hql + orderHql);
//				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
//		
//				List<Goods> goodsList=query.list();
//				page.setData(goodsList);
				
//				//查询总数
//				preHql = "select count(gs.objId) from Goods gs where gs.useStatus = '"+GoodsEnum.USE_VALID+"' and gs.sellStatus = '"+GoodsEnum.SELL_START+"' ";
//				query = session.createQuery(preHql + hql);
//				page.setTotalRowNum((Long) query.list().get(0));
				
				
				String column = " select g.goods_id ,g.picture_id ,g.product_name,g.product_code " +
						" ,(select nvl(avg(e.GOODS_EVALUATE_LEVEL),0) from GOODS_EVALUATE e where e.GOODS_ID = g.goods_id) evalSum " +
						" ,g.refer_price" +
						" ,g.spec";
				
				//连接表
				String table = " from goods g ,goods_class c, goods_brand b ,purcatalog_category p" +
						" where g.goods_class_id = c.goods_class_id" +
						" and g.goods_brand_id = b.goods_brand_id" +
						" and g.pur_category_id = p.id" +
						" and g.MANGER_STATUS = '"+GoodsEnum.USE_VALID+"' and g.SELL_STATUS = '"+GoodsEnum.SELL_START+"' ";
				
				//选择条件
				StringBuilder condition = new StringBuilder();
				
				//品牌id过滤
				if(StringUtils.hasLength(brandId)) {
					condition.append(" and g.goods_brand_id in (").append(StringUtils.convertToQuoteString(brandId)).append(") ");
				}
				
				//分类过滤
				if(StringUtils.hasLength(goodsClassId)) {
					condition.append(" and g.goods_class_id in (").append(StringUtils.convertToQuoteString(goodsClassId)).append(") ");
				}
				if(StringUtils.hasLength(goodsClassCode)){
					condition.append(" and instr(c.goods_class_code ,'").append(goodsClassCode).append("')>0 ");
				}
				
				//关键字
				if(StringUtils.hasLength(keyWord)) {
					condition.append(" and ( instr( g.product_name , '").append(keyWord).append("')>0 ");
					condition.append(" or instr(g.product_code ,'").append(keyWord).append("')>0 ");
					condition.append(" or instr(p.category_name , '").append(keyWord).append("')>0 ");
					condition.append(" or instr(c.goods_class_name, '").append(keyWord).append("')>0 ");
					condition.append(" or instr(b.brand_name , '").append(keyWord).append("')>0 ) ");
				}
				
				//价格
				if(StringUtils.hasLength(price)) {
					String[] temp = price.split(",");
					if(temp.length > 0 && StringUtils.hasLength(temp[0])) {
						condition.append(" and g.refer_price >= ").append(temp[0]);
					}
					if(temp.length > 1 && StringUtils.hasLength(temp[1])) {
						condition.append(" and g.refer_price <= ").append(temp[1]);
					}
				}
				
				//更多搜索条件
				if(StringUtils.hasLength(moreFilter)) {
					String[] temp = moreFilter.split(",");
					for (String string : temp) {
						condition.append(" and g.").append(string).append(" is not null ");
					}
				}
				
				//排序条件
				StringBuilder orderHql = new StringBuilder();
				String orderHqlStr = null;
				if(paramsMap.get("order")!=null) {
					orderHql.append(" order by ");
					String[] order = ((String)paramsMap.get("order")).split(",");
					for (String string : order) {
						
						orderHql.append(string).append(",");
					}
					if(orderHql.toString().endsWith(",")) {
						orderHql.delete(orderHql.length()-1, orderHql.length());
					}
					
					//特殊处理
					orderHqlStr = orderHql.toString().replace("evalSum", "(select nvl(avg(e.GOODS_EVALUATE_LEVEL),0) from GOODS_EVALUATE e where e.GOODS_ID = g.GOODS_ID)")
					.replace("productDateIssued", "product_date_issued")
					.replace("referPrice", "refer_price");
				}
				
				Query query = session.createSQLQuery(column + table + condition.toString() + orderHqlStr);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				List<Goods> dataList = new ArrayList<Goods>();
				
				//拼装对象
				for(Object[] objects : (List<Object[]>)query.list()){
					Goods goods = new Goods();
					goods.setObjId((String)objects[0]);
					goods.setPicture((String)objects[1]);
					goods.setProductName((String)objects[2]);
					goods.setProductCode((String)objects[3]);
					goods.setEvalSum((BigDecimal)objects[4]);
					goods.setReferPrice((BigDecimal)objects[5]);
					goods.setSpec((String)objects[6]);
					dataList.add(goods);
				}
				page.setData(dataList);
				
				//count
				query = session.createSQLQuery("select count(g.goods_id) "+table + condition.toString());
				page.setTotalRowNum(new Long(query.list().get(0).toString()));
				return page;
		}});
	}
	
	/** 
	 * Description :  根据商品分类获得下级商品分类的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   goodsClassId 商品分类id，goodsClassCode 商品分类编号, isLeaf 是否只取叶子节点
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getClassListShowByClass(final String goodsClassId,final String goodsClassCode,final Boolean isLeaf,final String keyWord) throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder("select b.goods_class_id, b.goods_class_name, count(goods_id) from " +
										"goods a, " +
										"goods_class b " +
										"where a.manger_status =:useStatus and a.sell_Status =:sellStatus " +
										"and a.goods_class_id = b.goods_class_id ");
				
				if(StringUtils.hasLength(goodsClassId)) {
					sql.append(" and b.goods_class_id in (:goodsClassId) ");
				}
				if(StringUtils.hasLength(goodsClassCode)) {
					sql.append(" and b.goods_class_code like :goodsClassCode ");
				}
				if(isLeaf) {
					sql.append(" and b.goods_class_is_leaf = :isLeaf ");
				}
				if(StringUtils.hasLength(keyWord)) {
					sql.append(" and ( a.PRODUCT_NAME like '%").append(keyWord).append("%' ");
					sql.append(" or a.PRODUCT_CODE like '%").append(keyWord).append("%' ");
					sql.append(" or b.GOODS_CLASS_NAME like '%").append(keyWord).append("%' ) ");
				}
				
				sql.append(" group by b.goods_class_id, b.goods_class_name order by b.goods_class_id");
				
				Query query = session.createSQLQuery(sql.toString());
				query.setString("useStatus", GoodsEnum.USE_VALID);
				query.setString("sellStatus", GoodsEnum.SELL_START);
				
				if(StringUtils.hasLength(goodsClassId)) {
					query.setParameterList("goodsClassId", goodsClassId.split(","));
				}
				if(StringUtils.hasLength(goodsClassCode)) {
					query.setString("goodsClassCode", goodsClassCode+"%");
				}
				if(isLeaf) {
					query.setBoolean("isLeaf", isLeaf);
				}
				
				List<String[]> gcList=query.list();
				
				return gcList;
			}});
	}

	/** 
	 * Description :  根据品目获得商品品牌的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   categoryId 品目id，categoryCode 品目编号
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getBrandListShowByCategory(final String categoryId,final String categoryCode) throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder("select d.goods_brand_id, d.brand_name, count(a.goods_id) from " +
											"goods a, " +
											"goods_class b, " +
											"purcatalog_category c, " +
											"goods_brand d " +
											"where a.manger_status = '" + GoodsEnum.USE_VALID+ "' and a.sell_status = '"+GoodsEnum.SELL_START+"' " +
											"and a.goods_class_id = b.goods_class_id " +
											"and a.pur_category_id = c.id " +
											"and a.goods_brand_id = d.goods_brand_id ");
				
				if(StringUtils.hasLength(categoryId)) {
					sql.append(" and c.id in (:categoryId) ");
				}
				if(StringUtils.hasLength(categoryCode)) {
					sql.append(" and c.category_code like :categoryCode ");
				}
				
				sql.append(" group by d.goods_brand_id, d.brand_name order by d.brand_name");
				
				Query query = session.createSQLQuery(sql.toString());
				
				if(StringUtils.hasLength(categoryId)) {
					query.setParameterList("categoryId", categoryId.split(","));
				}
				if(StringUtils.hasLength(categoryCode)) {
					query.setString("categoryCode", categoryCode+"%");
				}
				
				List<String[]> gcList=query.list();
				
				return gcList;
			}});
	}

	/** 
	 * Description :  根据商品分类获得商品品牌的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   classId 分类id，classCode 分类编号
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getBrandsListShowByClass(final String classId,final String classCode,final String keyWord) throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder("select b.goods_brand_id,b.brand_name,count(a.goods_id) from " +
										"goods a, " +
										"goods_brand b, " +
										"goods_class c " +
										"where a.manger_status = '" + GoodsEnum.USE_VALID+ "' and a.sell_status = '"+GoodsEnum.SELL_START+"' " + 
										"and a.goods_brand_id=b.goods_brand_id " + 
										"and a.goods_class_id=c.goods_class_id ");
				
				if(StringUtils.hasLength(classId)) {
					sql.append(" and c.goods_class_id in (:classId) ");
				}
				if(StringUtils.hasLength(classCode)) {
					sql.append(" and c.goods_class_code like :classCode ");
				}
				if(StringUtils.hasLength(keyWord)) {
					sql.append(" and ( a.PRODUCT_NAME like '%").append(keyWord).append("%' ");
					sql.append(" or a.PRODUCT_CODE like '%").append(keyWord).append("%' ");
					sql.append(" or b.BRAND_NAME like '%").append(keyWord).append("%' ");
					sql.append(" or c.GOODS_CLASS_NAME like '%").append(keyWord).append("%' ) ");
				}
				
				sql.append(" group by b.goods_brand_id,b.brand_name order by b.brand_name");
				
				Query query = session.createSQLQuery(sql.toString());
				
				if(StringUtils.hasLength(classId)) {
					query.setParameterList("classId", classId.split(","));
				}
				if(StringUtils.hasLength(classCode)) {
					query.setString("classCode", classCode+"%");
				}
				
				List<String[]> gcList=query.list();
				
				return gcList;
			}});
	}

	/** 
	 * Description :  商品唯一性校验
	 * 				  1 同分类、同品牌、有效的商品名称不能重复
	 * 				  2 同分类、同品牌、有效的商品型号不能重复
	 * Create Date: 2011-5-6下午06:50:51 by sunl  Modified Date: 2011-5-6下午06:50:51 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer checkGoodsUnique(final Map<String,Object> param) throws Exception {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					StringBuilder hql = new StringBuilder();
					//过滤掉报废商品
					hql.append("select count(*) from Goods t where 1=1 ");
					hql.append("and t.useStatus = '").append(GoodsEnum.USE_VALID).append("' ");
					hql.append("and t.auditStatus = '").append(GoodsEnum.PASS_EXAM).append("' ");
					hql.append("and t.goodsClass.objId = '").append((String)param.get("classId")).append("' ");
					hql.append("and t.goodsBrand.objId = '").append((String)param.get("brandId")).append("' ");
					
					if(StringUtils.hasLength((String)param.get("objId"))) {
						hql.append("and t.objId <>:objId ");
					}
					if(StringUtils.hasLength((String)param.get("productName"))) {
						hql.append("and t.productName like :productName ");
					}
					if(StringUtils.hasLength((String)param.get("productCode"))) {
						hql.append("and t.productCode = :productCode ");
					}
					
					Query query = session.createQuery(hql.toString());
					
					if(StringUtils.hasLength((String)param.get("objId"))) {
						query.setParameter("objId",(String)param.get("objId"));
					}
					if(StringUtils.hasLength((String)param.get("productName"))) {
						query.setParameter("productName",(String)param.get("productName"));
					}
					if(StringUtils.hasLength((String)param.get("productCode"))) {
						query.setParameter("productCode",(String)param.get("productCode"));
					}
					Integer res = 0;
					if(query.list() != null) {
						res = ((Long)query.list().get(0)).intValue();
					}
					
					return res;
				}
		});
	}
	
	/** 
	 * Description :  根据主键，获得商品的详细信息，包括基本信息、扩展信息、图片、参数等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param 
	 * @return  
	 * @Exception   
	 */
	public Goods getGoodsAllInfo(final String objId) throws Exception {
		List<Goods> list = getGoodsAllInfoList(objId);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	/** 
	 * Description :  根据主键，获得商品的详细信息，包括基本信息、扩展信息、图片、参数等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param objIds以逗号分隔
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Goods> getGoodsAllInfoList(final String objIds) throws Exception {
		return (List<Goods>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				//查询商品信息
				String hql = "from Goods as gs " +
								"left join fetch gs.purCategory as pc " +
								"left join fetch gs.goodsClass as cs " +
								"left join fetch gs.goodsBrand as gb " +
								"left join fetch gs.goodsParamSet as gp " +
								"left join fetch gp.goodsClassParam " +
								"left join fetch gp.goodsOptionalFittingSet " +
								"where gs.objId in (:objId) ";
				
				Query query = session.createQuery(hql);
				query.setParameterList("objId", objIds.split(","));
				
				Set<Goods> goodsSet = new HashSet<Goods>(query.list());
				List<Goods> goodsList = new ArrayList<Goods>();
				goodsList.addAll(goodsSet);
				return goodsList;
		}});
	}
	
	/** 
	 * Description :  查询商品历史
	 * Create Date: 2010-8-6下午06:57:32 by sunl  Modified Date: 2010-8-6下午06:57:32 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Goods> getGoodsHistory(final Map<String,Object> param) throws Exception {
		return (ArrayList<Goods>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				Query query = null;
				hql.append("from GoodsChange t where t.goods.objId=:id");
				query = session.createQuery(hql.toString());
				query.setParameter("id", param.get("id"));
				return query.list();
			}
		});
	}
	
	/** 
     * Description : 获得商品参数分类和商品参数值信息
     * 			     在商品修改和变更时，需要重新获取商品参数分类以及参数
     * Create Date: 2010-8-4下午05:07:29 by sunl  Modified Date: 2010-8-4下午05:07:29 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @SuppressWarnings("unchecked")
	public List<Object> getGoodsParamAndClassParam(final String goodsId,final String classId) throws Exception {
    	return (ArrayList<Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				
				sql.append("select t.goods_class_param_id,t.param_parent_id,t.param_name ");
				sql.append(",t.need_input,t.param_is_leaf,t.sort ");
				sql.append(",p.goods_param_id,p.goods_id,p.param_value,t.PARAM_UNIT ");
				sql.append("from goods_class_param t ");
				sql.append("left join ");
				sql.append("(select * from goods_param where goods_id =:goodsId) p ");
				sql.append("on p.goods_class_param_id = t.goods_class_param_id ");
				sql.append("where t.goods_class_id =:classId ");
				sql.append("order by t.sort asc ");
				
				Query query = session.createSQLQuery(sql.toString());
				
				query.setString("goodsId", goodsId);
				query.setString("classId", classId);
				
				return query.list();
			}
		});
    }
    
    /** 
     * Description : 此方法在
     * 				 1 商品删除之后触发（删除变更商品之后，需要将objId为currentId的数据currentId更新为空）
     *  			 2 保存变更商品之后触发（将所变更的商品currentId更新为新商品的objId）
     *  			 3 审核通过之后,将objId为currentId或currentId为currentId的数据的currentId变为goods的objId
     * Create Date: 2010-8-4下午05:07:29 by sunl  Modified Date: 2010-8-4下午05:07:29 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @SuppressWarnings("unchecked")
	public Integer updateGoodsCurrentId(final String objId, final String currentId, final String type) throws Exception {
    	return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				Query query = null;
				if(type.equals("save")){
					hql.append("update Goods t set t.currentId =:objId where t.objId =:currentId");
					query = session.createQuery(hql.toString());
					query.setParameter("objId", objId);
					query.setParameter("currentId", currentId);
				}else if(type.equals("remove")){
					hql.append("update Goods t set t.currentId = '' where t.objId in (:currentId)");
					query = session.createQuery(hql.toString());
					query.setParameterList("currentId", currentId.split(","));
				}else if(type.equals("audit")){
					hql.append(" update Goods t set t.currentId =:newGoodsId ");
					hql.append(" where t.objId =:currentId or t.currentId =:currentId");	
					query = session.createQuery(hql.toString());
					query.setParameter("newGoodsId", objId);
					query.setParameter("currentId", currentId);
				}
				return query.executeUpdate();
			}
		});
    }
    
    /** 
	 * Description :  禁卖商品
	 * Create Date: 2010-8-10下午03:55:09 by sunl  Modified Date: 2010-8-10下午03:55:09 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer disableGoods(final String objIds) throws Exception {
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("update Goods t set t.sellStatus = '").append(GoodsEnum.SELL_STOP).append("' where t.objId in (:objId)");
				Query query = session.createQuery(hql.toString());
				query.setParameterList("objId", objIds.split(","));
				return query.executeUpdate();
			}
		});
	}
	
	/** 
	 * Description :  启卖商品
	 * Create Date: 2010-8-10下午03:55:09 by sunl  Modified Date: 2010-8-10下午03:55:09 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer enableGoods(final String objIds) throws Exception {
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("update Goods t set t.sellStatus = '").append(GoodsEnum.SELL_START).append("' where t.objId in (:objId)");
				Query query = session.createQuery(hql.toString());
				query.setParameterList("objId", objIds.split(","));
				return query.executeUpdate();
			}
		});
	}
	
	/** 
	 * Description :  报废商品
	 * Create Date: 2010-8-10下午03:55:09 by sunl  Modified Date: 2010-8-10下午03:55:09 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer scrappedGoods(final String objIds) throws Exception {
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("update Goods t set t.useStatus = '").append(GoodsEnum.USE_INVALID).append("',t.invalidTime =:invalidTime where t.objId in (:objId)");
				Query query = session.createQuery(hql.toString());
				query.setParameterList("objId", objIds.split(","));
				query.setDate("invalidTime", new Date());
				return query.executeUpdate();
			}
		});
	}
	
	/**
	 * Description :  根据商品Id获取商品选配信息
	 * Create Date: 2010-8-11下午02:41:57 by sunl  Modified Date: 2010-8-11下午02:41:57 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsOptionalFitting> getOptionFittingByGoods(final String goodsId) throws Exception {
    	return (ArrayList<GoodsOptionalFitting>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				Query query = null;
				hql.append("from GoodsOptionalFitting t where t.goodsParam.goods.objId =:goodsId");
				
				query = session.createQuery(hql.toString());
				
				query.setString("goodsId", goodsId);
				
				return query.list();
			}
		});
    }
	
	/** 
	 * Description :  商品列表
	 * 				  查询条件为：指定维护商的维护商品
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Goods> listGoods(Page<Goods> page, Map<String,Object> param) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("from goods t join goods_brand b on t.goods_brand_id = b.goods_brand_id ");
		sql.append("join goods_class c on c.goods_class_id = t.goods_class_id ");
		sql.append("join purcatalog_category p on p.id = t.pur_category_id ");
		sql.append("join auth_user u on u.USR_ID = t.CREATOR_ID join AUTH_ORG_EMPLOYEE e on e.emp_id = u.emp_id ");
		sql.append("where 1 = 1 ");
		
		//过滤供应商自己创建的商品(manager不过滤)
		if(StringUtils.hasLength((String)param.get("roleType")) && "supplier".equals((String)param.get("roleType"))) {
			sql.append(" and t.CREATOR_ID = '").append(AuthenticationHelper.getCurrentUser(true).getObjId()).append("'");
		} 
		
		//拼装查询条件
		if(StringUtils.hasLength(param.get("productName").toString())){
			sql.append(" and t.product_name like '%").append(param.get("productName").toString()).append("%' ");
		}
		if(StringUtils.hasLength(param.get("productCode").toString())){
			sql.append(" and t.product_code like '%").append(param.get("productCode").toString()).append("%' ");
		}
		if(StringUtils.hasLength(param.get("brandName").toString())){
			sql.append(" and b.brand_name like '%").append(param.get("brandName").toString()).append("%' ");
		}
		if(StringUtils.hasLength(param.get("startDate").toString()) && StringUtils.hasLength(param.get("endDate").toString())){
			sql.append(" and t.create_time <= ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date((String)param.get("endDate")));
			sql.append(" and t.create_time >= ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date((String)param.get("startDate")));
		}
		if(StringUtils.hasLength(param.get("startDate").toString()) && StringUtils.hasLength(param.get("endDate").toString())){
			sql.append(" and t.create_time >= ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date((String)param.get("startDate")));
		}
		if(StringUtils.hasLength(param.get("startDate").toString()) && StringUtils.hasLength(param.get("endDate").toString())){
			sql.append(" and t.create_time <= ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date((String)param.get("endDate")));
		}
		
		//不同的选项卡类型参数
		if(StringUtils.hasLength(param.get("type").toString())){
			if(param.get("type").toString().equals("newGoods")){//00
				sql.append("and t.manger_status = '").append(GoodsEnum.USE_TEMP).append("' and t.CURRENT_VALID_ID is null");
			}else if(param.get("type").toString().equals("modifyGoods")){//00
				sql.append("and t.manger_status = '").append(GoodsEnum.USE_TEMP).append("' and t.CURRENT_VALID_ID is not null");
			}else if(param.get("type").toString().equals("validGoods")){//01 01
				sql.append("and t.manger_status = '").append(GoodsEnum.USE_VALID).append("' and t.sell_status = '").append(GoodsEnum.SELL_START).append("'");
			}else if(param.get("type").toString().equals("historyGoods")){//02 02
				sql.append("and t.CURRENT_VALID_ID is null and (t.manger_status = '").append(GoodsEnum.USE_INVALID).append("' or t.sell_status = '").append(GoodsEnum.SELL_STOP).append("')");
			}
		}
		//过滤掉禁卖的品牌02
		sql.append(" and b.sell_status != '").append(GoodsEnum.SELL_STOP).append("'");
		
		if(StringUtils.hasLength((String)param.get("goodsClassId"))){
			sql.append(" and c.goods_class_id in (:goodsClassId) ");
		}
		String order = " order by t.create_time desc ";
		//排序
		if(StringUtils.hasLength(param.get("order").toString())){
			String orderStr = param.get("order").toString();
			StringBuilder multiOrder = new StringBuilder();
			if(orderStr.indexOf("picture") != -1){
				multiOrder.append("t.goods_id, ");
			}
			if(orderStr.indexOf("productName") != -1){
				multiOrder.append("t.product_name, ");
			}
			if(orderStr.indexOf("productCode") != -1){
				multiOrder.append("t.product_code, ");
			}
			if(orderStr.indexOf("goodsBrand.brandName") != -1){
				multiOrder.append("b.BRAND_NAME, ");
			}
			if(orderStr.indexOf("purCategory.categoryName") != -1){
				multiOrder.append("p.category_Name, ");
			}
			if(orderStr.indexOf("auditStatus") != -1){
				multiOrder.append("t.audit_status, ");
			}
			if(orderStr.indexOf("sellStatus") != -1){
				multiOrder.append("t.sell_status, ");
			}
			if(orderStr.indexOf("useStatus") != -1){
				multiOrder.append("t.manger_status, ");
			}
			if(orderStr.indexOf("createUser.emp.name") != -1){
				multiOrder.append("t.creator_id, ");
			}
			sql.append(" order by ").append(multiOrder.toString().substring(0,multiOrder.toString().length()-2));//去掉空格
			sql.append(param.get("order_flag").toString().equals("true") ? " desc ":" asc ");
		}else{
			sql.append(order);
		}
		
		String queryColumn = "";
		if(StringUtils.hasLength((String)param.get("roleType")) && "supplier".equals((String)param.get("roleType"))) {
			queryColumn = "select t.goods_id,t.picture_id,t.product_name,t.product_code,b.BRAND_NAME,t.audit_status,t.sell_status,t.manger_status,t.CURRENT_VALID_ID ";
		}else if(StringUtils.hasLength((String)param.get("roleType")) && "manager".equals((String)param.get("roleType"))){
			queryColumn = "select t.goods_id,t.picture_id,t.product_name,t.product_code,b.BRAND_NAME,e.EMP_NAME,t.audit_status,t.sell_status,t.manger_status,t.CURRENT_VALID_ID ";
		}
			
		Query queryCount = this.getSession().createSQLQuery("select count(*) " + sql.toString());
		Query query = this.getSession().createSQLQuery(queryColumn + sql.toString());
		
		if(StringUtils.hasLength((String)param.get("goodsClassId"))) {
			query.setParameterList("goodsClassId", param.get("goodsClassId").toString().split(","));
		}
		if(StringUtils.hasLength((String)param.get("goodsClassId"))) {
			queryCount.setParameterList("goodsClassId", param.get("goodsClassId").toString().split(","));
		}
		
		query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
		Integer totalRow = ((BigDecimal)queryCount.list().get(0)).intValue();
		List list = query.list();
		Page<Goods> pageData = new Page<Goods>(page.getStart(),totalRow,page.getPageSize(),list );
		return pageData;	
	}
	
	/** 
     * Description : 根据当前用户组织机构ID获取所能维护的商品分类
     * Create Date: 2010-8-10上午11:13:50 by sunl  Modified Date: 2010-8-10上午11:13:50 by sunl
     * @param   
     * @return  
     * @Exception   
     */
	@SuppressWarnings("unchecked")
	public List<GoodsClass> getGoodsClassByOrg(final String orgInfoId) throws Exception {
		return (ArrayList<GoodsClass>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				
				hql.append("from GoodsClass g where g.objId in ");
				hql.append("(select t.goodsClass.objId from GoodsModifier t ");
				hql.append("where t.supplier.objId =:orgInfoId) ");
				hql.append("order by g.objId desc");
				
				Query query = session.createQuery(hql.toString());
				
				query.setString("orgInfoId", orgInfoId);
				
				return query.list();
			}
		});
	}
	
	/** 
	 * Description :  数据迁移
	 * Create Date: 2010-9-2下午04:10:45 by sunl  Modified Date: 2010-9-2下午04:10:45 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer update_goods_transfer(final String spec,final String goodsId) throws Exception {
    	return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				Integer result = -1;
				sql.append("update goods t set t.spec =:spec where t.goods_id =:goodsId");
				Query query = session.createSQLQuery(sql.toString());
				query.setParameter("spec", spec);
				query.setParameter("goodsId", goodsId);
				result = query.executeUpdate();
				return result;
			}
		});
    }
	
	/** 
	 * Description :   获取商品展示的列表数据(过滤供应商的 投标范围及类别)
	 * Create Date: 2009-4-13上午11:04:42 by yucy  Modified Date: 2009-4-13上午11:04:42 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Goods> getGoodsListBySupplierBidRange(final Page<Goods> page,final Map<String, Object> param) throws Exception{
			return (Page<Goods>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					String today = UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date(DateUtil.format(new Date()));
					
					//拼装匹配条件
					String bidForRange =((OrgInfo)param.get("orgInfo")).getBidForRange();
					String bidForRangeSql =" and ( 1=2 ";
					if(StringUtils.hasLength(bidForRange)){
						String[] bidForRangeArray = bidForRange.replace("##||##", "&").split("&");
						bidForRangeArray = bidForRangeArray.length>0?bidForRangeArray[0].split(","):new String[]{};
						for (int i = 0; i < bidForRangeArray.length; i++) {
							bidForRangeSql += " or p.id like '%"+bidForRangeArray[i]+"%' ";
						}
					}
					bidForRangeSql += " )";
					
					String reslutHql = "select g.goods_id" +
							",g.product_name" +
							",g.picture_id" +
							",g.product_code" +
							",b.brand_name, "+
					" (select count(gp.goods_price_id)"+
					" from goods_price gp, goods_price_supplier gs"+
					" where gs.goods_id = g.goods_id and gp.PRICE_SUPPLIER_ID= gs.GOODS_PRICE_SUPPLIER_ID and gp.use_status = '01' and gs.supplier_id='"+AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()+"' " +
					" and gp.efct_date <= "+today+" and gp.end_date >= "+ today +
					") as countPrice " +
					", c.goods_class_name" +
					", g.refer_price";
				
				 String condition =	" from goods g,"+
					" goods_brand b,"+
					" goods_class c,"+
					" purcatalog_category p ,"+
					" org_info o"+
					" where "+
					" g.goods_brand_id = b.goods_brand_id " +
					" and g.goods_class_id = c.goods_class_id "+
					" and g.manger_status = '01' and g.sell_status = '01'"+//A0201 like 
					" and g.pur_category_id = p.id "+
					
					((StringUtils.hasLength(bidForRange)&&param.get("nobidForRange")==null)?bidForRangeSql:"")+//param.get("nobidForRange")传入此参数另列表并不过滤投标范围
					" and o.org_info_id= '"+AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()+"'";
				 
				 	if(null!=param.get("categoryId")&&!"".equals((String)param.get("categoryId"))){
						condition += " and p.id like '%"+(String)param.get("categoryId")+"%'";
				 	}
					
					if(null!=param.get("productName")&&!"".equals((String)param.get("productName"))){
						condition += " and g.product_name like '%"+(String)param.get("productName")+"%'";
					}
					if(null!=param.get("brandName")&&!"".equals((String)param.get("brandName"))){
						condition += " and b.brand_name like '%"+(String)param.get("brandName")+"%'";
					}
					if(null!=param.get("className")&&!"".equals((String)param.get("className"))){
						condition += " and c.goods_class_name like '%"+(String)param.get("className")+"%'";
					}
					
					if(param.get("validePrice")!=null){
						condition += "  and (select count(gp.goods_price_id) from goods_price gp, goods_price_supplier gs"+
						" where gs.goods_id = g.goods_id and gp.PRICE_SUPPLIER_ID= gs.GOODS_PRICE_SUPPLIER_ID and gp.use_status = '01' and gs.supplier_id='"+AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()+"' " +
						" ) > 0 ";
					}
					
					if(param.get("noValidePrice")!=null){
						condition += "  and (select count(gp.goods_price_id) from goods_price gp, goods_price_supplier gs"+
						" where gs.goods_id = g.goods_id and gp.PRICE_SUPPLIER_ID= gs.GOODS_PRICE_SUPPLIER_ID and gp.use_status = '01' and gs.supplier_id='"+AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()+"' " +
						" ) = 0 ";
					}
					
					//排序条件()
					String orderHql = "";
					if("productName".equals((String)param.get("order"))){
						orderHql = " order by g.product_name " + (((String)param.get("order_flag")).split(",")[0].equals("true")?"desc":"asc");
					}else if("productCode".equals((String)param.get("order"))){
						orderHql = " order by g.product_code " + (((String)param.get("order_flag")).split(",")[0].equals("true")?"desc":"asc");
					}else if("goodsBrand.brandName".equals((String)param.get("order"))){
						orderHql = " order by b.brand_name " + (((String)param.get("order_flag")).split(",")[0].equals("true")?"desc":"asc");
					}else if("goodsClass.goodsClassName".equals((String)param.get("order"))){
						orderHql = " order by c.goods_class_name " + (((String)param.get("order_flag")).split(",")[0].equals("true")?"desc":"asc");
					}else if("remark".equals((String)param.get("order"))||(String)param.get("order")==null){//默认按这个
						orderHql = " order by countPrice " + (((String)param.get("order")==null||((String)param.get("order_flag")).split(",")[0].equals("true"))?"desc":"asc");
					}

					//查询结果
					Query query = session.createSQLQuery(reslutHql+condition+orderHql );
					query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
					
					List<Goods> goodsList = new ArrayList<Goods>();
					for (Object object : query.list()) {
						Object[] objects = (Object[])object;
						Goods goods = new Goods();
						goods.setObjId((String)objects[0]);
						goods.setProductName((String)objects[1]);
						goods.setPicture((String)objects[2]);
						goods.setProductCode((String)objects[3]);
						
						GoodsBrand goodsBrand = new GoodsBrand();
						goodsBrand.setBrandName((String)objects[4]);
						goods.setGoodsBrand(goodsBrand);
						
						goods.setRemark(""+objects[5]);
						
						GoodsClass goodsClass = new GoodsClass();
						goodsClass.setGoodsClassName(""+objects[6]);
						goods.setGoodsClass(goodsClass);
						
						goods.setReferPrice((BigDecimal)objects[7]);
						
						goodsList.add(goods);
					}
					page.setData(goodsList);
					//查询记录数
					query = session.createSQLQuery("select count(g.goods_id) "+condition);
					String num = query.list().get(0).toString();
					page.setTotalRowNum(Long.valueOf(num));
					return page;
				}
		});
	}
	
	/** 
	 * Description :  按关键字获得商品类目
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getProductsBySearchName(Map<String,Object> params) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select gp.id categoryid,gp.category_name categoryname,");
		sql.append("gc.goods_class_id goodsclassid,gc.goods_class_name goodsclassname,");
		sql.append("gb.goods_brand_id goodsbrandid,gb.brand_name brandname ");
		sql.append("from goods_class_to_category c inner join goods_brand_to_class b on c.goods_class_id=b.goods_class_id ");
		sql.append("left join purcatalog_category gp on gp.id = c.pur_category_id ");
		sql.append("left join goods_class gc on gc.goods_class_id=b.goods_class_id ");
		sql.append("left join goods_brand gb on gb.goods_brand_id=b.goods_brand_id where 1=1 ");
		sql.append("and(gp.category_name like :keyWords or gc.goods_class_name like :keyWords or gb.brand_name like :keyWords) ");
		sql.append("and gb.manager_status=:status ");
		if(StringUtils.hasLength((String)params.get("categoryCodes"))) {
			sql.append("and gp.id in (:categoryCodes) ");
		}
			
		Query query = this.getSession().createSQLQuery(sql.toString());
		query.setParameter("status",GoodsEnum.USE_VALID);
		query.setParameter("keyWords","%"+params.get("keyWords").toString()+"%" );
		if(StringUtils.hasLength((String)params.get("categoryCodes"))) {
			query.setParameterList("categoryCodes", params.get("categoryCodes").toString().split(","));
		}
		return query.list();
	}
}
