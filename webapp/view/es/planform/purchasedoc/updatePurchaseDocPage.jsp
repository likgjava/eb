<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/updatePurchaseDocPage.js"></script>
<input type="hidden" id="projectId"   name="projectId"  value="${purchaseDoc.project.objId}">
<input type="hidden" id="purchaseDocId" name="purchaseDocId" value="${purchaseDoc.objId}">
<input type="hidden" name="auditStatus" id="auditStatus" value="Y"/>
<input type="hidden" id="divTarget"   name="divTarget"  value="${divTarget}">
<input type="hidden" id="divTargetUrl"   name="divTargetUrl"  value="${divTargetUrl}">
<div class="formLayout" >         		
      <h5><span>项目信息</span></h5>
      <ul>
        <li>
           <label for="projName"><spring:message code="projectForm.projName"/>：</label>
							<span id="projName">${purchaseDoc.project.projName}</span>
        </li>
        <li>
           <label for="projCode"><spring:message code="projectForm.projCode"/>：</label>
							<span id="projCode">${purchaseDoc.project.projCode}</span>
        </li>
      </ul>     
     		<ul>
     		<li><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>：</li>
     		         <li>
     		             <label for="keyWord"><spring:message code="purchaseDocForm.keyWord"/>：</label>
							<span id="keyWord">${purchaseDoc.keyWord }</span>
     		        </li>
     		        <li>
						<label for="content"><spring:message code="purchaseDocForm.content"/>：</label>
							<span id="content">${purchaseDoc.content}</span>
					</li>
					<li>
     		             <label for="filePrice"><spring:message code="purchaseDocForm.filePrice"/>：</label>
							<span id="filePrice">${purchaseDoc.filePrice }</span>
     		        </li>
					 <li>
						<label for="auditStatus"><spring:message code="purchaseDocForm.auditStatus"/>：</label>
 								 <span id="auditStatus">${purchaseDoc.auditStatusCN}</span>
					</li>
					<li>
				        <c:import url="/view/srplatform/auth/attachment.jsp">
          			    <c:param name="attachRelaId" value="${purchaseDoc.attachRelaId}"/>
         			    <c:param name="isSingle" value="yes"/>
          				<c:param name="isSelect" value="yes"/>
                        </c:import>
					</li>
		</ul>
</div>
			<div class="conOperation">
				<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
	   	    </div>
	   	    
	   	    <div id="historyView"></div>