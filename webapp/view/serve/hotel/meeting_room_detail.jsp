<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

	<form:form id="meetingRoomInfoForm" name="meetingRoomInfoForm" method="post" enctype="multipart/form-data" modelAttribute="meetingRoom"> 
	<input type="hidden" name="hotel.objId"  value="${param.hotelId }"/>
	<form:hidden path="objId" id="meetingRoomId"/>
	
	<div id="meetingRoomInfo" class="formLayout form2Pa">
			<h4 class="title"><span>会议室信息</span></h4>
			<div class="k1">
				<div class="img_250_1" id="newPreview">
					<img id="pictureView"  src="<%=request.getContextPath()%>/view/resource/skin/goods/img/goods_add.gif" width="200px" height="175px"></img>
				</div>
				<form:hidden path="picture" />
			</div>
			<ul style="height:220px">
				<div class="formTips warm hidden" id="tips"></div>
				<li class="fullLine"><label>会议室编号 ：</label> 
					<span id="meetingRoomCode">${meetingRoom.meetingRoomCode}</span>
				</li>
				
				<li class="fullLine"><label>市场价 ：</label> 
					<span class="money" id="marketPrice">${meetingRoom.marketPrice}</span>
				</li>
				<li class="fullLine"><label>坐席人数 ：</label> 
					<span class="digits" id="containNum">${meetingRoom.containNum}</span>
				</li>
				<li class="fullLine"><label>价格单位：</label> 
					<span id="unit">${meetingRoom.unitCN}</span>
				</li>
				<li class="fullLine"><label>会议室类型：</label> 
					<span id="sameMeetingRoomCode">${meetingRoom.meetingRoomTypeCN}</span>
				</li>
				<li class="fullLine"><label>会议室设施：</label> 
					<span id="meetingRoomFacilities">${meetingRoom.meetingRoomFacilitiesName}</span>
				</li>
				
			</ul>
			<h4 class="title"><span>会议室描述</span><span class="eleRequired"></span></h4>
			<ul>
			<li class="fullLine"><label>会议室描述：</label>
					<span id="meetingRoomFesc" >${meetingRoom.meetingRoomFesc}</span>
				</li>
			</ul>
		<div class="conOperation">
			<button class="largeBtn" id="closeBtn" name="closeBtn" type="button" tabindex="17"><span>关闭</span></button>
		</div>
	</div>
	</form:form>
	
<script>
$(document).ready(function(){	
	//返回
	$("#closeBtn").click(function(){         
		$('#epsDialogClose').click(); 
	})
	
	// 显示图片
    if($('#picture').val() !== ""){
    	$('img[id=pictureView]').attr('src',$('#initPath').val()+'/AttachmentController.do?method=showImg&objId='+$('#picture').val());
    }
})
</script>
