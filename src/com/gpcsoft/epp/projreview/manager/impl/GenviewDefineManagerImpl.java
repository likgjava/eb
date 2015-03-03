package com.gpcsoft.epp.projreview.manager.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.projreview.dao.GenviewDefineDao;
import com.gpcsoft.epp.projreview.dao.OpenBidGeneralVitemDao;
import com.gpcsoft.epp.projreview.domain.GenviewDefine;
import com.gpcsoft.epp.projreview.domain.OpenBidGeneralVitem;
import com.gpcsoft.epp.projreview.manager.GenviewDefineManager;

@Repository
public class GenviewDefineManagerImpl extends BaseManagerImpl<GenviewDefine> implements GenviewDefineManager {

	@Autowired(required=true)  @Qualifier("genviewDefineDaoHibernate")
	private GenviewDefineDao genviewDefineDaoHibernate;

	@Autowired(required=true)  @Qualifier("openBidGeneralVitemDaoHibernate")
	private OpenBidGeneralVitemDao openBidGeneralVitemDaoHibernate;
	
	/**
	 * FuncName:reomveGenviewDefine
	 * Description :根据项目删除开标一览表指标  
	 * @param   projectId:项目主键
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-10-9下午01:22:46 
	 * @Modifier liuke 
	 * @Modified Date: 2010-10-9下午01:22:46  
	 */
	public void reomveGenviewDefine(String projectId) {
	   List<OpenBidGeneralVitem> openBidGeneralVitemList = openBidGeneralVitemDaoHibernate.getAllOpenBidGeneralVitem(projectId);
	   for(OpenBidGeneralVitem openBidGeneralVitem:openBidGeneralVitemList){
		   openBidGeneralVitemDaoHibernate.remove(openBidGeneralVitem); 
	   }
	   List<GenviewDefine> list = genviewDefineDaoHibernate.getGenviewDefineListByProjectId(projectId);
	   for(Iterator<GenviewDefine>iterator =list.iterator(); iterator.hasNext(); ){
			genviewDefineDaoHibernate.remove(iterator.next());
		}
	}
	
	/**
	 * FuncName:removeGenviewDefineByCongruousFactorId
	 * Description :根据指标删除开标一览表指标  
	 * @param   factorId:指标主键
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-10-9下午01:22:46
	 * @Modifier   liuke
	 * @Modified Date: 2010-10-9下午01:22:46 
	 */
	public void removeGenviewDefineByCongruousFactorId(String factorId) {
		genviewDefineDaoHibernate.removeGenviewDefineByCongruousFactorId(factorId);
	}

}
