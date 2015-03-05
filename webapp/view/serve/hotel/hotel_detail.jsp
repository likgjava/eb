<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

	<form:form id="hotelInfoForm" name="hotelInfoForm" method="post" enctype="multipart/form-data" modelAttribute="hotel"> 
	<input type="hidden" name="objId" id="hotelId"  value="${param.objId }"/>
	<input type="hidden" name="auditType" id="auditType"  value="${param.auditType }"/>
	<input type="hidden" name="additionPicture" value="${hotel.additionPicture }"/>
	
	<div id="hotelInfo">
		<div class="formLayout imgAndForm">	
			<h4 class="title"><span>酒店基本信息</span><span class="eleRequired"></span></h4>
			<div class="k1">
				<div class="img_250_1" id="newPreview" style="width:200px;height:200px;">
					<img id="pictureView"  src="<%=request.getContextPath()%>/view/resource/skin/goods/img/goods_add.gif" width="200px" height="200px"></img>
				</div>
				<form:hidden path="picture" />
			</div>
			<ul >
				<div class="formTips warm hidden" id="tips"></div>
				
				<li class="fullLine"><label>酒店名称 ：</label> 
					<span id="hotelName">${hotel.hotelName}</span>
				</li>
				<li class="fullLine"><label>酒店星级：</label> 
				<span id="star">${hotel.starCN}</span>
				</li>
				<li class="fullLine">
		            <label>开业时间：</label>
		            <span id="startTime"><fmt:formatDate value="${hotel.startTime}" pattern="yyyy-MM-dd"/></span>
		        </li>
				<li class="fullLine"><label>联系电话：</label> 
					<span id="contact">${hotel.contact}</span>
				</li>
				<li class="fullLine"><label>传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真：</label> 
					<span id="fax">${hotel.fax}</span>
				</li>
				<li class="fullLine"><label>周围环境：</label>
					<span id="surroundings">${hotel.surroundings }<c:if test="${fn:length(hotel.surroundings) < 100}">
				</c:if><br/><br/></span>
				</li>
				
			</ul>
		</div>
		<div class="formLayout form2Pa">
			<h4 class="title"><span>酒店详细信息</span></h4>
			<ul id="hotelSelectBox">
				<li class="fullLine"><label>酒店服务项目：</label> 
					<span id="serviceItems">${hotel.serviceItemsName }</span>
				</li>
				<li class="fullLine"><label>餐饮设施：</label> 
					<span id="foodFacilities">${hotel.foodFacilitiesName }</span>
				</li>
				<li class="fullLine"><label>娱乐设施：</label> 
					<span id="funFacilities">${hotel.funFacilitiesName }</span>
				</li>
				<li class="fullLine"><label>客房设施和服务：</label> 
						<span id="guestRoomFacilities">${hotel.guestRoomFacilitiesName }</span>
				</li>
				<li class="fullLine"><label>可接受信用卡类型：</label> 
					<span id="creditCardType">${hotel.creditCardTypeName }</span>
				</li>
				<li class="fullLine">
		            <label>所在区域：</label>
		            <span >${hotel.district.parent.parent.name}&nbsp;${hotel.district.parent.name}&nbsp;${hotel.district.name}</span>
				</li>
				<li class="fullLine"><label>酒店地址：</label> 
					<span id="hotelAddress">${hotel.hotelAddress}</span>
				</li>
				<li class="fullLine"><label>酒店概况：</label>
					<span id="hotelDesc">${hotel.hotelDesc }</span>
				</li>
			</ul>
		</div>
		<div id="picturesInfo" class="formLayout form2Pa">
			<h4 class="title"><span>更多酒店图片信息</span></h4>
			<div class="uploadFile2" id="additionPicture"></div>
		</div>
	</div>
	</form:form>

<div id="epsTabs">
	<ul>
		<li>
			<a href="#hotelDetail"  class="refreshData"><span>酒店详细介绍</span></a>
		</li>
		<li>
			<a href="#guestRoomPage"  class="refreshData"><span>客房</span></a>
		</li>
		<li>
			<a href="#meetingRoomPage"  class="refreshData"><span>会议室</span></a>
		</li>
	</ul>
	<div id="hotelDetail" class="formLayout form2Pa">
		<span id="hotelDesc">${hotel.hotelDetail }</span>
	</div>
	<div id="guestRoomPage"> </div>
	<div id="meetingRoomPage"> </div>
</div>
	
	<div class="conOperation">
		<button type="button" id="auditHotelBtn_02" class="hidden"><span>审核通过</span></button>
		<button type="button" id="auditHotelBtn_03" class="hidden"><span>审核不通过</span></button>
		<button class="largeBtn" id="returnBtn" name="returnBtn" type="button" tabindex="17"><span>返回</span></button>
	</div>

<script>
var hotelInfoForm={};

$(document).ready(function(){	
	
	// 如果是审核
	if($('#auditType').val() && $('#auditType').val()=="audit"){
		$('button[id^=auditHotelBtn_]').show();
	}
	
	//返回
	$("button[name=returnBtn]").click(function(){         
		$('#conBody').loadPage($('#returnUrl').val());
	})
	
	// 显示图片
    if($('#picture').val() !== ""){
    	$('img[id=pictureView]').attr('src',$('#initPath').val()+'/AttachmentController.do?method=showImg&objId='+$('#picture').val());
     }

	//更多图片
	$('#additionPicture').loadPage($('#initPath').val()+'/AttachmentController.do?method=toUploadAjaxImg',{
			defineSelf:'additionPicture',//存放关联id的属性名
			maxSize:'1024',
			fileType: 'gif|jpeg|jpg|bmp|png',
			quantity:'4',//附件最大数
			attachRelaId:$("input[name=additionPicture]").val(),
			isView:'1',//是否只是显示图片不删除和上传
			maxWidth:100,//建议长
			maxHeight:100,//建议宽
			nopicPath:'AttachmentController.do?method=showImg'//提示图片  可以是AttachmentController.do?method=showImg
	});
	
	//加载tabs,绑定选中事件为加载列表
	var $tabs = $('#epsTabs').tabs({
		select: function(event, ui) {
			if(ui.index == 1){//客房
				$('#guestRoomPage').loadPage($('#initPath').val()+'/view/serve/hotel/guest_room_list_view.jsp');
			}
			if(ui.index == 2){// 会议室
				$('#meetingRoomPage').loadPage($('#initPath').val()+'/view/serve/hotel/meeting_room_list_view.jsp');
			}
		}
	});

	//审核
	$('button[id^=auditHotelBtn_]').click(function(){
		$('button[id^=auditHotelBtn_]').attr('disabled',true);
		var auditStatus = $(this).attr('id').replace('auditHotelBtn_','');
		$.getJSON($('#initPath').val()+'/HotelController.do?method=updateHotelAuditStatus',{'objId':$('#hotelId').val(),'auditStatus':auditStatus},function(json){
			if(json.result)alert(json.result);if(json.failure){$('button[id^=auditHotelBtn_]').attr('disabled',false);return};
	 		if(json.success){
					alert('审核成功!');
					$('#conBody').loadPage($('#returnUrl').val());
	     	}
 		});
	});

})
</script>
