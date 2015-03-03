<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="SupplierDetailForm" method="post" modelAttribute="supplier">
	<input type="hidden" id="supplier_ObjId" name="supplier_ObjId" value="<c:out value="${supplier.objId}"/>"/>
	<input type="hidden" id="orgInfoCurrentId" name="orgInfoCurrentId" value="<c:out value="${orgInfoCurrentId}"/>"/>
	<div class="formLayout form2Pa"><span id="historySupplierJsonString" class="hidden">${historyObject}</span>
	<ul>
		<li><label>经营地址：</label> <span id="unitAddress">${supplier.unitAddress}</span>
		</li>
		<li><label>企业性质：</label> <span id="entPrptCN">${supplier.entPrptCN}</span>
		</li>
		<li><label>开户银行名称：</label> <span id="openBank">${supplier.openBank}</span>
		</li>
		<li><label>开户账号姓名：</label> <span id="openAccountNmbr">${supplier.openAccountNmbr}</span>
		</li>
		<li><label>开户银行账号：</label> <span id="openAccount">${supplier.openAccount}</span>
		</li>
		<li><label>是否厂家：</label> 
			<span id="isManufacturer">
				<c:choose>
				<c:when test="${supplier.isManufacturer=='1'}">是</c:when>
				<c:otherwise>否</c:otherwise>
				</c:choose>
			</span> 
	    </li>
		<li class="fullLine"><label>网址：</label> <span id="webUrl">${supplier.webUrl}</span>
		</li>
		<li class="fullLine"><label>投标范围及类别：</label> <span id="bidForRangeName">${supplier.bidForRangeName}</span>
		</li>
		<li class="fullLine"><label>主营范围（中）：</label> <span id="mainProductsCn">${supplier.mainProductsCn}</span>
		</li>
		<li class="fullLine"><label>主营范围（英）：</label> <span id="mainProductsEn">${supplier.mainProductsEn}</span>
		</li>
		<li class="fullLine"><label>企业简介（中）：</label> <span id="descCn">${supplier.descCn}</span>
		</li>
		<li class="fullLine"><label>企业简介（英）：</label> <span id="descEn">${supplier.descEn}</span>
		</li>
		<li><label>法定代表人：</label><span id="corporation">${supplier.corporation}</span>
		</li>
		<li><label>工商注册地址：</label><span id="regAddress">${supplier.regAddress}</span>
		</li>
		<li><label>工商注册号：</label><span id="regCode">${supplier.regCode}</span>
		</li>
		<li><label for="input02">工商注册日期：</label><span id="regDate">${supplier.regDate}</span>
		</li>
		<li><label for="input02">工商注册有效期：</label><span id="regToDate">${supplier.regToDate}</span>
		</li>
		<li><label for="input02">营业执照开始日期：</label><span id="tradeStartDate">${supplier.tradeStartDate}</span>
		</li>
		<li><label for="input02">营业执照结束日期：</label><span id="tradeEndDate">${supplier.tradeEndDate}</span>
		</li>
		<li><label for="input02">注册资本：</label><span id="regCapital">￥<fmt:formatNumber value="${supplier.regCapital}" pattern="#,##0.00#" /></span>
		</li>
		<li><label for="input02">实收资本：</label><span id="paidUpCapital">￥<fmt:formatNumber value="${supplier.paidUpCapital}" pattern="#,##0.00#" /></span>
		</li>
		<li><label for="input02">企业规模：</label><span id="unitScape">${supplier.unitScape}</span>
		</li>
		<li><label for="input02">组织机构证登记号：</label><span id="orgUnitRegNmbr">${supplier.orgUnitRegNmbr}</span>
		</li>
		<li><label for="input02">组织机构证颁发单位：</label><span id="orgUnitAwardUnit">${supplier.orgUnitAwardUnit}</span>
		</li>
		<li><label for="input02">组织机构证开始日期：</label><span id="orgUnitStartDate">${supplier.orgUnitStartDate}</span>
		</li>
		<li><label for="input02">组织机构证结束日期：</label><span id="orgUnitEndDate">${supplier.orgUnitEndDate}</span>
		</li>
		<li><label for="input02">国税登记编号：</label><span id="natTaxNmbr">${supplier.natTaxNmbr}</span>
		</li>
		<li><label for="input02">税务登记证国税电脑编码：</label><span id="natTaxCmptNmbr">${supplier.natTaxCmptNmbr}</span>
		</li>
		<li><label for="input02">地税登记编号：</label><span id="landTaxNmbr">${supplier.landTaxNmbr}</span>
		</li>
		<li><label for="input02">税务登记证地税电脑编码：</label><span id="landTaxCmptNmbr">${supplier.landTaxCmptNmbr}</span>
		</li>
		<li><label for="input02">工商注册发证机关：</label><span id="regAuthOrg">${supplier.regAuthOrg}</span>
		</li>
		<li><label for="input02">工商注册营业范围：</label><span id="regBusScope">${supplier.regBusScope}</span>
		</li>
	</ul>
	</div>
	
	<c:if test="${hasSupplierRole == null}">
		<c:if test="${supplier.auditStatus != '02' && viewType=='apply'}">
				<div id="cancelApplyDiv_Supplier">
				 	<div class="conOperation">
						<button type="button" class="largeBtn" id="cancelApply_Supplier"><span>取消申请</span></button>
					</div>
				</div>
		</c:if>
	</c:if>
</form:form>

<script>
$(document).ready(function(){
	//获得变更前对象进行对比
	if($("#historySupplierJsonString").text() != null && $("#historySupplierJsonString").text() != ""){
		//循环页面元素显示历史值
		var json = JSON.parse($("#historySupplierJsonString").text());
		json.historyObject = json;
        displayHistoryObject(json,'SupplierDetailForm');
	}

	//取消申请
	$('#cancelApply_Supplier').click(function() {
		if(window.confirm('确定取消?')) {
			$.getJSON($('#initPath').val()+'/SupplierController.do?method=updateCancelApply',{"supplierId":$("#supplier_ObjId").val()},function(json){
				if(json.failure){if(json.result)alert(json.result);return;}
				alert('取消成功!')
				$('#cancelApplyDiv_Supplier').hide();

				$('#conBody').loadPage($('#initPath').val()+'/OrgInfoController.do?method=toApplyOrgInfo');
			});  
		}
	})
})
</script>