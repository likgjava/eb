package com.gpcsoft.bizplatform.base.purcategory.dao.hibernate;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.purcategory.dao.PurCategoryDao;
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.core.utils.StringUtils;

@Repository
public class PurCategoryDaoHibernate extends BaseGenericDaoHibernate<PurCategory> implements PurCategoryDao {
    
    /**
     * 
     * Description :  根据parentCode得到它下级品目
     * Create Date: 2010-1-18上午09:43:38 by xiaogh  Modified Date: 2010-1-18上午09:43:38 by xiaogh
     * @param   parentCode
     * @return  List<PurCategory>
     * @Exception
     */
    public List<PurCategory> getPurCategoryItems(String parentCode) throws Exception{
        QueryObject<PurCategory> query = new QueryObjectBase<PurCategory>();
        query.setEntityClass(PurCategory.class);
        QueryParam param = new QueryParam();
        param.and(new QueryParam("purCategory.categoryCode", QueryParam.OPERATOR_EQ, parentCode));
        query.setQueryParam(param);        
        List<PurCategory> purCategoryItems = this.findByQuery(query) ;    
      return purCategoryItems;
    }
    /**
     * 
     * Description :  根据categoryCode判断是否有下级品目
     * Create Date: 2010-1-18上午09:43:38 by xiaogh  Modified Date: 2010-1-18上午09:43:38 by xiaogh
     * @param   categoryCode
     * @return  boolean
     * @Exception
     */
    public boolean isHasNextPurCategory(String categoryCode)throws Exception{
        if(getPurCategoryItems(categoryCode).size()>0)return true;
        else return false;
    }
    
