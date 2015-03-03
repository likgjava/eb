<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/mamagement/vender_qualification_form.js"></script>

<%
String vendername = null;
if(null!=request.getParameter("vendername")){
     vendername = new String(request.getParameter("vendername").getBytes("ISO-8859-1"),"gbk");
}
%>

<input type="hidden" name="agreementId" id="agreementId" value="<c:out value="${param.agreementId}"/>"/>
<input type="hidden" name="agreeSecondId" id="agreeSecondId" value="<c:out value="${param.agreeSecondId}"/>"/>   

<input type="hidden" name="resource" id="resource" value="<c:out value="${param.resource}"/>"/>   
<input type="hidden" name="vendername" id="vendername" value="<%=vendername%>"/>   

<div class="formLayout form2Pa">
	<form:form id="agreementForm" method="post" modelAttribute="agreement">
	<form:hidden path="objId"></form:hidden>
    <h4><span>协议信息</span></h4>
    <ul>
        <li>
            <label>协议名称：</label>
            <span id="name">${agreement.name}</span>
        </li>   
        <li>
            <label>签订时间：</label>
            <span id="creTime">${agreement.creTime }</span>
        </li>
        <li>
            <label>甲方(代理机构)：</label>
            <span id="org.orgName">${agreement.org.orgName }</span>
        </li>    
        <li>
            <label>乙方(经销商)：</label>
            <span id="org.orgName">${agreement.supplier.orgName }</span>
        </li>      
        <li class="fullLine">
            <label>协议期间：</label>
           	<form:hidden path="period.beginDate"></form:hidden>
     	    <form:hidden path="period.endDate"></form:hidden>
            <span id="period.periodName">
            <fmt:formatDate value="${agreement.period.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;至&nbsp;
            <fmt:formatDate value="${agreement.period.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </span>
        </li>
        <li>
            <label>协议区间：</label>
            <span id="area.areaName">${agreement.areaNames }</span>
			<form:hidden path="area.objId"></form:hidden>
        </li>
        <li >
            <label>备注：</label>
            <span id="memo">${agreement.memo }</span>
        </li>
    </ul>
    </form:form>
</div>
    <div class="formLayout form1Pa">
		<form id="SupplierQualify" method="post">
			<input type="hidden" name="agreement.objId" id="agreement.objId" value="<c:out value="${agreement.objId}"/>"/>
			<h4><span>供货商资格</span></h4>
			<ul>
				<li class="fullLine">
					<label>协议名称：</label>
					<input type="text" size="40" name="name" id="name" class="required"/>
					<span class='eleRequired'>*</span>
				</li>
				<li>
					<label>供货商：</label>
		            <input type="hidden" size="40" name="supplyer.objId" id="supplyId" class = "required"/>
		            <input type="text" size="40" name="supplyer.orgName" id="supplyname" class="required sysicon siSearch"/>
		            <span class='eleRequired'>*</span>
				</li>
				<li>
					<label>供货区间：</label>
					<input type="hidden" name="area.objId" id="areaId" value="" size="20" />
					<input type="text" size="40" name="area.areaName" id="areaName" class="required sysicon siSearch"/>
					<span class='eleRequired'>*</span>
				</li>
				<li class="fullLine">
					<label>供货期间：</label>
					<input type="text" name="beginDate" id="beginDate" />至<input type="text" name="endDate" id="endDate" />
					<span class='eleRequired'>*</span>
				</li>
				<li class="formTextarea">
					<label>商品分类：</label>
					<input type="hidden" id="goodsClassId" name="goodsClassId" value="<c:out value="${param.goodsClassId}"/>" >
					<input type="hidden" id="goodsClassName" name="goodsClassName" >
					<input type="hidden" id="brandName" name="brandName" >
					
					<select multiple="multiple" size="5" id="goodsClass" style="width:350px"></select>
					<a href="javascript:void(0);" id="addcategory">添加</a>
					<a href="javascript:void(0);" id="delcategory">删除</a>
				</li>
				
				<li class="formTextarea">
					<label>单品：</label>
					<input type="hidden" id="goodsId" name="goodsId" value="<c:out value="${param.goodsId}"/>">
					<input type="hidden" id="goodsName" name="goodsName" >
		            <select multiple="multiple" size="5" id="goods" style="width:350px"></select>
					<a href="javascript:void(0);" id="addGoods">添加</a>
					<a href="javascript:void(0);" id="delGoods">删除</a>
				</li>
				<li>
					<label>附件：</label>
					<div id="content" class="uploadFile"></div>
				</li>
			</ul>
		</form>
		</div>
		
		<div class="conOperation">
		  	<button  id="venderQualifySave" type="button" ><span><spring:message code="globe.save"/></span></button>
		  	<button  id="venderQualifyReturn" type="button" ><span><spring:message code="globe.return"/></span></button>
		</div>
