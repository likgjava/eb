var templateList={};
templateList.pageContent;

//删除
templateList.delObject = function(id){
	if(window.confirm("确定删除?")){
		$.ajax({
				url:"TemplateController.do?method=remove",
				type:"POST",
				data:{"objId":id},
				success:function(msg){
					templateList.pageContent.searchForm();
				}
		})
	}
};

//修改
templateList.updateObject = function(id){
	loadPage_toContent("TemplateController.do?method=toCreateOrUpdate&objId="+id);
};

//查询
templateList.query=function(){
	var queryJson=formToJsonObject($("#TemplatequeryForm")[0]);
	templateList.pageContent.query(queryJson)
}

$(document).ready(function(){
	
	templateList.typeList=$.ajax({ url: "DictionaryController.do?method=getBaseObjectListByProperty&_queryCols=objId,dicName",data:{"dicType":"templateType"}, async: false }).responseText
	templateList.typeJosn =JSON.parse(templateList.typeList);
	if(templateList.typeJosn.length == 0)
		$("#typeId").append("<option value=''>暂无模版类型，请先在字典表中创建模版类型</option>");
	else{
		$.each(templateList.typeJosn,function(i,n){
			$("#typeId").append("<option value='"+n.objId+"'>"+n.dicName+"</option>")
		})
	}
	
	templateList.pageContent=new PageList("TemplateTbody","TemplateTemplate","templateList.pageContent","TemplatePage");
	templateList.pageContent.sorttable=function(){
		sorttable.init("TemplateTable");
	}
	templateList.pageContent.setCheckedEdit("checkboxAll","recheck");
	templateList.pageContent.initData({url:"TemplateController.do?method=listOld",data:{}},function(obj,j,o,n){
		if(o.id=="update"){
			$(o).append("<a href='javascript:void(0);' class='ui-icon ui-icon-pencil' title='修改'></a>")
			$(o).find("a").click(function(){
				templateList.updateObject(n.objId);
			})
		}
		if(o.id=="delete"){
			$(o).append("<a href='javascript:void(0);' class='ui-icon ui-icon-close' title='删除'></a>")
			$(o).find("a").click(function(){
				templateList.delObject(n.objId);
			})
		}
		
		return true;
	})
	templateList.pageContent.searchForm();

	$("#TemplatequeryBuuton").click(templateList.query);
	
	//新增
	$("#newTemplate").click(function(){
		loadPage_toContent("TemplateController.do?method=toCreateOrUpdate");
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
	$("#batchDelTemplate").click(function(){
    	if(templateList.pageContent.getCheckedValues()<=0){
    		alert("请至少选择一项");
    		return;
    	}
    	templateList.delObject(templateList.pageContent.getCheckedValues().toString())
    });
})