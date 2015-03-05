//定义文件全局变量 处理方法名重复问题
var OperLogsList={};
OperLogsList.pageContent;


$(document).ready(function(){
	OperLogsList.pageContent=new PageList("OperLogsTbody","OperLogsTemplate","OperLogsList.pageContent","OperLogsPage");
	OperLogsList.pageContent.sorttable=function(){
		sorttable.init("OperLogsTable");
	}
 
	OperLogsList.pageContent.initData({url:"OperLogsController.do?method=listOld&loginLogs.objId="+$("input[id=loginLogsId]").val(),data:{}},function(obj,j,o,n){
		return true;
	})
	OperLogsList.pageContent.searchForm();

})

