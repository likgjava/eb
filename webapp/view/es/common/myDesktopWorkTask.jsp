<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/common/myDesktopWorkTask.js"></script>
<div class="listNote">
	<ul>
	 	<c:forEach items="${taskModelList}" begin="0"  end="4"  var="taskModel">
		<li><a title="${taskModel.name }" href="#" onClick="waitProcWordTask.doTask('${taskModel.objId}');">${taskModel.name }</a><span class="listNoteTime"><fmt:formatDate value="${taskModel.createTime }" pattern="yyyy-MM-dd HH:mm"/></span></li>
	    </c:forEach>
	   <c:if test="${fn:length(taskModelList)> 5}">
			<li class="more"><a title="更多…" href="#" id="moreWaitTask" onclick="toMoreWaitTask('${param.taskType}')"><span class="sysicon siNext">更多…</span></a></li>
		</c:if>
	</ul>
</div>