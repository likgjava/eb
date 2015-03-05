
var loginLogsList={};
loginLogsList.rows=null;//列表查询的结果集
	//新增
	loginLogsList.add=function(name,grid){
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+"/LoginLogsController.do?method=toCreateOrUpdate");
	}   
	//修改
	loginLogsList.update=function(name,grid){
		if(!loginLogsList.validation(name,grid))return;
		//跳转到修改页面
		$('#conBody').loadPage('LoginLogsController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
	}   
	//删除
	loginLogsList.remove=function(name,grid){
		if(!loginLogsList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/LoginLogsController.do?method=remove',{objId:$(grid).getSelects().split(",")},function(json){
				if(json.result)alert(json.result,{inco:'info'});;if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	//列表操作验证
	loginLogsList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
		//if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	loginLogsList.before=function(){
		
		var option={
			//'objId':'objId',
			//'objId_op':'like'
		}
		var onlineUserId = $('input[name=onlineUserId]').val();
		if(onlineUserId!=''){
			option['user.objId']=onlineUserId;
			$('input[name=onlineUserId]').val('');
		}
		$('#loginLogsGrid').flexOptions({params:option});
		return true;
	}
	
	loginLogsList.detail = function(userId){
		$.epsDialog({
			        title:'账号信息',
			        url:$('#initPath').val()+'/view/srplatform/auth/float/userDetail.jsp?usName='+userId,
			        width: '500',
			        height: '400',
			        onOpen: function(){ },
			        afterLoad: function(){ },
			        onClose: function(){ }
			    }); 
	}
	//加载数据成功之后调用的函数
	loginLogsList.success=function(){
		$("#loginLogsGrid").flexGetColByName({
					 'user.usName' : function(id,t){
					 	var json = $("#loginLogsGrid").flexGetRowJsonById(id); 
					 	var content = '<a href="#" onclick="loginLogsList.detail(\''+json['user.usName']+'\');return false;">'+$(t).html()+'</a>';
					 	$(t).html(content);
					}
		});
	}
$(document).ready(function(){
	//事件代码
});

