package com.gpcsoft.epp.bid.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.bid.dao.BailRecordDao;
import com.gpcsoft.epp.bid.dao.BidDao;
import com.gpcsoft.epp.bid.domain.BailRecord;
import com.gpcsoft.epp.bid.domain.BailStatusEnum;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.manager.BidManager;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.project.dao.ProjectDao;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.signuprecord.dao.SignUprecordDao;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;

@Repository
public class BidManagerImpl extends BaseManagerImpl<Bid> implements BidManager {

	@Autowired(required=true)  @Qualifier("bidDaoHibernate")
	private BidDao bidDaoHibernate;

	@Autowired(required=true)  @Qualifier("projectDaoHibernate")
	private ProjectDao projectDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("signUprecordDaoHibernate")
	private SignUprecordDao signUprecordDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("bailRecordDaoHibernate")
	private BailRecordDao bailRecordDaoHibernate;
	
	/**
	 * Funcname:saveOrUpdateBid
	 * Description : 修改或更新投标对象
	 * @Create Date: 2010-5-17下午02:34:27 
	 * @author liuke  
	 * @Modified Date: 2010-5-17下午02:34:27  
	 * @author liuke
	 * @param   Bid bid:投标对象
	 * @return  void
	 * @Exception
	 */
	public void saveOrUpdateBid(Bid bid) {
		bidDaoHibernate.saveOrUpdateBid(bid);
	}
	
	/**
	 * FuncName:getBidByQueryObject
	 * Description:根据查询条件得到投标对象 
	 * @Create Date: 2010-5-19下午06:59:31 
	 * @author liuke  
	 * @Modified Date: 2010-5-19下午06:59:31  
	 * @author liuke
	 * @param   queryObject:平台封装对象
	 * @return  Bid:投标对象
	 * @Exception
	 */
	public Bid getBidByQueryObject(QueryObject queryObject) {
		StringBuffer hql = new StringBuffer();
		hql.append("");
		List list = bidDaoHibernate.findByQuery(queryObject);
		//List list = bidDaoHibernate.findByQuery(queryObject);
		if(list.size()>0){
			return (Bid)list.get(0);		
		}else {
			return null;
		}	
	}
	
	/**
	 * Funcname:getAllBidByProjectId
	 * Description :通过项目主键得到投标列表  
	 * @Create Date: 2010-5-26上午09:58:30 
	 * @author liuke  
	 * @Modified Date: 2010-5-26上午09:58:30 
	 * @author liuke
	 * @param   projectId:项目主键
	 * @return  List<Bid>
	 * @Exception
	 */
	public List<Bid> getAllBidByProjectId(String projectId) {
		return bidDaoHibernate.getAllBidByProjectId(projectId);
	}
	
	/**
	 * FuncName:getAllPageBidByProjectId
	 * Description : 通过项目主键得到投标列表   
	 * @Create Date: 2010-5-26下午02:56:43 
	 * @author liuke  
	 * @Modified Date: 2010-5-26下午02:56:43 
	 * @author liuke
	 * @param   projectId:项目主键,start;起始条,pagesize:每页显示条数
	 * @return  Page<Bid>
	 * @Exception
	 */
	public Page<Bid> getAllPageBidByProjectId(String projectId, int start,
			int pagesize) {
		return bidDaoHibernate.findbyHql("from Bid bid where bid.project.objId =?", start, pagesize, projectId);
	}
	
	/**
	 * FuncName:getBidListByProjectIdAndUserId
	 * Description :根据项目主键和供应商得到所有投标对象列表  
	 * @Create Date: 2010-6-24上午11:06:12 
	 * @author liuke 
	 * @Modified Date: 2010-6-24上午11:06:12 
	 * @author liuke
	 * @param   projectId:项目主键,supplierId：供应商主键
	 * @return  List<Bid>:投标对象集合
	 * @Exception
	 */
	public List<Bid> getBidListByProjectIdAndUserId(String projectId,String supplierId) {
		List<Bid> list = bidDaoHibernate.getBidListByProjectIdAndUserId(projectId, supplierId);
		return list;
	}
	
