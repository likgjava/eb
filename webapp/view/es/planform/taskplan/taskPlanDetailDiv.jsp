<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<script>
	var taskPlanDetail = {};
	taskPlanDetail.success = function(){
		$("#taskPlanSubGrid").flexGetColByName({
			 'taskPlanSub.objId' : function(id,t){
			 	var json = $("#taskPlanSubGrid").flexGetRowJsonById(id); 
			 	$(t).html('<span><a href="#" class="abtn" onclick="taskPlanDetail.showDetail(\''+json['taskPlanSub.objId']+'\')">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>');
			}
		});
	}
	taskPlanDetail.showDetail = function(objId){
		$.epsDialog({
	    	title:'申报书条目-查看需求信息',
	    	url:$('#initPath').val()+'/TaskPlanController.do?method=toLookTaskPlanSubRequireInfoView&objId='+objId,
	    	width: '800',
	    	height: '420',
	    	onClose: function(){ 
	        	
	       }
		});
	}



	//判断当前申报书类型是否为大宗项目
	$("input[name=taskType]").click( function() {
		//当前项目为大宗项目
		 if(this.value=='01'){
			$("#dljg").hide();
		 }
		 else{
			$("#dljg").show();
	     }
	}); 

	$(document).ready(function(){
		//如果申报书类型为大宗项目
		if($("#taskTypes").val()=='01'){
			$("#dljg").hide();
		}
	})

	
</script>
<input type="hidden" id="taskTypes" name="taskTypes"  value="${taskPlan.taskType}">
<div class="partContainers">
<div class="formLayout form2Pa">  
	<input type="hidden" id="taskType" name="taskType" value="${taskPlan.taskType}">      
	<form:form id="taskPlanForm" method="post" modelAttribute="taskPlan">
		<form:hidden path="objId"></form:hidden> 
		<form:hidden path="useStatus"></form:hidden>
		<form:hidden path="auditDetail"></form:hidden>
		<form:hidden path="confirmStatus"></form:hidden>
		<h5><span><dm:out value="local__taskPlanManager__taskplan_zh">申报书</dm:out>信息</span></h5>
     		<ul>
					<li>
						<label class="short"  for="taskCode"><spring:message code="taskPlanForm.taskCode"/>：</label>
						<span id="taskCode">${taskPlan.taskCode}</span>
					</li>
					<li>
						<label class="short"  for="taskName"><spring:message code="taskPlanForm.taskName"/>：</label>
						<span id="taskName">${taskPlan.taskName}</span>
					</li>
					<li>
						<label class="short"  for="applyDate"><spring:message code="taskPlanForm.applyDate"/>：</label>
						<span id="applyDate"><fmt:formatDate value="${taskPlan.applyDate}" pattern="yyyy-MM-dd"/></span>	
					</li>
					
					<li>
						<label class="short"  for="finishDate"><spring:message code="taskPlanForm.finishDate"/>：</label>
						<span id="finishDate"><fmt:formatDate value="${taskPlan.finishDate}" pattern="yyyy-MM-dd"/></span>	
					</li>
					<li>
						<label class="short"  for="ebuyMethod"><spring:message code="taskPlanForm.ebuyMethodCN"/>：</label>
						<span id="ebuyMethodCN">${taskPlan.ebuyMethodCN}</span>
					</li>
					<c:if test="${'YES'==isChanged}">
						<li><label class="short"  for="taskType"><spring:message code="taskPlanForm.taskType"/>：</label>
							<c:if test="${taskPlan.taskType == '00'}">
								非大宗交易
							</c:if>
							<c:if test="${taskPlan.taskType == '01'}">
								大宗交易
							</c:if>
						</li>
					</c:if>
					<c:if test="${'YES'!=isChanged}">
					<li><label class="short"  for="taskType"><spring:message code="taskPlanForm.taskType"/>：</label>
						<span id="taskTypeCN">${taskPlan.taskTypeCN}</span>
					</li>
					</c:if>
					<!-- 
					<c:if test="${'YES'!=isChanged}">
					<li><label class="short"  for="taskAgent"><spring:message code="taskPlanForm.taskAgent"/>：</label>
						<span id="taskAgentName">${taskPlan.taskAgentName}</span>	
					</li>
					</c:if>
					<c:if test="${'YES'==isChanged}">
						<li  ID="dljg" class="fullLine">
							<label class="short"  for="taskAgent"><spring:message code="taskPlanForm.taskAgent"/>：</label>
							<input type= "text" id="taskAgentName" name="taskAgentName" value="${taskPlan.taskAgentName}">
							<input type= "hidden" id="taskAgent.objId" name="taskAgent.objId" value="${taskPlan.taskAgent.objId}">
							<span class="eleRequired">*</span>
						</li>
					</c:if>
					 -->
					<li>
						<label class="short"  for="budgetName"><spring:message code="taskPlanForm.budget"/>：</label>
						<span id="budgetName">${taskPlan.budgetName}</span>			 		  
					</li>
					<li>
						<label class="short"  for="budgetLinker"><spring:message code="taskPlanForm.budgetLinker"/>：</label>
						<span id="budgetLinker">${taskPlan.budgetLinker}</span>	
					</li>
					<li>
						<label class="short"  for="budgetLinkerTel"><spring:message code="taskPlanForm.budgetLinkerTel"/>：</label>
						<span id="budgetLinkerTel">${taskPlan.budgetLinkerTel}</span>	
					</li>
				</ul>
			<h6 align="center"><span>业务处室信息</span></h6>
				<ul>
					<li>
						<label class="short"  for="governmentName"><spring:message code="taskPlanForm.government"/>：</label>
					    <span id="governmentName">${taskPlan.governmentName}</span>	
					</li>
					<li>
						<label class="short"  for="govLinker"><spring:message code="taskPlanForm.govLinker"/>：</label>
						<span id="govLinker">${taskPlan.govLinker}</span>			 	  
					</li>
					<li class="fullLine">
						<label class="short"  for="govLinkerTel"><spring:message code="taskPlanForm.govLinkerTel"/>：</label>
						<span id="govLinkerTel">${taskPlan.govLinkerTel}</span>			 		  
					</li>
		</ul>
			<h6 align="center"><span>主管单位信息</span></h6>
				<ul>
					<li>
						<label class="short"  for="departmentName"><spring:message code="taskPlanForm.department"/>：</label>
					    <span id="departmentName">${taskPlan.departmentName}</span>	
					</li>
					<li>
						<label class="short"  for="departmentLinker"><spring:message code="taskPlanForm.departmentLinker"/>：</label>
						<span id="departmentLinker">${taskPlan.departmentLinker}</span>		
					</li>
					<li class="fullLine">
						<label class="short"  for="departmentLinkerTel"><spring:message code="taskPlanForm.departmentLinkerTel"/>：</label>
						<span id="departmentLinkerTel">${taskPlan.departmentLinkerTel}</span>			 
					</li>
				</ul>
			
	</form:form>
