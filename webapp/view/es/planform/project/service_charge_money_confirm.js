var serviceChargeMoneyConfirm = {};
var backOffice = {};

serviceChargeMoneyConfirm.success = function(){
	$("#serviceChargeMoneyConfirmGrid").flexAddOptionStr({
		  '<button class="btn" type="button"><span><span>确认</span></span></button>' : function(btn,rowId,obj){
				 btn.click(function(){
					 backOffice.backMoney(rowId);
				 })
				 .appendTo(obj)
			 }
	})
}
backOffice.backMoney=function(rowId){
	alert("确认成功");
}

$(document).ready(function(){ 
	$("#beginAffirm").click(function (){
		$("#affirmProcess").toggle("slow");
		$("#beginAffirm span").toggleClass("collapsable");	
	})
	
	$("#next").click(function(){	
		$("#projectContent").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/release_challenge.jsp");
	})
	$("#pre").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/service_charge_money.jsp");
	})
	$("#history").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
});


