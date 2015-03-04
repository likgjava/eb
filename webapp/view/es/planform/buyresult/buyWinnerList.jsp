<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/buyresult/buyWinnerList.js"></script>
   <div id="buyWinnerListView">
   <input type="hidden" id="buyResultId" name="buyResultId" value="${param.buyResultId}">
	<flex:flexgrid checkbox="true"
		id="buyWinnerGrid" url="BuyWinnerController.do?method=list&buyResult.objId=${param.buyResultId}" queryColumns=""  
			searchZone="buyWinnerSearchZone" rp="10" title="成交投标单位清单"  
			onSubmit="buyWinnerList.before" onSuccess="buyWinnerList.success">
					<flex:flexCol name="selllerId" display="buyWinnerForm.selllerId" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="sellerName" display="buyWinnerForm.sellerName" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="buyWinnerList.add"></flex:flexBtn>	
		<flex:flexBtn name="globe.modify" bclass="modify" onpress="buyWinnerList.update"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="buyWinnerList.remove"></flex:flexBtn>	
	</flex:flexgrid>
   </div>