<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script>
function success () {
	$("#WaitpTaskListGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">进入</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#WaitpTaskListGrid").flexGetRowJsonById(rowId);
				//$('#conBody').empty().loadPage($('#initPath').val()+'/'+json.url+'&returnToList=true');
				$('#conBody').empty().loadPage($('#initPath').val()+'/WorkFlowController.do?method=loadAuditPage&returnToList=true&nodeId='+json.nodeId);
			}).appendTo(obj);
		}
	});
}
</script>
<flex:flexgrid checkbox="false" id="WaitpTaskListGrid" url="WorkFlowController.do?method=loadWaitpTaskList&processType=${param.processType}&order=startDate&order_flag=true" title=""  queryColumns="objId,startDate,nodeId,nodeName,bizName,url,status" onSuccess="success" searchZone="waitpTaskSearchZone">
	<flex:flexCol name="bizName" display="项目名称" sortable="true" width="360" align="left"></flex:flexCol>
	<flex:flexCol name="nodeName" display="任务名称" sortable="true" width="150" align="left"></flex:flexCol>
	<flex:flexCol name="startDate" display="任务启动时间" sortable="true" width="70" align="center" format="date"></flex:flexCol>
	<flex:flexCol name="status" display="任务类型" sortable="true" width="70" align="center" format="date"></flex:flexCol>
	<flex:flexCol name="objId" display="操作" sortable="true" width="30" align="center"></flex:flexCol>
</flex:flexgrid>


