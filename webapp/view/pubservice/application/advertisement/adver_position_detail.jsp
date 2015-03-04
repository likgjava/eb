<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script>
var adverPositionDetail = {};
//flash字符串拼接
adverPositionDetail.appendUrl = function(filePath,adverHeight,adverWidth){
	var flashPre;
	flashPre = document.createElement("embed");
	flashPre.type="application/x-shockwave-flash";
	flashPre.width=adverWidth;
	flashPre.height=adverHeight;
	flashPre.src=filePath;
	return flashPre;
}

//初始化广告预览
adverPositionDetail.initPreview = function(fileType,adverFile,adverHeight,adverWidth){
	//图片
	if("00" == fileType){
		$("#newPreview").empty().append('<img src="AttachmentController.do?method=showImg&objId='+adverFile+'" width="'+adverWidth+'px" height="'+adverHeight+'px"></img>');
	}
	//flash
	if("01" == fileType){
		$("#newPreview").empty().append(adverPositionDetail.appendUrl("AttachmentController.do?method=showImg&objId="+adverFile,adverHeight,adverWidth));
	}
	//跑马灯
	if("02" == fileType){
		$('#newPreview').loadPage($("#initPath").val()+"/AdvertisingPositionController.do?method=toAdverPositionDetail&objId="+$('#objId').val()+"&operType=preview");
	}
}

$(document).ready(function(){
	var fileType = $('#fileType').val();//广告位类型
	var adverHeight = $("#adverHeight").val();//广告高度
	var adverWidth = $("#adverWidth").val();//广告宽度
	var adverFile = $('#adverFile').val();//广告文件Id
	adverPositionDetail.initPreview(fileType,adverFile,adverHeight,adverWidth);
});
</script>

<div class="formLayout form2Pa">
	<input type="hidden" name="adverFile" id="adverFile" value="${advertisingSubscribeDiv.adverFile}"/>
	<input type="hidden" id="fileType" name="fileType" value="${advertisingPosition.adverType}"/>
	<input type="hidden" id="adverHeight" value="${advertisingPosition.adverLength}"/>
	<input type="hidden" id="adverWidth" value="${advertisingPosition.adverWidth}"/>
	
	<h4 class="title"><span>广告位信息</span></h4>
	<input type="hidden" name="objId" id="objId" value="${advertisingPosition.objId}"/>
	<ul>			
		<li>
			<label>位名称：</label>${advertisingPosition.positionName}
		</li>
		<li>
			<label>位置：</label>${advertisingPosition.positionDictionary.dicName }
		</li>
		<li>
			<label>高度(px)：</label>${advertisingPosition.adverLength}
		</li>
		<li>
			<label>宽度(px)：</label>${advertisingPosition.adverWidth}
		</li>
		<li>
			<label>文件类型：</label>${advertisingPosition.adverTypeCN}
		</li>
		<li>
			<label>容量(KB)：</label>${advertisingPosition.adverFileMaxValue}
		</li>
		<li class="fullLine">
			<label>资费(元/月)：</label>${advertisingPosition.adverOutlay}
		</li>
		<li>
			<label>创建人：</label>${advertisingPosition.createUser.username}
		</li>
		<li>
			<label>创建日期：</label>${advertisingPosition.createTime}
		</li>
		<li>
			<label>修改人：</label>${advertisingPosition.updateUser.username}
		</li>
		<li>
			<label>修改日期：</label>${advertisingPosition.updateTime}
		</li>
	</ul>
	<c:if test="${advertisingSubscribeDiv != null && advertisingPosition.adverType != '02'}">
		<h4 class="title"><span>有效广告订阅信息</span></h4>
		<ul>
			<li>
				<label>投放单位：</label>${advertisingSubscribeDiv.orgName}
			</li>	
			<li>
				<label>总费用(元)：</label>${advertisingSubscribeDiv.totalOutlay}元
			</li>
			<li class="fullLine">
				<label>有效时间：</label>
				<fmt:formatDate value="${advertisingSubscribeDiv.startTime}" pattern="yyyy-MM-dd"/>————
				<fmt:formatDate value="${advertisingSubscribeDiv.endTime}" pattern="yyyy-MM-dd"/>
			</li>
			<li>
				<label>使用状态：</label>${advertisingSubscribeDiv.useStatusCN}
			</li>
			<li>
				<label>审核状态：</label>${advertisingSubscribeDiv.auditStatusCN}
			</li>
			<li>
				<label>创建人：</label>${advertisingSubscribeDiv.createUser.username}
			</li>
			<li>
				<label>创建日期：</label>${advertisingSubscribeDiv.createTime}
			</li>
			<li>
				<label>修改人：</label>${advertisingSubscribeDiv.updateUser.username}
			</li>
			<li>
				<label>修改日期：</label>${advertisingSubscribeDiv.updateTime}
			</li>
			<li>
				<label>审核人：</label>${advertisingSubscribeDiv.verifyUser.username}
			</li>
			<li>
				<label>审核日期：</label>${advertisingSubscribeDiv.verifyTime}
			</li>
			<li class="fullLine">
				<label>审核意见：</label>${advertisingSubscribeDiv.opinion}
			</li>
		</ul>
	</c:if>
	<c:if test="${advertisingSubscribeDiv != null}">
		<h4 class="title"><span>广告订阅预览：</span></h4>
		<div id="newPreview" style="margin-left: 120px;overflow: hidden;">
		</div>
	</c:if>
</div>
<div class="conOperation">
	<button id="adverPositionClosed" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
</div>

<script>
//关闭
$('#adverPositionClosed').click(function(){
	$('.epsDialogClose').trigger('click');
});
</script>
