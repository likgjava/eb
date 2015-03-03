package com.gpcsoft.agreement.bargin.bidding.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.agreement.bargin.bidding.dao.BiddingChatDao;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingChat;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class BiddingChatDaoHibernate extends BaseGenericDaoHibernate<BiddingChat> implements BiddingChatDao {
	
	/** 
	 * Description :  定时查询新聊天内容
	 * Create Date: 2010-10-24下午01:53:58 by liangxj  Modified Date: 2010-10-24下午01:53:58 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findNewChatContent(final Map<String, Object> params) {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				String projectId=(String) params.get("projId");  //项目id
				String orgInfoId=(String) params.get("orgInfoId");	//发送机构id,接受机构id
				Boolean readStatus=false;//阅读状态-未读
				
				StringBuilder hql=new StringBuilder();
				hql.append("select c.sayOrgInfo.objId,count(c) from BiddingChat c where 1 = 1");
				hql.append(" and c.project.objId=:projectId ");
				hql.append(" and c.readStatus =:readStatus ");
				
				if(StringUtils.hasLength(orgInfoId)) {
					String[] orgId = orgInfoId.split(",");
					if(orgId.length == 2) {
						String senders[] = orgId[1].split("_");
						String hql_say = "";
						for(int i=0; i<senders.length; i++) {
							if(!"".equals(senders[i])) {
								hql_say += " c.sayOrgInfo.objId = '" + senders[i] + "' or";
							}
						}
						hql_say = hql_say.substring(0, hql_say.length() - 2);
						hql.append(" and (c.receiveOrgInfo.objId = '" + orgId[0] + "' and ( ");
						hql.append(hql_say);
						hql.append(") ) ");
					}
					
					hql.append(" group by c.sayOrgInfo.objId ");
				}
				
				Query query = session.createQuery(hql.toString());
				query.setString("projectId", projectId);
				if(readStatus != null && !readStatus){
					query.setBoolean("readStatus", false);
				}
				List<Object[]> res = query.list();
				return res;
			}});
	}
	
	/** 
	 * Description :  查找聊天内容 
	 * Create Date: 2010-10-24下午01:53:58 by liangxj  Modified Date: 2010-10-24下午01:53:58 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<BiddingChat> findChatContent(final Map<String, Object> params) {
		return (List<BiddingChat>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String projectId=(String) params.get("projId");  //项目id
				String trunId=(String) params.get("turnId");	 //轮次id
				String orgInfoId=(String) params.get("orgInfoId");	//发送机构id,接受机构id
				Boolean readStatus=(Boolean) params.get("readStatus");		//阅读状态
				
				StringBuilder hql=new StringBuilder();
				hql.append(" from BiddingChat c left join fetch c.sayOrgInfo o where ");
				hql.append(" c.project.objId=:projectId ");
				
				//轮次 
				if(StringUtils.hasLength(trunId)){
					hql.append(" and c.bargainTurn.objId= '"+trunId+"' ");
				}
				
				if(StringUtils.hasLength(orgInfoId)) {
					String[] orgId = orgInfoId.split(",");
					
					//只接收人
					if("receive".equals(params.get("sayType"))){
						hql.append(" and c.receiveOrgInfo.objId = '" + orgId[0] + "' ");
						if(orgId.length == 2)
						{
							hql.append(" and c.sayOrgInfo.objId = '" + orgId[1] + "' ");
						}
					}
					//发送或接受人a ,b
					else if(orgId.length == 2) {
						hql.append(" and ( (c.receiveOrgInfo.objId = '" + orgId[0] + "' and c.sayOrgInfo.objId = '" + orgId[1] + "')");
						hql.append(" or (c.sayOrgInfo.objId = '" + orgId[0] + "' and c.receiveOrgInfo.objId = '" + orgId[1] + "'))");
					}
					//发送人或接收人为a
					else if(orgId.length == 1) {
						hql.append(" and ( c.receiveOrgInfo.objId = '" + orgId[0] + "' or c.sayOrgInfo.objId = '" + orgId[0] + "') ");
					}
					
				}
				
				//读取状态为未读
				if(readStatus != null && !readStatus){
					hql.append(" and c.readStatus =:readStatus ");
				}
				
				hql.append(" order by c.createTime asc");
				
				Query query = session.createQuery(hql.toString());
				query.setString("projectId", projectId);
				
				if(readStatus != null && !readStatus){
					query.setBoolean("readStatus", false);
				}

				return query.list();
			}});
	}

	/** 
	 * Description :  修改阅读状态
	 * Create Date: 2010-10-29下午04:14:53 by yucy  Modified Date: 2010-10-29下午04:14:53 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean updateChatRecord(final  Map<String, Object> params) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				
				String orgInfoId=(String) params.get("orgInfoId");
				
				String hql = " update BiddingChat b set b.readStatus = :readStatus" +
						" where b.receiveOrgInfo.objId = :reInfoId and b.sayOrgInfo.objId = :sInfoId ";
				Query query = session.createQuery(hql.toString());
				
				if(StringUtils.hasLength(orgInfoId)) {
					String[] orgId = orgInfoId.split(",");
					query.setParameter("reInfoId", orgId[0]);
					query.setParameter("sInfoId", orgId[1]);
				}
				query.setBoolean("readStatus", true);
				return query.executeUpdate()>0?true:false;
			}});
	}
}
