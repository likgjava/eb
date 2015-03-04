<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/buyresult/buyResultForm.js"></script>
<div class="partContainers">
<div class="formLayout form2Pa">        
	<form id="buyResultForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="buyResultId" id="buyResultId" value="${param.objId}"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="projectId" id="projectId" value="${param.projectId}">
		<input type="hidden" name="project.objId" id="project.objId" value="${param.projectId}">
		<h5><span>成交结果</span></h5>
     		<ul>
					<li>
						<label for="ebuyMethod"><spring:message code="buyResultForm.ebuyMethod"/>：</label>
							<select name="ebuyMethod" id="ebuyMethod" class="required">
								<option></option>
								<option value="00">公开招标</option>
								<option value="01">邀请招标</option>
								<option value="02">竞争性谈判</option>
								<option value="03">询价</option>
								<option value="04">单一来源</option>
							</select>
						<span class="eleRequired">*</span> 	  		 	 	  
					</li>

					<li>
						<label for="buyrResult"><spring:message code="buyResultForm.buyrResult"/>：</label>
						<select name="buyrResult" id="buyrResult" class="required">
							<option value=""></option>
							<option value="00">无人参与</option>
							<option value="01">没有足够参与者</option>
							<option value="02">选定</option>
							<option value="03">放弃</option>
						</select>
						<span class="eleRequired">*</span>
					</li>
		</ul>
		<div class="conOperation">
			<button id="buyResultSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="buyResultReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>
</div>
</div>
<div id="buyWinnerListView"> </div>