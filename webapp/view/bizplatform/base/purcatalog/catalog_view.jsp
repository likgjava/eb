<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/bizplatform/base/purcatalog/catalog_view.js"/>'></script>
<div>
	<form:form id="catalogForm" method="post" modelAttribute="purCatalog">
	<form:hidden path="objId"></form:hidden>
	<div class="formLayout form2Pa">
	    <h4><span>采购目录信息</span></h4>
		<ul>
			<li>
				<label>目录标题：</label>
				<span>${purCatalog.title}</span>
			</li>	
			<li>
				<label>使用区域编码：</label>
				<span>${purCatalog.areaCode}</span>
			</li>
			<li>
				<label>使用区域名称：</label>
				<span>${purCatalog.areaName}</span>
			</li>
			<li>
				<label>年度：</label>
				<span>${purCatalog.year}</span>
			</li>
			<li class="fullLine">
				<label >生效时间：</label>
				<span>${purCatalog.relDate}</span>
			</li>		
			<li class="fullLine">
				<label>有关说明及要求：</label>
				<span>${purCatalog.content}</span>
			</li>
		</ul>
	</div>
	</form:form>
	
	<!-- 品目树 -->
	<div id="catalogDetailTree">
		<div class="treePage frameMainSub frameMs12 fullScreen">
		<div class="formLayout form2Pa"><h4><span>采购目录明细</span></h4></div>
		<div class="treeOutside frameMain"><div id="goodsClassTreeForm" class="treeContentDiv"></div></div>	
		<div class="treeRight frameSub" id="treeRight">
			<!-- 表单 -->
			<div class="formLayout form2Pa">
				<form id="CatalogDetailForm" method="post">
				   <input type="hidden" name="detailId"/>
				   <ul>
				   		<!-- catalogDetail -->
				   	  	<li class="fullLine">
					        <label for="goodsPrice">采购单价（元）：</label>
					        <span id="goodsPrice"></span>
				        </li>
				   	    <li class="fullLine">
					        <label for="yearTotal">年批量采购金额（元）：</label>
					        <span id="yearTotal"></span>
				       	</li>
				       	
				       	<!-- catalogType -->
				       	<li class="fullLine">采购方式明细</li>
				    </ul>
				    <ul>
				    	<li id="1" class="fullLine">
				    		<label>采购方式：</label>
							<label>采购金额（元）：</label>
				    	</li>
				    </ul>
				</form>
			</div>
	    </div>
		</div>	
	</div>	
	
	<div class="conOperation" id="catalogBtn">
		<button  id="auditReturn"><span>返回</span></button>
	</div>
</div>