/*
 * 平台开发demo
 * author: yucy
 * mail: yuchengyang@yeah.net
 */
var authorizedCategoryOrGoods={};

//byclassTable
authorizedCategoryOrGoods.oTableClass;
//byGoods
authorizedCategoryOrGoods.oTableGoods;

//添加供应商（授权/未授权分类）
authorizedCategoryOrGoods.authorizedCategoryaddVender=function(){
	var goodsClassId = $('#authorizedCategoryList').dtSelects();
	
	$('#conBody').loadPage($('#initPath').val()+"/AgreementController.do?method=toChooseClassBrand&toView=toVenderFormView&goodsClassId="+goodsClassId+"&objId="+$("#objId").val()+"&resource="+$("#resource").val());

}

//添加供货商(授权/未授权 单品)
authorizedCategoryOrGoods.authorizedGoodsaddVender=function(){
	var goodsId = $('#authorizedGoodsList').dtSelects();
	
	$('#conBody').loadPage($('#initPath').val()+"/AgreementController.do?method=toChooseClassBrand&toView=toVenderFormView&goodsId="+goodsId+"&objId="+$("#objId").val()+"&resource="+$("#resource").val());

}

//删除授权分类
authorizedCategoryOrGoods.delAuthorizedClass = function(agreementSecondId,agreementGoodsClassId){
	
	$.getJSON($('#initPath').val()+'/AgreementGoodsclassController.do?method=delAuthorizedClass',
			{
				"agreementSecondId":agreementSecondId,
				"agreementGoodsClassId":agreementGoodsClassId
			},
			function(json){
				if(json.success){
					alert(json.result);
					authorizedCategoryOrGoods.getByClass($("#agreementId").val());
				}
			}
	);
}

//删除授权商品
authorizedCategoryOrGoods.delAuthorizedGoods = function(agreementSecondId,agreementGoodsId){
	
	$.getJSON($('#initPath').val()+'/AgreementGoodsController.do?method=delAuthorizedGoods',
			{
				"agreementSecondId":agreementSecondId,
				"agreementGoodsId":agreementGoodsId
			},
			function(json){
				if(json.success){
					alert(json.result);
					authorizedCategoryOrGoods.getByGoods($("#agreementId").val());
				}
			}
	);
	
}

//返回
authorizedCategoryOrGoods.authorizedCategoryOrGoodsReturn = function(){
	$('#conBody').loadPage($('#initPath').val()+"/view/mamagement/vender_qualification_list.jsp");
}

//跳转到授权供应商详细页面
authorizedCategoryOrGoods.toAuthorizedSupplyer = function(agreementSecondId){
	//alert(agreementSecondId);
	$('#conBody').loadPage($('#initPath').val()+"/AgreementController.do?method=toChooseClassBrand&venderId="+agreementSecondId+"&toView=toAuthorizedVenderView&objId="+$("#objId").val());
	//$('#conBody').loadPage($('#initPath').val()+"/view/mamagement/authorized_vender.jsp?venderId="+agreementSecondId+"&agreementId="+$("#agreementId").val());
}

