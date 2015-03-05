
var cas_mapping_list={};
	
	
	
	//查询条件过滤
	cas_mapping_list.before=function(){}
	
	
	//加载数据成功之后调用的函数
	cas_mapping_list.success=function(){
		$("#casMappingGrid").flexAddOptionStr({
			 '<button class="btn" type="button"><span><span>删除</span></span></button>' : function(btn,rowId,obj){
					 btn.click(function(){
							if(window.confirm('确实要删除该映射')){
								var json = $("#casMappingGrid").flexGetRowJsonById(rowId); 
								$.getJSON($('#initPath').val()+'/CasMappingController.do?method=delete',{userName:json.ssoUserId,sysId:json.sys.objId},function(json){
									$("#casMappingGrid").reload();
								})
							}
						})
					 .appendTo(obj)
					 }
		})
	}
$(document).ready(function(){
	$("#casMappingGrid").flexigrid({
		url:$('#initPath').val()+'/CasMappingController.do?method=list',
		usepager:false,//是否分页
		checkbox:true,
		onSuccess:cas_mapping_list.success,
//		buttons : [
//				{name: '新增', bclass: 'add', onpress : cas_mapping_list.add},
//				{separator: true}
//				],
  		title: '映射列表',//列表title
  		colModel : [
		{display: 'sso用户名', name : 'ssoUserId', width : 150, sortable : true, align: 'left'},
		{display: 'local用户名', name : 'localUserId', width : 150, sortable : true, align: 'left'},
		{display: '子系统名称', name : 'sys.name', width : 200, sortable : true, align: 'center'},
		{display: '子系统URL', name : 'sys.url', width : 200, sortable : true, align: 'left'}
		]//列结构
	});
	
	$('#casMappingForm').validate();
	//提交
	$('#save').click(function(){
		if(!$('#casMappingForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/CasMappingController.do?method=save', formToJsonObject('casMappingForm'), function(json){
			if(json.result)alert(json.result,{inco:'info'});;if(json.failure)return;
			$("#casMappingGrid").reload();//刷新
			$('#casMappingForm').clearForm();
		});
	});
});

