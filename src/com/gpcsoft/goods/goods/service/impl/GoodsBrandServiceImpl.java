package com.gpcsoft.goods.goods.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.common.util.WordToSpell;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.dao.GoodsBrandDao;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.GoodsBrandChange;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.goods.goods.manager.GoodsBrandChangeManager;
import com.gpcsoft.goods.goods.manager.GoodsModifierManager;
import com.gpcsoft.goods.goods.service.GoodsBrandService;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.utils.SequenceNumberUtil;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;

/** 
  *  Comments: <strong>GoodsBrandServiceImpl</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-9-2 下午02:03:13 by yucy    					                            
  *  <br/>Modified Date:  2010-9-2 下午02:03:13 by yucy                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
@Service 
public class GoodsBrandServiceImpl extends BaseGenericServiceImpl<GoodsBrand> implements GoodsBrandService {
	
	@Autowired(required=true) @Qualifier("goodsBrandDaoHibernate")
	private GoodsBrandDao goodsBrandDaoHibernate;
	
	@Autowired(required=true) @Qualifier("goodsClassDaoHibernate")
	private GoodsClassDao goodsClassDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	
	@Autowired(required=true)  @Qualifier("goodsModifierManagerImpl")
	private GoodsModifierManager goodsModifierManager;
	
	@Autowired(required=true)  @Qualifier("goodsBrandChangeManagerImpl")
	private GoodsBrandChangeManager goodsBrandChangeManager;
	
	/** 
	 * Description :  检查品牌名称
	 * Create Date: 2011-5-6上午09:51:53 by yucy  Modified Date: 2011-5-6上午09:51:53 by yucy
	 * @param   brandName		检查目标
	 * 			goodsClassId	过滤的范围
	 * 			notObjId		排除的id
	 * @return  
	 * @Exception   
	 */
	public Map<String,Object> checkBrandName(String brandName, String goodsClassId, String notObjId) throws Exception{ 
		return goodsBrandDaoHibernate.checkBrandName(brandName,goodsClassId,notObjId);
	}

	/** 
	 * Description :  品牌新增
	 * Create Date: 2010-8-4下午02:44:59 by yucy  Modified Date: 2010-8-4下午02:44:59 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean createGoodsBrand(GoodsBrand goodsBrand , Map<String, Object> param ) throws Exception {
		String classBrandString = (String)param.get("classBrandString");
		
		String saveType = (String)param.get("saveType");
		
		if(StringUtils.hasLength(classBrandString)){
			Set<GoodsClass> goodsClassSet = new HashSet<GoodsClass>();
			goodsClassSet.addAll( goodsClassDaoHibernate.findGoodsClassByIds(classBrandString.split(",")) );
			goodsBrand.getGoodsClasses().clear();
			goodsBrand.setGoodsClasses(goodsClassSet);
		}
		
    	goodsBrand.setUseStatus(GoodsEnum.USE_TEMP);//临时

		if(null!=saveType&&"save".equals(saveType)){
			goodsBrand.setAuditStatus(GoodsEnum.DRAFT_EXAM);//草稿
		}else{
			goodsBrand.setAuditStatus(GoodsEnum.AWAIT_EXAM);//待审核
			if(roleManagerImpl.isHasThisRole(AuthenticationHelper.getCurrentUser(true).getObjId(), OrganizationEnum.ROLE_MANAGER)){
				goodsBrand.setAuditStatus(GoodsEnum.PASS_EXAM);//管理员直接通过
				goodsBrand.setUseStatus(GoodsEnum.USE_VALID);//管理员直接通过
			}
		}
		
		WordToSpell spell = new WordToSpell();
		goodsBrand.setShortSpellName(spell.String2Alpha(goodsBrand.getBrandName()));//设置拼写简码
		goodsBrand.setSellStatus(GoodsEnum.SELL_START);//起卖
    	goodsBrand.setCreateTime(new Date());
    	goodsBrand.setCreateUser(AuthenticationHelper.getCurrentUser(true));
    	
		goodsBrand.setBrandCode(SequenceNumberUtil.getGoodsBrandSN()); //设置品牌的编号
    	
    	save(goodsBrand);
    	return true;
	}
	
	/** 
	 * Description :  品牌修改
	 * Create Date: 2010-8-4下午02:44:59 by yucy  Modified Date: 2010-8-4下午02:44:59 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateGoodsBrand(GoodsBrand goodsBrand,Map<String, Object> param) throws Exception {
		String classBrandString = (String)param.get("classBrandString");
		String saveType = (String)param.get("saveType");
		
		if(StringUtils.hasLength(classBrandString)){
			Set<GoodsClass> goodsClassSet = new HashSet<GoodsClass>();
			goodsClassSet.addAll( goodsClassDaoHibernate.findGoodsClassByIds(classBrandString.split(",")) );
			
			goodsBrand.getGoodsClasses().clear();
			goodsBrand.setGoodsClasses(goodsClassSet);
		}
		
    	goodsBrand.setUseStatus(GoodsEnum.USE_TEMP);//临时

		if(null!=saveType&&"save".equals(saveType)){
			goodsBrand.setAuditStatus(GoodsEnum.DRAFT_EXAM);//草稿
		}else{
			goodsBrand.setAuditStatus(GoodsEnum.AWAIT_EXAM);//待审核
			if(roleManagerImpl.isHasThisRole(AuthenticationHelper.getCurrentUser(true).getObjId(), OrganizationEnum.ROLE_MANAGER)){
				goodsBrand.setAuditStatus(GoodsEnum.PASS_EXAM);//管理员直接通过
				goodsBrand.setUseStatus(GoodsEnum.USE_VALID);//管理员直接通过
			}
		}
		
		WordToSpell spell = new WordToSpell();
		goodsBrand.setShortSpellName(spell.String2Alpha(goodsBrand.getBrandName()));//设置拼写简码
		goodsBrand.setSellStatus(GoodsEnum.SELL_START);//起卖
    	goodsBrand.setUpdateUser(AuthenticationHelper.getCurrentUser(true));
    	goodsBrand.setUpdateTime(new Date());
    	
    	goodsBrand.setBrandCode(SequenceNumberUtil.getGoodsBrandSN());

    	save(goodsBrand);
    	return true;
	}
	
	/** 
	 * Description :  品牌变更
	 * Create Date: 2010-8-4下午02:44:59 by yucy  Modified Date: 2010-8-4下午02:44:59 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean saveChangeGoodsBrand(GoodsBrand goodsBrand,Map<String, Object> param) throws Exception {
		
		String saveType = (String)param.get("saveType");
		
    	String changeClassId = (String)param.get("changeClass.objId");
    	String changeClassName = (String)param.get("changeClass.name");
    	String changeName = (String)param.get("changeName");
    	String changeEnglish = (String)param.get("changeEnglish");
    	
    	//旧的classIds
    	String classBrandString = (String)param.get("classBrandString");

    	//是否是管理员
    	boolean isManager = roleManagerImpl.isHasThisRole(AuthenticationHelper.getCurrentUser(true).getObjId(), OrganizationEnum.ROLE_MANAGER);
    	
		//品牌分类的变更
		if(StringUtils.hasLength(changeClassId) && StringUtils.hasLength(changeClassName) ){
			
			Set<GoodsClass> goodsClassSetNew = new HashSet<GoodsClass>();
			goodsClassSetNew.addAll( goodsClassDaoHibernate.findGoodsClassByIds(changeClassId.split(",")) );
			
			Set<GoodsClass> goodsClassSetOld = new HashSet<GoodsClass>();
			goodsClassSetOld.addAll( goodsClassDaoHibernate.findGoodsClassByIds(classBrandString.split(",")) );
			
			if(goodsClassSetNew.containsAll(goodsClassSetOld) && goodsClassSetOld.containsAll(goodsClassSetNew)   ){
				
			}else{
				
				//查找上次变更的数据
				List<GoodsBrandChange> goodsBrandChangeList = goodsBrandChangeManager.getGoodsBrandChange(goodsBrand.getObjId(),"goodsClass",GoodsEnum.DRAFT_EXAM );
				
				GoodsBrandChange goodsBrandChange  = goodsBrandChangeList!=null&&goodsBrandChangeList.size()>0?goodsBrandChangeList.get(0):new GoodsBrandChange();
				
				goodsBrandChange.setGoodsBrand(goodsBrand);
				goodsBrandChange.setModifyType("goodsClass");
				goodsBrandChange.setNewValue(changeClassId+"##"+changeClassName);
				goodsBrandChange.setOldValue(classBrandString+"##"+goodsBrand.getGoodsClassNames());
				goodsBrandChange.setCreateTime(new Date());
				goodsBrandChange.setCreateUser(AuthenticationHelper.getCurrentUser(true));
				goodsBrandChange.setAuditStatus( saveType.indexOf("save")>=0? GoodsEnum.DRAFT_EXAM: GoodsEnum.AWAIT_EXAM );
				
				goodsBrandChangeManager.save(goodsBrandChange);
			}
		}
		
		//品牌名称的变更
		if(StringUtils.hasLength(changeName) && !changeName.equals(goodsBrand.getBrandName())){
			
			//查找上次变更的数据
			List<GoodsBrandChange> goodsBrandChangeList = goodsBrandChangeManager.getGoodsBrandChange(goodsBrand.getObjId(),"brandName",GoodsEnum.DRAFT_EXAM );
			
			GoodsBrandChange goodsBrandChange  = goodsBrandChangeList!=null&&goodsBrandChangeList.size()>0?goodsBrandChangeList.get(0):new GoodsBrandChange();
			
			goodsBrandChange.setGoodsBrand(goodsBrand);
			goodsBrandChange.setModifyType("brandName");
			goodsBrandChange.setNewValue(changeName);
			goodsBrandChange.setOldValue(goodsBrand.getBrandName());
			goodsBrandChange.setCreateTime(new Date());
			goodsBrandChange.setCreateUser(AuthenticationHelper.getCurrentUser(true));
			goodsBrandChange.setAuditStatus( saveType.indexOf("save")>=0? GoodsEnum.DRAFT_EXAM: GoodsEnum.AWAIT_EXAM );
			
			goodsBrandChangeManager.save(goodsBrandChange);
		}
		
		//品牌因文名称的变更
		if(StringUtils.hasLength(changeEnglish) && !changeEnglish.equals(goodsBrand.getEnglishName())){
			
			//查找上次变更的数据
			List<GoodsBrandChange> goodsBrandChangeList = goodsBrandChangeManager.getGoodsBrandChange(goodsBrand.getObjId(),"englishName",GoodsEnum.DRAFT_EXAM );
			GoodsBrandChange goodsBrandChange  = goodsBrandChangeList!=null&&goodsBrandChangeList.size()>0?goodsBrandChangeList.get(0):new GoodsBrandChange();
			
			goodsBrandChange.setGoodsBrand(goodsBrand);
			goodsBrandChange.setModifyType("englishName");
			goodsBrandChange.setNewValue(changeEnglish);
			goodsBrandChange.setOldValue(goodsBrand.getEnglishName());
			goodsBrandChange.setCreateTime(new Date());
			goodsBrandChange.setCreateUser(AuthenticationHelper.getCurrentUser(true));
			goodsBrandChange.setAuditStatus( saveType.indexOf("save")>=0? GoodsEnum.DRAFT_EXAM: GoodsEnum.AWAIT_EXAM );
			
			goodsBrandChangeManager.save(goodsBrandChange);
		}
		
		//若提交将所有的变更都提交
		if(saveType.indexOf("submit")>=0){
			//如果是管理员提交
			if(isManager&& saveType.indexOf("submit")>=0){
				List<GoodsBrandChange> goodsBrandChangeList = goodsBrandChangeManager.getGoodsBrandChange(goodsBrand.getObjId(),null,GoodsEnum.AWAIT_EXAM );
				for (GoodsBrandChange goodsBrandChange : goodsBrandChangeList) {
					if("englishName".equals(goodsBrandChange.getModifyType())){
						goodsBrand.setEnglishName(goodsBrandChange.getNewValue());
						goodsBrandChange.setAuditStatus(GoodsEnum.PASS_EXAM);
						goodsBrandChange.setVerifyTime(new Date());
						goodsBrandChange.setVerifyUser(AuthenticationHelper.getCurrentUser(true));
					}
					if("brandName".equals(goodsBrandChange.getModifyType())){
						goodsBrand.setBrandName(goodsBrandChange.getNewValue());
						goodsBrandChange.setAuditStatus(GoodsEnum.PASS_EXAM);
						goodsBrandChange.setVerifyTime(new Date());
						goodsBrandChange.setVerifyUser(AuthenticationHelper.getCurrentUser(true));
					}
					if("goodsClass".equals(goodsBrandChange.getModifyType())){
						Set<GoodsClass> goodsClassSetNew = new HashSet<GoodsClass>();
						goodsClassSetNew.addAll( goodsClassDaoHibernate.findGoodsClassByIds(goodsBrandChange.getNewValue().split("##")[0].split(",")) );
						goodsBrand.getGoodsClasses().clear();
						goodsBrand.getGoodsClasses().addAll(goodsClassSetNew);
						goodsBrand.setGoodsClassNames(goodsBrandChange.getNewValue().split("##")[1]);
						goodsBrandChange.setAuditStatus(GoodsEnum.PASS_EXAM);
						goodsBrandChange.setVerifyTime(new Date());
						goodsBrandChange.setVerifyUser(AuthenticationHelper.getCurrentUser(true));
					}
				}
			}
			goodsBrandChangeManager.updateAuditStatus(goodsBrand.getObjId(),null,GoodsEnum.DRAFT_EXAM, GoodsEnum.AWAIT_EXAM);
		}
		
		goodsBrand.setUpdateUser(AuthenticationHelper.getCurrentUser(true));
    	goodsBrand.setUpdateTime(new Date());
    	
    	save(goodsBrand);
    	return true;
	}
	
	/** 
	 * Description :  品牌审核
	 * Create Date: 2010-8-4下午02:44:59 by yucy  Modified Date: 2010-8-4下午02:44:59 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateBrandAuditStatus(Map<String, Object> param) throws Exception {
		Boolean result = false;
		String auditStatus = (String)param.get("auditStatus");
		GoodsBrand goodsBrand = (GoodsBrand)param.get("goodsBrand");
		preVerify(goodsBrand);
		
		//审核通过
		if(null!=auditStatus&&"pass".equals(auditStatus)){
			goodsBrand.setUseStatus(GoodsEnum.USE_VALID);
			goodsBrand.setAuditStatus(GoodsEnum.PASS_EXAM);
			goodsBrand.setValidTime(new Date());
			goodsBrand.setCurrentId(null);
			save(goodsBrand);
			
			//审核成功后置为指定供应商(有供应商)
			if(StringUtils.hasLength(goodsBrand.getBelongsId())){
				goodsModifierManager.createGoodsModifier(goodsBrand, goodsBrand.getBelongsId().split(","));
			}
		}
		//审核不通过
		else if (null!=auditStatus&&"nopass".equals(auditStatus)) {
			goodsBrand.setUseStatus(GoodsEnum.USE_TEMP);
			goodsBrand.setAuditStatus(GoodsEnum.NO_PASS_EXAM);
			save(goodsBrand);
		}
		return result;
	}
	
	/** 
	 * Description :  启卖禁卖
	 * Create Date: 2010-8-4下午05:02:27 by yucy  Modified Date: 2010-8-4下午05:02:27 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateSellStatus(Map<String, Object> param) throws Exception {
		return goodsBrandDaoHibernate.updateSellStatus(param);
	}

	/** 
	 * Description :  删除品牌
	 * Create Date: 2010-8-4下午05:02:27 by yucy  Modified Date: 2010-8-4下午05:02:27 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean deleteBrand(Map<String, Object> param) throws Exception {
		return goodsBrandDaoHibernate.deleteBrand(param);
	}

	/** 
	 * Description :  报废品牌(有效品牌)
	 * Create Date: 2010-8-6上午09:54:02 by yucy  Modified Date: 2010-8-6上午09:54:02 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateDestroy(Map<String, Object> param) throws Exception {
		return goodsBrandDaoHibernate.updateDestroy(param);
	}

	/** 
	 * Description :  获取指定维护供应商的商品品牌 
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsBrand> getAssignedGoodsBrand() throws Exception {
		List<String[]> list = new ArrayList<String[]>();
		
		User user = AuthenticationHelper.getCurrentUser(true);
		String orgInfoId = "";
		// 如果当前用户是供应商
		if(null != user.getOrgInfo()) {
			// 如果当前用户是供应商且是管理员
			if(roleManagerImpl.isHasThisRole(user.getObjId(),"3")){
				orgInfoId = "";
			}else{
				orgInfoId = user.getOrgInfo().getObjId();
			}
		}
		
		// 如果当前用户是供应商并且是管理员，则取全部品牌
		list = goodsBrandDaoHibernate.getAssignedGoodsBrand(orgInfoId);
		List<GoodsBrand> goodsBrandList = new ArrayList<GoodsBrand>();
		
		for(Object[] obj : list){
			GoodsBrand goodsBrand = new GoodsBrand();
			goodsBrand.setObjId((String) obj[0]);
			goodsBrand.setBrandName((String) obj[1]);
			goodsBrandList.add(goodsBrand);
		}
		return goodsBrandList;
	}
	/** 
	 * Description : 获得供应商可维护的所有品牌  
	 * Create Date: 2010-9-9下午03:52:29 by guoyr  Modified Date: 2010-9-9下午03:52:29 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsBrand> getAllGoodsBrandByOrgId(){
		
		User user = AuthenticationHelper.getCurrentUser(true);
		String orgInfoId = "";
		// 如果当前用户是供应商
		if(null != user.getOrgInfo()) {
			// 如果当前用户是供应商且是管理员
			if(roleManagerImpl.isHasThisRole(user.getObjId(),"3")){
				orgInfoId = "";
			}else{
				orgInfoId = user.getOrgInfo().getObjId();
			}
		}
		
		// 如果当前用户是供应商并且是管理员，则取全部品牌
		return goodsBrandDaoHibernate.getAllGoodsBrandByOrgId(orgInfoId);
	}

	/** 
	 * Description :  根据参数获得品牌的展示信息列表
	 * Create Date: 2011-2-16下午05:50:15 by liangxj  Modified Date: 2011-2-16下午05:50:15 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：排序信息
	 * @return  
	 * @Exception   
	 */
	public Page<GoodsBrand> getGoodsBrandListForShow(Page<GoodsBrand> page,Map<String, Object> paramsMap) throws Exception{
		return goodsBrandDaoHibernate.getGoodsBrandListForShow(page,paramsMap);
	}

	/** 
	 * Description :  审核变更信息
	 * Create Date: 2011-5-10上午09:37:02 by yucy  Modified Date: 2011-5-10上午09:37:02 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean auditChange(String brandId, String auditStatus) throws Exception {
		
		List<GoodsBrandChange> goodsBrandChangeList= goodsBrandChangeManager.getGoodsBrandChange(brandId, null, GoodsEnum.AWAIT_EXAM);
		
		if(GoodsEnum.PASS_EXAM.equals(auditStatus)){
			
			GoodsBrand brand = get(brandId);
			
			for (GoodsBrandChange goodsBrandChange : goodsBrandChangeList) {
				//更新名称
				if("brandName".equals(goodsBrandChange.getModifyType())){
					brand.setBrandName(goodsBrandChange.getNewValue());
				}
				//更新英文名
				if("englishName".equals(goodsBrandChange.getModifyType())){
					brand.setEnglishName(goodsBrandChange.getNewValue());
				}
				//更新分类
				if("goodsClass".equals(goodsBrandChange.getModifyType())){
					String classIds = goodsBrandChange.getNewValue().split("##")[0];
					brand.getGoodsClasses().clear();
					brand.getGoodsClasses().addAll( goodsClassDaoHibernate.findGoodsClassByIds(classIds.split(",")) );
					brand.setGoodsClassNames(goodsBrandChange.getNewValue().split("##")[1]);
				}
			}
			save(brand);
		}
		return goodsBrandChangeManager.updateAuditStatus(brandId, null, GoodsEnum.AWAIT_EXAM, auditStatus);
	}
}
