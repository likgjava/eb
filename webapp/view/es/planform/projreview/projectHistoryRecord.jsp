<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style  type="text/css">
.spanFont{font: 0.9em/0.9em;font-size: 12;margin-left: 2px;}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/projectHistoryRecord.js"></script>
<input type="hidden" value="${project.objId }" id="projectId" name="projectId"/>
<input type="hidden" value="${project.ebuyMethodCN }" id="ebuyMethodCN"/>
<input type="hidden" value="${project.ebuyMethod}" name="ebuyMethod" id="ebuyMethod"/>
<div id="project" class="partContainers">
	<div class="formLayout form2Pa">
		<h5><span>项目信息</span></h5>
		<ul>
			<li>
				<label class="short" ><spring:message code="projectForm.projCode"/>：</label>
				${project.projCode}
			</li>
		</ul>
		<ul>
			<li>
				<label class="short" ><spring:message code="projectForm.projName"/>：</label>
				${project.projName}
			</li>
		</ul>
		<ul>
			<li>
				<label class="short" >项目状态：</label>
				${empty project.projProcessStatusCN ? "未知" : project.projProcessStatusCN}
			</li>
		</ul>
			<ul>
			<li>
				<label class="short" >备案状态：</label>
				<c:choose>
				<c:when test="${project.tenderRecord=='01'}">已备案
				<!--		<button id="dRecordProject" ><span>撤销备案</span></button> -->
				</c:when>
				<c:otherwise>未备案
				<authz:authorize ifAnyGranted="ProjectController.do?method=saveRecordProject">
				<button id="recordProject" ><span>
				备案</span>
				</button>
				</authz:authorize>
				</c:otherwise>
				</c:choose>
			</li>
		</ul>
	</div>
	<div style="margin-bottom: 5px;" class="tabs">		
	<div id="projectInfoTabs" class="tabsHeader">
		<ul>
		  	<li><a href="#" id="tabform2"><span><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span></a></li>
	    	<li class="hidden"><a href="#" id="tabform10"><span><dm:out value="local__OpenTendering__clarificationDoc_zh">澄清文件</dm:out></span></a></li>	    	
	    	<c:if test="${project.ebuyMethod!='01'&&project.ebuyMethod!='04'}"><li><a href="#tabform" id="tabform1"><span><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></span></a></li></c:if>
	    	<li><a href="#" id="tabform6"><span><dm:out value="local__RESULTBULLETIN" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">中标公告</dm:out></span></a></li>
	    	<li><a href="#" id="tabform7"><span><dm:out value="local__OpenTendering__notice_zh">通知书</dm:out></span></a></li>
	    	<li><a href="#" id="tabform9"><span>合          同</span></a></li>
	    	<li><a href="#" id="tabform8"><span>答          疑</span></a></li>
	   </ul>
	</div>
	  <div  id="tabform"> </div>
	</div>
	
</div>