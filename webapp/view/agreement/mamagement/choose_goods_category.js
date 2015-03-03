/*
 * 协议管理，挑选协议商品by分类页面
 * author: yucy
 * mail: yuchengyang@yeah.net
 */

//定义文件全局变量 处理方法名重复问题
var chooseGoodsByCategory={};
chooseGoodsByCategory.oTable = null;	

//修改每行
chooseGoodsByCategory.saveRow = function(goods){
	$.getJSON(
			$('#initPath').val()+'/AgreementGoodsController.do?method=saveAgreementGoods',
			goods,
			function(json){
				if(json.success){
				}
			}
	);
}

//删除协议商品
chooseGoodsByCategory.del = function(goodIds){
	if(confirm("确定删除?")){
		var ids = goodIds.split(",");
		$.getJSON(
				$('#initPath').val()+'/AgreementGoodsController.do?method=remove',{objId:ids},
				function(json){
					if(json.success){
						alert("删除成功!");
						chooseGoodsByCategory.oTable.fnDraw();
					}
				}
		);
	}
}

//根据分类和品牌获得的商品TableList
chooseGoodsByCategory.getGoodsTableByGategory = function(agreementGoodsclassId){
	//不存在列表则创建
	if(null==chooseGoodsByCategory.oTable||""==chooseGoodsByCategory.oTable){
		//创建
		chooseGoodsByCategory.oTable = $('#chooseGoodsByCategory').dataTable( {
			'params':
			{
				"agreementGoodsclass.objId":agreementGoodsclassId
			},
			'singleSelect':false,	
			'checkbox':true,		
			'queryColumns':'goods.productName,goods.productCode,marketPrice,agreementPrice,discountRatio',
			//'queryColumns':'goods.productName',
			'hiddenColumns':'objId',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
			},
			'fnRowCallback': function( nRow,aData,iDisplayIndex) {
				//市场价编辑
				var column3=$(nRow).find('td:eq(3)');
				column3.html('<span name="marketPrice">'+aData['marketPrice']+'</span><input style="width:50px"  class="hidden" name="marketPrice" type="text" value="'+aData['marketPrice']+'"/>');//可以加ID或者onClick
				column3.find('input[name=marketPrice]').change(function(){
					if(isNaN($(this).val()+'')){
						alert("请输入数字");
						return;
					}else{
						$(nRow).find('input[name=agreementPrice]').val(getPrtcPrice($(this).val(),$(nRow).find('input[name=discountRatio]').val()));
					}
				});
				
				//协议价编辑
				var column4=$(nRow).find('td:eq(4)');
				column4.html('<span name="agreementPrice">'+aData['agreementPrice']+'</span><input style="width:50px"  class="hidden" name="agreementPrice" type="text" value="'+aData['agreementPrice']+'"/>');//可以加ID或者onClick
				column4.find('input[name=agreementPrice]').change(function(){
					var agreementPriceBefore = getPrtcPrice($(nRow).find('input[name=marketPrice]').val(),$(nRow).find('input[name=discountRatio]').val());   
					if(isNaN($(this).val()+'')||Number($(this).val())>agreementPriceBefore){
						alert("请输入数字且不得高于预期协议价!");
						$(this).val(agreementPriceBefore);
					}else{
						$(nRow).find('input[name=discountRatio]').val(getDscuRate($(nRow).find('input[name=marketPrice]').val(),$(this).val()));
					}
				});
				
				//折扣率编辑
				var column5=$(nRow).find('td:eq(5)');
				column5.html('<span name="discountRatio">'+aData['discountRatio']+'</span><input style="width:50px"  class="hidden" name="discountRatio" type="text" value="'+aData['discountRatio']+'"/>');//可以加ID或者onClick
				column5.find('input[name=discountRatio]').change(function(){
					if(isNaN($(this).val()+'')||Number($(this).val())>aData.discountRatio){
						alert("请输入数字且不得高于原始折扣!");
						$(this).val(aData.discountRatio);
					}else{
						$(nRow).find('input[name=agreementPrice]').val(getPrtcPrice($(nRow).find('input[name=marketPrice]').val(),$(this).val()));
					}
				});
				
				//行末加操作按钮
				$(nRow).append('<td class="operation"><a type="alink" name="rowSave" class="hidden" href="javascript:void(0);"><span>保存</span></a>'
						+'<a type="alink" name="rowModify" href="javascript:void(0);"><span>修改</span></a>'
						+'</td>');
				
				//点击修改
				$(nRow).find('a[name=rowModify]').click(function(){
					$(nRow).find('input[name=marketPrice],input[name=agreementPrice],input[name=discountRatio],a[name=rowSave]').show();
					$(nRow).find('span[name=marketPrice],span[name=agreementPrice],span[name=discountRatio],a[name=rowModify]').hide();
				});
				
				//点击保存
				$(nRow).find('a[name=rowSave]').click(function(){
					chooseGoodsByCategory.saveThisRow(nRow,aData);
				});
				
				//键盘事件
				$(nRow).find('input[name=marketPrice],input[name=agreementPrice],input[name=discountRatio]').keydown(function(event){
					switch(event.keyCode){	//键盘事件可能扩展多个 (13 enter键)
						case 13:{	
							chooseGoodsByCategory.saveThisRow(nRow,aData);
						};
					}
				});
				
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/AgreementGoodsController.do?method=list"
		});
		
	}else{
		//存在则改变参数刷新数据
		$(chooseGoodsByCategory.oTable.dataTableSettings).attr("params",{"agreementGoodsclass.objId":agreementGoodsclassId});
		chooseGoodsByCategory.oTable.fnDraw();
	}
}

