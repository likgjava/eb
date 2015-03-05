<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.upload-pic {margin: 10px;border: 1px solid #add2ff;padding: 8px;}
.pic-manager .first .preview { border-color: #FFC097; }
.pic-manager .preview {
    background-color: white;
    background-position: 20px -120px;
    border: 1px solid #CFCFCF;
    /*display: table-cell;*/
    font-family: arial;
    /*height: 88px;
    line-height: 88px;*/
    overflow: hidden;
    text-align: center;
    vertical-align: middle;
    /*width: 88px;*/
    margin:0 auto;
}

.pic-manager .upload a,
.upload-manager .act span {
    background: url("view/resource/skin/goods/img/upload_sets.png") no-repeat scroll 0 0 transparent;
    postion:absolute;
}
.pic-manager .preview img {
	/*
	max-height: 88px;max-width: 88px;*/
	vertical-align: middle;
}

.pic-manager .upload a { 
	background-position: -32px -40px; 
	cursor: pointer;
	display: inline-block;
	height: 20px; 
	/*margin-top: 7px;*/ 
	overflow: hidden; 
	width: 90px;
	text-decoration:none;
	hover{text-decoration:underline}
}

.pic-manager .upload input { 
	cursor: pointer; 
	direction: rtl; 
	opacity: 0; 
	width: 50px;
	filter:alpha(opacity=0); /* 下面的3行支持不同的浏览器*/
	background-color:#000;
}
.upload-manager .act {
    background: none repeat scroll 0 0 #C1DBFF;
    /*bottom: 18px;*/
    display:none;
    height: 18px;
    margin-left: 17px;
    opacity: 0.8;
    overflow: hidden;
    padding: 7px 0 0 0;
    position: absolute;
    margin:0 auto;
}
.pic-manager .unsupport:hover .act,
.pic-manager .support:hover .act,
.pic-manager div.hover .act, 
.upload-video-ed .hover .act {
    display: block;
}
.rc-bt {clear: both;}
.upload-manager .act div { cursor: pointer; float: left; height: 11px; margin-left:20px;text-indent: -999em; width: 11px; }
.upload-manager .act .moveright { background-position: -66px -3px; }
.upload-manager .act .del { background-position: -77px -3px; }
</style>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/plug-in/jquery/ImgPreView/CJL.0.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/plug-in/jquery/ImgPreView/ImagePreviewd.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/plug-in/jquery/ImgPreView/QuickUpload.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/plug-in/jquery/jquery.qtip-1.0.0-rc3.js"></script>

<!-- 最大宽度 -->
<input type="hidden" name="maxWidth" value="${param.maxWidth}"/>
<!-- 最大高度 -->
<input type="hidden" name="maxHeight" value="${param.maxHeight}"/>
<!-- 表单名称 -->
<input type="hidden" name="propertieName" value="${param.propertieName}"/>
<!-- 上传图片宽高规则字符参数 -->
<input type="hidden" name="pic_WH_rule_str" value="${param.pic_WH_rule_str}"/>
<!-- 表单值 -->
<input type="hidden" name="propertieValue" value="${param.propertieValue}"/>

<div class="upload-manager upload-pic" style="display: block; float: left; padding: 0pt; margin: 0pt;">
	<div class="pic-manager" style="padding-left:0; height:${param.maxHeight+35}px">
			<div style="width:${param.maxWidth}px; height:${param.maxHeight}px;" id="newPreview" class="preview">
				<img id="newPreviewImg" style="width:${param.maxWidth}px; height:${param.maxHeight}px; max-width:${param.maxWidth}px; max-height:${param.maxHeight}px;" 
				src="<c:choose><c:when test="${param.propertieValue == null || param.propertieValue=='' }">${param.nopicPath}</c:when><c:otherwise>AttachmentController.do?method=showImg<c:if test="${param.propertieValue!=null}">&objId=${param.propertieValue}</c:if></c:otherwise></c:choose>"/>
			</div>
			<div class="upload" style="margin: 0pt auto; text-align: center;">
				<a href="javascript:void(0);" name="pictureFileA"><input type="file" name="${param.propertieName}" id="${param.propertieName}" onchange="changeFilea();"/></a>
			</div>
	</div>
	<div style="margin:0 auto; text-align:center;"><span class="formTips smallLight">默认尺寸：（${param.maxWidth}:${param.maxHeight}）</span></div>
</div>
<div style="clear:both;"></div>
<img class="hidden" id="tempImg">

<script type="text/javascript">

var maxWidth = $("input[name=maxWidth]").val();

var maxHeight = $("input[name=maxHeight]").val();

var replaceHtml = '<a href="javascript:void(0);" name="pictureFileA"><input type="file" name="'+$("input[name=propertieName]").val()+'" id="'+$("input[name=propertieName]").val()+'" onchange="changeFilea();"/></a>';

//初始
initChange = function(){
	var ip = new ImagePreview( 
			$$($("input[name=propertieName]").val()), 
			$$("tempImg"), 
			{
				maxWidth: maxWidth, 
				maxHeight: maxHeight, 
				action: "viewImg.jsp",
				onShow:function(){
					setTimeout("checkSize()", 100);
				}
			}
	);
	ip.img.src = ImagePreview.TRANSPARENT;
	changeFilea = function(){ 
		ip.preview(); 
	};
}

//检测尺寸
checkSize = function(){
    var img = $("#tempImg");
    var img_width = img.width();
    var img_height = img.height();

    //限制比例
	if( img_width/img_height != maxWidth/maxHeight ){
    	$("a[name=pictureFileA]").replaceWith( replaceHtml );
		//再次初始
    	initChange();
    	alert("图片不符合标准,请上传系统默认尺寸("+maxWidth+":"+maxHeight+")或同比例的图片");
	}else{
		$("#newPreviewImg").replaceWith( img.clone().attr("id","newPreviewImg").attr("class","show") );
		$("#newPreviewImg").qtip({
			         content: '<a href="javascript:void(0);" onclick="delPicture();" style="color:#68BADA;font-weight:blod;">删除</a>', // Give it some content
			         position: 'topRight', // Set its position
			         hide: { fixed: true /* Make it fixed so it can be hovered over*/},
			         style: {
			            padding: '1px 5px', /*Give it some extra padding*/
			            name: 'dark' /*And style it with the preset dark theme*/
			         }
		});
    }
}

//删除图片
delPicture = function(){
	$("#newPreviewImg").qtip('destroy');
	$("a[name=pictureFileA]").replaceWith( replaceHtml );
	//再次初始
	initChange();
	//显示初始图片
	setTimeout("showInitImg()", 100);//设定一点延迟保证程序稳定
}

//显示初始的图片
showInitImg = function(){
	$("#newPreviewImg").attr("src","AttachmentController.do?method=showImg");
}

$(document).ready(function(){
	//初始一次
	initChange();
})

</script>