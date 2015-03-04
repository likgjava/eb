<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="formLayout form2Pa">
	<h5><span>录入<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>信息</span></h5>
	<form id="subprojectForm" method="post">
		<ul>
  			<li class="fullLine">
     			<label for="projCode"><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>编号：</label>
   				<input type="text" name="projCode" id="projCode" class="required" value="${subProject.projCode }" onkeyup="checkSubProjectName('code','projCode');"/>
   				<span class="eleRequired" id="warnNo">*</span>
   				<input type="hidden" name="parentId" id="parentId" value="${parentId}" />
   				<input type="hidden" name="objId" id="objId" value="${subProject.objId }" />
   			</li>
   			<li class="fullLine">
   				<label for="projName"><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>名称：</label>
   				<input type="text" name="projName" id="projName" class="required" value="${subProject.projName }" onkeyup="checkSubProjectName('name','projName');"/>
   				<span class="eleRequired" id="warnName">*</span>
   			</li>
   			<li class="fullLine">
   				<label for="projSummary"><dm:out value="local__package" tenderType="${param.tenderType}">项目</dm:out>摘要：</label>
   				<input type="text" name="projSummary" id="projSummary" class="required" value="${subProject.projSummary }" onkeyup="checkSubProjectName('summary','projSummary');"/>
   				<span class="eleRequired" id="warnSummary">*</span>
   			</li>
 			</ul>
 				<!-- 隐藏数据用于判断 -->
			<input type="hidden" name="gloVar" id="gloVar" value="${purchaseId}"/>
			<input type="hidden" id="flagCodeId" value="" />
			<input type="hidden" id="flagNameId" value="" />
   			<div class="conOperation">
				<button id="subProjectSaveButton" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
				<button id="subProjectReturn" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
 			</div>
		</form>
</div>

<div class="partContainers">
	<table class="tableList" id="SubProjectList">
		<caption>申报书条目</caption>
  		<thead>
      		<tr class="center">
      			<th>选择</th>
      			<th>品目编号</th>
          		<th>品目名称</th>
          		<th>未拆分数量</th>
          		<th>未拆分预算（元）</th>
          		<th>拆分数量</th>
          		<th>拆分预算（元）</th>
     		</tr>
		</thead>
	<tbody>
	<c:forEach items="${taskPlanMSubList}" var="taskPlanMSub" varStatus="i">
		<tr>
			<td align="center"><input type="checkbox" id="checkboxOrgId_${i.count}" name="checkboxOrgId" value="" <c:if test="${taskPlanMSub.quantity!=null}">checked</c:if> onclick="getRadionVal('${taskPlanMSub.taskPlanSub.realMoney}','${taskPlanMSub.taskPlanSub.purchase.objId}','${taskPlanMSub.taskPlanSub.realQuantity}','${taskPlanMSub.taskPlanSub.objId}','${taskPlanMSub.taskPlan.objId}','${i.count}');"/>
			</td>
			<td>${taskPlanMSub.taskPlanSub.purchase.categoryCode}</td>
			<td>${taskPlanMSub.taskPlanSub.purchase.categoryName}</td>
			<td>
						<fmt:formatNumber value="${taskPlanMSub.taskPlanSub.realQuantity}" type="number"/>
			</td>
			<td><fmt:formatNumber value="${taskPlanMSub.taskPlanSub.realMoney}" type="currency"/></td>
			<td>
			<input type="text" style="width:50;" name="quantity_${i.count}" id="quantity_${i.count}" value="${taskPlanMSub.quantity}" onkeyup="subProjectForm.showmoney('quantity_${i.count}')" onblur="subProjectForm.recordSaveData('${taskPlanMSub.taskPlanSub.purchase.objId}','${taskPlanMSub.taskPlanSub.objId}','${taskPlanMSub.taskPlan.objId}','${i.count}');"/>
			<span id="quantity_${i.count}2"></span>
			</td>
			<td>
			<input type="text" style="width:50;" name="money_${i.count}" id="money_${i.count}" value="${taskPlanMSub.money}" onkeyup="subProjectForm.showmoney('money_${i.count}')" onblur="subProjectForm.recordSaveData('${taskPlanMSub.taskPlanSub.purchase.objId}','${taskPlanMSub.taskPlanSub.objId}','${taskPlanMSub.taskPlan.objId}','${i.count}');"/>
			<span id="money_${i.count}2"></span>
			</td>
			<!-- 隐藏数据用于判断 -->
			<input type="hidden" name="record" id="record_${i.count}" indexNo="${i.count}" value="${taskPlanMSub.taskPlanSub.objId},${taskPlanMSub.taskPlan.objId},${taskPlanMSub.quantity},${taskPlanMSub.money}"/>
			<input type="hidden" name="check_money" id="check_money_${i.count}"value="${taskPlanMSub.money}"/>
			<input type="hidden" name="check_quantity" id="check_quantity_${i.count}"value="${taskPlanMSub.quantity}"/>
			<input type="hidden" name="check_quantity2" id="check_quantity2_${i.count}"value="${taskPlanMSub.taskPlanSub.realQuantity}"/>
			</tr>
		
	</c:forEach>
	</tbody>
    </table>
