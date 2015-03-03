/**
 * 
 * create by yucy
 * 
 * 
 */
var catalogDetailForm={};
catalogDetailForm.tree;
var templateLi = $("#templateUl").find("li");

//初始化树
catalogDetailForm.initGoodsClassTree=function(){
	catalogDetailForm.tree=new dhtmlXTreeObject('goodsClassTreeForm',"100%","100%",0);
	catalogDetailForm.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
	catalogDetailForm.tree.enableDragAndDrop(1);
	catalogDetailForm.tree.enableCheckBoxes(true);
	//初始化按钮不级联选中 0为表示不选中,1为选中状态
	catalogDetailForm.tree.enableThreeStateCheckboxes(0);
	
	catalogDetailForm.tree.setOnOpenEndHandler(catalogDetailForm.nodeOpen);
	
	catalogDetailForm.tree.setOnCheckHandler(catalogDetailForm.nodeCheck);
	
	catalogDetailForm.tree.setOnClickHandler(catalogDetailForm.nodeClick);
	
	catalogDetailForm.tree.setXMLAutoLoading($('#initPath').val()+'/PurCategoryController.do?method=getTree&action=listById&order_flag=false');
	catalogDetailForm.tree.loadXML($('#initPath').val()+'/PurCategoryController.do?method=getTree&action=listTop&isOpen=0&order_flag=false',function(){
        //初始化树之后的回调函数
        var firstChildIdOfRoot=catalogDetailForm.tree.getChildItemIdByIndex('-1',0); /*取得根节点下面的第一个子节点的Id*/
        
    	//第一层节点的信息
    	catalogDetailForm.getOpenItemInfo($("#objId").val(),"");
    });
}

//树形点击事件
catalogDetailForm.nodeClick = function(id){
	//抓取数据回填表单
	$.getJSON($("#initPath").val()+"/PurCatalogController.do?method=getDetailAndTypeDetailInfo",{"categoryId":id,"catalogId":$("#objId").val()},function(json){
		if(json.success){
			var  catalogdetail = json.detailInfo[0];
			var  catalogType = json.typeInfo;
			//没有被添加过
			if((json.detailInfo==null||json.detailInfo.length<=0)&&(json.typeInfo==null||json.typeInfo.length<=0)){
				//初始化新增的表单
				catalogDetailForm.initsaveForm();
				return false;
			}else{
				//改变按钮
				$("#updateCatalogDetailBtn").show();
				$("#saveCatalogDetailBtn").hide();
				
				if(catalogdetail!=null){
					$("#CatalogDetailForm").find("input[name=detailId]").val(catalogdetail.objId);
					$("#CatalogDetailForm").find("input[name=goodsPrice]").val(catalogdetail.goodsPrice);
					$("#CatalogDetailForm").find("input[name=yearTotal]").val(catalogdetail.yearTotal);
				}
				
				if(catalogType!=null){
					//清空
					$("#CatalogDetailForm").find("ul:last").empty();
					
					$.each(catalogType,function(index,obj){
						var newLi = $(templateLi).clone();
						$(newLi).attr("id",index+1);
						$(newLi).find("input:first").val(obj.objId);
						$(newLi).find("input:last").val(obj.procTotal);
						
						$("#CatalogDetailForm").find("ul:last").append(newLi);
						$("#CatalogDetailForm").find("ul:last li:last").find("select[name=procType]").val(obj.procType);
					})
				}
			}
		}
	});
}


//树展开事件
catalogDetailForm.nodeOpen = function(id){
	catalogDetailForm.getOpenItemInfo($("#objId").val(),id);
}

//树check事件
catalogDetailForm.nodeCheck = function(id){
	if(catalogDetailForm.tree.getAllChecked()!=id&&catalogDetailForm.tree.getAllChecked()!=null&&catalogDetailForm.tree.getAllChecked().length>0){
		return false;
	}
	//清空表单
	catalogDetailForm.initsaveForm();
	$("#updateCatalogDetailBtn").hide();
	$("#saveCatalogDetailBtn").show();	
}

//初始化新增的表单
catalogDetailForm.initsaveForm = function(){
	$("#CatalogDetailForm").find("input[name=detailId]").val("");
	$("#CatalogDetailForm").find("input[name=goodsPrice]").val("");
	$("#CatalogDetailForm").find("input[name=yearTotal]").val("");
	
	$("#CatalogDetailForm").find("ul:last").empty().append(
			$(templateLi).clone()
	);
	$("#saveCatalogDetailBtn").show();
	$("#updateCatalogDetailBtn").hide();
}


