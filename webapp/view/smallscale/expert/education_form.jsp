<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

$(document).ready(function() {

	$("#enrollDate").epsDatepicker({applyRule: endRule });
	$("#graduateDate").epsDatepicker({applyRule: startRule });

	//表单验证
	$("#expertInfoForm").validate();	

	// 专业
	var specialityList=$.ajax({ url: $('#initPath').val()+"/DictionaryController.do?method=getObjectQuery&queryColumns=objId,dicValue,dicName",data:{"dicType.groupName":"speciality"}, async: false }).responseText
	var specialityJosn =JSON.parse(specialityList).result;
	$.each(specialityJosn,function(i,n){
		if($('#speciality').val() == n.objId){
			$("select[name=speciality.objId]").append("<option value='"+n.objId+"' selected='selected'>"+n.dicName+"</option>")
		}else{
			$("select[name=speciality.objId]").append("<option value='"+n.objId+"'>"+n.dicName+"</option>")
		}
	})
	
	// 学历
	var specialityList=$.ajax({ url: $('#initPath').val()+"/DictionaryController.do?method=getObjectQuery&queryColumns=objId,dicValue,dicName",data:{"dicType.groupName":"degree"}, async: false }).responseText
	var specialityJosn =JSON.parse(specialityList).result;
	$.each(specialityJosn,function(i,n){
		if($('#degree').val() == n.objId){
			$("select[name=degree.objId]").append("<option value='"+n.objId+"' selected='selected'>"+n.dicName+"</option>")
		}else {
			$("select[name=degree.objId]").append("<option value='"+n.objId+"'>"+n.dicName+"</option>")
		}
	})
	
	//回填附件
	var fileId = $('#expertInfoForm').find('input[id=expInfoFileId]').val();
	if(null!=fileId&&""!=fileId){
		$('#expInfoFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=file&attachRelaId='+fileId);
	}
	else{
		$('#expInfoFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=file');
	}
	// 保存专家教育经历信息
	$('button[id^=educationBtn]').click(function() {
		if(!$('#expertInfoForm').valid()){alert('请正确填写表单!');return;}
		$('button[id^=educationBtn]').attr("disabled",true);
		var education = formToJsonObject('expertInfoForm');
		education.saveType = $(this).attr('id').replace("educationBtn_","");
		$.getJSON($("#initPath").val()+"/EducationController.do?method=saveEducation",education,function(json){
			if(json.failure){alert(json.result);$('button[id^=educationBtn]').attr("disabled",false);return;}
			if(json.success){
				if(education.saveType =="submit"){
					alert("已提交审核！");
				}else{
					alert("保存成功！");
				}
				$('#conBody').loadPage($("#returnUrl").val());
			}
		});
	})
	
	// 返回
	$('#returnEducationBtn').click(function(){
		$('#conBody').loadPage($('#returnUrl').val());
	});
})

</script>

<div class="partContainers formLayout form2Pa">
	<form:form id="expertInfoForm" method="post" modelAttribute="education">
	<form:hidden path="objId"></form:hidden>
	<form:hidden path="expertInfo.objId"></form:hidden>
	<input type="hidden" id="expInfoFileId" value="${education.file}"/>
	<input type="hidden" id="speciality" value="${education.speciality.objId}"/>
	<input type="hidden" id="degree" value="${education.degree.objId}"/>
     	<ul>
	     	<li class="fullLine">
	     		<label>毕业院校：</label>
	     			<form:input path="graduateSchool" cssClass="required" maxlength="50" size="60%"></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label>所学专业：</label>
	     			<select  name="speciality.objId" class="required"><option value="">--请选择专业--</option></select><span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label>学历：</label>
	     			<select  name="degree.objId" class="required"><option value="">--请选择学历--</option></select><span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label>入学时间：</label>
	     			<form:input path="enrollDate" cssClass="required" ></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label>毕业时间：</label>
	     			<form:input path="graduateDate" cssClass="required" ></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li class="fullLine">
	     		<label>证明文件：</label>
	     		<div id="expInfoFile" name="expInfoFile" class="uploadFile"></div>
    	    </li>
	     	
    	</ul>
     </form:form>
        <div class="conOperation">
				<button type="button" id="educationBtn_save"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="educationBtn_submit"><span>提交</span></button>
				<button type="button" id="returnEducationBtn"><span><spring:message code="globe.return"/></span></button>
		   </div>
 </div>
    	    