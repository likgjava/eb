package com.gpcsoft.smallscale.pointmall.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.pointmall.dao.GiftCommentDao;
import com.gpcsoft.smallscale.pointmall.manager.GiftCommentManager;
import com.gpcsoft.smallscale.pointmall.domain.GiftComment;

@Repository
public class GiftCommentManagerImpl extends BaseManagerImpl<GiftComment> implements GiftCommentManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("giftCommentDaoHibernate")
	private GiftCommentDao giftCommentDaoHibernate;

}