//获得展开的节点的信息
catalogDetailForm.getOpenItemInfo = function(catalogId,categoryId){
	$.getJSON($("#initPath").val()+"/PurCatalogController.do?method=getOpenItemInfo",
			{"catalogId":catalogId,"categoryId":categoryId},
			function(json){
				$.each(json.openItemInfo,function(index,obj){
					var flag = (obj.length==4)?3:2
					if(obj[flag]=='yes'){
						catalogDetailForm.tree.setCheck(obj[0],"unsure");
						catalogDetailForm.tree.disableCheckbox(obj[0],1);
						if(catalogDetailForm.tree.getItemText(obj[0]).indexOf('(已选)')<0){
							catalogDetailForm.tree.setItemText(obj[0],catalogDetailForm.tree.getItemText(obj[0])+'(已选)',catalogDetailForm.tree.getItemText(obj[0]));
						}
					}else{
						if(catalogDetailForm.tree.getItemText(obj[0]).indexOf('(其下节点已选)')<0){
							catalogDetailForm.tree.setItemText(obj[0],catalogDetailForm.tree.getItemText(obj[0])+'(其下节点已选)',catalogDetailForm.tree.getItemText(obj[0]));
						}
					}
				})
	})
}

//保存事件
catalogDetailForm.saveCatalogDetail = function(ids){
	if(!$('#CatalogDetailForm').valid()){alert('请正确填写表单!');return;}
	//至少选择一个品目
	if(null==ids||""==ids){alert("您至少需要选择一个品目！");return false;}
	
	var detailId = $("#CatalogDetailForm").find("input[name=detailId]").val();
	var procType = $("#CatalogDetailForm").find("ul:last li:last").find("select[name=procType]").val();
	var procTotal = $("#CatalogDetailForm").find("ul:last li:last").find("input[name=procTotal]").val();
	if(	($("#goodsPrice").val()==null||""==$("#goodsPrice").val())&&($("#yearTotal").val()==null||""==$("#yearTotal").val())&&
		(procType==null||""==procType||"-1"==procType)&&(procTotal==null||""==procTotal)	){
		alert("您至少选填一项！");return;
	}else{
		var catalogTypeHtml = $("#CatalogDetailForm").find("ul:last li");
		var catalogTypeJson = "";
		$.each(catalogTypeHtml,function(index,obj){
			var cellprocType = $(obj).find('select[name=procType]').val();
			var cellprocTotal = $(obj).find('input[name=procTotal]').val();
			var procTypeId = ( $(obj).find('input[name=procTypeId]').val()==null )?"":$(obj).find('input[name=procTypeId]').val();
			
			//拼装
			if((cellprocType!=null&&cellprocType!="")||(cellprocTotal!=null&&cellprocTotal!="")){
				if(index==0){
					catalogTypeJson += cellprocType + ":" + cellprocTotal + ":" + procTypeId;
				}else{
					catalogTypeJson += "," + cellprocType + ":" + cellprocTotal + ":" + procTypeId;
				}
			}
		});
		
		$.getJSON($("#initPath").val()+"/PurCatalogController.do?method=saveCatalogDetailOrProcType",
				{
					"detailId":detailId,
					"categoryIds":ids,
					"catalogId":$("#objId").val(),
					"goodsPrice":$("#goodsPrice").val(),
					"yearTotal":$("#yearTotal").val(),
					"catalogTypeJson":catalogTypeJson
				},
				function(json){
					if(json.success){
						alert(json.result);
						
						//保存完就灰掉
						if(null!=ids&&""!=ids){
							$.each(ids.split(","),function(index,obj){
								catalogDetailForm.tree.disableCheckbox(obj,1);
							})
						}
						
						//点击所选的的第一个品目
						catalogDetailForm.nodeClick(ids.split(",")[0]);
					}
		});
	}
}

//删除采购方式明细
catalogDetailForm.delTypeDetail = function(obj){
	var objId = $(obj).parent().find('input[name=procTypeId]').val();
	if(null==objId||objId==""){$(obj).parent().remove(); return}
	$.getJSON($("#initPath").val()+"/PurCatalogProcTypeController.do?method=deletePurCatalogProcType",{"objId":objId},function(json){
		if(json.success){
			$(obj).parent().remove();
		}else{
			alert("删除失败！");
		}
	})
}

$(document).ready(function(){
	//初始化树
	catalogDetailForm.initGoodsClassTree();
	
	//保存
	$("#saveCatalogDetailBtn").click(function(){
		var ids=(catalogDetailForm.tree.getAllChecked());
		catalogDetailForm.saveCatalogDetail(ids);
	});
	
	//更新
	$("#updateCatalogDetailBtn").click(function(){
		var id = catalogDetailForm.tree.getSelectedItemId();
		catalogDetailForm.saveCatalogDetail(id);
	})
	
	//新建采购方式目录
	$("#newTypeBtn").click(function(){
		var currentIdStr =  $("#CatalogDetailForm").find("ul:last li:last").attr("id");
		var currentId = (null==currentIdStr?0:parseInt(currentIdStr));
		
		var newLi = $(templateLi).clone();
		$(newLi).attr("id",currentId+1);
		
		if(currentId<5&&currentId>0){
			$("#CatalogDetailForm").find("ul:last li:last").after(newLi);
		}else if(currentId==0){
			$("#CatalogDetailForm").find("ul:last").append(newLi);
		}else{
			alert('不得大于5个');
		}
	})
});