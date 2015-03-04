<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bid/syndicEvalBidRecordList.js"></script>
<input type="hidden" id="projectId" value="${param.projectId}">
<div class="partContainers" style="overflow:hidden; margin-bottom:6px;">
	<DIV id="projectContent">
		<DIV id="projectSub" style="width:20%;">
			<UL>
				<LI class="selected">
					<A>投标单位</A> 
					<UL id="supplier_list">
						<c:forEach items="${signUprecordList}" var="signUprecord">
							<LI signUprecordId="${signUprecord.objId}"  ><A href="#">${signUprecord.supplier.orgName}</A></LI>
						</c:forEach>
					</UL>
				</LI>
			</UL>
		</DIV>
		<div id="syndicEvalRecordList" style="margin-left: 258px;"></div>
	</DIV>
</div>