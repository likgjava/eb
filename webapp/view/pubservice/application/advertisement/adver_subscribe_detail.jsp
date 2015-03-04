<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script>
var adverSubscribeDetail = {};
//flash字符串拼接
adverSubscribeDetail.appendUrl = function(filePath,adverHeight,adverWidth){
	var flashPre;
	flashPre = document.createElement("embed");
	flashPre.type="application/x-shockwave-flash";
	flashPre.width=adverWidth;
	flashPre.height=adverHeight;
	flashPre.src=filePath;
	return flashPre;
}

//初始化广告预览
adverSubscribeDetail.initPreview = function(fileType,adverFile,adverHeight,adverWidth,adverLink){
	//图片
	if("00" == fileType){
		if(adverLink!=null&&adverLink!=""){
			$("#newPreview").empty().append('<a href="'+adverLink+'" target="_blank"><img src="AttachmentController.do?method=showImg&objId='+adverFile+'" width="'+adverWidth+'px" height="'+adverHeight+'px"></img></a>');
		}else{
			$("#newPreview").empty().append('<img src="AttachmentController.do?method=showImg&objId='+adverFile+'" width="'+adverWidth+'px" height="'+adverHeight+'px"></img>');
		}
	}
	//flash
	if("01" == fileType){
		$("#newPreview").empty().append(adverSubscribeDetail.appendUrl("AttachmentController.do?method=showImg&objId="+adverFile,adverHeight,adverWidth));
	}
	//跑马灯
	if("02" == fileType){
		$('#newPreview').loadPage($("#initPath").val()+"/AdvertisingPositionController.do?method=toAdverPositionDetail&objId="+$('#positionId').val()+"&operType=preview");
	}
}

$(document).ready(function(){
	var fileType = $('#fileType').val();//广告位类型
	var adverHeight = $("#adverHeight").val();//广告高度
	var adverWidth = $("#adverWidth").val();//广告宽度
	var adverFile = $('#adverFile').val();//广告文件Id
	var adverLink = $('#adverLink').val();//广告链接
	adverSubscribeDetail.initPreview(fileType,adverFile,adverHeight,adverWidth,adverLink);
});

//关闭
$('#adverSubscribeClosed').click(function(){
	$('.epsDialogClose').trigger('click');
});
</script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/pubservice/css/adver_position.css"/>
<div class="formLayout form2Pa">
	
	<input type="hidden" id="objId" value="${advertisingSubscribe.objId}"/>
	<input type="hidden" id="positionId" value="${advertisingSubscribe.advertisingPosition.objId}"/>
	<input type="hidden" name="adverFile" id="adverFile" value="${advertisingSubscribe.adverFile}"/>
	<input type="hidden" id="fileType" name="fileType" value="${advertisingSubscribe.advertisingPosition.adverType}"/>
	<input type="hidden" id="adverHeight" value="${advertisingSubscribe.advertisingPosition.adverLength}"/>
	<input type="hidden" id="adverWidth" value="${advertisingSubscribe.advertisingPosition.adverWidth}"/>
	<input type="hidden" id="adverLink" value="${advertisingSubscribe.adverLink}"/>
	
	<h4 class="title"><span>广告订阅信息：</span></h4>
	<c:if test="${advertisingSubscribe.advertisingPosition.adverType == '02'}">
		<div class="k1">
			<div class="img_250_1">
				<c:if test="${advertisingSubscribe.adverLink != null && advertisingSubscribe.adverLink!=''}">
					<a href="${advertisingSubscribe.adverLink}" target="_blank">
						<img id="view"  src="AttachmentController.do?method=showImg&objId=${advertisingSubscribe.adverFile}" width="200px" ></img>
					</a>
				</c:if>
				<c:if test="${advertisingSubscribe.adverLink == null || advertisingSubscribe.adverLink==''}">
					<img id="view"  src="AttachmentController.do?method=showImg&objId=${advertisingSubscribe.adverFile}" width="200px" ></img>
				</c:if>
				
			</div>
		</div>
	</c:if>
	<ul>
		<li class="fullLine">
			<label>投放单位：</label>${advertisingSubscribe.orgName}
		</li>	
		<li class="fullLine">
			<label>广告位：</label>${advertisingSubscribe.advertisingPosition.positionDictionary.dicName}
		</li>
		<li class="fullLine">
			<label>有效时间：</label>
			<fmt:formatDate value="${advertisingSubscribe.startTime}" pattern="yyyy-MM-dd"/> -- <fmt:formatDate value="${advertisingSubscribe.endTime}" pattern="yyyy-MM-dd"/>
		</li>
		<li class="fullLine">
			<label>总费用：</label>￥<fmt:formatNumber value="${advertisingSubscribe.totalOutlay}" pattern="#,##0.00" /> 元
		</li>
		<li>
			<label>使用状态：</label>${advertisingSubscribe.useStatusCN}
		</li>
		<li>
			<label>审核状态：</label>${advertisingSubscribe.auditStatusCN}
		</li>
		<li>
			<label>创建人：</label>${advertisingSubscribe.createUser.username}
		</li>
		<li>
			<label>创建日期：</label><fmt:formatDate value="${advertisingSubscribe.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</li>
	</ul>
	<h4 class="title"><span>广告位信息：${advertisingSubscribe.advertisingPosition.positionDictionary.dicName}</span></h4>
	<ul>
		<li><label>广告位：</label>${advertisingSubscribe.advertisingPosition.positionDictionary.dicName}</li>
		<li><label>文件类型：</label>${advertisingPosition.adverTypeCN}</li>
		<li><label>高度(px)：</label>${advertisingPosition.adverLength}</li>
		<li><label>宽度(px)：</label>${advertisingPosition.adverWidth}</li>
		<li><label>容量(KB)：</label>${advertisingPosition.adverFileMaxValue}</li>
		<li><label>资费(元/月)：</label>${advertisingPosition.adverOutlay}</li>
	</ul>
	<h4 class="title"><span>广告订阅预览：</span></h4>
	<div id="newPreview" style="margin-left: 120px;overflow: hidden;">
	</div>
</div>

<div class="conOperation">
	<button id="adverSubscribeClosed" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
</div>

