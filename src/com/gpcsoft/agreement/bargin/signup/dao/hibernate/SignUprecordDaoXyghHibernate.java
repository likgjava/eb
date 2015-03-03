package com.gpcsoft.agreement.bargin.signup.dao.hibernate;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.signup.dao.SignUprecordDaoXygh;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.signuprecord.dao.hibernate.SignUprecordDaoHibernate;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Service 
public class SignUprecordDaoXyghHibernate extends SignUprecordDaoHibernate implements SignUprecordDaoXygh {
	
	/** 
	 * Description :  确认报名
	 * Create Date: 2010-10-13下午04:26:28 by yucy  Modified Date: 2010-10-13下午04:26:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean updateSignUprecordAuditStatus(final Map<String, Object> param) {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					String Hql = " update SignUprecord s set " +
							" s.auditStatus = :auditStatus " +
							" , s.auditDate = :auditDate " +
							" , s.auditUser.objId= :userdIds " +
							" where s.objId in (:signIds) ";
					Query query = session.createQuery(Hql);
					query.setParameter("auditStatus", param.get("auditStatus"));
					query.setDate("auditDate",new Date() );
					query.setParameter("userdIds", AuthenticationHelper.getCurrentUser(true).getObjId());
					query.setParameterList("signIds", (String[])param.get("signIds"));
					return query.executeUpdate()>0;
				}
		});
	}

	/** 
	 * Description :  判断该项目是否已经报名
	 * Create Date: 2010-10-13下午10:18:54 by yucy  Modified Date: 2010-10-13下午10:18:54 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean ifHasSignUp(final Map<String, Object> param) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					String Hql = " select s.objId from SignUprecord s where s.project.objId = :projectId and s.supplier.objId = :orgId ";
					Query query = session.createQuery(Hql);
					query.setParameter("projectId", param.get("projectId"));
					query.setParameter("orgId", param.get("orgId"));
					return query.list().size()>0;
				}
		});
	}

	
	/** 
	 * Description : 撤销报名 
	 * Create Date: 2010-10-14上午01:07:26 by yucy  Modified Date: 2010-10-14上午01:07:26 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean updateSignUprecordAppllyStatus(final Map<String, Object> param) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					String Hql = " update SignUprecord s set " +
							" s.applyStatus = :applyStatus ," +
							" s.unApplyReason = :unApplyReason ," +
							" s.unApplyDate = :unApplyDate" +
							" where s.objId = :objId ";
					Query query = session.createQuery(Hql);
					
					SignUprecord signUprecord= (SignUprecord)param.get("signUprecord");
					query.setParameter("unApplyReason", signUprecord.getUnApplyReason());
					query.setDate("unApplyDate", new Date());
					query.setParameter("applyStatus", signUprecord.getApplyStatus());
					query.setParameter("objId", signUprecord.getObjId());
					return query.executeUpdate()>0;
				}
		});
	}

	/** 
	 * Description :  取得评价对象
	 * Create Date: 2010-10-28下午04:49:35 by yucy  Modified Date: 2010-10-28下午04:49:35 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getEvaluateObjectSupplier(final String projectId) 	throws Exception {
		return (Map<String, Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					String Hql = " select r.supplier.objId, r.supplier.orgName  from SignUprecord r where r.project.objId = :projectId and r.supplier.objId not in ( select e.org.objId from Evaluate e where e.rater.objId = :currentUserId and e.project = :projectId  ) ";
					Query query = session.createQuery(Hql);
					query.setParameter("projectId",projectId );
					query.setParameter("currentUserId",AuthenticationHelper.getCurrentUser(true).getObjId() );
					Map<String,Object> model = new HashMap<String, Object>();
					model.put("evaluateOrg", query.list());
					return model;
				}
		});
	}
	
	/** 
	 * Description :   供应商用户取出被投诉举报报名机构(其他供应商和项目采购人)
	 * Create Date: 2010-10-29下午01:55:32 by dongcl  Modified Date: 2010-10-29下午01:55:32 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getComplainObjectSupplier(final String projectId) 	throws Exception {
		//supplier.objId,supplier.orgName,createUser.objId,createUser.emp.name,createUser.emp.email	
		Map<String,Object> result = null;
		
		result = (Map<String, Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				OrgInfo org = null;
				if(AuthenticationHelper.getCurrentUser(true).getOrgInfo()!=null){
					org = (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo();
				}
				if(org != null){
					String Hql = " select supplier.objId,supplier.orgName,createUser.objId,createUser.emp.name from SignUprecord  r where r.project.objId = :projectId and r.supplier.supplierId<> :currentUserOrgId";
					Query query = session.createQuery(Hql);
					query.setParameter("projectId",projectId );
					query.setParameter("currentUserOrgId",org.getSupplierId() );
					Map<String,Object> model = new HashMap<String, Object>();
					model.put("orgInfo", query.list());
					return model;
				}
				return null;
			}
		});
		
		
		//被投诉举报的项目采购人		
		Map<String,Object> cgr = (Map<String,Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					String Hql = " select buyersId,buyersName,createUser.objId,createUser.emp.name  from Project  p where p.objId = :projectId ";
					Query query = session.createQuery(Hql);
					query.setParameter("projectId",projectId );
					
					Map<String,Object> model = new HashMap<String, Object>();
					model.put("cgr", query.list());
					return model;
				}
		});		
		
		if(null != result.get("orgInfo")){
			List list = (List)result.get("orgInfo");
			list.add(((List)cgr.get("cgr")).get(0));
			result.put("orgInfo", list);
			
		}
		else{
			result.put("orgInfo", cgr.get("cgr"));
		}		
		return result;
	}
	
	/** 
	 * Description :  校验身份证
	 * Create Date: 2010-11-8下午03:09:56 by yucy  Modified Date: 2010-11-8下午03:09:56 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean checkedIdCard(final Map<String, Object> param) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					String Hql = " select s.objId from SignUprecord s where s.idCard = :idCard and s.project.objId = :projectId ";
					Query query = session.createQuery(Hql);
					query.setParameter("idCard",param.get("cardId") );
					query.setParameter("projectId",param.get("projectId") );
					return query.list().size()>0?false:true;
				}
		});
	}

	/** 
	 * Description :  判断是否有这个联系人,如果有返回其empId，否则返回空字符串
	 * Create Date: 2010-11-16上午11:49:58 by yucy  Modified Date: 2010-11-16上午11:49:58 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public String getHasThisLinker(final Map<String, Object> param) throws Exception {
		return (String) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					String Hql = " select e.objId from Employee e where e.company.objId = :companyId and e.idCard = :idCard ";
					Query query = session.createQuery(Hql);
					query.setParameter("idCard",param.get("cardId") );
					query.setParameter("companyId",param.get("companyId") );
					return query.list().size()>0?(String)query.list().get(0):"";
				}
		});
	}

	/** 
	 * Description :  批量保存供应商的报名信息
	 * Create Date: 2011-8-3下午02:27:23 by likg  Modified Date: 2011-8-3下午02:27:23 by likg
	 * @param   projectId:项目Id  supplierIds:供应商Id，以逗号分割
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void saveSignUprecordBatch(final String projectId, final String supplierIds) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				
				StringBuilder sql = new StringBuilder();
				sql.append(" insert into ECP_TENDER_APPLY_REC (APPLYID,TENDERID,SUPPLYERID,SUPPLYERNAME,APPLYDATE,LINKER,IDCARD,LINKER_TEL,ADDRESS,AUDITSTATUS,CREUSER,CREDATE) ");
				sql.append(" select o.ORG_INFO_ID || :mm,:projectId,o.ORG_INFO_ID,o.ORG_NAME,:date,e.EMP_NAME,e.EMP_IDCARD,e.EMP_TEL_OFFICE,e.EMP_ADDRESS,:auditStatus,:userId,:date ");
				sql.append(" from ORG_INFO o ");
				sql.append(" left join AUTH_USER u on u.USR_ID = o.USER_ID ");
				sql.append(" left join AUTH_ORG_EMPLOYEE e on e.EMP_ID = u.EMP_ID ");
				sql.append(" where o.ORG_INFO_ID in (:supplierIds) ");
				
				Query query = session.createSQLQuery(sql.toString());
				query.setParameter("mm", new Date().getTime());
				query.setParameter("projectId", projectId);
				query.setParameterList("supplierIds", supplierIds.split(","));
				query.setParameter("date", new Date());
				query.setParameter("auditStatus", AuditStatusEnum.AUDIT_PASS);
				query.setParameter("userId", AuthenticationHelper.getCurrentUser(true).getObjId());
				
				query.executeUpdate();
				return null;
			}
		});
	}
}
