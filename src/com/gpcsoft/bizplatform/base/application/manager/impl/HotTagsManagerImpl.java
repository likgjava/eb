package com.gpcsoft.bizplatform.base.application.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.application.dao.HotTagsDao;
import com.gpcsoft.bizplatform.base.application.domain.HotTags;
import com.gpcsoft.bizplatform.base.application.manager.HotTagsManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class HotTagsManagerImpl extends BaseManagerImpl<HotTags> implements HotTagsManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("hotTagsDaoHibernate")
	private HotTagsDao hotTagsDaoHibernate;

}
