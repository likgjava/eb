<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style type="text/css">
<!--
a.abtn {text-decoration:underline ;color: red}
.modifyBtn{float: right;}
--> 
</style>

<script>
var projectInfoDetail = {};


projectInfoDetail.update = function(projectId){
	$("#projectDoDiv").loadPage($("#initPath").val()+'/ProjectController.do?method=toUpdateInputTenderInfo',{objId:projectId})
}
projectInfoDetail.showDetail=function(objId){
	$.epsDialog({
        title:'查看申报书信息',
        url:$('#initPath').val()+'/TaskPlanController.do?method=showDetail&objId='+objId+'&type=selectAgent',
        width: '800',
        height: '500',
 	    isReload:true
			});
	}
</script>
<div class="partContainers">
<div class="formLayout form2Pa"> 
<c:set var="taskPlanMSubListSize" value="${fn:length(user.menus)}"></c:set>
<c:if test="${taskPlanMSubListSize>0}"><!-- 如果没有，则不显示申报书 -->
<div class="operationLog">
	<h5 ><span class="switch  left11">申报书条目信息</span></h5>
</div>
<div class="partContainers">
	<table class="tableList" id="SubProjectList">
  		<thead>
      		<tr class="center">
      			<th>招标单位</th>
          		<th>申报书编号</th>
          		<th>品目名称</th>
          		<th>预算（元）</th>
     		</tr>
		</thead>
	<tbody>
		<c:forEach items="${taskPlanMSubList}" var="subProjectMTaskPlanSub" varStatus="i">
		<tr>
			<td class="center">${subProjectMTaskPlanSub.taskPlanSub.budgetName}</td>
			<td class="center"><a href="#" onclick="projectInfoDetail.showDetail('${subProjectMTaskPlanSub.taskPlan.objId}')">${subProjectMTaskPlanSub.taskPlan.taskCode}</a></td>
			<td class="center">${subProjectMTaskPlanSub.taskPlanSub.purchaseName}</td>
			<td class="center"><fmt:formatNumber value="${subProjectMTaskPlanSub.taskPlanSub.totalPrice}" type="currency"/></td>
		</tr>
		
	</c:forEach>
		
	</tbody>
    </table>
