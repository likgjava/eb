<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/openBidRoomForIsOff.js"></script>
<div class="formLayout form2Pa"> 
	<form id="openBidRoomIsOffFrom" method="post">     
     	<ul>
			<li class="fullLine">
				<label class="short">启用/禁用开标室：</label>
				<input type = "radio" name="useStatus" value="2" id="isOpen">启用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type = "radio" name="useStatus" value="3" id="isOff">禁用
			</li>
			<li class="formTextarea" id="remarkId">
				<label class="short">原由：</label>
				<textarea name="remark" id="remark"></textarea>
			</li>
		</ul>
		<div class="conOperation">
			<input type="hidden" name="rid" id="rid" value="${param.rid}">
			<input type="hidden" name="timeBucket" id="timeBucket" value="${param.timBuc}">
			<input type="hidden" name="rtime" id="rtime" value="${param.rtime}">
			<button id="openBidRoomIdS" type="button" tabindex="18"><span>确认</span></button>
			<button id="openBidRoomIdR" type="button" tabindex="19"><span>关闭</span></button>
		</div>
	</form>
</div>