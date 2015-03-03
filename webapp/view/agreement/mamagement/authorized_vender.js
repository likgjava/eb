/*
 * 平台开发demo
 * author: yucy
 * mail: yuchengyang@yeah.net
 */
var authorizedVenderForm={};

authorizedVenderForm.oTable;

authorizedVenderForm.oTable2;

//弹出区间选择页面
authorizedVenderForm.selectArea = function(nodeId){
	/**参数:
	 * DialogId:弹出框Id供自动关闭使用
	 * className:实体名称
	 * Column：查询列
	 * defineRetuColums:回填表单Id
	 */
	var params = 'DialogId=selectArea&'
				+'className=Area'
				+'&Column=objId,areaName'
				+'&isOpen=1'
				+'&defineRetuColums=areaId,areaName'
				//+'&isCheckBox=true'
				+'&nodeId='+nodeId;
	$.epsDialog({
		id:"selectArea",
		title:'选择关联区间',
       url:$('#initPath').val()+'/view/agreement/mamagement/ObjectSelectTree.jsp?'+params,
       width: '700',
       height: '400'
   }); 
}

//删除类别
authorizedVenderForm.deleteClass = function(ids){
	$.getJSON($('#initPath').val()+'/AgreementGoodsclassController.do?method=removeAgreementGoodsclassAndGoods',
			{
				"agreementGoodsclassIds":ids
			},
			function(json){
					if(json.success){
						alert(json.result);
						//刷列表
						authorizedVenderForm.getTables($("#agreeSecondId").val());
					}
			}
	);
}

//授权类别
authorizedVenderForm.addClass = function(){
	/**
	 * 参数    
	 * DialogId:	弹出层的id 关闭使用
	 * isCheckBox:	是否复选（不传为单选）
	 * domain:		要查的实体名称
	 * colums:		列表显示属性:逗号分隔(需存在于指定的domain中)
	 * returnColums:需回填属性:逗号分隔(需存在于指定的domain中  且默认回填的form表单id等于指定的属性名称  单选)
	 * defineRetuColums:指定回填的表单Id(如指定    指定顺序同returnColums 要一一对应 如未指定 则需回填表单Id必须与returnColums一一对应)
	 * columCns:	列表显示的中文表头:中文逗号分隔(因为国际化并未规范 所以也要传)
	 */
	var params = 'defineRetuColums=goodsClassId' 
				+'&returnColums=objId'
				+'&DialogId=addcategorySelect'
				+'&isCheckBox=true'
				+'&colums=goodsClass.goodsClassName,brand.brandName'
				+'&columCns=类别名称,品牌名称'
				+'&domain=AgreementGoodsclass'
				+'&queryParams="agreement.objId":"'+$("#objId").val()+'"';
	$.epsDialog({
	   id:'addcategorySelect',
       title:'请选择商品分类',
       url:$('#initPath').val()+'/view/agreement/mamagement/object_select_list.jsp?'+params,
       width: '500',
       height: '400',
       onClose: function(){
    	   if(""!=$("#goodsClassId").val()&&null!=$("#goodsClassId").val()){
	       		$.getJSON($('#initPath').val()+'/AgreementGoodsclassController.do?method=authorizGoodsClass',
	    				{
	    					"goodsClassIds":$("#goodsClassId").val(),
	    					"agreeSecondId":$("#agreeSecondId").val()
	    				},
	    				function(json){
	    						if(json.success){
	    							alert(json.result);
	    							//刷新列表
	    							authorizedVenderForm.getTables($("#agreeSecondId").val());
	    							//清空
	    							$("#goodsClassId").val("");
	    						}
	    				}
	    		);
    	   }
       }
	}); 
	
}

//删除商品
authorizedVenderForm.deleteGoods = function(ids){
	$.getJSON($('#initPath').val()+'/AgreementGoodsController.do?method=deleteGoods',
			{
				"goodsIds":ids
			},
			function(json){
					if(json.success){
						
						alert(json.result);
						//刷列表
						authorizedVenderForm.getTables($("#agreeSecondId").val());
					}
			}
	);
}


