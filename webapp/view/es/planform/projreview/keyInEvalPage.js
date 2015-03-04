var keyInEvalPage ={};
$(document).ready(function(){
	var count = $("#expertCount").val();
	var quoteSum = $("#quoteSum").val();
	var projectId = $("#projectId").val();
	var supplierId = $("#supplierId").val();
	var subProjId = $("#subProjId").val();
	
	keyInEvalPage.remove = function(count){
		var thisObj = $("a[evaSellerRecordId="+count+"]");
	
		if(window.confirm("确认删除 ?")){
			$.getJSON($('#initPath').val()+'/EvaSellerRecordController.do?method=remove',{objId:count},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
	    	});
			thisObj.parent().parent().remove();
		}
	}
	// 修改、新增 绑定按钮事件	
	keyInEvalPage.addOrUpdate = function(count){
		// 初始化局部变量
		var thisObj = $("a[openBidRecordId="+count+"]");
		var trObj = $("#"+$(thisObj).attr("openBidRecordId")).parent().parent();
		var tdfactorScoreObj = $(trObj).find('td[type=factorScore]');
		var tdevalLinkerObj = $(trObj).find('td[type=evalLinker]');
		var tdrecommendObj = $(trObj).find('td[type=recommend]');
		var eventId = $(thisObj).attr("id");
		var jsonObj = formToJsonObject($(thisObj).attr("openBidRecordId"));
	
		// 保存或更新
		if("saveOrUpdate" == eventId){
			jsonObj.evalLinkerName = $(tdevalLinkerObj).find('input[type=text]').val();
		    jsonObj.recommend = $(tdrecommendObj).find('input[type=radio]:checked').val();
			jsonObj.factorScore = $(tdfactorScoreObj).find('input[type=text]').val();
			if(''==jsonObj.evalLinkerName){
				alert("请输入专家名称！");
				return false;
			}
			if(!/^(-|\+)?[0-9.]+$/.test(jsonObj.factorScore)) {
				alert("请输入数字！");
				return false;
			} 
			if(jsonObj.factorScore<0 ||jsonObj.factorScore>100){
				alert("请输入0-100数字！");
				return false;
			}
			if($(trObj).find('input[name=objId]').val()==null||$(trObj).find('input[name=objId]').val()==""){
				$.getJSON($('#initPath').val()+'/EvaSellerRecordController.do?method=saveAllEvaSellerRecord',jsonObj, function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					//planTemplateTask.fulfillPlan($('#projectTaskId').val());// 完成工作计划
					
					// 回填列表
					$(thisObj).attr("id","");
					$(thisObj).html("<span>修改</span>");
					if("" == jsonObj.objId){
						$(trObj).find('td').eq(4).html("已录入");
					}
					
					// 回填数据
					$(tdfactorScoreObj).html(jsonObj.factorScore).css("text-align","center");
					$(tdevalLinkerObj).html(jsonObj.evalLinkerName).css("text-align","center");
					if(jsonObj.recommend=='00'){
						$(tdrecommendObj).html("不推荐").css("text-align","center");
					}
					if(jsonObj.recommend=='01'){
						$(tdrecommendObj).html("推荐").css("text-align","center");
					}
					
					$(trObj).find('input[name=objId]').val(json.evaSellerRecord.objId);
					$(trObj).find('input[name=recommend]').val(json.evaSellerRecord.recommend);
					$(trObj).find('input[name=factorScore]').val(json.evaSellerRecord.factorScore);
					$(trObj).find('input[name=evalLinkerName]').val(json.evaSellerRecord.evalLinkerName);
				})
			}else {
				$.getJSON($('#initPath').val()+'/EvaSellerRecordController.do?method=updateAllEvaSellerRecord',jsonObj, function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					//planTemplateTask.fulfillPlan($('#projectTaskId').val());// 完成工作计划
					
					// 回填列表
					$(thisObj).attr("id","");
					$(thisObj).html("<span>修改</span>");
					if("" == jsonObj.objId){
						$(trObj).find('td').eq(4).html("已录入");
					}
					
					// 回填数据
					$(tdfactorScoreObj).html(jsonObj.factorScore).css("text-align","center");
					$(tdevalLinkerObj).html(jsonObj.evalLinkerName).css("text-align","center");
					if(jsonObj.recommend=='00'){
						$(tdrecommendObj).html("不推荐").css("text-align","center");
					}
					if(jsonObj.recommend=='01'){
						$(tdrecommendObj).html("推荐").css("text-align","center");
					}
					
					$(trObj).find('input[name=objId]').val(json.evaSellerRecord.objId);
					$(trObj).find('input[name=recommend]').val(json.evaSellerRecord.recommend);
					$(trObj).find('input[name=factorScore]').val(json.evaSellerRecord.factorScore);
					$(trObj).find('input[name=evalLinkerName]').val(json.evaSellerRecord.evalLinkerName);
				})
			}
			
		}
		
		// 回填文本框
		if("" == eventId){
			var html = '<input type="text" style="width:60px;" value="'+jsonObj.factorScore+'"></input>';
			var html1 = '<input type="text" style="width:100px;" value="'+jsonObj.evalLinkerName+'"></input>';
			var html2 = '<input type="radio"  name="recommend_'+thisObj.attr("openBidRecordId")+'" value="01" checked="checked">推荐 <input type="radio"  name="recommend_'+thisObj.attr("openBidRecordId")+'" value="00">不推荐'
			$(tdfactorScoreObj).html(html).css("text-align","center");
			$(tdevalLinkerObj).html(html1).css("text-align","center");
			$(tdrecommendObj).html(html2).css("text-align","center");
			$(thisObj).html("<span>保存</span>");
			$(thisObj).attr("id","saveOrUpdate");
		}
	}
	
	
	
	
	
	
	keyInEvalPage.insertRow = function(){
		var html = "";
		html+= "<tr>";
		html+="<td class=\"hidden\">";
		html+= "<form id='"+count+"'>"
		html+="<input type='hidden' name='objId' value=''></input>"
		html+="<input type='hidden' name='supplierId' value='"+supplierId+"'></input>";		
		html+="<input type='hidden' name='supplierName' value=''></input>";
		html+="<input type='hidden' name='quoteSum' value='"+quoteSum+"'></input>";
		html+="<input type='hidden' name='factorScore' value=''></input>";
		html+="<input type='hidden' name='recommend' value=''></input>";
		html+="<input type='hidden' name='num' value=''></input>";
		html+="<input type='hidden' name='subProjId' value='"+subProjId+"'></input>";
		html+="<input type='hidden' name='projectId' value='"+projectId+"'></input>";
		html+="<input type='hidden' name='evalLinkerName' value=''></input>";
		html+="</form>";
		html+="</td>";
		html+="<td style='text-align: center' type='evalLinker'></td>";
		html+="<td style='text-align: center' type='factorScore'></td>";
		html+="<td  type='recommend' class='center'>未录入</td>";
		html+="<td style='text-align: center'>未录入</td>";
		html+="<td style='text-align: center'><a class='abtn' openBidRecordId='"+count+"'  onclick='keyInEvalPage.addOrUpdate("+count+");' identifier='keyinbid'><span>录入</span></a></td>"
		html+="</tr>";
		$("#addExpert").append(html);	
		keyInEvalPage.addOrUpdate(count);
		count++;
	}
	
	
	//返回事件
	$("#returnBtn").click(function(){
		$('#epsDialogCloseReload').click();
	})
	if($("#expertCount").val()==100){
		keyInEvalPage.insertRow();
	}
	

	
})