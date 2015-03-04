var putInOpenbidGeneralviewList={};

$(document).ready(function(){
	$("#openbidGeneralList").tabs();
	
	$("li.ui-state-disabled").each(function(){
		$(this).attr("class","ui-state-default ui-corner-top ui-state-active");
	})
	
	$("li a.refreshData").click(function(){
		putInOpenbidGeneralviewList.currentTabID = $(this).attr("ids");
		if (!putInOpenbidGeneralviewList.currentTabID) {
			putInOpenbidGeneralviewList.currentTabID = this.ids;
		}
		$("#currentTabId").val(putInOpenbidGeneralviewList.currentTabID);
	})
	$("li a.refreshData:first").click();
   
	$("td.genviewDefinetd").each(function(){
		var num = $(this).find("input").length;
		if(num==0){
		//	$(this).append("<input type='text' value='' class='required'>");
		}
	})
	
	//判断时间是否可以录入开标一览表内容
	var isOpenBid = $("#isOpenBid").val();
	if(isOpenBid != 'YES'){
		$("button").attr("disabled","disabled");
		$("#message").append("<span style='color:red'>开标时间未到，不能录入开标一览表！</span>")
	}

})


