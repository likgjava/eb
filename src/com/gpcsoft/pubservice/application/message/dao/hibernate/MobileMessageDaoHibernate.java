package com.gpcsoft.pubservice.application.message.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.pubservice.application.message.dao.MobileMessageDao;
import com.gpcsoft.pubservice.application.message.domain.MobileMessage;
import org.springframework.stereotype.Repository;

@Repository
public class MobileMessageDaoHibernate extends BaseGenericDaoHibernate<MobileMessage> implements MobileMessageDao {

}
