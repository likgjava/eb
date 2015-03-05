<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout imgAndForm">
	<div class="k1" style="width: 258px;">
		<div class="img_250_2" style="width: 258px; height: 175px;">
			<img src="<c:url value="AttachmentController.do?method=showImg&objId=${groupBuying.picture}" />" width="258px" height="175px">
		</div>
	</div>
	<ul>
    	<li class="fullLine">
            <label>团购名称：</label>
            <p>${groupBuying.name}</p>
        </li>
        <li class="fullLine">
            <label>市场价：</label>
            <span>￥ <fmt:formatNumber value="${groupBuying.marketPrice}" pattern="#,##0.00#" />元</span>
        </li>
		<li class="fullLine">
            <label>团购价：</label>
            <span>￥ <fmt:formatNumber value="${groupBuying.groupPrice}" pattern="#,##0.00#" />元</span>
        </li>
		<li class="fullLine">
            <label>最低团购数：</label>
            <span>${groupBuying.minNumber}</span>
        </li>
        <c:if test="${groupBuying.maxNumber != null}">
        <li class="fullLine">
            <label>最大团购数：</label>
            <span>${groupBuying.maxNumber}</span>
        </li>
        </c:if>
        <li class="fullLine">
            <label>开始时间：</label>
            <span><fmt:formatDate value="${groupBuying.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
        </li>
        <li class="fullLine">
          	<label>结束时间：</label>
          	<span><fmt:formatDate value="${groupBuying.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
   	  	</li>
    </ul>
</div>
<div class="formLayout form2Pa">
    <ul>
        <li class="formTextarea">
           <label>团购描述：</label>
           <div>${groupBuying.desc}</div>
        </li>
    </ul>
 </div>   
  	<div class="conOperation">
	<button type="button" id="groupBuyingCloseBut"><span>关闭</span></button>
</div>

<script>
$(document).ready(function(){
	//关闭
	$("#groupBuyingCloseBut").click(function(){
		$('.epsDialogClose').trigger('click');
	});
})
</script>