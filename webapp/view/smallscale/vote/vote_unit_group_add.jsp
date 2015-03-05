<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout form2Pa">
	<form id="voteUnitGroupForm" method="post">
		<input type="hidden" name="objId" id="voteUnitGroupId" value="${voteUnitGroup.objId}"/>
		<input type="hidden" name="voteTemplate.objId" id="voteTemplateObjId" value="${voteUnitGroup.voteTemplate.objId}"/>
		<input type="hidden" name="createUser.objId" id="voteUnitGroupCreator" value="${voteUnitGroup.createUser.objId}"/>
		<input type="hidden" name="createTime" id="createTime" value="${voteUnitGroup.createTime}"/>
		<input type="hidden" name="" id="groupTypeValue" value="${voteUnitGroup.groupType}"/>
     	<h4><span>评选组：</span></h4>
     	<ul>
     		<li class="fullLine">
	     		<label>评选组类型：</label>
	     		<select name="groupType" id="groupType">
					<option value='00'>企业</option>
					<option value='01'>品牌</option>
					<option value='02'>服务</option>
					<option value='03'>商品</option>
				</select>
    	   		<span class="eleRequired">*</span>
    	    </li>
     		<li class="fullLine">
	     		<label>评选组名称：</label>
					<input type="text" name="groupName" id="groupName" class="required" maxlength="40" size="40" value="${voteUnitGroup.groupName}"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
    	    <li class="formTextarea">
				<label>描述：</label>
				<textarea id="memo" name="memo" maxLength="100">${voteUnitGroup.memo}</textarea>
			</li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="voteUnitGroupSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="historyBackBtn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
		   </div>
	</form>
</div>

<script>
// 返回
$('#historyBackBtn').click(function(){
	//自动关闭		
	$('.epsDialogClose').trigger('click');
});

// 提交
$('#voteUnitGroupSave').click(function(){
	if(!$('#voteUnitGroupForm').valid()){alert('请正确填写表单!');return;}
	
	$('#voteUnitGroupForm').ajaxSubmit({
		url:$("#initPath").val()+"/VoteTemplateController.do?method=saveVoteUnitGroup",
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
	var groupTypeValue = $('#groupTypeValue').val();
	if(groupTypeValue != null && groupTypeValue != ''){
		var groupType = $("#groupType");
		var options = groupType.children();
		for(var i=0;i<options.length;i++){
			if(options[i].value == groupTypeValue){
				options[i].selected = "selected";
			}
		}
	}
});
</script>