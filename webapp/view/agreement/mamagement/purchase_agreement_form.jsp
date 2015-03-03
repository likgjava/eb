<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/mamagement/purchase_agreement_form.js"></script>
<!-- 导航显示 -->
<div class="nextBar">
    <ol class="num4">
        <li class="current"><span class="first">1. 填写协议基本信息</span></li> 
        <li><span>2. 增加协议商品分类</span></li>
        <li class="last"><span>3. 增加协议单品</span></li>
    </ol>
</div>

	<form:form id="AgreementForm" method="post" modelAttribute="agreement">
	<form:hidden path="objId"></form:hidden>
	<div id = "addpurchaseAgreement" class="formLayout form2Pa">
	    <h4><span>协议信息</span></h4>
		<ul>
			<li id="agreementNameLi" class="<c:if test='${agreement.agreementNo==null}'>fullLine</c:if>">
				<label>协议名称：</label>
				<form:input path="name" cssClass="required"></form:input>
				<span class='eleRequired'>*</span>
			</li>	
			<c:if test="${agreement.agreementNo!=null}">
			<li id="agreementNoLi">
				<label>协议编号：</label>
				<span id="agreementNo">${agreement.agreementNo }</span>
			</li>	
			</c:if>
			<li class="fullLine">
				<label>签订时间：</label>
				<form:input path="creTime" id="creTime"></form:input>
				<span class='eleRequired'>*</span>
			</li>
			<li>
				<label>甲方(代理机构)：</label>
				<form:hidden path="org.objId"></form:hidden>
				<form:input path="org.orgName" cssClass="sysicon siSearch required"></form:input>
				<span class='eleRequired'>*</span>
			</li>
			<li>
				<label>乙方(经销商)：</label>
				<form:hidden path="supplier.objId"></form:hidden>
				<form:input path="supplier.orgName" cssClass="sysicon siSearch required"></form:input>
				<span class='eleRequired'>*</span>
			</li>
			<li class="fullLine">
				<label>协议期间：</label>
				<form:hidden path="period.objId"></form:hidden>
				<form:input path="period.periodName" cssClass="sysicon siSearch required"></form:input>
				<span class='eleRequired'>*</span>
			</li>
			<li class="fullLine">
				<label>协议区间：</label>
				<form:hidden path="areaIds"></form:hidden>
				<form:textarea path="areaNames" cssClass="required" cssStyle="width:400px;"/>
				<a href="javascript:void(0);" onclick="AgreementForm.selectArea();"><span class="sysicon siSearch ">&nbsp;&nbsp;</span></a>
				<span class='eleRequired'>*</span>
			</li>
			<li class="fullLine">
				<label>协议文件：</label>
				<div id="agreementFile" class="uploadFile" value="${agreement.agreementFile}"></div>
			</li>
			<li class="formTextarea">
				<label>备注：</label>
				<form:textarea path="memo" cols="35" rows="7"/>
			</li>
		</ul>
	</div>
	</form:form>
   	
   	<div class="conOperation center">
	  	<button  id="purchaseAgreementSave" type="button" ><span><spring:message code="globe.save"/></span></button>
	  	<button  id="purchaseAgreementNext" type="button" ><span><spring:message code="globe.next"/></span></button>
	  	<button  id="purchaseAgreementReturn" type="button" ><span><spring:message code="globe.return"/></span></button>
	</div>
   	

