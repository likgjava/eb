<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/noticemanage/noticeList.js"></script>
</head>
<body>	  

	<form id="noticeSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
								<li><label><spring:message code="noticeForm.noticeType"/></label>
									<select name="noticeType" id="noticeType" class="required">
										<option></option>
										<option value="00">成交<dm:out value="local__OpenTendering__notice_zh">通知书</dm:out></option>
										<option value="01">结果<dm:out value="local__OpenTendering__notice_zh">通知书</dm:out></option>
									</select>
								</li>
								<li><label><spring:message code="noticeForm.noticeTitle"/></label>
									<input type="text" name="noticeTitle"  >
									<input type="hidden" name="noticeTitle_op" value="like"/>
								</li>		
				<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>

	<flex:flexgrid checkbox="true"
		id="noticeGrid" url="NoticeController.do?method=list" queryColumns=""  
			searchZone="noticeSearchZone" rp="10" title="通知书"  
			onSubmit="noticeList.before" onSuccess="noticeList.success">
					<flex:flexCol name="noticeTitle" display="noticeForm.noticeTitle" sortable="true" width="150"align="left"></flex:flexCol>
					<flex:flexCol name="noticeType" display="noticeForm.noticeType" sortable="true" width="120"align="left"></flex:flexCol>
					<flex:flexCol name="projName" display="noticeForm.projName" sortable="true" width="150"align="left"></flex:flexCol>
					<flex:flexCol name="projCode" display="noticeForm.projCode" sortable="true" width="120"align="left"></flex:flexCol>
					<flex:flexCol name="subProjName" display="noticeForm.subProjName" sortable="true" width="150"align="left"></flex:flexCol>
					<flex:flexCol name="subProjCode" display="noticeForm.subProjCode" sortable="true" width="120"align="left"></flex:flexCol>
					<flex:flexCol name="sellerName" display="noticeForm.sellerName" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="noticeList.add"></flex:flexBtn>	
		<flex:flexBtn name="globe.modify" bclass="add" onpress="noticeList.update"></flex:flexBtn>
		<flex:flexBtn name="查看" bclass="add" onpress="noticeList.look"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="noticeList.remove"></flex:flexBtn>	
	</flex:flexgrid>
</body>
</html>
