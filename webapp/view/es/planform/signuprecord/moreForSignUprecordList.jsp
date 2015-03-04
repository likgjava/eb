<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/signuprecord/moreForSignUprecordList.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<!-- 查询条件 -->
<div class="conSearch">
    <h4><span><spring:message code="globe.query"/></span></h4>
    <form id="contractSearchZone">
	    <ul>
	      <li>
	        <label>招标编号：</label>
			<input name="projCode" type="text" >	
	      </li>
	      <li>
	        <label>招标名称：</label>
		  	<input name="projName" type="text" >	
	      </li>
	      <li class="operationBtnDiv right">
	       	<button type="submit" id="query"><span><spring:message code="globe.query"/></span></button>
	      	<input type="hidden" name="auditStatus" id="paramId" value="${auditStatus}"/>
	      </li>
	    </ul>
    </form>
</div>

  <flex:flexgrid checkbox="false"
		id="signUprecordG" url="SignUprecordController.do?method=searchSignUprecordByQueryObject&bailRecord=isPayFor" queryColumns=""
			searchZone="contractSearchZone" rp="10"  title="报名信息列表"  onSuccess="signUprecordList.success" onSubmit="signUprecordList.before" minGridHeight="305" height="305">
			<flex:flexCol name="project.projCode" display="招标编号" sortable="true" width="140" align="center"></flex:flexCol>
			<flex:flexCol name="project.projName"  display="招标名称" sortable="true" width="150" align="left"></flex:flexCol>
			<flex:flexCol name="supplier.orgName" display="投标单位名称" sortable="true" width="200" align="left"></flex:flexCol>
			<flex:flexCol name="applyDate"  display="报名时间" format="date" sortable="true" width="100" align="center"></flex:flexCol>
	</flex:flexgrid>