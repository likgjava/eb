<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/bizdata/bargain/bargain_purchaser_list.js"></script>
 
<!-- 查询条件 -->
<!--<div class="formLayout form2Pa">-->
<div class="conSearch">
  <h4><span>查询条件</span></h4>
  <form id="bargainPurchaserForm">
  <ul>
  	<li>
  		<label for="input01">议价编号：</label>
        <input name="bargainNo" type="text" >
  	</li>
  	<li>
  		<label for="input01">创建时间：</label>
  		<input name="startDate" id="startDate">&nbsp;到
  		<input name="endDate" id="endDate">
  	</li>
  	<li>
  		<label for="input01">项目名称：</label>
  		<input name="bargainName" type="text" >
  	</li>
  	 <li class="operationBtnDiv" style="text-align:right"> 
        <button type="button" id="query"><span>搜索</span></button>
    </li>
  </ul>
  </form>
</div>

<!-- Tab页 -->
<div id="epsTabs" class="">
  <ul>
  	<li>
      <a href="#myBargainPurchaserListInfo" id = "tabs_tostart" class="refreshData"><span>待启动的议价</span></a>
    </li>
    <li>
      <a href="#myBargainPurchaserListInfo" id = "tabs_bargaining" class="refreshData" ><span>正在进行中的议价</span></a>
    </li>
    <li>
      <a href="#myBargainPurchaserListInfo" id = "tabs_done" class="refreshData" ><span>已完成的议价</span></a>
    </li>
  </ul>
  
  <div id="myBargainPurchaserListInfo">
    <!-- 议价列表 -->
    <table class="frontTableList" id="BargainPurchaserList">
      <thead>
      	<tr>
          <th class="center">议价编号</th>
          <th>项目名称</th>
          <th class="omission">供应商名称</th>
          <th class="center date">创建时间</th>
          <th class="operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</div>
        
        

 
