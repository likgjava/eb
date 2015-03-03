var purCategoryTreeForm={};
purCategoryTreeForm.tree;

// 点击节点事件
purCategoryTreeForm.nodeClick=function(id){
	removeEleWarning();

	// 切换表单
	$("#purCategoryFormPage").hide();
	$("#purCategoryDetailPage").show();
	if("" == id || "-1" == id) {
		$("#newPurCategory").attr("disabled",true);
		$("#modifyPurCategory").attr("disabled",true);
		$("#deletePurCategory").attr("disabled",true);
		$("#purCategoryForm")[0].reset();
		json2Object("purCategoryDetail","");
	}else {
		$("#newPurCategory").attr("disabled",false);
		$("#modifyPurCategory").attr("disabled",false);
		$("#newPurCatalog").attr("disabled",false);
		
		$.getJSON($('#initPath').val()+'/PurCategoryController.do?method=createOrUpdate',{objId:id},function(json){
			json2Object("purCategoryDetail",json.purCategory);
			$("#categoryId").val(json.purCategory.objId)
			jsonObjectToForm('purCategoryForm', json.purCategory);
			if(undefined != json.purCategory.parent) {
				$("#parentId").val(json.purCategory.parent.objId)
			}
			if(json.purCategory.isLeaf == "true") {
				$("#deletePurCategory").attr("disabled",false);
			}else {
				$("#deletePurCategory").attr("disabled",true);
			}
		});
	}
}

// 初始化树
purCategoryTreeForm.initGoodsClassTree=function(){
    purCategoryTreeForm.tree=new dhtmlXTreeObject('purCategoryTreeForm',"100%","100%",0);
    purCategoryTreeForm.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
    purCategoryTreeForm.tree.enableDragAndDrop(false);
    purCategoryTreeForm.tree.enableThreeStateCheckboxes(true);
    purCategoryTreeForm.tree.setOnClickHandler(purCategoryTreeForm.nodeClick);
    purCategoryTreeForm.tree.setXMLAutoLoading($('#initPath').val()+'/PurCategoryController.do?method=getTree&order=sort&action=listById');//点 + 号触发展开事件
    purCategoryTreeForm.tree.loadXML($('#initPath').val()+'/PurCategoryController.do?method=getTree&action=listTop&order=sort&isOpen=0',function(){
    });
}

// 新增品目
purCategoryTreeForm.newPurCategory=function(){
	
	// 切换表单
	$("#purCategoryFormPage").show();
	$("#purCategoryDetailPage").hide();
	
	// 初始化id
	var parentId = $("input[id=categoryId]").val();
	$("#purCategoryForm")[0].reset();
	$("input[name=objId]").val("");
	$("input[name=parent.objId]").val(parentId);
}

//修改事件
purCategoryTreeForm.modifyPurCategory = function(){
	
	if("-1" == $("input[id=categoryId]").val() || "" == $("input[id=categoryId]").val()){
		alert("请选择一个节点进行修改!");
		return false;
	}
	
	//切换表单
	$("#purCategoryFormPage").show();
	$("#purCategoryDetailPage").hide();
}

/*保存采购品目*/
purCategoryTreeForm.save=function(id){
	if(!$('#purCategoryForm').valid()){alert('请正确填写表单!');return;}
    var purCategory = formToJsonObject('purCategoryForm');
    
    $.getJSON($('#initPath').val()+'/PurCategoryController.do?method=savePurCategory',purCategory,function(json){
        if(json.result)alert(json.result);
        if(json.failure){
        	alert(failure);
            return;
        }
        
        // 修改
        if($("input[id=categoryId]").val() == json.purCategory.objId){
        	treeUtil.refreshTree(purCategoryTreeForm.tree,"edit",json.purCategory.objId,$("input[id=categoryName]").val());
        }else{// 新增
        	var pid=$("input[id=parentId]").val();
        	var oper = "new";
        	if(pid && pid != "") oper = "child";
        	treeUtil.refreshTree(purCategoryTreeForm.tree,oper,json.purCategory.objId,$("input[id=categoryName]").val());
        	json.purCategory.objId = pid;//新增成功则单击父节点
        }
        purCategoryTreeForm.nodeClick(json.purCategory.objId);
    });
}

