var agentList={};

//查询条件过滤
agentList.before=function(){
	var option={"supplierId":"null","supplierId_op":"!=","orgInfo.objId":$('#supplierIds').val(),"orgInfo.objId_op":"!in"}
	$('#agentListGrid').flexOptions({params:option});
	return true;
}

//加载数据成功之后调用的函数
agentList.success=function(){
	$("#agentListGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
		 var json = $("#agentListGrid").flexGetRowJsonById(rowId); //得到JSON对象
		btn.click(function(){
			var num = $('#num').val();
			$("#supplierId_"+num).val(json["orgInfo.objId"]);
			$("#taskAgentName_"+num).val(json["orgInfo.orgName"]);
			//关闭弹出层
			$('#epsDialogCloseNoReload').click();
		}).appendTo(obj);
	}
	});
	$("#agentListGrid").flexGetColByName({//招标中心列
		'orgInfo.orgName':function(id,t){
			var json = $("#agentListGrid").flexGetRowJsonById(id);
			var supplierId =  json['objId'];
			var supplierIdName = json['orgInfo.orgName'];			
			$(t).html('<a href="#" onClick="getSupplyMessage(\''+supplierId+'\',\''+supplierIdName+'\');">'+$(t).html()+'</a>');
		}
	});
}

function getSupplyMessage(objId,name)//获得招标中心信息
{
	  $.epsDialog({
	        title:'投标单位信息',
	        url:$('#initPath').val()+'/UserApiController.do?method=getSupplyMessageByObjId&objId='+objId,
	        width: '600',
	        height: '450',
	 	    isReload:true,
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        onClose: function(){ }
				});
}


$(document).ready(function(){
	var supplierIds = new Array();
    $("input[name=supplierId]").each(function(i,n){
    	if ($(n).val()!=null&&$(n).val()!=undefined&&$(n).val()!="") {
    		supplierIds.push($(n).val());
    	}
    })
    $('#supplierIds').val(supplierIds.toString());
});

