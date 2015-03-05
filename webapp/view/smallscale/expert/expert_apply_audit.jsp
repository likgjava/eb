<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="expertChangeForm" method="post" modelAttribute="org"> 
	<input type="hidden" name="objId" id="objId" value="${applyExpert.objId}"/>
	<input type="hidden" name="expertId" id="expertId" value="${applyExpert.expertInfo.objId}"/>
	<input type="hidden" name="applyType" id="applyType" value="${applyExpert.applyType}"/>
	
	<div class="formLayout form2Pa">
		<ul>
			<li class="fullLine">
				<label>专家姓名：</label>
				<span>${applyExpert.expertInfo.name}</span>
			</li>
			<li class="fullLine">
				<label>申请状态：</label>
				<span>
					<c:if test="${applyExpert.applyType=='consultant'}">申请成为咨询员</c:if>
					<c:if test="${applyExpert.applyType=='reviewers'}">申请成为评审员</c:if>
				</span>
			</li>
		</ul>
	</div>
	
	<div class="formLayout form2Pa">
		<ul>
			<li class="formTextarea"><label>审核意见：</label> 
				<textarea name="opinion" maxlength="250" class="required"></textarea>
				<span class="eleRequired">*</span> 
			</li>
		</ul>
	</div>
	<div class="conOperation">
		<button type="button" id="agree"><span>通过</span></button>
		<button type="button" id="notAgree"><span>不通过</span></button>
		<button type="button" id="return"><span>关闭</span></button>
	</div>
</form:form>		

<script>
/**
 * 专家申请审核页面
 * create by likg
 */
var ExpertChangeForm={};

//提交审核
ExpertChangeForm.audit=function(auditStatus){
	if(!$('#expertChangeForm').valid()){alert('请正确填写表单!');return;}
	
	var msg = (auditStatus=='02' ? "通过" : "不通过");
	if(window.confirm('确定审核'+msg+"吗?")) {
	  var jsonObj = formToJsonObject('expertChangeForm');
	  jsonObj.auditStatus = auditStatus;
	  $.getJSON($('#initPath').val()+'/ExpertInfoApplyController.do?method=auditApplyExpert',jsonObj,function(json){
	      if(json.result)alert(json.result);
	      if(json.failure){return;}
	      $('#return').click();
	  });	
	}
}

$(document).ready(function(){
	$("#expertChangeForm").validate();
	
	//通过
	$('#agree').click(function(){
		ExpertChangeForm.audit('02');
	})
	//不通过
	$('#notAgree').click(function(){
		ExpertChangeForm.audit('03');
	})
	
	//关闭
	$('#return').click(function(){
		$('.epsDialogClose').trigger('click');
	})
})
</script>