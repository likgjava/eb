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
	
	// 返回
	$('#closeTrainingBtn').click(function(){
		$('#epsDialogClose').click();
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
    	    <li class="fullLine">
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
				<button type="button" id="closeTrainingBtn"><span><spring:message code="globe.close"/></span></button>
		   </div>
 </div>
    	    