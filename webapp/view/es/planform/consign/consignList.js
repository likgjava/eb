/*
 * 执行平台，委托书列表
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var consignList={};

//设定返回时的路径
consignList.setRetrun=function(){
	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/consign/consignList.jsp");
}

//新增
consignList.add=function(name,grid){
	$('#conBody').empty().loadPage($('#initPath').val()+"/view/es/planform/consign/consignDraft.jsp");
}   
//修改
consignList.update=function(name,grid){
	if(!consignList.validation(name,grid))return;
	//跳转到修改页面
	$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=toSaveOrUpdate&isSave=YES&objId='+$(grid).getSelect());
}   

//查看
consignList.showDetail=function(name,grid){
	if(!consignList.validation(name,grid))return;
	$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=showDetail&objId='+$(grid).getSelect());
}

//提交确认
consignList.submit=function(name,grid){
	if(!consignList.validation(name,grid))return;
	if(window.confirm('确定要提交招标单位确认吗？')){
		$.getJSON($('#initPath').val()+'/ConsignController.do?method=batchSubmitConsign',{"objIds":$(grid).getSelects(),"confirmStatus":"01","useStatus":"01"},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$(grid).reload();//刷新
		});
	}
}

//删除
consignList.remove=function(name,grid){
	if(window.confirm('确定'+name+'?')){
		$.getJSON($('#initPath').val()+'/ConsignController.do?method=remove',{objId:$(grid).getSelect()},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$(grid).reload();//刷新
		});
	}
}

//创建项目
consignList.createProject = function(name,grid){
	//跳转到修改页面
	$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toCreateProjectByConsignId&objId='+$(grid).getSelect());
}
//列表操作验证
consignList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请至少选择一份委托协议！');return false;}//是否选中
	return true;
}
//查询条件过滤
var isClick = true;
consignList.before=function(){
	// 如果是从桌面进入，则：默认显示被退回的委托协议
	var paramType = $('#param_type').val();
	if('desktop' == paramType){
		if(isClick){
			isClick = false;
			 $('#param_type').val('');
		}
		$('#tabs_toAdjust').click();
		return false;
	}
	if('toSubmit' == paramType){
		if(isClick){
			isClick = false;
			 $('#param_type').val('');
		}
		$('#tabs_toSubmit').click();
		return false;
	}
    var option={"findMethod":"forSubmitByAgent"}
	$('#consignGrid').flexOptions({params:option});
	return true;
}
consignList.success = function(){
	if(consignList.currentTabID == "tabs_toSure"){
		$("#consignGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=showDetail&fromto=consignList&objId='+rowId+"&returnType=view/es/planform/consign/consignList.jsp");
				}).appendTo(obj);
			}
		});
	}
	if(consignList.currentTabID == "tabs_toSubmit"){
		$("#consignGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=showDetail&fromto=consignList&objId='+rowId+"&returnType=view/es/planform/consign/consignList.jsp&isShowOpinion=false");
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=toSaveOrUpdate&isSave=YES&objId='+rowId+"&isShowOpinion=false");
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					if(window.confirm('确定删除?')){
						$.getJSON($('#initPath').val()+'/ConsignController.do?method=removeConsign',{"objId":rowId},function(json){
							if(json.result)alert(json.result);if(json.failure)return;
							$("#consignGrid").reload();
						});
					}
				}).appendTo(obj);
			}
		});
	}
	if(consignList.currentTabID == "tabs_toAdjust"){
		$("#consignGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=showDetail&fromto=consignList&objId='+rowId+"&returnType=view/es/planform/consign/consignList.jsp&isShowOpinion=true");
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=toSaveOrUpdate&isSave=YES&objId='+rowId+"&isShowOpinion=true");
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					if(window.confirm('确定删除?')){
						$.getJSON($('#initPath').val()+'/ConsignController.do?method=removeConsign',{"objId":rowId},function(json){
							if(json.result)alert(json.result);if(json.failure)return;
							$("#consignGrid").reload();
						});
					}
				}).appendTo(obj);
			}
		});
	}
	if(consignList.currentTabID == "tabs_done"){
		$("#consignGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=showDetail&fromto=consignList&objId='+rowId+"&returnType=view/es/planform/consign/consignList.jsp");
				}).appendTo(obj);
			}
		});
	}
}

$(document).ready(function(){

	//日历控件
	$('#consTime').epsDatepicker();
	
	//设定返回时的路径
	consignList.setRetrun();

	//加载tabs
	$('#epsTabs').tabs();
	
	//tabs的点击事件
	consignList.currentTabID = "tabs_toSubmit";
	$("li a.refreshData").click(function(){
		consignList.currentTabID = $(this).attr("id");
		//参数值
		var paramValue = "";
		var buttons = [];
		if(consignList.currentTabID == "tabs_toSubmit"){//待提交	
			buttons = [{name:"批量提交",bclass:"audit",onpress:consignList.submit}];
			$('#consignGrid').attr("p").url = "ConsignController.do?method=list&confirmStatus=00";
		}else if(consignList.currentTabID == "tabs_toSure"){//待确认
			buttons = [];
			$('#consignGrid').attr("p").url = "ConsignController.do?method=list&findMethod=waitConfirmByBuyer";
		}else if(consignList.currentTabID == "tabs_toAdjust"){//被退回
			buttons = [{name:"批量提交",bclass:"audit",onpress:consignList.submit}];
			$('#consignGrid').attr("p").url = "ConsignController.do?method=list&findMethod=returnBackByBuyer";
		}else if(consignList.currentTabID == "tabs_done"){//已确认
			buttons = [];
			$('#consignGrid').attr("p").url = "ConsignController.do?method=list&findMethod=alreadyPassByBuyer";
		}
		$('#consignGrid').flexReDrawButtons(buttons);
		$('#consignGrid').reload();
	})

	
	
	

	
});

