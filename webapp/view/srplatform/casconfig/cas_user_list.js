
var cas_user_list={};
	
	//删除
	cas_user_list.remove=function(name,grid){
		if(!cas_user_list.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/LoginLogsController.do?method=remove',{objId:$(grid).getSelects().split(",")},function(json){
				if(json.result)alert(json.result,{inco:'info'});;if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	
	//查询条件过滤
	cas_user_list.before=function(){}
	
	
	//加载数据成功之后调用的函数
	cas_user_list.success=function(){
		$("#casUserGrid").flexAddOptionStr({
			 '<button class="btn" type="button"><span><span>删除</span></span></button>' : function(btn,rowId,obj){
					 btn .click(function(){
							if(window.confirm('确实要删除该用户')){
								$.getJSON($('#initPath').val()+'/CasUserController.do?method=delete',{id:rowId},function(json){
									$("#casUserGrid").reload();
								})
							}
						})
					 .appendTo(obj)
					 }
		})
	}
$(document).ready(function(){
	$("#casUserGrid").flexigrid({
		url:$('#initPath').val()+'/CasUserController.do?method=list',
		usepager:false,//是否分页
		checkbox:false,
		onSuccess:cas_user_list.success,
//		buttons : [
//				{name: '新增', bclass: 'add', onpress : cas_user_list.add},
//				{separator: true}
//				],
  		title: '单点用户列表',//列表title
  		colModel : [
		{display: '用户名', name : 'objId', width : 150, sortable : true, align: 'left'},
		{display: '中文名', name : 'ssoUserName', width : 150, sortable : true, align: 'left'},
		{display: '子系统名称', name : 'sys.name', width : 200, sortable : true, align: 'center'},
		{display: '子系统URL', name : 'sys.url', width : 200, sortable : true, align: 'left'}
		]//列结构
	});
	
	$('#casUserForm').validate();
	//提交
	$('#save').click(function(){
		if(!$('#casUserForm').valid()){alert('请正确填写表单!');return;}
		$('input[name=password]').attr('value',hex_sha1($('input[name=password]').val()));
		$.getJSON($('#initPath').val()+'/CasUserController.do?method=save', formToJsonObject('casUserForm'), function(json){
			$('#casUserForm').clearForm();
			if(json.result)alert(json.result);;if(json.failure)return;
			$("#casUserGrid").reload();//刷新
			
		});
	});
});

