<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/eval/congruousFactorTypeTabList.js"></script>
<input type="hidden" id="project_objId" value="${projectId}"></input>
<input type="hidden" id="curObjId"/>
<input type="hidden" id="cur_weightCoefficient" value=""/>
<input type="hidden" id="cur_parentId" value=""/>

<div class="treeOutside frameMain" style="width: 120px;height: 365px;" id="tree">
	  <div class="treeContentDiv" style="overflow: hidden;" id="congruousFactorTypeTree"></div>
</div>
<authz:authorize ifAnyGranted="No_UpdatePurBulletin">
<c:choose>
<c:when test="${doc.auditStatus == '04'}">
<input type="hidden" id="isJBR">
<div id="treeRight" class="treeRight frameSub" >
		<div id="resourceForm" >
		<div class="formLayout ">
		<form id="congruousFactorTypeForm">
			 <h5><span>新增指标分类</span></h5>
			 <div style="width: 100%;" class="treeEditNav">
			</div>
			  	    <input type="hidden" tabindex="9" value="" id="objId" name="objId">
    				<input type="hidden" tabindex="9" value="" id="projId" name="projId">
    				<input type="hidden" tabindex="9" value="1" id="isLeaf" name="isLeaf">
    				<input type="hidden" tabindex="9" value="" id="parent.objId" name="parent.objId">
			    <ul>
    				 <li>
			        <label for="rname" class="short">指标编号：</label>
			        <input type="text" value="" id="typeCode"  name="typeCode" class="required number">
			        <span class="eleRequired" id="typeCodeCheck">*</span>
			      </li>
			      <li>
			        <label for="rname" class="short">指标名称：</label>
			        <input type="text" tabindex="1" class="required" name="typeName" value="" id="typeName">
			        <span class="eleRequired">*</span>
			      </li>
			      	<li class="fullLine">	
	     			<label  class="short">审核方式：</label>
	     			<html:select styleClass="required" id="auditMethod" name="auditMethod" code="auditMethod" >
						</html:select>
	   	   			<span class="eleRequired">*</span>
	    		 </li>
			       <li>
			        <label for="rname" class="short">指标权重：</label>
			        <input type="text" tabindex="1" class="required number" name="weightCoefficient" value="" id="weightCoefficient">
			        <span class="eleRequired">*</span>
			      </li>
			       <li>
			        <label for="rname" class="short">指标排序号：</label>
			        <input type="text" tabindex="1" class="required number" name="sort" value="" id="sort">
			        <span class="eleRequired">*</span>
			      </li>
			      <li class="formTextarea" >
			        <label for="input01" class="short">指标说明：</label>
			        <textarea id="remark" name="remark" class="required" cols="5" rows="5" style="width: 500px;height: 100px;"></textarea>
				    <span class="eleRequired">*</span>			      
		      	 </li>
			      </ul>
			      <input type="reset" style="display: none;" id="reset">
			      </form>
			     	<div class="conOperation">
				      <button tabindex="11" type="button" id="saveResource"><span>保存</span></button>
				    </div>
			  </div>
			  <div class="formTips">
			    <ul>
			       <li>注意:带<span class="eleRequired">*</span>号的为必填字段.</li>
			    </ul>
			  </div>
           </div>
      </div>
</c:when>
<c:otherwise>
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
</c:otherwise>
</c:choose>

</authz:authorize>

<authz:authorize ifAnyGranted="NO_WaitConfPurDOC">
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
</authz:authorize>



<authz:authorize ifAnyGranted="NO_UpdateContract">
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
</authz:authorize>


<authz:authorize ifAnyGranted="No_WaitAuditBulletin">
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
</authz:authorize>