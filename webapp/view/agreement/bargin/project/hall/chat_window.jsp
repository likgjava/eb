<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="userType" value="${userType}" />
<div class="tdog-popup tdog-popup-blue" style="width:500px; bottom:auto; left:400px; top:10px;">
	<div class="tdog-popup-head">
		<div class="tdog-status-free"><i></i>
			<div class="tdog-contact-info"><span class="tdog-popup-contact"></span> - 在线</div>
		</div>
		<span class="tdog-popup-tools"><span title="关闭" class="tdog-popup-close"></span></span>
	</div>
	<div class="tdog-popup-main">
		<div class="tdog-popup-talkleftouter">
			<div class="tdog-popup-talkleftinner">
				<div class="tdog-popup-talkcontainer">
					<div class="tdog-popup-talkhistory">
						<div class="tdog-popup-tms-bullet"><div class="tdog-popup-tms-bulletin"></div></div>
					</div>
					<div class="tdog-popup-talkbar"><button id="showChatLogsBut" class="showChatter">聊天记录</button></div>
					<div class="tdog-popup-talkinput">
						<textarea cols="5" id="contentArea"></textarea>
					</div>
				</div>
				<div class="tdog-popup-talkfoot"><span id="sendMSG" class="tdog-popup-sendbut tdog-popup-sendbut-off"><span class="tdog-popup-send">发送</span></span></div>
			</div>
		</div>
		<div style="background-image: block;" class="tdog-popup-talkright">
			<div class="tdog-popup-userinfo">
				<!--供应商列表(或采购人)开始-->
				<div id="supplierList" class="chatRole hidden">
					<c:choose>
						<c:when test="${userType == 'buyer'}">
							<c:set var="supplierIds" />
							<c:forEach var="supp" items="${supplierList}" varStatus="status1">
								<c:set var="supplierIds" value="${supplierIds}_${supp.supplier.objId}" />
								<p name="supplierName" id="${supp.supplier.objId}" <c:if test="${status1.index==0}">class="tdog-userinfo-username"</c:if>>${supp.supplier.orgName}</p>
							</c:forEach>
							<input type="hidden" name="supplierIds" id="supplierIds" value="${supplierIds}"/>
						</c:when>
						<c:otherwise>
							<p name="supplierName" id="${project_supplier.buyersId}" class="tdog-userinfo-username">${project_supplier.buyersName}</p>
						</c:otherwise>
					</c:choose>
				</div>
				<!--供应商列表(或采购人)结束-->
				<!--聊天记录开始-->
				<div class="chatRecord hidden" id="chatLogsList"></div>
				<!--聊天记录结束-->
			</div>
		</div>
		<div class="tdog-popup-clear"></div>
	</div>
    <div class="tdog-popup-handle"></div>
    <div class="tdog-popup-handle-x"></div>
    <div class="tdog-popup-handle-y"></div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	var projId = $("#projId").val();//项目ID
	var userType = $("#userType").val();//用户类型(buyer:采购人页面，supplier:供应商页面)

	//点击'聊天记录'或'供应商列表'
	$('#showChatLogsBut').click(function(){
		if($(this).hasClass('showChatter')){ //查看聊天记录
			$('#supplierList').hide();
			$('#chatLogsList').show();
			$(this).attr('class','showLogs');
			if('buyer' == userType){
				$(this).text('供应商列表');
			}

			//获取聊天记录信息
			$('#chatLogsList').empty();
			var currentChatterOrgId = $('#supplierList').find('p[class=tdog-userinfo-username]').attr('id');
			$.getJSON($('#initPath').val()+'/BiddingChatController.do?method=findHistoryChatContent',{"projId":projId,"orgInfoId":currentChatterOrgId},function(json){
				$(json.chatList).each(function(index, chat){
					var logHtml = '<dl class="'+(currentChatterOrgId==chat.sayOrgInfo.objId ? 'me' : 'buddy')+'">';
					logHtml += '<dt>'+chat.sayOrgInfo.orgName+' '+chat.createTime.substring(10,19)+'</dt>';
					logHtml += '<dd>'+chat.content+'</dd>';
					logHtml += '</dl>';
					$('#chatLogsList').append(logHtml);
				});
			});
		}else{
			$('#chatLogsList').hide();
			$('#supplierList').show();
			$(this).attr('class','showChatter');
			$(this).text('聊天记录');
		}
	});
});
</script>