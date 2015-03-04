<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

$(document).ready(function() {
	$.validator.addMethod("paramCodeUnique",function(value,element,param){return uniqueHandler("GoodsClassParamType",param,value,$('#objId').val());},'该参数编号已存在');

	//表单验证
	$("#goodsClassParamTypeForm").validate({
		rules:{
			paramCode:{paramCodeUnique:"paramCode"}
		}
	});	
})

// 保存资质分类
$('button[id=savegoodsClassParamTypeBtn]').click(function() {
	if(!$('#goodsClassParamTypeForm').valid()){alert('请正确填写表单!');return;}
	$.getJSON($("#initPath").val()+"/GoodsClassParamTypeController.do?method=save",formToJsonObject('goodsClassParamTypeForm'),function(json){
		if(json.success == "true"){

			// 将详情的样式改为已修改
			$("tr[id="+json.goodsClassParamType.objId+"]").find("span").each(function() {
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
	<form:form id="goodsClassParamTypeForm" method="post" modelAttribute="goodsClassParamType">
	<form:hidden path="objId"></form:hidden>
     	<ul>
	     	<li class="fullLine">
	     		<label>参数名称：</label>
					<span>${goodsClassParamType.paramName}</span>
    	    </li>
	     	<li class="fullLine">
	     		<label>参数编号：</label>
	     			<form:input path="paramCode" cssClass="required"></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li class="formTextarea">
	     		<label>参数分类说明：</label>
	     		<form:textarea path="paramDesc" />
    	    </li>
    	</ul>
     </form:form>
        <div class="conOperation">
				<button type="button" id="savegoodsClassParamTypeBtn"><span><spring:message code="globe.save"/></span></button>
		   </div>
 </div>
    	    