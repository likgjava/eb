/*
 * 平台开发demo
 * author: yucy
 * mail: yuchengyang@yeah.net
 */
var authorizedCategoryOrGoods={};
authorizedCategoryOrGoods.CategoryGoodsTable;
authorizedCategoryOrGoods.GoodsTable;

//返回
authorizedCategoryOrGoods.authorizedCategoryOrGoodsReturn=function(){
	$('#conBody').loadPage($('#initPath').val()+"/view/mamagement/agreement_goods_list.jsp");
}

//关闭tips
authorizedCategoryOrGoods.hiddentips= function(){
	$("#tips").hide();
}

//修改每行
authorizedCategoryOrGoods.saveRow = function(goods){
	$.getJSON(
			$('#initPath').val()+'/AgreementGoodsController.do?method=saveAgreementGoodsByCopy',
			{agreeGoods:JSON.stringify(goods)},
			function(json){
				if(json.success){
					//chooseGoodsByCategory.oTable.fnDraw();
				}
			}
	);
}


//获得分类下的商品的列表
authorizedCategoryOrGoods.getCategoryGoodsTable = function(agreementGoodsclassId){
	if(null==authorizedCategoryOrGoods.CategoryGoodsTable){
		//创建
		authorizedCategoryOrGoods.CategoryGoodsTable = $('#agreementCategoryGoodsList').dataTable( {
			'params':
			{
				"agreementGoodsclass.objId":agreementGoodsclassId
			},
			'singleSelect':true,	
			'queryColumns':'goods.productName,goods.productCode,marketPrice,agreementPrice,discountRatio',
			//'queryColumns':'goods.productName',
			'hiddenColumns':'objId',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
			},
			'fnRowCallback': function( nRow,aData,iDisplayIndex) {
				//市场价编辑
				var column3=$(nRow).find('td:eq(2)');
				if(''==column3.html()){
					column3.html('<input class="marketPrice" type="text" value="'+aData['marketPrice']+'"/>');//可以加ID或者onClick
					column3.find('input:marketPrice').change(function(){
						//保存本行数据只更新discountRatio
						if(isNaN($(this).val()+'')){
				        	alert("请输入数字");
						}else{
							var goods = {}
							goods.objId = aData.objId;
							goods.marketPrice = $(this).val();
							authorizedCategoryOrGoods.saveRow(goods);
						}
					});
				}
				
				//协议价编辑
				var column4=$(nRow).find('td:eq(3)');
				column4.html('<input class="agreementPrice" type="text" value="'+aData['agreementPrice']+'"/>');//可以加ID或者onClick
				column4.find('input:agreementPrice').change(function(){
					//保存本行数据只更新discountRatio
					if(isNaN($(this).val()+'')){
			        	alert("请输入数字");
					}else{
						var goods = {}
						goods.objId = aData.objId;
						goods.agreementPrice = $(this).val();
						authorizedCategoryOrGoods.saveRow(goods);
					}
				});
				
				//折扣率编辑
				var column5=$(nRow).find('td:eq(4)');
				if(''==column5.html()){
					column5.html('<input class="discountRatio" type="text" value="'+aData['discountRatio']+'"/>');//可以加ID或者onClick
					column5.find('input:discountRatio').change(function(){
						//保存本行数据只更新discountRatio
						$(this).val();
						if(isNaN($(this).val()+'')){
				        	alert("请输入数字");
						}else{
							var goods = {}
							goods.objId = aData.objId;
							goods.discountRatio = $(this).val();
							authorizedCategoryOrGoods.saveRow(goods);
						}
					});
				}
				
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/AgreementGoodsController.do?method=list"
		});
		
	}else{
		//存在则替换参数刷新
		$(authorizedCategoryOrGoods.CategoryGoodsTable.dataTableSettings).attr("params",{"agreementGoodsclass.objId":agreementGoodsclassId});
		authorizedCategoryOrGoods.CategoryGoodsTable.fnDraw();
	}
}


