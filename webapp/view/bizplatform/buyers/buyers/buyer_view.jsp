<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="BuyerDetailForm" method="post" modelAttribute="buyer">
	<input type="hidden" id="buyer_ObjId" name="buyer_ObjId" value="<c:out value="${buyer.objId}"/>"/>
	<input type="hidden" id="orgInfoCurrentId" name="orgInfoCurrentId" value="<c:out value="${orgInfoCurrentId}"/>"/>
	<div class="formLayout form2Pa"><span id="historyBuyerJsonString" class="hidden">${historyObject}</span>
	<ul>
		<li class="fullLine"><label>单位性质：</label> <span id="unitTypeCN">${buyer.unitTypeCN}</span></li>
		<li class="fullLine"><label>所属行业：</label> <span id="belongIndustry">${buyer.belongIndustry.name}</span></li>
<!--		<li class="fullLine"><label>行政部门：</label> <span id="execDeptCN">${buyer.execDeptCN}</span></li>-->
<!--		<li class="fullLine"><label>主管部门：</label> <span id="cmptDepTypeCN">${buyer.cmptDepTypeCN}</span></li>-->
<!--		<li class="fullLine"><label>资金归口处室：</label> <span id="fundDeptCN">${buyer.fundDeptCN}</span></li>-->
		<li class="fullLine"><label>上级单位：</label> <span id="parentUnit.orgName">${buyer.parentUnit.orgName}</span></li>
<!--		<li class="fullLine"><label>预算编码：</label> <span id="parentUnit.orgName">${buyer.budgetCode}</span></li>-->
		<li class="fullLine"><label>机构简介：</label> <span id="unitIntroduction">${buyer.unitIntroduction}</span></li>
	</ul>
	</div>
</form:form>
