<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>


<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/plug-in/jquery/ImgPreView/CJL.0.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/plug-in/jquery/ImgPreView/ImagePreviewd.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/plug-in/jquery/ImgPreView/QuickUpload.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/plug-in/jquery/jquery.qtip-1.0.0-rc3.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/upload/attachmentAjaxImg.js"></script>
<style>
#J_form {line-height: 1.3;}
.upload-pic {margin: 0 10px 10px 5px;}
.upload-pic .tabs {position: relative;padding-left: 22px;overflow: hidden;zoom: 1;}
.upload-pic .tabs li {float: left;width: auto;margin-top: 3px;margin-right: 3px;background: red;}
.upload-pic .tabs .selected {margin-top: 0;}
.upload-pic .tabs li span,.upload-pic .tabs li a {background: url(view/resource/skin/goods/img/upload_pic_tabs.png) no-repeat;}
.upload-pic .tabs li span {float: left;margin: 0;background-color: #e8f2fe;}
.upload-pic .tabs li a {float: left;line-height: 23px;color: #424240;padding: 0 9px;border-bottom: 1px solid #add2ff;background-position: right -30px;}
.upload-pic .tabs .selected span {background-position: 0 -60px;background-color: white;}
.upload-pic .tabs .selected a {line-height: 26px;color: black;font-weight: bold;border-bottom-color: white;background-position: right -90px;}
.upload-pic .tabs li a:hover {text-decoration: none;}
.upload-pic .panel {zoom: 1;margin-top: -1px;background: white;border: 1px solid #add2ff;padding: 8px;}
.pic-manager {/*padding-left: 128px;*/ min-height: 120px;_height: 120px;}
.pic-manager .side {background: url("view/resource/skin/goods/img/picPreview.png") no-repeat scroll 0 0 transparent;/*margin-left: -128px; by yucy*/position: absolute;width: 124px; height:127px;}
.pic-manager .side li, .pic-manager .upload a, .upload-video .upload, .upload-manager .preview, .upload-manager .act span {
    background: url("view/resource/skin/goods/img/upload_sets.png") no-repeat scroll 0 0 transparent;
}
.pic-manager .main { overflow: hidden; /*width: 490px;*/}
.pic-manager .main ul {
	border:none; 
	overflow: hidden; 
	width:99%;
}
	
.pic-manager .main li {
	float: left; 
	margin-right: 10px; 
	position: relative; 
	text-align:center;
	/*width: 90px;*/ 
}
.pic-manager .first .preview { border-color: #FFC097; }
.pic-manager .preview {
    background-color: white;
    background-position: 20px -120px;
    border: 1px solid #CFCFCF;
    /*display: table-cell;*/
    font-family: arial;
    /*
    height: 88px;
    line-height: 88px;
    */
    vertical-align: middle;
    overflow: hidden;
    text-align: center;
    /*width: 88px;*/
}
/*
.pic-manager .preview img {
	max-height: 88px;
	max-width: 88px;
	vertical-align: middle;
}*/


.ke-gecko .form li span, .ke-trident4 .form li span, .ke-trident5 .form li span, .ke-webkit .form li span { display: block; }
.pic-manager .upload a { 
	background-position: -32px -40px; 
	cursor: pointer; 
	display: inline-block; 
	height: 20px; 
	margin-top: 7px; 
	overflow: hidden; 
	width: 90px;
	text-decoration:none;
	hover{text-decoration:underline};
}
.pic-manager .upload input { 
	cursor: pointer; 
	direction: rtl; 
	font-size: 40px; 
	height: 40px; 
	opacity: 0; 
	width: 100px;
	filter:alpha(opacity=0); /* 下面的3行支持不同的浏览器*/
	background-color:#000;
}
	
.main .act {
    background: none repeat scroll 0 0 #C1DBFF;
    display: none;
    height: 18px;
    /*left: 1px;*/
    opacity: 0.8;
    overflow: hidden;
    /*padding: 7px 0 0 17px;*/
    position: absolute;
    padding: 7px 0 0 0;
    /*top: 64px;*/
    /*width: 71px;*/
}
.pic-manager .main .unsupport:hover .act, .pic-manager .main .support:hover .act, .pic-manager .main li.hover .act, .upload-video-ed .main:hover .act, .upload-video-ed .hover .act {
    display: block;
}
.rc-bt {clear: both;}

.upload-manager .main .act span { 
	cursor: pointer; 
	float: left; 
	height: 11px; 
	/*margin-left:20px;*/
	text-indent: -999em; 
	width: 11px; 
}
.upload-manager .act .moveright { background-position: -66px -3px; }
.upload-manager .act .del { background-position: -77px -3px; }

.act span {
    background: url("view/resource/skin/goods/img/upload_sets.png") no-repeat scroll 0 0 transparent;
    postion:absolute;
    background-position: -77px -3px;
    
    cursor: pointer; 
	float: left; 
	height: 11px; 
	/*margin-left:20px;*/
	text-indent: -999em; 
	width: 11px; 
}

.form li span .module-form { margin-left: 5px; margin-right: 10px;}
.form li span .module-form .skin { float: none;}
.module-form .skin {background: none repeat scroll 0 0 #F8F8F8;border: 1px solid #ECECEC;float: left;overflow: hidden;padding: 10px 0 10px 10px;}
.form li span li label, .form li span .module-property li span li label {float: none;line-height: 1.8;padding-right: 0;padding-top: 0;width: auto;}
</style>
<%@page import="com.gpcsoft.core.context.FrameBeanFactory"%>
<%@ page language="java" import="com.gpcsoft.srplatform.auth.domain.Attachment,java.text.SimpleDateFormat,com.gpcsoft.srplatform.auth.service.AttachmentService,com.gpcsoft.core.utils.locator.ServiceLocator,java.util.*"%>
<%
	//获得接口
	AttachmentService attachmentService = (AttachmentService)FrameBeanFactory.getBean("attachmentServiceImpl");
    List attachmentList=new ArrayList();
    int quantity = Integer.valueOf(request.getParameter("quantity"));//附件form的数量
	String attachRelaID = request.getParameter("attachRelaId");//与哪块连接?
	if(attachRelaID == null || "".equals(attachRelaID.trim())|| "null".equals(attachRelaID.trim())){
		attachRelaID = "";//默认状态
	}else{
		attachmentList = attachmentService.getAttachmentsByRealID(attachRelaID); //得到该块对应的附件列表，可以对其维护 
	} 
	String fileType = request.getParameter("fileType");//要求附件格式串，以逗号分开，如：doc,xml,txt
	if(fileType == null || "".equals(fileType.trim()) || "null".equals(fileType.trim())){ 
		fileType = "all";//表示所有的文件格式都可以上传   		
	}
	String defineSelf = request.getParameter("defineSelf");//与哪块连接可能不是用字段attachRelaId，就在此定义
	if(defineSelf == null || "".equals(defineSelf.trim()) || "null".equals(defineSelf.trim())){
		defineSelf = "attachRelaId";
	}
	String maxSize = request.getParameter("maxSize");
	if(maxSize == null || "".equals(maxSize.trim()) || "null".equals(maxSize.trim())){
		maxSize = "";
	}
	String isView = request.getParameter("isView");//是否只显示图片
%>

<div class="pic-manager">
<div class="main">
	<input type="hidden" name="fileType" value="<%=fileType%>"/>
    <input type="hidden" name="<%=defineSelf %>" value="<%=defineSelf%>"/>
    <input type="hidden" name="attachRelaID" value="<%=attachRelaID%>"/>
    <input type="hidden" name="maxSize" value="<%=maxSize%>"/>
    <input type="hidden" name="isView" value="<%=isView%>"/>
    <input type="hidden" name="reducedWidth" value="<%=request.getParameter("reducedWidth")%>"/>
    <input type="hidden" name="reducedHeight" value="<%=request.getParameter("reducedHeight")%>"/>
 
    <input type="hidden" name="maxWidthM" value="${param.maxWidth }"/>
    <input type="hidden" name="maxHeightM" value="${param.maxHeight }"/>
    <input type="hidden" name="nopicPath" value="${param.nopicPath }"/>
    <input type="hidden" name="pic_WH_rule_str" value="${param.pic_WH_rule_str }"/>
 <ul id="imgUploadUL">   
	<%	int i;
		for(i=0;i<attachmentList.size();i++){
 		Attachment attachment = (Attachment)attachmentList.get(i);
 	%>
 				<form id='form_<%=i%>' method="post" enctype="multipart/form-data">
					<li class="first firstLi" style="width:auto;" >
						<span style="width:${param.maxWidth}px; height:${param.maxHeight}px;padding-left:0; display:inline-block;" id="newPreview<%=i%>" class="preview">
						<img style="width:${param.maxWidth}px;height:${param.maxHeight}px;vertical-align:baseline;" src="AttachmentController.do?method=showImg&objId=<%=attachment.getObjId() %>" />
						<script type="text/javascript">
						$("#newPreview<%=i%>").find("img").qtip({
						    content: '<a href="javascript:void(0);" onclick="attachmentAjaxImg.del(\''+'<%=attachment.getObjId()%>'+'\',<%=i%>);" style="color:#68BADA;font-weight:blod;">删除</a>',
						    position: 'topRight', 
						    hide:{fixed:true},
						    style:{padding: '1px 5px', name: 'dark'}
						});
						</script>
						</span>
						<%	if("0".equals(isView)){
			 			%>	
						<span class="upload" style="display:block;padding-left:0;"><a href="javascript:void(0);">
						<input onchange="attachmentAjaxImg.upload(<%=i%>,this);" type="file" attachid="<%=attachment.getObjId() %>" name="attachFile" id="attachFile_<%=i%>" disabled="disabled"/>
						</a></span>

						<%	}  %>		
					</li>
				</form>
 	<%} %>
 	
 	<%for(i=attachmentList.size();i<quantity;i++){
 		%>
				<form id='form_<%=i%>' method="post" enctype="multipart/form-data">
					<li class="first firstLi" style="width:auto;"  >
						<span style="width:${param.maxWidth}px; height:${param.maxHeight}px;padding-left:0;display:inline-block;" id="newPreview<%=i%>" class="preview">
						<img style="width:${param.maxWidth}px; height:${param.maxHeight}px;vertical-align:baseline;" src="${param.nopicPath}"/>
							<span class="act" style="width:${param.maxWidth+2}px;top:${param.maxHeight-23}px;" ></span>
						</span>
					<%	if("0".equals(isView)){
		 			%>	
						<span class="upload" style="display:block;padding-left:0;"><a href="javascript:void(0);"><input type="file" name="attachFile" id="attachFile_<%=i%>"/></a></span>
					<%	}  %>
					</li>
				</form>
 		<%
 	} %>
 </ul>
 </div>
  <div><span class="formTips smallLight">默认比例：（${param.maxWidth}:${param.maxHeight}）</span></div>
  
  
  <%for(i = 0;i<quantity;i++){%>
  
    <img class="hidden" id="tempImgM_<%=i%>" >
    
  <%}%>
</div>

