package com.gpcsoft.epp.bid.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.bid.dao.BidSubKeyDao;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.bid.domain.BidSubKey;

@Repository
@SuppressWarnings(value={"unchecked"})
public class BidSubKeyDaoHibernate extends BaseGenericDaoHibernate<BidSubKey> implements BidSubKeyDao {

	
	/**
	 * Funcname:getBidIdByProjCodeAndOrgId
	 * Description: 根据机构ID,项目编号获取项目ID
	 * @param orgId:机构Id
	 * @param projCode:项目编号
	 * @return Bid：投标记录对象
	 * @Create Date 2011-8-5 上午10:05:48 
	 * @author caojz
	 * @throws Exception
	 */
	public Bid getBidIdByProjCodeAndOrgId(String projCode, String supplierId) {
		String hql = "from Bid as bid where bid.projCode=:projCode and bid.supplier.objId=:supplierId ";
		List<Bid> bidList = this.getSession().createQuery(hql.toString()).setString("projCode", projCode).setString("supplierId", supplierId).list();
		if(null != bidList && bidList.size()==1){
			return bidList.get(0);
		}
		return null;
	}

	/**
	 * Funcname:getBidPackageByBidId
	 * Description: 根据包组和投标记录得到投标包组对象 
	 * @param bidId:投标Id
	 * @param subProjectId:包组主键
	 * @return BidPackage：投标与包件中间对象
	 * @Create Date 2011-8-5 上午10:05:48 
	 * @author caojz
	 * @throws Exception
	 */
	public BidPackage getBidPackageByBidId(String bidId,String subProjectId) throws Exception {
		String hql = "from BidPackage as p where p.bid.objId=:bidId and p.pack.objId=:subProjectId";
		//List<BidPackage> bidPackage =  this.findbyHql(hql.toString(),bidId,subProjectId);
		List<BidPackage> bidPackage = this.getSession().createQuery(hql.toString()).setString("bidId",bidId).setString("subProjectId",subProjectId).list();
		if( null !=bidPackage && bidPackage.size()==1){
			return bidPackage.get(0);
		}
		return null;
	}

	/**
	 * Funcname:getBidSubKeysById
	 * Description: 根据投标记录ID得到开标子密钥集合 
	 * @param bidPackId:投标记录ID
	 * @return BidSubKey：开标子密钥集合
	 * @Create Date 2011-8-5 上午10:05:48 
	 * @author caojz
	 * @throws Exception
	 */
	public List<BidSubKey> getBidSubKeysById(String bidPackId) throws Exception {
		String hql = "from BidSubKey as subKey where subKey.bidPackage.objId = ?";
		List<BidSubKey> subKeys = this.findbyHql(hql, bidPackId);
		return subKeys;
	}

	/**
	 * Funcname:saveOrUpdateBidSubKey
	 * Description :  保存或更新开标子密钥
	 * @Create Date: 2011-8-5 上午10:05:48     
	 * @author caojz
	 * @param   BidSubKey bidSubKey
	 * @return  BidSubKey对象
	 * @Exception 
	 */
	public BidSubKey saveOrUpdate(BidSubKey subKey) {
//		String sql = "insert into ecp_open_subkey (BID_P_ID,TENDER_ID,OPEN_SUBKEY) values(?,?,?)";
//		return this.getSession().createSQLQuery(sql.toString()).setString(0, tenderId)
//		      .setString(1, bidPackId).setString(2, subKey).executeUpdate();
		return this.saveOrUpdate(subKey);
	}

 
	/**
	 * 根据投标bidId和supplierId，查询BidSubKey是否存在
	 * @param bidId
	 * @param supplierId
	 * @return
	 * @author zhouzhanghe
	 * @created at 2011-11-09 16:56
	 */
	public boolean getIsExistByBidIdAndSupplierId(final String bidId, final String supplierId)  {
		Object o = getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("select count(t2.subkey_id) ");
				sql.append(" from ECP_OPEN_SUBKEY t2 ");
				sql.append("  left join ECP_BID_PACKAGE t1 on t2.bid_p_id = t1.bid_p_id ");
				sql.append("  left join ECP_BID t on t1.bid_id = t.bid_id ");
				sql.append(" where t.bid_id = ?");
				sql.append("   and t.supplier_id = ?");
				
				SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
				sqlQuery.setParameter(0, bidId);
				sqlQuery.setParameter(1, supplierId);
				
				List list = sqlQuery.list();
				if(list != null && list.size() > 0)
					return Integer.parseInt(list.get(0).toString()) > 0 ? true : false;
				
				return false;
			}
		});
		return Boolean.parseBoolean(o.toString());
	}
}
