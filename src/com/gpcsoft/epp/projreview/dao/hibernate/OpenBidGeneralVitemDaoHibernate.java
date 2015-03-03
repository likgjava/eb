package com.gpcsoft.epp.projreview.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.projreview.dao.OpenBidGeneralVitemDao;
import com.gpcsoft.epp.projreview.domain.OpenBidGeneralVitem;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class OpenBidGeneralVitemDaoHibernate extends BaseGenericDaoHibernate<OpenBidGeneralVitem> implements OpenBidGeneralVitemDao {

	/**
	 * FuncName:getAllOpenBidGeneralVitem
	 * Description :根据项目获得所有开标一览表明细  
	 * @param   projectId:项目主键
	 * @return  List<OpenBidGeneralVitem>
	 * @author liuke
	 * @Create Date: 2010-10-11下午01:39:25 
	 * @Modifier   liuke
	 * @Modified Date: 2010-10-11下午01:39:25 s 
	 */
	public List<OpenBidGeneralVitem> getAllOpenBidGeneralVitem(String projectId) {
		List<OpenBidGeneralVitem> list = this.findbyHql("from OpenBidGeneralVitem t where t.genviewDefine.project = '"+projectId+"' or  t.genviewDefine.project.parentId = '"+projectId+"'");
		return list;
	}
	
	/**
	 * FuncName:getOpenBidGeneralVitemListByOpenbidGeneralview
	 * Description :根据开标一览表获得开标一览表明细
	 * @param openbidGeneralviewId
	 * @return  List<OpenBidGeneralVitem>
	 * @author liuke
	 * @Create Date: 2010-12-15上午11:52:14 
	 * @Modifier  liuke
	 * @Modified Date: 2010-12-15上午11:52:14  
	 */
	public List<OpenBidGeneralVitem> getOpenBidGeneralVitemListByOpenbidGeneralview(
			String openbidGeneralviewId) {
		return this.findbyHql("from OpenBidGeneralVitem t where t.openbidGeneralview.objId =?", openbidGeneralviewId);
	}

	/**
	 * FuncName:removeAllOpenBidGeneralVitemByProject
	 * Description:根据项目删除开标一览表明细对象
	 * @param projectId
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-12-15下午04:28:08 
	 * @Modifier    liuke
	 * @Modified Date: 2010-12-15下午04:28:08
	 */
	@SuppressWarnings("unchecked")
	public void removeAllOpenBidGeneralVitemByProject(final String projectId) {
		this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer();
				sql.append(" delete from ecp_openbid_general_v_item eogvi ");
				sql.append("  where eogvi.gen_view_define_id in ");
				sql.append(" (select egvd.gen_view_define_id ");
				sql.append(" from ecp_gen_view_define egvd ");
				sql.append("  where egvd.tenderid = '"+projectId+"' or egvd.tenderid in  ");
				sql.append(" (select etp.tenderid from ecp_tender_project etp where etp.parent_id = '"+projectId+"' )) ");
				Query query  = session.createSQLQuery(sql.toString());
				query.executeUpdate();
				return null;
			}});
	}

	
	/**
	 * FuncName : removeOpenBidGeneralVitemByOpenBidGeneralId
	 * Description :  根据开标一览表表头删除开标一览表信息
	 * Create Date: 2011-11-1上午10:37:16 by liuke
	 * Modified Date: 2011-11-1上午10:37:16 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public void removeOpenBidGeneralVitemByOpenBidGeneralId(
		final	String openBidGeneralId) {
		this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer();
				sql.append(" delete from ecp_openbid_general_v_item eogvi ");
				sql.append(" where eogvi.GEN_VIEW_DEFINE_ID = '"+openBidGeneralId+"'  ");
				Query query  = session.createSQLQuery(sql.toString());
				query.executeUpdate();
				return null;
			}});
		
	}

	/**
	 * FuncName : getOpenBidGeneralVitemByGenViewIdAndFactorId
	 * Description :  
	 * @param genViewId
	 * @param factorId
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-15 13:31
	 */
	public OpenBidGeneralVitem getOpenBidGeneralVitemByGenViewIdAndFactorId(
			String genViewId, String factorId) {
		List<OpenBidGeneralVitem> openBidGeneralVitemList = findbyHql("from OpenBidGeneralVitem t where t.openbidGeneralview.objId = ? and t.factorId = ?", genViewId, factorId);
		if(openBidGeneralVitemList != null && openBidGeneralVitemList.size() > 0)
			return openBidGeneralVitemList.get(0);
		return null;
	}

	/**
	 * FuncName : removeOpenBidGeneralVitemByOpenbidGeneralviewId
	 * Description :  根据开标一览删除开标一览表信息
	 * @param   
	 * @return  
	 * @Exception
	 * @author zhouzhanghe
	 * @Created date 2011-11-16 10:25
	 */
	@SuppressWarnings("unchecked")
	public void removeOpenBidGeneralVitemByOpenbidGeneralviewId(
			final String openbidGeneralviewId) {
		this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer();
				sql.append(" delete from ecp_openbid_general_v_item eogvi ");
				sql.append(" where eogvi.GEN_VIEW_ID = ?");
				Query query  = session.createSQLQuery(sql.toString());
				query.setParameter(0, openbidGeneralviewId);
				query.executeUpdate();
				return null;
			}});
	}
}
