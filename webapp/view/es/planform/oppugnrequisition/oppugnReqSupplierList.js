
var oppugnRequisitionList={};
	//新增
	oppugnRequisitionList.add=function(name,grid){
		$('#tabform').loadPage($('#initPath').val()+"/OppugnRequisitionController.do?method=toAddOppugnRequisition");
	}   
	//修改
	oppugnRequisitionList.update=function(objId){
		$('#tabform').loadPage($('#initPath').val()+"/OppugnRequisitionController.do?method=toUpdateOppugnRequisition&objId="+objId);
	}   
	//删除
	oppugnRequisitionList.remove=function(objId){
		if(window.confirm('确定删除?')){
			$.getJSON($('#initPath').val()+'/OppugnRequisitionController.do?method=deleteOppugnRequisition',{objId:objId},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$("#tabform").empty().loadPage($('#initPath').val()+'/view/es/planform/oppugnrequisition/oppugnReqSupplierList.jsp');//刷新
			});
		}
	}
	//批量删除
	oppugnRequisitionList.removes=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择一个质疑');return false;}//是否选中
		if(window.confirm('确定'+name+'?')){
			var ids = $(grid).getSelects().split(",");
			$.getJSON($('#initPath').val()+'/OppugnRequisitionController.do?method=remove',{objId:ids},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	//查看质疑回复
	oppugnRequisitionList.viewOppugnRequisition=function(objId){
    	$.epsDialog({
    		id :"oppugnRequisitionId",
	        title:"详情",
	        url:$("#initPath").val()+"/OppugnRequisitionController.do?method=getReplyOppugnRequisition&objId="+objId,
	        width: '800',
	        height: '500',
	        isReload: true
		});
	}
	//列表操作验证
	oppugnRequisitionList.validation=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择质疑'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个质疑'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤--投标单位质疑
	oppugnRequisitionList.before=function(){
		var option={
				"project.objId":$("#projectId").val(),
				"createUser.objId":PlatForm.user.objId
				}
		$('#oppugnRequisitionGrid').flexOptions({params:option});
		return true;
	}
	
	//加载数据成功之后调用的函数
	oppugnRequisitionList.success=function(){
    	$("#oppugnRequisitionGrid").flexAddOptionStr({
    		'<span><a href="#" class="abtn">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
    			btn.click(function(){
    				oppugnRequisitionList.remove(rowId);
    			}).appendTo(obj);
    	}
    	});
    	$("#oppugnRequisitionGrid").flexAddOptionStr({
    		'<span><a href="#" class="abtn">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
    		 var json = $("#oppugnRequisitionGrid").flexGetRowJsonById(rowId); 
    		 if(json["useStatus"]=="01"){//质疑是正式的状态
    			 btn.click(function(){
    				 oppugnRequisitionList.viewOppugnRequisition(rowId);
    			 }).appendTo(obj);
    		 }
    	}
    	});
    	$("#oppugnRequisitionGrid").flexAddOptionStr({
    		'<span><a href="#" class="abtn">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
    		 var json = $("#oppugnRequisitionGrid").flexGetRowJsonById(rowId); 
    		 if(json["useStatus"]=="00"){//质疑是临时的状态
    			 btn.click(function(){
    				 oppugnRequisitionList.update(rowId);
    			 }).appendTo(obj);
    		 }
    	}
    	});
	}
	
	
$(document).ready(function(){
	
});

