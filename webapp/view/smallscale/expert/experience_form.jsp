<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

$(document).ready(function() {

	$("#startTime").epsDatepicker({applyRule: endRule });
	$("#endTime").epsDatepicker({applyRule: startRule });

	//表单验证
	$("#expertInfoForm").validate();	
	
	// 保存专家任职经历
	$('button[id^=experienceBtn]').click(function() {
		if(!$('#expertInfoForm').valid()){alert('请正确填写表单!');return;}
		$('button[id^=experienceBtn]').attr("disabled",true);
		var experience = formToJsonObject('expertInfoForm');
		experience.saveType = $(this).attr('id').replace("experienceBtn_","");
		$.getJSON($("#initPath").val()+"/ExperienceController.do?method=saveExperience",experience,function(json){
			if(json.failure){alert(json.result);$('button[id^=experienceBtn]').attr("disabled",false);return;}
			if(json.success){
				if(experience.saveType =="submit"){
					alert("已提交审核！");
				}else{
					alert("保存成功！");
				}
				$('#conBody').loadPage($("#returnUrl").val());
			}
		});
	})
	
	// 返回
	$('#returnExperienceBtn').click(function(){
		$('#conBody').loadPage($('#returnUrl').val());
	});
})

</script>

<div class="partContainers formLayout form2Pa">
	<form:form id="expertInfoForm" method="post" modelAttribute="experience">
	<form:hidden path="objId"></form:hidden>
	<form:hidden path="expertInfo.objId"></form:hidden>
     	<ul>
	     	<li class="fullLine">
	     		<label>工作单位：</label>
	     			<form:input path="orgName" cssClass="required" maxlength="50"></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label>职业：</label>
	     			<form:input path="specialty" cssClass="required" maxlength="50"></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label>职务：</label>
	     			<form:input path="duty" cssClass="required" maxlength="50"></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label>开始时间：</label>
	     			<form:input path="startTime" cssClass="required" ></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label>结束时间：</label>
	     			<form:input path="endTime" cssClass="required" ></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li class="formTextarea">
	     		<label>成就描述：</label>
	     			<textarea name="achievement" cols="60" rows="8" maxlength="2000">${experience.achievement}</textarea>
			</li>
	     	
    	</ul>
     </form:form>
        <div class="conOperation">
				<button type="button" id="experienceBtn_save"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="experienceBtn_submit"><span>提交</span></button>
				<button type="button" id="returnExperienceBtn"><span><spring:message code="globe.return"/></span></button>
		   </div>
 </div>
    	    