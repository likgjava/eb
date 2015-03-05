<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="_guestRoomPriceDialogID"  value="<c:out value="${param.dialogId}"/>"/>
<form:form id="guestRoomPriceForm" method="post">
	<input type="hidden" name="guestRoom.objId" value="<c:out value="${param.guestRoomId}"/>" />
	<div class="formLayout  form2Pa">
		<ul>
	    	<li class="fullLine">
	            <label>协议价(元)：</label>
	            <input type="text" id="agreePrice" name="agreePrice" class="required floats" />
	            <span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	            <label>开始时间：</label>
	            <input type="text" id="startTime_grp" name="startTime" class="required" />
	            <span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	            <label>结束时间：</label>
	            <input type="text" id="endTime_grp" name="endTime" class="required" />
	            <span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	            <label>是否含早餐：</label>
	            <input type="radio" id="hasBreakfast2" name="hasBreakfast" class="required" value="false" checked="checked"/>不含早餐&nbsp;&nbsp;
	            <input type="radio" id="hasBreakfast1" name="hasBreakfast" class="required" value="true"/>含早餐
	        </li>
	        <li class="fullLine">
	          	<label>早餐数量：</label>
	          	<input type="text" name="breakfastNum" id="breakfastNum" class="digits" disabled="disabled" maxlength="3"/>
    	  	</li>
	        <li class="formTextarea">
	           <label>备注：</label>
	           <textarea name="remark" id="remark" maxlength="500"></textarea>
	        </li>
	    </ul>
	    
    	<div class="conOperation">
			<button type="button" id="guestRoomPriceSaveBut" class="next"><span>保存</span></button>
			<button type="button" id="guestRoomPriceCloseBut" class="next"><span>关闭</span></button>
		</div>
	</div>
</form:form>		

<script>
/**
 * 供应商新增客房价格页面
 * create by likg
 */
var GuestRoomPriceForm={};

//保存客房价格信息
GuestRoomPriceForm.save=function(){
	if(!$("#guestRoomPriceForm").valid()){
		alert("请正确填写客房价格信息!");return;
	}
	
    if(window.confirm("确定保存客房价格信息吗?")){
        $("#guestRoomPriceSaveBut").attr("disabled",true);
		var url = $('#initPath').val()+"/GuestRoomPriceController.do?method=saveGuestRoomPrice";

    	$('#guestRoomPriceForm').ajaxSubmit({
    		url:url,
			dataType:'json',
			success:function(json){
    			$("#guestRoomPriceCloseBut").click();
			},
			error:function(msg){
				alert(JSON.stringify(msg));
				$("#guestRoomPriceSaveBut").attr("disabled",false);
			}
		});	
	}
}

$(document).ready(function(){
	$("#startTime_grp").epsDatepicker();
	$("#endTime_grp").epsDatepicker();
	
	//保存
	$("#guestRoomPriceSaveBut").click(function(){
		GuestRoomPriceForm.save();
	})
	
	//关闭
	$("#guestRoomPriceCloseBut").click(function(){
		if($("#_guestRoomPriceDialogID").val() != ""){
			$(document.getElementById($("#_guestRoomPriceDialogID").val())).find('.epsDialogClose').trigger('click');
		}else{
			$('.epsDialogClose').trigger('click');
		}
	});

	//是否包含早餐
	$("#hasBreakfast1").click(function(){
		$(this).parent().next().find("input").attr("disabled",false);
	});
	$("#hasBreakfast2").click(function(){
		$(this).parent().next().find("input").val("");
		$(this).parent().next().find("input").attr("disabled","disabled");
	});

})
</script>