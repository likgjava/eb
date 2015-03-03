package com.gpcsoft.epp.projreview.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.projreview.domain.GenviewDefine;

public interface GenviewDefineService extends BaseGenericService<GenviewDefine> {

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
	public Project saveGenviewDefine(String message,String projectId);
	
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
	public List<GenviewDefine> getGenviewDefineListByProjectId(String projectId);
		
	
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
    public void savePriceInfo(List<String>name ,Project project);
    
    
    /**
     * FuncName : 
     * Description :  
     * Create Date: 2011-10-31下午05:40:26 by liuke
     * Modified Date: 2011-10-31下午05:40:26 by liuke
     * @param   
     * @return  
     * @Exception
     */
    public GenviewDefine saveGenviewDefineInfo(GenviewDefine genviewDefine);
    
    
    
    
    /**
     * FuncName : deleteGenviewDefineAndItemByObjId
     * Description :  根据Id删除开标一览表数据
     * Create Date: 2011-11-1上午10:32:12 by liuke
     * Modified Date: 2011-11-1上午10:32:12 by liuke
     * @param   
     * @return  
     * @Exception
     */
    public void deleteGenviewDefineAndItemByObjId(String objId);
    
    
    
    /**
     * FuncName : saveGenviewDefineEx
     * Description : 保存额外的开标一览表表头
     * Create Date: 2011-11-10上午10:37:33 by liuke
     * Modified Date: 2011-11-10上午10:37:33 by liuke
     * @param   
     * @return  
     * @Exception
     */
    public void saveGenviewDefineEx(GenviewDefine genviewDefine);
    
    
    /**
     * FuncName : saveFinishGenviewDefine
     * Description :  保存完成开标一览表头设置
     * Create Date: 2011-11-15下午03:08:03 by liuke
     * Modified Date: 2011-11-15下午03:08:03 by liuke
     * @param   
     * @return  
     * @Exception
     */
    public Project saveFinishGenviewDefine(String projectId);
    
	/**
	 * 根据factorName从genviewDefineList查找对象
	 * @author zhouzhanghe
	 * @created at 2011-11-19 12:02
	 */
	public GenviewDefine getGenviewDefine(List<GenviewDefine> genviewDefineList, String factorName);
}
