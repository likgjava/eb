<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<table class="compareTable" id="compareTable">
	<c:forEach var="comp_goods" items="${compareGoodsList}" varStatus="status">
		<tr>
			<c:choose>
				<c:when test="${status.index == 0}">
					<c:forEach var="title" items="${comp_goods}" begin="1">
						<td class="title"><em>${title}</em></td>
					</c:forEach>
				</c:when>
				<c:when test="${status.index == 1}">
					<c:forEach var="pic" items="${comp_goods}" begin="1">
						<td class="img">
							<c:if test="${pic != '&nbsp;'}">
								<img src="<c:url value="AttachmentController.do?method=showImg&objId=${pic}" />" width="150px" height="150px">
							</c:if>
						</td>
					</c:forEach>
				</c:when>
				<c:when test="${status.index == 3}">
					<td>${comp_goods[1]}</td>
					<c:forEach var="price" items="${comp_goods}" begin="2">
						<td class="red">${price}</td>
					</c:forEach>
				</c:when>
				<c:when test="${comp_goods[0] > 1}">
					<td colspan="${comp_goods[0]}"><em>${comp_goods[1]}</em></td>
				</c:when>
				<c:when test="${status.index == (fn:length(compareGoodsList) - 1)}">
					<td><a href="javascript:void(0);" onclick="removeCompare();return false;">移除全部</a></td>
					<c:forEach var="remove" items="${comp_goods}" begin="2" varStatus="s">
						<td><a href="javascript:void(0);" onclick="removeCompare('${s.index}','${remove}');return false;">移除</a></td>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="info" items="${comp_goods}" begin="1">
						<td>${info}</td>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tr>
	</c:forEach>
</table>

<div class="conOperation">
	<button class="largeBtn" id="close"  type="button"><span>关闭</span></button>
</div>

<script>
$("#close").click(function(){
	$('#close').closest(".epsDialog").find('.epsDialogClose').trigger('click');
})

//移除
function removeCompare(index,objId){
	//单独移除
	if(index && objId) {
		$.each($("#compareTable tr"),function(i,n){
			var td = $(n).find("td");
			
			td.eq(index-1).remove();  //删除单元格
			if(td.attr("colspan")) {
				td.attr("colspan",td.attr("colspan")-1);
			}
		})
		show_list.removeCompare(objId);
	}else {
		$("#close").click();
		show_list.removeCompare();
	}
}
</script>