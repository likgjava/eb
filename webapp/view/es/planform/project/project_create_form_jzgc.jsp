<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<input type="hidden" id="isEndWorkTask" value="${isEndWorkTask}"></input>
	<form id="subprojectForm" method="post">
<div class="formLayout form2Pa">
	<h5><span>录入<dm:out value="local__package" tenderType="${param.tenderType}">标段</dm:out>信息</span></h5>
		<ul>
   			<li class="fullLine">
   				<label for="projCode"><dm:out value="local__package" tenderType="${param.tenderType}">标段</dm:out>编号：</label>
   				<input type="text" name="projCode" id="projCode" class="required" value="${subProject.projCode}" maxlength="3" onkeyup="subProjectForm.checkSubProjectName('code','projCode');"/>
   				<span class="eleRequired" id="warnNo">*</span>
   				<input type="hidden" name="parentId" id="parentId" value="${parentId}" />
   			</li>
   			<li class="fullLine">
   				<label for="projCode"><dm:out value="local__package" tenderType="${param.tenderType}">标段</dm:out>名称：</label>
   				<input type="text" name="projName" id="projName" class="required" value="${subProject.projName}" maxlength="10"  onkeyup="subProjectForm.checkSubProjectName('name','projName');"/>
   				<span class="eleRequired" id="warnName">*</span>
   			</li>
   			<li class="fullLine">
   				<label for="projCode"><dm:out value="local__package" tenderType="${param.tenderType}">投资</dm:out>金额（元）：</label>
   				<input type="text" name="budgetTotalMoney" id="budgetTotalMoney" class="required floats" maxlength="13" value="${subProject.budgetTotalMoney}"/>
   				<span class="eleRequired" id="warnName">*</span>
   			</li>
   			<li class="formTextarea">
   				<label for="projCode"><dm:out value="local__package" tenderType="${param.tenderType}">招标</dm:out>范围：</label>
   				<textarea rows="55" cols="55" name="zhaobiaoArea" id="zhaobiaoArea" class="required" maxlength="100">${subProject.zhaobiaoArea}</textarea>
   				<span class="eleRequired" id="warnName">*</span>
   			</li>
    		</ul>
				<!-- 隐藏数据用于判断 -->
			<input type="hidden" name="gloVar" id="gloVar" value=""/>
			<input type="hidden" name="objId" id="objId" value="${subProject.objId}"/>
			<input type="hidden" name="parentId" id="parentId" value="${subProject.parentId}"/>
			<input type="hidden" id="flagCodeId" value="" />
			<input type="hidden" id="flagNameId" value="" />
   			<div class="conOperation">
   				<button id="subProjectSaveButton" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
				<button id="subProjectReturn" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
 			</div>
		
</div>
    </form>

<script language="javascript">

var subProjectForm = {}

//提交
$('#subProjectSaveButton').click(function(){
	if(!$('#subprojectForm').valid()){alert('请准确填写表单！');return;}
		if ($('#flagNameId').val()=='false') {
			alert('该标段已存在！');
			return false;
		}
					  
		var jsonObj =  formToJsonObject('subprojectForm');
		if ($('#isEndWorkTask').val() == 'true') {
			var workFlowTaskId = $('[id=auditTaskId]').val();
			if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
				workFlowTaskId = $('[id=auditTask_Id]').val();
			}
			jsonObj.workFlowTaskId = workFlowTaskId;
			jsonObj.auditStatus = 'Y';
		}
		jsonObj.projectTaskId = $('#projectTaskId').val();
	  	$.getJSON($('#initPath').val()+'/ProjectController.do?method=saveSubProjectJZGC&ebuyMethod='+$('#ebuyMethod').val(),jsonObj, function(json){
		   	if(json.result)alert(json.result);if(json.failure)return;
		   	$('#epsDialogCloseReload').click();
		    if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			  } else {
				$("#myDesktop").click();
			}
		});	
});
//关闭
$("#subProjectReturn").click(function(){
	$('#epsDialogCloseNoReload').click();
});
 
	subProjectForm.checkSubProjectName = function(name,id){
		var idVal = $('#'+id).val();
		var parentId = $('#parentId').val();
		if (idVal!=null&&idVal!=''&&idVal!=undefined) {//判断是否有值
			if ('code'==name) {//编号
				var jsonObj = {};
				jsonObj.value = idVal;
				$.getJSON($('#initPath').val()+'/ProjectController.do?method=checkSubProject&key=code&projectId='+parentId,jsonObj,function(json){
				   	if(json.result)alert(json.result);if(json.failure)return;
					if (json.projectList.length!=''&&json.projectList.length!=null&&json.projectList.length!=undefined&&json.projectList.length>0) {
						$('#warnNo').empty().html('该标段已存在!');
						$('#flagCodeId').val('false');
					}else{
						$('#warnNo').empty();
						$('#flagCodeId').val('true');
					}
				});	
			}else{//名称
				var jsonObj = {};
				jsonObj.value = idVal;
				$.getJSON($('#initPath').val()+'/ProjectController.do?method=checkSubProject&key=name&projectId='+parentId,jsonObj, function(json){
				   	if(json.result)alert(json.result);if(json.failure)return;
				   	if (json.projectList.length!=''&&json.projectList.length!=null&&json.projectList.length!=undefined&&json.projectList.length>0) {
						$('#warnName').empty().html('该标段已存在!');
						$('#flagNameId').val('false');
					}else{
						$('#warnName').empty();
						$('#flagNameId').val('true');
					}
				});	
			}
		}

	}
</script>