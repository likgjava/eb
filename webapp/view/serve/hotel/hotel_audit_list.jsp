<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/serve/hotel/hotel_audit_list.js"></script>
<input type="hidden" name="orgInfoId" id="orgInfoId" value="${orgInfoId }"/>
<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span><span class="detailsSwitch" id="hightSearchSwitch">高级搜索</span></h4>	
<form:form id="HotelSearchForm" modelAttribute="hotel">
	<ul >
	 <li>
       <label> 酒店名称：</label>
         <input type="text" name="hotelName" id="hotelName">
         <input type="hidden" name="hotelName_op" id="hotelName_op" value="like">
     </li>
     <li class="hightSearch">
       	<label>创建时间：</label>
		<input type="text" name="startDate" id="startDate">&nbsp;到<input type="text" name="endDate" id="endDate">
     </li>
     <li class="hightSearch">
          <label>所在区域：</label>
          <form:select path="district.parent.parent.objId" id="province">
          		<form:option value="">请选择</form:option>
          	<form:options items="${province}" itemValue="code" itemLabel="name"/> 
          </form:select>
          <form:select path="district.parent.objId" id="city">
          	<form:options items="${city}" itemValue="code" itemLabel="name"/> 
          </form:select>
          <form:select path="district.objId" id="district.objId" cssClass="required">
          	<form:options items="${town}" itemValue="code" itemLabel="name"/> 
          </form:select>
          <span class="eleRequired">*</span>
	</li>
      

       <li class="operationBtnDiv">
		 <button type="button" id="queryHotel"><span><spring:message code="globe.query" /></span></button>
		</li>
    </ul>
</form:form>
</div>
<div id="epsTabs">
  <ul>
    <li>
      <a href="#hotelListPage" id = "auditStatus_01" class="refreshData"><span>待审核</span></a>
    </li>
    <li>
      <a href="#hotelListPage" id = "auditStatus_02" class="refreshData"><span>已通过</span></a>
    </li>
  </ul>
  <div id="hotelListPage">
	<table class="frontTableList" id="hotelList">
		<thead>
			<tr>
				<th class="operation">图片</th>
				<th class="omission" omiLength="10">酒店名称</th>
				<th class="omission" omiLength="15">所属机构</th>
				<th class="omission" omiLength="10">区域</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
  </div>
</div>
