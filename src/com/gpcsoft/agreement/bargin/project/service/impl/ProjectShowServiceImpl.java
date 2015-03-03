package com.gpcsoft.agreement.bargin.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.project.dao.ProjectShowDao;
import com.gpcsoft.agreement.bargin.project.service.ProjectShowService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.project.domain.Project;

@Service 
public class ProjectShowServiceImpl extends BaseGenericServiceImpl<Project> implements ProjectShowService {
	
	@Autowired(required=true) @Qualifier("projectShowDaoHibernate")
	private ProjectShowDao projectShowDaoHibernate;
	
	/** 
	 * Description :  获取采购金额范围列表（供列表展示使用）
	 * Create Date: 2011-8-16上午08:51:14 by likg  Modified Date: 2011-8-16上午08:51:14 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getAmountRangeListForShow(Map<String, Object> paramsMap) throws Exception {
		return projectShowDaoHibernate.getAmountRangeListForShow(paramsMap);
	}

	/** 
	 * Description :  获取采购区域列表（供列表展示使用）
	 * Create Date: 2011-8-15上午10:50:29 by likg  Modified Date: 2011-8-15上午10:50:29 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getDistrictListForShow(Map<String, Object> param) throws Exception {
		return projectShowDaoHibernate.getDistrictListForShow(param);
	}

	/** 
	 * Description :  获取采购品目列表（供列表展示使用）
	 * Create Date: 2011-8-15上午10:50:29 by likg  Modified Date: 2011-8-15上午10:50:29 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getPurCategoryListForShow(Map<String, Object> param) throws Exception {
		List<Object[]> objsList = new ArrayList<Object[]>();
		String purCategoryId = (String) param.get("purCategoryId"); //品目Id
		
		//获取项目的品目信息
		List<Object[]> idNameList = projectShowDaoHibernate.getPurCategoryListForShow(param);
		
		//循环遍历每一个项目对象中purCategoryIds字段，计算品目数量
		List<String> idList = new ArrayList<String>();
		List<String> nameList = new ArrayList<String>();
		List<Long> numList = new ArrayList<Long>();
		for(Object[] strs : idNameList) {
			if(strs[0]==null || strs[1]==null) continue;
			String[] ids = strs[0].toString().split(",");
			String[] names = strs[1].toString().split(",");
			for(int i=0; i<ids.length; i++) {
				//按品目Id过滤
				if(StringUtils.hasLength(purCategoryId) && ids[i].indexOf(purCategoryId)<0) {
					continue ;
				}
				
				if(idList.contains(ids[i])) {
					numList.set(idList.indexOf(ids[i]), numList.get(idList.indexOf(ids[i]))+1);
				}else{
					idList.add(ids[i]);
					nameList.add(names[i]);
					numList.add(1L);
				}
			}
		}
		
		//把List转化为Array
		objsList.add(idList.toArray());
		objsList.add(nameList.toArray());
		objsList.add(numList.toArray());
		return objsList;
	}

	/** 
	 * Description :  获取项目列表（供列表展示使用）
	 * Create Date: 2011-8-15下午01:16:20 by likg  Modified Date: 2011-8-15下午01:16:20 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Project> getProjectListForShow(Page<Project> page, Map<String, Object> paramsMap) throws Exception {
		return projectShowDaoHibernate.getProjectListForShow(page, paramsMap);
	}

	/** 
	 * Description :  获取电子招标项目列表数据
	 * Create Date: 2011-8-16下午07:56:23 by likg  Modified Date: 2011-8-16下午07:56:23 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Project> getBiddingProjectList(Page<Project> page, Map<String, Object> paramsMap) throws Exception {
		return projectShowDaoHibernate.getBiddingProjectList(page, paramsMap);
	}
	
	/** 
	 * Description :  获取电子采购项目列表数据
	 * Create Date: 2011-8-16下午07:56:23 by likg  Modified Date: 2011-8-16下午07:56:23 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Project> getBargainProjectList(Page<Project> page, Map<String, Object> paramsMap) throws Exception {
		return projectShowDaoHibernate.getBargainProjectList(page, paramsMap);
	}
	
}