//授权商品
authorizedVenderForm.addGoods = function(){
	/**
	 * 参数    
	 * DialogId:	弹出层的id 关闭使用
	 * isCheckBox:	是否复选（不传为单选）
	 * domain:		要查的实体名称
	 * colums:		列表显示属性:逗号分隔(需存在于指定的domain中)
	 * returnColums:需回填属性:逗号分隔(需存在于指定的domain中  且默认回填的form表单id等于指定的属性名称  单选)
	 * defineRetuColums:指定回填的表单Id(如指定    指定顺序同returnColums 要一一对应 如未指定 则需回填表单Id必须与returnColums一一对应)
	 * columCns:	列表显示的中文表头:中文逗号分隔(因为国际化并未规范 所以也要传)
	 */
	var params = 'defineRetuColums=goodsId' 
				+'&returnColums=objId'
				+'&DialogId=addGoodSelect'
				+'&isCheckBox=true'
				+'&colums=goods.productName,brand.brandName'
				+'&columCns=单品名称,品牌'
				+'&domain=AgreementGoods'
				+'&queryParams="agreement.objId":"'+$("#objId").val()+'"';
	$.epsDialog({
	   id:'addGoodSelect',
       title:'请选择商品',
       url:$('#initPath').val()+'/view/agreement/mamagement/object_select_list.jsp?'+params,
       width: '500',
       height: '400',
       onClose: function(){
    	   
    	   if(""!=$("#goodsId").val()&&null!=$("#goodsId").val()){
    		   
    		   $.getJSON($('#initPath').val()+'/AgreementGoodsController.do?method=authorizGoods',
	    				{
	    					"goodsIds":$("#goodsId").val(),
	    					"agreeSecondId":$("#agreeSecondId").val()
	    				},
	    				function(json){
	    						if(json.success){
	    							alert(json.result);
	    							//刷新列表
	    							authorizedVenderForm.getTables($("#agreeSecondId").val());
	    							//清空
	    							$("#goodsId").val("");
	    						}
	    				}
	    		);
    		   
    	   }
    	   
       }
	}); 
}


//新增供货商
authorizedVenderForm.addVender=function(){
	$('#conBody').loadPage($('#initPath').val()+"/AgreementController.do?method=toChooseClassBrand&toView=toVenderFormView&objId="+$("#objId").val()+"&resource=authorizedVender");
}

//返回
authorizedVenderForm.authorizedVenderReturn = function(){
	if(null!=$("#resource").val()&&""!=$("#resource").val()&&"maintainVender"==$("#resource").val()){
		//来源于维护供货商
		$('#conBody').loadPage($('#initPath').val()+"/view/agreement/mamagement/purchase_agreement_list.jsp");
	}else{
		//来源于供货商资格列表
		$('#conBody').loadPage($('#initPath').val()+"/view/agreement/mamagement/vender_qualification_list.jsp");
	}
}

//获取列表
authorizedVenderForm.getTables = function(agreementId){
	
	if(null==authorizedVenderForm.oTable){
		//不存在则创建
		authorizedVenderForm.oTable = $('#categoryList').dataTable( {
			'params':
			{
				"agreementSecond.objId":agreementId
			},
			'singleSelect':true,	
			'queryColumns':'goodsClass.goodsClassName,brand.brandName',
			'hiddenColumns':'objId',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
			},
			'fnRowCallback': function( nRow,aData,iDisplayIndex) {
				$(nRow).find('td[name=goodsClass.goodsClassName]').html('<a href="javascript:void(0);">'+aData["goodsClass.goodsClassName"]+'</a>');
				$(nRow).find('td[name=goodsClass.goodsClassName]').find('a').click(function(){
					//跳转到分类页面
					//$('#conBody').loadPage($('#initPath').val()+"/view/agreement/mamagement/authorized_category_goods.jsp?resource=category&agreementId="+agreementId);
					$('#conBody').loadPage($('#initPath').val()+"/AgreementController.do?method=toChooseClassBrand&resource=category&toView=toAuthorizedGoodsOrClassView&objId="+$("#objId").val());
				})
				$(nRow).append('<td><a href="javascript:void(0);" type="alink"><span>删除</span></a></td>')//添加修改按钮
				$(nRow).find('a:last').click(function(){
					authorizedVenderForm.deleteClass(aData.objId);
				});
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/AgreementGoodsclassController.do?method=list"
		});
		
		//不存在则创建
		authorizedVenderForm.oTable2 = $('#goodsList').dataTable( {
			'params':
			{
				"agreementSecond.objId":agreementId
			},
			'singleSelect':true,	
			'queryColumns':'goods.productName,goods.productCode',
			'hiddenColumns':'objId',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
			},
			'fnRowCallback': function( nRow,aData,iDisplayIndex) {
				
				$(nRow).find('td[name=goods.productName]').html('<a href="javascript:void(0);">'+aData["goods.productName"]+'</a>');
				$(nRow).find('td[name=goods.productName]').find('a').click(function(){
					//跳转到分类页面
					$('#conBody').loadPage($('#initPath').val()+"/view/agreement/mamagement/authorized_category_goods.jsp?resource=goods&agreementId="+agreementId);
					$('#conBody').loadPage($('#initPath').val()+"/AgreementController.do?method=toChooseClassBrand&resource=category&toView=toAuthorizedGoodsOrClassView&objId="+$("#objId").val());
				})
				
				$(nRow).append('<td><a href="javascript:void(0);" type="alink"><span>删除</span></a></td>')//添加修改按钮
				$(nRow).find('a:last').click(function(){
					authorizedVenderForm.deleteGoods(aData.objId);
				});
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/AgreementGoodsController.do?method=list"
		});
	}else{
		
		$(authorizedVenderForm.oTable.dataTableSettings).attr("params",{"agreementSecond.objId":agreementId});
		
		authorizedVenderForm.oTable.fnDraw();
		
		$(authorizedVenderForm.oTable2.dataTableSettings).attr("params",{"agreementSecond.objId":agreementId});
		
		authorizedVenderForm.oTable2.fnDraw();
	}
	
}