//获得classTAble
authorizedCategoryOrGoods.getByClass = function(agreementId){
	if(null==authorizedCategoryOrGoods.oTableClass){
		//为空加载列表分类
		authorizedCategoryOrGoods.oTableClass=$('#authorizedCategoryList').dataTable( {
			'params':
				{
					'agreement.objId':agreementId
				},
			'singleSelect':false,	
			'checkbox':true,		
			'queryColumns':'goodsClass.goodsClassName,brand.brandName',
			'hiddenColumns':'objId,brand.objId,goodsClass.objId',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {
				//定制表格
				var a = oSettings;
				
				for(var i= 0;i<a.appendData.length;i++){
					for(var j= 0;j<a.appendData[i].length;j++){
						if(j==0){
							
						}else{
							
							$('#authorizedCategoryList tbody').find('tr[objid='+a.appendData[i][0][0]+']').attr("rowspanNum",a.appendData[i][j].length==0?'1':a.appendData[i][j].length);
							
							$('#authorizedCategoryList tbody').find('tr[objid='+a.appendData[i][0][0]+']')
							.find('td:first,td[name=goodsClass.goodsClassName],td[name=brand.brandName]').attr("rowspan",a.appendData[i][j].length==0?'1':a.appendData[i][j].length);
							
							$('#authorizedCategoryList tbody').find('tr[objid='+a.appendData[i][0][0]+']')
							.find('td[name=supplier],td[name=area],td[name=period]').remove();
							
							//判断是否有二级协议
							if(0<a.appendData[i][j].length){
								for(var k = 0;k<a.appendData[i][j].length;k++){
									//alert(JSON.stringify(a.appendData[i][j][k].objId));
									if(k>0){
										var colsHtml = '';
										colsHtml += '<tr class="'+$('#authorizedCategoryList tbody').find('tr[objid='+a.appendData[i][0][0]+']').attr("class")+'"><td><a  href="javascript:void(0);" onclick="authorizedCategoryOrGoods.toAuthorizedSupplyer(\''+a.appendData[i][j][k].objId+'\');return false;">'+a.appendData[i][j][k].supplyer.orgName+'</a></td><td>'
										+a.appendData[i][j][k].area.areaName+'</td><td>'
										+a.appendData[i][j][k].beginDate.substr(0,10)+'至'+a.appendData[i][j][k].endDate.substr(0,10)
										+'</td><td><a href="javascript:void(0);" onclick="authorizedCategoryOrGoods.delAuthorizedClass(\''+a.appendData[i][j][k].objId+'\',\''+a.appendData[i][0][0]+'\');return false;">删除</a></td></tr>';
										$('#authorizedCategoryList tbody').find('tr[objid='+a.appendData[i][0][0]+']')
										.after(colsHtml);
									}else{
										var colsHtml = '';
										
										colsHtml += '<td><a href="javascript:void(0);" onclick="authorizedCategoryOrGoods.toAuthorizedSupplyer(\''+a.appendData[i][j][k].objId+'\');return false;" >'+a.appendData[i][j][k].supplyer.orgName+'</a></td><td>'
										+a.appendData[i][j][k].area.areaName+'</td><td>'
										+a.appendData[i][j][k].beginDate.substr(0,10)+'至'+a.appendData[i][j][k].endDate.substr(0,10)
										+'</td><td><a href="javascript:void(0);" onclick="authorizedCategoryOrGoods.delAuthorizedClass(\''+a.appendData[i][j][k].objId+'\',\''+a.appendData[i][0][0]+'\');return false;">删除</a></td>'
										$('#authorizedCategoryList tbody').find('tr[objid='+a.appendData[i][0][0]+']')
										.append(colsHtml);
									}
								}
							}else{
								$('#authorizedCategoryList tbody').find('tr[objid='+a.appendData[i][0][0]+']')
								.append('<td class="center" colspan="4">该分类暂未分配供货商</td>');
							}
						}
					}
				}
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				//追加列
				$(nRow).append('<td name="supplier"></td><td name="area"></td><td name="period"></td>');
				return nRow;
			},
			"sPaginationType": "full_numbers",
			"bProcessing": true,
			"bServerSide": true,
			"bPaginate": false,
			"sAjaxSource": $('#initPath').val()+"/AgreementGoodsclassController.do?method=listClass"
		});
	}else{
		//刷新
		authorizedCategoryOrGoods.oTableClass.fnDraw();
	}
}

