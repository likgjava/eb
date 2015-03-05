<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script type="text/javascript" src="<%=request.getContextPath()%>/view/smallscale/vote/vote_template_list.js"></script>
	</head>
	<body>
		<!--搜索条件 -->
		<div class="conSearch">
			<h4><span>搜索</span></h4>
			<form id="voteTemplateSearchForm">
				<ul>
					<li>
						<label for="title">评选主题</label>	  	        
	             		<input type="text" name="templateName" id="templateName" class="sysicon">
	             		<input type="hidden" name="templateName_op" value="like">
					</li>
					<li>
						<label>评价专员:</label>
					 	<input type="text" id="appraiseCommissioner" name="appraiseCommissioner" class="sysicon">
					 	<input type="hidden" name="appraiseCommissioner_op" value="like">
					</li>				    			
					<li class="operationBtnDiv">
				        <button id = "voteTemplateSearchBtn" type="button"><span>查询</span></button>
				    </li>
				</ul>
			</form>
		</div>
		
		<div class="formTips attention">
			<ul>			
				<li>			
					新增投票评选主题请点击
					<span class="sysicon siAdd"><a href="javascript:void(0);" id="voteTemplateAdd"><strong>新增投票评选主题</strong></a></span>
				</li>
			</ul>
		</div>
		<!--tab页列表 -->
		<div id="epsTabs" class="epsTabs">			
			<div id="voteTemplateListInfo">
				<table class="frontTableList" id="voteTemplateList">
					<thead>
						<tr>
							<th class="left">评选主题</th>
							<th class="date">开始时间</th>
							<th class="date">结束时间</th>							
							<th class="date">创建时间</th>
							<th class="left">投票类型</th>
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