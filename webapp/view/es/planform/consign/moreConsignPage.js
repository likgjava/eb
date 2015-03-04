var consignList={};




//查询条件过滤
consignList.before=function(){
	var option={"taskType":"01",
				"confirmStatus":'01'}
	$('#consignGrid').flexOptions({params:option});
	return true;
}


//加载数据成功之后调用的函数
consignList.success=function(){
		$("#consignGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">确认</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$("#conBody").empty().loadPage($("#initPath").val()+"/view/es/planform/consign/consignFormForAccept.jsp?type=fromDesk&fromMyDesktop=yes&objId="+rowId).show();
				}).appendTo(obj);
			}
		});
}