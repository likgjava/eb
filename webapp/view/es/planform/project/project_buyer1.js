var project_buyer = {};
$(document).ready(function(){
	$('#tabInfo').tabs();
	$("#tabValue").val("1");
	
	//采购公告
	$("#tabform1").click(function(){
		$("#tabValue").val("1");
		$("#tabform").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toPurBulletinRead&projectId='+$("#projectId").val());	
	})
	
	//采购文件
	$("#tabform2").click(function(){
		$("#tabValue").val("2");
		$("#tabform").empty().loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDocbuyer&projectId='+$("#projectId").val());
		
	})
	
	//项目规则
	$("#tabform3").click(function(){
		$("#tabValue").val("3");
		$("#tabform").empty().loadPage($('#initPath').val()+'/ProjProcessRuleController.do?method=toProjProcessRuleBuyer&projectId='+$("#projectId").val());
		
	})
	
	
	//报名信息
	$("#tabform4").click(function(){
		$("#tabValue").val("4");
		$("#tabform").empty().loadPage($('#initPath').val()+'/view/es/planform/signuprecord/supplierList.jsp?projectId='+$("#projectId").val());
	})
	
	//结果公告
	$("#tabform5").click(function(){
		$("#tabValue").val("5");
		$("#tabform").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=getRestBulletin&projectId='+$("#projectId").val());
	})
	
	//通知书
	$("#tabform6").click(function(){
		$("#tabValue").val("6");
		$("#tabform").empty().loadPage($('#initPath').val()+'/view/es/planform/noticemanage/supplierNoticeList.jsp?projectId='+$("#projectId").val());
	})
	
	//质疑
	$("#tabform7").click(function(){
		$("#tabValue").val("7");
		$("#tabform").empty().loadPage($('#initPath').val()+'/view/es/planform/oppugnrequisition/oppugnReqList.jsp?projectId='+$("#projectId").val());
	})
	/*$("#tabform").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toPurBulletin&projectId='+$("#projectId").val());
	$("#tabform1").click(function(){
		$("#tabValue").val("1");
		$("#tabform").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toPurBulletin&projectId='+$("#projectId").val());
		
	})
	$("#tabform4").click(function(){
		$("#tabValue").val("4");
		$("#tabform").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toPurBulletin&projectId='+$("#projectId").val());
		
	})
	$("#tabform2").click(function(){
		$("#tabValue").val("2");
		$("#tabform").empty().loadPage("view/test4.jsp?objId=2");
		
	})
	$("#tabform3").click(function(){
		$("#tabValue").val("3");
		$("#tabform").empty().loadPage("view/test4.jsp?objId=3");
	})*/
})