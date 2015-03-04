var adverSubscribeAddModify = {};

//flash字符串拼接
adverSubscribeAddModify.appendUrl = function(filePath,adverHeight,adverWidth){
//	var srcUrl = '';
//	srcUrl += '<embed width="320" height="267" pluginspage="http://www.macromedia.com/go/getflashplayer" ';
//	srcUrl += 'flashvars="isShowRelatedVideo=false&amp;VideoIDS=63298273&amp;isAutoPlay=false&amp;isDebug=false&amp;UserID=0&amp;RecordCode=1001,1002,1003,1004,1005,1006,2001,3001,3002,3003,3004,3005,3007,3008,9999&amp;RecordResource=index&amp;isLoop=false&amp;imglogo=http://g3.ykimg.com/1130391F464D8A9176459803C5DAE1E3578090-D208-AFE6-374B-FF70CCA22889?u=1300926839&amp;winType=index&amp;playMovie=true&amp;MMControl=true&amp;MMout=true" ';
//	srcUrl += 'wmode="transparent" quality="high" bgcolor="#FFFFFF" name="index_player_swf" id="index_player_swf" ';
//	srcUrl += 'src="' + filePath + '" ';
//	srcUrl += 'type="application/x-shockwave-flash"> ';
//	return srcUrl;
	var flashPre;
	flashPre = document.createElement("embed");
	flashPre.type="application/x-shockwave-flash";
	flashPre.width=adverWidth;
	flashPre.height=adverHeight;
	flashPre.src=filePath;
	return flashPre;
}

//初始化广告预览
adverSubscribeAddModify.initPreview = function(fileType,adverFile,adverHeight,adverWidth,adverLink){
	//图片
	if("00" == fileType){
		$("#newPreview").empty().append('<a href="'+adverLink+'" target="_blank"><img src="AttachmentController.do?method=showImg&objId='+adverFile+'" width="'+adverWidth+'px" height="'+adverHeight+'px"></img></a>');
	}
	//flash
	if("01" == fileType){
		$("#newPreview").empty().append(adverSubscribeAddModify.appendUrl("AttachmentController.do?method=showImg&objId="+adverFile,adverHeight,adverWidth));
	}
	//跑马灯
	if("02" == fileType){
		$('#newPreview').loadPage($("#initPath").val()+"/AdvertisingPositionController.do?method=toAdverPositionDetail&objId="+$('#advertisingPositionId').val()+"&operType=preview");
	}
}

$(document).ready(function(){
	//日期表单验证
	$("#startTime").epsDatepicker();
	$("#endTime").epsDatepicker();
	
	//修改时初始化展示广告预览
	var fileType = $('#fileType').val();//广告位类型
	var adverHeight = $("#adverHeight").val();//广告高度
	var adverWidth = $("#adverWidth").val();//广告宽度
	var adverFile = $('#adverFile').val();//广告文件Id
	var adverLink = $("#adverLink").val();//广告链接
	if("" != fileType && fileType != null){
		adverSubscribeAddModify.initPreview(fileType,adverFile,adverHeight,adverWidth,adverLink);
		$('#advertisingPositionId').attr("disabled","disabled");
	}
	
	
});

//广告位信息
$('#advertisingPositionId').change(function(){
	var objId = this.value;
	if(objId == null || objId == ""){
		$('#adverPosInfoDiv').empty();$('#adverPosInfoDiv').addClass("hidden");
		$('#newPreview').empty();
		$('#adverFileSizeSpan').html("0");
		return;
	}else{
		$('#adverPosInfoDiv').removeClass("hidden");
	}
	$.getJSON($('#initPath').val()+"/AdvertisingPositionController.do?method=toAdverPositionDetail",{"objId":objId,"operType":"selectLoad"},function(json){
		var strHtml = "<lable>广告类型：</lable>"+json.advertisingPosition.adverTypeCN+"<br><lable>容量(KB)：</lable>"+json.advertisingPosition.adverFileMaxValue+"<br>";
			strHtml = strHtml + "<lable>高度(px)：</lable>"+json.advertisingPosition.adverLength+"<br><lable>宽度(px)：</lable>"+json.advertisingPosition.adverWidth+"<br>";
			strHtml = strHtml + "<lable>资费(元/月)：</lable>"+json.advertisingPosition.adverOutlay+"";
		$('#adverPosInfoDiv').empty().append(strHtml);
		$('#fileType').val(json.advertisingPosition.adverType);//文件类型
		$('#adverHeight').val(json.advertisingPosition.adverLength);//高度
		$('#adverWidth').val(json.advertisingPosition.adverWidth);//宽度
		$('#adverFileMaxValue').val(json.advertisingPosition.adverFileMaxValue);//容量
		$('#pictureFile').val("");
		$('#newPreview').empty();
		$('#adverFileSizeSpan').html(json.advertisingPosition.adverFileMaxValue);
		
		//'跑马灯'时执行
		if("02" == json.advertisingPosition.adverType && json.adverSubscribeList.length>0){
			$('#newPreview').loadPage($("#initPath").val()+"/AdvertisingPositionController.do?method=toAdverPositionDetail&objId="+objId+"&operType=preview");
		}
	});
});

