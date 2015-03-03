package com.gpcsoft.epp.bid.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.bid.dao.BidPackageDao;
import com.gpcsoft.epp.bid.domain.BidPackage;

@Repository
@SuppressWarnings(value={"unchecked"})
public class BidPackageDaoHibernate extends BaseGenericDaoHibernate<BidPackage> implements BidPackageDao {
	
	/**
	 * Funcname:getBidPackageByPackIdAndBidId
	 * Description: 根据包件ID,投标ID获取投标与包件中间表
	 * @param packId:包件Id
	 * @param bidId:投标Id
	 * @return BidPackage：投保与包件中间对象
	 * @Create Date 2010-8-5 上午10:05:48 
	 * @author wanghz
	 * @throws Exception
	 */
	public BidPackage getBidPackageByPackIdAndBidId(String packId, String bidId) {
		String hql = "from BidPackage as p where p.bid.objId=:bidId and p.pack.objId=:packId ";
		List<BidPackage> bidPackageList = this.getSession().createQuery(hql.toString()).setString("packId", packId).setString("bidId", bidId).list();
		if(null != bidPackageList && bidPackageList.size() == 1){
			return bidPackageList.get(0);
		}
		return null;
	}

	/**
	 * Funcname:getBidPackageIsMayRemove
	 * Description: 根据投标ID,投标包件ID,获取可以删除的 投标与包件中间表,只返回ID
	 * @param bidId:投标主键
	 * @param packIds:投标包件ID
	 * @return List<BidPackage>投保与包件中间对象集合
	 * @Create Date 2010-8-5 下午12:59:39 
	 * @author wanghz
	 */
	@SuppressWarnings("unchecked")
	public List<BidPackage> getBidPackageIsMayRemove(final String bidId,final String[] packIds) {
		return (List<BidPackage>) getHibernateTemplate().execute(new HibernateCallback(){
			public List<BidPackage> doInHibernate(Session session)throws HibernateException, SQLException {	
				List<BidPackage> bidPackageList = new ArrayList<BidPackage>(0);
				StringBuffer hql = new StringBuffer();
				hql.append("select bidPack.objId,bidPack.objId from BidPackage as bidPack ");
				hql.append("where bidPack.bid.objId = ? and bidPack.pack.objId not in ( ");
				for(String s:packIds){
					hql.append("?,");
				}
				hql = new StringBuffer(hql.toString().substring(0,hql.toString().length()-1)+" ) ");
				Query query = session.createQuery(hql.toString());
				query.setString(0, bidId);
				for(int i=0;i<packIds.length;i++){
					query.setString((i+1), packIds[i]+"");
				}
				List<Object[]> objects = query.list();
				if(null != objects && objects.size()>0){
					for(Object[] object:objects){
						BidPackage bidPackage = new BidPackage();
						bidPackage.setObjId(object[0]+"");
						bidPackageList.add(bidPackage);
					}
				}
				return bidPackageList;
			}
		});
	}
    
