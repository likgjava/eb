<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formZone">         
		<input type="hidden" name="" id="fromnoteId" value="${viewNote.objId}"/>
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
     			<c:if test="${viewNote.isReply}">
	     			<li>
	     				<label>回复人：</label>${viewNote.receiverName}
	     			</li>
	     			<li>
	     				<label>回复时间：</label>${viewNote.replyTime}
	     			</li>
	     			<li class="formTextarea">
	     				<label>回复内容：</label>${viewNote.replyContent}
	     			</li>
     			</c:if>
     			<c:if test="${!viewNote.isReply}">
     				<li class="formTextarea">
	     				<label>提示：</label><span style="Color:red">还没与对此<c:if test="${viewNote.type=='00'}">留言</c:if><c:if test="${viewNote.type=='01'}">咨询</c:if>进行回复。</span>
	     			</li>
     			</c:if>    							
     		</ul>
		</div>
	    <div class="conOperation">
			<input type="hidden" name="" id="currentTabsId" value="${param.currentTabsId}"/>
	        <button id="viewNoteDialogCloseBut" type="button" ><span>关闭</span></button>
	    </div>
</div>
<script>
$(document).ready(function(){
	var currentTabIdStr = $('#currentTabsId').val();	
	
	if(currentTabIdStr == "tabs_send"){//当前Tab页为发表页时,可能执行阅读状态的更改
		if(${viewNote.isRead == 'false'} && ${viewNote.isReply == 'true'}){
			$.getJSON($('#initPath').val()+'/NoteController.do?method=viewSave',{objId:$('#fromnoteId').val()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
			});
		}
	}	
	if(currentTabIdStr == "tabs_receive"){//当前Tab页为接收页时,可能执行阅读状态的更改
		if(${viewNote.isRead == 'false'} && ${viewNote.isReply == 'false'}){
			$.getJSON($('#initPath').val()+'/NoteController.do?method=viewSave',{objId:$('#fromnoteId').val()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
			});
		}		
	}
	
	//关闭
	$('#viewNoteDialogCloseBut').click(function(){
		$('.epsDialogClose').trigger('click');
	});
});

</script>