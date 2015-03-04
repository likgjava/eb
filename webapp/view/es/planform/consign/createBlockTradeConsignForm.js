/*
 * 执行平台，委托书表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var consignForm={};
consignForm.lookTaskPlan = function(taskPlanId) { 
	$.epsDialog({
        title:"查看详情",
        url:$("#initPath").val()+'/BlockTradeController.do?method=showDetail&type=forSum&isAudit=isAudit&objId='+taskPlanId,
        width: 780,
        height: 550,
        onClose: function(){
	    }
	});    

}


//删除项目申报书
consignForm.deleteTaskPlan = function(taskPlanId,consignId) {
	  var num = $("#num").val();
	  if(num > 1) {
		  $.getJSON($('#initPath').val()+'/ConsignController.do?method=deleteTaskPlanForBlockTradeProject&taskPlanId='+taskPlanId+'&consignId='+consignId, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#conBody').loadPage($('#initPath').val()+'/ConsignController.do?method=toBlockTradeSaveOrUpdate&flag=update&objId='+$("#consignId").val());
			});
       } else {
			alert("至少要剩余一条申报书！！");
    }

	 
}

//添加申报书
consignForm.addTaskPlan = function() {
	$.epsDialog({
        title:"添加申报书",
        url:$("#initPath").val()+'/view/es/planform/taskplan/addTaskPlanListPage.jsp?consignId='+$("#consignId").val(),
        width: 800,
        height:400,
        isReload:false,
        onClose: function(){
			$('#conBody').loadPage($('#initPath').val()+'/ConsignController.do?method=toBlockTradeSaveOrUpdate&flag=update&objId='+$("#consignId").val());
	    }
	});  
}

//返回跳转
consignForm.setRetrun = function(){
 //表示为新增委托
$("#returnUrl").val($('#initPath').val()+"/view/es/planform/taskplan/blockTradeListForCreate.jsp");
}

$(document).ready(function(){
	//返回时跳转页面
	consignForm.setRetrun();
	$('#consignForm').validate();
	
	//日历控件
    $("#consTime").val(new Date().Format('yyyy-MM-dd')).epsDatepicker();
    $("#consFinishTime").epsDatepicker();
    
    //加载上传组件
    //$('#consContentsFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=consContentsAtt&attachRelaId='+$("#consContentsFile").text());
    
    //附件
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text()
	});
    
	 //随机抽取招标中心
    $("#selectOrgInfo").click(function(){
    	 $("input[id=consAgent.orgName]").attr("disabled","disabled"); 
    	 $("input[id=consAgent.orgName]").val("");
    	 $("input[id=consAgent.objId]").val("");
		$.epsDialog({
			id:"selectAgent",
	        title:"抽取招标中心",
	        url:$("#initPath").val()+"/view/es/planform/taskplan/blockTradeForRandomAgent.jsp",
	        width: 800,
	        height: 400,
	        isReload:false,
	        onClose: function(){
		        }
		});    


      });

   //录入招标中心
   $("#keyinOrgInfo").click(function(){
	   $("input[id=consAgent.orgName]").removeAttr("disabled"); 
	   $("input[id=consAgent.orgName]").focus();
	});


 //选择招标中心
	$("input[id=consAgent.orgName]").autocomplete(
		$('#initPath').val() + '/UserApiController.do?method=getAgentForFile', 
		{
			matchColumn:'orgName',//作为查询显示, 被选中之后匹配的列
			extraParams:{"agencyId":null,"agencyId_op":"!="},
			mustMatch: true,
			formatItem: function(data, i, total) {
				return data.orgName;
			},
			formatMatch: function(data, i, total) {
				return data.orgName;
			},
			formatResult: function(data) {
				return data.orgName;
			}
		}
	).result(function(event,data,formatted){
		if(data){
			$("input[id=consAgent.orgName]").val(data.orgName);//回填id
			$("input[id=consAgent.objId]").val(data.objId);//回填id
		}
	});

	//保存
	$('#consignSave').click(function(){
		if($('#form_one'))$('#form_one').remove();//移除附件form否则validator会出错
		if(!$('#consignForm').valid()){return;}
		if($("#consTime").val()>=$("#consFinishTime").val()){alert("拟完成时间应该在委托时间之后！");return;}
		if (confirm("确认保存委托协议吗？")) {
			if(!$('#consignForm').valid()){alert('请正确填写表单!');return;}
			var taskPlanIds ="";
			$("input[name='taskPlans']").each(function(){
				taskPlanIds += $(this).val()+',';		
			})
			$.getJSON($('#initPath').val()+'/ConsignController.do?method=createBlockTradeDraftConsign&taskPlanIds='+taskPlanIds, formToJsonObject('consignForm'), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/consign/blockTradeConsignList.jsp');
			});
		}
	});
	
	//提交
	$('#consignSubmit').click(function(){
		if($('#form_one'))$('#form_one').remove();//移除附件form否则validator会出错
		if($("#consTime").val()>=$("#consFinishTime").val()){alert("拟完成时间应该在委托时间之后！");return;}
		if(window.confirm('确定要提交招标中心确认吗？')){
			if(!$('#consignForm').valid()){alert('请正确填写表单!');return;}
			var taskPlanIds ="";
			$("input[name='taskPlans']").each(function(){
				taskPlanIds += $(this).val()+',';		
			})
			$.getJSON($('#initPath').val()+'/ConsignController.do?method=submitBlockTradeDraftConsign&taskPlanIds='+taskPlanIds, formToJsonObject('consignForm'), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/consign/blockTradeConsignList.jsp');
			});
		}
	});
});
