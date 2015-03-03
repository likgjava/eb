<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/register.css" type="text/css" rel="stylesheet" />

<form:form id="OrgInfoApplyForm" method="post" modelAttribute="orgInfo">
		<input type="hidden" id="objId" name="objId" value="<c:out value="${orgInfo.objId}"/>"/>
		<input type="hidden" id="supplierId" name="supplierId" value="<c:out value="${orgInfo.supplierId}"/>"/>
		<input type="hidden" id="buyerId" name="buyerId" value="<c:out value="${orgInfo.buyerId}"/>"/>
		<input type="hidden" id="agencyId" name="agencyId" value="<c:out value="${orgInfo.agencyId}"/>"/>
		
		<c:set var="roleType" value=""></c:set>
		<strong>您目前是
			<c:forEach var="role" items="${roles}" varStatus="status">
				<!--不显示默认机构管理员,默认采购人,默认供应商角色-->
				<c:if test="${fn:contains(role.type,'b') || fn:contains(role.type,'s') || fn:contains(role.type,'m')}">
					[${role.chName}]
					<c:set var="roleType" value="${roleType},${role.type}"></c:set>
				</c:if>
			</c:forEach>
			
			<c:choose>
			<c:when test="${fn:contains(roleType,'b') && fn:contains(roleType,'s') && fn:contains(roleType,'m')}">
				已没有要申请的角色.
			</c:when>
			<c:otherwise>
				申请成为：
			</c:otherwise>
			</c:choose>
		</strong>
		
		<c:choose>
			<c:when test="${!fn:contains(roleType,'m')}">
        		<div class="
					<c:if test="${fn:contains(roleType,'b')}">
						applyGys
					</c:if>
					<c:if test="${fn:contains(roleType,'s')}">
						applyCgr
					</c:if>
					<c:if test="${fn:contains(roleType,'s') && fn:contains(roleType,'b')}">
						hidden
					</c:if>
				">
			        <ul>
			        	<!--如果目前是采购人,可以申请成为供应商,如果是供应商,可以申请成为采购人-->
			        	<c:if test="${fn:contains(roleType,'b') && orgInfo.supplierAuditStatus != '01' }">
				         <li><a href="javascript:void(0);" id="supplierRole" title="供应商">供应商</a></li>
				        </c:if>
				        <c:if test="${fn:contains(roleType,'s') && orgInfo.buyerAuditStatus !='01' }">
				         <li><a href="javascript:void(0);" id="buyerRole" title="采购人">采购人</a></li>
				        </c:if>
				        <c:if test="${orgInfo.supplierAuditStatus=='01'}"><font color="red">【供应商】审核中</font></c:if>
	        			<c:if test="${orgInfo.buyerAuditStatus=='01'}"><font color="red">【采购人】审核中</font></c:if>
			        </ul>
        		</div>
			</c:when>
			
			<c:otherwise>
				<c:choose>
				<c:when test="${fn:contains(roleType,'b') && fn:contains(roleType,'s') && fn:contains(roleType,'m')}">
				</c:when>
				<c:otherwise>
				<div class="
					<c:if test="${fn:contains(roleType,'b')}">
						applyGys
					</c:if>
					<c:if test="${fn:contains(roleType,'s')}">
						applyCgr
					</c:if>
					<c:if test="${fn:contains(roleType,'s') && fn:contains(roleType,'b')}">
						hidden
					</c:if>
					">
			        <ul>
			        	<!--如果目前是采购人,可以申请成为供应商,如果是供应商,可以申请成为采购人-->
			        	<c:if test="${fn:contains(roleType,'b') && orgInfo.supplierAuditStatus != '01' }">
				         <li><a href="javascript:void(0);" id="supplierRole" title="供应商">供应商</a></li>
				        </c:if>
				        <c:if test="${fn:contains(roleType,'s') && orgInfo.buyerAuditStatus !='01'}">
				         <li><a href="javascript:void(0);" id="buyerRole" title="采购人">采购人</a></li>
				        </c:if>
			        </ul>
	        		</div>
				</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
		
		<input type="hidden" id="currentRole" name="currentRole" value="<c:out value="${roleType}"/>"/>
</form:form>

<script>

$(document).ready(function(){
	//申请成为供应商
	$("#supplierRole").click(function(){
		if(window.confirm('确定要申请成为供应商?')) {
			var jsonSupplier = {};
			jsonSupplier.objId = $('#supplierId').val();
			$.getJSON($('#initPath').val()+'/SupplierController.do?method=saveApplySupplier&orgInfoId='+$('#objId').val(),jsonSupplier, function(json){
				if(json.success=='true'){
					alert('申请成功，请耐心等待管理员的审核');
					$('#conBody').loadPage($('#initPath').val()+"/OrgInfoController.do?method=toApplyOrgInfo");
				}else{
					alert('操作失败');
				}
			})
		}
	})
	
	//申请成为厂商
	$("#manufactorRole").click(function(){
		var currentRole = $('#currentRole').val()==null?"":$('#currentRole').val();
		if(currentRole.indexOf('s')==-1) {
			alert('对不起，您还不是供应商，请先申请成为供应商!');
			return;
		}
		if(window.confirm('确定要申请成为厂商?')) {
			$.getJSON($('#initPath').val()+'/OrgInfoController.do?method=saveApplyManufactor&orgInfoId='+$('#objId').val(),{}, function(json){
				if(json.success=='true'){
					alert('申请成功，请耐心等待管理员的审核');
					$('#conBody').loadPage($('#initPath').val()+"/OrgInfoController.do?method=toApplyOrgInfo");
				}else{
					alert('操作失败');
				}
			})
		}
	})
	
	//申请成为采购人
	$("#buyerRole").click(function(){
		if(window.confirm('确定要申请成为采购人?')) {
			var jsonBuyer = {};
			jsonBuyer.objId = $('#buyerId').val();
			$.getJSON($('#initPath').val()+'/BuyerController.do?method=saveApplyBuyer&orgInfoId='+$('#objId').val(),jsonBuyer, function(json){
				if(json.success=='true'){
					alert('申请成功，请耐心等待管理员的审核');
					$('#conBody').loadPage($('#initPath').val()+"/OrgInfoController.do?method=toApplyOrgInfo");
				}else{
					alert('操作失败');
				}
			})
		}
	})
})
</script>