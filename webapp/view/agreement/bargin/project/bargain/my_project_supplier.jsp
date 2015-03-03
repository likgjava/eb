<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>


<style type="text/css">
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


<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/bargin/project/bargain/my_project_supplier.js"></script>

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
				<label>采购人名称：</label>
				<input name="buyerName">
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
					<option value="bargained">已结束</option>
					<option value="nostart">未开始</option>
				</select>
			</li>
			
		    <li class="operationBtnDiv">
		        <button type="button" onclick="BargainProjectQueryList.getTableList();"><span>查询</span></button>
		    </li>
	  </ul>
</form>
</div>

<!-- Tab页 -->
<div id="epsTabs" class="">
  
  <div id="reverseProjectListInfo">
    <table id="BargainProjectQueryList" class="frontTableList">
      <thead>
      	<tr>
      	  <th class="operation" style="min-width:0px;"><span class="openBtn" id="allOpenOrClose" onclick="BargainProjectQueryList.closeOrOpenAll(this);" title="全部展开/全部关闭">&nbsp;</span></th>
          <th width="150px;" class="omission" omiLength="10">项目名称</th>
          <th class="omission">采购人</th>
          <th class="omission">采购方式</th>
          <th class="center">立项时间</th>
          <th class="center">中标金额</th>
          <th class="center">状态</th>
          <th>操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</div>
        
        

 
