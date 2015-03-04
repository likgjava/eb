/*
 * 维护分类页面
 * create by liujf
 * modify by yucy 
 */
var goodsClassTreeForm={};
goodsClassTreeForm.tree;
goodsClassTreeForm.paramInputType = {'01':'整体录入','02':'分项录入','03':'两者任选'};

//处理树 开始
function treeTab(){
	treeHe();
	$(window).wresize(treeHe);
	var h =$('#contentThis').height();
	$('.treeOutside').jqResize('.treeResize',function(w){if(parseInt(w)<240){$('.treeOutside').width(240);}	$('.treeOutside').height(h)});
	function treeHe(){$('.treeOutside,.treeRight,.treeResize').height($('#contentThis').height());}
}

///点击节点事件
goodsClassTreeForm.nodeClick=function(id){
	//切换表单
	$("#goodsClassFormPage").hide();
	$("#goodsClassDetailPage").show();
	//点击非根节点
	if(id!='-1'){
		$.getJSON($('#initPath').val()+'/GoodsClassController.do?method=getGoodsClassDetail',{goodsClassId:id},function(json){
			if(json.goodsClassInfoMap){
				json2Object("goodsClassDetail",json.goodsClassInfoMap.goodsClass,true);
				$("div[id=purCategoryIdsName]").html(json.goodsClassInfoMap.purCategoryNames!=null?json.goodsClassInfoMap.purCategoryNames:"");
				$("span[id=paramInputType]").html(goodsClassTreeForm.paramInputType[json.goodsClassInfoMap.goodsClass.paramInputType]);
				$("input[name=objId]").val(json.goodsClassInfoMap.goodsClass.objId); //设置id
				if(null!=json.goodsClassInfoMap.goodsClass.parentClazz&&""!=json.goodsClassInfoMap.goodsClass.parentClazz){
					$("input[name=parentClazz.objId]").val(json.goodsClassInfoMap.goodsClass.parentClazz.objId); //设置父id
				}
			}
		});
	}
	//点击根节点
	else{
		goodsClassTreeForm.resetDetail();
		goodsClassTreeForm.resetForm();
	}
}

//初始化树
goodsClassTreeForm.initGoodsClassTree=function(){
    goodsClassTreeForm.tree=new dhtmlXTreeObject('goodsClassTreeForm',"100%","100%",0);
    goodsClassTreeForm.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
    goodsClassTreeForm.tree.enableDragAndDrop(false);
    goodsClassTreeForm.tree.enableThreeStateCheckboxes(true);
    goodsClassTreeForm.tree.setOnClickHandler(goodsClassTreeForm.nodeClick);
    goodsClassTreeForm.tree.setXMLAutoLoading($('#initPath').val()+'/GoodsClassController.do?method=getTree&action=listById&order=sort&order_flag=false');//点 + 号触发展开事件
    goodsClassTreeForm.tree.loadXML($('#initPath').val()+'/GoodsClassController.do?method=getTree&action=listTop&isOpen=0&order=sort&order_flag=false',function(){
    });
}

//重置表单
goodsClassTreeForm.resetForm = function(){
	$("#goodsClassForm")[0].reset();
	$("input[name=objId],input[name=parentClazz.objId],input[name=goodsClassCode],input[name=goodsClassName],input[name=purCategoryNames],input[name=purCategoryIds],textarea[name=remarks]").val("");
}

//重置详情
goodsClassTreeForm.resetDetail = function(){
	$("span[id=goodsClassCode],span[id=goodsClassName],span[id=shortSpellName],span[id=paramInputType],div[id=purCategoryIdsName],span[id=remarks]").html("");
}


//新增子分类
goodsClassTreeForm.newSubGoodsClass=function(){
	//切换表单
	$("#goodsClassFormPage").show();
	$("#goodsClassDetailPage").hide();
	//取得分配的分类 编号
	$.getJSON($('#initPath').val()+'/GoodsClassController.do?method=getGoodsClassCode',
			{"parentClassId":$("input[name=objId]").val()},
			function(json){
				if(null!=json){
					
					var parentId = $("input[name=objId]").val();
					
					//清空表单
					goodsClassTreeForm.resetForm();
					
					$("input[id=goodsClassCode]").val(json.goodsClassCode);
					//初始化id
					$("input[name=parentClazz.objId]").val(parentId);
					
				}
			}
	);
}

