<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

$(document).ready(function() {
 var objId = $("#expertInfoForm").find("input[id=objId]").val()
	
	//回填附件
	$.getJSON($("#initPath").val()+"/EducationController.do?method=createOrUpdate&includedProperties=expertInfo,speciality,degree&objId="+objId,{},function(json){
		var fileId = json.education.file;
		if(null!=fileId && ""!=fileId){
			$('#expInfoFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=file&isSelect=false&attachRelaId='+fileId);
		}
		json2ObjectSpan('expertInfoForm', json.education);
	});
	
	//提交
	$('button[id^=auditEducationBtn]').click(function(){
		$('button[id^=auditEducationBtn]').attr('disabled',true);
		var auditStatus = $(this).attr('id').replace('auditEducationBtn_','');
		$.getJSON($('#initPath').val()+'/EducationController.do?method=auditEducation',{'objIds':objId,'auditStatus':auditStatus},function(json){
			if(json.result)alert(json.result);if(json.failure){$('button[id^=auditEducationBtn]').attr('disabled',false);return};
	 		if(json.success){
					alert('审核成功!');
					$('#conBody').loadPage($('#returnUrl').val());
	     	}
 		});
	});
	
	// 返回
	$('#returnEducationBtn').click(function(){
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
	     		<label>学历：</label>
	     			<span id="degree.dicName" ></span>
    	    </li>
	     	<li>
	     		<label>毕业院校：</label>
	     			<span id="graduateSchool" ></span>
    	    </li>
	     	<li>
	     		<label>所学专业：</label>
	     			<span id="speciality.dicName" ></span>
    	    </li>
	     	<li>
	     		<label>入学时间：</label>
	     			<span id="enrollDate"></span>
    	    </li>
	     	<li>
	     		<label>毕业时间：</label>
	     			<span id="graduateDate"></span>
    	    </li>
	     	<li class="fullLine">
	     		<label>证明文件：</label>
	     		<div id="expInfoFile" name="expInfoFile" class="uploadFile"></div>
    	    </li>
	     	
    	</ul>
     </form>
       <div class="conOperation">
			<button type="button" id="auditEducationBtn_02"><span>审核通过</span></button>
			<button type="button" id="auditEducationBtn_03"><span>审核不通过</span></button>
			<button type="button" id="returnEducationBtn"><span><spring:message code="globe.return"/></span></button>
		 </div>
 </div>
    	    