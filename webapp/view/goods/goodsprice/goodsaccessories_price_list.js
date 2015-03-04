
var goodsAccessoriesPriceList={};

//保存
goodsAccessoriesPriceList.save=function(){
	var accPriceJson = [];
	$('#GoodsAccessoriesPriceList tbody').find('tr').each(function(i,n){
		//拼装json数组对象
		accPriceJson[i] = {};
		if(!$(n).find('input[name=notAddChkBox]').attr('checked')) {
			if($(n).attr('priceId')) {//有价格时修改
				accPriceJson[i].objId = $(n).attr('priceId');
			}
			//零配件
			accPriceJson[i].goodsAccessories = {};
			accPriceJson[i].goodsAccessories.objId = $(n).attr('objid');
			//行情供应商
			accPriceJson[i].supplier = {};
			accPriceJson[i].supplier.objId = $('#goodsPriceSupplierId').val();
			//行情
			accPriceJson[i].goodsPrice = {};
			accPriceJson[i].goodsPrice.objId = $('#goodsPriceId').val();
			accPriceJson[i].accessoryPrice=$(n).find('input[name=accPrice]').val();
		}
	})
	//保存
	$.getJSON($('#initPath').val()+'/GoodsAccessoriesPriceController.do?method=saveAccPrice',{"accPriceStr":JSON.stringify(accPriceJson)}, function(json){
		if(json.failure){if(json.result)alert(json.result);return;}
		$('.epsDialogClose').trigger('click');
	})
}

$(document).ready(function(){
	$('#goodsAccessoriesPriceSearchZone').validate();
	
	//加载零配件列表
	goodsAccessoriesPriceList.oTable = $('#GoodsAccessoriesPriceList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'product_name, accessory_qty,is_off',
		'hiddenColumns' : 'accessories_price,goods_accessories_price_id',
		'alias':'',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			goodsAccessoriesPriceList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			var html = '<td align="center">';
			if(aData.accessories_price) {
				html += '<input type="text" class="money" name="accPrice" value="'+aData.accessories_price+'"/>';
			}else {
				html += '<input type="text" class="money" name="accPrice" value="0"/>';
			}
			html += '&nbsp;<a href="javascript:void(0);" name="delPrice">删除</a>&nbsp;';
			html += '<input type="checkbox" name="notAddChkBox" >不对此配件报价[<font color="red">如果不能提供此配件请勾选</font>]';
			html += '</td>';
			$(nRow).append(html);
			$(nRow).find('a[name=delPrice]').click(function(){
			    if(window.confirm('确定要删除此零配件价格吗?')) {
			    	if(aData.goods_accessories_price_id) {
				    	$.getJSON($('#initPath').val()+'/GoodsAccessoriesPriceController.do?method=remove',{"objId":aData.goods_accessories_price_id},function(json){
							if(json.failure){if(json.result)alert(json.result);return;}
							goodsAccessoriesPriceList.oTable.fnDraw();
						});
			    	}
			    }
			})
			//控制是否报价
			$(nRow).find('input[name=notAddChkBox]').click(function(){
				if($(this).attr('checked')) {
					$(nRow).find('input[name=accPrice]').attr('disabled','disabled');
				}else {
					$(nRow).find('input[name=accPrice]').removeAttr('disabled');
				}
			})
			
			$(nRow).attr('priceId',aData.goods_accessories_price_id);
			if(aData.goods_accessories_price_id) {//有报价且报价为0
				if(aData.accessories_price == 0){
					$(nRow).find('td[name=is_off]').html('配件赠送');
				}else{
					$(nRow).find('td[name=is_off]').html('已报价');
				}
					
			}else {
				$(nRow).find('td[name=is_off]').html('没有报价');
			}
			return nRow;
		},
		"params":{"goodsPriceId":$('#goodsPriceId').val(),"goodsId":$('#goodsId').val()},
		"sAjaxSource" : $('#initPath').val()+ "/GoodsAccessoriesPriceController.do?method=listAccPrice"
	});
	
	//保存
	$("#saveAccPrice").click(function(){
		if(!$('#goodsAccessoriesPriceSearchZone').valid()){alert('请正确填写表单!');return;}
		if(window.confirm('确定保存吗?')) {
			goodsAccessoriesPriceList.save();
		}
	})
	
	//关闭
	$("#closeAccPrice").click(function(){
		$('.epsDialogClose').trigger('click');
	})
});

