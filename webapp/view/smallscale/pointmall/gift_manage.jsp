<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/smallscale/pointmall/gift_manage.js"></script>
<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query"/></span></h4>	
<form id="GiftManageSearchForm">
	<ul >
		<li>
			<label>礼品名称：</label>
			<input name="giftName" id="giftName">
			<input type="hidden" name="giftName_op" id="giftName_op" value="like">
		</li>
		<li>
			<label>礼品系列：</label>
			<input name="giftSeries.name" id="giftSeries.name">
			<input type="hidden" name="giftSeries.name_op" id="giftSeries.name_op" value="like">
		</li>
		<li>
			<label>创建时间：</label>
			<input type="text" name="startDate" id="startDate">&nbsp;到<input type="text" name="endDate" id="endDate">
		</li>
		<li>
			<label>礼品类型：</label>
			<select name="giftType">
				<option value="">所有</option>
				<option value="00">实物</option>
				<option value="01">虚拟</option>
			</select>
		</li>
		<li class="operationBtnDiv">
			<button type="button" onclick="GiftManageList.getTableList();"><span><spring:message code="globe.query" /></span></button>
		</li>
	</ul>
</form>
</div>
<div class="formTips attention">
	<ul>
		<li>
			新增礼品请点击
			<span class="sysicon siAdd"><a  href="javascript:GiftManageList.toCreateOrUpdate();"><strong>新增礼品</strong></a></span>.
			在此进行批量
			<a href="javascript:void(0);" id="removeGiftBtn" onclick="GiftManageList.deleteGift(GiftManageList.oTable.dtSelects());"><strong>删除礼品</strong></a>
			&nbsp;
			<a href="javascript:void(0);" id="removeGiftBtn" onclick="GiftManageList.recommendGift(GiftManageList.oTable.dtSelects(),'1');"><strong>设为推荐礼品</strong></a>
		</li>
	</ul>
</div>
<div id="epsTabs">
  <div id="giftInfo">
	<table class="frontTableList" id="giftManageList">
		<thead>
			<tr>
				<th class="operation">图片</th>
				<th class="left omission" omiLength="15">礼品名称</th>
				<th class="left omission" omiLength="15">礼品系列</th>
				<th class="center">礼品类型</th>
				<th class="center">兑换总数</th>
				<th class="center">启用状态</th>
				<th class="date">创建时间</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
  </div>
</div>
