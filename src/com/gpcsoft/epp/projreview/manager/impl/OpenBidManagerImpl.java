package com.gpcsoft.epp.projreview.manager.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.bid.dao.BailRecordDao;
import com.gpcsoft.epp.bid.dao.BidPackageDao;
import com.gpcsoft.epp.bid.domain.BailStatusEnum;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.projreview.dao.OpenBidDao;
import com.gpcsoft.epp.projreview.domain.OpenBid;
import com.gpcsoft.epp.projreview.manager.OpenBidManager;
import com.gpcsoft.srplatform.sysconfig.dao.SysConfigItemValueDao;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigItemValue;

@Repository
public  class OpenBidManagerImpl extends BaseManagerImpl<OpenBid> implements OpenBidManager {

	@Autowired(required=true)  @Qualifier("openBidDaoHibernate")
	private OpenBidDao openBidDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("bidPackageDaoHibernate")
	private BidPackageDao bidPackageDaoHibernate;
	
	@Autowired(required = true) @Qualifier("sysConfigItemValueDaoImpl")
	private SysConfigItemValueDao sysConfigItemValueDao;
	
	@Autowired(required=true) @Qualifier("bailRecordDaoHibernate")
	private BailRecordDao bailRecordDao;
	
	/**
	 * FuncName:getOpenBidByQueryObject
	 * Description :根据查询对象得到开标对象  
	 * @param   queryObject:查询对象
	 * @return OpenBid
	 * @author liuke  
	 * @Create Date: 2010-5-24下午01:13:46 
	 * @Modifier    liuke
	 * @Modified Date: 2010-5-24下午01:13:46 
	 */
	public OpenBid getOpenBidByQueryObject(QueryObject queryObject) {
		List list = openBidDaoHibernate.findByQuery(queryObject);
		if(list.size()>0) {
			return (OpenBid) list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * FuncName:saveOpenBid
	 * Description :保存开标对象  
	 * @param   openBid:开标对象
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-5-25下午05:23:23 
	 * @Modifier   liuke
	 * @Modified Date: 2010-5-25下午05:23:23  
	 */
	public void saveOpenBid(OpenBid openBid) {
		openBidDaoHibernate.saveOpenBid(openBid);
	}
	
	/**
	 * FuncName:getOpenBidByprojectId
	 * Description :  根据项目主键得到开标主表对象
	 * @param   projectId:项目主键
	 * @return  OpenBid
	 * @author liuke
	 * @Create Date: 2010-6-4下午02:11:29  
	 * @Modifier liuke
	 * @Modified Date: 2010-6-4下午02:11:29  
	 */
	public OpenBid getOpenBidByprojectId(String projectId) {
		QueryObject<OpenBid> queryObject = new QueryObjectBase<OpenBid>();
		queryObject.setEntityClass(OpenBid.class);
		queryObject.setQueryParam(new QueryParam("projId",QueryParam.OPERATOR_EQ,projectId));
		List list = openBidDaoHibernate.findByQuery(queryObject);
        if(list != null && !list.isEmpty()){
        	return (OpenBid) list.get(0);
        }else{
        	return null;
        }
	}
	
	/**
	 * FuncName:getOpenBidBySubProjectId
	 * Description :根据包组主键得到开标主表对象
	 * @param   subProjectId 包组主键
	 * @return  OpenBid
	 * @author liuke
	 * @Create Date: 2010-6-4下午02:11:29 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-4下午02:11:29 
	 */
	public OpenBid getOpenBidBySubProjectId(String subProjectId) {
		QueryObject<OpenBid> queryObject = new QueryObjectBase<OpenBid>();
		queryObject.setEntityClass(OpenBid.class);
		queryObject.setQueryParam(new QueryParam("subProjId",QueryParam.OPERATOR_EQ,subProjectId));
		List list = openBidDaoHibernate.findByQuery(queryObject);
        if(list != null && !list.isEmpty()){
        	return (OpenBid) list.get(0);
        } else {
        	return null;
        }
	}
	
	/** 
	 * FuncName:isJudgeSupplierNum
	 * Description :  判断投标供应商数量是否符合要求
	 * @param   ebuyMethod:采购方式,projectId:项目主键,projName:项目名称
	 * @return  Boolean
	 * @author yangx
	 * @Create Date: 2010-11-4下午04:50:05 
	 * @Modifier    yangx
	 * @Modified Date: 2010-11-4下午04:50:05 
	 */
	public Boolean isJudgeSupplierNum (String ebuyMethod,String projectId,String projName)throws Exception{
		StringBuffer exception = new StringBuffer();
		Boolean checkValue = true;//判断是否有不满足条件的数据
		if (ebuyMethod!=null&&EbuyMethodEnum.OPEN_BIDDING.equals(ebuyMethod)) {// 公开招标
			exception.append(this.checkSupplierNum(projectId,projName,SysConfigEnum.PubleOpenBid__OPENBIDDINGRULE_PURCHASESUPPLIERNUM,SysConfigEnum.PubleOpenBid__OPENBIDDINGRULE_PURCHASESUPPLIERMAXNUM));
		}else if (ebuyMethod!=null&&EbuyMethodEnum.INVITE_BIDDING.equals(ebuyMethod)) {//邀请招标
			exception.append(this.checkSupplierNum(projectId,projName,SysConfigEnum.Bidding__INVITEBIDDINGRULE_INVITEBIDDINGSUPPLIERNUM,SysConfigEnum.Bidding__INVITEBIDDINGRULE_INVITEBIDDINGSUPPLIERMAXNUM));
		}else if (ebuyMethod!=null&&EbuyMethodEnum.NEGOTIATE.equals(ebuyMethod)) {//竞争性谈判
			exception.append(this.checkSupplierNum(projectId,projName,SysConfigEnum.CompetitionPalaver__NEGOTIATERULE_NEGOTIATESUPPLIERNUM,SysConfigEnum.CompetitionPalaver__NEGOTIATERULE_NEGOTIATESUPPLIERMAXNUM));
		}else if (ebuyMethod!=null&&EbuyMethodEnum.INQUIRY.equals(ebuyMethod)) {//询价
			exception.append(this.checkSupplierNum(projectId,projName,SysConfigEnum.InquirePrice__INQUIRYRULE_INQUIRYSUPPLIERNUM,SysConfigEnum.InquirePrice__INQUIRYRULE_INQUIRYSUPPLIERMAXNUM));
		}else if (ebuyMethod!=null&&EbuyMethodEnum.SINGLE_SOURCE.equals(ebuyMethod)) {//单一来源
			exception.append(this.checkSupplierNum(projectId,projName,SysConfigEnum.SinglenessSource__SINGLESOURCERULE_SINGLESOURCESUPPLIERNUM,SysConfigEnum.SinglenessSource__SINGLESOURCERULE_SINGLESOURCESUPPLIERMAXNUM));
		}else {
			exception.append(messageSource.getMessage(EsExceptionEnum.PROJECT_ISNOTEXISTED));
		}
		if(exception.length()>0)checkValue=false;//若有异常信息，表示有异常，需要把判断置为false;
		if (!checkValue) {
			throw new EsException(exception.toString());//若有影响项目操作的数据，则抛出异常
		}
		return checkValue;
	}
	
	/**
	 * FuncName:isSupplyerInBailrecord
	 * Description : 用以判断是否所有供应商都录入保证金缴纳情况
	 * @param projectId：项目Id,signUprecordId：报名Id
	 * @return  Boolean
	 * @author shenjianzhuang
	 * @Create Date: 2010-9-20上午10:19:27 
	 * @Modifier shenjianzhuang
	 * @Modified Date: 2010-9-20上午10:19:27  
	 */	
	public Boolean isSupplyerInBailrecord(String projectId,String signUprecordId) {
		StringBuffer exception = new StringBuffer();
		Boolean checkValue = true;//判断是否有不满足条件的数据
		if (null==projectId ||"".equals(projectId)) {//项目不存在
			exception.append(messageSource.getMessage(EsExceptionEnum.PROJECT_ISNOTEXISTED));
		} else {
				if(bailRecordDao.getBailRecordBySignUprecord(signUprecordId)==null) {
					exception.append(messageSource.getMessage(EsExceptionEnum.SUPPLIER_NOTIN_BAILRECORD));
				}else if(bailRecordDao.getBailRecordBySignUprecord(signUprecordId).getBailStatus().equals(BailStatusEnum.NO_RECEIVE)){
					exception.append(messageSource.getMessage(EsExceptionEnum.SUPPLIER_NO_PAYBAILMONEY));
				}
			}
		if(exception.length()>0)checkValue=false;//若有异常信息，表示有异常，需要把判断置为false;
		if (!checkValue) {
			throw new EsException(exception.toString());//若有影响项目操作的数据，则抛出异常
		}
		return checkValue;
	}
	
	/** 
	 * FuncName:checkSupplierNum
	 * Description:检查供应商数量是否正确
	 * @param   projectId:项目Id,sysCode:系统配置中的编号
	 * @return  String
	 * @author yangx
	 * @Create Date: 2010-10-28下午06:20:13    
	 * @author yangx
	 * @Modified Date: 2010-10-28下午06:20:13    
	 */
	private String checkSupplierNum(String projectId,String projName,String sysMixCode,String sysMaxCode) throws Exception{
		String msg = "";
		List<SysConfigItemValue> mixValueList = sysConfigItemValueDao.getSysConfigItemValuesByItemCode(sysMixCode);//查询系统配置中最少的投标供应商数量
		List<SysConfigItemValue> maxValueList = sysConfigItemValueDao.getSysConfigItemValuesByItemCode(sysMaxCode);//查询系统配置中最多的投标供应商数量
		List<BidPackage> bidPackageList = bidPackageDaoHibernate.getAllByPackId(projectId);//投标的供应商数量
		BigDecimal bidList = new BigDecimal(bidPackageList.size());
		SysConfigItemValue sysConfigItemMixValue = null;
		SysConfigItemValue sysConfigItemMaxValue = null;
		if (mixValueList!=null&&mixValueList.size()>0) {//判断是否没有配置最小值
			sysConfigItemMixValue =  mixValueList.get(0);
			BigDecimal mixValue = new BigDecimal(sysConfigItemMixValue.getValue());
			if (bidList.compareTo(mixValue)==-1) {//判断是否比最小值还小
				msg = projName+messageSource.getMessage(EsExceptionEnum.SUPPLIER_LESS);
				msg = msg.replace("X",mixValue.toString());
			}
		}
		if (maxValueList!=null&&maxValueList.size()>0) {//判断是否没有配置最大值
			sysConfigItemMaxValue = maxValueList.get(0);
			BigDecimal maxValue = new BigDecimal(sysConfigItemMaxValue.getValue());
			if (bidList.compareTo(maxValue)==1) {//判断是否比最大值还大
				msg = projName+messageSource.getMessage(EsExceptionEnum.SUPPLIER_MORE);
				msg = msg.replace("X",maxValue.toString());
			} else if (bidPackageList.size()<1) {//判断是否没有供应商投标
				msg = projName+messageSource.getMessage(EsExceptionEnum.SUPPLIER_NO);
			}
		}
		if (sysConfigItemMixValue==null&&sysConfigItemMaxValue==null) {//都没有配置
			msg = messageSource.getMessage(EsExceptionEnum.SUPPLIER_NOTNUM);
		}
		return msg;
	}
	
	/**
	 * FuncName:removeAllOpenBidByProject
	 * Description :根据项目删除开标信息
	 * @param projectId:项目主键
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-12-15下午05:46:51 
	 * @Modifier    liuke
	 * @Modified Date: 2010-12-15下午05:46:51 by 
	 */
	public void removeAllOpenBidByProject(String projectId) {
		openBidDaoHibernate.removeAllOpenBidByProject(projectId);
	}
}
