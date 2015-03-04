
var oppugnRequisitionList={};
oppugnRequisitionList.rows=null;//列表查询的结果集 
	//查看
	oppugnRequisitionList.look=function(objId){
		$("#tabform").empty().loadPage($('#initPath').val()+'/OppugnRequisitionController.do?method=toShowDetail&objId='+objId+"&type=buyer");

	}   
	//查看质疑回复
	oppugnRequisitionList.look2=function(objId){
    	$.epsDialog({
    		id :"oppugnRequisitionId",
	        title:"详情",
	        url:$("#initPath").val()+"/OppugnRequisitionController.do?method=getReplyOppugnRequisition&objId="+objId,
	        width: '800',
	        height: '500',
	        isReload: true
		});
	}
	//新增质疑答复
	oppugnRequisitionList.reply=function(objId){
		var url = $('#initPath').val()+'/OppugnReplyController.do?method=toAddOppugnReply&oppugnId='+objId+"&type=buyer";
		$.epsDialog({
	        title:"答复",
	        url:url,
	        width: '800',
	        height: '250',
	        isReload: true
		});
		
	}   
	//列表操作验证
	oppugnRequisitionList.validation=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择质疑'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个质疑'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤--招标单位质疑
	oppugnRequisitionList.before=function(){
		var option={
				"project.objId":$("#projectId").val(),
				"consBuyerName":'01,02',
				"useStatus":'01',
				"consBuyerName_op":'in'
		}//非指向招标单位的质疑
		$('#oppugnRequisitionGrid').flexOptions({params:option});
		return true;
	}
	
	oppugnRequisitionList.success=function(){
		$("#oppugnRequisitionGrid").flexAddOptionStr({
    		'<span><a href="#" class="abtn">质疑答复</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				var json = $("#oppugnRequisitionGrid").flexGetRowJsonById(rowId); //得到JSON对象
				btn.click(function(){
    				oppugnRequisitionList.reply(rowId);
    			}).appendTo(obj);
    	}
    	});
		$("#oppugnRequisitionGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			var json = $("#oppugnRequisitionGrid").flexGetRowJsonById(rowId); //得到JSON对象
			btn.click(function(){
				oppugnRequisitionList.look2(rowId);
			}).appendTo(obj);
		}
		});
	}
	
$(document).ready(function(){
	//日历控件
	$('#applyDateId').epsDatepicker();
});

