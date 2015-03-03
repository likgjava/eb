<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!-- 导航显示 -->
<div class="nextBar">
    <ol class="num4">
        <li class="done"><span class="first">1. 填写基本信息</span></li> 
        <li class="done current-prev"><span>2. 填写财务信息</span></li>
        <li class="current"><span>3. 填写法务信息</span></li>
        <li class="last"><span>4. 填写企业资质</span></li>
    </ol>
</div>
<div class="formTips attention">
	<ul>
		<li>
			<em>注意：</em>如果您的企业基本信息还未提交，请将企业的信息完善后再进行提交操作，提交操作在"基本信息"页面！
		</li>
	</ul>
</div>
<!-- end 导航显示 -->

<input type="hidden" name="orgId" id="orgId" value="${orgId }"/>
<input type="hidden" id="returnUrlType" name="qualificationForm" value="${param.urlType}" />
<form id="exInfoForm" name="exInfoForm" method="post" enctype="multipart/form-data">
	<div class="formLayout form1Pa">
	<input type="hidden" id="returnUrl" name="qualificationForm" value="${param.returnUrl}" />
		<ul>
			<c:forEach var ="qualificationParam" items="${qualificationParamList}" varStatus="status">
				<c:set var="isUpdate" value="0"/>
				<c:forEach var="qualificationDetail" items="${qualificationDetailList}">
					<c:if test="${qualificationDetail.qualityParam.objId==qualificationParam.objId}">
						<c:set var="isUpdate" value="1"/>
						<li>
							<label>${qualificationParam.qualityName}：</label>
							<input type="hidden" name="objId" value="${qualificationDetail.orgQuality.objId}">
							<input name="value" maxlength="50" id="orgFile${status.index}" value="${qualificationDetail.paramValue}" <c:if test="${qualificationParam.isRequired==true}">class="required"</c:if>>
							<input type="hidden" name="paramId" value="${qualificationParam.objId}">
							<input type="hidden" name="classId" value="${qualificationParam.parent.parent.objId }">
							<input type="hidden" name="defineId" value="${qualificationParam.parent.objId }">
							<input name="orgFile${status.index}" type="file" >
							<c:if test="${qualificationDetail.orgQuality.qualificationFile!=null}">
								<a href="javascript:void(0);" name="qualificationFile" id="${qualificationDetail.orgQuality.qualificationFile }">文件下载</a>
							</c:if>
							<c:if test="${qualificationParam.isRequired==true}"><span class='eleRequired'>*</span></c:if>
						</li>	
					</c:if>
				</c:forEach>
				<c:if test="${isUpdate=='0'}">
					<li>
						<label>${qualificationParam.qualityName}：</label>
						<input type="hidden" name="objId" value="">
						<input name="value" maxlength="50" id="orgFile${status.index}" value="" <c:if test="${qualificationParam.isRequired==true}">class="required"</c:if>>
						<input type="hidden" name="paramId" value="${qualificationParam.objId}">
						<input type="hidden" name="classId" value="${qualificationParam.parent.parent.objId }">
						<input type="hidden" name="defineId" value="${qualificationParam.parent.objId }">
						<input name="orgFile${status.index}" type="file">
						<c:if test="${qualificationParam.isRequired==true}"><span class='eleRequired'>*</span></c:if>
					</li>		
				</c:if>
			</c:forEach>
		</ul>
	</div>
	<div class="conOperation">
		<button type="button" name="saveExInfo" id="saveQualificationDetailBtn"><span>保存</span></button>
	</div>
</form>


<script>
var legalInfoForm = {};
//保存或者提交
legalInfoForm.saveOrUpdateQuality = function(orgQualitysJson){

	orgQualitysJson = native2ascii(orgQualitysJson);
	
	//提交
	$('#exInfoForm').ajaxSubmit({
		url:$("#initPath").val()+"/ExOrgInfoController.do?method=saveExOrgQuality&orgId="+$("input[name=orgId]").val()+"&orgQualitysJson="+orgQualitysJson,
		dataType:'json',
		success:function(json){
			if(json.result=='success') {
				alert("保存成功！");
				if($('input[id=returnUrlType][name=qualificationForm]').val() !="" && $('input[id=returnUrlType][name=qualificationForm]').val() !="null"){
					$("#conBody").loadPage($("#initPath").val()+"/ExOrgInfoController.do?method=toOrgLegalInfo&urlType=self&orgId="+$("input[name=orgId]").val()+"&qualityClassId="+$("#exInfoForm").find("li:first").find("input[name=classId]").val());
				}else{
					$('#conBody').loadPage($('#returnUrl').val());
				}
			}else {
				alert(ascii2native(json.result));
			}
			$('#saveQualificationDetailBtn').attr('disabled',false);
		},
		error:function(msg){
		}
	});	
	
}
$(document).ready(function(){

	//验证
	$('#exInfoForm').validate();
	
	//保存
	$("button[name=saveExInfo]").click(function(){
		if(!$('#exInfoForm').valid()){alert('请正确填写表单!');return;}
		$('#saveQualificationDetailBtn').attr('disabled',true);
		var orgQualitysJson = "";
		$.each($("#exInfoForm").find("li") ,function(index,obj){
			var objId = "-1";
			if($(obj).find("input[name=value]").val()||$(obj).find("input[name=objId]").val()){
				if($(obj).find("input[name=objId]").val()!=""){
					objId = $(obj).find("input[name=objId]").val();
				}
				if(orgQualitysJson==""){
					orgQualitysJson += $(obj).find("input[name=value]").val()
					+":"+$(obj).find("input[name=classId]").val()
					+":"+$(obj).find("input[name=defineId]").val()
					+":"+$(obj).find("input[name=value]").attr("id")+":"+objId
					+":"+$(obj).find("input[name=paramId]").val();
				}else{
					orgQualitysJson += ","+$(obj).find("input[name=value]").val()
					+":"+$(obj).find("input[name=classId]").val()
					+":"+$(obj).find("input[name=defineId]").val()
					+":"+$(obj).find("input[name=value]").attr("id")+":"+objId
					+":"+$(obj).find("input[name=paramId]").val();
				}
			}
		})
		legalInfoForm.saveOrUpdateQuality(orgQualitysJson);
	})
	
	//添加附件事件
	$.each($("body").find("a[name=qualificationFile]"),function(index,obj){
		$(obj).click(function(){
			var isSingle ="no";	
		    var fileType ="all";
			var attachRelaID=obj.id;
			var maxSize="1024";	
			$.epsDialog({
				id:"uploadFile",
				title:"上传文件",
				url:$('#initPath').val()+"/AttachmentController.do?method=toUpload&&divId=uploadFile&attachRelaID="+attachRelaID+"&isSingle="+isSingle+"&fileType="+fileType
			})
		})
	})

});
</script>
