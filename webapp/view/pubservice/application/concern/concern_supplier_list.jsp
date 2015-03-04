<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/application/concern/concern_supplier_list.js"></script>
 
<!-- 查询条件 -->
<input type="hidden" id="curUserId" value="${curUserId}"/>

<div class="conSearch">
    <h4><span><spring:message code="globe.query"/></span></h4>
    <form id="concernListForm">
		<ul>
			<li>
				<label for="input01">供应商名称：</label>
				<input name="orgInfo.orgName" type="text" >
				<input type="hidden" name="orgInfo.orgName_op" value="like">
			</li>
			<li>
				<label for="input01">所属分组：</label>
				<select name="concernGroup.objId" id="" >
					<option value="">-请选择-</option>
					<c:choose>
						<c:when test="${concernGroupList != null && fn:length(concernGroupList) > 0}">
							<c:forEach var="concernGroup" items="${concernGroupList}" varStatus="status">
							<option value="${concernGroup.objId}">${concernGroup.groupName}</option>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<li>没有记录!</li>
						</c:otherwise>
					</c:choose>
				</select>
  				<input name="concernGroup.objId_op" id="" type="hidden" value="like">
			</li>
			<li class="operationBtnDiv" style="text-align:right">
		      <button type="button" id = "query"><span>搜索</span></button>
		  </li>
		</ul>
    </form>
</div>

<div class="formTips attention">
	<ul>
		<li>
			<em class="hidden">新增关注供应商请点击
			<span class="sysicon siAdd"><a id="createConcernBtn" href="javascript:void(0);"><strong>关注供应商</strong></a></span>
			</em>
			<em>管理供应商分组请点击
			<span class="sysicon siAdd"><a id="editConcernGroupBtn" href="javascript:void(0);"><strong>管理供应商分组</strong></a></span>
			</em>
		</li>
	</ul>
</div>

<!-- Tab页 -->
<div id="epsTabs" class="">
  <ul>
    <li>
      <a href="#concernListInfo" id = "listType_01"><span>短名单</span></a>
    </li>
    <li>
      <a href="#concernListInfo" id = "listType_02"><span>长名单</span></a>
    </li>
    <li>
      <a href="#concernListInfo" id = "listType_03"><span>黑名单</span></a>
    </li>
  </ul>
  
  <div id="concernListInfo">
    <!-- 订单列表 -->
    <table class="frontTableList" id="ConcernList">
      <thead>
      	<tr>
          <th>供应商</th>
          <th >所属分组</th>
          <th class="center date">关注时间</th>
          <th class="center">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</div>
        
        

 
