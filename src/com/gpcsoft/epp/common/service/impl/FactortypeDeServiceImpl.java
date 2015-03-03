package com.gpcsoft.epp.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.common.dao.FactortypeDeDao;
import com.gpcsoft.epp.common.domain.FactortypeDe;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.manager.FactortypeDeManager;
import com.gpcsoft.epp.common.service.FactortypeDeService;

@Service 
public class FactortypeDeServiceImpl extends BaseGenericServiceImpl<FactortypeDe> implements FactortypeDeService {

	@Autowired(required=true) @Qualifier("factortypeDeManagerImpl")
	private FactortypeDeManager factortypeDeManager;

	@Autowired(required=true)  @Qualifier("factortypeDeDaoHibernate")
	private FactortypeDeDao factortypeDeDaoHibernate;
	
	/**
	 * @Description: 根据指标分类ID,获取下级指标分类记录条数
	 * @param 
	 * @return Integer
	 * @throws Exception
	 * @Create Date 2010-8-10 下午03:44:05 By wanghz
	 */
	public Integer getsubFactortypeDes(String factortypeId)throws Exception{
		return factortypeDeDaoHibernate.getsubFactortypeDes(factortypeId);
	}
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.epp.common.service.FactortypeDeService#removeFactortypeDe(java.lang.String)
	 */
	public void removeFactortypeDe(String objId) throws Exception {
		FactortypeDe factortypeDe = factortypeDeManager.get(objId);
		if(null != factortypeDe.getParent() && !"".equals(factortypeDe.getParent().getObjId())){
			FactortypeDe parent = factortypeDe.getParent();
			Integer num = factortypeDeDaoHibernate.getsubFactortypeDes(parent.getObjId());
			if(num == 1){
				parent.setIsLeaf(true);
				factortypeDeManager.save(parent);
			}
		}
		factortypeDeManager.remove(objId, FactortypeDe.class);// 删除指标分类
	}
	
	
	/**
	 * @Description: 创建指标分类
	 * @param factortypeDe
	 * @return FactortypeDe
	 * @throws Exception
	 * @Create Date 2010-9-7 下午03:14:08 By wanghz
	 */
	public FactortypeDe saveCreatefactortypeDe(FactortypeDe factortypeDe)throws EsException{
		factortypeDe.setCreateTime(new java.util.Date());
		factortypeDe.setIsLeaf(true);
		factortypeDeManager.save(factortypeDe);
		if(null != factortypeDe.getParent() && !"".equals(factortypeDe.getParent().getObjId())){
			FactortypeDe parent = factortypeDeManager.get(factortypeDe.getParent().getObjId());
			parent.setIsLeaf(false);
			factortypeDeManager.save(parent);
		}
		return factortypeDe;
	}
	
	/**
	 * @Description: 更新指标分类
	 * @param factortypeDe
	 * @return FactortypeDe
	 * @throws Exception
	 * @Create Date 2010-9-7 下午03:14:08 By wanghz
	 */
	public FactortypeDe updateFactortypeDe(FactortypeDe factortypeDe)throws EsException{
		// 根据当前ID获取下级
		Integer num = factortypeDeDaoHibernate.getsubFactortypeDes(factortypeDe.getObjId());
		if(num >0 ){
			factortypeDe.setIsLeaf(false);
		} else {
			factortypeDe.setIsLeaf(true);
		}
		factortypeDeManager.save(factortypeDe);
		if(null != factortypeDe.getParent() && !"".equals(factortypeDe.getParent().getObjId())){
			FactortypeDe parent = factortypeDeManager.get(factortypeDe.getParent().getObjId());
			parent.setIsLeaf(false);
			factortypeDeManager.save(parent);
		}
		return factortypeDe;
	}
}
