<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" >
var ServiceFrom = {};

$(document).ready(function(){
	$("#ServiceForm").validate();
	
	//保存
	$("#serviceSave").click(function(){
		if(!$('#ServiceForm').valid()){return;}

		$('#serviceSave').attr('disabled','disabled');
		var url = $('#initPath').val()+"/ServiceBaseController.do?method=saveService";
		$('#ServiceForm').ajaxSubmit({
			url:url,
			dataType:'json',
			success:function(json){
				if("success" == json.returnMessage){
					alert("保存成功！");
					$("#serviceClose").click();
				}else{
					alert(json.returnMessage);
					$('#serviceSave').removeAttr('disabled');
				}
			},
			error:function(msg){
				$('#serviceSave').removeAttr('disabled');
				alert(JSON.stringify(msg));
			}
		});
	});

	//选择前置服务
	$("input[id=servicePrepositionName]").click(function(){
		var url =$('#initPath').val()+'/view/pubservice/application/service/select_service.jsp?dialogId=selectServiceDialog&Hname=servicePrepositionName&Hid=servicePrepositionObjId';
	    $.epsDialog({
		    id: 'selectServiceDialog',
	        title:'请选择前置服务',
	        url:url
	    });
	})

	//关闭
	$("#serviceClose").click(function(){
		$('.epsDialogClose').trigger('click');
	});

	//预览图片
	$("#pictureFile").change(function(){
		var filePath = $("#pictureFile").val();
		var fileName = filePath.replace(/.+\\([^\\]+)/,'$1');
		var i = fileName.lastIndexOf('.');       	 //从右边开始找第一个'.'
		var len = fileName.length;                	 //取得总长度
		var str = fileName.substring(len,i+1);    	 //取得后缀名
		var exName = "PNG,BMP,JPG,GIF";       		 //允许的后缀名
		var k = exName.indexOf(str.toUpperCase());	 //转成大写后判断
		if(k==-1){                                	 //没有符合的
		    alert("上传文件错误！只能上传"+exName);
			this.value="";
		}else{
			$("#newPreview").find('img').attr("src",preViewPic(this));
		}
	});
})
</script>

<form:form id="ServiceForm" method="post" modelAttribute="serviceBase" enctype="multipart/form-data"> 
	<input type="hidden" id="objId" name="objId" value="${serviceBase.objId }"/>
	
	<div class="formLayout form2Pa">
	    <div class="k1">
			<div class="img_250_2" id="newPreview">
				<c:choose>
					<c:when test="${serviceBase.servicePic==null}">
						<img id="view"  src="<%=request.getContextPath()%>/view/resource/skin/smallscale/img/expert_add.gif" width="200px" height="200px"></img>
					</c:when>
					<c:otherwise>
						<img src="<c:url value="AttachmentController.do?method=showImg&objId=${serviceBase.servicePic}" />" width="200px" height="200px">
					</c:otherwise>
				</c:choose>
			</div>
			<input name="pictureFile" type="file" id="pictureFile" size="22"/>
		</div>
		<ul>
			<li class="fullLine">
				<label>服务名称：</label>
				<input id="serviceName" name="serviceName" value="${serviceBase.serviceName}" size="30" class="required">
				<span class="eleRequired">*</span> 
			</li>	
			<li class="fullLine">
				<label>服务费用（元）：</label>
				<input id="servicePrice" name="servicePrice" value="${serviceBase.servicePrice}" class="money required" size="30">
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>服务金额单位：</label>
				<html:select id="serviceUnit" name="serviceUnit" selectedValue="${serviceBase.serviceUnit}" code="pubservice.application.service.serviceUnit"></html:select>
			</li>
			<li class="fullLine">
				<label>前置服务：</label>
				<input type="hidden" id="servicePrepositionObjId" name="servicePreposition.objId" value="${serviceBase.servicePreposition.objId }"/>
				<input type="text" id="servicePrepositionName" class="sysicon siSearch" value="${serviceBase.servicePreposition.serviceName}" size="26"/>
			</li>
			<li class="fullLine">
				<label>外部链接：</label>
				<input id="serviceLink" name="serviceLink" value="${serviceBase.serviceLink}" class="url" size="30">
			</li>
			<li class="fullLine">
				<label>协议外部链接：</label>
				<input id="serviceAgreementLink" name="serviceAgreementLink" value="${serviceBase.serviceAgreementLink}" class="url" size="30">
			</li>
			<li class="fullLine">
				<label>能否单独购买：</label>
				<input type="radio" id="isSinglePurchase1" name="isSinglePurchase" value="1" <c:if test="${serviceBase.isSinglePurchase=='1'}">checked="checked"</c:if>>&nbsp;可以&nbsp;&nbsp;
				<input type="radio" id="isSinglePurchase2" name="isSinglePurchase" value="0" <c:if test="${serviceBase.isSinglePurchase=='0'}">checked="checked"</c:if>>&nbsp;不可以
			</li>
			<li class="fullLine">
				<label>是否推荐服务：</label>
				<input type="radio" id="isRecommendation1" name="isRecommendation" value="1" <c:if test="${serviceBase.isRecommendation=='1'}">checked="checked"</c:if>>&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="isRecommendation2" name="isRecommendation" value="0" <c:if test="${serviceBase.isRecommendation=='0'}">checked="checked"</c:if>>&nbsp;不是
			</li>
		</ul>
	</div>
	<div class="formLayout form2Pa">
		<ul>
			<li class="formTextarea">
				<label>服务描述：</label>
				<textarea name="serviceDesc" id="serviceDesc" maxlength="500" class="required">${serviceBase.serviceDesc}</textarea>
				<span class="eleRequired">*</span>
			</li>
		</ul>
	</div>
	
	<div class="conOperation center">
		<button id="serviceSave" type="button" class="enterBtn"><span><spring:message code="globe.save"/></span></button>
		<button id="serviceClose" type="button" class="operationBtnDiv"><span><spring:message code="globe.close"/></span></button>
	</div>
</form:form>