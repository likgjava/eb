package com.gpcsoft.epp.expertrule.dao.hibernate;

import java.sql.SQLException;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.expertrule.dao.ExpertRulePurchaseCategoryDao;
import com.gpcsoft.epp.expertrule.domain.ExpertRulePurchaseCategory;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class ExpertRulePurchaseCategoryDaoHibernate extends BaseGenericDaoHibernate<ExpertRulePurchaseCategory> implements ExpertRulePurchaseCategoryDao {
	@SuppressWarnings("unchecked")
	public void saveExpertRulePurchaseCategory(final ExpertRulePurchaseCategory category) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer();
				String isleaf ="";
				if(category.getIsLeaf()){
					isleaf = "1";
				}else{
					isleaf = "0";
				}
				sql.append("insert into PURCATALOG_CATEGORY_EXP (ID,PARENT_ID,CATEGORY_CODE,CATEGORY_NAME,PURCATEGORY_IS_LEAF,RELEASE_STATUS,RELEASE_DATE) values('"+category.getObjId()+"','"+category.getParent().getObjId()+"','"+category.getCategoryCode()+"','"+category.getCategoryName()+"','"+isleaf+"','"+category.getReleaseStatus()+"',sysdate)");
				session.createSQLQuery(sql.toString()).executeUpdate();
				return null;
			}});
	}

	/**
	 * 
	 * Description :删除专家库品目 
	 * Create Date: 2010-11-18上午10:46:44 by liuke  Modified Date: 2010-11-18上午10:46:44 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public void removeAllExpertRulePurchaseCategory() {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer();
				sql.append("delete from PURCATALOG_CATEGORY_EXP where 1=1");
				session.createSQLQuery(sql.toString()).executeUpdate();
				return null;
			}});
		
	}

}
