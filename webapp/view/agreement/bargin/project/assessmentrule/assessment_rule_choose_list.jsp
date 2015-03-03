<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div>
<table class="tableList" style="width:100%">
		<tr>
			<th>规则名称</th>
			<th>品目名称</th>
			<th>规则文件</th>
			<th>创建人</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
	<c:forEach var ="assessmentRule" items="${assessmentRuleList}">
		<tr>
			<td>${assessmentRule.name }</td>
			<td>${assessmentRule.categoryName}</td>
			<td><a href="AttachmentController.do?method=downLoadFile&objId=${assessmentRule.assessmentFile.objId}">${assessmentRule.assessmentFile.viewName}</a></td>
			<td>${assessmentRule.createUser.emp.name}</td>
			<td>${assessmentRule.createTime}</td>
			<td><a href="javascript:void(0);" name="chooseFileRule" id="chooseFileRule" onclick="AssessmentRuleChooseForm.chooseFile('${assessmentRule.assessmentFile.objId}','${assessmentRule.assessmentFile.viewName}');">选择</a></td>
		</tr>
	</c:forEach>
	<c:if test="${fn:length( assessmentRuleList )==0}">
		<tr>
			<td align="center" colspan="6">暂无可用规则文件</td>
		</tr>
	</c:if>
</table>
</div>
  	
  	
<div class="conOperation center">
  	<button type="button" id="close" onclick="$('#close').closest('.epsDialog').find('.epsDialogClose').trigger('click');"><span><spring:message code="globe.close"/></span></button>
</div>
	
	
<script type="text/javascript">
var AssessmentRuleChooseForm={};

//选择文件
AssessmentRuleChooseForm.chooseFile = function( attachmentId ,viewName ){
	//回填名称
	$("#assessmentFile_name").html( '<a href="AttachmentController.do?method=downLoadFile&objId='+attachmentId+'">'+viewName+'</a>' );
	//回填id
	$("input[id=assessmentFile.objId]").val( attachmentId );
	$('#close').closest('.epsDialog').find('.epsDialogClose').trigger('click');
}

$(document).ready(function(){
	
});
</script>
