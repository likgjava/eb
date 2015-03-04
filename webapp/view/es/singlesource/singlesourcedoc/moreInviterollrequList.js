
var moreInviterollrequList={};

//查询条件过滤
moreInviterollrequList.before=function(){
	var option={
		'auditStatus':$('#auditStatus').val(),
		'useStatus':$('#useStatus').val()
	}
	$('#inviterollrequGrid').flexOptions({params:option});
	return true;
}

//加载数据成功之后调用的函数
moreInviterollrequList.success=function(){
	var auditStatus =$('#auditStatus').val();
	var useStatus = $('#useStatus').val();
	if(auditStatus=='01'&&useStatus=='01'){
	$("#inviterollrequGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">审核</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			 btn.click(function(){
				var rowobj = $("#inviterollrequGrid").getRowById(rowId);
				moreInviterollrequList.auditInviterollrequ(rowobj.project.objId);
			 }).appendTo(obj);
		}
	});
	}
}

//待招标单位确认的合同
moreInviterollrequList.auditInviterollrequ=function(rowId){
	$('#conBody').loadPage($('#initPath').val()+'/InviterollrequController.do?method=toInviterollrequAudit&projectId='+rowId);
}




