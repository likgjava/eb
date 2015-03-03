<%@ page pageEncoding="utf-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="partContainers">

<div class="formLayout form2Pa">
	<h5>&nbsp;<strong>${baseCompany.orgName}</strong><c:if test="${!empty baseCompany.code}">(${baseCompany.code})</c:if></h5>
	<div class="k1">
		<div class="img_250_2" id="newPreview">
			<c:choose>
				<c:when test="${baseCompany.logo==null}">
					<img id="view"  src="<%=request.getContextPath()%>/view/resource/skin/bizplatform/img/orginfo_add.gif" width="200px" height="200px"></img>
				</c:when>
				<c:otherwise>
					<img src="<c:url value="AttachmentController.do?method=showImg&objId=${baseCompany.logo}" />" width="200px" height="200px">
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<ul id="companyInfo">
		<li><label><spring:message code="baseCompanyForm.shortName"/>：</label>
			<span>${baseCompany.shortName}</span>
		</li>
		<li><label><spring:message code="baseCompanyExtForm.orgCode"/>：</label>
			<span>${baseCompany.companyExt.orgCode}</span>
		</li>
		<li><label><spring:message code="baseCompanyForm.entPrpt"/>：</label>
			<span>${baseCompany.entPrptCN}</span>
		</li>
		<li><label><spring:message code="baseCompanyForm.belongIndustry"/>：</label>
			<span>${baseCompany.belongIndustry.name}</span>
		</li>
		<li><label><spring:message code="baseCompanyExtForm.regCapital"/>(万元)：</label>
			<span><c:if test="${!empty baseCompany.companyExt.regCapital}"><fmt:formatNumber value="${baseCompany.companyExt.regCapital}" pattern="#,##0.00#" /></c:if></span>
		</li>
		<li><label><spring:message code="baseCompanyExtForm.unitScape"/>：</label>
			<span>${baseCompany.companyExt.unitScapeCN}</span>
		</li>
		<li><label><spring:message code="baseCompanyForm.croporate"/>：</label>
			<span>${baseCompany.croporate}</span>
		</li>
		<li><label><spring:message code="baseCompanyForm.contactor"/>：</label>
			<span>${baseCompany.contactor.name}</span>
		</li>
		<li><label><spring:message code="baseCompanyForm.tel"/>：</label>
			<span>${baseCompany.tel}</span>
		</li>
		<li><label><spring:message code="baseCompanyForm.fax"/>：</label>
			<span>${baseCompany.fax}</span>
		</li>
		<li><label><spring:message code="baseCompanyForm.email"/>：</label>
			<span>${baseCompany.email}</span>
		</li>	
		<li><label><spring:message code="baseCompanyForm.webUrl"/>：</label>
			<span>${baseCompany.webUrl}</span>
		</li>	
		<li class="fullLine"><label><spring:message code="baseCompanyForm.areaName"/>：</label>
			<span>${baseCompany.areaName}</span>
		</li>
		<li class="fullLine"><label><spring:message code="baseCompanyForm.address"/>：</label>
			<span>${baseCompany.address}</span>
		</li>	
		
		<c:if test="${baseCompany.companyExt!=null}">
			<li><label><spring:message code="baseCompanyExtForm.openBank"/>：</label>
				<span>${baseCompany.companyExt.openBank}</span>
			</li>
			
			<li><label><spring:message code="baseCompanyExtForm.openAccount"/>：</label>
				<span>${baseCompany.companyExt.openAccount}</span>
			</li>
			
			<li class="fullLine"><label><spring:message code="baseCompanyExtForm.openAccountName"/>：</label>
				<span>${baseCompany.companyExt.openAccountName}</span>
			</li>
			
			<li class="fullLine"><label><spring:message code="baseCompanyExtForm.descCn"/>：</label>
				<span>${baseCompany.companyExt.descCn}</span>
			</li>	
		</c:if>
	</ul>
	<span class="rightTool"><spring:message code="baseCompanyForm.updateTime"/>：<fmt:formatDate value="${baseCompany.updateTime}" pattern="yyyy年MM月dd日"/></span>
</div>


