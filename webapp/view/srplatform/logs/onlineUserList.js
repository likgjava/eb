var onlineUserList={};
onlineUserList.rows=null;
 
	//注销用户
	onlineUserList.offline=function(name,grid){
		if(!onlineUserList.validation(name,grid))return;
		if(window.confirm("是否确定注销?")){
			var objId=$(grid).getSelect();
			var row=$('#userGrid').getRowById(objId);
			$.getJSON($('#initPath').val()+'/OnlineUserController.do?method=offline',{usNames:row.usName},function(json){
				if(json.result)alert(json.result,{inco:'info'});;if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	} 

  	//列表操作验证
	onlineUserList.validation=function(name, grid){
		//$(grid).getSelect()获取一个选中ID $(grid).getSelects()获取多个选中ID
		//$(grid).isSelectEmpty()是否没有选中 $(grid).isSelectOne()是否只选中了一个
 		if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
 		if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
 			return true;
	}
onlineUserList.detail = function(userId){
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
onlineUserList.success=function(){
		$("#userGrid").flexGetColByName({
					 'usName' : function(id,t){
					 	var json = $("#userGrid").flexGetRowJsonById(id); 
					 	var content = '<a href="#" onclick="onlineUserList.detail(\''+json['usName']+'\');return false;">'+$(t).html()+'</a>';
					 	$(t).html(content);
					}
		});
		$("#userGrid").flexAddOptionStr({
		  '<button class="functionBtnDiv" type="button"><span><span>查看登陆日志</span></span></button>' : function(btn,rowId,obj){
			 btn.click(function(){
			 	$('#conBody').load($('#initPath').val()+"/LoginLogsController.do?onlineUserId="+rowId);
								})
			 .appendTo(obj)
		 },
		  '<button class="functionBtnDiv" type="button"><span><span>查看操作日志</span></span></button>' : function(btn,rowId,obj){
			 btn.click(function(){
				 var url = $('#initPath').val()+'/OperLogsController.do?onlineUserId='+rowId;
				 $('#conBody').loadPage(url);
								})
			 .appendTo(obj)
		 }
	 })
}

$(document).ready(function(){
}); 