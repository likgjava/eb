//定义文件全局变量 处理方法名重复问题
var ConsignList={};
//查询条件过滤
ConsignList.before=function(){
	var option={"confirmStatus":$('#confirmStatus').val(),"consAgent":$('#consAgent').val()};
	$('#ConsignGrid').flexOptions({params:option});
	return true;
}

//加载数据成功之后调用的函数
ConsignList.success=function(){
	$("#ConsignGrid").flexAddOptionStr({
		'<button class="enable" type="button"><span>接收</span></button>' : function(btn,rowId,obj){
			 btn.click(function(){
				 ConsignList.add(rowId);
			 }).appendTo(obj);
		}
	});
}

ConsignList.add=function(rowId){
	$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/consign/consignFormForAccept.jsp?objId='+rowId+'&fromMyDesktop=yes');
}