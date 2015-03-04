<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bulletin/relBulletinListForSupervise.js"></script>
<!-- 查询条件 -->
<div class="conSearch">
    <h4><span><spring:message code="globe.query"/></span></h4>
    <form id="bulletinSearchZone">
	    <ul>
	      <li>
	        <label><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out>编号：</label>
			<input name="bulletinNo" type="text" >	
	      </li>
	      <li>
	        <label><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out>标题：</label>
		  	<input name="bullTitle" type="text" >	
	      </li>
	      <li>
	  		<label ><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out>类型：</label>
	  		<select name="bullTypes">
	  		<option value="">---请选择---</option>
	  		<option value="01"><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></option>
	  		<option value="06"><dm:out value="local__RESULTBULLETIN" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">中标公告</dm:out></option>
	  		</select>
		  </li>
	      <li class="operationBtnDiv right">
	       	<button type="submit" id="query"><span><spring:message code="globe.query"/></span></button>
	      </li>
	    </ul>
    </form>
</div>

<!-- Tab页 -->
<div id="epsTabs" class="">
  <ul>
    <li>
      <a href="#bulletinInfo" id = "tabs_wait" class="refreshData"><span>待发送</span></a>
    </li>
    <li>
      <a href="#bulletinInfo" id = "tabs_pass" class="refreshData"><span>发送成功</span></a>
    </li>
    <li>
      <a href="#bulletinInfo" id = "tabs_notpass" class="refreshData"><span>发送失败</span></a>
    </li>
  </ul>
  <div id="bulletinInfo">
    <flex:flexgrid checkbox="true"
		id="BulletinGrid" url="BulletinController.do?method=getBulletinListForRel" queryColumns=""
			searchZone="bulletinSearchZone" rp="10"  title="公告列表" height="235"
			onSubmit="BulletinList.before" onSuccess="BulletinList.success">
			<flex:flexCol name="bulletinNo" display="公告编号" sortable="true" width="80" align="center"></flex:flexCol>
			<flex:flexCol name="bullTitle"  display="公告标题" sortable="true" width="150" align="left"></flex:flexCol>
			<flex:flexCol name="project.projName" display="招标名称" sortable="true" width="200" align="left"></flex:flexCol>
			<flex:flexCol name="project.projCode" display="招标编号" sortable="true" width="200" align="center"></flex:flexCol>
			<flex:flexCol name="bullType" alias="bullTypeCN" display="公告类型" sortable="true" width="100" align="center"></flex:flexCol>
			<flex:flexBtn name="批量发送" bclass="audit" onpress="BulletinList.batchRel"></flex:flexBtn>	
	</flex:flexgrid>
  </div>
</div>
        
        

 
