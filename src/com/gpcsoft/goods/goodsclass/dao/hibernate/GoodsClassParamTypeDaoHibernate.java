package com.gpcsoft.goods.goodsclass.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassParamTypeDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParamType;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class GoodsClassParamTypeDaoHibernate extends BaseGenericDaoHibernate<GoodsClassParamType> implements GoodsClassParamTypeDao {
   
	/* (non-Javadoc)
	 * @see com.gpcsoft.goods.goodsclass.dao.GoodsClassParamTypeDao#getgoodsClassParamTypeMaxSort(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public int getgoodsClassParamTypeMaxSort(String parentId) {
		List<Object> list = null;
		
		// 如果父ID为空
		if(null != parentId && !"".equals(parentId)) {
			list = getHibernateTemplate().find("select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("max(sort)","0") + " from GoodsClassParamType where parent.objId = ? ", parentId);
		}else {
			list = getHibernateTemplate().find( "select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("max(sort)","0") + " from GoodsClassParamType where parent.objId is null ");
		}
		
		return (Integer) list.get(0);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.goods.goodsclass.dao.GoodsClassParamTypeDao#updateQualificationIsLeaf(boolean)
	 */
	@SuppressWarnings("unchecked")
	public void updateGoodsClassParamTypeIsLeaf(final String objId, final boolean isleaf) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="update GoodsClassParamType set isLeaf =:isLeaf where objId =:objId";
				Query query = session.createQuery(hql);
				query.setString("objId", objId);
				query.setBoolean("isLeaf", isleaf);
				return query.executeUpdate();
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.goods.goodsclass.dao.GoodsClassParamTypeDao#getSubGoodsClassParamTypeCount(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Long getSubGoodsClassParamTypeCount(final String objId) {
		return (Long)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "select count(objId) from GoodsClassParamType  where parent.objId = ( " +
				"select parent.objId from GoodsClassParamType  where objId =:objId)";
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
     * Description : 根据商品分类获取分类参数信息  
     * Create Date: 2010-8-4下午05:07:29 by sunl  Modified Date: 2010-8-4下午05:07:29 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @SuppressWarnings("unchecked")
	public List<GoodsClassParamType> getGoodsClassParamByClass(String classId) throws Exception{
    	StringBuilder hql = new StringBuilder();
    	hql.append(" from GoodsClassParamType t where t.goodsClass.objId =:classId order by t.sort asc ");
    	
    	Query query = super.getSession().createQuery(hql.toString());
		query.setString("classId", classId);
		return query.list();
    }
    
    /** 
	 * Description : 判断在同一商品分类下的商品参数在父节点下是不是唯一  
	 * Create Date: 2010-11-25上午10:23:13 by guoyr  Modified Date: 2010-11-25上午10:23:13 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public boolean isUnique(final String goodsClassId, final String paramName, final String objId, final String parentId ){
		return(Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				StringBuffer sb= new StringBuffer("select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count(objId)","0")+" from GoodsClassParamType  where paramName =:paramName");
				sb.append(" and goodsClass.objId =:goodsClassId");
				if(StringUtils.hasLength(parentId)){
					sb.append(" and parent.objId =:parentId");
				}else {
					sb.append(" and parent.objId  is null");
				}
				if(StringUtils.hasLength(objId)){
					sb.append(" and objId <>:objId");
				}
				Query query = session.createQuery(sb.toString());
				query.setString("paramName", paramName);
				query.setString("goodsClassId", goodsClassId);
				if(StringUtils.hasLength(parentId)){
					query.setString("parentId", parentId);
				}
				if(StringUtils.hasLength(objId)){
					query.setString("objId", objId);
				}
				List<Long>  list =  query.list();
				boolean flag = true;
				if(list.get(0) > 0){
					flag = false;
				}
				return flag;
			}
			
		});
	}

	/** 
	 * Description :  删除分类参数(by分类id)
	 * Create Date: 2010-11-30下午01:55:04 by yucy  Modified Date: 2010-11-30下午01:55:04 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void deleteByGoodsClassId(final String classId) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {	
				String hqlString = " delete from GoodsClassParamType where goodsClass.objId = :classId";
				Query query = session.createQuery(hqlString);
				query.setParameter("classId",classId );
				return query.executeUpdate();
			}
		});
	}

	/** 
	 * Description :  批量导入商品分类参数
	 * Create Date: 2011-5-27下午04:55:07 by likg  Modified Date: 2011-5-27下午04:55:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void saveGoodsClassParamTypeBatch(final Map<String, Object> param) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String goodsClassId = (String) param.get("goodsClassId");
				String goodsClassIds = (String) param.get("goodsClassIds");
				
				//筛选可被导入的参数类型
				StringBuilder hql1 = new StringBuilder();
				hql1.append(" select cp.goods_class_param_id");
				hql1.append(" from goods_class_param cp");
				hql1.append(" where cp.goods_class_param_id in (");
					hql1.append(" select gcp.goods_class_param_id");
					hql1.append(" from goods_class_param gcp");
					hql1.append(" where gcp.goods_class_param_id in (");
						hql1.append(" select max(gcp1.goods_class_param_id)");
						hql1.append(" from goods_class_param gcp1");
						hql1.append(" where goods_class_id in (:goodsClassIds)");
						hql1.append(" and gcp1.param_type = '01'");
						hql1.append(" group by gcp1.param_name )");
					hql1.append(" and not exists (");
						hql1.append(" select 'x'");
						hql1.append(" from goods_class_param gcp2");
						hql1.append(" where gcp2.goods_class_id = :goodsClassId");
						hql1.append(" and gcp2.param_type = '01'");
						hql1.append(" and gcp.param_name = gcp2.param_name");
					hql1.append(" )");
				hql1.append(" )");
				hql1.append(" or cp.param_parent_id in(");
					hql1.append(" select gcp.goods_class_param_id");
					hql1.append(" from goods_class_param gcp");
					hql1.append(" where gcp.goods_class_param_id in (");
						hql1.append(" select max(gcp1.goods_class_param_id)");
						hql1.append(" from goods_class_param gcp1");
						hql1.append(" where goods_class_id in (:goodsClassIds)");
						hql1.append(" and gcp1.param_type = '01'");
						hql1.append(" group by gcp1.param_name )");
					hql1.append(" and not exists (");
						hql1.append(" select 'x'");
						hql1.append(" from goods_class_param gcp2");
						hql1.append(" where gcp2.goods_class_id = :goodsClassId");
						hql1.append(" and gcp2.param_type = '01'");
						hql1.append(" and gcp.param_name = gcp2.param_name");
					hql1.append(" )");
				hql1.append(" )");
				Query query = session.createSQLQuery(hql1.toString());
				query.setParameterList("goodsClassIds", goodsClassIds.split(","));
				query.setParameter("goodsClassId", goodsClassId);
				List<String> paramIds = query.list();
				
				//没有可导入的参数
				if(paramIds==null || paramIds.size()<=0) {
					return null;
				}
				
				//批量导入参数
				String time = new Date().getTime()+"";
				StringBuilder hql2 = new StringBuilder();
				hql2.append(" insert into goods_class_param ");
				hql2.append(" (GOODS_CLASS_PARAM_ID, PARAM_PARENT_ID, TREE_PATH, GOODS_CLASS_ID, SORT, PARAM_CODE,CREATE_TIME,CREATOR_ID,PARAM_NAME,PARAM_TYPE, PARAM_IS_LEAF )");
					hql2.append(" select");
						hql2.append(" substr(pp.GOODS_CLASS_PARAM_ID,1,32) || '").append(time).append("' ,");
						hql2.append(" case when pp.param_parent_id is null then null else substr(pp.param_parent_id,1,32) || '").append(time).append("' end, ");
						hql2.append(" case when pp.param_parent_id is null then null else substr(pp.param_parent_id,1,32) || '").append(time).append("#' end, ");
						hql2.append(" '").append(goodsClassId).append("',");
						hql2.append(" case when c1.count1>=rownum and pp.param_type = '01' then c1.count1+c3.count3-rownum+1 else pp.sort end,");
						hql2.append(" case when c1.count1>=rownum and pp.param_type = '01' then 'T").append(goodsClassId).append("'|| lpad(c1.count1+c3.count3-rownum+1,2,'0') else null end,");
						hql2.append(" :createTime, :createUser, pp.PARAM_NAME, pp.PARAM_TYPE, pp.PARAM_IS_LEAF");
					hql2.append(" from");
						hql2.append(" (select *");
						hql2.append(" from goods_class_param p1");
						hql2.append(" where p1.goods_class_param_id in(:paramIds)");
						hql2.append(" order by param_type");
						hql2.append(" ) pp,");
						hql2.append(" (select count(*) count1");
						hql2.append(" from goods_class_param ct1");
						hql2.append(" where ct1.goods_class_param_id in(:paramIds)");
						hql2.append(" and ct1.param_type = '01'");
						hql2.append(" ) c1,");
						hql2.append(" (select count(*) count2");
						hql2.append(" from goods_class_param ct2");
						hql2.append(" where ct2.goods_class_param_id in(:paramIds)");
						hql2.append(" and ct2.param_type = '02'");
						hql2.append(" ) c2,");
						hql2.append(" (select nvl(max(ct3.sort),0) count3");
						hql2.append(" from goods_class_param ct3");
						hql2.append(" where ct3.goods_class_id = '").append(goodsClassId).append("'");
						hql2.append(" and ct3.param_type = '01'");
						hql2.append(" ) c3");
				query = session.createSQLQuery(hql2.toString());
				query.setParameterList("paramIds", paramIds);
				query.setParameter("createTime", new Date());
				query.setParameter("createUser", AuthenticationHelper.getCurrentUser(true).getObjId());
				query.executeUpdate();
				
				//组装新插入的参数id
				List<String> newParamIds = new ArrayList<String>();
				for(String paramId : paramIds) {
					newParamIds.add(paramId.substring(0, 32) + time);
				}
				
				//批量修改分类参数的paramCode（即：paramType=02）
				StringBuilder hql3 = new StringBuilder();
				hql3.append(" update goods_class_param p set p.param_code = (");
					hql3.append(" select replace(pp.param_code,'T','P') || lpad(p.sort,2,'0') ");
					hql3.append(" from goods_class_param pp ");
					hql3.append(" where pp.goods_class_param_id = p.param_parent_id )");
				hql3.append(" where p.goods_class_param_id in (:newParamIds)");
				hql3.append(" and p.param_type = '02'");
				query = session.createSQLQuery(hql3.toString());
				query.setParameterList("newParamIds", newParamIds);
				query.executeUpdate();
				
				return null;
			}
		});
	}
	
}