/*删除采购品目*/
purCategoryTreeForm.removePurCategory=function(){
	if($("input[id=categoryId]").val()==null||$("input[id=categoryId]").val()==""){
		alert("请选择一个分类，再进行删除操作!");
		return false;
	}
    if(confirm("是否确认删除【"+$('span[id=categoryName]').html()+"】?")){
        $.getJSON($('#initPath').val()+'/PurCategoryController.do?method=removePurCategory',{objId:$("input[id=categoryId]").val()},function(json){
        	if(json.failure){
        		alert("【"+$('span[id=categoryName]').html()+"】已经被关联，不充许删除！");
        		return false;
        	}
        	if(json.result)alert(json.result);
            treeUtil.refreshTree(purCategoryTreeForm.tree,'delete','');
            
            // 点击父节点
            purCategoryTreeForm.nodeClick($("input[name=parent.objId]").val());
        });
    }
}

/*返回*/
purCategoryTreeForm.back=function(){
	if("" == $("input[id=categoryId]").val()|| "-1" == $("input[id=categoryId]").val()){
		purCategoryTreeForm.nodeClick($("input[id=parentId]").val());
	}else{
		purCategoryTreeForm.nodeClick($("input[id=categoryId]").val());
	}
}


/*****业务操作处理 结束*****/

$(document).ready(function(){
	purCategoryTreeForm.initGoodsClassTree();//加载树
	
	$.validator.addMethod("categoryCodeUnique",function(value,element,param){return uniqueHandler("PurCategory",param,value,$("#categoryId").val());},'该品目编号已存在');
	$.validator.addMethod("categoryNameUnique",function(value,element,param){return uniqueHandler("PurCategory",param,value,$("#categoryId").val());},'该品目名称已存在');

	//表单验证
	$("#purCategoryForm").validate({
		rules:{
		categoryCode:{categoryCodeUnique:"categoryCode"},
		categoryName:{categoryNameUnique:"categoryName"}
		}
	});	
    //选择品目
    $('input[id=purCategoryIds.name]').click(function(e){
	    $.epsDialog({
	        title:'请选择品目',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=purCategoryIds&className=PurCategory&action=listTop&isOpen=1&isCheckBox=true&checkValues='+$("input[id=purCategoryIds.objId]").val()
	    }); 
	});
    
    //返回
    $("#returnBtn").click(function(){
    	removeEleWarning();
    	purCategoryTreeForm.back();
    });
    
    //保存
    $("#savePurcategoryBtn").click(function(){
        purCategoryTreeForm.save();
    });
    
    //跳转到新增页面
    $('#newPurCategory').click(function(){
    	removeEleWarning();
        purCategoryTreeForm.newPurCategory();
    });
    
    //修改
    $('#modifyPurCategory').click(function(){
    	removeEleWarning();
        purCategoryTreeForm.modifyPurCategory();
    });
    
    //删除
    $("#deletePurCategory").click(function(){
        purCategoryTreeForm.removePurCategory();
    });
    
    //新增一个目录
    $("#newPurCatalog").click(function(){
    	if($("input[id=categoryId]").val()==null||$("input[id=categoryId]").val()==""){
    		alert("请选择一个品目，再进行增加操作!");
    		return false;
    	}else{
    		//弹出层
    		$.epsDialog({
    			id:'newCatalogByCategory',
    	        title:'新增目录 '+'（您选择的品目是：'+purCategoryTreeForm.tree.getItemText($("input[id=categoryId]").val())+'）',
    	        url:$('#initPath').val()+"/PurCatalogController.do?method=addCatalogFromCategoryView&categoryId="+$("input[id=categoryId]").val(),
    	        width: '900',
    	        height: '500'
    	    }); 
    	}
    })
    
    //上移
    $('#up').click(function(){
        treeUtil.sortUp(purCategoryTreeForm.tree,"up","com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory")
    });

    //下移
    $('#down').click(function(){
    	treeUtil.sortDown(purCategoryTreeForm.tree,"down","com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory")
    });
   
    $("input[name=releaseDate]").epsDatepicker();
    $("#modifyPurCategory").attr("disabled",true);
	$("#deletePurCategory").attr("disabled",true);
	$("#newPurCatalog").attr("disabled",true);
})

function removeEleWarning(){
	$("#purCategoryFormPage span.eleWarning").html("*"); 
	$("#purCategoryFormPage input").removeClass("eleWarning");
}