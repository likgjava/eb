<%@ page pageEncoding="UTF-8"%>

<link href="<%=request.getContextPath()%>/view/pubservice/application/cropzoomimg/css/jquery-ui-1.7.2.custom.css" rel="Stylesheet" type="text/css" />
<style type="text/css">
.crop { width: 750px; margin: 10px auto; border: 1px solid #d3d3d3; padding: 4px; background: #fff }
#cropzoom_container { float: left; width: 500px; background-color: #CCCCCC; border: 1px solid #666666; height: 360px; overflow: hidden; position: relative; }
#preview { float: left; width: 150px; height: 200px; border: 1px solid #999; margin-left: 10px; padding: 4px; background: #f7f7f7; }
.page_btn { float: left; width: 220px; margin-top: 20px; line-height: 30px; text-align: center }
.top_title { margin-top: 10px; line-height: 24px; text-align: center }
.clear { clear: both }
.btn { cursor: pointer }
</style>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/application/cropzoomimg/js/jquery.cropzoom.js"></script>

<!--原图片的Attachement的objId-->
<input type="hidden" id="cropOldAttachmentId" name="oldAttachmentId" value="${param.oldAttachmentId}" />
<!--图片的属性名称，用于回填上传后的附件Id-->
<input type="hidden" id="cropPropertyName" name="propertyName" value="${param.propertyName}" />
<!--裁剪框的默认宽度-->
<input type="hidden" id="cropPicWidth" name="picWidth" value="${param.picWidth}" />
<!--裁剪框的默认高度-->
<input type="hidden" id="cropPicHeight" name="picHeight" value="${param.picHeight}" />
<!--上传图片的宽高规则-->
<input type="hidden" id="cropImgPicWHRule" name="pic_WH_rule_str" value="${param.pic_WH_rule_str}" />

<div id="main">
	<div class="top_title">
		<form id="cropZoomForm" method="post" enctype="multipart/form-data">
			请选择图片：<input type="file" name="attachFile" class="btn" id="selectImgBut" /> 支持JPG、JPEG、PNG、BMP等格式的图片
		</form>
	</div>

	<div class="crop">
		<div id="cropzoom_container"></div>
		<div id="preview" style="width: ${param.picWidth}px; height: ${param.picHeight}px;">
			<img id="generated" style="width: ${param.picWidth}px; height: ${param.picHeight}px;" src="<%=request.getContextPath()%>/view/pubservice/application/cropzoomimg/img/head.gif" />
		</div>
		<div class="page_btn">
			<input type="hidden" id="cropImgPath" />
			<input type="button" class="btn" disabled="disabled" id="cropImg" value="剪切图片" />
			<input type="button" class="btn" disabled="disabled" id="uploadNormalImg" value="确定" />
			<input type="button" class="btn" disabled="disabled" id="restoreImg" value="图片复位" />
		</div>
		<div class="clear"></div>
	</div>
</div>

<div class="conOperation">
	<button id="closeCropZoom" type="button"  class="largeBtn" ><span>关闭</span></button>
</div>

<script>
var CropZoomImg = {};
var cropzoom = null;
CropZoomImg.sourceImgWidth = 0; //源图片的宽度
CropZoomImg.sourceImgHeight = 0; //源图片的高度
CropZoomImg.imgSource = ''; //源图片的路径

//初始化控件
CropZoomImg.initCropZoom = function(imgSource, sourceImgWidth, sourceImgHeight){
	cropzoom = null;
	cropzoom = $('#cropzoom_container').cropzoom({
        width: 500,
        height: 360,
        bgColor: '#ccc',
        enableRotation: false, //是否可旋转  
        enableZoom: true, //是否放大、缩小、变焦 
		selector: {
			w: Number($('#cropPicWidth').val()), //裁剪框的默认宽度
			h: Number($('#cropPicHeight').val()), //裁剪框的默认高度
			aspectRatio: true, //改变选择框的大小时，是否保持宽高比
			showPositionsOnDrag: true, //显示 选择框的左上角的坐标:x:175px,Y:80px 
			showDimetionsOnDrag: false, //显示 选择框的的大小  
			centered: true, //旋转框是否存在图片的中央 
			bgInfoLayer:'#fff',
			borderColor: 'blue',
			borderColorHover: 'yellow'
		},
		image: {
			source: imgSource,
			width: sourceImgWidth,
			height: sourceImgHeight,
			minZoom: 50,
			maxZoom: 250
		}
    });
}

$(document).ready(function(){
	var cropOldAttachmentId = $('#cropOldAttachmentId').val(); //原图片的Attachement的objId
	
	//存在原图
	if(cropOldAttachmentId){
		$.getJSON($('#initPath').val()+'/CropZoomImgController.do?method=copyOldImgToCropFolder', {'objId':cropOldAttachmentId}, function(json){
			if(json.success && json.isFindOldImg){
				CropZoomImg.initCropZoom(json.sourceImgPath, Number(json.sourceImgWidth), Number(json.sourceImgHeight));
				$('#cropImg').attr('disabled', false); //放开剪切图片按钮
				$('#restoreImg').attr('disabled', false); //放开图片复位按钮

				//保存源图片的路径、宽、高
				CropZoomImg.sourceImgWidth = Number(json.sourceImgWidth);
				CropZoomImg.sourceImgHeight = Number(json.sourceImgHeight);
				CropZoomImg.imgSource = json.sourceImgPath;
			}
		});
	}

    //裁剪图片
	$("#cropImg").click(function(){
		if(!cropzoom){alert('请选择图片！'); return ;}
		cropzoom.send($("#initPath").val()+'/CropZoomImgController.do?method=uploadCropImg', 'POST', {}, function(jsonStr) {
			var json = eval('('+jsonStr+')');
			$('#generated').attr('src', json.cropImgPath);
			$('#cropImgPath').val(json.cropImgPath);
		});
		$('#uploadNormalImg').attr('disabled', false); //放开确定按钮
	});
	
	//图片复位
	$("#restoreImg").click(function(){
		$("#generated").attr("src", $("#initPath").val()+"/view/pubservice/application/cropzoomimg/img/head.gif");
		cropzoom.restore();
		//CropZoomImg.initCropZoom(CropZoomImg.imgSource, CropZoomImg.sourceImgWidth, CropZoomImg.sourceImgHeight);
		$('#uploadNormalImg').attr('disabled', true); //禁用确定按钮		  
	});

	//选择图片
	$("#selectImgBut").change(function(){
		//判断是否为有效地图片格式
		var fileName = $("#selectImgBut").val().replace(/.+\\([^\\]+)/,'$1');
		var suffixName = fileName.substring(fileName.length, fileName.lastIndexOf('.')+1); //取得后缀名
		var exName = "JPG,JPEG,PNG,BMP"; //允许的后缀名
		if(exName.indexOf(suffixName.toUpperCase()) == -1){
		    alert("选择图片错误！只能选择"+exName+"格式的图片！");
			this.value = "";
			return ;
		}
		
		$('#cropZoomForm').ajaxSubmit({
			url:$('#initPath').val()+'/CropZoomImgController.do?method=uploadSourceImg',
			dataType:'json',
			success:function(json){
				if(json.failtrue){ alert(json.result); return; }
				cropzoom = null;
				CropZoomImg.initCropZoom(json.sourceImgPath, Number(json.sourceImgWidth), Number(json.sourceImgHeight));
				$('#cropImg').attr('disabled', false); //放开剪切图片按钮
				$('#restoreImg').attr('disabled', false); //放开图片复位按钮

				//保存源图片的路径、宽、高
				CropZoomImg.sourceImgWidth = Number(json.sourceImgWidth);
				CropZoomImg.sourceImgHeight = Number(json.sourceImgHeight);
				CropZoomImg.imgSource = json.sourceImgPath;
			},
			error:function(msg){
				alert(JSON.stringify(msg));
			}
		});
	})

	//上传正式图片
	$("#uploadNormalImg").click(function(){
		$.getJSON($('#initPath').val()+'/CropZoomImgController.do?method=uploadNormalImg', {'cropImgPath':$("#cropImgPath").val(), 'pic_WH_rule_str':$("#cropImgPicWHRule").val()}, function(json){
			if(json.success){
				$('#'+$('#cropPropertyName').val()).val(json.attachmentObjId);
				$('#newPreview').find('img').attr('src','AttachmentController.do?method=showImg&objId='+json.attachmentObjId);
				$("#closeCropZoom").click();
			}
		});
	});

	//关闭弹出层
	$("#closeCropZoom").click(function(){
		if($("#_dialogID_messageDetail").val() != ""){
			$(document.getElementById($("#_dialogID_messageDetail").val())).find('.epsDialogClose').trigger('click');
		}else{
			$('.epsDialogClose').trigger('click');
		}
	});
});
</script>