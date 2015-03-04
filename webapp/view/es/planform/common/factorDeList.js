var factorDeList={};
factorDeList.factortypeId;

factorDeList.add=function(name,grid){
	factorDeList.addFactorDe("");
}   

factorDeList.addFactorDe = function(id){
	
	if(undefined == id || null == id || "" == id){
		$.epsDialog({
	        title:'新增指标',
	        url:$('#initPath').val()+'/FactorDeController.do?method=toCreateFactorDe',
	        width: '600',
	        height: '400',
	        onClose: function(){ 
	        	$("#factorDeGrid").reload();
	        }
		});
	}else{
		$.epsDialog({
	        title:'修改指标',
	        url:$('#initPath').val()+'/FactorDeController.do?method=toUpdateFactorDe&objId='+id,
	        width: '600',
	        height: '400',
	        onClose: function(){ 
	        	$("#factorDeGrid").reload();
	        }
		});
	}
}

	factorDeList.update=function(name,grid){
		if(!factorDeList.validation(name,grid))return;
		$('#conBody').loadPage($('#initPath').val()+'/FactorDeController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
	}   
	factorDeList.remove=function(name,grid){
		if(!factorDeList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/FactorDeController.do?method=remove',{objId:$(grid).getSelect()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	factorDeList.before=function(){
		var option={
			"factortypeDe.objId":factorDeList.factortypeId
		}
		$('#factorDeGrid').flexOptions({params:option});
		return true;
	}
	factorDeList.success=function(){
		$("#factorDeGrid").flexAddOptionStr({
			'<button class="enable" type="button"><span>修改</span></button>' : function(btn,rowId,obj){
				btn.click(function(){
					factorDeList.addFactorDe(rowId);
				}).appendTo(obj);
			},
			'<button class="enable" type="button"><span>删除</span></button>' : function(btn,rowId,obj){
				btn.click(function(){
					if(window.confirm('确定删除吗?')){
						$.getJSON($('#initPath').val()+'/FactorDeController.do?method=removeFactorDe',{"objId":rowId},function(json){
							if(json.result)alert(json.result);if(json.failure)return;
							$("#factorDeGrid").reload();
						});
					}
				}).appendTo(obj);
			}
		});
	}
$(document).ready(function(){
	factorDeList.factortypeId = $("#_factortypeId").val();
});

