<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/goods/goods/goodsmng/goods_manage_list.js"></script>
<input type="hidden" name="org" id="org" value=""/>
<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span><span class="detailsSwitch" id="hightSearchSwitch">高级搜索</span></h4>	
<form:form id="GoodsManageSearchForm" modelAttribute="goodsClass">
			<ul >
                <li>
                  <label for="goodsClass.goodsclassName">商品品牌：</label>
                    <input type="text" name="brandName" id="brandName">
                </li>
                <li>
                  <label for="productName"> 商品名称：</label>
                    <input type="text" name="productName" id="productName">
                </li>
                <li class="hightSearch">
                  <label for="productCode">规格型号：</label>
                    <input type="text" name="productCode" id="productCode">
                </li>
                
                <li class="hightSearch">
                	<label>创建时间：</label>
		  			<input type="text" name="startDate" id="startDate">&nbsp;到
	            	<input type="text" name="endDate" id="endDate">
                </li>

                <li class="operationBtnDiv">
       				 <button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
     			 </li>
              </ul>
</form:form>
</div>
<div class="formTips attention">
	<ul>
		<li>
			<em>注意：</em><spring:message code="globe.goods.noscrappandforbid.prompt"/>
		</li>
		<li>
			新增商品请点击
			<span class="sysicon siAdd"><a id="addGoodsBtn" href="javascript:void(0);"><strong>新增商品</strong></a></span>.
			在此进行批量
			<a href="javascript:void(0);" id="removeGoodsBtn"><strong>删除商品</strong></a>
			<a href="javascript:void(0);" id="forbidGoodsBtn"><strong>禁卖商品</strong></a>
			<a href="javascript:void(0);" id="scrappGoodsBtn"><strong>报废商品</strong></a>
			<a href="javascript:void(0);" id="startGoodsBtn"><strong>启卖商品</strong></a>
		</li>
	</ul>
	<ul class="hidden right">
		<li>
			<a id="transferGoodsBtn" href="javascript:void(0);"><strong>商品数据迁移</strong></a>
		</li>
	</ul>
</div>
<div id="epsTabs">
  <ul>
    <li>
      <a href="#goodsInfo" id = "tabs_newGoods" class="refreshData"><span>新建商品</span></a>
    </li>
    <li class="hidden">
      <a href="#goodsInfo" id = "tabs_modifyGoods" class="refreshData"><span>变更商品</span></a>
    </li>
    <li>
      <a href="#goodsInfo" id = "tabs_validGoods" class="refreshData"><span>有效商品</span></a>
    </li>
    <li>
      <a href="#goodsInfo" id = "tabs_historyGoods" class="refreshData"><span>禁卖/报废商品</span></a>
    </li>
  </ul>
  <div id="goodsInfo">
  	
	<table class="frontTableList" id="goodsManageList">
		<thead>
			<tr>
				<th class="operation">图片</th>
				<th class="omission" omiLength="10">商品名称</th>
				<th class="omission" omiLength="15">规格型号</th>
				<th class="omission" omiLength="10">品牌</th>
				<th class="omission" omiLength="10">创建人</th>
				<th class="center">审核状态</th>
				<th class="center">售卖状态</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
  </div>
</div>
