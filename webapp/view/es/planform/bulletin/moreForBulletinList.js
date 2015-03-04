//定义文件全局变量 处理方法名重复问题
var BulletinList={};
BulletinList.auditMethodName = '';//controller审核方法名
//查询条件过滤
BulletinList.before=function(){
	var option={"auditStatus":$('#auditStatus').val(),"bullType":$('#bullType').val()};
	$('#BulletinGrid').flexOptions({params:option});
	return true;
}


//加载数据成功之后调用的函数
BulletinList.success=function(){
	//状态
	var auditStatus = $('#auditStatus').val();
	//类型
	var bullType = $('#bullType').val();
		if(auditStatus=='00'){
			$("#BulletinGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">审核</a></span>'  : function(btn,rowId,obj){
			btn.click(function(){
				BulletinList.audit(rowId);
			}).appendTo(obj);
			}
			});
		}else if(auditStatus=='01'){
			$("#BulletinGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">查看</a></span>' : function(btn,rowId,obj){
				btn.click(function(){
					BulletinList.view(rowId);
				}).appendTo(obj);
				}
				});
		}else if(auditStatus=='02'){
				$("#BulletinGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">修改</a></span>' : function(btn,rowId,obj){
					btn.click(function(){
						BulletinList.modify(rowId,bullType);
					}).appendTo(obj);
					}
					});
		}
}
//待审核招标公告
BulletinList.audit=function(rowId){
	$('#conBody').loadPage($('#initPath').val()+'/BulletinController.do?method=getBulletinAuditByObjId&objId='+rowId+'&fromDesk=yes');
}
//待修改的公告
BulletinList.modify=function(rowId,bullType){
	$('#conBody').loadPage($('#initPath').val()+'/BulletinController.do?method=getBulletinByBullType&objId='+rowId+'&bullType='+bullType+'&fromDesk=yes');
}
//获取公告内容
BulletinList.view=function(rowId){
	$('#conBody').loadPage($('#initPath').val()+'/PurBulletinController.do?method=getBulletinByObjId&objId='+rowId+'&returnUrl=yes');
}

BulletinList.batchAudit = function (name,grid){
	var ids = $(grid).getSelects().split(",");
	if(ids == ""){
		alert("请至少选择一个公告!");
		return false;
	}else{
		var json = {};
		json['idsP']='ids';
		json['opinionP']='opinion';
		json['ids'] =ids;
		json['passAction'] = 'ResultBulletinController.do?method='+BulletinList.auditMethodName+';passStatus=01';
		json['noPassAction'] = 'ResultBulletinController.do?method='+BulletinList.auditMethodName+';passStatus=02';
		$.epsDialog({
					title:'批量审核',
					url:$('#initPath').val()+'/view/srplatform/batch/audit.jsp?json='+obj2str(json), 
					width: 500,
					height: 200,
					afterLoad: function(){}, //加载完url后调用
					onClose: function(){$(grid).reload()} //关闭后调用
				});	
	}
}


$(document).ready(function(){
	var title = '公告列表';
	//状态
	var auditStatus = $('#auditStatus').val();
	//类型
	var bullType = $('#bullType').val();
	var buttons = [];
	var checkbox = false;
	if(auditStatus=='00'&&bullType=='01'){//批量审核招标公告
		BulletinList.auditMethodName = 'purbulletinAudit';
		title = '招标公告';
		checkbox = true;
		buttons = [{name: '批量审核', bclass: 'enable', onpress : BulletinList.batchAudit}]
	}
	else if(auditStatus=='00'&&bullType=='06'){//批量审核中标公告又叫结果公告
		BulletinList.auditMethodName = 'auditResultBulletin';
		title="结果公告";
		checkbox = true;
		buttons = [{name: '批量审核', bclass: 'enable', onpress : BulletinList.batchAudit}]
	}
	else if(auditStatus=='00'&&bullType=='10'){//批量审核询价公告
		BulletinList.auditMethodName = '';//有待扩展
		title='询价公告';
		checkbox = true;
		buttons = [{name: '批量审核', bclass: 'enable', onpress : BulletinList.batchAudit}]
	}
	
	var grid = {
 		url: $('#initPath').val()+'/BulletinController.do?method=getBulletinList',
 		rp:10,
 		checkbox:checkbox,
 		searchZone:'contractSearchZone',
 		onSuccess:BulletinList.success,
 		onSubmit:BulletinList.before,
 		params:{},
 		minGridHeight:305,
 		height:305,
  		title: title,//列表title
 		queryColumns:'',//列表查询属性
 		colModel : [
 			{display: '公告编号',hide:false, name : 'bulletinNo', width : 150, sortable : true, align: 'center'},
 			{display: '公告标题',hide:false, name : 'bullTitle', width : 150, sortable : true, align: 'left'},
 			{display: '招标名称', name : 'project.projName', width : 200, sortable : true, align: 'left'},
 			{display: '公告类型', name : 'bullType',alias:'bullTypeCN', width : 100, sortable : true, align: 'center'}
 			],//列结构
 		buttons : buttons
 	}
	
	$("#BulletinGrid").flexigrid(grid)
			
});