<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="guestRoomForm">
	<div class="formLayout imgAndForm">
		<div class="k1">
			<div class="img_250_2" id="newPreview">
				<c:choose>
					<c:when test="${guestRoom.picture==null}">
						<img id="view"  src="<%=request.getContextPath()%>/view/resource/skin/bizplatform/img/orginfo_add.gif" width="200px" height="200px"></img>
					</c:when>
					<c:otherwise>
						<img src="<c:url value="AttachmentController.do?method=showImg&objId=${guestRoom.picture}" />" width="200px" height="200px">
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<ul>
	    	<li class="fullLine">
	            <label>客房编号：</label>
	            <span>${guestRoom.guestroomCode}</span>
	        </li>
	        <li class="fullLine">
	            <label>房间数量：</label>
	            <span>${guestRoom.guestroomNum}</span>
	        </li>
	        <li class="fullLine">
	            <label>门市价：</label>
	            <span>${guestRoom.retailPrice} 元</span>
	        </li>
	        <li class="fullLine">
	            <label>面积：</label>
	            <span>${guestRoom.area} 平方</span>
	        </li>
	        <li class="fullLine">
	          	<label>楼层：</label>
	          	<span>${guestRoom.floor}</span>
    	  	</li>
	        <li class="fullLine">
	          	<label>宽带：</label>
	          	<span>${guestRoom.broadband}</span>
    	  	</li>
	    	<li class="fullLine">
	            <label>客房类型：</label>
	            <span>${guestRoom.guestRoomTypeCN}</span>
	        </li>
	        <li class="fullLine">
	            <label>床型：</label>
				<span>${guestRoom.bedTypeCN}</span>
	        </li>
	        <li class="fullLine">
	          	<label>早餐类型：</label>
	          	<span>${guestRoom.breakfastTypeCN}</span>
    	  	</li>
	    </ul>
	</div>
	<div class="formLayout form2Pa">
	    <ul>
	        <li class="formTextarea">
	           <label>房间描述：</label>
	           <div>${guestRoom.guestroomDesc}</div>
	        </li>
	    </ul>
	 </div>   
   	<div class="conOperation">
		<button type="button" id="guestRoomCloseBut"><span>关闭</span></button>
	</div>
</form:form>		

<script>
/**
 * 客房详细信息页面
 * create by likg
 */

$(document).ready(function(){
	//关闭
	$("#guestRoomCloseBut").click(function(){
		$('.epsDialogClose').trigger('click');
	});

})
</script>