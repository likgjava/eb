<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<div class="partContainers formLayout form2Pa">         
	<form id="giftSeriesForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		
     	<div class="formFieldset">
     		<ul>
     			<li class="fullLine centre">
	     		<label>系列名称：</label>
					<span>${giftSeries.name}</span>
    	    </li>
	     	<li class="fullLine centre">
	     		<label>系列编号：</label>
	     			<span>${giftSeries.seriesCode}</span>
    	    </li>
     		</ul>
		</div>
		
	    <div class="conOperation">
				<button type="button" id="giftSeriesSave"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>
</div>

<script>
$(document).ready(function(){

	//提交
	$('#giftSeriesSave').click(function(){
		$('#epsDialogClose').click(); 		
	});

});
</script>