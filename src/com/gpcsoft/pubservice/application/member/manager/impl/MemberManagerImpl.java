package com.gpcsoft.pubservice.application.member.manager.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.member.dao.MemberClassDao;
import com.gpcsoft.pubservice.application.member.dao.MemberDao;
import com.gpcsoft.pubservice.application.member.domain.Member;
import com.gpcsoft.pubservice.application.member.domain.MemberClass;
import com.gpcsoft.pubservice.application.member.manager.MemberManager;
import com.gpcsoft.pubservice.application.service.dao.ServiceSubscribeDao;

@Repository
public class MemberManagerImpl extends BaseManagerImpl<Member> implements MemberManager {

	@Autowired(required=true)  @Qualifier("memberDaoHibernate")
	private MemberDao memberDao;
	
	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("serviceSubscribeDaoHibernate")
	private ServiceSubscribeDao serviceSubscribeDao;
	@Autowired(required=true)  @Qualifier("memberClassDaoHibernate")
	private MemberClassDao memberClassDao;

	/** 
	 * Description :  同步会员信息
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void syncMemberInfo(Map<String,Object> params) throws Exception {
		String memberClassId = (String)params.get("memberClassId");//会员级别ID
		Double payAmount = ((BigDecimal)params.get("payAmount")).doubleValue();//缴费金额
		
		//获取会员
		Member member = memberDao.getMemberByOrgInfoId(params);
		
		//更新缴费金额和会员级别
		member.setMemberClass(memberClassDao.get(memberClassId));
		member.setPayAmount(BigDecimal.valueOf(member.getPayAmount().doubleValue() + payAmount));
		
		memberDao.save(member);
	}
	
	/** 
	 * Description :  初始化会员数据
	 * Create Date: 2011-3-30下午03:26:39 by sunl  Modified Date: 2011-3-30下午03:26:39 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void initMemberInfo(Map<String,Object> params) throws Exception {
		Member member = new Member();
		member.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		member.setCreateTime(new Date());
		member.setOrgInfo((OrgInfo)params.get("orgInfo"));
		member.setPayAmount(BigDecimal.valueOf(0));//缴费金额
		member.setMemberDuration(BigDecimal.valueOf(0));//购买服务时长
		member.setMemberClass(memberClassDao.getInitMemberClass());//获取初始会员级别
		memberDao.save(member);
	}
	
	/** 
	 * Description :  根据机构ID获取会员信息
	 * Create Date: 2011-3-30下午03:26:39 by sunl  Modified Date: 2011-3-30下午03:26:39 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Member getMemberByOrgInfoId(Map<String,Object> params) throws Exception {
		Member member = memberDao.getMemberByOrgInfoId(params);
		
		//没有机构的会员信息，臆造一条会员信息（级别为0）
		if(null == member) {
			MemberClass initMemberClass = memberClassDao.getInitMemberClass();//获取0及会员级别
			
			if(initMemberClass == null) {//如果没有0会员级别,臆造一条0级的会员级别
				initMemberClass = new MemberClass();
				initMemberClass.setMaxAge(BigDecimal.valueOf(0));
				initMemberClass.setMinAge(BigDecimal.valueOf(0));
				initMemberClass.setMaxFee(BigDecimal.valueOf(0));
				initMemberClass.setMinFee(BigDecimal.valueOf(0));
				initMemberClass.setMemberClassNum(BigDecimal.valueOf(0));
			}
			member = new Member();
			member.setMemberClass(initMemberClass);
			member.setMemberDuration(BigDecimal.valueOf(0));
			member.setMemberDuration(BigDecimal.valueOf(0));
			member.setPayAmount(BigDecimal.valueOf(0));
		}
		return member;
	}
}
