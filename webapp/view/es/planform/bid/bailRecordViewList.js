
var bailRecordList={};

$(document).ready(function(){
	
	bailRecordList.viewBailRecord = function(bailRecordId){//查看
		$.epsDialog({
	        title:"查看保证金",
	        url:$("#initPath").val()+"/BailRecordController.do?method=toViewBailRecord&bailRecordId="+bailRecordId,
	        width: 650,
	        height: 400,
	        isReload: false
		});
	}
	
});