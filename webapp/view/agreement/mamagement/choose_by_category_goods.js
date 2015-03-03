/*
 * 协议管理，协议期间页面
 * author: yucy
 * mail: yuchengyang@yeah.net
 */

//定义文件全局变量 处理方法名重复问题
var chooseGoodsByCategory={};

$(document).ready(function(){
	$('#epsTabs').tabs();

	//按分类
	$("#byCategoryClick").click(function(){
		$("#byCategory").loadPage($('#initPath').val()+"/view/agreement/mamagement/choose_goods_category.jsp");
	});
	
	//按单品
	$("#byGoodsClick").click(function(){
		$("#byGoods").loadPage($('#initPath').val()+"/view/agreement/mamagement/choose_goods_by_goods.jsp");
	});
	
	//默认按照分类
	$("#byCategoryClick").click();
	
	//上一步
	$("#chooseGoodsByCategoryPrev").click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/AgreementController.do?method=toChooseClassBrand&toView=toChooseClassBrandView&objId='+$("#objId").val());
	});
	
	//返回
	$("#chooseGoodsByCategoryReturn").click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/view/agreement/mamagement/purchase_agreement_list.jsp");
	});
});
	
