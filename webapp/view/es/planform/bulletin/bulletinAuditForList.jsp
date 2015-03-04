<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>


<div class="formLayout formPa">
	<h5><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out>内容</h5>
	<span style="height:auto;">${bulletin.bullContents}</span>
</div>
<!-- 
<div class="formLayout" id="signUpCondFactorView"></div>
 -->
<div class="formLayout formPa" style="height: 73px;"> 
  			<h5>审核意见</h5>
      		<form id="BulletinForm" method="post">
				<input type="hidden" name="objId" id="objId" value="<c:out value="${bulletin.objId}"/>"/>
				<input type="hidden" name="auditStatus" id="auditStatus" value="<c:out value="${bulletin.auditStatus}"/>"/>
				<input type="hidden" name="bullType" id="bullType" value="<c:out value="${bulletin.bullType}"/>"/>
				<ul>
					<li>
						<textarea name="opinion" id="opinion" maxlength="200" class="maxInput" style="width: 99%;height: 45px;">同意</textarea>
						<span class="eleRequired"></span>
					</li>
				</ul>
			</form>
</div>
<div class="conOperation">
<button id="passButton" type="button" tabindex="18"><span>通过</span></button>
<button id="noPassButton" type="button" tabindex="18"><span>不通过</span></button>
<button id="returnBtn" type="button" tabindex="18"><span>返回</span></button>
<!-- <button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button> -->
</div>
 <div id="historyView"></div>
<script>
$(document).ready(function(){
	$("#signUpCondFactorView").hide();
	var bullType = $("bullType").val();
	if(bullType=='01' || bullType=='10'){//如果为招标公告或询价公告，才显示报名指标
	$("#signUpCondFactorView").loadPage($('#initPath').val()+'/SignUpCondFactorController.do?method=getSignUpCondFactorListByProjectId&type=detail&projectId='+$("input[name=projectId]").val());
	$("#signUpCondFactorView").show();
	}
	$("#BulletinForm").validate();
	$("#noPassButton").click(function(){
		if(!$('#BulletinForm').valid()){alert('请正确填写表单!','提示',{icon:'info'});return false;}
		//评审未通过且未填写意见
		if($("#opinion").val()=='')
		{
			alert("请填写审核意见");
			return false;
		}
		else if(window.confirm("确认提交?"))
		{
			$.getJSON($('#initPath').val()+'/PurBulletinController.do?method=purbulletinAudit&passStatus=02',formToJsonObject('BulletinForm'),function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#returnBtn').click();			
	    	});
		}
	});
	$('#passButton').click(function(){
		if(!$('#BulletinForm').valid()){alert('请正确填写表单!','提示',{icon:'info'});return false;}
		if(window.confirm("确认提交?")){
			$.getJSON($('#initPath').val()+'/PurBulletinController.do?method=purbulletinAudit&passStatus=01',formToJsonObject('BulletinForm'),function(json){
				$('#returnBtn').click();		
	    	});		
	    	}
	});

	//返回
	$('#returnBtn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/bulletin/viewBulletinList.jsp');					

	});
	
});


//操作历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=01');
});
</script>