    /** 
	 * Description :  首页需要的采购品目3级
	 * Create Date: 2010-7-15下午02:59:17 by liangxj  Modified Date: 2010-7-15下午02:59:17 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<PurCategory> findPurCategoryForIndex(String nameFirstSpell){
		StringBuilder hql=new StringBuilder();
		if(nameFirstSpell==null)
			hql.append(" select g from PurCategory g where g.parent.parent is null order by g.categoryCode asc");
		else
			hql.append(" select g from PurCategory g where g.isLeaf =true and g.shortSpellName like ").append("'").append(nameFirstSpell.toUpperCase()).append("%'").append(" order by g.categoryCode asc");
		return getHibernateTemplate().find(hql.toString());
	}
	
	/** 
	 * Description :  首页需要的品目分类
	 * Create Date: 2011-2-18下午05:42:06 by sunl  Modified Date: 2011-2-18下午05:42:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<PurCategory> findGoodsCategoryForHasGoods(String nameFirstSpell) {  
		StringBuilder hql=new StringBuilder();
		String[] queryColum = new String[]{"objId","categoryCode","categoryName"};;
		if(nameFirstSpell==null){
			hql.append(" select distinct g.objId,g.categoryCode,g.categoryName " +
					" from PurCategory g left join g.parent p left join p.parent pp  order by g.categoryCode ");
		}
		else
		{
			hql.append(" select g.objId,g.categoryCode,g,categoryName from PurCategory g where g.isLeaf=true and g.shortSpellName like ").append("'").append(nameFirstSpell).append("%'").append(" order by g.categoryName");
		}		 
		List<String[]> list = getHibernateTemplate().find(hql.toString());
		return HqlResultConvertUtils.hqlResultConvert(list,queryColum,PurCategory.class);
	}
	
	/** 
	 * Description :  根据code获得品目
	 * Create Date: 2011-2-18下午05:49:02 by sunl  Modified Date: 2011-2-18下午05:49:02 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<PurCategory> findGoodsCategoryByCodes(final Set<String> codes) {
		return (List<PurCategory>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
					StringBuilder hql=new StringBuilder("select g.objId,g.categoryCode,g.categoryName,g.parent.objId from PurCategory g where g.categoryCode in (:categoryCodes) order by g.categoryCode");
					
					Query query = session.createQuery(hql.toString());
					query.setParameterList("categoryCodes", codes);
					List<String[]> list = query.list();
					
					String[] queryColum = new String[]{"objId","categoryCode","categoryName","remarks"};  //由于内置对象无法转，所以暂时使用remark存放上级id
					return HqlResultConvertUtils.hqlResultConvert(list,queryColum,PurCategory.class);
			}});
	}
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.bizplatform.base.purcategory.dao.PurCategoryDao#updatePurCategoryIsLeaf(java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	public void updatePurCategoryIsLeaf(final String objId, final boolean isleaf) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="update PurCategory set isLeaf =:isLeaf where objId =:objId";
				Query query = session.createQuery(hql);
				query.setString("objId", objId);
				query.setBoolean("isLeaf", isleaf);
				return query.executeUpdate();
			}
		});
	}
	/* (non-Javadoc)
	 * @see com.gpcsoft.bizplatform.base.purcategory.dao.PurCategoryDao#getSubPurCategoryCount(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Long getSubPurCategoryCount(final String objId) {
		return (Long)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "select count(objId) from PurCategory  where parent.objId = ( " +
				"select parent.objId from PurCategory  where objId =:objId)";
				Query query = session.createQuery(hql);
				query.setString("objId", objId);
				List<Object> list = query.list();
				if(list != null && list.size() > 0) {
					return (Long)list.get(0);
				} else {
					return 0;
				}
		
			}
		});
	}
	
	/** 
	 * Description :  获得该父类下面最大的编号。(by hql)
	 * Create Date: 2010-7-28下午05:50:26 by yucy  Modified Date: 2010-7-28下午05:50:26 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public String getMaxChildKeyCodeByHQl(final String parentId, final String domain, final String parentPropertyName, final String targetPropertyName) {
		String code = "";
		List list = (List)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql="select max(substr(d.objId,1,1))||max( to_number(substr(d.objId,2,length(d.objId))) ) from "+domain+" d ";
				if(parentId==null||"".equals(parentId)){
					hql += " where d."+parentPropertyName+" is null";
				}else{
					hql += " where d."+parentPropertyName+" = '"+parentId+"'";
				}
				Query query = session.createQuery(hql);
				return query.list();
		}});
		if(list!=null&&list.size()>0){
			code = (String)list.get(0);
		}
		return code;
	}
	
	/** 
	 * Description :  分级获得品目
	 * Create Date: 2011-3-18下午05:39:26 by yucy  Modified Date: 2011-3-18下午05:39:26 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<PurCategory> getCategorysByLevel(final Map<String, Object> param) throws Exception {
		return (List<PurCategory>)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				
     			String level = (String)param.get("level");
     			
     			String parentId = (String)param.get("parentId");
				
     			String hql = " select p.objId,p.categoryName,p.parent.objId, p.isLeaf from PurCategory p where 1=1 ";
     			
				//二级
				if(StringUtils.hasLength(level)&&"second".equals(level)){
					hql += " and p.parent.parent.objId is null";
				}
				//三级
				if(StringUtils.hasLength(level)&&"third".equals(level)){
					hql += " and p.parent.parent.parent.objId is null";
				}
				//父级
				if(StringUtils.hasLength(parentId)){
					hql += " and p.parent.objId = '"+parentId+"'";
				}
				Query query = session.createQuery(hql);
				
				List<PurCategory> list = new ArrayList<PurCategory>();
				for(Object object: query.list()){
					Object[] objectArray = (Object[])object ;
					PurCategory purCategory= new PurCategory();
					purCategory.setObjId((String)objectArray[0]);
					purCategory.setCategoryName((String)objectArray[1]);
					PurCategory parent= new PurCategory();
					parent.setObjId((String)objectArray[2]);
					purCategory.setParent(parent);
					purCategory.setIsLeaf((Boolean)objectArray[3]);
					list.add(purCategory);
				}
				return list;
			}
		});
	}
	
	/** 
	 * Description :  按关键字获得品目
	 * Create Date: 2011-3-22下午02:31:46 by yucy  Modified Date: 2011-3-22下午02:31:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<PurCategory> getCategorysByKeyWords(final Map<String, Object> param)throws Exception {
		
		return (List<PurCategory>)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				
     			String keyWords = (String)param.get("keyWords");
     			
     			String hql = "select distinct "+
                " temp.p3id, temp.p3name, temp.p3parent, "+
                " temp.p2id, temp.p2name, temp.p2parent, "+
                " temp.p1id, temp.p1name, temp.p1parent "+
                
                " from ("+
                
	                " select distinct p1.id  p1id,"+
	                " p1.category_name p1name,"+
	                " p1.parent_id p1parent,"+
	                " p2.id p2id,"+
	                " p2.category_name p2name,"+
	                " p2.parent_id p2parent,"+
	                " p3.id p3id,"+
	                " p3.category_name p3name,"+
	                " p3.parent_id p3parent"+

	                " from purcatalog_category p1 "+
	                " left join purcatalog_category p2 on p1.parent_id = p2.id "+
	                " left join purcatalog_category p3 on p2.parent_id = p3.id "+
	                " where p1.purcategory_is_leaf = '1' and   "+
	                " (p1.category_name like :keyWords or p2.category_name like :keyWords or p3.category_name like :keyWords ) "+

	                " union all "+

	                "select distinct p1.id  p1id, "+
	                " p1.category_name p1name, "+
	                " p1.parent_id p1parent, "+
	                " p2.id p2id, "+
	                " p2.category_name p2name, "+
	                " p2.parent_id p2parent, "+
	                " p3.id p3id, "+
	                " p3.category_name p3name, "+
	                " p3.parent_id p3parent "+
	
	                "  from (select distinct p.* "+
	                "  		from purcatalog_category p "+
	                "       left join goods g on p.id = g.pur_category_id "+
	                "        where g.product_name like :keyWords "+
	                "        ) p1 "+
	                " left join purcatalog_category p2 on p1.parent_id = p2.id "+
	                " left join purcatalog_category p3 on p2.parent_id = p3.id "+
	            " ) temp "+
                " order by temp.p3id , temp.p2id, temp.p1id";
     			
				Query query = session.createSQLQuery(hql);
				
				query.setParameter("keyWords","%"+keyWords+"%" );
				
				List<PurCategory> list = new ArrayList<PurCategory>();
				
				for (Object object : query.list()) {
					
					PurCategory purCategory1 = null;
					PurCategory purCategory2 = null;
					PurCategory purCategory3 = null;
					
					Object[] objectArray = (Object[])object ;
					
					if(null!=objectArray[0]){
						purCategory1 = new PurCategory();
						purCategory1.setObjId((String)objectArray[0]);
						
						purCategory1.setCategoryName((String)objectArray[1]);

						if(null!=objectArray[2]){
							PurCategory parent = new PurCategory();
							parent.setObjId((String)objectArray[2]);
							purCategory1.setParent(parent);
						}
					}
					
					if(null!=objectArray[3]){
						purCategory2 = new PurCategory();
						purCategory2.setObjId((String)objectArray[3]);
						purCategory2.setCategoryName((String)objectArray[4]);
						if(null!=objectArray[5]&&objectArray[5].equals(objectArray[0])){
							purCategory2.setParent(purCategory1);
						}
					}
					
					if(null!=objectArray[6]){
						purCategory3 = new PurCategory();
						purCategory3.setObjId((String)objectArray[6]);
						purCategory3.setCategoryName((String)objectArray[7]);
						if(null!=objectArray[8]&&objectArray[8].equals(objectArray[3])){
							purCategory3.setParent(purCategory2);
						}
					}
					
					list.add(purCategory3);
					
				}
				return list;
			}
		});
	}
	
	/** 
	 * Description :  根据经营范围code获取品目
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getCategoryByMainProducts(final String bidForRangeCodes) throws Exception {
		return (List<Object[]>)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
     			StringBuilder sql = new StringBuilder();
     			sql.append("select t.id, t.category_Name,count(c.goods_class_id) from PURCATALOG_CATEGORY t  left join GOODS_CLASS_TO_CATEGORY c ");
     			sql.append("on t.id = c.pur_category_id where 1=1 ");
     			sql.append("and t.PURCATEGORY_IS_LEAF=:isLeaf  ");
     			if(StringUtils.hasLength(bidForRangeCodes)) {
     				sql.append(" and t.id in (:categoryCodes) ");
     			}
     			sql.append("group by t.id,t.category_name");
     			
     			
     			Query query = session.createSQLQuery(sql.toString());
     			query.setBoolean("isLeaf", true);
     			if(StringUtils.hasLength(bidForRangeCodes)) {
					query.setParameterList("categoryCodes", bidForRangeCodes.split(","));
				}
				List list = query.list();
				
				return list;
			}
		});
	}
	
	/** 
	 * Description :  获取采购品目的最新更新时间
	 * Create Date: 2011-5-13上午11:45:39 by likg  Modified Date: 2011-5-13上午11:45:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Date getLastUpdateTime() throws Exception {
		return (Date)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Date lastUpdateTime = null;
     			String sql = "select to_char(max(p.CREATE_DATE),'yyyy-MM-dd hh24:mi:ss'), to_char(max(p.UPDATE_DATE),'yyyy-MM-dd hh24:mi:ss') from PURCATALOG_CATEGORY p ";
     			
     			Query query = session.createSQLQuery(sql);
     			Object[] times = (Object[]) query.uniqueResult();
     			Date createTime = null;
     			Date updateTime = null;
				try {
					createTime = DateUtil.parse((String)times[0], "yyyy-MM-dd HH:mm:ss");
					updateTime = DateUtil.parse((String)times[1], "yyyy-MM-dd HH:mm:ss");
				} catch (ParseException e) {
					e.printStackTrace();
				}
     			if(createTime!=null && updateTime!=null) {
     				lastUpdateTime = (createTime.getTime()>=updateTime.getTime() ? createTime : updateTime);
     			} else if(createTime != null) {
     				lastUpdateTime = createTime;
     			} else if(updateTime != null) {
     				lastUpdateTime = updateTime;
     			}
				
				return lastUpdateTime;
			}
		});
	}
	
	/** 
	 * Description :  根据品目code获取品目
	 * Create Date: 2011-12-9下午03:07:20 by yucy  Modified Date: 2011-12-9下午03:07:20 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public PurCategory getPurCategoryByCategoryCode(final String categoryCode) throws Exception {
		return (PurCategory)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String  hql= " select * from purcatalog_category pc where pc.category_code = '"+ categoryCode +"' ";
				SQLQuery sqlQuery = session.createSQLQuery(hql);
				sqlQuery.addEntity(PurCategory.class);
				List result = sqlQuery.list();
				return result!=null&& result.size()>0 ? result.get(0):null;
			}
		});
	}
}