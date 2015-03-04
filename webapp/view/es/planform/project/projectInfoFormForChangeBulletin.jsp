<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/projectInfoFormForChangeBulletin.js"></script>
<div class="partContainers">
<div class="formLayout form2Pa">        
	<div class="operationLog"><h5 id="reportResults"><span class="switch  left11">项目基本信息：</span></h5></div>
    	<ul>
    		<li>
				<label  class="short">采购招标编号：</label>
				<span>${project.projCode}</span>
			</li>
    		<li>
				<label  class="short">项目采购方式：</label>
				<span>${project.ebuyMethodCN}</span>
			</li>
    		<li>
				<label  class="short">采购招标名称：</label>
				<span>${project.projName}</span>
			</li>
    		<li>
				<label  class="short">负责人联系邮件：</label>
				<span>${project.manager.email}</span>
			</li>
    		<li>
				<label  class="short">项目负责人：</label>
				<span>${project.manager.name}</span>
			</li>
    		<li>
				<label  class="short">负责人联系电话：</label>
				<span>${project.manager.telOffice}</span>
			</li>
    		<li>
				<label  class="short">采购项目内容：</label>
				<span>${project.content}</span>
			</li>
    	</ul>

	<div class="operationLog"><h5 id="reportResults"><span class="switch  left11">项目详细信息： </span></h5></div>
	<form id="tenderForm">
		<input type="hidden" name="objId" value="${project.objId}"/>
    	<ul>
    		<li>
				<label  class="short">招标编号：</label>
				<span>${project.projCode}</span>
			</li>
    		<li>
				<label  class="short">招标名称：</label>
				<input type="text" name="projName"  id="projName" value="${project.projName}" class="required"/><span class="eleRequired">*</span>
			</li>
    		<li>
				<label  class="short">采购方式：</label>
				<span>${project.ebuyMethodCN}</span>
			</li>
    		<li>
				<label  class="short"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>价格：</label>
				<input type="text" name="purDocPrice"  id="purDocPrice" class="money" value="${project.purDocPrice}"/>
			</li>
    		<li class="fullLine">
				<label  class="short"><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>地点：</label>
				<input type="text" name="openBidAddr"  id="openBidAddr" value="${projectRule.openBidAddr}" size="44"/>
			</li>
    		<li class="formTextarea" style="height:50px;">
				<label  class="short">招标内容：</label>
				<textarea name="content" style="height:40px;" class="required" maxlength="2000" class="maxInput">${project.content}</textarea><span class="eleRequired">*</span>
			</li>
    	</ul>
	<div class="operationLog"><h5 id="reportResults"><span class="switch  left11">时间规则： </span></h5></div>
    	<ul>
    		<li>
				<label class="short" for="signUpSTime">报名开始时间：</label>
				<input type="text" name="signUpSTime" id="signUpSTime" class="required"  value="<fmt:formatDate value="${projectRule.signUpSTime}" pattern="yyyy-MM-dd HH:mm:SS"/>" />
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short" for="signUpETime">报名结束时间：</label>
				<input type="text" name="signUpETime" id="signUpETime" class="required"	 value="<fmt:formatDate value="${projectRule.signUpETime}" pattern="yyyy-MM-dd HH:mm:SS"/>" />
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short" for="tenderStartTime"><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>开始时间：</label>
				<input type="text" name="tenderStartTime" id="tenderStartTime" class="required"  value="<fmt:formatDate value="${projectRule.submitStTime}" pattern="yyyy-MM-dd HH:mm:SS"/>" />
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short" for="tenderEndTime"><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>结束时间：</label>
				<input type="text" onPropertychange="projectInfoForm.timeChange();" name="tenderEndTime" id="tenderEndTime"  class="required" value="<fmt:formatDate value="${projectRule.submitETime}" pattern="yyyy-MM-dd HH:mm:SS"/>"/>
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short"><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>时间：</label>
				<input type="text" name="openBidStartDate" id="openBidStartDate"  class="required" value="<fmt:formatDate value="${projectRule.openBidSDate}" pattern="yyyy-MM-dd HH:mm:SS"/>"/>
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short">评标开始时间：</label>
				<input type="text" name="evalStartTime" id="evalStartTime"  class="required" value="<fmt:formatDate value="${projectRule.evalSTime}" pattern="yyyy-MM-dd HH:mm:SS"/>"/>
				<span class="eleRequired">*</span>
			</li>
    	</ul>
	</form>
	<form id="subProjectForm">
    	<table class="tableList" id="SubProjectList">
		<caption>项目拆分信息：</caption>
  		<thead>
      		<tr class="center">
      			<th><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>编号</th>
          		<th><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>名称</th>
          		<th><dm:out  value="local__BAILRECORD" tenderType="${project.tenderType}">保证金</dm:out>(元)</th>
     		</tr>
		</thead>
	<tbody>
		<c:forEach items="${subProjectList}" var="subProject" varStatus="i">
			<tr>
				<td>${subProject.projCode}</td>
				<td>${subProject.projName}</td>
				<td>
				<input type="text" name="subProject[${i.count}].bail"  value="${subProject.bail}" class="money"/>
				<input type="hidden" name="subProject[${i.count}].objId" value="${subProject.objId}"/>
				</td>
			</tr>
		</c:forEach>
	</tbody>
    </table>
    <div class="conOperation">
		<button id="projectSave" type="button" tabindex="18" ><span>保存并进行下一步</span></button>
		<button id="returnback" type="button" tabindex="18" ><span>返回</span></button>
	</div>
	</form>
	</div>    
</div>
