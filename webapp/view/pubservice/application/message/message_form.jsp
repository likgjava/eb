<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/application/message/message_form.js"></script>

<div class="formLayout formPa">         
	<form:form id="messageForm" name="messageForm" method="post" modelAttribute="message">
 	<form:hidden path="objId"/>
	<!-- 站内信类型 （00:系统站内信，01:私人站内信） -->
	<input id="messageType" type="hidden" name="type" value="${messageType}" />
	
	<h4><span>发送站内信</span></h4>
	<ul>
		<c:if test="${messageType=='01'}">
			<li>
				<label for="receiverName">收信人：</label>
				<form:hidden id="receiver.objId" path="receiver"/>
				<form:input id="receiver.name" path="receiverName" cssClass="required" cssStyle="width:280px;"/>
				<span class='eleRequired'>*</span>
				<input type="hidden" name="receiverName_op" value="like">
				<input type="button" id="sendMorePerson" value="发送到多人" />
			</li>
		</c:if>
		<c:if test="${messageType=='00'}">
			<li>
				<label for="receiverName">收信人：</label>
				<input id="receiverType.objId" type="hidden" name="receiverType_objIds"/>
				<input id="receiverType.name" type="text" readonly="readonly" value="所有人" style="width:295px;"/>
				<input id="to_all" type="radio" name="receivers" checked="checked" value="to_all"/>所有人
				<input id="to_role" type="radio" name="receivers" value="to_role" />指定角色
				<input id="to_user" type="radio" name="receivers" value="to_user" />指定人
			</li>
		</c:if>
		
		<li>
			<label for="title">标题：</label>
			<form:input path="title" cssClass="required" maxlength="50" cssStyle="width:295px;"/>
			<span class='eleRequired'>*</span>
		</li>
		<li class="fullLine">
			<label for="content">内容：</label>
			<form:textarea path="content" cssClass="required" cssStyle="width:400px;height:200px;"/>
			<span class='eleRequired'>*</span>
		</li>
		<c:if test="${isManager && messageType=='00'}">
			<input type="hidden" name="isSave" value="1"/>
		</c:if>
		<c:if test="${!isManager || messageType=='01'}">
		<li>
			<label for="isSave"></label>
			<form:checkbox path="isSave" value="1"/>&nbsp;保存到发件箱
		</li>
		</c:if>
	</ul>
	<div class="conOperation">
		<button id="submitBtn" type="button"  class="largeBtn"><span>发送</span></button>
		<button id="cancelBtn" type="button"  class="largeBtn"><span><spring:message code="globe.return"/></span></button>
	</div>
	</form:form>
</div>