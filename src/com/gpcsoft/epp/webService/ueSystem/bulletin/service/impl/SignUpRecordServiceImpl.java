package com.gpcsoft.epp.webService.ueSystem.bulletin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.signuprecord.domain.SignUpRespone;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.ApplyProjectDTO;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.SignUpResponeDTO;
import com.gpcsoft.epp.webService.ueSystem.bulletin.service.SignUpRecordService;

@Service 
public class SignUpRecordServiceImpl extends BaseGenericServiceImpl implements SignUpRecordService {

	@Autowired(required=true)@Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	@Autowired(required=true)@Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true)@Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	
	public boolean saveSignUpRecord(ApplyProjectDTO applyProjectDTO, List<SignUpResponeDTO> signUpResponeDTOList) throws Exception{
		SignUprecord signUprecord = new SignUprecord();
		List<SignUpRespone> signUpResponeList = new ArrayList<SignUpRespone>();
		
		Project project = projectManager.findProjectByProjCode(applyProjectDTO.getProjCode());
		if(project == null)
			return false;
		OrgInfo orgInfo = userApiService.getOrgInfoByOrgCode(applyProjectDTO.getOrgCode());
		if(orgInfo == null)
			return false;
		
		//值转换
		signUprecord.setProject(project);
		signUprecord.setSupplier(orgInfo);
		signUprecord.setApplyDate(DateUtil.parse(applyProjectDTO.getApplyDate(), "yyyy-MM-dd HH:mm:ss"));
		signUprecord.setLinker(applyProjectDTO.getLinkerMan());
		signUprecord.setLinkerTel(applyProjectDTO.getLinkTel());
		signUprecord.setIdCard(applyProjectDTO.getLinkerIdCard());
		signUprecord.setAddress(applyProjectDTO.getLinkAddress());
		signUprecord.setZipCode(applyProjectDTO.getZipCode());
		//TODO 未存储applyFile报名文件
		signUprecord.setMemo(applyProjectDTO.getMemo());
		
		//TODO 放入指标响应
		for(SignUpResponeDTO signUpResponeDTO : signUpResponeDTOList){
		}
		
		
		//保存
		signUprecordService.saveSignUprecordAndSignUpRespone(signUprecord, signUpResponeList,null);
		return false;
	}
	 
}
