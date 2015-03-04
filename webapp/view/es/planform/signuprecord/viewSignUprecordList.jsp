<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
<!--
a.abtn {text-decoration:underline}
-->
</style>
<div class="partContainers">
	<input type="hidden" value="${projectId }" id="projectId"/>
	<table class="tableList" id="SubProjectList">
		<caption style=" text-align: right">
		<a href="#"><span onclick="addSignUprecord()"	 title="新增"  class="sysicon siAdd">新增&nbsp;&nbsp;&nbsp;</span></a>
		</caption>
  		<thead>
      		<tr class="center">
      			<th>投标单位名称</th>
          		<th>状态</th>
          		<th>操作</th>
     		</tr>
		</thead>
	<tbody>
	<c:forEach items="${signUprecordList}" var="signUprecord" varStatus="i">
		<tr>
			<td><a href="#" onclick="viewSignUprecord('${signUprecord.objId}');" class="abtn">${signUprecord.supplier.orgName}</a></td>
			<td class="center">${signUprecord.auditStatusCN}</td>
			<td class="center"><a href="#"><span class="sysicon siAccept" onclick="viewSignUprecord('${signUprecord.objId}');" title="查看">查看&nbsp;&nbsp;&nbsp;</span></a>
				<a href="#"><span class="sysicon siModify" onclick="updateSignUprecord('${signUprecord.objId}');" title="修改">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span></a>
				<a href="#"><span class="sysicon siDelete" onclick="deleteSignUprecord('${signUprecord.objId}');" title="删除">删除&nbsp;&nbsp;&nbsp;</span></a>
			</td>
			</tr>
		
	</c:forEach>
	</tbody>
    </table>
</div>

<script language="javascript">
function viewSignUprecord(objId){
	$.epsDialog({
        title:"报名信息",
        url:$("#initPath").val()+"/SignUprecordController.do?method=toViewSignupPageByAgent&objId="+objId,
        width: 700,
        height: 225,
        isReload: false,
        onClose: function(){
            if($("#projectTaskId") && $("#projectTaskId").val() != ""){
	        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
            }
       	}
	});
}
function addSignUprecord(){
	$.epsDialog({
        title:"补录投标单位",
        url:$("#initPath").val()+"/SignUprecordController.do?method=toPageForAgency&projectId="+$("#projectId").val(),
        width: 600,
        height: 280,
        isReload: false,
        onClose: function(){
       	}
	});
}
function updateSignUprecord(objId){
	$.epsDialog({
        title:"修改投标单位报名信息 ",
        url:$("#initPath").val()+"/SignUprecordController.do?method=updatePageForAgency&objId="+objId,
        width: 600,
        height: 280,
        isReload: false,
        onClose: function(){
       	}
	});
}
function deleteSignUprecord(objId){
	if(window.confirm("确认删除吗?")){
		$.getJSON($('#initPath').val()+'/SignUprecordController.do?method=removeSignUprecordForAgency',{objId:objId},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			if($("#projectTaskId") && $("#projectTaskId").val() != ""){
	        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
            }
		});
	}
}
</script>