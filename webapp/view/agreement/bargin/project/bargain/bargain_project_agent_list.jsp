<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/bargin/project/bargain/bargain_project_agent_list.js"></script>

<div class="conSearch">
<form id="projectSearchForm">
<!-- 搜索条件 -->
	<h4><span>搜索</span></h4>
	<ul>
			<li>
				<label>项目名称：</label>
				<input type="text" name="projName">
				<input type="hidden" name="projName_op" value="like">
			</li>
			<li>
				<label>项目编号：</label>
				<input type="text" name="projCode">
				<input type="hidden" name="projCode_op" value="like">
			</li>
			<li>
				<label>项目类型：</label>
				<select name="ebuyMethod" >
					<option value="05,06">全部</option><!-- 小额过滤 竞价和议价 -->
					<option value="06" <c:if test="${param.ebuyMethod == '06'}">selected</c:if> >竞价</option>
					<option value="05" <c:if test="${param.ebuyMethod == '05'}">selected</c:if> >议价</option>
				</select>
				<input type="hidden" name="ebuyMethod_op" value="in">
			</li>
		    <li class="operationBtnDiv">
		        <button type="button" onclick="BargainProjectList.oTable.fnDraw()"><span>查询</span></button>
		    </li>
	  </ul>
</form>
</div>

<!-- Tab页 -->
<div id="epsTabs" class="">
  <ul>
    <li>
      <a href="#reverseProjectListInfo" class="refreshData" id = "tabs_bargaining"><span>进行中的项目</span></a>
    </li>
    <li>
      <a href="#reverseProjectListInfo" class="refreshData" id = "tabs_begaining"><span>即将开始的项目</span></a>
    </li>
    <li>
      <a href="#reverseProjectListInfo" class="refreshData" id = "tabs_over"><span>已结束的项目</span></a>
    </li>
  </ul>
  
  <div id="reverseProjectListInfo">
    <table id="BargainProjectList" class="frontTableList">
      <thead>
      	<tr>
          <th class="hidden center">项目编号</th>
          <th class="omission">项目名称</th>
          <th class="omission">采购方式</th>
          <th class="center">报价开始时间</th>
          <th class="center">报价结束时间</th>
          <th class="operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</div>
        
        

 
