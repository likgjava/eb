var SelectList={}

//查询条件过滤
SelectList.before=function(){
	var option={};
	if($("#_param").val() != ""){
		$('#employeeGrid').attr("p").url += "&"+$("#_param").val();
	}
	$('#employeeGrid').flexOptions({params:option});
	return true;
}

//确定
SelectList.add=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一个用户');return false;}
	returnValue($(grid).getSelect(),$('#employeeGrid').getRowById($(grid).getSelect())["name"]);
}

//清空
SelectList.clear=function(name,grid){
	returnValue("","");
}

$(document).ready(function(){
})
	
function returnValue(id,name){
	//回填id
	if("" == $("#_ID").val())
		document.getElementById($("#_property").val()+".objId").value=id;
	else
		document.getElementById($("#_ID").val()).value=id;
	
	//回填name
	if("" == $("#_NAME").val()){
		document.getElementById($("#_property").val()+".name").value=name;
		$(document.getElementById($("#_property").val()+".name")).keyup();
	}
	else{
		document.getElementById($("#_NAME").val()).value=name;
		$(document.getElementById($("#_NAME").val())).keyup();
	}
	
	//关闭弹出层
	if($("#_dialogID").val() != "")
		$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
	else
		$('.epsDialogClose').trigger('click');
}

	
