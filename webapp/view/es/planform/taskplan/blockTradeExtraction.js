/*
 * 执行平台，大宗交易
 * author: 杨兴
 * mail: yangx@gpcsoft.com
 */
var blockTradeExtraction={};

$(document).ready(function(){
	//日历控件
	$('#applyDate').epsDatepicker();
	
	//录入招标中心
	$('#AgentSave').click(function(){
		var flag = $(":radio:checked").val();
		if("AgentInput"==flag){
			//录入招标中心
			$.epsDialog({
		        title:"录入招标中心",
		        url:$("#initPath").val()+"/BlockTradeController.do?method=getAgents&type=1",
		        width: 800,
		        height: 400,
		        isReload: false,
		        onClose: function(){
		       	}
			});
		}else{			
			//开始抽取招标中心
				$.epsDialog({
			        title:"抽取招标中心",
			        url:$("#initPath").val()+"/BlockTradeController.do?method=getAgents&type=2",
			        width: 800,
			        height: 400,
			        isReload: false,
//			        onClose: function(){
//						$('#conBody').loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfoForAgent&objId='+$("#projectId").val());
//			       	}
				});
		}
	});

});