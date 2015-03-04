<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/reserveRoomDetail.js"></script>
<form id="reserveRoomFrom" method="post">
	<div class="formLayout">         
		<input type="hidden" name="objId" id="objId" />
		<input type="hidden" name="isSelect" id="isSelect" value="${param.isSelect}">
		<input type="hidden" name="isSelectId" id="isSelectId" value="${param.isSelectId}">
     	<h5><span>预订评标室</span></h5>
     	<ul>
     	<li><label class="short">招标名称：</label>
     	<span id="projectName"></span>
     	</li>
     	<li><label class="short">标评室名称：</label>
     	<span id="bidRoomName"></span>
     	</li>
     	<li><label class="short">标评室地点：</label>
     	<span id="bidRoomAddress"></span>
     	</li>
     	<li><label class="short">使用开始时间：</label>
		<span id="startDate"></span>
     	</li>
     	<li><label class="short">使用截止时间：</label>
     	<span id="endDate"></span>
     	</li>
     	</ul>
     </div>
	</form>
	 <c:if test="${'yes'==param.isSelect}">
     	<div class="conOperation">
     		<button class="btn primary" id="projectRemove" type="button" tabindex="18"><span>取消预订</span></button>
     	</div>
   	</c:if>