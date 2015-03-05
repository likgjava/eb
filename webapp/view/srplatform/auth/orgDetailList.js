//定义文件全局变量 处理方法名重复问题
var OrgDetailList={};
OrgDetailList.pageContent;

//删除
OrgDetailList.delObject = function(id){
	if(window.confirm("确定删除?")){
		$.ajax({
				url:"/OrgDetailController.do?method=remove",
				type:"POST",
				data:{"objId":id},
				success:function(msg){
					OrgDetailList.pageContent.searchForm();
				},
				error:function(msg){
				    alert(msg);
				}
		})
	}
};
//修改
OrgDetailList.updateObject = function(id){
	if($("#openTabs").val()=='true'){
		$('#tabsPage>.ui-tabs-panel:visible').loadPage("/OrgDetailController.do?method=toCreateOrUpdate&objId="+id);
	}else{
		$('#content').loadPage("/OrgDetailController.do?method=toCreateOrUpdate&objId="+id);
	}
};
//查询
OrgDetailList.query=function(){
	var queryJson=formToJsonObject($("#OrgDetailqueryForm")[0]);
	OrgDetailList.pageContent.query(queryJson)
}

$(document).ready(function(){
	$("#OrgDetailPage").parent().attr("colSpan",$("#OrgDetailTemplate td").length)
	OrgDetailList.pageContent=new PageList("OrgDetailTbody","OrgDetailTemplate","OrgDetailList.pageContent","OrgDetailPage");
	OrgDetailList.pageContent.sorttable=function(){
		sorttable.init("OrgDetailTable");
	}
	OrgDetailList.pageContent.setCheckedEdit("checkboxAll","recheck");
	OrgDetailList.pageContent.initData({url:$("#initPath").val()+"/OrgDetailController.do?method=listOld",data:{}},function(obj,j,o,n){
		if(o.id=="update"){
			$(o).append("<a href='javascript:void(0);' class='ui-icon ui-icon-pencil' title='修改'></a>")
			$(o).find("a").click(function(){
				OrgDetailList.updateObject(n.objId);
			})
		}
		if(o.id=="delete"){
			$(o).append("<a href='javascript:void(0);' class='ui-icon ui-icon-close' title='删除'></a>")
			$(o).find("a").click(function(){
				OrgDetailList.delObject(n.objId,obj);
			})
		}
		return true;
	})
	OrgDetailList.pageContent.searchForm();

	$("#OrgDetailqueryBuuton").click(OrgDetailList.query);
	//新增
	$("#newOrgDetail").click(function(){
		if($("#openTabs").val()=='true'){
			$('#tabsPage>.ui-tabs-panel:visible').loadPage("/OrgDetailController.do?method=toCreateOrUpdate");
		}else{
			$('#content').loadPage("/OrgDetailController.do?method=toCreateOrUpdate");
		}
	})
	//展示搜索条件
	var isShowSearch = true;
	$("#search").click(function(){
		if(isShowSearch == true){
			$("#simpleSearch").show();
			isShowSearch =false;
		}else{
			$("#simpleSearch").hide();
			isShowSearch =true;
		}
	})
	//批量删除
	$("#batchDelOrgDetail").click(function(){
    	if(OrgDetailList.pageContent.getCheckedValues()<=0){
    		alert("请至少选择一项");
    		return;
    	}
    	OrgDetailList.delObject(OrgDetailList.pageContent.getCheckedValues().toString())
    });
})
