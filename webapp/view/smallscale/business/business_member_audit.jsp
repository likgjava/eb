<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="BusinessMemberDetailForm" method="post" modelAttribute="businessMember">
	<input type="hidden" id="objId" name="objId" value="${businessMember.objId}"/>
	<input type="hidden" id="orgInfoId" name="orgInfo.objId" value="${businessMember.orgInfo.objId}"/>
	
	<div class="formLayout form1Pa">
		<ul>
			<li><label>机构名称：</label> <span id="orgInfo.orgName">${businessMember.orgInfo.orgName}</span>
			</li>
			
			<li><label>会员时长：</label> <span>${businessMember.timeTypeCN}</span>
			</li>
			
			<li><label>开始日期：</label> <span>${businessMember.begainDate}</span>
			</li>
			
			<li><label>结束日期：</label> <span>${businessMember.endDate}</span>
			</li>
		</ul>
	</div>
	
	<div class="conOperation">
		<button class="operationBtnDiv" type="button" id="agreeBtn"><span>审核通过</span></button>
		<button class="operationBtnDiv" type="button" id="refuseBtn"><span>审核不通过</span></button>
		<button type="button" name="historyBackBtn" id="return"><span>返回</span></button>
	</div>
</form:form>

	<!-- 扩展信息部分结束 -->

<script>
	var BusinessMemberDetailForm={};
	//审核
	BusinessMemberDetailForm.auditMember=function(memberId,auditStatus){
	  var jsonObj = formToJsonObject('BusinessMemberDetailForm');
	  if(auditStatus=='02') {
		  jsonObj.useStatus='01';;
	  }
	  jsonObj.auditStatus=auditStatus;
	  
	  $.getJSON($('#initPath').val()+'/BusinessMemberController.do?method=auditBusinessMember',jsonObj,function(json){
	      if(json.result)alert(json.result);
	      if(json.failure){return;}
	      $('#return').click();
	  });
	}

$(document).ready(function(){
	$('#agreeBtn').click(function(){//通过
		 if(window.confirm("确认通过吗")){
			 BusinessMemberDetailForm.auditMember($("#objId").val(),'02');
		 }
	});
	$('#refuseBtn').click(function(){//不通过
		 if(window.confirm("确认不通过吗")){
			 BusinessMemberDetailForm.auditMember($("#objId").val(),'03');
		 }
	});
});
</script>