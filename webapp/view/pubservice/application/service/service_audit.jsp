<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="SubscribeAuditForm" method="post" modelAttribute="subscribe">
		<input type="hidden" id="objId" name="objId" value="${subscribe.objId}"/>
			
	<div class="formLayout form2Pa">
   		<ul>	
   			<li class="fullLine"><label>机构名称：</label> <span>${subscribe.orgInfo.orgName}</span></li>
   			<li class="fullLine"><label>订阅服务名称：</label> <span>${subscribe.serviceBase.serviceName}</span></li>
   			<li class="fullLine"><label>服务描述：</label> <p>${subscribe.serviceBase.serviceDesc}</p></li>
   			<li class="fullLine"><label>缴费金额：</label> <span>${subscribe.payAmount}</span></li>
   			<li class="fullLine"><label>服务外部链接：</label> <span><a href="${subscribe.serviceBase.serviceLink}" target="_blank">${subscribe.serviceBase.serviceLink}</a></span></li>
   			<li class="fullLine"><label>订阅时间：</label> <span>${fn:substring(subscribe.startTime,0,19)} 至 ${fn:substring(subscribe.endTime,0,19)}</span></li>
   		</ul>
	</div>
	
	<c:if test="${param.type=='audit'}">
		<div class="formLayout form2Pa">
			<ul>
				<li class="formTextarea"><label>审核意见：</label><textarea id="opinion" name="opinion" maxLength="100"></textarea>
				</li>
			</ul>
		</div>
	</c:if>	
	
	</form:form>
	
	<div class="conOperation">
		<c:choose>
			<c:when test="${param.type=='audit'}">
				<button class="operationBtnDiv" type="button" id="agreeBtn"><span>审核通过</span></button>
				<button class="operationBtnDiv" type="button" id="refuseBtn"><span>审核不通过</span></button>
				<button type="button" name="historyBackBtn" ><span>返回</span></button>
			</c:when>
			<c:otherwise>
				<button type="button" class="largeBtn" id="close"><span>关闭</span></button>
			</c:otherwise>
		</c:choose>
	</div>

<script>
	var SubscribeAuditForm={};
	//审核
	SubscribeAuditForm.auditOrgInfo=function(auditStatus){
	  var url = $('#initPath').val()+'/ServiceSubscribeController.do?method=auditSubscribe&objId='+$('#objId').val()+'&auditStatus='+auditStatus;
	  $.getJSON(url,{},function(json){
	      if(json.failure){return;}
	      alert('审核成功!');
	      $('button[name=historyBackBtn]').click();
	  });
	}

$(document).ready(function(){
	$('#agreeBtn').click(function(){//通过
		 if(window.confirm("确认通过吗")){
				SubscribeAuditForm.auditOrgInfo('02');
		 }
	});
	$('#refuseBtn').click(function(){//不通过
		 if(window.confirm("确认不通过吗")){
				SubscribeAuditForm.auditOrgInfo('03');
		 }
	});
	//关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})
})
</script>