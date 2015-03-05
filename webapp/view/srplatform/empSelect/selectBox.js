var SelectBox={};

//获取数据
SelectBox.getList= function(url,queryColumn,params){
	//加载div分页
	$('#boxPager').ajaxPager({   
			url:url,
			rp:20,
			data:params,
			startPage:1, 
			fnCallbackDraw: function(json,pageIndex){
				$("#toSelect").empty();
				if(json.rows.length>0){
					$.each(json.rows,function(index,n){
						var optionStr = '<option value='+n[queryColumn[0]]+'>'+n[queryColumn[1]];
						for(var j=2; j<queryColumn.length; j++){
							if(j==2){
								optionStr += " (";
							}
							optionStr += n[queryColumn[j]]+"  ";
							if(j==queryColumn.length-1){
								optionStr += ")";
							}
						}
						optionStr += "</option>";
						
						$("#toSelect").append(optionStr);

						if($("#_checkValues").val()&&$("#_checkValues").val().indexOf(n[queryColumn[0]])>-1){
							$("#alreadySelect").append(optionStr);
						}
					})
				} else {
					$('div[class=pages]').html('');
				}
			}
	});
}


$(document).ready(function(){
	var domainName = $("#_domainName").val(); //实体类名（如：User）
	var queryColumns = $("#_queryColumns").val(); //查询字段
	var orderColumns = $("#_orderColumns").val(); //排序字段
	//设置默认值
	if(domainName==null || domainName==""){ domainName="Employee"; }
	if(orderColumns==null || orderColumns==""){
		if(domainName=="Employee"){ orderColumns="company.objId"; }
	}
	
	//var url = $("#initPath").val()+"/"+domainName+"Controller.do?method=getObjectQuery&queryColumns="+queryColumns;
	var url = $("#initPath").val()+"/"+domainName+"Controller.do?method=list&queryColumns="+queryColumns;
	
	//角色的分页特殊处理
	if(domainName=="Role"){
		url = $("#initPath").val()+"/RoleController.do?method=getRoleListPage&queryColumns="+queryColumns;
	}
	//用户的分页特殊处理
	if(domainName=="User"){
		url = $("#initPath").val()+"/UserController.do?method=getUserListPage&queryColumns="+queryColumns;
	}

	if(orderColumns!=null && orderColumns!=""){
		url += "&order="+orderColumns;
	}
	
	if($("#_param").val() != ""){
		url += "&"+$("#_param").val();
	}
	
	var queryColumn = queryColumns.split(",");
	
	SelectBox.getList(url,queryColumn,{});
	
	//添加
	$("#ADD").click(function(){
		var value;
		var toSelect = $("#toSelect").find("option:selected");
		if(toSelect.length==0){
			alert("请至少选择一项！");
			return;
		}
		$.each(toSelect,function(index,obj){
			value= $(obj).val();
			if($("#alreadySelect").children().length==0 || !$("#alreadySelect").find("option[value="+value+"]").html()) {
				$("#alreadySelect").append($(obj).clone());
			}
		});
	})
	
	//删除
	$("#DELETE").click(function(){
		var value = $('#alreadySelect').val();
		if(!value){
			alert("请至少选择一项！");
			return;
		}
		for(var i=0; i<value.length; i++){
			$("#alreadySelect").removeItem(value[i]);
		}
	})
	
	//确定
	$("#OK").click(function(){
		var value = "";
		var item = "";
		var show = "";
		var options;
		
		//是否回填
		if($("#_backfill").val()=="false"){
			options = $("#alreadySelect option[type!=checkedVal]");
			
		}else {
			options = $("#alreadySelect option");
		}
		
		for(var i=0; i<options.length; i++){
			value += options[i].value + ",";
			item += options[i].text + ",";
			show += options[i].text + "<br />";
		}
		if(options.length > 0){
			value = value.substring(0,value.length-1);
			item = item.substring(0,item.length-1);
		}
		returnValue(value,item,show);
	})
	
	//清空
	$("#CLEAR").click(function(){
		$("#alreadySelect").empty();
		returnValue("","","");
	})
	
	//搜索
	$("#SEARCH").click(function(){
		var temp = $("#condition").val(); //关键字
		var paramJsonStr = '{';
		for(var j=1; j<queryColumn.length; j++){
			paramJsonStr += '"'+queryColumn[j]+'":"'+temp+'","'+queryColumn[j]+'_op":"like","'+queryColumn[j]+'_relative":"[and:or]",';
		}
		paramJsonStr = paramJsonStr.substring(0,paramJsonStr.length-1);
		paramJsonStr += '}';
		var paramJson = eval('('+paramJsonStr.toString()+')');
		
		SelectBox.getList(url,queryColumn,paramJson);
	})
	
	//已经选定的值
	
	if($("#_property").val()){
		var id = document.getElementById($("#_property").val()+".objId").value;
		var name = document.getElementById($("#_property").val()+".name").value;
		if(id&&name){
			var ids = id.split(",");
			var names = name.split(",");
			$.each(ids,function(index,obj){
				$("#alreadySelect").append('<option value='+obj+'>'+names[index]+'</option>');
			})
		}
	}
	
})
	
function returnValue(id,name,show){
	//回填id
	if("" != $("#_property").val())
		document.getElementById($("#_property").val()+".objId").value=id;
	else if("" != $("#_ID").val())
		document.getElementById($("#_ID").val()).value=id;
	
	//回填name
	if("" != $("#_property").val()){
		document.getElementById($("#_property").val()+".name").value=name;
		$(document.getElementById($("#_property").val()+".name")).keyup();
	}
	else if("" != $("#_NAME").val()){
		document.getElementById($("#_NAME").val()).value=name;
		$(document.getElementById($("#_NAME").val())).keyup();
	}
	
	//回填span或div
	if("" != $("#_SHOWS").val()){
		$("#"+$("#_SHOWS").val()).html(show);
	}
	
	//关闭弹出层
	if($("#_dialogID").val() != "")
		$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
	else
		$('.epsDialogClose').trigger('click');
}
