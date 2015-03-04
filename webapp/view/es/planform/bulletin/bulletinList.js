
var bulletinList={};
bulletinList.rows=null;//列表查询的结果集
	//新增
	bulletinList.add=function(name,grid){
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+"/BulletinController.do?method=toCreateOrUpdate");
	}   
	//修改
	bulletinList.update=function(name,grid){
		if(!bulletinList.validation(name,grid))return;
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+'/BulletinController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
	}   
	//查看
	bulletinList.look=function(name,grid){
		if(!bulletinList.validation(name,grid))return;
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+'/BulletinController.do?method=toShowView&objId='+$(grid).getSelect());
	}   
	//删除
	bulletinList.remove=function(name,grid){
		if(!bulletinList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/BulletinController.do?method=remove',{objId:$(grid).getSelect()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	//列表操作验证
	bulletinList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	bulletinList.before=function(){
		var option={
			//'objId':'objId',
			//'objId_op':'like'
		}
		$('#bulletinGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	bulletinList.success=function(){
		$("#bulletinGrid").flexGetColByName({
			'bullIssuedate' : function(id,t){
				$(t).html($(t).html().substring(0,10));
			},
			'bullValidDdte' : function(id,t){
				$(t).html($(t).html().substring(0,10));
			},
			'bullInvaliddate' : function(id,t){
				$(t).html($(t).html().substring(0,10));
			},
			//公告类型
	        'bullType':function(id,t){
		 		if($(t).html()=="00"){
		 			$(t).html("采购预告");
		 		}else if($(t).html()=="01"){
		 			$(t).html("采购公告");
		 		}else if($(t).html()=="02"){
		 			$(t).html("变更公告");
		 		}else if($(t).html()=="03"){
		 			$(t).html("资格预审公告");
		 		}else if($(t).html()=="04"){
		 			$(t).html("资格预审结果公示");
		 		}else if($(t).html()=="05"){
		 			$(t).html("结果公示");
		 		}else if($(t).html()=="06"){
		 			$(t).html("结果公告");
		 		}else if($(t).html()=="07"){
		 			$(t).html("暂停公告");
		 		}else if($(t).html()=="08"){
		 			$(t).html("失败公告");
		 		}
		 	},
	 		//采购方式
	 		'buyMethod':function(id,t){
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

