<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

$(document).ready(function() {
	$.validator.addMethod("qualityCodeUnique",function(value,element,param){return uniqueHandler("Qualification",param,value,$("#objId").val());},'该资质编号已存在');

	//表单验证
	$("#qualificationClassForm").validate({
		rules:{
			qualityCode:{qualityCodeUnique:"qualityCode"}
		}
	});	
})

// 保存资质分类
$('button[id=saveQualificationClassBtn]').click(function() {
	if(!$('#qualificationClassForm').valid()){alert('请正确填写表单!');return;}
	$.getJSON($("#initPath").val()+"/QualificationClassController.do?method=save",formToJsonObject('qualificationClassForm'),function(json){
		if(json.success == "true"){

			// 将详情的样式改为已修改
			$("tr[id="+json.qualificationClass.objId+"]").find("span").each(function() {
				if($(this).hasClass("siEditBtn")) {
					$(this).removeClass("siEditBtn").addClass("siEdit");
				}
			})
			$('#epsDialogClose').click(); 
		}
		if(json.failure) {
			return;
		}
	});
})
</script>

<div class="partContainers formLayout">
	<form:form id="qualificationClassForm" method="post" modelAttribute="qualificationClass">
	<form:hidden path="objId"></form:hidden>
     	<ul>
	     	<li>
	     		<label>资质名称：</label>
					<span>${qualificationClass.qualityName}</span>
    	    </li>
	     	<li>
	     		<label>资质编号：</label>
	     			<form:input path="qualityCode" cssClass="required"></form:input><span class="eleRequired">*</span>
    	    </li>
    	    
	     	<li>
	     		<label>资质类型：</label>
	     		<form:select path="classType">
	     		<form:option value="1">企业资质</form:option>
	     		<form:option value="2">行业资质</form:option>
	     		<form:option value="3">产品资质</form:option>
	     		<form:option value="4">人员资质</form:option>
	     		<form:option value="5">项目资质</form:option>
	     		<form:option value="6">其它</form:option>
	     		</form:select>
    	    </li>
    	    <li>
	     		<label>是否显示：</label>
	     		<form:radiobutton path="isDisplay" value="true" />显示
				<form:radiobutton path="isDisplay" value="false"/>不显示
    	    </li>
    	</ul>
     </form:form>
        <div class="conOperation">
				<button type="button" id="saveQualificationClassBtn"><span><spring:message code="globe.save"/></span></button>
		   </div>
 </div>
    	    