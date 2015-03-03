package com.gpcsoft.epp.projreview.dao;

import java.util.List;
import java.util.Set;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.projreview.domain.GenviewDefine;

public interface GenviewDefineDao extends BaseGenericDao<GenviewDefine> {

	/**
	  * FuncName:getGenviewDefineListByProjectId
	  * Description :根据项目获得开标一览表指标列表  
	  * @param   projectId:项目主键
	  * @return  List<GenviewDefine>
	  * @author liuke
	  * @Create Date: 2010-10-9上午11:50:59 
	  * @Modifier   liuke
	  * @Modified Date: 2010-10-9上午11:50:59 
	  */ 
	public List<GenviewDefine> getGenviewDefineListByProjectId(String projectId);

	/**
	 * FuncName:removeGenviewDefineByCongruousFactorId
	 * Description :根据指标删除开标一览表指标  
	 * @param   factorId:指标主键
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-10-9下午01:22:46
	 * @Modifier liuke
	 * @Modified Date: 2010-10-9下午01:22:46  
	 */
	public void removeGenviewDefineByCongruousFactorId(String factorId);
	
	/**
	 * FuncName: removeGenviewDefineByProjectId
	 * Description :  根据开标一览表表头关联的项目或包组主键删除开标一览表表头
	 * @param   projectId
	 * @return  void
	 * @author zhouzhanghe
	 * @Create Date: 2011-11-30 17:44
	 */
	public void removeGenviewDefineByProjectId(String projectId);
	
	
	/**
	 * FuncName: saveGenviewDefineByPriceList
	 * Description :  根据开标一览表保存指标数据项
	 * @param 
	 * @param name
	 * @param subProject void
	 * @author: liuke
	 * @Create Date:2011-9-27  上午10:07:17
	 * @Modifier: liuke
	 * @Modified Date:2011-9-27  上午10:07:17
	 */
	public void saveGenviewDefineByPriceList(List<String> name,Project project,List<Project> subProjList);

	
	/**
	 * FuncName : 
	 * Description :  
	 * Create Date: 2011-11-10上午10:39:41 by liuke
	 * Modified Date: 2011-11-10上午10:39:41 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveGenviewDefineEx(GenviewDefine genviewDefine);
	
	/**
	 * 使用SQL方式来保存GenviewDefine
	 * @param genviewDefine
	 * @author zhouzhanghe
	 * @Created date 2011-12-01 09:33
	 */
	public void saveGenviewDefineUsingSQL(GenviewDefine genviewDefine);
	
	/**
	 * 使用SQL方式来保存genviewDefineList
	 * @param genviewDefineList
	 * @author zhouzhanghe
	 * @Created date 2011-12-01 09:33
	 */
	public void saveGenviewDefineUsingSQL(List<GenviewDefine> genviewDefineList);
}
