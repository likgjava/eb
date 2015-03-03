package com.gpcsoft.epp.workgroup.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.workgroup.domain.WorkGroup;

public interface WorkGroupService extends BaseGenericService<WorkGroup> {


	/** 
	 * Description :  通过项目主键和小组类型得到工作小组对象
	 * Create Date: 2010-11-2下午02:20:38 by yangx  Modified Date: 2010-11-2下午02:20:38 by yangx
	 * @param   projectId：项目Id,workType：小组类型
	 * @return  WorkGroup：工作小组
	 * @Exception   
	 */
	public WorkGroup getWorkGroupByProjectIdAndType(String projectId,String workType);

	/** 
	 * Description :  根据项目Id、小组类型保存工作小组对象
	 * Create Date: 2010-11-2下午02:07:09 by yangx  Modified Date: 2010-11-2下午02:07:09 by yangx
	 * @param   projectId：项目Id,workType：小组类型
	 * @return  WorkGroup：工作小组
	 * @Exception   
	 */
	public WorkGroup saveWorkGroupByProjectIdAndType(String projectId,String workType);

	/** 
	 * Description :  按指定组别获取项目下所有标段的某组成员
	 * Create Date: 2010-6-25下午01:39:48 by wangcl  Modified Date: 2010-6-25下午01:39:48 by wangcl
	 * @param   projectId：项目Id,groupType：工作组类型
	 * @return  List<WorkGroup>
	 * @Exception   
	 */
	public List<WorkGroup> saveOrgetAllGroupMenber(String projectId,String groupType);
	
	/** 
	 * Description :  获取项目下所有标段的论证组成员
	 * Create Date: 2010-6-25下午01:39:48 by wangcl  Modified Date: 2010-6-25下午01:39:48 by wangcl
	 * @param   projectId：项目Id
	 * @return  List<WorkGroup>
	 * @Exception   
	 */
	public List<WorkGroup> saveOrGetProofGroupMenber(String projectId);
	
	public void saveOrUpdateWorkGroup(WorkGroup workGroup);	
	
	
	
	/**
	 * FuncName: saveOrGetProofGroupMenber
	 * Description : 获取项目下所有标段的评审小组成员
	 * @param 
	 * @param projectId
	 * @return List<WorkGroup>
	 * @author: liuke
	 * @Create Date:2011-3-3  下午06:57:02
	 * @Modifier: liuke
	 * @Modified Date:2011-3-3  下午06:57:02
	 */
	public List<WorkGroup> saveOrGetJudgeGroupMenber(String projectId);
	
	
}
