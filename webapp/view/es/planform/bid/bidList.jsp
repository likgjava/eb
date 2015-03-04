<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bid/bidList.js"></script>
   <div id="bidFormView">
	<form id="bidSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
				<li><label><spring:message code="bidForm.bidBrand"/></label>
										<input type="text" name="bidBrand"  >
									   <input type="hidden" name="bidBrand_op" value="like"/>
									</li>
				<li><label>投标单位名称</label>
										<input type="text" name="supplierName"  >
									   <input type="hidden" name="supplierName_op" value="like"/>
									</li>
				<li><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>

	<flex:flexgrid checkbox="true"
		id="bidGrid" url="BidController.do?method=list&project.objId=${param.projectId}" queryColumns=""  
			searchZone="bidSearchZone" rp="10" title="投标单位投标结果" width="800"  
			onSubmit="bidList.before" onSuccess="bidList.success">
					<flex:flexCol name="bidBrand" display="bidForm.bidBrand" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="supplierName" display="投标单位名称" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="bidLinker" display="bidForm.bidLinker" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="createTime" display="bidForm.createTime" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="查看详细" bclass="look" onpress="bidList.showDetail"></flex:flexBtn>	
	</flex:flexgrid>
</div>
