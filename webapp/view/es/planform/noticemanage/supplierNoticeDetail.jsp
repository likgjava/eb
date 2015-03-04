<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script>
	$(document).ready(function(){
		$("#sure").click(function(){
			if(confirm("确定确认此通知书吗?")){
				$.getJSON($('#initPath').val()+'/NoticeController.do?method=sureNotice',{noticeId:$('#noticeId').val(), includedProperties:''},function(json){
		    		if(json.result){
			    		if(json.result=='success'){
			    			alert("确认成功!");
					    }	
			    	}
			    	if(json.failure)return;
		    	});	
	    	}
		})
	})
</script>

<div class="formLayout form2Pa">  
	<c:if test="${empty notice}">
		<div align="center" style="height:75px;margin-top:70px">暂无<dm:out value="local__OpenTendering__notice_zh">通知书</dm:out>!</div>
	</c:if>
	<c:if test="${not empty notice}">
	<form id="noticeDetailForm" method="post">
		<h4><span><dm:out value="local__OpenTendering__notice_zh">通知书</dm:out></span></h4>
		<ul>
			<li>
				<label for="noticeTitle"><spring:message code="noticeForm.noticeTitle"/></label>
				<span id="noticeTitle">${notice.noticeTitle}</span>
			</li>
			<li>
				<label for="noticeType"><spring:message code="noticeForm.noticeType"/></label>
				<span id="noticeType">
				<c:if test="${notice.noticeType=='00'}">成交<dm:out value="local__OpenTendering__notice_zh">通知书</dm:out></c:if>
				<c:if test="${notice.noticeType=='01'}">结果<dm:out value="local__OpenTendering__notice_zh">通知书</dm:out></c:if>
				</span>
			</li>
			<li>
				<label for="projName"><spring:message code="noticeForm.projName"/></label>
				<span id="projName">${notice.projName}</span>
			</li>
			<li>
				<label for="projCode"><spring:message code="noticeForm.projCode"/></label>
				<span id="projCode">${notice.projCode}</span>
			</li>
			<li>
				<label for="subProjName"><spring:message code="noticeForm.subProjName"/></label>
				<span id="subProjName">${notice.subProjName}</span>
			</li>
			<li>
				<label for="subProjCode"><spring:message code="noticeForm.subProjCode"/></label>
				<span id="subProjCode">${notice.subProjCode}</span>
			</li>
			<li>
				<label for="sellerName"><spring:message code="noticeForm.sellerName"/></label>
				<span id="sellerName">${notice.sellerName}</span>
			</li>
			<li style="width:100%;height:150px">
				<label for="noticeContent"><spring:message code="noticeForm.noticeContent"/></label>
				<span id="noticeContent">${notice.noticeContent}</span>
			</li>
			<li style="width:100%;height:80px">
				<label for="noticeRemark"><spring:message code="noticeForm.noticeRemark"/></label>
				<span id="noticeRemark">${notice.noticeRemark}</span>
			</li>
			<input type="hidden" name="noticeId" id="noticeId" value="${notice.objId}"/>
			<li style="width:100%">
				<c:if test="${notice.noticeAffirmRecord.objId==''}">
				<div class="conOperation">
	        		<button class="btn primary" id="sure" type="button" tabindex="19"><span>确认<dm:out value="local__OpenTendering__notice_zh">通知书</dm:out></span></button>
	    		</div>
	    		</c:if>
			</li>
		</ul>
	</form>
	</c:if>
</div>
