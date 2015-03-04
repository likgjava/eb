<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/signuprecord/signUprecordForm.js"></script>
	<div class="formLayout form2Pa">
     	<caption><dm:out value="local__signup" tenderType="${project.tenderType}">报名信息</dm:out></caption>
     		<ul>
     	<input type="hidden" value="${isDividePack }" name="isDividePack" id="isDividePack">
     	<form id="signUprecordForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="maxSize" value="10240"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="project.objId" id="project.objId" value="${projectId}"/>
		<input type="hidden" value="01" name="signType" id="signType">
		<input type="hidden" value="" name="subPrjIds" id="subPrjIds">
		<input type="hidden" name="isUploadFile" id="isUploadFile" value="false"></input>
		<input type="hidden" name="fromType" id="fromType" value="${fromType}"/>
		<!-- 隐藏数据，提前表单时默认审核状态为待审核 -->
		<input type="hidden" name="auditStatus" id="auditStatus" value="00"/>
		<!-- 隐藏数据，提前表单时默认报名状态为已报名 -->
		<input type="hidden" name="applyStatus" id="applyStatus" value="00"/>
				<c:if test="${isDividePack == true}">
				<li class="fullLine"><label style="width: 90px;"   class="short">选择<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>：</label>
	     		<c:forEach items="${subPrjList}" var="pack">
	     			<input type="checkbox" name="sub" value="${pack.objId}" id="sub" >${pack.projName}
	     		</c:forEach>
	     		<span class="eleRequired">*</span></li>
	     		</c:if>
	     	<li><label style="width: 90px;" class="short">联&nbsp;系&nbsp;人&nbsp;：</label>
	     		<input type="text" name="linker" id="linker" style="width: 130px;"  class="required" value="${linker.name}" />
	     		<span class="eleRequired">*</span>
	     	</li>
	     	<li><label style="width: 90px;"   class="short">联系电话：</label>
	     		<input type="text" name="linkerTel" id="linkerTel" style="width: 130px;" class="required cnPhone" value="${linker.mobile}" /><span class="eleRequired">*</span>
	     	</li>
	     		
     		<li>
	     		<label style="width: 90px;"   class="short">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编：</label>
	     		<input type="text" name="zipCode" id="zipCode" style="width: 130px;"  value="${company.postCode}" class="required cnZipCode"/><span class="eleRequired">*</span>
	     	</li>
	     	<li><label style="width: 90px;"  style="width: 90px;"   class="short">身份证号：</label>
	     		<input type="text" name="idCard" id="idCard" style="width: 130px;" class="required cnIdCard" value="${linker.idCard}" style="width: 160px;"/><span class="eleRequired">*</span>
	     	</li>
	     	<li class="fullLine"><label style="width: 90px;"   class="short">联系地址：</label>  
	     		<input type="text" name="address" id="address" value="${company.address}" style="width: 480px;" size="74"/>
	     	</li>
	     	<li class="formTextarea" style="height:90px;">
	     		<label style="width: 90px;"   class="short">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
	     		<textarea rows="20" cols="20" style="width:480px;" id="memo" name="memo" value="" class="maxInput" maxlength="25" ></textarea>
	     	</li>
	     	<!-- 报名响应指标Start -->
		<c:if test="${signUpResponeNum!='none'}">
		<div id="signUpRespone" style="width: auto;margin-top: 2px;">
			<table class="tableList" style="width:100%;">
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
	<!-- 报名响应指标添加 -->
	     	 </form>
     		<li class="fullLine" > 
     		<label class="short" style="width: 90px;">附件：</label>
     		  <div class="uploadFile" id="attachRelaId"></div>
     		 </li>
     		</ul>
	<div class="conOperation">
		<button id="signUprecordSave" type="button" tabindex="18"><span>提交</span></button>
		<c:if test="${close!=01}">
			<button id="signUprecordReturn" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
		</c:if>
	</div>
</div>