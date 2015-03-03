<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<%@page import="com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalog"%><script type="text/javascript" src='<c:url value="/view/bizplatform/base/purcatalog/catalog_form_dialog.js"/>'></script>

<% PurCatalog purCatalog = new PurCatalog(); %>

<div>
	<form id="catalogForm" method="post">
	<div class="formLayout form2Pa">
	    <h4><span>采购目录信息</span></h4>
		<ul>
			<li class="fullLine">
				<label>目录标题：</label>
				<input name="title" class="required"></input><span class="eleRequired">*</span>
				<span class="eleRight"></span>
			</li>	
			<li>
				<label>使用区域名称：</label>
				<input type="hidden" id="district.objId" name="areaCode"/>
				<input id="district.name" name="areaName" class="sysicon siSearch required" readonly="true"/>
				<span class="eleRequired">*</span>
				<span class="eleRight"></span>
			</li>
			<li>
				<label>年度：</label>
				<select name="year"></select>
				<span class="eleRight"></span>
			</li>
			<li class="fullLine">
				<label >生效时间：</label>
				<input name="relDate" class="required"/><span class="eleRequired">*</span>
				<span class="eleRight"></span>
			</li>		
			<li class="formTextarea">
				<label>有关说明及要求：</label>
				<textarea id="content" name="content" class="required"></textarea>
			</li>
			
			<c:if test="${purCatalog.opinion!=null}">
			<li class="fullLine"><label>审核意见：</label>
				<span>${purCatalog.opinion}(${purCatalog.auditStatusCN })</span>
			</li>
			</c:if>
		</ul>
	</div>
	</form>
	
	<div class="conOperation" id="catalogBtn">
		<button  id="catalogSave"><span><spring:message code="globe.save"/></span></button>
		<button  id="catalogclose"><span><spring:message code="globe.close"/></span></button>
	</div>
</div>