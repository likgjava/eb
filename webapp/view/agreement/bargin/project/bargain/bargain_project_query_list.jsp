<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>



<%@page import="com.gpcsoft.plugin.acegi.AuthenticationHelper"%><style type="text/css">
.closeBtn {
    background-image: url("view/resource/skin/bizplatform/img/closeBtn.gif");
    background-position: left center;
    background-repeat: no-repeat;
    line-height: 18px;
    padding-left: 20px;
    text-indent: 18px;
}

.openBtn {
    background-image: url("view/resource/skin/bizplatform/img/openBtn.gif");
    background-position: left center;
    background-repeat: no-repeat;
    line-height: 18px;
    padding-left: 20px;
    text-indent: 18px;}

</style>


<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/bargin/project/bargain/bargain_project_query_list.js"></script>

<input type="hidden" name="companyId" id="companyId" value="${companyId}">

<input type="hidden" name="orgInfoId" id="orgInfoId" value="${orgInfoId}">

<input type="hidden" name="subOrgCount" id="subOrgCount" value="${subOrgCount}"/>

<div class="treePage frameMainSub frameMs12 fullScreen">
	<c:if test="${subOrgCount>0}">
	<!-- 组织机构树 -->
	<div class="treeOutside frameMain">
		<div id="orgnizationTreeGrid" style="overflow-x: auto;overflow-y: hidden;" class="treeContentDiv"></div>
	</div>
	</c:if>
	
	<!-- 项目信息 -->
	<div class="treeRight${subOrgCount>0?' frameSub':''}">

	<div class="conSearch">
	<form id="projectSearchForm">
	<!-- 搜索条件 -->
		<h4><span>搜索</span></h4>
		<ul>
				<li>
					<label>项目名称：</label>
					<input name="projName">
				</li>
				<li>
					<label>项目编号：</label>
					<input name="projCode">
				</li>
				<li>
					<label>供应商名称：</label>
					<input name="supplierName">
				</li>
				<li>
					<label>项目开始：</label>
					<input name="startTime" id="startTime">&nbsp;至：<input name="endTime" id="endTime">
				</li>
				<li>
					<label>项目状态：</label>
					<select name="status" id="status">
						<option value="other">所有</option>
						<option value="bargaining">进行中</option>
						<option value="nostart">未开始</option>
						<option value="bargained">已结束</option>
					</select>
				</li>
				
			    <li class="operationBtnDiv">
			        <button type="button" onclick="BargainProjectQueryList.getTableList('${orgInfoId}');"><span>查询</span></button>
			    </li>
		  </ul>
	</form>
	</div>
	
	<div class="formTips attention">
		<ul>
			<li>
				<em>创建竞价项目请点击
				<span class="sysicon siAdd" id="createProjectSpan"><a id="createBargainProjectBtn" href="javascript:void(0);"><strong>创建竞价项目</strong></a></span>
				</em>
			</li>
		</ul>
	</div>
	
	<!-- Tab页 -->
	<div id="epsTabs" class="">
	  
	  <div id="reverseProjectListInfo">
	    <table id="BargainProjectQueryList" class="frontTableList">
	      <thead>
	      	<tr>
	      	  <th class="operation" style="min-width:0px;"><span class="openBtn" id="allOpenOrClose" onclick="BargainProjectQueryList.closeOrOpenAll(this);" title="全部展开/全部关闭">&nbsp;</span></th>
	          <th width="150px;" class="omission" omiLength="10">项目名称</th>
	          <th class="omission">采购方式</th>
	          <th class="center">立项时间</th>
	          <th class="center">报名数</th>
	          <th class="center">中标总金额</th>
	          <th class="center">节省</th>
	          <th class="center">状态</th>
	          <th class="operation">操作</th>
	      	</tr>
	      </thead>
	      <tbody>
	      </tbody>
	    </table>
	  </div>
	</div>

</div>
</div>
        
        

 
