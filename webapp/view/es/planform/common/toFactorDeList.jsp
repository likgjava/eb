<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/common/toFactorDeList.js"></script>
<input type="hidden" id="_factor_type_id" value="${param.factortypeId}"></input>
<flex:flexgrid checkbox="false"
	id="factorDeGrid" url="FactorDeController.do?method=list" queryColumns=""  
	searchZone="factorDeSearchZone" rp="6" title="" width="560" height="200" 
	onSubmit="before" onSuccess="success" >
	<flex:flexCol name="factorName" display="factorDeForm.factorName" sortable="true" width="450" align="left"></flex:flexCol>
	<flex:flexBtn name="globe.new" bclass="add" onpress="factorDeList.add"></flex:flexBtn>	
</flex:flexgrid>
<div class="conOperation"><button id="CLEAR"><span>清空</span></button>&nbsp;<button id="RETURN"><span>返回</span></button></div>