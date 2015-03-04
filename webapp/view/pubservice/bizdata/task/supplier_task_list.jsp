<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('a[name=taskHref]').live('click',function() {
		common.open($(this).attr('id'),$(this).parent().attr('id'));
	});
});
</script>
     <div class="importantNote myTask myTaskL">
       <h4><span>项目</span></h4>
       <ul>
       <c:choose>
		<c:when test="${projectTask != null && fn:length(projectTask) > 0}">
	       	<c:forEach items="${projectTask}" var="task">
	           	<li id="${task.objId }"><a href="javascript:void(0);"/></span></li>
	        </c:forEach>
        </c:when>
    	<c:otherwise><li>没有项目待办记录！</li></c:otherwise>
       </c:choose>   
       </ul>
     </div>
     <div class="importantNote myTask myTaskR">
       <h4><span>合同</span></h4>
       <ul>
       <c:choose>
		<c:when test="${contractTask != null && fn:length(contractTask) > 0}">
	       	<c:forEach items="${contractTask}" var="task">
	           	<li id="${task.objId }"><a href="javascript:void(0);"/></span></li>
	        </c:forEach>
        </c:when>
    	<c:otherwise><li>没有合同待办记录！</li></c:otherwise>
       </c:choose> 
       </ul>
     </div>
     <div class="importantNote myTask myTaskL">
       <h4><span>订单</span></h4>
       <ul>
       <c:choose>
		<c:when test="${orderTask != null && fn:length(orderTask) > 0}">
	       	<c:forEach items="${orderTask}" var="task">
	           	<li id="${task.objId }"><a href="javascript:void(0);"/></span></li>
	        </c:forEach>
        </c:when>
    	<c:otherwise><li>没有订单待办记录！</li></c:otherwise>
       </c:choose> 
       </ul>
     </div>
     <div class="importantNote myTask myTaskR">
       <h4><span>议价</span></h4>
       <ul>
       <c:choose>
		<c:when test="${bargainTask != null && fn:length(bargainTask) > 0}">
	       	<c:forEach items="${bargainTask}" var="task">
	           	<li id="${task.objId }"><a href="javascript:void(0);"/></span></li>
	        </c:forEach>
        </c:when>
    	<c:otherwise><li>没有订单待办记录！</li></c:otherwise>
       </c:choose> 
       </ul>
     </div>