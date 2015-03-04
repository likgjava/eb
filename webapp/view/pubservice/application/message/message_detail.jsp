<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="_dialogID_messageDetail"  value="<c:out value="${param.dialogId}"/>"/>
<div class="formLayout form2Pa">         
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
 		<tr>
			<td>
				标&nbsp;&nbsp;&nbsp;题：<strong>${message.title }</strong>&nbsp;&nbsp;<span>(<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${message.sendTime }"/>)</span><br/>
				发件人：${message.senderName }
			</td>             
		</tr>
		<tr>
			<td>
				<div style="width: 100%px;height: 100%px;background: #e4fdff;text-align: left;">${message.content}</div>
			</td>
		</tr>
	</table>
	
	<div class="conOperation">
		<button id="close" type="button"  class="largeBtn" ><span><spring:message code="globe.close"/></span></button>
	</div>
</div>

<script>
/*
 * 查看站内信内容页面
 * created by likg
 */

$(document).ready(function(){
	//关闭弹出层
	$("#close").click(function(){
		if($("#_dialogID_messageDetail").val() != ""){
			$(document.getElementById($("#_dialogID_messageDetail").val())).find('.epsDialogClose').trigger('click');
		}else{
			$('.epsDialogClose').trigger('click');
		}
	});
});
</script>
