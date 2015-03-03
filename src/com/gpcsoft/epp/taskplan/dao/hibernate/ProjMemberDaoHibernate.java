package com.gpcsoft.epp.taskplan.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.taskplan.dao.ProjMemberDao;
import com.gpcsoft.epp.taskplan.domain.ProjMember;
import org.springframework.stereotype.Repository;

@Repository
public class ProjMemberDaoHibernate extends BaseGenericDaoHibernate<ProjMember> implements ProjMemberDao {

}
