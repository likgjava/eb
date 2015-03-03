<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="AgencyDetailForm" method="post" modelAttribute="agency">
	 <input type="hidden" id="agency_ObjId" name="agency_ObjId" value="<c:out value="${agency.objId}"/>"/>
	 <input type="hidden" id="orgInfoCurrentId" name="orgInfoCurrentId" value="<c:out value="${orgInfoCurrentId}"/>"/>
     <div class="formLayout form2Pa"><span id="historyAgencyJsonString" class="hidden">${historyObject}</span>
	     <ul>
	      <li>
	        <label for="ecnmNature">经济性质：</label>
	        <span id="ecnmNature">${agency.ecnmNature}</span>
	      </li>
	      <li>
	        <label for="orgName">主管单位：</label>
	        <span id="unitInCharge.orgName">${orgInfo.orgName}</span>
		  </li>
	      <li>
	        <label for="regCode">工商注册号：</label>
	        <span id="regCode">${agency.regCode}</span>
		  </li>
	      <li>
	        <label for="regDate">注册日期：</label>
	        <span id="regDate">${agency.regDate}</span>
		  </li>
	      <li>
	        <label for="tradeStartDate">营业执照有效期：</label>
	        <span id="tradeStartDate">${agency.tradeStartDate}</span>
		  </li>
	      <li>
	        <label for="regAddress">注册地址：</label>
	        <span id="regAddress">${agency.regAddress}</span>
	      </li>
	      <li>
	        <label for="regAuthOrg">发证机关：</label>
	        <span id="regAuthOrg">${agency.regAuthOrg}</span>
	      </li>
	      <li>
	        <label for="prctTotalNmbr">从业人员总数：</label>
	        <span id="prctTotalNmbr">${agency.prctTotalNmbr}</span>
	      </li>
	      <li>
	        <label for="middleTitleTchstNmbr">中级职称 技术人员数：</label>
	        <span id="middleTitleTchstNmbr">${agency.middleTitleTchstNmbr}</span>
	      </li>
	      <li>
	        <label for="highTitleTchstNmbr">高级职称技术人员数：</label>
	        <span id="highTitleTchstNmbr">${agency.highTitleTchstNmbr}</span>
	      </li>
	      <li>
	        <label for="regPrctsNmbr">注册执业人员数：</label>
	        <span id="regPrctsNmbr">${agency.regPrctsNmbr}</span>
	      </li>
	       <li>
	        <label for="regISOCN">是否通过ISO认证： </label>
	        <span id="regISOCN">${agency.regISOCN}</span>
	      </li>
	      <li>
	        <label for="regCapital">资产总额：</label>
	        <span id="regCapital">￥<fmt:formatNumber value="${agency.regCapital}" pattern="#,##0.00#" /></span>
	      </li>
	      <li>
	        <label for="fixedAstDprcYr">固定资产年折旧额： </label>
	        <span id="fixedAstDprcYr">￥<fmt:formatNumber value="${agency.fixedAstDprcYr}" pattern="#,##0.00#" /></span>
	      </li>
	      <li>
	        <label for="crntAst">流动资产：</label>
	        <span id="crntAst">￥<fmt:formatNumber value="${agency.crntAst}" pattern="#,##0.00#" /></span>
	      </li>
	      <li>
	        <label for="totalCharge">负债总额：</label>
	        <span id="totalCharge">￥<fmt:formatNumber value="${agency.totalCharge}" pattern="#,##0.00#" /></span>
	      </li>
	      <li>
	        <label for="bidPrpsEvltAddr">评标地址 ：</label>
	        <span id="bidPrpsEvltAddr">${agency.bidPrpsEvltAddr}</span>
	      </li>
	      <li>
	        <label for="adminGrdCN">行政级别：</label>
	        <span id="adminGrdCN">${agency.adminGrdCN}</span>
	      </li>
	      <li class="fullLine">
	        <label for="unitTypeCN">企业类型：</label>
	        <span id="unitTypeCN">${agency.unitTypeCN}</span>
	      </li>
	      <li class="fullLine">
	        <label for="mianBussScp">营业范围(主营)(1000字以内)：</label>
	        <span id="mianBussScp">${agency.mianBussScp}</span>
	      </li>
	      <li class="fullLine">
	        <label for="cncrBussScp">营业范围(兼营)(1000字以内)：</label>
	        <span id="cncrBussScp">${agency.cncrBussScp}</span>
	      </li>
	      <li class="fullLine">
	        <label for="agencyBussCndt">近三年内承担过的主要招标代理项目(1000字以内)：</label>
	        <span id="agencyBussCndt">${agency.agencyBussCndt}</span>
	      </li>
	      <li class="fullLine">
	        <label for="undtkBidProj">近三年内代理经营情况(1000字以内)：</label>
	        <span id="undtkBidProj">${agency.undtkBidProj}</span>
	      </li>
	      <li class="fullLine">
	        <label for="remarks">备注(1000字以内)：</label>
	        <span id="remarks">${agency.remarks}</span>
	      </li>
	   	</ul>
	  </div>
	  
	  <c:if test="${hasAgencyRole == null}">
		  <c:if test="${agency.auditStatus != '02' && viewType=='apply'}">
				<div id="cancelApplyDiv_Agency">
				 	<div class="conOperation">
						<button type="button" class="largeBtn" id="cancelApply_Agency"><span>取消申请</span></button>
					</div>
				</div>
		  </c:if>
	  </c:if>
</form:form>

<script>
$(document).ready(function(){
	//获得变更前对象进行对比
	if($("#historyAgencyJsonString").text() != null && $("#historyAgencyJsonString").text() != ""){
		//循环页面元素显示历史值
		var json = JSON.parse($("#historyAgencyJsonString").text());
		json.historyObject = json;
        displayHistoryObject(json,'AgencyDetailForm');
	}

	//取消申请
	$('#cancelApply_Agency').click(function() {
		if(window.confirm('确定取消?')) {
			$.getJSON($('#initPath').val()+'/AgencyController.do?method=updateCancelApply',{"agencyId":$("#agency_ObjId").val()},function(json){
				if(json.failure){if(json.result)alert(json.result);return;}
				alert('取消成功!')
				$('#conBody').loadPage($('#initPath').val()+'/OrgInfoController.do?method=toApplyOrgInfo');
			});  
		}
	})
})
</script>