//获得单品列表
authorizedCategoryOrGoods.getGoodsTable = function(agreementId){
	if(null==authorizedCategoryOrGoods.GoodsTable){
		//创建
		authorizedCategoryOrGoods.GoodsTable = $('#authorizedGoodsList').dataTable( {
			'params':
			{
				"agreement.objId":agreementId
			},
			'singleSelect':true,	
			'queryColumns':'goods.productName,goods.productCode,marketPrice,agreementPrice,discountRatio',
			//'queryColumns':'goods.productName',
			'hiddenColumns':'objId',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
			},
			'fnRowCallback': function( nRow,aData,iDisplayIndex) {
				
				//市场价编辑
				var column3=$(nRow).find('td:eq(2)');
				if(''==column3.html()){
					column3.html('<input class="marketPrice" type="text" value="'+aData['marketPrice']+'"/>');//可以加ID或者onClick
					column3.find('input:marketPrice').change(function(){
						//保存本行数据只更新discountRatio
						if(isNaN($(this).val()+'')){
				        	alert("请输入数字");
						}else{
							var goods = {}
							goods.objId = aData.objId;
							goods.marketPrice = $(this).val();
							authorizedCategoryOrGoods.saveRow(goods);
						}
					});
				}
				
				
				//协议价编辑
				var column4=$(nRow).find('td:eq(3)');
				column4.html('<input class="agreementPrice" type="text" value="'+aData['agreementPrice']+'"/>');//可以加ID或者onClick
				column4.find('input:agreementPrice').change(function(){
					//保存本行数据只更新agreementPrice
					if(isNaN($(this).val()+'')){
			        	alert("请输入数字");
					}else{
						var goods = {}
						goods.objId = aData.objId;
						goods.agreementPrice = $(this).val();
						authorizedCategoryOrGoods.saveRow(goods);
					}
				});
				
				//折扣率编辑
				var column5=$(nRow).find('td:eq(4)');
				
				if(''==column5.html()){
					column5.html('<input class="discountRatio" type="text" value="'+aData['discountRatio']+'"/>');//可以加ID或者onClick
					column5.find('input:discountRatio').change(function(){
						//保存本行数据只更新discountRatio
						$(this).val();
						if(isNaN($(this).val()+'')){
				        	alert("请输入数字");
						}else{
							var goods = {}
							goods.objId = aData.objId;
							goods.discountRatio = $(this).val();
							authorizedCategoryOrGoods.saveRow(goods);
						}
					});
				}
				
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/AgreementGoodsController.do?method=list"
		});
		
	}else{
		//存在则刷新
		$(authorizedCategoryOrGoods.GoodsTable.dataTableSettings).attr("params",{"agreement.objId":agreementId});
		authorizedCategoryOrGoods.GoodsTable.fnDraw();
	}
}

