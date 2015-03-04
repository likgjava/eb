
var projectListForAudit={};



$(document).ready(function(){
	
	
	projectListForAudit.audit = function(name,grid)
	{
		if(!projectListForAudit.validation(name,grid))return;
		$('#conBody').loadPage($('#initPath').val()+'/ProjectController.do?method=toAuditBlockTaskProject&objId='+$(grid).getSelect());
	}
	
	//列表操作验证
	projectListForAudit.validation=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择一个项目');return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请只选择一个项目');return false;}//是否只选中一个
		return true;
	}
	
	
	
});

