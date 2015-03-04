var openbidGeneralview = {};

$(document).ready(function(){
	var thisTime = $("#thisTime").val();
	var openBidSDate = $("#openBidSDate").val().substr(0,19);
	if(thisTime<openBidSDate){//当前时间小于开标时间
		$("#openBidGeneralVitemSave").attr("disabled","disabled");
	    $("#message").text("还未到开标时间,不能保存！").css("color","red").css("margin-top","10px");
	}
	var num = $("#num").val();
	$("#openbidGeneralList").tabs();
	
	openbidGeneralview.insertRow = function(packId){
		var html = "";	
		html += "<tr id='"+num+"_tr'>";
		html += "<td width='15%'><select  name='"+num+"_supplierId' id='"+num+"_supplierId' class='required' onChange=openbidGeneralview.chooseSupplierName(\""+num+"\");></select>";
		html += "<input type='hidden' name='"+num+"_supplierName' id='"+num+"_supplierName' value=''/>";
		html += "<input type='hidden' name='"+num+"_subProjectId' id='"+num+"_subProjectId' value='"+packId+"'/></td>";
		html += "<td width='5%'><input style='width:80%;' type='text' name='"+num+"_quotesum' id='"+num+"_quotesum' class='required money'/></td>";
		var vitemNum = $("#openbidGeneralview_"+packId).parent().find("th").length ;
		var n = 0;
		$("#openbidGeneralview_"+packId).parent().find("th").each(function(){
			var message = $(this).attr("id");
			if(message!=null&&message!=''&&message!='undefined'){
				html += "<td width='5%'><input style='width:80%;' type='text' id='"+num+"_openBidGeneralVitem_"+n+"' name='"+num+"_openBidGeneralVitem_"+n+"' value=''  class='required'/>";
			    html += "<input type='hidden' id='"+num+"_openBidGeneralVitemValue_"+n+"' name='"+num+"_openBidGeneralVitemValue_"+n+"' value='"+message+"'/></td>";
			    n++;
			}
		})
		html += "<td width='15%'><input type='file' name='"+num+"_attrFile' id='"+num+"_attrFile' class='required'/>";
		html += "<input type='hidden' name='"+num+"_isUploadFile' value='false'/>";
		html += "<input type='hidden' name='"+num+"_attrId'/>";
		html += "</td>";
		html += "<td width='10%'><a href='#' onClick='openbidGeneralview.removeOpenbidGeneralview(\""+num+"\");' id='removeOpenbidGeneralview${"+num+"}'>删除</a></td>";
		html += "</tr>";
		$("#openbidGeneralview_"+packId).append(html);
		openbidGeneralview.getSupplier(num,$("#_project_id").val(),packId);
		num ++;
		$("#n").val(n);
	}
	
	$("#openBidGeneralVitemSave").click(function(){
		$("input[name$=_attrFile]").each(function(){
			var index =  $(this).attr('name').substring('0','1');
			if($(this).val()==''){
				$("input[name="+index+"_isUploadFile]").val('false');
			}else{
				$("input[name="+index+"_isUploadFile]").val('true');
			}
			
			if($("input[name="+index+"_attrId]").val()!=''){
				$(this).attr('class','');
			}
		})
		
		if(!$('#openbidGeneralviewForm').valid()){
			return false;
		}
		
		var jsonObj = formToJsonObject('openbidGeneralviewForm','jsonUtils');
	
		
		var projectId = $("#_project_id").val();
		$('#openbidGeneralviewForm').ajaxSubmit({
			url:$('#initPath').val()+"/OpenbidGeneralviewController.do?method=saveOpenbidGeneralviewMessage&num="+num+"&n="+$("#n").val()+"&projectId="+projectId,
			dataType:'json',
			success:function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
					planTemplateTask.clickMethod($("#projectTaskId").val()+"");
					planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				  } else {
					$("#myDesktop").click();
				}
			},
			error:function(msg){
				alert(msg);
			
			}
		});

		
	})

})

	openbidGeneralview.removeOpenbidGeneralview = function(num){
		$("#"+num+"_tr").remove();
	}

    openbidGeneralview.getSupplier = function(num,projectId,packId){
    	$.getJSON($("#initPath").val()+"/SignUprecordController.do?method=getSignUpListByProjectId",{projectId:projectId,packId:packId},function  (json){
    		var signlist = json.signList;
    		if(signlist.length>0){
    			$("input[name="+num+"_supplierName]").val(signlist[0].supplier.orgName);
    			for(var i=0;i<signlist.length;i++){
    				$("select[name="+num+"_supplierId]").append("<option  value='"+signlist[i].supplier.objId+"'>"+signlist[i].supplier.orgName+"</option>");
    			}    			
    		}
    	});
	}
	
    openbidGeneralview.chooseSupplierName = function(num){//获取投标单位名称
    	$("input[name="+num+"_supplierName]").val($('#'+num+'_supplierId').find('option:selected').text());
    }
