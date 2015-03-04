<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/application/message/message_list.js"></script>
<input id="currentUserId" type="hidden" value="${currentUserId}" />
<input id="username" type="hidden" name="username" value="${username}" />
<input id="isManager" type="hidden" name="isManager" value="${isManager}" />

<!-- 搜索条件 -->
<div>
	<div class="conSearch">
		<h4><span>搜索</span></h4>
		<form id="messageSearchForm" >
			<ul>
			    <li>
					<label for="title">站内信标题：</label>
					<input type="text" name="title" value="">
					<input type="hidden" name="title_op" value="like">
			    </li>
			    <li class="operationBtnDiv">
			        <button id = "messageSearch" type="button" onclick="messageList.getMessageList()"><span>查询</span></button>
			    </li>
		  </ul>
		 </form>
	</div>
</div>

<!-- 发送站内信 -->
<div class="formTips attention">
	<ul>
		<li>
			发送站内信请点击
			<c:if test="${isManager}">
				<span class="sysicon siAdd">
					<a id="addSystemMessage" style="hide-focus: true" href="javascript:void(0);"><strong>发送系统站内信</strong></a>
				</span>
			</c:if>
			<span class="sysicon siAdd">
					<a id="addUserMessage" style="hide-focus: true" href="javascript:void(0);"><strong>发送私人站内信</strong></a>
			</span>   
			批量删除请点击
			<span class="sysicon siDelete">
				<a style="hide-focus: true" href="javascript:void(0);" onclick="messageList.batchDelete();"><strong>批量删除</strong></a>
			</span>
		</li>
	</ul>
</div>

<!-- Tab页 -->
<div id="epsTabs">
	<ul>
		<li><a href="#messageListInfo" id="tabs_toSystem" class="refreshData"><span>系统站内信</span></a></li>
		<li><a href="#messageListInfo" id="tabs_toUser" class="refreshData"><span>私人站内信</span></a></li>
		<li><a href="#outboxListInfo" id="tabs_toOutbox" class="refreshData"><span>发件箱</span></a></li>
	</ul>
	<!-- 站内信列表[收件箱] -->
 	<div id="messageListInfo">
		<table class="frontTableList" id="QualityList">
			<thead>
				<tr>
					<th class="center">状态</th>
					<th class="left">标题</th>
					<th class="center">发信人</th>
					<th class="center">接收时间</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<!-- 发件箱列表 -->
	<div id="outboxListInfo">
		<table class="frontTableList" id="OutboxList">
			<thead>
				<tr>
					<th class="left">标题</th>
					<th class="center">收信人</th>
					<th class="center">发送时间</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>