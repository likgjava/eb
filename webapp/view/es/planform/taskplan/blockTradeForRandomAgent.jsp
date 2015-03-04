<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/blockTradeForRandomAgent.js"></script>
<div class="formLayout form2Pa" id="randomAgent">
	<h5><span>随机抽取</span></h5>
		<ul>
  			<li class="fullLine">
  			<tr><td>
   				<input type="text" name="name" id="org_name" class="required" value="" size="50" readonly="readonly" disabled="disabled"/>
   				<input type="button" name="randomButton" id="randomButton" value="抽 取" />
   				<input type="hidden" name="agentObjId" id="org_id" value=""/>
   			</td>
   			</tr>
   			</li>
		</ul>
		<ul>
  			<li class="fullLine">
  			<tr><td>
   				<input type="text" name="name" id="org_name" class="required" value="" size="50" readonly="readonly" disabled="disabled"/>
   				<input type="button" name="randomButton" id="randomButton" value="抽 取" />
   				<input type="hidden" name="agentObjId" id="org_id" value=""/>
   			</td>
   			</tr>
   			</li>
		</ul>
		<ul>
  			<li class="fullLine">
  			<tr><td>
   				<input type="text" name="name" id="org_name" class="required" value="" size="50" readonly="readonly" disabled="disabled"/>
   				<input type="button" name="randomButton" id="randomButton" value="抽 取" />
   				<input type="hidden" name="agentObjId" id="org_id" value=""/>
   			</td>
   			</tr>
   			</li>
		</ul>
		<ul>
  			<li class="fullLine">
  			<tr><td>
   				<input type="text" name="name" id="org_name" class="required" value="" size="50" readonly="readonly" disabled="disabled"/>
   				<input type="button" name="randomButton" id="randomButton" value="抽 取" />
   				<input type="hidden" name="agentObjId" id="org_id" value=""/>
   			</td>
   			</tr>
   			</li>
		</ul>
		<ul>
  			<li class="fullLine">
  			<tr><td>
   				<input type="text" name="name" id="org_name" class="required" value="" size="50" readonly="readonly" disabled="disabled"/>
   				<input type="button" name="randomButton" id="randomButton" value="抽 取" />
   				<input type="hidden" name="agentObjId" id="org_id" value=""/>
   			</td>
   			</tr>
   			</li>
		</ul>
		<form id="subprojectForm" method="post">
		<div class="conOperation" id="reminder">
			<!--<input type="text" id="reminderinput" name="name" value="" style=""/>
		--></div>
			<input type="hidden" id="reminderid" name="objId" value=""/>
		</form>

   			<div class="conOperation">					
				<button id="subProjectSure" type="button" tabindex="19"><span>确认</span></button>
				<button id="subProjectReset"  type="button"  tabindex="19"><span>重新抽取</span></button>
				<button id="subProjectReturn" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
 			</div>
</div>