<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/general_view_agent.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/toUpdateProjImplStatus.js"></script> 
<div style="margin-bottom: 5px;" class="tabs">
	<div class="tabsHeader">
		<div id="agentDoDiv" class="tabsHeaderContent">
			<ul>
				<li id="tab9" class="selected"><a href="#" onClick="general_agent.viewProjImplStatus('${project.objId }');"><span>项目实施</span></a></li>	
				<li id="tab6" class=""><a href="#" onClick="general_agent.viewProjectRule('${project.objId }');"><span>项目规则</span></a></li>
				<li id="tab7" class=""><a href="#" onClick="general_agent.viewBidRoom('${project.objId }');"><span>预定评标室</span></a></li>
			    <li id="tab0" class=""><a href="#" id="subProject" onClick="general_agent.viewSubProject('${project.objId }');"><span>项目拆分</span></a></li>
				<!-- <li id="tab1" class=""><a href="#" onClick="general_agent.viewPurBulletin('${project.objId }');"><span>招标公告</span></a></li>-->
				<li id="tab2" class=""><a href="#" id="generalAgent" onClick="general_agent.viewSignupRecord('${project.objId }');"><span>参与投标单位</span></a></li>
				<li id="tab3" class=""><a href="#" onClick="general_agent.viewOppu();";><span>投标单位质疑</span></a></li>
				<li id="tab4" class=""><a href="#" onClick="general_agent.openBidGroup('${project.objId }');";><span><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>小组</span></a></li>
				<li id="tab5" class=""><a href="#" onClick="general_agent.evaluateBidGroup('${project.objId }');";><span>组建评标委员会</span></a></li>
		   <!-- <li id="tab8" class=""><a href="#" onClick="general_agent.viewProTime('${project.objId }');";><span>项目时间维护</span></a></li> -->	
			</ul>
		</div>
	</div>
	<div id="agentTabDiv" class="tabsContent">
	</div>
</div>