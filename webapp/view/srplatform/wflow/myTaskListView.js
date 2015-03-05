//包组区分JS方法、属性的唯一性
var myTaskListView={};

//查询条件过滤
myTaskListView.before=function(){
	var option={
		//'objId':'objId',
		//'objId_op':'like'
	}
	$('#myTaskListViewGrid').flexOptions({params:option});
	return true;
}
//加载数据成功之后调用的函数
myTaskListView.success=function(){
	//alert('load complete!');
	//('#sysConfigItemGrid').selectedRows(['id1', 'id2']);//选中用户已拥有的行	参数ID数组
	//if(sysConfigItemList.rows==null) sysConfigItemList.rows=$('#sysConfigItemGrid').flexOptions()[0].p.rows;//获取列表的查询结果，存储格式[{id1:1, name1:2},{id2:11, name2:22}]
}
myTaskListView.openTask=function(){
	//alert("tdHBML = "+$(td).html());
}
//删除
myTaskListView.remove=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择任务条目'+name);return false;}//是否选中
	$.getJSON($('#initPath').val()+'/WorkFlowController.do?method=removeTaskById',{ids:$(grid).getSelect()},function(json){
		if(json.result)alert(json.result);
		if(json.failure)return;
		$(grid).reload();//刷新
		//$("#sysconfigItemListView").click();
	});
}

//进入任务
myTaskListView.doTask=function(name,grid){
	if(!$(grid).isSelectOne()){alert('请选择一个待办任务'+name);return false;}//是否选中
	//到任务操作界面
	$('#conBody').empty().loadPage($('#initPath').val()+'/view/srplatform/wflow/taskDOPage.jsp?taskId='+$(grid).getSelect());
}

myTaskListView.startBidBuytProIn = function(proInKey){
	$("#conBody").loadPage($("#initPath").val()+"/WorkFlowController.do?method=startProcIn&procInKey=TASKPLAN");
}
//获得流程定义节点
myTaskListView.getNodeMode = function(name,grid){
	$.getJSON($('#initPath').val()+'/WorkFlowController.do?method=getNodeModelsByProDefKey',{proDefKey:"newproject",taskId:$(grid).getSelect()},function(json){
	});
}
$(document).ready(function(){
	
});

