<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/bizplatform/organization/manager/org_quality_audit.js"/>'></script>
<!-- 查询条件 -->
<div class="conSearch">
	<form id="qualitySearchForm">
    <h4><span>搜索</span></h4>
    <ul>
      <li>
        <label  for="input01">机构名称：</label>
        <input type="text" id="org.orgName" name="org.orgName" value=""/>
        <input type="hidden" id="org.orgName_op" name="org.orgName_op" value="like"/>
      </li>
      <li>
      	<label for="input02">分类名称：</label>
      	<input type="text" id="qualificationClass.qualityName" name="qualificationClass.qualityName" />
      	<input type="hidden" id="qualificationClass.qualityName_op" name="qualificationClass.qualityName_op" value="like"/>
      </li>
      <li class="operationBtnDiv">
        	<button id="qualitySearch" type="button"><span>查询</span></button>
      </li>
    </ul>
    </form>
</div>

<div id="epsTabs">
		<ul>
			<li>
				<a href="#tabDemo" id = "tabs_new" class="refreshData"><span>待审核</span></a>
			</li>
			<li>
				<a href="#tabDemo" id = "tabs_pass" class="refreshData"><span>审核通过</span></a>
			</li>
		</ul>
  		<div id="tabDemo">
	  		<table class="frontTableList" id="QualityList">
		      <thead>
		        <tr>
		          <th class="left">机构</th>
		          <th class="left">分类</th>
		          <th class="left">定义</th>
		          <th class="center">审核状态</th>
		          <th class="operation">操作</th>
		        </tr>
		      </thead>
	      	  <tbody>
	      	  </tbody>
			</table>
  		</div>
</div>
