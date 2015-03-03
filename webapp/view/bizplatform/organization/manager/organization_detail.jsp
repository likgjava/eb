<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="OrgInfoDetailForm" method="post" modelAttribute="orgInfo">
		<input type="hidden" id="objId" name="objId" value="${orgInfo.objId}"/>
		<input type="hidden" id="currentId" name="currentId" value="<c:out value="${orgInfo.currentId}"/>"/>
        <input type="hidden" id="useStatus" name="useStatus" value="<c:out value="${orgInfo.useStatus}"/>"/>
		<input type="hidden" id="supplierId" name="supplierId" value="${orgInfo.supplierId}"/>
		<input type="hidden" id="buyerId" name="buyerId" value="${orgInfo.buyerId}"/>
		<input type="hidden" id="agencyId" name="agencyId" value="${orgInfo.agencyId}"/>
	
	<div class="formLayout form2Pa">
		<div class="k1">
			<div class="img_250_2" id="newPreview">
				<img src="<c:url value="AttachmentController.do?method=showImg&objId=${orgInfo.logo}" />" width="160px" height="150px">
			</div>
		</div>
		<ul>
			<li><label>机构名称：</label> <span id="orgInfo.orgName">${orgInfo.orgName}</span>
			</li>
			<li><label>机构代码：</label> <span id="orgInfo.orgCode">${orgInfo.orgCode}</span>
			</li>
			<li><label>联系手机：</label> <span id="orgInfo.company.mobilePhone">${orgInfo.company.mobilePhone}</span>
			</li>
			<li><label>联系电话：</label> <span id="orgInfo.company.tel">${orgInfo.company.tel}</span>
			</li>
			<li><label>行政区域：</label> <span id="distinctName">${orgInfo.distinctName}</span>
			</li>
			<li><label>邮编：</label> <span id="company.postCode">${orgInfo.company.postCode}</span>
			</li>
			<li class="formTextarea"><label>详细通信地址：</label> <span id="orgInfo.company.address">${orgInfo.company.address}</span>
			</li>
		</ul>
	</div>

	
	<!-- 扩展信息部分开始 -->
	<div id="infoDiv">
		<ul id="infoUl">
			<c:if test="${orgInfo.supplierId != null && orgInfo.supplierId != ''}">
				<li id="supplierLi"><a href="#supplierInfo" id="loadSupplierBtn">供应商信息</a></li>
			</c:if>
			
			<c:if test="${orgInfo.buyerId != null && orgInfo.buyerId != ''}">
				<li id="buyerLi"><a href="#buyerInfo" id="loadBuyerBtn">采购人信息</a></li>
			</c:if>
			
			<c:if test="${orgInfo.agencyId != null && orgInfo.agencyId != ''}">
				<li id="agencyLi"><a href="#agencyInfo" id="loadAgencyBtn">代理机构信息</a></li>
			</c:if>
		
			<c:if test="${orgInfo.qualitys != null && fn:length(orgInfo.qualitys) > 0}">
				<li id="qualityLi"><a href="#qualityInfo" id="loadAgencyBtn">资质信息</a></li>
			</c:if>
			
			<c:if test="${orgInfo.successCases != null && fn:length(orgInfo.successCases) > 0}">
				<li id="successcaseLi"><a href="#successcaseInfo" id="loadAgencyBtn">成功案例信息</a></li>
			</c:if>
		</ul>
		<div id="supplierInfo"></div>
		<div id="buyerInfo"></div>
		<div id="agencyInfo"></div>
		<div id="qualityInfo">
			<c:if test="${orgInfo.qualitys != null && fn:length(orgInfo.qualitys) > 0}">
				<table class="frontTableList" id="orgQualitysCaseList">
				<c:choose>
				<c:when test="${orgInfo.qualitys != null && fn:length(orgInfo.qualitys) > 0}">
					<c:forEach var="orgQual" items="${orgInfo.qualitys}">
						<tr>
							<td>${orgQual.qualificationClass.qualityName}</td>
							<td>${orgQual.qualificationDefine.qualityName}</td>
							<td align="center"><fmt:formatDate value="${orgQual.createTime}" pattern="yyyy年MM月dd日"/></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
						<tr><td align="center" colspan="4">该机构暂无成功案例信息</td></tr>
				</c:otherwise>
				</c:choose>
				</table>
			</c:if>
		</div>
		<div id="successcaseInfo">
			<c:if test="${orgInfo.successCases != null && fn:length(orgInfo.successCases) > 0}">
				<table class="frontTableList" id="orgSuccessCaseList">
				<c:choose>
				<c:when test="${orgInfo.successCases != null && fn:length(orgInfo.successCases) > 0}">
					<c:forEach var="orgSucc" items="${orgInfo.successCases}">
						<tr>
							<td>${orgSucc.projectName}</td>
							<td align="center"><fmt:formatDate value="${orgSucc.startTime}" pattern="yyyy年MM月dd日"/></td>
							<td align="center"><fmt:formatDate value="${orgSucc.endTime}" pattern="yyyy年MM月dd日"/></td>
							<td>${orgSucc.categoryNames}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
						<tr><td align="center" colspan="4">该机构暂无成功案例信息</td></tr>
				</c:otherwise>
				</c:choose>
				</table>
			</c:if>
		</div>
	</div>
	</form:form>
	
	<div class="conOperation">
		<button class="operationBtnDiv" type="button" id="close"><span>关闭</span></button>
	</div>
	<!-- 扩展信息部分结束 -->

<script>
	var OrgInfoDetailForm={};
	//审核
	OrgInfoDetailForm.auditOrgInfo=function(orgInfoId,auditStatus){
	  var jsonObj = formToJsonObject('OrgInfoDetailForm');
	  jsonObj.auditStatus=auditStatus;
	  $.getJSON($('#initPath').val()+'/OrgInfoController.do?method=auditOrgInfo',jsonObj,function(json){
	      if(json.result)alert(json.result);
	      if(json.failure){return;}
	      $('#conBody').loadPage($('#initPath').val()+"/view/bizplatform/organization/manager/organization_audit_list.jsp");
	  });
	}
	//获得供应商角色信息
	function getSupplierRoleInfo(){
		$("#supplierInfo").loadPage($("#initPath").val()+"/SupplierController.do?method=toSupplierRole&supplierId="+$("#supplierId").val());
	}
	//获得采购人角色信息
	function getBuyerRoleInfo(){
		$("#buyerInfo").loadPage($("#initPath").val()+"/BuyerController.do?method=toBuyerRole&buyerId="+$("#buyerId").val());
	}
	//获得代理机构角色信息
	function getAgencyRoleInfo(){
		$("#agencyInfo").loadPage($("#initPath").val()+"/AgencyController.do?method=toAgencyRole&agencyId="+$("#agencyId").val());
	}
$(document).ready(function(){
  	//加载扩展信息
  	var $tabs=$("#infoDiv").tabs();
	if($("#agencyId").val() != "" && $("#agencyId").val() != null){
		getAgencyRoleInfo();
	}
	if($("#buyerId").val() != "" && $("#buyerId").val() != null){
		getBuyerRoleInfo();
	}
	if($("#supplierId").val() != "" && $("#supplierId").val() != null){
		getSupplierRoleInfo();
	}

	//关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})
});
</script>