<c:if test="${baseCompany.supplierId!=null}">
	<fieldset class="operationDiv">
	<legend>供应商信息</legend>
	
	<div class="formLayout form2Pa">
		<h5><a href="javascript:void(0);" onclick="companyDetail.openOrCloseInfo(this,'${baseCompany.supplierId}');" name="supplierOrgInfo" id="/BaseSupplierController.do?method=toLoadSupplierOrgInfo"><strong>[展开或收起]</strong></a>&nbsp;组织机构信息</h5>
		<!-- 加载页面 -->
		<ul class="hidden" id="supplierOrgInfo"></ul>
	</div>
	
	<div class="formLayout form2Pa">
		<h5><a href="javascript:void(0);" onclick="companyDetail.openOrCloseInfo(this,'${baseCompany.supplierId}');" name="supplierRegInfo" id="/BaseSupplierController.do?method=toLoadSupplierRegInfo"><strong>[展开或收起]</strong></a>&nbsp;工商注册信息</h5>
		<!-- 加载页面 -->
		<ul class="hidden" id="supplierRegInfo"></ul>
	</div>
	
	<div class="formLayout form2Pa">
		<h5><a href="javascript:void(0);" onclick="companyDetail.openOrCloseInfo(this,'${baseCompany.supplierId}');" name="supplierTaxInfo" id="/BaseSupplierController.do?method=toLoadSupplierTaxInfo"><strong>[展开或收起]</strong></a>&nbsp;税务登记信息</h5>
		<!-- 加载页面 -->
		<ul class="hidden" id="supplierTaxInfo"></ul>
	</div>
	
	</fieldset>
</c:if>

<c:if test="${baseCompany.buyerId!=null}">
	<fieldset class="operationDiv">
	<legend>采购人信息</legend>
	
	<div class="formLayout form2Pa">
		<h5><a href="javascript:void(0);" onclick="companyDetail.openOrCloseInfo(this,'${baseCompany.buyerId}');" name="buyerDepInfo" id="/BaseBuyerController.do?method=toLoadBuyerDepInfo"><strong>[展开或收起]</strong></a>&nbsp;部门组织信息</h5>
		<!-- 加载页面 -->
		<ul class="hidden" id="buyerDepInfo"></ul>
	</div>
	
	<div class="formLayout form2Pa">
		<h5><a href="javascript:void(0);" onclick="companyDetail.openOrCloseInfo(this,'${baseCompany.buyerId}');" name="buyerFundInfo" id="/BaseBuyerController.do?method=toLoadBuyerFundInfo"><strong>[展开或收起]</strong></a>&nbsp;财政资金信息</h5>
		<!-- 加载页面 -->
		<ul class="hidden" id="buyerFundInfo"></ul>
	</div>
	</fieldset>
</c:if>

<c:if test="${baseCompany.agentId!=null}">
	<fieldset class="operationDiv">
	<legend>代理机构信息</legend>
	
	<div class="formLayout form2Pa">
		<h5><a href="javascript:void(0);" onclick="companyDetail.openOrCloseInfo(this,'${baseCompany.agentId}');" name="agentQfInfo" id="/BaseAgentController.do?method=toLoadAgentQfInfo"><strong>[展开或收起]</strong></a>&nbsp;代理资质信息</h5>
		<!-- 加载页面 -->
		<ul class="hidden" id="agentQfInfo"></ul>
	</div>
	
	<div class="formLayout form2Pa">
		<h5><a href="javascript:void(0);" onclick="companyDetail.openOrCloseInfo(this,'${baseCompany.agentId}');" name="agentHrInfo" id="/BaseAgentController.do?method=toLoadAgentHrInfo"><strong>[展开或收起]</strong></a>&nbsp;机构人力信息</h5>
		<!-- 加载页面 -->
		<ul class="hidden" id="agentHrInfo"></ul>
	</div>
	</fieldset>
</c:if>

<!--
<div class="formLayout form2Pa">
	<h5><a href="javascript:void(0);" onclick="companyDetail.openOrCloseInfo(this);" name="supervisionInfo" id=""><strong>[展开或收起]</strong></a>&nbsp;监管机构信息</h5>
	 加载页面 
	<ul class="hidden" id="supervisionInfo"></ul>
</div>
-->

</div>

<c:if test="${pageType!='load'}">
	<div class="operationBtnDiv">
		<button id="printPage" type="button" onclick="companyDetail.companyPrint();"><span>打印</span></button>
		<button name="historyBackBtn"><span>返回</span></button>
	</div>
</c:if>
<script type="text/javascript">
//全局变量
var companyDetail = {};

//打印方法
companyDetail.companyPrint = function(){
	$(".partContainers").printArea();
}

//展开或关闭相关信息方法
companyDetail.openOrCloseInfo  = function(e,objId){
	var a = $(e);
	var ul = $(e).parent().next("ul[id="+$(e).attr("name")+"]");
	if(ul.html()==""){
		ul.loadPage($("#initPath").val()+ a.attr("id")+"&objId="+objId, function(){
			//加载完之后显示或隐藏
			ul.toggle("fast");
		});
	}else{
		ul.toggle("fast");
	}
}
</script>
