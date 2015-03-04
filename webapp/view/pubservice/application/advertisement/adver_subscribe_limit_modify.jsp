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
	//日期表单验证
	$("#startTime").epsDatepicker();
	$("#endTime").epsDatepicker();
	
	var fileType = $('#fileType').val();//广告位类型
	var adverHeight = $("#adverHeight").val();//广告高度
	var adverWidth = $("#adverWidth").val();//广告宽度
	var adverFile = $('#adverFile').val();//广告文件Id
	var adverLink = $('#adverLink').val();//广告链接
	adverSubscribeDetail.initPreview(fileType,adverFile,adverHeight,adverWidth,adverLink);
});

//关闭
$('#adverSubscribeClosed').click(function(){
	$("#conBody").loadPage($("#initPath").val()+"/AdvertisingSubscribeController.do");
});

//保存
$('#adverSubscribeSave').click(function(){
	var url = $("#initPath").val()+"/AdvertisingSubscribeController.do?method=save";
	$("#adverSubscribeAddOrModifyForm").ajaxSubmit({
		url:url,
		dataType:"json",
		success:function(json){
			alert("操作成功！");
			$("#conBody").loadPage($("#initPath").val()+"/AdvertisingSubscribeController.do");
		},
		error:function(msg){
			alert("操作失败,请审查所填数据,重新保存！");
		}
	});
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
	
	<h4 class="title"><span>广告订阅预览：</span></h4>
	<div id="newPreview" style="margin-left: 120px;overflow: hidden;">
	</div>
	
	<h4 class="title"><span>此广告信息：</span></h4>
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
		<form id="adverSubscribeAddOrModifyForm" name="adverSubscribeAddOrModifyForm" method="post">
			<input type="hidden" name="objId" id="objId" value="${advertisingSubscribe.objId}"/>
			<input type="hidden" name="advertisingPosition.objId" id="advertisingPosition.objId" value="${advertisingSubscribe.advertisingPosition.objId}"/>
			<input type="hidden" name="orgInfo.objId" id="orgInfoId" value="${advertisingSubscribe.orgInfo.objId }"/>
			<input type="hidden" name="orgName" id="orgName" value="${advertisingSubscribe.orgName}"/>
			<input type="hidden" name="adverFile" id="adverFile" value="${advertisingSubscribe.adverFile}"/>
			<input type="hidden" name="auditStatus" id="auditStatus" value="${advertisingSubscribe.auditStatus}"/>
			<input type="hidden" name="useStatus" id="useStatus" value="${advertisingSubscribe.useStatus}"/>
			<input type="hidden" name="createUser.objId" id="createUser.objId" value="${advertisingSubscribe.createUser.objId}"/>
			<input type="hidden" name="createTime" id="createTime" value="${advertisingSubscribe.createTime}"/>
			<input type="hidden" name="verifyUser.objId" id="verifyUser.objId" value="${advertisingSubscribe.verifyUser.objId}"/>
			<input type="hidden" name="verifyTime" id="verifyTime" value="${advertisingSubscribe.verifyTime}"/>
			<input type="hidden" name="opinion" id="opinion" value="${advertisingSubscribe.opinion}"/>			
		
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
		</form>
	</ul>	
</div>

<div class="conOperation">
	<button id="adverSubscribeSave" type="button" tabindex="19"><span><spring:message code="globe.save"/></span></button>
	<button id="adverSubscribeClosed" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
</div>

