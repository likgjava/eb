<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/application/message/consulation_deal_List.js"></script>
</head>
<body>
	<!-- 搜索条件 -->
	<div class="conSearch">
		<h4><span>咨询搜索</span></h4>
		<form id="consulationSearchForm" >
			<ul>
				<li>
					<label>咨询时间：</label>
					<input name="startDate" id="startDate">&nbsp;&nbsp;到
	        		<input name="endDate" id="endDate">
				</li>
			    <li class="operationBtnDiv">
			        <button id = "consulationSearch" type="button"><span>查询</span></button>
			    </li>
		  </ul>
		 </form>
	</div>
	
	<input type="hidden" id="consulationLeaver" value="${noteUser}"/>
	<div id="epsTabs" class="epsTabs">
		<ul>
			<li><a href="#consulationInfo" id = "tabs_receive" class="refreshData"><span>未回复咨询</span></a></li>
			<li><a href="#consulationInfo" id = "tabs_done" class="refreshData"><span>已回复咨询</span></a></li>			
		</ul>
	
		<div id="consulationInfo">
			<div id="consulationDemo">
				<table class="frontTableList" id="consulationList">
					<caption><span></span></caption>
					<thead>
			 		  <tr>
			 		  	 <th class="left">阅读状态</th>
			 		  	 <th class="left">是否回复</th>		   					         
			   			 <th class="left omission">咨询内容</th>			         
			   			 <th class="left">咨询时间</th>
			   			 <th class="left">咨询人</th>	
			    		 <th class="operation">操作</th>
			  		 </tr>
					</thead>
		     		<tbody>
		     		</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
