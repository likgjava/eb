<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src ='<c:url value ="/view/bizplatform/base/purcategory/purcategory_list.js"/>'></script>

<div class="treePage frameMainSub frameMs12 fullScreen ">
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
       <div id="purCategoryTreeForm" class="treeContentDiv"></div>
  	<div class="treeResize" ></div>
	</div>	
	
	<div class="treeRight frameSub " id="treeRight">
		<!-- 详情 -->
		<div id="purCategoryDetailPage">
			<div class="formLayout formPa">
			<div class="operationBtnDiv r">
				<button id="newPurCategory" ><span>新增品目</span></button>
				<button id="modifyPurCategory" ><span>修改品目</span></button>
				<button id="deletePurCategory" ><span>删除品目</span></button>
				<!--小额交易暂屏蔽掉采购目录 <button id="newPurCatalog" class="linkLongButton"><span>新增采购目录</span></button>-->
			</div>
			<form id="purCategoryDetail" method="post">
			<h4>采购品目详情</h4>
			<ul>
		   	  	<li>
			        <label>品目编号：</label>
			        <span id="categoryCode"></span>
				</li>
		   	  	<li>
			        <label>品目名称：</label>
			        <span id="categoryName"></span>
				</li>
		   	  	<li>
			        <label>拼音缩写：</label>
			        <span id="shortSpellName"></span>
				</li>
		   	  	<li>
			        <label>发布日期：</label>
			        <span id="releaseDate"></span>
				</li>
			  </ul>
			  </form>
		      </div>
		</div>
		
		<!-- 表单 -->
		<div id="purCategoryFormPage" class="hidden">
			  <div class="formLayout formPa">
			  <form id="purCategoryForm" method="post">
				   <input type="hidden" name="objId" id="categoryId" value=""/>
				   <input type="hidden" name="parent.objId" id="parentId" value=""/>
			  <h4>采购品目信息</h4>
				   <ul>
				   		<!--
				   	  	<li>
					        <label>品目编号：</label>
					        <input type="text" value="" name="categoryCode" id="categoryCode" class="required"/>
					        <span class="eleWarning">*</span>
				        </li>
				   	  	-->
				   	  	<li>
					        <label>品目名称：</label>
					        <input type="text" value="" name="categoryName" id="categoryName" class="required"/>
					        <span class="eleWarning">*</span>
				        </li>
				   	  	<li>
					        <label>发布日期：</label>
					        <input type="text" value="" name="releaseDate" id="releaseDate" class="required"/>
					        <span class="eleWarning">*</span>
				        </li>
				    </ul>
			        <div class="conOperation" id="goodsClassAddBtnDiv">
			            <button type="button" class="largeBtn" id="savePurcategoryBtn"><span><spring:message code='globe.save'/></span></button>
			            <button type="button" class="largeBtn" id="returnBtn"><span><spring:message code='globe.return'/></span></button>
			        </div>
				</form>
				</div>
		</div>
    </div>
</div>