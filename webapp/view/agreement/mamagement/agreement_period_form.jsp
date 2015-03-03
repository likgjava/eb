<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="AgreementPeriod" method="post" modelAttribute="period">
<div class="formLayout form1Pa">
<form:hidden path="objId"></form:hidden>
<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
<h4><span>协议期间</span></h4>
<ul>
<li>
	<label>期间名称：</label>
	<form:input path="periodName" cssClass="required"></form:input>
	<span class="eleRequired">*</span>
</li>
<li>
	<label>开始时间：</label>
	<form:input path="beginDate" id="beginDate" cssClass="required"></form:input>
	<span class="eleRequired">*</span>
	结束时间：
	<form:input path="endDate" id="endDate" cssClass="required"></form:input>
	<span class="eleRequired">*</span>
</li>
<li class="formTextarea">
	<label>备注：</label>
	<form:textarea path="memo"/>
</li>
</ul>  
</div>
<div class="conOperation">
	<button id="agreementPeriodSave" type="button" ><span><spring:message code="globe.save"/></span></button>
	<button id="agreementPeriodReturn" type="button" ><span><spring:message code="globe.return"/></span></button>
</div>
</form:form>
		
<script type="text/javascript">
/*
 * 平台开发demo
 * author: yucy
 * mail: yuchengyang@yeah.net
 */
var agreementForm={};

$(document).ready(function(){
	//开始时间
    $("#beginDate").epsDatepicker({applyRule: endRule });  //增加结束时间的规则

	//结束时间
    $("#endDate").epsDatepicker({applyRule: startRule });  //增加开始时间的规则
	
	//返回
	$('#agreementPeriodReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/PeriodController.do?method=toPeriodList");
		//$('#conBody').loadPage($('#initPath').val()+"/view/mamagement/agreement_period_list.jsp");
	});
	
	//提交
	$('#agreementPeriodSave').click(function(){
		if(!$('#AgreementPeriod').valid()){alert('请正确填写表单!');return;}
		var period = formToJsonObject("AgreementPeriod");
		$.getJSON($('#initPath').val()+'/PeriodController.do?method=save',period,function(json){
			if(json.failure){
				alert(json.result);
				return;
			}else{
				alert("保存成功!");
				//PeriodController.do?method=toPeriodList
				$('#conBody').loadPage($('#initPath').val()+"/PeriodController.do?method=toPeriodList");
				//$('#conBody').loadPage($('#initPath').val()+"/view/mamagement/agreement_period_list.jsp");
			}
		});
	});
});

</script>
		
		
		
		
		

