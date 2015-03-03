<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/bizplatform/base/purcatalog/catalog_detail_form.js"/>'></script>

	<!-- 品目树 -->
	<div class="treePage frameMainSub frameMs12 fullScreen">
		<div class="formLayout form2Pa"><h4><span>采购目录明细</span></h4></div>
		
		<div class="treeOutside frameMain"><div id="goodsClassTreeForm" class="treeContentDiv"></div></div>	
		
		<div class="treeRight frameSub" id="treeRight">
			<!-- 表单 -->
			<div class="formLayout form1Pa">
				<form id="CatalogDetailForm" method="post">
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
							<button type="button" onclick="catalogDetailForm.delTypeDetail(this)" class="sysicon siDelete"><span>&nbsp;</span></button>	
				    	</li>
				    </ul>
			        <div class="operationBtnDiv" id="CatalogDetailBtnDiv">
			        	<button type="button" class="largeBtn hidden" id="updateCatalogDetailBtn"><span>更新</span></button>
			            <button type="button" class="largeBtn" id="saveCatalogDetailBtn"><span><spring:message code='globe.save'/></span></button>
			            <button type="button" class="linkLongButton" id="newTypeBtn"><span>添加采购方式</span></button>
			        </div>
				</form>
			</div>
	    </div>
	</div>	
<ul class="hidden" id="templateUl">
	<li id="1">
   		<label>采购方式：</label>
   		<input type="hidden" name="procTypeId"/>
   		<html:select selectedValue="0" id="type" name="procType" code="buyMethod">
   			<html:option value="-1">----请选择----</html:option>
   		</html:select>
		<span class="label">采购金额（元）：</span>
		<input type="text" style="width:100px;" value="" name="procTotal" class="money"/>
		<button type="button" onclick="catalogDetailForm.delTypeDetail(this)" class="sysicon siDelete"><span>&nbsp;</span></button>		
   	</li>
</ul>