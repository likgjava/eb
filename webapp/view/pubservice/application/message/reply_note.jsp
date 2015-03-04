<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formZone">         
	<form id="noteReplyForm" method="post">
    	<div class="formLayout form2Pa">
     		<ul>
     			<li>
     				<label><c:if test="${viewNote.type=='00'}">留言人</c:if><c:if test="${viewNote.type=='01'}">咨询人</c:if>：</label>${viewNote.leaverName}
     			</li>
     			<li>
     				<label><c:if test="${viewNote.type=='00'}">留言时间</c:if><c:if test="${viewNote.type=='01'}">咨询时间</c:if>：</label>${viewNote.createTime}
     			</li>
     			<c:if test="${viewNote.attFile != null}">
	     			<li class="fullLine">
	     				<label>表情附件：</label><img alt="找不到图片表情" src="${faceFolder}/${viewNote.attFile}">
	     			</li>
     			</c:if>
     			<li class="formTextarea">
     				<label><c:if test="${viewNote.type=='00'}">留言内容</c:if><c:if test="${viewNote.type=='01'}">咨询内容</c:if>：</label>${viewNote.content}
     			</li>
     			<li class="formTextarea">
     				<label for="replyContent">回复内容：</label>
     					<textarea name="replyContent" id="replyContent" rows="30" cols="400" class="required" maxlength="100"></textarea>
     				<span class="eleRequired">*最大100个字</span>
					<input type="hidden" name="objId" id="objId" value="${viewNote.objId}"/>
					<input type="hidden" name="goodsId" id="goodsId" value="${viewNote.goodsId}"/>
					<input type="hidden" name="content" id="content" value="${viewNote.content}"/>
					<input type="hidden" name="attFile" id="attFile" value="${viewNote.attFile}"/>
					<input type="hidden" name="receiver" id="receiver" value="${viewNote.receiver}"/>
					<input type="hidden" name="receiverName" id="receiverName" value="${viewNote.receiverName}"/>
					<input type="hidden" name="type" id="type" value="${viewNote.type}"/>
					<input type="hidden" name="leaver" id="leaver" value="${viewNote.leaver}"/>
					<input type="hidden" name="leaverName" id="leaverName" value="${viewNote.leaverName}"/>
					<input type="hidden" name="isAnnoy" id="isAnnoy" value="${viewNote.isAnnoy}"/>
					<input type="hidden" name="createTime" id="createTime" value="${viewNote.createTime}"/>
					<input type="hidden" name="isRead" id="isRead" value="false"/>
					<input type="hidden" name="isReply" id="isReply" value="true" />
     			</li>     			
     		</ul>
		</div>
	    <div class="conOperation">
	        <button id="replySave" type="button" tabindex="18"><span>回复</span></button>
	    </div>
	</form>
</div>
<script>
$(document).ready(function(){
	//当阅读状态 isRead = false, 且回复状态isReply = false时,执行阅读状态更改
	if(${viewNote.isRead == 'false'} && ${viewNote.isReply == 'false'}){
		$.getJSON($('#initPath').val()+'/NoteController.do?method=viewSave',{objId:$('#objId').val()},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
		});
	}
});
//回复
$('#replySave').click(function(){
	if(!$('#noteReplyForm').valid()){alert('请正确填写表单!');return;}
	
	//ajax的URL	
	var url = $('#initPath').val()+'/NoteController.do?method=noteSave&saveType=replySave';

	//typeParam参数为'dealConsult’ 提交成功后跳转到咨询处理页面
	if($('#type').val()=='01'){typeParam = "dealConsult";}
	else{typeParam = null}
	
	$('#noteReplyForm').ajaxSubmit({
		url:url,
		dataType:'json',
		success:function(json){
			alert(json.result);
			//$('#conBody').loadPage($('#initPath').val()+'/NoteController.do?method=toNoteList&typeParam='+typeParam);
		},
		error:function(msg){
			alert(JSON.stringify(msg));
		}
	});
	//自动关闭
	$('.epsDialogClose').trigger('click');	
});	
</script>