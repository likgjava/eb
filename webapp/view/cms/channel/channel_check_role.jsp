<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>  

<div class="conSearch">
	<h4><span><spring:message code="globe.query"/></span></h4>
    	<ul class="highclassSearch">
		  	<li>
				<label for="condition">用户名关键字：</label>
				<input id="condition" style="width:300px"/>
			</li>
			<li class="operationBtnDiv">
				<button id="SEARCH"><span><spring:message code="globe.query"/></span></button>
			</li>
		</ul>
</div>
