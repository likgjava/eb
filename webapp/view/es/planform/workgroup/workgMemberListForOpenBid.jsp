<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!-- 隐藏公共数据 -->
<input type="hidden" value="${workGroup.objId }" name="workGroupId" id="workGroupId" />
<table class="tableList" id="SubProjectList" style="width:800px;">
	<caption><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>小组<span id="addOpenBidGroup"  style="float:right;">[新增]</span></caption>
	<thead>
   		<tr>
       		<th class="center"><spring:message code="workgMemberForm.workgmName"/></th>
  			<th class="center"><spring:message code="workgMemberForm.workgmType"/></th>
     		<th class="operation">操作</th>
   		</tr>
	</thead>
	<tbody>
	
	<c:set value="0" var="subProjectCount"/>
	<c:forEach items="${workGroup.memberSet}" var="member" >
		<tr>
			<td>${member.workgmName }
			</td>
			<td>${member.workgmType }
			</td>
			<td style="text-align: center;">
				<button class="sysicon siModify" onclick="javascript:workgMemberListForOpenBid.updateMember('${ member.objId}');"><span>修改</span></button>
				<button class="sysicon siDelete" onclick="javascript:workgMemberListForOpenBid.removeMember('${ member.objId}');"><span>删除</span></button>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<script language="javascript">

var workgMemberListForOpenBid = {};
$(document).ready(function(){	
	//新增开标小组成员
	$("#addOpenBidGroup").click(function(){
		$.epsDialog({
	        title:"新增开标小组成员",
	        url:$("#initPath").val()+"/WorkGroupController.do?method=toAddOpenBidMember&workGroupId="+$("#workGroupId").val(),
	        width: 500,
	        height: 130,
	        isReload:false,
	        onClose: function(){ 
	        	checkProjectMenu('menu_openbidgroup');
	 		}
		});
	});
});
//修改
workgMemberListForOpenBid.updateMember = function(memberId){
	$.epsDialog({
        title:"修改开标小组成员",
        url:$("#initPath").val()+"/WorkGroupController.do?method=toUpdateOpenBidMember&memberId="+memberId,
        width: 500,
        height: 130,
        isReload:false,
        onClose: function(){ 
        	planTemplateTask.refresh($("#projectTaskId").val());
 		}
	});
	
}
//删除
workgMemberListForOpenBid.removeMember = function(memberId){
	$.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=remove', {objId:memberId}, function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		planTemplateTask.refresh($("#projectTaskId").val());
	});
}
</script>