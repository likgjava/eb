<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/mamagement/choose_goods_by_goods.js"></script>

<div class="conSearch" >
  <h4><span>商品选择</span></h4>
    <ul>
	      <li class="functionBtnDiv">
		        <label  for="input01">商品品牌：</label>
		       	<input type="hidden" name="brandParam" id="brandParam">
		   		<input type="hidden" name="brandId" id="brandId">
		   		<input type="text" name="brandName" id="brandName">
		   		
		        <label  for="input02">商品分类：</label>
		        <input type="hidden" id="goodsClassParam" name="goodsClassParam" value=""/>
		       	<input type="hidden" id="goodsClassId" name="goodsClassId" value=""/>
		        <input type="text" id="goodsClassName" name="goodsClassName" value=""/>
	        
	        	<button type="button" id="chooseGoods"><span>搜索</span></button>
	      </li>
	</ul>
</div>

<div>

<div class="functionBtnDiv right">
	<button type="button" id="addGoods"><span>新增</span></button>
    <button type="button" id="goodsByGoodsDel"><span>删除</span></button>
</div>

	<table class="frontTableList" id="chooseGoodsByGoods">
        <thead>
            <tr>
                <th>商品名称</th>
                <th>商品型号</th>
                <th>市场价</th>
                <th>协议价</th>
                <th>折扣(%)</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>  
        </tbody>
    </table>
</div>

        
        

 
