package com.gpcsoft.epp.projreview.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.bid.dao.BailRecordDao;
import com.gpcsoft.epp.bid.dao.BidDao;
import com.gpcsoft.epp.bid.domain.BailRecord;
import com.gpcsoft.epp.bid.domain.BailStatusCNEnum;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.manager.IAccountAsset;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.dao.ProjectDao;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.projreview.dao.OpenBidSignDao;
import com.gpcsoft.epp.projreview.domain.OpenBidSign;
import com.gpcsoft.epp.projreview.manager.OpenBidSignManager;
import com.gpcsoft.epp.projreview.service.OpenBidSignService;
import com.gpcsoft.epp.signuprecord.dao.SignUprecordDao;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class OpenBidSignServiceImpl extends BaseGenericServiceImpl<OpenBidSign> implements OpenBidSignService {

	@Autowired(required=true) @Qualifier("openBidSignManagerImpl")
	private OpenBidSignManager openBidSignManager;
	
	@Autowired(required=true) @Qualifier("openBidSignDaoHibernate")
	private OpenBidSignDao openBidSignDao;
	
	@Autowired(required=true) @Qualifier("bidDaoHibernate")
	private BidDao bidDao;
	
	@Autowired(required=true) @Qualifier("projectDaoHibernate")
	private ProjectDao projectDao;
	
	@Autowired(required=true) @Qualifier("bailRecordDaoHibernate")
	private BailRecordDao bailRecordDao;
	
	@Autowired(required=true)  @Qualifier("signUprecordDaoHibernate")
	public SignUprecordDao signUprecordDaoHibernate;
	
	private IAccountAsset accountAsset;	
	private IAccountAsset getAccountAssetImpl(){
		if(this.accountAsset == null){
			this.accountAsset = (IAccountAsset)FrameBeanFactory.getBean("accountAssetImpl");
		}
		return this.accountAsset;
	}
	   /**
	 * FuncName: anonymousSignUp
	 * Description :  匿名投标
	 * @param 
	 * @param bidNo
	 * @param openBidSign
	 * @param orginfox
	 * @param currentUser
	 * @param csSn void
	 * @author: shenjz
	 * @Create Date:2011-12-25  下午02:25:39
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-25  下午02:25:39
	*/		 
	public void saveAnonymousSignUp(String bidNo,String projectId,OpenBidSign openBidSign,
			OrgInfo orginfo, User currentUser, String csSn){
		/**
		 * 1.确认当前用户是否和KEY使用者是同一个机构
		 * 2.确认时需要核对是否为对应的招标项目/包组
		 * 3,匹配投标文件,签到记录
		 * 4.确认成功后将报名记录维护到数据库中(暂缓)
		 * 5.标书匹配成功后调申请扣除保证金，并将申请的情况写入到保证金记录表中
		 */
		List<Bid> bidList = bidDao.getBidAnonymousListByProjectIdAndBidNO(projectId, bidNo);
		Project project = projectDao.get(projectId);
		Bid bid =null;
		if(bidList!=null&&!bidList.isEmpty()){
			bid = bidList.get(0);
			bid.setSupplier(orginfo);
			bid.setSupplierName(orginfo.getOrgName());
			bidDao.save(bid);
			openBidSignManager.save(openBidSign);
			//保存报名记录
			SignUprecord signUprecord = new SignUprecord();
			signUprecord.setProject(project);
			signUprecord.setSupplier(orginfo);
			signUprecord.setSupplierName(orginfo.getOrgName());
			signUprecord.setApplyStatus("01");
			signUprecord.setAuditStatus("01");//报名通过
			signUprecord.setCreateUser(currentUser);
			signUprecordDaoHibernate.save(signUprecord);
			if(this.getAccountAssetImpl() != null){//若有资金管理，则扣除相关保证金
				if(this.getAccountAssetImpl().saveAccountForFreeze(currentUser.getObjId(), orginfo.getObjId(), project.getBail().toString())){
					//若扣除成功，则将数据添加到保证金缴纳记录里;
					BailRecord bailRecord = new BailRecord();
					bailRecord.setBallMoney(project.getBail());
					bailRecord.setSignUprecord(signUprecord);
					bailRecord.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
					bailRecord.setAuditStatus(AuditStatusEnum.AUDIT_PASS);
					bailRecord.setBailStatus(BailStatusCNEnum.ALREADY_RECEIVE);
					bailRecord.setProjId(project.getObjId());
					bailRecord.setProjName(project.getProjName());
					bailRecord.setCreateUser(currentUser);
					bailRecordDao.save(bailRecord);
				}
			}
		}
		
	}
	/**
	 * FuncName: getListBySupplierIdAndTenderId
	 * Description :  创建或修改对象
	 * @param 
	 * @param orginfo
	 * @param tenderId
	 * @return List<OpenBidSign>
	 * @author: shenjz
	 * @Create Date:2011-12-25  下午04:47:02
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-25  下午04:47:02
	 */
	public List<OpenBidSign> getListBySupplierIdAndTenderId(OrgInfo orginfo,String tenderId){
		return openBidSignDao.getOpenBidSignByTenderIdAndOrgCode(tenderId, orginfo.getOrgCode());
	}
}
