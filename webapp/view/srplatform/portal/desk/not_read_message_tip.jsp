<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.tdog-sysinfo {
    background-color: #FFFFFF;
    overflow-x: hidden;
    overflow-y: auto;
    padding: 0 2px;
    position: relative;
    color:#3E3E3E;
}
.tdog-sysinfo dt span {
    display: inline-block;
}
.tdog-sysinfo dt p {
    line-height: 45px;
}
.tdog-sysinfo dt span.tdog-info-mailicon {
    background: url("cms/fileico/close.png") no-repeat;
    float: left;
    height: 15px;
    margin-right: 5px;
    width: 20px;
    margin-top: 15px;
}
.tdog-sysinfo dt span.tdog-info-mailicon-open {
    background: url("cms/fileico/open.png") no-repeat;
    float: left;
    height: 15px;
    margin-right: 5px;
    width: 20px;
    margin-top: 15px;
}
.tdog-sysinfo span.tdog-info-title {
    float: left;
    height: 45px;
    margin-right: 10px;
    overflow: hidden;
    white-space: pre;
    width: 220px;
}
.tdog-sysinfo dd {
    border-bottom: 1px solid #EAE9E1;
    display: none;
    margin: 0;
    padding: 5px 0 5px 25px;
}
</style>
<input type="hidden" id="_dialogID"  value="<c:out value="${param.dialogId}"/>"/>
<input type="hidden" id="notTipMessageCount" value="${notTipMessageNum}">
<div>
	<div class="tdog-sysinfo">
		<dl>
			<c:forEach var="message" items="${notTipMessage}">
			<dt>
				<p>
					<span class="tdog-info-mailicon"></span>
					<span class="tdog-info-title" onclick="NotReadMessageTip.openOrCloseMsg('dd_${message.objId}',this)" readMark="0" id="${message.objId}" title="${message.title}"><c:choose><c:when test="${fn:length(message.title) > 17}">${fn:substring(message.title,0,16)}…</c:when><c:otherwise>${message.title}</c:otherwise></c:choose></span>
					<span class="tdog-info-time"><fmt:formatDate value="${message.sendTime }" pattern="yyyy-MM-dd"/></span>
				</p>
			</dt>
			<dd id="dd_${message.objId}">
				${message.content }
				<br /> 
				${message.senderName }
			</dd>
	</c:forEach>
		</dl>
	</div>
	<div class="conOperation">
		<button type="button" onclick="NotReadMessageTip.showStationMessage()"><span>查看全部</span></button>
	</div>
</div>

<script>
/**
 * 站内信提示页面
 * create by likg
 */
var NotReadMessageTip = {};

//显示或隐藏站内信的内容
NotReadMessageTip.openOrCloseMsg=function(id, obj){
	if($("#"+id).css("display") == "none"){
		$("#"+id).css("display","block");
		if($(obj).attr("readMark") == "0"){
			//修改页面顶部提示未读站内信的数量
			myDesktop.changeNotReadMessageNum(parseInt($("#notReadMessageNum").attr('num'))-1);
			$(obj).attr("readMark", "1");
			$(obj).prev().attr('class', 'tdog-info-mailicon-open');

			//同步到数据库
			$.getJSON($('#initPath').val()+'/MessageController.do?method=updateMessage',{'objId':$(obj).attr('id')},function(){});
		}
	}else {
		$("#"+id).css("display","none");
	}
}

//跳转到站内信列表页面
NotReadMessageTip.showStationMessage=function(){
	$('#conBody').loadPage($('#initPath').val()+"/MessageController.do?method=toMessageListView");
	//关闭提示窗口
	$("#messageTip").find('.epsDialogClose').trigger('click');
}

$(document).ready(function(){
	if($("#notTipMessageCount").val() == 0){
		//关闭弹出层
		if($("#_dialogID").val() != ""){
			$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
		}else{
			$('.epsDialogClose').trigger('click');
		}
	}
});
</script>