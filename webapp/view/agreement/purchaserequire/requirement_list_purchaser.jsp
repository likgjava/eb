<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/purchaserequire/requirement_list_purchaser.js"></script>
<div>
	<!-- 搜索条件 -->
	<div class="conSearch">
		<h4><span>搜索</span></h4>
		<form id="requirementSearchForm" >
			<ul>
			    <li>
					<label>标题：</label>
					<input type="text" name="title" value="">
					<input type="hidden" name="title_op" value="like">
			    </li>
			    <li>
					<label>审核状态：</label>
					<select id="auditStatus" name="auditStatus">
						<option value="">所有</option>
						<option value="00">草稿</option>
						<option value="01">待审核</option>
						<option value="02">通过</option>
						<option value="03">不通过</option>
					</select>
			    </li>
			    <li class="operationBtnDiv">
			        <button id = "requirementSearch" type="button" onclick="requirement_list.getRequirementList();"><span>查询</span></button>
			    </li>
		  </ul>
		 </form>
	</div>
</div>

<div class="formTips attention">
<ul>
	<li>
	发布新的采购需求请点击 <span class="sysicon siAdd"><a id="addRequirementBtn" href="<%=request.getContextPath()%>/RequirementController.do?method=toChooseCategory" target="_blank"><strong>起草采购需求</strong></a>
	</span>
	
	<span id="batCreateProjectSpan">&nbsp;由需求创建
		<a id="bargin" href="javascript:void(0);" onclick="{if(requirement_list.batConfrim()){requirement_list.batCreateBargin(requirement_list.oTable.dtSelects());}}"><strong>竞价项目</strong></a>&nbsp;或
		<a id="talkin" href="javascript:void(0);" onclick="{if(requirement_list.batConfrim()){requirement_list.batCreateTalkin(requirement_list.oTable.dtSelects());}}"><strong>议价项目</strong></a>
		,注：每个需求只能创建一次项目
	</span>
	</li>
</ul>
</div>


<div id="epsTabs">
<ul>
	<li>
		<a href="#requirement" id = "tabs_pub" class="refreshData"><span>已发布</span></a>
	</li>
	<li>
		<a href="#requirement" id = "tabs_nopub" class="refreshData"><span>未发布</span></a>
	</li>
</ul>

<div id="requirement">
<!-- 需求列表 -->
	<div id="tabDemo">
		<table class="frontTableList" id="requirementList">
	      <thead>
	        <tr>
	          <th class="left omission">标题</th>
	          <th class="left">品目</th>
	          <th class="left">采购数量</th>
	          <th class="left">单个预算（元）</th>
	          <th class="left">区域</th>
	          <th class="left">结束时间</th>
			  <th class="center">审核状态</th>
			  <th class="left">已报名</th>
	          <th class="operation">操作</th>
	        </tr>
	      </thead>
      	  <tbody>
      	  </tbody>
		</table>
	</div>
</div>

</div>
