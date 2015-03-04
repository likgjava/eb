<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/syndicEvalBidRecordList.js"></script>
<input type="hidden" id="factor_type_id" value="${factorTypeId}"></input>
<input type="hidden" id="project_pack_id" value="${packId}"></input>
<div class="partContainers">
	<DIV id="projectContent">
		<DIV id="projectSub" style="width:20%;">
			<UL>
				<LI class="selected">
					<A>投标单位</A> 
					<UL id="supplier_list">
						<c:forEach items="${suppliers}" var="supplier">
							<LI supplierId="${supplier.supplierId}" ><A href="#">${supplier.supplierName}</A></LI>
						</c:forEach>
					</UL>
				</LI>
			</UL>
		</DIV>
		<div id="syndicEvalRecordList"></div>
	</DIV>
</div>