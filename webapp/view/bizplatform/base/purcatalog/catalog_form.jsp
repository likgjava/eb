<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/bizplatform/base/purcatalog/catalog_form.js"/>'></script>
<div>
	<form:form id="catalogForm" method="post" modelAttribute="purCatalog">
	<form:hidden path="objId"></form:hidden>
	<div class="formLayout form2Pa">
	    <h4><span>采购目录信息</span></h4>
		<ul>
			<li class="fullLine">
				<label>目录标题：</label>
				<form:input path="title" cssClass="required"></form:input>
				<span class='eleRequired'>*</span>
			</li>	
			<li>
				<label>使用区域名称：</label>
				<form:hidden id="district.objId" path="areaCode"></form:hidden>
				<form:input id="district.name" path="areaName" cssClass="sysicon siSearch required" readonly="true"/>
				<span class='eleRequired'>*</span>
			</li>
			<li>
				<label>年度：</label>
				<input type="hidden" id="year" value="${purCatalog.year }">
				<select name="year"></select>
				<span class='eleRequired'>*</span>
			</li>
			<li class="fullLine">
				<label >生效时间：</label>
				<form:input path="relDate" cssClass="required"></form:input>
				<span class='eleRequired'>*</span>
			</li>		
			<li class="formTextarea">
				<label>有关说明及要求：</label>
				<form:textarea path="content" cssClass="required"></form:textarea>
				<span class="operationBtnDiv"><button type="button" id="saveSecond" class="hidden">保存</button></span>
			</li>
			
			<c:if test="${purCatalog.opinion!=null}">
			<li class="fullLine"><label>审核意见：</label>
				<span>${purCatalog.opinion}(${purCatalog.auditStatusCN })</span>
			</li>
			</c:if>
		</ul>
	</div>
	</form:form>
	
	<!-- 品目树 -->
	<div id="catalogDetailTree" class="hidden"></div>	
	
	<div class="conOperation" id="catalogBtn">
		<button  id="catalogSave"><span><spring:message code="globe.save"/></span></button>
		<button  id="catalogSubmit"><span><spring:message code="globe.submit"/></span></button>
		<button  id="catalogReturn"><span><spring:message code="globe.return"/></span></button>
	</div>
</div>