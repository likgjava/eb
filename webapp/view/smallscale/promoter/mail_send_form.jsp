<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout form2Pa">         
	
    <form id="messageForm" method="post">
 	<input type="hidden" name="reducedWidth" id="reducedWidth" value="120" />
	<input type="hidden" name="reducedHeight" id="reducedHeight" value="120" />

	
	<h4><span>发送邮件</span></h4>
	<ul>
		<li>
			<label><strong>提示：</strong></label>
			<span>多个邮箱请用逗号分割</span>
		</li>		
		<li class="formTextarea">
			<label for="content">收信人邮箱：</label>
			 <textarea name="mailadds"  id="mailadds"  class="required"></textarea>
              <span class="eleRequired">*</span>		
		</li>
	<!-- 
			<li class="formTextarea">
				<label for="receiverName">收信人：</label>
				
				<input type="hidden" id="receiver_objId" name="receiver"/>
				<textarea id="receiver_name" name="receiverName" readonly="readonly"  ></textarea>				
				<span class="conOperation">
				<button id="sendMorePerson" type="button"  class="largeBtn"><span>选择用户发送</span></button>
				</span>
			</li>
	 -->		
		<li class="fullLine">
			<label for="title">标题：</label>
			<input id="title" name="title" class="required" maxlength="50"/>
			<span class='eleRequired'>*</span>
		</li>
		
		<li class="formTextarea">
			<label for="content">内容：</label>
			 <textarea name="content"  id="content"  class="required"><spring:message code="smallscale.promoter.invitation.content"/></textarea>
              <span class="eleRequired">*</span>		
		</li>
				
	</ul>
	<div class="conOperation">
		<button id="submitBtn" type="button"  class="largeBtn"><span>发送</span></button>
	</div>
	</form>
</div>

<script>
var messageAdd = {};

// 发送
messageAdd.saveMessage = function(messageJson)
{	
	$('#messageForm').ajaxSubmit({
		//url:$("#initPath").val()+"/PromoterController.do?method=sendMail&objIds="+$("#receiver_objId").val(),
		url:$("#initPath").val()+"/PromoterController.do?method=sendMail",
		dataType:'json',
		success:function(json){
			if(json.success)
			{
				alert("发送成功！");
				$('.epsDialogClose').trigger('click');
			}
		},
		error:function(msg){
			alert("发送失败！");
		}
	});	
}

$(document).ready(function(){
    //发送
    $('#submitBtn').click(function(){
		if(!$('#messageForm').valid()){alert('请正确填写表单!');return;}
		if(confirm('确定发送？'))
		{
			var messageJson = formToJsonObject("messageForm");
			messageAdd.saveMessage(messageJson);
		}
    });  
})
</script>

