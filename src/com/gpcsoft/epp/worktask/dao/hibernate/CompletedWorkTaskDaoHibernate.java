package com.gpcsoft.epp.worktask.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.worktask.dao.CompletedWorkTaskDao;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTask;

@Repository
public class CompletedWorkTaskDaoHibernate extends BaseGenericDaoHibernate<CompletedWorkTask> implements CompletedWorkTaskDao {

}
