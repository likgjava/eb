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
			if(window.confirm("该供应商还末审核或末通过报名审核，是否继续操作？")){
				$(this).attr("checked","checked");		
			}
		}
	})
	
	
	$("#confirmBailRecordSave").click(function(){
		if(window.confirm("确定要提交吗？")){
			
			var bailRecordValueStr = '';
			$("input:checked").each(function(){
				var currentVal =  $(this).attr("id");
			    var bail = $(this).parent().parent().find('td').eq(2).html();
			    if(bail!=''&&bail!=null){
			    	  currentVal+=bail;
			    }
				bailRecordValueStr += currentVal + ',';
			})
			
			$("#confirmBailRecordSave").attr("disabled","disabled");
			$.getJSON($("#initPath").val()+"/BidController.do?method=saveConfirmBailRecord",{bailRecordValue:bailRecordValueStr},function(json){
	    	    planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			});
		}
	})
})


