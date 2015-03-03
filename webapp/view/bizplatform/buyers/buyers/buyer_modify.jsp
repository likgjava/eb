<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<form:form id="BuyerInfoForm" method="post" modelAttribute="buyer">
	<input type="hidden" name="objId" id="buyerId" value="<c:if test="${param.buyerId!='undefined'}">${param.buyerId}</c:if>" />
	<input type="hidden" id="buyerAuditStatus" value="${buyer.auditStatus}" />
	
  	<div class="formLayout form2Pa">
		<ul>
    	  <li class="fullLine">
             <label for="purSbjct">单位性质：</label>
             <html:select selectedValue="${buyer.unitType}" id="unitType" name="unitType" code="biz.buyer.unitType"></html:select>
          </li>
    	  <li class="fullLine">
          	<label for="belongIndustry">所属行业：</label>
          	<input type="text" id="belongIndustry_display" readonly="readonly" value="${buyer.belongIndustry.name}" size="60"/>
          	<input type="hidden" name="belongIndustry.objId" id="belongIndustry" value="${buyer.belongIndustry.objId}"/>
    	  </li>
    	  <li class="fullLine">
            <label for="supervisor">上级单位：</label>
            <c:if test="${empty buyer.parentUnit}">
	            <input type="text" name="supervisor" id="supervisor" size="60" readonly="readonly" class="required"/>
	            <span class="eleRequired">*</span> 
				<input type="hidden" id="supervisorId" name="parentUnit.objId"/>
            </c:if>
            <c:if test="${!empty buyer.parentUnit}">
	            <span>${buyer.parentUnit.orgName}</span>
	            <input type="hidden" id="supervisorId" name="parentUnit.objId" value="${buyer.parentUnit.objId}"/>
            </c:if>
          </li>
<!--		  <li class="fullLine">-->
<!--            <label for="execDept">行政部门：</label>-->
<!--            <html:select selectedValue="${buyer.execDept}" id="execDept" name="execDept" code="biz.buyer.execDept"></html:select>-->
<!--           </li>-->
<!--		   <li>-->
<!--		    <label for="cmptDepType">主管部门</label>-->
<!--            <html:select selectedValue="${buyer.cmptDepType}" id="cmptDepType" name="cmptDepType" code="biz.buyer.cmptDepType"></html:select>-->
<!--		   </li>-->
<!--		   <li class="fullLine">-->
<!--	        <label for="input02">资金归口处室：</label>-->
<!--            <html:select selectedValue="${buyer.fundDept}" id="fundDept" name="fundDept" code="biz.buyer.fundDept"></html:select>-->
<!--		   </li>-->
<!--		   <li class="fullLine">-->
<!--	            <label for="input02">预算编码：</label>-->
<!--	         	<input type="text" name="budgetCode" maxlength="50" id="budgetCode" value="${buyer.budgetCode}" size="60"/>-->
<!--		   </li>-->
		   <li class="formTextarea">
	            <label for="input02">机构简介：</label>
	         	<textarea name="unitIntroduction" id="unitIntroduction" maxlength="500">${buyer.unitIntroduction}</textarea>
		   </li>
		   
		   <c:if test="${buyer.opinion!=null}">
        	<li class="formTextarea">
        		<label>审核意见：</label>
        		<span>${buyer.opinion }(${buyer.auditStatusCN })</span>
        	</li>
           </c:if>
		</ul>
	</div>
</form:form>

<script>
var BuyerInfoForm={};
$(document).ready(function(){
	$('#BuyerInfoForm').validate();
	
	//上级单位
	$("#supervisor").click(function(){
		$.epsDialog({
	        title:'选择',
	        url:$("#initPath").val()+'/view/bizplatform/organization/manager/select_orginfo.jsp?Hname=supervisor&Hid=supervisorId&orgType=b'
	    })
	})
	
	//选择所属行业
 	$("#belongIndustry_display").click(function(e){
	    $.epsDialog({
	        title:'选择所属行业',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=belongIndustry&NAMES=belongIndustry_display&className=Industry&action=listTop'
	    }); 
 	});
})
</script>