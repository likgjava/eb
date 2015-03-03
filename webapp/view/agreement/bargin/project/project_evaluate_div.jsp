<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<input type="hidden" name="projectId" id="projectId" value="${param.projectId }">
<input type="hidden" name="projectName" id="projectName" value="${param.projectName }">
<input type="hidden" name="userType" id="userType" value="${param.userType }">


<div class="formTips attention"><ul><li><em>待评价机构：</em></li><li id="ulforEvaluate"></li></ul></div>
<div id="evaluateFormDiv"></div>

<script type="text/javascript">

var projectEvlauateDiv = {};

//取得评价对象
projectEvlauateDiv.getEvlauteObject = function(){
	var url = "/BargainProjectController.do?method=getEvaluateObjectSupplier";
	if($("#userType").val()=="buyer"){
		url = "/SupplierSignupController.do?method=getEvaluateObjectSupplier";
	}	
	$.getJSON($("#initPath").val()+url,{projectId:$("#projectId").val()},function(json){
		if(json.success){
			var ahref = '';
			if(json.evaluateOrg!=null&&json.evaluateOrg.length>0){
				$.each(json.evaluateOrg,function(index,obj){
					var orgType = '供应商';
					ahref += '<input type="radio" id="'+obj[0]+'" name="evaluateObjectRadio"'+(index==0?' checked="checked"':'')+' onclick="projectEvlauateDiv.openEvaluateClick(\''+obj[0]+'\',\''+$("#projectId").val()+'\',\''+$("#projectName").val()+'\');" title="'+orgType+'"/>'+obj[1];
					if(index==0){projectEvlauateDiv.openEvaluateClick(obj[0],$("#projectId").val(),$("#projectName").val());}
				})
			}else{
				ahref = '无可评价对象';
				$("#evaluateFormDiv").empty();
			}
			$("#ulforEvaluate").html(ahref);
		}
	})
}


$(document).ready(function(){
	projectEvlauateDiv.getEvlauteObject();
})

//评价弹出评价
projectEvlauateDiv.openEvaluateClick = function(objId,projectId,projectName){
	projectName = native2ascii(projectName);
	$("#evaluateFormDiv").loadPage($("#initPath").val()+"/EvaluateController.do?method=toEvaluate&orgId="+objId+"&projectId="+projectId+"&projectName="+projectName);
}

</script>