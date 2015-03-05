<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/schedulerJobForm.js"></script>
<div class="formZone">         
	<form id="schedulerJobForm" method="post">
	    <table class="thInLeft">
	      <tr>
	        <th><label>任务名称:</label></th>
	        <td><input class="input_large" name="" type="text"/></td>
	      </tr>
	      <tr>
	        <th><label>任务类名:</label></th>
	        <td><input class="input_large" name="" type="text"/></td>
	      </tr>
	      <tr>
	        <th><label>触发事件:</label></th>
	        <td><input type="text" id="haveTime" /></td>
	      </tr>
	      <tr>
	        <th>自定义表达式</th>
	        <td><input class="input_large" name="input2" type="text"/></td>
	      </tr>
	      <tr>
	        <th><label>上传图片:</label></th>
	        <td><input class="input_middle" name="input" type="text"/><button class="normBtn"><span>浏览…</span></button></td>
	      </tr>
	    </table>
	     <div class="conOperation">
		   	<button type="button" id="saveRoleExtend"><span><spring:message code="globe.save"/></span></button>
		   	<button type="reset" ><span><spring:message code="globe.reset"/></span></button>
		</div>
	</form>
</div>