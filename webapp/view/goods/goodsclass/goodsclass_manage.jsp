<%@ page contentType="text/html;charset=UTF-8"%>

<%@ include file="/view/srplatform/common/init.jsp"%>

<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src ='<c:url value ="/view/goods/goodsclass/goodsclass_manage.js"/>'></script>

<div class="treePage frameMainSub frameMs12 fullScreen">
	<div class="treeOutside frameMain">
   	<!--树操作菜单-->
	<div class="treeBtn" id="goodsClassTreeTools">
	<ul>
		<li>
		  <a href="javascript:void(0);" id="up" class="sysicon siUp" ><spring:message code='globe.up'/></a>
		</li>
		<li>
		  <a href="javascript:void(0);" id="down" class="sysicon siDown" ><spring:message code='globe.down'/></a>
		</li>
	</ul>
	</div>
       <div id="goodsClassTreeForm" class="treeContentDiv"></div>
  	<div class="treeResize" ></div>
	</div>	
	
	<div class="treeRight frameSub" id="treeRight">
		<!-- 详情 -->
		<div id="goodsClassDetailPage">
			<div class="formLayout">
			<form id="goodsClassDetail" method="post">
			<div class="operationBtnDiv r">
				<button id="newGoodsClass" type="button"><span>新增分类</span></button>
				<button id="modifyGoodsClass" type="button"><span>修改分类</span></button>
				<button id="deleteGoodsClass" type="button"><span>删除分类</span></button>
			</div>
			<h4>商品分类详情</h4>
			<ul>
		   	  	<li>
			        <label for="goodsClassCode"><spring:message code='goodsClassForm.goodsClassCode'/>：</label>
			        <span id="goodsClassCode"></span>
				</li>
				<li>
					<label for="goodsClassName"><spring:message code='goodsClassForm.goodsClassName'/>：</label>
					<span id="goodsClassName"></span>
				</li>
				<li>
					<label>拼音简码：</label>
					<span id="shortSpellName"></span>
				</li>
				<li>
					<label>采购品目：</label>
					<div id="purCategoryIdsName"></div>
				</li>
				<li class="formTextarea">
					<label for="remarks"><spring:message code='goodsClassForm.remarks'/>：</label>
					<span id="remarks"></span>
				</li>
			  </ul>
			  </form>
		      </div>
		</div>
		
		<!-- 表单 -->
		<div id="goodsClassFormPage" class="hidden">
			  <div class="formLayout">
			  <form id="goodsClassForm" method="post">
			  <h4>商品分类信息</h4>
				   <input type="hidden" name="objId" id="objId"/>
				   <input type="hidden" name="parentClazz.objId" id="parentClazzObjId"/>
				   <ul>
				   	  	<li>
					        <label for="goodsClassCode"><spring:message code='goodsClassForm.goodsClassCode'/>：</label>
					        <input type="text" value="" name="goodsClassCode" id="goodsClassCode" class="required"/><span class="eleRequired">*</span>
				        </li>
				   	    <li>
					        <label for="goodsClassName"><spring:message code='goodsClassForm.goodsClassName'/>：</label>
					        <input type="text" value="" name="goodsClassName" id="goodsClassName" class="required"/><span class="eleRequired">*</span>
				       	</li>
				   	   	<li>
					        <label for="purCategoryNames">采购品目：</label>
				         	   <input type="hidden" value="" id="purCategoryIds.objId" name="purCategoryIds" class="input_medium"/>
				         	   <input style="width:190px;" id="purCategoryIds.name" name="purCategoryNames" class="sysicon siSearch" readonly="readonly">
					        <span class="eleNote"></span>
				       	</li>
				   	   	<li class="formTextarea">
					        <label for="remarks"><spring:message code='goodsClassForm.remarks'/>：</label>
				               <textarea name="remarks" id="remarks" rows="6" cols="60" class="input_auto" maxlength="500"></textarea>
					        <span class="eleNote"></span>
				       	</li>
				    </ul>
			        <div class="conOperation" id="goodsClassAddBtnDiv">
			            <button type="button" class="largeBtn" id="saveGoodsClassBtn"><span><spring:message code='globe.save'/></span></button>
			            <button type="button" class="largeBtn" id="returnBtn"><span><spring:message code='globe.return'/></span></button>
			        </div>
				</form>
				</div>
		</div>
    </div>
</div>