<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

$(document).ready(function() {
 var objId = $("#expertInfoForm").find("input[id=objId]").val()
	
	//回填附件
	$.getJSON($("#initPath").val()+"/TrainingController.do?method=createOrUpdate&includedProperties=expertInfo&objId="+objId,{},function(json){
		var fileId = json.training.file;
		if(null!=fileId && ""!=fileId){
			$('#expInfoFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=file&isSelect=false&attachRelaId='+fileId);
		}
		json2ObjectSpan('expertInfoForm', json.training);
	});
	
	//提交
	$('button[id^=auditTrainingBtn]').click(function(){
		$('button[id^=auditTrainingBtn]').attr('disabled',true);
		var auditStatus = $(this).attr('id').replace('auditTrainingBtn_','');
		$.getJSON($('#initPath').val()+'/TrainingController.do?method=auditTraining',{'objIds':objId,'auditStatus':auditStatus},function(json){
			if(json.result)alert(json.result);if(json.failure){$('button[id^=auditTrainingBtn]').attr('disabled',false);return};
	 		if(json.success){
					alert('审核成功!');
					$('#conBody').loadPage($('#returnUrl').val());
	     	}
	 	});
	});
	
	// 返回
	$('#returnTrainingBtn').click(function(){
		$('#conBody').loadPage($('#returnUrl').val());
	});
})

</script>

<div class="partContainers formLayout form2Pa">
	<form id="expertInfoForm">
	<input type="hidden" id="objId" value="${param.objId }"/>
     	<ul>
     		<li class="fullLine">
	     		<label>专家姓名：</label>
	     			<span id="expertInfo.name"></span>
    	    </li>
	     	<li>
	     		<label>培训课程：</label>
	     			<span id="trainingCourse"></span>
    	    </li>
	     	<li>
	     		<label>培训机构：</label>
	     			<span id="trainingOrg"></span>
    	    </li>
	     	<li>
	     		<label>开始时间：</label>
	     			<span id="beginDate"></span>
    	    </li>
	     	<li>
	     		<label>结束时间：</label>
	     			<span id="endDate"></span>
    	    </li>
    	    <li class="formTextarea">
	     		<label>课程介绍：</label>
	     			<span id="courseMemo"></span>
			</li>
	     	<li class="fullLine">
	     		<label>证书附件：</label>
	     			<div id="expInfoFile" name="expInfoFile"  class="uploadFile"></div>
    	    </li>
    	</ul>
     </form>
        <div class="conOperation">
			<button type="button" id="auditTrainingBtn_02"><span>审核通过</span></button>
			<button type="button" id="auditTrainingBtn_03"><span>审核不通过</span></button>
			<button type="button" id="returnTrainingBtn"><span><spring:message code="globe.return"/></span></button>
		 </div>
 </div>
    	    