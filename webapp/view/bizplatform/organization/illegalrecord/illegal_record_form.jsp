<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
var IllegalRecForm={};

//选择机构
IllegalRecForm.selectOrg = function(){
	$.epsDialog({
		id:"selectOrg",
		title:"选择机构",
		url:$("#initPath").val()+"/view/bizplatform/organization/manager/select_orginfo.jsp?Hname=orgName&Hid=orgId"
	})
}

//保存或更新
IllegalRecForm.createOrSave = function(){
	if(!$('#IllegalRecForm').valid()){alert('请正确填写表单!');return;}
	$.getJSON($('#initPath').val()+'/IllegalRecController.do?method=save',
		formToJsonObject("IllegalRecForm"),
		function(json){
			if(json.success){
				alert("保存成功!");
				$("button[name=historyBackBtn]").click();
			}
		}
	);
}

$(document).ready(function(){
	
});
</script>


	<form:form id="IllegalRecForm" method="post" modelAttribute="illegalRec">
	<form:hidden path="objId"></form:hidden>
	<div id = "addIllegalRec" class="formLayout form1Pa">
	    <h4><span>违法信息</span></h4>
		<ul>
			<li>
				<label>标题：</label>
				<form:input path="title" cssClass="required"></form:input>
				<span class="eleRequired">*</span>
			</li>	
			<li>
				<label>违法机构：</label>
				<input style="width:250px;" type="text" id="orgName" name="org.orgName" value="${illegalRec.org.orgName }" class="required sysicon siSearch" onclick="IllegalRecForm.selectOrg();">
				<input type="hidden" id="orgId" name="org.objId" value="${illegalRec.org.objId }">
				<span class="eleRequired">*</span>
			</li>	
			<li>
				<label>是否显示：</label>
				<form:select path="isShow">
					<form:option value="true">是</form:option>
					<form:option value="false">否</form:option>
				</form:select>				
			</li>
			<li class="formTextarea">
				<label>违规内容：</label>
				<form:textarea path="reason" cssClass="required"/>
				<span class="eleRequired">*</span>
			</li>	
		</ul>
	</div>
	</form:form>
   	
   	<div class="conOperation center">
	  	<button id="IllegalRecSave" type="button" onclick="IllegalRecForm.createOrSave();" ><span><spring:message code="globe.save"/></span></button>
	  	<button name="historyBackBtn" type="button" ><span><spring:message code="globe.return"/></span></button>
	</div>