</div>
</div>
<input type="hidden" id="taskPlanZh" value="<dm:out value="local__taskPlanManager__taskplan_zh">申报书</dm:out>明细">
<div id="taskPlanSubListView">
    	<flex:flexgrid checkbox="false"
			id="taskPlanSubGrid" url="TaskPlanMSubController.do?method=list&taskPlan.objId=${taskPlan.objId}" queryColumns="" rp="10" title="#taskPlanZh"  
				usepager="false"  onSuccess="taskPlanDetail.success">
						<flex:flexCol name="taskPlanSub.budgetName" display="taskPlanForm.budget" sortable="true" width="100"align="left"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.purchaseName" display="taskPlanSubForm.purchaseName" sortable="true" width="100"align="left"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.unit" display="taskPlanSubForm.unit" sortable="true" width="50"align="center"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.quantity" format="amount" display="taskPlanSubForm.quantity" sortable="true" width="100"align="right"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.totalPrice" format="money" display="taskPlanSubForm.totalPrice" sortable="true" width="100"align="right"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.objId" display="操作" sortable="true" width="60" align="center"></flex:flexCol>
		</flex:flexgrid>
    </div>
    <input type="hidden" id="taskPlanDetailZh" value="<dm:out value="local__taskPlanManager__taskPlanDetail_zh">采购资金</dm:out>明细">
    <div id="taskPlanDetailListView">
    	<flex:flexgrid checkbox="false"
			id="taskPlanDetailGrid" url="TaskPlanMDetailController.do?method=list&taskPlan.objId=${taskPlan.objId}" queryColumns=""  rp="10" title="#taskPlanDetailZh"  
				usepager="false">
						<flex:flexCol name="taskPlanDetail.approvalNumber" display="taskPlanDetailForm.approvalNumber" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.superiorApp"format="money" display="taskPlanDetailForm.superiorApp" sortable="true" width="76"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.localApp" format="money" display="taskPlanDetailForm.localApp" sortable="true" width="76"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.ownerApp" format="money" display="taskPlanDetailForm.ownerApp" sortable="true" width="76"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.otherApp" format="money" display="taskPlanDetailForm.otherApp" sortable="true" width="76"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.quantity" format="money" display="taskPlanDetailForm.quantity" sortable="true" width="76"align="right"></flex:flexCol>
		</flex:flexgrid>
    </div>
<script>
$(document).ready(function(){	
    var taskType =  "<c:out value="${taskPlan.taskType}"/>";
    var isChanged = "<c:out value="${isChanged}"/>";
    if(isChanged=='YES'){
		if('00'==taskType){
    	      $("#radioNO").attr("checked","checked");
      	}else{
        	$("#radioYES").attr("checked","checked");
   		}
		//选择招标中心
		$("#taskAgentName").autocomplete(
			$('#initPath').val() + '/OrgInfoController.do?method=getObjectQuery&queryColumns=objId,orgName', 
			{
				matchColumn:'orgName',//作为查询显示, 被选中之后匹配的列
				extraParams:{"agencyId":null,"agencyId_op":"!="},
				mustMatch: true,
				formatItem: function(data, i, total) {
					return data.orgName;
				},
				formatMatch: function(data, i, total) {
					return data.orgName;
				},
				formatResult: function(data) {
					return data.orgName;
				}
			}
		).result(function(event,data,formatted){
			if(data){
				$("#taskAgentName").val(data.orgName);//回填id
				$("input[id=taskAgent.objId]").val(data.objId);//回填id
			}
		});
	}
   
	//隐藏委托书信息
    if($("#consigns").text().length < 20){
        $("#consignLi").hide();
    }
})
</script>