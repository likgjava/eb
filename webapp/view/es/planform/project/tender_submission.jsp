<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/tender_submission.js"></script>
<div class="tabs">
		<div class="tabsHeader">
		<div class="tabsHeaderContent">
			<ul>
				<li class="selected"><a rel="worktask" target="subProjectTabDiv" href="newPage1.html"><span>待审核采购申报书</span></a></li>
				<li class=""><a rel="worktask1" target="subProjectTabDiv" href="newPage1.html"><span>待审核<dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></span></a></li>
				<li class=""><a rel="worktask2" target="subProjectTabDiv" href="newPage1.html"><span>待审核<dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span></a></li>
			</ul>
		</div>
		</div>
		<div style="height: 150px;" id="subProjectTabDiv" class="tabsContent">
		</div>

	</div>
<div class="formLayout">
	<h5 id="addGroup" style="margin-top:15px;margin-left:7px"><span>递交<dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>文件</span></h5>
	<form id="resultForm">
		<ul style="padding-top:15px">
			<li>
			<label><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>文件：</label>
			<input type="file" name="title" id="title">&nbsp;&nbsp;<button type="button" id="sure"><span>确定</span></button>
			</li>
		</ul>
	</form>
</div>

