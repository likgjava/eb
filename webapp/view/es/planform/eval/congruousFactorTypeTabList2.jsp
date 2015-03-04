<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
 .formLayout th, .formLayout td{padding:0px;}
</style>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/eval/congruousFactorTypeTabList2.js"></script>
<input type="hidden" id="project_objId" value="${projectId}"></input>
<input type="hidden" id="curObjId"/>
<input type="hidden" id="cur_weightCoefficient" value=""/>
<input type="hidden" id="cur_parentId" value=""/>

<div style="float:left;border:1px solid #CCCCCC;width:170px;overflow:auto;min-height:100px;" id="tree" >
	  <div class="treeContentDiv" style="overflow: hidden;" id="congruousFactorTypeTree"></div>
</div>
<div id="treeRight" class="treeRight frameSub" >
	<div id="resourceForm" >
	<div class="formLayout ">
		<form id="congruousFactorTypeForm">
			 <div style="width: 100%;" class="treeEditNav" ></div>
			        <input type="hidden" tabindex="9" value="" id="objId" name="objId">
    				<input type="hidden" tabindex="9" value="" id="projId" name="projId">
    				<input type="hidden" tabindex="9" value="1" id="isLeaf" name="isLeaf">
    				<input type="hidden" tabindex="9" value="" id="parent.objId" name="parent.objId">
			      <input type="reset" style="display: none;" id="reset">
		</form>
	</div>
		<div class="formTips" style="min-height:100px;">
		    <ul>
		       <li>注意:点击指标分类,显示其分类下的相关指标</li>
		    </ul>
		</div>
     </div>
</div>
