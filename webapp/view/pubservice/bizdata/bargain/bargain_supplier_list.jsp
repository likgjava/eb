<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/bizdata/bargain/bargain_supplier_list.js"></script>
 
<!-- 查询条件 -->

<div class="conSearch">
    <h4><span><spring:message code="globe.query"/></span></h4>
    <form id="BargainSupplierListForm">
		<ul>
			<li>
				<label for="input01">议价编号：</label>
		      <input type="text" name="bargainNo" />
			</li>
			<li>
				<label for="input01">创建时间：</label>
				<input name="startDate" id="startDate" type="text" >&nbsp;&nbsp;&nbsp;&nbsp;到&nbsp;
  				<input name="endDate" id="endDate" type="text" >
			</li>
			<li>
				<label for="input01">项目名称：</label>
				<input name="bargainName" type="text" >
			</li>
			<li class="operationBtnDiv" style="text-align:right">
		      <button type="button" id = "query"><span>搜索</span></button>
		  </li>
		</ul>
    </form>
</div>
<!-- Tab页 -->
<div id="epsTabs">
  <ul>
    <li>
      <a href="#BargainSupplierListInfo" id="tabs_bargaining" class="refreshData"><span>正在进行中的议价</span></a>
    </li>
    <li>
      <a href="#BargainSupplierListInfo" id="tabs_done" class="refreshData"><span>已完成的议价</span></a>
    </li>
  </ul>
  
  <div id="BargainSupplierListInfo">
    <!-- 订单列表 -->
    <table class="frontTableList" id="BargainSupplierList">
      <thead>
      	<tr>
          <th class="center">议价编号</th>
          <th >项目名称</th>
          <th class="omission">采购人</th>
          <th class="center date">创建时间</th>
          <th class="operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</div>
        
        

 
