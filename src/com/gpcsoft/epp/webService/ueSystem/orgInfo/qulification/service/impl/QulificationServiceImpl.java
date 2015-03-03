package com.gpcsoft.epp.webService.ueSystem.orgInfo.qulification.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.qualitymanagement.manager.QualificationDefineManager;
import com.gpcsoft.bizplatform.base.qualitymanagement.manager.QualificationParamManager;
import com.gpcsoft.bizplatform.organization.manager.OrgQualityManager;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.webService.ueSystem.common.dto.response.LoginDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.qulification.dto.MaintainDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.qulification.dto.QualificationDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.qulification.service.QulificationService;

@SuppressWarnings("unchecked")
@Service
public class QulificationServiceImpl extends BaseGenericServiceImpl implements QulificationService{
	@Autowired(required=true) @Qualifier("qualificationDefineManagerImpl")
	private QualificationDefineManager qualificationDefineManagerImpl;

	@Autowired(required=true) @Qualifier("qualificationParamManagerImpl")
	private QualificationParamManager qualificationParamManagerImpl;
	
	@Autowired(required=true) @Qualifier("orgQualityManagerImpl")
	private OrgQualityManager orgQualityManagerImpl;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	
	public LoginDTO updateQulification(String userCode, String orgCode,
			MaintainDTO maintainDTO) {
		LoginDTO loginDTO = new LoginDTO();
		
		List<QualificationDTO> qualificationDTOList = maintainDTO.getBody().getQualificationList();
		try {
			/*for(QualificationDTO qualificationDTO : qualificationDTOList){
				OrgQuality orgQuality = new OrgQuality();
				
				orgQuality.setOrg(userApiService.getOrgInfoByOrgCode(orgCode));
				
				QualificationDefine qualificationDefine = qualificationDefineManagerImpl.getQualificationDefineByCode(qualificationDTO.getQualificationCode());
				orgQuality.setQualificationDefine(qualificationDefine);
				
				Set<QualificationDetail> qualificationDetailSet = new HashSet<QualificationDetail>();
				
				List<AttributeDTO> attributeDTOList = qualificationDTO.getAttributeList();
				for(AttributeDTO attributeDTO : attributeDTOList){
					QualificationDetail qualificationDetail = new QualificationDetail();
					QualificationParam qualificationParam = qualificationParamManagerImpl.getQualificationParamByCode(attributeDTO.getCode());
					
					qualificationDetail.setQualityParam(qualificationParam);
					
					qualificationDetailSet.add(qualificationDetail);
				}
				
				orgQuality.setQualificationDetailSet(qualificationDetailSet);
				orgQualityManagerImpl.save(orgQuality);
			}*/
		} catch (Exception e) {
			logger.error(e.getMessage());
			loginDTO.getHeader().setOperTag("N");
			loginDTO.getHeader().setOperTag(e.getMessage());
		}

		
		return loginDTO;
	}
}
