package com.gpcsoft.epp.expertrule.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.MathUtil;
import com.gpcsoft.epp.expertrule.dao.ExpertRuleDao;
import com.gpcsoft.epp.expertrule.domain.TakeExpertRule;
import com.gpcsoft.epp.workgroup.domain.WorkGroupTypeEnum;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;

@Repository
public class ExpertRuleDaoHibernate extends BaseGenericDaoHibernate<TakeExpertRule> implements ExpertRuleDao{

	/** 
	 * Description :  根据当前用户和包组获得成员信息  
	 * Create Date: 2010-8-5上午11:35:42 by yangx  Modified Date: 2010-8-5上午11:35:42 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<WorkgMember> getWorkMemberListByUserIdAndProjectId(String workgType, String projectId) {
		StringBuffer hql = new StringBuffer();
		hql.append("From WorkgMember wm where");
		hql.append(" wm.workGroup.objId in (select tw.objId from WorkGroup tw where tw.project.objId ='"+projectId+"')");
		hql.append("and wm.workGroup.workgType in (select tw.workgType from WorkGroup tw where tw.workgType ='"+workgType+"')");
		List list = this.findbyHql(hql.toString());
		return list;
	}
	
	/** 
	 * Description :   根据工作组成员objId与工作组objId更新小组中的组长
	 * Create Date: 2010-8-5下午02:18:04 by yangx  Modified Date: 2010-8-5下午02:18:04 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void updateWorkgMemberForIsLeader(final String objId,final String workGroupId){
		
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String sql1 = "update ECP_WORKG_MEMBER t set t.workg_m_isleader='"+WorkGroupTypeEnum.IS_LEADER+"' where t.workg_m_id='"+objId+"'";
				String sql2 = "update ECP_WORKG_MEMBER t set t.workg_m_isleader='"+WorkGroupTypeEnum.IS_NOLEADER+"' where t.work_id='"+workGroupId+"' and t.workg_m_id <> '"+objId+"'";
				session.createSQLQuery(sql1).executeUpdate();
				session.createSQLQuery(sql2).executeUpdate();
				return null;
			}
		});
	}
	
	/** 
	 * Description :  根据小组成员Id删除小组成员与对应的用户
	 * Create Date: 2010-8-6下午03:05:20 by yangx  Modified Date: 2010-8-6下午03:05:20 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void removeWorkgMemberAndUser(final WorkgMember workgMember){
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String sql1 = "delete from ECP_WORKG_MEMBER where WORKG_M_ID='"+workgMember.getObjId()+"'";
				String sql2 = "delete from AUTH_USER_ROLE where USR_ID in(select USR_ID from AUTH_USER where USR_NAME='"+workgMember.getWorkgmAccount()+"' and USR_PASSWORD='"+MathUtil.encryptPassWordSHA(workgMember.getWorkgmPassWord())+"')";
				String sql3 = "delete from AUTH_USER where USR_NAME='"+workgMember.getWorkgmAccount()+"' and USR_PASSWORD='"+MathUtil.encryptPassWordSHA(workgMember.getWorkgmPassWord())+"'";
				session.createSQLQuery(sql1).executeUpdate();
				session.createSQLQuery(sql2).executeUpdate();
				session.createSQLQuery(sql3).executeUpdate();
				return null;
			}
		});
	}
	
	/** 
	 * Description :  根据小组成员Id跟新正选专家、备选专家
	 * Create Date: 2010-8-9上午11:09:31 by yangx  Modified Date: 2010-8-9上午11:09:31 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void updateWorkgMemberForAmount(final String workgMemberId,final String isAmount){
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String sql1 = "update ECP_WORKG_MEMBER t set t.ISAMOUNT='"+isAmount+"' where t.workg_m_id='"+workgMemberId+"'";
				session.createSQLQuery(sql1).executeUpdate();
				return null;
			}
		});
	}
	
	/** 
	 * Description :  通过包组Id和小组类型得到工作小组对象
	 * Create Date: 2010-8-5上午10:48:06 by yangx  Modified Date: 2010-8-5上午10:48:06 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List getWorkGroupByProjectIdAndType(String projectId,String workType){
		List list = this.findbyHql("from WorkGroup wg where wg.project.objId=? and wg.workgType=?", projectId,workType);
		return list;
	}
	
	/** 
	 * Description :  根据账号查询User
	 * Create Date: 2010-8-9上午11:40:24 by yangx  Modified Date: 2010-8-9上午11:40:24 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List getUserByAccount(String account){
		String hql = "From User u where u.usName='"+account+"'";
		List list = this.findbyHql(hql);
		return list;
	}
	
	/** 
	 * Description :  根据专家Id获取小组成员
	 * Create Date: 2010-8-10下午04:13:05 by yangx  Modified Date: 2010-8-10下午04:13:05 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public WorkgMember getWorkMemberByExpertId(String expertId){
		String hql = "From WorkgMember t where t.workgMAPIId='"+expertId+"'";
		List list = this.findbyHql(hql);
		if(list!=null&&list.size()>0) {
			return (WorkgMember)list.get(0);
		}
		return null;
	}
	
	/** 
	 * Description :  获取品目[来源：本地]
	 * Create Date: 2010-8-19上午09:36:29 by yangx  Modified Date: 2010-8-19上午09:36:29 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List getPurCategory(){
		String hql = "From PurCategory";
		List list = this.findbyHql(hql);
		return list;
	}
	
	/** 
	 * Description :  根据专家规则Id获取条目
	 * Create Date: 2010-8-30下午09:34:13 by yangx  Modified Date: 2010-8-30下午09:34:13 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List getTakeExpertRuleItemBytakeExpertRuleId(String takeExpertRuleId){
		String hql = "From TakeExpertRuleItem t where t.takeExpertRule.objId='"+takeExpertRuleId+"'";
		List listTakeExpertRuleItem = this.findbyHql(hql);
		return listTakeExpertRuleItem;
	}
	
	
	/** 
	 * Description : 根据项目Id获取评标室 
	 * Create Date: 2010-9-20下午02:08:03 by yangx  Modified Date: 2010-9-20下午02:08:03 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List getMeetRoomByProjectId(String projectId){
		String hql = "From MeetRoom t where t.objId in (select b.meetRoom.objId from BidRoom b where b.project.objId='"+projectId+"')";
		List list = this.findbyHql(hql);
		return list;
	}

	public TakeExpertRule getByProjectId(String projectId) {
		String hql = "from TakeExpertRule t where t.subProjectId.objId = ?";
		List<TakeExpertRule> list = this.findbyHql(hql, projectId);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
