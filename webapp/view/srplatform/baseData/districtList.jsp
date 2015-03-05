<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src ='<c:url value ="/view/srplatform/baseData/districtList.js"/>'></script>

<div class="treePage frameMainSub frameMs12 fullScreen ">
	<div class="treeOutside frameMain">
   	<!--树操作菜单-->
	<div class="treeBtn" id="goodsClassTreeTools">
	<ul>
		<li>
		  <a href="javascript:void(0)" id="up" class="sysicon siUp" ><spring:message code='globe.up'/></a>
		</li>
		<li>
		  <a href="javascript:void(0)" id="down" class="sysicon siDown" ><spring:message code='globe.down'/></a>
		</li>
	</ul>
	</div>
       <div id="districtTreeForm" class="treeContentDiv"></div>
  	<div class="treeResize" ></div>
	</div>	
	
	<div class="treeRight frameSub " id="treeRight">
		<!-- 详情 -->
		<div id="districtDetailPage">
			<div class="formLayout formPa">
			<div class="operationBtnDiv r">
				<button id="newDistrict" ><span>新增区域</span></button>
				<button id="modifyDistrict" ><span>修改区域</span></button>
				<button id="deleteDistrict" ><span>删除区域</span></button>
			</div>
			<form id="districtDetail" method="post">
			<h4>行业详情</h4>
			<ul>
		   	  	<li>
			        <label>行政区域代码：</label>
			        <span id="code"></span>
				</li>
		   	  	<li>
			        <label>地区名称：</label>
			        <span id="name"></span>
				</li>
		   	  	<li>
			        <label>区域简称：</label>
			        <span id="shortName"></span>
				</li>
			  </ul>
			  </form>
		      </div>
		</div>
		
		<!-- 表单 -->
		<div id="districtFormPage" class="hidden">
			  <div class="formLayout formPa">
			  <form id="districtForm" method="post">
				   <input type="hidden" name="objId" id="districtId" value=""/>
				   <input type="hidden" name="isLeaf" id="isLeaf" value=""/>
				   <input type="hidden" name="level" id="level" value=""/>
				   <input type="hidden" name="parent.objId" id="parentId" value=""/>
			  <h4>区域信息</h4>
				   <ul>
				   	  	<li>
					        <label>行政区域代码：</label>
					        <input type="text" value="" name="code" id="code" class="required" maxlength="6"/>
					        <span class="eleWarning">*</span>
				        </li>
				   	  	<li>
					        <label>地区名称：</label>
					        <input type="text" value="" name="name" id="name" class="required"/>
					        <span class="eleWarning">*</span>
				        </li>
				   	  	<li>
					        <label>区域简称：</label>
					        <input type="text" value="" name="shortName" id="shortName" class="required"/>
					        <span class="eleWarning">*</span>
				        </li>
				    </ul>
			        <div class="conOperation" id="goodsClassAddBtnDiv">
			            <button type="button" class="largeBtn" id="saveDistrictBtn"><span><spring:message code='globe.save'/></span></button>
			            <button type="button" class="largeBtn" id="returnBtn"><span><spring:message code='globe.return'/></span></button>
			        </div>
				</form>
				</div>
		</div>
    </div>
</div>