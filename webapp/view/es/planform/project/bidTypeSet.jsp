<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/bidTypeSet.js"></script>

<div class="partContainers">
   	<div class="formLayout">
 		<h5><span>设置采购方式</span></h5>
 		<input type="hidden" name="old_ebuyMethod" id="old_ebuyMethod" value="${project.ebuyMethod}">
		<form id="bidtypeForm">
		<ul>
	   		<li>
	        	<label for="input01">采购方式：</label>
	        	<input type="hidden" name="objId" id="objId" value="${project.objId}">
	        	<input type="radio" name="ebuyMethod" id="ebuyMethod_00" value="00"/>公开招标&nbsp;&nbsp;
	        	<input type="radio" name="ebuyMethod" id="ebuyMethod_01" value="01"/>邀请招标&nbsp;&nbsp;
	        	<input type="radio" name="ebuyMethod" id="ebuyMethod_02" value="02"/>竞争性谈判&nbsp;&nbsp;
	        	<input type="radio" name="ebuyMethod" id="ebuyMethod_03" value="03"/>询价采购&nbsp;&nbsp;
	        	<input type="radio" name="ebuyMethod" id="ebuyMethod_04" value="04"/>单一来源 &nbsp;&nbsp;
			</li>
		</ul>
		<div class="conOperation">
   			<button type="button" id="sure"><span>确定</span></button>
		</div>
		</form>
   	</div>
</div>