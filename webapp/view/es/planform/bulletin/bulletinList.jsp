<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bulletin/bulletinList.js"></script>
</head>
<body>	  

	<form id="bulletinSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
								<li><label><spring:message code="bulletinForm.bullType"/></label>
										<select name="bullType">
											<option></option>
											<option value="00">采购预告</option>
											<option value="01"><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></option>
											<option value="02">变更公告</option>
											<option value="03">资格预审公告</option>
											<option value="04">资格预审<dm:out  value="local__PURCHASEPREVIEW" tenderType="${project.tenderType}">结果公示</dm:out></option>
											<option value="05"><dm:out  value="local__PURCHASEPREVIEW" tenderType="${project.tenderType}">结果公示</dm:out></option>
											<option value="06"><dm:out value="local__RESULTBULLETIN" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">中标公告</dm:out></option>
											<option value="07">暂停公告</option>
											<option value="08">失败公告</option>
										</select>
								</li>
								<li><label>采购方式:</label>
										<select name="buyMethod">
											<option></option>
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
		id="bulletinGrid" url="BulletinController.do?method=list" queryColumns=""  
			searchZone="bulletinSearchZone" rp="10" title="公告"  
			onSubmit="bulletinList.before" onSuccess="bulletinList.success">
					<flex:flexCol name="bullTitle" display="bulletinForm.bullTitle" sortable="true" width="100"align="left"></flex:flexCol>	
					<flex:flexCol name="bulletinNo" display="bulletinForm.bulletinNo" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="bullType" display="bulletinForm.bullType" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="buyMethod" display="bulletinForm.buyMethod" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="bullIssuedate" display="bulletinForm.bullIssuedate" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="bullValidDdte" display="bulletinForm.bullValidDdte" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="bullInvaliddate" display="bulletinForm.bullInvaliddate" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="bulletinList.add"></flex:flexBtn>	
		<flex:flexBtn name="globe.modify" bclass="add" onpress="bulletinList.update"></flex:flexBtn>
		<flex:flexBtn name="查看" bclass="add" onpress="bulletinList.look"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="bulletinList.remove"></flex:flexBtn>	
	</flex:flexgrid>
</body>
</html>
