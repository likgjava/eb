<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div id="printingId" class="frameReport">
   	<div class="reportTitle">
     		<h2>${project.projName }</h2>
    		<span class="projectNumber">招标编号：${project.projCode }</span>
    		<span class="publishTime">发布时间：<fmt:formatDate value="${project.createTime }" pattern="yyyy-MM-dd HH:mm"/></span>
    	</div>
	<div class="abstract">
		<p><span>报名时间：<fmt:formatDate value="${projectRule.signUpSTime }" pattern="yyyy-MM-dd HH:mm"/> 至 <fmt:formatDate value="${projectRule.signUpETime }" pattern="yyyy-MM-dd HH:mm"/></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>时间：<fmt:formatDate value="${projectRule.submitStTime }" pattern="yyyy-MM-dd HH:mm"/> 至 <fmt:formatDate value="${projectRule.submitETime }" pattern="yyyy-MM-dd HH:mm"/></span>
		</p>
       	<p></p>
   	</div>
	<p>本项目为<span class="em">${project.tenderTypeCN}</span>项目(采取<span class="em highlight">${project.ebuyMethodCN}</span>采购方式)，项目<dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">采购公告</dm:out>刊登在门户网站上，截止至<span class="em"><fmt:formatDate value="${project.signUpETime }" pattern="yyyy-MM-dd HH:mm"/></span> ，共有<span class="em">${project.projectCountView.signUpC }</span>家公司购买了采购文件。评审程序是严格按照政府采购相关法律法规来进行的，评审办法及评分细则是依照该项目采购文件中相关规定制定的。</p>
</div>
<script>
$(document).ready(function(){

})
</script>