//获得GoodsTAble
authorizedCategoryOrGoods.getByGoods = function(agreementId){
	
	if(null==authorizedCategoryOrGoods.oTableGoods){
		//为空加载列表分类
		authorizedCategoryOrGoods.oTableGoods=$('#authorizedGoodsList').dataTable( {
			'params':
				{
					'agreement.objId':agreementId
				},
			'singleSelect':false,	
			'checkbox':true,		
			'queryColumns':'goods.productName,brand.brandName,goodsClass.goodsClassName,goods.productCode',
			'hiddenColumns':'objId,goods.objId',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {
				
				//定制表格
				var a = oSettings;
				
				for(var i= 0;i<a.appendData.length;i++){
					for(var j= 0;j<a.appendData[i].length;j++){
						if(j==0){
							
						}else{
							
							//添加rowspanNum
							$('#authorizedGoodsList tbody').find('tr[objid='+a.appendData[i][0][0]+']').attr("rowspanNum",a.appendData[i][j].length==0?'1':a.appendData[i][j].length);
							
							//改变跨行
							$('#authorizedGoodsList tbody').find('tr[objid='+a.appendData[i][0][0]+']')
							.find('td:first,td[name=goods.productName],td[name=brand.brandName],td[name=goodsClass.goodsClassName],td[name=goods.productCode]').attr("rowspan",a.appendData[i][j].length==0?'1':a.appendData[i][j].length);
							
							//移除后三列
							$('#authorizedGoodsList tbody').find('tr[objid='+a.appendData[i][0][0]+']')
							.find('td[name=supplier],td[name=area],td[name=period]').remove();
							
							//判断是否有二级协议
							if(0<a.appendData[i][j].length){
								
								for(var k = 0;k<a.appendData[i][j].length;k++){
									if(k>0){
										var colsHtml = '';
										colsHtml += '<tr class="'+$('#authorizedGoodsList tbody').find('tr[objid='+a.appendData[i][0][0]+']').attr("class")+'"><td><a href="javascript:void(0);" onclick="authorizedCategoryOrGoods.toAuthorizedSupplyer(\''+a.appendData[i][j][k].objId+'\');return false;" >'+a.appendData[i][j][k].supplyer.orgName+'</a></td><td>'
										+a.appendData[i][j][k].area.areaName+'</td><td>'
										+a.appendData[i][j][k].beginDate.substr(0,10)+'至'+a.appendData[i][j][k].endDate.substr(0,10)+'</td><td><a href="javascript:void(0);" onclick="authorizedCategoryOrGoods.delAuthorizedGoods(\''+a.appendData[i][j][k].objId+'\',\''+a.appendData[i][0][0]+'\');return false;">删除</a></td></tr>';
										
										$('#authorizedGoodsList tbody').find('tr[objid='+a.appendData[i][0][0]+']')
										.after(colsHtml);
									}else{
										var colsHtml = '';
										
										colsHtml += '<td><a href="javascript:void(0);" onclick="authorizedCategoryOrGoods.toAuthorizedSupplyer(\''+a.appendData[i][j][k].objId+'\');return false;">'+a.appendData[i][j][k].supplyer.orgName+'</a></td><td>'
										+a.appendData[i][j][k].area.areaName+'</td><td>'
										+a.appendData[i][j][k].beginDate.substr(0,10)+'至'+a.appendData[i][j][k].endDate.substr(0,10)+'</td><td><a href="javascript:void(0);" onclick="authorizedCategoryOrGoods.delAuthorizedGoods(\''+a.appendData[i][j][k].objId+'\',\''+a.appendData[i][0][0]+'\');return false;">删除</a></td>'
										
										$('#authorizedGoodsList tbody').find('tr[objid='+a.appendData[i][0][0]+']')
										.append(colsHtml);
									}
								}
								
							}else{
								
								$('#authorizedGoodsList tbody').find('tr[objid='+a.appendData[i][0][0]+']')
								.append('<td class="center" colspan="4">该单品暂未分配供货商</td>');
								
							}
							
						}
					}
				}
				
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				//追加列
				$(nRow).append('<td name="supplier"></td><td name="area"></td><td name="period"></td>');
				return nRow;
			},
			"bServerSide": true,
			"bPaginate": false,
			"sAjaxSource": $('#initPath').val()+"/AgreementGoodsController.do?method=listGoods"
		});
		
	}else{
		//刷新
		authorizedCategoryOrGoods.oTableGoods.fnDraw();
	}
	
}



$(document).ready(function(){
	//加载tabs
	$('#authorizedVenderTabs').tabs();
	
	//tab切换 事件
	$('#authorizedVenderTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){
			authorizedCategoryOrGoods.getByClass($("#objId").val());
		}else{
			authorizedCategoryOrGoods.getByGoods($("#objId").val());
		}
	})
	
	//默认加载分类 列表
	authorizedCategoryOrGoods.getByClass($("#objId").val());
	
	//根据请求来源显示不通的tab页
	if($("#resource").val()=="category"){
		$("#authorizedCategoryClick").click();
	}else if($("#resource").val()=="goods"){
		$("#authorizedGoodsClick").click();
	}
	
	//添加供货商
	$("#authorizedGoodsaddVender").click(function(){
		authorizedCategoryOrGoods.authorizedCategoryaddVender();
	})
	
});
