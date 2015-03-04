<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<div class="formLayout form2Pa">
   <form id="recordFormDetailForm" >
     	<h4><span>备案书</span></h4>
			     	<ul>
						<li>
			 				<label >底价</label>
			 				<input type="hidden" value="${recordForm.objId }" name="objId" id="objId">
			 				<input type="text" value="${recordForm.reservePrice }" name="reservePrice" id="reservePrice" class="required"/>
							<span class="eleRequired">*</span>
						</li>
					</ul>
	</form>
	<div class="formLayout form2Pa">
		   <div class="conOperation">
				<button type="button" id="recordFormSave"><span><spring:message code="globe.save"/></span></button>
		   </div>
</div>
</div>
<script type="text/javascript"> 
$(document).ready(function(){
	$("#recordFormSave").click(function(){
		$.getJSON($('#initPath').val()+'/RecordFormController.do?method=updateRecordFormJz', formToJsonObject('recordFormDetailForm'), function(json){
			if(json.failure){alert(json.result);return;}
			if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			 }
		});
	});
})
</script>
