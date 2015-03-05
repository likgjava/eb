<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form id="voteAssessedThingForm" method="post" enctype="multipart/form-data">
	<div class="formLayout form2Pa">
		<input type="hidden" name="objId" id="voteAssessedThingId" value="${voteAssessedThing.objId}"/>
		<input type="hidden" name="voteTemplate.objId" id="voteTemplateId" value="${voteAssessedThing.voteTemplate.objId}"/>
		<input type="hidden" name="isRecommended" id="isRecommended" value="${voteAssessedThing.isRecommended}"/>
		<input type="hidden" name="useStatus" id="useStatus" value="${voteAssessedThing.useStatus}"/>
     	<h4><span>新增参选对象</span></h4>
     	<div class="k1">
			<div class="img_250_1" id="newPreview">
				<c:choose>
				<c:when test="${voteAssessedThing.picture!= null}">
					<img width="200" height="175" src="<c:url value="AttachmentController.do?method=showImg&objId=${voteAssessedThing.picture}"/>"></img>
				</c:when>
				<c:otherwise>
					<img width="200" height="175" src="<c:url value="/view/resource/skin/bizplatform/img/orginfo_add.gif"/>"></img>
				</c:otherwise>
				</c:choose>
			</div>
			<input name="pictureFile" type="file" id="pictureFile" size="22" value="" />
			<input type="hidden" name="picture" id="picture" value="${voteAssessedThing.picture}"/>
		</div>
     	<ul>
	   	    <li class="fullLine">
	   	    	<label>评选组：</label>
	   	    	<select id="voteUnitGroupId" name="voteUnitGroup.objId" class="required">
	   	    		<option vlaue=""></option>
	   	    		<c:forEach var="voteUnitGroup" items="${voteUnitGroupList}">
	   	    			<option value="${voteUnitGroup.objId }" <c:if test="${voteUnitGroup.objId == voteAssessedThing.voteUnitGroup.objId}">selected="selected"</c:if>>${voteUnitGroup.groupName }</option>
	   	    		</c:forEach>
	   	    	</select>
	   	    	<span class="eleRequired">*</span>
	   	    </li>
	   	    <li class="fullLine">
	     		<label>参选对象：</label>
					<input type="text" name="attenderName" id="attenderName" class="required" maxlength="50" size="40" value="${voteAssessedThing.attenderName}"/>
					&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:selectOrgInfo();"><strong>选择已注册参选对象</strong></a>
					<input type="hidden" name="attender" id="attender" value="${voteAssessedThing.attender}"/>
    	   			<span class="eleRequired">*</span>
	   	    </li>
	   	    <li class="fullLine">
	     		<label>描述：</label>
				<textarea id="thingComment" name="thingComment" style="width: 390px;height: 140px;">${voteAssessedThing.thingComment}</textarea>
	   	    </li>
		</ul>
	   <div class="conOperation">
			<button type="button" id="voteAssessedThingSave"><span><spring:message code="globe.save"/></span></button>
			<button type="button" id="historyBackBtn" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	   </div>
	</div>
</form>


<script>

//选择参选对象
selectOrgInfo = function(){
	var voteUnitGroupId = $('#voteUnitGroupId').val();
	//评选组类型(00：企业；01：品牌；02：服务；03：商品),默认为企业
	var groupType = "00";
	if(voteUnitGroupId == null || voteUnitGroupId == ''){
		alert("请先选择评选组！");return;
	}
	
	$.getJSON($('#initPath').val()+'/VoteTemplateController.do?method=toCreateOrUpdateVoteUnitGroup',{'objId':voteUnitGroupId,"operateType":"getGroup"},function(json){
		if(json.failure) {
			alert(json.result);
			return;
		}else{
			groupType = json.voteUnitGroup.groupType;

			var url = "";
			if(groupType == '00'){//企业
				url = $('#initPath').val()+'/view/smallscale/vote/select_assess_orginfo.jsp?Hname=attenderName&Hid=attender';
			}
			if(groupType == '01'){//品牌
				url = $('#initPath').val()+'/view/smallscale/vote/select_goods_brands.jsp?Hname=attenderName&Hid=attender';
			}
			
			$.epsDialog({
				title:'请选择参选对象',
				url:url
			});
		}
	});
}

// 返回
$('#historyBackBtn').click(function(){
	$('#conBody').loadPage($('#initPath').val()+"/VoteTemplateController.do?method=viewVoteTemplate&operateType=assessedThingMaintenace&objId="+$('#voteTemplateId').val());
});

//提交
$('#voteAssessedThingSave').click(function(){
	FCKeditor_BackValue();
	if(!$('#voteAssessedThingForm').valid()){alert('请正确填写表单!');return;}	
	//是否上传图片
	var pictureFile = $('#pictureFile').val();
	var picture = $('#picture').val();
	if(pictureFile == '' && picture == ''){
		alert("请上传参选对象图片！");return;
	}

	$('#voteAssessedThingForm').ajaxSubmit({
		url:$("#initPath").val()+"/VoteAssessedThingController.do?method=saveVoteAssessedThing",
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
		$("input[id=picture]").val(null);
	}
})

</script>