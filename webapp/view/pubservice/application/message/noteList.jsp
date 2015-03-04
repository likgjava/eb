<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/application/message/noteList.js"></script>
</head>
<body>
	<!-- 搜索条件 -->
	<div class="conSearch">
		<h4><span>留言搜索</span></h4>
		<form id="noteSearchForm" >
			<ul>			   
				<li>
					<label for="isReply">是否回复：</label>
					<select name="isReply" id="isReply">
						<option value=''>所有</option>
                        <option value='true'>已经回复</option>
                        <option value='false'>没有回复</option>    
			       	</select>
			       	<input type="hidden" name="isReply_op" value="=">
				</li>
			    <li class="operationBtnDiv">
			        <button id = "noteSearch" type="button"><span>查询</span></button>
			    </li>
		  </ul>
		 </form>
	</div>
	<input type="hidden" id="noteLeaver" value="${noteUser}"/>
	<div id="epsTabs" class="epsTabs">
		<ul>
			<li><a href="#noteSendInfo" id = "tabs_send" class="refreshData"><span>发表的留言</span></a></li>
			<li><a href="#noteReceiveInfo" id = "tabs_receive" class="refreshData"><span>接收的留言</span></a></li>
		</ul>
	
		<div id="noteSendInfo">
			<div id="noteDemo">
				<table class="frontTableList" id="NoteSendList">
					<caption><span></span></caption>
					<thead>
			 		  <tr>
			 		  	 <th class="left">阅读状态</th>
			 		  	 <th class="left">是否回复</th>		 		  	   						         
			   			 <th class="left omission">留言内容</th>			         
			   			 <th class="left">留言时间</th>
			   			 <th class="left">接收人</th>			    		 	          			          
			    		 <th class="operation">操作</th>
			  		 </tr>
					</thead>
		     		<tbody>
		     		</tbody>
				</table>
			</div>
		</div>
		<div id="noteReceiveInfo">
			<div id="noteDemo2">
				<table class="frontTableList" id="NoteReceiveList">
					<caption><span></span></caption>
					<thead>
			 		  <tr>
			 		  	 <th class="left">阅读状态</th>	
			 		  	 <th class="left">是否回复</th>
			   			 <th class="left omission">留言内容</th>			         
			   			 <th class="left">留言时间</th>
			   			 <th class="left">留言人</th>			    		
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
