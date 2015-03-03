/*
 * 协议管理，协议期间页面
 * author: yucy
 * mail: yuchengyang@yeah.net
 */
//定义文件全局变量 处理方法名重复问题
var chooseGoodsByGoods={};
chooseGoodsByGoods.oTable;	

//新增事件
chooseGoodsByGoods.addGoods = function(){
	var trClass = "";
	//判断奇偶行
	if($("#chooseGoodsByGoods").find("tbody").find("tr:first").attr("class")=="odd"){
		trClass = "even";
	}else{
		trClass = "odd";
	}
	var newRow= $("#chooseGoodsByGoods").find("tbody").find("tr[objid=newClassAndBrandTr]");
	
	if(null!=newRow.html()){
		alert("请先保存新增的数据!");
	}else{
		$("#chooseGoodsByGoods").find("tbody").find("tr:first").before(
				'<tr objid="newClassAndBrandTr" class="'+trClass+'"><td class="center sorting_1">'
				+'<input type="checkbox" value="" name="checkAll"></td>'
				+'<td name="goods.productName">'
				+'<input type="hidden" name="goodsId" >'
				+'<input type="text" name="goodsName" style="width:180px">'
				+'</td>'
				+'<td name="goods.productCode">'
				+'<input type="text" name="goodsCode" style="width:150px">'
				+'</td>'
				+'<td name="marketPrice" class="right"><input type="text" style="width:50px" name="marketPrice" value="0"></td>'
				+'<td name="agreementPrice"  class="right"><input type="text" style="width:50px" name="agreementPrice" value="0"></td>'
				+'<td name="discountRatio" class="right"><input type="text" style="width:50px" name="discountRatio" value="0"></td>'
				+'<td class=""><a href="javascript:void(0);" name="newRowSave">保存</a></td></tr>');
		newRow= $("#chooseGoodsByGoods").find("tbody").find("tr[objid=newClassAndBrandTr]");
	}
	
	//点击计数器
	var clickCount = 0;
	//行点击事件
	newRow.click(function(){
		if(clickCount%2>0){
			$(this).removeClass("row_selected")
			$(this).find("input[type=checkbox]").attr("checked","");
		}else{
			$(this).addClass("row_selected")
			$(this).find("input[type=checkbox]").attr("checked","checked");
		}
		clickCount++;
	});
	
	//选择商品
	newRow.find("input[name=goodsName]").focus(function(){
		var params = {};
		if(null!=newRow.find("input[name=goodsCode]").val()&&""!=newRow.find("input[name=goodsCode]").val()){
			params = {"productCode":newRow.find("input[name=goodsCode]").val(),"productCode_op":"like"};
		}
		$(this).autocomplete(
				$('#initPath').val() + '/GoodsController.do?method=getObjectQuery&queryColumns=objId,productName,productCode,goodsClass.objId', 
				{
					matchColumn:'productName,productCode',//作为查询显示, 被选中之后匹配的列
					extraParams:params,
					minChars: 0,
					max: 8,
					autoFill: true,
					mustMatch: false,
					scrollHeight: 220,
					formatItem: function(data, i, total) {
						return '<I>'+data.productName+' '+data.productCode+'</I>';
					},
					formatMatch: function(data, i, total) {
						return data.productName;
					},
					formatResult: function(data) {
						return data.productName;
					}
				}
			).result(function(event,data,formatted){
				if(data){
					newRow.find("input[name=goodsId]").val(data.objId);//回填id
					newRow.find("input[name=goodsCode]").val(data.productCode);//回填id
				}
			});
	})
	
	//选择Code
	newRow.find("input[name=goodsCode]").autocomplete(
			$('#initPath').val() + '/GoodsController.do?method=getObjectQuery&queryColumns=objId,productName,productCode', 
			{
				matchColumn:'productCode,productName',//作为查询显示, 被选中之后匹配的列
				extraParams:{},
				minChars: 0,
				max: 8,
				autoFill: true,
				mustMatch: false,
				scrollHeight: 220,
				formatItem: function(data, i, total) {
					return '<I>'+data.productCode+' '+data.productName+'</I>';
				},
				formatMatch: function(data, i, total) {
					return data.productCode;
				},
				formatResult: function(data) {
					return data.productCode;
				}
			}
		).result(function(event,data,formatted){
			if(data){
				//newRow.find("input[name=goodsId]").val(data.objId);//回填id
				//newRow.find("td[name=goods.productCode]").html(data.productCode);//回填id
			}
		});
	
	//编辑市场价
	newRow.find('input[name=marketPrice]').change(function(){
		if(isNaN($(this).val()+'')){
			alert("请输入数字");
			return;
		}else{
			$(newRow).find('input[name=agreementPrice]').val(getPrtcPrice($(this).val(),$(newRow).find('input[name=discountRatio]').val()));
		}
	});
	//编辑协议价
	newRow.find('input[name=agreementPrice]').change(function(){
		var agreementPriceBefore = getPrtcPrice($(newRow).find('input[name=marketPrice]').val(),$(newRow).find('input[name=discountRatio]').val());   
		if(isNaN($(this).val()+'')||Number($(this).val())>agreementPriceBefore){
			alert("请输入数字且不得高于预期协议价!");
			$(this).val(agreementPriceBefore);
		}else{
			$(newRow).find('input[name=discountRatio]').val(getDscuRate($(newRow).find('input[name=marketPrice]').val(),$(this).val()));
		}
	});
	
	//编辑折扣率
	newRow.find('input[name=discountRatio]').change(function(){
		if(isNaN($(this).val()+'')||Number($(this).val())>100){
			alert("请输入数字且不得高于100!");
			$(this).val(100);
		}else{
			$(newRow).find('input[name=agreementPrice]').val(getPrtcPrice($(newRow).find('input[name=marketPrice]').val(),$(this).val()));
		}
	});
	
	//操作事件(保存)
	newRow.find("a[name=newRowSave]").click(function(){
		if(0>=newRow.find("input[name=marketPrice]").val()||0>=newRow.find("input[name=agreementPrice]").val()||0>=newRow.find("input[name=discountRatio]").val()){
			alert("请完善数据!");
			return;
		}
		var goods = {};
		goods['agreement.objId'] = $("#objId").val();
		goods['goods.objId'] = newRow.find("input[name=goodsId]").val();
		goods.marketPrice =newRow.find("input[name=marketPrice]").val().replace(/,/g,'');
		goods.agreementPrice = newRow.find("input[name=agreementPrice]").val().replace(/,/g,'');
		goods.discountRatio =  newRow.find("input[name=discountRatio]").val().replace(/,/g,'');
		chooseGoodsByGoods.saveRow(goods,true);
	});
}