	/**
	 * FuncName: getBidAnonymousListByProjectIdAndUserId
	 * Description :  根据项目主键和匿名投标号得到所有投标对象列表  
	 * @param 
	 * @param projectId
	 * @param bidNo
	 * @return List<Bid>
	 * @author: shenjz
	 * @Create Date:2011-12-13  上午11:34:46
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-13  上午11:34:46
	 */
	public List<Bid> getBidAnonymousListByProjectIdAndBidNo(String projectId,String bidNo) {
		List<Bid> list = bidDaoHibernate.getBidAnonymousListByProjectIdAndBidNO(projectId, bidNo);
		return list;
	}

	/**
	 * Funcname:getBidPackByBidId
	 * Description: 根据投标ID获取投标投标包件,只返回包件ID
	 * @param bidId:投标对象主键
	 * @return List<Project>
	 * @throws Exception
	 * @Create Date 2010-8-5 上午10:42:22 
	 * @author  wanghz
	 */
	public List<Project> getBidPackByBidId(String bidId) {
		return bidDaoHibernate.getBidPackByBidId(bidId);
	}

	/**
	 * Funcname:isCanBid
	 * Description :是否可以投标  
	 * @Create Date: 2010-8-30上午10:04:27 
	 * @author liuke  
	 * @Modified Date: 2010-8-30上午10:04:27 
	 * @author liuke
	 * @param   projectId:项目主键,supplierId:供应商主键,bailRecord:投标保证金对象
	 * @return  boolean
	 * @Exception
	 */
	public boolean isCanBid(String projectId, String supplierId,BailRecord bailRecord) throws EsException{
		StringBuffer exception = new StringBuffer();
		Boolean checkValue = true;//判断是否有不满足条件的数据
		if (null==projectId ||"".equals(projectId)) {//项目不存在
			exception.append(messageSource.getMessage(EsExceptionEnum.PROJECT_ISNOTEXISTED));
		} else {
/*			SignUprecord signUprecord = signUprecordDaoHibernate.getSignUprecordByprojectIdAndSupplierId(projectId, supplierId);
			if (null==signUprecord){//未报名
				exception.append(messageSource.getMessage(EsExceptionEnum.SIGNUPRECORD_IS_NONE)).append(",");
			}else if(signUprecord!=null&&AuditStatusEnum.WAIT_AUDIT.equals(signUprecord.getAuditStatus())){  //报名待审核
				exception.append(messageSource.getMessage(EsExceptionEnum.SIGNUPRECORD_WAIT_AUDIT)).append(",");
			}else if(signUprecord!=null&&AuditStatusEnum.AUDIT_NO_PASS.equals(signUprecord.getAuditStatus())){//报名审核未通过
				exception.append(messageSource.getMessage(EsExceptionEnum.SIGNUPRECORD_IS_NO_PASS)).append(",");
			}
			if(signUprecord!=null&&bailRecord==null){  //报名记录不为空
				bailRecord = bailRecordDaoHibernate.getBailRecordBySignUprecord(signUprecord.getObjId());
			}
*/			ProjectRule projectRule = projectDaoHibernate.getProjectRuleByProjectId(projectId);
			Date today = new Date();
			if(today.before(projectRule.getSubmitStTime())){//未到投标开始时间
				exception.append(messageSource.getMessage(EsExceptionEnum.BEFORE_SUBMIT_TIME)).append(",");
			}
			if(today.after(projectRule.getSubmitETime())){//投标时间已过
				exception.append(messageSource.getMessage(EsExceptionEnum.AFTER_SUBMIT_TIME)).append(",");
			}
			/*if(null==bailRecord ||!BailStatusEnum.ALREADY_RECEIVE.equals(bailRecord.getBailStatus())) {  //未缴纳保证金
				exception.append(messageSource.getMessage(EsExceptionEnum.BAILRECORDID_BAILSTATUS_UNERCEIVED)).append(",");
			}*/
		}
		if(exception.length()>0)checkValue=false;//若有异常信息，表示有异常，需要把判断置为false;
		if (!checkValue) {
			String exceptionStr = exception.toString();
			throw new EsException(exceptionStr.substring(0, exceptionStr.lastIndexOf(",")));//若有影响项目操作的数据，则抛出异常
		}
		return checkValue;
	}

	/** 
	 * Funcname:removeBidByProject
	 * Description :删除投标信息
	 * @Create Date: 2010-12-15下午07:26:10  
	 * @author liuke
	 * @Modified Date: 2010-12-15下午07:26:10 
	 * @author liuke
	 * @param projectId
	 * @return  void
	 * @Exception
	 */
	public void removeBidByProject(String projectId) {
		bidDaoHibernate.removeAllBidByProject(projectId);
	}
}
