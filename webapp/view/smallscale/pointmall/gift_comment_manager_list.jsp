<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/smallscale/pointmall/gift_comment_manager_list.js"></script>
</head>
<body>	
	<!--搜索条件 -->
	<div class="conSearch">
		<h4><span>搜索</span></h4>
		<form id="giftCommentSearchForm">
			<ul>		
				<li>
					<label>礼品名称：</label>
					<input name="gift.giftName" id="gift.giftName">
					<input type="hidden" name="gift.giftName_op" id="gift.giftName_op" value="like">
				</li>
				<li>
					<label>点评标题：</label>
					<input name="title" id="title">
					<input type="hidden" name="title_op" id="title_op" value="like">
				</li>				
				<li>
					<label>点评时间：</label>
					<input type="text" name="startDate" id="startDate">&nbsp;到<input type="text" name="endDate" id="endDate">
				</li>			    			
				<li class="operationBtnDiv">
			        <button id = "giftCommentSearch" type="button"><span>查询</span></button>
			    </li>
			</ul>
		</form>
	</div>
	
	<!--tab页列表 -->
	<div id="epsTabs" class="epsTabs">
		<ul>
			<li>
				<a href="#giftCommentInfo" id="tabs_manager" class="refreshData"><span>礼品点评管理</span></a>
			</li>
		</ul>
		<div id="giftCommentInfo">
			<table class="frontTableList" id="giftCommentList">
				<thead>
					<tr>
						<th class="left omission" omiLength="10">礼品名称</th>
						<th class="left omission" omiLength="10">评价标题</th>					
						<th class="left omission">礼品评价描述</th>
						<th class="left">评价人</th>
						<th class="date">评价时间</th>
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
