<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/requirement/purRequirementDetail.js"></script>
<div class="partContainers">
	<div class="formLayout form2Pa">        
		<form id="purRequirementForm" method="post">
			<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
			<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
	     		<h5><span>采购需求</span></h5>
	     		<ul>
						<li class="fullLine">
							<label for="name"><spring:message code="purRequirementForm.name"/>：</label>
							<span id="name"></span>
						</li>
						<li class="fullLine">
							<label for="preqAbout"><spring:message code="purRequirementForm.preqAbout"/>：</label>
							<span id="preqAbout"></span>
						</li>
						<li class="fullLine">
							<label for="requireAtt"><spring:message code="purRequirementForm.requireAtt"/>：</label>
							<span id="requireFile"></span>
						</li>
	
			</ul>
			<div class="conOperation">
				<button name="historyBackBtn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
			</div>
		</form>
	</div>
</div>
<div id="preqEntryList">
	<flex:flexgrid checkbox="true"
		id="preqEntryGrid" url="PreqEntryController.do?method=list" queryColumns=""  
			rp="10" title="采购需求条目"  
			onSubmit="purRequirementDetail.before">
					<flex:flexCol name="name" display="preqEntryForm.name" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="purchaseName" display="preqEntryForm.purchase" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="spec" display="preqEntryForm.spec" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="unit" display="preqEntryForm.unit" sortable="true" width="60"align="left"></flex:flexCol>
					<flex:flexCol name="quantity" format="amount" display="preqEntryForm.quantity" sortable="true" width="60"align="right"></flex:flexCol>
					<flex:flexCol name="totalPrice" format="money" display="preqEntryForm.totalPrice" sortable="true" width="100"align="right"></flex:flexCol>
		<flex:flexBtn name="globe.detail" bclass="look" onpress="purRequirementDetail.showDetail"></flex:flexBtn>
	</flex:flexgrid>
</div>