	/**
	 * FuncName:getBidPackage
	 * Description : 根据包组和投标记录得到投标包组对象 
	 * @Create Date: 2010-8-11上午11:53:10   .
	 * @author liuke
	 * @Modified Date: 2010-8-11上午11:53:10  
	 * @author liuke
	 * @param   subProjectId;包组主键,bidId:投标主键
	 * @return  BidPackage投保与包件中间对象
	 * @Exception
	 */
	public BidPackage getBidPackage(String subProjectId,String bidId) {
		List<BidPackage> list = this.findbyHql("from BidPackage bp where bp.pack.objId =? and bp.bid.objId =?", subProjectId,bidId);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * FuncName:getAllByPackId
	 * Description: 根据包件ID获取所有投标包件中间表
	 * @param packId
	 * @return List<BidPackage> 投保与包件中间对象集合
	 * @throws Exception
	 * @Create Date 2010-8-16 下午01:32:50 
	 * @author wanghz
	 */
	public List<BidPackage> getAllByPackId(final String packId)throws Exception{
		return (List<BidPackage>) getHibernateTemplate().execute(new HibernateCallback(){
			public List<BidPackage> doInHibernate(Session session)throws HibernateException, SQLException {	
				String hql = "from BidPackage as bidPack where bidPack.pack.objId=:packId ";
				List<BidPackage> bidPackageList = session.createQuery(hql).setString("packId", packId).list();
				if(null == bidPackageList || bidPackageList.size()<1){
					bidPackageList = new ArrayList<BidPackage>(0);
				}
				return bidPackageList;
			}
		});
	}

	/**
	 * Funcname:getAllBidPackageByProjectId
	 * Description:根据项目获得所有投标包件中间表  
	 * @Create Date: 2010-10-11上午09:16:52 
	 * @author  liuke  
	 * @Modified Date: 2010-10-11上午09:16:52 
	 * @author  liuke 
	 * @param   projectId
	 * @return  List<BidPackage>:投保与包件中间对象集合
	 * @Exception
	 */
	public List<BidPackage> getAllBidPackageByProjectId(String projectId)
			throws Exception {
		 List<BidPackage> list = this.findbyHql("from BidPackage bp where bp.bid.project.objId = ?",projectId );
		return list;
	}
	
	/**
	 * Funcname:getAllBidPackageByProjId
	 * Description :根据项目或包组ID获得所有投标包件中间表  
	 * @Create Date: 2010-10-11上午09:16:52 
	 * @author sunl  
	 * @Modified Date: 2010-10-11上午09:16:52 
	 * @author sunl  
	 * @param   projId:项目或包组ID
	 * @return  List<BidPackage>:投保与包件中间对象集合
	 * @Exception
	 */
	public List<BidPackage> getAllBidPackageByProjId(String projId) throws Exception {
		List<BidPackage> list = this.findbyHql("select distinct bp from BidPackage bp where bp.bid.project.objId = ? or bp.pack.objId = ?",projId,projId );
		return list;
	}

	/**
	 * Funcname:removeAllBidPackageByProject
	 * Description :根据项目删除投标包件中间表
	 * @Create Date: 2010-12-15下午07:18:56 
	 * @author  liuke  
	 * @Modified Date: 2010-12-15下午07:18:56 
	 * @author liuke
	 * @param projectId:项目主键
	 * @return  void
	 * @Exception
	 */
	public void removeAllBidPackageByProject(final String projectId) {
		this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer();
				sql.append("delete from ecp_bid_package t where t.bid_id in (select t.bid_id from ecp_bid t where t.proj_id = ?)");
				Query query  = session.createSQLQuery(sql.toString());
				query.setString(0, projectId).executeUpdate();
				return null;
			}});
	}
	
	/**
	 * Funcname:removeAllBidPackageByPackId
	 * Description:根据项目删除投标包件中间表
	 * @Create Date: 2010-12-15下午07:18:56 
	 * @author liuke  
	 * @Modified Date: 2010-12-15下午07:18:56 
	 * @author  liuke
	 * @param packId:项目或包组Id
	 * @return  void
	 * @Exception
	 */
	public void removeAllBidPackageByPackId(final String packId) {
		this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer();
				sql.append("delete from ecp_bid_package t where t.sub_proj_id=:packId");
				Query query  = session.createSQLQuery(sql.toString());
				query.setString("packId", packId).executeUpdate();
				return null;
			}});
		
	}
	
	/**
	 * FuncName: getAllBidPackagesByBid
	 * Description :  根据投标Id获得投标包件中间表对象
	 * @param bidId:投标主键,
	 * @return List<BidPackage>
	 * @author: liuke
	 * @Create Date:2011-5-30  下午06:50:02
	 * @Modifier: liuke
	 * @Modified Date:2011-5-30  下午06:50:02
	 */
	public List<BidPackage> getAllBidPackagesByBidAndNotInPack(String bidId,String subProjId) {
		return this.findbyHql("from BidPackage t where t.bid.objId = ? and t.pack.objId <> ? ", bidId,subProjId);
	}
	
	/**
	 * FuncName: getBidPackageByPackCodeAndBidId
	 * Description :  根据投标ID和包组编号获取到投标与包件中间表
	 * @param packCode:包组编号
	 * @param bidId:投标主键
	 * @return BidPackage:包件中间表
	 * @author: shenjz
	 * @Create Date:2011-6-23  上午09:32:38
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-23  上午09:32:38
	 */
	public BidPackage getBidPackageByPackCodeAndBidId(String packCode, String bidId) {
		String hql = "from BidPackage as p where p.bid.objId=:bidId and p.pack.projCode=:projCode ";
		List<BidPackage> bidPackageList = this.getSession().createQuery(hql.toString()).setString("projCode", packCode).setString("bidId", bidId).list();
		if(null != bidPackageList && bidPackageList.size() == 1){
			return bidPackageList.get(0);
		}
		return null;
	}

	/**
	 * FuncName: getBidPackageInfoByProjectIds
	 * Description :  根据项目ID获得投标信息
	 * @param projectIds:项目主键
	 * @return List<BidPackage>:投保与包件中间对象集合
	 * @author: liuke
	 * @Create Date:2011-6-24  上午11:23:04
	 * @Modifier: liuke
	 * @Modified Date:2011-6-24  上午11:23:04
	 */
	public List getBidPackageInfoByProjectIds(final String projectIds) {
	 return(List)this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer();
				sql.append(" select bidpackage.sub_proj_id,bidpackage.bid_file,bid.supplier_id, bid.proj_id ");
				sql.append("  from ecp_bid bid ");
				sql.append(" left join Ecp_Bid_Package bidpackage on bid.bid_id = bidpackage.bid_id ");
				sql.append("  where bid.proj_id in ( "+projectIds+" ) ");
				Query query  = session.createSQLQuery(sql.toString());
				List list = query.list();
				return list;
			}});
	}

	
	
	/**
	 * @Description: 根据包件ID,与供应商ID查找投标与包件中间表
	 * @param packId
	 * @param supplierId
	 * @return
	 */
	public BidPackage getBidPackageByPackIdAndSupplierId(String packId,
			String supplierId) {
		String hql = "from BidPackage as p where p.bid.supplier.objId = '"+supplierId+"'  and p.pack.objId = '"+packId+"' ";
		List<BidPackage> bidPackageList = this.findbyHql(hql);
		if(null != bidPackageList && bidPackageList.size() == 1){
			return bidPackageList.get(0);
		}
		return null;
	}

}
