<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/bargin/project/bargain/delay_project_time.js"></script>

<form id="BargainProjectModifyForm" method="post">
<input type="hidden" name="currentTime" id="currentTime" value="${currentTime}"/>
<input type="hidden" name="projId" id="projId" value="${projId}"/>
<input type="hidden" name="evalStartTime" id="evalStartTime" value="<fmt:formatDate value="${evalStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
<input type="hidden" name="evalEndTime" id="evalEndTime" value="<fmt:formatDate value="${evalEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
		<div id="barginTurn">
			<ul class="safe-info">
				<li class="item identity">
					<div class="title" style="width:120px;">报名时间</div>
					<div class="supply" style="width:400px">
						<span><input type="text" class="required" style="width:150px;" name="signUpSTime" id="signUpSTime" value="<fmt:formatDate value="${signUpSTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly"/></span>
						至
						<span><input type="text" class="required" style="width:150px;" name="signUpETime" id="signUpETime" value="<fmt:formatDate value="${signUpETime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly"/></span>
					</div>
					<div class="state"><span class="over" id="bargainNumber_span"></span></div>
					<div class="operate"></div>
				</li>
				<li class="item identity">
					<div class="title" style="width:120px;">竞价轮次</div>
					<div class="supply" style="width:400px">[项目的竞价起止时间为第一轮开始时间和最后一轮结束时间]</div>
					<div class="state"><span class="over" id="bargainNumber_span"></span></div>
					<div class="operate"></div>
				</li>
				<c:choose>
				<c:when test="${!empty bargainTurnList && fn:length(bargainTurnList) > 0}">
					<c:forEach var="bargainTurn" items="${bargainTurnList}" varStatus="status">
						<li class="item identity temp">
							<input type="hidden" name="turnNo${bargainTurn.turnNo}" id="turnNo${bargainTurn.turnNo}" value="${bargainTurn.turnNo}"/>
							<input type="hidden" name="objId${bargainTurn.turnNo}" id="objId${bargainTurn.turnNo}" value="${bargainTurn.objId}"/>
							<div class="title" style="width:120px;">
								<label>第<b id="${bargainTurn.turnNo}">${bargainTurn.turnNo}</b>轮：</label>
							</div>
							<div class="supply" style="width:400px">
								<span><input type="text" class="required" style="width:150px;" name="startTime" id="startTime${bargainTurn.turnNo}" value="<fmt:formatDate value="${bargainTurn.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly"/></span>
								至
								<span><input type="text" class="required" style="width:150px;" name="endTime" id="endTime${bargainTurn.turnNo}" value="<fmt:formatDate value="${bargainTurn.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly"/></span>
							</div>
							<div class="state">
								<span class="over" id="bargainNumber_span"></span>
							</div>
							<div class="operate">
								<c:choose>
								<c:when test="${fn:length(bargainTurnList) == status.index+1}">
									<c:if test="${status.index!=0}">
										<span id="delTurn${status.index+1}" class="sysicon siDelete"><a class="tempa" href="javascript:void(0);">删除</a></span>
									</c:if>
									<span id="addTurn${status.index+1}" class="sysicon siAdd"><a href="javascript:BargainProjectModifyForm.cloneTurn();">增加</a></span>
								</c:when>
								<c:otherwise>
									<span id="delTurn${status.index+1}" class="sysicon siDelete hidden"><a class="tempa" href="javascript:void(0);">删除</a></span>
									<span id="addTurn${status.index+1}" class="sysicon siAdd hidden"><a href="javascript:BargainProjectModifyForm.cloneTurn();">增加</a></span>
								</c:otherwise>
								</c:choose>
							</div>
						</li>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<li class="item identity temp">
						<input type="hidden" name="objId1" id="objId1" value=""/>
						<input type="hidden" name="turnNo1" id="turnNo1" value="1"/>
						<div class="title" style="width:120px;">
							<label>第<b id="1">1</b>轮：</label>
						</div>
						<div class="supply" style="width:400px">
							<span><input type="text" name="startTime" id="startTime1" style="width:150px;" class="required" value="" readonly="readonly"/></span>
							至
							<span><input type="text" name="endTime" id="endTime1" style="width:150px;" class="required" value="" readonly="readonly"/></span>
						</div>
						<div class="state">
							<span class="over" id="bargainNumber_span"></span>
						</div>
						<div class="operate">
							<span id="delTurn1" class="sysicon siDelete"><a class="tempa" href="javascript:void(0);">删除</a></span>
							<span id="addTurn1" class="sysicon siAdd"><a href="javascript:BargainProjectModifyForm.cloneTurn();">增加</a></span>
						</div>
					</li>
				</c:otherwise>
				</c:choose>
			</ul>
		</div>
		
		<div class="conOperation">
			<button id="save" type="button"><span>确定</span></button>
			<button name="historyBackBtn" type="button" id="willClose"><span>返回</span></button>
			<script type="text/javascript">
			$("#willClose").click(function(){
				if($('.epsDialogClose').html()){
					$('.epsDialogClose').trigger('click');
				}
			});
			</script>
		</div>
</form>

<ul class="hidden safe-info" id="tempUL">
	<li class="item identity temp">
		<input type="hidden" name="objId1" id="objId1" value=""/>
		<input type="hidden" name="turnNo1" id="turnNo1" value="1"/>
		<div class="title" style="width:120px;">
			<label>第<b id="1">1</b>轮：</label>
		</div>
		<div class="supply" style="width:400px">
			<span><input type="text" name="startTime1" style="width:150px;" id="startTime1" class="required" value="" readonly="readonly"/></span>
			至
			<span><input type="text" name="endTime1" style="width:150px;" id="endTime1" class="required" value="" readonly="readonly"/></span>
		</div>
		<div class="state">
			<span class="over" id="bargainNumber_span"></span>
		</div>
		<div class="operate">
			<span id="delTurn1" class="sysicon siDelete"><a class="tempa" href="javascript:void(0);">删除</a></span>
			<span id="addTurn1" class="sysicon siAdd"><a href="javascript:BargainProjectModifyForm.cloneTurn();">增加</a></span>
		</div>
	</li>
</ul>
