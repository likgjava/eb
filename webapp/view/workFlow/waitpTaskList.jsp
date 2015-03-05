<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script>
function success () {
	$("#WaitpTaskListGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">进入</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#WaitpTaskListGrid").flexGetRowJsonById(rowId);
				$('#conBody').empty().loadPage($('#initPath').val()+'/'+json.url+'&returnToList=true');
			}).appendTo(obj);
		}
	});
}
$("[name=startTimeQo]").epsDatepicker();
$("[name=endTimeQo]").epsDatepicker();
</script>
<div class="partContainers">
	<form id="waitpTaskSearchZone">
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
				<li class="fullLine">
					任务名称： <input type="text" name="nodeName" class="" value="">
					<input type="hidden" name="nodeName_op" value="like">
					项目名称： <input type="text" name="bizName" class="" value="">
					<input type="hidden" name="bizName_op" value="like">
					任务启动时间：
					<input type="text" name="startTimeQo" style="width: 70px;" readonly="readonly"/>
					至
					<input type="text" name="endTimeQo" style="width: 70px;" readonly="readonly"/>
				</li>
				<li class="operationBtnDiv">
					<button type="submit"><span><spring:message code="globe.query"/></span></button>
				</li>
			</ul>
		</div>
	</form>
	<flex:flexgrid checkbox="false" id="WaitpTaskListGrid" url="WorkFlowController.do?method=loadWaitpTaskList&processType=${param.processType}&order=startDate&order_flag=true" title=""  queryColumns="objId,startDate,nodeName,bizName,url,status" onSuccess="success" searchZone="waitpTaskSearchZone">
		<flex:flexCol name="bizName" display="项目名称" sortable="true" width="360" align="left"></flex:flexCol>
		<flex:flexCol name="nodeName" display="任务名称" sortable="true" width="150" align="left"></flex:flexCol>
		<flex:flexCol name="startDate" display="任务启动时间" sortable="true" width="70" align="center" format="date"></flex:flexCol>
		<flex:flexCol name="status" display="任务类型" sortable="true" width="70" align="center" format="date"></flex:flexCol>
		<flex:flexCol name="objId" display="操作" sortable="true" width="30" align="center"></flex:flexCol>
	</flex:flexgrid>
</div>


