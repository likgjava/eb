<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

$(document).ready(function() {
	$.validator.addMethod("paramCodeUnique",function(value,element,param){return uniqueHandler("GoodsClassParamType",param,value,$("#objId").val());},'该参数编号已存在');

	//表单验证
	$("#goodsClassParamForm").validate({
		rules:{
			paramCode:{paramCodeUnique:"paramCode"}
		}
	});	
})

// 保存资质分类
$('button[id=savegoodsClassParamBtn]').click(function() {
	if(!$('#goodsClassParamForm').valid()){alert('请正确填写表单!');return;}
	$.getJSON($("#initPath").val()+"/GoodsClassParamController.do?method=save",formToJsonObject('goodsClassParamForm'),function(json){
		if(json.success == "true"){

			// 将详情的样式改为已修改
			$("tr[id="+json.goodsClassParam.objId+"]").find("span").each(function() {
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

<div class="partContainers formLayout form2Pa">
	<form:form id="goodsClassParamForm" method="post" modelAttribute="goodsClassParam">
	<form:hidden path="objId"></form:hidden>
     	<ul>
	     	<li class="fullLine">
	     		<label>参数名称：</label>
					<span>${goodsClassParam.paramName}</span>
    	    </li>
	     	<li class="fullLine">
	     		<label>参数编号：</label>
	     			<form:input path="paramCode" cssClass="required"></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label>是否可选配：</label>
	     		<form:radiobutton path="canSelect" value="true"/>是
	     		<form:radiobutton path="canSelect" value="false"/>否
    	    </li>
	     	<li>
	     		<label>是否必填：</label>
	     		<form:radiobutton path="needInput" value="true"/>是
	     		<form:radiobutton path="needInput" value="false"/>否
    	    </li>
	     	<li>
	     		<label>是否主要参数：</label>
	     		<form:radiobutton path="mainParam" value="true"/>是
	     		<form:radiobutton path="mainParam" value="false"/>否
    	    </li>
	     	<li>
	     		<label>是否常用检索参数：</label>
	     		<form:radiobutton path="usuallySearch" value="true"/>是
	     		<form:radiobutton path="usuallySearch" value="false"/>否
    	    </li>
	     	<li class="fullLine">
	     		<label>参数值计量单位：</label>
	     		<form:input path="paramUnit"/>
    	    </li>
    	    
	     	<li class="formTextarea">
	     		<label>参数分类说明：</label>
	     		<form:textarea path="paramDesc" />
    	    </li>
    	</ul>
     </form:form>
        <div class="conOperation">
				<button type="button" id="savegoodsClassParamBtn"><span><spring:message code="globe.save"/></span></button>
		   </div>
 </div>
    	    