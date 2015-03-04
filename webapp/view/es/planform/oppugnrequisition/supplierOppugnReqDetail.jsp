<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout form2Pa">  
		<h4><span>质疑内容 </span></h4>
		<input type="hidden" value="${size}" id="size"/>
		<ul>	
			<li>
				<label for="oppuType"><spring:message code="oppugnRequisitionForm.oppuType"/></label>
				<span id="oppuType">
					<c:if test="${oppugnRequisition.oppuType=='00'}"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>质疑</c:if>
					<c:if test="${oppugnRequisition.oppuType=='01'}">采购过程质疑</c:if>
					<c:if test="${oppugnRequisition.oppuType=='02'}">初定采购结果质疑</c:if>
					<c:if test="${oppugnRequisition.oppuType=='03'}">其它</c:if>
				</span>
			</li>
			<li>
				<label for="consBuyerName"><spring:message code="oppugnRequisitionForm.consBuyerName"/></label>
				<span id="consBuyerName">
					<c:if test="${oppugnRequisition.consBuyerName=='00'}">招标单位</c:if>
					<c:if test="${oppugnRequisition.consBuyerName=='01'}">招标中心</c:if>
					<c:if test="${oppugnRequisition.consBuyerName=='02'}">两者</c:if>
				</span>
			</li>
			<li>
				<label for="oppuLinker"><spring:message code="oppugnRequisitionForm.oppuLinker"/></label>
				<span id="oppuLinker">${oppugnRequisition.oppuLinker }</span>
			</li>
			<li>
				<label for="oppuLinkerTel"><spring:message code="oppugnRequisitionForm.oppuLinkerTel"/></label>
				<span id="oppuLinkerTel">${oppugnRequisition.oppuLinkerTel }</span>
			</li>
			<li class="formTextarea">
				<label for="oppuContent"><spring:message code="oppugnRequisitionForm.oppuContent"/></label>
				<textarea id="oppuContent">${oppugnRequisition.oppuContent }</textarea>
			</li>
			<c:if test="${!empty oppugnRequisition.attachRelaId}">
				<li class="fullLine" >
							<label for="attachRelaId"><spring:message code="oppugnRequisitionForm.attachRelaId"/>：</label>
							<span id="attachRelaId">${oppugnRequisition.attachRelaId}</span>
				</li>
			</c:if>
			
		</ul>
			<div class="conOperation">
				<button id="oppugnRequisitionBtn" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
 			</div>
</div>
<div class="partContainers">
<h4><span>答复列表</span></h4>
<table class="tableList">
	<c:if test="${listOppugnReply!='no'}">
	<thead><tr>
		<th class="name">答复内容</th><th class="name">答复时间</th>
		<th class="name">备注</th><th class="name">附件</th>
	</tr></thead>
	<tbody>
		<c:forEach items="${listOppugnReply}" var="oppugnReply" varStatus="status"> 
			<tr>
				<td >
						${oppugnReply.oppurReplyContent}
				</td>
				<td >
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${oppugnReply.createTime}"/>
				</td>
				<td >
						${oppugnReply.oppurRemark}
				</td>
					<td class="fullLine">
      					<div id="${status.count}" class="uploadFile" >${oppugnReply.attachRelaId}</div>
      				</td>	
      		</tr>
		</c:forEach>
		</tbody>
	</c:if>
</table>
</div>
 			
<script type="text/javascript">
	$(document).ready(function(){
		$('#attachRelaId').loadPage(
				//$('#initPath').val()+'/AttachmentController.do?defineSelf=attachRelaId&isSelect=yes&attachRelaId='+$("#attachRelaId").text()
				$('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',
							{
								readOnly:'yes',
								attachRelaId:$("#attachRelaId").text()
							}
				);
		//遍历加载附件
		$.each($("div.uploadFile"),function(i,n){
				if(''!=$(this).text()){
					$(this).loadPage(
							//$('#initPath').val()+'/AttachmentController.do?defineSelf=attachRelaId&isSelect=yes&attachRelaId='+$(this).text()
							$('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',
							{
								readOnly:'yes',
								attachRelaId:$(this).text()
							}
					);
				}
				
			})
	});
//关闭
	$("#oppugnRequisitionBtn").click(function(){
		$('#epsDialogCloseNoReload').click();
	});
</script>
 			