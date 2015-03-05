<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div id="giftCommentInfo" class="formLayout form2Pa">
	<h4 class="title"><span>礼品点评信息</span></h4>
	<div class="k1">
		<div class="img_250_1" id="newPreview">
				<img width="200" height="175" src="<c:url value="AttachmentController.do?method=showImg&objId=${giftComment.gift.picture}"/>"></img>
		</div>
	</div>
	<ul>			
		<li class="">
			<label>礼品名称：</label>
			${giftComment.gift.giftName }
		</li>		
		<li class=""><label>礼品编号：</label>
            <span>${giftComment.gift.giftCode}</span>
		</li>
		<li class=""><label>所属系列：</label>
            <span>${giftComment.gift.giftSeries.name}</span>
		</li>
		<li class=""><label>礼品类型：</label> 
			<span><c:if test="${giftComment.gift.giftType == '00'}">实物类型</c:if><c:if test="${giftComment.gift.giftType == '01'}">虚拟类型</c:if></span>
		</li>
		<li class="fullLine"><label>开始时间：</label>
            <span><fmt:formatDate value="${giftComment.gift.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>至：<span><fmt:formatDate value="${giftComment.gift.endTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
		</li>
		<li class=" left">
			<label>评价人：</label>
			${giftComment.createUser.usName }
		</li>
		<li class=" left">
			<label>评价人中文名：</label>
			${giftComment.rateName}
		</li>
		<li class=" left">
			<label>评价时间：</label>
			${giftComment.createTime }
		</li>
		<li class=" left">
			<label>评价标题：</label>
			${giftComment.title}
		</li>
		<li class=" left">
			<label>评价级别：</label>
			<span id="levelHtml"></span>&nbsp;
			${giftComment.level} &nbsp;级
		</li>			
	</ul>
	<h4>评价描述：</h4>
	<div>${giftComment.comment}</div>
	
    <div class="conOperation">	        
        <button class="largeBtn" id="giftCommentBtnReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
    </div>
</div>

<script>
$(document).ready(function(){
	var levelHtml = '';
	for(var i= 0;i<${giftComment.level};i++){
		levelHtml += '<img src="view/resource/skin/skin07/img/az-detail_star-fen.gif">';
	}
	$('#levelHtml').append(levelHtml);
});

//返回
$('#giftCommentBtnReturn').click(function(){
	//自动关闭		
	$('.epsDialogClose').trigger('click');	
});
</script>