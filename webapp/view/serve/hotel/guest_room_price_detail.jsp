<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="_viewGuestRoomPriceDialogID"  value="<c:out value="${param.dialogId}"/>"/>
<form:form id="guestRoomPriceForm">
	<div class="formLayout  form2Pa">
		<ul>
	    	<li class="fullLine">
	            <label>协议价(元)：</label>
	            <span>${guestRoomPrice.agreePrice}</span>
	        </li>
	        <li class="fullLine">
	            <label>开始时间：</label>
	            <span><fmt:formatDate value="${guestRoomPrice.startTime}" pattern="yyyy-MM-dd"/></span>
	        </li>
	        <li class="fullLine">
	            <label>结束时间：</label>
	            <span><fmt:formatDate value="${guestRoomPrice.endTime}" pattern="yyyy-MM-dd"/></span>
	        </li>
	        <li class="fullLine">
	            <label>是否含早餐：</label>
	            <c:if test="${guestRoomPrice.hasBreakfast}" var="hasBf"><span>含早餐</span></c:if>
	            <c:if test="${!hasBf}"><span>不含早餐</span></c:if>
	        </li>
	        <c:if test="${hasBf}">
	        <li class="fullLine">
	          	<label>早餐数量：</label>
	          	<span>${guestRoomPrice.breakfastNum}</span>
    	  	</li>
    	  	</c:if>
	        <li class="formTextarea">
	          	<label>备注：</label>
	          	<span>${guestRoomPrice.remark}</span>
    	  	</li>
	    </ul>
	    
    	<div class="conOperation">
			<button type="button" id="guestRoomPriceCloseBut"><span>关闭</span></button>
		</div>
	</div>
</form:form>		

<script>
/**
 * 客房价格详细信息页面
 * create by likg
 */

$(document).ready(function(){
	//关闭
	$("#guestRoomPriceCloseBut").click(function(){
		if($("#_viewGuestRoomPriceDialogID").val() != ""){
			$(document.getElementById($("#_viewGuestRoomPriceDialogID").val())).find('.epsDialogClose').trigger('click');
		}else{
			$('.epsDialogClose').trigger('click');
		}
	});

})
</script>