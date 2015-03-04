<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/project_supplier.js"></script>
<!-- 项目 全局使用 end-->
<input type="hidden" name="projectId" id="projectId" value="${project.objId}">
<div id="projectTop" class="accountInfo" >
    <input type="hidden" id="ebuyMethod" name="ebuyMethod" value="${project.ebuyMethod}">
    
	<div style=" width:100%; height:60px; padding-bottom:5px;padding-top:5px;" class="accountInfo">
		<div>
	<p><span>[${project.projCode }]${project.projName }</span></p><br/>
	<p >
		<b>采购方式：</b>${project.ebuyMethodCN } |
		<b>监管：</b>${project.monitor.name } |
		<b>负责人：</b><a title="${project.agencies.orgName }"> ${project.manager.name }</a>|
		<b>项目状态：</b>${project.projProcessStatusCN} 
	</p>
	</div>
	<br/>
	<div>
		<p>报名时间：<fmt:formatDate value="${projectRule.signUpSTime}" pattern="yyyy-MM-dd HH:mm"/> 至 <fmt:formatDate value="${projectRule.signUpETime}" pattern="yyyy-MM-dd HH:mm"/>
		<dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>时间：<fmt:formatDate value="${projectRule.submitStTime}" pattern="yyyy-MM-dd HH:mm"/> 至 <fmt:formatDate value="${projectRule.submitETime}" pattern="yyyy-MM-dd HH:mm"/></p>
	</div>
	</div>
</div>
<div style="height:15px;"></div>
<c:if test="${subProjectListSize!=0}">
<div style="padding-top:4px;background-color:#F2F9FF;height:20px;">
<p><b>&nbsp;&nbsp;&nbsp;<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>信息：</b><c:forEach items="${subProjectList}" var="subProject" >[<a href="#" onClick="viewSubProject('${subProject.objId}');">${subProject.projName}</a>]&nbsp;&nbsp;&nbsp;</c:forEach></p>
</div>
</c:if>
<div>
	<div id="tabInfo">
	    <ul>
	    	<li>
	    	<c:if test="${project.ebuyMethod=='01'||project.ebuyMethod=='04'}"><a href="#tabform" id="tabform12"><span>邀请函</span></a></c:if>
	    	<c:if test="${project.ebuyMethod!='01'&&project.ebuyMethod!='04'}"><a href="#tabform" id="tabform1"><span><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></span></a></c:if>
	    	</li>
	    	<li><a href="#tabform" id="tabform2"><span><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span></a></li>
	    	<li><a href="#tabform" id="tabform4"><span><dm:out value="local__signup" tenderType="${project.tenderType}">报名信息</dm:out></span></a></li>
	    	<li><a href="#tabform" id="tabform16"><span>录入保证金</span></a></li>
	    	<li><a href="#tabform" id="tabform15"><span>录入标书费</span></a></li>
	    	<li><a href="#tabform" id="tabform5"><span>投标信息</span></a></li>
	    	<li><a href="#tabform" id="tabform6"><span><dm:out value="local__RESULTBULLETIN" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">中标公告</dm:out></span></a></li>
	    	<li class="hidden"><a href="#tabform" id="tabform11"><span>更正公告</span></a></li>
	    	<li><a href="#tabform" id="tabform7"><span><dm:out value="local__OpenTendering__notice_zh">通知书</dm:out></span></a></li>
	    	<li><a href="#tabform" id="tabform9"><span>合          同</span></a></li>
	    	<li><a href="#tabform" id="tabform8"><span>质          疑</span></a></li>
	    	<li><a href="#tabform" id="tabform14"><span>项目补充公告</span></a></li>
	    </ul>
	    <div id="tabform">
	    投标单位界面
   		</div>
	</div>
</div>
<script language="javascript">

function viewSubProject(id){
	$.epsDialog({
        title:"查看包组",
        url:$("#initPath").val()+"/ProjectController.do?method=getSubProjectForSupplier&projectId="+id,
        width: 600,
        height: 400,
        isReload: false,
        onClose: function(){
       	}
	});
}

</script>