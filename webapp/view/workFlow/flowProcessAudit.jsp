<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<c:if test="${null != processNodes && fn:length(processNodes) > 0}">
	<div id="projectNav">
	  <ul id="projectNav10">
	    <c:forEach items="${processNodes}" var="processNode">
	    	<li class="${processNode.class}" style="width: 8em;"><span>${processNode.nodeName}</span>
	    		<c:if test="${null != processNode.nodes && fn:length(processNode.nodes) > 0}">
	    			<ul style="width: auto;">
	    				<c:forEach items="${processNode.nodes}" var="node">
	    					<li><a href="#" target="">${node.name}</a></li>
	    				</c:forEach>
	    			</ul>
	    		</c:if>
	    	</li>
	    </c:forEach>
	  </ul>
	</div>
</c:if>
<script>
$(document).ready(function(){
	/*++++++++
	+ 
	+ 下拉菜单
	+ 
	++++++++*/
	var menu = $('#projectNav');
	$('li:has(ul)',menu).hover(function(){
									  var ul = $('ul:first',this);
									  ul.show();
									
									  }
									  ,
									  function(){
									  var ul = $('ul:first',this);
									  ul.hide();  
										  
										  })

});

</script>