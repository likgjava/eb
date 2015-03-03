<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/bizplatform/base/purcatalog/catalog_add_catagory.js"/>'></script>

<input type="hidden" name="categoryId" value="${categoryId }">
<input type="hidden" name="catalogId">


<!-- 搜索 -->
<div class="conSearch">
		<h4><span>搜索</span></h4>
		<form id="catalogSearchForm" >
			<ul>
			    <li>
					<label for="title">目录标题：</label>
					<input type="text" name="title" value="">
					<input type="hidden" name="title_op" value="like">
			    </li>
			    <li>
					<label for="areaCode">区域编码：</label>
					<input type="text" name="areaCode" value="">
					<input type="hidden" name="areaCode_op" value="like">
			    </li>
			    <li>
					<label for="year">年度：</label>
					<select name="year">
						<option value="">--所有--</option>
			       	</select>
			    </li>
			    <li class="operationBtnDiv">
			        <button id = "catalogSearch" type="button"><span>查询</span></button>
			    </li>
		  </ul>
		 </form>
</div>


<div id="newcatalog">
			<div class="operationBtnDiv r" id="newBtnDiv">
				<button id="addCatalog"><span>新增目录</span></button>
			</div>
			<div id="newCatalogDiv" class="hidden"></div>
			
			<table class="frontTableList" id="catalogList">
			      <thead>
			        <tr>
			          <th class="left">目录标题</th>
			          <th class="left">区域编码</th>
			          <th class="left">区域名称</th>
			          <th class="center">发布状态</th>
			          <th class="center">年度</th>
			          <th class="operation">操作</th>
			        </tr>
			      </thead>
			      <tbody>
			      </tbody>
			</table>
</div>
		<br>
		<br>
		
	<!-- 品目树 -->
	<div class="formLayout form1Pa">
				<form id="CatalogDetailForm" method="post">
				<h4><span>采购目录信息</span></h4>
				   <input type="hidden" name="detailId"/>
				   <ul>
				   		<!-- catalogDetail -->
				   	  	<li class="fullLine">
					        <label for="goodsPrice">采购单价（元）：</label>
					        <input type="text" name="goodsPrice" id="goodsPrice" class="money"/>
				        </li>
				   	    <li class="fullLine">
					        <label for="yearTotal">年采购金额（元）：</label>
					        <input type="text" name="yearTotal" id="yearTotal" class="money"/>
				       	</li>
				       	<!-- catalogType -->
				       	<li class="fullLine">采购方式明细</li>
				    </ul>
				    <ul>
						<li id="1">
				    		<label>采购方式：</label>
				    		<html:select selectedValue="0" id="type" name="procType" code="buyMethod">
				    			<html:option value="-1">----请选择----</html:option>
				    		</html:select>
							<span class="label">采购金额（元）：</span>
							<input type="text" style="width:100px;" value="" name="procTotal" class="money"/>
				    	</li>
				    </ul>
			        <div class="conOperation" id="CatalogDetailBtnDiv">
			        	<button type="button" id="updateCatalogDetailBtn" class="hidden"><span>更新</span></button>
			            <button type="button" id="saveCatalogDetailBtn"><span><spring:message code='globe.save'/></span></button>
			            <button type="button" id="newTypeBtn"><span>添加采购方式</span></button>
			        </div>
				</form>
	</div>


<ul class="hidden" id="templateUl">
	<li id="1">
		<input type="hidden" name="procTypeId"/>
   		<label>采购方式：</label>
   		<html:select selectedValue="0" id="type" name="procType" code="buyMethod">
   			<html:option value="-1">----请选择----</html:option>
   		</html:select>
		<span class="label">采购金额（元）：</span>
		<input type="text" style="width:100px;" value="" name="procTotal" class="money"/>
   	</li>
</ul>
