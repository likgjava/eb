<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/purchaserequire/requirement_list_manager.js"></script>
<div>
	<!-- 搜索条件 -->
	<div class="conSearch">
		<h4><span>搜索</span></h4>
		<form id="requirementSearchForm" >
			<ul>
			    <li>
					<label>标签搜索：</label>
					<input type="text" name="title" value="">
					<input type="hidden" name="title_op" value="like">
			    </li>
			    
			    <li>
					<label>发布机构：</label>
					<input type="text" name="pubOrg.orgName" value="">
					<input type="hidden" name="pubOrg.orgName_op" value="like">
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
	<span id="batPublishBulletinSpan">注意：发布需求的机构需要机构信息审核通过后才能对外发布<br></span>
	发布新的采购需求请点击 <span class="sysicon siAdd"><a id="addRequirementBtn" href="javascript:void(0);" onclick="requirement_list.toCreateOrUpdate();"><strong>起草采购需求</strong></a>
	</span>
	<span id="batPublishBulletinSpan">&nbsp;在此批量
		<a id="batPubRequirement" href="javascript:void(0);" class="hidden" onclick="{if(requirement_list.batConfrim()){requirement_list.batPubRequirement(requirement_list.oTable.dtSelects());}}"><strong>发布需求</strong></a>
		<a id="batAudRequirement" href="javascript:void(0);" class="hidden" onclick="{if(requirement_list.batConfrim()){requirement_list.batAudRequirementHref();}} "><strong>审核需求</strong></a>
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
			<li>
				<a href="#requirement" id = "tabs_to_pass" class="refreshData"><span>待审核</span></a>
			</li>
		</ul>
  		<div id="requirement">
			<!-- 需求列表 -->
			<div id="tabDemo">
				<table class="frontTableList" id="requirementList">
			      <thead>
			        <tr>
			          <th class="left omission">标题</th>
			          <th class="left">发布机构</th>
			          <th class="left">品目</th>
			          <th class="left">采购数量</th>
			          <th class="left">单个预算（元）</th>
			          <th class="left">区域</th>
			          <th class="left">结束时间</th>
			          <th class="left">待审/已审报名</th>
			          <th class="operation">操作</th>
			        </tr>
			      </thead>
		      	  <tbody>
		      	  </tbody>
				</table>
			</div>
		</div>
</div>