function pictureFileOnClick(){
	var flag = true;
	if($('#advertisingPositionId').val()==''){
		alert('请先选择广告位!')
		flag = false;
	}
	return flag;
}

//预览广告图片文件
$('#pictureFile').change(function(){

	var adverHeight = $("#adverHeight").val();//广告高度
	var adverWidth = $("#adverWidth").val();//广告宽度
	var adverLink = $("#adverLink").val();//广告链接
	
	var filePath = $("#pictureFile").val();
	var fileName = filePath.replace(/.+\\([^\\]+)/,'$1');
	var i = fileName.lastIndexOf('.');       	 //从右边开始找第一个'.'
	var len = fileName.length;                	 //取得总长度
	var str = fileName.substring(len,i+1);    	 //取得后缀名
	var exName = "PNG,BMP,JPG,GIF"; 		 //允许的后缀名
	var flaName = "SWF";
	var k = (exName+','+flaName).indexOf(str.toUpperCase());	 //转成大写后判断
	if(k==-1){                                	 //没有符合的
	    alert("上传文件格式错误！只能上传"+exName+','+flaName+' 格式文件');
		$('#pictureFile').val("")
	}else{
		var fileType = $('#fileType').val();//广告位类型
		if("00" == fileType){//图片
			var pl = exName.indexOf(str.toUpperCase());
			if(pl==-1){
				alert("此广告位是'图片广告'，只能上传图片！");$('#pictureFile').val("");
			}else{
				$("#newPreview").empty().append('<a href="'+adverLink+'" target="_blank"><img src="'+preViewPic(this)+'" width="'+adverWidth+'px" height="'+adverHeight+'px"></img></a>');
			}
		}
		if("01" == fileType){//flash
			if("SWF" != str.toUpperCase()){
				alert("此广告位是FLASH广告，只能上传FLASH！");$('#pictureFile').val("");
			}else{
				//$("#newPreview").empty().append(adverSubscribeAddModify.appendUrl("view/resource/images/L001.swf",adverHeight,adverWidth));
				$("#newPreview").empty().append("");
			}
		}
		if("02" == fileType){//跑马灯
			var pl = exName.indexOf(str.toUpperCase());
			if(pl==-1){
				alert("此广告位是'跑马灯广告'，只能上传图片！");$('#pictureFile').val("");
			}else{
				$('#newPreview').loadPage($("#initPath").val()+"/AdvertisingPositionController.do?method=toAdverPositionDetail&objId="+$('#advertisingPositionId').val()+"&operType=preview");
			}
		}
		
	}
});

//选择投放单位
adverSubscribeAddModify.selectOrgInfo = function(){
	var url = $('#initPath').val()+'/view/bizplatform/organization/manager/select_orginfo.jsp?Hname=orgName&Hid=orgInfoId';
	$.epsDialog({
		title:'请选择单位',
		url:url
	});
}


//返回
$("#adverSubscribeBtnReturn").click(function(){
	$("#conBody").loadPage($("#initPath").val()+"/AdvertisingSubscribeController.do");
});

//保存或提交
$('button[id^=adverSubscribeBtn_]').click(function(){
	if(!$("#adverSubscribeAddOrModifyForm").valid()){alert("请正确填写表单");return;}
	var disp = $(this).attr('id').replace("adverSubscribeBtn_","")=="save"?"保存":"提交";
	//设置使用状态默认值'00'
	$('#useStatus').val('00');
	
	//当为提交时  设置审核状态我为'01'
	if($(this).attr('id').replace("adverSubscribeBtn_","")=="save"){
		if($('#auditStatus').val() == ''){$('#auditStatus').val('00');}
	}else{
		$('#auditStatus').val('01');
	}
	
	//判断是否上传广告图片
	if($('#pictureFile').val() != '' || $('#adverFile').val() != ''){
		if(window.confirm("确定"+disp+"广告吗?")){
			adverSubscribeAddModify.submit();
		}
	}else{
		alert("还未上传广告图片！");
	}
});

//表单form提交
adverSubscribeAddModify.submit = function(){
	var fileValueTotal = $('#fileValueTotal').val();
	if(fileValueTotal == null || fileValueTotal == ''){
		fileValueTotal = 0;
	}
	var url = $("#initPath").val()+"/AdvertisingSubscribeController.do?method=saveAdverSubscribe&fileValueTotal="+fileValueTotal;
	$("#adverSubscribeAddOrModifyForm").ajaxSubmit({
		url:url,
		dataType:"json",
		success:function(json){
				if(json.result == 'true'){
					alert("操作成功！");
					$("#conBody").loadPage($("#initPath").val()+"/AdvertisingSubscribeController.do");
				}
				if(json.result == 'false'){
					alert("文件大小已超出此广告位限制的最大容量");$('#pictureFile').val("");$('#newPreview').empty();
				}
			},
		error:function(msg){
			alert("操作失败,请审查所填数据,重新保存！");
			}
	});
}