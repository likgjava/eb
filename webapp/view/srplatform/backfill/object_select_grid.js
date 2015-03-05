/**
 * 通过grid回填对象
 * 参数:
 * 弹出层url示例：
 * var grid = {
 * 			//grid请求数据url，注意‘=’由‘:’代替，'&'由';'代替。
 *			url: $('#initPath').val()+'/OrganizationController.do?method:list;status:0',
 *			params:{method:'list',status:'0'},//可选，作为访问上面url的参数，可取代url后面的参数，
 *			usepager:false,//是否分页
 *	 		title: '机构列表',//列表title
 *			selectOne:false,//是否只回填一个对象
 *			checkValues:'',//上次选中的记录的id值
 *			hiddenId:'bb',//存放对象id域
 *			displayId:'aa',//显示对象域
 *			queryColumns:'name,type',//列表查询属性
 *			colModel : [
 *				{display: '名称', name : 'name', width : 200, sortable : true, align: 'right'},
 *				{display: '类型', name : 'type', width : 100, sortable : true, align: 'left'}
 *				],//列结构
 *			displayProperty:'name'//作为显示内容的属性
 *		}
 *	//把整个json对象转换成string作为url的参数的值，参数名为grid
 *   var url =$('#initPath').val()+'/BackfillController.do?method=toGrid&grid='+encodeURI(obj2str(grid));
 */
var objectSelectGrid = {};
objectSelectGrid.grid;
objectSelectGrid.select= function(name,grid){
	if($(grid).isSelectEmpty()){
		return objectSelectGrid.backfill('','');
	}
	if(objectSelectGrid.grid.selectOne){
		if(!$(grid).isSelectOne()){alert('请选择一条记录');return false;}//是否只选中一个
	}
	var ids = $(grid).getSelects().split(',');
	var vals = [];
	$.each(ids,function(i,n){
		var json = $("#objectSelectGridTable").flexGetRowJsonById(ids[i]);
		vals.push(json[objectSelectGrid.grid.displayProperty]);
	})
	objectSelectGrid.backfill(ids,vals);
	
}
objectSelectGrid.backfill = function(hiddenVal,displayVal){
	document.getElementById(objectSelectGrid.grid.hiddenId).value=hiddenVal;
	document.getElementById(objectSelectGrid.grid.displayId).value=displayVal;
	$('#epsDialogClose').click();
	return false;
}
objectSelectGrid.onSuccess = function(){
	if(objectSelectGrid.grid.checkValues){
		var selected = objectSelectGrid.grid.checkValues.split(',');
		if(selected.length>0){
			$('#objectSelectGridTable').selectedRows(selected);//已选记录
		}
	}
}

$(document).ready(function(){
	
	var grid = eval('('+$('#_grid').val()+')');
	grid.url=grid.url.replace(new RegExp(':','gm'),'=').replace(new RegExp(';','gm'),'&');
	grid = $.extend({
			dataType: 'json',
			buttons : [
				{name: '选定', bclass: 'enable', onpress : objectSelectGrid.select},
				{separator: true}
				],
			checkbox:true,
			selectOne:true,
			onSuccess:objectSelectGrid.onSuccess
	},grid);
	objectSelectGrid.grid=grid;
	$("#objectSelectGridTable").flexigrid(grid)
			
});
