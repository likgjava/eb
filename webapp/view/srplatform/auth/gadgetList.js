
var gadgetList={};

//新增
gadgetList.add=function(name,grid){
//	$.epsDialog({
//        title:'添加小工具',
//        url:$('#initPath').val()+'/GadgetController.do?method=toCreateOrUpdate',
//        width: '380',
//        height: '190',
//        hasBg:true,//背景
//        fadeTo:80//透明度
//    });
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+"/GadgetController.do?method=toCreateOrUpdate");
}

gadgetList.success=function(){
	
	$("#gadgetGrid").flexAddOptionStr({
		 '<button class="enable" type="button"><span>修改</span></button>' : function(btn,rowId,obj){
			 btn.click(function(){
//					$.epsDialog({
//				        title:'修改小工具',
//				        url:$('#initPath').val()+'/GadgetController.do?method=toCreateOrUpdate&objId='+rowId,
//				        width: '380',
//				        height: '190',
//				        hasBg:true,//背景
//				        fadeTo:80//透明度
//				    });
				//跳转到修改页面
				$('#conBody').loadPage('GadgetController.do?method=toCreateOrUpdate&objId='+rowId);
			 }).appendTo(obj);
		},
		 '<button class="enable" type="button"><span>删除</span></button>' : function(btn,rowId,obj){
			 btn.click(function(){
//				if(window.confirm('确定删除 '+$('#gadgetGrid').flexGetRowJsonById(rowId).name+' ?')){
					$.getJSON($('#initPath').val()+'/GadgetController.do?method=remove',{objId:rowId},function(json){
						if(json.result)alert(json.result);if(json.failure)return;
						$('#gadgetGrid').flexReload($('#gadgetGrid').flexOptions()[0].p);
					});
//				}
			 }).appendTo(obj);
		}
	});
}

$(document).ready(function(){
 
});