//加载分类商品
authorizedCategoryOrGoods.getClassTableAndTips = function(){
	//加载类的连接
	if(null!=$("#agreementId").val()&&""!=$("#agreementId").val()){
		$.getJSON(
				$("#initPath").val()+"/AgreementGoodsclassController.do?method=getGoodsClassWithTips",
				{
					"agreementId":$("#agreementId").val()
				},
				function(json){
					if(json.success){
						if(null!=json.agreementClassList&&''!=json.agreementClassList){
							var href = '';
							for(var i=0;i<json.agreementClassList.length;i++){
								var tips = json.agreementClassList[i].haveNew>0?'<span class="eleRequired">New!</span>':'';
								if(i<json.agreementClassList.length-1){
									href += '<a href="javascript:void(0);" classId="'+json.agreementClassList[i].goodsClass.objId+'" brandId="'+json.agreementClassList[i].brand.objId+'" haveNew="'+json.agreementClassList[i].haveNew+'" class="category" id="'+json.agreementClassList[i].objId+'">'+json.agreementClassList[i].brand.brandName+'-'+json.agreementClassList[i].goodsClass.goodsClassName+'</a>'+tips+',&nbsp;'
								}else{
									href += '<a href="javascript:void(0);" classId="'+json.agreementClassList[i].goodsClass.objId+'" brandId="'+json.agreementClassList[i].brand.objId+'" haveNew="'+json.agreementClassList[i].haveNew+'" class="category" id="'+json.agreementClassList[i].objId+'">'+json.agreementClassList[i].brand.brandName+'-'+json.agreementClassList[i].goodsClass.goodsClassName+'</a>'+tips;
								}
							}
							$("#CategorySelected").html(href);
							
							//点击类别加载相应的商品
							$("#CategorySelected").find('a').click(function(){
								if($(this).attr("haveNew")>0){
									$("#tips").show();
									//设置分类参数
									$("#AclassId").val(this.id);
									$("#brandId").val($(this).attr("brandId"));
									$("#classId").val($(this).attr("classId"));
								}else{
									$("#tips").hide();
									//清空分类参数
									$("#AclassId").val("-1");
									$("#brandId").val("-1");
									$("#classId").val("-1");
								}
								//加载类别下的商品
								authorizedCategoryOrGoods.getCategoryGoodsTable(this.id);
							})
							//预加载第一个分类下的商品
							authorizedCategoryOrGoods.getCategoryGoodsTable(json.agreementClassList[0].objId);
							//预加载第一个分类tips
							if(json.agreementClassList[0].haveNew>0){
								$("#tips").show();
								$("#AclassId").val(json.agreementClassList[0].objId);
								$("#brandId").val(json.agreementClassList[0].brand.objId);
								$("#classId").val(json.agreementClassList[0].goodsClass.objId);
							}else{
								$("#tips").hide();
								$("#AclassId").val("-1");
								$("#brandId").val("-1");
								$("#classId").val("-1");
							}
							
						}else{
							authorizedCategoryOrGoods.getCategoryGoodsTable('-1');
						}
					}
				}
		)
	}
	
	//加载一级协议信息
	if(""!=$("#agreementId").val()&&null!=$("#agreementId").val()){
		$.getJSON($('#initPath').val()+'/AgreementController.do?method=getObjectQuery',{objId:$("#agreementId").val(),queryColumns:"modifyTime"},function(json){
			//alert(json.result[0].modifyTime);
			var modifyTime = json.result[0].modifyTime.substr(0,10);
			
			//点击新增商品
			$("#AddNewGoods").click(function(){
				$("#agreementNewGoodsList").show();
				
				//弹出 商品选择页面
				/**
				 * 参数    
				 * DialogId:	弹出层的id 关闭时用
				 * isCheckBox:	是否复选（不传为单选）
				 * domain:		要查的实体名称
				 * colums:		列表显示属性:逗号分隔(需存在于指定的domain中)
				 * returnColums:需回填属性:逗号分隔(需存在于指定的domain中  且默认回填的form表单id等于指定的属性名称  单选)
				 * defineRetuColums:指定回填的表单Id(如指定    指定顺序同returnColums 要一一对应 如未指定 则需回填表单Id必须与returnColums一一对应)
				 * columCns:	列表显示的中文表头:中文逗号分隔(因为国际化并未规范 所以也要传)
				 */
				var params = 'defineRetuColums=goodsIds'
							+'&returnColums=objId'
							+'&DialogId=GoodsSelect'
							+'&isCheckBox=true'
							+'&colums=productName,productCode,goodsBrand.brandName,goodsClass.goodsClassName'
							+'&columCns=商品名称,商品编码,商品品牌,商品分类'
							+'&domain=Goods'//"productDateIssued":"'+modifyTime+'","productDateIssued_op":"gt",
							+'&queryParams="goodsBrand.objId":"'+$("#brandId").val()+'","goodsClass.objId":"'+$("#classId").val()+'","productDateIssued":"'+modifyTime+'","productDateIssued_op":">"';
				$.epsDialog({
					id:'GoodsSelect',
			        title:'请选择商品',
			        url:$('#initPath').val()+'/view/agreement/mamagement/object_select_list.jsp?'+params,
			        width: '700',
			        height: '400',
			        onClose: function(){ 
			        	//根据选择的商品id添加为到相应的协议商品
			        	if(""!=$("#goodsIds").val()&&null!=$("#goodsIds").val()){
			        		authorizedCategoryOrGoods.addAgreementGoods($("#goodsIds").val(),$("#AclassId").val(),'00');
			        		$("#goodsIds").val("");
			        	}
			        }
				});
			});
			
		});
	};
	
}


//添加协议单品 
authorizedCategoryOrGoods.addAgreementGoods = function(goodsIds,agreementClassId,agreementType){
	$.getJSON(
			$('#initPath').val()+'/AgreementGoodsController.do?method=addAgreementNewGoods',
			{
				"goodsIds":goodsIds,
				"agreementClassId":agreementClassId,
				"agreementType":agreementType,
				"brandId":$("#brandId").val(),
				"classId":$("#classId").val()
			},
			function(json){
				if(json.success){
					authorizedCategoryOrGoods.getClassTableAndTips();
				}
	});
}



$(document).ready(function(){
	//加载tabs
	$('#authorizedVenderTabs').tabs();
	
	//切换tab页
	$('#authorizedVenderTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){
			authorizedCategoryOrGoods.getClassTableAndTips();
		}else{
			authorizedCategoryOrGoods.getGoodsTable($("#agreementId").val());
		}
	});
	//默认加载分类商品
	authorizedCategoryOrGoods.getClassTableAndTips();
});
