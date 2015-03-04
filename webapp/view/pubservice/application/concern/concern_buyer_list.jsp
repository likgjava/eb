<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/application/concern/concern_buyer_list.js"></script>
 

<input type="hidden" id="currentTab" value="${param.currentTab}"/>
<input type="hidden" id="curUserId" value="${curUserId}"/>
<input type="hidden" id="curOrgInfoId" value="${curOrgInfoId}"/>
<input type="hidden" id="isOrgManager" value="${isOrgManager}"/>
<!-- 查询条件开始 -->
<div class="conSearch">
    <h4><span><spring:message code="globe.query"/></span></h4>
    <form id="concernListForm">
		<ul>
			<li>
				<label>客户名称：</label>
				<input type="text" name="orgInfo.orgName" id="orgInfo.orgName"/>
				<input type="hidden" name="orgInfo.orgName_op" value="like"/>
			</li>
			<li>
				<label>所属分组：</label>
				<select name="concernGroup.objId" id="concernGroupSel" >
					<option value="">-请选择-</option>					
					<c:choose>
						<c:when test="${concernGroupList != null && fn:length(concernGroupList) > 0}">
							<c:forEach var="concernGroup" items="${concernGroupList}">
								<option value="${concernGroup.objId}">${concernGroup.groupName}</option>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<li>没有记录!</li>
						</c:otherwise>
					</c:choose>
				</select>
  				<input name="concernGroup.objId_op" type="hidden" value="like">
			</li>
			<li class="operationBtnDiv">
		    	<button type="button" id = "query"><span>搜索</span></button>
		  	</li>
		</ul>
    </form>
</div>
<!-- 查询条件结束 -->

<!-- 添加页面操作开始 -->
<div class="formTips attention">
	<ul>
		<li>
			<em>管理客户分组请点击
				<span class="sysicon siAdd"><a id="editConcernGroupBtn" href="javascript:void(0);"><strong>管理客户分组</strong></a></span>
			</em>
		</li>
	</ul>
</div>
<!-- T添加页面操作结束 -->

<!-- TAB页开始 -->
<div id="epsTabs">
	<ul>
		<li>
			<a href="#concernListInfo" id = "listType_01"><span>我的客户</span></a>
		</li>
		<li>
			<a href="#concernListInfo" id = "listType_02"><span>黑名单</span></a>
		</li>
		<li>
			<a href="#concernListInfo" id = "listType_03"><span>共享客户</span></a>
		</li>
		<!--
			<li>
				<a href="#concernListInfo" id = "listType_03"><span>潜在客户</span></a>
			</li>
		-->
		</ul>
</div>
<!-- TAB页结束 -->

<!-- 列表开始 -->
<div id="concernListInfo">	
	<table class="frontTableList" id="ConcernList">
		<thead>
			<tr>
				<th class="left">客户名称</th>
				<th>所属分组</th>
				<th class="center">成交量(笔)</th>
				<th class="money">成交总金额(万元)</th>
				<th class="center date">最近成交日期</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>
<!-- 列表结束 -->
