<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<input type="hidden" name="projectId" id="projectId" value="${param.projectId }">
<input type="hidden" name="projectName" id="projectName" value="${param.projectName }">
<input type="hidden" name="type" id="type" value="${param.type }">
<input type="hidden" name="ebuyMethod" id="ebuyMethod" value="${param.ebuyMethod }">
<input type="hidden" name="userType" id="userType" value="${param.userType }">

<div class="formTips attention"><ul><li><em>机构：</em></li><li id="ulforComplain"></li></ul></div>
<div id="complainFormDiv"></div>

<script type="text/javascript">

var projectComplainDiv = {};

//取得评价对象
projectComplainDiv.getComplainObject = function(){
	var url = $("#initPath").val()+"/SignUprecordController.do?method=getObjectQuery&queryColumns=supplier.objId,supplier.orgName,createUser.objId,createUser.emp.name";
	if($("#userType").val()=="supplier"){
		url = $("#initPath").val()+"/SupplierSignupController.do?method=getComplainOrgInfo";
	}	
	$.getJSON(url,{"project.objId":$("#projectId").val(),"projectId":$("#projectId").val()},function(json){
		if(json.success){			
			var ahref = '';			
			if($("#userType").val()=="supplier"){
				$.each(json.orgInfo,function(index,obj){
					var orgId = obj[0];
					var orgName = obj[1];
					var userId = obj[2];
					var userName = obj[1];	
					ahref += '<input type="radio" name="radioforcomplain"'+(index==0?' checked="checked"':'')+' onclick="projectComplainDiv.openComplainClick(\''+$("#projectId").val()+'\',\''+userId+'\',\''+orgId+'\',\''+userName+'\',\''+orgName+'\',\''+$("#type").val()+'\');"/>'+orgName+' ';			
					if(index==0){projectComplainDiv.openComplainClick($("#projectId").val(),userId,orgId,userName,orgName,$("#type").val());}
				})
			}else{
				$.each(json.result,function(index,obj){	
					var userId, userName;
					var orgId  = obj['supplier.objId'];
					var orgName = obj['supplier.orgName'];
					if($("#ebuyMethod").val()!= '05'){//议价项目 不取创建人
						userId = obj['createUser.objId'];
						userName = obj['createUser.emp.name'].split(" ")[0];
					}
					ahref += '<input type="radio" name="radioforcomplain"'+(index==0?' checked="checked"':'')+' onclick="projectComplainDiv.openComplainClick(\''+$("#projectId").val()+'\',\''+userId+'\',\''+orgId+'\',\''+userName+'\',\''+orgName+'\',\''+$("#type").val()+'\');"/>'+orgName+' ';			
					if(index==0){projectComplainDiv.openComplainClick($("#projectId").val(),userId,orgId,userName,orgName,$("#type").val());}
				})
			}
			$("#ulforComplain").html(ahref);
		}
	})
}

$(document).ready(function(){
	projectComplainDiv.getComplainObject();
})

//评价弹出评价
projectComplainDiv.openComplainClick = function(projectId,userId,orgId,userName,orgName,type){
	var param = '&type='+type+'&beCompanyId='+orgId+'&beCompanyName='+orgName+"&beComplain="+userId + '&beComplainName='+userName + '&projectId=' + projectId;
	$("#complainFormDiv").loadPage($("#initPath").val()+'/ComplainController.do?method=toCreateComplain'+param);
}
</script>