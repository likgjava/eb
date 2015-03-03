package com.gpcsoft.bizplatform.base.purcatalog.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.purcatalog.dao.PurCatalogDao;
import com.gpcsoft.bizplatform.base.purcatalog.dao.PurCatalogDetailDao;
import com.gpcsoft.bizplatform.base.purcatalog.dao.PurCatalogProcTypeDao;
import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalog;
import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogDetail;
import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogDistrict;
import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogProcType;
import com.gpcsoft.bizplatform.base.purcatalog.manager.PurCatalogDistrictManager;
import com.gpcsoft.bizplatform.base.purcatalog.service.PurCatalogService;
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.BeanUtils;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.baseData.domain.District;

@Service 
public class PurCatalogServiceImpl extends BaseGenericServiceImpl<PurCatalog> implements PurCatalogService {

	@Autowired(required=true) @Qualifier("purCatalogDaoHibernate")
	private PurCatalogDao purCatalogDaoHibernate;
	
	@Autowired(required=true) @Qualifier("purCatalogDetailDaoHibernate")
	private PurCatalogDetailDao purCatalogDetailDaoHibernate;
	
	
	@Autowired(required=true) @Qualifier("purCatalogProcTypeDaoHibernate")
	private PurCatalogProcTypeDao purCatalogProcTypeDaoHibernate;
	
