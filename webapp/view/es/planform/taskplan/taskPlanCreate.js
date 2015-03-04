 /*
 * 执行平台，采购需求 表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var taskPlanForm={};
//加载附件
taskPlanForm.loadAttachment = function(itemId){
	//附件
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
	$("#taskPlanDetailListView").empty().load($('#initPath').val()+'/view/es/planform/taskplan/taskPlanDetailList.jsp');
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



// 返回
$("button[name=return_to_list]").click(function(){
	if($("#task_plan_form").length==0){
		$('#conBody').loadPage($('#initPath').val()+'/view/srplatform/portal/myDesktop.jsp');
	}else{
		$("#task_plan_list").show();
		$("#task_plan_form").empty().hide();
		$("#taskPlanGrid").reload();
	}
})

$(document).ready(function(){
	//加载附件
	$('#attachId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachId").text()
	});
	
	var dateObj = {};
	
	//时间控件
    $("#applyDate").val(new Date().Format('yyyy-MM-dd')).epsDatepicker();
    var finishdate = new Date();
    finishdate.setDate(finishdate.getDate()+31);//取得系统时间的前一天,重点在这里,负数是前几天,正数是后几天 
    $("#finishDate").val(finishdate.Format('yyyy-MM-dd')).epsDatepicker();
     			
    //判断id是否为空
    if ($("#objId").val()=='') {
    	var date = new Date();
    	$("#epsTabs1").hide();  //隐藏下面的子项
    	$("#epsTabs2").hide();  //隐藏下面的子项
    } else {
    	//加载子项
    	taskPlanForm.showSubsAndDetails();
    } 			
	//业主单位
	$("#yzdw_name").click(function(){
		$.epsDialog({
	        title:'选择',
	        url:$("#initPath").val()+'/view/bizplatform/organization/manager/select_orginfo.jsp?orgType=b&Hname=yzdw_name&Hid=yzdw_id'
	    })
	})

	//选择代理机构
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
//	//选择招标中心项目负责人
//	$("#taskAgentName").autocomplete(
//		$('#initPath').val() + '/OrgInfoController.do?method=getObjectQuery&queryColumns=objId,orgName', 
//		{
//			matchColumn:'orgName',//作为查询显示, 被选中之后匹配的列
//			extraParams:{"agencyId":null,"agencyId_op":"!="},
//			mustMatch: true,
//			formatItem: function(data, i, total) {
//				return data.orgName;
//			},
//			formatMatch: function(data, i, total) {
//				return data.orgName;
//			},
//			formatResult: function(data) {
//				return data.orgName;
//			}
//		}
//	).result(function(event,data,formatted){
//		if(data){
//			$("#taskAgentName").val(data.orgName);//回填id
//			$("input[id=taskAgent.objId]").val(data.objId);//回填id
//		}
//	});
	// 保存
	$('#taskPlanSave').click(function(){
		if(!$('#taskPlanForm').valid()){alert('请正确填写表单!');return;}
		if($('#departmentLinker').val()=='-1') {
			alert('请选择招标中心项目负责人');
			return;
		}
		var applyDate = $('#applyDate').val();
		var finishDate = $('#finishDate').val();
		if(applyDate>finishDate){
			alert('希望完成日期应大于申请日期！');
			return false;
		}
		// 验证是否选择联系人
		var govLinker = $("#govLinker").val();
//		if(null == govLinker || "" == govLinker){
//			alert("请选择业务处室联系人！");
//			return false;
//		}
		$("#taskPlanSave").attr("disabled","disabled");
		
		//附件ID
		var attachRelaId=$("input[name=attachRelaId]").val();
		
		$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=saveTaskPlan&attachRelaId='+attachRelaId, formToJsonObject('taskPlanForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$("#taskCodeSpan").empty().append(json.taskCode);
			$("#taskCode").val(json.taskCode);
			//隐藏“下一步”
			$("#taskPlanSave").hide();
			if($("#objId").val() == ''){
			$("#objId").val(json.taskPlanId);
//				//显示子项和提交按钮
//				$("#epsTabs1").show();
//				$("#epsTabs2").show();
//			    //加载附件信息部分第一个TabPanel的数据
//				$('#epsTabs2').find("li").each(function(i,n){
//						$(this).find(">a").click();
//						return false;
//				});
//				$("#taskPlanSubmit").show();
//				taskPlanForm.showSubsAndDetails();
//				$("#taskPlanGrid").reload();
			
			$("button[name=return_to_list]").click();
			}
		});
	});
	
	//保存为正式
	$('#taskPlanSubmit').click(function(){
			//判断是否添加了需求书条目和资金条目，若没有，则提示
			if(!$('#taskPlanForm').valid()){alert('请正确填写表单!');return;}
			if(!$('#taskPlanForm').valid()){alert('请正确填写表单!');return;}
			if($('#departmentLinker').val()=='-1') {
				alert('请选择招标中心项目负责人');
				return;
			}
//			var sub_rows=$('#taskPlanSubGrid').flexOptions()[0].p.rows;
//			var detail_rows=$('#taskPlanDetailGrid').flexOptions()[0].p.total;
//			if(sub_rows == 0){
//				alert("请填写“需求书明细”！");
//				return;
//			}
//			if(detail_rows == 0){
//				alert("请填写“采购资金明细”！");
//				return;
//			}
			// 验证是否选择联系人
			var govLinker = $("#govLinker").val();
//			if(null == govLinker || "" == govLinker){
//				alert("请选择业务处室联系人！");
//				return false;
//			}
			var b=taskPlanForm.formatNumber(Number($("#moneySub").val())-Number($("#moneyDetail").val()));
//			if(Number($("#moneySub").val())>Number($("#moneyDetail").val())){alert("需求书明细超出预算￥"+b+"元,请调整需求书明细的资金或采购资金明细的预算金额!");return false;}
			$("#taskPlanSubmit").attr("disabled","disabled");
			$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=submitTaskPlan&attachRelaId='+attachRelaId,formToJsonObject('taskPlanForm'), function(json){
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
				$("#task_plan_form").empty().hide();
				$("#taskPlanGrid").reload();
			});
	});
});
