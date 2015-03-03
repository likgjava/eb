<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/mamagement/agreement_category_goods.js"></script>

<input type="hidden" name="agreementId" id="agreementId" value="<c:out value="${param.agreementId}"/>"/>

<div id="authorizedVenderTabs">
	<ul>
		<li><a href="#authorizedCategory" id="">协议分类</a></li>
		<li><a href="#authorizedGoods" id="">协议单品</a></li>
	</ul>
	
	<div id="authorizedCategory">
	    <!-- 分类选择 -->
		<div class="conSearch" >
			<div>已选择的分类：<span id="CategorySelected"></span></div>
		    <!-- tips -->
		    <div class="formTips hidden" id="tips" >
   				<input type="hidden" name="goodsIds" id="goodsIds" value=""/>
				<input type="hidden" name="AclassId" id="AclassId" value=""/>
				<input type="hidden" name="brandId" id="brandId" value=""/>
				<input type="hidden" name="classId" id="classId" value=""/>
		      <ul>
		      		<li>
				         	<a href="javascript:void(0);" id="AddNewGoods"><span class="eleRequired">该分类下有新的商品,点击此提示文字选择分类下的商品</span></a>
				    </li>
		      </ul>
     	    <button onclick="authorizedCategoryOrGoods.hiddentips()" type="button" ><span>关闭提示</span></button>
		    </div>
	    </div>
	    <table class="frontTableList" id="agreementCategoryGoodsList">
	        <thead>
	        	<tr>
	            <th class="left">商品名称</th>
	            <th class="left">商品型号</th>
	            <th class="money">市场价</th>
	            <th class="money">协议价</th>
	            <th>折扣率(%)</th>
	        	</tr>
	        </thead>
	        <tbody>
	        </tbody>
	    </table>
	    
	    <!-- 新增的商品列表 -->
	   	<table class="frontTableList hidden" id="agreementNewGoodsList">
	        <thead>
	        	<tr>
	            <th class="left">商品名称</th>
	            <th class="left">商品型号</th>
	            <th class="money">市场价</th>
	            <th class="money">协议价</th>
	            <th>折扣率(%)</th>
	        	</tr>
	        </thead>
	        <tbody>
	        </tbody>
	    </table>
	    
	</div>
	
	<div id="authorizedGoods">   
	    <table class="frontTableList" id="authorizedGoodsList">
	      <thead>
	      	<tr>
	        <th class="left">商品名称</th>
            <th class="left">商品型号</th>
            <th class="money">市场价</th>
            <th class="money">协议价</th>
            <th class="amout">折扣率(%)</th>
	      	</tr>
	      </thead>
	      <tbody>
	      </tbody>
	    </table>
	</div>
	
	<div class="conOperation">
		<button  id="" onclick="authorizedCategoryOrGoods.authorizedCategoryOrGoodsReturn()" type="button" ><span><spring:message code="globe.return"/></span></button>
	</div>
</div>
