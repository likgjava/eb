<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout form2Pa">
	<form id="votePointerForm" method="post">
		<input type="hidden" name="objId" id="votePointerId" value="${votePointer.objId}"/>
		<input type="hidden" name="voteTemplate.objId" id="voteTemplateObjId" value="${votePointer.voteTemplate.objId}"/>
     	<h4><span>评选主题指标</span></h4>
     	<ul>
     		<li class="fullLine">
	     		<label>指标名称：</label>
					<input type="text" name="pointerName" id="pointerName" class="required" maxlength="40" size="40" value="${votePointer.pointerName}"/>
    	   			<span class="eleRequired">*</span>
    	    </li><li class="fullLine">
	     		<label>指标编号：</label>
					<input type="text" name="pointerCode" id="pointerCode" class="required" maxlength="40" size="40" value="${votePointer.pointerCode}"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li class="fullLine">
	     		<label>比例系数(%)：</label>
					<input type="text" name="pointerFactor" id="pointerFactor" class="required floats" size="40" value="${votePointer.pointerFactor}"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="votePointerSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="historyBackBtn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
		   </div>
	</form>
</div>

<script>
var votePointerAdd={};

// 返回
$('#historyBackBtn').click(function(){
	//自动关闭		
	$('.epsDialogClose').trigger('click');
});

// 提交
$('#votePointerSave').click(function(){
	if(!$('#votePointerForm').valid()){alert('请正确填写表单!');return;}
	
	$('#votePointerForm').ajaxSubmit({
		url:$("#initPath").val()+"/VotePointerController.do?method=savePointer",
		dataType:'json',
		success:function(json){
			if(json.result=='success') {
				alert("操作成功！");
			}else {
				alert(ascii2native(json.result));
			}
			$('.epsDialogClose').trigger('click');
		},
		error:function(msg){
			alert("操作失败！");
		}
	});
});

$(document).ready(function(){
});
</script>