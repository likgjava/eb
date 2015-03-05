
var sysLogsList={};
sysLogsList.rows=null;//列表查询的结果集
	
	sysLogsList.download=function(name,grid){
		if(sysLogsList.validation()){
			var url = $("#initPath").val()+"/SysLogsController.do?method=downloadSysLogs&fileName="+$(grid).getSelect();
			window.location.href=url ;
		}
		
	}   
	sysLogsList.see=function(name,grid){
		if(sysLogsList.validation()){
			var url = $("#initPath").val()+"/SysLogsController.do?method=seeSysLogs&fileName="+$(grid).getSelect();
			window.open(url) ;
		}
	}   
	
	
	//列表操作验证
	sysLogsList.validation=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择一个日志文件');return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个日志文件');return false;}//是否只选中一个
		return true;
	}
	//加载数据成功之后调用的函数
	sysLogsList.success=function(){
		//alert('load complete!');
		//('#operLogsGrid').selectedRows(['id1', 'id2']);//选中用户已拥有的行	参数ID数组
		//if(sysLogsList.rows==null) sysLogsList.rows=$('#operLogsGrid').flexOptions()[0].p.rows;//获取列表的查询结果，存储格式[{id1:1, name1:2},{id2:11, name2:22}]
	}
$(document).ready(function(){
	//事件代码
});

