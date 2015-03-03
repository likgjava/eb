package com.gpcsoft.epp.projreview.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import org.hibernate.Query;

import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.projreview.dao.GenviewDefineDao;
import com.gpcsoft.epp.projreview.domain.GenviewDefine;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class GenviewDefineDaoHibernate extends BaseGenericDaoHibernate<GenviewDefine> implements GenviewDefineDao {
	
	/**
	 * FuncName:getGenviewDefineListByProjectId
	 * Description :根据项目获得开标一览表指标列表  
	 * @param   projectId:项目主键
	 * @return  List<GenviewDefine>
	 * @author 	   liuke
	 * @Create Date: 2010-10-9上午11:50:59 
	 * @Modifier   liuke
	 * @Modified Date: 2010-10-9上午11:50:59 
	 */
	public List<GenviewDefine> getGenviewDefineListByProjectId(String projectId) {
		String hql = "from GenviewDefine t where t.project.objId = '"+projectId+"' or t.project.parentId ='"+projectId+"' order by t.showNo";
		List<GenviewDefine> list = this.findbyHql(hql);
		return list;
	}

	/**
	 * FuncName:removeGenviewDefineByCongruousFactorId
	 * Description :根据指标删除开标一览表指标  
	 * @param   factorId:指标主键
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-10-9下午01:22:46
	 * @Modifier liuke
	 * @Modified Date: 2010-10-9下午01:22:46
	 * @Modifier chenhj 20111017 修改删除指标  t.factor.objId 改为  t.factorId
	 * @Modified Date: 2011-10-17下午01:22:46   
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void removeGenviewDefineByCongruousFactorId(final String factorId) {
		this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "delete from GenviewDefine t where t.factorId=?";
				Query query = session.createQuery(hql);
				query.setString(0, factorId).executeUpdate();
				return null;
			}
		});
	}

	
	
	/**
	 * FuncName: saveGenviewDefineByPriceList
	 * Description :  根据开标一览表保存指标数据项
	 * @param 
	 * @param name
	 * @param subProject void
	 * @author: liuke
	 * @Create Date:2011-9-27  上午10:07:17
	 * @Modifier: liuke
	 * @Modified Date:2011-9-27  上午10:07:17
	 */
	@SuppressWarnings("unchecked")
	public void saveGenviewDefineByPriceList(final List<String> name,final Project project,final List<Project> subProjList) {
		this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				/*删除原来的开标一览表相关数据*/
				String del_ECP_GEN_VIEW_DEFINE = "delete from ecp_gen_view_define t where t.tenderid = '"+project.getObjId()+"' or  t.tenderid in (select t.tenderid from ecp_tender_project t where t.parent_id = '"+project.getObjId()+"')";
				session.createSQLQuery(del_ECP_GEN_VIEW_DEFINE).executeUpdate();	
				
				/*保存开标一览表数据项  */
				int no=2;  //排列顺序
				for (Iterator iterator2 = name.iterator(); iterator2.hasNext();) {
					String name = (String) iterator2.next();
					for (Iterator iterator = subProjList.iterator(); iterator.hasNext();) {
							Project subProj = (Project) iterator.next();
							String objId =UUID.randomUUID().toString();
							String insert_ECP_GEN_VIEW_DEFINE = "insert into ecp_gen_view_define(GEN_VIEW_DEFINE_ID,TENDERID,FACTOR_ID,FACTOR_NAME,SHOW_NO,CURRENT_VALID_ID) values('"+objId+"','"+subProj.getObjId()+"','"+objId+"','"+name+"','"+no+"','Price.xml')";
							session.createSQLQuery(insert_ECP_GEN_VIEW_DEFINE).executeUpdate();	
					}  
					no++;
				}
				return null;
			}});
	}

	
	
	/**
	 * FuncName : 
	 * Description :  
	 * Create Date: 2011-11-10上午10:39:41 by liuke
	 * Modified Date: 2011-11-10上午10:39:41 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveGenviewDefineEx(GenviewDefine genviewDefine) {
	   this.getHibernateTemplate().save(genviewDefine);
	}

	/**
	 * FuncName: removeGenviewDefineByProjectId
	 * Description :  根据开标一览表表头关联的项目或包组主键删除开标一览表表头
	 * @param   projectId
	 * @return  void
	 * @author zhouzhanghe
	 * @Create Date: 2011-11-30 17:44
	 */
	@SuppressWarnings("unchecked")
	public void removeGenviewDefineByProjectId(final String projectId) {
		this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "delete from GenviewDefine t where t.project.objId=?";
				Query query = session.createQuery(hql);
				return query.setString(0, projectId).executeUpdate();
			}
		});
	}
	
	/**
	 * 使用SQL方式来保存GenviewDefine
	 * @param genviewDefine
	 * @author zhouzhanghe
	 * @Created date 2011-12-01 09:33
	 */
	@SuppressWarnings("unchecked")
	public void saveGenviewDefineUsingSQL(final GenviewDefine genviewDefine){
		this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
					String insert_ECP_GEN_VIEW_DEFINE = "insert into ecp_gen_view_define(GEN_VIEW_DEFINE_ID,TENDERID,FACTOR_ID,FACTOR_NAME,SHOW_NO,CURRENT_VALID_ID) values(?,?,?,?,?,?)";
					SQLQuery sqlQuery = session.createSQLQuery(insert_ECP_GEN_VIEW_DEFINE);
					sqlQuery.setParameter(0, genviewDefine.getObjId());
					sqlQuery.setParameter(1, genviewDefine.getProject().getObjId());
					sqlQuery.setParameter(2, genviewDefine.getFactorId());
					sqlQuery.setParameter(3, genviewDefine.getFactorName());
					sqlQuery.setInteger(4, genviewDefine.getShowNo() == null ? 0 : genviewDefine.getShowNo().intValue());
					sqlQuery.setParameter(5, genviewDefine.getCurrentId());
					return sqlQuery.executeUpdate();
				}
			});
	}

	/**
	 * 使用SQL方式来保存genviewDefineList
	 * @param genviewDefineList
	 * @author zhouzhanghe
	 * @Created date 2011-12-01 09:33
	 */
	public void saveGenviewDefineUsingSQL(List<GenviewDefine> genviewDefineList) {
		for(GenviewDefine genviewDefine : genviewDefineList){
			saveGenviewDefineUsingSQL(genviewDefine);
		}
	}
}
