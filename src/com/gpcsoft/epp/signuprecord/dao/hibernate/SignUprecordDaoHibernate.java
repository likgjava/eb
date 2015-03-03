package com.gpcsoft.epp.signuprecord.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.utils.StringUtil;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.signuprecord.dao.SignUprecordDao;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class SignUprecordDaoHibernate extends
		BaseGenericDaoHibernate<SignUprecord> implements SignUprecordDao {

	/** 
	 * Description :  更改报名状态为支付状态
	 * Create Date: 2011-7-21下午01:43:12 by sunl  Modified Date: 2011-7-21下午01:43:12 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean updatePayStatus(final String projId, final String supplierId, final String status) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuilder hql= new StringBuilder();
				hql.append("update SignUprecord t set t.docpricePayStatus = :status where 1=1 ");
				hql.append("and t.project.objId = :projId and t.supplier.objId = :supplierId ");
				Query query = session.createQuery(hql.toString());
				query.setString("status", status);
				query.setString("projId", projId);
				query.setString("supplierId", supplierId);
				return query.executeUpdate()>0;
			}
		});
	}
	
	/**
	 * Description : 获取供应商报名信息列表 Create Date: 2010-6-23下午03:27:02 by yangx
	 * Modified Date: 2010-6-23下午03:27:02 by yangx
	 * @param
	 * @return
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public Page<SignUprecord> searchSignUprecordByQueryObject(
			Page<SignUprecord> page, QueryObject queryObject) {
		StringBuffer hql = new StringBuffer();
		hql.append("from SignUprecord t where 1=1 ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if (queryList != null && !queryList.isEmpty()) {
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam) queryList.get(i);
				/** 审核状态 */
				if ("auditStatus".equals(queryParam.getName())
						&& queryParam.getValue() != null
						&& !"".equals(queryParam.getValue())) {
					hql.append(" and t.auditStatus = '"
							+ (String) queryParam.getValue() + "'");
				}
				/** 项目名称 */
				if ("projName".equals(queryParam.getName())
						&& queryParam.getValue() != null
						&& !"".equals(queryParam.getValue())) {
					hql.append(" and t.project.projName like '%"
							+ (String) queryParam.getValue() + "%'");
				}
				/** 项目编号 */
				if ("projCode".equals(queryParam.getName())
						&& queryParam.getValue() != null
						&& !"".equals(queryParam.getValue())) {
					hql.append(" and t.project.projCode like '%"
							+ (String) queryParam.getValue() + "%'");
				}
				/** 项目负责人 */
				if ("managerID".equals(queryParam.getName())
						&& queryParam.getValue() != null
						&& !"".equals(queryParam.getValue())) {
					hql.append(" and t.project.objId in ");
					hql.append(" (select distinct "
							+ UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull(
									"tm.objId")
							+ " from Project tm where tm.manager.objId='"
							+ (String) queryParam.getValue() + "') ");
				}
				/** 供应商 */
				if ("supplierID".equals(queryParam.getName())
						&& queryParam.getValue() != null
						&& !"".equals(queryParam.getValue())) {
					hql.append(" and t.supplier.objId = '"
							+ (String) queryParam.getValue() + "'");
				}
				if("bailRecord".equals(queryParam.getName()) //只有缴纳了保证金的报名才能被查出   20110825 by liuke 
						&& queryParam.getValue() != null
						&& !"".equals(queryParam.getValue())){
					hql.append(" and t.objId in ( select br.signUprecord.objId from BailRecord br )   ");
				}

			}
		}
		hql.append(" order by applyDate desc");
		Page<SignUprecord> pages = this.findbyHql(hql.toString(), page
				.getStart(), page.getPageSize(), SignUprecord.class);
		return pages;
	}

	/**
	 * Description : 根据查询条件统计对应的报名信息数据 Create Date: 2010-7-10上午10:57:34 by yangx
	 * Modified Date: 2010-7-10上午10:57:34 by yangx
	 * 
	 * @param queryObject
	 *            [auditStatus:审核状态;managerID:项目负责人]
	 * @return
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal getSignUprecordTotalByQueryObject(QueryObject queryObject) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(t) from SignUprecord t where 1=1  ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if (queryList != null && !queryList.isEmpty()) {// 若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam) queryList.get(i);
				/** 项目负责人 */
				if ("managerID".equals(queryParam.getName())
						&& queryParam.getValue() != null
						&& !"".equals(queryParam.getValue())) {
					hql.append(" and t.project.objId in ");
					hql.append(" (select distinct "
							+ UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull(
									"tm.objId")
							+ " from Project tm where tm.manager.objId='"
							+ (String) queryParam.getValue() + "') ");
				}
				if ("auditStatus".equals(queryParam.getName())) {
					hql.append(" and t.auditStatus='"
							+ (String) queryParam.getValue() + "'");
				}
				/** 供应商 */
				if ("supplierID".equals(queryParam.getName())
						&& queryParam.getValue() != null
						&& !"".equals(queryParam.getValue())) {
					hql.append(" and t.supplier.objId = '"
							+ (String) queryParam.getValue() + "'");
				}
				if("bailRecord".equals(queryParam.getName()) //只有缴纳了保证金的报名才能被查出   20110825 by liuke 
						&& queryParam.getValue() != null
						&& !"".equals(queryParam.getValue())){
					hql.append(" and t.objId in ( select br.signUprecord.objId from BailRecord br )   ");
				}
				
				
			}
		}
		List list = this.findbyHql(hql.toString());
		Object object = list.get(0);
		return new BigDecimal(object.toString());
	}

	/**
	 * 
	 * Description :根据包组或项目和供应商得到报名信息 Create Date: 2010-8-30上午10:14:25 by liuke
	 * Modified Date: 2010-8-30上午10:14:25 by liuke
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	public SignUprecord getSignUprecordByprojectIdAndSupplierId(
			String projectId, String supplierId) {
		List<SignUprecord> list = this.findbyHql("from SignUprecord t where t.project.objId = ?  and t.supplier.objId = ?",projectId, supplierId);
		return (!list.isEmpty())?list.get(0):null;
	}

	/**
	 * 
	 * Description :根据项目和供应商得到报名信息 Create Date: 2010-8-30上午10:14:25 by liuke
	 * Modified Date: 2010-8-30上午10:14:25 by shenjz
	 * @param
	 * @return
	 * @Exception
	 */
	public SignUprecord getSignUprecordByprojectAndSupplierId(
			String projectId, String supplierId) {
		List<SignUprecord> list = this.findbyHql(" from SignUprecord t where (t.project.objId = ? or t.project.parentId = ? )  and t.supplier.objId = ? order by t.createTime desc ",projectId,projectId,supplierId);
		return (!list.isEmpty())?list.get(0):null;
	}
	
	/**
	 * 
	 * Description :根据项目得到报名信息 Create Date: 2010-9-1上午11:22:49 by shenjianzhuang
	 * Modified Date: 2010-9-1上午11:22:49 by shenjianzhuang
	 * 
	 * @param projectId
	 * @return
	 * @return List<SignUprecord>
	 * @Exception
	 */
	public List<SignUprecord> getSignUprecordByprojectId(String projectId) {
		return this.findbyHql("select distinct t from SignUprecord t where t.project.objId = '"+projectId+"' or t.project.parentId = '"+ projectId + "'");
	}

	/**
	 * 
	 * Description :根据项目和审核状态获得报名信息  
	 * Create Date: 2010-9-10下午01:43:22 by liuke  Modified Date: 2010-9-10下午01:43:22 by liuke
	 * @param   projectId：项目Id,auditStatus：审核状态
	 * @return  List<SignUprecord>
	 * @Exception
	 */
	public List<SignUprecord> getSignUprecordByprojectIdAndAuditStatus(
			String projectId) {
		return this.findbyHql("from SignUprecord t where t.project.objId = ? or t.project.parentId = ?",projectId, projectId);
	}

	/**
	 * Description :根据报名信息ID审核报名信息
	 * 
	 * @param objIds
	 * @param auditStatus
	 */
	@SuppressWarnings("unchecked")
	public void saveAuditSignUprecord(final String objIds,
			final String auditStatus) {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append("update SignUprecord t set t.auditStatus = '"
						+ auditStatus + "' where t.objId in (" + objIds + ")");
				session.createQuery(hql.toString()).executeUpdate();
				return null;
			}
		});

	}

	/**
	 * 
	 * FuncName: getSignUprecordList Description : 获取报名xml（电子评审系统）
	 * 
	 * @param
	 * @return List<SignUprecord>
	 * @author: shenjz
	 * @Create Date:2011-3-24 上午11:25:19
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-24 上午11:25:19
	 * @Modifier: zhouzhanghe
	 * @Modified Date:2011-7-8 上午15:34:19
	 */
	public List<SignUprecord> getSignUprecordList(String projectids) {
		List list = findbyHql("select s.objId, s.applyDate, s.linker, s.linkerTel, s.idCard, s.address, s.zipCode, s.memo, s.project.objId, s.supplier.objId, s.supplier.orgCode, s.supplier.orgName ,s.project.projCode  from SignUprecord s where  s.auditStatus = '"+AuditStatusEnum.AUDIT_PASS+"'  and s.project.objId in ("
				+ projectids + ") or s.project.parentId in (" + projectids
				+ ")");
		
		List<SignUprecord> returnList = new ArrayList<SignUprecord>();
		for(Object o : list){
			Object[] tempO = (Object[]) o;
			
			SignUprecord signUprecord = new SignUprecord(); 
			OrgInfo supplier = new OrgInfo();
			signUprecord.setSupplier(supplier);
			Project project = new Project();
			signUprecord.setProject(project);
			
			signUprecord.setObjId((String)tempO[0]);
			signUprecord.setApplyDate((Date)tempO[1]);
			signUprecord.setLinker((String)tempO[2]);
			signUprecord.setLinkerTel((String)tempO[3]);
			signUprecord.setIdCard((String)tempO[4]);
			signUprecord.setAddress((String)tempO[5]);
			signUprecord.setZipCode((String)tempO[6]);
			signUprecord.setMemo((String)tempO[7]);
			project.setObjId((String)tempO[8]);
			project.setProjCode((String)tempO[12]);
			supplier.setObjId((String)tempO[9]);
			supplier.setOrgCode((String)tempO[10]);
			supplier.setOrgName((String)tempO[11]);
			
			returnList.add(signUprecord);
		}
		return returnList;
	}

	/**
	 * Description : 根据查询条件统计对应的报名信息数据 Create Date: 2010-7-10上午10:57:34 by yangx
	 * Modified Date: 2010-7-10上午10:57:34 by yangx
	 * 
	 * @param queryObject
	 *            [auditStatus:审核状态;managerID:项目负责人,projectID:项目ID]
	 * @return
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SignUprecord> getSignUprecordListByQueryObject(
			QueryObject queryObject) {
		StringBuffer hql = new StringBuffer();
		hql.append("select distinct t from SignUprecord t where 1=1  ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if (queryList != null && !queryList.isEmpty()) {// 若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam) queryList.get(i);
				/** 项目负责人 */
				if ("managerID".equals(queryParam.getName())
						&& queryParam.getValue() != null
						&& !"".equals(queryParam.getValue())) {
					hql.append(" and t.project.objId in ");
					hql.append(" (select distinct "
							+ UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tm.objId")
							+ " from Project tm where tm.manager.objId='"
							+ (String) queryParam.getValue() + "') ");
				}
				//add by chenhj 20111017 修改获取项目报名供应商
				
				if("projectID".equals(queryParam.getValue())&&!StringUtil.empty(queryParam.getValue())){
					hql.append(" and t.project.objId in ");
					 hql.append(" (select distinct tm.obj from Project tm where tm.parentId='").append((String)queryParam.getValue()).append("' or tm.objId='").append((String)queryParam.getValue()).append("') ");
				}
				
				if("auditStatus".equals(queryParam.getValue())&&!StringUtil.empty(queryParam.getValue())){
					hql.append(" and t.auditStatus='").append(queryParam.getValue()).append("' ");
				}
			}
		}
		List list = this.findbyHql(hql.toString());
		return list;
	}
	
	
	/**
	 * Description : 根据查询条件统计对应的报名信息数据 Create Date: 2010-7-10上午10:57:34 by yangx
	 * Modified Date: 2010-7-10上午10:57:34 by yangx
	 * 
	 * @param queryObject
	 *            [auditStatus:审核状态;managerID:项目负责人,projectID:项目ID]
	 * @return
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SignUprecord> getSignUprecordSupplierListByQueryObject(
			QueryObject queryObject) {
		StringBuffer hql = new StringBuffer();
		hql.append("select distinct t.supplier,  from SignUprecord t where 1=1  ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if (queryList != null && !queryList.isEmpty()) {// 若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam) queryList.get(i);
				
				if("projectID".equals(queryParam.getValue())&&!StringUtil.empty(queryParam.getValue())){
					hql.append(" and t.project.objId in ");
					 hql.append(" (select distinct tm.obj from Project tm where tm.parentId='").append((String)queryParam.getValue()).append("' or tm.objId='").append((String)queryParam.getValue()).append("') ");
				}
				
				if("auditStatus".equals(queryParam.getValue())&&!StringUtil.empty(queryParam.getValue())){
					hql.append(" and t.auditStatus='").append(queryParam.getValue()).append("' ");
				}
			}
		}
		List list = this.findbyHql(hql.toString());
		return list;
	}
	

	/**
	 * 
	 * Description :根据项目和供应商得到报名信息 Create Date: 2010-8-30上午10:14:25 by liuke
	 * Modified Date: 2010-8-30上午10:14:25 by liuke
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	public List<SignUprecord> getSignUprecordListByprojectIdAndSupplierId(
			String projectId, String supplierId) {
		List<SignUprecord> list = this.findbyHql("from SignUprecord t where ( t.project.objId = ? or t.project.parentId=? ) and t.supplier.objId = ?",
						projectId, projectId, supplierId);
		return list;
	} 
	
	
	/** 
	 * FuncName : getWaitAuditSignUprecordByProjectid
	 * Description :  根据项目ID获取待审核的报名信息
	 * Create Date: 2011-8-8下午02:26:48 by yangx  Modified Date: 2011-8-8下午02:26:48 by yangx
	 * @param   projectid：项目Id 
	 * @return  List<SignUprecord>
	 * @Exception   
	 */
	public List<SignUprecord> getWaitAuditSignUprecordByProjectid(String projectId){
		return this.findbyHql("from SignUprecord t where t.auditStatus='"+AuditStatusEnum.WAIT_AUDIT+"' and (t.project.objId = '"+projectId+"' or t.project.parentId = '"+ projectId + "')");
	}
	/**
	 * FuncName: getCountSignUpSupplierByProjectId
	 * Description :  统计报名供应商家数
	 * @param projectId
	 * @author: zhouzhanghe
	 * @Create Date:2011-8-4 11:47
	 */
	public int getCountSignUpSupplierByProjectId(String projectId) {
		String sql = "select count(distinct supplyerid) from ECP_Tender_Apply_Rec etap where etap.tenderid in (select tenderid from ECP_Tender_Project  where (tenderid = '"+projectId+"' or parent_id = '"+projectId+"'))";
		List result = getSession().createSQLQuery(sql).list();
		return Integer.parseInt((result.get(0).toString()));
	}
	/** FuncName: getSignUprecordNotEntryBailRecord
	 * Description :  得到未录入保证金的项目记录
	 * @param 
	 * @return List<SignUprecord>
	 * @author: liuke
	 * @Create Date:2011-8-10  上午10:29:46
	 * @Modifier: liuke
	 * @Modified Date:2011-8-10  上午10:29:46
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getSignUprecordNotEntryBailRecord() {
	  List<Object> list =	(List) this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer();
				sql.append(" select count(1),p.tenderid,p.parent_id,ru.rule_signup_end_date,ru.rule_bail_end_date  ");
				sql.append(" from ECP_Tender_Apply_Rec etap   ");
				sql.append(" left join ECP_Tender_Project p  on  p.tenderid = etap.tenderid  ");
				sql.append(" left join ecp_tend_rule ru on (ru.tender_id = p.tenderid or ru.tender_id = p.parent_id)  ");
				sql.append(" where  etap.auditstatus = '01'  ");
				sql.append(" and p.tenderid not in (select distinct b.tenderid  from  ");
				sql.append(" ( select rc.tenderid ,count(1) n1 from ecp_bail_record rd ,ecp_tender_apply_rec rc  ");
				sql.append(" where rd.applyid=rc.applyid   ");
				sql.append(" and rd.bailstatus = '01' group by rc.tenderid) a,(select tenderid,count(1) n1 from  ecp_tender_apply_rec rc  ");
				sql.append(" where rc.auditstatus='01' group by tenderid) b  where a.tenderid=b.tenderid and a.n1 = b.n1)  ");
				sql.append(" group by p.tenderid,p.tendername,p.parent_id,ru.rule_signup_end_date,ru.rule_bail_end_date ");
				return session.createSQLQuery(sql.toString()).list();	
			}});
		return list;
	}
	
	/** 
	 * FuncName : getSignUprecordBySupplierId
	 * Description :  根据供应商Id获取报名记录
	 * Create Date: 2011-9-27下午03:15:34 by yangx  
	 * Modified Date: 2011-9-27下午03:15:34 by yangx
	 * @param   supplierId：供应商Id
	 * @return  List<SignUprecord>
	 * @Exception   
	 */
	public List<SignUprecord> getSignUprecordBySupplierId(String supplierId){
		String hql = "FROM SignUprecord s where s.supplier.objId='"+supplierId+"'";
		List<SignUprecord> signUprecordList = this.findbyHql(hql);
		return signUprecordList;
	}
}
