<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/openBidRoomForView.js"></script>
<div class="formLayout form2Pa"> 
     	<ul>
			<li class="formTextarea" id="remarkId">
				<label class="short">信息：</label>
				<span name="remark" id="remark"></span>
			</li>
		</ul>
		<div class="conOperation">
			<input type="hidden" name="rid" id="rid" value="${param.rid}">
			<input type="hidden" name="timeBucket" id="timeBucket" value="${param.timBuc}">
			<input type="hidden" name="rtime" id="rtime" value="${param.rtime}">
			<button id="openBidRoomIdR" type="button" tabindex="19"><span>关闭</span></button>
		</div>
</div>