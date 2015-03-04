<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/pubservice/css/adver_position.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/application/advertisement/adver_subscribe_add_modify.js"></script>

<div class="formTips attention">
 		若广告投放单位已注册本系统请点击<span class="sysicon siAdd"><a href="javascript:adverSubscribeAddModify.selectOrgInfo();"><strong>选择投放单位</strong></a>&nbsp;&nbsp;&nbsp;&nbsp;否则将作为未注册单位处理</span>
</div>
<div class="formLayout form2Pa">    
	<form id="adverSubscribeAddOrModifyForm" name="adverSubscribeAddOrModifyForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="objId" id="objId" value="${advertisingSubscribe.objId}"/>
		<input type="hidden" name="sort" id="sort" value="${advertisingSubscribe.sort}"/>
		<input type="hidden" name="useStatus" id="useStatus" value="${advertisingSubscribe.useStatus}"/>
		<input type="hidden" name="auditStatus" id="auditStatus" value="${advertisingSubscribe.auditStatus}"/>
		<input type="hidden" name="adverFile" id="adverFile" value="${advertisingSubscribe.adverFile}"/>
		<input type="hidden" id="fileType" name="fileType" value="${advertisingSubscribe.advertisingPosition.adverType}"/>
		<input type="hidden" id="adverHeight" value="${advertisingSubscribe.advertisingPosition.adverLength}"/>
		<input type="hidden" id="adverWidth" value="${advertisingSubscribe.advertisingPosition.adverWidth}"/>
		<input type="hidden" id="adverFileMaxValue" name="adverFileMaxValue" value="${advertisingSubscribe.advertisingPosition.adverFileMaxValue}"/>
		
		<h4 class="title"><span>广告订阅信息</span></h4>
		<ul>
			<li class="fullLine">
				<label>投放单位：</label>
				<input type="text" id="orgName" name="orgName" value="${advertisingSubscribe.orgName}" size="50" class="required"/><span class="eleRequired">*</span>
				<input type="hidden" name="orgInfo.objId" id="orgInfoId" value="${advertisingSubscribe.orgInfo.objId }"/>
			</li>
			<li class="fullLine">
				<label>广告位：</label>
				<select name="advertisingPosition.objId" id="advertisingPositionId" class="required">
					<option value=""></option>
					<c:forEach var="adverPosition" items="${adverPositionList}">
						<option value="${adverPosition.objId }" <c:if test="${adverPosition.objId == advertisingSubscribe.advertisingPosition.objId}">selected="selected"</c:if>>${adverPosition.positionDictionary.dicName }</option>
					</c:forEach>
				</select>
				<span class="eleRequired">*</span>
				<div id="adverPosInfoDiv" class="adverPosInfoDiv hidden" style="width: 270px;">
				</div>
			</li>
			<li class="fullLine">
				<label>开始时间：</label>
				<input type="text" id="startTime" name="startTime" value="${advertisingSubscribe.startTime}" size="50" class="sysicon siDate required"/><span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>结束时间：</label>
				<input type="text" id="endTime" name="endTime" value="${advertisingSubscribe.endTime}" size="50" class="sysicon siDate required"/><span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>广告链接：</label>
				<input type="text" id="adverLink" name="adverLink" value="${advertisingSubscribe.adverLink}" size="50" class="required url"/><span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>总费用(元)：</label>
				<input type="text" id="totalOutlay" name="totalOutlay" value="${advertisingSubscribe.totalOutlay}" class="required money" size="50"/><span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>广告附件：</label>
				<input name="pictureFile" type="file" id="pictureFile" size="40" onclick="return pictureFileOnClick();"/>(附件最大<span id="adverFileSizeSpan">0</span>KB,可通过设置广告位的容量来改变大小)
			</li>
		</ul>
	</form>
</div>
<div class="conOperation">
	<button class="largeBtn" id="adverSubscribeBtn_save" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
	<button class="largeBtn" id="adverSubscribeBtn_submit" type="button" tabindex="18"><span><spring:message code="globe.submit"/></span></button>
	<button class="largeBtn" id="adverSubscribeBtnReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
</div>

<div id="newPreview" style="margin-left: 120px;overflow: hidden;">
</div>
