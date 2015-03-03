//定义文件全局变量 处理方法名重复问题
var ObjectSelectList={};
ObjectSelectList.pageSelectContent;
ObjectSelectList.pageCheckContent;


ObjectSelectList.search = function(){
	ObjectSelectList.oTable.fnDraw();
}

$(document).ready(function(){
	//参数
	var params = '{'+$("#_queryParams").val()+'}';
	var json = eval('('+params+')');
	
	
	//追加列表
	var columTrArray = new Array();
	columTrArray = $("#_columCns").val().split(",");
	var str = '';
	for(var i=0;i<columTrArray.length;i++){
		str += '<th>'+columTrArray[i]+'</th>';
	}
	
	//判断是否有确定按钮
	if($.trim($("#_isCheckBox").val())!=""&&$("#_isCheckBox").val()=="true"){
		//$("#_clear").before("<button id='_OK'>确定</button>")
		$("#_OK").show();
		$("#objectList").find('tr').append(str);
	}else{
		//清空按钮也隐藏掉
		$("#_clear").hide();
		str +='<th>操作</th>';
		$("#objectList").find('tr').append(str);
	}
	
	//加载列表
	ObjectSelectList.oTable=$('#objectList').dataTable( {
		
		'params':json,
		'searchZone':'ObjectSearchForm',
		'singleSelect':$("#_isCheckBox").val()=="true"?false:true,
		'checkbox':$("#_isCheckBox").val()=="true"?true:false,
		'queryColumns':$("#_colums").val(),
		'hiddenColumns':'',
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {	
		},
		'fnRowCallback': function( nRow, aData, i) {
			//复选为否时(每行后面出现选择按钮)
			if($("#_isCheckBox").val()!="true"){
				$(nRow).append('<td><a href="javascript:void(0);">选择</a></td>');
				$(nRow).find('a:last').click(function(){
					returnValue(getData(aData['objId']),$("#_returnColums").val());
				});
			}
			return nRow;
		},
		'sAjaxSource': $('#initPath').val()+'/AgreementController.do?method=chooseSupplier'
	});
	//清空
	$("#_clear").click(function(){
		returnValue("","");
	})
	
	//确定(复选确定)
	$("#_OK").click(function(){
		returnValue(getData($('#objectList').dtSelects()),$("#_returnColums").val());
	});
	
	//搜索
	$("#objectSearch").click(function(){
		ObjectSelectList.search();
	});
})


//取得数据
function getData(ids){
	if(null==ids||""==ids)
		ids = '-1';
	var result;
	$.ajax({
		url:$("#initPath").val()+"/"+$("#_domain").val()+"Controller.do?method=getObjectQuery",
		data:{
			"objId":ids,
			"objId_op":"in",
			 queryColumns:$("#_returnColums").val()
		},
		dataType:"json",
		type:"POST",
		async:false,
		success:function(msg){
				result = msg.result;
		}
	})
	return result;
}

//回填
function returnValue(result,returnColums){
	
	var defineRetuColums = null;
	
	//是否指定回填的 表单id
	if(null!=$("#_defineRetuColums").val()&&""!=$("#_defineRetuColums").val()){
		defineRetuColums = $("#_defineRetuColums").val().split(",");
	}
	
	if(null!=returnColums&&""!=returnColums){
		var colum= returnColums.split(",");
		//先清空回填的表单
		for(var i=0;i<colum.length;i++){
			$("#"+colum[i]).empty();
		}
		//循环回填数据
		for(var i=0;i<colum.length;i++){
			for(var j=0;j<result.length;j++){
				//按指定回填的 表单id回填
				if(null!=$("#_defineRetuColums").val()&&""!=$("#_defineRetuColums").val()){
					if(j>0){
						$('input[id='+defineRetuColums[i]+']').val($('input[id='+defineRetuColums[i]+']').val()+","+result[j][colum[i]]);
					}else{
						$('input[id='+defineRetuColums[i]+']').val(result[j][colum[i]]);
					}
				}else
				//默认按回填列的名称回填
				{
					if(j>0){
						$("#"+colum[i]).val($("#"+colum[i]).val()+","+result[j][colum[i]]);
					}else{
						$("#"+colum[i]).val(result[j][colum[i]]);
					}
				}
			}
		}
	}
	//关闭弹出层
	$("#floaterDiv").dialog("close");
	var epsDialogId = $("#_DialogId").val();
	$("#"+epsDialogId+"").find('.epsDialogClose').trigger('click');
}
