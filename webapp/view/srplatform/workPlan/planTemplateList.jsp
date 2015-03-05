<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/workPlan/planTemplateList.js"></script>  

<form id="planTemplateSearchZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
				<li><label><spring:message code="planTemplateForm.name"/>：</label>
					<input type="text" name="name" id="templateName">
					<input type="hidden" name="name_op" value="like"/>
				</li>
				<li><label><spring:message code="planTemplateForm.org"/>：</label>
					<input type="text" name="org.name" id="orgName">
					<input type="hidden" name="org.name_op" value="like"/>
				</li>
				<li><label><spring:message code="planTemplateForm.type"/>：</label>
					<select name="type">
						<option value="">全部</option>
						<option value="00">公开招标</option>
						<option value="01">邀请招标</option>
						<option value="02">竞争性谈判</option>
						<option value="03">询价</option>
						<option value="04">单一来源</option>
					</select>
				</li>
			<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
		</ul>
	</div>
</form>

<flex:flexgrid checkbox="true"
	id="planTemplateGrid" url="PlanTemplateController.do?method=list" queryColumns=""  
		searchZone="planTemplateSearchZone" rp="10" title="计划模版" height="400" 
		onSubmit="planTemplateList.before">
				<flex:flexCol name="name" display="planTemplateForm.name" sortable="true" width="400"align="left"></flex:flexCol>
				<flex:flexCol name="code" display="编号" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="org.name" display="planTemplateForm.org" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="type" alias="typeCN" display="planTemplateForm.type" sortable="true" width="100"align="center"></flex:flexCol>
				<flex:flexCol name="rule" display="planTemplateForm.rule" sortable="true" width="180"align="left"></flex:flexCol>
				<flex:flexCol name="memo" display="planTemplateForm.memo" sortable="true" width="200"align="left"></flex:flexCol>
	<flex:flexBtn name="globe.new" bclass="add" onpress="planTemplateList.add"></flex:flexBtn>	
	<flex:flexBtn name="globe.modify" bclass="modify" onpress="planTemplateList.update"></flex:flexBtn>	
	<flex:flexBtn name="globe.delete" bclass="delete" onpress="planTemplateList.remove"></flex:flexBtn>
	<flex:flexBtn name="计划明细" bclass="import" onpress="planTemplateList.tasks"></flex:flexBtn>		
</flex:flexgrid>
