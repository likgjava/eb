package com.gpcsoft.epp.project.service;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectExceptionApply;

public interface ProjectExceptionApplyService extends BaseGenericService<ProjectExceptionApply> {
	
	/** 
	 * Description :  保存异常申请
	 * Create Date: 2010-10-11下午03:57:54 by yangx  Modified Date: 2010-10-11下午03:57:54 by yangx
	 * @param   projectExceptionApply：异常对象
	 * @return  ProjectExceptionApply
	 * @Exception   
	 */
	public ProjectExceptionApply saveExceptionApply(ProjectExceptionApply projectExceptionApply);
	
	/** 
	 * Description :  保存修改异常申请
	 * Create Date: 2010-10-11下午03:57:54 by yangx  Modified Date: 2010-10-11下午03:57:54 by yangx
	 * @param   projectExceptionApply：异常对象
	 * @return  ProjectExceptionApply
	 * @Exception   
	 */
	public ProjectExceptionApply saveUpdateExceptionApply(ProjectExceptionApply projectExceptionApply);

	/** 
	 * Description :  提交异常申请
	 * Create Date: 2010-10-12下午01:09:47 by yangx  Modified Date: 2010-10-12下午01:09:47 by yangx
	 * @param   projectExceptionApply：异常对象
	 * @return  ProjectExceptionApply
	 * @Exception   
	 */
	public ProjectExceptionApply saveSubmitExceptionApply(ProjectExceptionApply projectExceptionApply);
	
	/** 
	 * Description :  提交修改异常申请
	 * Create Date: 2010-10-12下午01:09:47 by yangx  Modified Date: 2010-10-12下午01:09:47 by yangx
	 * @param   PrprojectExceptionApply：异常对象
	 * @return  ProjectExceptionApply
	 * @Exception   
	 */
	public ProjectExceptionApply saveSubmitUpdateExceptionApply(ProjectExceptionApply projectExceptionApply);
	
	/** 
	 * Description : 根据项目Id获取待审核的暂停项目 
	 * Create Date: 2010-10-11下午07:15:27 by yangx  Modified Date: 2010-10-11下午07:15:27 by yangx
	 * @param   projectId：项目Id
	 * @return  ProjectExceptionApply
	 * @Exception   
	 */
	public ProjectExceptionApply getProjectExceptionApplyByProjectId(String projectId);
	
	/** 
	 * Description :  审核异常申请
	 * Create Date: 2010-10-11下午08:19:35 by yangx  Modified Date: 2010-10-11下午08:19:35 by yangx
	 * @param   projectExceptionApplyId：项目异常Id,auditStatus:审核状态
	 * @return  ProjectExceptionApply
	 * @Exception   
	 */
	public Project saveAuditStatusExceptionApply(String projectExceptionApplyId,String auditStatus,String opinion) throws Exception;
	
	/** 
	 * Description :  获取异常申请列表
	 * Create Date: 2010-10-12上午10:55:07 by yangx  Modified Date: 2010-10-12上午10:55:07 by yangx
	 * @param   queryObject：封装条件,page：分页对象
	 * @return  Page<ProjectExceptionApply>
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<ProjectExceptionApply> getProjectExceptionList(Page page,QueryObject queryObject);
	
	/** 
	 * Description :  根据主键获取异常申请信息
	 * Create Date: 2010-10-12下午02:57:19 by yangx  Modified Date: 2010-10-12下午02:57:19 by yangx
	 * @param   projectExceptionApplyId：项目异常Id
	 * @return  ProjectExceptionApply
	 * @Exception   
	 */
	public ProjectExceptionApply getProjectExceptionApplyByObjId(String projectExceptionApplyId);

}
