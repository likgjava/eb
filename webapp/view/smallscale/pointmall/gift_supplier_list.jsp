<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/smallscale/pointmall/gift_supplier_list.js"></script>
</head>
<body>	
	<!--搜索条件 -->
	<div class="conSearch">
		<h4><span>搜索</span></h4>
		<form id="giftSupplierSearchForm">
			<ul>
				<li>
					<label for="title">供货商名称</label>	  	        
             		<input type="text" name="supplierName" id="supplierName">
             		<input type="hidden" name="supplierName_op" value="like">
				</li>
				<li>
					<label>开始-结束时间：</label>
					<input name="startTime" id="startTime">&nbsp;&nbsp;到
	        		<input name="endTime" id="endTime">
				</li>
			    			
				<li class="operationBtnDiv">
			        <button id = "giftSupplierSearch" type="button"><span>查询</span></button>
			    </li>
			</ul>
		</form>
	</div>
	
	<div class="formTips attention">
		<ul>			
			<li>			
				新增礼品供货商请点击
				<span class="sysicon siAdd"><a href="javascript:void(0);" id="giftSupplierAdd"><strong>新增礼品供货商</strong></a></span>
			</li>
		</ul>
	</div>
	
	<!--tab页列表 -->
	<div id="epsTabs" class="epsTabs">
		<ul>
			<li>
				<a href="#giftSupplierInfo" id="tabs_manager" class="refreshData"><span>礼品供货商管理</span></a>
			</li>
		</ul>
		<div id="giftSupplierInfo">
			<table class="frontTableList" id="giftSupplierList">
				<thead>
					<tr>
						<th class="left">供货商名称</th>
						<th class="datetime">开始时间</th>
						<th class="datetime">结束时间</th>
						<th class="left">启用状态</th>
						<th class="operation">操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
