package com.gpcsoft.pubservice.application.member.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.pubservice.application.member.dao.MemberDao;
import com.gpcsoft.pubservice.application.member.domain.Member;

@Repository
public class MemberDaoHibernate extends BaseGenericDaoHibernate<Member> implements MemberDao {
	/** 
	 * Description :  根据机构ID获取会员信息
	 * Create Date: 2011-3-30下午03:26:39 by sunl  Modified Date: 2011-3-30下午03:26:39 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Member getMemberByOrgInfoId(Map<String,Object> params) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("from Member m where m.orgInfo.objId=?");
		List<Member> memberList = this.findbyHql(hql.toString(), (String)params.get("orgInfoId"));
		Member member = null;
		if(null != memberList && memberList.size()>0) {
			member = memberList.get(0);
		}
		return member;
	}
}
