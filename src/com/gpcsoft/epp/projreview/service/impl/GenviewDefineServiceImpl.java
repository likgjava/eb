package com.gpcsoft.epp.projreview.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.eval.domain.CongruousFactor;
import com.gpcsoft.epp.eval.manager.CongruousFactorManager;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.projreview.dao.GenviewDefineDao;
import com.gpcsoft.epp.projreview.dao.OpenBidGeneralVitemDao;
import com.gpcsoft.epp.projreview.domain.GenviewDefine;
import com.gpcsoft.epp.projreview.manager.GenviewDefineManager;
import com.gpcsoft.epp.projreview.service.GenviewDefineService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class GenviewDefineServiceImpl extends BaseGenericServiceImpl<GenviewDefine> implements GenviewDefineService {

	@Autowired(required=true) @Qualifier("genviewDefineManagerImpl")
	private GenviewDefineManager genviewDefineManager;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true) @Qualifier("congruousFactorManagerImpl")
	private CongruousFactorManager congruousFactorManager;

	
	@Autowired(required=true)  @Qualifier("genviewDefineDaoHibernate")
	private GenviewDefineDao genviewDefineDaoHibernate;
	
	
	@Autowired(required=true)  @Qualifier("openBidGeneralVitemDaoHibernate")
	private OpenBidGeneralVitemDao openBidGeneralVitemDaoHibernate;
	/**
	 * FuncName: saveGenviewDefine
	 * Description :保存开标一览表指标  
	 * @param   message:获取的开标一览表规则信息,projectId:项目主键
	 * @return  Project
	 * @author liuke
	 * @Create Date: 2010-10-9上午10:37:43 
	 * @Modifier liuke
	 * @Modified Date: 2010-10-9上午10:37:43 
	 */
	public Project saveGenviewDefine(String message,String projectId) {
		logger.debug("\nes GenviewDefineServiceImpl||saveGenviewDefine\n");
		genviewDefineManager.reomveGenviewDefine(projectId);//根据项目ID删除开标一览表指标  
		String[] messageArray = message.split(",");
		for(String messages:messageArray){
			String newMessages =   messages.replace("|", ",");
			String[] genviewDefineMessages = newMessages.split(",");
			Project subProject = projectManager.get(genviewDefineMessages[1]);
			CongruousFactor congruousFactor = congruousFactorManager.get(genviewDefineMessages[0]);
			GenviewDefine genviewDefine = new GenviewDefine();
			genviewDefine.setProject(subProject);
			genviewDefine.setFactorId(congruousFactor.getObjId());
			genviewDefine.setFactorName(genviewDefineMessages[2]);
			String showNo = genviewDefineMessages[3];
			genviewDefine.setShowNo(new BigDecimal(showNo));
			genviewDefineManager.save(genviewDefine);
		}
		Project project = projectManager.get(projectId);
		User user=AuthenticationHelper.getCurrentUser();
		project.setUser(user);
		project.setParentBizId(projectId);
		return project;
	}

	/**
	 * FuncName:getGenviewDefineListByProjectId
	 * Description :根据项目得到开标一览表指标列表  
	 * @param   projectId:项目主键
	 * @return  List<GenviewDefine>
	 * @author     liuke
	 * @Create Date: 2010-10-9上午11:48:51
	 * @Modifier    liuke
	 * @Modified Date: 2010-10-9上午11:48:51  
	 */
	public List<GenviewDefine> getGenviewDefineListByProjectId(String projectId) {
		logger.debug("\n GenviewDefineServiceImpl||getGenviewDefineListByProjectId\n");
		return genviewDefineDaoHibernate.getGenviewDefineListByProjectId(projectId);
	}

	
	/**
	 * FuncName: savePriceInfo
	 * Description :  保存开标一览表数据项
	 * @param 
	 * @param name
	 * @param project void
	 * @author: liuke
	 * @Create Date:2011-9-27  上午09:59:52
	 * @Modifier: liuke
	 * @Modified Date:2011-9-27  上午09:59:52
	 */
	public void savePriceInfo(List<String> name, Project project) {
		List<Project> subProjectList = projectManager.getSubProjectByQueryObject(project.getObjId());
		if(subProjectList.isEmpty()){ //如果没分包
			subProjectList.add(project);
		}
		genviewDefineDaoHibernate.saveGenviewDefineByPriceList(name,project,subProjectList);
	}

	
	 /**
     * FuncName : saveGenviewDefineInfo
     * Description :  保存开标一览表表头
     * Create Date: 2011-10-31下午05:40:26 by liuke
     * Modified Date: 2011-10-31下午05:40:26 by liuke
     * @param   
     * @return  
     * @Exception
     */
	public GenviewDefine saveGenviewDefineInfo(GenviewDefine genviewDefine) {
		User user = AuthenticationHelper.getCurrentUser();
		String projectId="";
		genviewDefineDaoHibernate.save(genviewDefine);
		Project subProj = projectManager.get(genviewDefine.getProject().getObjId());
		if(subProj.getParentId()==null||"".equals(subProj.getParentId())){
			projectId = subProj.getObjId();
		}else{
			projectId = subProj.getParentId();
		}
		genviewDefine.setParentBizId(projectId);
		genviewDefine.setUser(user);
		return genviewDefine;
	}
	
	
	/**
     * FuncName : deleteGenviewDefineAndItemByObjId
     * Description :  根据Id删除开标一览表数据
     * Create Date: 2011-11-1上午10:32:12 by liuke
     * Modified Date: 2011-11-1上午10:32:12 by liuke
     * @param   
     * @return  
     * @Exception
     */
	public void deleteGenviewDefineAndItemByObjId(String objId) {
		openBidGeneralVitemDaoHibernate.removeOpenBidGeneralVitemByOpenBidGeneralId(objId); //删除开标一览表信息
		genviewDefineDaoHibernate.remove(objId, GenviewDefine.class);//删除开标一览表表头
		
	}

	
	/**
     * FuncName : saveGenviewDefineEx
     * Description : 保存额外的开标一览表表头
     * Create Date: 2011-11-10上午10:37:33 by liuke
     * Modified Date: 2011-11-10上午10:37:33 by liuke
     * @param   
     * @return  
     * @Exception
     */
	public void saveGenviewDefineEx(GenviewDefine genviewDefine) {
		genviewDefineDaoHibernate.saveGenviewDefineEx(genviewDefine);
	}

	
	/**
     * FuncName : saveFinishGenviewDefine
     * Description :  保存完成开标一览表头设置
     * Create Date: 2011-11-15下午03:08:03 by liuke
     * Modified Date: 2011-11-15下午03:08:03 by liuke
     * @param   
     * @return  
     * @Exception
     */
	public Project saveFinishGenviewDefine(String projectId) {
		User user = AuthenticationHelper.getCurrentUser();
		Project subProj = projectManager.get(projectId);
		if(subProj.getParentId()==null||"".equals(subProj.getParentId())){
			projectId = subProj.getObjId();
		}else{
			projectId = subProj.getParentId();
		}
		subProj.setParentBizId(projectId);
		subProj.setUser(user);
		return subProj;
	}

	/**
	 * 根据factorName从genviewDefineList查找对象
	 * @author zhouzhanghe
	 * @created at 2011-11-19 12:02
	 */
	public GenviewDefine getGenviewDefine(List<GenviewDefine> genviewDefineList, String factorName) {
		for(GenviewDefine genviewDefine : genviewDefineList)
			if(factorName.equals(genviewDefine.getFactorName()))
				return genviewDefine;
		return null;
	}
}
