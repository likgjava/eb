<%@ page pageEncoding="utf-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/sysconfig/itemValueAdd.js"></script>
		<div class="formLayout">
		<form id="itemValueAddForm" method="post">
			
			<div class="conOperation">
				<button id="itemValueAddSave" type="button" ><span><spring:message code="globe.save"/></span></button>
		        <button id="itemValueAddReturn" type="button" ><span><spring:message code="globe.return"/></span></button>
		   	</div>
		</form>
		</div>
