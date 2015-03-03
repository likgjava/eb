package com.gpcsoft.epp.expertrule.manager.impl;

import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.utils.FileUtils;
import com.gpcsoft.epp.expertrule.dao.ExpertRuleDao;
import com.gpcsoft.epp.expertrule.dao.ExpertRulePurchaseCategoryDao;
import com.gpcsoft.epp.expertrule.domain.ExpertRulePurchaseCategory;
import com.gpcsoft.epp.expertrule.domain.TakeExpertRule;
import com.gpcsoft.epp.expertrule.manager.ExpertRuleManager;
import com.gpcsoft.epp.expertrule.manager.TakeManager;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.workgroup.domain.WorkGroup;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;
import com.gpcsoft.eprocurement.taker.provider.CodePO;
import com.gpcsoft.eprocurement.taker.provider.TakerExpert;
import com.gpcsoft.eprocurement.taker.provider.TakerExpertServiceLocator;

@Repository
public class ExpertRuleManagerImpl extends BaseManagerImpl<TakeExpertRule> implements ExpertRuleManager,TakeManager{

	@Autowired(required=true)  @Qualifier("expertRuleDaoHibernate")
	private ExpertRuleDao expertRuleDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("expertRulePurchaseCategoryDaoHibernate")
	private ExpertRulePurchaseCategoryDao expertRulePurchaseCategoryDaoHibernate;
	
	/** 
	 * Description :  通过WebService读取信息写到指定位置[来源:外库]
	 * Create Date: 2010-8-30下午03:45:16 by yangx  Modified Date: 2010-8-30下午03:45:16 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveInfoForWebService(String infoType) {
		String takeExpertURL = messageSource.getMessage("takeExpertURL");//webService地址
		try {
			URL url = new URL(takeExpertURL);
			TakerExpertServiceLocator locator = new TakerExpertServiceLocator();
			TakerExpert service = locator.getTakerExpert(url);
			CodePO[] codes = null;
			String fileName = SeparationEnum.CATEGORY;
			if (infoType!=null&&SeparationEnum.UNIT.equals(infoType)) {//判断获取的是否为单位
				codes = service.getArrayBuyer();//单位
				fileName = SeparationEnum.UNIT;
			}else if (infoType!=null&&SeparationEnum.CITYCODE.equals(infoType)) {//判断获取的是否为评审地域
				codes = service.getArrayCityCode();//评审地域
				fileName = SeparationEnum.CITYCODE;
			}else if (infoType!=null&&SeparationEnum.EXPERTGROUP.equals(infoType)) {//判断获取的是否为专家类型
				codes = service.getArrayExpertGroup();//专家类型
				fileName = SeparationEnum.EXPERTGROUP;
			}else if (infoType!=null&&SeparationEnum.EDU.equals(infoType)) {//判断获取的是否为专家学历
				codes = service.getArrayEdu();//专家学历
				fileName = SeparationEnum.EDU;
			}else if (infoType!=null&&SeparationEnum.ROOM.equals(infoType)) {//判断获取的是否为评审室
				codes = service.getArrayRoomByStaffId(messageSource.getMessage("staff_id"));//评审室
				fileName = SeparationEnum.ROOM;
			}else {//判断获取的是否为品目
				codes = service.getArrayCategory();//品目
				fileName = SeparationEnum.CATEGORY;
				this.saveCategoryInfo(codes);
			}
			String content = "";
			if (codes!=null&&codes.length>0) {
				for(int i=0;i<codes.length;i++){
					CodePO codePO = codes[i];
					content += codePO.getColumn_value()+SeparationEnum.COMMA+codePO.getColumn_name()+SeparationEnum.AT;
				}
			}
			String filePath = messageSource.getMessage("takeUrl");
			String path=filePath.replaceAll("\\$\\{rootPath}", Constants.ROOTPATH);
			FileUtil.mkdirs(path);
			FileUtils.writerFile(path+fileName,content);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TRANS_EXPERTRULE_FAIL));
		}
	}
	
	
	/**
	 * 
	 * Description : 将专家品目信息插入到数据库中 
	 * Create Date: 2010-11-17下午04:53:05 by liuke  Modified Date: 2010-11-17下午04:53:05 by liuke
	 * @param   CodePO[] codes 从webservice中获得的数据数组
	 * @return  
	 * @Exception
	 */
	public void saveCategoryInfo(CodePO[] codes){
		if (codes!=null&&codes.length>0){
			expertRulePurchaseCategoryDaoHibernate.removeAllExpertRulePurchaseCategory();//删除专家库品目
			for(int i=0;i<codes.length;i++){
				CodePO codePO = codes[i];
				ExpertRulePurchaseCategory  category = new ExpertRulePurchaseCategory();
				category.setObjId(codePO.getColumn_value());
				category.setCategoryCode(codePO.getColumn_value());
				category.setCategoryName(codePO.getColumn_name());
				ExpertRulePurchaseCategory  ParentCategory = new ExpertRulePurchaseCategory();
				String parentId="";
				String objId = codePO.getColumn_value();
				if(objId.length()>1){
					parentId = objId.substring(0, objId.length()-2);
				}
				ParentCategory.setObjId(parentId);
				category.setParent(ParentCategory);
				category.setIsLeaf(false);
				category.setReleaseStatus("1");
				expertRulePurchaseCategoryDaoHibernate.saveExpertRulePurchaseCategory(category);//添加专家库品目
			}
		}
		
	}
	
	
	/** 
	 * Description :  根据单位名称进行模糊查询单位信息
	 * Create Date: 2010-8-23下午03:38:47 by yangx  Modified Date: 2010-8-23下午03:38:47 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<CodePO> getUnitByName(String unitName){
		return null;
	}
	public List<CodePO> getInfoForFile(String infoType) {
		return null;
	}
	public List<PurCategory> getPurCategory(){
		return null;
	}
	public TakeExpertRule getByProjectId(String projectId) {
		return expertRuleDaoHibernate.getByProjectId(projectId);
	}


	/** 
	 * Description :  通过包组Id和小组类型得到工作小组对象
	 * Create Date: 2010-8-5上午10:48:06 by yangx  Modified Date: 2010-8-5上午10:48:06 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public WorkGroup getWorkGroupByProjectIdAndType(String subProjectId,String workType){
		 List list = expertRuleDaoHibernate.getWorkGroupByProjectIdAndType(subProjectId,workType);
	     if(list!=null&&list.size()>0){
	    	 return (WorkGroup) list.get(0);
	     }else{
	    	 Project p = new Project();
	    	 p.setObjId(subProjectId);
	    	 WorkGroup g = new WorkGroup();
	    	 g.setProject(p);
	    	 g.setWorkgType(workType);
	    	 expertRuleDaoHibernate.save(g,WorkGroup.class);
	    	 return g; 
	     }	   
	}


	/** 
	 * Description :  根据工作组类别和包组获得成员信息  
	 * Create Date: 2010-8-5上午11:35:42 by yangx  Modified Date: 2010-8-5上午11:35:42 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<WorkgMember> getWorkMemberListByUserIdAndProjectId(
			String workgType, String subProjectId) {
		return expertRuleDaoHibernate.getWorkMemberListByUserIdAndProjectId(workgType, subProjectId);
	}
	
}
