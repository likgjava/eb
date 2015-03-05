<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/smallscale/point/exchange_manager.js"></script>
<!-- 查询条件 -->
<!--<input type="text" id="schic"/>-->
<div class="conSearch">
  <h4><span class="detailsSwitch">我的积分</span></h4>
  <table width="85%" align="center">
       <tr>
          <td width="20%" align="right"><b>历史总积分：</b></td>
          <td width="5%"></td>
          <td><strong><fmt:formatNumber value="${history}" pattern="#,###.###" type="number"/></strong></td>
       </tr>
       <tr>
          <td width="20%" align="right"><b>当前有效积分：</b></td>
          <td width="5%"></td>
          <td><strong><fmt:formatNumber value="${nonce}" pattern="#,###.###" type="number"/></strong></td>
       </tr>
    </table>
</div>
<div class="formTips attention">
	<ul>
		<li>
			<em>注意：</em>
			积分管理
			<!--<span class="sysicon siAdd">-->
				<a href="javascript:void(0);" id="cashBtn"><strong>兑现积分</strong></a>
				<a href="javascript:void(0);" id="dealBtn"><strong>交易积分</strong></a>
            <!--</span>.-->
		</li>
	</ul>
</div>
<!-- Tab页 -->
<div id="epsTabs" class="">
  <ul>
  	<li>
      <a href="#orderPurchaserListInfo1" id = "tabs_toSubmit" class="refreshData"><span>积分管理</span></a>
    </li>
    <li>
      <a href="#orderPurchaserListInfo2" id = "tabs_toSure" class="refreshData"><span>兑现积分</span></a>
    </li>
    <li>
      <a href="#orderPurchaserListInfo3" id = "tabs_toAdjust" class="refreshData"><span>交易积分</span></a>
    </li>
    <li>
      <a href="#orderPurchaserListInfo4" id = "tabs_done" class="refreshData"><span>消费积分</span></a>
    </li>
  </ul>
  <div id="orderPurchaserListInfo1">
    <!-- 积分表 -->
    <table class="frontTableList" id="OrderPurchaserList1">
      <thead>
      	<tr>
          <th class="center">获取方式 </th>
          <th class="center amount">积分额度</th>
          <th class="center money">购买金额（￥/元）</th>
          <th class="center date">获得日期</th>
          <th class="center date">有效日期</th>
          <th class="center">状态</th>
		  <th class="center operation">操作</th>	
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
  
  <div id="orderPurchaserListInfo2">
<!--  	<div class="functionBtnDiv">-->
<!--    	<span class="showContract"><input type="checkbox" id="onlyNoContract" >只显示未起草合同订单</span>-->
<!--  		<button id="addContract" class="add"><span>起草合同</span></button>-->
<!--    </div>-->
    <!-- 兑现表 -->
    <table class="frontTableList" id="OrderPurchaserList2">
      <thead>
      	<tr>
          <th class="center date">兑现日期</th>
          <th class="center amount">兑换积分额度</th>
          <th class="center money">兑现金额（￥/元）</th>
          <th class="center operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>

  <div id="orderPurchaserListInfo3">
    <!-- 交易表 -->
    <table class="frontTableList" id="OrderPurchaserList3">
      <thead>
      	<tr>
          <th class="center date">交易日期</th>
          <th class="center amount">交易积分额度</th>
          <th class="center">谁赠送</th>
          <th class="center">赠送给</th>
          <th class="operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>

  <div id="orderPurchaserListInfo4">
    <!-- 消费表 -->
    <table class="frontTableList" id="OrderPurchaserList4">
      <thead>
      	<tr>
          <th class="center">消费类型</th>
          <th class="center date">消费日期</th>
          <th class="center amount">消费额度</th>          
          <th class="center operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</div>
        