//修改每行
chooseGoodsByGoods.saveRow = function(agreeGoods,isAlert){
	$.getJSON(
			$('#initPath').val()+'/AgreementGoodsController.do?method=saveAgreementGoods',
			agreeGoods,
			function(json){
				if(json.success){
					if(isAlert){
						alert(json.result);
						chooseGoodsByGoods.oTable.fnDraw();
					}
				}
			}
	);
}

//删除协议商品
chooseGoodsByGoods.del = function(goodIds){
	if(confirm("确定删除?")){
		var ids = goodIds.split(",").toString();
		if(ids.indexOf('newClassAndBrandTr')>=0){
			ids = ids.replace(',newClassAndBrandTr','');
			ids = ids.replace('newClassAndBrandTr,','');
			ids = ids.replace('newClassAndBrandTr','');
		}
		$.getJSON(
				$('#initPath').val()+'/AgreementGoodsController.do?method=deleteGoods',
				{
					"goodsIds":ids
				},
				function(json){
					if(json.success){
						alert("删除成功!");
						chooseGoodsByGoods.getGoodsTableByGoods();
					}
				}
		);
	}
}

//获得协议单品TableList
chooseGoodsByGoods.getGoodsTableByGoods = function(agreementId){
	//不存在列表则创建
	if(null==chooseGoodsByGoods.oTable||""==chooseGoodsByGoods.oTable){
		//创建列表
		chooseGoodsByGoods.oTable = $('#chooseGoodsByGoods').dataTable( {
			'singleSelect':false,	
			'checkbox':true,		
			'queryColumns':'goods.productName,goods.productCode,marketPrice,agreementPrice,discountRatio',
			'hiddenColumns':'objId,goods.objId,goods.goodsClass.goodsClassName,goods.goodsBrand.brandName',
			//'hiddenColumns':'objId,goods.objId,goods.goodsBrand.brandName,goods.goodsClass.remarks',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
			},
			'fnRowCallback': function( nRow,aData,iDisplayIndex) {
				//市场价编辑
				var column3=$(nRow).find('td:eq(3)');
				column3.html('<span name="marketPrice">'+aData['marketPrice']+'</span><input class="hidden" style="width:50px" name="marketPrice" type="text" value="'+aData['marketPrice']+'"/>');//可以加ID或者onClick
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
				column4.html('<span name="agreementPrice">'+aData['agreementPrice']+'</span><input class="hidden" style="width:50px"  name="agreementPrice" type="text" value="'+aData['agreementPrice']+'"/>');//可以加ID或者onClick
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
				column5.html('<span name="discountRatio">'+aData['discountRatio']+'</span><input class="hidden" style="width:50px"  name="discountRatio" type="text" value="'+aData['discountRatio']+'"/>');//可以加ID或者onClick
				column5.find('input[name=discountRatio]').change(function(){
					if(isNaN($(this).val()+'')||Number($(this).val())>100){
						alert("请输入数字且不得高于100!");
						$(this).val(100);
					}else{
						$(nRow).find('input[name=agreementPrice]').val(getPrtcPrice($(nRow).find('input[name=marketPrice]').val(),$(this).val()));
					}
				});
				

				//商品选择
				var column1=$(nRow).find('td:eq(1)');
				//code
				var column2=$(nRow).find('td:eq(2)');
				
				column1.html('<span name="goodsName">'+aData['goods.productName']+'</span><input type="hidden" name="goodsId" value="'+aData['goods.objId']+'"><input type="text" class="hidden" name="goodsName" value="'+aData['goods.productName']+'" style="width:180px">');
				column2.html('<span name="goodsCode">'+aData['goods.productCode']+'</span><input type="text" class="hidden" name="goodsCode" value="'+aData['goods.productCode']+'" style="width:150px">');
				
				column1.find('input[name=goodsName]').focus(function(){
					var params = {};
					if(null!=column1.find('input[name=goodsName]').val()){
						params = {"productCode":column2.find('input[name=goodsCode]').val(),"productCode_op":"like"};
					}
					$(this).autocomplete(
							$('#initPath').val() + '/GoodsController.do?method=getObjectQuery&queryColumns=objId,productName,productCode', 
							{
								matchColumn:'productName,productCode',//作为查询显示, 被选中之后匹配的列
								extraParams:params,
								minChars: 0,
								max: 8,
								autoFill: true,
								mustMatch: false,
								scrollHeight: 220,
								formatItem: function(data, i, total) {
									return '<I>'+data.productName+' '+data.productCode+'</I>';
								},
								formatMatch: function(data, i, total) {
									return data.productName;
								},
								formatResult: function(data) {
									return data.productName;
								}
							}
						).result(function(event,data,formatted){
							if(data){
								column1.find("span[name=goodsName]").html(data.productName)
								column1.find("input[name=goodsId]").val(data.objId);//回填id
								column2.find('input[name=goodsCode]').val(data.productCode);
								column2.find('span[name=goodsCode]').html(data.productCode);
							}
						});
				});
				
				column2.find('input[name=goodsCode]').autocomplete(
						$('#initPath').val() + '/GoodsController.do?method=getObjectQuery&queryColumns=objId,productName,productCode', 
						{
							matchColumn:'productCode,productName',//作为查询显示, 被选中之后匹配的列
							extraParams:{},
							minChars: 0,
							max: 8,
							autoFill: true,
							mustMatch: false,
							scrollHeight: 220,
							formatItem: function(data, i, total) {
								return '<I>'+data.productCode+' '+data.productName+'</I>';
							},
							formatMatch: function(data, i, total) {
								return data.productCode;
							},
							formatResult: function(data) {
								return data.productCode;
							}
						}
					).result(function(event,data,formatted){
						if(data){
						}
					});
				
				//行末加操作按钮
				$(nRow).append('<td class="operation"><a type="alink" name="rowSave" class="hidden" href="javascript:void(0);"><span>保存</span></a>'
						+'<a type="alink" name="rowModify" href="javascript:void(0);"><span>修改</span></a>'
						+'</td>');
				
				//点击修改
				$(nRow).find('a[name=rowModify]').click(function(){
					$(nRow).find('input[name=marketPrice],input[name=agreementPrice],input[name=discountRatio],a[name=rowSave],input[name=goodsName],input[name=goodsCode]').show();
					$(nRow).find('span[name=marketPrice],span[name=agreementPrice],span[name=discountRatio],a[name=rowModify],span[name=goodsName],span[name=goodsCode]').hide();
				});
				
				//点击保存
				$(nRow).find('a[name=rowSave]').click(function(){
					var marketPrice = $(nRow).find('input[name=marketPrice]').val().replace(/,/g,'');
				    var agreementPrice = $(nRow).find('input[name=agreementPrice]').val().replace(/,/g,'');
					var discountRatio = $(nRow).find('input[name=discountRatio]').val().replace(/,/g,'');
					if(0>=marketPrice||0>=agreementPrice||0>=discountRatio){
						alert("请完善数据！");
					}else{
						$(nRow).find('span[name=marketPrice]').html(marketPrice);
						$(nRow).find('span[name=agreementPrice]').html(agreementPrice);
						$(nRow).find('span[name=discountRatio]').html(discountRatio);
						
						$(nRow).find('input[name=marketPrice],input[name=agreementPrice],input[name=discountRatio],a[name=rowSave],input[name=goodsName],input[name=goodsCode]').hide();
						$(nRow).find('span[name=marketPrice],span[name=agreementPrice],span[name=discountRatio],a[name=rowModify],span[name=goodsName],span[name=goodsCode]').show();
						
						var agreegoods = {};
						agreegoods['agreement.objId'] = $("#objId").val();
						agreegoods['goods.objId'] = column1.find('input[name=goodsId]').val();
						agreegoods.objId = aData.objId;
						agreegoods.marketPrice =marketPrice;
						agreegoods.agreementPrice = agreementPrice;
						agreegoods.discountRatio = discountRatio;
						chooseGoodsByGoods.saveRow(agreegoods);
					}
				});
				
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/AgreementGoodsController.do?method=list&agreement.objId="+agreementId
		});
		
	}else{
		//存在则改变参数刷新数据
		chooseGoodsByGoods.oTable.fnDraw();
	}
}

