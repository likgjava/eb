package com.gpcsoft.bizplatform.organization.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationParam;
import com.gpcsoft.bizplatform.organization.dao.OrgQualityDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.domain.OrgQuality;
import com.gpcsoft.bizplatform.organization.domain.QualificationDetail;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.manager.OrgInfoManager;
import com.gpcsoft.bizplatform.organization.manager.OrgQualityManager;
import com.gpcsoft.bizplatform.organization.service.OrgQualityService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.manager.AttachmentManager;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

@Service 
public class OrgQualityServiceImpl extends BaseGenericServiceImpl<OrgQuality> implements OrgQualityService {

	@Autowired(required=true) @Qualifier("orgQualityManagerImpl")
	private OrgQualityManager orgQualityManager;
	
	@Autowired(required=true) @Qualifier("attachmentManagerImpl")
	private AttachmentManager attachmentManager;
	
	@Autowired(required=true) @Qualifier("orgInfoManagerImpl")
	private OrgInfoManager orgInfoManager;
	
	@Autowired(required=true) @Qualifier("orgQualityDaoHibernate")
	private OrgQualityDao orgQualityDaoHibernate;
	
	/**
     * Description :获取资质分类下资质信息 （资质分类下联动菜单数据拼装）
     * Create Date: 2010-5-28 by liqy  Modified Date: 2010-5-28 by liqy
     * @param   
     * @return  
     * @Exception
     */
	public List<Object> getQualityGradesSelect(String defindId,List<Object> modelReturnList)throws Exception{		
		return orgQualityManager.getQualityGradesSelect(defindId,modelReturnList);				
	}
	
	/**
     * Description :资质信息对象唯一性判断
     * Create Date: 2010-6-1 by liqy  Modified Date: 2010-6-1 by liqy
     * @param   objectId所属对象ID, quaGradesId资质定义信息ID,objectName所属对象名称,verNo版本号（02为最新）
     * @return  存在返回true 不存在返回false
     * @Exception
     */
	public Boolean isUniqueQualification(String objectId, String quaGradesId,String objectName)throws Exception{
		if(orgQualityManager.getQualificationList(objectId, quaGradesId, objectName,
				this.enumService.getValueByConstant("base.qualification.verNo", "VER_02")) != null)return true;
			else return false;
	}
	
