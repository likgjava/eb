var proofOpinionList={};
proofOpinionList.rows=null;//列表查询的结果集

var isproof = $("#isProof").val();

//新增
proofOpinionList.add=function(name,grid){
	//跳转到新增页面
	var projectId = $("#projectId").val();
	if(isproof=='YES'){alert("已经录入论证结果，不能再新增！");return ;}
	proofOpinionList.loadPopUupProgram("新增论证信息","/ProofOpinionController.do?method=toProofOpinionForm&projectId="+projectId);
}
//查询条件过滤
proofOpinionList.before=function(){
	var option={
		'tenderId':$("#projectId").val()
	}
	$('#proofOpinionGrid').flexOptions({params:option});
	return true;
}
//加载数据成功之后调用的函数
proofOpinionList.success=function(){
	$("#proofOpinionGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn" title="修改论证信息">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>'  : function(btn,rowId,obj){
			btn.click(function(){
				if(isproof=='YES'){alert("已经录入论证结果，不能再修改！");return ;}
				proofOpinionList.loadPopUupProgram("修改论证信息",'/ProofOpinionController.do?method=toUpdateProofOpinionForm&objId='+rowId);
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn" title="删除论证信息">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				if(isproof=='YES'){alert("已经录入论证结果，不能再删除！");return ;}
				if(window.confirm('确定删除吗?')){
					$.getJSON($('#initPath').val()+'/ProofOpinionController.do?method=remove',{objId:rowId},function(json){
						$('#proofOpinionGrid').reload();
					});
				}
			}).appendTo(obj);
		}
	});
}
//查看论证结果
proofOpinionList.look=function(){
	proofOpinionList.loadPopUupProgram("录入论证结果",'/ProofOpinionController.do?method=toLookProofOpinion&projectId='+$("#projectId").val());	
}
// 弹出层
proofOpinionList.loadPopUupProgram = function(showName,url){
	//$('#epsDialogCloseNoReload').click();
	$.epsDialog({
        title:showName,
        url:$('#initPath').val()+url,
        width: '800',
        height: '300',
        maxWin:false,
        onClose: function(){ 
        }
	});	
}
$(document).ready(function(){
	
});

