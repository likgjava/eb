<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers"id="epsTabs">
  <ul>
  <c:forEach items="${subPrjList }" var="subPrj">
  	<li>
      <a href="#taskPlanListInfo" id = "${subPrj.objId }" class="refreshData"><span>${subPrj.projName }</span></a>
    </li>
   </c:forEach>
   </ul>
<input type="hidden" id="subProjId" value="">
<div class="formLayout" id="taskPlanListInfo">
</div>
</div>

<script language="javascript">
var signUprecordInfoList = {};
signUprecordInfoList.currentTabID = "";
$(document).ready(function(){
	$("#subProjId").val($("#projectId").val());    //第一次进入默认传入项目ID
	$('#epsTabs').tabs();


	 $("li.ui-state-disabled").each(function(){
			$(this).attr("class","ui-state-default ui-corner-top ui-state-active");
		})
	
	$("li a.refreshData").click(function(){
		signUprecordInfoList.currentTabID = $(this).attr("id");
		$("#subProjId").val($(this).attr("id"));    //点击后传入包组ID
		$("#taskPlanListInfo").loadPage($('#initPath').val()+"/SignUprecordController.do?method=toSignupRecordViewList&prjId="+$(this).attr("id"));
	})
    $("li a.refreshData:first").click();	

signUprecordInfoList.viewSignUprecord =function(objId,bailStatus,supplierName){
	$.epsDialog({
        title:"报名信息",
        url:$("#initPath").val()+"/SignUprecordController.do?method=toViewSignupInfo&objId="+objId,
        width: 700,
        height: 450,
        isReload: false,
        onClose: function(){
            if($("#projectTaskId") && $("#projectTaskId").val() != ""){
	        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
            }
       	}
	});
}

//查看供应商投标详情
signUprecordInfoList.viewSupplierBidDetail =function(packId,supplierId,supplierName){
	$.epsDialog({
        title:"供应商投标详情",
        url:$("#initPath").val()+"/BidController.do?method=toSupplierBidDetail&projectId="+$("#projectId").val()+"&packId="+packId+"&supplierId="+supplierId+"&supplierName="+native2ascii(supplierName),
        width: 700,
        height: 450
	});
}
})









</script>