
var operLogsList={};
operLogsList.rows=null;//列表查询的结果集
	//新增
	operLogsList.add=function(name,grid){
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+"/OperLogsController.do?method=toCreateOrUpdate");
	}   
	//修改
	operLogsList.update=function(name,grid){
		if(!operLogsList.validation(name,grid))return;
		//跳转到修改页面
		$('#conBody').loadPage('OperLogsController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
	}   
	//删除
	operLogsList.remove=function(name,grid){
		if(!operLogsList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/OperLogsController.do?method=remove',{objId:$(grid).getSelects().split(",")},function(json){
				if(json.result)alert(json.result,{inco:'info'});;if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	//列表操作验证
	operLogsList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
		//if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	operLogsList.before=function(){
		var option={
		}
		var onlineUserId = $('input[name=onlineUserId]').val();
		if(onlineUserId!=''){
			option['user.objId']=onlineUserId;
			$('input[name=onlineUserId]').val('');
		}
		if($("#logIp").val()) {
			option['logIp']=$("#logIp").val();
		}
		$('#operLogsGrid').flexOptions({params:option});
		return true;
	}
	operLogsList.detail = function(userId){
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
	operLogsList.success=function(){
		$("#operLogsGrid").flexGetColByName({
					 'user.usName' : function(id,t){
					 	var json = $("#operLogsGrid").flexGetRowJsonById(id); 
					 	var content = '<a href="#" onclick="operLogsList.detail(\''+json['user.usName']+'\');return false;">'+$(t).html()+'</a>';
					 	$(t).html(content);
					}
		});
	}
	

	
$(document).ready(function(){
	$('#startTime').epsDatepicker({ timeShow:false, picker: "",applyRule: rule });
	//$('#startTime').val(new Date().Format('yyyy-MM-dd'));
	$('#endTime').epsDatepicker({ timeShow:false, picker: "" ,applyRule: rule});
	
});

//约束时间规则 开始
function rule(id) {
	if (id == 'startTime') {
		var v = $("#endTime").val().split(' ')[0];
		if (v == "") {return null;}
		else {
			var d = v.match(/^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2})$/);
			if (d != null) {
				var nd = new Date(parseInt(d[1], 10), parseInt(d[3], 10) - 1, parseInt(d[4], 10));
				return { enddate: nd };
			}
			else {return null;}
		}
	}
	else {
		var v = $("#startTime").val().split(' ')[0];
		if (v == "") {return null;}
		else {
			var d = v.match(/^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2})$/);
			if (d != null) {
				var nd = new Date(parseInt(d[1], 10), parseInt(d[3], 10) - 1, parseInt(d[4], 10));
				return { startdate: nd };
			}
			else {return null;}
		}

	}
}
//约束时间规则 结束

//startTim and endTime
