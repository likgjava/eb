<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="formLayout form2Pa" > 
<input type="hidden" id="projectId" name="projectId" value="${projectId}">
<input type="hidden" id="typeflag" name="typeflag" value="${typeflag}">
<h5>抽取到的投标单位列表</h5>
<ul>
<c:set var="count" value="0"></c:set>
<c:forEach items="${supplierList}" var="supplier">
<li class="fullLine">
  <label>投标单位名称:</label> 
  <span id="supplierName">${supplier.orgName}</span>
  <input type="hidden" id="supplierId_${count}" name="supplierId" value="${supplier.objId}">
</li>
<c:set var="count" value="${count+1}"></c:set>
</c:forEach>
</ul>
<div class="conOperation">
<button id="supplierSave" type="submit" tabindex="18"><span>录入</span></button>
</div>
</div>
<script>
$("#supplierSave").click(function(){
	var supplierIds ='';
    $("input[name=supplierId]").each(function(i,n){
    	supplierIds += $(n).val()+',' ;
    })
    var projectId = $("#projectId").val();
	$.getJSON($("#initPath").val()+"/InviterollrequController.do?method=saveInviterollrequ",{projectId:$("#projectId").val(),supplierIds:supplierIds,typeflag:$("#typeflag").val()},function(json){
		if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
			planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		  } else {
			$("#myDesktop").click();
		}		
	});
});


$(document).ready(function(){

	
});
</script>