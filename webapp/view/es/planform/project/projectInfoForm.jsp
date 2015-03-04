<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/projectInfoForm.js"></script>
<div class="partContainers">
<div class="formLayout form2Pa">
<div class="operationLog">
<input type="hidden" name="projectType" id="projectType" value="${project.tenderType}"/>
<h5 ><span class="switch  left11">申报书条目信息 </span></h5></div>
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
			<td class="center"><a href="#" onclick="projectInfoForm.showDetail('${subProjectMTaskPlanSub.taskPlan.objId}')">${subProjectMTaskPlanSub.taskPlan.taskCode}</a></td>
			<td class="center">${subProjectMTaskPlanSub.taskPlanSub.purchaseName}</td>
			<td class="center" ><fmt:formatNumber value="${subProjectMTaskPlanSub.taskPlanSub.totalPrice}" type="currency"/></td>
			<td class="hidden" id="totalPriceId">${subProjectMTaskPlanSub.taskPlanSub.totalPrice}</td>
		</tr>
	</c:forEach>
		
	</tbody>
    </table>
</div>
	<div class="operationLog"><h5 id="reportResults"><span class="switch  left11">招标项目详细信息： </span></h5></div>
	<form id="tenderForm">
		<input type="hidden" name="objId" value="${project.objId}"/>
    	<ul>
    		<li>
				<label  class="short">招标编号：</label>
				<span>${project.projCode}</span>
				<input type="hidden" name="projCode" id="projCode" value="${project.projCode}"/>
			</li>
    		<li>
				<label  class="short">招标名称：</label>
				<input type="text" name="projName"  id="projName" value="${project.projName}" class="required"/><span class="eleRequired">*</span>
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
				<label  class="short">项目负责人：</label>
				<span>${project.manager.name}</span>
			</li>
			<li>
				<label class="short">项目类型：</label>
				<span>${project.tenderTypeCN}</span>
			</li>
			<li>
				<label class="short">招标类型：</label>
				<span>${project.purCategoryNames}</span>
			</li>
			<li id="fixAmountDiv" <c:if test="${projectRule.serviceFeePayType=='01' || empty projectRule.serviceFeePayType}">style="display:none;"</c:if>>
				<label class="short">固定金额(元)：</label>
				<input name="fixAmount" id="fixAmount" value="${projectRule.fixAmount}"/>
			</li>
			<!-- 如果为分包，标书费按照项目收取，否则按照包收取 -->
    		<li>
				<label  class="short"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>价格（元）：</label>
				<input type="text" name="purDocPrice" maxlength="15" id="purDocPrice" class="required money" value="${project.purDocPrice}" onkeyup="projectInfoForm.insertMoney('purDocPrice')" /><span class="eleRequired">*</span><span id="purDocPriceview"></span>
			</li>
    		<li class="short">
				<label  class="short">评标地点：</label>
				<input type="text" name="openBidAddr"  maxlength="100"  id="openBidAddr" value="${bidRoom.bidRoomAddress}" size="44"/>
			</li>
			
			<c:if test="${subProjectLength ==0}">
				<li>
					<label class="short"><dm:out  value="local__BAILRECORD" tenderType="${project.tenderType}">保证金</dm:out>：</label>
					
					<input type="radio" name="feeType" id="amount" <c:if test="${empty project.bailPercent}">checked="checked"</c:if>/>
					<input type="text" name="bail"  id="bail" maxlength="10" class="required money" value="${project.bail}" style="width:60px" onkeyup="projectInfoForm.insertMoney('bail')"/>万元
					
					<input type="radio" name="feeType" id="percent" <c:if test="${empty project.bail}">checked="checked"</c:if>>
					<input type="text" name="bailPercent"  id="bailPercent" maxlength="2" class="digits" value="${project.bailPercent}" disabled="disabled" style="width:60px">%<span id="bailview"></span><span class="eleRequired">*</span>
				</li>
			</c:if>
			
    		<li class="formTextarea" style="height:50px;">
				<label  class="short">招标内容：</label>
				<textarea name="content" style="height:40px;" class="required" maxlength="2000" class="maxInput">${project.content}</textarea><span class="eleRequired">*</span>
			</li>
    	</ul>
	<div class="operationLog"><h5 id="reportResults"><span class="switch  left11">规则： </span></h5></div>
    	<ul>
    		<li>
				<label class="short" for="signUpSTime">报名开始时间：</label>
				<input type="text" name="signUpSTime" id="signUpSTime" class="required" style="width: 150px" readonly="readonly" value="<fmt:formatDate value="${projectRule.signUpSTime}" pattern="yyyy-MM-dd HH:mm:SS"/>" />
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short" for="signUpETime">报名结束时间：</label>
				<input type="text" name="signUpETime" id="signUpETime" class="required" style="width: 150px"	readonly="readonly" value="<fmt:formatDate value="${projectRule.signUpETime}" pattern="yyyy-MM-dd HH:mm:SS"/>" />
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short" for="tenderStartTime">投标开始时间：</label>
				<input type="text" name="tenderStartTime" id="tenderStartTime" class="required" style="width: 150px" readonly="readonly" value="<fmt:formatDate value="${projectRule.submitStTime}" pattern="yyyy-MM-dd HH:mm:SS"/>" />
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short" for="tenderEndTime">投标结束时间：</label>
				<input type="text"  name="tenderEndTime" id="tenderEndTime" readonly="readonly" style="width: 150px" class="required" value="<fmt:formatDate value="${projectRule.submitETime}" pattern="yyyy-MM-dd HH:mm:SS"/>"/>
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short">开标时间：</label>
				<input type="text" name="openBidStartDate" id="openBidStartDate" style="width: 150px" readonly="readonly" class="required" value="<fmt:formatDate value="${projectRule.openBidSDate}" pattern="yyyy-MM-dd HH:mm:SS"/>"/>
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short">评标开始时间：</label>
				<input type="text" name="evalStartTime" id="evalStartTime" style="width: 150px" readonly="readonly" class="required" value="<fmt:formatDate value="${projectRule.evalSTime}" pattern="yyyy-MM-dd HH:mm:SS"/>"/>
				<span class="eleRequired">*</span>
			</li>
			<li >
				<label class="short">服务费支付方式：</label>
				<select name="serviceFeePayType" id="serviceFeePayType" onchange="inputFixAmount();">
					<c:choose>
					<c:when test="${projectRule.serviceFeePayType=='01'}">
						<option value="01" selected="selected">中标单位支付</option>
						<option value="02">业主单位支付</option>
					</c:when>
					<c:when test="${projectRule.serviceFeePayType=='02'}">
						<option value="01">中标单位支付</option>
						<option value="02" selected="selected">业主单位支付</option>
					</c:when>
					<c:otherwise>
						<option value="01">中标单位支付</option>
						<option value="02">业主单位支付</option>
					</c:otherwise>
					</c:choose>
				</select>
			</li>
			<li>
				<label class="short">服务费计算方式：</label>
				<span  style="margin-top:3px;"><input type="radio"  value="01" checked="checked"<c:if test="${projectRule.serviceFeeCalculateType=='01'}">checked="checked"</c:if> id="fix_money" name="serviceFeeCalculateType"/></span>
				<span style="margin-top:0px">固定金额</span>
				<span  style="margin-top:3px;"><input type="radio" value="02" <c:if test="${projectRule.serviceFeeCalculateType=='02'}">checked="checked"</c:if> id="diffience" name="serviceFeeCalculateType"/></span>
				<span style="margin-top:0px">差额累计</span>
			</li>
			<li>
				<!--屏蔽匿名投标功能
				<label class="short">是否匿名投标：</label>
				<span  style="margin-top:3px;"><input type="radio"  value="1" checked="checked"<c:if test="${projectRule.ruleAnonymous=='1'}">checked="checked"</c:if> id="ruleAnonymous0" name="ruleAnonymous"/></span>
				<span style="margin-top:0px">是</span>
				<span  style="margin-top:3px;"><input type="radio" value="0" <c:if test="${projectRule.ruleAnonymous=='0'}">checked="checked"</c:if> id="ruleAnonymous1" name="ruleAnonymous"/></span>
				<span style="margin-top:0px">否</span>
				-->
				<input type="hidden" value="0" id="ruleAnonymous1" name="ruleAnonymous"/>
			</li>
    	</ul>
	</form>
	
	<form id="subProjectForm">
	<c:if test="${subProjectLength!=0}">
    	<table class="tableList" id="SubProjectList">
		<div class="operationLog"><h5><span class="switch  left11">项目拆分信息：</span></h5></div>
  		<thead>
      		<tr class="center">
      			<th><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>编号</th>
          		<th><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>名称</th>
          		<th><dm:out  value="local__BAILRECORD" tenderType="${project.tenderType}">保证金</dm:out>(元)</th>
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
					<input type="radio" name="feeType_${i.count}" id="amount_${i.count}" checked="checked"/>
					<input type="text" name="subProject[${i.count}].bail" style="width:50px;" value="${subProject.bail}" class="money" maxlength="10" onkeyup="projectInfoForm.insertMoney('subProject${i.count}')" id="bail_${i.count}"/>
					(元)
					<input type="radio" name="feeType_${i.count}" id="percent_${i.count}">
					<input type="text" name="subProject[${i.count}].bailPercent" style="width:50px;" maxlength="2" value="${subProject.bailPercent}" class="digits" maxlength="15" onkeyup="projectInfoForm.insertMoney('subProject${i.count}')" id="bailPercent_${i.count}"/>
					%
					<span id="subProject${i.count}view"></span>
					<span></span>
					<input type="hidden" name="subProject[${i.count}].objId" value="${subProject.objId}"/>
				</td>
				<c:if test="${projProcessRule.resValue==true}">
					<td>
						<input type="text" name="subProject[${i.count}].purDocPrice"  value="${subProject.purDocPrice}" class="required money" maxlength="15" onkeyup="projectInfoForm.insertMoney('subProject1${i.count}')" id="subProject1${i.count}"/>
						<span id="subProject1${i.count}view"></span>
						<span class="eleRequired">*</span>
					</td>
          		</c:if>
			</tr>
		</c:forEach>
	</tbody>
    </table>
    </c:if>
    <div class="conOperation">
		<input type="hidden" id="subProjectLength"  value="${subProjectLength}"/>
		<button id="projectSave" type="button" tabindex="18" ><span>提交</span></button>
	</div>
	</form>
	</div>    
</div>
