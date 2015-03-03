package com.gpcsoft.epp.worktask.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.worktask.dao.WorktaskReceiveDao;
import com.gpcsoft.epp.worktask.domain.WorktaskReceive;

import org.springframework.stereotype.Repository;

@Repository
public class WorktaskReceiveDaoHibernate extends BaseGenericDaoHibernate<WorktaskReceive> implements WorktaskReceiveDao {

}