//修改事件
goodsClassTreeForm.modifyGoodsClass = function(){
	var id = $("input[id=objId]").val();
	if(null==id||""==id){
		alert("请选择一个非根节点进行修改!");
		return false;
	}
	//切换表单
	$("#goodsClassFormPage").show();
	$("#goodsClassDetailPage").hide();
	$.getJSON($('#initPath').val()+'/GoodsClassController.do?method=getGoodsClassDetail',{goodsClassId:id},function(json){
        jsonObjectToForm('goodsClassForm', json.goodsClassInfoMap.goodsClass);
        $("span[id=purCategoryIds.name]").html(json.goodsClassInfoMap.purCategoryNames==null?"":json.goodsClassInfoMap.purCategoryNames);
        $("input[id=purCategoryIds.objId]").val(json.goodsClassInfoMap.purCategoryIds);
        $("input[id=purCategoryIds.name]").val(json.goodsClassInfoMap.purCategoryNames==null?"":json.goodsClassInfoMap.purCategoryNames);
        $("textarea[id=remarks]").val(json.goodsClass.remarks==null?"":json.goodsClass.remarks);
    });
}

//保存采购分类的值
goodsClassTreeForm.save=function(id){
	if(!$('#goodsClassForm').valid()){alert('请正确填写表单!');return;}
    var goodsClazz = formToJsonObject('goodsClassForm');
    goodsClazz.purCategoryIds= $("input[id=purCategoryIds.objId]").val();
    
    $.getJSON($('#initPath').val()+'/GoodsClassController.do?method=saveGoodsClass',goodsClazz,function(json){
        if(json.success)alert('保存成功！');
        if(json.failure){
        	alert('保存失败，原因：'+json.result);
            return;
        }
        if($("input[id=objId]").val()==json.objId){
        	treeUtil.refreshTree(goodsClassTreeForm.tree,"edit",json.objId,$("input[id=goodsClassName]").val());
        }else{
        	var pid=$("input[id=parentClazzObjId]").val();
        	var oper = "new";
        	if(pid && pid != "") oper = "child";
        	treeUtil.refreshTree(goodsClassTreeForm.tree,oper,json.objId,$("input[id=goodsClassName]").val());
        	json.objId = pid;//新增成功则单击父节点
        }
        goodsClassTreeForm.nodeClick(json.objId);
    });
}

//删除商品分类
goodsClassTreeForm.removeGoodsClass=function(){
	if($("input[name=objId]").val()==null||$("input[name=objId]").val()==""){
		alert("请选择一个非根节点，再进行删除操作!");
		return false;
	}
    if(confirm("是否确认删除【"+$('span[id=goodsClassName]').html()+"】?")){
        $.getJSON($('#initPath').val()+'/GoodsClassController.do?method=removeGoodsClass',{goodsClassId:$("input[name=objId]").val()},function(json){
            if(json.result)alert(json.result);if(json.failure)return;
            treeUtil.refreshTree(goodsClassTreeForm.tree,'delete','');
            //点击父节点
            goodsClassTreeForm.nodeClick($("input[name=parentClazz.objId]").val());
        });
    }
}

//加一个className验证规则
$.validator.addMethod("classNameUnique",function(value,element,param){return uniqueHandler("GoodsClass",param,value, $("#objId").val() );},'该分类已存在');
//规则引入
$("#goodsClassForm").validate({ rules:{goodsClassName:{classNameUnique:"goodsClassName"}} });

$(document).ready(function(){
	//加载树
	goodsClassTreeForm.initGoodsClassTree();
	
	//表单验证
	$('#goodsClassForm').validate();
    
    //选择品目
    $('input[id=purCategoryIds.name]').click(function(e){
	    $.epsDialog({
	        title:'请选择品目',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=purCategoryIds&className=PurCategory&action=listTop&isOpen=1&isCheckBox=true&childNodeOnly=true&checkValues='+$("input[id=purCategoryIds.objId]").val()
	    }); 
	});
    
    //返回
    $("#returnBtn").click(function(){
    	if(null==$("input[name=objId]").val()||""==$("input[name=objId]").val()){
    		goodsClassTreeForm.nodeClick($("input[name=parentClazz.objId]").val());
    	}else{
    		goodsClassTreeForm.nodeClick($("input[name=objId]").val());
    	}
    });
    
    //保存
    $("#saveGoodsClassBtn").click(function(){
        goodsClassTreeForm.save();
    });

    //跳转到新增页面
    $('#newGoodsClass').click(function(){
        goodsClassTreeForm.newSubGoodsClass();
    });
    
    //修改
    $('#modifyGoodsClass').click(function(){
        goodsClassTreeForm.modifyGoodsClass();
    });
    
    //删除
    $("#deleteGoodsClass").click(function(){
        goodsClassTreeForm.removeGoodsClass();
    });
    
    //上移
    $('#up').click(function(){
    	treeUtil.sortUp(goodsClassTreeForm.tree,"up","com.gpcsoft.goods.goodsclass.domain.GoodsClass")
    });

    //下移
    $('#down').click(function(){
    	treeUtil.sortDown(goodsClassTreeForm.tree,"down","com.gpcsoft.goods.goodsclass.domain.GoodsClass")
    });
})
