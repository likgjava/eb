<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<form:form id="guestRoomForm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="hotel.objId" value="${param.hotelId}" />
	<div class="formLayout imgAndForm">
		<div class="k1">
			<div class="img_250_2" id="newPreview">
				<img id="view"  src="<%=request.getContextPath()%>/view/resource/skin/bizplatform/img/orginfo_add.gif" width="200px" height="200px"></img>
			</div>
			<input name="pictureFile" type="file" id="pictureFile" size="22"/>
		</div>
		<ul>
	    	<li class="fullLine">
	            <label>客房编号：</label>
	            <input type="text" id="guestroomCode" name="guestroomCode" class="required" />
	            <span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	            <label>房间数量：</label>
	            <input type="text" id="guestroomNum" name="guestroomNum" class="required digits" />
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	            <label>门市价(元)：</label>
	            <input type="text" id="retailPrice" name="retailPrice" class="required floats" />
	            <span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	            <label>面积(平方)：</label>
	            <input type="text" id="area" name="area" class="required floats" />
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	          	<label>楼层：</label>
	          	<input type="text" name="floor" id="floor" />
    	  	</li>
	        <li class="fullLine">
	          	<label>宽带：</label>
	          	<input type="text" name="broadband" id="broadband" />
    	  	</li>
	    	<li class="fullLine">
	            <label>客房类型：</label>
	            <html:select id="guestRoomType" name="guestRoomType" code="serve.hotel.type"></html:select>
	        </li>
	        <li class="fullLine">
	            <label>床型：</label>
	            <html:select id="bedType" name="bedType" code="serve.hotel.bedType"></html:select>
	        </li>
	        <li class="fullLine">
	          	<label>早餐类型：</label>
	          	<html:select id="breakfastType" name="breakfastType" code="serve.hotel.breakfastType"></html:select>
    	  	</li>
	    </ul>
	</div>
	<div class="formLayout form2Pa">
	    <ul>
	        <li class="formTextarea">
	           <label>房间描述：</label>
	           <textarea name="guestroomDesc" id="guestroomDesc" maxlength="1000"></textarea>
	        </li>
	    </ul>
	</div>    
   	<div class="conOperation">
		<button type="button" id="guestRoomSaveBut" class="next"><span>保存</span></button>
		<button id="closeBtn" type="button" class="largeBtn"><span>关闭</span></button>
	</div>
</form:form>		

<script>
/**
 * 供应商新增客房页面
 * create by likg
 */
var GuestRoomForm={};

//保存客房信息
GuestRoomForm.save=function(){
	if(!$("#guestRoomForm").valid()){
		alert("请正确填写客房信息!");return;
	}
	
    if(window.confirm("确定保存客房信息吗?")){
        $("#guestRoomSaveBut").attr("disabled",true);
		var url = $('#initPath').val()+"/GuestRoomController.do?method=saveGuestRoom";

    	$('#guestRoomForm').ajaxSubmit({
    		url:url,
			dataType:'json',
			success:function(json){
    			$("#closeBtn").click();
			},
			error:function(msg){
				alert(JSON.stringify(msg));
				$("#guestRoomSaveBut").attr("disabled",false);
			}
		});	
	}
}

$(document).ready(function(){
	//预览图片
	$("#pictureFile").change(function(){
		var filePath = $("#pictureFile").val();
		var fileName = filePath.replace(/.+\\([^\\]+)/,'$1');
		var i = fileName.lastIndexOf('.');       	 //从右边开始找第一个'.'
		var len = fileName.length;                	 //取得总长度
		var str = fileName.substring(len,i+1);    	 //取得后缀名
		var exName = "PNG,BMP,JPG,GIF";       		 //允许的后缀名
		var k = exName.indexOf(str.toUpperCase());	 //转成大写后判断
		if(k==-1){                                	 //没有符合的
		    alert("上传文件错误！只能上传"+exName);
			this.value="";
		}else{
			$("#newPreview").find('img').attr("src",preViewPic(this));
		}
	});
	
	//保存
	$("#guestRoomSaveBut").click(function(){
		GuestRoomForm.save();
	})
	
	//关闭
	$("#closeBtn").click(function(){         
		$('#epsDialogClose').click(); 
	})

})
</script>