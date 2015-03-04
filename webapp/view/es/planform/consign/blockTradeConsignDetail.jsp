<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script>
/*
 * 执行平台，委托协议详情
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var consignDetail = {};

//显示申报书信息
consignDetail.showTaskPlan = function(id){
	$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&isBlockTrade=isBlockTrade&objId='+id);
}

$(document).ready(function(){

	//加载上传组件
	//$('#consContentsFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=consContentsAtt&isSelect=yes&attachRelaId='+$("#consContentsFile").text(),function(){
		//$('#consContentsFile').find('a').each(function(i,n){
			//$(n).attr("style","color: blue;")
		//})
	//});
	//附件
	$('#consContentsFile').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'consContentsFile',//存放关联id的属性名
		attachRelaId:$("#consContentsFile").text(),
		readOnly:'yes'
	});
	
	//判断返回按钮
	if($("#consignId").val() && $("#consignId").val() != ''){
		$("#returnBtn").hide();
	}

	//返回
	  $("#returnBtn").click(function(){  
		  $('#conBody').loadPage($('#initPath').val()+'/view/es/planform/consign/blockTradeConsignList.jsp');  
	  })
})
</script>

<div class="partContainers">
	<div class="formLayout form2Pa"> 
		<form:form id="consignDetailForm" method="post" modelAttribute="consign">
			<form:hidden path="objId"></form:hidden> 
			<form:hidden path="useStatus"></form:hidden>
			<form:hidden path="confirmStatus"></form:hidden>
			<h5><span>委托协议详细</span></h5>
			<ul>
				<li>
					<label for="consCode"><spring:message code="consignForm.consCode"/>：</label>
					<span id="consCode">${consign.consCode}</span>
				</li>
				<li>
					<label for="consName"><spring:message code="consignForm.consName"/>：</label>
					<span id="consName">${consign.consName}</span>
				</li>
				<li>
					<label for="consBuyerName">采购办名称：</label>
					<span id="consBuyerName">${consign.consBuyerName}</span>
				</li>
				<li>
					<label for="consBuyerLinker">采购办联系人：</label>
					<span id="consBuyerLinker">${consign.consBuyerLinker}</span>
				</li>
				<li>
					<label for="consBuyerTel">采购办联系电话：</label>
					<span id="consBuyerTel">${consign.consBuyerTel}</span>
				</li>
				<li>
					<label for="consAgentName"><spring:message code="consignForm.consAgent"/>：</label>
					<span id="consAgentName">${consign.consAgentName}</span>
				</li>
				<li>
					<label for="consAgentLinker"><spring:message code="consignForm.consAgentLinker"/>：</label>
					<span id="consAgentLinker">${consign.consAgentLinker}</span>
				</li>
				<li>
					<label for="consAgentTel"><spring:message code="consignForm.consAgentTel"/>：</label>
					<span id="consAgentTel">${consign.consAgentTel}</span>
				</li>
				<li>
					<label for="consTime"><spring:message code="consignForm.consTime"/>：</label>
					<span id="consTime"><fmt:formatDate value="${consign.consTime}" pattern="yyyy-MM-dd"/></span>
				</li>
				<li>
					<label for="consFinishTime"><spring:message code="consignForm.consFinishTime"/>：</label>
					<span id="consFinishTime"><fmt:formatDate value="${consign.consFinishTime}" pattern="yyyy-MM-dd"/></span>
				</li>
				<li class="fullLine">
					<label for="taskPlan">采购申报书：</label>
					<span id="taskplans">
						<c:forEach items="${tpList}" var="taskPlan" >
							<a href="javascript:void(0)" style="color: blue;" onclick="consignDetail.showTaskPlan('${taskPlan.objId }')">${taskPlan.taskName }</a>
						</c:forEach>
					</span>
				</li>
				<li class="fullLine">
					<label for="consOpinion"><spring:message code="consignForm.consOpinion"/>：</label>
					<span id="consOpinion">${consign.consOpinion}</span>
				</li>
				<li class="fullLine">
					<label for="consRemark"><spring:message code="consignForm.consRemark"/>：</label>
					<span id="consRemark">${consign.consRemark}</span>
				</li>
				<li class="fullLine">
					<label for="consContentsAtt"><spring:message code="consignForm.consContentsAtt"/>：</label>
					<span id="consContentsFile">${consign.consContentsAtt}</span>
				</li>
			</ul>
		    <div class="conOperation">
				<button id="returnBtn" name="returnBtn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
			</div>
		</form:form>
	</div>
</div>
