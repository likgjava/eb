<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/supplier_seniority_affirm.js"></script>
<div class="formLayout">
<div class="partContainers operationLog"><h5 id="seniorityAffirm"><span class="switch  left11">确认<dm:out value="local__RoleType__Supplier" tenderType="${project.tenderType}">投标单位</dm:out>资格</span></h5></div>
<div id="seniorityAffirmDetail">
 <flex:flexgrid id="supplierGrid" usepager="false" showTableToggleBtn="true" url="view/esdemo/projectmanager/data/supplier_affirm.txt" queryColumns="" onSubmit="list.before" onSuccess="list.success"  height="100" checkbox="false">
	<flex:flexCol name="supplier" display="投标单位" width="150" sortable="true"></flex:flexCol>
	<flex:flexCol name="orgin" display="来源" width="150" sortable="true"></flex:flexCol>
	<flex:flexCol name="linkMan" display="联系人" width="150" sortable="true"></flex:flexCol>
	<flex:flexCol name="phone" display="电话" width="150" sortable="true"></flex:flexCol>
	<flex:flexCol name="item" display="参与包组" width="150" sortable="true"></flex:flexCol>
	<flex:flexCol name="isPaid" display="是否报名" width="150" sortable="true"></flex:flexCol>
 </flex:flexgrid>
 <div class="conOperation" align="center">
   <button type="button" id="saveBtn"><span>确认资格</span></button> 
 </div>
 </div>
 </div>