</div>

<script language="javascript">
var subProjectForm = {}

function checkSubProjectName(name,id){
	var idVal = $('#'+id).val();
	var parentId = $('#parentId').val();
	var tes =  /^[a-zA-Z0-9]+$/;
	if (idVal!=null&&idVal!=''&&idVal!=undefined) {//判断是否有值
		if ('code'==name) {//编号
			var jsonObj = {};
			jsonObj.value = idVal;
			$.getJSON($('#initPath').val()+'/ProjectController.do?method=checkSubProject&subProjectId='+$("#objId").val()+'&key=code&projectId='+parentId,jsonObj, function(json){
			   	if(json.result)alert(json.result);if(json.failure)return;
				if (json.projectList.length!=''&&json.projectList.length!=null&&json.projectList.length!=undefined&&json.projectList.length>0) {
					$('#warnNo').empty().html('该包组编号已存在!');
					$('#flagCodeId').val('false');
				}else if(!tes.test(idVal)) {//给编号加一个判断，编号只能由数字 和字母组成。若非，则return false.
					$('#warnNo').empty().html('包组编号只能由数字或字母组成!');
					$('#flagCodeId').val('false');	
				}else{
					$('#warnNo').empty();
					$('#flagCodeId').val('true');
				}
			});	
		}else{//名称
			var jsonObj = {};
			jsonObj.value = idVal;
			$.getJSON($('#initPath').val()+'/ProjectController.do?method=checkSubProject&subProjectId='+$("#objId").val()+'&key=name&projectId='+parentId,jsonObj, function(json){
			   	if(json.result)alert(json.result);if(json.failure)return;
			   	if (json.projectList.length!=''&&json.projectList.length!=null&&json.projectList.length!=undefined&&json.projectList.length>0) {
					$('#warnName').empty().html('该包组名称已存在!');
					$('#flagNameId').val('false');
				}else{
					$('#warnName').empty();
					$('#flagNameId').val('true');
				}
			});	
		}
	}

}	
//提交
$('#subProjectSaveButton').click(function(){
	checkSubProjectName('code','projCode');
	checkSubProjectName('name','projName');
	if(!$('#subprojectForm').valid()){alert('请准确填写表单！');return;}
		if ($('#flagCodeId').val()=='false') {
			alert('包组编号只能由数字或字母组成,请正确输入！');
			return false;
		}
		if ($('#flagNameId').val()=='false') {
			alert('该包组名称已存在！');
			return false;
		}
		//获取拼装好的值：条目Id、申报书Id、数量
		 var recordCount='';
		  $('input[name="record"]').each(function(){
			  var indexNo = $(this).attr('indexNo');
			  var checkBoxObj = $('#checkboxOrgId_'+indexNo);
			  if($(this).val()!=""&&checkBoxObj.attr('checked')){
			  recordCount+=$(this).val()+':';
			  }
		  });
		//判断是否有选中的申报书条目
			var checkSelected = '';
			$('input[name="checkboxOrgId"]:checked').each(function(){
				  checkSelected='yes';
			});
		if(checkSelected==''){
			alert("请选择条目！");
			return false;
		}else{
		  $.getJSON($('#initPath').val()+'/ProjectController.do?method=updateProjectAndReq&recordCount='+recordCount+'&ebuyMethod='+$('#ebuyMethod').val(), formToJsonObject('subprojectForm'), function(json){
			   if(json.result)alert(json.result);if(json.failure)return;
				  $('#epsDialogCloseNoReload').click();
				  if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
						planTemplateTask.clickMethod($("#projectTaskId").val()+"");
						//planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
					  } else {
						$("#myDesktop").click();
				}
			});
		}
});
//关闭
$("#subProjectReturn").click(function(){
	$('#epsDialogCloseNoReload').click();
});