	@Autowired(required=true) @Qualifier("purCatalogDistrictManagerImpl")
	private PurCatalogDistrictManager purCatalogDistrictManagerImpl;
	
	
	/** 
	 * Description :  保存目录
	 * Create Date: 2010-8-10上午10:31:45 by yucy  Modified Date: 2010-8-10上午10:31:45 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public PurCatalog savePurCatalog(PurCatalog purCatalog,String saveType ,String districtString) throws Exception {
		//新增
		if((null==purCatalog.getObjId()||"".equals(purCatalog.getObjId()))&&(null!=saveType&&"save".equals(saveType))){
			purCatalog.setUseStatus(OrganizationEnum.USE_TEMP);
			purCatalog.setAuditStatus(OrganizationEnum.DRAFT_EXAM);
			purCatalog.setPublishStatus(OrganizationEnum.UNPUBLISHED);
			purCatalog.setCreateTime(new Date());
			purCatalog.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		}//提交
		else if((null!=purCatalog.getObjId()&&!"".equals(purCatalog.getObjId()))&&(null!=saveType&&"submit".equals(saveType))){
			purCatalog.setUseStatus(OrganizationEnum.USE_TEMP);
			purCatalog.setAuditStatus(OrganizationEnum.AWAIT_EXAM);
		}
		
		if(null!=purCatalog.getObjId()&&!"".equals(purCatalog.getObjId())){
			//删除原来的集合
			purCatalogDistrictManagerImpl.deletePurCatalogDistrictByCataId(purCatalog.getObjId());
		}
		//拼装区域集合对象
		purCatalog.setPurCatalogDistrictSet(getPurCatalogDistrictSet(purCatalog,districtString));
		
		return  purCatalogDaoHibernate.savePurCatalog(purCatalog);
	}
	
	/** 
	 * Description :  拼装区域集合对象
	 * Create Date: 2010-8-25上午09:55:48 by yucy  Modified Date: 2010-8-25上午09:55:48 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Set<PurCatalogDistrict> getPurCatalogDistrictSet(PurCatalog purCatalog,String districtString){
		 Set<PurCatalogDistrict> purCatalogDistrictSet = new HashSet<PurCatalogDistrict>();
		 if(null!=districtString&&!"".equals(districtString)){
			 for(String districtId: districtString.split(",")){
				 PurCatalogDistrict purCatalogDistrict = new PurCatalogDistrict();
				 
				 District district = new District();
				 district.setObjId(districtId);
				 purCatalogDistrict.setDistrict(district); 
				 
				 purCatalogDistrict.setPurCatalog(purCatalog);
				 
				 purCatalogDistrictSet.add(purCatalogDistrict);
			 }
		 }
		 return purCatalogDistrictSet;
	}

	/** 
	 * Description :  保存目录明细对象
	 * Create Date: 2010-8-10下午04:19:18 by yucy  Modified Date: 2010-8-10下午04:19:18 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean saveCatalogDetailOrProcType(Map<String, Object> param) throws Exception {
		String detailId = (String)param.get("detailId");
		String catalogId =  (String)param.get("catalogId");
		String categoryIds = (String)param.get("categoryIds" );
		String goodsPrice = (String) param.get("goodsPrice" );
		String yearTotal = (String)param.get("yearTotal");
		String catalogTypeJson = (String)param.get("catalogTypeJson");
		Integer flag = 0;
       
		if(StringUtils.hasLength(catalogId)&&StringUtils.hasLength(categoryIds)){
			List<PurCatalogDetail> PurCatalogDetailList = new ArrayList<PurCatalogDetail>();
			List<PurCatalogProcType> PurCatalogProcTypeList = new ArrayList<PurCatalogProcType>();
			for (String categoryId : categoryIds.split(",")) {
				//保存PurCatalogDetail
				if(StringUtils.hasLength(catalogId)||StringUtils.hasLength(yearTotal)){
					PurCatalogDetail purCatalogDetail = new PurCatalogDetail();
					PurCatalog purCatalog = new PurCatalog (); purCatalog.setObjId(catalogId);
					purCatalogDetail.setPurCatalog(purCatalog);
					PurCategory  purCategory = new PurCategory();purCategory.setObjId(categoryId);
					purCatalogDetail.setPurCategory(purCategory);
					purCatalogDetail.setGoodsPrice((null==goodsPrice||"".equals(goodsPrice))?null:new BigDecimal(goodsPrice));
					purCatalogDetail.setYearTotal((null==yearTotal||"".equals(yearTotal))?null:new BigDecimal(yearTotal));
					//修改则有id
					purCatalogDetail.setObjId((null==detailId||"".equals(detailId))?null:detailId);
					PurCatalogDetailList.add(purCatalogDetail);
				}
				//保存PurCatalogProcType
				if(StringUtils.hasLength(catalogTypeJson)){
					for (String purCatalogProcTypeString : catalogTypeJson.split(",")) {
						if(StringUtils.hasLength(purCatalogProcTypeString)){
		    				String[] fieldStrings =  purCatalogProcTypeString.split(":");
		    				
		    				if(!(fieldStrings.length==1&&"-1".equals(fieldStrings[0]))){
			    				PurCatalogProcType purCatalogProcType = new PurCatalogProcType();
				  				PurCatalog purCatalog = new PurCatalog (); purCatalog.setObjId(catalogId);
				  				purCatalogProcType.setPurCatalog(purCatalog);
								PurCategory  purCategory = new PurCategory();purCategory.setObjId(categoryId);
								purCatalogProcType.setPurCategory(purCategory);
								purCatalogProcType.setProcType(fieldStrings[0]);
								purCatalogProcType.setProcTotal((fieldStrings.length>1&&fieldStrings[1]!=null&&!"".equals(fieldStrings[1]))?new BigDecimal(fieldStrings[1]):null);
								//修改则有id
								purCatalogProcType.setObjId((fieldStrings.length>2&&fieldStrings[2]!=null&&!"".equals(fieldStrings[2]))?fieldStrings[2]:null);
								PurCatalogProcTypeList.add(purCatalogProcType);
		    				}
						}
        		  }
        	  }
			}
			flag +=  purCatalogDetailDaoHibernate.save(PurCatalogDetailList).size();
			flag +=  purCatalogProcTypeDaoHibernate.save(PurCatalogProcTypeList).size();
    	  
       }
       return flag>0?true:false;
	}

	/** 
	 * Description :  获得展开节点的信息
	 * Create Date: 2010-8-11上午12:33:36 by yucy  Modified Date: 2010-8-11上午12:33:36 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public  List<Object> getOpenItemInfo(Map<String, Object> param) throws Exception {
		return purCatalogDaoHibernate.getOpenItemInfo(param);
	}
	
	/** 
	 * Description :  获得catalogdetail明细
	 * Create Date: 2010-8-11上午10:50:38 by yucy  Modified Date: 2010-8-11上午10:50:38 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCatalogDetail> getDetailInfo(Map<String, Object> param) throws Exception {
		return purCatalogDetailDaoHibernate.getDetailInfo(param);
	}

	/** 
	 * Description :  获得catalogType 明细
	 * Create Date: 2010-8-11上午10:51:13 by yucy  Modified Date: 2010-8-11上午10:51:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCatalogProcType> getTypeInfo(Map<String, Object> param) throws Exception {
		return purCatalogProcTypeDaoHibernate.purCatalogProcTypeHibernate(param);
	}

	
	/** 
	 * Description : 拷贝 catalog 
	 * Create Date: 2010-8-11下午07:45:08 by yucy  Modified Date: 2010-8-11下午07:45:08 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String createCopyCatalog(Map<String, Object> param) throws Exception {
		String objId = (String)param.get("objId");
		
		String title = (String)param.get("copyTitle");

		PurCatalog purCatalog =  get(objId);
		if(null==title||"".equals(title)){
			title = purCatalog.getTitle()+"（复制）";
		}
		
		PurCatalog purCatalogCopy = new PurCatalog();
		BeanUtils.copyPropertiesFilterEmptyNew(purCatalogCopy, purCatalog);
		purCatalogCopy.setObjId(null);
		
		Set<PurCatalogDetail> purCatalogDetailCopySet = new HashSet<PurCatalogDetail>();
		Set<PurCatalogProcType> purCatalogProcTypeCopySet = new HashSet<PurCatalogProcType>();
		
		//取PurCatalogDetailList
		List<PurCatalogDetail> purCatalogDetailList= purCatalogDetailDaoHibernate.getDetailInfoByCatalogId(purCatalog.getObjId());
		//取PurCatalogProcTypeList
		List<PurCatalogProcType> purCatalogProcTypeList = purCatalogProcTypeDaoHibernate.getProcTypeInfoByCatalogId(purCatalog.getObjId());
		
		for (PurCatalogDetail purCatalogDetail : purCatalogDetailList) {
			PurCatalogDetail purCatalogDetailCopy = new PurCatalogDetail();
			BeanUtils.copyPropertiesFilterEmpty(purCatalogDetailCopy, purCatalogDetail);
			purCatalogDetailCopy.setPurCatalog(purCatalogCopy);
			purCatalogDetailCopy.setObjId(null);
			purCatalogDetailCopySet.add(purCatalogDetailCopy);
		}
		
		for (PurCatalogProcType purCatalogProcType : purCatalogProcTypeList) {
			PurCatalogProcType purCatalogProcTypeCopy = new PurCatalogProcType();
			BeanUtils.copyPropertiesFilterEmpty(purCatalogProcTypeCopy, purCatalogProcType);
			purCatalogProcTypeCopy.setPurCatalog(purCatalogCopy);
			purCatalogProcTypeCopy.setObjId(null);
			purCatalogProcTypeCopySet.add(purCatalogProcTypeCopy);
		}
		
		purCatalogCopy.setPurCatalogDetailSet(purCatalogDetailCopySet);
		purCatalogCopy.setPurCatalogProcTypeSet(purCatalogProcTypeCopySet);
		
		purCatalogCopy.setObjId(null);
		
		purCatalogCopy.setTitle(title);
		
		purCatalogDaoHibernate.save(purCatalogCopy);
		
		return purCatalogCopy.getObjId();
		
	}

	/** 
	 * Description :  删除目录(备以约束删除)
	 * Create Date: 2010-8-12上午01:06:03 by yucy  Modified Date: 2010-8-12上午01:06:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean deleteCatalog(Map<String, Object> param) throws Exception {
		String objId = (String)param.get("objId");
		purCatalogDaoHibernate.remove(objId, PurCatalog.class);
		return true;
	}

	public Boolean getIshasAnyDetail(Map<String, Object> param)throws Exception  {
		String objId = (String)param.get("objId");
		//取PurCatalogDetailList
		List<PurCatalogDetail> purCatalogDetailList= purCatalogDetailDaoHibernate.getDetailInfoByCatalogId(objId);
		//取PurCatalogProcTypeList
		List<PurCatalogProcType> purCatalogProcTypeList = purCatalogProcTypeDaoHibernate.getProcTypeInfoByCatalogId(objId);
	   
		return (purCatalogDetailList.size()+purCatalogProcTypeList.size())<=0?false:true;
	}

	/** 
	 * Description :  审核
	 * Create Date: 2010-8-12下午01:56:08 by yucy  Modified Date: 2010-8-12下午01:56:08 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean auditPurCatalog(Map<String, Object> param) {
		String auditType = (String)param.get("auditType");
		PurCatalog purCatalog = (PurCatalog)param.get("purCatalog");
		
		if(auditType!=null&&"pass".equals(auditType)){
			purCatalog.setUseStatus(OrganizationEnum.USE_VALID);
			purCatalog.setAuditStatus(OrganizationEnum.PASS_EXAM);
		}else{
			purCatalog.setUseStatus(OrganizationEnum.USE_TEMP);
			purCatalog.setAuditStatus(OrganizationEnum.NO_PASS_EXAM);
		}
		this.preVerify(purCatalog);
		purCatalogDaoHibernate.save(purCatalog);
		return true;
	}

	/** 
	 * Description :  发布
	 * Create Date: 2010-8-25上午11:21:23 by yucy  Modified Date: 2010-8-25上午11:21:23 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updatePubStatus(String catalogId) throws Exception{
		return purCatalogDaoHibernate.updatePubStatus(catalogId);
	}
	
	
	/** 
	 * Description :  根据品目取得目录
	 * Create Date: 2010-9-3下午04:21:32 by yucy  Modified Date: 2010-9-3下午04:21:32 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getCatalogByCategory(Map<String, Object> param) throws Exception {
		return purCatalogDaoHibernate.getCatalogByCategory(param);
	}

	/** 
	 * Description :  取得年度
	 * Create Date: 2009-4-9下午04:12:16 by yucy  Modified Date: 2009-4-9下午04:12:16 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Integer> getAnual() throws Exception {
		return purCatalogDaoHibernate.getAnual();
	}
}
