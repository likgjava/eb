/**
 * 
 * create by yucy
 * 
 * 
 */
var catalogForm={};

catalogForm.procTypeCN = {"-1":"","00":"公开招标","01":"邀请招标","02":"竞争性谈判","03":"询价","04":"单一来源"};

catalogForm.tree;

//审核
catalogForm.audit = function(auditType){
	var catalogForm  = formToJsonObject("catalogForm");
	catalogForm.auditType = auditType;
	$.getJSON($('#initPath').val()+'/PurCatalogController.do?method=auditPurCatalog',
			catalogForm,
			function(json){
				if(json.success){
					alert(json.result);
					$('#conBody').loadPage($('#initPath').val()+"/view/bizplatform/base/purcatalog/catalog_audit_list.jsp");
				}
			}
		);
}

//初始化树
catalogForm.initGoodsClassTree=function(){
	catalogForm.tree=new dhtmlXTreeObject('goodsClassTreeForm',"100%","100%",0);
	catalogForm.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
	catalogForm.tree.enableDragAndDrop(1);
	catalogForm.tree.enableCheckBoxes(false);
	//初始化按钮不级联选中 0为表示不选中,1为选中状态
	catalogForm.tree.enableThreeStateCheckboxes(0);
	catalogForm.tree.setOnOpenEndHandler(catalogForm.nodeOpen);
	catalogForm.tree.setOnCheckHandler(catalogForm.nodeCheck);
	catalogForm.tree.setOnClickHandler(catalogForm.nodeClick);
	catalogForm.tree.setXMLAutoLoading($('#initPath').val()+'/PurCategoryController.do?method=getTree&action=listById&order_flag=false');
	catalogForm.tree.loadXML($('#initPath').val()+'/PurCategoryController.do?method=getTree&action=listTop&isOpen=0&order_flag=false',function(){
        //初始化树之后的回调函数
        var firstChildIdOfRoot=catalogForm.tree.getChildItemIdByIndex('-1',0); /*取得根节点下面的第一个子节点的Id*/
        
    	//第一层节点的信息
    	catalogForm.getOpenItemInfo($("#objId").val(),"");
    });
}


//树形点击事件
catalogForm.nodeClick = function(id){
	//抓取数据回填表单
	$.getJSON($("#initPath").val()+"/PurCatalogController.do?method=getDetailAndTypeDetailInfo",{"categoryId":id,"catalogId":$("#objId").val()},function(json){
		if(json.success){
			var  catalogdetail = json.detailInfo[0];
			var  catalogType = json.typeInfo;
			
			//没有被添加过
			if((json.detailInfo==null||json.detailInfo.length<=0)&&(json.typeInfo==null||json.typeInfo.length<=0)){
				//初始化新增的表单
				catalogForm.initsaveForm();
				return false;
			}else{
				if(catalogdetail!=null){
					$("#CatalogDetailForm").find("span[id=goodsPrice]").html(catalogdetail.goodsPrice);
					$("#CatalogDetailForm").find("span[id=yearTotal]").html(catalogdetail.yearTotal);
				}
				if(catalogType!=null){
					//清空
					$("#CatalogDetailForm").find("ul:last").empty();
					
					$.each(catalogType,function(index,obj){
						$("#CatalogDetailForm").find("ul:last").append(
								'<li id="'+(index+1)+'" class="fullLine"><label>采购方式：</label><span>'+(obj.procType==null?'':catalogForm.procTypeCN[obj.procType])+'</span><span class="label">采购金额（元）：</span><span>'+(obj.procTotal==null?'':obj.procTotal)+'</span></li>'
						);
					})
					$("#CatalogDetailForm").find("input[name=typeId]").val(catalogType.objId);
					$("#CatalogDetailForm").find("input[name=procType]").val(catalogType.procType);
					$("#CatalogDetailForm").find("input[name=procTotal]").val(catalogType.procTotal);
				}
			}
		}
	});
}

//初始化新增的表单
catalogForm.initsaveForm = function(){
	
	$("#CatalogDetailForm").find("span[id=goodsPrice]").html("");
	$("#CatalogDetailForm").find("span[id=yearTotal]").html("");
	
	$("#CatalogDetailForm").find("table").empty().append(
			'<tr id="1"><td>采购方式：</td><td></td><td>采购方式金额（元）：</td><td></td></tr>'
	);
	$("#saveCatalogDetailBtn").show();
	$("#updateCatalogDetailBtn").hide();
}


//树展开事件
catalogForm.nodeOpen = function(id){
	catalogForm.getOpenItemInfo($("#objId").val(),id);
}

//树check事件
catalogForm.nodeCheck = function(id){
	if(catalogForm.tree.getItemText(id).indexOf('(已选)')>=0){
		catalogForm.tree.setCheck(id,"unsure");
	}else{
		if(catalogForm.tree.getAllChecked()!=null&&catalogForm.tree.getAllChecked().length>0){
			return false;
		}
		//清空表单
		catalogForm.initsaveForm();
		
		$("#updateCatalogDetailBtn").hide();
		$("#saveCatalogDetailBtn").show();
	}
}


//获得展开的节点的信息
catalogForm.getOpenItemInfo = function(catalogId,categoryId){
	
	$.getJSON($("#initPath").val()+"/PurCatalogController.do?method=getOpenItemInfo",
			{"catalogId":catalogId,"categoryId":categoryId},
			function(json){
				$.each(json.openItemInfo,function(index,obj){
					var flag = (obj.length==4)?3:2
					if(obj[flag]=='yes'){
						catalogForm.tree.setCheck(obj[0],"unsure");
						if(catalogForm.tree.getItemText(obj[0])!=null&&catalogForm.tree.getItemText(obj[0]).indexOf('(已选)')<0){
							catalogForm.tree.setItemText(obj[0],catalogForm.tree.getItemText(obj[0])+'(已选)',catalogForm.tree.getItemText(obj[0]));
						}
					}else{
						if(catalogForm.tree.getItemText(obj[0])!=null&&catalogForm.tree.getItemText(obj[0]).indexOf('(其下节点已选)')<0){
							catalogForm.tree.setItemText(obj[0],catalogForm.tree.getItemText(obj[0])+'(其下节点已选)',catalogForm.tree.getItemText(obj[0]));
						}
					}
				})
	})
}

$(document).ready(function(){
	
	//初始化树
	catalogForm.initGoodsClassTree();
	
	//返回
	$("#auditReturn").click(function(){				
		$('#conBody').loadPage($('#initPath').val()+"/view/bizplatform/base/purcatalog/catalog_audit_list.jsp");
	})
	
	//审核通过
	$("#auditPass").click(function(){
		catalogForm.audit("pass");
	})
	
	//审核通过
	$("#auditNoPass").click(function(){
		catalogForm.audit("nopass");
	})
});