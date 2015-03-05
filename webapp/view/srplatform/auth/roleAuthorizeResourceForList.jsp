<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers">
	<c:set var="line" value="0"></c:set>
	<table calss="tablelist">
		<tr>
		<c:forEach var="resource" items="${resourceList}" >
			<c:if test="${line == showLineNumber}">
				</tr><tr>
				<c:set var="line" value="0"></c:set>
			</c:if>
			<td style="width: auto;"><input type="checkbox" name="check_resource" value="${resource.path}" ></input>${resource.name}</td>
			<c:set var="line" value="${line+1}"></c:set>
		</c:forEach>
		</tr>
	</table>
			<div class="conOperation">
			<button id="save_role_res" type="button" tabindex="19"><span><spring:message code="globe.save"/></span></button>
			<button name="historyBackBtn" tabindex="20"><span><spring:message code="globe.return"/></span></button>
		</div>
</div>
<script>
$(document).ready(function(){
	$('#save_role_res').click(function(){
		var resPathIds = new Array();
		$('input[type=checkbox][name=check_resource][checked=true]').each(function(){
			resPathIds.push(this.value);
		})
		var resPathIds = resPathIds.toString();
		$('input[type=checkbox][name=check_resource][checked=true]').each(function(){
			resPathIds = resPathIds.replace(",","#");
		})
	})
})
</script>