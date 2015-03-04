var confirmBailRecordPage={};

$(document).ready(function(){
	$("#openbidGeneralList").tabs();
	 $("li.ui-state-disabled").each(function(){
			$(this).attr("class","ui-state-default ui-corner-top ui-state-active");
		})
	$("li a.refreshData").click(function(){
		confirmBailRecordPage.currentTabID = $(this).attr("ids");
		if (!confirmBailRecordPage.currentTabID) {
			confirmBailRecordPage.currentTabID = this.ids;
		}
		$("#currentTabId").val(confirmBailRecordPage.currentTabID);
	})
	$("li a.refreshData:first").click();

	if($("#isNotFirst").val()=='NO'){ //如果是第一次，默认全选所有供应商
		$("input[name=confirmBailRecord]").attr("checked","checked");
	}
	
	
	$("input[name=falseBailRecord]").click(function(){
		if($(this).attr("checked")){
			if(window.confirm("该供应商末通过报名审核，是否继续操作？")){
				$("input[name=falseBailRecord]").attr("checked","checked");		
			}
		}
	})
})


