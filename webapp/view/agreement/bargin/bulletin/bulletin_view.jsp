<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<div class="frameNews">
       <h1>${bulletin.bullTitle}</h1>
       <div class="subtitle"></div>
       <div class="source">
      <span>发布时间:<fmt:formatDate value="${bulletin.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
      <span>来源: </span>
       </div>
     ${bulletin.bullContents }
     <div class="conOperation">
		<button type="button" id="bulletinReturnBtn" tabindex="19"><span><spring:message	code="globe.return" /></span></button>
	</div>
   </div>
<script>
$(document).ready(function(){
	$('#bulletinReturnBtn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/view/agreement/project/reverse_project_list.jsp");
	});
})
</script>