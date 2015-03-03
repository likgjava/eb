<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/bargin/project/bargain/modify_project_other_info.js"></script>

<form:form id="BargainProjectCreateForm" method="post" modelAttribute="payInfo">
	<input type="hidden" name="project.objId" id="projId" value="${projId}" />
	<input type="hidden" name="payInfo.objId" id="payInfoId" value="${payInfo.objId}" />
	<input type="hidden" name="contactInfo.objId" id="contactInfoId" value="${contactInfo.objId}" />
	<input type="hidden" name="companyId" id="companyId" value="${companyId}" />
	<input type="hidden" name="currentEmpId" id="currentEmpId" value="${currentEmpId}" />
	<input type="hidden" name="signInfo.objId" id="signInfoId" value="${signInfo.objId}" />

	<div class="formLayout form2Pa">
		<h4>支付方式信息</h4>
		<ul>
			<li class="fullLine">
				<label>交货日期：</label>
				<input type="text" name="deliveryDate" id="deliveryDate" class="required" value="${payInfo.deliveryDate}" maxlength="100" size="50"/>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>交货地点：</label>
				<input type="text" name="deliveryAddress" id="deliveryAddress" class="required" value="${payInfo.deliveryAddress}" maxlength="100" size="50"/>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>交货方式：</label>
				<html:select selectedValue="${payInfo.deliveryType}" styleClass="required" id="deliveryType" name="deliveryType" code="biz.project.deliveryType"></html:select>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>付款方式：</label>
				<html:select selectedValue="${payInfo.payType}" styleClass="required" id="payType" name="payType" code="biz.project.payType"></html:select>
				<span class="eleRequired">*</span>
			</li>
			<li class="formTextarea">
				<label>其他补充说明：</label>
				<textarea name="supplement" id="supplement" maxlength="500" style="width:370px">${payInfo.supplement}</textarea>
			</li>
		</ul>
		
		<h4>联系方式信息</h4>
		<ul>
			<li class="fullLine">
				<label>联系人：</label>
				<input type="text" name="linker" id="linker" class="required" value="<c:if test="${contactInfo.objId==null || contactInfo.objId==''}">${contactName}</c:if><c:if test="${contactInfo.objId!=null || contactInfo.objId!=''}">${contactInfo.linker}</c:if>" maxlength="20" size="50"/>
				<span class="eleRequired">*</span>
				<a href="javascript:void(0);" onclick="BargainProjectCreateForm.chooseLinker();return false;" name="chooseLinker">点击选择联系人</a>
			</li>
			<li class="fullLine">
				<label>移动电话：</label>
				<input type="text" name="mobilePhone" id="mobilePhone" value="<c:if test="${contactInfo.objId==null || contactInfo.objId==''}">${contact.mobilePhone}</c:if><c:if test="${contactInfo.objId!=null || contactInfo.objId!=''}">${contactInfo.mobilePhone}</c:if>" class="required cnMobile" maxlength="11" size="50"/>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>固定电话：</label>
				<input type="text" id="fixedTelephone" name="fixedTelephone" value="<c:if test="${contactInfo.objId==null || contactInfo.objId==''}">${contact.tel}</c:if><c:if test="${contactInfo.objId!=null || contactInfo.objId!=''}">${contactInfo.fixedTelephone}</c:if>" maxlength="20" class="cnPhone required" size="50"/>
			</li>
			<li class="fullLine">
				<label>传真：</label>
				<input type="text" name="fax" id="fax" class="cnPhone required" value="<c:if test="${contactInfo.objId==null || contactInfo.objId==''}">${contact.fax}</c:if><c:if test="${contactInfo.objId!=null || contactInfo.objId!=''}">${contactInfo.fax}</c:if>" maxlength="20" size="50"/>
			</li>
			<li class="fullLine">
				<label>邮编：</label>
				<input type="text" name="postCode" id="postCode" class="cnZipCode" value="<c:if test="${contactInfo.objId==null || contactInfo.objId==''}">${contact.postCode}</c:if><c:if test="${contactInfo.objId!=null || contactInfo.objId!=''}">${contactInfo.postCode}</c:if>" maxlength="6" size="50"/>
			</li>
			<li class="formTextarea">
				<label>地址：</label>
				<textarea name="address" id="address" maxlength="100" class="required" style="width: 370px;"><c:if test="${contactInfo.objId==null || contactInfo.objId==''}">${contact.address}</c:if><c:if test="${contactInfo.objId!=null || contactInfo.objId!=''}">${contactInfo.address}</c:if></textarea>
			</li>
		</ul>
		
		<h4>供应商资质信息</h4>
		<ul>
			<li class="formTextarea">
				<label>企业资质：</label>
				<textarea name="companyQualification" id="companyQualification" class="required" maxlength="100" style="width:370px">${signInfo.companyQualification}</textarea>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>附加语：</label>
				<input type="text" name="additionalMemo" id="additionalMemo" value="${signInfo.additionalMemo}" maxlength="100" size="50"/>
			</li>
			
			<li class="fullLine">
				<label>评审规则文件：</label>
				<span>
				<input name="chooseFile" value="choose" onchange="BargainProjectCreateForm.chooseFileChange();" id="chooseFromSys" type="radio"  checked="checked"/>
				<span id="assessmentFile_name"><c:if test="${signInfo.assessmentFile!=null}"><a href="AttachmentController.do?method=downLoadFile&objId=${signInfo.assessmentFile.objId}">${signInfo.assessmentFile.viewName}</a></c:if></span>
				<input type="hidden" id="assessmentFile.objId" name="assessmentFile.objId" value="${signInfo.assessmentFile.objId}"/>&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="BargainProjectCreateForm.chooseRuleFile();">>>点击从已有的文件中选择</a><br/>
				<input name="chooseFile" value="upload" onchange="BargainProjectCreateForm.chooseFileChange();" id="chooseUpload" type="radio"/>上传一个评审规则<input type="file" id="assessment_File" name="assessment_File" disabled="disabled"/>
				</span>
			</li>
		</ul>
	</div>
		
	<div class="conOperation">
		<button id="save" type="button"><span>保存</span></button>
	</div>
</form:form>