//保存这一行
chooseGoodsByCategory.saveThisRow  = function(nRow,aData){
	var marketPrice = $(nRow).find('input[name=marketPrice]').val().replace(/,/g,'');
    var agreementPrice = $(nRow).find('input[name=agreementPrice]').val().replace(/,/g,'');
	var discountRatio = $(nRow).find('input[name=discountRatio]').val().replace(/,/g,'');
	
	$(nRow).find('span[name=marketPrice]').html(marketPrice);
	$(nRow).find('span[name=agreementPrice]').html(agreementPrice);
	$(nRow).find('span[name=discountRatio]').html(discountRatio);
	
	$(nRow).find('input[name=marketPrice],input[name=agreementPrice],input[name=discountRatio],a[name=rowSave]').hide();
	$(nRow).find('span[name=marketPrice],span[name=agreementPrice],span[name=discountRatio],a[name=rowModify]').show();
	
	var goods = {}
	goods.objId = aData.objId;
	goods.marketPrice =marketPrice;
	goods.agreementPrice = agreementPrice;
	goods.discountRatio = discountRatio;
	chooseGoodsByCategory.saveRow(goods);
}

$(document).ready(function(){
	//取得已选择的分类链接
	if(null!=$("#objId").val()&&""!=$("#objId").val()){
		$.getJSON(
				$("#initPath").val()+"/AgreementGoodsclassController.do?method=getObjectQuery",
				{
					"agreement.objId":$("#objId").val(),
					queryColumns:'objId,brand.brandName,goodsClass.goodsClassName'
				},
				function(json){
					if(json.success){
						var href = '';
						for(var i=0;i<json.result.length;i++){
							if(i<json.result.length-1){
								href += '<a href="javascript:void(0);" class="category" id="'+json.result[i].objId+'">'+json.result[i]['brand.brandName']+'-'+json.result[i]['goodsClass.goodsClassName']+'</a>,&nbsp;'
							}else{
								href += '<a href="javascript:void(0);" class="category" id="'+json.result[i].objId+'">'+json.result[i]['brand.brandName']+'-'+json.result[i]['goodsClass.goodsClassName']+'</a>'
							}
						}
						$("#CategorySelected").append(href);
						//默认加载第一个分类品牌下的商品
						chooseGoodsByCategory.getGoodsTableByGategory(json.result[0].objId);
						//点击分类品牌链接事件
						$("#CategorySelected").find('a:category').click(function(){
							chooseGoodsByCategory.getGoodsTableByGategory(this.id);
						});
					}
				}
		);
		
	}
	
	//批量删除
	$("#goodsByCategoryDel").click(function(){
		chooseGoodsByCategory.del($('#chooseGoodsByCategory').dtSelects());
	})
});
	
