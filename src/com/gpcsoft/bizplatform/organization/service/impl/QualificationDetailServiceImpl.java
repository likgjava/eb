package com.gpcsoft.bizplatform.organization.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.dao.OrgQualityDao;
import com.gpcsoft.bizplatform.organization.dao.QualificationDetailDao;
import com.gpcsoft.bizplatform.organization.domain.OrgQuality;
import com.gpcsoft.bizplatform.organization.domain.QualificationDetail;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.service.QualificationDetailService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class QualificationDetailServiceImpl extends BaseGenericServiceImpl<QualificationDetail> implements QualificationDetailService {
	
	@Autowired(required=true)  @Qualifier("qualificationDetailDaoHibernate")
	private QualificationDetailDao qualificationDetailDaoHibernate;
	@Autowired(required=true) @Qualifier("orgQualityDaoHibernate")
	private OrgQualityDao orgQualityDaoHibernate;
	

//	@Autowired(required=true) @Qualifier("qualityGradesManagerImpl")
//	private QualityGradesManager qualityGradesManager;
//	 /**
//     * Description :获取资质信息定义下的资质参数动态生成页面元素
//     * Create Date: 2010-5-28 by liqy  Modified Date: 2010-5-28 by liqy
//	 * @param modelReturn  
//     * @return  
//     * @Exception
//     */
//	@SuppressWarnings("unchecked")
//	public Map<String, Object> getQualityParamsView(String gradesId, Map<String, Object> modelReturn)throws Exception{
//		QualityGrades qualityGrades = qualityGradesManager.get(gradesId);
//		List<QualityParam> qualityParamList = CollectionUtil.covSetToList(qualityGrades.getQualityGradesParamList());
//		if(!qualityParamList.isEmpty()){
//			int lan = 0;
//			String dateIds = "";//用于页面调用日期控件指定ID
//			String photoIds = "";//用于页面验证图片后缀指定ID
//			String quaParamIds = "";//用于页面保存时资质信息与资质参数对象关联上
//			StringBuffer  strParam = new StringBuffer ();//页面内容
//			Boolean showTip = null;//有附件时页面提示
//			for(QualityParam list: qualityParamList){
//				lan++;
//				strParam.append(compagesQualityParamsView(list.getObjId(),list.getParamName(),list.getParamValueType(),list.getIsRequired(),list.getParamValueLen(),lan));				
//				quaParamIds += list.getObjId()+",";
//				if(this.enumService.getValueByConstant("base.qualityParam.paramValueType", "QUA_PARAM_VALUE_TYPE_04")
//						.equals(list.getParamValueType()))
//							dateIds += "paramValue"+lan+",";
//				if(this.enumService.getValueByConstant("base.qualityParam.paramValueType", "QUA_PARAM_VALUE_TYPE_06")
//						.equals(list.getParamValueType()))
//							photoIds += "paramValue"+lan+",";
//				if(this.enumService.getValueByConstant("base.qualityParam.paramValueType", "QUA_PARAM_VALUE_TYPE_05")
//						.equals(list.getParamValueType())
//					|| this.enumService.getValueByConstant("base.qualityParam.paramValueType", "QUA_PARAM_VALUE_TYPE_06")
//						.equals(list.getParamValueType()))
//							showTip = true;
//			}			
//			modelReturn.put("showLi", strParam.toString());//页面内容
//			modelReturn.put("showTip", showTip);//有附件时页面提示
//			if("".equals(dateIds)) modelReturn.put("showDateIds","");
//				else modelReturn.put("showDateIds", dateIds.substring(0, dateIds.length()-1));
//			if("".equals(photoIds)) modelReturn.put("showPhotoIds","");
//				else modelReturn.put("showPhotoIds", photoIds.substring(0, photoIds.length()-1));
//			if ("".equals(quaParamIds))modelReturn.put("showQuaParamIds", "");
//				else modelReturn.put("showQuaParamIds", quaParamIds.substring(0, quaParamIds.length()-1));
//		}else{ 
//			modelReturn.put("showLi", "<li><label>没有资质参数：</label></li>");//页面内容
//			modelReturn.put("showTip", "");//有附件时页面提示
//			modelReturn.put("showDateIds","");//用于页面调用日期控件指定ID
//			modelReturn.put("showPhotoIds","");//用于页面验证图片后缀指定ID
//			modelReturn.put("showQuaParamIds", "");//用于页面保存时资质信息与资质参数对象关联上
//		}		
//		return modelReturn;		
//	}
	
	 /**
     * Description :组织资质定义页面元素
     * Create Date: 2010-5-28 by liqy  Modified Date: 2010-5-28 by liqy
	 * @param 资质参数ID,参数名称,参数值类型,是否必填,参数值长度,拥有参数个数（list长度）   
     * @return  
     * @Exception
     */
	public StringBuffer compagesQualityParamsView(String objId,String name,String type,Boolean isReq,Integer valLan,int lan)throws Exception{
		StringBuffer  str = new StringBuffer ();
		str.append("<li><label>"+name+"：</label>");
		if(this.enumService.getValueByConstant("base.qualityParam.paramValueType", "QUA_PARAM_VALUE_TYPE_01").equals(type)){/*参数值为字符*/
			str.append("<input type=\"text\" id=\"paramValue"+lan+"\" name=\"paramValue"+lan+"\" value=\"\" maxlength=\""+valLan+"\" class=\"userName ");
		}else if(this.enumService.getValueByConstant("base.qualityParam.paramValueType", "QUA_PARAM_VALUE_TYPE_02").equals(type)){/*参数值为整数*/
			str.append("<input type=\"text\" id=\"paramValue"+lan+"\" name=\"paramValue"+lan+"\" value=\"\" maxlength=\""+valLan+"\" class=\"digits ");
		}else if(this.enumService.getValueByConstant("base.qualityParam.paramValueType", "QUA_PARAM_VALUE_TYPE_03").equals(type)){/*参数值为含小数*/
			str.append("<input type=\"text\" id=\"paramValue"+lan+"\" name=\"paramValue"+lan+"\" value=\"\" maxlength=\""+valLan+"\" class=\"money ");
		}else if(this.enumService.getValueByConstant("base.qualityParam.paramValueType", "QUA_PARAM_VALUE_TYPE_04").equals(type)){/*参数值为日期*/
			str.append("<input type=\"text\" id=\"paramValue"+lan+"\" name=\"paramValue"+lan+"\" value=\"\" class=\"sysicon siDate date ");
		}else if(this.enumService.getValueByConstant("base.qualityParam.paramValueType", "QUA_PARAM_VALUE_TYPE_05").equals(type)){/*参数值为附件*/
			str.append("<input type=\"file\" id=\"paramValue"+lan+"\" name=\"paramValue"+lan+"\" class=\" ");
		}else if(this.enumService.getValueByConstant("base.qualityParam.paramValueType", "QUA_PARAM_VALUE_TYPE_06").equals(type)){/*参数值为图片*/
			str.append("<input type=\"file\" id=\"paramValue"+lan+"\" name=\"paramValue"+lan+"\" class=\" ");
		}
		if(isReq){//必填
			str.append(" required\"/><span class='eleRequired'>*</span></li>");
		}else
			str.append(" \"/></li>");
		return str;		
	}
	
	/** 
	 * Description :  保存或修改企业的财务信息
	 * Create Date: 2010-11-15下午03:15:45 by guoyr  Modified Date: 2010-11-15下午03:15:45 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveOrUpdateQualificationDetail(String financeInfoJSONString) {
		if(!financeInfoJSONString.equals("undefined")&& !financeInfoJSONString.equals("")){
			JSONArray jSONArray = JSONArray.fromObject(financeInfoJSONString);
			if(jSONArray != null && !jSONArray.isEmpty()){
				Set<OrgQuality> orgQualitySet = new HashSet<OrgQuality>();
				for (int i = 0; i < jSONArray.size(); i++) {
					if("null".equals(jSONArray.get(i).toString())){
						continue;
					}
					JSONObject jSONObject = (JSONObject)jSONArray.get(i);
					QualificationDetail qualificationDetail = (QualificationDetail)JSONObject.toBean(jSONObject,QualificationDetail.class);
					if(null == qualificationDetail){
						continue;
					}
					
					// 修改
					if(null != qualificationDetail.getObjId() && !"".equals(qualificationDetail.getObjId())){
						qualificationDetailDaoHibernate.updateQualificationDetail(qualificationDetail.getObjId(), qualificationDetail.getParamValue());
					}else {
						
						// 如果有参数值才做保存，避免出现空数据
						if( null == qualificationDetail.getParamValue() || "".equals(qualificationDetail.getParamValue())){
							continue;
						}
						
						// 资质主对象，如果当前没有资质对象，则新增
						if(null == qualificationDetail.getOrgQuality() || null == qualificationDetail.getOrgQuality().getObjId() || "".equals(qualificationDetail.getOrgQuality().getObjId())){
							OrgQuality orgQuality = null;
							
							// 新增之前先在资质集合中查找，如果已经存在则直接使用（如果该参数定的的第一个参数新增过了，第二个参数就可以直接使用）
							Iterator<OrgQuality> iterator = orgQualitySet.iterator();
							for(; iterator.hasNext();){
								OrgQuality obj = iterator.next();
								if(obj.getQualificationDefine().getObjId().equals(qualificationDetail.getOrgQuality().getQualificationDefine().getObjId())){
									orgQuality = obj;
								}
							}
							
							// 如果没有找到该参数定义的资质对象，则新增
							if(null == orgQuality){
								
								orgQuality = qualificationDetail.getOrgQuality();
								// 默认为待审核
								orgQuality.setAuditStatus(OrganizationEnum.AWAIT_EXAM);
								orgQuality.setObjId(null);
								orgQuality.setAuditStatus(OrganizationEnum.PASS_EXAM);
								orgQuality.setUseStatus(OrganizationEnum.USE_VALID);
								orgQuality = orgQualityDaoHibernate.save(orgQuality);
								orgQualitySet.add(orgQuality);
							}
							qualificationDetail.setOrgQuality(orgQuality);
						}
						qualificationDetail.setObjId(null);
						qualificationDetailDaoHibernate.save(qualificationDetail);
					}
				}
			}
		}
		
	}

	/** 
	 * Description :  根据资质分类code和机构id获取信息(财务信息和法务信息用的)
	 * Create Date: 2011-8-19下午02:18:57 by yucy  Modified Date: 2011-8-19下午02:18:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<QualificationDetail> getQualificationDetailListByCodeAndOrgId(String code, String orgId) throws Exception {
		return qualificationDetailDaoHibernate.getQualificationDetailListByCodeAndOrgId( code, orgId);
	}
}
