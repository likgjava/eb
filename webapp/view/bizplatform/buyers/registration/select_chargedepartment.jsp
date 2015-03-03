<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/bizplatform/buyers/registration/select_chargedepartment.js"></script>

<div class="conSearch">
	 <form id="BuyerChargeDepartmentForm" >
	 <input type="hidden" id="Hname" value="${param.Hname}"/>
	 <input type="hidden" id="Hid" value="${param.Hid}"/>
	 <!-- orgType : m, s ,b ,a -->
	 <input type="hidden" id="orgType" value="${param.orgType}"/>
	 
	 	<ul>
			 <li>
			     <label for="orgName">上级单位名称：</label>
			     <input type="text" name="orgName" id="orgName">
			     <input type="hidden" name="orgName_op" value="like">
			 </li>
	         <li class="operationBtnDiv">
					 <button type="button" id="query"><span>查询</span></button>
		 	 </li>
	    </ul>
	 </form>
</div>

<table class="frontTableList" id="buyerSuperList">
		<thead>
			<tr>
				<th class="left">组织机构代码</th>
				<th class="left omission">机构名称</th>
				<th class="center">联系人</th>
				<th class="date center">生效日期</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
</table>

<div class="conOperation">
	<button id="close" type="button"  class="largeBtn" ><span><spring:message code="globe.close"/></span></button>
</div>
