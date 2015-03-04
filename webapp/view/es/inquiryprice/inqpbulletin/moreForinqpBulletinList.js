//定义文件全局变量 处理方法名重复问题
var BulletinList={};
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
			'<span><a href="#" class="abtn">查看</a></span>'  : function(btn,rowId,obj){
				btn.click(function(){
					BulletinList.view(rowId);
				}).appendTo(obj);
				}
				});
		}else if(auditStatus=='02'){
				$("#BulletinGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">修改</a></span>'  : function(btn,rowId,obj){
					btn.click(function(){
						BulletinList.modify(rowId,bullType);
					}).appendTo(obj);
					}
					});
		}
}
//待审核招标公告
BulletinList.audit=function(rowId){
	$('#conBody').loadPage($('#initPath').val()+'/InqpbulletinController.do?method=getBulletinAuditByObjId&objId='+rowId+'&fromDesk=yes');
}
//待修改的公告
BulletinList.modify=function(rowId,bullType){
	$('#conBody').loadPage($('#initPath').val()+'/InqpbulletinController.do?method=getBulletinByBullType&objId='+rowId+'&bullType='+bullType+'&fromDesk=yes');
}
//获取公告内容
BulletinList.view=function(rowId){
	$('#conBody').loadPage($('#initPath').val()+'/InqpbulletinController.do?method=getBulletinByObjId&objId='+rowId+'&returnUrl=yes');
}