function getRadionVal(money,purchaseId,quantity,taskPlanSubId,taskPlanId,num){
	var check = $('#check_quantity_'+num).val();
	var checkM = $('#check_money_'+num).val();
	var count = Number(quantity)+Number(check);
	var countM = Number(money)+Number(checkM);
	//if(Number(quantity)==0){alert("申报书条目数量已用完！");$("#checkboxOrgId_"+num).removeAttr("checked");return false;}
	var golvar = $('#gloVar').val();
	//判断是否为同一品目
	if(golvar==""||golvar==purchaseId){
	//判断是否被选中
	if($("#checkboxOrgId_"+num).attr("checked")==true)
	{
	//var relMoney = Number(count)*Number(unitPrice)*1;//拆分预算金额
	//默认拆分数量
	$('#quantity_'+num).val(count);
	//默认拆分预算
	$('#money_'+num).val(countM);
	//拼装后台处理数据
	var record = taskPlanSubId+','+taskPlanId+','+count+','+countM
	$('#record_'+num).val(record);
	//用于判断是否为同一品目
	$('#gloVar').val(purchaseId);
//	subProjectForm.showRealMoney(num,relMoney);//预算金额
	}
/**	else if (Number(money)<relMoney) {
		alert("填写数量过大，预算总金额不足！");
		$("#moneyRId_"+num).empty();
		$('#quantity_'+num).val("");
		return false;
	}
*/
	else{
		//判断是否还有选中的
		if($("[name='checkboxOrgId']").attr("checked")==true){
		$('#record_'+num).val("");
		$('#quantity_'+num).val("");
		}else{
		$('#record_'+num).val("");
		$('#quantity_'+num).val("");
		//没有选中的置为空
		$('#gloVar').val("");
		}
		$("#money_"+num).val("");
	}
	}//不是同一品目
	else{
		alert("请选择同一品目！");
		$("#checkboxOrgId_"+num).removeAttr("checked");
		$("#money_"+num).val("");
		$('#quantity_'+num).val("");
	}
}
/**
function checkQuantity(unitPrice,money,purchaseId,quantity,taskPlanSubId,taskPlanId,num,moneys){
	var quantity = $('#quantity_'+num).val();
	var check = $('#check_quantity_'+num).val();
	var quantityCheck = $('#check_quantity2_'+num).val();
	var count = Number(quantityCheck)+Number(check);
	var relMoney = Number(quantity)*Number(unitPrice)*1;
	var golvar = $('#gloVar').val();
	var moneyCheck = Number(money)+Number(moneys);
	if(Number(quantity)+Number(check)==0){
		alert("请正确填写数量！");
		$('#quantity_'+num).val('');
		$("#moneyRId_"+num).empty();
		$("#checkboxOrgId_"+num).attr("checked",false);
		return false;
	}else if(Number(quantity)>count){
		alert("填写数量过大，未拆分数量不足！");
		$('#quantity_'+num).val('');
		$("#moneyRId_"+num).empty();
		$("#checkboxOrgId_"+num).attr("checked",false);
		return false;
	}else if (moneyCheck<relMoney) {
		alert("填写数量过大，预算总金额不足！");
		$('#quantity_'+num).val('');
		$("#moneyRId_"+num).empty();
		$("#checkboxOrgId_"+num).attr("checked",false);
		return false;
	}else if (golvar!=purchaseId&&golvar!=''&&golvar!=null) {
		alert("请选择同一品目！");
		$('#quantity_'+num).val('');
		$("#moneyRId_"+num).empty();
		$("#checkboxOrgId_"+num).attr("checked",false);
		return false;
	}else{
		subProjectForm.showRealMoney(num,relMoney);
		//拼装后台处理数据
		var record = taskPlanSubId+','+taskPlanId+','+quantity+','+relMoney
		$('#record_'+num).val(record);
	}
}
*/



//subProjectForm.showRealMoney = function(num,relMoney){
//	$("#moneyRId_"+num).empty().html("<font color='red'>￥"+subProjectForm.formatNumber(relMoney)+".00</font>");
//}

subProjectForm.recordSaveData=function(purchaseId,taskPlanSubId,taskPlanId,num){
	var golvar = $('#gloVar').val();
	if (golvar!=purchaseId&&golvar!=''&&golvar!=null) {
		alert("请选择同一品目！");
		$('#quantity_'+num).val('');
		$("#money_"+num).empty();
		$("#checkboxOrgId_"+num).attr("checked",false);
		return false;
	}else{
		var quantity = $('#quantity_'+num).val();
		var relMoney = $('#money_'+num).val();
		//拼装后台处理数据
		var record = taskPlanSubId+','+taskPlanId+','+quantity+','+relMoney
		$('#record_'+num).val(record);
	}
}

subProjectForm.showmoney = function(obj){
	   var a = $("#"+obj).val();
	   var re = /^[0-9]+.?[0-9]*$/;   //判断字符串是否为数字      
	   //判断正整数 /^[1-9]+[0-9]*]*$/      
	   if (!re.test(a)){    
	    	 $("#"+obj+"2").html("<font color='red'>请输入数字</font>");
	    	 $("#"+obj).val('');
	         $("#"+obj).focus();    
	         return false;    
	   }else{
		   $("#"+obj+"2").empty();
	   }
	}

subProjectForm.formatNumber=function(number){
		var number = ""+number;
		if(number.length > 3){
		   var mod = number.length%3;
		   var put = (mod > 0 ? (number.substring(0,mod)) : "");
		   var j=(number.length-mod)/3;
		   for(i=0;i<j;i++){
		    if(mod==0&&i==0){
		     put+=number.substring(mod+3*i,mod+3*i+3);
		    }else if(mod==0&&i!=0){
		     put+=","+number.substring(mod+3*i,mod+3*i+3);
		    }else {
		     put+=","+number.substring(mod+3*i,mod+3*i+3);
		    }
		   }
		   number=put;
		}
		return number;
	}


</script>