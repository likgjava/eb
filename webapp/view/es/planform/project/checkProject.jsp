<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
<!--
.styleA{
cursor: hand;
}
.styleCenter{
text-align: center;
}
-->
</style>
<div class="partContainers">
	<table class="tableList">
		<caption>检查项目完整性</caption>
  		<thead>
      		<tr class="center">
      			<th>检查项</th>
          		<th>状态</th>
     		</tr>
		</thead>
	<tbody>
		<tr>
			<td>
				<c:choose>
				<c:when test="${project.isDividePack=='1'}"><span class="sysicon siAccept">&nbsp;&nbsp;</span></c:when>
				<c:when test="${project.isDividePack=='3'}"><span class="sysicon siAccept">&nbsp;&nbsp;</span></c:when>
				<c:otherwise><span class="sysicon siCancel">&nbsp;&nbsp;</span></c:otherwise>
				</c:choose>
				[${project.projCode}]${project.projName}：
				<c:choose>
					<c:when test="${project.isDividePack=='1'}"><span class="greenHighlight">该项目检查完整</span></c:when>
					<c:when test="${project.isDividePack=='3'}"><span class="greenHighlight">该项目检查完整</span></c:when>
					<c:otherwise><a title="拆分" id="subProjectIdC" class="highlight">该项目预算有剩余，拆分不完整</a></c:otherwise>
				</c:choose>			
			</td>
			<td class="styleCenter">
			<c:choose>
				<c:when test="${project.isDividePack=='1'}"><span class="greenHighlight">完整</span></c:when>
				<c:when test="${project.isDividePack=='3'}"><span class="greenHighlight">完整</span></c:when>
				<c:otherwise><a title="拆分" id="subProjectIdA" class="highlight">不完整</a></c:otherwise>
			</c:choose>
			</td>
		</tr>
	</tbody>
    </table>
</div>

<script type="text/javascript">
$(document).ready(function(){
var checkProject = {};

	$('#congrType').click(function(){
		$('#projectDoDiv').loadPage($('#initPath').val()+"/CongruousFactorController.do?method=toCongruousFactorListView&projectId="+$('#projectId').val());
	})
	$('#congrTypeA').click(function(){
		$('#projectDoDiv').loadPage($('#initPath').val()+"/CongruousFactorController.do?method=toCongruousFactorListView&projectId="+$('#projectId').val());
	})
	$('#subProjectIdC').click(function(){
		$('#projectDoDiv').loadPage($('#initPath').val()+"/ProjectController.do?method=toProjectCraete&projectId="+$('#projectId').val());
	})
	$('#subProjectIdA').click(function(){
		$('#projectDoDiv').loadPage($('#initPath').val()+"/ProjectController.do?method=toProjectCraete&projectId="+$('#projectId').val());
	})
	$('#congrId').click(function(){
		$('#projectDoDiv').loadPage($('#initPath').val()+"/CongruousFactorTypeController.do?projectId="+$('#projectId').val());
	})
	$('#congrIdA').click(function(){
		$('#projectDoDiv').loadPage($('#initPath').val()+"/CongruousFactorTypeController.do?projectId="+$('#projectId').val());
	})
	
	// 刷新项目进度状态
	planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
});
</script> 

