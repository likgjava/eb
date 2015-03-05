<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

$(document).ready(function() {
 var objId = $("#expertInfoForm").find("input[id=objId]").val()
	
	//回填附件
	$.getJSON($("#initPath").val()+"/ExperienceController.do?method=createOrUpdate&includedProperties=expertInfo&objId="+objId,{},function(json){
		json2ObjectSpan('expertInfoForm', json.experience);
	});
	
	//提交
	$('button[id^=auditExperienceBtn]').click(function(){
		$('button[id^=auditExperienceBtn]').attr('disabled',true);
		var auditStatus = $(this).attr('id').replace('auditExperienceBtn_','');
		$.getJSON($('#initPath').val()+'/ExperienceController.do?method=auditExperience',{'objIds':objId,'auditStatus':auditStatus},function(json){
			if(json.result)alert(json.result);if(json.failure){$('button[id^=auditExperienceBtn]').attr('disabled',false);return};
	 		if(json.success){
					alert('审核成功!');
					$('#conBody').loadPage($('#returnUrl').val());
	     	}
	 	});
	});
	
	// 返回
	$('#returnExperienceBtn').click(function(){
		$('#conBody').loadPage($('#returnUrl').val());
	});
})

</script>

<div class="partContainers formLayout form2Pa">
	<form id="expertInfoForm">
	<input type="hidden" id="objId" value="${param.objId }"/>
     	<ul>
     		<li>
	     		<label>专家姓名：</label>
	     			<span id="expertInfo.name"></span>
    	    </li>
	     	<li>
	     		<label>工作单位：</label>
	     			<span id="orgName"></span>
    	    </li>
	     	<li>
	     		<label>职业：</label>
	     			<span id="specialty"></span>
    	    </li>
	     	<li>
	     		<label>职务：</label>
	     			<span id="duty"></span>
    	    </li>
	     	<li>
	     		<label>开始时间：</label>
	     			<span id="startTime"></span>
    	    </li>
	     	<li>
	     		<label>结束时间：</label>
	     			<span id="endTime" ></span>
    	    </li>
	     	<li class="fullLine">
	     		<label>成就描述：</label>
	     			<span id="achievement"></span>
			</li>
    	</ul>
     </form>
        <div class="conOperation">
			<button type="button" id="auditExperienceBtn_02"><span>审核通过</span></button>
			<button type="button" id="auditExperienceBtn_03"><span>审核不通过</span></button>
			<button type="button" id="returnExperienceBtn"><span><spring:message code="globe.return"/></span></button>
		 </div>
 </div>
    	    