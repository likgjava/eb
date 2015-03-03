package com.gpcsoft.pubservice.application.member.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.pubservice.application.member.dao.MemberClassDao;
import com.gpcsoft.pubservice.application.member.domain.MemberClass;
import com.gpcsoft.pubservice.application.service.enumeration.ServiceEnum;

@Repository
public class MemberClassDaoHibernate extends BaseGenericDaoHibernate<MemberClass> implements MemberClassDao {

	/** 
	 * Description :  获得初始普通会员级别
	 * Create Date: 2011-3-30下午03:53:20 by likg  Modified Date: 2011-3-30下午03:53:20 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public MemberClass getInitMemberClass() throws Exception {
		MemberClass memberClass = null;
		List<MemberClass> memberClassList = this.findbyHql("from MemberClass mc where mc.memberClassNum=?", new BigDecimal(ServiceEnum.DEFAULT));
		if(memberClassList!=null && memberClassList.size()>0) {
			memberClass = memberClassList.get(0);
		}
		return memberClass;
	}
	
	/** 
	 * Description :  根据总缴费金额返回级别（最最大级别）
	 * Create Date: 2011-3-23上午10:16:07 by sunl  Modified Date: 2011-3-23上午10:16:07 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public MemberClass getMemberClassByDurationAndFee(Map<String,Object> params) throws Exception {
		MemberClass memberClass = null;
		StringBuilder sql = new StringBuilder();
		sql.append("from MemberClass mc where ? between mc.minFee and mc.maxFee ");
		sql.append("order by mc.memberClassNum desc ");
		
		Query query = this.getSession().createQuery(sql.toString());
		query.setBigDecimal(0, BigDecimal.valueOf((Double)params.get("totalFee")));
		
		List<MemberClass> memberClassList = query.list();
		
		//获取级别最大的
		if(memberClassList!=null && memberClassList.size()>0 && memberClassList.get(0) != null) {
			memberClass = memberClassList.get(0);
		}
		return memberClass;
	}
}
