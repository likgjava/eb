<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/expertInfoDetail.js"></script>
<flex:flexgrid usepager="false" checkbox="false" id="zhuanjGrid" url="view/esdemo/projectmanager/data/purchase_file_prove_zj.txt" queryColumns=""  
			searchZone="zhuanjSearchZone" rp="10" title="" width="800" height="60" onSubmit="reviewReportGrid.before" onKeyRight="reviewReportGrid.keyRight" onSuccess="reviewReportGrid.success">
		<flex:flexCol name="name" display="专家姓名" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="account" display="专家编号" sortable="true" width="150" align="left"></flex:flexCol>
		<flex:flexCol name="phone" display="手机" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="brond" display="评审品目" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="address" display="工作单位" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="zc" display="专家职称" sortable="true" width="100" align="left"></flex:flexCol>
</flex:flexgrid>