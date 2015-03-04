<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/buyresult/buyResultDetail.js"></script>

<div class="formLayout form2Pa">  
	<form id="buyResultDetailForm" method="post">
		<input type="hidden" name="buyResultId" id="buyResultId" value="${param.objId}"/>
		<h5><span>成交结果</span></h5>
		<ul>
			<li>
				<label for="ebuyMethod"><spring:message code="buyResultForm.ebuyMethod"/>：</label>
				<span id="ebuyMethodCN"></span>
			</li>
			<li>
				<label for="buyrResult"><spring:message code="buyResultForm.buyrResult"/>：</label>
				<span id="buyrResultCN"></span>
			</li>
		</ul>
	    <div class="conOperation">
	        <button class="btn primary" id="buyResultReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	    </div>
	</form>
</div>
<div id="buyWinnerListView"> 
<flex:flexgrid checkbox="true"
		id="buyWinnerGrid" url="BuyWinnerController.do?method=list&buyResult.objId=${param.objId}" queryColumns=""  
			searchZone="buyWinnerSearchZone" rp="10" title="投标单位清单" >
					<flex:flexCol name="selllerId" display="buyWinnerForm.selllerId" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="sellerName" display="buyWinnerForm.sellerName" sortable="true" width="100"align="left"></flex:flexCol>	
	</flex:flexgrid>
</div>
