<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers">
<div class="formLayout form2Pa">  
<form id="signUprecordDetailForm" method="post">
			<h5><span>投标单位报名记录</span></h5>
				<ul>
				<li>
					<label class="short"for="supplier">投标单位名称：</label>
					<span>${signUprecord.supplier.orgName}</span>
				</li>
				<li>
					<label class="short"for="project">项目(<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>)名称：</label>
					<span>${signUprecord.project.projName}</span>
				</li>
				<li>
					<label class="short"for="applyDate">报名时间：</label>
					<span><fmt:formatDate value="${signUprecord.applyDate}" pattern="yyyy-MM-dd"></fmt:formatDate></span>
				</li>
					<li>
					<label class="short"for="linker">联系人：</label>
					<span>${signUprecord.linker}</span>
					</li>
					
					<li>
						<label class="short"for="idCard">身份证号：</label>
						<span>${signUprecord.idCard}</span>
					</li>
					<li>
						<label class="short"for="linkerTel">联系电话：</label>
						<span>${signUprecord.linkerTel}</span>
					</li>
					<li>
						<label class="short"for="address">联系地址：</label>
						<span>${signUprecord.address}</span>
					</li>
					<li>
						<label class="short"for="zipCode">邮编：</label>
						<span>${signUprecord.zipCode}</span>
					</li>
					<li>
					<label class="short"for="memo">备注：</label>
					<span>${signUprecord.memo}</span>
					</li>
					<li>
					<label class="short"for="memo">状态：</label>
					<span>${signUprecord.auditStatusCN}</span>
					</li>
						<li class="fullLine" >
							<label class="short">附件：</label>
						<span id="attachRelaId" class="uploadFile" >${signUprecord.attachRelaId}</span>
					</li>
				</ul>
</form>
</div>
</div>
    <c:if test="${signUpResponeNum!='none'}">
		<div class="formLayout partContainers" id="signUpRespone">
		<table border="0">
	            <thead>
	              <tr>
	                <th style="text-align: center;">指标名称</th>
					<th style="text-align: center;">说明</th>
					<th style="text-align: center;">报名响应</th>
					<th style="text-align: center;">响应附件</th>
	              </tr>
	            </thead>
	            <tbody >
	            <c:set var="i" value="0"> </c:set>
	            	<c:forEach items="${signUpResponeList}" var="signUpRespone">
	              <tr>
	                 <td class="center"><input type="hidden" id="signUpResponeId${i}" name="signUpResponeId${i}" value="${signUpRespone.objId}">
	                <input type="hidden" id="signUpCondFactorId${i}" name="signUpCondFactorId${i}" value="${signUpRespone.signUpCondFactor.objId}">
	               <span id="factorName${i}">${signUpRespone.signUpCondFactor.factorName }</span></td>
	                <td class="center"><textarea name='remark${i}' id='remark${i}' readonly="readonly">${signUpRespone.signUpCondFactor.remark }</textarea></td>
	                <td class="center"><textarea name='responseValue${i}' id='responseValue${i}' readonly="readonly">${signUpRespone.responseValue}</textarea></td>
	                <td class="center" colspan="3">
	                    <c:if test='${null != signUpRespone.signUpResponeFile.objId && "" != signUpRespone.signUpResponeFile.objId}'>
							<a href="<%=request.getContextPath()%>/AttachmentController.do?method=downLoadFile&objId=${signUpRespone.signUpResponeFile.objId}">${signUpRespone.signUpResponeFile.viewName}</a>
						</c:if>
 	                </td>
	                
	              </tr>
	              <c:set var="i" value="${i+1}"></c:set>
	              </c:forEach>
	            </tbody>
		  </table>
		</div>
	</c:if>
		 
	<c:if test="${param.fromDiv == 'yes'}">
		<div id="supplierDetailsaveButtonDiv" class="conOperation">									
				<button id="supplierDetailReturnButton" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
		</div>
	</c:if>
		 
<script language="javascript">
var supplierDetail = {};

$(document).ready(function(){
	 //附件
	if($('#auditStatusId').val()=='02'){
		$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
			defineSelf:'attachRelaId',//存放关联id的属性名
			attachRelaId:$("#attachRelaId").text()
		});
	}else{
		$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
			defineSelf:'attachRelaId',//存放关联id的属性名
			attachRelaId:$("#attachRelaId").text(),
			readOnly:'yes'
		});
	}
	 $("#supplierDetailReturnButton").click(function(){
			$("#epsDialogCloseNoReload").click();
		});
});


</script>