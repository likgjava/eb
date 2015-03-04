<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/goods/goods/assignmodifier/brand_list_assignmodifier.js"></script>
<input type="hidden" name="org" id="org" value=""/>
<input type="hidden" name="_belongsId" id="_belongsId" value="${blongOrg.objId}"/>
<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>	
<form id="GoodsManageSearchForm">
	<ul >
                 <li>
                  <label for="goodsClass.goodsclassName">供应商名称：</label>
                    <input type="text" name="supplier.orgName" id="supplier.orgName">
                    <input type="hidden" name="supplier.orgName_op" value="like">
                </li>
                 <li>
                  <label for="goodsClass.goodsclassName">商品品牌：</label>
                    <input type="text" name="goodsBrand.brandName" id="goodsBrand.brandName">
                    <input type="hidden" name="goodsBrand.brandName_op" value="like">
                </li>
                 <li>
                  <label for="goodsClass.goodsClassName">商品分类：</label>
		      		<input type="text" name="goodsClass.goodsClassName" id="goodsClassName" value="" class="sysicon siSearch">
		      		<input type="hidden" name="goodsClass.goodsClassName_op" value="like">
                </li>
               
                <li class="operationBtnDiv">
       				 <button type="button" id="query"><span>查询</span></button>
     			 </li>
              </ul>
</form>
</div>


<div id="epsTabs">
  <ul>
    <li>
      <a href="#unspecifiedGoods" id = "tabs_unspecified" class="refreshData"><span>未指定维护供应商产品</span></a>
    </li>
    <li>
      <a href="#specifiedGoods" id = "tabs_specified" class="refreshData"><span>已指定维护供应商产品</span></a>
    </li>
  </ul>
  
  <div id="unspecifiedGoods">
	<table class="frontTableList" id="unspecifiedGoodsTable">
		<thead>
			<tr>
			<th class="left">商品品牌</th>
			<th class="left">商品分类</th>
			<th class="operation">指定供应商</th>
		</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
  </div>
  <div id="specifiedGoods">
	<table class="frontTableList" id="specifiedGoodsTable">
		<thead>
			<tr>
			<th class="left">商品品牌</th>
			<th class="left">商品分类</th>
			<th class="left">维护供应商</th>
			<th class="operation">修改供应商</th>
		</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
  </div>
</div>