	/**
     * Description :新增保存资质信息 
     * Create Date: 2010-5-31 by yucy  Modified Date: 2010-5-31 by yucy
     * @param   
     * @return  
     * @Exception
     */
	public Boolean saveQualificationInfo(OrgQuality orgQuality,Map<String, Object> param)throws Exception{
		String paramJson = (String)param.get("paramJson");//资质参数String
		Set<QualificationDetail>  qualificationDetailSet = new HashSet<QualificationDetail>();//拼装qualificationDetailSet对象 用于级联保存
		if(null!=paramJson&&!"".equals(paramJson)){
			String[] detail = paramJson.split(",");
			for (int i = 0; i < detail.length; i++) {
				QualificationDetail qualificationDetail = new QualificationDetail();
				QualificationParam qualificationParam = new QualificationParam();
				String[] strings = detail[i].split(":");
				if(strings.length>0&&null!=strings[0]&&!"".equals(strings[0])&&!"undefined".equals(strings[0]))
					qualificationParam.setObjId(strings[0]);
				if(strings.length>1&&null!=strings[1]&&!"".equals(strings[1])&&!"undefined".equals(strings[0]))
					qualificationDetail.setParamValue(strings[1]);
				if(strings.length>2&&!"undefined".equals(strings[2])&&null!=strings[2]&&!"".equals(strings[2])){
					qualificationDetail.setObjId(strings[2]);
				}
				qualificationDetail.setQualityParam(qualificationParam);
				qualificationDetailSet.add(qualificationDetail);
			}
		}
		orgQuality.setQualificationDetailSet(qualificationDetailSet);
		orgQuality.setCreateTime(new Date());
		orgQuality.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		//是否修改
		if(null!=orgQuality.getObjId()&&!"".equals(orgQuality.getObjId())){
			orgQuality.setUpdateTime(new Date());
			orgQuality.setUpdateUser(AuthenticationHelper.getCurrentUser(true));
		}
		orgQuality.setOrg((OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo());
		save(orgQuality);
		return true;	
	}
	
		/**
     * Description :保存资质信息详细中的图片和附件 
     * Create Date: 2010-6-2 by liqy  Modified Date: 2010-6-2 by liqy
     * @param   
     * @return  
     * @Exception
     */
	public QualificationDetail saveQuaDetailUpLoadFile(QualificationDetail qualificationDetail,HttpServletRequest request,String fileStr)throws Exception{
		Attachment upFile = null;
		String attObjId = null;
		String attViewName = null;
		try {
			upFile = AttachmentUtil.uploadFile(request, fileStr);			
			Attachment att = attachmentManager.save(upFile);
			attObjId = att.getObjId();
			attViewName = att.getViewName();
		} catch (Exception e) {
			System.out.println("nothing to do for upload!");
		}
		if(upFile!=null){
			/*删除旧图片和附件*/
			if(!"".equals(qualificationDetail.getParamValue()) && qualificationDetail.getParamValue() !=null){
				removeQuaDetailUpLoadFile(qualificationDetail.getParamValue().split(",")[1]);
			}
			qualificationDetail.setParamValue(attViewName+","+attObjId);//上传附件并设置ParamValue值为附件名称+ID
		}
		return qualificationDetail;		
	}
	
	/**
     * Description :删除资质信息详细中的图片和附件 
     * Create Date: 2010-6-3 by liqy  Modified Date: 2010-6-3 by liqy
     * @param   
     * @return  
     * @Exception
     */
	public Boolean removeQuaDetailUpLoadFile(String objId)throws Exception{
		attachmentManager.remove(objId, Attachment.class);//删除数据（暂时只做数据删除，不做物理性删除）   
		return true;		
	}
	
	
	/** 
	 * Description :  删除资质信息 (草稿和不通过可删)
	 * Create Date: 2010-8-3上午01:24:49 by yucy  Modified Date: 2010-8-3上午01:24:49 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean removeQualificationInfo(String objId)throws Exception{
		boolean flag = false;
		OrgQuality orgQuality = orgQualityManager.get(objId);
		//只有待审核状态不能删
		if(!this.enumService.getValueByConstant("biz.orgQuality.status", "AWAIT_EXAM").equals(orgQuality.getAuditStatus())) {
			remove(objId, OrgQuality.class);
			flag = true;
		}
		return flag;		
	}
	
	/**
     * Description :组织资质信息详细页面元素
     * Create Date: 2010-6-2 by liqy  Modified Date: 2010-6-2 by liqy
	 * @param 参数值,参数名称,参数值类型,是否必填,参数值长度,拥有参数个数（list长度）,路径前缀   
     * @return  
     * @Exception
     */
	public StringBuffer compagesQualityDetailView(String value,String name,String type,Boolean isReq,Integer valLan,int lan,String initPath)throws Exception{
		StringBuffer  str = new StringBuffer ();
		str.append("<li><label>"+name+"：</label>");
		if(this.enumService.getValueByConstant("base.qualityParam.paramValueType", "QUA_PARAM_VALUE_TYPE_01").equals(type)){/*参数值为字符*/
			str.append("<input type=\"text\" id=\"paramValue"+lan+"\" name=\"paramValue"+lan+"\" value=\""+value+"\" maxlength=\""+valLan+"\" class=\"userName ");
		}else if(this.enumService.getValueByConstant("base.qualityParam.paramValueType", "QUA_PARAM_VALUE_TYPE_02").equals(type)){/*参数值为整数*/
			str.append("<input type=\"text\" id=\"paramValue"+lan+"\" name=\"paramValue"+lan+"\" value=\""+value+"\" maxlength=\""+valLan+"\" class=\"digits ");
		}else if(this.enumService.getValueByConstant("base.qualityParam.paramValueType", "QUA_PARAM_VALUE_TYPE_03").equals(type)){/*参数值为含小数*/
			str.append("<input type=\"text\" id=\"paramValue"+lan+"\" name=\"paramValue"+lan+"\" value=\""+value+"\" maxlength=\""+valLan+"\" class=\"money ");
		}else if(this.enumService.getValueByConstant("base.qualityParam.paramValueType", "QUA_PARAM_VALUE_TYPE_04").equals(type)){/*参数值为日期*/
			str.append("<input type=\"text\" id=\"paramValue"+lan+"\" name=\"paramValue"+lan+"\" value=\""+value+"\" class=\"sysicon siDate date ");
		}else if(this.enumService.getValueByConstant("base.qualityParam.paramValueType", "QUA_PARAM_VALUE_TYPE_05").equals(type)){/*参数值为附件*/
			str.append("<input type=\"file\" id=\"paramValue"+lan+"\" name=\"paramValue"+lan+"\" class=\" ");
		}else if(this.enumService.getValueByConstant("base.qualityParam.paramValueType", "QUA_PARAM_VALUE_TYPE_06").equals(type)){/*参数值为图片*/
			str.append("<input type=\"file\" id=\"paramValue"+lan+"\" name=\"paramValue"+lan+"\" class=\" ");
		}
		if(isReq){//必填
			str.append(" required\"/><span class='eleRequired'>*</span>");
		}else
			str.append(" \"/>");
		/*有附件下载时*/
		if(this.enumService.getValueByConstant("base.qualityParam.paramValueType", "QUA_PARAM_VALUE_TYPE_05").equals(type) 
			|| this.enumService.getValueByConstant("base.qualityParam.paramValueType", "QUA_PARAM_VALUE_TYPE_06").equals(type)){
			if(value!=null){
				str.append("&nbsp;&nbsp;<a title='点击下载' id=\"downLoadFile"+lan+"\" href=\""+initPath+"/AttachmentController.do?method=downLoadFile&objId="+value.split(",")[1]+"\" target=\"_blank\">"+value.split(",")[0]+"</a>&nbsp;&nbsp;");		
				str.append("<a href=\"#\" title='点击删除' id=\"deleteFile"+lan+"\" onclick=\"qualityGradesEdit.removeUpfile('downLoadFile"+lan+",deleteFile"+lan+","+lan+"')\"><span class=\"sysicon siDelete\">删除</span></a>");
			}
		}
		str.append("</li>");
		return str;		
	}
	
	/** 
	 * Description : 取得机构信息 
	 * Create Date: 2010-8-2下午09:54:36 by yucy  Modified Date: 2010-8-2下午09:54:36 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getQualityOrgInfo(String objId) throws Exception {
		return orgInfoManager.getQualityOrgInfo(objId);
	}

	/** 
	 * Description :  审核资质信息
	 * Create Date: 2010-8-3上午01:23:35 by yucy  Modified Date: 2010-8-3上午01:23:35 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean saveAuditStatus(Map<String, Object> param) {
		String statu = (String)param.get("statu");
		Assert.notNull(statu);
		OrgQuality orgQuality = (OrgQuality)param.get("orgQuality");
		preVerify(orgQuality);
		
		if ("pass".equals(statu)) {//通过
			orgQuality.setAuditStatus(OrganizationEnum.PASS_EXAM);
			orgQuality.setUseStatus(OrganizationEnum.USE_VALID);
			
		}else{					   //不通过
			orgQuality.setAuditStatus(OrganizationEnum.NO_PASS_EXAM);
			orgQuality.setUseStatus(OrganizationEnum.USE_TEMP);
		}
		save(orgQuality);
		return true;
	}

	/** 
	 * Description :  取得以保存的资质（组织内部对象,包括新添加的参数）
	 * Create Date: 2010-8-30上午11:37:11 by yucy  Modified Date: 2010-8-30上午11:37:11 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getCreateOrUpdateOrgQuality(String parameter)throws Exception {
		return orgQualityDaoHibernate.getCreateOrUpdateOrgQuality(parameter);
	}

	/** 
	 * Description :  取得机构的资质(根据机构id)
	 * Create Date: 2009-4-8下午07:50:19 by yucy  Modified Date: 2009-4-8下午07:50:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<OrgQuality> getOrgQuality(String objId) throws Exception {
		return orgQualityDaoHibernate.getOrgQuality(objId);
	}
	
}
