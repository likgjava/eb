package com.gpcsoft.epp.purchasedoc.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.purchasedoc.dao.PurchaseDocDao;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocEnum;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class PurchaseDocDaoHibernate extends BaseGenericDaoHibernate<PurchaseDoc> implements PurchaseDocDao {
	
	/**
	 * 
	 * Description : 根据项目主键得到采购文件 
	 * Create Date: 2010-5-19下午03:48:57 by liuke  Modified Date: 2010-5-19下午03:48:57 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public PurchaseDoc getPurchaseDocByProjectId(String projectId) {
	    List<PurchaseDoc> list = super.findbyHql("from PurchaseDoc purdoc where purdoc.fileType='"+PurchaseDocEnum.PURCHASEDOC+"' and purdoc.project.objId=?", projectId);
	    return (list.size()>0)?list.get(0):null;
	}

	public PurchaseDoc getPurchaseDocbyProjectIdAndStatus(String projectId,
			String status) {
		 List<PurchaseDoc> list = super.findbyHql("from PurchaseDoc purdoc where purdoc.project.objId=? and purdoc.auditStatus=?", projectId,status);
		 return (list.size()>0)?list.get(0):null;
	}

	public void savePurchaseDoc(PurchaseDoc purchaseDoc) {
		this.save(purchaseDoc);
	}

	public void updatePurchaseDoc(PurchaseDoc purchaseDoc) {
		purchaseDoc = this.getHibernateTemplate().merge(purchaseDoc);  
		this.getHibernateTemplate().update(purchaseDoc);

		
	}

	/** 
	 * Description :  根据查询条件统计对应的采购文件数量
	 * Create Date: 2010-7-7下午03:38:09 by Administrator  Modified Date: 2010-7-7下午03:38:09 by Administrator
	 * @param queryObject[buyerId:采购人ID;auditStatus:审核状态]
	 * @return
	 */
	public BigDecimal getPurDocTotalByQueryObject(QueryObject queryObject) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(t) from PurchaseDoc t where 1=1  ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if("buyerId".equals(queryParam.getName())){
					hql.append(" and t.project.objId in ");
					hql.append(" (select distinct tm.tproject.objId from ProjectMTaskPlan tm where tm.tproject.objId is not null and tm.buyMainBody.objId='"+(String)queryParam.getValue()+"') ");
				}
				if("auditStatus".equals(queryParam.getName())){
					hql.append(" and t.auditStatus='"+(String)queryParam.getValue()+"'");
				}
				if("monitorId".equals(queryParam.getName())){
					hql.append(" and t.project.objId in ");
					hql.append(" (select distinct tm.objId from Project tm where tm.objId is not null and tm.monitor.objId='"+(String)queryParam.getValue()+"') ");
				}
				if("useStatus".equals(queryParam.getName())){
					hql.append(" and t.useStatus='"+(String)queryParam.getValue()+"'");
				}
				/** 创建人 */
				if("createUser".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.createUser.objId='"+queryParam.getValue()+"'");
				}
				/** 文件类型*/
				if("fileType".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.fileType='"+queryParam.getValue()+"'");
				}
			}
		}
		List list=this.findbyHql(hql.toString());
		Object object = list.get(0);
		return new BigDecimal(object.toString());
	}
   
	
	/**
	 * 
	 * Description :得到更多采购文件信息  
	 * Create Date: 2010-7-13上午10:42:20 by liuke  Modified Date: 2010-7-13上午10:42:20 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
		public Page<PurchaseDoc> searchPurchaseDocsByQueryObject(
			Page<PurchaseDoc> page, QueryObject queryObject) {
			StringBuffer hql = new StringBuffer();
			hql.append("from PurchaseDoc pd where 1=1 ");
			
			List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
			if(queryList != null && !queryList.isEmpty()){
				for (int i = 0; i < queryList.size(); i++) {
					QueryParam queryParam = (QueryParam)queryList.get(i);
					if("buyerId".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
						hql.append(" and pd.project.objId in ");
						hql.append(" (select distinct tm.tproject.objId from ProjectMTaskPlan tm where tm.tproject.objId is not null and tm.buyMainBody.objId='"+(String)queryParam.getValue()+"') ");
					}
					/** 审核状态 */
					if("auditStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
						hql.append(" and pd.auditStatus = '"+(String)queryParam.getValue()+"'");
					}
					/** 项目名称 */
					if("projName".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
						hql.append(" and pd.project.projName like '%"+(String)queryParam.getValue()+"%'");
					}
					/** 项目编号 */
					if("projCode".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
						hql.append(" and pd.project.projCode like '%"+(String)queryParam.getValue()+"%'");
					}
					if("ebuyMethod".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
						hql.append(" and pd.project.ebuyMethod = '"+(String)queryParam.getValue()+"'");
					}
					/** 项目负责人 */
					//这里的采购文件修改是谁创建的谁修改，与谁是项目负责人无关
					if("managerID".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
						hql.append(" and pd.createUser.objId = '"+(String)queryParam.getValue()+"'");
					}
					/** 文件类型 */
					if("fileType".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
						hql.append(" and pd.fileType = '"+(String)queryParam.getValue()+"'");
					}
					/** 是否正式 */
					if("useStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
						hql.append(" and pd.useStatus = '"+(String)queryParam.getValue()+"'");
					}
					/**创建人*/
					if("creater".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
						hql.append(" and pd.createUser = '"+(String)queryParam.getValue()+"'");
					}
					/**监管人*/
					if("monitorId".equals(queryParam.getName())){
						hql.append(" and pd.project.objId in ");
						hql.append(" (select distinct tm.objId from Project tm where tm.objId is not null and tm.monitor.objId='"+(String)queryParam.getValue()+"') ");
					}
				}
			}
			hql.append(" order by createTime desc");
			Page<PurchaseDoc> pages = this.findbyHql(hql.toString(),page.getStart(),page.getPageSize(),PurchaseDoc.class);
			return pages;	
		}
		
		
		
		public PurchaseDoc getPurchaseDocByProjectIdAndFileType(String projectId,String fileType) {
			StringBuffer hql = new StringBuffer();
				hql.append(" from PurchaseDoc purdoc where purdoc.project.objId= '"+projectId+"' ");
			if(fileType!=null&&!"".equals(fileType)){
				hql.append(" and purdoc.fileType= '"+fileType+"' ");
			}else{
				hql.append(" and ( purdoc.fileType= '"+PurchaseDocEnum.PURCHASEDOC+"' or purdoc.fileType= '"+PurchaseDocEnum.INQPDOC+"') ");
			}
		    List<PurchaseDoc> list = super.findbyHql(hql.toString());
		    return (list.size()>0)?list.get(0):null;
		}

	/**
	 * 
	 * Description : [采购人]根据申报书明细，得到采购人的待确认采购文件列表 []
	 * Create Date: 2010-7-6下午05:34:00 by liuke  Modified Date: 2010-7-6下午05:34:00 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page getPurchaseDocByBuyer(Page<PurchaseDoc> page, User user,
			String fileType) {
		String buyerId = user.getOrgInfo().getObjId();
		StringBuffer hql = new StringBuffer();
		hql.append("from PurchaseDoc pd where pd.auditStatus = ? and pd.fileType=? and pd.project.objId in ");
		hql.append("(select distinct pmtp.tproject.objId from ProjectMTaskPlan pmtp where pmtp.buyMainBody.objId = ?)");
		hql.append(" order by pd.createTime desc ");
		Page pages = this.findbyHql(hql.toString(), page.getStart(), page.getPageSize(),PurchaseDocEnum.FORAUDIT,fileType,buyerId);
		return pages;
	}
		
	/** 
	 * Description :  根据项目Id、招标文件Id获取未录入购买记录的且报名的供应商
	 * Create Date: 2010-10-28下午02:17:47 by yangx  Modified Date: 2010-10-28下午02:17:47 by yangx
	 * @param   projectId：项目Id,purchaseDocId：招标文件Id
	 * @return  List<SignUprecord>
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List getSupplierByProjectIdAndPurchaseDocId(String projectId,String purchaseDocId){
		StringBuffer hql = new StringBuffer();
		hql.append("From SignUprecord t where ");
		hql.append(" t.project.objId='"+projectId+"'");
		hql.append(" and t.supplier.objId not in");
		hql.append(" (select d.supplierId from DosBuyRecord d where d.fileId='"+purchaseDocId+"')");
		return this.findbyHql(hql.toString());
	}

	
	/**
	 * FuncName: getPurchaseDocByProjCode
	 * Description :  创建或修改对象
	 * @param 
	 * @param projCode
	 * @return PurchaseDoc
	 * @author: liuke
	 * @Create Date:2011-5-9  下午03:54:24
	 * @Modifier: liuke
	 * @Modified Date:2011-5-9  下午03:54:24
	 */
	public PurchaseDoc getPurchaseDocByProjCode(String projCode) {
		 List<PurchaseDoc> list =	this.findbyHql("from PurchaseDoc purdoc where purdoc.fileType='"+PurchaseDocEnum.PURCHASEDOC+"' and purdoc.project.projCode=?", projCode);
		return (list.size()>0)?list.get(0):null;
	}
}
