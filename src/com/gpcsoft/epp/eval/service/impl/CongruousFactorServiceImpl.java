package com.gpcsoft.epp.eval.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.eval.dao.CongruousFactorDao;
import com.gpcsoft.epp.eval.dao.FactypeMfactorDao;
import com.gpcsoft.epp.eval.domain.CongruousFactor;
import com.gpcsoft.epp.eval.domain.CongruousFactorType;
import com.gpcsoft.epp.eval.domain.FactypeMfactor;
import com.gpcsoft.epp.eval.manager.CongruousFactorManager;
import com.gpcsoft.epp.eval.manager.CongruousFactorTypeManager;
import com.gpcsoft.epp.eval.service.CongruousFactorService;
import com.gpcsoft.epp.project.dao.ProjectDao;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.projreview.manager.GenviewDefineManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class CongruousFactorServiceImpl extends BaseGenericServiceImpl<CongruousFactor> implements CongruousFactorService {

	@Autowired(required=true) @Qualifier("congruousFactorManagerImpl")
	private CongruousFactorManager congruousFactorManager;
	
	@Autowired(required=true)  @Qualifier("projectDaoHibernate")
	private ProjectDao projectDaoHibernate;
	@Autowired(required=true)  @Qualifier("congruousFactorDaoHibernate")
	private CongruousFactorDao congruousFactorDaoHibernate;
	
	@Autowired(required=true) @Qualifier("congruousFactorTypeManagerImpl")
	private CongruousFactorTypeManager congruousFactorTypeManager;
	
	@Autowired(required=true)  @Qualifier("factypeMfactorDaoHibernate")
	private FactypeMfactorDao factypeMfactorDaoHibernate;
	
	@Autowired(required=true) @Qualifier("genviewDefineManagerImpl")
	private GenviewDefineManager genviewDefineManager;
	
	/**
	 * @Description: 新增指标
	 * @param projId
	 * @param congruousFactor
	 * @return CongruousFactor
	 * @throws Exception
	 * @Create Date 2010-8-3 上午11:06:45 By wanghz
	 */
	public CongruousFactor saveCreateCongruousFactor(CongruousFactor congruousFactor,String projId) throws Exception {
		logger.debug("\nCongruousFactorServiceImpl||saveCreateCongruousFactor\n");
		String factorCode = UUID.randomUUID().toString();
		congruousFactor.setFactorCode(factorCode);//指标编号
		Set<FactypeMfactor> factypeMfactorSet = new HashSet<FactypeMfactor>(0);
		for(String packId:projId.split(",")){
			FactypeMfactor factypeMfactor = new FactypeMfactor();
			factypeMfactor.setProjId(packId);
			factypeMfactor.setFactor(congruousFactor);
			factypeMfactor.setFactorType(congruousFactor.getFactorType());
			factypeMfactor.setCreateTime(new java.util.Date());
			factypeMfactorSet.add(factypeMfactor);
		}
		congruousFactor.setFactypeMfactorSet(factypeMfactorSet);
		congruousFactor.setObjId(null);
		congruousFactor.setCreateTime(new java.util.Date());
		if (null == congruousFactor.getScore()) {
			congruousFactor.setScore(new Double(0));
		}
		congruousFactorManager.save(congruousFactor);
		CongruousFactorType congruousFactorType = (CongruousFactorType) congruousFactorManager.get(congruousFactor.getFactorType().getObjId(), CongruousFactorType.class);
		Project project = projectDaoHibernate.get(congruousFactorType.getProjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		User user = AuthenticationHelper.getCurrentUser();
		congruousFactor.setUser(user);
		congruousFactor.setParentBizId(parentBizId);
		return congruousFactor;
	}
	
	/**
	 * @Description: 更新指标
	 * @param projId
	 * @param congruousFactor
	 * @return CongruousFactor
	 * @throws Exception
	 * @Create Date 2010-8-3 上午11:06:45 By wanghz
	 */
	public CongruousFactor updateCongruousFactor(CongruousFactor congruousFactor,String projId)throws Exception{
		logger.debug("\nCongruousFactorServiceImpl||updateCongruousFactor\n");
		factypeMfactorDaoHibernate.removeFactypeMfactorByFactorId(congruousFactor.getObjId());
		Set<FactypeMfactor> factypeMfactorSet = new HashSet<FactypeMfactor>(0);
		for(String packId:projId.split(",")){
			FactypeMfactor factypeMfactor = new FactypeMfactor();
			factypeMfactor.setProjId(packId);
			factypeMfactor.setFactor(congruousFactor);
			factypeMfactor.setFactorType(congruousFactor.getFactorType());
			factypeMfactor.setCreateTime(new java.util.Date());
			factypeMfactorSet.add(factypeMfactor);
		}
		genviewDefineManager.removeGenviewDefineByCongruousFactorId(congruousFactor.getObjId());//删除开标一览表                                                  
		congruousFactor.setFactypeMfactorSet(factypeMfactorSet);
		congruousFactor.setChangeOrgId(AuthenticationHelper.getCurrentUser().getOrgInfo().getObjId());
		congruousFactor.setChanger(AuthenticationHelper.getCurrentUser().getObjId());
		congruousFactorManager.save(congruousFactor);
		return congruousFactor;
	}
	
	/**
	 * @Description: 保存引入指标,批量创建
	 * @param packIds
	 * @param factorTypeId
	 * @return void
	 * @throws Exception
	 * @Create Date 2010-8-11 上午10:30:23 By wanghz
	 */
	public CongruousFactor saveCreateCongruousFactor(String[] packIds,String isNeed,List<CongruousFactor> congruousFactorList){
		logger.debug("\nCongruousFactorServiceImpl||saveCreateCongruousFactor\n");
		String packNames = projectDaoHibernate.getPackNamesByPackIds(packIds);
		CongruousFactor temp = null;
		for (CongruousFactor congruousFactor:congruousFactorList) {
			congruousFactor.setCreateTime(new java.util.Date());
			congruousFactor.setProjName(packNames);
			congruousFactor.setIsNeed(isNeed);
			Set<FactypeMfactor> factypeMfactorSet = new HashSet<FactypeMfactor>(0);
			for(String packId:packIds){
				FactypeMfactor factypeMfactor = new FactypeMfactor();
				factypeMfactor.setProjId(packId);
				factypeMfactor.setFactor(congruousFactor);
				factypeMfactor.setFactorType(congruousFactor.getFactorType());
				factypeMfactor.setCreateTime(new java.util.Date());
				factypeMfactorSet.add(factypeMfactor);
			}
			congruousFactor.setFactypeMfactorSet(factypeMfactorSet);
			congruousFactorManager.save(congruousFactor);
			if(null == temp){
				temp = congruousFactor;
			}
		}
		User user = AuthenticationHelper.getCurrentUser();
		CongruousFactorType congruousFactorType =congruousFactorTypeManager.get(temp.getFactorType().getObjId());
		Project project = projectDaoHibernate.get(congruousFactorType.getProjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		temp.setUser(user);
		temp.setParentBizId(parentBizId);
		return temp;
	}
	
	/**
	 * @Description: 删除指标
	 * @param objId
	 * @return void
	 * @throws Exception
	 * @Create Date 2010-9-8 上午12:00:16 By wanghz
	 */
	public void removeCongruousFactor(String objId){
		logger.debug("\nCongruousFactorServiceImpl||removeCongruousFactor\n");
		genviewDefineManager.removeGenviewDefineByCongruousFactorId(objId);//删除开标一览表                                       
		factypeMfactorDaoHibernate.removeFactypeMfactorByFactorId(objId);
		congruousFactorManager.remove(objId, CongruousFactor.class);
	}
	
	/**
	 * @Description: 获取指标分值总和
	 * @param factorTypeId 分类ID
	 * @return Double
	 * @throws Exception
	 * @Create Date 2010-9-19 下午02:31:55 By wanghz
	 */
	public Double getScoreSum(String factorTypeId){
		logger.debug("\nCongruousFactorServiceImpl||getScoreSum\n");
		return congruousFactorDaoHibernate.getScoreSum(factorTypeId);
	}

	public boolean saveBatchUpdateCongruousFactor(
			List<CongruousFactor> congruousFactorList) {
		save(congruousFactorList);
		return false;
	}

	/**
	 * FuncName: saveCongruousFactor
	 * Description :  保存指标数据
	 * @param 
	 * @return List<CongruousFactor>
	 * @author: zhouzhanghe
	 * @Create Date:2011-7-6 17:27
	 */
	public void saveCongruousFactor(List<Map> congruousFactorList) {
		congruousFactorDaoHibernate.saveCongruousFactor(congruousFactorList);
	}
	
	
	/**
	 * FuncName: getCongruousFactorList
	 * Description :  获取所有指标
	 * @param 
	 * @return List<CongruousFactor>
	 * @author: yangyc
	 * @Create Date:2011-11-29  下午05:35:07
	 * @Modifier: yangyc
	 * @Modified Date:2011-11-29  下午05:35:07
	 */
	public List<CongruousFactor> getAllFactorByProjectId(String projectId) {
		return congruousFactorDaoHibernate.getAllFactorByProjectId(projectId);
	}
}

