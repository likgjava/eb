package com.gpcsoft.epp.eval.dao.hibernate;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.eval.dao.CongruousFactorTypeDao;
import com.gpcsoft.epp.eval.domain.CongruousFactorType;

@Repository
public class CongruousFactorTypeDaoHibernate extends BaseGenericDaoHibernate<CongruousFactorType> implements CongruousFactorTypeDao {

	/**
	 * @Description: 获取指标分类
	 * @param projectId
	 * @return List<CongruousFactorType>
	 * @throws Exception
	 * @Create Date 2010-9-17 上午11:04:01 By wanghz
	 */
	public List<CongruousFactorType> getCongruousFactorTypeByProjectId(String projectId){
		StringBuffer hql = new StringBuffer();
		hql.append(" from CongruousFactorType as congruousFactorType ");
		hql.append(" where congruousFactorType.projId =? ");
		hql.append(" order by congruousFactorType.sort asc");
		return this.findbyHql(hql.toString(), new Object[]{projectId});
	}
	
	/**
	 * @Description: 获取指标分类
	 * @param projectId
	 * @return List<CongruousFactorType>
	 * @throws Exception
	 * @Create Date 2010-9-17 上午11:04:01 By wanghz
	 */
	public List<CongruousFactorType> getGradeCongruousFactorTypeByProjectId(String projectId){
		StringBuffer hql = new StringBuffer();
		hql.append(" from CongruousFactorType as congruousFactorType ");
		hql.append(" where congruousFactorType.projId =? ");
		hql.append(" and congruousFactorType.typeCode is null ");
		hql.append(" order by congruousFactorType.sort asc");
		return this.findbyHql(hql.toString(), new Object[]{projectId});
	}
	
