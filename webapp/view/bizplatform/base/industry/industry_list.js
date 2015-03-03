var industryTreeForm={};
industryTreeForm.tree;

// 点击节点事件
industryTreeForm.nodeClick=function(id){
	removeEleWarning();
	// 切换表单
	$("#industryFormPage").hide();
	$("#industryDetailPage").show();
	if("" == id || "-1" == id) {
		$("#modifyIndustry").attr("disabled",true);
		$("#deleteIndustry").attr("disabled",true);
		$("#newIndustry").attr("disabled",false);
		$("#industryForm")[0].reset();
		json2Object("industryDetail","");
		$("input[name=level]").val(0);
	}else {
		$("#newIndustry").attr("disabled",false);
		$("#modifyIndustry").attr("disabled",false);
		
		$.getJSON($('#initPath').val()+'/IndustryController.do?method=createOrUpdate',{objId:id},function(json){
			json2Object("industryDetail",json.industry);
			$("#industryId").val(id)
			jsonObjectToForm('industryForm', json.industry);
			if(undefined != json.industry.parent) {
				$("#parentId").val(json.industry.parent.objId)
			}
			if(json.industry.isLeaf == "true") {
				$("#deleteIndustry").attr("disabled",false);
			}else {
				$("#deleteIndustry").attr("disabled",true);
			}
		});
	}
}

// 初始化树
industryTreeForm.initGoodsClassTree=function(){
    industryTreeForm.tree=new dhtmlXTreeObject('industryTreeForm',"100%","100%",0);
    industryTreeForm.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
    industryTreeForm.tree.enableDragAndDrop(false);
    industryTreeForm.tree.enableThreeStateCheckboxes(true);
    industryTreeForm.tree.setOnClickHandler(industryTreeForm.nodeClick);
    industryTreeForm.tree.setXMLAutoLoading($('#initPath').val()+'/IndustryController.do?method=getTree&order=sort&action=listById');//点 + 号触发展开事件
    industryTreeForm.tree.loadXML($('#initPath').val()+'/IndustryController.do?method=getTree&action=listTop&order=sort&isOpen=0',function(){
    });
}

// 新增品目
industryTreeForm.newIndustry=function(){
	
	// 切换表单
	$("#industryFormPage").show();
	$("#industryDetailPage").hide();
	
	// 初始化id
	var parentId = $("input[id=industryId]").val();
	var level = $("input[name=level]").val();
	$("#industryForm")[0].reset();
	$("input[name=objId]").val("");
	$("input[name=parent.objId]").val(parentId);
	$("input[name=level]").val(parseInt(level)+1);
}

//修改事件
industryTreeForm.modifyIndustry = function(){
	
	if("-1" == $("input[id=industryId]").val() || "" == $("input[id=industryId]").val()){
		alert("请选择一个节点进行修改!");
		return false;
	}
	
	//切换表单
	$("#industryFormPage").show();
	$("#industryDetailPage").hide();
}

/*保存行业*/
industryTreeForm.save=function(id){
	if(!$('#industryForm').valid()){alert('请正确填写表单!');return;}
    var industry = formToJsonObject('industryForm');
    
    $.getJSON($('#initPath').val()+'/IndustryController.do?method=saveIndustry',industry,function(json){
        if(json.result)alert(json.result);
        if(json.failure){
        	alert(failure);
            return;
        }
        
        // 修改
        if($("input[id=industryId]").val() == json.industry.objId){
        	treeUtil.refreshTree(industryTreeForm.tree,"edit",json.industry.objId,$("input[id=name]").val());
        }else{// 新增
        	var pid=$("input[id=parentId]").val();
        	var oper = "new";
        	if(pid && pid != "") oper = "child";
        	treeUtil.refreshTree(industryTreeForm.tree,oper,json.industry.objId,$("input[id=name]").val());
        }
        industryTreeForm.nodeClick(json.industry.objId);
    });
}

/*删除行业*/
industryTreeForm.removeIndustry=function(){
	if($("input[id=industryId]").val()==null||$("input[id=industryId]").val()==""){
		alert("请选择一个分类，再进行删除操作!");
		return false;
	}
    if(confirm("是否确认删除【"+$('span[id=name]').html()+"】?")){
        $.getJSON($('#initPath').val()+'/IndustryController.do?method=removeIndustry',{objId:$("input[id=industryId]").val()},function(json){
        	if(json.failure){
        		alert("【"+$('span[id=name]').html()+"】已经被关联，不充许删除！");
        		return false;
        	}
        	if(json.result)alert(json.result);
            treeUtil.refreshTree(industryTreeForm.tree,'delete','');
            
            // 点击父节点
            industryTreeForm.nodeClick($("input[name=parent.objId]").val());
        });
    }
}

/*返回*/
industryTreeForm.back=function(){
	if("" == $("input[id=industryId]").val()|| "-1" == $("input[id=industryId]").val()){
		industryTreeForm.nodeClick($("input[id=parentId]").val());
	}else{
		industryTreeForm.nodeClick($("input[id=industryId]").val());
	}
}


/*****业务操作处理 结束*****/

$(document).ready(function(){
	industryTreeForm.initGoodsClassTree();//加载树
	
	$.validator.addMethod("codeUnique",function(value,element,param){return uniqueHandler("Industry",param,value,$("#industryId").val());},'该行业编号已存在');
	$.validator.addMethod("nameUnique",function(value,element,param){return uniqueHandler("Industry",param,value,$("#industryId").val());},'该行业名称已存在');

	//表单验证
	$("#industryForm").validate({
		rules:{
		code:{codeUnique:"code"},
		name:{nameUnique:"name"}
		}
	});	
    
    //返回
    $("#returnBtn").click(function(){
    	removeEleWarning();
    	industryTreeForm.back();
    });
    
    //保存
    $("#saveIndustryBtn").click(function(){
        industryTreeForm.save();
    });
    
    //跳转到新增页面
    $('#newIndustry').click(function(){
    	removeEleWarning();
        industryTreeForm.newIndustry();
    });
    
    //修改
    $('#modifyIndustry').click(function(){
    	removeEleWarning();
        industryTreeForm.modifyIndustry();
    });
    
    //删除
    $("#deleteIndustry").click(function(){
        industryTreeForm.removeIndustry();
    });
    
    //上移
    $('#up').click(function(){
        treeUtil.sortUp(industryTreeForm.tree,"up","com.gpcsoft.bizplatform.base.industry.domain.Industry")
    });

    //下移
    $('#down').click(function(){
    	treeUtil.sortDown(industryTreeForm.tree,"down","com.gpcsoft.bizplatform.base.industry.domain.Industry")
    });
   
    $("input[name=releaseDate]").epsDatepicker();
    $("#modifyIndustry").attr("disabled",true);
	$("#deleteIndustry").attr("disabled",true);
	$("#newIndustry").attr("disabled",true);
})

function removeEleWarning(){
	$("#industryFormPage span.eleWarning").html("*"); 
	$("#industryFormPage input").removeClass("eleWarning");
}