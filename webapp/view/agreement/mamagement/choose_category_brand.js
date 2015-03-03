/*
 * 协议管理，选择品牌和分类页面
 * author: yucy
 * mail: yuchengyang@yeah.net
 */
var chooseCategoryAndBrand={};
chooseCategoryAndBrand.oTable;	

//新增
chooseCategoryAndBrand.add = function(){
	
	var tBody = $("#chooseCategoryAndBrandTBody");
	if(tBody.find("tr[objid=newClassAndBrandTr]").html()){alert("请先保存新增的数据！"); return;}
	var newRow = $("#tempTrDiv").find("tr").clone();
	
	//先清空
	if(tBody.find("td[class=dataTables_empty]").html()!=null) tBody.html("");
	var trCount = tBody.find("tr").length+1;
	trCount%2==1?newRow.addClass("even"):newRow.addClass("old");//加奇偶样式
	tBody.prepend(newRow);
	newRow.find("input[id=newBrandName]").attr("name","newBrandName"+trCount);//加name属性
	newRow.find("input[id=brand.objId]").attr("name","newBrandId"+trCount);//加name属性
	newRow.find("select[id=newClassName]").attr("name","newClassName"+trCount);//加name属性

	//行点击事件
	newRow.click(function(){
		if($(this).hasClass("row_selected")){
			$(this).removeClass("row_selected")
			$(this).find("input[type=checkbox]").attr("checked","");
		}else{
			$(this).addClass("row_selected")
			$(this).find("input[type=checkbox]").attr("checked","checked");
		}
	});
	
	//弹出选择品牌
	chooseCategoryAndBrand.chooseBrand(newRow ,trCount);
	
	//操作事件(保存)
	newRow.find("a[name=newRowSave]").click(function(){
		var goodsClassId = newRow.find("select[id=newClassName]").val();
		var brandId = newRow.find("input[id=brand.objId]").val();
		var discountRatio = newRow.find("input[class=discountRatio]").val();
		if(!goodsClassId||!brandId||!discountRatio){
			alert("请完善新增的数据！");
		}else{
			//保存行
			chooseCategoryAndBrand.saveRow(null,goodsClassId,brandId,discountRatio,true);
		}
	});
	
}

//弹出选择品牌
chooseCategoryAndBrand.chooseBrand = function( newRow ,trCount ){
	//弹出选择品牌
	newRow.find("input[id=newBrandName]").click(function(){
		var beforeBrandId = newRow.find("input[id=brand.objId]").val();
		$.epsDialog({
			id:"chooseBrandForAgreement",
			title:"选择品牌",
			url:$("#initPath").val()+"/view/agreement/mamagement/choose_brand_for_agreement.jsp?orgId="+$("input[id=org.objId]").val()+"&returnName=newBrandName"+trCount+"&returnId=newBrandId"+trCount
			,onClose:function(){
				//有改变品牌则去取分类
				var currentBrandId = newRow.find("input[id=brand.objId]").val();
				if( beforeBrandId!= currentBrandId ){
					newRow.find("select[id=newClassName]").empty();
					chooseCategoryAndBrand.addGoodsClassSelect( currentBrandId, newRow.find("select[id=newClassName]") );
				}
			}
		})
	})
}


//填充分类选择框
chooseCategoryAndBrand.addGoodsClassSelect = function( currentBrandId , selectObject ){
	$.getJSON( $("#initPath").val()+"/GoodsClassBrandController.do?method=getClassListByBrand",{"brandId":currentBrandId},function(json){
		if(json.success){
			selectObject.find('option[value=chooseBrandFirst]').remove();
			$.each(json.result,function(index,obj){
				if( !selectObject.find('option[value='+obj.objId+']').html() ){
					selectObject.append('<option value="'+obj.objId+'">'+obj.goodsClassName+'</option>');
				}
			})
		}
	})
}

//折扣率改变事件
chooseCategoryAndBrand.discountRatioChange = function(e){
	if(isNaN($(e).val()+'')){
    	alert("请输入数字");
    	$(e).val("");//清空
	}else if($(e).val()>100||$(e).val()<0){
		alert("折扣为正数且不得大于100");
    	$(e).val("");//清空
	}
}

//保存每行
chooseCategoryAndBrand.saveRow = function(objId,goodsClassId,brandId,discountRatio,isAlert){
	$.getJSON(
			$('#initPath').val()+'/AgreementGoodsclassController.do?method=saveOrUpDateRow',
			{
				"objId":objId,
				"goodsClassId":goodsClassId,
				"brandId":brandId,
				"discountRatio":discountRatio,
				"agreementId":$("#objId").val(),
				"agreementType":"00"
			},
			function(json){
				if(json.success){
					if(isAlert){
						alert(json.result);
						chooseCategoryAndBrand.reload();
					}
				}
			}
	);
}

