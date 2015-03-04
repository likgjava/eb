<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/pubservice/css/adver_position.css"/>

<div class="formLayout form2Pa">    
	<form id="adverPositionAddOrModifyForm" name="adverPositionAddOrModifyForm" method="post">
		<h4 class="title"><span>广告位信息</span></h4>
		<input type="hidden" id="objId" name="objId" value="${advertisingPosition.objId}"/>
		<ul>
			<li class="fullLine">
				<label>广告位置：</label>
				<input type="text" id="dictionaryDicName" value="${advertisingPosition.positionDictionary.dicName }" size="50" readonly="readonly" class="required sysicon siSearch" onmouseover="adverPositionAddModify.positionDicShow()"/><span class="eleRequired">*</span>
				<div id="dictionaryListDiv" class="adverPosInfoDiv">
					<c:forEach var="dictionary" items="${positionDictionaryList}" varStatus="status">
						<a href="javascript:void(0);" name="posiion_${dictionary.objId}" onclick="adverPositionAddModify.positionDictionaryClick('${dictionary.objId}','${dictionary.dicValue}','${dictionary.dicName}');return false;"><span>${dictionary.dicName}&nbsp;&nbsp;</span></a>
						<c:if test="${status.count%4 == 0}"><br></c:if>
					</c:forEach>
				</div>
				<input type="hidden" id="positionName" name="positionName" value="${advertisingPosition.positionName }" />
				<input type="hidden" id="positionDictionaryObjId" name="positionDictionary.objId" value="${advertisingPosition.positionDictionary.objId }" />
			</li>
			<li class="fullLine">
				<label>高度(px)：</label>
				<input type="text" id="adverLength" name="adverLength" value="${advertisingPosition.adverLength}" size="50" class="required digits"/><span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>宽度(px)：</label>
				<input type="text" id="adverWidth" name="adverWidth" value="${advertisingPosition.adverWidth}" size="50" class="required digits"/><span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>容量(KB)：</label>
				<input type="text" id="adverFileMaxValue" name="adverFileMaxValue" value="${advertisingPosition.adverFileMaxValue}" size="50" class="required floats"/><span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>资费(元/月)：</label>
				<input type="text" id="adverOutlay" name="adverOutlay" value="${advertisingPosition.adverOutlay}" class="required money" size="50"/><span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>文件类型：</label>如下示例
				<input type="hidden" id="advertisingPositionAdverType" value="${advertisingPosition.adverType }"/><br>
			</li>
		</ul>
		<div class="adverTypeDivSize">
			<div class="adverDivPo"><img id="view" src="view/resource/images/goods_class_img7.jpg"></img></div>
			<input type="radio" name="adverType" id="adverTypePic" value="00" checked="checked"/>图片示例
		</div>
		<div class="adverTypeDivSize">
	        <div id="indexMiddleAd" class="adverDivPo">
				  <object id="FlashID" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="250px" height="200px">
				  <param name="movie" value="view/resource/images/L001.swf" />
				  <param name="quality" value="high" />
				  <param name="wmode" value="transparent" />
				  <param name="swfversion" value="8.0.35.0" />
				  <!-- 此 param 标签提示使用 Flash Player 6.0 r65 和更高版本的用户下载最新版本的 Flash Player。如果您不想让用户看到该提示，请将其删除。 -->
				  <param name="expressinstall" value="Scripts/expressInstall.swf" />
				  <!-- 下一个对象标签用于非 IE 浏览器。所以使用 IECC 将其从 IE 隐藏。 -->
				  <!--[if !IE]>-->
				  <object type="application/x-shockwave-flash" data="view/resource/images/L001.swf" width="250px" height="200px">
				    <!--<![endif]-->
				    <param name="quality" value="high" />
				    <param name="wmode" value="transparent" />
				    <param name="swfversion" value="8.0.35.0" />
				    <param name="expressinstall" value="Scripts/expressInstall.swf" />
				    <!-- 浏览器将以下替代内容显示给使用 Flash Player 6.0 和更低版本的用户。 -->
				    <div>
				      <h4>此页面上的内容需要较新版本的 Adobe Flash Player。</h4>
				      <p><a href="http://www.lzhb.com.cn"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="获取 Adobe Flash Player" width="112" height="33" /></a></p>
				    </div>
				    <!--[if !IE]>-->
				  </object>
				  <!--<![endif]-->
				</object>  
	      	</div>
			<input type="radio" name="adverType" id="adverTypeFla" value="01"/>FLASH示例
		</div>
		<div class="adverTypeDivSize">
			<div id="focus" class="imgPlay">
				<ul>
					<li><a href="#" title="开启新年财富大门"><img src="view/resource/skin/pubservice/img/nostart.png"/><span>开启新年财富大门！</span></a></li>
					<li><a href="#" title="新年新液晶，爆团抱回家！"><img src="view/resource/skin/pubservice/img/being.png"/><span>新年新液晶，爆团抱回家！</span></a></li>
					<li><a href="#" title="成长记录片，随拍随想"><img src="view/resource/skin/pubservice/img/over.png"/><span>成长记录片，随拍随想</span></a></li>
				</ul>
			</div>
			<input type="radio" name="adverType" id="adverTypeHor" value="02"/>跑马灯示例
		</div>
	</form>