	/**
	 * @Description: 删除指标分类
	 * @param congruousFactorTypeList 不需要删除的指标分类
	 * @param projectId 项目ID
	 * @return void
	 * @throws Exception
	 * @Create Date 2010-9-19 上午12:50:33 By wanghz
	 */
	public void removeCongruousFactorType(final List<CongruousFactorType> congruousFactorTypeList,final String projectId)throws EsException{
		 getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Set<String> tempSet = new HashSet<String>(1);
				for (CongruousFactorType congruousFactorType:congruousFactorTypeList) {
					if (null != congruousFactorType.getObjId() && 
						!"".equals(congruousFactorType.getObjId()) && 
						!"NULL".equals(congruousFactorType.getObjId().toUpperCase()) && 
						!"UNDEFINED".equals(congruousFactorType.getObjId().toUpperCase())) {
						tempSet.add(congruousFactorType.getObjId());
					}
				}
				if (tempSet.size()>0) {
					String hql = "select factorType.objId from  CongruousFactorType as factorType where factorType.objId not in (:ids) and factorType.projId=:projectId ";
					List<String> ids = session.createQuery(hql).setParameterList("ids", tempSet.toArray()).setString("projectId", projectId).list();
					if (null != ids && !ids.isEmpty()) {
						hql = " delete FactypeMfactor factypeMfactor  where factypeMfactor.factorType.objId in (:ids) ";
						session.createQuery(hql).setParameterList("ids", ids).executeUpdate();
						hql = " delete CongruousFactor factor where factor.factorType.objId in (:ids)";
						session.createQuery(hql).setParameterList("ids", ids).executeUpdate();
						hql = " delete CongruousFactorType factorType where factorType.objId in (:ids)";
						session.createQuery(hql).setParameterList("ids", ids).executeUpdate();
					}
				}
				return null;
			}});
	}
	
	/** 
	 * Description :  查询该项目下的指标分类权重值总和
	 * Create Date: 2010-9-27下午01:40:29 by yangx  Modified Date: 2010-9-27下午01:40:29 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Long getWeightCoefficientSumByProjectId(final String projectId){
		return (Long)getHibernateTemplate().execute(new HibernateCallback(){
			public Long doInHibernate(Session session)throws HibernateException, SQLException {
				String hql = " select sum(t.weightCoefficient) from CongruousFactorType t where t.projId=:projectId ";
				Long scoreSum = (Long) session.createQuery(hql).setString("projectId", projectId).list().get(0);
				return scoreSum;
			}});
	}
	
	/**
	 * FuncName: getCongruousFactorTypeList
	 * Description : 获取到指标分类集合
	 * @param 
	 * @return List<CongruousFactorType>
	 * @author: shenjz
	 * @Create Date:2011-5-9  上午11:13:20
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-9  上午11:13:20
	 */
	public List<CongruousFactorType> getCongruousFactorTypeList(String parentObjId,String projectId){
		List<CongruousFactorType> list = null;
		StringBuffer hql = new StringBuffer();
		hql.append("select objId, typeName, isLeaf");
		hql.append(" from CongruousFactorType t");
		hql.append(" where t.parent.objId = ?");
		if(parentObjId==null){
			hql.append(" and t.projId ='"+projectId+"'");
		}
		hql.append(" order by sort ");
		String strHql = hql.toString();
		if(null == parentObjId || ("").equals(parentObjId) || ("undefined").equals(parentObjId)){
			strHql = strHql.replaceAll("= \\?", "is null");
			list = super.findbyHql(strHql);
		}else{
			list = super.findbyHql(strHql, parentObjId);
		}
		return  HqlResultConvertUtils.hqlResultConvert(list, new String[] {"objId","typeName","isLeaf"}, CongruousFactorType.class);
	}
	/**
	 * FuncName: removeCongruousFactorTypeCascade
	 * Description :  删除指标类别以及其下的条目及其中间表
	 * @param 
	 * @param objId
	 * @throws EsException void
	 * @author: shenjz
	 * @Create Date:2011-5-10  下午02:52:12
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-10  下午02:52:12
	 */
	@SuppressWarnings("unchecked")
	public void removeCongruousFactorTypeCascade(final String objId)throws EsException{
		 getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
						String hql;
						hql = " delete FactypeMfactor factypeMfactor  where factypeMfactor.factorType.objId ='"+objId+"'";
						session.createQuery(hql).executeUpdate();
						hql = " delete CongruousFactor factor where factor.factorType.objId='"+objId+"'";
						session.createQuery(hql).executeUpdate();
						hql = " delete CongruousFactorType factorType where factorType.objId='"+objId+"'";
						session.createQuery(hql).executeUpdate();
				return null;
			}});
	}
	/**
	 * FuncName: getCongruousFactorList
	 * Description :  获取指标分类集合(电子评标系统接口)
	 * @param 
	 * @return List<CongruousFactorType>
	 * @author: shenjz
	 * @Create Date:2011-3-24  下午01:35:07
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-24  下午01:35:07
	 */
	public List<CongruousFactorType> getCongruousFactorTypList(String projectCode,String packCode){
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct c1 from CongruousFactorType c1,FactypeMfactor c2 where c1.objId = c2.factorType.objId and c1.projId=(select p.objId from Project p where p.projCode='"+projectCode+"')");
		if(packCode!=null&&!"".equals(packCode)){
			sb.append(" and c2.projId = (select p.objId from Project p where p.projCode='"+packCode+"' and p.parentId=(select p.objId from Project p where p.projCode='"+projectCode+"'))");
		}
		return this.findbyHql(sb.toString());
	} 
	/**
	 * FuncName: getListForCheck
	 * Description :  根据编号和项目主键校验编号是否唯一
	 * @param 
	 * @param typeCode
	 * @param projectId
	 * @return List<CongruousFactorType>
	 * @author: shenjz
	 * @Create Date:2011-6-2  下午04:09:13
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-2  下午04:09:13
	 */
	public List<CongruousFactorType> getListForCheck(String typeCode,String projectId){
		StringBuffer sb = new StringBuffer();
		sb.append("from CongruousFactorType c1 where c1.typeCode='"+typeCode+"' and c1.projId='"+projectId+"'");
		return this.findbyHql(sb.toString());
	}
	
	
	/**
	 * FuncName: getListForCheck
	 * Description :  根据项目Id取得所有指标分类
	 * @param 
	 * @param projectId
	 * @return List<CongruousFactorType>
	 * @author: yangyc
	 * @Create Date:2011-11-29  16:50:13
	 * @Modifier: yangyc
	 * @Modified Date:2011-11-29  16:50:13
	 */
	public List<CongruousFactorType> getAllFactorTypeByProjectId(String projectId) {
		StringBuffer sb = new StringBuffer();
		sb.append("from CongruousFactorType c where c.projId='"+projectId+"'");
		return this.findbyHql(sb.toString()) ;
	}
}

