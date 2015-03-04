<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div class="formLayout" > 
<form id="keyIntoSupplierForm" method="post">
<input type="hidden" value="${project.ebuyMethod }" />
<input type="hidden" id="projectId" name="projectId" value="${projectId}"/>
<input type="hidden" id="typeflag" name="typeflag" value="${typeflag}"/>
<input type="hidden" name="status" value="01">
<c:if test="${inviterollrequ==null||typeflag=='update'}">
<h5>指定投标单位</h5>
<ul id="agentUl">
<li id="chouqu" class="functionBtnDiv">
<c:choose>
<c:when test="${project.ebuyMethod == '04'}">
<label>指定投标单位:</label>
 	<input type="hidden" name="num" id="num" value="1" >
   <input type="hidden" id="supplierId_0" name="supplierId" value="${supplier.objId}"/>
 <input type="text" value="" id="taskAgentName" class="required"><span class='eleRequired'></span><button id="supplierSave" type="button" tabindex="19" ><span>确认</span></button>
 </c:when>
 <c:otherwise>
 	<c:set var="count" value="0"></c:set>	
 	<c:if test="${inviterollrequ.auditStatus=='00'||inviterollrequ.auditStatus== '03'||inviterollrequ.auditStatus== null}">
 	<button id="addTaskAgent" type="button" tabindex="19" ><span>新增</span></button>
 	</c:if>
 </c:otherwise>
 </c:choose>
<c:if test="${inviterollrequ!=null&&inviterollrequ.auditStatus==00}">
<button type="button" onclick="subinviterollrequ('${inviterollrequ.objId}')" tabindex="19" style="margin-left:35%" ><span >提交审核</span></button>
</c:if>
</li>
</ul>
<div class="conOperation">
<button id="saveTaskAgent" type="button" tabindex="19" style="margin-right:65%" ><span>录入指定投标单位</span></button>
</div>
</c:if>
</form>
<table class="tableList" id="inrqDetailList">
	<c:if test="${inviterollrequ!=null}">
	<thead >
	<tr>
	<th>
	投标单位名称
	</th>
	<th>
	邀请函种类
	</th>
	<th>
	状态
	</th>
	<th>
	操作
	</th>
	</tr>
	</thead>
	</c:if>
	<tbody>
		<c:forEach items="${listInrqDetail}" var="inrqDetail" varStatus="i">
		<tr>
			<td>${inrqDetail.supplier.orgName}</td>
			<td class="center">${inrqDetail.inrqDKindCN}</td>
			<td class="center">${inrqDetail.inviterollrequ.auditStatusCN}</td> 
			<td class="center"><span ><a href="#" class="abtn" onclick="look('${inrqDetail.objId}')">查看</a></span></td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<input type="hidden" id="inviterollrequAudit" value="${inviterollrequ.auditStatus}"/>
</div>
<div id="keyIntoSupplierPageView"></div>
<script>
function showImg() {
	$.epsDialog({
	    id :"showImg",
        title:"抽取投标单位",
        url:$("#initPath").val()+"/view/es/singlesource/singlesourcedoc/keyIntoSupplierImg.jsp",
        width: 400,
        height: 150,
        isReload: true
	});
}	
 function look(objId){
	$.epsDialog({
	    title:'邀请函信息',
	    url:$('#initPath').val()+'/InrqDetailController.do?method=toUpdateInrqDetailView&objId='+objId,
	    width: '600',
	    height: '250',
		isReload:true,
	    onOpen: function(){ },
	    afterLoad: function(){ },
	    onClose: function(){ }
			});

	}
function subinviterollrequ(objId){
	if(window.confirm("确认提交吗？"))
	{
		$.getJSON($('#initPath').val()+'/InviterollrequController.do?method=toInviterollrequSub&objId='+objId, formToJsonObject('keyIntoSupplierForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			if($("#projectTaskId") && $("#projectTaskId").val() != ""){
	        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	        }
		});
	}
}
//采购方式为邀请招标时供选择的投标单位列表
 function showAgentList(i){
 	 $.epsDialog({
	        title:'投标单位列表',
	        url:$('#initPath').val()+'/view/es/planform/taskplan/agentListGrid3.jsp?num='+i+'',
	        width: '700',
	        height: '500',
	 	    isReload:true,
	 	    hasBg:false
			});
    }
 //删除新增的一列
 function deleteAgent(i){
	 $("#li"+i).remove();
	 if($("li[name='li']").length==0){
		 $("#saveTaskAgent").hide();
	 }
 }
$(document).ready(function(){	
	$("#saveTaskAgent").hide();
	$("#keyIntoSupplierForm").validate();
	//采购方式为单一来源时供选择的投标单位列表
	$("#taskAgentName").click(function(){
		$.epsDialog({
	        title:'投标单位列表',
	        url:$('#initPath').val()+'/view/es/planform/taskplan/agentListGrid2.jsp?num='+0+'',
	        width: '700',
	        height: '500',
	 	    isReload:true,
	 	    hasBg:false
			});
	});
	
	//采购方式为单一来源时保存选择的投标单位
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
	
	//采购方式为邀请招标时新增一列投标单位
	var i=0;
	$("#addTaskAgent").click(function(){
			var a = i+1;
		    var html = "<li id='li"+i+"'name='li'>";
		    html+="<label class='short' for='taskCode'>指定投标单位:</label>";
		    html+="<input type='hidden' id='supplierId_"+i+"' name='supplierId'>";
    		html+= "<input type='hidden' name='taskAgentId["+i+"]' id='taskAgentId["+i+"]'/>";
    		html+= "<input type='text'  name='taskAgentName["+i+"]' id='taskAgentName_"+i+"' class='required' onclick='showAgentList("+i+")'readonly='readonly'/>";
    		html+="<span class='eleRequired'>*</span>"
    		html+="<span><a href='#' onclick='deleteAgent("+i+")'>移除</a></span>"
    		html+= "</li>";
    		$("#agentUl").append(html);
    		$("#saveTaskAgent").show();
    		i++;
	});
	
	//采购方式为邀请招标时保存选择的投标单位列表
	//在此加一个供应商邀请数目判断，小于3家不能录入！
	$("#saveTaskAgent").click(function(){
		if(i<3) {
			if(!window.confirm("供应商录入数不足三家，是否继续操作？")) {
				return false;
			}
		}
		if(window.confirm("录入之后不能补录，确定提交？")){
		if(!$('#keyIntoSupplierForm').valid()){alert('请正确填写表单!');return;}
	
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
		}
	});

	
$("#supplierExtract").click(function(){
	var num = $("#num").val();
	if(!/^[0-9]+$/.test(num)) {
		alert("请输入合法数字！");
		return false;
	} 
	if(num==0){
		alert("请输入大于0的数字！");
		return false;
		}
	 showImg();
	 setTimeout(function(){
	 var projectId = $("input[name=projectId]").val();
	 var typeflag = $("input[name=typeflag]").val();
	 $("#keyIntoSupplierPageView").load($("#initPath").val()+"/SignUprecordController.do?method=getSupplierList&num="+num+"&projectId="+projectId+"&typeflag="+typeflag);	
	 $('#showImg').find(".epsDialogClose").click();
	 },4000);
	
});
});
</script>