$(document).ready(function(){
	
	//初始化表格
	chooseGoodsByGoods.getGoodsTableByGoods($("#objId").val());
	
	//过滤品牌
	$("#brandName").focus(function(){
		var params = {};
		if(null!=$("#brandParam").val()&&""!=$("#brandParam").val()){
			params = {
					"objId":$("#brandParam").val(),
					"objId_op":"in"
			};
		}
		$("#brandName").autocomplete(
				$('#initPath').val() + '/GoodsBrandController.do?method=getObjectQuery&queryColumns=objId,brandName,englishName,shortSpellName,brandCode', 
				{
					matchColumn:'brandName,brandCode',//作为查询显示, 被选中之后匹配的列
					extraParams:params,
					minChars: 0,
					max: 8,
					autoFill: true,
					mustMatch: false,
					scrollHeight: 220,
					formatItem: function(data, i, total) {
						return '<I>'+data.brandName+' '+data.brandCode+'</I>';
					},
					formatMatch: function(data, i, total) {
						return data.brandName;
					},
					formatResult: function(data) {
						return data.brandName;
					}
				}
			).result(function(event,data,formatted){
				if(data){
					$("#brandId").val(data.objId);//回填id
					$.getJSON($('#initPath').val()+'/AgreementGoodsController.do?method=getBrandSelectParam',{goodsBrandId:data.objId},function(json){
						if(json.success){
							//只有一个结果
							if(json.list.length==1){
								$("#goodsClassId").val(json.list[0][0]);
								$("#goodsClassName").val(json.list[0][1]);
								$("#goodsClassParam").val(json.list[0][0]);
							}else{
								var paramId = '';
								for(var i=0;i<json.list.length;i++){
									paramId += json.list[i][0];
									paramId += i==(json.list.length-1)?"":",";
								}
								$("#goodsClassParam").val(paramId);
							}
						}
					});
				}else{
					alert("无结果");
				}
			});
	});
	
	//过滤条件清除
	$("#brandName").keyup(function(){
		if(null==$(this).val()||''==$(this).val()){
			$("#brandId").val("");
			$("#goodsClassParam").val("");
		}
	});
	
	//过滤分类
	$("#goodsClassName").focus(function(){
		var params = {};
		if(null!=$("#goodsClassParam").val()||""!=$("#goodsClassParam").val()){
			params = {
					"objId":$("#goodsClassParam").val(),
					"objId_op":"in"
			}
		}
		
		$("#goodsClassName").autocomplete(
				$('#initPath').val() + '/GoodsClassController.do?method=getObjectQuery&queryColumns=objId,goodsClassName,goodsClassCode,shortSpellName', 
				{
					matchColumn:'goodsClassName,goodsClassCode',//作为查询显示, 被选中之后匹配的列
					extraParams:params,
					minChars: 0,
					max: 8,
					autoFill: true,
					mustMatch: false,
					scrollHeight: 220,
					formatItem: function(data, i, total) {
						return '<I>'+data.goodsClassName+' '+data.goodsClassName+'</I>';
					},
					formatMatch: function(data, i, total) {
						return data.goodsClassName;
					},
					formatResult: function(data) {
						return data.goodsClassName;
					}
				}
			).result(function(event,data,formatted){
				if(data){
					$("#goodsClassId").val(data.objId);//回填id
					$.getJSON($('#initPath').val()+'/AgreementGoodsController.do?method=getBrandSelectParam',{goodsClassId:data.objId},function(json){
						if(json.success){
							if(json.list.length==1){
								$("#brandId").val(json.list[0][0]);
								$("#brandName").val(json.list[0][1]);
								$("#brandParam").val(json.list[0][0]);
							}else{
								var paramId = '';
								for(var i=0;i<json.list.length;i++){
									paramId += json.list[i][0];
									paramId += i==(json.list.length-1)?"":",";
								}
								$("#brandParam").val(paramId);
							}
						}
					});
				}else{
					alert("无结果");
				}
			});
	});
	
	//过滤条件清除
	$("#goodsClassName").keyup(function(){
		if(null==$(this).val()||''==$(this).val()){
			$("#goodsClassId").val("");
			$("#brandParam").val("");
		}
	});
	
	//点击搜索
	$("#chooseGoods").click(function(){
		$(chooseGoodsByGoods.oTable.dataTableSettings).attr("params",{
			"goods.goodsBrand.brandName":$("#brandName").val(),
			"goods.goodsBrand.brandName_op":"like",
			"goods.goodsBrand.brandName_relative":"[and:or]",
			
			"goods.goodsClass.goodsClassName":$("#goodsClassName").val(),
			"goods.goodsClass.goodsClassName_op":"like",			
			"goods.goodsClass.goodsClassName_relative":"[and:or]"
		});
		chooseGoodsByGoods.oTable.fnDraw();
	})
	
	//点击新增
	$("#addGoods").click(function(){
		chooseGoodsByGoods.addGoods();
	});
	
	//批量删除
	$("#goodsByGoodsDel").click(function(){
		chooseGoodsByGoods.del($('#chooseGoodsByGoods').dtSelects());
	})
	
});
	
