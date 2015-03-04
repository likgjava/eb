<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<input type="hidden" id="isShowOpinion" value="${param.isShowOpinion}"></input>
<script>
/*
 * 执行平台，委托协议详情
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var consignDetail = {};

//显示申报书信息

consignDetail.showTaskPlan = function(id,returntype){
		$.epsDialog({
	        title:'申报书信息',
	        url:$('#initPath').val()+'/TaskPlanController.do?method=showDetail&from=epsDialog&objId='+id,
	        width: '800',
	        height: '520',
	        maxWin:false,
	        onClose: function(){ 
	        }
		});	
	}   
$(document).ready(function(){
    //设置返回路径
	if($("#fromto").val()=='fromTaskplan'){//返回申报书明细页面
		$("#returnUrl").val($('#initPath').val()+"/TaskPlanController.do?method=toTaskPlanListPageForConsign");
	}else if($("#fromto").val()=='dazong'){
		$("#returnUrl").val($('#initPath').val()+"/view/es/planform/consign/consignListForAccept.jsp");
	}else{
		$("#returnUrl").val($('#initPath').val()+"/view/es/planform/consign/consignListForConfirm.jsp");
	}
    if($("#consignId").val() && $("#consignId").val() != ''){
		$("button[name=historyBackBtn]").hide();
	}
	//加载上传组件
	//$('#consContentsFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=consContentsAtt&isSelect=yes&attachRelaId='+$("#consContentsFile").text());
	//附件
	$('#consContentsFile').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'consContentsFile',//存放关联id的属性名
		attachRelaId:$("#consContentsFile").text(),
		readOnly:'yes'
	});
	
	$("[name=historyBackBtn]").click(function(){
		if($("#fromto").val()=='fromTaskplan'){//返回申报书明细页面
			$('#conBody').loadPage($('#initPath').val()+"/TaskPlanController.do?method=showDetail&objId="+$("#taskPlanId").val());
			}else if($("#fromto").val()=='fromConsign'){//返回创建委托协议列表页面
				$("#returnUrl").val($('#initPath').val()+"/TaskPlanController.do?method=toTaskPlanListPageForConsign");
			}else if($("#fromto").val()=='consignList'){//返回
				$("#returnUrl").val($('#initPath').val()+"/view/es/planform/consign/consignList.jsp");
				}
			else{
			var returnUrl = $("#return_type").val();
			if(undefined != returnUrl && "" != returnUrl){
			$("#returnUrl").val($('#initPath').val()+"/"+returnUrl);
			//$('#conBody').loadPage($('#initPath').val()+"/"+returnUrl);
			}
		}
		
	})

	var isShowOpinion = $("#isShowOpinion").val();
	if(isShowOpinion != "true"){
		$("#show_audit_view").remove();
	}
	
})

$('#show_audit_view').click(function(){
	$("#consign_audit_list").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#consignDetailForm").find('input[id=consignId2]').val()+'&taskType=05');
});

</script>

<div class="partContainers">
	<div class="formLayout form2Pa"> 
	<input type="hidden" id="return_type" value="${param.returnType }" />
	<input type="hidden" id="returntype" value="${returntype}" />
	<input type="hidden" id="fromto" value="${fromto}" />
	<input type="hidden" id="taskPlanId" value="${taskPlan.objId}" />
		<form:form id="consignDetailForm" method="post" modelAttribute="consign">
			<form:hidden path="objId"></form:hidden> 
			<form:hidden path="useStatus"></form:hidden>
			<form:hidden path="confirmStatus"></form:hidden>
			<input type="hidden" id="consignId2" value="${consign.objId}" />
			<input type="hidden" id="returntype" name="returntype"  value="${returntype}">
			<h5><span>委托协议详细</span></h5>
			<ul>
				<li>
					<label class="short" for="consCode"><spring:message code="consignForm.consCode"/>：</label>
					<span id="consCode">${consign.consCode}</span>
				</li>
				<li>
					<label class="short" for="consName"><spring:message code="consignForm.consName"/>：</label>
					<span id="consName">${consign.consName}</span>
				</li>
				<li class="fullLine">
					<label class="short" for="consBuyerName"><spring:message code="consignForm.consBuyer"/>：</label>
					<span id="consBuyerName">${consign.consBuyerName}</span>
				</li>
				<li>
					<label class="short" for="consBuyerLinker"><spring:message code="consignForm.consBuyerLinker"/>：</label>
					<span id="consBuyerLinker">${consign.consBuyerLinker}</span>
				</li>
				<li>
					<label class="short" for="consBuyerTel"><spring:message code="consignForm.consBuyerTel"/>：</label>
					<span id="consBuyerTel">${consign.consBuyerTel}</span>
				</li>
				<li class="fullLine">
					<label class="short" for="consAgentName"><spring:message code="consignForm.consAgent"/>：</label>
					<span id="consAgentName">${consign.consAgentName}</span>
				</li>
				<li>
					<label class="short" for="consAgentLinker"><spring:message code="consignForm.consAgentLinker"/>：</label>
					<span id="consAgentLinker">${consign.consAgentLinker}</span>
				</li>
				<li>
					<label class="short" for="consAgentTel"><spring:message code="consignForm.consAgentTel"/>：</label>
					<span id="consAgentTel">${consign.consAgentTel}</span>
				</li>
				<li>
					<label class="short" for="consTime"><spring:message code="consignForm.consTime"/>：</label>
					<span id="consTime"><fmt:formatDate value="${consign.consTime}" pattern="yyyy-MM-dd"/></span>
				</li>
				<li>
					<label class="short" for="consFinishTime"><spring:message code="consignForm.consFinishTime"/>：</label>
					<span id="consFinishTime"><fmt:formatDate value="${consign.consFinishTime}" pattern="yyyy-MM-dd"/></span>
				</li>
				<li class="fullLine">
					<label class="short" for="taskPlan">采购申报书：</label>
					<span id="taskplans">
						<c:forEach items="${tpList}" var="taskPlan" >
							<a href="javascript:void(0)" style="color: blue;" onclick="consignDetail.showTaskPlan('${taskPlan.objId }','${returntype}')">${taskPlan.taskName }</a>
						</c:forEach>
					</span>
				</li>
				<li class="fullLine">
					<label class="short" for="consContentsAtt"><spring:message code="consignForm.consContentsAtt"/>：</label>
					<span id="consContentsFile">${consign.consContentsAtt}</span>
				</li>
			</ul>
		    <div class="conOperation">
		  		<button id="show_audit_view" ><span>操作历史</span></button>
				<button name="historyBackBtn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
			</div>
		</form:form>
	</div>
</div>

<div id="consign_audit_list" ></div>
