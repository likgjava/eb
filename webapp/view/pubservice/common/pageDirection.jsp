<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<input type="hidden" id="page" name="page" value="${page }"/>
<input type="hidden" id="rp" name="rp" value="${PAGERESULT.pageSize }"/>
<input type="hidden" id="totalPageNum" name="totalPageNum" value="${PAGERESULT.totalPageNum }"/>
<input type="hidden" id="totalRowNum" name="totalRowNum" value="${PAGERESULT.totalRowNum }"/>

<div class="dataTables_paginate paging_full_numbers" id="QuotaList_paginate">
<c:choose>
	<c:when test="${PAGERESULT.totalPageNum > 0}">
		<c:if test="${PAGERESULT.pageNum != 1}">
			<span class="first paginate_button" id="QuotaList_first">第一页</span>
			<span class="previous paginate_button" id="QuotaList_previous">上一页</span>
		</c:if>
		<c:forEach begin="${PAGERESULT.pageNum - 3 < 1? 1 : PAGERESULT.pageNum - 3}" end="${PAGERESULT.pageNum + 3 > PAGERESULT.totalPageNum?PAGERESULT.totalPageNum : PAGERESULT.pageNum + 3}" step="1" varStatus="status">
			<c:choose>
				<c:when test="${status.index == PAGERESULT.pageNum}">
					<span class="paginate_active" onclick="pageDirection.jump('${status.index}')">${status.index}</span>
				</c:when>
				<c:otherwise>
					<span class="paginate_button" onclick="pageDirection.jump('${status.index}')">${status.index}</span>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${PAGERESULT.pageNum != PAGERESULT.totalPageNum}">
			<span class="next paginate_button" id="QuotaList_next">下一页</span>
			<span class="last paginate_button" id="QuotaList_last">最后页</span>
		</c:if>
		<span class="totalNum">共${PAGERESULT.totalPageNum }页/${PAGERESULT.totalRowNum }条记录</span>
	</c:when>
	<c:otherwise>
		<span class="l">没有检索到数据！</span>
	</c:otherwise>
</c:choose>
</div>

<script>
var pageDirection = {};
//跳转
pageDirection.jump = function(page){
	$("#page").val(page);
	show_list.makeSearchData();
}

$(document).ready(function(){
	//第一页点击
	$("#QuotaList_first").click(function(){
		pageDirection.jump('1');
	});
	
	//上一页点击
	$("#QuotaList_previous").click(function(){
		pageDirection.jump(parseInt($("#page").val())-1);
	});
	
	//下一页点击
	$("#QuotaList_next").click(function(){
		pageDirection.jump(parseInt($("#page").val())+1);
	});
	
	//最后页点击
	$("#QuotaList_last").click(function(){
		pageDirection.jump($("#totalPageNum").val());
	});
	
});
</script>