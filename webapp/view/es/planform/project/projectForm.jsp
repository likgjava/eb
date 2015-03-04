<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/projectForm.js"></script>
<div class="partContainers">
<div class="formLayout form2Pa">
	<form id="projectForm" method="post">
	<h5><span>创建项目</span></h5>
	<ul>
      <li>
      	<label for="projCode"><spring:message code="projectForm.projCode"/>：</label>
      	<input type="text" name="projCode" id="projCode" class="required" />
      	<span class="eleRequired">*</span>
      	<input type="hidden" name="objId" id="objId" value="${param.objId }" />
      </li>
      <li>
      	<label for="projName"><spring:message code="projectForm.projName"/>：</label>
      	<input type="text" name="projName" id="projName" class="required" />
      	<span class="eleRequired">*</span>
      </li>
      <li class="fullLine">
      	<label for="ebuyMethod"><spring:message code="projectForm.ebuyMethod"/>：</label>
      	<select name="ebuyMethod" id="ebuyMethod" class="required">
			<option value="00">公开招标</option>
			<option value="01">邀请招标</option>
			<option value="02">竞争性谈判</option>
			<option value="03">单一来源</option>
			<option value="04">询价</option>
		</select>
      </li>
      <li class="formTextarea">
      	<label for="projSummary"><spring:message code="projectForm.projSummary"/>：</label>
      	<textarea name="projSummary" id="projSummary"></textarea>
      </li>
    </ul>
    <div class="conOperation">									
		<button id="projectSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
 		<button id="projectReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
  	</div>
	</form>
</div>
</div>
