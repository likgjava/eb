package com.gpcsoft.smallscale.vote.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.smallscale.vote.dao.VoteAssessedDao;
import com.gpcsoft.smallscale.vote.domain.VoteAssessed;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class VoteAssessedDaoHibernate extends BaseGenericDaoHibernate<VoteAssessed> implements VoteAssessedDao {

	/**
	 * 验证当前登录用户是否对该主题下的此单位进行过评分
	 */
	@SuppressWarnings("unchecked")
	public boolean isUnique(final String templateId, final String voteAssessedThingId, final String userId,final String isSingleVote) {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				StringBuffer sb= new StringBuffer("select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count(objId)","0")+" from VoteAssessed va  where va.voteTemplate.objId =:templateId ");
				if(isSingleVote.equals("false")){
					sb.append("and va.voteAssessedThing.objId = :voteAssessedThingId ");
				}
				if(StringUtils.hasLength(userId)){
					sb.append("and va.createUser.objId = :userId");
				}
				
				Query query = session.createQuery(sb.toString());
				if(StringUtils.hasLength(templateId)){
					query.setString("templateId", templateId);
				}
				if(isSingleVote.equals("false")){
					if(StringUtils.hasLength(voteAssessedThingId)){
						query.setString("voteAssessedThingId", voteAssessedThingId);
					}
				}
				if(StringUtils.hasLength(userId)){
					query.setString("userId", userId);
				}
				List<Long>  list =  query.list();
				boolean flag = true;
				if(list.get(0) > 0){
					flag = false;
				}
				return flag;
			}
			
		});
	}

	/**
	 * 根据评选单位Id,计算该单位各指标的平均分和最终分
	 */
	@SuppressWarnings("unchecked")
	public List<Object> countAvgGradeOfPointer(final String templateId,final String voteAssessedThingId) {
		return (ArrayList<Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer("select vp.pointer_code,vp.pointer_name,vp.pointer_factor,gradeAvg.AvgGrade,(vp.pointer_factor * gradeAvg.AvgGrade / 100) as realGrade from vote_pointer vp,");
				sql.append("(select vg.pointer_id, avg(vg.vote_grade) as AvgGrade from vote_assessed_grade vg where vg.assessed_id in");
				sql.append("(select va.assessed_id from vote_assessed va where va.template_id = '"+templateId+"' and va.ASSESSED_THING_ID = '"+voteAssessedThingId+"')");
				sql.append("group by vg.pointer_id) gradeAvg where vp.pointer_id = gradeAvg.pointer_id order by vp.pointer_code asc");
				
				Query query = session.createSQLQuery(sql.toString());
			
				return query.list();			
			}
		});
	}

	/**
	 * 统计参与单位的投票数
	 */
	@SuppressWarnings("unchecked")
	public List<Object> countVoteAssessedNum(final String templateId,final String voteAssessedThingId) {
		return (ArrayList<Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer();
				sql.append("select v.ASSESSED_THING_ID, count(v.assessed_id) from vote_assessed v where v.template_id = '"+templateId+"' ");
				if(StringUtils.hasLength(voteAssessedThingId)){
					sql.append("and v.ASSESSED_THING_ID = '"+voteAssessedThingId+"' ");
				}
				sql.append("group by v.ASSESSED_THING_ID");
				
				Query query = session.createSQLQuery(sql.toString());
			
				return query.list();			
			}
		});
	}

	/**
	 * 根据模板Id,删除指标分值记录
	 */
	@SuppressWarnings("unchecked")
	public void removeAssessedAndGradeByTemplateId(final String templateId){
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("delete from VoteAssessedGrade vg where vg.voteAssessed.objId in (select v.objId from VoteAssessed v where v.voteTemplate.objId = :templateObjId)");
				Query query = session.createQuery(hql.toString());
				query.setString("templateObjId", templateId);
				return query.executeUpdate();
			}
		});
	}
	
	/**
	 * 根据模板Id,删除投票评分记录
	 */
	@SuppressWarnings("unchecked")
	public void removeAssessedByTemplateId(final String templateId){
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("delete from VoteAssessed v where v.voteTemplate.objId = :templateObjId)");
				Query query = session.createQuery(hql.toString());
				query.setString("templateObjId", templateId);
				return query.executeUpdate();
			}
		});
	}

	/**
	 * 评论展示列表(单个参选对象的评论列表)
	 */
	@SuppressWarnings("unchecked")
	public Page<VoteAssessed> getVoteAssessedCommentShow(final Page<VoteAssessed> page, final Map<String, Object> paramsMap) {
		return (Page<VoteAssessed>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				
				String voteAssessedThingId = (String) paramsMap.get("voteAssessedThingId");//主题Id
				
				String preSql = "from VoteAssessed va where va.voteAssessedThing.objId = :voteAssessedThingId and va.memo != null order by va.createTime desc";
				
				Query query = session.createQuery(preSql);
				query.setString("voteAssessedThingId", voteAssessedThingId);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				List showVoteAssessedCommentList = query.list();
				
				page.setData(showVoteAssessedCommentList);
				
				//查询总数
				String sql = "select count(va.objId) from VoteAssessed va where va.voteAssessedThing.objId = :voteAssessedThingId and va.memo != null";
				query = session.createQuery(sql);
				query.setString("voteAssessedThingId", voteAssessedThingId);
				String num = query.list().get(0).toString();
				page.setTotalRowNum(Long.valueOf(num));
				
				return page;
			}
		});
	}

	/**
	 * 投票结果列表(参选对象、投票数、评论数、成绩)
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getVoteResultShow(final Map<String, Object> paramMap) {
		return (ArrayList<Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				
				String templateId = (String) paramMap.get("templateId");//主题Id
				//参选对象
				hql.append("select vat.assessed_thing_id,vat.attender_name,vat.picture_id,vat.is_recommended,'','',''");
				hql.append("from vote_assessed_thing vat where vat.template_id = '"+templateId+"' order by vat.is_recommended desc,vat.create_time");
				Query query = session.createSQLQuery(hql.toString());
				List voteAssessedThingList = query.list();
				
				for(int i=0;i<voteAssessedThingList.size();i++){
					Object[] voteAssessedOjbect = (Object[]) voteAssessedThingList.get(i);
					
					//投票数  voteAssessedOjbect[4]
					String sqlVoteNum = " select count(va.assessed_id) from vote_assessed va where va.ASSESSED_THING_ID = :voteAssessedThingId";
					query = session.createSQLQuery(sqlVoteNum);
					query.setString("voteAssessedThingId", (String) voteAssessedOjbect[0]);
					voteAssessedOjbect[4] = query.list().get(0) == null?"0":query.list().get(0);
					
					//评论数voteAssessedOjbect[5]
					String sqlCommentNum = " select count(va.assessed_id) from vote_assessed va where va.ASSESSED_THING_ID = :voteAssessedThingId and va.memo is not null";
					query = session.createSQLQuery(sqlCommentNum);
					query.setString("voteAssessedThingId", (String) voteAssessedOjbect[0]);
					voteAssessedOjbect[5] = query.list().get(0) == null?"0":query.list().get(0);
					
					//评分成绩voteAssessedOjbect[6]
					String preSql1 = "select va.assessed_id from vote_assessed va where va.ASSESSED_THING_ID = :voteAssessedThingId";//参选对象的投票数据记录
					String preSql2 = "select vp.pointer_code,vp.pointer_name,vp.pointer_factor,vg.vote_grade,vg.assessed_id "+
									"from vote_assessed_grade vg, vote_pointer vp where vg.pointer_id = vp.pointer_id and vg.assessed_id in("+preSql1+")";//指标评分
					String preSql3 = "select assessedGrade.assessed_id,sum((assessedGrade.pointer_factor * assessedGrade.vote_grade / 100)) as grade from ("+
									preSql2+") assessedGrade group by assessedGrade.assessed_id";//参选对象投票评分平列表
					String preSql4 = "select avg(va1.pointer_factor * assessedSum.grade) from vote_assessed va1,("+
									preSql3+") assessedSum where va1.assessed_id = assessedSum.assessed_id";//参选对象最终评分成绩
					query = session.createSQLQuery(preSql4);
					query.setString("voteAssessedThingId", (String) voteAssessedOjbect[0]);
					voteAssessedOjbect[6] = query.list().get(0) == null?"0":query.list().get(0);					
				}
				
				return voteAssessedThingList;			
			}
		});
	}
}
