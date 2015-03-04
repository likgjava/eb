<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
	<div class="formLayout form2Pa">  
			<h5><span>投标单位报名记录</span></h5>
			<ul>
				<li>
					<label class="short" for="Linker">联系人：</label>
					<span>${signUprecord.linker}</span>
				</li>
				<li>
					<label class="short" for="supplier">投标单位名称：</label>
					<span>${signUprecord.supplier.orgName}</span>
				</li>
				<li>
					<label class="short" for="project">招标名称：</label>
					<span>${signUprecord.project.projName}</span>
				</li>
				<li>
					<label class="short" for="applyDate">报名时间：</label>
					<span>${signUprecord.applyDate}</span>
				</li><li>
					<label class="short" for="idCard">身份证号：</label>
					<span>${signUprecord.idCard}</span>
				</li>
				<li>
					<label class="short" for="linkerTel">联系电话：</label>
					<span>${signUprecord.linkerTel}</span>
				</li>
				<li>
					<label class="short" for="address">联系地址：</label>
					<span>${signUprecord.address}</span>
				</li>
				<li>
					<label class="short" for="zipCode">邮编：</label>
					<span>${signUprecord.zipCode}</span>
				</li>
				<li>
					<label class="short" for="memo">备注：</label>
					<span>${signUprecord.memo}</span>
				</li>
				<li class="fullLine"><label class="short">附件：</label>
      					<span id="attachRelaId" class="uploadFile" >${signUprecord.attachRelaId}</span>
      			</li>
				</ul>
	</div>
	
	    <c:if test="${signUpResponeNum!='none'}">
	   		<div class="formLayout" id="signUpRespone">
	   			<ul>
					<table border="0" style="width: 98%;">
			            <thead>
			              <tr>
			                <th width="20%" style="text-align: center;">指标名称</th>
							<th width="20%" style="text-align: center;">说明</th>
							<th width="25%" style="text-align: center;">报名响应</th>
							<th width="25%" style="text-align: center;">响应附件</th>
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
			</ul>
		</div>
	 </c:if>
<div class="formLayout" id="signUpRespone" style="height: 73px;">
	<h5><span>审核意见</span></h5>
		<form id="signUprecordDetailForm" method="post">
		<ul>
			<li style="height:50px;">
			<input type="hidden" name="objId" id="objId" value="<c:out value="${signUprecord.objId}"/>"/>
				<textarea name="opinion" id="opinion" class="maxInput" maxlength="200" style="width: 99%;height: 45px;">同意</textarea>
				<span class="eleRequired"></span>
			</li>
		</ul>
	</form>
</div>
<div class="conOperation">
     <button id="passButton" type="button" tabindex="18"><span>通过</span></button>
	<button id="noPassButton" type="button" tabindex="18"><span>不通过</span></button>
	<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
</div>
<div id="historyView"></div>
<script language="javascript">
var supplierDetail = {};

$(document).ready(function(){
	$('#signUprecordDetailForm').validate();
	
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text(),
		readOnly:'yes'
	});
	if($('#form_one'))$('#form_one').remove();//移除附件form否则validator会出错
	//审核通过
	$("#passButton").click(function(){
		if($("#opinion").val()==''||$("#opinion").val()==null){
			$("#opinion").val("同意");
		}
		if(!$('#signUprecordDetailForm').valid()){
			alert("请正确填写表单！");return;
		}else if(window.confirm("确认提交?"))
		{
			$.getJSON($('#initPath').val()+'/SignUprecordController.do?method=auditSignUprecord&auditStatus=01', formToJsonObject('signUprecordDetailForm'), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$("#myDesktop").click();
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
		} else if(!$('#signUprecordDetailForm').valid()){
			alert("请正确填写表单！");return;
		}else if(window.confirm("确认提交?"))
		{
			$.getJSON($('#initPath').val()+'/SignUprecordController.do?method=auditSignUprecord&auditStatus=02', formToJsonObject('signUprecordDetailForm'), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$("#myDesktop").click();
			});
		}
	});
});


//操作历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=02');
});

</script>