</div>
<div class="conOperation">
	<button class="largeBtn" id="adverPositionBtn_save" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
	<button class="largeBtn" id="adverPositionBtnReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
</div>

<script>

var adverPositionAddModify={};

//文件类型 初始化
adverPositionAddModify.initAdverType = function(){
	var advertisingPositionAdverType = $('#advertisingPositionAdverType').val();
	if(advertisingPositionAdverType == '02'){//跑马灯
		$('#adverTypeHor').attr("checked","checked");
	}else if(advertisingPositionAdverType == '01'){//FLA
		$('#adverTypeFla').attr("checked","checked");
	}else{//图片
		$('#adverTypePic').attr("checked","checked");
	}
}

$(document).ready(function(){
	$('#dictionaryListDiv').hide();
	
	//文件类型 初始化
	adverPositionAddModify.initAdverType();

	//跑马灯
	$("#focus").imgPlay();
});

//返回
$("#adverPositionBtnReturn").click(function(){
	$("#conBody").loadPage($("#initPath").val()+"/AdvertisingPositionController.do");
});

//判断是否还有广告记录,若有则广告文件类型不能更改
adverPositionAddModify.judgeCondition = function(url){
	var returnValue = true;
	var objId = $('#objId').val();
	var advertisingPositionAdverType1 = $('#advertisingPositionAdverType').val();//广告文件类型
	var value = true;
	if(advertisingPositionAdverType1 == '02'){
		value = $('#adverTypeHor').attr("checked");
	}else if(advertisingPositionAdverType1 == '01'){//FLA
		value = $('#adverTypeFla').attr("checked");
	}else{//图片
		value = $('#adverTypePic').attr("checked");
	}
	
	if(value == false){
		$.getJSON($('#initPath').val()+"/AdvertisingPositionController.do?method=isHasAdverSubcribe",{"objId":objId},function(json){
			if(json.result=="true"){
				alert("此广告位下还存在广告订阅记录，不能更改广告文件类型");
				//文件类型 初始化
				adverPositionAddModify.initAdverType();
			}
			if(json.result == 'false'){
				$("#adverPositionAddOrModifyForm").ajaxSubmit({
					url:url,
					dataType:"json",
					success:function(json){
						alert("操作成功！");
						$("#conBody").loadPage($("#initPath").val()+"/AdvertisingPositionController.do");
						},
					error:function(msg){
						alert("操作失败,请审查所填数据,重新保存！");
						}
				});
			}
		});
	}
	if(value == true){
		$("#adverPositionAddOrModifyForm").ajaxSubmit({
			url:url,
			dataType:"json",
			success:function(json){
				alert("操作成功！");
				$("#conBody").loadPage($("#initPath").val()+"/AdvertisingPositionController.do");
				},
			error:function(msg){
				alert("操作失败,请审查所填数据,重新保存！");
				}
		});
	}
}

//保存
$("#adverPositionBtn_save").click(function(){
	if(!$("#adverPositionAddOrModifyForm").valid()){alert("请正确填写表单");return;}
	var url = $("#initPath").val()+"/AdvertisingPositionController.do?method=save";

	//判断是否还有广告记录,若有则广告文件类型不能更改
	adverPositionAddModify.judgeCondition(url);
});

//字典项展开
adverPositionAddModify.positionDicShow = function(){
	$('#dictionaryListDiv').show();
}
//字典项隐藏
adverPositionAddModify.positionDicHidden = function(){
	$('#dictionaryListDiv').hide();
}

//广告位置选择点击事件
adverPositionAddModify.positionDictionaryClick = function(dictionaryId,dictionaryValue,dicName){
	$('#positionName').val(dictionaryValue);
	$('#positionDictionaryObjId').val(dictionaryId);
	$('#dictionaryDicName').val(dicName);

	$('#dictionaryListDiv').hide();
}
</script>