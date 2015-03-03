package com.gpcsoft.epp.webService.webService.evaluate.soap;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.service.ElectronicReviewService;

public class FactorSoapService  {

	private ElectronicReviewService electronicReviewService;
	public FactorSoapService(){
		electronicReviewService = (ElectronicReviewService) FrameBeanFactory.getBean("electronicReviewServiceImpl");
	}
	/**
	 * FuncName: getCongruousFactorList
	 * Description :  根据项目编号和包组编号获取到指标集合
	 * @param projectCode:项目编号
	 * @param packCode:包组编号
	 * @return String
	 * @throws Exception String
	 * @author: shenjz
	 * @Create Date:2011-9-29  上午10:35:44
	 * @Modifier: shenjz
	 * @Modified Date:2011-9-29  上午10:35:44
	 */
	public String  getCongruousFactorList(String projectCode,String packCode)throws Exception {
		if(projectCode==null){
			return null;
		}
		return electronicReviewService.getCongruousFactor(projectCode,packCode);
	}
	
	
}
