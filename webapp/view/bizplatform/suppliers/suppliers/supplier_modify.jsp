<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<form:form id="SupplierInfoForm" method="post" modelAttribute="supplier">
	<input type="hidden" name="objId" id="supplierId" value="${param.supplierId}" />
	<input type="hidden" id="supplierAuditStatus" value="${supplier.auditStatus}" />
	
	<div class="formLayout form2Pa">
			<ul>
				<li>
	        		<label for="entPrpt">企业性质</label>
					<html:select selectedValue="${supplier.entPrpt}" styleClass="required" id="entPrpt" name="entPrpt" code="biz.supplier.entprpt"></html:select>
					<span class="eleRequired">*</span> 
	    	    </li>
	    	    <li class="fullLine">
	        		<label for="unitAddress">经营地址:</label>
					<input type="text" id="unitAddress" name="unitAddress" maxlength="100" value="${supplier.unitAddress}" class="required" size="60"/>
					<span class="eleRequired">*</span> 
	    	    </li>	
	    	    
	    	    <li class="formTextarea">
		            <label for="bidForRange">投标范围及类别：</label>
		            <textarea name="bidForRange_1" id="bidForRange_1" <c:if test="${orgInfo.auditStatus=='02'||orgInfo.auditStatus=='01'}">disabled="disabled"</c:if> maxlength="500" readonly="readonly" class="required"></textarea>
		            <input type="hidden" id="bidForRange_2" />
		            <input type="hidden" name="bidForRange" id="bidForRange" value="${supplier.bidForRange}" class="required" />
		            <span class="eleRequired">*</span> 
	        	</li>
	        	
	        	<li class="formTextarea">
	            	<label for="mainProductsCn">主营范围（中）：</label>
	            	<textarea rows="6" cols="20" name="mainProductsCn" maxlength="500" id="mainProductsCn" class="required">${supplier.mainProductsCn}</textarea>
	            	<span class="eleRequired">*</span> 
	        	</li>
	        	
	    	    <li class="formTextarea">
	           		<label for="input02">主营范围（英）：</label>
	           		<textarea name="mainProductsEn" id="mainProductsEn" maxlength="500">${supplier.mainProductsEn}</textarea>
	        	</li>
	    	    <li class="fullLine">
	            	<label for="input02">开户银行名称：</label>
	            	<input type="text" name="openBank" id="openBank" maxlength="50" value="${supplier.openBank}" class="required" size="60"/>
	            	<span class="eleRequired">*</span> 
	        	</li>
		        <li class="fullLine">
		            <label for="input02">开户银行账号：</label>
		         	<input type="text" name="openAccount" id="openAccount" maxlength="100" value="${supplier.openAccount}" class="required digits" size="60"/>
		            <span class="eleRequired">*</span> 
		        </li>
		        <li class="fullLine">
		            <label for="input02">开户账号姓名：</label>
		        	<input type="text" name="openAccountNmbr" id="openAccountNmbr" maxlength="100" value="${supplier.openAccountNmbr}" class="required" size="60"/>
		            <span class="eleRequired">*</span> 
		        </li>
		        <li class="fullLine">
		            <label for="input02">网址：</label>
		         	<input type="text" name="webUrl" id="webUrl" class="url" maxlength="100" value="${supplier.webUrl}" size="60"/>（请以http:// 开头）
		            <span class="eleRequired"></span> 
		        </li>
		        <li class="formTextarea">
		            <label for="input02">企业简介（中）：</label>
		          	<textarea name="descCn" id="descCn" maxlength="1000">${supplier.descCn}</textarea>	
		        </li>
		        <li class="formTextarea">
		            <label for="input02">企业简介（英）:</label>
		            <textarea name="descEn" id="descEn" maxlength="1000">${supplier.descEn}</textarea>
		        </li>
		        <li class="fullLine">
		            <label for="input02">法定代表人：</label>
		         	<input type="text" name="corporation" id="corporation" maxlength="50" value="${supplier.corporation}" size="60"/>
		        </li>
		        <li class="fullLine">
		            <label for="input02">工商注册地址：</label>
		         	<input type="text" name="regAddress" id="regAddress" maxlength="100" value="${supplier.regAddress}" size="60"/>
		        </li>
		        <li class="fullLine">
		            <label for="input02">工商注册号：</label>
		         	<input type="text" name="regCode" id="regCode" maxlength="50" value="${supplier.regCode}" size="60"/>
		        </li>
		        <li class="fullLine">
		            <label for="input02">工商注册日期：</label>
		         	<input type="text" name="regDate" id="regDate" value="${supplier.regDate}" size="40" readonly="readonly"/>
		        </li>
		        <li class="fullLine">
		            <label for="input02">工商注册有效期：</label>
		         	<input type="text" name="regToDate" id="regToDate" value="${supplier.regToDate}" size="40" readonly="readonly"/>
		        </li>
		        <li class="fullLine">
		            <label for="input02">营业执照开始日期：</label>
		         	<input type="text" name="tradeStartDate" id="tradeStartDate" value="${supplier.tradeStartDate}" size="40" readonly="readonly"/>
		        </li>
		        <li class="fullLine">
		            <label for="input02">营业执照结束日期：</label>
		         	<input type="text" name="tradeEndDate" id="tradeEndDate" value="${supplier.tradeEndDate}" size="40" readonly="readonly"/>
		        </li>
		        <li class="fullLine">
		            <label for="input02">注册资本(万元)：</label>
		         	<input type="text" name="regCapital" id="regCapital" maxlength="8" class="money" value="<fmt:formatNumber value="${supplier.regCapital}" pattern="####" />" size="40"/>
		        </li>
		        <li class="fullLine">
		            <label for="input02">实收资本(万元)：</label>
		         	<input type="text" name="paidUpCapital" id="paidUpCapital" maxlength="8" class="money" value="<fmt:formatNumber value="${supplier.paidUpCapital}" pattern="####" />" size="40"/>
		        </li>
		        <li class="fullLine">
		            <label for="input02">组织机构证登记号：</label>
		         	<input type="text" name="orgUnitRegNmbr" id="orgUnitRegNmbr" maxlength="50" value="${supplier.orgUnitRegNmbr}" size="60"/>
		        </li>
		        <li class="fullLine">
		            <label for="input02">组织机构证颁发单位：</label>
		         	<input type="text" name="orgUnitAwardUnit" id="orgUnitAwardUnit" maxlength="50" value="${supplier.orgUnitAwardUnit}" size="60"/>
		        </li>
		        <li class="fullLine">
		            <label for="input02">组织机构证开始日期：</label>
		         	<input type="text" name="orgUnitStartDate" id="orgUnitStartDate" value="${supplier.orgUnitStartDate}" size="40" readonly="readonly"/>
		        </li>
		        <li class="fullLine">
		            <label for="input02">组织机构证结束日期：</label>
		         	<input type="text" name="orgUnitEndDate" id="orgUnitEndDate" value="${supplier.orgUnitEndDate}" size="40" readonly="readonly"/>
		        </li>
		        <li class="fullLine">
		            <label for="input02">国税登记编号：</label>
		         	<input type="text" name="natTaxNmbr" id="natTaxNmbr" maxlength="50" value="${supplier.natTaxNmbr}" size="60"/>
		        </li>
		        <li class="fullLine">
		            <label for="input02">税务登记证国税电脑编码：</label>
		         	<input type="text" name="natTaxCmptNmbr" id="natTaxCmptNmbr" maxlength="50" value="${supplier.natTaxCmptNmbr}" size="60"/>
		        </li>
		        <li class="fullLine">
		            <label for="input02">地税登记编号：</label>
		         	<input type="text" name="landTaxNmbr" id="landTaxNmbr" maxlength="50" value="${supplier.landTaxNmbr}" size="60"/>
		        </li>
		        <li class="fullLine">
		            <label for="input02">税务登记证地税电脑编码：</label>
		         	<input type="text" name="landTaxCmptNmbr" id="landTaxCmptNmbr" maxlength="50" value="${supplier.landTaxCmptNmbr}" size="60"/>
		        </li>
		        <li class="fullLine">
		            <label for="input02">工商注册发证机关：</label>
		         	<input type="text" name="regAuthOrg" id="regAuthOrg" maxlength="50" value="${supplier.regAuthOrg}" size="60"/>
		        </li>
		        <li class="formTextarea">
		            <label for="input02">工商注册营业范围：</label>
		         	<textarea name="regBusScope" id="regBusScope" maxlength="500">${supplier.regBusScope}</textarea>	
		        </li>
		        <li class="formTextarea">
		            <label for="input02">企业规模：</label>
		         	<textarea name="unitScape" id="unitScape" maxlength="500">${supplier.unitScape}</textarea>	
		        </li>
		        
		        <c:if test="${supplier.opinion!=null}">
       				<li class="formTextarea">
		        		<label>审核意见：</label>
		        		<span>${supplier.opinion }(${supplier.auditStatusCN })</span>
		        	</li>
        		</c:if>
			</ul>
	</div>
</form:form>

<script>
var SupplierInfoForm={};
$(document).ready(function(){
	$('#SupplierInfoForm').validate();
	$("#regDate").epsDatepicker();
	$("#regToDate").epsDatepicker();
	$("#tradeStartDate").epsDatepicker();
	$("#tradeEndDate").epsDatepicker();
	$("#orgUnitStartDate").epsDatepicker();
	$("#orgUnitEndDate").epsDatepicker();
	
	//回填投标范围及类别
	if($('#bidForRange').val()!=null && $('#bidForRange').val()!= ""){
		var bidForRange=$('#bidForRange').val().split("##||##");
		$('#bidForRange_2').val(bidForRange[0]);
		$('#bidForRange_1').val(bidForRange[1]);
	}
	
	//选择品目（投标范围及类别）
	$("#bidForRange_1").click(function(e){
	    $.epsDialog({
	        title:'选择品目',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=bidForRange_2&NAMES=bidForRange_1&className=PurCategory&action=listTop&isCheckBox=true',
	        onClose: function(){ 
      		$("#bidForRange").val($("#bidForRange_2").val()+"##||##"+$("#bidForRange_1").val());
      	}
	    }); 
	});
})
</script>
