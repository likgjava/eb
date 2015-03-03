package com.gpcsoft.epp.eval.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.eval.dao.CongFactorResponseDao;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;

@Repository
@SuppressWarnings(value={"unchecked"})
public class CongFactorResponseDaoHibernate extends BaseGenericDaoHibernate<CongFactorResponse> implements CongFactorResponseDao {
	
	/**
	 * @Description: 根据指标响应获取可用删除的指标响应
	 * @param congFactorResponseList
	 * @return List<CongFactorResponse>
	 * @throws Exception
	 * @Create Date 2010-8-6 下午02:07:41 By wanghz
	 */
	@SuppressWarnings("unchecked")
	public List<CongFactorResponse> getCongFactorResponseIsMayRemove(final List<CongFactorResponse> congFactorResponseList,final String bidId) {
		return (List<CongFactorResponse>) getHibernateTemplate().execute(new HibernateCallback(){
			public List<CongFactorResponse> doInHibernate(Session session)throws HibernateException, SQLException {
				List<CongFactorResponse> congFactorResponseRemoveList = new ArrayList<CongFactorResponse>(0);
				StringBuffer hql = new StringBuffer();
				hql.append("select distinct PCFR.congFactorResponse.objId,PCFR.objId ");
				hql.append("from PackCongFactorResponse as PCFR ,BidPackage as bidPackage ");
				hql.append("where PCFR.bidPackageId = bidPackage.objId and bidPackage.bid.objId = ? ");
				hql.append("and PCFR.congFactorResponse.objId not in ( ");
				StringBuffer congFactorResponseIds = new StringBuffer();
				for(CongFactorResponse congFactorResponse:congFactorResponseList){
					if(null != congFactorResponse.getObjId()){
						hql.append("?,");
						congFactorResponseIds.append(congFactorResponse.getObjId());
						congFactorResponseIds.append(",");
					}
				}
				if(congFactorResponseIds.toString().equals("")){
					return congFactorResponseRemoveList;
				}
				hql = new StringBuffer(hql.toString().substring(0,hql.toString().length()-1));
				hql.append(" ) "); 
				String[] ids = congFactorResponseIds.toString().substring(0,congFactorResponseIds.toString().length()-1).split(",");
				Query query = session.createQuery(hql.toString()).setString(0, bidId);
				for(int i=0;i<ids.length;i++){
					query.setString((i+1), ids[i]);
				}
				List<Object[]> objects = query.list();
				if(null != objects && objects.size()>0){
					for(Object[] object:objects){
						CongFactorResponse congFactorResponse = new CongFactorResponse();
						congFactorResponse.setObjId(object[0]+"");
						congFactorResponseRemoveList.add(congFactorResponse);
					}
				}
				return congFactorResponseRemoveList;
			}
		});
	}

	
	
	/**
	 * 
	 * Description :根据项目主键获得所有指标响应对象  
	 * Create Date: 2010-10-9下午06:35:36 by liuke  Modified Date: 2010-10-9下午06:35:36 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<CongFactorResponse> getAllCongFactorResponseByProjectId(
			String projectId) {
		   StringBuffer hql = new StringBuffer();
		   hql.append("from CongFactorResponse cfr where cfr.objId in (");
		   hql.append("select pfr.congFactorResponse.objId from PackCongFactorResponse pfr where pfr.bidPackageId in (");
		   hql.append("select bp.objId from BidPackage bp where (bp.pack.objId = '"+projectId+"' or bp.pack.parentId = '"+projectId+"')))");
		   List<CongFactorResponse> list = this.findbyHql(hql.toString());
		return list;
	}



	/**
	 * Description :删除评审指标对象
	 * Create Date: 2010-12-15下午06:48:35 by liuke  Modified Date: 2010-12-15下午06:48:35 by liuke
	 * @param projectId
	 * @return  void
	 * @Exception
	 */
	public void removeAllCongFactorResponseByProject(final String projectId) {
		 this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback(){
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					StringBuffer hql = new StringBuffer();
					hql.append("delete from CongFactorResponse t where t.objId in (select pfr.congFactorResponse.objId from PackCongFactorResponse pfr where pfr.bidPackageId in (select bp.objId from BidPackage bp where bp.bid.project.objId = ? ))");
					Query query  = session.createQuery(hql.toString());
					query.setString(0, projectId).executeUpdate();
					return null;
				}});
		
	}

}
