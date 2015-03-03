package com.gpcsoft.goods.goodsclass.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

@Repository
public class GoodsClassDaoHibernate extends BaseGenericDaoHibernate<GoodsClass> implements GoodsClassDao {
    /**
     * Description : 根据商品分类ID查找其孩子结点。
     * Create Date: Jan 27, 2010 1:36:41 PM by liujf  Modified Date: Jan 27, 2010 1:36:41 PM by yucy
     * @param   
     * @return  
     * @Exception
     */
    public List<GoodsClass> findChildGoodsClassesListByGoodsClassId(String parentGoodsClassId) {
        /*这个方法必须要按照goodsClass.sort来进行排序，因为影响到后面与顺序相关的操作。*/
    	List<GoodsClass> goodsClassesList = null;
    	if (parentGoodsClassId != null && !"-1".equals(parentGoodsClassId)) {
    		goodsClassesList = findbyHql("from GoodsClass goodsClass where parentClazz.objId=? order by goodsClass.sort ", parentGoodsClassId);	
    	} else {
    		/*查询顶级结点*/
    		goodsClassesList = findbyHql("from GoodsClass goodsClass where parentClazz is null order by goodsClass.sort ");
    	}
    	
        return goodsClassesList;
    }

    /**
     * Description : 查找商品分类信息。
     * Create Date: Jan 28, 2010 10:09:28 AM by liujf  Modified Date: Jan 28, 2010 10:09:28 AM by liujf
     * @param   
     * @return  
     * @Exception
     */
    public GoodsClass findGoodsClassByObjId(String goodsClassId) throws Exception {
        List<GoodsClass> goodsClassesList = findbyHql("select g from GoodsClass g left join fetch g.parentClazz left join fetch g.goodsClassCategorySet  where g.objId=?", goodsClassId);
        if (goodsClassesList == null || goodsClassesList.isEmpty()) return null;
        else return (GoodsClass) goodsClassesList.get(0);
    }

    /**
     * Description : 获得该父类下面最大的编号。
     * Create Date: May 6, 2010 10:11:03 AM by liujf  Modified Date: May 6, 2010 10:11:03 AM by liujf
     * @param   
     * @return  
     * @Exception
     */
	@SuppressWarnings("unchecked")
	public String getMaxChildKeyCode(String parentId, String tableName, String parentFieldName, String targetFieldName) {
		StringBuffer querySQL = new StringBuffer();
		querySQL.append("select max(" + targetFieldName + ")");
		querySQL.append("  from " + tableName);
		if (parentId == null || "".equals(parentId)) {
			querySQL.append(" where " + parentFieldName + " is null ");
		} else {
			querySQL.append(" where " + parentFieldName + "='" + parentId + "'");
		}
		
		List<String> maxKeyCode = this.getSession().createSQLQuery(querySQL.toString()).list();
		if (maxKeyCode == null || maxKeyCode.isEmpty()) {
			return null;
		} else {
			return (String) maxKeyCode.get(0);
		}
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
				String hql="select max(d."+targetPropertyName+") from "+domain+" d ";
				if(parentId==null||"".equals(parentId)){
					hql += " where d."+parentPropertyName+" is null";
				}else{
					hql += " where d."+parentPropertyName+"='"+parentId+"'";
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
	 * Description :  首页需要的3级商品分类
	 * Create Date: 2010-4-17下午06:59:27 by wangsw  Modified Date: 2010-4-17下午06:59:27 by wangsw
	 * @return  3级分类
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsClass> findGoodsClassForIndex(String nameFirstSpell) {   
		StringBuilder hql=new StringBuilder();
		if(nameFirstSpell==null)
			hql.append(" select g from GoodsClass g where g.parentClazz is null order by g.goodsClassName ");
		else
			hql.append(" select g from GoodsClass g where g.isLeaf =true and g.shortSpellName like ").append("'").append(nameFirstSpell).append("%'").append(" order by g.goodsClassName");
		return getHibernateTemplate().find(hql.toString());
	}
	
	/** 
	 * Description :  首页需要的商品分类,只查询有商品的
	 * Create Date: 2010-12-17下午02:51:55 by liangxj  Modified Date: 2010-12-17下午02:51:55 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsClass> findGoodsClassForHasGoods(String nameFirstSpell) {  
		StringBuilder hql=new StringBuilder();
		String[] queryColum = new String[]{"objId","goodsClassCode","goodsClassName"};;
		if(nameFirstSpell==null){
			hql.append(" select distinct g.objId,g.goodsClassCode,g.goodsClassName " +
					" from GoodsClass g left join g.parentClazz p left join p.parentClazz pp ,Goods goods where g=goods.goodsClass and goods.useStatus='").append(GoodsEnum.USE_VALID).append("'  order by g.goodsClassCode ");
		}
		else
		{
			hql.append(" select g.objId,g.goodsClassCode,g,goodsClassName from GoodsClass g where g.isLeaf=true and g.shortSpellName like ").append("'").append(nameFirstSpell).append("%'").append(" order by g.goodsClassName");
		}		 
		List<String[]> list = getHibernateTemplate().find(hql.toString());
		return HqlResultConvertUtils.hqlResultConvert(list,queryColum,GoodsClass.class);
	}
	
	/** 
	 * Description :  根据code获得分类
	 * Create Date: 2010-12-20下午04:33:21 by liangxj  Modified Date: 2010-12-20下午04:33:21 by liangxj
	 * @param   code数组
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsClass> findGoodsClassByCodes(final Set<String> codes) {
		return (List<GoodsClass>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
					StringBuilder hql=new StringBuilder("select g.objId,g.goodsClassCode,g.goodsClassName,g.parentClazz.objId from GoodsClass g where g.goodsClassCode in (:goodsCods) order by g.goodsClassCode");
					
					Query query = session.createQuery(hql.toString());
					query.setParameterList("goodsCods", codes);
					List<String[]> list = query.list();
					
					String[] queryColum = new String[]{"objId","goodsClassCode","goodsClassName","remarks"};  //由于内置对象无法转，所以暂时使用remark存放上级id
					return HqlResultConvertUtils.hqlResultConvert(list,queryColum,GoodsClass.class);
			}});
	}
	
	/** 
	 * Description :  根据ids获得分类
	 * Create Date: 2011-5-6下午03:57:52 by yucy  Modified Date: 2011-5-6下午03:57:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsClass> findGoodsClassByIds(final String[] objIds)throws Exception {
		return (List<GoodsClass>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					String  hql=  " from GoodsClass where objId in ( :objIds ) ";
					Query query = session.createQuery(hql);
					query.setParameterList("objIds", objIds);
					return query.list();
			}});
	}

	/** 
	 * Description :  根据classCode取得商品分类
	 * Create Date: 2011-12-9下午02:47:55 by yucy  Modified Date: 2011-12-9下午02:47:55 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public GoodsClass getGoodsClassByClassCode(final String classCode) throws Exception {
		return (GoodsClass) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					String  hql= " select gc.* from goods_class gc where gc.goods_class_code  = '"+ classCode +"' ";
					SQLQuery sqlQuery = session.createSQLQuery(hql);
					sqlQuery.addEntity(GoodsClass.class);
					List result = sqlQuery.list();
					return result!=null&& result.size()>0 ? result.get(0):null;
			}});
	}
}
