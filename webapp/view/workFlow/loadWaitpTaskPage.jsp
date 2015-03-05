<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script>
$(document).ready(function(){
	$('#epsTabs').tabs();
	$("a[name='tab']").click(function(){
		$('#processType').val(this.id);
		$('#waitpTaskListDiv').loadPage($('#initPath').val()+'/view/workFlow/loadWaitpTaskList.jsp?processType='+this.id);
	});
	$('#search').click(function(){
		$('#WaitpTaskListGrid').reload();
	});
	
	$('a[name="tab"]:first').click();
	$("[name=startTimeQo]").epsDatepicker();
	$("[name=endTimeQo]").epsDatepicker();
});

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
	<div id="epsTabs">
	<ul>
		<c:forEach var="waitpTaskType" items="${waitpTaskTypeList}">
	  	<li>
	      <a href="#waitpTaskListDiv" name="tab" id ="${waitpTaskType.code}" class="refreshData"><span>${waitpTaskType.name}</span></a>
	    </li>
	   </c:forEach>
   </ul>
   <input type="hidden" id="processType" value="">
   <div id="waitpTaskListDiv"></div>
	</div>
</div>


