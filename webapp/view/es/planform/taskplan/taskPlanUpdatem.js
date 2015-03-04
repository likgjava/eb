/*
 * 执行平台，采购申报书 表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var taskPlanForm={};
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


$("[name=taskType]").change( function() {
	 selectEbuyMethod(this.value);
}); 


function  selectEbuyMethod(taskType){  //根据申报书类型值返回采购方式值
	var ebuyMethodArray = new Array();
	if(taskType=='01'){//政府采购
		ebuyMethodArray.push(['00','公开招标'],['01','邀请招标']);
	}else if(taskType=='02'){//土地交易
		ebuyMethodArray.push(['08','挂牌'],['09','竞标'],['10','拍卖']);
	}else if(taskType=='03'){//产权交易
		ebuyMethodArray.push(['06','竞价']);
	}else if(taskType=='04'){//建筑工程
		ebuyMethodArray.push(['00','公开招标'],['01','邀请招标']);
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
	//加载附件
	$('#attachId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").val()
	});
	
	var ebuyMethodVal = $("[name=ebuyMethod]").val();
	selectEbuyMethod($('[name=taskTypes]').val());
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
	
//	//选择业务处室
//	$("#governmentName").autocomplete(
//		$('#initPath').val() + '/UserApiController.do?method=getDepartmentByQueryObject&queryColumns=objId,name,contact,tel', 
//		{
//			matchColumn:'name,shortSpellName',//作为查询显示, 被选中之后匹配的列
//			extraParams:{
//			'status':'1',
//			'status_relative':'[and]'},
//			mustMatch: true,
//			formatItem: function(data, i, total) {
//				return data.name;
//			},
//			formatMatch: function(data, i, total) {
//				return data.name;
//			},
//			formatResult: function(data) {
//				return data.name;
//			}
//		}
//	).result(function(event,data,formatted){
//		if(data){
//			dateObj = data;
//			$("#governmentName").val(data.name);//回填id
//			$("input[id=government.objId]").val(data.objId);//回填id
//			$("#govLinkerTel").val(data.tel); //联系电话
//			$("#govLinkerSpan").empty().append('');
//			$("#govLinker").val('');
//			
//			// 选择联系人(多选)
//			$.epsDialog({
//				id:"select_linker",
//		    	title:'请选择&nbsp;'+data.name+'&nbsp;联系人',
//		    	url:$('#initPath').val()+'/TaskPlanController.do?method=toSelectGovernmentLinkerView&params='+data.objId,
//		    	width: '480',
//		    	height: '270',
//		    	isReload: false
//			});
//		}
//	});
	
	//业主单位
	$("#yzdw_name").click(function(){
		$.epsDialog({
	        title:'选择',
	        url:$("#initPath").val()+'/view/bizplatform/organization/manager/select_orginfo.jsp?orgType=b&Hname=yzdw_name&Hid=yzdw_id'
	    })
	})
	
//	//选择招标中心
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
		// 验证是否选择联系人
		var govLinker = $("#govLinker").val();
//		if(null == govLinker || "" == govLinker){
//			alert("请选择业务处室联系人！");
//			return false;
//		}
		var attachRelaId=$("input[name=attachRelaId]").val();
		$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=updateTaskPlanTwain&attachRelaId='+attachRelaId, formToJsonObject('taskPlanForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
		});
	});
	
	//保存为正式
	$('#taskPlanSubmit').click(function(){
			//判断是否添加了申报书条目和资金条目，若没有，则提示
			if(!$('#taskPlanForm').valid()){alert('请正确填写表单!');return;}
//			var sub_rows=$('#taskPlanSubGrid').flexOptions()[0].p.rows;
//			var detail_rows=$('#taskPlanDetailGrid').flexOptions()[0].p.total;
//			if(sub_rows == 0){
//				alert("请填写“申报书明细”！");
//				return;
//			}
//			if(detail_rows == 0){
//				alert("请填写“采购资金明细”！");
//				return;
//			}
			// 验证是否选择联系人
//			var govLinker = $("#govLinker").val();
//			if(null == govLinker || "" == govLinker){
//				alert("请选择业务处室联系人！");
//				return false;
//			}
//			var b=taskPlanForm.formatNumber(Number($("#moneySub").val())-Number($("#moneyDetail").val()));
//			if(Number($("#moneySub").val())>Number($("#moneyDetail").val())){alert("申报书明细超出预算￥"+b+"元,请调整申报书明细的资金或采购资金明细的预算金额!");return false;}
			$("#taskPlanSubmit").attr("disabled","disabled");
			$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=submitTaskPlan',formToJsonObject('taskPlanForm'), function(json){
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
	});
	//查看历史
	$('#historyId').click(function(){
		$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=00');
	});
});
