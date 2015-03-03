package com.gpcsoft.goods.goods.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.dao.GoodsModifierDao;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.GoodsModifier;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class GoodsModifierDaoHibernate extends BaseGenericDaoHibernate<GoodsModifier> implements GoodsModifierDao {
	/**
     * Description : 查看该品牌下面的维护商列表的信息。
     * Create Date: Mar 27, 2010 7:59:14 PM by liujf  Modified Date: Mar 27, 2010 7:59:14 PM by liujf
     * @param   
     * @return  
     * @Exception
     */
	@SuppressWarnings("unchecked")
	public Page getGoodsModifiersListByBrandId(String brandId, int pageNumber, final int pageSize) throws DataAccessException {
		String queryStr = "SELECT distinct m.SUPPLIER_ID, o.ORG_NAME FROM GOODS_MODIFIER m, ORG_INFO o WHERE m.SUPPLIER_ID=o.ORG_INFO_ID and (m.GOODS_BRAND_ID = :brandId) ";
		
		StringBuilder recordCountQueryStr = new StringBuilder();
		recordCountQueryStr.append("SELECT count(distinct m.SUPPLIER_ID) FROM GOODS_MODIFIER m WHERE (m.GOODS_BRAND_ID = '" + brandId + "')");
		List countList = super.getSession().createSQLQuery(recordCountQueryStr.toString()).list();
		int recordCount = ((java.math.BigDecimal) countList.get(0)).intValue();
		if (recordCount <= 0) {
			return new Page();
		}
		
		int start = (pageNumber - 1) * pageSize;
		if (start < 0) {
			start = 0;
		}
		final int recordStart = start;
		Query query = super.getSession().createSQLQuery(queryStr);
		query.setParameter("brandId", brandId == null ? null:brandId, Hibernate.STRING);
		query.setFirstResult(recordStart);
		query.setMaxResults(pageSize);
		List goodsModifiersListT = query.list();
		
    	List<GoodsModifier> goodsModifiersList = new ArrayList<GoodsModifier>();
    	for(int i=0;i<goodsModifiersListT.size();i++){
    		GoodsModifier goodsModifier = new GoodsModifier();
    		Object[] obj = (Object[]) goodsModifiersListT.get(i);
    		goodsModifier.setObjId((String)obj[0]);
    		OrgInfo orgInfo = new OrgInfo();
    		orgInfo.setObjId((String)obj[0]);
    		orgInfo.setOrgName((String)obj[1]);
    		goodsModifier.setSupplier(orgInfo);
    		goodsModifiersList.add(goodsModifier);
    	}
		Page page = new Page<GoodsModifier>(start, recordCount, pageSize, goodsModifiersList);
		return page;
	}
	
	/**
     * Description : 查找该品牌下面的还没有被不是维护商的所有供应商信息。
     * Create Date: Mar 31, 2010 14:27:22 PM by liujf  Modified Date: Mar 31, 2010 14:27:22 PM by liujf
     * @param   
     * @return  
     * @Exception
     */
    @SuppressWarnings("unchecked")
	public Page getNonGoodsModifierOrgInfo(final String brandId, int pageNumber, final int pageSize) {
		StringBuilder recordCountQueryStr = new StringBuilder();
		recordCountQueryStr.append("SELECT count(o.ORG_INFO_ID) FROM ORG_INFO o WHERE o.SUPPLIER_ID is not null and o.ORG_INFO_ID not in (select distinct m.SUPPLIER_ID from GOODS_MODIFIER m where m.GOODS_BRAND_ID= '" + brandId + "')");
		List countList = super.getSession().createSQLQuery(recordCountQueryStr.toString()).list();
		int recordCount = ((java.math.BigDecimal) countList.get(0)).intValue();
		if (recordCount <= 0) {
			return new Page();
		}
		
		int start = (pageNumber - 1) * pageSize;
		if (start < 0) {
			start = 0;
		}
		final int recordStart = start;
		String queryStr = "SELECT o.ORG_INFO_ID, o.ORG_NAME FROM ORG_INFO o WHERE o.SUPPLIER_ID is not null and o.ORG_INFO_ID not in (select distinct m.SUPPLIER_ID from GOODS_MODIFIER m where m.GOODS_BRAND_ID= :brandId) ";
		Query query = super.getSession().createSQLQuery(queryStr);
		query.setParameter("brandId", brandId == null ? null:brandId, Hibernate.STRING);
		query.setFirstResult(recordStart);
		query.setMaxResults(pageSize);
		List orgInfosListT = query.list();

		List<OrgInfo> orgInfosList = new ArrayList<OrgInfo>();
    	for(int i=0;i<orgInfosListT.size();i++){
    		OrgInfo orgInfo = new OrgInfo();
    		Object[] obj = (Object[]) orgInfosListT.get(i);
    		orgInfo.setObjId((String)obj[0]);
    		orgInfo.setOrgName((String)obj[1]);
    		orgInfosList.add(orgInfo);
    	}
		Page page = new Page<OrgInfo>(start, recordCount, pageSize, orgInfosList);
		return page;
    }
    
    /**
     * 删除该维护商在该品牌下面的所有信息(在给指定维护商选定商品分类的时候就要先做这个操作)。
     * Create Date: Mar 31, 2010 16:17:01 PM by liujf  Modified Date: Mar 31, 2010 16:17:01 PM by liujf
     * @param [] orgIds
     * @param brandId
     */
    public void removeGoodsModifierBrandClass(String[] orgIds, String brandId) {
		StringBuilder removeSQL = new StringBuilder();
		removeSQL.append("delete from GOODS_MODIFIER m ");
		removeSQL.append(" where m.GOODS_BRAND_ID='" + brandId + "'");
		StringBuilder orgIdStr = new StringBuilder();
		orgIdStr.append(" and m.SUPPLIER_ID in (");
		for (String orgId : orgIds) {
			orgIdStr.append(" '" + orgId + "',");
		}
		removeSQL.append(orgIdStr.deleteCharAt(orgIdStr.length() - 1).append(" )").toString());

		super.getSession().createSQLQuery(removeSQL.toString()).executeUpdate();
    }
    
    /**
     * 清空该维护商在该品牌下面所维护的分类信息(在给指定维护商选定商品分类的时候就要先做这个操作)。
     * Create Date: Mar 31, 2010 16:17:01 PM by liujf  Modified Date: Mar 31, 2010 16:17:01 PM by liujf
     * @param [] orgIds
     * @param brandId
     */
    public void resetGoodsModifierBrandClass(String[] orgIds, String brandId) {
		StringBuilder removeSQL = new StringBuilder();
		removeSQL.append("update GOODS_MODIFIER m ");
		removeSQL.append("   set m.GOODS_CLASS_ID = null ");
		removeSQL.append(" where m.GOODS_BRAND_ID='" + brandId + "'");
		StringBuilder orgIdStr = new StringBuilder();
		orgIdStr.append(" and m.SUPPLIER_ID in (");
		for (String orgId : orgIds) {
			orgIdStr.append(" '" + orgId + "',");
		}
		removeSQL.append(orgIdStr.deleteCharAt(orgIdStr.length() - 1).append(" )").toString());

		super.getSession().createSQLQuery(removeSQL.toString()).executeUpdate();
    }
    
    /**
     * Description：查看品牌维护商的信息.
     * Created Date:Jun 17, 2010 10:45:53 AM by liujf Modified Date:Jun 17, 2010 10:45:53 AM by liujf
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public Page getGoodsModifier(String supplierId, int pageNumber, final int pageSize) throws Exception {
		StringBuilder recordCountQueryStr = new StringBuilder();
		recordCountQueryStr.append("SELECT count(m.objId) FROM GoodsModifier m WHERE (:supplierId is null or m.supplier.objId = :supplierId) and m.goodsBrand.auditStatus in ('3', '7') ");
		Query queryCount = super.getSession().createQuery(recordCountQueryStr.toString());
		queryCount.setParameter("supplierId", supplierId == null ? null:supplierId, Hibernate.STRING);
		List<Long> countList = queryCount.list();
		Long recordCount = (Long)countList.get(0);
		if (recordCount <= 0) {
			return new Page();
		}
		
		int start = (pageNumber - 1) * pageSize;
		if (start < 0) {
			start = 0;
		}
		final int recordStart = start;
		StringBuilder querySQL = new StringBuilder();
		querySQL.append("SELECT m FROM GoodsModifier m WHERE (:supplierId is null or m.supplier.objId = :supplierId) and m.goodsBrand.auditStatus in ('3', '7')  ");
		querySQL.append(" order by m.goodsBrand.brandName ");
		Query query = super.getSession().createQuery(querySQL.toString());
		query.setParameter("supplierId", supplierId == null ? null:supplierId, Hibernate.STRING);
		query.setFirstResult(recordStart);
		query.setMaxResults(pageSize);
		List<GoodsModifier> goodsModifiersListT = query.list();
    	
		List<GoodsModifier> goodsModifiersList = null;
		if (goodsModifiersListT != null && !goodsModifiersListT.isEmpty()) {
			goodsModifiersList = new ArrayList<GoodsModifier>();
			for (GoodsModifier goodsModifierT : goodsModifiersListT) {
				GoodsModifier goodsModifier = new GoodsModifier();
				goodsModifier.setObjId(goodsModifierT.getObjId());
				if (goodsModifierT.getGoodsBrand() != null) {
					GoodsBrand goodsBrand = new GoodsBrand();
					goodsBrand.setObjId(goodsModifierT.getGoodsBrand().getObjId());
					goodsBrand.setBrandName(goodsModifierT.getGoodsBrand().getBrandName());
					goodsModifier.setGoodsBrand(goodsBrand);
				}
				goodsModifiersList.add(goodsModifier);
			}
		}
		
		Page page = new Page<GoodsModifier>(start, recordCount, pageSize, goodsModifiersList);
		
		return page;
	}
	
	/** 
	 * Description :  指定维护商品品牌列表
	 * Create Date: 2010-8-2下午06:15:04 by sunl  Modified Date: 2010-8-2下午06:15:04 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<GoodsModifier> getGoodsBrandBySupplierId(Page<GoodsModifier> page,HttpServletRequest request) throws Exception {
		StringBuilder hql = new StringBuilder();
		String temp = "select count(t.objId) from GoodsModifier t where t.supplier.objId =:orgInfoId";
		hql.append(" from GoodsModifier t left join fetch t.goodsBrand");
		hql.append(" where t.supplier.objId =:orgInfoId");
		hql.append(" order by t.objId desc");
		Query queryCount = this.getSession().createQuery(temp);
		queryCount.setString("orgInfoId",AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		Query query = this.getSession().createQuery(hql.toString());
		query.setString("orgInfoId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
		Long totalRow = (Long)queryCount.list().get(0);
		List list = query.list();
		Page<GoodsModifier> pageData = new Page<GoodsModifier>(page.getStart(),totalRow,page.getPageSize(),list );
		return pageData;	
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.goods.goods.dao.GoodsModifierDao#getUnSpecifiedGoods(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Page<GoodsModifier> getUnSpecifiedGoods(final Page<GoodsModifier> page ,final String goodsClassName, final String goodsBrandName, final String order, final String order_flag, final String orgInfoId) throws Exception {
		return (Page<GoodsModifier>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder("select * from (select a.*,e.supplier_id from " +
						"(select c.goods_brand_id ,c.brand_name, d.goods_class_id, d.goods_class_name,c.belongs_id " +
						"from goods_brand_to_class b,  goods_brand c ,  goods_class d " +
						"where b.goods_brand_id = c.goods_brand_id and b.goods_class_id = d.goods_class_id " +
						"and c.manager_status = :useStatus and c.sell_status = :sellStatus ) a " +
						"left join goods_modifier e on a.goods_class_id = e.goods_class_id " +
						"and a.goods_brand_id = e.goods_brand_id ) " +
						"where supplier_id is null ");
				
				// 所属机构
				if(null != orgInfoId && !"".equals(orgInfoId)){
					sql.append(" and belongs_id = :orgInfoId");
				}
				if( null != goodsClassName && !"".equals(goodsClassName)) {
					sql.append(" and goods_class_name like :goodsClassName");
				}
				if( null != goodsBrandName && !"".equals(goodsBrandName)) {
					
					sql.append(" and brand_name like :goodsBrandName");
				}
				//排序
				if(StringUtils.hasLength(order)){
					if("goodsClass.goodsClassName".equals(order)) {
						sql.append(" order by goods_class_id");
					}else if("goodsBrand.brandName".equals(order)) {
						sql.append(" order by goods_brand_id");
					}
					sql.append(order_flag.equals("true") ? " desc ":" asc ");
				} else {
					sql.append(" order by goods_class_id desc");
				}
				Query query = session.createSQLQuery(sql.toString());
				
				query.setString("useStatus", GoodsEnum.USE_VALID);
				query.setString("sellStatus", GoodsEnum.SELL_START);
				
				//所属机构
				if(null != orgInfoId && !"".equals(orgInfoId)){
					query.setString("orgInfoId",orgInfoId);
				}
				if( null != goodsClassName && !"".equals(goodsClassName)) {
					query.setString("goodsClassName", "%"+goodsClassName+"%");
				}
				if( null != goodsBrandName && !"".equals(goodsBrandName)) {
					
					query.setString("goodsBrandName", "%"+goodsBrandName+"%");
				}
				
				// 获得总记录数
				int totalRow =  query.list().size();
				
				// 按页取数据
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				List<String[]> unSpecifiedGoods = query.list();
				
				List<GoodsModifier> list = new ArrayList<GoodsModifier>();
				
				// 将数封装成对象
				for (int i = 0; i < unSpecifiedGoods.size(); i++) {
					Object[]obj = unSpecifiedGoods.get(i);
					GoodsModifier goodsModifier = new GoodsModifier();
					goodsModifier.setObjId("");
					GoodsBrand goodsBrand = new GoodsBrand();
					goodsBrand.setObjId((String) obj[0]);
					goodsBrand.setBrandName((String) obj[1]);
					goodsModifier.setGoodsBrand(goodsBrand);
					GoodsClass goodsClass = new GoodsClass();
					goodsClass.setObjId((String) obj[2]);
					goodsClass.setGoodsClassName((String) obj[3]);
					goodsModifier.setGoodsClass(goodsClass);
					list.add(goodsModifier);
				}
				
				Page<GoodsModifier> pageData = new Page<GoodsModifier>(page.getStart(),totalRow,page.getPageSize(),list );
				return pageData;
			}});
	}
	/** 
	 * Description : 修改商品的维护商  
	 * Create Date: 2010-8-6上午11:09:34 by guoyr  Modified Date: 2010-8-6上午11:09:34 by guoyr
	 * @param goodsClassId 商品的分类  
	 * @param goodsBrandId 商品的品牌
	 * @param supplierId 商品的维护的供应商
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void updateGoodsModifier(final String objId,final String goodsClassId, final String goodsBrandId, final String supplierId){
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder("");
				sql.append("update goods_modifier t  set t.supplier_id = :supplierId  where t.goods_modifier_id = :objId or (t.goods_class_id = :goodsClassId and t.goods_brand_id in (select b.goods_brand_id from goods_brand b where b.current_valid_id = :goodsBrandId))");
				Query query = session.createSQLQuery(sql.toString());
				
				query.setString("objId", objId);
				query.setString("supplierId", supplierId);
				query.setString("goodsClassId", goodsClassId);
				query.setString("goodsBrandId", goodsBrandId);
				query.executeUpdate();
				return null;
			}
	
		});
	}

	/** 
	 * Description :  为新品牌更新维护商
	 * Create Date: 2010-9-2下午04:34:16 by yucy  Modified Date: 2010-9-2下午04:34:16 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void updateBrandForNewBrand(final String oldBrandId, final String objId)throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				String newModiferId = UnifyDBSqlFactory.getIUnifyDBSqlImpl().subString("m.goods_modifier_id", 22, 10)+"||"+
								UnifyDBSqlFactory.getIUnifyDBSqlImpl().subString("m.supplier_id", 22, 10)+"||"+
								UnifyDBSqlFactory.getIUnifyDBSqlImpl().subString("m.goods_brand_id", 21,12);
				
				String Sql = "insert into GOODS_MODIFIER (GOODS_MODIFIER_ID,GOODS_CLASS_ID,SUPPLIER_ID,GOODS_BRAND_ID ,CREATE_TIME,CREATOR_ID)"+
				" select replace("+newModiferId+",'c','_'), m.GOODS_CLASS_ID,m.supplier_id, :objId ,m.CREATE_TIME, m.CREATOR_ID"+
				" from goods_modifier m where m.goods_brand_id = :oldBrandId ";
				Query query = session.createSQLQuery(Sql);
				
				query.setParameter("objId", objId);
				
				query.setParameter("oldBrandId", oldBrandId);
				
				query.executeUpdate();
				return true;
			}
		});
	}
}
