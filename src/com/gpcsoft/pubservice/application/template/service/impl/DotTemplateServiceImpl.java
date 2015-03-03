package com.gpcsoft.pubservice.application.template.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.pubservice.application.template.domain.DotTemplate;
import com.gpcsoft.pubservice.application.template.manager.DotTemplateManager;
import com.gpcsoft.pubservice.application.template.service.DotTemplateService;
import com.gpcsoft.pubservice.common.enumeration.CommonEnum;
import com.gpcsoft.srplatform.baseData.domain.District;
import com.gpcsoft.srplatform.baseData.manager.DistrictManager;

@Service 
public class DotTemplateServiceImpl extends BaseGenericServiceImpl<DotTemplate> implements DotTemplateService {

	@Autowired(required=true) @Qualifier("dotTemplateManagerImpl")
	private DotTemplateManager dotTemplateManager;
	
	@Autowired(required=true) @Qualifier("districtManagerImpl")
	private DistrictManager districtManager;

	/** 
	 * Description :  保存范本信息
	 * Create Date: 2011-8-1上午11:44:37 by likg  Modified Date: 2011-8-1上午11:44:37 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveDotTemplate(DotTemplate dotTemplate) throws Exception {
		if(!StringUtils.hasLength(dotTemplate.getObjId())) {
			dotTemplate.setUseStatus(CommonEnum.USER_STATUS_CONFIRM);
		}
		dotTemplateManager.save(dotTemplate);
	}

	/** 
	 * Description :  根据参数获取范本列表集合
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   categoryCode:品目编号  districtCode:区域Code  templateType:范本类型  orgInfoId:机构Id
	 * @return  
	 * @Exception   
	 */
	public List<DotTemplate> getTemplateListByParam(Map<String, Object> param) throws Exception {
		return dotTemplateManager.getTemplateListByParam(param);
	}

	/** 
	 * Description :  获取各品目的范本数
	 * Create Date: 2011-8-3上午10:48:21 by likg  Modified Date: 2011-8-3上午10:48:21 by likg
	 * @param   param(categoryCode:品目编号  districtCode:区域Code  templateType:范本类型)
	 * @return  List([品目编号，品目名称，范本数量])
	 * @Exception   
	 */
	public List<Object[]> getTemplateNumByCategory(Map<String, Object> param) throws Exception {
		List<Object[]> objsList = new ArrayList<Object[]>();
		
		//获取范本记录
		List<DotTemplate> templateList = dotTemplateManager.getTemplateListByParam(param);
		
		//循环遍历每一个范本对象中categoryCode字段，计算品目数量
		List<String> codeList = new ArrayList<String>();
		List<String> nameList = new ArrayList<String>();
		List<Long> numList = new ArrayList<Long>();
		for(DotTemplate template : templateList) {
			String[] codes = template.getCategoryCode().split(",");
			String[] names = template.getCategoryName().split(",");
			for(int i=0; i<codes.length; i++) {
				if(codeList.contains(codes[i])) {
					numList.set(codeList.indexOf(codes[i]), numList.get(codeList.indexOf(codes[i]))+1);
				}else{
					codeList.add(codes[i]);
					nameList.add(names[i]);
					numList.add(1L);
				}
			}
		}
		
		//把List转化为Array
		for(int i=0; i<codeList.size(); i++) {
			objsList.add(new Object[]{codeList.get(i), nameList.get(i), numList.get(i)});
		}
		return objsList;
	}
	
	/** 
	 * Description :  获取各区域的范本数（省级区域）
	 * Create Date: 2011-8-3上午10:48:21 by likg  Modified Date: 2011-8-3上午10:48:21 by likg
	 * @param   param(categoryCode:品目编号  districtCode:区域Code  templateType:范本类型)
	 * @return  List([区域编号，区域名称，范本数量])
	 * @Exception   
	 */
	public List<Object[]> getTemplateNumByDistrict(Map<String, Object> param) throws Exception {
		List<Object[]> objsList = new ArrayList<Object[]>();
		
		//获取范本记录
		List<DotTemplate> templateList = dotTemplateManager.getTemplateListByParam(param);
		
		//循环遍历每一个范本对象中districtCode字段，计算省级区域数量
		List<String> codeList = new ArrayList<String>();
		List<String> nameList = new ArrayList<String>();
		List<Long> numList = new ArrayList<Long>();
		for(DotTemplate template : templateList) {
			String[] codes = template.getDistrictCode().split(",");
			//合并同一省级区域的Code
			Set<String> pcodeSet = new HashSet<String>();
			for(String code : codes) {
				pcodeSet.add(code.substring(0, 2));
			}
			for(String pcode : pcodeSet) {
				pcode = pcode+"0000";
				if(codeList.contains(pcode)) {
					numList.set(codeList.indexOf(pcode), numList.get(codeList.indexOf(pcode))+1);
				}else{
					codeList.add(pcode);
					numList.add(1L);
				}
			}
		}
		
		//获取省级区域的名称
		List<District> topDistricts = districtManager.findTopDistricts();
		for(String dis : codeList) {
			for(District district : topDistricts) {
				if(district.getObjId().equals(dis)) {
					nameList.add(district.getName());
					break ;
				}
			}
		}
		
		//把List转化为Array
		for(int i=0; i<codeList.size(); i++) {
			objsList.add(new Object[]{codeList.get(i), nameList.get(i), numList.get(i)});
		}
		return objsList;
	}
	
	/** 
	 * Description :  获取范本列表数据为前台展示使用
	 * Create Date: 2011-8-3下午01:04:52 by likg  Modified Date: 2011-8-3下午01:04:52 by likg
	 * @param   page:分页数据  param(categoryCode:品目编号  districtCode:区域Code  templateType:范本类型)
	 * @return  
	 * @Exception   
	 */
	public Page<DotTemplate> getTemplateListForShow(Page<DotTemplate> page, Map<String, Object> param) throws Exception {
		return dotTemplateManager.getTemplateListForShow(page, param);
	}

}
