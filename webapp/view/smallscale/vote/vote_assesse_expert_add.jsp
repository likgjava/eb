<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form id="voteAssesseExpertForm" method="post" enctype="multipart/form-data">
	<div class="formLayout form2Pa">
		<input type="hidden" name="objId" id="voteAssesseExpert" value="${voteAssesseExpert.objId}"/>
		<input type="hidden" name="voteTemplate.objId" id="voteTemplateId" value="${voteAssesseExpert.voteTemplate.objId}"/>
		<input type="hidden" name="createUser.objId" id="createUserId" value="${voteAssesseExpert.createUser.objId}"/>
		<input type="hidden" name="createTime" id="createTime" value="${voteAssesseExpert.createTime}"/>
     	<h4><span>新增评审专家</span></h4>
     	<div class="k1">
			<div class="img_250_1" id="newPreview">
				<c:choose>
				<c:when test="${voteAssesseExpert.expertPic!= null}">
					<img width="200" height="175" src="<c:url value="AttachmentController.do?method=showImg&objId=${voteAssesseExpert.expertPic}"/>"></img>
				</c:when>
				<c:otherwise>
					<img width="200" height="175" src="<c:url value="/view/resource/skin/bizplatform/img/orginfo_add.gif"/>"></img>
				</c:otherwise>
				</c:choose>
			</div>
			<input name="pictureFile" type="file" id="pictureFile" size="22" value="" />
			<input type="hidden" name="expertPic" id="expertPic" value="${voteAssesseExpert.expertPic}"/>
		</div>
     	<ul>
	   	    <li class="fullLine">
	   	    	<label>评选组：</label>
	   	    	<select id="voteUnitGroupId" name="voteUnitGroup.objId" class="required">
	   	    		<option value=""></option>
	   	    		<c:forEach var="voteUnitGroup" items="${voteUnitGroupList}">
	   	    			<option value="${voteUnitGroup.objId }" <c:if test="${voteUnitGroup.objId == voteAssesseExpert.voteUnitGroup.objId}">selected="selected"</c:if>>${voteUnitGroup.groupName }</option>
	   	    		</c:forEach>
	   	    	</select>
	   	    	<span class="eleRequired">*</span>
	   	    </li>
	   	    <li class="fullLine">
	     		<label>评审专家：</label>
					<input type="text" name="expertName" id="expertName" class="required" maxlength="50" size="40" value="${voteAssesseExpert.expertName}"/>
					&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:selectExpertInfo();"><strong>选择专家</strong></a>
					<input type="hidden" name="expertInfo.objId" id="expertInfoId" value="${voteAssesseExpert.expertInfo.objId}"/>
    	   			<span class="eleRequired">*</span>
	   	    </li> 
	   	    <li class="fullLine">
	     		<label>比例系数(%)：</label>
					<input type="text" name="proportionFactor" id="proportionFactor" class="required floats" size="40" value="${voteAssesseExpert.proportionFactor}"/>
    	   			<span class="eleRequired">*</span>
    	    </li> 
    	    <li class="fullLine">
	     		<label>简介描述：</label>
					<textarea id="expertComment" name="expertComment" style="width: 400px;height: 110px;">${voteAssesseExpert.expertComment}</textarea>
    	    </li>
		</ul>
		
	   <div class="conOperation">
			<button type="button" id="voteAssesseExpertSave"><span><spring:message code="globe.save"/></span></button>
			<button type="button" id="historyBackBtn" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	   </div>
	</div>
</form>


<script>

//选择评审专家
selectExpertInfo = function(){
	var url = $('#initPath').val()+'/view/smallscale/vote/select_assesse_expert.jsp?Hname=expertName&Hid=expertInfoId';
	$.epsDialog({
		title:'请选评审专家',
		url:url
	});
}

// 返回
$('#historyBackBtn').click(function(){
	$('#conBody').loadPage($('#initPath').val()+"/VoteTemplateController.do?method=viewVoteTemplate&operateType=assesseExpert&objId="+$('#voteTemplateId').val());
});

//提交
$('#voteAssesseExpertSave').click(function(){
	FCKeditor_BackValue();
	if(!$('#voteAssesseExpertForm').valid()){alert('请正确填写表单!');return;}	
	//是否上传图片
	var pictureFile = $('#pictureFile').val();
	var picture = $('#expertPic').val();
	if(pictureFile == '' && picture == ''){
		alert("请上传评审专家图像！");return;
	}

	$('#voteAssesseExpertForm').ajaxSubmit({
		url:$("#initPath").val()+"/VoteTemplateController.do?method=saveVoteAssessExpert",
		dataType:'json',
		success:function(json){
			if(json.result=='success') {
				alert("操作成功！");
			}else {
				alert(ascii2native(json.result));
			}
			$("button[id=historyBackBtn]").click();//返回
		},
		error:function(msg){
			alert("操作失败！");
		}
	});
});

//预览文件
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
		$("input[id=expertPic]").val(null);
	}
})

$(document).ready(function(){    
});
</script>