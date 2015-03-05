
var cas_system_list={};
	
	//删除
	cas_system_list.remove=function(name,grid){
		if(!cas_system_list.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/LoginLogsController.do?method=remove',{objId:$(grid).getSelects().split(",")},function(json){
				if(json.result)alert(json.result,{inco:'info'});;if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	
	//查询条件过滤
	cas_system_list.before=function(){}
	
	
	//加载数据成功之后调用的函数
	cas_system_list.success=function(){
		$("#casSystemGrid").flexAddOptionStr({
			 '<button class="btn" type="button"><span><span>删除</span></span></button>' : function(btn,rowId,obj){
					 btn .click(function(){
							if(window.confirm('确实要删除该系统')){
								$.getJSON($('#initPath').val()+'/CasSystemController.do?method=delete',{id:rowId},function(json){
									$("#casSystemGrid").reload();
								})
							}
						})
					 .appendTo(obj)
					 },
			 '<button class="btn" type="button"><span><span>修改</span></span></button>' : function(btn,rowId,obj){
					 btn.click(function(){
					 				var json = $("#casSystemGrid").flexGetRowJsonById(rowId); 
									jsonObjectToForm('casSystemForm', json);
						})
					 .appendTo(obj)
					 }
		})
	}
$(document).ready(function(){
	$("#casSystemGrid").flexigrid({
		url:$('#initPath').val()+'/CasSystemController.do?method=list',
		usepager:false,//是否分页
		checkbox:false,
		onSuccess:cas_system_list.success,
//		buttons : [
//				{name: '新增', bclass: 'add', onpress : cas_system_list.add},
//				{separator: true}
//				],
  		title: '单点系统列表',//列表title
  		colModel : [
		{display: '系统URL', name : 'url', width : 500, sortable : true, align: 'left'},
		{display: '名称', name : 'name', width : 200, sortable : true, align: 'left'}
		]//列结构
	});
	
	$('#casSystemForm').validate();
	//提交
	$('#save').click(function(){
		if(!$('#casSystemForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/CasSystemController.do?method=save', formToJsonObject('casSystemForm'), function(json){
			if(json.result)alert(json.result,{inco:'info'});;if(json.failure)return;
			$("#casSystemGrid").reload();//刷新
			$('#casSystemForm').clearForm();
		});
	});
});

