
var buyResultList={};
buyResultList.rows=null;//列表查询的结果集
	//新增
	buyResultList.add=function(name,grid){
		//跳转到修改页面
		var projectId = $("#projectId").val();
		$('#buyResultListView').empty().loadPage($('#initPath').val()+"/view/es/planform/buyresult/buyResultForm.jsp?projectId="+projectId);
	}   
	//修改
	buyResultList.update=function(name,grid){
		if(!buyResultList.validation(name,grid))return;
		//跳转到修改页面
		var objId = $(grid).getSelect();
		$('#buyResultListView').empty().loadPage($('#initPath').val()+'/BuyResultController.do?method=toCreateOrUpdate&objId='+objId);
	}   
	//查看
	buyResultList.look=function(name,grid){
		if(!buyResultList.validation(name,grid))return;
		//跳转到修改页面
		var objId = $(grid).getSelect();
		$('#buyResultListView').empty().loadPage($('#initPath').val()+'/BuyResultController.do?method=toShowView&objId='+objId);
	}   
	//删除
	buyResultList.remove=function(name,grid){
		if(!buyResultList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/BuyResultController.do?method=remove',{objId:$(grid).getSelect()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	//列表操作验证
	buyResultList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	buyResultList.before=function(){
		var option={
			//'objId':'objId',
			//'objId_op':'like'
		}
		$('#buyResultGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	buyResultList.success=function(){
		$("#buyResultGrid").flexGetColByName({
			//成交结果
	 		'buyrResult':function(id,t){
		 		if($(t).html()=="00"){
		 			$(t).html("无人参与");
		 		}else if($(t).html()=="01"){
		 			$(t).html("没有足够参与者");
		 		}else if($(t).html()=="02"){
		 			$(t).html("选定");
		 		}else if($(t).html()=="03"){
		 			$(t).html("放弃");
		 		}
			},
			'ebuyMethod':function(id,t){
		 		if($(t).html()=="00"){
		 			$(t).html("公开招标");
		 		}else if($(t).html()=="01"){
		 			$(t).html("邀请招标");
		 		}else if($(t).html()=="02"){
		 			$(t).html("竞争性谈判");
		 		}else if($(t).html()=="03"){
		 			$(t).html("询价");
		 		}else if($(t).html()=="04"){
		 			$(t).html("单一来源");
		 		}
			}
		});
	}
$(document).ready(function(){
});

