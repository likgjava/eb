/**
 * 
 * create by yucy
 * 
 * 
 */
var catalogCategoryForm={};

var templateLi = $("#templateUl").find("li");

//保存事件
catalogCategoryForm.saveCatalogDetail = function(ids){
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
					"catalogId":$("input[name=catalogId]").val(),
					"goodsPrice":$("#goodsPrice").val(),
					"yearTotal":$("#yearTotal").val(),
					"catalogTypeJson":catalogTypeJson
				},
				function(json){
					if(json.success){
						alert("保存成功！");
						$(document.getElementById($("#newCatalogByCategory").val())).find('.epsDialogClose').trigger('click');
					}
		});
	}
}

//初始化新增的表单
catalogCategoryForm.initsaveForm = function(){
	$("#CatalogDetailForm").find("input[name=detailId]").val("");
	$("#CatalogDetailForm").find("input[name=goodsPrice]").val("");
	$("#CatalogDetailForm").find("input[name=yearTotal]").val("");
	
	$("#CatalogDetailForm").find("ul:last").empty().append(
			$(templateLi).clone()
	);
	$("#saveCatalogDetailBtn").show();
	$("#updateCatalogDetailBtn").hide();
}

$(document).ready(function(){
	
	//搜
	$("#catalogSearch").click(function(){
		catalogCategoryForm.oTable.fnDraw();
	})
	
	//加载列表
	catalogCategoryForm.oTable=$('#catalogList').dataTable( {   
		'params':{"useStatus":"00","auditStatus_op":"in","auditStatus":"00,03"},
		'searchZone':'catalogSearchForm',
		'singleSelect':true,	
		'checkbox':false,		
		'queryColumns':'title,areaCode,areaName,publishStatus,year',
		'alias':'title,areaCode,areaName,publishStatusCN,year',
		'hiddenColumns':'useStatus,auditStatus',
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
			//添加操作
			$(nRow).append('<td class="operation"></td>');
			$(nRow).find('td:last').append('<a href="javascript:void(0);" name="modify"><span>选择</span></a>');
			$(nRow).find('a').click(function(){
				$("input[name=catalogId]").val(aData.objId);
				$.getJSON($("#initPath").val()+"/PurCatalogController.do?method=getDetailAndTypeDetailInfo",
						{"categoryId":$("input[name=categoryId]").val(),"catalogId":aData.objId},function(json){
							if(json.success){
								var  catalogdetail = json.detailInfo[0];
								var  catalogType = json.typeInfo;
								//没有被添加过
								if((json.detailInfo==null||json.detailInfo.length<=0)&&(json.typeInfo==null||json.typeInfo.length<=0)){
									//初始化新增的表单
									catalogCategoryForm.initsaveForm();
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
										$("#CatalogDetailForm").find("input[name=procTypeId]").val(catalogType.objId);
										$("#CatalogDetailForm").find("input[name=procType]").val(catalogType.procType);
										$("#CatalogDetailForm").find("input[name=procTotal]").val(catalogType.procTotal);
									}
								}
							}
				});
			});
			return nRow;
		},
		"sAjaxSource": $('#initPath').val()+"/PurCatalogController.do?method=list"
	});
	
	//初始化发布日期
	$("#relDate").epsDatepicker();
	
	//初始化年度
	var year = (new Date()).getFullYear(); 
	for(var i = -2; i <= 5; i++){
		$("#catalogSearchForm").find("select[name=year]").append("<option value='" + (year + i) + "'>" + (year + i) + "</option>");
	}
	
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
	
	//新增目录
	$("#addCatalog").click(function(){
		
		//加载div
		$("#newCatalogDiv").loadPage($("#initPath").val()+"/view/bizplatform/base/purcatalog/catalog_form_dialog.jsp",function(){
			$("#newCatalogDiv").show("slow");
		});
	});
	
	//保存
	$("#saveCatalogDetailBtn").click(function(){
		catalogCategoryForm.saveCatalogDetail($("input[name=categoryId]").val());
	});
	
	//更新
	$("#updateCatalogDetailBtn").click(function(){
		catalogCategoryForm.saveCatalogDetail($("input[name=categoryId]").val());
	})
	
	//返回
	$("#catalogReturn").click(function(){				
		$('#conBody').loadPage($('#initPath').val()+"/PurCatalogController.do");
	})
});