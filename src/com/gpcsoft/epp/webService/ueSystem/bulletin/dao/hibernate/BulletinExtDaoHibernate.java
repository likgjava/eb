package com.gpcsoft.epp.webService.ueSystem.bulletin.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dao.BulletinExtDao;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class BulletinExtDaoHibernate extends BaseGenericDaoHibernate<Bulletin> implements BulletinExtDao {
	
	/** 
	 * Description :  获取公告列表
	 * Create Date: 2011-3-3下午06:07:54 by yangx  Modified Date: 2011-3-3下午06:07:54 by yangx
	 * @param   bulletintype:公告类型,startTime:开始时间,endTime:结束时间,purareacode:采购区域,purcategorycode:采购品目编号,bulletinnum,获取记录条数
	 * @return  String：拼装好的XML字符串
	 * @Exception   
	 * @Modifier zhouzhanghe
	 * @Modifiy Date 2011-7-7 15:46(优化SQL)
	 */
	@SuppressWarnings("unchecked")
	public List<Bulletin> getBulletinList(final String bulletintype,final String startTime,final String endTime,final String purareacode,final String purcategorycode,final String bulletinnum,final String keyword) throws Exception{
		return (List<Bulletin>)getHibernateTemplate().execute(new HibernateCallback(){
			public List<Bulletin> doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sql= new StringBuffer();
				sql.append("select t.Bulletin_ID,t.Bulletin_No, t.Title, trim(t.Bulletin_Type), t.reldate, t2.TenderID, t2.TenderNo, t2.PURCATEGORY_IDS,t.SIGNUP_START_DATE,t.SIGNUP_END_DATE");
				sql.append(" from ecp_base_bulletin t left join ECP_Tender_Project t2 on t.TenderID = t2.TenderID");
				sql.append(" where t.bulletin_type<>'09' ");
				sql.append(" and t.AUDITSTATUS = '"+AuditStatusEnum.AUDIT_PASS+"' ");   //审核通过的公告才能被查出   2011-07-13 11:41 by liuke 
				if (bulletintype!=null&&!"".equals(bulletintype)) {//公告类型
					sql.append(" and t.bulletin_type in ('"+bulletintype+"')");
				}
				if (purcategorycode!=null&&!"".equals(purcategorycode)) {//品目编号
					String[] purcategoryArray =  purcategorycode.split(",");
					sql.append(" and t.PURCATEGORY_IDS in ( select c.id from purcatalog_category c where ");
					for(int i=0;i<purcategoryArray.length;i++){
						 if(i>0){
							sql.append(" or c.category_code like '%"+purcategoryArray[i]+"%'");
						}else {
							sql.append(" c.category_code like '%"+purcategoryArray[i]+"%'");
						}
					}
					sql.append(" )");
				}
				if (startTime!=null&&!"".equals(startTime)) {
					sql.append(" and t.reldate >= "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(startTime)+"");
				}
				if (endTime!=null&&!"".equals(endTime)) {
					sql.append(" and "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(endTime)+" >= t.reldate");
				}
				if (keyword!=null&&!"".equals(keyword.trim())) {//关键字
					String[] keywords = keyword.split(" ");
					sql.append(" and ( ");
					int t = 0;
					for (int i=0; i<keywords.length;i++) {
						++t;
						if (keywords[i]!=null&&!"".equals(keywords[i].trim())&&t==1) {//第一次
							sql.append(" t.title like '%"+keywords[i]+"%'");
						}else if (keywords[i]!=null&&!"".equals(keywords[i].trim())&&1<t&&t<keywords.length) {
							sql.append(" or t.title like '%"+keywords[i]+"%'");
						}else if (keywords[i]!=null&&!"".equals(keywords[i].trim())&&t==keywords.length) {//最后一次
							sql.append(" or t.title like '%"+keywords[i]+"%'");
						}
					}
					sql.append(")");
				}
				if (bulletinnum!=null&&!"".equals(bulletinnum)) {
					sql.append(" and rownum <= "+bulletinnum+"");
				}
				sql.append(" order by t.credate desc");
				Query query = session.createSQLQuery(sql.toString());
				
				List<Bulletin> returnList = new ArrayList<Bulletin>();
				List list = query.list();
				for(Object o : list){
					Object[] tempO = (Object[]) o;
					Bulletin bulletin = new Bulletin(); 
					Project project = new Project();
					bulletin.setObjId((String)tempO[0]);
					bulletin.setBulletinNo((String)tempO[1]);
					bulletin.setBullTitle((String)tempO[2]);
					bulletin.setBullType((String)tempO[3]);
					bulletin.setRelDate((Date)tempO[4]);
					project.setObjId((String)tempO[5]);
					project.setProjCode((String)tempO[6]);
					project.setPurCategoryIds((String)tempO[7]);
					bulletin.setProject(project);
					bulletin.setSignUpSTime((Date)tempO[8]);
					bulletin.setSignUpETime((Date)tempO[9]);
					returnList.add(bulletin);
				}
				return returnList;
			}
		});
	}

	public List getResultBySql(String sql) {
		SQLQuery query = getSession().createSQLQuery(sql);
		return query.list();
	}
}
