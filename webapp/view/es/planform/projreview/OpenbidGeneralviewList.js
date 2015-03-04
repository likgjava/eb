$(document).ready(function(){
	$("#openbidGeneralList").tabs();
	
	// 修改、新增 绑定按钮事件
	$("[identifier=openbid]").click(function(){
		// 初始化局部变量
		var thisObj = $(this);
		var trObj = $("#"+$(thisObj).attr("openBidRecordId")).parent();
		var tdObj = $(trObj).find('td[type=bidQuotesum]');
		var eventId = $(thisObj).attr("id");
		var jsonObj = formToJsonObject($(thisObj).attr("openBidRecordId"));
		
		// 保存或更新
		if("saveOrUpdate" == eventId){
			jsonObj.bidQuotesum = $(tdObj).find('input[type=text]').val();
			if(!/^[0-9.]+$/.test(jsonObj.bidQuotesum)) {
				alert("请输入合法报价！");
				return false;
			} 
			if($(trObj).find('input[name=objId]').val()==null||$(trObj).find('input[name=objId]').val()==""){
				$.getJSON($('#initPath').val()+'/OpenbidGeneralviewController.do?method=saveOpenbidGeneralview',jsonObj, function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
				   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				    } else {
				    	$("#myDesktop").click();
				    }
					
					// 回填列表
					$(thisObj).attr("id","");
					$(thisObj).html("<span>修改</span>");
	
					
					// 回填数据
					$(tdObj).html(jsonObj.bidQuotesum).css("text-align","right");
					$(trObj).find('input[name=objId]').val(json.openbidGeneralview.objId);
					$(trObj).find('input[name=bidQuotesum]').val(json.openbidGeneralview.bidQuotesum);
				})
			} else {
				$.getJSON($('#initPath').val()+'/OpenbidGeneralviewController.do?method=updateOpenbidGeneralview',jsonObj, function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					
					// 回填列表
					$(thisObj).attr("id","");
					$(thisObj).html("<span>修改</span>");
				
					
					// 回填数据
					$(tdObj).html(jsonObj.bidQuotesum).css("text-align","right");
					$(trObj).find('input[name=objId]').val(json.openbidGeneralview.objId);
					$(trObj).find('input[name=bidQuotesum]').val(json.openbidGeneralview.bidQuotesum);
				})
				
			}
			
		}
		
		// 回填文本框
		if("" == eventId){
			var html = '<input type="text" style="width:100px;border-color: blue" value="'+jsonObj.bidQuotesum+'"></input>';
			$(tdObj).html(html).css("text-align","left");
			$(thisObj).html("<span>保存</span>");
			$(thisObj).attr("id","saveOrUpdate");
		}
	})
})