<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="_updateGuestRoomPriceDialogID"  value="<c:out value="${param.dialogId}"/>"/>
<form:form id="guestRoomPriceForm" method="post">
	<input type="hidden" name="guestRoom.objId" value="${guestRoomPrice.guestRoom.objId}" />
	<input type="hidden" id="hasBreakfast" value="${guestRoomPrice.hasBreakfast}" />
	<div class="formLayout  form2Pa">
		<ul>
	    	<li class="fullLine">
	            <label>协议价(元)：</label>
	            <input type="text" id="agreePrice" name="agreePrice" value="${guestRoomPrice.agreePrice}" class="required floats" />
	            <span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	            <label>开始时间：</label>
	            <input type="text" id="startTime" name="startTime" value="<fmt:formatDate value="${guestRoomPrice.startTime}" pattern="yyyy-MM-dd"/>" class="required" />
	            <span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	            <label>结束时间：</label>
	            <input type="text" id="endTime" name="endTime" value="<fmt:formatDate value="${guestRoomPrice.endTime}" pattern="yyyy-MM-dd"/>" class="required" />
	            <span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	            <label>是否含早餐：</label>
	            <input type="radio" id="hasBreakfast2" name="hasBreakfast" class="required" value="false" />不含早餐
	            <input type="radio" id="hasBreakfast1" name="hasBreakfast" class="required" value="true" />含早餐&nbsp;&nbsp;
	        </li>
	        <li class="fullLine">
	          	<label>早餐数量：</label>
	          	<input type="text" name="breakfastNum" id="breakfastNum" class="digits" maxlength="3" value="${guestRoomPrice.breakfastNum}"/>
    	  	</li>
	        <li class="formTextarea">
	           <label>备注：</label>
	           <textarea name="remark" id="remark" maxlength="500">${guestRoomPrice.remark}</textarea>
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
 * 供应商修改客房价格页面
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
    			alert("修改成功!");
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
	$("#startTime").epsDatepicker();
	$("#endTime").epsDatepicker();
	
	//保存
	$("#guestRoomPriceSaveBut").click(function(){
		GuestRoomPriceForm.save();
	})
	
	//关闭
	$("#guestRoomPriceCloseBut").click(function(){
		if($("#_updateGuestRoomPriceDialogID").val() != ""){
			$(document.getElementById($("#_updateGuestRoomPriceDialogID").val())).find('.epsDialogClose').trigger('click');
		}else{
			$('.epsDialogClose').trigger('click');
		}
	});

	//是否包含早餐
	if($("#hasBreakfast").val()=='true'){
		$("#hasBreakfast1").attr('checked','checked');
	}else{
		$("#hasBreakfast2").attr('checked','checked');
		$("#hasBreakfast2").parent().next().find("input").val("");
		$("#hasBreakfast2").parent().next().find("input").attr("disabled",true);
	}
	$("#hasBreakfast1").click(function(){
		$(this).parent().next().find("input").attr("disabled",false);
	});
	$("#hasBreakfast2").click(function(){
		$(this).parent().next().find("input").val("");
		$(this).parent().next().find("input").attr("disabled",true);
	});
})
</script>