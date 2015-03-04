<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<%@page import="com.gpcsoft.srplatform.auth.domain.User"%>
<%@page import="com.gpcsoft.plugin.acegi.AuthenticationHelper"%>
<style>a.abtn {text-decoration:underline}</style>
<script type="text/javascript" >
$(document).ready(function(){
	$('#epsTabs').tabs();
	$("a[name='tab']").click(function(){
		$('#projectStatus').val(this.id);
		$('#resProjectListDiv').loadPage($('#initPath').val()+'/view/es/planform/resproject/loadResProjectInfoListForAgenty.jsp?projectStatus='+this.id);
	});
	$('#search').click(function(){
		$('#resProjectListGrid').reload();
	});
	
	$('a[name="tab"]:first').click();
});
</script>
<div id="partContainers" class="partContainers" style="width: 100%">
<form id="resProjectSeachZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li class="fullLine">委托建设项目名称：<input type="text" id="projectName" name="projectName" value=""></li>
			<li class="operationBtnDiv">
				<button id="search" type="submit"><span><spring:message code="globe.query"/></span></button>
			</li>
		</ul>
	</div>
</form>
<div id="epsTabs">
	<ul>
	  	<li>
	      <a href="#resProjectListDiv" name="tab" id ="00" class="refreshData"><span>未开始</span></a>
	    </li>
	    <li>
	      <a href="#resProjectListDiv" name="tab" id ="01" class="refreshData"><span>进行中</span></a>
	    </li>
	    <li>
	      <a href="#resProjectListDiv" name="tab" id ="02" class="refreshData"><span>已结束</span></a>
	    </li>
	    <li>
	      <a href="#resProjectListDiv" name="tab" id ="all" class="refreshData"><span>全公司</span></a>
	    </li>
   </ul>
   <input type="hidden" id="projectStatus" value=""/>
	<div id="resProjectListDiv">
	</div>
</div>
</div>