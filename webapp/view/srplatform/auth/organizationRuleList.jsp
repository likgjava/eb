<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/organizationRuleList.js"></script>
</head>
<body>	  
	<div class="formZone" >
		<form id="orgnizationRuleSearchZone" >
		</form>
	</div>
	<flex:flexgrid
		id="orgnizationRuleGrid" url="OrganizationRuleController.do?method=list" queryColumns=""  
			searchZone="orgnizationRuleSearchZone" rp="10" title="列表"  height="200" 
			onSubmit="orgnizationRuleList.before" onSuccess="orgnizationRuleList.success" checkbox="true">
		<flex:flexCol name="source" display="上级组织类型" sortable="true" width="150"align="left"></flex:flexCol>
		<flex:flexCol name="target" display="下级组织类型" sortable="true" width="150"align="left"></flex:flexCol>
		<flex:flexCol name="rule" display="拖动规则" sortable="true" width="80"align="left"></flex:flexCol>
		<flex:flexBtn name="新增" bclass="add" onpress="orgnizationRuleList.add"></flex:flexBtn>	
		<flex:flexBtn name="删除" bclass="delete" onpress="orgnizationRuleList.remove"></flex:flexBtn>	
	</flex:flexgrid>
</body>
</html>