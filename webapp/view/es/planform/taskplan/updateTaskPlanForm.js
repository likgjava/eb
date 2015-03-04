/*
 * 执行平台，采购申报书 表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var taskPlanForm={};
/*加载附件*/
taskPlanForm.loadAttachment = function(itemId){
	$('#TPFFSUploadAttachment').loadPage($('#initPath').val()+'/view/srplatform/upload/extAttachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attBizId:$("#objId").val(),
		attBizType:"TASKPLAN",
		itemId:itemId,
		readOnly:'no'
	});
}
//加载子项
taskPlanForm.showSubsAndDetails = function(){
	$("#taskPlanSubListView").empty().load($('#initPath').val()+'/view/es/planform/taskplan/taskPlanSubList.jsp');
}
taskPlanForm.formatNumber=function(number){
	var number = ""+number;
	if(number.length > 3){
	   var mod = number.length%3;
	   var put = (mod > 0 ? (number.substring(0,mod)) : "");
	   var j=(number.length-mod)/3;
	   for(i=0;i<j;i++){
	    if(mod==0&&i==0){
	     put+=number.substring(mod+3*i,mod+3*i+3);
	    }else if(mod==0&&i!=0){
	     put+=","+number.substring(mod+3*i,mod+3*i+3);
	    }else {
	     put+=","+number.substring(mod+3*i,mod+3*i+3);
	    }
	   }
	   number=put;
	}
	return number;
	}


$("[name=taskType]").change( function() {
	 selectEbuyMethod(this.value);
}); 


function  selectEbuyMethod(taskType){  //根据申报书类型值返回采购方式值
	var ebuyMethodArray = new Array();
	if(taskType=='01'){//政府采购
		ebuyMethodArray.push(['00','公开招标'],['01','邀请招标'],['02','竞争性谈判'],['03','询价'],['04','单一来源']);
	}else if(taskType=='02'){//土地交易
		ebuyMethodArray.push(['08','挂牌'],['09','竞标'],['10','拍卖']);
	}else if(taskType=='03'){//产权交易
		ebuyMethodArray.push(['06','竞价']);
	}else if(taskType=='04'){//建筑工程
		ebuyMethodArray.push(['00','公开招标'],['01','邀请招标']);
	}else if(taskType=='05'){
		ebuyMethodArray.push(['15','随机抽取'],['16','综合评估']);
	}
 var selectObj = $("[name=ebuyMethod]");
 selectObj.find('option').remove();
 for(var i=0;i<ebuyMethodArray.length;i++){
	 selectObj.append("<option class='required' value="+ebuyMethodArray[i][0]+">"+ebuyMethodArray[i][1]+"</option>");
 }
	 selectObj.val(ebuyMethodArray[0]);
}


// 返回
$("button[name=return_to_list]").click(function(){
	if($("#task_plan_form").length==0){
		$('#conBody').loadPage($('#initPath').val()+'/view/srplatform/portal/myDesktop.jsp');
	}else{
		$("#task_plan_list").show();
		$("#task_plan_form").empty().hide();
	}
})

$(document).ready(function(){
	var ebuyMethodVal = $("[name=ebuyMethod]").val();
	selectEbuyMethod($('[name=taskType]').val());
	$("[name=ebuyMethod]").val(ebuyMethodVal);
	
	var dateObj = {};
	
	//加载tabs
	$('#epsTabs1').tabs();
	$("#epsTabs2").tabs();//格式化附件上传部分
	
	//时间控件
    $("#applyDate").val(new Date().Format('yyyy-MM-dd')).epsDatepicker();
    var finishdate = new Date();
    finishdate.setDate(finishdate.getDate()+31);//取得系统时间的前一天,重点在这里,负数是前几天,正数是后几天 
    $("#finishDate").val(finishdate.Format('yyyy-MM-dd')).epsDatepicker();
     			
    //判断id是否为空
    if ($("#objId").val()=='') {
    	var date = new Date();
    	//$("#taskName").val(date.getFullYear()+"-公开招标采购");   添加了这一默认值后，会引导用户不去修改名称，导致申报书名称一致的问题
    	$("#epsTabs1").hide();  //隐藏下面的子项
    	$("#epsTabs2").hide();//隐藏附件上传部分
    	$('#taskPlanSubmit').hide();  //隐藏提交审核按钮
    	$('#taskPlanUpdate').hide();  //隐藏保存按钮
    } else {
    	//加载子项
    	taskPlanForm.showSubsAndDetails();
    } 			
    /*加载附件信息部分第一个TabPanel的数据*/
	$('#epsTabs2').find("li").each(function(i,n){
			$(this).find(">a").click();
			return false;
	});
	
	
	//选择招标中心
	$("#taskAgentName").autocomplete(
		$('#initPath').val() + '/OrgInfoController.do?method=getObjectQuery&queryColumns=objId,orgName', 
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
			$("#taskAgentName").val(data.orgName);//回填id
			$("input[id=taskAgent.objId]").val(data.objId);//回填id
		}
	});
	
	$('#taskPlanUpdate').click(function(){//修改保存
		$('#taskPlanUpdate').attr("disabled","disabled");
		$('#taskPlanSave').click();
		$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/taskplan/taskPlanList.jsp');
	});
	
	// 保存
	$('#taskPlanSave').click(function(){
		if(!$('#taskPlanForm').valid()){alert('请正确填写表单!');return;}
		var applyDate = $('#applyDate').val();
		var finishDate = $('#finishDate').val();
		if(applyDate>finishDate){
			alert('希望完成日期应大于申请日期！');
			return false;
		}
		$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=updateTaskPlanTwain', formToJsonObject('taskPlanForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
		});
	});
	
	//保存为正式
	$('#taskPlanSubmit').click(function(){
			//判断是否添加了申报书条目和资金条目，若没有，则提示
			if(!$('#taskPlanForm').valid()){alert('请正确填写表单!');return;}
			var sub_rows=$('#taskPlanSubGrid').flexOptions()[0].p.rows;
			if(sub_rows == 0){
				alert("请填写“申报书明细”！");
				return;
			}

			if(window.confirm('确定要提交到代理机构立项吗？')){
				$("#taskPlanSubmit").attr("disabled","disabled");
				$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=submitTaskPlanTwain',formToJsonObject('taskPlanForm'), function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					if('desktop' == $('#paramType').val()){// 从桌面进入,返回到桌面
						$("#myDesktop").click();
						return false;
					}
					if('desktopList' == $('#paramType').val()){// 从桌面列表进入,返回到列表
						var url = 'TaskPlanController.do?method=getMoreForTaskPlanList&useStatus=01&confirmStatus=04&auditDetail=04';
		    			$('#conBody').loadPage($('#initPath').val()+'/'+url);
						return false;
					}
					$("#task_plan_list").show();
					$("#epsTabs2").show();//显示附件上传部分
					$("#task_plan_form").empty().hide();
					$("#taskPlanGrid").reload();
				});
			}
				
	});
	//查看历史
	$('#historyId').click(function(){
		$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=00');
	});
});
