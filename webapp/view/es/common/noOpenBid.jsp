<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<input type="hidden" id="errorType" value="${errorType}">
<div class="partContainers">
<ul>
<li style="color: red;margin-left: 50%"><span id="errorMessage"></span></li>
</ul>
</div>
<script>
$(document).ready(function(){
   var errorType = $("#errorType").val();
   if(errorType=='noOpenBid'){
	$("span[id=errorMessage]").text("还未开标！");
	}
   if(errorType=='noEvlBid'){
	$("span[id=errorMessage]").text("谈判记录还末录入完成，不能起草评审报告！");
	}
   if(errorType=='noResultBid'){
	$("span[id=errorMessage]").text("还未确定中标人，不能起草中标公告！");
	}
   if(errorType=='noEvalReport'){
	$("span[id=errorMessage]").text("还未起草评标报告，不能确定中标人！");
	}
   if(errorType=='noReviewResult'){
	$("span[id=errorMessage]").text("还未录入评审结果，不能起草评审报告！");
	}
})
</script>