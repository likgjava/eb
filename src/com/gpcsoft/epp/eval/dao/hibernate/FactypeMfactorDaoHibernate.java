package com.gpcsoft.epp.eval.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.eval.dao.FactypeMfactorDao;
import com.gpcsoft.epp.eval.domain.CongruousFactorType;
import com.gpcsoft.epp.eval.domain.FactypeMfactor;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class FactypeMfactorDaoHibernate extends BaseGenericDaoHibernate<FactypeMfactor> implements FactypeMfactorDao {

	/**
	 * @Description: 根据指标ID删除指标与包件中间表
	 * @param factorId
	 * @return void
	 * @throws Exception
	 * @Create Date 2010-10-11 下午04:11:32 By wanghz
	 */
	public void removeFactypeMfactorByFactorId(final String factorId){
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String hql = " delete FactypeMfactor where factor.objId=:factorId ";
				session.createQuery(hql).setString("factorId", factorId).executeUpdate();
				return null;
		}});
	}
	/**
	 * FuncName: getCongruousFactorList
	 * Description :  获取指标分类集合(电子评标系统接口)
	 * @param projectCode:项目编号,packCode:包组编号
	 * @return List<CongruousFactorType>
	 * @author: shenjz
	 * @Create Date:2011-3-24  下午01:35:07
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-24  下午01:35:07
	 */
	public List<FactypeMfactor> getCongruousFactorTypList(String projectCode,String packCode){
		StringBuffer sb = new StringBuffer();
		sb.append("select c2 from CongruousFactorType c1,FactypeMfactor c2 where c1.objId = c2.factorType.objId and c1.projId=(select p.objId from Project p where p.projCode='"+projectCode+"')");
		if(packCode!=null&&!"".equals(packCode)){
			sb.append(" and c2.projId = (select p.objId from Project p where p.projCode='"+packCode+"' and p.parentId=(select p.objId from Project p where p.projCode='"+projectCode+"'))");
		}
		return this.findbyHql(sb.toString());
	}
}
