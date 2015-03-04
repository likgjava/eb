<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout form2Pa">
	<h4 class="title"><span>广告订阅信息</span></h4>
	<input type="hidden" id="positionId" value="${advertisingSubscribe.advertisingPosition.objId}"/>
	<input type="hidden" name="adverFile" id="adverFile" value="${advertisingSubscribe.adverFile}"/>
	<input type="hidden" name="adverLink" id="adverLink" value="${advertisingSubscribe.adverLink}"/>
	<input type="hidden" id="fileType" name="fileType" value="${advertisingSubscribe.advertisingPosition.adverType}"/>
	<input type="hidden" id="adverHeight" value="${advertisingSubscribe.advertisingPosition.adverLength}"/>
	<input type="hidden" id="adverWidth" value="${advertisingSubscribe.advertisingPosition.adverWidth}"/>
	
	<c:if test="${advertisingSubscribe.advertisingPosition.adverType == '02'}">
		<div class="k1">
			<c:if test="${advertisingSubscribe.adverLink != null && advertisingSubscribe.adverLink!=''}">
				<a href="${advertisingSubscribe.adverLink}" target="_blank">
					<img id="view"  src="AttachmentController.do?method=showImg&objId=${advertisingSubscribe.adverFile}" width="200px" ></img>
				</a>
			</c:if>
			<c:if test="${advertisingSubscribe.adverLink == null || advertisingSubscribe.adverLink==''}">
				<img id="view" src="AttachmentController.do?method=showImg&objId=${advertisingSubscribe.adverFile}" width="200px" height="175px" ></img>
			</c:if>			
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
			<fmt:formatDate value="${advertisingSubscribe.startTime}" pattern="yyyy-MM-dd"/>————
			<fmt:formatDate value="${advertisingSubscribe.endTime}" pattern="yyyy-MM-dd"/>
		</li>
		<li class="fullLine">
			<label>总费用(元)：</label>${advertisingSubscribe.totalOutlay}元
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
			<label>创建日期：</label>${advertisingSubscribe.createTime}
		</li>
	</ul>
	<h4>广告预览：</h4>
		<div id="newPreview" style="margin-left: 120px;overflow: hidden;">
	</div>
	<h4>审核意见：</h4>
	<form id="adverSubscribeAuditForm" name="adverSubscribeAuditForm" method="post">
		<input type="hidden" name="objId" id="objId" value="${advertisingSubscribe.objId}"/>
		<input type="hidden" name="useStatus" id="useStatus" value="01" />
		<input type="hidden" name="auditStatus" id="auditStatus" value="02" />
		<div class="formLayout form2Pa">
			<ul>
				<li class="formTextarea">
					<label>审核意见：</label>
					<textarea id="opinion" name="opinion" maxLength="100"></textarea>
				</li>
			</ul>
		</div>
	</form>
</div>

<div class="conOperation">
		<button class="largeBtn" id="adverSubscribetBtn_audit_yes" type="button" tabindex="18"><span>审核通过</span></button>
		<button class="largeBtn" id="adverSubscribetBtn_audit_yes_publish" type="button" tabindex="18"><span>审核通过并发布广告</span></button>
        <button class="largeBtn" id="adverSubscribetBtn_audit_no" type="button" tabindex="18"><span>审核不通过</span></button>
        <button class="largeBtn" id="adverSubscribeAuditReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
</div>

<script type="text/javascript">
var adverSubscribeAuditAdd = {};
//flash字符串拼接
adverSubscribeAuditAdd.appendUrl = function(filePath,adverHeight,adverWidth){
	var flashPre;
	flashPre = document.createElement("embed");
	flashPre.type="application/x-shockwave-flash";
	flashPre.width=adverWidth;
	flashPre.height=adverHeight;
	flashPre.src=filePath;
	return flashPre;
}

//初始化广告预览
adverSubscribeAuditAdd.initPreview = function(fileType,adverFile,adverHeight,adverWidth,adverLink){
	//图片
	if("00" == fileType){
		$("#newPreview").empty().append('<a href="'+adverLink+'" target="_blank"><img src="AttachmentController.do?method=showImg&objId='+adverFile+'" width="'+adverWidth+'px" height="'+adverHeight+'px"></img></a>');
	}
	//flash
	if("01" == fileType){
		$("#newPreview").empty().append(adverSubscribeAuditAdd.appendUrl("AttachmentController.do?method=showImg&objId="+adverFile,adverHeight,adverWidth));
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
	adverSubscribeAuditAdd.initPreview(fileType,adverFile,adverHeight,adverWidth,adverLink);
});

//返回
$('#adverSubscribeAuditReturn').click(function(){
	$('#conBody').loadPage($('#initPath').val()+'/AdvertisingSubscribeController.do?method=toAdverSubscribeAuditList');
});

//审核通过或审核不通过或审核通过并发布
$('button[id^=adverSubscribetBtn_audit_]').click(function(){
	var disp = "";
	var suffix = $(this).attr('id').replace("adverSubscribetBtn_audit_","");
	if(suffix == "yes"){disp="审核通过";}
	if(suffix == "no"){disp="审核不通过";}
	if(suffix == "yes_publish"){disp="审核通过并发布";}
	if(window.confirm("确定广告订阅"+disp+"吗?")){
		//表单提交
		adverSubscribeAuditAdd.submit($(this).attr('id').replace("adverSubscribetBtn_audit_",""));
	}
});

//表单提交
adverSubscribeAuditAdd.submit = function(saveType){
	var fileType = $('#fileType').val();
	var addUrl = "";
	if(saveType=="yes"){
		$('#auditStatus').val('02');
		$('#useStatus').val('01');
	}else if(saveType=="yes_publish"){
		$('#auditStatus').val('02');
		$('#useStatus').val('01');
		addUrl="&isPublish=true";
		
	}else{
		$('#auditStatus').val('03');
		$('#useStatus').val('00');
	}
	var url = $('#initPath').val()+'/AdvertisingSubscribeController.do?method=auditAdverAndRelease'+addUrl;

	if('02' != fileType){
		//判断此广告位是否还有有效的广告订阅信息
		//$.getJSON($('#initPath').val()+"/AdvertisingPositionController.do?method=isHasAdverSubcribe",{"objId":$('#positionId').val(),"operType":"auditValide"},function(json){
			//if(json.result == 'true'){
			//	alert("此广告位上还存在有效的广告,请先确认此广告位不存在有效的广告!");return;
			//}else{
				$('#adverSubscribeAuditForm').ajaxSubmit({
					url:url,
					dataType:'json',
					success:function(json){
						if(json.result == 'true'){
							alert("操作成功！");
							$('#adverSubscribeAuditReturn').click();
						}
					},
					error:function(msg){
						alert(JSON.stringify(msg));
					}
				});
		//	}
		//});
	}else{
		$('#adverSubscribeAuditForm').ajaxSubmit({
			url:url,
			dataType:'json',
			success:function(json){
				if(json.result == 'true'){
					alert("操作成功！");
					$('#adverSubscribeAuditReturn').click();
				}
			},
			error:function(msg){
				alert(JSON.stringify(msg));
			}
		});	
	}
}
</script>
