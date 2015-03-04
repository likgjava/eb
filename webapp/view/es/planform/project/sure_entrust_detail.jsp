<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<flex:flexgrid  usepager="false" showTableToggleBtn="true" id="buyerGrid" url="view/esdemo/projectmanager/data/planList.txt" queryColumns="" onSubmit="list.before" onSuccess="list.success" height="70" >
	<flex:flexCol name="objId" display="序号" width="30" sortable="true"></flex:flexCol>
	<flex:flexCol name="buyer" display="招标单位" width="150" sortable="true"></flex:flexCol>
	<flex:flexCol name="itemType" display="品目类型" width="100" sortable="true"></flex:flexCol>
	<flex:flexCol name="itemName" display="品目名称" width="150" sortable="true"></flex:flexCol>
	<flex:flexCol name="num" display="数量" width="90" sortable="true" align="right"></flex:flexCol>
	<flex:flexCol name="price" display="单价（元）" width="100" sortable="true" align="right"></flex:flexCol>
	<flex:flexCol name="totalPrice" display="总预算（元）" width="100" sortable="true" align="right"></flex:flexCol>
	<flex:flexCol name="bidType" display="采购方式" width="90" sortable="true"></flex:flexCol>
	<flex:flexCol name="branch" display="执行部门" width="90" sortable="true"></flex:flexCol>
</flex:flexgrid>