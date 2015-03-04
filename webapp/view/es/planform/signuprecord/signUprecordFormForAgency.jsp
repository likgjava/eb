<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/signuprecord/signUprecordFormForAgency.js"></script>
<div class="partContainers">
<form id="signUprecordForm" method="post" enctype="multipart/form-data">
	<div style="width: auto;">
		<input type="hidden" name="project.objId" id="project.objId" value="${projectId}"/>
		<input type="hidden" value="01" name="signType" id="signType">
		<input type="hidden" name="isUploadFile" id="isUploadFile" value="false"></input>
		<!-- 隐藏数据，提前表单时默认审核状态为审核通过 -->
		<input type="hidden" name="auditStatus" id="auditStatus" value="01"/>
		<!-- 隐藏数据，提前表单时默认报名状态为已报名 -->
		<input type="hidden" name="applyStatus" id="applyStatus" value="00"/>
     	<table class="tableList" style="width:100%;">
     	<caption><dm:out value="local__signup" tenderType="${project.tenderType}">报名信息</dm:out></caption>
     		<tr>
     		<th>投标单位:</th>
     		<td><input type="text" name="supplierName" id="supplierName" class="required" value="" /><span class="eleRequired">*</span>
     		<input type="hidden" value="" name="supplier.objId" id="supplier.objId"/>
     		</td>
     		</tr>
     		<tr>
	     		<th><label for="linker">联系人：</label></th>
	     		<td><input type="text" name="linker" id="linker" class="required" value="" /><span class="eleRequired">*</span></td>
	     		<th><label for="idCard">身份证号：</label></th>    
	     		<td><input type="text" name="idCard" id="idCard" class="required cnIdCard" value="" /><span class="eleRequired">*</span></td>
     		</tr>
     		<tr>
	     		<th><label for="linkerTel">联系电话：</label></th>
	     		<td><input type="text" name="linkerTel" id="linkerTel" class="required cnPhone" value="" /><span class="eleRequired">*</span></td>
	     		<th><label for="address">联系地址：</label></th>    
	     		<td><input type="text" name="address" id="address" value="" class="required"/><span class="eleRequired">*</span></td>
     		</tr>
     		<tr>
	     		<th><label for="zipCode">邮编：</label></th>
	     		<td><input type="text" name="zipCode" id="zipCode" value="" class="required cnZipCode"/><span class="eleRequired">*</span></td>
	     		<th><label for="memo">备注：</label></th>    
	     		<td><input type="text" name="memo" id="memo" value="" /></td>
     		</tr>
     		<tr>
     		  <th><label>附件：</label></th>
     		  <td colspan="3"><div id="attachRelaId" class="uploadFile" >${attachRelaId}</div></td>
     		</tr>
     	</table>
    </div>
    <c:if test="${signUpResponeNum!='none'}">
		<div id="signUpRespone" style="width: auto;margin-top: 2px;">
			<table class="tableList" style="width:97%;">
				<caption>报名指标响应</caption>
	              <tr>
	                <th>指标名称</th>
					<th>说明</th>
					<th>报名响应</th>
					<th>响应附件</th>
	              </tr>
		            <c:set var="i" value="0"> </c:set>
		            <c:forEach items="${signUpResponeList}" var="signUpRespone">
		              <tr>
		                <td class="center">
			                <input type="hidden" id="i" >
			                <input type="hidden" id="signUpResponeId${i}" name="signUpResponeId${i}" value="${signUpRespone.objId}">
			                <input type="hidden" id="signUpCondFactorId${i}" name="signUpCondFactorId${i}" value="${signUpRespone.signUpCondFactor.objId}">
			              	<span id="factorName${i}">${signUpRespone.signUpCondFactor.factorName }</span>
		              	</td>
		                <td class="center"><textarea name='remark${i}' id='remark${i}' readonly="readonly">${signUpRespone.signUpCondFactor.remark }</textarea></td>
		                <td class="center"><textarea name='responseValue${i}' id='responseValue${i}' class="required" >${signUpRespone.responseValue}</textarea></td>
		                <td class="center">
		                	<input type="file" name="attachFile${i}" id="attachFile${i}" class="required" style="width: 120px;"/>
		                	<input type="hidden" name="isUploadFile${i}" value="false"/>
							<input type="hidden" id="attachRelaId${i}" name="attachRelaId${i}"  value="${signUpRespone.signUpResponeFile.objId}"/>
		                </td>
		              </tr>
		              <c:set var="i" value="${i+1}"></c:set>
		       		</c:forEach>
			  </table>
		</div>
	</c:if>
	<div class="conOperation">
		<button id="signUprecordSave" type="button" tabindex="18" ><span>保存</span></button>
		<c:if test="${close!=01}">
			<button id="signUprecordReturn" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
		</c:if>
	</div>
</form>
</div>