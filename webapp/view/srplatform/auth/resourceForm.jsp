<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/ResourceForm.js"></script>

<div class="form-container">
<form method="post" name="ResourceForm" id="ResourceForm" action="ResourceController.do?method=save">
			<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
			<input type="hidden" name="parent.objId"  id="parent.objId"  value="<c:out value="${param.parentId}"/>"/>

			 <div class="formLayout form2Pa">
			 <h4><span>资源信息</span></h4>
			    <ul>
			      <li>
			        <label for="input01">资源名称:</label>
			        <input type="text" value=""  name="name" class="required" />
			        <em>*</em>	
			      </li>
			      <li>
			        <label for="input01">资源URL地址:</label>
			        <input type="text" name="url"  class="required" maxlength="50" class="required"/>
				    <em>*</em>				      
			        <span class="eleNote"></span>
		      	 </li>
			      <li>
			        <label for="input01">是否启用日志:</label>
			        <select id="isLog" name="isLog" class="required" tabindex="10" >
	          			<option value="0">禁用日志</option>
	          			<option value="1">启用日志</option>
	          		</select>
				    <em>*</em>				      
			        <span class="eleNote"></span>
		      	 </li>
			      <li>
			        <label for="input01">是否属于系统资源:</label>
			         <select id="isSys" name="isSys" class="required" tabindex="10" >
	          			<option value="0">非系统资源</option>
	          			<option value="1">系统资源</option>
	          		</select>
				    <em>*</em>				      
			        <span class="eleNote"></span>
		      	 </li>
			      <li>
			        <label for="input01">类型:</label>
			        <select id="type" name="type" class="required" tabindex="10" >
	          			<option value="URL">链接</option>
	            		<option value="METHOD">方法</option>
	          		</select>
				    <em>*</em>				      
			        <span class="eleNote"></span>
		      	 </li>
			      <li>
			        <label for="input01">上级资源:</label>
			         <input type="hidden" name="parent.objId" id="parent.objId" value="">
    				<input type="text" name="parent.name" disabled="disabled" id="parent.name" value="" readonly="readonly" class="sysicon siSearch">
			        <span class="eleNote"></span>
		      	 </li>
			      <li>
			        <label for="input01">是否产生默认菜单:</label>
				         <input name="defaultMenu" id="radio1" value="1" type="radio" tabindex="13" />
				          <span for="radio1">是 </span>
				          <input name="defaultMenu" id="radio2" value="0" type="radio" checked="checked"/>
				          <span for="radio2">否 </span>
			        <span class="eleNote"></span>
		      	 </li>
			      </ul>
			     	<div class="conOperation">
				      <button id="saveResource" type="button"><span>提交</span></button>
				      <button type="reset"><span>重置</span></button>
				    </div>
			  </div>
			  <div class="formTips">
			      <ul>
			         <li><spring:message code="globe.input.required.prompt"/></li>
			      </ul>
			  </div>
	</form>


