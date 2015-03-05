<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script language='javascript'> 
var isUpload = false;
/*
function getFullPath(obj) 
 { 
     if(obj) 
     { 
    	 isUpload = true;
    	 $('[name=ravatarRadio]').attr("checked",false);
         //ie 
         if (window.navigator.userAgent.indexOf("MSIE")>=1) 
         { 
             obj.select(); 
             return document.selection.createRange().text; 
         } 
         //firefox 
         else if(window.navigator.userAgent.indexOf("Firefox")>=1) 
         { 
             if(obj.files) 
             { 
                 return obj.files.item(0).getAsDataURL(); 
             } 
             return obj.value; 
         } 
         
         return obj.value; 
     } 
 } 
*/

//图片预览方法
 function preViewPic(obj) { 
     if(obj)    { 
    	 isUpload = true;
    	 $('[name=ravatarRadio]').attr("checked",false);
         if (window.navigator.userAgent.indexOf("MSIE")>=1) { 
             obj.select(); 
             return document.selection.createRange().text; 
         } 
         //firefox 
         else if(window.navigator.userAgent.indexOf("Firefox")>=1) 
         { 
             if(obj.files) 
             { 
                 return obj.files.item(0).getAsDataURL(); 
             } 
             return obj.value; 
         } 
         return obj.value; 
     } 
} 
 
$(document).ready(function(){
	
	$('[name=ravatarRadio]').click(function(){
		var checkedValue = $('[name=ravatarRadio]:checked').val();
		$('#defaultAvatar').val(checkedValue);
		$('#newPreview img').attr("src",checkedValue);
	})
	$('#uploadAvatarBtn').click(function(){

		if(!isUpload && $('[name=ravatarRadio]:checked').length < 1){
			alert("请上传头像或者选择默认图片！");return false;
		}
		 $("#uploadFileForm").ajaxSubmit({
			 	url:$('#initPath').val()+'/UserController.do?method=saveAvatar',
				dataType:'json',
				success:function(json){
					if(json.failure){alert(json.result);return;}
					if(isUpload){
						$('#userHeadPic').val(json.userAvatar);
						$("#J_Avatar img:eq(0)").attr("src",$('#initPath').val()+'/AttachmentController.do?method=showImg&objId='+json.userAvatar);
					}else {
						$("#J_Avatar img:eq(0)").remove();
						var _img = $('#ravatarShow').clone();
						$(_img).attr("id","");
						_img.insertBefore("#addPic");
					}
					$('#epsDialogClose').click();
				},
				error:function(msg){
					alert(JSON.stringify(msg));
				}
		 })
	});
	
	//表单提交用于上传附件
	if($('#attachId').val() != "") {
		$('#ravatarShow').attr("src",$('#initPath').val()+'/AttachmentController.do?method=showImg&objId='+$('#attachId').val());
	}
	$('#closeUploadAvatarBtn').click(function(){$('#epsDialogClose').click();});
})
</script> 


<!-- 
<form id="uploadFileForm" method="post" action="UserController.do?method=saveAvatar" enctype="multipart/form-data">
	<input id="avatarFile" name="avatarFile" type="file" onchange="document.getElementById('ravatarShow').src=getFullPath(this);"/> 
	<input type="hidden" name="defaultAvatar" id="defaultAvatar" value="" />
	<input type="hidden" name="reducedWidth" id="reducedWidth" value="160" />
	<input type="hidden" name="reducedHeight" id="reducedHeight" value="160" />
	<input type="hidden" name="attachId" id="attachId" value="${param.userHeadPic }" />
</form>

 -->

    <table>
    <tr >
    <td rowSpan="2">
    <!-- 
    <img id="ravatarShow" width="160px" height="160px"/>
     -->
      	 	<!-- 嵌入图片上传页面  
		maxWidth:宽
		maxHeight:高
		propertieName:表单中的名称（一般不直接写实体属性）
		nopicPath:提示图片路径
	 	propertieValue:表单值(用于回显图片)
	 -->
	<form id="uploadFileForm" method="post" action="UserController.do?method=saveAvatar" enctype="multipart/form-data">
	 
	<jsp:include page="/view/srplatform/upload/img_upload_load.jsp">
		<jsp:param name="maxWidth" value="90" />
		<jsp:param name="maxHeight" value="90" />
		<jsp:param name="propertieName" value="avatarFile" />
		<jsp:param name="propertieValue" value="${param.userHeadPic}" />
		<jsp:param name="nopicPath" value="AttachmentController.do?method=showImg" />
	</jsp:include>
    
    <input type="hidden" name="reducedWidth" id="reducedWidth" value="160" />
	<input type="hidden" name="reducedHeight" id="reducedHeight" value="160" />
    <input type="hidden" name="defaultAvatar" id="defaultAvatar" value="" />
    </form>
    
    </td>
    <c:set var="maleCount" value="1"/>
    <c:if test="${maleCount <= 4}">
	<c:forEach  items="${maleList}" var="male" >
	<c:set var="maleCount" value="${maleCount+1}"/>
    <td>
    	<input type="radio" name="ravatarRadio" value="${male}"/><img alt="" src="${male}" width="80px" height="80px">
    </td>
	</c:forEach>
    </c:if>
    </tr>
    <tr>
    <c:set var="femaleCount" value="1"/>
    <c:if test="${femaleCount <= 4}">
	<c:forEach  items="${femalelList}" var="female" >
	<c:set var="maleCount" value="${femaleCount+1}"/>
    <td>
    	<input type="radio" name="ravatarRadio" value="${female}"/><img alt="" src="${female}" width="80px" height="80px">
    </td>
	</c:forEach>
    </c:if>
    </tr>
    </table>

<div class="conOperation">
				<button type="button" id="uploadAvatarBtn"><span>保存</span></button>
				<button type="button" id="closeUploadAvatarBtn"><span><spring:message code="globe.close"/></span></button>
		   </div>