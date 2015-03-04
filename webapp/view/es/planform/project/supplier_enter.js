$(document).ready(function(){
	
	$("#supplierEnter").click(function (){
	$("#supplierEnterDetail").toggle("slow");
	$("#supplierEnter span").toggleClass("collapsable");	
	})
	$("#submitBtn").click(function(){
		  
		if($(".name").val()=='')
		  {
			  alert("投标单位名称为必填项");
		  }
		  else if($(".required").val()=='')
		  {
			  alert("联系人为必填项");
		  }
		  else if($(".IdCard").val()=='')
		  {
			  alert("身份证号码为必填项");
		  }
		  else if(window.confirm("确定要报名吗?")){
			alert('报名成功');
		  }
	})
	
	//加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	
	//返回上一步
	$("#lastStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/bid_notice_release.jsp");
	})
	//进行下一步
	$("#nextStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/supplier_affirm.jsp");
	})
})