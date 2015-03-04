<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('a[name=taskHref]').live('click',function() {
		common.open($(this).attr('id'),$(this).parent().attr('id'));
	});
});
</script>
<div class="importantNote">
   <h4><span>项目</span></h4>
   <ul>
   <c:choose>
	<c:when test="${agencyTaskList != null && fn:length(agencyTaskList) > 0}">
	   	<c:forEach items="${agencyTaskList}" var="task">
	       	<li id="${task.objId }"><a href="javascript:void(0);"/></span></li>
	    </c:forEach>
   </c:when>
	<c:otherwise><li>没有项目待办记录！</li></c:otherwise>
   </c:choose> 
   </ul>
 </div>