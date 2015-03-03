package com.gpcsoft.agreement.ftl.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.agreement.ftl.dao.FtlTemplatePropertyDao;
import com.gpcsoft.agreement.ftl.domain.FtlTemplateProperty;
import org.springframework.stereotype.Repository;

@Repository
public class FtlTemplatePropertyDaoHibernate extends BaseGenericDaoHibernate<FtlTemplateProperty> implements FtlTemplatePropertyDao {

}