//删除
chooseCategoryAndBrand.del=function(){
	//获取选中的复选框的ID
	var selectIds = $('#chooseCategoryAndBrand').dtSelects()
	if(selectIds.length<1){
		alert("请至少选择一行删除");
	}else {
		if(confirm("确定删除?")){
			//删除协议分类并删除该分类下协议商品
			$.getJSON($('#initPath').val()+'/AgreementGoodsclassController.do?method=removeAgreementGoodsclassAndGoods',{"agreementGoodsclassIds":selectIds.toString()},function(json){
				alert(json.result);
				chooseCategoryAndBrand.reload();
			});
		}
	}
}

//刷新
chooseCategoryAndBrand.reload=function(){
	chooseCategoryAndBrand.oTable.fnDraw();
}

//下一步
chooseCategoryAndBrand.toChooseAgreementGoods = function(){
	//若有没有保存的数据 保存
	var newRow= $("#chooseCategoryAndBrandTBody").find("tr[objid=newClassAndBrandTr]");
	if( newRow.html() ){
		var goodsClassId = newRow.find("select[id=newClassName]").val();
		var brandId = newRow.find("input[id=brand.objId]").val();
		var discountRatio = newRow.find("input[class=discountRatio]").val();
		if(!goodsClassId||!brandId||!discountRatio){
			alert("请完善新增的数据！");return;
		}else{
			//保存行
			chooseCategoryAndBrand.saveRow(null,goodsClassId,brandId,discountRatio,true);
		}
	}
	$('#conBody').loadPage($('#initPath').val()+"/AgreementController.do?method=toChooseClassBrand&toView=toChooseGoods&objId="+$("#objId").val());
}

//上一步
chooseCategoryAndBrand.toAgreementList = function(){
	$('#conBody').loadPage($('#initPath').val()+"/view/agreement/mamagement/purchase_agreement_list.jsp");
}

$(document).ready(function(){
	var trCount = 0;
	
	//加载列表
	chooseCategoryAndBrand.oTable=$('#chooseCategoryAndBrand').dataTable( {
		'params':{"agreement.objId":(""!=$("#objId").val()&&null!=$("#objId").val())==true?$("#objId").val():'-1'},
		'singleSelect':false,	
		'checkbox':true,		
		'queryColumns':'brand.brandName,goodsClass.goodsClassName,discountRatio',
		'hiddenColumns':'objId,goodsClass.objId,brand.objId',
		'fnInitComplete':function(oSettings) {},
		'fnDrawCallback':function(oSettings) {},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
			trCount++;
			//选第一列(品牌)
			$(nRow).find('td:eq(1)').empty().append( $("#tempTrDiv").find("input[id=brand.objId]").clone().attr("name","newBrandId"+trCount).val(aData['brand.objId']) ).append( $("#tempTrDiv").find("input[id=newBrandName]").clone().attr("name","newBrandName"+trCount).val(aData['brand.brandName'])  );
			chooseCategoryAndBrand.chooseBrand($(nRow),trCount);
			
			//选第二列(类别)
			$(nRow).find('td:eq(2)').empty().append( $("#tempTrDiv").find("select[id=newClassName]").clone().empty().append('<option value="'+aData['goodsClass.objId']+'">'+aData['goodsClass.goodsClassName']+'</option>') ).find('select[id=newClassName]').focus(function(){chooseCategoryAndBrand.addGoodsClassSelect( aData['brand.objId'],$(this) );  });
			
			//选第三列(折扣率)
			$(nRow).find('td:eq(3)').empty().append( $("#tempTrDiv").find("input.discountRatio").clone().val( aData['discountRatio'] ) );
			
			//加操作
			$(nRow).append( $("#tempTrDiv").find("td[class=operation]").clone().click(function(){
					var goodsClassId = $(nRow).find("select[id=newClassName]").val();
					var brandId = $(nRow).find("input[id=brand.objId]").val();
					var discountRatio = $(nRow).find("input[class=discountRatio]").val();
					if(!goodsClassId||!brandId||!discountRatio){
						alert("请完善新增的数据！");
					}else{
						//保存行
						chooseCategoryAndBrand.saveRow($(nRow).attr('objid') ,goodsClassId,brandId,discountRatio,true);
					}
			}) );
			return nRow;
		},
		"sPaginationType":"full_numbers",
		"bProcessing": true,
		"bServerSide": true,
		"sAjaxSource": $('#initPath').val()+"/AgreementGoodsclassController.do?method=list"
	});
	
});
	