//加载二级协议信息
authorizedVenderForm.loadSecondInfo = function(secondId){
	$.getJSON($('#initPath').val()+'/AgreementSecondController.do?method=createOrUpdate',{objId:secondId,includedProperties:"area"},
	function(json){
			//显示
			$("#agreementSecondFormDiv").hide();
			//隐藏
			$("#agreementSecondDetail").show();
			
			json2Object("agreementSecond",json.agreementSecond);
				
			//回设二级协议id
			$("#agreeSecondId").val(secondId);
	
			//加载列表
			authorizedVenderForm.getTables(json.agreementSecond.objId);
	});
}

$(document).ready(function(){
	
	//供货期间
	$("#agreementSecondForm").find('input[id=beginDate]').epsDatepicker({});
	$("#agreementSecondForm").find('input[id=endDate]').epsDatepicker({});

	//选择区间(只能选择一级协议的区间及子区间)
	$("#area").click(function(){
		authorizedVenderForm.selectArea($("input[id=area.objId]").val());
	});
	
	//加载tab页并注册事件
	$.getJSON($('#initPath').val()+'/AgreementSecondController.do?method=getObjectQuery',
		{
			"agreement.objId":$("#objId").val(),
			queryColumns:"objId,supplyer.orgName"
		},
		function(json){
			
		//加载tab标签
		$.each(json.result,function(i,n){
			$("#agreementSecondTab").append('<li><a href="#employeeInfo" id = "'+n.objId+'"><span>'+n["supplyer.orgName"]+'</span></a></li>')
			.find("a:last").click(function(){
				authorizedVenderForm.loadSecondInfo(this.id);
			});
		});
		
		//加载tabs
		$('#authorizedVenderTabs').tabs();
		
		//加载二级协议信息(默认第一个)
		authorizedVenderForm.loadSecondInfo(json.result[0].objId);
		
		//是否选中某个供货商
		if(null!=$("#venderId").val()&&""!=$("#venderId").val()){
			$('a[id='+$("#venderId").val()+']').click();
		}
		
	});
	

	
	//新增供货商
	$("#addVender").click(function(){
		authorizedVenderForm.addVender();
	});
	
	//修改
	$("#modifyVender").click(function(){
		
		//显示
		$("#agreementSecondFormDiv").show();
		//隐藏
		$("#agreementSecondDetail").hide();
		
		//判断加载二级协议信息
		if(""!=$("#agreeSecondId").val()&&null!=$("#agreeSecondId").val()){
			$.getJSON($('#initPath').val()+'/AgreementSecondController.do?method=createOrUpdate',{objId:$("#agreeSecondId").val(),includedProperties:"supplyer,area"},function(json){
				jsonObjectToForm("agreementSecondForm",json.agreementSecond);
				
				//加载附件
				if(""!=json.agreementSecond.content&&null!=json.agreementSecond.content){
					$('#content').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=content&attachRelaId='+json.agreementSecond.content);
				}
				else{
					$('#content').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=content');
				}
				
			});
		}else{
			//无附件
			$('#content').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=content');
		}
	
	});
	
	//保存
	$("#saveVender").click(function(){
		
		var ObjectStr =formToJsonObject("agreementSecondForm","jsonToBean")
		ObjectStr.objId = $("#agreeSecondId").val();
		
		$.getJSON($('#initPath').val()+'/AgreementSecondController.do?method=saveAgreementSecondSimple',{agreementSecondStr:JSON.stringify(ObjectStr)},
			function(json){
				if(json.success){
					alert(json.result);
					//加载二级协议信息
					authorizedVenderForm.loadSecondInfo($("#agreeSecondId").val());
				}
			}
		);
	});
	
	//授权分类
	$("#addClass").click(function(){
		authorizedVenderForm.addClass();
	})
	
	//授权商品
	$("#addGoods").click(function(){
		authorizedVenderForm.addGoods();
	})
	
});
