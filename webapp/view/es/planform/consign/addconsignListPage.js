
var addconsignList={};

//设定返回时的路径
addconsignList.setRetrun=function(){
	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/consign/addconsignList.jsp");
}

//新增
addconsignList.add=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择申报书明细');return false;}//是否选中
	$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=toDraft&taskPlanSubId='+$(grid).getSelects());
}

//查看
addconsignList.showDetail=function(name,grid){
	if(!addconsignList.validation(name,grid))return;
	$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=showDetail&objId='+$(grid).getSelect());
}

//列表操作验证
addconsignList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一份申报书条目');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请选择只一份申报书条目');return false;}//是否只选中一个
	return true;
}
//查询条件过滤
addconsignList.before=function(){
	var option={};
	$('#consignGrid').flexOptions({params:option});
	return true;
}

$(document).ready(function(){
	
});

