<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src ='<c:url value ="/view/bizplatform/base/industry/industry_list.js"/>'></script>

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
       <div id="industryTreeForm" class="treeContentDiv"></div>
  	<div class="treeResize" ></div>
	</div>	
	
	<div class="treeRight frameSub " id="treeRight">
		<!-- 详情 -->
		<div id="industryDetailPage">
			<div class="formLayout">
			<div class="operationBtnDiv r">
				<button id="newIndustry" ><span>新增行业</span></button>
				<button id="modifyIndustry" ><span>修改行业</span></button>
				<button id="deleteIndustry" ><span>删除行业</span></button>
			</div>
			<form id="industryDetail" method="post">
			<h4>行业详情</h4>
			<ul>
		   	  	<li>
			        <label>行业编号：</label>
			        <span id="code"></span>
				</li>
		   	  	<li>
			        <label>行业名称：</label>
			        <span id="name"></span>
				</li>
		   	  	<li>
			        <label>拼音缩写：</label>
			        <span id="shortSpellName"></span>
				</li>
			  </ul>
			  </form>
		      </div>
		</div>
		
		<!-- 表单 -->
		<div id="industryFormPage" class="hidden">
			  <div class="formLayout">
			  <form id="industryForm" method="post">
				   <input type="hidden" name="objId" id="industryId" value=""/>
				   <input type="hidden" name="level" id="level" value=""/>
				   <input type="hidden" name="parent.objId" id="parentId" value=""/>
			  <h4>行业信息</h4>
				   <ul>
				   	  	<li>
					        <label>行业编号：</label>
					        <input type="text" value="" name="code" id="code" class="required"/>
					        <span class="eleWarning">*</span>
				        </li>
				   	  	<li>
					        <label>行业名称：</label>
					        <input type="text" value="" name="name" id="name" class="required"/>
					        <span class="eleWarning">*</span>
				        </li>
				    </ul>
			        <div class="conOperation" id="goodsClassAddBtnDiv">
			            <button type="button" class="largeBtn" id="saveIndustryBtn"><span><spring:message code='globe.save'/></span></button>
			            <button type="button" class="largeBtn" id="returnBtn"><span><spring:message code='globe.return'/></span></button>
			        </div>
				</form>
				</div>
		</div>
    </div>
</div>