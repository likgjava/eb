<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

$(document).ready(function() {
 var objId = $("#expertInfoForm").find("input[id=objId]").val()
	
	//回填附件
	$.getJSON($("#initPath").val()+"/ExperienceController.do?method=createOrUpdate&includedProperties=expertInfo&objId="+objId,{},function(json){
		json2ObjectSpan('expertInfoForm', json.experience);
	});
	
	// 返回
	$('#closeExperienceBtn').click(function(){
		$('#epsDialogClose').click();
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
				<button type="button" id="closeExperienceBtn"><span><spring:message code="globe.close"/></span></button>
		   </div>
 </div>
    	    