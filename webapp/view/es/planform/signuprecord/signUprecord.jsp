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
					<label class="short"for="project">招标名称：</label>
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
      				<li class="fullLine">
      				<label class="short"for="attachRelaId">报名附件：</label>
						<div id="attachRelaId" class="uploadFile" >${signUprecord.attachRelaId}</div>
					</li>
					<li>
					<label class="short"for="memo"><dm:out  value="local__BAILRECORD" tenderType="${project.tenderType}">保证金</dm:out>：</label>
					<span>${bailRecord.ballMoney}</span>
					</li>
						<li>
					<label class="short"for="memo"><dm:out  value="local__BAILRECORD" tenderType="${project.tenderType}">保证金</dm:out>状态：</label>
					<span>${bailRecord.bailStatusCN}</span>
					</li>
						<li>
					<label class="short"for="memo">缴纳时间：</label>
					<span><fmt:formatDate value="${bailRecord.renderTime}" pattern="yyyy-MM-dd"></fmt:formatDate></span>
					</li>
						<li>
					<label class="short"for="memo">退回时间：</label>
					<span><fmt:formatDate value="${bailRecord.returnedTime}" pattern="yyyy-MM-dd"></fmt:formatDate></span>
					</li>
						<li>
					<label class="short"for="memo">缴纳证明文件 ：</label>
					<div id="attachRelaId1" class="uploadFile" >${bailRecord.renderAtt}</div>
					</li>
						<li>
					<label class="short"for="memo">退回证明文件 ：</label>
					<div id="attachRelaId2" class="uploadFile" >${bailRecord.returnedAtt}</div>
					</li>
				</ul>
			</form>
	</div>
</div>
        <c:if test="${signUpResponeNum!='none'}">
		<div class="formLayout partContainers" id="signUpRespone">
		<table  border="0" class="tableList">
	            <thead>
	              <tr>
	                <th>指标名称</th>
					<th>说明</th>
					<th>报名响应</th>
					<th>响应附件</th>
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
	
	
	
	
	<!-- 是否为监管机构 -->
	<c:if test="${agency=='yes'}">
	<!-- 是否为待审核 -->
		<c:if test="${signUprecord.auditStatus=='00'}">
			<form id="signUprecordDetailForm" method="post">
			<input type="hidden" name="objId" id="objId" value="<c:out value="${signUprecord.objId}"/>"/>
			<div class="formLayout formPa">  
			<h5><span>审核意见：</span></h5>
			<ul>
				<li class="formTextarea fullLine">
					<textarea name="opinion" id="opinion"  class="maxInput" maxlength="200" style="width: 99%;">同意</textarea>
				</li>
			</ul>
	   	    </div>
			</form>
			<div class="conOperation">
	       	  	<button id="passButton" type="button" tabindex="18"><span>通过</span></button>
				<button id="noPassButton" type="button" tabindex="18"><span>不通过</span></button>
				<c:if test="${param.fromDiv == 'yes'}">
				<button id="supplierDetailReturnButton" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
				</c:if>
				<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
			</div>
		</c:if>
	</c:if>
	 
	 <div id="historyView"></div>
	 
<script language="javascript">
var supplierDetail = {};

$(document).ready(function(){

	$("#signUprecordDetailForm").validate();

	//附件
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text(),
		readOnly:'yes'
	});
	$('#attachRelaId2').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId2',//存放关联id的属性名
		attachRelaId:$("#attachRelaId2").text(),
		readOnly:'yes'
	});
	$('#attachRelaId1').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId1',//存放关联id的属性名
		attachRelaId:$("#attachRelaId1").text(),
		readOnly:'yes'
	});
	$("#supplierDetailReturnButton").click(function(){
		$("#epsDialogCloseNoReload").click();
	});
	
	//审核通过
	$("#passButton").click(function(){
		if($("#opinion").val()==''||$("#opinion").val()==null){
			$("#opinion").val('同意');
		}
		if(!$("#signUprecordDetailForm").valid())
		{
			alert("请正确填写表单！");
			return false;
		}else if(window.confirm("审核通过?"))
		{
			var json = {};
			json.opinion = $("#opinion").val();
			json.objId = $("#objId").val();
			$.getJSON($('#initPath').val()+'/SignUprecordController.do?method=auditSignUprecord&auditStatus=01',json, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$("#epsDialogCloseNoReload").click();
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			});
		}
	});
	//审核不通过
	$("#noPassButton").click(function(){
		//评审未通过且未填写意见
		if($("#opinion").val()==''||$("#opinion").val()==null||$("#opinion").val()=='同意')
		{
			$("#opinion").val('');
			alert("请填写审核意见");
			return false;
		}else if(!$("#signUprecordDetailForm").valid())
		{
			alert("请正确填写表单！");
			return false;
		}else if(window.confirm("确认审核不通过?"))
		{
			var json = {};
			json.opinion = $("#opinion").val();
			json.objId = $("#objId").val();
			$.getJSON($('#initPath').val()+'/SignUprecordController.do?method=auditSignUprecord&auditStatus=02',json, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$("#epsDialogCloseNoReload").click();
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			});
		}
	});
});


//操作历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=02');
});


</script>