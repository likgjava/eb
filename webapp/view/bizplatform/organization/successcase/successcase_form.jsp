<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" >
var successCaseForm={};

$(document).ready(function(){
	$('#successCaseForm').validate();
	$("#startTime").epsDatepicker({timeShow:true});
	$("#endTime").epsDatepicker({timeShow:true});

	//返回
	$('#successCaseReturnBtn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/SuccessCaseController.do");
	});
	//保存或提交
	$('button[id^=successCaseSaveBtn_]').click(function(){
		if(!$('#successCaseForm').valid()){alert('请正确填写表单!');return;}
		var jsonObj = formToJsonObject('successCaseForm');
		jsonObj.auditStatus = "00";
		jsonObj.useStatus = "00";
		// 如果是提交，则修改审核状态为01
		if($(this).attr("id").replace("successCaseSaveBtn_","")=="submit") {
			if(!confirm("确认提交？")){return;}
			jsonObj.auditStatus = "01";
		}
		$.getJSON($('#initPath').val()+'/SuccessCaseController.do?method=save',jsonObj , function(json){
			if(json.failure) {
				alert(json.result);
				return;
			}
			$('#conBody').loadPage($('#initPath').val()+'/SuccessCaseController.do');
		});
	});
	$('#description').blur(function(){
		if($(this).val().length > 2000){
			$('#description').val($(this).val().substring(0,2000));
		}
	});

	/*弹出品目列表的层*/
	$('#categoryNames').click(function(){
		$.epsDialog({
			title:'选择采购内容',
			url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=categoryIds&NAMES=categoryNames&className=PurCategory&action=listTop&isCheckBox=true&childNodeOnly=true&checkStatus=true&checkValues='+$("input[id=purCategoryIds.objId]").val()
		}); 
	});
});

</script>
<div class="formLayout form2Pa">
	<form:form id="successCaseForm" method="post" modelAttribute="successCase">
		<form:hidden path="objId"></form:hidden>
     	<h4><span>案例信息</span></h4>
     	<ul>
	     	<li class="fullLine">
	     		<label for="successCaseForm.projectName"><spring:message code="successCaseForm.projectName"/>：</label>
				<form:input path="projectName" id="projectName" cssClass="required" size="60" maxlength="25"></form:input>
    	   		<span class="eleRequired">*</span>
    	    </li>
	     	<li class="fullLine">
	     		<label for="successCaseForm.startTime"><spring:message code="successCaseForm.startTime"/>：</label>
				<form:input path="startTime" id="startTime" cssClass="required"></form:input>
    	   		<span class="eleRequired">*</span>
    	    </li>
	     	<li class="fullLine">
	     		<label for="successCaseForm.endTime"><spring:message code="successCaseForm.endTime"/>：</label>
				<form:input path="endTime" id="endTime" cssClass="required"></form:input>
    	   		<span class="eleRequired">*</span>
    	    </li>
	     	<li class="fullLine">
	     		<label for="successCaseForm.categoryNames">采购内容：</label>
				<form:hidden path="categoryIds" />
				<form:input path="categoryNames" cssClass="required sysicon siSearch" size="60" maxlength="500"></form:input>
    	   		<span class="eleRequired">*</span>
    	    </li>
	     	<li class="formTextarea">
	     		<label for="successCaseForm.description"><spring:message code="successCaseForm.description"/>：</label>
				<form:textarea path="description" />
    	   		<span class="eleRequired"></span>
    	    </li>
    	    
    	    <c:if test="${successCase.opinion!=null}">
    	    <li class="formTextarea">
	     		<label>审核意见：</label>
				<span>${successCase.opinion}(${successCase.auditStatusCN })</span>
    	   	</li>
    	    </c:if>
		</ul>
		<div class="conOperation">
			<button type="button" id="successCaseSaveBtn_save"><span><spring:message code="globe.save"/></span></button>
			<button type="button" id="successCaseSaveBtn_submit"><span>提交</span></button>
			<button type="button" id="successCaseReturnBtn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
		</div>
	</form:form>
</div>