</div>   
</c:if>    
	<div class="operationLog">
	<h5 id="reportResults"><span class="switch  left11" style="float:left; width:80%">招标项目详细信息 </span>   
	<span class="modifyBtn">
		  [<a class="abtn" onclick="projectInfoDetail.update('${project.objId}');" href="#">修&nbsp;&nbsp;改</a>]
	</span>
	</h5></div>
		<input type="hidden" name="objId" value="${project.objId}"/>
    	<ul>
    		<li>
				<label  class="short">招标编号：</label>
				<span>${project.projCode}</span>
			</li>
			<li>
				<label  class="short">招标名称：</label>
				<span>${project.projName}</span>
			</li>
			<li>
				<label  class="short">代理机构：</label>
				<span>${project.agencies.orgName}</span>
			</li>
			<li>
				<label  class="short">招标单位：</label>
				<span>${project.buyersName}</span>
			</li>
    		
    		<li>
				<label  class="short">采购方式：</label>
				<span>${project.ebuyMethodCN}</span>
			</li>
			<li>
				<label  class="short">项目负责人</label>
				<span>${project.manager.name}</span>
			</li>
			<li>
				<label  class="short">项目类型：</label>
				<span>${project.tenderTypeCN}</span>
			</li>
			<li>
				<label  class="short">招标类型：</label>
				<span>${project.purCategoryNames}</span>
			</li>
			<li <c:if test="${projectRule.serviceFeePayType=='01' || empty projectRule.serviceFeePayType}">style="display:none;"</c:if>>
				<label class="short">固定金额(元)：</label>
				<div id="fixAmountDiv">
				<span>${projectRule.fixAmount}</span>
				</div>
			</li>	
			<c:if test="${subProjectLength==0}">
	    		<li>
					<label  class="short"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>价格（元）：</label>
					<span><fmt:formatNumber value="${project.purDocPrice}" type="currency"/></span>
				</li>
			</c:if>
    		<li class="short">
				<label  class="short"><dm:out value="local__OpenTendering__openBid_zh">评标</dm:out>地点：</label>
				<span>${bidRoom.bidRoomAddress}</span>
			</li>
			<c:if test="${subProjectLength==0}">
			<li class="short">
				<label  class="short"><dm:out  value="local__BAILRECORD" tenderType="${project.tenderType}">保证金</dm:out>：</label>
				<c:if test="${empty project.bailPercent}">
					<span><fmt:formatNumber value="${project.bail}" type="currency"/>万元</span>
				</c:if>
				<c:if test="${empty project.bail}">
					<span>${project.bailPercent}%</span>
				</c:if>
			</li>
			</c:if>
    		<li class="formTextarea" style="height:50px;">
				<label  class="short">招标内容：</label>
				<span>${project.content}</span>
			</li>
    	</ul>
	<div class="operationLog"><h5 id="reportResults"><span class="switch  left11">规则： </span></h5></div>
    	<ul>
    		<li>
				<label class="short" for="signUpSTime">报名开始时间：</label>
				<span>${fn:substring(projectRule.signUpSTime,0,16)}</span>
			</li>
			<li>
				<label class="short" for="signUpETime">报名结束时间：</label>
				<span>${fn:substring(projectRule.signUpETime,0,16)}</span>
			</li>
			<li>
				<label class="short" for="tenderStartTime">投标开始时间：</label>
				<span>${fn:substring(projectRule.submitStTime,0,16)}</span>
			</li>
			<li>
				<label class="short" for="tenderEndTime">投标结束时间：</label>
				<span>${fn:substring(projectRule.submitETime,0,16)}</span>
			</li>
			<li>
				<label class="short">开标时间：</label>
				<span>${fn:substring(projectRule.openBidSDate,0,16)}</span>
			</li>
			<li>
				<label class="short">评标开始时间：</label>
				<span>${fn:substring(projectRule.evalSTime,0,16)}</span>
			</li>
			<li >
				<label class="short">服务费支付方式：</label>
				<span>
				<c:choose>
					<c:when test="${projectRule.serviceFeePayType=='01'}">
						中标单位支付
					</c:when>
					<c:when test="${projectRule.serviceFeePayType=='02'}">
						业主单位支付
					</c:when>
				</c:choose>
				</span>
			</li>
			<!--
			 支付方式为业主单位支付时显示金额
			<c:if test="${projectRule.serviceFeePayType=='02'}">
			<li>
				<label class="short">金额：</label>
				<span> <fmt:formatNumber value="${projectRule.fixAmount}" type="currency"/></span>
			</li>
			</c:if>
			 -->
			<li>
				<label class="short">服务费计算方式：</label>
				<span  style="margin-top:3px;"><input type="radio"  value="01" checked="checked"<c:if test="${projectRule.serviceFeeCalculateType=='01'}">checked="checked"</c:if> id="fix_money" name="serviceFeeCalculateType" disabled="disabled"/></span>
				<span style="margin-top:0px">固定金额</span>
				<span  style="margin-top:3px;"><input type="radio" value="02" <c:if test="${projectRule.serviceFeeCalculateType=='02'}">checked="checked"</c:if> id="diffience" name="serviceFeeCalculateType" disabled="disabled"/></span>
				<span style="margin-top:0px">差额累计</span>
			</li>
			<li>
				<label class="short">是否匿名投标：</label>
				<span  style="margin-top:3px;"><input type="radio"  value="0" checked="checked"<c:if test="${projectRule.ruleAnonymous=='1'}">checked="checked"</c:if> id="ruleAnonymous0" name="ruleAnonymous" disabled="disabled"/></span>
				<span style="margin-top:0px">是</span>
				<span  style="margin-top:3px;"><input type="radio" value="1" <c:if test="${projectRule.ruleAnonymous=='0'}">checked="checked"</c:if> id="ruleAnonymous1" name="ruleAnonymous" disabled="disabled"/></span>
				<span style="margin-top:0px">否</span>
			</li>
    	</ul>
    	<c:if test="${subProjectLength!=0}">
    	<table class="tableList" id="SubProjectList">
		<div class="operationLog"><h5><span class="switch  left11">项目拆分信息：</span></h5></div>
  		<thead>
      		<tr class="center">
      			<th><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>编号</th>
          		<th><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>名称</th>
          		<th><dm:out value="local__BAILRECORD" tenderType="${project.tenderType}">保证金</dm:out>(元)</th>
          		<c:if test="${projProcessRule.resValue==true}">
	          		<th>标书费(元)</th>
          		</c:if>
     		</tr>
		</thead>
	<tbody>
		<c:forEach items="${subProjectList}" var="subProject" varStatus="i">
			<tr>
				<td>${subProject.projCode}</td>
				<td>${subProject.projName}</td>
				<td>
					<c:if test="${empty subProject.bailPercent}">
						<span><fmt:formatNumber value="${subProject.bail}" type="currency"/>元</span>
					</c:if>
					<c:if test="${empty subProject.bail}">
						<span><fmt:formatNumber value="${subProject.bailPercent}"/>%</span>
					</c:if>
				</td>
				<td><fmt:formatNumber value="${subProject.purDocPrice}" type="currency"/></td>
			</tr>
		</c:forEach>
	</tbody>
    </table>
    </c:if>
	</div>    
</div>
