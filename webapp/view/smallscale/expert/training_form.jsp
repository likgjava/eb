<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

$(document).ready(function() {

	$("#beginDate").epsDatepicker({applyRule: endRule  });
	$("#endDate").epsDatepicker({applyRule: startRule  });

	//表单验证
	$("#expertInfoForm").validate();	
	
	//回填附件
	var fileId = $('#expertInfoForm').find('input[id=expInfoFileId]').val();
	if(null!=fileId&&""!=fileId){
		$('#expInfoFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=file&attachRelaId='+fileId);
	}
	else{
		$('#expInfoFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=file');
	}
	// 保存专家培训信息
	$('button[id^=trainingBtn]').click(function() {
		if(!$('#expertInfoForm').valid()){alert('请正确填写表单!');return;}
		$('button[id^=trainingBtn]').attr("disabled",true);
		var training = formToJsonObject('expertInfoForm');
		training.saveType = $(this).attr('id').replace("trainingBtn_","");
		$.getJSON($("#initPath").val()+"/TrainingController.do?method=saveTraining",training,function(json){
			if(json.failure){alert(json.result);$('button[id^=trainingBtn]').attr("disabled",false);return;}
			if(json.success){
				if(training.saveType =="submit"){
					alert("已提交审核！");
				}else{
					alert("保存成功！");
				}
				$('#conBody').loadPage($("#returnUrl").val());
			}
		});
	})
	
	// 返回
	$('#returnTrainingBtn').click(function(){
		$('#conBody').loadPage($('#returnUrl').val());
	});
})

</script>

<div class="partContainers formLayout form2Pa">
	<form:form id="expertInfoForm" method="post" modelAttribute="training">
	<form:hidden path="objId"></form:hidden>
	<form:hidden path="expertInfo.objId"></form:hidden>
	<input type="hidden" id="expInfoFileId" value="${training.file}"/>
     	<ul>
	     	<li class="">
	     		<label>培训课程：</label>
	     			<form:input path="trainingCourse" cssClass="required" maxlength="25"></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label>培训机构：</label>
	     			<form:input path="trainingOrg" cssClass="required" maxlength="50"></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label>开始时间：</label>
	     			<form:input path="beginDate" cssClass="required" ></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label>结束时间：</label>
	     			<form:input path="endDate" cssClass="required" ></form:input><span class="eleRequired">*</span>
    	    </li>
    	    <li class="formTextarea">
	     		<label>课程介绍：</label>
	     			<textarea name="courseMemo" cols="60" rows="8" maxlength="2000">${training.courseMemo}</textarea>
			</li>
	     	<li class="fullLine">
	     		<label>证书附件：</label>
	     			<div id="expInfoFile" name="expInfoFile"  class="uploadFile"></div>
    	    </li>
	     	
    	</ul>
     </form:form>
        <div class="conOperation">
				<button type="button" id="trainingBtn_save"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="trainingBtn_submit"><span>提交</span></button>
				<button type="button" id="returnTrainingBtn"><span><spring:message code="globe.return"/></span></button>
		   </div>
 </div>
    	    