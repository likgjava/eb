<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/reserveRoomFrom.js"></script>
	<input type="hidden" name="checkTime" id="checkTime" />
<form id="reserveRoomFrom" method="post">
	<div class="formLayout">         
		<input type="hidden" name="objId" id="objId" />
		<input type="hidden" name="rid" id="rid" value="${param.rid}">
		<input type="hidden" name="timeBucket" id="timeBucket" value="${param.timBuc}">
		<input type="hidden" name="projPackId" id="projPackId" value="${param.projpackId}">
     	<h5><span>预订评标室</span></h5>
     	<ul>
     	<li><label class="short">标评室名称：</label>
     	<input type="text" name="bidRoomName" id="bidRoomName" class="required" readonly="readyonly"/>
     	</li>
     	<li><label class="short">标评室地点：</label>
     	<input type="text" name="bidRoomAddress" id="bidRoomAddress" class="required" readonly="readyonly"/>
     	</li>   
     	<li><label class="short">使用开始时间：</label>
     	<input type="text" name="startDate" id="startDate" class="required" value="${param.rtime}" readonly="readyonly"/>
     	</li>
     	<li><label class="short">使用截止时间：</label>
     	<input type="text" name="endDate" id="endDate" class="required" value="${param.rtime}" readonly="readyonly"/>
     	</li>
     	</ul>
     </div>
	</form>
	<div class="formTips">
      <ul>
          <li id="spanAppen">
          	
          </li>
      </ul>
    </div>
	 <div class="conOperation">
		<button class="btn primary" id="projectSave" type="button" tabindex="18"><span>预订评标室</span></button>
	</div>
